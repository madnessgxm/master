package com.iso8583

/**
 * Created by guoxiaomeng on 2017/6/23.
 */

class PayOutputStream {
    var buf: ByteArray?=null
    var len: Int = 0

    constructor() {
        this.buf = ByteArray(1024)
    }

    constructor(n: Int) {
        this.buf = ByteArray(n)
    }

    constructor(bs: ByteArray?) {
        var bs = bs
        if (bs != null && bs.size > 0) {
            this.buf = bs
            this.len = bs.size
        } else {
            bs = ByteArray(1024)
        }

    }

    fun bufSize(): Int {
        return this.buf!!.size
    }

    private fun newSize(n: Int) {
        var nlen = this.buf!!.size * 2
        if (nlen < n) {
            nlen = n
        }

        val pre = this.buf
        this.buf = ByteArray(nlen)
        System.arraycopy(pre, 0, this.buf, 0, pre!!.size)
    }

    fun reset() {
        this.len = 0
    }

    fun size(): Int {
        return this.len
    }

    fun toByteArray(): ByteArray {
        val r = ByteArray(this.len)
        System.arraycopy(this.buf, 0, r, 0, this.len)
        return r
    }

    fun toByteArray(pos: Int, len: Int): ByteArray {
        val r = ByteArray(len)
        System.arraycopy(this.buf, pos, r, 0, len)
        return r
    }

    fun write(b: ByteArray) {
        if (this.len + b.size > this.buf!!.size) {
            this.newSize(this.len + b.size)
        }

        System.arraycopy(b, 0, this.buf, this.len, b.size)
        this.len += b.size
    }

    fun write(b: ByteArray, pos: Int, blen: Int) {
        if (this.len + blen > this.buf!!.size) {
            this.newSize(this.len + blen)
        }

        System.arraycopy(b, pos, this.buf, this.len, blen)
        this.len += blen
    }

    fun writeByte(b: Int) {
        if (this.buf!!.size < this.len + 1) {
            this.newSize(this.len + 1)
        }

        this.buf!![this.len++] = b.toByte()
    }

    @Throws(PayException::class)
    fun writeBcdLen_c(nLen: Int, len: Int) {
        var nLen = nLen
        val n1 = nLen % 10
        nLen /= 10
        val n2 = nLen % 10
        nLen /= 10
        val n3 = nLen % 10
        if (len == 1) {
            this.writeByte(n1)
        } else if (len == 2) {
            this.writeByte(n1 or (n2 shl 4))
        } else {
            if (len != 3) {
                throw PayException("err len:$len")
            }

            this.writeByte(n3)
            this.writeByte(n1 or (n2 shl 4))
        }

    }

    @Throws(PayException::class)
    fun writeBcdLen(nLen: Int, len: Int) {
        var ts = Integer.toString(nLen + 100000000)
        ts = ts.substring(ts.length - len)

        for (i in 0 until ts.length) {
            this.writeByte(ts[i].toInt())
        }

    }

    fun writeASCII(s: String) {
        val len = s.length

        for (i in 0 until len) {
            this.writeByte(s[i].toInt())
        }

    }

    private fun getV(c: Char): Int {
        return if (c.toInt() >= 48 && c.toInt() <= 57) c.toInt() - 48 else if (c.toInt() >= 65 && c.toInt() <= 70) c.toInt() - 65 + 10 else if (c.toInt() >= 97 && c.toInt() <= 102) c.toInt() - 97 + 10 else 0
    }

    fun writeBCD_c(s: String) {
        var len = s.length
        val tb = len and 1 != 0
        if (tb) {
            --len
        }

        var i: Int
        i = 0
        while (i < len) {
            this.writeByte(getV(s[i]) shl 4 or getV(s[i + 1]))
            i += 2
        }

        if (tb) {
            this.writeByte(getV(s[i]) shl 4)
        }

    }

    fun writeBCD_c(s: String, fill: Int) {
        var len = s.length
        val tb = len and 1 != 0
        if (tb) {
            --len
        }

        var i: Int
        i = 0
        while (i < len) {
            this.writeByte(getV(s[i]) shl 4 or getV(s[i + 1]))
            i += 2
        }

        if (tb) {
            this.writeByte(getV(s[i]) shl 4 or fill)
        }

    }

    fun writeBCD_Hex(s: String, fill: Int) {
        var len = s.length
        val tb = len and 1 != 0
        if (tb) {
            --len
        }

        var i: Int
        i = 0
        while (i < len) {
            this.writeByte(getV(s[i]) shl 4 or getV(s[i + 1]))
            i += 2
        }

        if (tb) {
            this.writeByte(getV(s[i]) shl 4 or fill)
        }
    }

    fun writeBeInt(n: Int) {
        this.writeByte(n shr 24)
        this.writeByte(n shr 16)
        this.writeByte(n shr 8)
        this.writeByte(n shr 0)
    }

    fun writeBcdInt(n: Int, len: Int) {
        var n = n
        val bs = ByteArray(len)

        for (i in 0 until len) {
            bs[len - i - 1] = (48 + n % 10).toByte()
            n /= 10
        }

        this.write(bs)
    }

    fun writeLeInt(n: Int) {
        this.writeByte(n shr 0)
        this.writeByte(n shr 8)
        this.writeByte(n shr 16)
        this.writeByte(n shr 24)
    }

    fun setBeInt(p: Int, n: Int) {
        var p = p
        this.buf!![p++] = (n shr 24).toByte()
        this.buf!![p++] = (n shr 16).toByte()
        this.buf!![p++] = (n shr 8).toByte()
        this.buf!![p++] = (n shr 0).toByte()
    }

    operator fun set(p: Int, bs: ByteArray) {
        System.arraycopy(bs, 0, this.buf, p, bs.size)
    }
}
