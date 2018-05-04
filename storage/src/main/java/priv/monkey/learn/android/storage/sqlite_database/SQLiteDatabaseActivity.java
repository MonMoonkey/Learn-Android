package priv.monkey.learn.android.storage.sqlite_database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import priv.monkey.learn.android.storage.R;

/*
    创建SQLiteDatabase对象
    推荐运用SQLiteOpenHelper管理（因为创建和打开数据库时容易出现问题）
    onCreate中创建表格
    从SQLiteOpenHelper的getReadableDatabase()或getWritableDatabase()进行操作数据库
 */
public class SQLiteDatabaseActivity extends AppCompatActivity {

    private MySQLiteOpenHelper mSQLiteOpenHelper;
    private SQLiteDatabase mSQLiteDatabase;

    private EditText mTextView_id;
    private EditText mEditText_int;
    private EditText mEditText_stirng;
    private EditText mEditText_float;

    private ListView mListView;

    private Button mButton_insert;
    private Button mButton_query;
    private Button mButton_update;
    private Button mButton_delete;

    private EditText mEditText_execSQL;
    private Button mButton_execSQL;

    private Cursor mCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_database);

        mTextView_id = findViewById(R.id.text_id_value_sqlite_database);
        mEditText_int = findViewById(R.id.edit_text_int_sqlite_database);
        mEditText_stirng = findViewById(R.id.edit_text_string_sqlite_database);
        mEditText_float = findViewById(R.id.edit_text_float_sqlite_database);

        mListView = findViewById(R.id.list_view_sqlite_database);

        mButton_insert = findViewById(R.id.button_insert_sqlite_database);
        mButton_query = findViewById(R.id.button_query_sqlite_database);
        mButton_update = findViewById(R.id.button_update_sqlite_database);
        mButton_delete = findViewById(R.id.button_delete_sqlite_database);

        mEditText_execSQL = findViewById(R.id.edit_text_execsql_sqlite_database);
        mButton_execSQL = findViewById(R.id.button_execsql_sqlite_database);

        mButton_execSQL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mEditText_execSQL.getText().toString().length() != 0) {
                    mSQLiteDatabase.execSQL(mEditText_execSQL.getText().toString());
                }
            }
        });


        mSQLiteOpenHelper = new MySQLiteOpenHelper(this, "SQLiteDemo.db", null, 1);
        //只有当储存空间满了的时候，getReaderDatabase（）会与getWriterDatabase不同
        mSQLiteDatabase = mSQLiteOpenHelper.getWritableDatabase();  //返回数据库对象，如果没有问题，与getWritableDatabase完全相同，如果数据库满了，则返回的数据库只读不可写
        //mSQLiteDatabase = mSQLiteOpenHelper.getWritableDatabase(); 返回数据库对象，可读写、
