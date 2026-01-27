package net.javaguides.spring_security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//only for manual encryption

public class PasswordEncoder {
    static  void main(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("ramesh123"));
        System.out.println(passwordEncoder.encode("ali123"));
    }
}
