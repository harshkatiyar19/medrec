package medirec.demo.entity.patient;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
@Table(name = "reports")
@SequenceGenerator(name = "seqReports",
        sequenceName ="seq_reports" ,
        allocationSize = 100)
public class Reports extends VisitBase {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="seqReports")
    @Column(name="id")
    private long id;

    @Column(name="report_name",nullable = false)
    private String reportName;

    @Column(name="report_url",nullable = false)
    private String reportUrl;
}
