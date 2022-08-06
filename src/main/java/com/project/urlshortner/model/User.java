package com.project.urlshortner.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "USER_111")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;

    @NonNull
    @Column(name = "NAME")
    private String name;


    @Column(name = "PHONE")
    private String phone;

    @NonNull
    @Column(name="EMAIL")
    private String email;

    @NonNull
    @Column(name = "CREATION_TS")
    private Timestamp creationTime;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
//    @JsonManagedReference
//    List<UrlLink> urlLinkList;
}
