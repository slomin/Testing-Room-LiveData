package com.kotlinbglog.roomlivedata.dao

import android.support.test.runner.AndroidJUnit4
import com.kotlinbglog.roomlivedata.db.User
import com.kotlinbglog.roomlivedata.extensions.getValueBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
open class UserDaoTest : DbTest() {

    @Test
    fun insertUserTest() {
        val user = User(userName = "Test User")
        val userId = appDatabase.userDao().insertUser(user)
        val userFromDb = appDatabase.userDao().user
        assertEquals(userId, 1)
        assertEquals(userFromDb?.userName, user.userName)
    }

    @Test
    fun deleteUserTest() {
        val user = User(userName = "Test User")
        appDatabase.userDao().insertUser(user)
        var userFromDb = appDatabase.userDao().user
        appDatabase.userDao().deleteUser(userFromDb)
        userFromDb = appDatabase.userDao().user
        assertNull(userFromDb)
    }

    @Test
    fun getUserAsLiveDataTest() {
        val user = User(userName = "Test User")
        appDatabase.userDao().insertUser(user)
        val userWrappedInLiveData = appDatabase.userDao().userAsLiveData
        val userFromDb = userWrappedInLiveData.getValueBlocking()
        assertEquals(userFromDb?.userName, user.userName)
    }

}