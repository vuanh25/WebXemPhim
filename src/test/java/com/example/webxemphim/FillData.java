package com.example.webxemphim;


import com.example.webxemphim.Repositories.*;
import com.example.webxemphim.models.NguoiDung;
import com.example.webxemphim.models.Phim;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class FillData {
    @Autowired
    private DanhGiaPhimRepository danhGiaPhimRepository;
    @Autowired
    private DaoDienRepository daoDienRepository;
    @Autowired
    private DienVienRepository dienVienRepository;
    @Autowired
    private DienVienDongPhimRepository dienVienDongPhimRepository;
    @Autowired
    private GoiDichVuRepository goiDichVuRepository;
    @Autowired
    private NguoiDungRepository nguoiDungRepository;
    @Autowired
    private PhieuDangKyRepository phieuDangKyRepository;
    @Autowired
    private PhimRepository phimRepository;
    @Autowired
    private TheLoaiRepository theLoaiRepository;

//    @Test
//    public  void testCreate()
//    {
//
//    }
}
