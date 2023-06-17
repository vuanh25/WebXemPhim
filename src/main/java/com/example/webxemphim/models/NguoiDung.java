package com.example.webxemphim.models;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "NguoiDung")
public class NguoiDung {
    @Id
    @Column(name = "idnguoidung")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idnguoidung;

    @Column(nullable = false, length = 255)
    private String username;
    @Column(name = "hoten",length = 50)
    private String hoten;

    @Column(name ="gioitinh")
    private String gioitinh;


    @Column(name = "email",nullable = false,length = 255)
    private String email;

    @Column(name= "sdt",length = 50)
    private String sdt;

    @Column(name = "ngaysinh")
    private Date ngaysinh;

    @Column(name = "matkhau",nullable = false,length = 255)
    private String matkhau;

    @Column(name = "hinhanhnguoidung")
    private String hinhanhnguoidung;

    @Column(nullable = true, length = 255)
    private String tokenforgotpassword;

    @Column(nullable = true)
    private LocalDateTime timeexpired;

    @Column(name = "isdeleted", columnDefinition = "boolean default false")
    private boolean isdeleted;

    @Column(name = "verification_code", length = 255)
    private String verificationCode;



    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();








    public NguoiDung() {
    }

    public NguoiDung(Long idnguoidung, String username, String hoten, String gioitinh, String email, String sdt, Date ngaysinh, String matkhau, String hinhanhnguoidung, String tokenforgotpassword, LocalDateTime timeexpired, boolean isdeleted, String verificationCode, Set<Role> roles) {
        this.idnguoidung = idnguoidung;
        this.username = username;
        this.hoten = hoten;
        this.gioitinh = gioitinh;
        this.email = email;
        this.sdt = sdt;
        this.ngaysinh = ngaysinh;
        this.matkhau = matkhau;
        this.hinhanhnguoidung = hinhanhnguoidung;
        this.tokenforgotpassword = tokenforgotpassword;
        this.timeexpired = timeexpired;
        this.isdeleted = isdeleted;
        this.verificationCode = verificationCode;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }



    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getTokenforgotpassword() {
        return tokenforgotpassword;
    }

    public void setTokenforgotpassword(String tokenforgotpassword) {
        this.tokenforgotpassword = tokenforgotpassword;
    }

    public LocalDateTime getTimeexpired() {
        return timeexpired;
    }

    public void setTimeexpired(LocalDateTime timeexpired) {
        this.timeexpired = timeexpired;
    }

    public boolean isIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(boolean isdeleted) {
        this.isdeleted = isdeleted;
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

    @Transient
    public String getPhotosImagePath() {
        if (hinhanhnguoidung == null || idnguoidung == null)
            return null;

        return "/photos/users/" + idnguoidung + "/" + hinhanhnguoidung;
    }
}
