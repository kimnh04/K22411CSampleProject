package com.nguyenkim.connectors;

import com.nguyenkim.k22411csampleproject.models.Employee;
import com.nguyenkim.k22411csampleproject.models.ListEmployee;

public class EmployeeConnector {
    public Employee login(String usr,String pwd)
    {
        // khi làm thực tế gom đoạn dưới sau bằng câu truy vấn là xong
        ListEmployee ls=new ListEmployee();
        ls.gen_dataset();
        for (Employee emp : ls.getEmployees())
        {
            if (emp.getUsername().equalsIgnoreCase(usr) && emp.getPassword().equals(pwd))
            {
                return emp;
            }
        }
        return null;
    }
}
