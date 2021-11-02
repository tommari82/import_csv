package cz.tmsoft.import_csv.api.v1;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CallCountRest {
    @ApiModelProperty(notes = "Count of CDR")
    public Long count;
    @ApiModelProperty(notes = "Total duration for count of CDR")
    public Long duration;
}
