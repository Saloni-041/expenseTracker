package com.example.expenseTrackerApi.security;

import com.example.expenseTrackerApi.entity.User;
import com.example.expenseTrackerApi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User existingUser = userRepository.findByEmail(email).
                orElseThrow(() -> new UsernameNotFoundException("User not found with email id" + email));

        List<GrantedAuthority> authorityList = existingUser.getRoles().stream().
//      The map method is used to transform each element of the stream. In this case, each role (a string) is transformed into a SimpleGrantedAuthority object.
//      role -> new SimpleGrantedAuthority(role) is a lambda expression. It takes each role (string) from the stream and returns a new SimpleGrantedAuthority object constructed with that role.
                map(roles -> new SimpleGrantedAuthority(roles.getRoleName())).collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(existingUser.getEmail(), existingUser.getPassword(), authorityList);
//      or import org.springframework.security.core.userdetails.User;
//      String email = existingUser.getEmail();
//      String password = existingUser.getPassword();
//      List<GrantedAuthority> authorities = new ArrayList<>();
//      return new User(email, password, authorities);
    }
}
