package challenge.to_do.perficient_back_api.controller;

import challenge.to_do.perficient_back_api.JWT.AuthResponse;
import challenge.to_do.perficient_back_api.JWT.LoginRequest;
import challenge.to_do.perficient_back_api.JWT.RegisterRequest;
import challenge.to_do.perficient_back_api.service.impl.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class UserController {

    @Autowired
    private AuthService authService;
    @PostMapping("/create")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authService.register(request));
    }


}
