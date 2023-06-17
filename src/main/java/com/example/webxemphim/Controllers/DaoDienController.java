package com.example.webxemphim.Controllers;


import com.example.webxemphim.Services.DaoDienService;
import com.example.webxemphim.Services.DienVienService;
import com.example.webxemphim.Util.FileUploadUtil;
import com.example.webxemphim.models.DaoDien;
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

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        DaoDien daodien = new DaoDien();
        daodien.setTendaodien(name);
        daodien.setHinhanhdaodien(fileName);
        daoDienService.save(daodien);
        if (!file.getOriginalFilename().isBlank())
        {
            String uploadDir = "photos/đaoiens/" + daodien.getIddaodien();
            FileUploadUtil.saveFile(uploadDir,fileName,file);
        }
        daoDienService.save(daodien);
    }






}
