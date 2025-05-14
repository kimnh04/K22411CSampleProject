package com.nguyenkim.connectors;

import com.nguyenkim.k22411csampleproject.models.Customer;
import com.nguyenkim.k22411csampleproject.models.ListCustomer;

import java.util.ArrayList;

public class CustomerConnector {
    ListCustomer listCustomer;
    public CustomerConnector(){
        listCustomer=new ListCustomer();
        listCustomer.generate_sample_dataset();
    }
    public ArrayList<Customer> get_all_customers()
    {
        if (listCustomer==null)
        {
            listCustomer=new ListCustomer();
            listCustomer.generate_sample_dataset();
        }
        return listCustomer.getCustomers();
    }
    public ArrayList<Customer> get_customers_by_provide(String provider)
    {
        if (listCustomer==null)
        {
            listCustomer=new ListCustomer();
            listCustomer.generate_sample_dataset();
        }
        ArrayList<Customer>results=new ArrayList<>();
        for(Customer c: listCustomer.getCustomers())
        {
         if(c.getPhone().startsWith(provider))
         {
             results.add(c);
         }
        }
        return results;
    }

}
