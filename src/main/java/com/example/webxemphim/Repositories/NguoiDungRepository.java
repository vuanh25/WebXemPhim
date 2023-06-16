package com.example.webxemphim.Repositories;

import com.example.webxemphim.models.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NguoiDungRepository extends JpaRepository<NguoiDung,Long> {
    @Query("SELECT u FROM NguoiDung u WHERE u.hoten = :hoten")
    public NguoiDung getUserByUsername(@Param("hoten") String hoten);
    @Query("SELECT u FROM NguoiDung u WHERE u.email = :email")
    public NguoiDung getUserByEmail(@Param("email")String email);
    @Query("SELECT u FROM NguoiDung u WHERE u.tokenforgotpassword = :token")
    public NguoiDung getUserBytokenforgotpassword(String token);
    @Query("SELECT u FROM NguoiDung u WHERE u.verificationCode = :code")
    public NguoiDung findByVerificationCode(String code);
}
