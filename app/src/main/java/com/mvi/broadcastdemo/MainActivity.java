package com.mvi.broadcastdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView empty_call_list;
    private RecyclerView.LayoutManager layoutManager;
    private List<IncomingCalls> incomingCallsList = new ArrayList<IncomingCalls>();
    private IncomingCallAdapter adapter;
    private BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        empty_call_list = findViewById(R.id.empty_call_list);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new IncomingCallAdapter(incomingCallsList);
        recyclerView.setAdapter(adapter);
        readFromDb();

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                readFromDb();
            }
        };
    }

    private void readFromDb() {
        incomingCallsList.clear();
        DbHelper dbHelper = new DbHelper(this);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = dbHelper.readIncomingNos(database);

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {

                String number;
                int id;
                number = cursor.getString(cursor.getColumnIndex(DbContact.INCOMING_NUMBER));
                id = cursor.getInt(cursor.getColumnIndex(DbContact.ID));
                incomingCallsList.add(new IncomingCalls(id, number));
            }
            cursor.close();
            dbHelper.close();
            adapter.notifyDataSetChanged();
            recyclerView.setVisibility(View.VISIBLE);
            empty_call_list.setVisibility(View.GONE);


        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(broadcastReceiver, new IntentFilter(DbContact.UPDATE_UI_FILTER));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    }
}