package com.example.webxemphim.Services;

import com.example.webxemphim.Repositories.GoiDichVuRepository;
import com.example.webxemphim.models.GoiDichVu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoiDichVuService {
    @Autowired
    GoiDichVuRepository goiDichVuRepository;

    public List<GoiDichVu> listAll(){return  goiDichVuRepository.findAll();}


    public void save(GoiDichVu goiDichVu){goiDichVuRepository.save(goiDichVu);}
    public GoiDichVu get(Long id)
    {
        return goiDichVuRepository.findById(id).get();
    }

    public void delete(Long id)
    {
        goiDichVuRepository.deleteById(id);
    }

}
