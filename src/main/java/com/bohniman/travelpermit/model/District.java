package com.bohniman.travelpermit.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;

import org.hibernate.annotations.CollectionId;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import org.hibernate.annotations.Type;

/**
 * Entity District Date : 26-05-2020
 * 
 * @author Sangram Singha
 */
@Entity
@Table(name = "district")
public class District implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "district")
    private String district;

    @Column(name = "is_enable", nullable = false, columnDefinition = "tinyint(1) default 1")
    private Boolean enable;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
    @JoinTable(name = "district_scan_location_mapping", joinColumns = @JoinColumn(name = "district_id", referencedColumnName = "id", table = "district"), inverseJoinColumns = @JoinColumn(name = "scan_location_id", referencedColumnName = "id", table = "scan_location"))
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "nativeMysql")
    @CollectionId(columns = @Column(name = "id"), generator = "nativeMysql", type = @Type(type = "long"))
    private List<ScanLocation> scanLocations = new ArrayList<ScanLocation>();

    public District() {
    }

    public District(Long id, String district, Boolean enable, List<ScanLocation> scanLocations) {
        this.id = id;
        this.district = district;
        this.enable = enable;
        this.scanLocations = scanLocations;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDistrict() {
        return this.district;
    }

    public void setDistrict(String district) {
        this.district = district;
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

    public List<ScanLocation> getScanLocations() {
        return this.scanLocations;
    }

    public void setScanLocations(List<ScanLocation> scanLocations) {
        this.scanLocations = scanLocations;
    }

    public void addScanLocations(ScanLocation scanLocations) {
        this.scanLocations.add(scanLocations);
    }

    @Override
    public String toString() {
        return "{" + " id='" + id + "'" + ", district='" + district + "'" + ", enable='" + enable + "'"
                + ", scanLocations='" + scanLocations + "'" + "}";
    }

}