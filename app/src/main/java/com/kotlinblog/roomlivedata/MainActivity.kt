package com.kotlinblog.roomlivedata

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kotlinblog.roomlivedata.db.User
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        observeUser()
        btnUpdateUser.setOnClickListener {
            mViewModel.addOrReplaceUser(etUserName.text.toString())
            etUserName.text = null
        }
        btnDeleteUser.setOnClickListener { mViewModel.deleteUser() }
        btnShowLog.setOnClickListener { mViewModel.showLog() }
    }

    private fun observeUser() {
        val observer = Observer<User> {
            tvUserName.text = it?.userName
        }
        mViewModel.user.observe(this, observer)
    }
}
