package com.wrdijkstra.wrdcontent.activities;

import com.wrdijkstra.wrdcontent.controllers.WrdContentContract;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.wrdijkstra.wrdcontent.R;
import com.wrdijkstra.wrdcontent.daos.CounterDao;
import com.wrdijkstra.wrdcontent.vos.CounterVo;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ContentValues counter = new ContentValues();
        ContentValues counterUpdate = new ContentValues();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
