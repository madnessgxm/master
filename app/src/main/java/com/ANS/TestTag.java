package com.ANS;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DLTaggedObject;

import java.io.IOException;

public class TestTag extends DLTaggedObject {
    public TestTag(
            int         tagNo,
            ASN1Encodable    obj)
    {
        super(true, tagNo, obj);
    }

    

}
