package com.example.webxemphim.Controllers;


import com.example.webxemphim.Services.NguoiDungService;
import com.example.webxemphim.models.NguoiDung;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("API/nguoidungs")
public class NguoiDungController {
    @Autowired
    private NguoiDungService nguoiDungService;

    @GetMapping()
    public ResponseEntity<List<NguoiDung>> listAll()
    {
        List<NguoiDung> nguoiDungs = nguoiDungService.listAll();
        return  new ResponseEntity<>(nguoiDungs, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<NguoiDung>get(@PathVariable(name = "id")Long id)
    {
        System.out.println("get1");
        try
        {
            NguoiDung nguoiDung =  nguoiDungService.get(id);
            if (nguoiDung == null)
            {
                return new ResponseEntity<NguoiDung>(nguoiDung, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<NguoiDung>(nguoiDung, HttpStatus.OK);
        }
        catch (NoSuchElementException e)
        {
            return new ResponseEntity<NguoiDung>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/add")
    public void add(@RequestBody NguoiDung nguoiDung)
    {
        System.out.println("add");
        nguoiDungService.save(nguoiDung);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<NguoiDung> update(
            @RequestParam("idnguoidung") Long id,
            @RequestBody NguoiDung nguoiDung,
            @RequestParam("hinhanhnguoidung") MultipartFile file) {
        System.out.println("edit");
        try {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            NguoiDung nguoiDung1 = nguoiDungService.get(id);
            if (nguoiDung1 == null) {
                return new ResponseEntity<NguoiDung>(HttpStatus.NOT_FOUND);
            }
            nguoiDung.setHinhanhnguoidung(fileName);
            NguoiDung savenguoidung = nguoiDungService.save(nguoiDung);
            return ResponseEntity.ok(savenguoidung);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        System.out.println("Delete");
        nguoiDungService.delete(id);
    }


}
