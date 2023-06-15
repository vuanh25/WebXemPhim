package com.example.webxemphim.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "DienVienDongPhim")
public class DienVienDongPhim {

    @EmbeddedId
    private DienVienDongPhimId id;

    @Column(name = "tenvaidien",length = 50)
    private String tenvaidien;


    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idphim")
    private Phim phim;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("iddienvien")
    private DienVien dienVien;

    public DienVienDongPhim() {
    }

    public DienVienDongPhim(DienVienDongPhimId id, String tenvaidien, Phim phim, DienVien dienVien) {
        this.id = id;
        this.tenvaidien = tenvaidien;
        this.phim = phim;
        this.dienVien = dienVien;
    }

    public DienVienDongPhimId getId() {
        return id;
    }

    public void setId(DienVienDongPhimId id) {
        this.id = id;
    }

    public String getTenvaidien() {
        return tenvaidien;
    }

    public void setTenvaidien(String tenvaidien) {
        this.tenvaidien = tenvaidien;
    }

    public Phim getPhim() {
        return phim;
    }

    public void setPhim(Phim phim) {
        this.phim = phim;
    }

    public DienVien getDienVien() {
        return dienVien;
    }

    public void setDienVien(DienVien dienVien) {
        this.dienVien = dienVien;
    }

    @Embeddable
    public  static  class DienVienDongPhimId implements Serializable
   {
       @Column(name = "idphim")
       private Long idphim;

       @Column(name ="iddienvien")
       private Long iddienvien;

       public DienVienDongPhimId() {
       }

       public DienVienDongPhimId(Long idphim, Long iddienvien) {
           this.idphim = idphim;
           this.iddienvien = iddienvien;
       }

       public Long getIdphim() {
           return idphim;
       }

       public void setIdphim(Long idphim) {
           this.idphim = idphim;
       }

       public Long getIddienvien() {
           return iddienvien;
       }

       public void setIddienvien(Long iddienvien) {
           this.iddienvien = iddienvien;
       }
   }
}
