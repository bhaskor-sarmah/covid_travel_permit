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
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import org.hibernate.annotations.Type;

/**
 * QrCodeData Date : 09-05-2020
 * 
 * @author Sangram Singha
 */
@Entity
@Table(name = "qrcode_data")
public class QrCodeData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token_id", unique = true)
    private String tokenId;

    @Column(name = "reached_screening_center", nullable = false, columnDefinition = "tinyint(1) default 0")
    private Boolean reachedScreeningCenter;

    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.LAZY)
    @JoinTable(name = "qrcode_data_qrcode_scan_detail_mapping", joinColumns = @JoinColumn(name = "qrcode_data_id", referencedColumnName = "id", table = "qrcode_data"), inverseJoinColumns = @JoinColumn(name = "qrcode_scan_detail_id", referencedColumnName = "id", table = "qrcode_scan_detail"))
    // @GeneratedValue(strategy=GenerationType.IDENTITY, generator = "nativeMysql")
    // @CollectionId(
    // columns = @Column(name="id"),
    // generator = "nativeMysql",
    // type = @Type(type="long")
    // )
    private List<QrcodeScanDetail> scanDetails = new ArrayList<QrcodeScanDetail>();

    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.LAZY)
    @JoinTable(name = "qrcode_data_qrcode_member_detail_mapping", joinColumns = @JoinColumn(name = "qrcode_data_id", referencedColumnName = "id", table = "qrcode_data"), inverseJoinColumns = @JoinColumn(name = "qrcode_member_detail_id", referencedColumnName = "id", table = "qrcode_member_detail"))
    // @GeneratedValue(strategy=GenerationType.IDENTITY, generator = "nativeMysql")
    // @CollectionId(
    // columns = @Column(name="id"),
    // generator = "nativeMysql",
    // type = @Type(type="long")
    // )
    private List<QrCodeMemberDetail> memberDetails = new ArrayList<QrCodeMemberDetail>();

    public QrCodeData() {
    }

    public QrCodeData(Long id, String tokenId, Boolean reachedScreeningCenter, List<QrcodeScanDetail> scanDetails,
            List<QrCodeMemberDetail> memberDetails) {
        this.id = id;
        this.tokenId = tokenId;
        this.reachedScreeningCenter = reachedScreeningCenter;
        this.scanDetails = scanDetails;
        this.memberDetails = memberDetails;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTokenId() {
        return this.tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public Boolean isReachedScreeningCenter() {
        return this.reachedScreeningCenter;
    }

    public Boolean getReachedScreeningCenter() {
        return this.reachedScreeningCenter;
    }

    public void setReachedScreeningCenter(Boolean reachedScreeningCenter) {
        this.reachedScreeningCenter = reachedScreeningCenter;
    }

    public List<QrcodeScanDetail> getScanDetails() {
        return this.scanDetails;
    }

    public void setScanDetails(List<QrcodeScanDetail> scanDetails) {
        this.scanDetails = scanDetails;
    }

    public List<QrCodeMemberDetail> getMemberDetails() {
        return this.memberDetails;
    }

    public void setMemberDetails(List<QrCodeMemberDetail> memberDetails) {
        this.memberDetails = memberDetails;
    }

    @Override
    public String toString() {
        return "{" + " id='" + id + "'" + ", tokenId='" + tokenId + "'" + ", reachedScreeningCenter='"
                + reachedScreeningCenter + "'" + ", scanDetails='" + scanDetails + "'" + ", memberDetails='"
                + memberDetails + "'" + "}";
    }

}