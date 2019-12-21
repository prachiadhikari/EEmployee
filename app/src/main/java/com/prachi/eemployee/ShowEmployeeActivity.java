package com.prachi.eemployee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class ShowEmployeeActivity extends AppCompatActivity {
    ListView lstEmployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_employee);

        lstEmployee=findViewById(R.id.lstEmployee);
    }
}
