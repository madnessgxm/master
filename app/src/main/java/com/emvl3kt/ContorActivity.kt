package com.emvl3kt

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import com.selfview.GroupAdapter
import com.selfview.MyFilpperAdpater
import kotlinx.android.synthetic.main.activity_contor.*
import java.util.*
import kotlin.math.log

class ContorActivity : BaseActivity() {

    var groupList:List<String>?=null
    var groupitem:List<List<String>>?=null
    var imgid:IntArray?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contor)
        tv_contor.text="也许在手机设备开发中似乎没太注意到“多用户”新特性的作用，无需太过在意，它主要在平板上使用，手机端是禁用的，但底层实现原理相同"
        var year = "first second thread four fire"
        var adapterView  = ArrayAdapter.createFromResource(this,R.array.spinneritem,R.layout.spinneritem)
        adapterView.setDropDownViewResource(R.layout.spinnerdropdown)
        spinner.adapter = adapterView
        spinner.onItemSelectedListener =object:AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Log.d("onItemSelected ",(view as TextView).text.toString())
                Log.d("onItemSelected ",adapterView.getItem(position).toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        var  mContacts = listOf<String>("test","abc", "aaa", "aabbcc", "bac" ,"ok", "say","aabbsd")
        var listAdapter = ArrayAdapter<String>(this@ContorActivity,android.R.layout.simple_dropdown_item_1line,mContacts)
        autocompletettext.setAdapter(listAdapter)
        multautocompletetextview.setTokenizer(MultiAutoCompleteTextView.CommaTokenizer())
        multautocompletetextview.setAdapter(listAdapter)

        expand_activities_button.setGroupIndicator(null)
        initData()
        var groupadapter = GroupAdapter(this@ContorActivity,groupList!!,groupitem!!)
        expand_activities_button.setAdapter(groupadapter)
        expand_activities_button.setOnGroupClickListener(object :ExpandableListView.OnGroupClickListener{
            override fun onGroupClick(parent: ExpandableListView?, v: View?, groupPosition: Int, id: Long): Boolean {
                if(groupList!![groupPosition].isEmpty())
                {
                    Log.d(javaClass.name,"grouplist")
                    return true
                }else
                {
                    return false
                }
            }
        })

        expand_activities_button.setOnChildClickListener(object :ExpandableListView.OnChildClickListener{
            override fun onChildClick(parent: ExpandableListView?, v: View?, groupPosition: Int, childPosition: Int, id: Long): Boolean {
                Log.d(javaClass.name,"groupitem")
                if(groupitem!![groupPosition][childPosition].isEmpty())
                {
                    Log.d(javaClass.name,"groupitemchild")
                    return true
                }
                return false
            }
        })

        var myFilpperAdpater = MyFilpperAdpater(this@ContorActivity,imgid!!)
        adapterViewflipper.adapter = myFilpperAdpater


        auto_btn.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                adapterViewflipper.startFlipping()
            }
        })
        next_btn.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                 //To change body of created functions use File | Settings | File Templates.
                adapterViewflipper.showNext()
                adapterViewflipper.stopFlipping()
            }
        })

        prev_btn.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                //To change body of created functions use File | Settings | File Templates.
                adapterViewflipper.showPrevious()
                adapterViewflipper.stopFlipping()
            }
        })
    }
    fun initData()
    {
        groupList = listOf<String>("中国","美国","日本","泰国")
        groupitem = listOf(listOf("北京","上海","广州","深圳"),
                listOf("华人","唐人街","硅谷"),
                listOf("东京","北海道","樱花"),
                listOf("海底","海上","欠妥","大木工"))
        imgid = intArrayOf(R.drawable.back,R.drawable.balance,R.drawable.purchase,R.drawable.pwd)
    }
}
