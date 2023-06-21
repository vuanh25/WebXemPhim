package com.example.webxemphim.Services;

import com.example.webxemphim.Repositories.DienVienRepository;
import com.example.webxemphim.models.DienVien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class DienVienService {
    @Autowired
    DienVienRepository dienVienRepository;


    public List<DienVien> listAll(){return  dienVienRepository.findAll();}


    public DienVien save(DienVien dienVien){dienVienRepository.save(dienVien);
        return dienVien;
    }
    public DienVien get(Long id)
    {
        return dienVienRepository.findById(id).get();
    }

    public void delete(Long id)
    {
        dienVienRepository.deleteById(id);
    }

}
