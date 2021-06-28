package com.example.indrajit.Room;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class Database_Impl extends Database {
  private volatile CountryDao _countryDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Country` (`name` TEXT, `flag` TEXT, `capital` TEXT, `region` TEXT, `subregion` TEXT, `population` TEXT, `languages` TEXT, `borders` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '5f2b8e68b30fd8dddfb5af68c709ae00')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `Country`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsCountry = new HashMap<String, TableInfo.Column>(8);
        _columnsCountry.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCountry.put("flag", new TableInfo.Column("flag", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCountry.put("capital", new TableInfo.Column("capital", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCountry.put("region", new TableInfo.Column("region", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCountry.put("subregion", new TableInfo.Column("subregion", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCountry.put("population", new TableInfo.Column("population", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCountry.put("languages", new TableInfo.Column("languages", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCountry.put("borders", new TableInfo.Column("borders", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCountry = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCountry = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCountry = new TableInfo("Country", _columnsCountry, _foreignKeysCountry, _indicesCountry);
        final TableInfo _existingCountry = TableInfo.read(_db, "Country");
        if (! _infoCountry.equals(_existingCountry)) {
          return new RoomOpenHelper.ValidationResult(false, "Country(com.example.indrajit.Model.Country).\n"
                  + " Expected:\n" + _infoCountry + "\n"
                  + " Found:\n" + _existingCountry);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "5f2b8e68b30fd8dddfb5af68c709ae00", "a722de4197bba17855a6d0de830d5748");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "Country");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `Country`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(CountryDao.class, CountryDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  CountryDao getCountryDao() {
    if (_countryDao != null) {
      return _countryDao;
    } else {
      synchronized(this) {
        if(_countryDao == null) {
          _countryDao = new CountryDao_Impl(this);
        }
        return _countryDao;
      }
    }
  }
}
