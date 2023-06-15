package com.example.webxemphim.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name ="TheLoai")
public class TheLoai {
    @Id
    @Column(name = "idtheloai")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idtheloai;

    @Column(name = "tentheloai",nullable = false,length = 255)
    private String tentheloai;

    @OneToMany(mappedBy = "TheLoai")
    @JsonIgnore
    private List<Phim> phims;

    public TheLoai() {
    }

    public TheLoai(Long idtheloai, String tentheloai, List<Phim> phims) {
        this.idtheloai = idtheloai;
        this.tentheloai = tentheloai;
        this.phims = phims;
    }

    public Long getIdtheloai() {
        return idtheloai;
    }

    public void setIdtheloai(Long idtheloai) {
        this.idtheloai = idtheloai;
    }

    public String getTentheloai() {
        return tentheloai;
    }

    public void setTentheloai(String tentheloai) {
        this.tentheloai = tentheloai;
    }

    public List<Phim> getPhims() {
        return phims;
    }

    public void setPhims(List<Phim> phims) {
        this.phims = phims;
    }
}
