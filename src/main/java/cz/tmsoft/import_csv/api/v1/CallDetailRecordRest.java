package cz.tmsoft.import_csv.api.v1;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CallDetailRecordRest {

    @ApiModelProperty(notes = "Phone number of the caller")
    private String callerId;
    @ApiModelProperty(notes = "Phone number of the recipient")
    private String recipient;
    @ApiModelProperty(notes = "Date on which the call was made")
    private String callDate;
    @ApiModelProperty(notes = "Time when the call ended")
    private String endTime;
    @ApiModelProperty(notes = "Duration of the cal in second")
    private Long duration;
    @ApiModelProperty(notes = "The billable cost of the call")
    private BigDecimal cost;
    @ApiModelProperty(notes = "Unique reference for the call")
    private String reference;
    @ApiModelProperty(notes = "Currency for the cost, ISO alpha-3")
    private String currency;
    @ApiModelProperty(notes = "Call Type, 1 = Domestic, 2 = International")
    private String type;
}
