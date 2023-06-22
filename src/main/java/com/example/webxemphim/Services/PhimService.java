package com.example.webxemphim.Services;

import com.example.webxemphim.Repositories.PhimRepository;
import com.example.webxemphim.models.Phim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhimService {
    @Autowired
    PhimRepository phimRepository;

    public List<Phim> listAllNoPage()
    {
        return  phimRepository.findAll();
    }

    public List<Phim> listAllNopage(String theloai,String keyword) {
        if (theloai != null)
        {
            return phimRepository.searchMoviesByTheLoaiNoPage(theloai);
        }
        else if (keyword != null)
        {
            return phimRepository.searchPhimByTenphim(keyword);
        }
        return phimRepository.findAll();
    }

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

    public int getTotalPages( String theloai, String keyword) {
        int pageSize = 10;
        Pageable pageable = PageRequest.of(0, pageSize );
        if (theloai != null) {
            Page<Phim> page = phimRepository.searchMoviesByTheLoai(theloai, pageable);
            return page.getTotalPages();
        } else if (keyword != null) {
            Page<Phim> page = phimRepository.findByTenphimContainingIgnoreCase(keyword, pageable);
            return page.getTotalPages();
        }
        Page<Phim> page = phimRepository.findAll(pageable);
        return page.getTotalPages();
    }





    public Phim save(Phim phim){phimRepository.save(phim);
        return phim;
    }
    public Phim get(Long id)
    {
        return phimRepository.findById(id).get();
    }

    public void delete(Long id)
    {
        phimRepository.deleteById(id);
    }
}
