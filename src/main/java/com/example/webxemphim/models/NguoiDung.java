package com.example.webxemphim.models;

import javax.persistence.*;
import javax.print.attribute.standard.MediaSize;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "NguoiDung")
public class NguoiDung {
    @Id
    @Column(name = "idnguoidung")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idnguoidung;

    @Column(name = "hoten",nullable = false,length = 50)
    private String hoten;

    @Column(name ="gioitinh")
    private String gioitinh;


    @Column(name = "email",nullable = false,length = 100)
    private String email;

    @Column(name= "sdt",nullable = false,length = 50)
    private String sdt;

    @Column(name = "ngaysinh")
    private Date ngaysinh;

    @Column(name = "taikhoan",nullable = false,length = 50)
    private String taikhoan;

    @Column(name = "matkhau",nullable = false,length = 50)
    private String matkhau;

    @Column(name = "hinhanhnguoidung")
    private String hinhanhnguoidung;


    public NguoiDung() {
    }

    public NguoiDung(Long idnguoidung, String hoten, String gioitinh, String email, String sdt, Date ngaysinh, String taikhoan, String matkhau, String hinhanhnguoidung) {
        this.idnguoidung = idnguoidung;
        this.hoten = hoten;
        this.gioitinh = gioitinh;
        this.email = email;
        this.sdt = sdt;
        this.ngaysinh = ngaysinh;
        this.taikhoan = taikhoan;
        this.matkhau = matkhau;
        this.hinhanhnguoidung = hinhanhnguoidung;
    }

    public Long getIdnguoidung() {
        return idnguoidung;
    }

    public void setIdnguoidung(Long idnguoidung) {
        this.idnguoidung = idnguoidung;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public Date getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(Date ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getTaikhoan() {
        return taikhoan;
    }

    public void setTaikhoan(String taikhoan) {
        this.taikhoan = taikhoan;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getHinhanhnguoidung() {
        return hinhanhnguoidung;
    }

    public void setHinhanhnguoidung(String hinhanhnguoidung) {
        this.hinhanhnguoidung = hinhanhnguoidung;
    }
}
