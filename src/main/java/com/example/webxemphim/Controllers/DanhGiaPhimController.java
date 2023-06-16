package com.example.webxemphim.Controllers;


import com.example.webxemphim.Services.DanhGiaPhimService;
import com.example.webxemphim.Services.PhimService;
import com.example.webxemphim.Services.UserServices;
import com.example.webxemphim.models.DanhGiaPhim;
import com.example.webxemphim.models.NguoiDung;
import com.example.webxemphim.models.Phim;
import com.example.webxemphim.models.TheLoai;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/API/danhgiaphims")
public class DanhGiaPhimController {
    @Autowired
    private DanhGiaPhimService danhGiaPhimService;
    @Autowired
    private UserServices userServices;
    @Autowired
    private PhimService phimService;


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
    public void add(@RequestBody Map<String, String> requestData)
    {
        DanhGiaPhim danhGiaPhim1 = new DanhGiaPhim();

        Long idPhim =  Long.parseLong(requestData.get("idphim"));
        Long idNguoiDung = Long.parseLong(requestData.get("idnguoidung"));
        String noidungbinhluan = requestData.get("noidungbinhluan");
        Integer sosao = Integer.parseInt(requestData.get("sosao"));
        Date thoigianbinhluan = Date.valueOf(requestData.get("thoigianbinhluan"));
        danhGiaPhim1.setNoidungbinhluan(noidungbinhluan);
        danhGiaPhim1.setSosao(sosao);
        danhGiaPhim1.setThoigianbinhluan(thoigianbinhluan);
        NguoiDung nguoiDung = userServices.get(idNguoiDung);
        Phim phim = phimService.get(idPhim);

        if (nguoiDung == null)
        {
            throw  new RuntimeException("Người dùng không tồn tại");
        }
        if (phim == null)
        {
            throw  new RuntimeException("Phim không tồn tại");
        }
        danhGiaPhim1.setNguoidung(nguoiDung);
        danhGiaPhim1.setDanhGiaPhim(phim);
        danhGiaPhimService.save(danhGiaPhim1);

    }



}
