package com.example.indrajit.Room;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.DBUtil;
import com.example.indrajit.Model.Country;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class CountryDao_Impl implements CountryDao {
  private final RoomDatabase __db;

  public CountryDao_Impl(RoomDatabase __db) {
    this.__db = __db;
  }

  @Override
  public void insert(final Country c) {
  }

  @Override
  public LiveData<List<Country>> getCountries() {
    final String _sql = "Select * from country order by id ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"country"}, false, new Callable<List<Country>>() {
      @Override
      public List<Country> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
