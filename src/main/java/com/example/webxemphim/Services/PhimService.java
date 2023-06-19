package com.example.webxemphim.Services;

import com.example.webxemphim.Repositories.PhimRepository;
import com.example.webxemphim.models.Phim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PhimService {
    @Autowired
    PhimRepository phimRepository;

    public Page<Phim> listAll(int pageNum,String theloai,String keyword) {
        int pageSize = 10;

        Pageable pageable = PageRequest.of(
                pageNum - 1, pageSize);
        if (theloai != null)
        {
            return phimRepository.searchMoviesByTheLoai(theloai,pageable);
        }
        else if (keyword != null)
        {
            return phimRepository.findByTenphimContainingIgnoreCase(keyword,pageable);
        }
        return phimRepository.findAll(pageable);
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
