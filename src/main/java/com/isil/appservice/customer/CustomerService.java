package com.isil.appservice.customer;

import java.util.List;

public interface CustomerService {

    void addCustomer(Customer customer) throws Exception;
    void updateCustomer(Customer customer,Long id) throws Exception;

    void updateCustomer(Customer customer,String email) throws Exception;
    List<CustomerDTO> findAllCustomers();
    Customer findCustomerByEmail(String email);
    void deleteCustomer(Long id);
}
