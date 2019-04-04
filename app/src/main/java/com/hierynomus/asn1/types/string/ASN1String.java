/*
 *    Copyright 2016 Jeroen van Erp <jeroen@hierynomus.com>
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.hierynomus.asn1.types.string;

import java.util.Collections;
import java.util.Iterator;
import com.hierynomus.asn1.encodingrules.ber.BERDecoder;
import com.hierynomus.asn1.types.*;

/**
 * An ASN.1 STRING type can either be expressed as a Primitive encoded or Constructed encoded sequence.
 */
public abstract class ASN1String<T> extends ASN1Object<T> implements ASN1Primitive, ASN1Constructed {
    protected byte[] valueBytes;

    public ASN1String(ASN1Tag<?> tag, byte[] bytes) {
        super(tag);
        this.valueBytes = bytes;
    }

    @Override
    public Iterator<ASN1Object> iterator() {
        if (tag.getAsn1Encoding() == ASN1Encoding.Constructed) {
            return ASN1Tag.SEQUENCE.newParser(new BERDecoder()).parse(ASN1Tag.SEQUENCE, valueBytes).iterator();
        } else {
            return Collections.<ASN1Object>singletonList(this).iterator();
        }
    }

    public abstract int length();
}
