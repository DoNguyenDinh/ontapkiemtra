package com.example.ontapkiemtra.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ontapkiemtra.Data.DBManager;
import com.example.ontapkiemtra.EditActivity;
import com.example.ontapkiemtra.MainActivity;
import com.example.ontapkiemtra.PhoneBook;
import com.example.ontapkiemtra.R;

import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static androidx.core.content.ContextCompat.startActivity;

public class PhoneBookAdapter extends RecyclerView.Adapter<PhoneBookAdapter.PhoneBookViewHolder> {

    Context context;
    List<PhoneBook> list;

    public PhoneBookAdapter(Context context, List<PhoneBook> listPBook) {
        this.context = context;
        this.list = listPBook;
    }

    @NonNull
    @Override
    public PhoneBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View menuView = LayoutInflater.from(context).inflate(R.layout.item_phonebook, parent, false);

        PhoneBookViewHolder viewHolder = new PhoneBookViewHolder(menuView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneBookViewHolder holder, int position) {

        //
        PhoneBook phoneBook = list.get(position);
        holder.txt_name.setText(phoneBook.getName());
        holder.txt_number.setText(phoneBook.getNumber());
        holder.txt_ID.setText(phoneBook.getId() + "");


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PhoneBookViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_name;
        private TextView txt_number;
        private Button btn_edit, btn_del;
        private TextView txt_ID;

        public PhoneBookViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_ID = (TextView) itemView.findViewById(R.id.txt_id);

            txt_name = itemView.findViewById(R.id.txt_name);
            txt_number = itemView.findViewById(R.id.txt_phoneNumber);
            btn_del = (Button) itemView.findViewById(R.id.btn_delete);
            btn_edit = (Button) itemView.findViewById(R.id.btn_edit);


            btn_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String id = txt_ID.getText().toString();
                    String name = txt_name.getText() + "";
                    String number = txt_number.getText() + "";

                    Intent i = new Intent(context, EditActivity.class);
                    i.putExtra("idNum",id);
                    i.putExtra("name",name);
                    i.putExtra("number",number);

                    i.setFlags(FLAG_ACTIVITY_NEW_TASK);
                    startActivity(context, i, null);

                }
            });


            btn_del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos;//vi tri item duoc click
                    pos = getLayoutPosition();
                    DBManager db = new DBManager(context);

                    int id = Integer.parseInt(txt_ID.getText().toString());

                    db.deletenum(id);
                    Intent i = new Intent(context, MainActivity.class);
                    i.setFlags(FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);

                }
            });
        }
    }
}

