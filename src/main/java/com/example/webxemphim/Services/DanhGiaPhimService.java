package com.example.webxemphim.Services;

import com.example.webxemphim.Repositories.DanhGiaPhimRepository;
import com.example.webxemphim.Repositories.DaoDienRepository;
import com.example.webxemphim.models.DanhGiaPhim;
import com.example.webxemphim.models.DaoDien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DanhGiaPhimService {
    @Autowired
    DanhGiaPhimRepository danhGiaPhimRepository;
    public List<DanhGiaPhim> listAll(){return  danhGiaPhimRepository.findAll();}


    public void save(DanhGiaPhim danhGiaPhim){danhGiaPhimRepository.save(danhGiaPhim);}
    public DanhGiaPhim get(Long id)
    {
        return danhGiaPhimRepository.findById(id).get();
    }

    public void delete(Long id)
    {
        danhGiaPhimRepository.deleteById(id);
    }
}
