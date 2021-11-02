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
import org.springframework.http.MediaType;
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
    public CallDetailRecordRest getCallRecordByReference(@PathVariable String reference) {
        return CallDetailMapper.INSTANCE.map(callDetailService.getCallRecordByReference(reference));
    }

    @PostMapping("/")
    @ApiOperation(value = "Retrieve a count and total duration of all calls in a specified time period.", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public CallCountRest getCountOfCall(@Valid @RequestBody CallFilterRest callCountFilterRest) {
        CallerFilter filter = CallDetailMapper.INSTANCE.map(callCountFilterRest);

        return CallDetailMapper.INSTANCE.map(callDetailService.getCountOfCall(filter));
    }

    @PostMapping("/{callerId}")
    @ApiOperation(value = "Retrieve all CDRs for a specific Caller ID in a specified time period. ", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<CallDetailRecordRest> getCallDetailForCaller(@PathVariable String callerId, @Valid @RequestBody CallFilterRest callFilterRest) {
        CallerFilter filter = CallDetailMapper.INSTANCE.map(callFilterRest);
        filter.setCallerId(callerId);

        List<CallDetailRecord> callDetailForCaller = callDetailService.getCallDetailForCaller(filter);
        return CallDetailMapper.INSTANCE.map(callDetailForCaller);
    }

    @PostMapping("/{callerId}/{count}")
    @ApiOperation(value = "Retrieve N most expensive calls, in GBP, for a specific Caller ID in a specified time period . ", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<CallDetailRecordRest> getCallDetailForCaller(@PathVariable String callerId, @PathVariable Long count, @Valid @RequestBody CallFilterRest callFilterRest) {
        CallerFilter filter = CallDetailMapper.INSTANCE.map(callFilterRest);
        filter.setCallerId(callerId);
        filter.setCount(count);

        List<CallDetailRecord> callDetailForCaller = callDetailService.getCallDetailForCaller(filter);
        return CallDetailMapper.INSTANCE.map(callDetailForCaller);
    }

}
