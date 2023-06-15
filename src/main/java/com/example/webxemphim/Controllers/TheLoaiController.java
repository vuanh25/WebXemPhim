package com.example.webxemphim.Controllers;


import com.example.webxemphim.Repositories.TheLoaiRepository;
import com.example.webxemphim.Services.TheLoaiService;
import com.example.webxemphim.models.DaoDien;
import com.example.webxemphim.models.TheLoai;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("API/theloais")
public class TheLoaiController {
    @Autowired
    private TheLoaiService theLoaiService;

    @GetMapping()
    public ResponseEntity<List<TheLoai>> listAll()
    {
        List<TheLoai> theLoais = theLoaiService.listAll();
        return  new ResponseEntity<>(theLoais, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<TheLoai>get(@PathVariable(name = "id")Long id)
    {
        System.out.println("get1");
        try
        {
            TheLoai theLoai =  theLoaiService.get(id);
            if (theLoai == null)
            {
                return new ResponseEntity<TheLoai>(theLoai, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<TheLoai>(theLoai, HttpStatus.OK);
        }
        catch (NoSuchElementException e)
        {
            return new ResponseEntity<TheLoai>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/add")
    public void add(@RequestBody TheLoai theloai)
    {
        System.out.println("add");
        theLoaiService.save(theloai);
    }

    @PostMapping("/addAll")
    public void addAll(@RequestBody List<TheLoai > theloais)
    {
        for (TheLoai theloai : theloais){
            System.out.println("add");
            theLoaiService.save(theloai);
        }

    }
}
