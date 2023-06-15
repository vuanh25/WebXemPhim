package com.example.webxemphim.Services;

import com.example.webxemphim.Repositories.DaoDienRepository;
import com.example.webxemphim.Repositories.DienVienRepository;
import com.example.webxemphim.models.DaoDien;
import com.example.webxemphim.models.DienVien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DaoDienService {
    @Autowired
    DaoDienRepository daoDienRepository;


    public List<DaoDien> listAll(){return  daoDienRepository.findAll();}


    public void save(DaoDien daoDien){daoDienRepository.save(daoDien);}
    public DaoDien get(Long id)
    {
        return daoDienRepository.findById(id).get();
    }

    public void delete(Long id)
    {
        daoDienRepository.deleteById(id);
    }
}
