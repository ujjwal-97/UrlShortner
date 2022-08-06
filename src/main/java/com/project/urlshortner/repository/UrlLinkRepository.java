package com.project.urlshortner.repository;

import com.project.urlshortner.model.UrlLink;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UrlLinkRepository extends JpaRepository<UrlLink, Long> {
    List<UrlLink> findAll();
    Optional<UrlLink> findById(Long id);
    UrlLink findByOriginalUrl(String originalUrl);
    UrlLink findByShortenedUrl(String shortenedUrl);
    Boolean existsByShortenedUrl(String shortenedUrl);

}
