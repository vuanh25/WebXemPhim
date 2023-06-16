package com.example.webxemphim.Services;

import com.example.webxemphim.Repositories.NguoiDungRepository;
import com.example.webxemphim.models.CustomUserDetails;
import com.example.webxemphim.models.NguoiDung;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;

@Controller
public class UserDetailsServicelmpl implements UserDetailsService {
    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        NguoiDung nguoiDung = nguoiDungRepository.getUserByUsername(username);
        if (nguoiDung == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new CustomUserDetails(nguoiDung);
    }
}
