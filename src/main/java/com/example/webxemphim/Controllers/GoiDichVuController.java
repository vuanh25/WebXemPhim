package com.example.webxemphim.Controllers;

import com.example.webxemphim.Services.GoiDichVuService;
import com.example.webxemphim.models.GoiDichVu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("API/goidichvus")
public class GoiDichVuController {
    @Autowired
    private GoiDichVuService goiDichVuService;


    @GetMapping()
    public ResponseEntity<List<GoiDichVu>> listAll()
    {
        List<GoiDichVu> goiDichVus = goiDichVuService.listAll();
        return  new ResponseEntity<>(goiDichVus, HttpStatus.OK);
    }



}
