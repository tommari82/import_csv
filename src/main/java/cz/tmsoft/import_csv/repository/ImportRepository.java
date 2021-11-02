package cz.tmsoft.import_csv.repository;

import cz.tmsoft.import_csv.domain.CallDetailRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ImportRepository {

     @Insert("INSERT INTO call_detail (caller_id, recipient, call_date, end_time, duration, cost, reference, currency, type, start_date_time, end_date_time)\n" +
             "values (#{callerId}, #{recipient}, #{callDate}, #{endTime}, #{duration}, #{cost}, #{reference}, #{currency}, #{type}, #{startDateTime}, #{endDateTime})")
     void saveCallDetailRecord(CallDetailRecord callDetailRecord);
}
