package com.iso8583

/**
 * Created by guoxiaomeng on 2017/6/23.
 */

class Field(s: String, val compress: Boolean) {
    var index: Int = 0
    var name: String
    var maxLen: Int = 0
    var varLen: Int = 0
    var type: Char = ' '
    private val pre: String

    override fun toString(): String {
        return this.pre
    }

    init {
        var s = s
        this.pre = s
        val i = s.indexOf(":")
        this.index = Integer.parseInt(s.substring(0, i))
        val ss = s.substring(i + 1).split(",".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
        this.name = ss[0]
        s = ss[1]
        if (s.startsWith("LLLVAR-")) {
            this.varLen = 3
            s = s.substring(7)
        } else if (s.startsWith("LLVAR-")) {
            this.varLen = 2
            s = s.substring(6)
        } else if (s.startsWith("LVAR-")) {
            this.varLen = 1
            s = s.substring(5)
        } else {
            this.varLen = 0
        }

        if (s.startsWith("ANS")) {
            this.type = 83.toChar()
            s = s.substring(3)
        } else if (s.startsWith("AN")) {
            this.type = 65.toChar()
            s = s.substring(2)
        } else if (s.startsWith("N")) {
            this.type = 78.toChar()
            s = s.substring(1)
        } else if (s.startsWith("B")) {
            this.type = 66.toChar()
            s = s.substring(1)
        } else if (s.startsWith("SN")) {
            this.type = 67.toChar()
            s = s.substring(2)
        } else {
            if (!s.startsWith("E")) {
                throw RuntimeException("type error:$i")
            }

            this.type = 69.toChar()
            s = s.substring(1)
        }

        this.maxLen = Integer.parseInt(s)
        if (this.type.toInt() == 66) {
            this.maxLen /= 8
            if (compress) {
                this.maxLen *= 2
            }
        }

    }

    @Throws(PayException::class)
    fun encode(o: String, os: PayOutputStream) {
        var o = o
        if (this.index == 55) {
            println("55")
        }

        if (this.index == 52) {
            println("52:$o")
        }

        if (this.type.toInt() == 69) {
            throw PayException("不支持的类型:" + this.index)
        } else {
            val need = this.maxLen - o.length
            if (need < 0) {
                throw PayException("数据太长:" + o.length + ">" + this.maxLen + " " + o + " filed:" + this.index)
            } else {
                if (this.compress) {
                    if (this.varLen > 0) {

                        if (this.type.toInt() == 78) {
                            os.writeBcdLen_c(o.length, this.varLen)
                            os.writeBCD_c(o)
                        } else if (this.type.toInt() == 67) {
                            os.writeBcdLen_c(o.length / 2, this.varLen)
                            os.writeBCD_Hex(o, this.varLen)
                        } else {
                            os.writeBcdLen_c(o.length, this.varLen)
                            os.writeASCII(o)
                        }
                    } else if (this.type.toInt() == 78) {
                        if (need > 0) {
                            o = "0000000000000000000000000000000000000".substring(0, need) + o
                        }

                        os.writeBCD_c(o)
                    } else if (this.type.toInt() == 66) {
                        if (need != 0) {
                            throw PayException(this.index.toString() + "长度异常")
                        }

                        os.writeBCD_c(o)
                    } else {
                        if (need > 0) {
                            o = o + "                                     ".substring(0, need)
                        }

                        os.writeASCII(o)
                    }
                } else if (this.varLen > 0) {
                    os.writeBcdLen(o.length, this.varLen)
                    os.writeASCII(o)
                } else if (this.type.toInt() == 78) {
                    if (need > 0) {
                        o = "0000000000000000000000000000000000000".substring(0, need) + o
                    }
                    os.writeASCII(o)
                } else if (this.type.toInt() == 67) {
                    os.writeBcdLen_c(o.length / 2, this.varLen)
                    os.writeBCD_Hex(o, this.varLen)
                } else if (this.type.toInt() == 66) {
                    if (need != 0) {
                        throw PayException(this.index.toString() + "域长度异常:" + need + "; ")
                    }
                    os.writeASCII(o)
                } else {
                    if (need > 0) {
                        o = o + "                                     ".substring(0, need)
                    }

                    os.writeASCII(o)
                }

            }
        }
    }

    @Throws(PayException::class)
    fun decode(ins: PayInputStream): String {
        if (this.type.toInt() == 69) {
            throw PayException("不支持的类型:" + this.index)
        } else {
            val len: Int
            if (this.compress) {
                if (this.varLen > 0) {
                    len = ins.readBcdInt_c(this.varLen)
                    return if (len > this.maxLen) {
                        throw PayException(this.index.toString() + "长度太长:" + len + ">" + this.maxLen)
                    } else if (this.type.toInt() == 78) {
                        ins.readBCD_c(len)
                        //return (this.type == 78 || this.type==67)?ins.readBCD_c(len):ins.readASCII(len);
                    } else if (this.type.toInt() == 67) {
                        ins.readBCD_c(len * 2)
                    } else {
                        ins.readASCII(len)
                    }
                } else {
                    return if (this.type.toInt() == 78) ins.readBCD_c(this.maxLen) else if (this.type.toInt() == 66) ins.readBCD_c(this.maxLen) else ins.readASCII(this.maxLen)
                }
            } else if (this.varLen > 0) {
                len = ins.readBcdInt(this.varLen)
                return if (len > this.maxLen) {
                    throw PayException("长度太长:" + len + ">" + this.maxLen)
                } else {
                    ins.readASCII(len)
                }
            } else {
                return ins.readASCII(this.maxLen)
            }
        }
    }

    companion object {
        private val ZS = "0000000000000000000000000000000000000"
        private val ES = "                                     "

        fun makeItems(FORMAT: String, isCompress: Boolean): Array<Field?> {
            val fitems = arrayOfNulls<Field>(129)
            val ss = FORMAT.split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val var6 = ss.size

            for (var5 in 0 until var6) {
                val i = ss[var5]
                val f = Field(i, isCompress)
                fitems[f.index] = f
            }

            for (var9 in fitems.indices) {
                if (fitems[var9] == null) {
                    fitems[var9] = Field(var9.toString() + ":未定义,E1", isCompress)
                }
            }

            return fitems
        }
    }
}
