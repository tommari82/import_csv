package cz.tmsoft.import_csv.service;

import cz.tmsoft.import_csv.domain.CallCount;
import cz.tmsoft.import_csv.domain.CallCountFilter;
import cz.tmsoft.import_csv.domain.CallDetailRecord;
import cz.tmsoft.import_csv.domain.CallerFilter;
import cz.tmsoft.import_csv.repository.CallDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CallDetailService {

    @Autowired
    private CallDetailRepository callDetailRepository;

    public CallDetailRecord getCallRecordByReference(String reference) {
        return callDetailRepository.getCallRecordByReference(reference);
    }

    public CallCount getCountOfCall(CallerFilter filter) {
        return callDetailRepository.getCountOfCalls(filter);
    }

    public List<CallDetailRecord> getCallDetailForCaller(CallerFilter filter){
        return callDetailRepository.getCallDetailForCaller(filter);
    }
}
