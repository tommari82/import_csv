package cz.tmsoft.import_csv.controller.v1;

import cz.tmsoft.import_csv.api.v1.CallCountRest;
import cz.tmsoft.import_csv.api.v1.CallDetailRecordRest;
import cz.tmsoft.import_csv.api.v1.CallFilterRest;
import cz.tmsoft.import_csv.domain.CallDetailRecord;
import cz.tmsoft.import_csv.domain.CallerFilter;
import cz.tmsoft.import_csv.mapper.CallDetailMapper;
import cz.tmsoft.import_csv.service.CallDetailService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/call")
public class CallDetailController {

    @Autowired
    private CallDetailService callDetailService;

    @GetMapping("/{reference}")
    @ApiOperation(value = "Retrieve individual CDR by the CDR Reference", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CallDetailRecordRest> getCallRecordByReference(@PathVariable String reference) {
        CallDetailRecord callRecordByReference = callDetailService.getCallRecordByReference(reference);
        if (callRecordByReference == null) {
            return new ResponseEntity("Not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(CallDetailMapper.INSTANCE.map(callRecordByReference));
    }

    @GetMapping("/")
    @ApiOperation(value = "Retrieve a count and total duration of all calls in a specified time period.", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public CallCountRest getCountOfCall(@Valid CallFilterRest callCountFilterRest) {
        CallerFilter filter = CallDetailMapper.INSTANCE.map(callCountFilterRest);

        return CallDetailMapper.INSTANCE.map(callDetailService.getCountOfCall(filter));
    }

    @GetMapping("/caller/{callerId}")
    @ApiOperation(value = "Retrieve all CDRs for a specific Caller ID in a specified time period. ", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<CallDetailRecordRest> getCallDetailForCaller(@PathVariable String callerId, @Valid CallFilterRest callFilterRest) {
        CallerFilter filter = CallDetailMapper.INSTANCE.map(callFilterRest);
        filter.setCallerId(callerId);

        List<CallDetailRecord> callDetailForCaller = callDetailService.getCallDetailForCaller(filter);
        return CallDetailMapper.INSTANCE.map(callDetailForCaller);
    }

    @GetMapping("/caller/{callerId}/{count}")
    @ApiOperation(value = "Retrieve N most expensive calls, in GBP, for a specific Caller ID in a specified time period . ", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<CallDetailRecordRest> getCallDetailForCaller(@PathVariable String callerId, @PathVariable Long count, @Valid CallFilterRest callFilterRest) {
        CallerFilter filter = CallDetailMapper.INSTANCE.map(callFilterRest);
        filter.setCallerId(callerId);
        filter.setCount(count);

        List<CallDetailRecord> callDetailForCaller = callDetailService.getCallDetailForCaller(filter);
        return CallDetailMapper.INSTANCE.map(callDetailForCaller);
    }

}
