package com.emvl3kt

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.context.AppContext
import com.greendao.Student
import kotlinx.android.synthetic.main.activity_green_dao.*
import org.greenrobot.greendao.query.QueryBuilder
import ui.wangpos.com.utiltool.HEXUitl

class GreenDaoActivity :BaseActivity(){

    private val TAG: String?=GreenDaoActivity@javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_green_dao)

        btnadd.setOnClickListener {
            try {

                var student = Student();
                student.age = "10"
                student.id = 2
                student.name = "hello"
                //AppContext.daoSession!!.insert(student)
                var mstudent = AppContext.daoSession!!.studentDao.queryRaw("where id=?", "1")
                Log.d(TAG, mstudent.get(0).age)

            }catch (ex:Exception)
            {
                ex.printStackTrace()
            }
        }
    }


}
