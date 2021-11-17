package com.example.ConsumeApi.Controller;

import com.example.ConsumeApi.Model.User;
import com.example.ConsumeApi.Service.ServiceLayer;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private final ServiceLayer serviceLayer;
    @Autowired
    public HomeController(ServiceLayer serviceLayer) {
        this.serviceLayer = serviceLayer;
    }

    @GetMapping("/coin")
    public ResponseEntity<User> getData(){
     return new ResponseEntity<>(serviceLayer.consumeAPI(), HttpStatus.OK);}

//    @GetMapping(value = "api/v1/exchange/currencies")
//    public ResponseDTO<?> getCurrencies(@RequestHeader(name = "Accept-Language", required = false) Locale locale) throws JsonProcessingException {
//        return responseUtil.ok(exchangeCoinService.getCurrencies(),"success.text");
//    }

    @GetMapping("/group")
    public String group()
    {
        return "Group";
    }
//    ResponseUtil
}
