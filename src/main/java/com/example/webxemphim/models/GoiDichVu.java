package com.example.webxemphim.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "GoiDichVu")
public class GoiDichVu {
    @Id
    @Column(name = "idgoidichvu")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idgoidichvu;

    @Column(name = "tengoidichvu")
    private String tengoidichvu;

    @Column(name = "giagoidichvu")
    private Double giagoidichvu;

    @Column(name = "motagoidichvu")
    private String motagoidichvu;

    @OneToMany(mappedBy = "GoiDichVu")
    private List<PhieuDangKy> phieudangkys;

    public GoiDichVu() {
    }

    public GoiDichVu(Long idgoidichvu, String tengoidichvu, Double giagoidichvu, String motagoidichvu, List<PhieuDangKy> phieudangkys) {
        this.idgoidichvu = idgoidichvu;
        this.tengoidichvu = tengoidichvu;
        this.giagoidichvu = giagoidichvu;
        this.motagoidichvu = motagoidichvu;
        this.phieudangkys = phieudangkys;
    }

    public Long getIdgoidichvu() {
        return idgoidichvu;
    }

    public void setIdgoidichvu(Long idgoidichvu) {
        this.idgoidichvu = idgoidichvu;
    }

    public String getTengoidichvu() {
        return tengoidichvu;
    }

    public void setTengoidichvu(String tengoidichvu) {
        this.tengoidichvu = tengoidichvu;
    }

    public Double getGiagoidichvu() {
        return giagoidichvu;
    }

    public void setGiagoidichvu(Double giagoidichvu) {
        this.giagoidichvu = giagoidichvu;
    }

    public String getMotagoidichvu() {
        return motagoidichvu;
    }

    public void setMotagoidichvu(String motagoidichvu) {
        this.motagoidichvu = motagoidichvu;
    }

    public List<PhieuDangKy> getPhieudangkys() {
        return phieudangkys;
    }

    public void setPhieudangkys(List<PhieuDangKy> phieudangkys) {
        this.phieudangkys = phieudangkys;
    }
}
