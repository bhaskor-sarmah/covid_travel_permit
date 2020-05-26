package com.bohniman.travelpermit.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * QrCodeMemberDetails Date : 09-05-2020
 * 
 * @author Sangram Singha
 */
@Entity
@Table(name = "qrcode_member_detail")
public class QrCodeMemberDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 512)
    private String name;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "address", length = 512)
    private String address;

    @Column(name = "district")
    private String district;

    @Column(name = "thana")
    private String thana;

    @Column(name = "pin")
    private String pin;

    public QrCodeMemberDetail() {
    }

    public QrCodeMemberDetail(Long id, String name, String mobileNumber, String address, String district, String thana,
            String pin) {
        this.id = id;
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.address = address;
        this.district = district;
        this.thana = thana;
        this.pin = pin;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return this.mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistrict() {
        return this.district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getThana() {
        return this.thana;
    }

    public void setThana(String thana) {
        this.thana = thana;
    }

    public String getPin() {
        return this.pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    @Override
    public String toString() {
        return "{" + " id='" + id + "'" + ", name='" + name + "'" + ", mobileNumber='" + mobileNumber + "'"
                + ", address='" + address + "'" + ", district='" + district + "'" + ", thana='" + thana + "'"
                + ", pin='" + pin + "'" + "}";
    }

}