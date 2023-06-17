package com.example.webxemphim.models;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "DienVien")
public class DienVien {
    @Id
    @Column(name = "iddienvien")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDienVien;

    @Column(name = "tendienvien",nullable = false,length = 255)
    private String tendienvien;


    @Column(name = "hinhanhdienvien",nullable = false)
    private String hinhanhdienvien;

    @OneToMany(mappedBy = "dienvien",cascade = CascadeType.ALL)
    private List<DienVienDongPhim> dienviendongphims;

    public DienVien(Long idDienVien, String tendienvien, String hinhanhdienvien, List<DienVienDongPhim> dienviendongphims) {
        this.idDienVien = idDienVien;
        this.tendienvien = tendienvien;
        this.hinhanhdienvien = hinhanhdienvien;
        this.dienviendongphims = dienviendongphims;
    }


    public List<DienVienDongPhim> getDienviendongphims() {
        return dienviendongphims;
    }

    public void setDienviendongphims(List<DienVienDongPhim> dienviendongphims) {
        this.dienviendongphims = dienviendongphims;
    }

    public Long getIdDienVien() {
        return idDienVien;
    }

    public void setIdDienVien(Long idDienVien) {
        this.idDienVien = idDienVien;
    }

    public String getTendienvien() {
        return tendienvien;
    }

    public void setTendienvien(String tendienvien) {
        this.tendienvien = tendienvien;
    }



    public DienVien() {

    }


    public String getHinhanhdienvien() {
        return hinhanhdienvien;
    }

    public void setHinhanhdienvien(String hinhanhdienvien) {
        this.hinhanhdienvien = hinhanhdienvien;
    }

    public DienVien(Long idDienVien, String tendienvien, String hinhanhdienvien) {
        this.idDienVien = idDienVien;
        this.tendienvien = tendienvien;
        this.hinhanhdienvien = hinhanhdienvien;
    }


    @Transient
    public String getPhotosImagePath() {
        if (hinhanhdienvien == null || idDienVien == null)
            return null;

        return "/photos/dienviens/" + idDienVien + "/" + hinhanhdienvien;
    }
}
