package com.example.webxemphim.Controllers;


import com.example.webxemphim.Services.DienVienService;
import com.example.webxemphim.models.DienVien;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

           String imageName = saveImage(file);
           DienVien dienVien = new DienVien();
           dienVien.setTendienvien(name);
           dienVien.setHinhanhdienvien(imageName);
           dienVienService.save(dienVien);
    }



    private String saveImage(MultipartFile imageFile) throws IOException {
        // Tạo tên duy nhất cho tệp hình ảnh
        String imageName = UUID.randomUUID().toString() + "." + imageFile.getOriginalFilename().split("\\.")[1];

        // Lưu trữ tệp hình ảnh
        String imagePath = "path/to/image/directory/" + imageName;
        File file = new File(imagePath);
        FileUtils.writeByteArrayToFile(file, imageFile.getBytes());

        return imageName;
    }







}
