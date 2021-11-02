package cz.tmsoft.import_csv.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class CallDetailRecord {

    private Long id;
    private String callerId;
    private String recipient;
    private LocalDate callDate;
    private LocalTime endTime;
    private Long duration;
    private BigDecimal cost;
    private String reference;
    private String currency;
    private String type;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
}
