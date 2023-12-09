show databases ;
create database sci character set utf8mb4;
use sci;
drop table if exists lab;
create table `lab`(
    `lab_id` int unsigned not null unique auto_increment comment '研究室id',
    `name` varchar(20) not null unique comment '研究室名',
    `introduction` varchar(50) comment '研究方向介绍',
    `director_id` int unique comment '主任作为科研人员的id',
    `secretary_id` int comment '秘书id',
    PRIMARY KEY (`lab_id`) USING BTREE
);

drop table if exists office;
create table `office`(
    `office_id` int unsigned not null unique auto_increment comment '办公场地id',
    `area` double unsigned comment '面积',
    `address` varchar(50) comment '地址',
    PRIMARY KEY (`office_id`) USING BTREE
);

drop table if exists lab_office;
create table `lab_office`(
    `id` int unsigned not null unique auto_increment comment 'id',
    PRIMARY KEY (id) ,
    `lab_id` int comment '研究所id',
    `office_id` int comment '办公场地id'
);

drop table if exists researcher;
create table `researcher`(
    `researcher_id` int unsigned not null unique auto_increment comment '科研人员工号',
    `lab_id` int comment '所属研究室id',
    `sub_project_id` int comment '在项目中的子课题id',
    `name` varchar(10) comment '姓名',
    `gender` boolean comment '性别' ,-- 0为男，1为女
    `age` int unsigned comment '年龄',
    `profession_grade` varchar(30) comment '职称',
    `research_direction` varchar(50) comment '研究方向',
    PRIMARY KEY (`researcher_id`) USING BTREE
);

drop table if exists secretary;
create table secretary(
    `secretary_id` int unsigned not null unique auto_increment comment '秘书工号',
    `name` varchar(10) comment '姓名',
    `gender` boolean comment '性别',-- 0为男，1为女
    `age` int unsigned comment '年龄',
    `employ_date` date comment '聘用时间',
    `work` varchar(30) comment '职责',
    PRIMARY KEY (`secretary_id`) USING BTREE
);

drop table if exists sci_project;
create table sci_project(
    `sci_project_id` int unsigned not null unique auto_increment comment '项目号',
    `client_id` int comment '委托方id',
    `testing_id` int comment '检测方id',
    `head_name` varchar(10) comment '项目负责人名',
    `name` varchar(20) comment '项目名',
    `content` varchar(50) comment '研究内容',
    `fund` double comment '经费总额',
    `start_date` date comment '开工时间',
    `end_date` date comment '完成时间',
    PRIMARY KEY (`sci_project_id`) USING BTREE
);

drop table if exists sub_project;
create table sub_project(
    `sub_project_id` int unsigned not null unique auto_increment comment '子课题id',
    `head_name` varchar(10) comment '子课题负责人名',
    `sequence_id` int comment '序号',
    `deadline` date comment '完成时间要求',
    `fund` double comment '可支配经费',
    PRIMARY KEY (sub_project_id) USING BTREE
);

drop table if exists researcher_sci_project_record;
create table researcher_sci_project_record(
    `record_id` int unsigned not null unique auto_increment comment '记录id',
    `sci_project_id` int comment '科研项目id',
    `researcher_id` int comment '研究人员id',
    `start_date` date comment '参加时间',
    `workload` varchar(10) comment '工作量',
    `fund` double comment '可支配经费',
    PRIMARY KEY (record_id) USING BTREE
);

drop table if exists sci_project_sub_project;
create table sci_project_sub_project(
    `id` int unsigned not null unique auto_increment comment 'id',
    PRIMARY KEY (id) ,
    `sci_project_id` int comment '科研项目id',
    `sub_project_id` int comment '子课题id'
);

drop table if exists payoffs;
create table payoffs(
    `payoff_id` int unsigned not null unique auto_increment comment '科研成果id',
    `type` varchar(10) comment '成果类型',
    `name` varchar(20) comment '成果名',
    `date` date comment '取得时间',
    `rank` int comment '排名',
    PRIMARY KEY (payoff_id) USING BTREE
);

drop table if exists sci_project_payoffs;
create table sci_project_payoffs(
    `id` int unsigned not null unique auto_increment comment 'id',
    PRIMARY KEY (id) ,
    `sci_project_id` int comment '科研项目id',
    `payoff_id` int comment '科研成果id'
);

drop table if exists sci_project_contributor;
create table sci_project_contributor(
    `id` int unsigned not null unique auto_increment comment 'id',
    PRIMARY KEY (id) ,
    `sci_project_id` int comment '科研项目id',
    `contributor_id` int comment '成果贡献人id，实际为科研人员id'
);

drop table if exists client;
create table client(
    `client_id` int unsigned not null unique auto_increment comment '委托方id',
    `name` varchar(50) comment '委托单位名',
    `address` varchar(50) comment '单位地址',
    `head_id` int comment '负责人id',
    PRIMARY KEY (client_id) USING BTREE
);

drop table if exists partner;
create table partner(
    `partner_id` int unsigned not null unique auto_increment comment '合作方id',
    `name` varchar(50) comment '合作单位名',
    `address` varchar(50) comment '单位地址',
    `head_id` int comment '负责人id',
    PRIMARY KEY (partner_id) USING BTREE
);

drop table if exists testing;
create table testing(
    `testing_id` int unsigned not null unique auto_increment comment '检测方id',
    `name` varchar(50) comment '检测单位名',
    `address` varchar(50) comment '单位地址',
    `head_id` int comment '负责人id',
    PRIMARY KEY (testing_id) USING BTREE
);

drop table if exists heads;
create table heads(
    `head_id` int unsigned not null unique auto_increment comment '负责人id',
    `name` varchar(10) comment '姓名',
    `office_number` bigint comment '办公电话',
    `mobile_number` bigint comment '移动电话',
    `email_address` varchar(30) comment '邮箱',
    PRIMARY KEY (head_id) USING BTREE
);

drop table if exists links;
create table links(
    `link_id` int unsigned not null unique auto_increment comment '联系人id',
    `name` varchar(10) comment '姓名',
    `office_number` bigint comment '办公电话',
    `mobile_number` bigint comment '移动电话',
    `email_address` varchar(30) comment '邮箱',
    PRIMARY KEY (link_id) USING BTREE
);

drop table if exists client_link;
create table client_link(
    `id` int unsigned not null unique auto_increment comment 'id',
    PRIMARY KEY (id) ,
    `client_id` int comment '委托方id',
    `link_id` int comment '联系人id'
);

drop table if exists partner_link;
create table partner_link(
    `id` int unsigned not null unique auto_increment comment 'id',
    PRIMARY KEY (id) ,
    `client_id` int comment '合作方id',
    `link_id` int comment '联系人id'
);

drop table if exists testing_link;
create table testing_link(
    `id` int unsigned not null unique auto_increment comment 'id',
    PRIMARY KEY (id) ,
    `client_id` int comment '检测方id',
    `link_id` int comment '联系人id'
);

drop table if exists user;
create table user(
    `user_id` int unsigned not null unique auto_increment comment '用户id',
    PRIMARY KEY (user_id),
    `name` varchar(20) comment '用户名',
    `phone_number` bigint not null comment '手机号',
    `password` varchar(20) not null comment '密码'
);