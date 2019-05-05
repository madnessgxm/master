package com.xiaomi;

import com.facebook.common.util.Hex;

import ui.wangpos.com.utiltool.DESUitl;
import ui.wangpos.com.utiltool.HEXUitl;

public class test {

    public static String getTransKey(String workKey, String random) {//这里的workKey参数即前面提到的MK，random即前面提到的分散数据，都是16进制的数据
        if (null == workKey || 32 != workKey.length() || null == random || 16 != random.length()) {
            return null;
        }

        try {
            // 计算过程密钥左8字节
            byte[] byteKey = HEXUitl.hexToBytes(workKey);//Hex类为专门做byte与Hex转换，此处不贴代码了，网上很多，若不愿做转化可直接传入byte数组作为参数
            byte[] byteRandomRight = new byte[8];
            byte[] byteRandomLeft = HEXUitl.hexToBytes(random);
            for (int i = 0; i < byteRandomRight.length; i++) {
                byteRandomRight[i] = (byte) ~byteRandomLeft[i];
            }

            byte[] wkLeft = DESUitl.autoEncrypt(byteKey, byteRandomLeft);
            byte[] wkRight = DESUitl.autoEncrypt(byteKey, byteRandomRight);
            byte[] result = new byte[16];
            System.arraycopy(wkLeft, 0, result, 0, 8);
            for (int i = 0; i < byteRandomRight.length; i++) {
                result[i] = (byte) (wkLeft[i] | wkRight[i]);
            }
           /* for (int i = 0; i < wkLeft.length; i++) {
                result[i] = wkLeft[i];
            }*/

           /* for (int i = 8; i < result.length; i++) {
                result[i] = wkRight[i - 8];
            }*/

            return HEXUitl.bytesToHex(result);
        } catch (Exception e) {
        }

        return null;
    }

}
