package com.isil.appservice.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public void addCustomer(Customer customer) throws Exception {

        try{
            customerRepository.save(customer);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }

    @Override
    public void updateCustomer(Customer customer, Long id) throws Exception {

    }

    @Override
    public void updateCustomer(Customer customerInput, String email) throws Exception {
        String pattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        try{

            Optional<Customer> customerToUpdate = customerRepository.findCustomerByEmail(email);

            if( customerInput.getEmail()==null){
                throw new Exception("El correo no ha sido enviado en el request");
            }else{
                if(customerToUpdate.isEmpty()){
                    throw new Exception("Usuario con el correo "+email+" no existe");
                }
                if(customerInput.getEmail().isBlank()){
                    throw new Exception("El correo no puede ser vacio");
                }
                if(!customerInput.getEmail().matches(pattern)){
                    throw new Exception("El correo no tiene el formato correcto");
                }
            }

            Customer editCustomer = customerToUpdate.get();
            editCustomer.setName(customerInput.getName().isBlank()? editCustomer.getName(): customerInput.getName());
            editCustomer.setAddress(customerInput.getAddress().isBlank()? editCustomer.getAddress(): customerInput.getAddress());
            editCustomer.setLastname(customerInput.getLastname().isBlank()? editCustomer.getLastname(): customerInput.getLastname());
            editCustomer.setDocumentId(customerInput.getDocumentId().isBlank()? editCustomer.getDocumentId(): customerInput.getDocumentId());
            customerRepository.save(editCustomer);

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }

    @Override
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findCustomerByEmail(String email) {

        Optional<Customer> customerToFind = customerRepository.findCustomerByEmail(email);



        return customerToFind.orElse(null);

    }

    @Override
    public void deleteCustomer(Long id) {
        try{
            Optional<Customer> customerToDelete = customerRepository.findById(id);
            if(customerToDelete.isEmpty()){
                throw new Exception("El cliente no existe");
            }else{
                Customer deleteCustomer = customerToDelete.get();
                customerRepository.delete(deleteCustomer);
            }
        }catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }



}
