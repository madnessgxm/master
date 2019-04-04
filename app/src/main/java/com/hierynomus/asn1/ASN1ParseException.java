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
package com.hierynomus.asn1;

public class ASN1ParseException extends RuntimeException {
    public ASN1ParseException(String message) {
        super(message);
    }

    public ASN1ParseException(Throwable cause, String messageFormat, Object... args) {
        super(String.format(messageFormat, args), cause);
    }

    public ASN1ParseException(String messageFormat, Object... args) {
        super(String.format(messageFormat, args));
    }
}
