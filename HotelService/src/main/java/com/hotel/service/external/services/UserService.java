package com.hotel.service.external.services;

import com.hotel.service.payload.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USERSERVICE")
public interface UserService {

    @GetMapping("/users/{userId}")
    User getUser(@PathVariable("userId") String userId);

}
