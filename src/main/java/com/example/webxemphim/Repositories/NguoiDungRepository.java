package com.example.webxemphim.Repositories;

import com.example.webxemphim.models.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NguoiDungRepository extends JpaRepository<NguoiDung,Long> {
    Optional<NguoiDung> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    public NguoiDung findByEmail(String emailId);

}
