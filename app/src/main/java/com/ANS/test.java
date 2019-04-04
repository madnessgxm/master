package com.ANS;


import com.hierynomus.asn1.ASN1Parser;
import com.hierynomus.asn1.ASN1Serializer;
import com.hierynomus.asn1.annotations.ASN1;
import com.hierynomus.asn1.encodingrules.ASN1Decoder;
import com.hierynomus.asn1.encodingrules.ASN1Encoder;
import com.hierynomus.asn1.types.ASN1Object;
import com.hierynomus.asn1.types.ASN1Tag;
import com.hierynomus.asn1.types.primitive.ASN1PrimitiveValue;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;

public class test extends ASN1PrimitiveValue<Object> {
    /*public test(Object value) {
        this(new ASN1Tag() {
            @Override
            public ASN1Parser newParser(ASN1Decoder decoder) {
                return null;
            }

            @Override
            public ASN1Serializer newSerializer(ASN1Encoder encoder) {
                return null;
            }
        });
    }*/
    public test(ASN1Tag tag) {
        super(tag);
    }

    public test(ASN1Tag tag, byte[] valueBytes) {
        super(tag, valueBytes);
    }

    @Override
    protected int valueHash() {
        return super.valueHash();
    }

    @Override
    public Object getValue() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public ASN1Tag getTag() {
        return super.getTag();
    }
}
