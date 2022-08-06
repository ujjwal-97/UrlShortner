package com.project.urlshortner.service;

import com.project.urlshortner.model.UrlLink;
import com.project.urlshortner.repository.UrlLinkRepository;
import com.project.urlshortner.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.List;
import java.util.Objects;

@org.springframework.stereotype.Service
@Data
public class UrlService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UrlLinkRepository urlLinkRepository;

    static int SHORT_URL_CHAR_SIZE=7;
    public String convertUrl(String longUrl){
        try{
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(longUrl.getBytes(StandardCharsets.UTF_8));
            byte[] messageDigest = digest.digest();

            StringBuilder hexString = new StringBuilder();
            for(byte b: messageDigest){
                hexString.append(Integer.toHexString(0xFF & b));
            }
            return hexString.toString();

        } catch (Exception e) {
            throw  new RuntimeException(e);
        }
    }

    public String generateShortUrl(String longUrl){
        //Check if the original Url exists in the DataBase or not
        UrlLink urlLinkOriginal = urlLinkRepository.findByOriginalUrl(longUrl);
        if(Objects.nonNull(urlLinkOriginal)) return urlLinkOriginal.getShortenedUrl();

        //Else Generate new Hash
        String hash = convertUrl(longUrl);
        int numbersOfCharsHash = hash.length();
        int counter =0;
        while(counter < numbersOfCharsHash-SHORT_URL_CHAR_SIZE){
            String shortenedUrl = hash.substring(counter, counter+SHORT_URL_CHAR_SIZE);
            if(!urlLinkRepository.existsByShortenedUrl(shortenedUrl)){
                //Make entry in DB
                try{
                    UrlLink urlLink = new UrlLink();
                    urlLink.setOriginalUrl(longUrl);
                    urlLink.setShortenedUrl(shortenedUrl);
                    urlLinkRepository.save(urlLink);
                    return shortenedUrl;
                }catch (Exception e){
                    throw new Error("DataBase Insertion Error");
                }
            }
            counter++;
        }
        return hash;
    }

    public List<UrlLink> getAllUrls(){
        List<UrlLink> urlLinkList = urlLinkRepository.findAll();
        return urlLinkList;
    }

    public UrlLink getUrl(String url) {
        UrlLink urlLink = urlLinkRepository.findByShortenedUrl(url);
        return urlLink;
    }
}
