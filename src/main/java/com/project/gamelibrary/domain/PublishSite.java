package com.project.gamelibrary.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter @Setter
public class PublishSite {
    @Id @GeneratedValue
    @Column(name="publishSite_id")
    private Long id;

    private String siteName;

    private String siteUrl;

    private String siteNation;
}
