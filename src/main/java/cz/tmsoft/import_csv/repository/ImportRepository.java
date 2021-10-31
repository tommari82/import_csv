package cz.tmsoft.import_csv.repository;

import cz.tmsoft.import_csv.domain.CallDetailRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ImportRepository {

     void saveCallDetailRecord(CallDetailRecord callDetailRecord);
}
