package com.example.thiandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edName;
    private EditText edQuantity;
    private Button btnAdd;
    private Button btnList;
    private DBHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        db = new DBHelper(this);
        db.getReadableDatabase();
    }

    private void initView() {
        edName = findViewById(R.id.ed_name);
        edQuantity = findViewById(R.id.ed_quantity);
        btnList = findViewById(R.id.btn_list);
        btnAdd = findViewById(R.id.btn_add);
        btnList.setOnClickListener(this);
        btnAdd.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if (view == btnAdd) {
            onAdd();
        } else if (view == btnList){
            onList();
        }
    }

    private void onList() {
        Intent intent = new Intent(MainActivity.this, ListProductActivity.class);
        startActivity(intent);
    }

    private void onAdd() {
        if (edName.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter name", Toast.LENGTH_LONG).show();
            return;
        }

        String isAdd = db.addProduct(edName.getText().toString(),
                edQuantity.getText().toString());
        Toast.makeText(this, isAdd, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(MainActivity.this, ListProductActivity.class);
        startActivity(intent);
    }
}