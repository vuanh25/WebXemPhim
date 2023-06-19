package com.example.webxemphim.Repositories;

import com.example.webxemphim.models.Phim;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PhimRepository extends JpaRepository<Phim,Long> {
    Page<Phim> findByTenphimContainingIgnoreCase(String keyword, Pageable pageNum);


//    Page<Phim> findByTheLoaiTentheloaiAndTenphimContainingIgnoreCase( Pageable pageable,@Param("tentheloai") String theloai, String keyword);




    @Query("SELECT p FROM Phim p WHERE p.TheLoai.tentheloai LIKE %:tentheloai%")
    Page<Phim> searchMoviesByTheLoai(@Param("tentheloai") String tentheloai, Pageable pageNum);
}
