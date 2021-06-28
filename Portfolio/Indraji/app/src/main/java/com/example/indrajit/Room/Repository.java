package com.example.indrajit.Room;

import androidx.lifecycle.LiveData;

import com.example.indrajit.Model.Country;

import java.util.List;

public class Repository {
    private CountryDao countryDao;
    LiveData<List<Country>> allcountry = countryDao.getCountries();

    public Repository(CountryDao dao) {
        this.countryDao = dao;
    }

    void insert(Country c)
    {
        countryDao.insert(c);
    }
    void delete(Country c)
    {
        countryDao.delete(c);
    }


}
