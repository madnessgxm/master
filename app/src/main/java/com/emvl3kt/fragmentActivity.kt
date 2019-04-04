package com.emvl3kt

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentTransaction
import android.view.View
import com.view.firstFragment
import com.view.secondFragment
import com.view.thirdFragment
import kotlinx.android.synthetic.main.activity_fragment.*

class fragmentActivity : FragmentActivity(),View.OnClickListener{

    var sFragment:secondFragment?=null
    var tFragment:thirdFragment?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
       /* var fragmentcontext  =supportFragmentManager
        var fragmentTransaction =  fragmentcontext.beginTransaction()
        fragmentTransaction.add(R.id.linefirstfragment, firstFragment())
        fragmentTransaction.add(R.id.fragmentfirst, firstFragment())
        fragmentTransaction.commit()*/
        btnAdd.setOnClickListener(this)
        btnRemove.setOnClickListener(this)
        btnUpdate.setOnClickListener(this)
        sFragment = secondFragment()
        tFragment = thirdFragment()

    }

    override fun onClick(v: View?) {
         //To change body of created functions use File | Settings | File Templates.
        var fragmentcontext  =supportFragmentManager
        var fragmentTransaction =  fragmentcontext.beginTransaction()
        when(v!!.id)
        {
            R.id.btnAdd->
            {
                fragmentTransaction.add(R.id.fragmentsecond, sFragment!!)
                fragmentTransaction.add(R.id.fragmentthrid, tFragment!!)
            }
            R.id.btnRemove->
            {
                fragmentTransaction.remove(sFragment!!)
            }
            R.id.btnUpdate->
            {
                fragmentTransaction.replace(R.id.fragmentthrid,sFragment!!)
            }
        }
        fragmentTransaction.commit()
    }
}