//        mSQLiteDatabase.execSQL("DROP TABLE sqliteDemoTable");
        boolean table_exist=false;
        String sql= "select DISTINCT tbl_name from sqlite_master where tbl_name = 'sqliteDemoTable'";
        try{
            mCursor = mSQLiteDatabase.rawQuery(sql, null);
            if(mCursor!=null){
                int count = mCursor.getCount();
                if(count>0){
                    table_exist = true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        if(!table_exist) {
            mSQLiteDatabase.execSQL("CREATE TABLE sqliteDemoTable(_id INTEGER PRIMARY KEY NOT NULL,int INTEGER,string TEXT,float REAL);");
        }
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("int", 520);
//        contentValues.put("string", "GongYue I love you!");
//        contentValues.put("float", 0.1314);
        //三个参数 表名，希望插入NULL值的列名，contentValues对象（键-值对）
//        long rawId = mSQLiteDatabase.insert("sqliteDemoTable", null, contentValues);
//        Toast.makeText(this, "rawId="+String.valueOf(rawId), Toast.LENGTH_SHORT).show();
        updateListView();

        mCursor = mSQLiteDatabase.query(
                "sqliteDemoTable",
                null,
                null,
                null,
                null,
                null,
                null);
//        mSQLiteDatabase.query(
//                table,  //表名
//                columns,    //列名 String[]
//                selection,  //where后边的语名
//                selectionArgs,  //行条件参数 String[]
//                groupBy,    //group by 后面的语句
//                having,
//                orderby);


//        mSQLiteDatabase.close();
//        mSQLiteOpenHelper.close();
    }


    public void crudOperation(View view) {

        switch (view.getId()) {
            case R.id.button_insert_sqlite_database:
                insert();
                break;
            case R.id.button_query_sqlite_database:
                query();
                break;
            case R.id.button_update_sqlite_database:
                update();
                break;
            case R.id.button_delete_sqlite_database:
                delete();
                break;
            default:
        }
    }

    private void insert() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("int", Integer.parseInt(mEditText_int.getText().toString()));
        contentValues.put("string", mEditText_stirng.getText().toString());
        contentValues.put("float", Float.parseFloat(mEditText_float.getText().toString()));
        mSQLiteDatabase.insert("sqliteDemoTable", null, contentValues);
        updateListView();

    }

    private void query() {
        int query_id = Integer.valueOf(mTextView_id.getText().toString());
        mCursor = mSQLiteDatabase.query("sqliteDemoTable", null, null, null, null, null, null);
        while (mCursor.moveToNext()){
            if (mCursor.getInt(mCursor.getColumnIndex("_id"))==query_id) {
                cursorDisplay();
            }
        }
        updateListView();
    }

    private void update() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("int", Integer.parseInt(mEditText_int.getText().toString()));
        contentValues.put("string", mEditText_stirng.getText().toString());
        contentValues.put("float", Float.parseFloat(mEditText_float.getText().toString()));


        String update_id = mTextView_id.getText().toString();

        String whereClause = "_id=?";
        String[] whereArgs = new String[]{update_id};

        mSQLiteDatabase.update("sqliteDemoTable", contentValues, whereClause, whereArgs);
        updateListView();

    }

    private void delete() {
        String update_id = mTextView_id.getText().toString();
        String[] whereArgs = new String[]{update_id,String.valueOf(Integer.parseInt(update_id)+2)};

        StringBuilder stringBuilder = new StringBuilder();
        //用for循环生成 ?,?,......,?,?
        for(int i=0;i<whereArgs.length;i++){
            if(i!=0){
                stringBuilder.append(",");
            }
            stringBuilder.append("?");
        }
        String whereClause = "_id IN ("+stringBuilder.toString()+")";

//      应该用这样的语法删除多行database.delete("rows", "id IN (?, ?, ?, ?)", ids );
        mSQLiteDatabase.delete("sqliteDemoTable", whereClause, whereArgs);
        updateListView();

    }


    public void cursorOperation(View view) {
        switch (view.getId()) {
            case R.id.button_cursor_first_sqlite_database:
                if (mCursor.moveToFirst())
                    cursorDisplay();
                break;
            case R.id.button_cursor_previous_sqlite_database:
                if (mCursor.moveToPrevious())
                    cursorDisplay();
                break;
            case R.id.button_cursor_next_sqlite_database:
                if (mCursor.moveToNext())
                    cursorDisplay();
                break;
            case R.id.button_cursor_last_sqlite_database:
                if (mCursor.moveToLast())
                    cursorDisplay();
                break;
        }
    }

    private void cursorDisplay() {
        int id_value = mCursor.getInt(mCursor.getColumnIndex("_id"));
        int int_value = mCursor.getInt(mCursor.getColumnIndex("int"));
        String string_value = mCursor.getString(mCursor.getColumnIndex("string"));
        float float_value = mCursor.getFloat(mCursor.getColumnIndex("float"));

        mTextView_id.setText(String.valueOf(id_value));
        mEditText_int.setText(String.valueOf(int_value));
        mEditText_stirng.setText(string_value);
        mEditText_float.setText(String.valueOf(float_value));
    }

    private void updateListView() {
        Cursor cur = mSQLiteDatabase.query(
                "sqliteDemoTable",
                null,
                null,
                null,
                null,
                null,
                null);
        /*
        显示Cursor的第一种方法，实现CursorAdapter抽象类
         */
//        CursorAdapter cursorAdapter = new CursorAdapter(this,cur) {
//            @Override
//            public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
//                View view= LayoutInflater.from(SQLiteDatabaseActivity.this).inflate(R.layout.item_sqlite_database,null);
//                return view;
//            }
//
//            @Override
//            public void bindView(View view, Context context, Cursor cursor) {
//                TextView text_id=view.findViewById(R.id.item_id_sqlite_database);
//                TextView text_int=view.findViewById(R.id.item_int_sqlite_database);
//                TextView text_string=view.findViewById(R.id.item_string_sqlite_database);
//                TextView text_float=view.findViewById(R.id.item_float_sqlite_database);
//
//                int columnIndex_id=cursor.getColumnIndex("_id");
//                int _id = cursor.getInt(columnIndex_id);
//
//                int columnIndex_string=cursor.getColumnIndex("string");
//                String string=cursor.getString(columnIndex_string);
//
//                int  columnIndex_boolean=cursor.getColumnIndex("boolean");
//                int boolean_value=cursor.getInt(columnIndex_boolean);
//
//                text_id.setText(String.valueOf(_id));
//                text_string.setText(string);
//                text_float.setText(String.valueOf(boolean_value));
//
//            }
//        };
//        mListView.setAdapter(cursorAdapter);


        /*
        显示Cursor的第二种方法，SampleCursorAdapter
         */
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(SQLiteDatabaseActivity.this, R.layout.item_sqlite_database, cur, new String[]{"_id", "int", "string", "float"}, new int[]{R.id.item_id_sqlite_database, R.id.item_int_sqlite_database, R.id.item_string_sqlite_database, R.id.item_float_sqlite_database});
        mListView.setAdapter(simpleCursorAdapter);
    }

}


