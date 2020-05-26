package com.bohniman.travelpermit.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * QrcodeScanDetails Date : 09-05-2020
 * 
 * @author Sangram Singha
 */
@Entity
@Table(name = "qrcode_scan_detail")
public class QrcodeScanDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "scan_date_time", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date scanDateTime;

    @Column(name = "gps_location")
    private String gpsLocation;

    @JoinColumn(name = "fk_scan_location")
    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.EAGER)
    private ScanLocation scanLocation;

    @Column(name = "personnel_name", length = 512)
    private String personnelName;

    @Column(name = "mobile_number", length = 10)
    private String mobileNumber;

    public QrcodeScanDetail() {
    }

    public QrcodeScanDetail(Long id, Date scanDateTime, String gpsLocation, ScanLocation scanLocation,
            String personnelName, String mobileNumber) {
        this.id = id;
        this.scanDateTime = scanDateTime;
        this.gpsLocation = gpsLocation;
        this.scanLocation = scanLocation;
        this.personnelName = personnelName;
        this.mobileNumber = mobileNumber;
    }

    public QrcodeScanDetail(Date scanDateTime, String gpsLocation, ScanLocation scanLocation, String personnelName,
            String mobileNumber) {
        this.scanDateTime = scanDateTime;
        this.gpsLocation = gpsLocation;
        this.scanLocation = scanLocation;
        this.personnelName = personnelName;
        this.mobileNumber = mobileNumber;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getScanDateTime() {
        return this.scanDateTime;
    }

    public void setScanDateTime(Date scanDateTime) {
        this.scanDateTime = scanDateTime;
    }

    public String getGpsLocation() {
        return this.gpsLocation;
    }

    public void setGpsLocation(String gpsLocation) {
        this.gpsLocation = gpsLocation;
    }

    public ScanLocation getScanLocation() {
        return this.scanLocation;
    }

    public void setScanLocation(ScanLocation scanLocation) {
        this.scanLocation = scanLocation;
    }

    public String getPersonnelName() {
        return this.personnelName;
    }

    public void setPersonnelName(String personnelName) {
        this.personnelName = personnelName;
    }

    public String getMobileNumber() {
        return this.mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Override
    public String toString() {
        return "{" + " id='" + id + "'" + ", scanDateTime='" + scanDateTime + "'" + ", gpsLocation='" + gpsLocation
                + "'" + ", scanLocation='" + scanLocation + "'" + ", personnelName='" + personnelName + "'"
                + ", mobileNumber='" + mobileNumber + "'" + "}";
    }

}