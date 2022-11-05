package com.example.deadline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    TextView name;
    TextView data;
    TextView time;
    TextView info;

    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    long userId=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        name = findViewById(R.id.name);
        data = findViewById(R.id.data);
        time = findViewById(R.id.time);
        info = findViewById(R.id.info);

        sqlHelper = new DatabaseHelper(this);
        db = sqlHelper.getWritableDatabase();

//        Bundle extras = getIntent().getExtras();
//        if (extras != null) {
//            userId = extras.getLong("id");
//        }
//
//        userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE + " where " +
//                DatabaseHelper.COLUMN_ID + "=?", new String[]{String.valueOf(userId)});
//        userCursor.moveToFirst();
//        name.setText(userCursor.getString(1));
//        data.setText(userCursor.getString(2));
//        time.setText(userCursor.getString(3));
//        info.setText(userCursor.getString(4));
//        userCursor.close();

    }

    public void backDoor(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}