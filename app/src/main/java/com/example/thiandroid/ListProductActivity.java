package com.example.thiandroid;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

public class ListProductActivity extends AppCompatActivity {

    private DBHelper db;
    private Cursor c;
    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);

        db = new DBHelper(this);
        ListView lvUser = findViewById(R.id.lvProduct);

        c = db.getAllUser();

        adapter = new SimpleCursorAdapter(this, R.layout.item_product, c,
                new String[]{DBHelper.ID, DBHelper.NAME, DBHelper.QUANTITY},
                new int[]{R.id.tvId, R.id.tvName, R.id.tvQuantity},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        lvUser.setAdapter(adapter);
        lvUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) adapter.getItem(position);
                @SuppressLint("Range") int _id = cursor.getInt(cursor.getColumnIndex(DBHelper.ID));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(DBHelper.NAME));
                @SuppressLint("Range") String quantity = cursor.getString(cursor.getColumnIndex(DBHelper.QUANTITY));

                Intent intent = new Intent(ListProductActivity.this, MainActivity.class);
                intent.putExtra(DBHelper.ID, _id);
                intent.putExtra(DBHelper.NAME, name);
                intent.putExtra(DBHelper.QUANTITY, quantity);

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Reload data in list view
        c = db.getAllUser();
        adapter.changeCursor(c);
        adapter.notifyDataSetChanged();
        db.close();
    }
}