package cz.tmsoft.import_csv.mapper;


import cz.tmsoft.import_csv.domain.CallDetail;
import cz.tmsoft.import_csv.domain.CallDetailRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ImportMapper {

    ImportMapper INSTANCE = Mappers.getMapper(ImportMapper.class);

    @Mappings({
            @Mapping(target = "callDate", dateFormat = "dd/MM/yyyy"),
            @Mapping(target = "endTime", expression = "java(getLocalTime(callDetail.getEndTime()))")
    })
    CallDetailRecord map(CallDetail callDetail);

    default LocalTime getLocalTime(String localTime){
        return LocalTime.parse(localTime, DateTimeFormatter.ofPattern( "HH:mm:ss" ));
    }
}
