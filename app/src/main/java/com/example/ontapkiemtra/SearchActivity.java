package com.example.ontapkiemtra;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ontapkiemtra.Adapter.PhoneBookAdapter;
import com.example.ontapkiemtra.Data.DBManager;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    EditText edtSearch;
    TextView txtID,txtName,txtNumber;
    RecyclerView rv_result;
    List<PhoneBook>listPhone;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        edtSearch=(EditText)findViewById(R.id.edt_search);

        setTitle("Search");
        listPhone = new ArrayList<>();
        rv_result=(RecyclerView)findViewById(R.id.rv_result);
    }

    public void searchName(View view) {

        DBManager db=new DBManager(getApplicationContext());

        listPhone = db.searchName(edtSearch.getText().toString());

        if(listPhone.size()==0){
            Toast.makeText(getApplicationContext(),"not found",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getApplicationContext(),"found "+listPhone.size()+" people",Toast.LENGTH_SHORT).show();

            PhoneBookAdapter PBAdapter = new PhoneBookAdapter(getApplicationContext(), listPhone);

            rv_result.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            rv_result.setAdapter(PBAdapter);
            rv_result.hasFixedSize();
        }

    }

    public void searchNumber(View view) {
        DBManager db=new DBManager(getApplicationContext());

        listPhone = db.searchNumber(edtSearch.getText().toString());


        if(listPhone.size()==0){
            Toast.makeText(getApplicationContext(),"not found",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getApplicationContext(),"found "+listPhone.size()+" people",Toast.LENGTH_SHORT).show();

            PhoneBookAdapter PBAdapter = new PhoneBookAdapter(getApplicationContext(), listPhone);

            rv_result.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            rv_result.setAdapter(PBAdapter);
            rv_result.hasFixedSize();
        }




    }

    public void back(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
