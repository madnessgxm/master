package com.view

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import com.emvl3kt.R
import kotlinx.android.synthetic.main.dlg_toast.*
import org.w3c.dom.Text
import android.view.Window.FEATURE_NO_TITLE
import android.widget.FrameLayout



class DlgToast:Dialog
{

    var tips:TextView?=null
    var txtcontext:TextView?=null
    var btnok:TextView?=null
    var btncancel:TextView?=null
    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, themeStyle: Int) : super(context, themeStyle) {
        initView()
    }

    fun initView()
    {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setCanceledOnTouchOutside(false)
        setContentView(R.layout.dlg_toast)
        tips = findViewById<TextView>(R.id.txttitle)
        txtcontext = findViewById(R.id.txt_content)
        btnok = findViewById(R.id.txt_ok)
        btncancel =findViewById(R.id.txt_cancel)
    }

    companion object {
        fun ShowOkCancelTips(context: Context,tipsstr :String ,contextstr:String,onOkClickListener: View.OnClickListener)
        {
           val dlgtoast = DlgToast(context)
            dlgtoast.tips?.text=tipsstr
            dlgtoast.txt_content?.text= contextstr

            dlgtoast.txt_ok?.setOnClickListener(onOkClickListener)

            dlgtoast.txt_cancel.visibility = View.GONE
            dlgtoast.txt_cancel.setOnClickListener {
                dlgtoast.dismiss()
            }
            dlgtoast.show();
        }
    }
}
