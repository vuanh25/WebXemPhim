package com.example.webxemphim.Repositories;

import com.example.webxemphim.models.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NguoiDungRepository extends JpaRepository<NguoiDung,Long> {
//    @Query("SELECT n FROM NguoiDung n WHERE N.taikhoan = :taikhoan")
//    public NguoiDung get
}
