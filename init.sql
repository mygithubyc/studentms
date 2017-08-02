create table users(
usid int primary key,
username varchar2(16) not null,
passowrd varchar2(32) not null,
name varchar2(12),
sex varchar2(2) check(sex='1'or sex='2'),
phonenum varchar2(11),
email varchar2(24),
usertype varchar2(2) check(usertype in('1','2','3')))

create sequence users_seq
minvalue 1
nomaxvalue
start with 1
increment by 1
nocycle
nocache;

create or replace trigger users_tri
before insert on users for each row when(new.usid is null)
begin
select users_seq.nextval into:new.usid from dual;
end;

SELECT * from (select A.*,ROWNUM RN from(select * from job WHERE USERNAME LIKE '%ad%' AND SENDTIME BETWEEN TO_DATE('2017-01-01', 'yyyy-mm-dd') and TO_DATE('2017-08-15', 'yyyy-mm-dd') ORDER BY JID ASC)A where ROWNUM <=2)WHERE RN >0;

create table job(
jid number(8) primary key,
title varchar2(80) not null,
content varchar2(500) not null,
sendtime date not null,
username varchar2(16) not null,
path varchar2(120),
deadtime date not null);

create sequence job_seq
minvalue 1
nomaxvalue
start with 1
increment by 1
nocycle
nocache;

create or replace trigger job_tri
before insert on job for each row when(new.jid is null)
begin
select job_seq.nextval into:new.jid from dual;
end;

INSERT into job(title,content,sendtime,username,path,deadtime) VALUES ('test','test content',TO_DATE('2017-08-01', 'yyyy-mm-dd'),'admin1','xxx.rar',TO_DATE('2017-08-02', 'yyyy-mm-dd'))

create table comjob(
cid number(8) primary key,
username varchar2(16) not null,
uploadtime date,
path varchar2(120) not null,
jid number(8) not null,
struts varchar2(2) not null check(struts='0' or struts='1'));

create sequence comjob_seq
minvalue 1
nomaxvalue
start with 1
increment by 1
nocycle
nocache;

create or replace trigger comjob_tri
before insert on comjob for each row when(new.cid is null)
begin
select comjob_seq.nextval into:new.cid from dual;
end;

INSERT into comjob(username,uploadtime,path,jid,struts) VALUES('xiaoming',TO_DATE('2017-08-01', 'yyyy-mm-dd'),'xxx.rar',1,'1');











