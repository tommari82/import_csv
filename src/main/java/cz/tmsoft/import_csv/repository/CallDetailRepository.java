package cz.tmsoft.import_csv.repository;

import cz.tmsoft.import_csv.domain.CallCount;
import cz.tmsoft.import_csv.domain.CallDetailRecord;
import cz.tmsoft.import_csv.domain.CallerFilter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CallDetailRepository {

    @Select("select * from call_detail where reference = #{reference}")
    CallDetailRecord getCallRecordByReference(String reference);

    CallCount getCountOfCalls(CallerFilter filter);

    List<CallDetailRecord> getCallDetailForCaller(CallerFilter filter);
}
