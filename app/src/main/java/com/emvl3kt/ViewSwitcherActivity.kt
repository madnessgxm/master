package com.emvl3kt

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.GridView
import android.widget.ViewSwitcher
import com.selfview.CGridItem
import com.selfview.GridAdapter
import com.selfview.GroupAdapter
import kotlinx.android.synthetic.main.activity_contor.view.*
import kotlinx.android.synthetic.main.activity_view_switcher.*

class ViewSwitcherActivity : BaseActivity() {

    lateinit var gridlist: java.util.ArrayList<CGridItem>

    companion object {
        val NUMBER_PER_SCREEN: Int = 12
        // 记录当前正在显示第几屏的程序
        var screenNo: Int = -1
        // 保存程序所占的总屏数
        var screenCount: Int = 0
    }

    var gridAdapter: GridAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_switcher)
        gridlist = java.util.ArrayList<CGridItem>()
        //初始化BaseAdapter
        for (i in 0..40) {
            var cGridItem = CGridItem()
            cGridItem.imgname = "item" + i.toString()
            cGridItem.imgid = R.drawable.pwd
            gridlist!!.add(cGridItem)
        }

        screenCount = if (gridlist!!.size % NUMBER_PER_SCREEN == 0) gridlist!!.size / NUMBER_PER_SCREEN else gridlist!!.size / NUMBER_PER_SCREEN + 1

        viewswitchtest.setFactory(object : ViewSwitcher.ViewFactory {
            override fun makeView(): View {
                //To change body of created functions use File | Settings | File Templates.
                return this@ViewSwitcherActivity.layoutInflater.inflate(R.layout.gridview, null)
            }
        })

        gridAdapter = GridAdapter(this, gridlist)

        btnpre.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                pre()
            }
        })

        btnnext.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                //To change body of created functions use File | Settings | File Templates.
                next()
            }
        })
        next()
    }

    fun pre() {
        if (screenNo > 1) {
            screenNo++
        }
        (viewswitchtest.nextView as GridView).adapter = gridAdapter
        viewswitchtest.showPrevious()
    }

    fun next() {
        if (screenNo < screenCount - 1) {
            screenNo++
        }
        (viewswitchtest.nextView as GridView).adapter = gridAdapter
        viewswitchtest.showNext()
    }
}

