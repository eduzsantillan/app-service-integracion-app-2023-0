package com.isil.appservice.customer;


import lombok.Builder;
import lombok.Data;

import java.util.Calendar;
import java.util.Date;

@Data
@Builder
public class CustomerDTO {

    private String fullname ;

    private String email;

    private String address;

    private String documentId;

    private Boolean isCreatedThisWeek;

    private String saludo;



    public CustomerDTO initFullname(String name, String lastname){
        this.fullname = name+" "+lastname;
        return this;
    }

    public CustomerDTO initCreatedThisWeek(Date createdAt){

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK,calendar.getFirstDayOfWeek());
        Date startOfWeek = calendar.getTime();
        calendar.add(Calendar.DATE,7);
        Date endOfWeek = calendar.getTime();

        if(createdAt.after(startOfWeek) && createdAt.before(endOfWeek)){
            this.isCreatedThisWeek= true;
        }else{
            this.isCreatedThisWeek = false;
        }

        return this;
    }

}
