package com.example.webxemphim.Controllers;


import com.example.webxemphim.Services.DanhGiaPhimService;
import com.example.webxemphim.models.DanhGiaPhim;
import com.example.webxemphim.models.NguoiDung;
import com.example.webxemphim.models.TheLoai;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/API/danhgiaphims")
public class DanhGiaPhimController {
    @Autowired
    private DanhGiaPhimService danhGiaPhimService;


    @GetMapping()
    public ResponseEntity<List<DanhGiaPhim>> listAll()
    {
        List<DanhGiaPhim> danhGiaPhims= danhGiaPhimService.listAll();
        return  new ResponseEntity<>(danhGiaPhims, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<DanhGiaPhim>get(@PathVariable(name = "id")Long id)
    {
        System.out.println("get1");
        try
        {
            DanhGiaPhim danhGiaPhim =  danhGiaPhimService.get(id);
            if (danhGiaPhim == null)
            {
                return new ResponseEntity<DanhGiaPhim>(danhGiaPhim, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<DanhGiaPhim>(danhGiaPhim, HttpStatus.OK);
        }
        catch (NoSuchElementException e)
        {
            return new ResponseEntity<DanhGiaPhim>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/add")
    public void add(@RequestBody DanhGiaPhim danhGiaPhim)
    {

        System.out.println("add");
        danhGiaPhimService.save(danhGiaPhim);
    }



}
