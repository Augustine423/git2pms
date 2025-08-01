package org.mdt.crewtaskmanagement.controller;

import lombok.RequiredArgsConstructor;
import org.mdt.crewtaskmanagement.dto.crew.AdminDto;
import org.mdt.crewtaskmanagement.dto.crew.CrewDto;
import org.mdt.crewtaskmanagement.exception.NotImplementedException;
import org.mdt.crewtaskmanagement.service.impl.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    record LoginUserDto(String username,String password){}

    @PostMapping("/login")
    public ResponseEntity<AuthService.LoginResponseWithOneRole> login(@RequestBody LoginUserDto loginUserDto) throws NotImplementedException {
        return ResponseEntity.ok(authService.login(loginUserDto.username,loginUserDto.password));
    }

    @PostMapping("/register/crew")
    public ResponseEntity<String> register(@RequestBody CrewDto registerUserDto) {
        authService.registerCrewByAdmin(registerUserDto);
        return ResponseEntity.ok("register success");
    }
















    @PostMapping("/register/admin")
    public ResponseEntity<String> register(@RequestBody AdminDto registerUserDto) {
        System.out.println("in register method");
        System.out.println("Password" + registerUserDto.getPassword());
        System.out.println("mehthod registering in controller");
        authService.registerAdmin(registerUserDto);
        return ResponseEntity.ok("register success admin");
    }
}