/********************************************************************************




 /*
 基本不区分大小写
 SQLite数据类型NULL,INTEGER,REAL,TEXT,BLOB
 SQLite数据库语句
 1. 创建Table：CREATE TABLE table_name(column1 datatype  PRIMARY KEY(one or more columns) NOT NULL, column2 datatype, ... ...  columnN datatype); 最好有一column _id
 2. 删除Table：DROP TABLE table_name;
 3. 插入：
 INSERT INTO TABLE_NAME [(column1, column2, column3,...columnN)]
 VALUES (value1, value2, value3,...valueN);
 例如：
 INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY)
 VALUES (1, 'Paul', 32, 'California', 20000.00 );

 当要为表中所有列添加值：
 INSERT INTO TABLE_NAME VALUES (value1,value2,value3,...valueN);
 例如：
 INSERT INTO COMPANY VALUES (7, 'James', 24, 'Houston', 10000.00 );
 4. 选择：
 SELECT column1, column2, columnN FROM table_name; 选择哪几列的数据
 SELECT * FROM table_name; 选择表的所有字段

 AND	AND 运算符允许在一个 SQL 语句的 WHERE 子句中的多个条件的存在。
 BETWEEN	BETWEEN 运算符用于在给定最小值和最大值范围内的一系列值中搜索值。
 EXISTS	EXISTS 运算符用于在满足一定条件的指定表中搜索行的存在。
 IN	IN 运算符用于把某个值与一系列指定列表的值进行比较。
 NOT IN	IN 运算符的对立面，用于把某个值与不在一系列指定列表的值进行比较。
 LIKE	LIKE 运算符用于把某个值与使用通配符运算符的相似值进行比较。
 GLOB	GLOB 运算符用于把某个值与使用通配符运算符的相似值进行比较。GLOB 与 LIKE 不同之处在于，它是大小写敏感的。
 NOT	NOT 运算符是所用的逻辑运算符的对立面。比如 NOT EXISTS、NOT BETWEEN、NOT IN，等等。它是否定运算符。
 OR	OR 运算符用于结合一个 SQL 语句的 WHERE 子句中的多个条件。
 IS NULL	NULL 运算符用于把某个值与 NULL 值进行比较。
 IS	IS 运算符与 = 相似。
 IS NOT	IS NOT 运算符与 != 相似。
 ||	连接两个不同的字符串，得到一个新的字符串。
 UNIQUE	UNIQUE 运算符搜索指定表中的每一行，确保唯一性（无重复）。
 5.WHERE:
 SELECT column1, column2, columnN
 FROM table_name
 WHERE [CONDITION | EXPRESSION];

 6.AND/OR:
 SELECT column1, column2, columnN
 FROM table_name
 WHERE [condition1] AND [condition2]...AND [conditionN];

 SELECT column1, column2, columnN
 FROM table_name
 WHERE [condition1] OR [condition2]...OR [conditionN]
 7.更新:
 UPDATE table_name
 SET column1 = value1, column2 = value2...., columnN = valueN
 WHERE [condition];
 例如：
 UPDATE COMPANY SET ADDRESS = 'Texas' WHERE ID = 6;

 8.删除：
 DELETE FROM table_name
 WHERE [condition];
 例如：
 DELETE FROM COMPANY WHERE ID = 7;
 9.LIKE:
 这里有两个通配符与 LIKE 运算符一起使用：
 百分号 （%）
 下划线 （_）
 百分号（%）代表零个、一个或多个数字或字符。下划线（_）代表一个单一的数字或字符。这些符号可以被组合使用。

 SELECT column_list
 FROM table_name
 WHERE column LIKE 'XXXX%'

 or

 SELECT column_list
 FROM table_name
 WHERE column LIKE '%XXXX%'

 or

 SELECT column_list
 FROM table_name
 WHERE column LIKE 'XXXX_'

 or

 SELECT column_list
 FROM table_name
 WHERE column LIKE '_XXXX'

 or

 SELECT column_list
 FROM table_name
 WHERE column LIKE '_XXXX_'

 10.GLOB:
 与 LIKE 运算符不同的是，GLOB 是大小写敏感的，对于下面的通配符，它遵循 UNIX 的语法。
 星号 （*）
 问号 （?）
 星号（*）代表零个、一个或多个数字或字符。问号（?）代表一个单一的数字或字符。这些符号可以被组合使用。

 SELECT FROM table_name
 WHERE column GLOB 'XXXX*'

 or

 SELECT FROM table_name
 WHERE column GLOB '*XXXX*'

 or

 SELECT FROM table_name
 WHERE column GLOB 'XXXX?'

 or

 SELECT FROM table_name
 WHERE column GLOB '?XXXX'

 or

 SELECT FROM table_name
 WHERE column GLOB '?XXXX?'

 or

 SELECT FROM table_name
 WHERE column GLOB '????'

 11.LIMIT
 SQLite 的 LIMIT 子句用于限制由 SELECT 语句返回的数据数量。

 SELECT column1, column2, columnN
 FROM table_name
 LIMIT [no of rows]

 SELECT column1, column2, columnN
 FROM table_name
 LIMIT [no of rows] OFFSET [row num] //OFFSET代表到多少行为止

 12.ORDER BY:
 SELECT column-list
 FROM table_name
 [WHERE condition]
 [ORDER BY column1, column2, .. columnN] [ASC | DESC];

 13.GROUP BY:
 能够把每一列相同的人总结起来显示
 SELECT column-list
 FROM table_name
 WHERE [ conditions ]
 GROUP BY column1, column2....columnN        //GROUP BY 子句必须放在 WHERE 子句中的条件之后，必须放在 ORDER BY 子句之前。
 ORDER BY column1, column2....columnN

 14.HAVING:
 HAVING用来筛选有表中有几条这个数据的
 SELECT column1, column2
 FROM table1, table2
 WHERE [ conditions ]
 GROUP BY column1, column2
 HAVING [ conditions ]
 ORDER BY column1, column2
 例如：
 下面是一个实例，它将显示名称计数大于 2 的所有记录：
 SELECT * FROM COMPANY GROUP BY name HAVING count(name) > 2;

 15.DISTINCT:
 SELECT DISTINCT column1, column2,.....columnN
 FROM table_name
 WHERE [condition]
 例如：
 SELECT DISTINCT name FROM COMPANY;
 */