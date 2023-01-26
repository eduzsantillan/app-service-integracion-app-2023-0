package com.isil.appservice.customer;


import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;


@Data
@Entity(name="Customer")
@Table(name="tbl_customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String lastname;

    @Column(name="email",length=50,nullable = false,unique = true)
    private String email;

    private String address;

    private String documentId;

    private String password;

    private Date createdAt;




    @PostPersist
    public void postPersist(){
        this.createdAt=new Date();
    }

}
