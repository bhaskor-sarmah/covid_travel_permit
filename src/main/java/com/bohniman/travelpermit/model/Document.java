package com.bohniman.travelpermit.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Entity Document Date : 01-06-2020
 * 
 * @author Sangram Singha
 */
@Entity
@Table(name = "document")
public class Document implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Lob
    @Column(name = "byte_file", columnDefinition = "mediumblob")
    private byte[] byteFile;

    @Column(name = "file_type")
    private String fileType;

    @Transient
    private MultipartFile file;

    public Document() {
    }

    public Document(Long id, byte[] byteFile, String fileType, MultipartFile file) {
        this.id = id;
        this.byteFile = byteFile;
        this.fileType = fileType;
        this.file = file;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getByteFile() {
        return this.byteFile;
    }

    public void setByteFile(byte[] byteFile) {
        this.byteFile = byteFile;
    }

    public String getFileType() {
        return this.fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public MultipartFile getFile() {
        return this.file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "{" + " id='" + id + "'" + ", byteFile='" + byteFile + "'" + ", fileType='" + fileType + "'" + ", file='"
                + file + "'" + "}";
    }

}