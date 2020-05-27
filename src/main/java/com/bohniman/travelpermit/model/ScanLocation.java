package com.bohniman.travelpermit.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * ScanLocation Date : 11-05-2020
 * 
 * @author Sangram Singha
 */
@Entity
@Table(name = "scan_location")
public class ScanLocation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "location", length = 512)
    private String location;

    @Column(name = "is_enable", nullable = false, columnDefinition = "tinyint(1) default 1")
    private Boolean enable;

    @Column(name = "is_screening_center", nullable = false, columnDefinition = "tinyint(1) default 0")
    private Boolean screeningCenter;

    public ScanLocation() {
    }

    public ScanLocation(Long id, String location, Boolean enable, Boolean screeningCenter) {
        this.id = id;
        this.location = location;
        this.enable = enable;
        this.screeningCenter = screeningCenter;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean isEnable() {
        return this.enable;
    }

    public Boolean getEnable() {
        return this.enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Boolean isScreeningCenter() {
        return this.screeningCenter;
    }

    public Boolean getScreeningCenter() {
        return this.screeningCenter;
    }

    public void setScreeningCenter(Boolean screeningCenter) {
        this.screeningCenter = screeningCenter;
    }

    @Override
    public String toString() {
        return "{" + " id='" + id + "'" + ", location='" + location + "'" + ", enable='" + enable + "'"
                + ", screeningCenter='" + screeningCenter + "'" + "}";
    }

}