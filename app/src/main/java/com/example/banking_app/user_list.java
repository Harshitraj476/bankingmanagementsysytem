package com.example.banking_app;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class user_list extends AppCompatActivity {

    private static final String TAG = "user_list";
    List<Model> modelList_showlist = new ArrayList<>();
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    com.example.banking_app.CustomeAdapter_userlist adapter;
    String phonenumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_allusers);

        Log.d(TAG, "onCreate: userlist create");

        mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        showData();
    }

    private void showData() {
        Log.d(TAG, "showData: called");
        modelList_showlist.clear();
        Log.d(TAG, "showData: modellist cleared");
        Cursor cursor = new DatabaseUserNames(this).readalldata();
        while(cursor.moveToNext()){

            Log.d(TAG, "showData: inside cursor");
            String balancefromdb = cursor.getString(2);
            Double balance = Double.parseDouble(balancefromdb);

            NumberFormat nf = NumberFormat.getNumberInstance();
            nf.setGroupingUsed(true);
            nf.setMaximumFractionDigits(2);
            nf.setMinimumFractionDigits(2);
            String price = nf.format(balance);

            Model model = new Model(cursor.getString(0), cursor.getString(1), price);
            modelList_showlist.add(model);
        }
        Log.d(TAG, "showData: outside cursor");

        adapter = new com.example.banking_app.CustomeAdapter_userlist(com.example.banking_app.user_list.this, modelList_showlist);
        mRecyclerView.setAdapter(adapter);

    }

    public void nextActivity(int position) {
        Log.d(TAG, "nextActivity: called");
        phonenumber = modelList_showlist.get(position).getPhoneno();
        Intent intent = new Intent(com.example.banking_app.user_list.this, user_data.class);
        intent.putExtra("phonenumber",phonenumber);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_history, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.action_history){
            startActivity(new Intent(com.example.banking_app.user_list.this, com.example.banking_app.history_list.class));
        }
        return super.onOptionsItemSelected(item);
    }
}