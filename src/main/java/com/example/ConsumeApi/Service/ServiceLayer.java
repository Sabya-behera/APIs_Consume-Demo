package com.example.ConsumeApi.Service;

import com.example.ConsumeApi.Model.User;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ServiceLayer {
    private final RestTemplate restTemplate;

    @Autowired
    public ServiceLayer(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<User> consumeAPI() {
        return (List<User>) restTemplate.getForObject("https://jsonplaceholder.typicode.com/todos", Object.class);
    }

    @JsonAnyGetter
    public void getUser(User user) throws URISyntaxException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        URI uri = new URI("https://jsonplaceholder.typicode.com/todos");
        user.setUserId(11);
        user.setId(1);
        user.setTitle("delectus aut autem");
        HttpEntity<User> httpEntity = new HttpEntity<>(user, headers);
        RestTemplate restTemplate = new RestTemplate();
        User user2 = restTemplate.postForObject(uri, httpEntity, User.class);
        System.out.println("Id: " + user2.getId());

//        Map user= new HashMap();
//        user.put(user.get(userId),userId);
//        user.put(user.get(id),id);
//        user.put(user.get(title),title);
//        HttpEntity<Map<Object, List<Object>>> httpEntity = new HttpEntity<Map<Object,List<Object>>>(user, headers);
//        return (Map<String, Object>) restTemplate.postForObject("https://jsonplaceholder.typicode.com/todos",httpEntity,Object.class);

    }

    //    @JsonAnySetter
//    public void setUser(User u, User user) {
//        // IMPORTANT
//        // Here you have to create or find appropriate Resource in your code
//        // and add current task to it
//        System.out.println(u+" "+ user);
//    }
    @FeignClient(name = "${service.name}", url = "${service.base.url}")
    public interface UserService {

        @GetMapping("/user/{id}")
        User getUser(@PathVariable("id") String id)
        @GetMapping(value = "/users")
        List<User> getUserList()
        @PostMapping("/user")
        String addUser(User user)

        @DeleteMapping("/user/{id}")
        String deleteUser(@PathVariable("id") String id)

        @PutMapping("/updateAddress/{id}/{newAddress}")
        String updateAddress(@PathVariable("id") String id, @PathVariable("newAddress") String newAddress)

    }
}
