package com.example.webxemphim.Controllers;


import com.example.webxemphim.Services.DienVienService;
import com.example.webxemphim.Util.FileUploadUtil;
import com.example.webxemphim.models.DienVien;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("API/dienviens")
public class DienVienController {
    @Autowired
    private DienVienService dienVienService;



    @GetMapping()
    public ResponseEntity<List<DienVien>> listAll()
    {
        List<DienVien> dienViens = dienVienService.listAll();
        return  new ResponseEntity<>(dienViens, HttpStatus.OK);
    }

    @PostMapping("/add")
    public void addDienVien(@RequestParam("tendienvien") String name,@RequestParam("hinhanhdienvien") MultipartFile file) throws IOException
    {


            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
           DienVien dienVien = new DienVien();
           dienVien.setTendienvien(name);
           dienVien.setHinhanhdienvien(fileName);
           dienVienService.save(dienVien);
        if (!file.getOriginalFilename().isBlank())
        {
            String uploadDir = "photos/dienviens/" + dienVien.getIdDienVien();
            FileUploadUtil.saveFile(uploadDir,fileName,file);
        }
        dienVienService.save(dienVien);

    }










}
