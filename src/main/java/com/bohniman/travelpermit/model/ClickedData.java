package com.bohniman.travelpermit.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity ClickedData Date : 01-06-2020
 * 
 * @author Sangram Singha
 */
@Entity
@Table(name = "clicked_data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClickedData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "unique_id")
    private String uniqueId;

    @Column(name = "token_id")
    private String tokenId;

    @OneToMany(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.LAZY) // corrected to many user
                                                                                             // can have many role
    @JoinTable(name = "clicked_data_document_mapping", joinColumns = @JoinColumn(name = "clicked_data_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "document_id", referencedColumnName = "id"))
    // @GeneratedValue(strategy=GenerationType.IDENTITY, generator = "nativeMysql")
    // @CollectionId(columns = @Column(name="id"), generator = "nativeMysql", type =
    // @Type(type="long") )
    private List<Document> documents = new ArrayList<Document>();

    private String username;
    private String entryStatus; // Pending/Completed

}