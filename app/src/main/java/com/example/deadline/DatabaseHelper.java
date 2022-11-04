package com.example.deadline;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "DeadLine.db"; // название бд
    private static final int SCHEMA = 1; // версия базы данных
    static final String TABLE = "tasks"; // название таблицы в бд
    // названия столбцов
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_TIME = "time";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE "+TABLE+" (" + COLUMN_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT, "
                + COLUMN_DATE + " TEXT, "
                + COLUMN_TIME + " TEXT );");

//        //добавление начальных данных
//        db.execSQL("INSERT INTO "+ TABLE +" ("
//                + COLUMN_NAME
//                + ", " + COLUMN_VALUE
//                + ", " + COLUMN_DOSAGE
//                + ", " + COLUMN_DATE1
//                + ", " + COLUMN_DATE2
//                + ", " + COLUMN_TIME1
//                + ", " + COLUMN_TIME2
//                + ", " + COLUMN_TIME3
//                + ", " + COLUMN_TIME4
//                + ", " + COLUMN_TIME5
//                + ", " + COLUMN_TIME6
//                + ", " + COLUMN_VALUETIME
//                + ") VALUES ('Працетомол', 2, 'Таблеток', '12.05.2022', '12.06.2022', '9:30', '10:30', '11:30', '13:30', '15:30', '17:30', 'ШЕСТЬ РАЗ В ДЕНЬ');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE);
        onCreate(db);
    }
}
