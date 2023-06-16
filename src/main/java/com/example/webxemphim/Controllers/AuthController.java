package com.example.webxemphim.Controllers;

import com.example.webxemphim.Exception.UsernotFoundException;
import com.example.webxemphim.Repositories.NguoiDungRepository;
import com.example.webxemphim.Services.MailServices;
import com.example.webxemphim.Services.RoleServices;
import com.example.webxemphim.Services.UserServices;
import com.example.webxemphim.Util.Utilities;
import com.example.webxemphim.models.CustomUserDetails;
import com.example.webxemphim.models.NguoiDung;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@RestController
@RequestMapping("/API")
public class AuthController {

    @Autowired
    private UserServices userServices;

    @Autowired
    private RoleServices roleServices;


    @Autowired
    private NguoiDungRepository nguoiDungRepository;


    @Autowired
    private MailServices mailServices;


    @Autowired
    private HttpServletRequest request;



    @GetMapping("/auth/me")
    public ResponseEntity<NguoiDung> findMe(Authentication authentication)
    {
        NguoiDung nguoiDung = ((CustomUserDetails) authentication.getPrincipal()).getNguoiDung();

        if (nguoiDung == null)
        {
            return ResponseEntity.notFound().build();
        }
        else
        {
            return ResponseEntity.ok(nguoiDung);
        }
    }

    @PostMapping("/process_register")
    public ResponseEntity<Object> processRegister(@RequestBody NguoiDung nguoiDung)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodePassword = passwordEncoder.encode(nguoiDung.getMatkhau());
        nguoiDung.setMatkhau(encodePassword);
        String verificationCode = RandomString.make(30);
        nguoiDung.setVerificationCode(verificationCode);
        nguoiDungRepository.save(nguoiDung);
        try
        {
            String activeAccountLink = Utilities.getSiteURL(request) + "/register_request?verificationCode=" + verificationCode;
            String subject = "Link verify";
            String content = "<p>Chao`,</p>" + "<p>Kích hoạt tài khoản.</p>"
                    + "<p>Nhấn vào liên kết bên dưới để kích hoạt tài khoản:</p>" + "<p><a href=\"" + activeAccountLink
                    + "\">Active Account</a></p>" + "<br>";
            mailServices.sendEmail(nguoiDung.getEmail(), subject, content);

            return ResponseEntity.ok("Đăng ký tài khoản thành công");
        }
        catch (Exception ex)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/register_request")
    public ResponseEntity<Object> showRegisterRequest(@RequestParam("verificationCode")String verificationCode)
    {
        if (userServices.verify(verificationCode))
        {
            return  ResponseEntity.ok("Tai khoan da duoc mo");
        }
        else
        {
            return ResponseEntity.ok("Tai khoan chua mo duoc");
        }
    }


    @GetMapping("/login")
    public ResponseEntity<Object> login()
    {
        return ResponseEntity.ok("auth/login");
    }


    @GetMapping("/forgot_password")
    public ResponseEntity<String> showForgotPasswordForm() {
        // Trả về một thông báo hoặc một đối tượng DTO chứa thông tin của API
        String message = "Please provide your email to reset password.";
        return ResponseEntity.ok(message);
    }
    @PostMapping("/forgot_password")
    public ResponseEntity<String> processForgotPassword(@RequestBody Map<String,String> requestParams)
    {
        String email = requestParams.get("email");
        String token = RandomString.make(30);
        try
        {
            userServices.updateResetPasswordToken(token,email);
            String resetPasswordLink = Utilities.getSiteURL(request)+ "/reset_password?token" +token;
            String subject = "Link password";

            String content = "<p>Hello,</p>" + "<p>You have requested to reset your password.</p>"
                    + "<p>Click the link below to change your password:</p>" + "<p><a href=\"" + resetPasswordLink
                    + "\">Change my password</a></p>" + "<br>" + "<p>Ignore this email if you do remember your password, "
                    + "or you have not made the request.</p>";
            mailServices.sendEmail(email, subject, content);

            String message = "We have sent a reset password link to your email. Please check.";
            return ResponseEntity.ok(message);
        }
        catch (UsernotFoundException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (UnsupportedEncodingException | MessagingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while sending email");
        }
    }

    @GetMapping("/verify")
    public ResponseEntity<String> verifyUser(@RequestParam("code") String code) {
        if (userServices.verify(code)) {
            String message = "Congratulations, your account has been verified.";
            return ResponseEntity.ok(message);
        } else {
            String errorMessage = "Sorry, we could not verify the account. It may have already been verified, or the verification code is incorrect.";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
    }






}
