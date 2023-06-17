package com.example.webxemphim.models;

public class JwtResponse {
    private String token;
    // Các thông tin khác cần trả về

    public JwtResponse(String token) {
        this.token = token;
    }


    public JwtResponse() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
