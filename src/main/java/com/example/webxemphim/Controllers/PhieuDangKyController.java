package com.example.webxemphim.Controllers;

import com.example.webxemphim.Services.PhieuDangKyService;
import com.example.webxemphim.models.PhieuDangKy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/API/phieudangkys")
public class PhieuDangKyController {
    @Autowired
    private PhieuDangKyService phieuDangKyService;


    @GetMapping()
    public ResponseEntity<List<PhieuDangKy>> listAll()
    {
        List<PhieuDangKy> phieuDangkis = phieuDangKyService.listAll();
        return  new ResponseEntity<>(phieuDangkis, HttpStatus.OK);
    }
}
