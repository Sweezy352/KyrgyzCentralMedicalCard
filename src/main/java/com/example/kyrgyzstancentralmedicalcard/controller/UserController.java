package com.example.kyrgyzstancentralmedicalcard.controller;

import com.example.kyrgyzstancentralmedicalcard.dto.request.UserRequest;
import com.example.kyrgyzstancentralmedicalcard.dto.response.UserResponse;
import com.example.kyrgyzstancentralmedicalcard.mapper.UserMapper;
import com.example.kyrgyzstancentralmedicalcard.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/get-all-users")
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers().stream().map(userMapper::toResponse).toList());
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok(userMapper.toResponse(userService.getById(id)));
    }

    @GetMapping("/get-by-fio")
    public ResponseEntity<UserResponse> getByFIO(@RequestParam("fio") String fio){
        return ResponseEntity.ok(userMapper.toResponse(userService.getByFIO(fio)));
    }

    @GetMapping("/get-by-inn")
    public ResponseEntity<UserResponse> getByInn(@RequestParam("inn") String inn){
        return ResponseEntity.ok(userMapper.toResponse(userService.getByINN(inn)));
    }

    @PostMapping("/update")
    public ResponseEntity<UserResponse> update(@RequestBody UserRequest userRequest){
        return ResponseEntity.ok(userMapper.toResponse(userService.update(userMapper.toEntity(userRequest))));
    }
}
