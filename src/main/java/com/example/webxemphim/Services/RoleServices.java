package com.example.webxemphim.Services;


import com.example.webxemphim.Repositories.RoleRepository;
import com.example.webxemphim.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServices {
    @Autowired
    private RoleRepository roleRepository;
    public List<Role> listAll(){
        return roleRepository.findAll();
    }
    public void save(Role role) {
        roleRepository.save(role);
    }
    public Role get(long id) {
        return roleRepository.findById(id).get();
    }
    public void delete(long id) {
        roleRepository.deleteById(id);
    }

}
