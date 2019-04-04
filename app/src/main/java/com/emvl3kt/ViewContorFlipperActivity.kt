package com.emvl3kt

import android.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.SearchView
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.*
import kotlinx.android.synthetic.main.activity_view_contor_flipper.*
import ui.wangpos.com.utiltool.StringUtil

class ViewContorFlipperActivity : BaseActivity() {

   lateinit var imgid:IntArray
    var imgIdex :Int=0
    lateinit var txtarr :List<String>
    var txtindex:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_contor_flipper)
        imgid = intArrayOf(R.drawable.back,R.drawable.balance,R.drawable.purchase,R.drawable.pwd)
        txtarr = listOf<String>("中国","美国","日本","泰国")
        imgSwitch.setFactory(object : ViewSwitcher.ViewFactory {
            override fun makeView(): View {
                 //To change body of created functions use File | Settings | File Templates.
                var img = ImageView(this@ViewContorFlipperActivity)
               // img.layoutParams = imgSwitch.layoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
                return img


            }
        })
        imgSwitch.setOnClickListener(object:View.OnClickListener{
            override fun onClick(v: View?) {
                 //To change body of created functions use File | Settings | File Templates.
                if(imgIdex<imgid!!.size)
                {
                    imgIdex++
                }else
                {
                    imgIdex=0
                }
                imgSwitch.setImageResource(imgid[imgIdex])
            }
        })
        imgSwitch.setImageResource(imgid[0])

        txtSwitch.setFactory(object :ViewSwitcher.ViewFactory{
            override fun makeView(): View {
                //To change body of created functions use File | Settings | File Templates.
                var txtView = TextView(this@ViewContorFlipperActivity)

                return txtView
            }
        })
        txtSwitch.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                 //To change body of created functions use File | Settings | File Templates.
                if(txtindex<txtarr!!.size)
                {
                    txtindex++
                }else
                {
                    txtindex = 0
                }
                txtSwitch.setText(txtarr[txtindex])
            }
        })
        txtSwitch.setText(txtarr[0])
        var  mDatas:List<String> = listOf("aaa", "bbb", "ccc", "airsaid")
        lstview.adapter = ArrayAdapter<String>(this@ViewContorFlipperActivity,android.R.layout.simple_list_item_1,mDatas)
        lstview.isTextFilterEnabled=true
        searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener
        {
            override fun onQueryTextChange(p0: String?): Boolean {
                if(!StringUtil.isNull(p0)) {
                    lstview.setFilterText(p0)
                }else
                {
                    lstview.clearTextFilter()
                }
                return false//To change body of created functions use File | Settings | File Templates.
            }

            override fun onQueryTextSubmit(p0: String?): Boolean {
                 //To change body of created functions use File | Settings | File Templates.

                return false
            }
        })
    }

}
