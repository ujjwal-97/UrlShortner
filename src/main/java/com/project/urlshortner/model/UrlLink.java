package com.project.urlshortner.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
;
import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "URL_LINK_111")
public class UrlLink implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;

    @NonNull
    @Column(name = "ORIGINAL_URL")
    private String originalUrl;

    @NonNull
    @Column(name = "SHORTENED_URL")
    private String shortenedUrl;

//    @ManyToOne
//    @JsonBackReference
//    User user;

}
