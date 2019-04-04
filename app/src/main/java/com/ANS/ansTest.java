package com.ANS;

import android.util.Log;

import com.facebook.common.util.Hex;
import com.hierynomus.asn1.annotations.ASN1;
import com.hierynomus.asn1.encodingrules.der.DEREncoder;
import com.hierynomus.asn1.types.ASN1Tag;
import com.hierynomus.asn1.types.ASN1TagClass;
import com.hierynomus.asn1.types.constructed.ASN1Sequence;
import com.hierynomus.asn1.types.constructed.ASN1TaggedObject;
import com.hierynomus.asn1.types.primitive.ASN1Integer;
import com.hierynomus.asn1.types.string.ASN1BitString;
import com.hierynomus.asn1.types.string.ASN1OctetString;
import com.hierynomus.asn1.types.string.ASN1String;
import com.iso8583.Field;

import org.asnlab.asndt.runtime.conv.AsnConverter;
import org.asnlab.asndt.runtime.conv.EncodingRules;
import org.asnlab.asndt.runtime.type.AsnModule;
import org.asnlab.asndt.runtime.type.AsnType;
import org.asnlab.asndt.runtime.type.BitStringType;
import org.asnlab.asndt.runtime.type.Buffer;
import org.asnlab.asndt.runtime.type.Codec;
import org.asnlab.asndt.runtime.type.IA5String;
import org.asnlab.asndt.runtime.type.TaggedType;
import org.asnlab.asndt.runtime.value.BitString;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Null;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OutputStream;
import org.bouncycastle.asn1.ASN1StreamParser;
import org.bouncycastle.asn1.DERInteger;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.DEROutputStream;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERSequenceParser;
import org.bouncycastle.asn1.DERUTF8String;
import org.bouncycastle.asn1.cms.Time;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.security.Security;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import wangpos.sdk4.libbasebinder.HEX;

