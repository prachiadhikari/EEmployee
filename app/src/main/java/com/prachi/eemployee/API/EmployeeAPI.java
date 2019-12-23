package com.prachi.eemployee.API;

import com.prachi.eemployee.model.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EmployeeAPI {

    @GET("employees")
    Call<List<Employee>> getAllEmployees();

    @GET("employee/{empID}")
    Call<Employee> getEmployeeById(@Path("empID")int empId);
}
