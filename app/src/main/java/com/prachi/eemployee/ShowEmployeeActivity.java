package com.prachi.eemployee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.prachi.eemployee.API.EmployeeAPI;
import com.prachi.eemployee.model.Employee;
import com.prachi.eemployee.url.URL;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShowEmployeeActivity extends AppCompatActivity {
   // TextView tvEmployee;
   private  EmployeeAdaptar employeeAdapter;
   RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_employee);


        //tvEmployee=findViewById(R.id.tvEmp);
        recyclerView = findViewById(R.id.recyclerView);



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        EmployeeAPI employeeAPI =retrofit.create(EmployeeAPI.class);

        Call<List<Employee>> listCall =employeeAPI.getAllEmployees();

        //asynchronous call

        listCall.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {

                if (!response.isSuccessful())
                {
                    Toast.makeText(ShowEmployeeActivity.this, "Error code" + response.code(), Toast.LENGTH_SHORT).show();
                    Log.d("msg","onFailure"+ response.code());

                    return;
                }
                LoadDataList(response.body());

                /*List<Employee> employeeList=response.body();

                for (Employee emp : employeeList)
                {*/
                  /*  String data ="";
                    data +="Name is:" +emp.getEmployee_name()+ "\n";
                    data +="Salary is:" +emp.getEmployee_salary()+ "\n";
                    data +="------------------------------------------"+ "\n";
                    tvEmployee.append(data);*/

                   /* int id,age;
                    String name;
                    float salary;
                    id = emp.getId();
                    age = emp.getEmployee_age();
                    name = emp.getEmployee_name();
                    salary = emp.getEmployee_salary();
                   employeeViewList.add(new EmployeeView(id,name,salary,age));
                    //employeeViewList.add(new EmployeeView(2,"You:Aw,come on.Seriously?",9879,2));
*/


                }



            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {

                Log.d ("Msg","onFailure" +  t.getLocalizedMessage());
                Toast.makeText(ShowEmployeeActivity.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
}
    //get refrence to the retrieved data as a list
    private void LoadDataList(List<Employee>employeeList){
        recyclerView=findViewById(R.id.recyclerView);
        employeeAdapter=new EmployeeAdaptar(employeeList);


        //using a linear layout manager
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(ShowEmployeeActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        //set adapter to the recyclerview
        recyclerView.setAdapter(employeeAdapter);
    }

    }

