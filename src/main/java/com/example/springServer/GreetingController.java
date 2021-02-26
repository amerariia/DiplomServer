package com.example.springServer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class GreetingController {

    @GetMapping("/greeting")
    public String greeting(
            @RequestParam(name="name", required=false, defaultValue="World") String name,
            Map<String, Object> model
    ) {
        model.put("name", name);
        return "greeting";
    }
    @GetMapping("/")
    public String main(Map<String, Object> model){
        model.put("some", "azazazazazazza");
        return "main";
    }
    //    class A{
//        public String name;
//        public Integer age = 10;
//        public A(String f){
//            name = f;
//        }
//    }
//    @GetMapping("/greeting")
//    public A greeting(
//            @RequestParam(name="name", required=false, defaultValue="World") String name
//    ) {
//
//        //model.put("name", name);
//        return new A(name);
//    }
//    @GetMapping("/")
//    public String main(Map<String, Object> model){
//        model.put("some", "azazazazazazza");
//        return "main";
//    }

}