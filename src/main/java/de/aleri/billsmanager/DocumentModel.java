package de.aleri.billsmanager;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
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
    private String entranceDate;
    private String entrancePerson;

    /* Approval */
    private String approvalDate;
    private String approvalPerson1;
    private String approvalPerson2;

    /* Shipment */
    private String shippmentDate;

    /* Info */
    private String comment;

    /* Default constructor on file upload */
    public DocumentModel(String fileName, String fileType, byte[] fileData) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileData = fileData;
    }
}