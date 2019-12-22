package com.prachi.eemployee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prachi.eemployee.EmployeeView;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<com.prachi.eemployee.EmployeeAdapter.EmployeeViewHolder> {

    Context mcontext;
    List<EmployeeView> employeeViewList;

    public EmployeeAdapter(Context mcontext, List<EmployeeView> employeeViewList) {
        this.mcontext = mcontext;
        this.employeeViewList = employeeViewList;
    }



    @NonNull
    @Override
    public com.prachi.eemployee.EmployeeAdapter.EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_employee,parent,false);
        return new com.prachi.eemployee.EmployeeAdapter.EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull com.prachi.eemployee.EmployeeAdapter.EmployeeViewHolder holder, int position) {

        EmployeeView employeeView = employeeViewList.get(position);
        holder.tvEmployeeId.setText(employeeView.getId()+"");
        holder.tvEmployeeName.setText(employeeView.getEmployee_name());
        // holder.tvEmployeeSalary.setText(employeeView.getEmployee_salary());
        holder.tvEmployeeAge.setText(employeeView.getEmployee_age()+"");
    }

    @Override
    public int getItemCount() {
        return employeeViewList.size();

    }
    public class EmployeeViewHolder extends RecyclerView.ViewHolder{
        TextView tvEmployeeId,tvEmployeeName,tvEmployeeSalary,tvEmployeeAge;
        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvEmployeeId = itemView.findViewById(R.id.tvEmployeeId);
            tvEmployeeName = itemView.findViewById(R.id.tvEmployeeName);
            tvEmployeeSalary = itemView.findViewById(R.id.tvEmployeeSalary);
            tvEmployeeAge = itemView.findViewById(R.id.tvEmployeeAge);
        }
    }

}

