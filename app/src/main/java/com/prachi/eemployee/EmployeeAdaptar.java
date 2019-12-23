package com.prachi.eemployee;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prachi.eemployee.model.Employee;

import java.util.List;

public class EmployeeAdaptar extends RecyclerView.Adapter<EmployeeAdaptar.EmployeeViewHolder>
{

    private List<Employee> employeeList;

    public EmployeeAdaptar(List<Employee>employeeList){
        this.employeeList=employeeList;


    }



    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_show_employee,parent, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        Employee employee = employeeList.get(position);
        holder.tvid.setText(Integer.toString(employee.getId()));
        holder.tvname.setText(employee.getEmployee_name());
        holder.tvage.setText(Integer.toString(employee.getEmployee_age()));
        //tvholder.tvsalary.setText(employee.getEmployee_salary().toString());
        holder.tvprofile.setText(employee.getProfile_image());
    }

    @Override
    public int getItemCount() {
        return employeeList.size()  ;
    }


    public class EmployeeViewHolder extends RecyclerView.ViewHolder{
        TextView tvid,tvname,tvage,tvsalary,tvprofile;
        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);

            tvid=itemView.findViewById(R.id.tvEmployeeId);
            tvname=itemView.findViewById(R.id.tvEmployeeName);
            tvage=itemView.findViewById(R.id.tvEmployeeAge);
            tvsalary=itemView.findViewById(R.id.tvEmployeeSalary);
            //tvprofile=itemView.findViewById(R.id.tvProfile);

        }

    }

}