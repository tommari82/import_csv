package cz.tmsoft.import_csv.mapper;

import cz.tmsoft.import_csv.api.v1.CallCountRest;
import cz.tmsoft.import_csv.api.v1.CallDetailRecordRest;
import cz.tmsoft.import_csv.api.v1.CallFilterRest;
import cz.tmsoft.import_csv.domain.CallCount;
import cz.tmsoft.import_csv.domain.CallDetailRecord;
import cz.tmsoft.import_csv.domain.CallerFilter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CallDetailMapper {

    CallDetailMapper INSTANCE = Mappers.getMapper(CallDetailMapper.class);

    CallDetailRecordRest map(CallDetailRecord callRecordByReference);

    CallCountRest map(CallCount countOfCall);


    @Mappings({
            @Mapping(target = "startTime", dateFormat = "dd.MM.yyyy HH:mm:ss"),
            @Mapping(target = "endTime", dateFormat = "dd.MM.yyyy HH:mm:ss"),
    })
    CallerFilter map(CallFilterRest callFilterRest);

    List<CallDetailRecordRest> map(List<CallDetailRecord> callDetailForCaller);
}
