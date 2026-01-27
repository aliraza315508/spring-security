package net.javaguides.spring_security.service.impl;

import net.javaguides.spring_security.dto.LoginDto;
import net.javaguides.spring_security.dto.RegisterDto;
import net.javaguides.spring_security.entity.Role;
import net.javaguides.spring_security.entity.User;
import net.javaguides.spring_security.exception.EmailAlreadyExistsException;
import net.javaguides.spring_security.repository.RoleRepository;
import net.javaguides.spring_security.repository.UserRepository;
import net.javaguides.spring_security.security.JwtTokenProvider;
import net.javaguides.spring_security.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository ;
    private RoleRepository roleRepository ;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager ;
    private JwtTokenProvider jwtTokenProvider ;


    public AuthServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder,
                           AuthenticationManager authenticationManager,
                           JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider ;
    }


    @Override
    public String register(RegisterDto registerDto) {

        if (userRepository.existsByUsername(registerDto.getUsername())) {
            throw new UsernameNotFoundException("Username already exists.");
        }

        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists.");
        }


        User user = new User() ;
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>() ;
        for(String role : registerDto.getRoles()){
            roles.add(roleRepository.findByName(role)) ;
        }

        user.setRoles(roles);

        userRepository.save(user) ;

        return "User Registered Successfully" ;
    }




    @Override
    public String login(LoginDto loginDto) {

    Authentication authentication = authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(
                loginDto.getUsername() ,
                loginDto.getPassword()
        )) ;

        SecurityContextHolder.getContext().
                setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return token ;
    }
}
