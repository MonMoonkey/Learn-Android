package priv.monkey.learn.android.storage.content_provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import priv.monkey.learn.android.storage.sqlite_database.MySQLiteOpenHelper;

public class MonkeyContentProvider extends ContentProvider {

    public static final String AUTHORITY = "priv.monkey.learn.android.storage.content_provider";
    private static final String DATABASE_NAME = "SQLiteDemo.db";

    private static final String TABLE_1 = "sqliteDemoTable";
    private static final String TABLE_2 = "sqliteDemoTable_2";

    private static final String KEY_ID = "_id";
    private static final String KEY_NAME = "name";
    private static final String KEY_NUMBER = "number";

    private static final int TABLE_1_ID = 1;
    private static final int TABLE_2_ID = 2;
    private static final int TABLE_2_ALL =3;

    private Context mContext;
    private MySQLiteOpenHelper mSQLiteOpenHelper;
    private SQLiteDatabase mSQLiteDatabase;
    private Cursor mCursor;
    private static UriMatcher mUriMatcher;

    static {
        mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mUriMatcher.addURI(AUTHORITY, TABLE_1, TABLE_1_ID);
        mUriMatcher.addURI(AUTHORITY, TABLE_2, TABLE_2_ID);
        mUriMatcher.addURI(AUTHORITY,TABLE_2+"/#",TABLE_2_ALL);
    }


    @Override
    public boolean onCreate() {
        mContext = getContext();
        mSQLiteOpenHelper = new MySQLiteOpenHelper(mContext, DATABASE_NAME, null, 1);
        mSQLiteDatabase = mSQLiteOpenHelper.getWritableDatabase();

        return false;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        String table_name = getTableName(uri);
        long id = mSQLiteDatabase.insert(table_name, null, contentValues);
        if(id>0) {
            Uri new_uri = ContentUris.withAppendedId(uri, id);
            return new_uri;
        }else{
            throw new SQLException("Failed to insert row into" + uri);
        }
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        String table_name = getTableName(uri);
        switch (mUriMatcher.match(uri)){
            case TABLE_1_ID:
            case TABLE_2_ID:
                //运用SQLiteQueryBuilder查询
                SQLiteQueryBuilder mSQLiteQueryBuilder = new SQLiteQueryBuilder();
                mSQLiteQueryBuilder.setTables(table_name);
                mSQLiteQueryBuilder.appendWhere(KEY_ID+"="+ContentUris.parseId(uri));
                mCursor = mSQLiteQueryBuilder.query(mSQLiteDatabase, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case TABLE_2_ALL:
                mCursor = mSQLiteDatabase.query(table_name, projection,selection,selectionArgs,null,null,sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        return mCursor;
    }


    /**
     * 它的作用是根据URI返回该URI所对应的数据的MIME类型字符串。
     * 这个MIME类型字符串的作用是要匹配AndroidManifest.xml文件
     * <activity>标签下<intent-filter>标签的子标签<data>的属性 android:mimeType。
     * 如果不一致，则会导致对应的Activity无法启动。
     */
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }



    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int id;
        String table_name = getTableName(uri);
        switch (mUriMatcher.match(uri)){
            case TABLE_1_ID:
            case TABLE_2_ID:
                id=mSQLiteDatabase.delete(table_name, KEY_ID+"="+ContentUris.parseId(uri), selectionArgs);
                break;
            case TABLE_2_ALL:
               id= mSQLiteDatabase.delete(table_name, selection, selectionArgs);
                break;
                default:
                    throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        mSQLiteDatabase.delete(table_name, selection, selectionArgs);
        return id;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
        int id;
        String table_name = getTableName(uri);
        switch (mUriMatcher.match(uri)) {
            case TABLE_1_ID:
            case TABLE_2_ID:
                id = mSQLiteDatabase.update(table_name, contentValues, KEY_ID+"="+ContentUris.parseId(uri), selectionArgs);
                break;
            case TABLE_2_ALL:
                id = mSQLiteDatabase.update(table_name, contentValues, selection, selectionArgs);
                break;
                default:
                    throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        return id;
    }

    private String getTableName(Uri uri) {
        String table_name = null;
        switch (mUriMatcher.match(uri)) {
            case 1:
                table_name = TABLE_1;
                break;
            case 2:
            case 3:
                table_name = TABLE_2;
                break;
        }
        return table_name;
    }
}
