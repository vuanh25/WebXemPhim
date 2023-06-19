package com.example.webxemphim.Controllers;


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



//    @GetMapping()
//    public ResponseEntity<List<Phim>> listAll()
//    {
//        List<Phim> phims = phimService.listAll();
//        return new ResponseEntity<>(phims, HttpStatus.OK);
//    }



//    @GetMapping("{quantity}")
//    public ResponseEntity<List<Phim>> listAll(@RequestParam int quantity) {
//        List<Phim> phims = phimService.listQuantity(quantity);
//        return new ResponseEntity<>(phims, HttpStatus.OK);
//    }

//    @GetMapping("/search")
//    public List<Phim> searchMovies(@RequestParam(value = "keyword", required = false) String keyword,
//                                   @RequestParam(value = "theloai", required = false) String theloai) {
//        if (keyword != null) {
//            return phimService.searchMovies(keyword);
//        } else if (theloai != null) {
//            return phimService.searchMoviesByTheLoai(theloai);
//        } else {
//            return new ArrayList<>();
//        }
//    }

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


    @GetMapping("/page/{pageNum}")
    public List<Phim> list(
            @PathVariable(name = "pageNum") int pageNum,
            @Param("theloai") String theloai,
            @Param("keyword") String keyword
    ) {
        Page<Phim> page = phimService.listAll(pageNum,theloai,keyword);
        return page.getContent();
    }

}