public class ansTest {
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static void main(String[] args) {


       /* try
        {

            Integer orderId = 1;
            String itemName = "苹果";
            int number = 3;
            float amount = 15.0f;
            Time genTime = new Time(new Date());
            try {
                TBSOrder tbsOrder = new TBSOrder(
                        new DERInteger(orderId),
                        new BitString(BitStringType.CLASS_PRIVATE),
                        new DERInteger(number),
                        new DERUTF8String(String.valueOf(amount)),
                        genTime);
                TBSOrder tbsOrder1 = new TBSOrder(
                        new DERInteger(2),
                        new BitString(BitStringType.CLASS_PRIVATE),
                        new DERInteger(number),
                        new DERUTF8String(String.valueOf(amount)),
                        genTime);
                //将结果进行b64编码
                TBSOrder tbsOrder2 = new TBSOrder(
                        new DERInteger(3),
                        new BitString(BitStringType.CLASS_PRIVATE),
                        new DERInteger(number),
                        new DERUTF8String(String.valueOf(amount)),
                        genTime);

                String orderB64 =HEX.bytesToHex(tbsOrder.getEncoded());
                System.out.println("result:"+ orderB64);
                 orderB64 =HEX.bytesToHex(tbsOrder.getEncoded());
                System.out.println("result:"+ orderB64);

                ASN1EncodableVector encodableVector = new ASN1EncodableVector();
                encodableVector.add(tbsOrder);


                BitString bitString = new BitString(BitStringType.CLASS_PRIVATE);


                encodableVector.add(tbsOrder1);

                encodableVector.add(tbsOrder2);
                DERSequence derSequence = new DERSequence(encodableVector);

                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                //创建ByteArrayOutputStream，用于放置输出的byte流
                DEROutputStream derOutputStream = new DEROutputStream(outputStream);
                //ASN1EncodableVector封装为DERSequence
                derOutputStream.writeObject(derSequence);
                //写入DERSequence数据。

                derOutputStream.flush();

                System.out.println(HEX.bytesToHex(outputStream.toByteArray()));


                //DERSequenceParser // new DERSequenceParser(asn1StreamParser);

              *//* //重新测试
                ASN1EncodableVector asn1EncodableVector = new ASN1EncodableVector();

                asn1EncodableVector.add(new DLTaggedObject(true,0,tbsOrder));
                DERSequence derSequence1 = new DERSequence(asn1EncodableVector);
                derOutputStream.writeObject(derSequence1);

                System.out.println("test "+HEX.bytesToHex(derSequence1.getEncoded()));
                System.out.println("dhdld "+HEX.bytesToHex(outputStream.toByteArray()));


                ASN1TaggedObject asn1TaggedObject  = new DERTaggedObject();
*//*
            } catch (Exception e) {
                e.printStackTrace();
            }

        }catch (Exception ex)
        {
            ex.printStackTrace();
        }*/
        try {
            ByteArrayOutputStream an1output = new ByteArrayOutputStream();
            com.hierynomus.asn1.ASN1OutputStream asn1OutputStream = new com.hierynomus.asn1.ASN1OutputStream(new DEREncoder(), an1output);
            asn1OutputStream.writeObject(new ASN1OctetString("nihao".getBytes()));
            asn1OutputStream.flush();

            System.out.println(HEX.bytesToHex(an1output.toByteArray()));

            List<com.hierynomus.asn1.types.ASN1Object> list = new ArrayList<>();

            list.add(new ASN1TaggedObject(ASN1Tag.OCTET_STRING,new ASN1OctetString("nihao".getBytes()) ,false));

            ASN1Sequence asn1Objects = new ASN1Sequence(list);

            asn1OutputStream.writeObject(asn1Objects);
            asn1OutputStream.flush();

            System.out.print(HEX.bytesToHex(an1output.toByteArray()));
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        /*try
        {




            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            //创建ByteArrayOutputStream，用于放置输出的byte流
            DEROutputStream derOutputStream = new DEROutputStream(outputStream);
            //创建DEROutputStream
            //derOutputStream.writeObject(new DERInteger(10));
            //写入DERInteger数据，10对应的hex为0a。
            //derOutputStream.writeObject(new DERBoolean(false));
            //写入DERBoolean数据，false对应asn1中的hex为00

            DERObject derObject = new DERObject();
            derObject.setModel(100);
            derObject.setTest(1);
            ASN1EncodableVector a = new ASN1EncodableVector();
            a.add(new DERPrintableString("PP"));
            ASN1EncodableVector encodableVector = new ASN1EncodableVector();
            //创建ASN1EncodableVector，用于放Sequence的数据
            encodableVector.add(new DERPrintableString("PP"));
            //encodableVector中写入各种对象
            encodableVector.add(new DERUTCTime(new Date()));
            encodableVector.add(new DERNull());
            encodableVector.add(derObject);
            a.addAll(encodableVector);

            DERSequence derSequence = new DERSequence(a);
            //ASN1EncodableVector封装为DERSequence
            derOutputStream.writeObject(derSequence);
            //写入DERSequence数据。

            derOutputStream.flush();
            System.out.println(HEX.bytesToHex(outputStream.toByteArray()));

        }catch (Exception ex)
        {

        }*/
    }

    /*public void performTest() throws Exception
    {
        Target[] targets = new Target[2];
        Target targetName = new Target(Target.targetName, new GeneralName(GeneralName.dNSName, "www.test.com"));
        Target targetGroup = new Target(Target.targetGroup, new GeneralName(GeneralName.directoryName, "o=Test, ou=Test"));
        targets[0] = targetName;
        targets[1] = targetGroup;
        Targets targetss = new Targets(targets);
        TargetInformation targetInformation1 = new TargetInformation(targetss);
        // use an Target array
        TargetInformation targetInformation2 = new TargetInformation(targets);
        // targetInformation1 and targetInformation2 must have same
        // encoding.
        if (!targetInformation1.equals(targetInformation2))
        {
            //fail("targetInformation1 and targetInformation2 should have the same encoding.");
        }
        TargetInformation targetInformation3 = TargetInformation.getInstance(targetInformation1);
        TargetInformation targetInformation4 = TargetInformation.getInstance(targetInformation2);
        if (!targetInformation3.equals(targetInformation4))
        {
            //fail("targetInformation3 and targetInformation4 should have the same encoding.");
        }
    }*/

}
