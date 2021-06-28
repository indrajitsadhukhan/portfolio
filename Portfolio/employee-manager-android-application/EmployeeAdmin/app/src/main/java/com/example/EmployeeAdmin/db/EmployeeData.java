package com.example.EmployeeAdmin.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Employee")
public class EmployeeData {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    String id;
    @ColumnInfo(name = "Name")
    String name;
    @ColumnInfo(name = "Email")
    @NonNull
    String email;

    EmployeeData(String name,String email)
    {
        this.name=name;
        this.email=email;
    }

}
