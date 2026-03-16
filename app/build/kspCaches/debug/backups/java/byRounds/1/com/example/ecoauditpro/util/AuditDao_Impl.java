package com.example.ecoauditpro.util;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.ecoauditpro.data.AuditReport;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AuditDao_Impl implements AuditDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<AuditReport> __insertionAdapterOfAuditReport;

  public AuditDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAuditReport = new EntityInsertionAdapter<AuditReport>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `audit_reports` (`id`,`codigoRelatorio`,`empresa`,`setor`,`data`,`score`,`detalhes`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final AuditReport entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getCodigoRelatorio());
        statement.bindString(3, entity.getEmpresa());
        statement.bindString(4, entity.getSetor());
        statement.bindString(5, entity.getData());
        statement.bindDouble(6, entity.getScore());
        statement.bindString(7, entity.getDetalhes());
      }
    };
  }

  @Override
  public Object saveReport(final AuditReport report, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfAuditReport.insert(report);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object getAllReports(final Continuation<? super List<AuditReport>> $completion) {
    final String _sql = "SELECT * FROM audit_reports ORDER BY id DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<AuditReport>>() {
      @Override
      @NonNull
      public List<AuditReport> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCodigoRelatorio = CursorUtil.getColumnIndexOrThrow(_cursor, "codigoRelatorio");
          final int _cursorIndexOfEmpresa = CursorUtil.getColumnIndexOrThrow(_cursor, "empresa");
          final int _cursorIndexOfSetor = CursorUtil.getColumnIndexOrThrow(_cursor, "setor");
          final int _cursorIndexOfData = CursorUtil.getColumnIndexOrThrow(_cursor, "data");
          final int _cursorIndexOfScore = CursorUtil.getColumnIndexOrThrow(_cursor, "score");
          final int _cursorIndexOfDetalhes = CursorUtil.getColumnIndexOrThrow(_cursor, "detalhes");
          final List<AuditReport> _result = new ArrayList<AuditReport>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AuditReport _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpCodigoRelatorio;
            _tmpCodigoRelatorio = _cursor.getString(_cursorIndexOfCodigoRelatorio);
            final String _tmpEmpresa;
            _tmpEmpresa = _cursor.getString(_cursorIndexOfEmpresa);
            final String _tmpSetor;
            _tmpSetor = _cursor.getString(_cursorIndexOfSetor);
            final String _tmpData;
            _tmpData = _cursor.getString(_cursorIndexOfData);
            final double _tmpScore;
            _tmpScore = _cursor.getDouble(_cursorIndexOfScore);
            final String _tmpDetalhes;
            _tmpDetalhes = _cursor.getString(_cursorIndexOfDetalhes);
            _item = new AuditReport(_tmpId,_tmpCodigoRelatorio,_tmpEmpresa,_tmpSetor,_tmpData,_tmpScore,_tmpDetalhes);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
