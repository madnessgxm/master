package com.ANS;

import org.asnlab.asndt.runtime.type.BitStringType;
import org.asnlab.asndt.runtime.type.IA5String;
import org.asnlab.asndt.runtime.type.ImplicitType;
import org.asnlab.asndt.runtime.type.OctetStringType;
import org.asnlab.asndt.runtime.type.TaggedType;
import org.asnlab.asndt.runtime.value.BitString;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Null;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.BERGenerator;
import org.bouncycastle.asn1.BERSequence;
import org.bouncycastle.asn1.BERTaggedObject;
import org.bouncycastle.asn1.DEREnumerated;
import org.bouncycastle.asn1.DERIA5String;
import org.bouncycastle.asn1.DERInteger;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERUTF8String;
import org.bouncycastle.asn1.cms.Time;

import java.util.Enumeration;


public class TBSOrder extends ASN1Object{

    private ASN1Sequence tbsOrderSeq;
    private DERInteger orderId;//订单号
    private BitString itemName;//商品名称
    private DERInteger number;//商品数量


    private DERUTF8String amount;//总金额（楼主实在不知道REAL该对应什么类型，暂定位DERUTF8String吧）
    private Time genTime;//订单生产时间
    DERIA5String recordType = new DERIA5String("recordType");


    /** 所有成员的get、set方法略 **/
    public TBSOrder() {
        super();

    }

    public TBSOrder(DERInteger orderId, BitString itemName,
                    DERInteger number, DERUTF8String amount, Time genTime) {
        super();
        this.orderId = orderId;
        this.itemName =itemName;
        this.number = number;
        this.amount = amount;
        this.genTime = genTime;
        //OctetStringType.CLASS_PRIVATE



        //ImplicitType.CLASS_PRIVATE
    }

    @SuppressWarnings("unchecked")
    private TBSOrder(ASN1Sequence tbsOrderSeq) {
        this.tbsOrderSeq = tbsOrderSeq;
        Enumeration<Object> emu = this.tbsOrderSeq.getObjects();
        orderId = (DERInteger) emu.nextElement();//顺序狠重要
        itemName = (BitString) emu.nextElement();
        number = (DERInteger) emu.nextElement();
        amount = (DERUTF8String) emu.nextElement();
        genTime = Time.getInstance(emu.nextElement());
    }

    public static TBSOrder getInstance(Object obj) {
        if (obj instanceof TBSOrder) {
            return (TBSOrder) obj;
        } else if (obj != null) {
            return new TBSOrder(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    @Override
    public ASN1Primitive toASN1Primitive() {


        ASN1EncodableVector vector = new ASN1EncodableVector();

        vector.add(this.orderId); //顺序狠重要，必须和上面的一致
        vector.add(new BERTaggedObject(false,40, new DEROctetString(itemName.bytes)));
        vector.add(new BERTaggedObject(false,50,number));
        vector.add(new BERTaggedObject(false,ImplicitType.CLASS_PRIVATE,amount));
        vector.add(new BERTaggedObject(false,ImplicitType.CLASS_PRIVATE,genTime));

        vector.add(new BERTaggedObject(false,100,new BERTaggedObject(false,ImplicitType.CLASS_PRIVATE,genTime)));

        //vector.add(TaggedType.encodeTag());
        return new BERSequence(vector);
    }
}
