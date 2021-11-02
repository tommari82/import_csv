package cz.tmsoft.import_csv.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CallerFilter {
    public String callerId;
    public LocalDateTime startTime;
    public LocalDateTime endTime;
    public String type;
    public Long count;
}
