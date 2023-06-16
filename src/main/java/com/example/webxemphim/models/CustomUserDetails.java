package com.example.webxemphim.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public class CustomUserDetails implements UserDetails {
    private NguoiDung nguoiDung;

    public CustomUserDetails(NguoiDung nguoiDung) {
        this.nguoiDung = nguoiDung;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roles = nguoiDung.getRoles();
        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    public NguoiDung getNguoiDung() {
        return nguoiDung;
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return nguoiDung.getMatkhau();
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return nguoiDung.getHoten();
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return nguoiDung.isEnabled();
    }
}

