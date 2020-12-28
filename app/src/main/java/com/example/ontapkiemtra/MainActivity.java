package com.example.ontapkiemtra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ontapkiemtra.Adapter.PhoneBookAdapter;
import com.example.ontapkiemtra.Data.DBManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<PhoneBook> listPhone;

    Button btn_edit, btn_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv_danhba);
        listPhone = new ArrayList<>();
        btn_edit = (Button) findViewById(R.id.btn_edit);
        btn_delete = (Button) findViewById(R.id.btn_delete);
        listPhone = null;
        DBManager dbManager = new DBManager(getApplicationContext());

        setTitle("Phone Book");

        //lay danh sach ban tu database
        listPhone = dbManager.selectListTable();

        PhoneBookAdapter PBAdapter = new PhoneBookAdapter(getApplicationContext(), listPhone);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(PBAdapter);
        recyclerView.hasFixedSize();
    }


    //open activity add new
    public void addNew(View view) {
        Intent i = new Intent(MainActivity.this, AddNewActivity.class);
        startActivity(i);
    }

    public void search(View view) {
        Intent i = new Intent(MainActivity.this, SearchActivity.class);
        startActivity(i);
    }
}