package com.example.indrajit.Room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.indrajit.Model.Country;

import java.util.List;

public class ViewModel extends AndroidViewModel {
   public LiveData<List<Country>> allCountry;
    public ViewModel(@NonNull @org.jetbrains.annotations.NotNull Application application) {
        super(application);
        CountryDao countryDao = null;
        Database database = null;
       CountryDao dao = database.getDatabase(application).getCountryDao();
       Repository repository = new Repository(dao);
     allCountry = repository.allcountry;


    }

}
