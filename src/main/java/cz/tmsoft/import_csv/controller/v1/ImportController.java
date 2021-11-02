package cz.tmsoft.import_csv.controller.v1;

import cz.tmsoft.import_csv.api.v1.UploadFileResponseApi;
import cz.tmsoft.import_csv.service.ImportServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/import")
public class ImportController {

    @Autowired
    ImportServiceImpl importService;

    @PostMapping(value = "/uploadFile")
    @ApiOperation(value = "Make a POST request to upload the file, media type must be text/csv", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 415, message = "Unsupported media type")
    })
    public ResponseEntity<UploadFileResponseApi> uploadFile(@RequestParam("file") MultipartFile file) {

        if (file.getContentType().toLowerCase().compareTo("text/csv") != 0) {
            return new ResponseEntity("Unsupported media type", HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        }

        importService.importFile(file);

        return ResponseEntity.status(HttpStatus.OK).body(UploadFileResponseApi.builder().fileName(file.getName()).fileType(file.getContentType()).size(file.getSize()).build());
    }
}
