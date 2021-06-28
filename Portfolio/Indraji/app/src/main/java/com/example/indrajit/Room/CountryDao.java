package com.example.indrajit.Room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.indrajit.Model.Country;

import java.util.List;

@Dao
interface CountryDao{
    @Insert
    void insert(Country c);
    @Delete
    void delete(Country c);

    @Query(value = "Select * from country order by id ASC")
     LiveData<List<Country>> getCountries();
}
