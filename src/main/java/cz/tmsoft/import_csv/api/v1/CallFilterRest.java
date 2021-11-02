package cz.tmsoft.import_csv.api.v1;

import com.fasterxml.jackson.annotation.JsonFormat;
import cz.tmsoft.import_csv.validator.CustomTimeValid;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@CustomTimeValid
public class CallFilterRest {
    @ApiModelProperty(notes = "input start date time for filet, format  'dd.MM.yyyy HH:mm:ss'")
    @JsonFormat(pattern = "dd.MM.yyyy hh:mm:ss")
    @NotNull
    private String startTime;
    @ApiModelProperty(notes = "input end date time for filet, format  'dd.MM.yyyy HH:mm:ss'")
    @JsonFormat(pattern = "dd.MM.yyyy hh:mm:ss")
    @NotNull
    private String endTime;
    @ApiModelProperty(notes = "type of call, 1 = Domestic, 2 = International", required = false)
    private String type;
}
