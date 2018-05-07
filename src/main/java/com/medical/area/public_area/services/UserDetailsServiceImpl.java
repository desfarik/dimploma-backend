package com.medical.area.public_area.services;

import com.medical.area.public_area.models.CustomUserDetails;
import com.medical.area.public_area.repositories.UserRepository;
import com.medical.area.public_area.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional

public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User applicationUserByName = userRepository.findFirstByUserName(username);
        User applicationUserByPhone = userRepository.findFirstByPhone(username);

        if (applicationUserByName == null && applicationUserByPhone == null) {
            throw new UsernameNotFoundException(username);
        }
        if(applicationUserByName != null) {
            return new CustomUserDetails(applicationUserByName.getUserName(), applicationUserByName.getPassword());
        }

        return new CustomUserDetails(applicationUserByPhone.getUserName(), applicationUserByPhone.getPassword());
    }
}