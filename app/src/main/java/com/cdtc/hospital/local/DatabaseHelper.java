package com.cdtc.hospital.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.cdtc.hospital.base.App;

/**
 * Created by Sweven on 2018/11/28.
 * Email:sweventears@Foxmail.com
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;//默认的数据库版本

    //继承SQLiteOpenHelper类的类必须有自己的构造函数
    //该构造函数4个参数，直接调用父类的构造函数。其中第一个参数为该类本身；第二个参数为数据库的名字；
    //第3个参数是用来设置游标对象的，这里一般设置为null；参数四是数据库的版本号。
    private DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //该构造函数有3个参数，因为它把上面函数的第3个参数固定为null了
    DatabaseHelper(Context context, String name, int version) {
        this(context, name, null, version);
    }

    //该构造函数只有2个参数，在上面函数 的基础山将版本号固定了
    public DatabaseHelper(Context context, String name) {
        this(context, name, VERSION);
    }

    //该函数在数据库第一次被建立时调用
    @Override
    public void onCreate(SQLiteDatabase arg0) {
        System.out.println("create a sqlite database");
        //execSQL()为执行参数里面的SQL语句，因此参数中的语句需要符合SQL语法,这里是创建一个表

        // user表
        arg0.execSQL("CREATE TABLE " + App.TABLE_USER + " (" +
                App.TableUser.LOGIN_NAME + " VARCHAR(32) NOT NULL," +
                App.TableUser.PASSWORD + " VARCHAR(80) NOT NULL," +
                App.TableUser.TRUE_NAME + " VARCHAR(80) NOT NULL," +
                App.TableUser.EMAIL + " VARCHAR(80) DEFAULT NULL," +
//                " u_loginTime VARCHAR(80) NOT NULL," +
//                " u_exitAppTime VARCHAR(80) NOT NULL," +
                App.TableUser.STATE + " INT(1)  NOT NULL DEFAULT '0'," +
                "  PRIMARY KEY (" + App.TableUser.LOGIN_NAME + ")" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        System.out.println("update a sqlite database");
    }
}
