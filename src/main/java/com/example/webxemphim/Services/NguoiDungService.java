package com.example.webxemphim.Services;

import com.example.webxemphim.Repositories.NguoiDungRepository;
import com.example.webxemphim.models.NguoiDung;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NguoiDungService {
    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    public List<NguoiDung> listAll() {return nguoiDungRepository.findAll();}


    public NguoiDung save(NguoiDung nguoiDung){nguoiDungRepository.save(nguoiDung);
        return nguoiDung;
    }

    public NguoiDung get(Long id){return  nguoiDungRepository.findById(id).get();}


    public void delete(Long id){nguoiDungRepository.deleteById(id);}
}
