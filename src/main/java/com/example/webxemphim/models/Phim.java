package com.example.webxemphim.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name ="Phim")
public class Phim {
    @Id
    @Column(name = "idphim")
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long idphim;
    @Column(name = "tenphim", nullable = false, length = 255)
    private String tenphim;
    @Column(name ="ngaysanxuat")
    private Date ngaysanxuat;
    @Column(name = "noidungphim")
    private String noidungphim;
    @Column(name= "diemIMDB")
    private String diemIMDB;
    @Column(name= "luotxem")
    private Long luotxem;
    @Column(name= "thoiluong")
    private Long thoiluong;
    @Column(name = "hinhanh")
    private String hinhanh;
    @Column(name = "linkphim")
    private String linkphim;

    @ManyToOne
    @JoinColumn(name = "iddaodien",nullable = false)
    private DaoDien daodien;

    @ManyToOne
    @JoinColumn(name = "idtheloai",nullable = false)
    private TheLoai TheLoai;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "phim")
    @JsonIgnore
    private List<DienVienDongPhim> dienviendongphims;



    @OneToMany(mappedBy = "DanhGiaPhim")
    @JsonIgnore
    private List<DanhGiaPhim> danhgiaphims;

    @Column(name = "sosaotrungbinh")
    private Double sosaotrungbinh;

    @Column(name = "soluotdanhgia")
    private Integer soluotdanhgia;

    public Phim() {
    }




    public Long getIdphim() {
        return idphim;
    }

    public void setIdphim(Long idphim) {
        this.idphim = idphim;
    }

    public String getTenphim() {
        return tenphim;
    }

    public void setTenphim(String tenphim) {
        this.tenphim = tenphim;
    }

    public Date getNgaysanxuat() {
        return ngaysanxuat;
    }

    public void setNgaysanxuat(Date ngaysanxuat) {
        this.ngaysanxuat = ngaysanxuat;
    }

    public String getNoidungphim() {
        return noidungphim;
    }

    public void setNoidungphim(String noidungphim) {
        this.noidungphim = noidungphim;
    }

    public List<DienVienDongPhim> getDienviendongphims() {
        return dienviendongphims;
    }

    public void setDienviendongphims(List<DienVienDongPhim> dienviendongphims) {
        this.dienviendongphims = dienviendongphims;
    }

    public Phim(Long idphim, String tenphim, Date ngaysanxuat, String noidungphim, String diemIMDB, Long luotxem, Long thoiluong, String hinhanh, String linkphim, DaoDien daodien, com.example.webxemphim.models.TheLoai theLoai, List<DienVienDongPhim> dienviendongphims, List<DanhGiaPhim> danhgiaphims, Double sosaotrungbinh, Integer soluotdanhgia) {
        this.idphim = idphim;
        this.tenphim = tenphim;
        this.ngaysanxuat = ngaysanxuat;
        this.noidungphim = noidungphim;
        this.diemIMDB = diemIMDB;
        this.luotxem = luotxem;
        this.thoiluong = thoiluong;
        this.hinhanh = hinhanh;
        this.linkphim = linkphim;
        this.daodien = daodien;
        TheLoai = theLoai;
        this.dienviendongphims = dienviendongphims;
        this.danhgiaphims = danhgiaphims;
        this.sosaotrungbinh = sosaotrungbinh;
        this.soluotdanhgia = soluotdanhgia;
    }

    public String getDiemIMDB() {
        return diemIMDB;
    }

    public void setDiemIMDB(String diemIMDB) {
        this.diemIMDB = diemIMDB;
    }

    public Long getLuotxem() {
        return luotxem;
    }

    public void setLuotxem(Long luotxem) {
        this.luotxem = luotxem;
    }

    public Long getThoiluong() {
        return thoiluong;
    }

    public void setThoiluong(Long thoiluong) {
        this.thoiluong = thoiluong;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getLinkphim() {
        return linkphim;
    }

    public void setLinkphim(String linkphim) {
        this.linkphim = linkphim;
    }

    public DaoDien getDaodien() {
        return daodien;
    }

    public void setDaodien(DaoDien daodien) {
        this.daodien = daodien;
    }

    public com.example.webxemphim.models.TheLoai getTheLoai() {
        return TheLoai;
    }

    public void setTheLoai(com.example.webxemphim.models.TheLoai theLoai) {
        TheLoai = theLoai;
    }

    public List<DanhGiaPhim> getDanhgiaphims() {
        return danhgiaphims;
    }

    public void setDanhgiaphims(List<DanhGiaPhim> danhgiaphims) {
        this.danhgiaphims = danhgiaphims;
    }


    public Phim(Long idphim, String tenphim, Date ngaysanxuat, String noidungphim, String diemIMDB, Long luotxem, Long thoiluong, String hinhanh, String linkphim, DaoDien daodien, com.example.webxemphim.models.TheLoai theLoai, List<DanhGiaPhim> danhgiaphims, Double sosaotrungbinh, Integer soluotdanhgia) {
        this.idphim = idphim;
        this.tenphim = tenphim;
        this.ngaysanxuat = ngaysanxuat;
        this.noidungphim = noidungphim;
        this.diemIMDB = diemIMDB;
        this.luotxem = luotxem;
        this.thoiluong = thoiluong;
        this.hinhanh = hinhanh;
        this.linkphim = linkphim;
        this.daodien = daodien;
        TheLoai = theLoai;
        this.danhgiaphims = danhgiaphims;
        this.sosaotrungbinh = sosaotrungbinh;
        this.soluotdanhgia = soluotdanhgia;
    }

    public Double getSosaotrungbinh() {
        if (danhgiaphims.isEmpty())
            return 0.0;
        else
            return (double) Math.round(danhgiaphims.stream().mapToInt(DanhGiaPhim::getSosao).average().orElse(0));
    }

    public void setSosaotrungbinh(Double sosaotrungbinh) {
        this.sosaotrungbinh = sosaotrungbinh;
    }

    public Integer getSoluotdanhgia() {
        return danhgiaphims.size();
    }

    public void setSoluotdanhgia(Integer soluotdanhgia) {
        this.soluotdanhgia = soluotdanhgia;
    }



    @Transient
    public String getPhotosImagePath() {
        if (hinhanh == null || idphim == null)
            return null;

        return "/photos/phims/" + idphim + "/" + hinhanh;
    }

    @Transient
    public String getVideosImagePath() {
        if (linkphim == null || idphim == null)
            return null;

        return "/photos/videos/" + idphim + "/" + linkphim;
    }


}
