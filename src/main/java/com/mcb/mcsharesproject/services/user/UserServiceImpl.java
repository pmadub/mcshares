package com.mcb.mcsharesproject.services.user;

import com.mcb.mcsharesproject.entities.user.User;
import com.mcb.mcsharesproject.repositories.user.UserRepository;
import com.mcb.mcsharesproject.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if(user == null) {
            log.error(" user not found by username = {} ",username);
            throw new UsernameNotFoundException("user not found ");
        } else {
            log.info(" user found by username = {} ",username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ADMIN"));
        return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(),authorities);
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User persistedUser = userRepository.save(user);
        log.info(" User = {} has been saved with encrypt password = {} ", user.getName(), user.getPassword());
        return persistedUser;
    }

    @Override
    public User saveUser(UserVO userVO) {
        User user = new User();
        user.setUserName(userVO.getUsername());
        user.setPassword(passwordEncoder.encode(userVO.getPassword()));
        user.setName(userVO.getName());
        User persistedUser = userRepository.save(user);
        log.info(" User name = {} has been saved with encrpt password = {} ", user.getName(), user.getPassword());
        return persistedUser;
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUserName(username);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

}
