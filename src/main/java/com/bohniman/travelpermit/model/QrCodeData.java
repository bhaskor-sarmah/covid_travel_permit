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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * QrCodeData Date : 09-05-2020
 * 
 * @author Sangram Singha
 */
@Entity
@Table(name = "qrcode_data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QrCodeData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token_id", unique = true)
    private String tokenId;

    @Column(name = "destination_district")
    private String destinationDistrict;

    @Column(name = "status", columnDefinition = "varchar(255) default 'ACTIVE'")
    private String status;

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

}