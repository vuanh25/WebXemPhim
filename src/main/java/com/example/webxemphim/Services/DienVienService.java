package com.example.webxemphim.Services;

import com.example.webxemphim.Repositories.DienVienRepository;
import com.example.webxemphim.models.DienVien;
import com.example.webxemphim.models.DienVienDongPhim;
import com.example.webxemphim.models.TheLoai;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class DienVienService {
    @Autowired
    DienVienRepository dienVienRepository;


    public List<DienVien> listAll(){return  dienVienRepository.findAll();}


    public void save(DienVien dienVien){dienVienRepository.save(dienVien);}
    public DienVien get(Long id)
    {
        return dienVienRepository.findById(id).get();
    }

    public void delete(Long id)
    {
        dienVienRepository.deleteById(id);
    }

}
