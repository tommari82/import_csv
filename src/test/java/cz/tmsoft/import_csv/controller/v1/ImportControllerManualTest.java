package cz.tmsoft.import_csv.controller.v1;

import cz.tmsoft.import_csv.api.v1.UploadFileResponseApi;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.validation.constraints.AssertTrue;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ImportControllerManualTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ResourceLoader resourceLoader = null;

    @Test
    void uploadFile() throws IOException {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(MediaType.MULTIPART_FORM_DATA_VALUE));

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", getTestFile());


        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        String serverUrl = "http://localhost:" + port + "/api/v1/import/uploadFile";
        ResponseEntity<UploadFileResponseApi> response = restTemplate.postForEntity(serverUrl, requestEntity, UploadFileResponseApi.class);
        assertTrue(response.getStatusCode() == HttpStatus.OK);
    }

    private Resource getTestFile() throws IOException {
        File dataFile = resourceLoader.getResource("classpath:data/techtest_cdr_dataset.csv").getFile();
        return new FileSystemResource(dataFile);
    }
}