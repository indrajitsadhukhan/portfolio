package com.example.EmployeeAdmin.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EmployeeDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(EmployeeData employeeData);
    @Delete
    void delete(EmployeeData employeeData);
    @Query("Select * from Employee")
    LiveData<List<EmployeeData>> getAll();
}
