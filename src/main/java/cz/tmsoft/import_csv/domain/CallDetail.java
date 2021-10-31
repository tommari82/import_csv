package cz.tmsoft.import_csv.domain;


import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class CallDetail {
    private String callerId;
    private String recipient;
    private String callDate;
    private String endTime;
    private String duration;
    private String cost;
    private String reference;
    private String currency;
    private String type;
}
