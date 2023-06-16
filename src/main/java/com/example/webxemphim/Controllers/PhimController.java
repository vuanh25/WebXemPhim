package com.example.webxemphim.Controllers;


import com.example.webxemphim.Services.DaoDienService;
import com.example.webxemphim.Services.PhimService;
import com.example.webxemphim.Services.TheLoaiService;
import com.example.webxemphim.models.DaoDien;
import com.example.webxemphim.models.Phim;
import com.example.webxemphim.models.TheLoai;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("/API/phims")
public class PhimController {
    @Autowired
    private PhimService phimService;
    @Autowired
    private DaoDienService daoDienService;
    @Autowired
    private TheLoaiService theLoaiService;

    @GetMapping()
    public ResponseEntity<List<Phim>> listAll()
    {
        List<Phim> phims = phimService.listAll();
        return new ResponseEntity<>(phims, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Phim>> listAll(@RequestParam(defaultValue = "10") int quantity) {
        List<Phim> phims = phimService.listQuantity(quantity);
        return new ResponseEntity<>(phims, HttpStatus.OK);
    }

    @PostMapping("/add")
    public void addPhim(@RequestParam("tenphim") String tenphim,
                        @RequestParam("diemimdb") String diemimdb,
                        @RequestParam("linkphim") String linkphim,
                        @RequestParam("luotxem") Long luotxem,
                        @RequestParam("ngaysanxuat") Date ngaysanxuat,
                        @RequestParam("noidungphim") String noidungphim,
                        @RequestParam("soluotdanhgia") Integer soluotdanhgia,
                        @RequestParam("sosaotrungbinh") Double sosaotrungbinh,
                        @RequestParam("thoiluong") Long thoiluong,
                        @RequestParam("idtheloai") Long idtheloai,
                        @RequestParam("iddanhgia") Long iddanhgia,
                        @RequestParam("iddaodien") Long iddaodien,
                        @RequestParam("hinhanhphim") MultipartFile file) throws IOException
    {
        DaoDien daoDien = daoDienService.get(iddaodien);
        TheLoai theLoai = theLoaiService.get(idtheloai);
        if (daoDien == null)
        {
            throw  new RuntimeException("Đạo diễn không tồn tại");
        }
        if(theLoai == null)
        {
            throw  new RuntimeException("Thể loại không tồn tại");
        }
        String imageName = saveImage(file);
        Phim phim1 = new Phim();
        phim1.setTenphim(tenphim);
        phim1.setDiemIMDB(diemimdb);
        phim1.setLinkphim(linkphim);
        phim1.setLuotxem(luotxem);
        phim1.setNgaysanxuat(ngaysanxuat);
        phim1.setNoidungphim(noidungphim);
        phim1.setThoiluong(thoiluong);
        phim1.setSoluotdanhgia(soluotdanhgia);
        phim1.setSosaotrungbinh(sosaotrungbinh);
        phim1.setHinhanh(imageName);
        phim1.setDaodien(daoDien);
        phim1.setTheLoai(theLoai);
        phimService.save(phim1);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Phim> get(@PathVariable(name = "id") Long id) {
        System.out.println("get1");
        try {
            Phim phim = phimService.get(id);
            if (phim == null) {
                return new ResponseEntity<Phim>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<Phim>(phim, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Phim>(HttpStatus.NOT_FOUND);
        }
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
