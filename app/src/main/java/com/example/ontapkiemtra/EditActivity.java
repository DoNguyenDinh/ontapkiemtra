package com.example.ontapkiemtra;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ontapkiemtra.Data.DBManager;

public class EditActivity extends AppCompatActivity {

    EditText edtEditNumber, edtEditName;
    Button btnSaveEdit;
    TextView txt_EditID;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);


        anhxa();
        setTitle("Edit");
        getData();
    }

    private void anhxa() {
        edtEditNumber = (EditText) findViewById(R.id.edt_editNumber);
        edtEditName = (EditText) findViewById(R.id.edt_editName);
        btnSaveEdit = (Button) findViewById(R.id.btn_saveEdit);
        txt_EditID = (TextView) findViewById(R.id.txt_editID);
    }

    public void saveEdit(View view) {
        String name, number;
        number = edtEditNumber.getText().toString();
        name = edtEditName.getText().toString();
        int id;
        id=Integer.parseInt(txt_EditID.getText().toString());

        if (name.matches("") || number.matches("")) {
            Toast.makeText(getApplicationContext(), "gia tri nhap trong", Toast.LENGTH_SHORT).show();
        } else {

            DBManager db = new DBManager(getApplicationContext());
            db.updateNumber(id,name,number);


            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }

    }

    void getData() {

        Intent intent = getIntent();


        Bundle bundle = getIntent().getExtras();

        String id = bundle.getString("idNum");
        String name = bundle.getString("name");
        String number = bundle.getString("number");


        edtEditName.setText(name);
        edtEditNumber.setText(number);
        txt_EditID.setText(id);

    }

    public void cancel(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
