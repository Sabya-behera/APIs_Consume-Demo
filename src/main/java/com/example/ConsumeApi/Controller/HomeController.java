package com.example.ConsumeApi.Controller;

import com.example.ConsumeApi.Model.User;
import com.example.ConsumeApi.Service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URISyntaxException;
import java.util.List;

@RestController
public class HomeController {

    private final ServiceLayer serviceLayer;
    @Autowired
    public HomeController(ServiceLayer serviceLayer) {
        this.serviceLayer = serviceLayer;
    }

    @GetMapping("/user")
    public ResponseEntity<Object> getData(){
       // System.out.println("THE VALUES "+serviceLayer.consumeAPI().toString());
     return new ResponseEntity<>(serviceLayer.consumeAPI(), HttpStatus.OK);
    }

//    @GetMapping(value = "api/v1/exchange/currencies")
//    public ResponseDTO<?> getCurrencies(@RequestHeader(name = "Accept-Language", required = false) Locale locale) throws JsonProcessingException {
//        return responseUtil.ok(exchangeCoinService.getCurrencies(),"success.text");
//    }
    @PostMapping(value = "/users/{id}")
    public ResponseEntity<User> getUser(/*@ModelAttribute("user")*/ @RequestBody User user , UriComponentsBuilder builder) throws URISyntaxException {
         serviceLayer.getUser(user);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<User>(user, headers, HttpStatus.CREATED);
    }

    @GetMapping("/group")
    public String group()
    {
        return "Group";
    }


    @GetMapping("user/{id}")
    public User user(@PathVariable("id") String id){
       // logger.info("calling userList() to get list of users");
        return serviceLayer.getUser(id);
    }

    @GetMapping("userList")
    public List<User> userList(){
       // logger.info("calling userList() to get list of users");
        return serviceLayer.getUserList();
    }

    @PostMapping("user")
    public String addUser(@RequestBody User user){
        //logger.info("adding user");
        return serviceLayer.addUser(user);
    }
    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable String id){
       // logger.info("deleting user - {}", id);
        return serviceLayer.deleteUser(id);
    }

    @PutMapping("/updateAddress/{id}/{newAddress}")
    String updateAddress(@PathVariable String id, @PathVariable String newAddress){
       // logger.info("updating user - {}", id);
        return serviceLayer.updateAddress(id, newAddress);
    }
}
