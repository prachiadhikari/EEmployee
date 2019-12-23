package com.prachi.eemployee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.prachi.eemployee.API.EmployeeAPI;
import com.prachi.eemployee.model.Employee;
import com.prachi.eemployee.url.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity {
    EditText etNameE;
    Button btnSearch;
    TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        etNameE=findViewById(R.id.etNameE);
        btnSearch=findViewById(R.id.btnSearch);
        tvData=findViewById(R.id.tvData);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              loadData();

            }
        });




    }

    private void loadData()
    {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        EmployeeAPI employeeAPI=retrofit.create(EmployeeAPI.class);

        Call<Employee> listCall =employeeAPI.getEmployeeById(Integer.parseInt(etNameE.getText().toString()));
        
        listCall.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {

                Toast.makeText(SearchActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();
                String content="";
                content+="Id : " + response.body().getId()+"\n";
                content+="name : " + response.body().getEmployee_name()+"\n";
                content+="Age : " + response.body().getEmployee_age()+"\n";
                content+="Salary : " + response.body().getEmployee_salary()+"\n";

                tvData.setText(content);

            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {

                Toast.makeText(SearchActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
