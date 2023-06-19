package com.example.webxemphim.Services;


import com.example.webxemphim.Repositories.NguoiDungRepository;
import com.example.webxemphim.models.NguoiDung;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class UserServices {
    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    PasswordEncoder encoder;


    public List<NguoiDung> listAll(){return  nguoiDungRepository.findAll();}


    public NguoiDung save(NguoiDung nguoiDung)
    {
        nguoiDungRepository.save(nguoiDung);
        return nguoiDung;
    }



    public NguoiDung get(Long id){return nguoiDungRepository.findById(id).orElse(null);}

    public void  delete(Long id){nguoiDungRepository.deleteById(id);}



//    public void updatePassword(NguoiDung nguoiDung, String newPassword) {
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String encodedPassword = passwordEncoder.encode(newPassword);
//        nguoiDung.setMatkhau(encodedPassword);
//
//        nguoiDung.setOtp(0);
//        nguoiDungRepository.save(nguoiDung);
//    }

    public String getValidatedUserEmail(String email) {
        for(NguoiDung user:nguoiDungRepository.findAll())
        {
            if(user.getEmail().equals(email))
            {

                return	user.getUsername() + " tồn tại trong cơ sở dữ liệu. mật khẩu của bạn là" + user.getMatkhau();

            }

        }

        return "Email không hợp lệ hoặc người dùng chưa được đăng ký";

    }




    public boolean sendEmail(int otp,String email) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);

        msg.setSubject("Reset lại mật khẩu của bạn");
        msg.setText("OTP của bạn là: " + otp);
        javaMailSender.send(msg);
        return true;
    }

    public boolean getValidEmail(String email) {

        for(NguoiDung user:nguoiDungRepository.findAll())
        {
            if(user.getEmail().equals(email))
            {
                return	true;
            }

        }

        return false;
    }

    public String setPass(String pass) {
        for(NguoiDung user:nguoiDungRepository.findAll())
        {
            user.setMatkhau(pass);
            nguoiDungRepository.save(user);
        }
        return "Mật khẩu đã được cập nhập";
    }

    public NguoiDung fetchByUserEmailId(String emailId) {

        return nguoiDungRepository.findByEmail(emailId);
    }

    public NguoiDung updatePass(NguoiDung user,String pass) {
        user.setMatkhau(encoder.encode(pass));
        nguoiDungRepository.save(user);
        return user;
    }












}
