package com.example.webxemphim.models;

import javax.persistence.*;

@Entity
@Table(name = "Admin")
public class Admin {
    @Id
    @Column(name = "idadmin")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idadmin;


    @Column(name = "taikhoanadmin",nullable = false,length = 50)
    private String taikhoanadmin;

    @Column(name = "matkhauadmin",nullable = false,length = 50)
    private String matkhauadmin;


    @Column(name = "tenadmin",nullable = false,length = 50)
    private String tenadmin;


    @Column(name = "emailadmin",nullable = false,length = 100)
    private String emailadmin;


    public Admin() {
    }

    public Admin(Long idadmin, String taikhoanadmin, String matkhauadmin, String tenadmin, String emailadmin) {
        this.idadmin = idadmin;
        this.taikhoanadmin = taikhoanadmin;
        this.matkhauadmin = matkhauadmin;
        this.tenadmin = tenadmin;
        this.emailadmin = emailadmin;
    }

    public Long getIdadmin() {
        return idadmin;
    }

    public void setIdadmin(Long idadmin) {
        this.idadmin = idadmin;
    }

    public String getTaikhoanadmin() {
        return taikhoanadmin;
    }

    public void setTaikhoanadmin(String taikhoanadmin) {
        this.taikhoanadmin = taikhoanadmin;
    }

    public String getMatkhauadmin() {
        return matkhauadmin;
    }

    public void setMatkhauadmin(String matkhauadmin) {
        this.matkhauadmin = matkhauadmin;
    }

    public String getTenadmin() {
        return tenadmin;
    }

    public void setTenadmin(String tenadmin) {
        this.tenadmin = tenadmin;
    }

    public String getEmailadmin() {
        return emailadmin;
    }

    public void setEmailadmin(String emailadmin) {
        this.emailadmin = emailadmin;
    }
}
