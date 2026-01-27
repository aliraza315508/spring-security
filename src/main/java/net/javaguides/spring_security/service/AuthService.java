package net.javaguides.spring_security.service;

import net.javaguides.spring_security.dto.LoginDto;
import net.javaguides.spring_security.dto.RegisterDto;

public interface AuthService {

    String register(RegisterDto registerDto) ;

    String login(LoginDto loginDto) ;
}
