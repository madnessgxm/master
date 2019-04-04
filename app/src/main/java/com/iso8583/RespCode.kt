package com.iso8583

import java.util.HashMap

/**
 * Created by guoxiaomeng on 2017/6/23.
 */

class RespCode @JvmOverloads constructor(code: String?, private val errInfo: String? = null) {
    var s: String?=""
    var OrderNo: String? = null
    var QRcode: String? = null
    var isUndoErr: Boolean = false

    val errorMsg: String
        get() {
            val i = this.s!!.lastIndexOf(":")
            return this.s!!.substring(i + 1)
        }

    val code: String
        get() {
            val i = this.s!!.indexOf(":")
            return this.s!!.substring(0, i)
        }

    val flag: Char
        get() = this.s!![3]

    val info: String
        get() {
            val i = this.s!!.lastIndexOf(":")
            return this.s!!.substring(i + 1)
        }

    init {
        var code = code
        if (code != null && code.length > 0) {
            if (code.length > 2) {
                this.s = code
                //return
            }
             else if (code.length != 2) {
                code = "FE"
            } else if ("++" == code) {
                this.s = "00:A 冲正成功"
                //return
            }
        } else {
            code = "FF"
        }

        var t: String? = map.get(code)
        if (t == null) {
            t = "$code:C:交易失败"
        }

        this.s = t
    }

    override fun toString(): String {
        return if (this.isUndoErr) "冲正失败:" + this.s + ",请联系发卡行" else this.errInfo ?: this.s.toString()
    }

    companion object {

        private val ES = "00:A:交易成功;01:C:请持卡人与发卡银行联系;03:C:无效商户;04:D:此卡为无效卡;05:C:持卡人认证失败;06:C:错误;10:A:显示部分批准金额，提示操作员;11:A:成功，VIP 客户;12:C:无效交易;13:B:无效金额;14:B:无效卡号;15:C:此卡无对应发卡方;21:C:该卡未初始化或睡眠卡;22:C:操作有误，或超出交易允许天数;25:C:没有原始交易，请联系发卡方;30:C:请重试;34:D:作弊卡，呑卡;38:D:密码错误次数超限，请与发卡方联系;40:C:发卡方不支持的交易类型;41:D:挂失卡（POS）;43:D:被窃卡（POS）;51:D:可用余额不足;54:D:该卡已过期;55:C:密码错;57:C:不允许此卡交易;58:C:发卡方不允许该卡在本终端进行此交易;59:C:卡片校验错;61:C:交易金额超限;62:C:受限制的卡;64:C:交易金额与原交易不匹配;65:C:超出取款次数限制;68:C:交易超时，请重试;75:C:密码错误次数超限;77:B:请重新签到后再交易;90:C:系统日切，请稍后重试;91:C:发卡方状态不正常，请稍后重试;92:C:发卡方线路异常，请稍后重试;94:C:拒绝，重复交易，请稍后重试;96:C:拒绝，交换中心异常，请稍后重试;97:D:终端号未登记;98:E:发卡方超时;99:B:PIN格式错，请重新签到;A0:B:MAC校验错，请重新签到;A1:C:转账货币不一致;A2:A:交易成功，请向资金转入行确认;A3:C:资金到账行账号不正确;A4:A:交易成功，请向资金到账行确认;A5:A:交易成功，请向资金到账行确认;A6:A:交易成功，请向资金到账行确认;A7:C:安全处理失败;FF:C:网络连接故障;F2:文件记录重复;"
        private val map = HashMap<String,String>()

        init {
            val ss = "00:A:交易成功;01:C:请持卡人与发卡银行联系;03:C:无效商户;04:D:此卡为无效卡;05:C:持卡人认证失败;06:C:错误;10:A:显示部分批准金额，提示操作员;11:A:成功，VIP 客户;12:C:无效交易;13:B:无效金额;14:B:无效卡号;15:C:此卡无对应发卡方;21:C:该卡未初始化或睡眠卡;22:C:操作有误，或超出交易允许天数;25:C:没有原始交易，请联系发卡方;30:C:请重试;34:D:作弊卡，呑卡;38:D:密码错误次数超限，请与发卡方联系;40:C:发卡方不支持的交易类型;41:D:挂失卡（POS）;43:D:被窃卡（POS）;51:D:可用余额不足;54:D:该卡已过期;55:C:密码错;57:C:不允许此卡交易;58:C:发卡方不允许该卡在本终端进行此交易;59:C:卡片校验错;61:C:交易金额超限;62:C:受限制的卡;64:C:交易金额与原交易不匹配;65:C:超出取款次数限制;68:C:交易超时，请重试;75:C:密码错误次数超限;77:B:请重新签到后再交易;90:C:系统日切，请稍后重试;91:C:发卡方状态不正常，请稍后重试;92:C:发卡方线路异常，请稍后重试;94:C:拒绝，重复交易，请稍后重试;96:C:拒绝，交换中心异常，请稍后重试;97:D:终端号未登记;98:E:发卡方超时;99:B:PIN格式错，请重新签到;A0:B:MAC校验错，请重新签到;A1:C:转账货币不一致;A2:A:交易成功，请向资金转入行确认;A3:C:资金到账行账号不正确;A4:A:交易成功，请向资金到账行确认;A5:A:交易成功，请向资金到账行确认;A6:A:交易成功，请向资金到账行确认;A7:C:安全处理失败;FF:C:网络连接故障;F2:文件记录重复;".split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val var3 = ss.size

            for (var2 in 0 until var3) {
                val s = ss[var2]
                val i = s.indexOf(":")
                if (i > 0) {
                    map.put(s.substring(0, 2), s)
                }
            }

        }
    }
}
