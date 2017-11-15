package com.kotlinbglog.roomlivedata

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.kotlinbglog.roomlivedata.db.AppDatabase
import com.kotlinbglog.roomlivedata.db.User
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.longToast
import org.jetbrains.anko.uiThread

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private var mDb: AppDatabase = AppDatabase.getInstance(application)

    private var mUser: LiveData<User>
    val user: LiveData<User> get() = mUser

    init {
        mUser = mDb.userDao().userAsLiveData
    }

    fun addOrReplaceUser(userName: String) {
        doAsync {
            val user = mDb.userDao().user
            if (user == null) {
                mDb.userDao().insertUser(User(userName = userName))
            } else {
                user.userName = userName
                mDb.userDao().updateUser(user)
            }
        }
    }

    fun deleteUser() {
        doAsync {
            val user = mDb.userDao().user
            if (user != null) {
                mDb.userDao().deleteUser(user)
            }
        }
    }

    fun showLog() {
        doAsync {
            val user = mDb.userDao().user
            uiThread { getApplication<Application>().longToast("User name: ${user?.userName}") }
        }
    }


}