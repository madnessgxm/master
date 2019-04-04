package com.ANS;

import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;

public class ASN1TagConstants extends ASN1Object {
    public static final int TAG_SERVED_NUM=0xD1;
    public static final int TAG_USER_TYPE =0xD2;

    @Override
    public ASN1Primitive toASN1Primitive() {
        return null;
    }


}
