package com.iso8583

/**
 * Created by guoxiaomeng on 2017/6/23.
 */
//解决报文
class Msg {
    var tpdu: String? = null
    var head: String? = null
    var body: Body? = null
    var macResult: Int = 0

    val reqCode: RespCode
        get() {
            val s = this.body!!.get(39)
            return RespCode(s)
        }

    val reqCode2: RespCode
        get() {
            val s = this.body!!.getField(39)
            val respCode = RespCode(s)
            respCode.QRcode = this.body!!.getField(62)
            respCode.OrderNo = this.body!!.getField(20)
            return respCode
        }
    //return new Field39Code().getErrCode(this.body.get(39));
    val field39Code: String?
        get() = this.body!!.get(39)

    override fun toString(): String {
        var mac = "MAC:NONE\n"
        if (this.macResult == 1) {
            mac = "MAC:OK\n"
        } else if (this.macResult == 2) {
            mac = "MAC:ERR\n"
        }

        return "TPDU:" + this.tpdu + "\nHEAD:" + this.head + "\n" + mac + this.body
    }

    @Throws(PayException::class)
    fun toByteArray(): ByteArray {
        val baos = PayOutputStream()
        baos.writeBCD_c(this.tpdu.toString())
        if (this.head != null) {
            baos.writeBCD_c(this.head.toString())
        }

        this.body!!.toByteArray(baos)
        return baos.toByteArray()
    }

    companion object {
        val MAC_NONE = 0
        val MAC_OK = 1
        val MAC_ERR = 2
    }
}
