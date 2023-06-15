package com.example.webxemphim.Services;

import com.example.webxemphim.Repositories.TheLoaiRepository;
import com.example.webxemphim.models.TheLoai;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TheLoaiService {
    @Autowired
    private TheLoaiRepository theLoaiRepository;

    public List<TheLoai> listAll() {return theLoaiRepository.findAll();}


    public void save(TheLoai theloai){theLoaiRepository.save(theloai);}

    public TheLoai get(Long id){return  theLoaiRepository.findById(id).get();}


    public void delete(Long id){theLoaiRepository.deleteById(id);}

}
