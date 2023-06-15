package com.example.webxemphim.models;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "PhieuDangKy")
public class PhieuDangKy {
    @Id
    @Column(name = "idphieudangky")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idphieudangky;

    @ManyToOne
    @JoinColumn(name = "idgoidichvu",nullable = false)
    private GoiDichVu GoiDichVu;

    @ManyToOne
    @JoinColumn(name = "idnguoidung",nullable = false)
    private NguoiDung nguoidung;


    @Column(name = "ngaythanhtoan")
    private Date ngaythanhtoan;
    @Column(name = "ngayhethang")
    private Date ngayhethang;

    @Column(name = "thanhtien")
    private Double thanhtien;

    public PhieuDangKy() {
    }

    public PhieuDangKy(Long idphieudangky, com.example.webxemphim.models.GoiDichVu goiDichVu, NguoiDung nguoidung, Date ngaythanhtoan, Date ngayhethang, Double thanhtien) {
        this.idphieudangky = idphieudangky;
        GoiDichVu = goiDichVu;
        this.nguoidung = nguoidung;
        this.ngaythanhtoan = ngaythanhtoan;
        this.ngayhethang = ngayhethang;
        this.thanhtien = thanhtien;
    }

    public Long getIdphieudangky() {
        return idphieudangky;
    }

    public void setIdphieudangky(Long idphieudangky) {
        this.idphieudangky = idphieudangky;
    }

    public com.example.webxemphim.models.GoiDichVu getGoiDichVu() {
        return GoiDichVu;
    }

    public void setGoiDichVu(com.example.webxemphim.models.GoiDichVu goiDichVu) {
        GoiDichVu = goiDichVu;
    }

    public NguoiDung getNguoidung() {
        return nguoidung;
    }

    public void setNguoidung(NguoiDung nguoidung) {
        this.nguoidung = nguoidung;
    }

    public Date getNgaythanhtoan() {
        return ngaythanhtoan;
    }

    public void setNgaythanhtoan(Date ngaythanhtoan) {
        this.ngaythanhtoan = ngaythanhtoan;
    }

    public Date getNgayhethang() {
        return ngayhethang;
    }

    public void setNgayhethang(Date ngayhethang) {
        this.ngayhethang = ngayhethang;
    }

    public Double getThanhtien() {
        return thanhtien;
    }

    public void setThanhtien(Double thanhtien) {
        this.thanhtien = thanhtien;
    }
}
