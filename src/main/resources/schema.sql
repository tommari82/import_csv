create TABLE call_detail
(
    ID              BIGINT NOT NULL AUTO_INCREMENT,
    caller_id       varchar(50),
    recipient       varchar(50),
    call_date       date,
    end_time        time,
    duration        BIGINT,
    cost            decimal(18, 3),
    reference       varchar(50),
    currency        varchar(3),
    type            varchar(1),
    start_date_time timestamp,
    end_date_time   timestamp,
    PRIMARY KEY (`ID`)
);