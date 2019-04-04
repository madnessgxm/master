package com.iso8583

import java.util.Arrays
import java.util.HashMap

import ui.wangpos.com.utiltool.HEXUitl
import ui.wangpos.com.utiltool.Util
import kotlin.experimental.and
import kotlin.experimental.or

/**
 * Created by guoxiaomeng on 2017/6/23.
 */

class Body(private val items: Array<Field>) {
    val ds = arrayOfNulls<String>(128)
    val byteint :Int?=128
    var type: String?=""
    get() = field
    set(value) {
        type=field
    }

    fun getFieldData(i: Int): ByteArray? {
        var i = i
        --i
        if (i >= 0 && i < this.ds.size && this.ds[i] != null) {
            val s = this.ds[i]
            return Util.string2bytes(s)
        } else {
            return null
        }
    }

    fun getField(i: Int): String? {
        return this[i]
    }

    operator fun get(i: Int): String? {
        var i = i
        --i
        return if (i >= 0 && i < this.ds.size && this.ds[i] != null) {
            ds[i]
        } else {
            null
        }
    }

    fun setFieldData(i: Int, bs: ByteArray) {
        this.setField(i, Util.bytes2string(bs))
    }

    @Throws(PayException::class)
    fun read(ins: PayInputStream) {
        val pos = ins.pos
        if (this.items[2].compress) {
            this.type = ins.readBCD_c(4)
        } else {
            this.type = ins.readASCII(4)
        }

        Arrays.fill(this.ds, null as Any?)
        val ms = ByteArray(16)
        ins.read(ms, 0, 8)

        if ((ms[0] and byteint!!.toByte()).toInt()!=0) {
            ins.read(ms, 8, 8)
        }

        for (i in 1..127) {
            if (i == 63) {
                ins.getdata(pos, ins.pos)
            }

            if ((ms[i shr 3] and (1 shl 7 - (i and 7)).toByte()).toInt() != 0) {
                try {
                    this.ds[i] = this.items[i + 1].decode(ins)
                } catch (var6: Exception) {
                    throw PayException(var6.message + " [" + (i + 1) + "]域解析失败", var6)
                }

            }
        }
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("TYPE:" + this.type + "\n")
        for (i in 1 until this.ds.size) {
            if (this.ds[i] != null) {
                val name = this.items[i + 1].name
                var ts = this.ds[i].toString()
                if (!isChars(ts)) {
                    ts = "HEX:" + HEXUitl.bytesToHex(Util.string2bytes(ts))
                }
                val index = i + 1
                if (index == 2) {
                    sb.append((i + 1).toString() + ":" + ts.toString() + "//" + name + "\n")
                } else if (index != 35 && index != 52 && index != 53 && index != 55) {
                    sb.append(index.toString() + ":" + ts + "//" + name + "\n")
                } else {
                    sb.append((i + 1).toString() + ":" + ts.toString() + "//" + name + "\n")
                }
            }
        }

        return sb.toString()
    }

    fun setField(i: Int, n: String?) {
        if (n == null) {
            this.ds[i - 1] = null
        } else {
            this.ds[i - 1] = n
        }

    }

    @Throws(PayException::class)
    fun toByteArray(os: PayOutputStream) {
        var bs = ByteArray(16)
        var is128 = false

        var i: Int
        i = 0
        while (i < this.ds.size) {
            if (this.ds[i] != null) {
                bs[i shr 3] = (bs[i shr 3] or (1 shl 7 - (i and 7)).toByte()).toByte()
                if (i >= 64) {
                    is128 = true
                }
            }
            ++i
        }

        if (is128) {
            bs[0] = (bs[0] or byteint!!.toByte()).toByte()
        } else {
            bs = Arrays.copyOf(bs, 8)
        }

        if (this.items[2].compress) {
            os.write(Util.toBCD(this.type))
        } else {
            os.writeASCII(this.type.toString())
        }

        os.write(bs)

        i = 1
        while (i < this.ds.size) {
            if (this.ds[i] != null) {
                this.items[i + 1].encode(this.ds[i].toString(), os)
            }
            ++i
        }

    }

    companion object {

        fun makeMap(s: String): ByteArray {
            val ss = s.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val bs = ByteArray(16)
            val var5 = ss.size

            for (var4 in 0 until var5) {
                val t = ss[var4]
                var p = Integer.parseInt(t.trim { it <= ' ' })
                --p
                bs[p shr 3] = (bs[p shr 3] or (1 shl 7 - (p and 7)).toByte()).toByte()
            }

            return bs
        }

        private fun isChars(s: String): Boolean {
            for (i in 0 until s.length) {
                val c = s[i]
                if (c.toInt() < 32) {
                    return false
                }
            }

            return true
        }
    }
}
