package com.example.ontapkiemtra;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ontapkiemtra.Data.DBManager;

public class AddNewActivity extends AppCompatActivity {

    EditText edt_name, edt_number;
    Button btn_save;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);


        setTitle("New");
        anhxa();
    }

    private void anhxa() {
        edt_name = findViewById(R.id.edt_name);
        edt_number = findViewById(R.id.edt_number);
        btn_save = findViewById(R.id.btn_save);
    }

    public void save(View view) {


        String name = edt_name.getText().toString();
        String number = edt_number.getText().toString();
        if (name.matches("") || number.matches("")) {
            Toast.makeText(getApplicationContext(), "chua nhap", Toast.LENGTH_SHORT).show();
        } else {

            DBManager db = new DBManager(getApplicationContext());
            PhoneBook PB = addNumber();
            db.addNewPhone(PB);

            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }


    }

    public PhoneBook addNumber() {

        String name = edt_name.getText().toString();
        String number = edt_number.getText().toString();

        PhoneBook phone = new PhoneBook(name, number);
        return phone;
    }
}
