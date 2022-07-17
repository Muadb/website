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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

@Service
public class VisitorService {
    private static final String HEADER_USERAGENT = "user-agent";
    private static final Logger LOGGER = LoggerFactory.getLogger(VisitorService.class);

    @Autowired
    VisitorDao visitorDao;

    @Autowired
    EmailService emailService;

    @Value("${geolib.path}")
    private String geolib;

    public void visitorClientInfo(HttpServletRequest request) {
        File database = new File(geolib);
        String visitorIp = request.getRemoteAddr();

        CityResponse response = null;
        String country = null;
        String city = null;
        String postal = null;
        String state = null;
        String os = null;
        String ip = null;

        try {
            DatabaseReader dbReader = new DatabaseReader.Builder(database).build();
            InetAddress inetAddress = InetAddress.getByName(visitorIp);
            response = dbReader.city(inetAddress);
        } catch (IOException | GeoIp2Exception e) {
            throw new RuntimeException("Visitor information cannot be resolved");
        }

        try {
            country = response.getCountry().getName();
            city = response.getCity().getName();
            postal = response.getPostal().getCode();
            state = response.getLeastSpecificSubdivision().getName();
        } catch (NullPointerException e) {
        throw new RuntimeException("Response from dbreader is NULL");
    }
            os = request.getHeader(HEADER_USERAGENT);
            ip = request.getRemoteAddr();

            TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
            Date date = new Date();
            Visitor visitor = new Visitor(country, city, postal, state, date.toString(), os, ip);
            visitorDao.save(visitor);
            String emailMessage = "[Visitor] - IP: " + ip + ", Country: " + country + ", City: " + city + ", Postal: " + postal + ", OS: " + os;
            LOGGER.info(emailMessage);

            //if (country.equals("Turkey")) {
            //    emailService.sendEmailToMe(message);
            //}


    }
}