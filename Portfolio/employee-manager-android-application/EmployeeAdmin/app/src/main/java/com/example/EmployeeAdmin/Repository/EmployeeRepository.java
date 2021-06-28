package com.example.EmployeeAdmin.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.EmployeeAdmin.db.EmployeeDao;
import com.example.EmployeeAdmin.db.EmployeeData;
import com.example.EmployeeAdmin.db.EmployeeDatabase;

import java.util.List;

public class EmployeeRepository {

    private EmployeeDao employeeDao;
    private LiveData<List<EmployeeData>> employees;
    public EmployeeRepository(Application application) {
        EmployeeDatabase db = EmployeeDatabase.getDatabase(application);
        employeeDao = db.employeeDao();
        employees = employeeDao.getAll();
    }
   public LiveData<List<EmployeeData>> getAll() {
        return employees;
    }
   public void insert(EmployeeData employeeData) {
        EmployeeDatabase.databaseWriteExecutor.execute(() -> {
            employeeDao.insert(employeeData);
        });
    }
}
