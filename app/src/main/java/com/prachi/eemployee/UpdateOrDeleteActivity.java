package com.prachi.eemployee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.prachi.eemployee.API.EmployeeAPI;
import com.prachi.eemployee.model.Employee;
import com.prachi.eemployee.model.EmployeeCUD;
import com.prachi.eemployee.url.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateOrDeleteActivity extends AppCompatActivity {

    EditText etEmployee,etName,etAge,etSalary;
    Button btnS,btnUpdate,btnDelete;
    Retrofit retrofit;
    EmployeeAPI employeeAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_or_delete);

        etEmployee=findViewById(R.id.etEmployeed);
        etName=findViewById(R.id.etNamed);
        etAge=findViewById(R.id.etAged);
        etSalary=findViewById(R.id.etSalaryd);

        btnS=findViewById(R.id.btnS);
        btnS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();

            }
        });
        btnUpdate=findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               updateEmployee();

            }
        });


        btnDelete=findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteEmployee();

            }
        });


    }
  /*  private  void CreateInstance(){
         retrofit = new Retrofit.Builder()
                .baseUrl(URL.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        employeeAPI=retrofit.create(EmployeeAPI.class);
    }*/

    private void loadData(){


       // CreateInstance();
        EmployeeAPI employeeAPI=URL.CreateInstance().create(EmployeeAPI.class);
        Call<Employee> listCall=employeeAPI.getEmployeeById(Integer.parseInt(etEmployee.getText().toString()));

        listCall.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                etName.setText(response.body().getEmployee_name());
                etSalary.setText(Float.toString(response.body().getEmployee_salary()));
                etAge.setText(Integer.toString(response.body().getEmployee_age()));

            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {

                Toast.makeText(UpdateOrDeleteActivity.this, "Error" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private  void updateEmployee(){

       // CreateInstance();
        EmployeeAPI employeeAPI=URL.CreateInstance().create(EmployeeAPI.class);


        EmployeeCUD employee=new EmployeeCUD(
                etName.getText().toString(),
                Float.parseFloat(etSalary.getText().toString()),
                Integer.parseInt(etAge.getText().toString())

        );
        Call<Void> voidCall=employeeAPI.updateEmployee(Integer.parseInt(etEmployee.getText().toString()),employee);

        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(UpdateOrDeleteActivity.this, "Updated", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(UpdateOrDeleteActivity.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });




    }
    private void deleteEmployee(){
       // CreateInstance();
        EmployeeAPI employeeAPI=URL.CreateInstance().create(EmployeeAPI.class);



        Call<Void> voidCall=employeeAPI.deleteEmployee(Integer.parseInt(etEmployee.getText().toString()));

        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(UpdateOrDeleteActivity.this, "deleted sucessfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(UpdateOrDeleteActivity.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });

    }

}
