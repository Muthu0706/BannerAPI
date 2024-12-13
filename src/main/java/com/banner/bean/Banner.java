package com.banner.bean;

import java.time.ZonedDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="tbl_banner")
@Getter
@Setter
public class Banner {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",unique=true)
    private Long bannerId;
    
    private String title;
    
    private String content;
    
    private ZonedDateTime startDateTime;
    
    private ZonedDateTime endDateTime;
    
    @Enumerated(EnumType.STRING) 
    private BannerType type;

    public enum BannerType {
        ALERT,
        INFORMATION,
        PROMOTION
     }

}


