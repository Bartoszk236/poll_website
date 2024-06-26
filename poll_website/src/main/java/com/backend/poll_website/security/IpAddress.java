package com.backend.poll_website.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class IpAddress {

    public String getIpAddress(HttpServletRequest request){
        return request.getRemoteAddr();
    }
}
