package com.example.indrajit.Room;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.indrajit.Model.Country;

@androidx.room.Database(entities =Country.class,version = 1,exportSchema = false )
abstract class Database extends RoomDatabase {
abstract CountryDao getCountryDao();
    Database INSTANCE=null;
   public synchronized Database getDatabase(Context context)
    {
            if(INSTANCE!=null)
            {
                return INSTANCE;
            }
            else{
              Database instance=Room.databaseBuilder(context.getApplicationContext(), Database.class,"country").build();
           INSTANCE=instance;
            return INSTANCE;
            }
    }

}
