package com.example.webxemphim.Controllers;


import com.example.webxemphim.Repositories.PhimRepository;
import com.example.webxemphim.Services.DaoDienService;
import com.example.webxemphim.Services.PhimService;
import com.example.webxemphim.Services.TheLoaiService;
import com.example.webxemphim.Util.FileUploadUtil;
import com.example.webxemphim.models.DaoDien;
import com.example.webxemphim.models.Phim;
import com.example.webxemphim.models.TheLoai;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/API/phims")
public class PhimController {
    @Autowired
    private PhimService phimService;
    @Autowired
    private DaoDienService daoDienService;
    @Autowired
    private TheLoaiService theLoaiService;

    @Autowired
    private PhimRepository phimRepository;


    @GetMapping()
    public List<Phim> listAll() {return phimRepository.findAll();}



    @PostMapping("/add")
    public void addPhim(@RequestParam("tenphim") String tenphim,
                        @RequestParam("diemimdb") String diemimdb,
                        @RequestParam("linkphim") MultipartFile filephim,
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
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String filePhim = StringUtils.cleanPath(filephim.getOriginalFilename());
        Phim phim = new Phim();
        phim.setTenphim(tenphim);
        phim.setDiemIMDB(diemimdb);
        phim.setLinkphim(filePhim);
        phim.setLuotxem(luotxem);
        phim.setNgaysanxuat(ngaysanxuat);
        phim.setNoidungphim(noidungphim);
        phim.setThoiluong(thoiluong);
        phim.setSoluotdanhgia(soluotdanhgia);
        phim.setSosaotrungbinh(sosaotrungbinh);
        phim.setHinhanh(fileName);
        phim.setDaodien(daoDien);
        phim.setTheLoai(theLoai);
        phimService.save(phim);
        if (!file.getOriginalFilename().isBlank()||!filephim.getOriginalFilename().isBlank())
        {
            String uploadDir = "photos/phims/" + phim.getIdphim();
            String uploadDir1 = "photos/videos/" + phim.getIdphim();
            FileUploadUtil.saveFile(uploadDir,fileName,file);
            FileUploadUtil.saveFile(uploadDir1,filePhim,filephim);
        }
        phimService.save(phim);

    }

    @GetMapping("/chi-tiet/{id}")
    public ResponseEntity<Phim> get(
            @PathVariable(name = "id") Long id
    ) {
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


    @GetMapping("/page/{pageNum}")
    public List<Phim> list(
            @PathVariable(name = "pageNum") int pageNum,
            @Param("theloai") String theloai,
            @Param("keyword") String keyword
    ) {
        Page<Phim> page = phimService.listAll(pageNum,theloai,keyword);
        return page.getContent();
    }



    @PutMapping("/edit/{id}")
    public ResponseEntity<?> update(
            @PathVariable(name = "id") Long id,
            @Param("tenphim") String tenphim,
            @Param("diemimdb") String diemimdb,
            @Param("linkphim") MultipartFile filephim,
            @Param("ngaysanxuat") Date ngaysanxuat,
            @Param("noidungphim") String noidungphim,
            @Param("thoiluong") Long thoiluong,
            @Param("idtheloai") Long idtheloai,
            @Param("iddaodien") Long iddaodien,
            @Param("hinhanhphim") MultipartFile file
           ) {
        System.out.println("edit");
        try {
            if (file != null && !file.isEmpty())
            {
                String filename = StringUtils.cleanPath(file.getOriginalFilename());
            }
            if (filephim != null && !filephim.isEmpty())
            {
                String fileName1 = StringUtils.cleanPath(filephim.getOriginalFilename());
            }
            Phim phim = phimService.get(id);
            if (phim == null) {
                return new ResponseEntity<DaoDien>(HttpStatus.NOT_FOUND);
            }
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
            Phim phim1 = new Phim();
            phim1.setIdphim(id);
            phim1.setTenphim(tenphim);
            phim1.setThoiluong(thoiluong);
            phim1.setDiemIMDB(diemimdb);
            phim1.setNgaysanxuat(ngaysanxuat);
            phim1.setNoidungphim(noidungphim);
            phim1.setDaodien(daoDien);
            phim1.setTheLoai(theLoai);
            phimService.save(phim1);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        System.out.println("Delete");
        phimService.delete(id);
    }



}
