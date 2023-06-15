package com.example.webxemphim.models;


import org.springframework.data.relational.core.sql.In;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name ="DanhGiaPhim")
public class DanhGiaPhim {
    @Id
    @Column(name = "iddanhgiaphim")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iddanhgiaphim;

    @Column(name = "thoigianbinhluan")
    private Date thoigianbinhluan;

    @Column(name = "noidungbinhluan")
    private String noidungbinhluan;


    @Column(name ="sosao")
    private Integer sosao;


    @ManyToOne
    @JoinColumn(name = "idphim",nullable = false)
    private Phim DanhGiaPhim;


    @ManyToOne
    @JoinColumn(name = "idnguoidung",nullable = false)
    private NguoiDung nguoidung;


    public DanhGiaPhim() {
    }

    public DanhGiaPhim(Long iddanhgiaphim, Date thoigianbinhluan, String noidungbinhluan, Integer sosao, Phim danhGiaPhim, NguoiDung nguoidung) {
        this.iddanhgiaphim = iddanhgiaphim;
        this.thoigianbinhluan = thoigianbinhluan;
        this.noidungbinhluan = noidungbinhluan;
        this.sosao = sosao;
        DanhGiaPhim = danhGiaPhim;
        this.nguoidung = nguoidung;
    }

    public Long getIddanhgiaphim() {
        return iddanhgiaphim;
    }

    public void setIddanhgiaphim(Long iddanhgiaphim) {
        this.iddanhgiaphim = iddanhgiaphim;
    }

    public Date getThoigianbinhluan() {
        return thoigianbinhluan;
    }

    public void setThoigianbinhluan(Date thoigianbinhluan) {
        this.thoigianbinhluan = thoigianbinhluan;
    }

    public String getNoidungbinhluan() {
        return noidungbinhluan;
    }

    public void setNoidungbinhluan(String noidungbinhluan) {
        this.noidungbinhluan = noidungbinhluan;
    }

    public Integer getSosao() {
        return sosao;
    }

    public void setSosao(Integer sosao) {
        this.sosao = sosao;
    }


    public Phim getDanhGiaPhim() {
        return DanhGiaPhim;
    }

    public void setDanhGiaPhim(Phim danhGiaPhim) {
        DanhGiaPhim = danhGiaPhim;
    }

    public NguoiDung getNguoidung() {
        return nguoidung;
    }

    public void setNguoidung(NguoiDung nguoidung) {
        this.nguoidung = nguoidung;
    }
}
