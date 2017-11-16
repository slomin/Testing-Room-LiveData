package com.kotlinblog.roomlivedata.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import org.jetbrains.annotations.Nullable;

/**
 * UserDao as Java class since AS doesn't support SQL syntax highlighting in Kotlin (yet)
 */
@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertUser(User user);

    @Delete
    void deleteUser(User user);

    @Update
    void updateUser(User user);

    @Query("SELECT * FROM current_user")
    @Nullable
    User getUser();

    @Query("SELECT * FROM current_user")
    LiveData<User> getUserAsLiveData();
}
