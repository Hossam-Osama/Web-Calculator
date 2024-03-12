package com.uni.calcdemo;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class claccontroller {
    @GetMapping("/divide")
    public String divide(double previous, double current){
        if(current==0){
            return "E";
        }
        return previous/current + "";
    }
    @GetMapping("/root")
    public String root(double current){
        if(current<0){
            return "Error";
        }
        return Math.sqrt(current)+ "";
    }
    @GetMapping("/reverse")
    public String reverse(double current){
        if(current==0){
            return "E";
        }
        return 1/current +"";
    }
    @GetMapping("/percent")
    public String percent(double current){
        return current/100 +"";
    }
    @GetMapping("/times")
    public String times(double previous, double current){
        return current * previous +"";
    }
    @GetMapping("/plus")
    public String plus(double previous, double current){
        return (current + previous) +"";
    }
    @GetMapping("/minus")
    public String minus(double previous, double current){
        return (previous-current) +"";
    }
    @GetMapping("/square")
    public String square(double current){
        return current * current +"";
    }
    @GetMapping("/sign")
    public String sign(double current){
        return (current)*(-1) +"";
    }
    @GetMapping("/show")
    public String show(){
        return "Enter a Second number";
    }
    @GetMapping("/hide")
    public String hide(){
        return "";
    }

}
