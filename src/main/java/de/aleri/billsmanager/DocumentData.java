package de.aleri.billsmanager;

import lombok.*;

import java.time.LocalDate;
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
}
