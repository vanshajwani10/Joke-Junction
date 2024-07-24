package com.example.ktorroomdbapp.db;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.ktorroomdbapp.model.JokeJSON;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class JokeDao_Impl implements JokeDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<JokeJSON> __insertionAdapterOfJokeJSON;

  private final SharedSQLiteStatement __preparedStmtOfDeleteFav;

  public JokeDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfJokeJSON = new EntityInsertionAdapter<JokeJSON>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR IGNORE INTO `Jokes` (`identity`,`category`,`type`,`joke`,`id`,`safe`,`lang`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final JokeJSON entity) {
        if (entity.getIdentity() == null) {
          statement.bindNull(1);
        } else {
          statement.bindLong(1, entity.getIdentity());
        }
        if (entity.getCategory() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getCategory());
        }
        if (entity.getType() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getType());
        }
        if (entity.getJoke() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getJoke());
        }
        statement.bindLong(5, entity.getId());
        final int _tmp = entity.getSafe() ? 1 : 0;
        statement.bindLong(6, _tmp);
        if (entity.getLang() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getLang());
        }
      }
    };
    this.__preparedStmtOfDeleteFav = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM Jokes where id= ?";
        return _query;
      }
    };
  }

  @Override
  public void insertFav(final JokeJSON joke) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfJokeJSON.insert(joke);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteFav(final int id) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteFav.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, id);
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfDeleteFav.release(_stmt);
    }
  }

  @Override
  public LiveData<List<JokeJSON>> getAllFav() {
    final String _sql = "SELECT * FROM Jokes";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"Jokes"}, false, new Callable<List<JokeJSON>>() {
      @Override
      @Nullable
      public List<JokeJSON> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfIdentity = CursorUtil.getColumnIndexOrThrow(_cursor, "identity");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfJoke = CursorUtil.getColumnIndexOrThrow(_cursor, "joke");
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfSafe = CursorUtil.getColumnIndexOrThrow(_cursor, "safe");
          final int _cursorIndexOfLang = CursorUtil.getColumnIndexOrThrow(_cursor, "lang");
          final List<JokeJSON> _result = new ArrayList<JokeJSON>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final JokeJSON _item;
            final Integer _tmpIdentity;
            if (_cursor.isNull(_cursorIndexOfIdentity)) {
              _tmpIdentity = null;
            } else {
              _tmpIdentity = _cursor.getInt(_cursorIndexOfIdentity);
            }
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final String _tmpType;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmpType = null;
            } else {
              _tmpType = _cursor.getString(_cursorIndexOfType);
            }
            final String _tmpJoke;
            if (_cursor.isNull(_cursorIndexOfJoke)) {
              _tmpJoke = null;
            } else {
              _tmpJoke = _cursor.getString(_cursorIndexOfJoke);
            }
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final boolean _tmpSafe;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSafe);
            _tmpSafe = _tmp != 0;
            final String _tmpLang;
            if (_cursor.isNull(_cursorIndexOfLang)) {
              _tmpLang = null;
            } else {
              _tmpLang = _cursor.getString(_cursorIndexOfLang);
            }
            _item = new JokeJSON(_tmpIdentity,_tmpCategory,_tmpType,_tmpJoke,_tmpId,_tmpSafe,_tmpLang);
            _result.add(_item);
          }
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
