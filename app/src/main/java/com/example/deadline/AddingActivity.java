package com.example.deadline;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class AddingActivity extends AppCompatActivity {

    EditText name;
    EditText description;
    Button data;
    Button time;
    Button save;

    Calendar dateAndTime = Calendar.getInstance();

    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    long userId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding);

        name = findViewById(R.id.name);
        description = findViewById(R.id.info);

        data = findViewById(R.id.Data);
        time = findViewById(R.id.Time);
        save = findViewById(R.id.save);

        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDatePickerDialogAfter(data);
            }
        });

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    public void saveToDb(View view) {

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NAME, name.getText().toString());
        values.put(DatabaseHelper.COLUMN_DATE, data.getText().toString());
        values.put(DatabaseHelper.COLUMN_TIME, time.getText().toString());


        db.insert(DatabaseHelper.TABLE, null, values);
        getToMainRes();

    }

    // установка начальных даты и времени
    private void setInitialDateTime(TextView textView) {

        textView.setText(DateUtils.formatDateTime(this,
                dateAndTime.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME));
    }

    // метод для выбора времени
//    public void setTimePickerDialog(TextView textView) {
//        TimePickerDialog timePickerDialog = new TimePickerDialog(AddingActivity.this,
//                // тема spinner
//                android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
//                (tp, sHour, sMinute) -> {
//                    dateAndTime.set(Calendar.HOUR_OF_DAY, sHour);
//                    dateAndTime.set(Calendar.MINUTE, sMinute);
//                    setInitialDateTime(textView);
//                }, 2, 3, false);
//        timePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
//        timePickerDialog.show();
//
//         установка обработчика выбора времени
//        TimePickerDialog.OnTimeSetListener t=new TimePickerDialog.OnTimeSetListener() {
//            public void onTimeSet(TimePicker view, int hourOfDay, int minute, boolean is24HourView) {
//                dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
//                dateAndTime.set(Calendar.MINUTE, minute);
//                setInitialDateTime(textView);
//            }
//        };
//
//
//    }
    int DIALOG_TIME = 1;
    int myHour = 14;
    int myMinute = 35;
    TimePickerDialog.OnTimeSetListener myCallBack = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            myHour = hourOfDay ;
            myMinute = minute;
            time.setText("Time is " + myHour + " hours " + myMinute + " minutes");
        }
    };



    //метод для выбора начальной даты
    public void setDatePickerDialogAfter(TextView textView2) {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {

                textView2.setText(dayOfMonth + "." + (monthOfYear + 1) + "." + year);

            }
        };

    }

    private void getToMainRes(){
        // закрываем подключение
        db.close();
        // переход к главной activity
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

}