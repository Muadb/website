package com.website.bce.service;

import com.website.bce.model.Visitor;
import com.website.bce.repository.VisitorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class VisitorService {
    private static final String HEADER_USERAGENT = "user-agent";

    @Autowired
    VisitorDao visitorDao;

    public void visitorClientInfo(HttpServletRequest request){
        Visitor visitor = new Visitor(new Date().toString(), request.getHeader(HEADER_USERAGENT), request.getRemoteAddr());
        visitorDao.save(visitor);
    }
}
