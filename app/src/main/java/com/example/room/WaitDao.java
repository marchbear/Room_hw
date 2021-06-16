package com.example.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WaitDao {
    @Insert
    void insertWait(Wait... waits);

    @Delete
    void deleteWait(Wait... waits);

    @Query("SELECT * FROM WAIT")
    LiveData<List<Wait>> getAllWait();
}
