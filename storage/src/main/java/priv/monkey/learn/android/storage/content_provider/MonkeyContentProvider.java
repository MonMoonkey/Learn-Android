package priv.monkey.learn.android.storage.content_provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import priv.monkey.learn.android.storage.sqlite_database.MySQLiteOpenHelper;

public class MonkeyContentProvider extends ContentProvider {

    public static final String AUTHORITY = "priv.monkey.learn.android.storage.content_provider";
    private static final String DATABASE_NAME = "SQLiteDemo.db";
    private static final String TABLE_1 = "sqliteDemoTable";
    private static final String TABLE_2 = "sqliteDemoTable_2";

    private Context mContext;
    private MySQLiteOpenHelper mSQLiteOpenHelper;
    private SQLiteDatabase mSQLiteDatabase;
    private Cursor mCursor;
    private static UriMatcher mUriMatcher;
    static{
        mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mUriMatcher.addURI(AUTHORITY,TABLE_1,1);
        mUriMatcher.addURI(AUTHORITY,TABLE_2,2);
    }

    @Override
    public boolean onCreate() {
        mContext=getContext();
        mSQLiteOpenHelper = new MySQLiteOpenHelper(mContext, DATABASE_NAME, null, 1);
        mSQLiteDatabase = mSQLiteOpenHelper.getWritableDatabase();

        return false;
    }


    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        String table_name = getTableName(uri);
        mCursor = mSQLiteDatabase.query(table_name, strings, s, strings1,);
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    private String getTableName(Uri uri) {
        String table_name=null;
        switch (mUriMatcher.match(uri)) {
            case 1: table_name=TABLE_1;
            break;
            case 2:
                table_name = TABLE_2;
                break;
        }
        return table_name;
    }
}
