package de.aleri.billsmanager;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DocumentModel {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    /* File */
    @Lob
    private byte[] fileData;
    private String fileName;
    private String fileType;

    /* Entrance */
    private Date entranceDate;
    private String entrancePerson;

    /* Approval */
    private Date approvalDate;
    private String approvalPerson1;
    private String approvalPerson2;

    /* Shipment */
    private Date shippmentDate;

    /* Info */
    private String comment;

    /* Default constructor on file upload */
    public DocumentModel(String fileName, String fileType, byte[] fileData) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileData = fileData;
    }
}