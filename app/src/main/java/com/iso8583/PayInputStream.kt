package com.iso8583

import java.util.Arrays
import kotlin.experimental.and

/**
 * Created by guoxiaomeng on 2017/6/23.
 */

class PayInputStream(private val bs: ByteArray) {
    var pos = 0
        internal set

    @Throws(PayException::class)
    fun readByte(): Int {
        return if (this.pos >= this.bs.size) {
            throw PayException("EOF")
        } else {
            (this.bs[this.pos++] and (255).toByte()).toInt()
        }
    }

    fun getdata(start: Int, end: Int): ByteArray {
        return Arrays.copyOfRange(this.bs, start, end)
    }

    @Throws(PayException::class)
    fun read(r: ByteArray) {
        if (this.pos + r.size > this.bs.size) {
            throw PayException("EOF")
        } else {
            System.arraycopy(this.bs, this.pos, r, 0, r.size)
            this.pos += r.size
        }
    }

    @Throws(PayException::class)
    fun read(r: ByteArray, pr: Int, len: Int) {
        if (this.pos + len > this.bs.size) {
            throw PayException("EOF")
        } else {
            System.arraycopy(this.bs, this.pos, r, pr, len)
            this.pos += len
        }
    }

    @Throws(PayException::class)
    fun readBcdInt_c(len: Int): Int {
        var len = len
        var r = 0
        len = len + 1 shr 1

        for (i in 0 until len) {
            val v = this.readByte()
            r *= 10
            r += v shr 4
            r *= 10
            r += v and 15
        }

        return r
    }

    @Throws(PayException::class)
    fun readBcdInt(len: Int): Int {
        var r = 0

        for (i in 0 until len) {
            val v = this.readByte()
            r *= 10
            r += v - 48
        }

        return r
    }

    @Throws(PayException::class)
    fun skip(len: Int) {
        if (this.pos + len > this.bs.size) {
            throw PayException("EOF")
        } else {
            this.pos += len
        }
    }

    @Throws(PayException::class)
    fun readBCD_c(len: Int): String {
        var len = len
        val rs = CharArray(len)
        val b = len and 1 != 0
        if (b) {
            --len
        }

        len /= 2
        var io = 0

        var v: Int
        v = 0
        while (v < len) {
            val v1 = this.readByte()
            rs[io++] = CS[v1 shr 4]
            rs[io++] = CS[v1 and 15]
            ++v
        }

        if (b) {
            v = this.readByte()
            rs[io++] = CS[v shr 4]
        }

        return String(rs)
    }

    @Throws(PayException::class)
    fun readASCII(len: Int): String {
        val rs = CharArray(len)
        if (this.pos + len > this.bs.size) {
            throw PayException("EOF")
        } else {
            for (i in 0 until len) {
                rs[i] = (this.bs[this.pos + i] and (255).toByte()).toChar()
            }

            this.pos += len
            return String(rs)
        }
    }

    companion object {
        private val CS = "0123456789ABCDEF".toCharArray()
    }
}

