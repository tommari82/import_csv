package cz.tmsoft.import_csv.api.v1;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UploadFileResponseRest {

    @ApiModelProperty(notes = "Name of imported file")
    private String fileName;
    @ApiModelProperty(notes = "file type of imported file")
    private String fileType;
    @ApiModelProperty(notes = "File size of imported file")
    private long size;
}

