package com.example.webxemphim.Controllers;

import com.example.webxemphim.Payload.request.LoginRequest;
import com.example.webxemphim.Payload.request.SignupRequest;
import com.example.webxemphim.Payload.response.MessageResponse;
import com.example.webxemphim.Repositories.NguoiDungRepository;
import com.example.webxemphim.Repositories.RoleRepository;
import com.example.webxemphim.Repository.UserInfoResponse;
import com.example.webxemphim.Security.jwt.JwtUtil;
import com.example.webxemphim.Services.MailServices;
import com.example.webxemphim.Services.RoleServices;
import com.example.webxemphim.Services.UserDetailsServicelmpl;
import com.example.webxemphim.Services.UserServices;
import com.example.webxemphim.models.CustomUserDetails;
import com.example.webxemphim.models.ERole;
import com.example.webxemphim.models.NguoiDung;
import com.example.webxemphim.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/API/auth")
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

    @Autowired
    private UserDetailsServicelmpl userDetailsServicelmpl;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    private RoleRepository roleRepository;










    @PostMapping ("/signin")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest)
    {

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),loginRequest.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        ResponseCookie jwtCookie = jwtUtil.generateJwtCookie(userDetails);
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new UserInfoResponse(userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getEmail(),
                        roles) {
                });

    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
        if (nguoiDungRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (nguoiDungRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        NguoiDung user = new NguoiDung();
        user.setEmail(signUpRequest.getEmail());
        user.setUsername(signUpRequest.getUsername());
        user.setMatkhau(encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        nguoiDungRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtUtil.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new MessageResponse("You've been signed out!"));
    }

    @GetMapping("/forgotpassword")
    public String forgotPassword(@RequestParam String email) {
        String checkUserExist = this.userServices.getValidatedUserEmail(email);
        return checkUserExist;
    }



    @PostMapping("/resetpassword")
    public ResponseEntity<String> resetPasswd(@RequestParam String email)
    {

        NguoiDung userObj=userServices.fetchByUserEmailId(email);

        if(userObj!=null)
        {
            int myotp= (int)(Math.random()*9000)+1000;
            userObj.setOtp(myotp);
            LocalTime time=LocalTime.now();
            LocalDate date=LocalDate.now();


            userObj.setLocalTime(time);
            userObj.setLocalDate(date);

            userServices.save(userObj);
            boolean status = userServices.sendEmail(myotp,email);
            if(!status)
            {
                return new ResponseEntity<>("Otp is not sending", HttpStatus.BAD_REQUEST);
            }


            return new ResponseEntity<>("Otp sent on " + userObj.getEmail(),HttpStatus.OK);
        }

        return new ResponseEntity<>("Email is invaild",HttpStatus.BAD_REQUEST);

    }


    @PostMapping("/resetpassword/verify")
    public ResponseEntity<String> resetPasswdWithVerify(@RequestParam String email,@RequestParam int otp, @RequestParam String pass)
    {

        NguoiDung userObj=userServices.fetchByUserEmailId(email);
        if (userObj==null)
            return new ResponseEntity<>("Incorrect User",HttpStatus.BAD_REQUEST);
        int myotp=userObj.getOtp();
        int t=LocalTime.now().minusMinutes(5).compareTo(userObj.getLocalTime());
        int d=userObj.getLocalDate().compareTo(LocalDate.now());

        if(d!=0 || t>=0)
            return new ResponseEntity<>("Otp expired! Please otp generate again.",HttpStatus.BAD_REQUEST);

        if(myotp!=otp)
            return new ResponseEntity<>("Incorrect otp",HttpStatus.BAD_REQUEST);

        // reset pass
        userServices.updatePass(userObj,pass);

        return new ResponseEntity<>("Password Changed Successfully ",HttpStatus.OK);
    }









}
