package com.isil.appservice.Player;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/","/home","/player"})
public class PlayerController {



    @GetMapping({"","/home","/index"})
    public String index(){
        return "Hola Mundo";
    }


    @GetMapping("/otracosa")
    public String otraCosa(){
        return "Hola, accediste al route  otracosa";
    }


    @GetMapping("/welcome")
    public String welcomeUser(@RequestParam(name="user",defaultValue = "anonymus") String user ){
        return "Hola, bienvenido " + user;
    }

    @GetMapping("/calculate")
    public int calculate(
            @RequestParam(name="num1", required = true) int num1,
            @RequestParam(name="num2", required = true) int num2
    ){
        return num1+num2;
    }
}
