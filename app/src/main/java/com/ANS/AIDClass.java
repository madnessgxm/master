package com.ANS;


import java.io.IOException;

public class AIDClass
{
    /*private char[]  stringC;

    *//**
     * return a BMP String from the given object.
     *
     * @param obj the object we want converted.
     * @exception IllegalArgumentException if the object cannot be converted.
     *//*
    public static AIDClass getInstance(
            Object  obj)
    {
        if (obj == null || obj instanceof AIDClass)
        {
            return (AIDClass)obj;
        }

        if (obj instanceof byte[])
        {
            try
            {
                return (AIDClass)fromByteArray((byte[])obj);
            }
            catch (Exception e)
            {
                throw new IllegalArgumentException("encoding error in getInstance: " + e.toString());
            }
        }

        throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
    }

    *//**
     * return a BMP String from a tagged object.
     *
     * @param obj the tagged object holding the object we want
     * @param explicit true if the object is meant to be explicitly
     *              tagged false otherwise.
     * @exception IllegalArgumentException if the tagged object cannot
     *              be converted.
     *//*
    public static AIDClass getInstance(
            ASN1TaggedObject obj,
            boolean          explicit)
    {
        ASN1Primitive o = obj.getObject();

        if (explicit || o instanceof AIDClass)
        {
            return getInstance(o);
        }
        else
        {
            return new AIDClass(ASN1OctetString.getInstance(o).getOctets());
        }
    }

    *//**
     * basic constructor - byte encoded string.
     *//*
   public AIDClass(byte[] strng)
    {
        char[]  cs = new char[strng.length / 2];

        for (int i = 0; i != cs.length; i++)
        {
            cs[i] = (char)((strng[2 * i] << 8) | (strng[2 * i + 1] & 0xff));
        }

        this.stringC = cs;
    }

    AIDClass(char[] string)
    {
        this.stringC = string;
    }

    *//**
     * basic constructor
     *//*
    public AIDClass(String string)
    {
        this.stringC = string.toCharArray();
    }

    public String getString()
    {
        return new String(stringC);
    }

    public String toString()
    {
        return getString();
    }

    public int hashCode()
    {
        return Arrays.hashCode(stringC);
    }

    protected boolean asn1Equals(ASN1Primitive o)
    {
        if (!(o instanceof AIDClass))
        {
            return false;
        }

        AIDClass  s = (AIDClass)o;

        return Arrays.areEqual(stringC, s.stringC);
    }

    boolean isConstructed()
    {
        return false;
    }

    int encodedLength()
    {
        return 1 + StreamUtilC.calculateBodyLength(stringC.length * 2) + (stringC.length * 2);
    }

    void encode(ASN1OutputStream out) throws IOException
    {
        out.write(BERTagsC.AIDTAG);
        out.writeLength(stringC.length * 2);
        for (int i = 0; i != stringC.length; i++)
        {
            char c = stringC[i];
            out.write((byte)(c >> 8));
            out.write((byte)c);
        }
    }*/
}
