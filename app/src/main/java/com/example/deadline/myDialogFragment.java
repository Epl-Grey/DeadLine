package com.example.deadline;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class myDialogFragment extends DialogFragment {
    long id2;
    Intent intent;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String title = "Выбрать";
        String message = "пункт 1, пункт 2";
        String button1String = "Информация";
        String button2String = "ВЫполнил";

        intent = new Intent(getContext(), InfoActivity.class);
        id2 = 0;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);  // заголовок
        builder.setMessage(message); // сообщение
        builder.setPositiveButton(button1String, (dialog, id) -> startActivity(intent));
        builder.setNegativeButton(button2String, (dialog, id) -> Toast.makeText(getActivity(), "Вы сделали правильный выбор",
                Toast.LENGTH_LONG).show());
        builder.setCancelable(true);

        return builder.create();
    }

}
