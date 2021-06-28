package com.example.EmployeeAdmin.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.EmployeeAdmin.Repository.EmployeeRepository;
import com.example.EmployeeAdmin.db.EmployeeData;

import java.util.List;

public class EmployeeViewModel extends AndroidViewModel {

    private EmployeeRepository employeeRepository;

    private final LiveData<List<EmployeeData>> employees;

    public EmployeeViewModel (Application application) {
        super(application);
        employeeRepository = new EmployeeRepository(application);
        employees = employeeRepository.getAll();
    }

    LiveData<List<EmployeeData>> getAllWords() { return employees; }

    public void insert(EmployeeData employeeData) { employeeRepository.insert(employeeData); }
}