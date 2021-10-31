package cz.tmsoft.import_csv.service;


import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import cz.tmsoft.import_csv.domain.CallDetail;
import cz.tmsoft.import_csv.mapper.ImportMapper;
import cz.tmsoft.import_csv.repository.ImportRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value = "importService")
@Slf4j
public class ImportServiceImpl {

    @Autowired
    ImportRepository importRepository;

    public void importFile(MultipartFile file) {
        List<CallDetail> callDetailList = getCallDetails(file);

        for (CallDetail callDetail : callDetailList){
            log.info(callDetail.toString());
            importRepository.saveCallDetailRecord(ImportMapper.INSTANCE.map(callDetail));

        }
    }

    private List<CallDetail> getCallDetails(MultipartFile file) {
        Map<String, String> mapping = new HashMap<String, String>();
        //caller_id,recipient,call_date,end_time,duration,cost,reference,currency,type
        mapping.put("caller_id", "callerId");
        mapping.put("recipient", "recipient");
        mapping.put("call_date", "callDate");
        mapping.put("end_time", "endTime");
        mapping.put("duration", "duration");
        mapping.put("cost", "cost");
        mapping.put("reference", "reference");
        mapping.put("currency", "currency");
        mapping.put("type", "type");

        HeaderColumnNameTranslateMappingStrategy<CallDetail> strategy = new HeaderColumnNameTranslateMappingStrategy<CallDetail>();
        strategy.setType(CallDetail.class);
        strategy.setColumnMapping(mapping);

        CSVReader csvReader = null;
        try {
            csvReader = new CSVReader(new InputStreamReader(file.getInputStream()));
        } catch (FileNotFoundException e) {
            log.error("File not found", e);
        } catch (IOException e) {
            log.error("Error readen file", e);
        }
        CsvToBean csvToBean = new CsvToBean();

        List<CallDetail> retVal = csvToBean.parse(strategy, csvReader);

        return retVal;
    }
}
