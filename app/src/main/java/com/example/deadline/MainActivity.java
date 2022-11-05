package com.example.deadline;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class MainActivity extends AppCompatActivity {

    Button adding;
    Intent addIntent;

    ListView pillList;
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Cursor pillCursor;
    SimpleCursorAdapter pillAdapter;
    Intent intent;

    public static long userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adding =findViewById(R.id.adding);
        addIntent = new Intent(this, AddingActivity.class);
        adding.setOnClickListener(view -> startActivity(addIntent));

        pillList = findViewById(R.id.listView);
        pillList.setOnItemClickListener((parent, view, position, id) -> {
            intent = new Intent(getApplicationContext(), InfoActivity.class);
            intent.putExtra("id", id);
            FragmentManager manager = getSupportFragmentManager();
            myDialogFragment myDialogFragment = new myDialogFragment();
            myDialogFragment.show(manager, "Выбор:");
            userId = id;
            System.out.println(userId);
        });


        databaseHelper = new DatabaseHelper(getApplicationContext());

    }

    @Override
    public void onResume() {
        super.onResume();
        // открываем подключение
        db = databaseHelper.getReadableDatabase();

        //получаем данные из бд в виде курсора
        pillCursor = db.rawQuery("select " + DatabaseHelper.COLUMN_ID + ", " + DatabaseHelper.COLUMN_NAME  + ", " + DatabaseHelper.COLUMN_DATE + " from " + DatabaseHelper.TABLE, null);
        // определяем, какие столбцы из курсора будут выводиться в ListView
        String[] headers = new String[]{DatabaseHelper.COLUMN_NAME, DatabaseHelper.COLUMN_DATE};
        // создаем адаптер, передаем в него курсор
        pillAdapter = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item,
                pillCursor, headers, new int[]{android.R.id.text1, android.R.id.text2}, 0);
        pillList.setAdapter(pillAdapter);


    }

    // по нажатию на кнопку запускаем UserActivity для добавления данных ✓


    @Override
    public void onDestroy() {
        super.onDestroy();
        // Закрываем подключение и курсор
        db.close();
        pillCursor.close();
    }


    public void intentId(long userId) {
        Bundle extras = getIntent().getExtras();
        userId = extras.getLong("id");



    }

}