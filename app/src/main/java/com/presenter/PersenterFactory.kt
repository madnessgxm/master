package com.presenter

import com.model.ICallBack
import com.until.SystemTool
import java.io.File
import java.util.*

class PersenterFactory {
    private val factorymode: Int = 0

    constructor() {
    }

    companion object {
        private var persenterControl = getfactory(0)
        private fun getfactory(factoryMode: Int): IPersenter {
            when (factoryMode) {
                0 -> {

                    if (persenterControl == null || !(persenterControl is PersenterControl)) {
                        persenterControl = PersenterControl()
                    }
                    return persenterControl

                }
                1 -> {
                    if (persenterControl == null || !(persenterControl is LocalHostControl)) {
                        persenterControl = LocalHostControl()
                    }
                    return persenterControl

                }
                else -> {
                    if (persenterControl == null || !(persenterControl is PersenterControl)) {
                        persenterControl = PersenterControl()
                    }
                    return persenterControl
                }
            }
        }

        fun Login(id: String, pwd: String, iCallBack: ICallBack) {

            persenterControl.login(id, pwd, iCallBack)
        }

        fun AddAid(iCallBack: ICallBack) {
            //aid json
            if (checkFileExit("capk.development.json") == null) {
                val error = "json file does not exist!"
                iCallBack.error(0, error as Objects)
            }
            persenterControl.addAid("")
        }

        private fun checkFileExit(fileName: String): File? {
            val cachePath = SystemTool.jsonParameterPath + fileName;
            val file = File(cachePath)
            if (file.exists()) {
                return file
            } else {
                return null
            }
        }
    }
}