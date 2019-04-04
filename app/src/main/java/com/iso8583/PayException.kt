package com.iso8583

/**
 * Created by guoxiaomeng on 2017/6/23.
 */

class PayException : Exception {

    constructor(s: String) : super(s) {}

    constructor(message: String, cause: Throwable) : super(message, cause) {}

    companion object {
        private val serialVersionUID = 7487634485730132399L
    }
}
