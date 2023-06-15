package com.example.webxemphim.Controllers;


import com.example.webxemphim.Services.DaoDienService;
import com.example.webxemphim.Services.DienVienService;
import com.example.webxemphim.models.DaoDien;
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
@RequestMapping("/API/daodiens")
public class DaoDienController {
    @Autowired
    private DaoDienService daoDienService;



    @GetMapping()
    public ResponseEntity<List<DaoDien>> listAll()
    {
        List<DaoDien> daoDiens = daoDienService.listAll();
        return  new ResponseEntity<>(daoDiens, HttpStatus.OK);
    }

    @PostMapping("/add")
    public void addDaoDien(@RequestParam("tendaodien") String name, @RequestParam("hinhanhdaodien") MultipartFile file) throws IOException
    {

        String imageName = saveImage(file);
        DaoDien daodien = new DaoDien();
        daodien.setTendaodien(name);
        daodien.setHinhanhdaodien(imageName);
        daoDienService.save(daodien);
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
