package com.example.webxemphim.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "DaoDien")
public class DaoDien {
    @Id
    @Column(name = "iddaodien")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iddaodien;

    @Column(name = "tendaodien",nullable = false,length = 255)
    private String tendaodien;

    @Column(name = "hinhanhdaodien")
    private String hinhanhdaodien;

    @OneToMany(mappedBy = "daodien")
    @JsonIgnore
    private List<Phim> phims;

    public DaoDien() {
    }

    public DaoDien(Long iddaodien, String tendaodien, String hinhanhdaodien, List<Phim> phims) {
        this.iddaodien = iddaodien;
        this.tendaodien = tendaodien;
        this.hinhanhdaodien = hinhanhdaodien;
        this.phims = phims;
    }

    public Long getIddaodien() {
        return iddaodien;
    }

    public void setIddaodien(Long iddaodien) {
        this.iddaodien = iddaodien;
    }

    public String getTendaodien() {
        return tendaodien;
    }

    public void setTendaodien(String tendaodien) {
        this.tendaodien = tendaodien;
    }

    public String getHinhanhdaodien() {
        return hinhanhdaodien;
    }

    public void setHinhanhdaodien(String hinhanhdaodien) {
        this.hinhanhdaodien = hinhanhdaodien;
    }

    public List<Phim> getPhims() {
        return phims;
    }

    public void setPhims(List<Phim> phims) {
        this.phims = phims;
    }
}
