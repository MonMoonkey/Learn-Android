package priv.monkey.learn.android.storage.sqlite_database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by WenzhuoHao on 2018/3/2.
 */

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    /**
     *
     * @param context 上下文，包名
     * @param name  数据库文件名
     * @param factory   如果为null，使用默认游标
     * @param version   版本号，int类型，只能升，不能降
     */
    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, null, 1);

    }

    //数据库文件不存在，创建数据库,并在第一次使用时调用,第二次使用就不会调用了，只被调用一次
    @Override
    public void onCreate(SQLiteDatabase sqLiteDate) {
            Log.i("SQLiteDatabase","MySQLiteOpenHelper.onCreate");
        //可以创建表，只需一次
    }

    //版本更新时调用
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.i("SQLiteDatabase","MySQLiteOpenHelper.onUpgrade");
        //在数据库更新的时候，需要更新数据库对象
    }
}
