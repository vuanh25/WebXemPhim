package com.example.webxemphim.Services;

import com.example.webxemphim.Repositories.DaoDienRepository;
import com.example.webxemphim.Repositories.PhimRepository;
import com.example.webxemphim.models.DaoDien;
import com.example.webxemphim.models.Phim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhimService {
    @Autowired
    PhimRepository phimRepository;


    public List<Phim> listAll(){return  phimRepository.findAll();}

    public List<Phim> listQuantity(int quantity){
        List<Phim> allPhims = phimRepository.findAll();
       if(quantity >= allPhims.size())
       {
           return allPhims;
       }
       else
       {
           return allPhims.subList(0,quantity);
       }
    }

    public List<Phim> searchMovies(String keyword) {
        return phimRepository.findByTenphimContainingIgnoreCase(keyword);
    }

    public List<Phim> searchMoviesByTheLoai(String tenTheLoai) {
        return phimRepository.searchMoviesByTheLoai(tenTheLoai);
    }
    public void save(Phim phim){phimRepository.save(phim);}
    public Phim get(Long id)
    {
        return phimRepository.findById(id).get();
    }

    public void delete(Long id)
    {
        phimRepository.deleteById(id);
    }
}
