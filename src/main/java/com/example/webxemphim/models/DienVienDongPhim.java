package com.example.webxemphim.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "DienVienDongPhim")
public class DienVienDongPhim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iddienviendongphim;

    @Column(name = "tenvaidien",length = 50)
    private String tenvaidien;


    @ManyToOne
    @JoinColumn(name = "phim_id")
    private Phim phim;

    @ManyToOne
    @JoinColumn(name = "iddienvien")
    private DienVien dienvien;

    public DienVienDongPhim() {
    }





    public String getTenvaidien() {
        return tenvaidien;
    }

    public void setTenvaidien(String tenvaidien) {
        this.tenvaidien = tenvaidien;
    }





    public Long getIddienviendongphim() {
        return iddienviendongphim;
    }

    public void setIddienviendongphim(Long iddienviendongphim) {
        this.iddienviendongphim = iddienviendongphim;
    }

    public DienVienDongPhim(Long iddienviendongphim, String tenvaidien, Phim phim, DienVien dienvien) {
        this.iddienviendongphim = iddienviendongphim;
        this.tenvaidien = tenvaidien;
        this.phim = phim;
        this.dienvien = dienvien;
    }


    public Phim getPhim() {
        return phim;
    }

    public void setPhim(Phim phim) {
        this.phim = phim;
    }

    public DienVien getDienvien() {
        return dienvien;
    }

    public void setDienvien(DienVien dienvien) {
        this.dienvien = dienvien;
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
