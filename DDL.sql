create database regdemo;
create user 'regdemouser_rw'@'%' identified by '123456';
grant all privileges on regdemo.* to 'regdemouser_rw'@'%';
use regdemo;
drop table if exists user_info;
create table user_info (
id int auto_increment primary key ,
name varchar(30) not null,
mobile varchar(20) not null,
email varchar(30) not null,
password varchar(30) not null,
lisenceFilePath varchar(255) not null comment "证件附件绝对路径",
passReviewed int not null default 0 comment "0-待审核，1-通过，-1-不通过",
create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
update_time timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
is_delete int not null DEFAULT 0 comment "软删除标志。0-未删除，1-已删除"
) comment '用户基本信息表';

