package com.userdetails.service;

import com.userdetails.modal.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    RestTemplate restTemplate;

    public UserEntity getUserContact(int id, String username) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<UserEntity> entity = new HttpEntity<>(headers);
        ResponseEntity<List<UserEntity>> rateResponse =
                restTemplate.exchange("https://jsonplaceholder.typicode.com/users",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<UserEntity>>() {
                        });
        List<UserEntity> users = rateResponse.getBody();
        users = users.stream().filter(u -> u.getId() == id || (username != null && u.getUsername().equals(username))).map(u -> new UserEntity(u.getName(), u.getEmail(), u.getPhone())).collect(Collectors.toList());
        if (users.isEmpty()) {
            return new UserEntity(-1);
        }
        return users.get(0);
    }
}
