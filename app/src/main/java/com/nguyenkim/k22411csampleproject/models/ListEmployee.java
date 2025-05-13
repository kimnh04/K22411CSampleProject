package com.nguyenkim.k22411csampleproject.models;

import java.util.ArrayList;

public class ListEmployee {
    private ArrayList<Employee> employees;
// generate constructor
    public ListEmployee() {
        employees = new ArrayList<>();
    }

//generate getter setter
    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    // giả lập data
    public void gen_dataset()
    {
        Employee e1=new Employee();
        e1.setId(1);
        e1.setName("john");
        e1.setEmail("john@gmail.com");
        e1.setPhone("38742399");
        e1.setUsername("john");
        e1.setPassword("123");
        employees.add(e1);

        Employee e2=new Employee();
        e2.setId(2);
        e2.setName("minah");
        e2.setEmail("minah@gmail.com");
        e2.setPhone("38732399");
        e2.setUsername("minah");
        e2.setPassword("433");
        employees.add(e2);

        Employee e3=new Employee();
        e3.setId(3);
        e3.setName("hanni");
        e3.setEmail("hanni@gmail.com");
        e3.setPhone("28732399");
        e3.setUsername("hanni");
        e3.setPassword("553");
        employees.add(e3);
    }
}
