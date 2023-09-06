create database JDBC_exercise;
use JDBC_exercise;

create table group_singer(
	mem_id char(8) not null primary key,  -- 가수 ID(PK)
    mem_name varchar(10) not null, 	-- 가수 이름
    mem_number int not null,  -- 인원 수
    addr varchar(2) not null,  -- 주소(2글자 입력)
    phone varchar(11),  -- 전화번호(하이픈 제외)
    height smallint,  -- 평균 키
    debut_date date  -- 데뷔
);

create table buy_history(
	buy_id int not null auto_increment primary key,  -- 순번(PK)
	mem_id char(8) not null,  -- ID(FK)
    prod_name char(6) not null,  -- 제품 이름
    group_name char(4),  -- 분류
    price int not null,  -- 단가
    amount smallint not null,  -- 수량
    
    constraint mem_id_fk foreign key(mem_id) references group_singer(mem_id)
    -- FOREIGN KEY (mem_id) REFERENCES group_singer(mem_id)
);

