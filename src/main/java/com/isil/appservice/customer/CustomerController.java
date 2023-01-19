package com.isil.appservice.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/add-customer")
    public ResponseEntity<Map<String,String>> addCustomer(
            @RequestBody Customer customer
    ){
        HashMap<String, String> response = new HashMap<>();
        try{
            customerService.addCustomer(customer);
            response.put("result","Cliente añadido correctamente");
            return ResponseEntity.status(201).body(response);
        }catch (Exception e){
            response.put("error",e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }


    @GetMapping({"/",""})
    public ResponseEntity<List<Customer>> getAll(){
        return ResponseEntity.ok(customerService.findAllCustomers());
    }

    @GetMapping("/get-by-email")
    public ResponseEntity<Map<String,Object>> getByEmail(
            @RequestParam(name="email",defaultValue = "") String email
    ){
        HashMap<String, Object> response = new HashMap<>();
        Customer responseCustomer = customerService.findCustomerByEmail(email);
        if(responseCustomer==null){
            response.put("error","No se encontro al cliente con el email " + email);
            return ResponseEntity.badRequest().body(response);
        }else{
            response.put("cliente",responseCustomer);
            return ResponseEntity.ok(response);
        }
    }

    @PutMapping("/update-by-email")
    public ResponseEntity<Map<String,String>> updateByEmail(
            @RequestBody Customer customerInput,
            @RequestParam(name="email",defaultValue = "")String email
    ){
        HashMap<String, String> response = new HashMap<>();
        try{
            customerService.updateCustomer(customerInput,email);
            response.put("resultado :","Cliente actualizado correctamente");
            return ResponseEntity.ok(response);
        }catch (Exception e){
            response.put("error :",e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("delete")
    public ResponseEntity<Map<String,String>> deleteCustomer(
            @RequestParam(name="id",required = true) Long id
    ){
        HashMap<String, String> response = new HashMap<>();
        try{
            customerService.deleteCustomer(id);
            response.put("resultado :","Cliente eliminado correctamente");
            return ResponseEntity.ok(response);
        }catch (Exception e){
            response.put("error :",e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }









}
