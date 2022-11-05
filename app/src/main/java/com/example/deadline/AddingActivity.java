package com.example.deadline;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;
import java.util.Locale;

public class AddingActivity extends AppCompatActivity {

    EditText name;
    EditText description;
    Button data;
    Button time;
    Button save;

    DatePickerDialog datePickerDialog;

    int hour;
    int minute;

    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding);

        name = findViewById(R.id.name);
        description = findViewById(R.id.info);

        sqlHelper = new DatabaseHelper(this);
        db = sqlHelper.getWritableDatabase();

        data = findViewById(R.id.Data);
        time = findViewById(R.id.Time);
        save = findViewById(R.id.save);

        initDatePicker();
        data.setOnClickListener(view -> openDatePicker(data));

        time.setOnClickListener(view -> setTimePicker(time));


    }


    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = (datePicker, year, month, day) -> {
            month = month + 1;
            String date = makeDateString(day, month, year);
            data.setText(date);
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_DEVICE_DEFAULT_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    private String makeDateString(int day, int month, int year)
    {
        return month + "." + day + "." + year;
    }

    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }

    public void setTimePicker(TextView textView) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = (timePicker, selectedHour, selectedMinute) -> {
            hour = selectedHour;
            minute = selectedMinute;
            textView.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));
        };

        int style = AlertDialog.THEME_HOLO_LIGHT;

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, style, onTimeSetListener, hour, minute, true);

        timePickerDialog.setTitle("До какого времени");
        timePickerDialog.show();

    }

    public void saveToDb(View view) {

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NAME, name.getText().toString());
        values.put(DatabaseHelper.COLUMN_DATE, data.getText().toString());
        values.put(DatabaseHelper.COLUMN_TIME, time.getText().toString());
        values.put(DatabaseHelper.COLUMN_INFO, description.getText().toString());

        Toast toast = Toast.makeText(getApplicationContext(),
                description.getText().toString(), Toast.LENGTH_SHORT);
        toast.show();

        db.insert(DatabaseHelper.TABLE, DatabaseHelper.COLUMN_INFO, values);
        getToMainRes();

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