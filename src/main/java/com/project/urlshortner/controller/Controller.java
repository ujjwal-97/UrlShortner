package com.project.urlshortner.controller;

import com.project.urlshortner.model.UrlLink;
import com.project.urlshortner.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class Controller {
    @Autowired
    UrlService urlService;

    @GetMapping("/listAll")
    public ResponseEntity<?> getAllLinks(){
        List<UrlLink> urlLinkList = urlService.getAllUrls();
        if(Objects.nonNull(urlLinkList)){
            return new ResponseEntity<>(urlLinkList, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/redirect/{url}")
    public ResponseEntity<?> getOriginalUrl(@PathVariable String url) throws UnsupportedEncodingException {
        UrlLink urlLink = urlService.getUrl(url);
        if(Objects.nonNull(urlLink)){
            String redirectionUrl = URLDecoder.decode(urlLink.getOriginalUrl(), StandardCharsets.UTF_8.toString()).substring(8);
            return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(redirectionUrl)).build();
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/generate")
    public ResponseEntity<?> createLink(@RequestBody String longUrl){
        String shortUrl = urlService.generateShortUrl(longUrl);
        if(Objects.nonNull(shortUrl)){
            return new ResponseEntity<>(shortUrl, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
