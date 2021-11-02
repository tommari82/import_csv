package cz.tmsoft.import_csv.controller.v1;

import cz.tmsoft.import_csv.api.v1.CallCountRest;
import cz.tmsoft.import_csv.api.v1.CallDetailRecordRest;
import cz.tmsoft.import_csv.api.v1.CallFilterRest;
import cz.tmsoft.import_csv.api.v1.UploadFileResponseApi;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
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

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CallDetailControllerManualTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ResourceLoader resourceLoader = null;


    @BeforeAll
     void uploadData() throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(MediaType.MULTIPART_FORM_DATA_VALUE));

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", getTestFile());


        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        String serverUrl = "http://localhost:" + port + "/api/v1/import/uploadFile";
        ResponseEntity<UploadFileResponseApi> response = restTemplate.postForEntity(serverUrl, requestEntity, UploadFileResponseApi.class);

    }

    private Resource getTestFile() throws IOException {
        File dataFile = resourceLoader.getResource("classpath:data/techtest_cdr_dataset.csv").getFile();
        return new FileSystemResource(dataFile);
    }


    @Test
    void getCallRecordByReference() {
        String url = "http://localhost:" + port + "/api/v1/call/C0FAAB1E6424B20D1625FEAAD5936053E";

        ResponseEntity<CallDetailRecordRest> response = restTemplate.getForEntity(url, CallDetailRecordRest.class);

        assertTrue(response.getStatusCode() == HttpStatus.OK);
    }

    @Test
    void getCountOfCall() {

        String url = "http://localhost:" + port + "/api/v1/call/";
        CallFilterRest request = new CallFilterRest();
        request.setStartTime("01.08.2016 00:00:00");
        request.setEndTime("30.08.2016 00:00:00");

        ResponseEntity<CallCountRest> response = restTemplate.postForEntity(url, request, CallCountRest.class);

        assertTrue(response.getStatusCode() == HttpStatus.OK);
    }

    @Test
    void getCallDetailForCaller() {
        String url = "http://localhost:" + port + "/api/v1/call/441910000000";

        CallFilterRest request = new CallFilterRest();
        request.setStartTime("01.08.2016 00:00:00");
        request.setEndTime("30.08.2016 00:00:00");

        ResponseEntity<List> response = restTemplate.postForEntity(url, request, List.class);

        assertTrue(response.getStatusCode() == HttpStatus.OK);

    }

    @Test
    void testGetCallDetailForCaller() {

        String url = "http://localhost:" + port + "/api/v1/call/441910000000/2";

        CallFilterRest request = new CallFilterRest();
        request.setStartTime("01.08.2016 00:00:00");
        request.setEndTime("30.08.2016 00:00:00");

        ResponseEntity<List> response = restTemplate.postForEntity(url, request, List.class);

        assertTrue(response.getStatusCode() == HttpStatus.OK);
    }
}