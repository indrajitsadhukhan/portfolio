package com.example.indrajit.Room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "country")
public class Entities {
    @PrimaryKey(autoGenerate = true)
    String id;
    @ColumnInfo
    String name,capital,flag,region,subregion,population,borders,languages;

}
