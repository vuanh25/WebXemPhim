package com.example.webxemphim.Services;


import com.example.webxemphim.Exception.UsernotFoundException;
import com.example.webxemphim.Repositories.NguoiDungRepository;
import com.example.webxemphim.models.NguoiDung;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class UserServices {
    @Autowired
    private NguoiDungRepository nguoiDungRepository;


    public List<NguoiDung> listAll(){return  nguoiDungRepository.findAll();}


    public NguoiDung save(NguoiDung nguoiDung)
    {
        nguoiDungRepository.save(nguoiDung);
        return nguoiDung;
    }

    public NguoiDung get(Long id){return nguoiDungRepository.findById(id).orElse(null);}

    public void  delete(Long id){nguoiDungRepository.deleteById(id);}


//    public void updateResetPasswordToken(String token,String email) throws UsernotFoundException
//    {
//        NguoiDung nguoiDung = nguoiDungRepository.getUserByEmail(email);
//        if (nguoiDung != null)
//        {
//            nguoiDung.setTokenforgotpassword(token);
//            nguoiDung.setTimeexpired(LocalDateTime.now().plusMinutes(1));
//            nguoiDungRepository.save(nguoiDung);
//        }
//        else
//        {
//            throw  new UsernotFoundException("Khong ton tai nguoi dung co email "+ email);
//        }
//    }

//    public NguoiDung getUserByTokenforgotpassWord(String token) {
//        return nguoiDungRepository.getUserBytokenforgotpassword(token);
//    }

    public void updatePassword(NguoiDung nguoiDung, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        nguoiDung.setMatkhau(encodedPassword);

        nguoiDung.setTokenforgotpassword(null);
        nguoiDungRepository.save(nguoiDung);
    }


//    public boolean verify(String verificationCode) {
//        NguoiDung nguoidung = nguoiDungRepository.findByVerificationCode(verificationCode);
//        if (nguoidung == null || nguoidung.isEnabled()) {
//            return false;
//        } else {
//            nguoidung.setVerificationCode(null);
//            nguoidung.setEnabled(true);
//            nguoiDungRepository.save(nguoidung);
//            return true;
//        }
//    }



}
