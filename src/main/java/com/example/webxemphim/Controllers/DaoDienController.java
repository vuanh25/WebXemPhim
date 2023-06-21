package com.example.webxemphim.Controllers;


import com.example.webxemphim.Services.DaoDienService;
import com.example.webxemphim.Util.FileUploadUtil;
import com.example.webxemphim.models.DaoDien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin(origins = {"http://127.0.0.1:5500"})
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
            String uploadDir = "photos/daodiens/" + daodien.getIddaodien();
            FileUploadUtil.saveFile(uploadDir,fileName,file);
        }
        daoDienService.save(daodien);
    }

    @GetMapping("/chi-tiet/{id}")
    public ResponseEntity<DaoDien> get(@PathVariable(name = "id") Long id) {
        System.out.println("get1");
        try {
            DaoDien daoDien = daoDienService.get(id);
            if (daoDien == null) {
                return new ResponseEntity<DaoDien>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<DaoDien>(daoDien, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<DaoDien>(HttpStatus.NOT_FOUND);
        }
    }




    @PutMapping("/edit/{id}")
    public ResponseEntity<DaoDien> update(
            @RequestParam("iddaodien") Long id,
            @RequestBody DaoDien daoDien,
            @RequestParam("hinhanhdaodien") MultipartFile file) {
        System.out.println("edit");
        try {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            DaoDien daoDien1 = daoDienService.get(id);
            if (daoDien1 == null) {
                return new ResponseEntity<DaoDien>(HttpStatus.NOT_FOUND);
            }
            daoDien.setHinhanhdaodien(fileName);
            DaoDien savedaodien = daoDienService.save(daoDien);
            return ResponseEntity.ok(savedaodien);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        System.out.println("Delete");
        daoDienService.delete(id);
    }





}
