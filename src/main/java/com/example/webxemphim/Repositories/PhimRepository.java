package com.example.webxemphim.Repositories;

import com.example.webxemphim.models.Phim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PhimRepository extends JpaRepository<Phim,Long> {
    List<Phim> findByTenphimContainingIgnoreCase(String keyword);

    @Query("SELECT p FROM Phim p WHERE p.TheLoai.tentheloai LIKE %:tentheloai%")
    List<Phim> searchMoviesByTheLoai(@Param("tentheloai") String tentheloai);
}
