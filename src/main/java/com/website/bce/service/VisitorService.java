package com.website.bce.service;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.website.bce.Application;
import com.website.bce.model.Visitor;
import com.website.bce.repository.VisitorDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;

@Service
public class VisitorService {
    private static final String HEADER_USERAGENT = "user-agent";
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    @Autowired
    VisitorDao visitorDao;

    public void visitorClientInfo(HttpServletRequest request) {
        File database = new File("/root/GeoLite2-City.mmdb");
        String visitorIp = request.getRemoteAddr();
        CityResponse response = null;
        try {
            DatabaseReader dbReader = new DatabaseReader.Builder(database).build();
            InetAddress inetAddress = InetAddress.getByName(visitorIp);
            response = dbReader.city(inetAddress);
            LOGGER.info(response.toJson());
        } catch (IOException | GeoIp2Exception e) {
            e.printStackTrace();
        }

        Visitor visitor = new Visitor(response.getCountry().getName(), response.getCity().getName(), response.getPostal().getCode(), response.getLeastSpecificSubdivision().getName(), new Date().toString(), request.getHeader(HEADER_USERAGENT), request.getRemoteAddr());
        visitorDao.save(visitor);
    }
}