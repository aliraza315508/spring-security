package net.javaguides.spring_security.controller;


import net.javaguides.spring_security.dto.JwtAuthResponseDto;
import net.javaguides.spring_security.dto.LoginDto;
import net.javaguides.spring_security.dto.RegisterDto;
import net.javaguides.spring_security.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private  AuthService authService ;

    public AuthController(AuthService authService)
    {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register
            (@RequestBody RegisterDto registerDto){
        String message = authService.register(registerDto) ;
        return ResponseEntity.ok(message) ;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponseDto> login
            (@RequestBody LoginDto loginDto){
        String token = authService.login(loginDto) ;
        JwtAuthResponseDto dto = new JwtAuthResponseDto(token);
        return ResponseEntity.ok(dto);

    }
}
