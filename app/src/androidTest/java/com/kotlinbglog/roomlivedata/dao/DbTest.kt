package com.kotlinbglog.roomlivedata.dao

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import com.kotlinbglog.roomlivedata.db.AppDatabase
import org.junit.After
import org.junit.Before
import java.io.IOException

abstract class DbTest {
    protected lateinit var appDatabase: AppDatabase

    @Before
    fun initDb() {
        appDatabase = Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getContext(),
                AppDatabase::class.java)
                .build()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        appDatabase.close()
    }
}