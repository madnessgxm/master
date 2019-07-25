package com.xml;

import android.util.Log;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * User: guoxiaomeng
 * Date: 2019-07-25
 * Time: 14:43
 */
public class XmlUtil<T extends XmlObj> {

    /**
     * 事例类：
     * public class CRY
     * {
     *     public CRY(){};
     *     private String RY_Bh;//
     *
     *     public String getRY_Bh() {
     *         return RY_Bh;
     *     }
     *
     *     public void setRY_Bh(String RY_Bh) {
     *         this.RY_Bh = RY_Bh;
     *     }
     *
     *     public String getRY_Name() {
     *         return RY_Name;
     *     }
     *
     *     public void setRY_Name(String RY_Name) {
     *         this.RY_Name = RY_Name;
     *     }
     *
     *     public String getBM() {
     *         return BM;
     *     }
     *
     *     public void setBM(String BM) {
     *         this.BM = BM;
     *     }
     *
     *     public String getIcid() {
     *         return icid;
     *     }
     *
     *     public void setIcid(String icid) {
     *         this.icid = icid;
     *     }
     *
     *     private String RY_Name;
     *     private String BM;
     *     private String icid;
     * }
     *
     *  事例及测试
     *  String tmpstr = "<root>" +"<tab>" +
     *                       "        <RY_Bh>01</RY_Bh>" +
     *                       "        <RY_Name>周扬升</RY_Name>" +
     *                       "        <BM>蜘蛛人</BM>" +
     *                       "        <icid>CA000001</icid>" +
     *                       "      </tab>" +
     *                       "      <tab>" +
     *                       "        <RY_Bh>02</RY_Bh>" +
     *                       "        <RY_Name>陈言翰</RY_Name>" +
     *                       "        <BM>蜘蛛人</BM>" +
     *                       "        <icid>CA000002</icid>" +
     *                       "      </tab>" +
     *                       "      <tab>" +
     *                       "        <RY_Bh>03</RY_Bh>" +
     *                       "        <RY_Name>廖秋铭</RY_Name>" +
     *                       "        <BM>蜘蛛人</BM>" +
     *                       "        <icid>CA000003</icid>" +
     *                       "      </tab>" +
     *                       "      <tab>" +
     *                       "        <RY_Bh>04</RY_Bh>" +
     *                       "        <RY_Name>许骥</RY_Name>" +
     *                       "        <BM>蜘蛛人</BM>" +
     *                       "        <icid>CA000004</icid>" +
     *                       "      </tab>" +
     *                       "      <tab>" +
     *                       "        <RY_Bh>05</RY_Bh>" +
     *                       "        <RY_Name>朱红源</RY_Name>" +
     *                       "        <BM>蜘蛛人</BM>" +
     *                       "        <icid>CA000005</icid>" +
     *                       "      </tab>"+
     *                       "</root>";
     *
     *               try {
     *                   XmlUtil<CRY> xmlUtil = new XmlUtil<>();
     *                   List<CRY> cries =  xmlUtil.getLst(CRY.class,tmpstr);
     *                   Log.d(TAG,cries.size()+"") ;
     *               }catch (Exception ex)
     *               {
     *                   ex.printStackTrace();
     *               }
     *
     *
     *
     */

    private static final String TAG = XmlUtil.class.getSimpleName();

    /*
     * 利用泛型解析XML 列表，并返回泛型列表*/
    public <T extends XmlObj> List<T> getLst(Class clazz, NodeList nodelst) {
        List<T> list = new ArrayList<>();
        try {
            for (int j = 0; j < nodelst.getLength(); j++) {
                Node node = nodelst.item(j);
                T o = (T) clazz.newInstance();
                NodeList nodeList = node.getChildNodes();
                for (int i = 0; i < nodeList.getLength(); i++) {
                    Node xe1 = nodeList.item(i);
                    Field field = clazz.getDeclaredField(xe1.getNodeName());
                    if(field!=null) {
                        Method method = clazz.getMethod("set" + xe1.getNodeName().substring(0, 1).toUpperCase() + xe1.getNodeName().substring(1), field.getType());
                        method.setAccessible(true);
                        if (xe1.getFirstChild() != null) {
                            method.invoke(o, xe1.getFirstChild().getNodeValue());
                        }
                    }
                }
                list.add(o);
            }
        } catch (Exception ex) {
            Log.e(TAG, ex.getMessage());
            ex.printStackTrace();
        }
        return list;
    }

    /*
     * 利用泛型解析XML 列表，并返回泛型列表*/
    public <T extends XmlObj> List<T> getLst(Class clazz, String xmlstr) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = null;
            builder = factory.newDocumentBuilder();
            org.w3c.dom.Document document = null;
            InputStream in = new ByteArrayInputStream(xmlstr.replace(" ", "").getBytes());
            document = builder.parse(in);
            Element root = document.getDocumentElement();
            NodeList nodeList = root.getChildNodes();
            return getLst(clazz, nodeList);

        } catch (Exception ex) {
            Log.e(TAG, ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }
}
