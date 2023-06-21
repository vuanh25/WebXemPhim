package com.example.webxemphim.Services;

import com.example.webxemphim.Repositories.PhieuDangKyRepository;
import com.example.webxemphim.models.PhieuDangKy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhieuDangKyService {
    @Autowired
    private PhieuDangKyRepository phieuDangKyRepository;

    public List<PhieuDangKy> listAll(){return  phieuDangKyRepository.findAll();}
}
