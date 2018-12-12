package de.aleri.billsmanager;

import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentData {
    private Long id;
    private String fileName;
    private String downloadLink;

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
}
