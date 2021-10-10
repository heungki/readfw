-- 고객정보
create table customerInfo ( customerNum varchar(255) not null,
	name varchar(255),
	primary key(customerNum)
);

-- 계좌정보
create table accountInfo ( accountNum varchar(255) not null,
	customernum varchar(255),
	balance varchar(255),
	primary key(accountNum)
);

-- 거래내역
create table transaction ( trankey varchar(255) not null,
	accountnum varchar(255),
	trantime datetime(0),
	trantype varchar(1),	
	amount varchar(255),
	sender varchar(255),
	primary key(trankey)
);
	
-- 로그
create table logdata ( log_id varchar(255) not null,
	url varchar(255),
	trx_datetime varchar(255),
	log_datetime datetime(0),
    guid varchar(31),
    request_type varchar(1),
    response_type varchar(2),
    bizdata clob,
    process_time varchar(10),
    PRIMARY KEY (log_id)
);
