package com.website.bce.service;

import com.website.bce.dto.PrincipalDto;
import com.website.bce.model.Principal;
import com.website.bce.repository.PrincipalDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class PrincipalService {
    private static final String HEADER_USERAGENT = "user-agent";

    @Autowired
    PrincipalDao principalDao;

    public void principleContact(PrincipalDto principalDto, HttpServletRequest request) {
        Principal principal = new Principal(principalDto.getName(), principalDto.getEmail(), principalDto.getSubject(), principalDto.getMessage(), new Date().toString());
        principleClientInfo(principal, request);
        principalDao.save(principal);
    }

    private void principleClientInfo(Principal principal, HttpServletRequest request) {
        principal.setOs(request.getHeader(HEADER_USERAGENT));
        principal.setIp(request.getRemoteAddr());
    }

}
