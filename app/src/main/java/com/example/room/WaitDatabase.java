package com.example.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Wait.class},version = 1,exportSchema = false)
public abstract class WaitDatabase extends RoomDatabase {
    private static WaitDatabase INSTANCE;
    static synchronized WaitDatabase getDatabase(Context context){
        if(INSTANCE==null){
            INSTANCE= Room.databaseBuilder(context.getApplicationContext(),WaitDatabase.class,"wait_database")
                    .build();
        }
        return  INSTANCE;
    }
    public abstract WaitDao getWaitDao();
}
