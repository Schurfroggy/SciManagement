/*show databases ;
create database sci character set utf8mb4;
use sci;*/

drop table if exists lab;
create table `lab`(
                      `lab_id` int unsigned not null unique auto_increment comment '研究室id',
                      `name` varchar(20) not null comment '研究室名',
                      `introduction` varchar(50) comment '研究方向介绍',
                      `director_id` int unique comment '主任id',
                      `secretary_id` int comment '秘书id',
                      PRIMARY KEY (`lab_id`) USING BTREE
);

insert into lab value(1, '西南联大物理实验室', 'science', 1, 2);
insert into lab value(2, '华南理工大学光学实验室', 'science', 5, 1);
insert into lab value(3, '未知实验室', 'science', 2, 3);
insert into lab value(4, '赛博坦实验室', 'science', 4, 4);
insert into lab value(5, '未知实验室2', 'science', 3, 5);

drop table if exists office;
create table `office`(
                         `office_id` int unsigned not null unique auto_increment comment '办公场地id',
                         `area` double unsigned comment '面积',
                         `address` varchar(50) comment '地址',
                         PRIMARY KEY (`office_id`) USING BTREE
);

insert into office value (1,102.5,'Chaos 042');
insert into office value (2,88.2,'广东省广州市番禺区华南理工大学大学城校区A2-401');
insert into office value (3,90.5,'广东省广州市番禺区华南理工大学大学城校区A3-502');
insert into office value (4,524.10,'未知');
insert into office value (5,42.2,'始祖星帕提卡园区B6栋2302室');
insert into office value (6,52.1,'始祖星帕提卡园区B7栋1001室');
insert into office value (7,243.1,'未知');
insert into office value (8,1120.2,'未知');

drop table if exists lab_office;
create table `lab_office`(
                             `id` int unsigned not null unique auto_increment comment 'id',
                             PRIMARY KEY (id) ,
                             `lab_id` int comment '研究所id',
                             `office_id` int comment '办公场地id'
);

insert into lab_office value (1,1,1);
insert into lab_office value (2,2,2);
insert into lab_office value (3,2,3);
insert into lab_office value (4,3,4);
insert into lab_office value (5,4,5);
insert into lab_office value (6,4,6);
insert into lab_office value (7,5,7);
insert into lab_office value (8,5,8);

drop table if exists researcher;
create table `researcher`(
                             `researcher_id` int unsigned not null unique auto_increment comment '科研人员工号',
                             `lab_id` int comment '所属研究室id',
                             `sub_project_id` int comment '在项目中的子课题id',
                             `name` varchar(10) comment '姓名',
                             `gender` varchar(5) comment '性别' ,
                             `age` int unsigned comment '年龄',
                             `profession_grade` varchar(30) comment '职称',
                             `research_direction` varchar(50) comment '研究方向',
                             PRIMARY KEY (`researcher_id`) USING BTREE
);

insert into researcher value (1,1,1,'杰克','男',22,'实习研究员','未知');
insert into researcher value (2,2,1,'岩雀','女',30,'教授','未知');
insert into researcher value (3,1,1,'吕奉先','男',21,'实习研究员','未知');
insert into researcher value (4,5,1,'高尔夫','男',45,'教授','未知');
insert into researcher value (5,4,1,'本杰明','男',39,'副教授','未知');
insert into researcher value (6,3,1,'艾利','女',22,'助理研究员','未知');
insert into researcher value (7,3,1,'程心','女',28,'教授','未知');
insert into researcher value (8,3,1,'金克斯','女',22,'实习研究员','未知');
insert into researcher value (9,3,1,'沃德','男',24,'实习研究员','未知');
insert into researcher value (10,4,1,'拉姆','女',23,'副研究员','未知');
insert into researcher value (11,1,1,'音符','女',35,'副教授','未知');
insert into researcher value (12,5,1,'青蛙','男',20,'实习研究员','未知');

drop table if exists secretary;
create table secretary(
                          `secretary_id` int unsigned not null unique auto_increment comment '秘书工号',
                          `lab_id` int comment '所属lab id',
                          `name` varchar(10) comment '姓名',
                          `gender` varchar(2) comment '性别',
                          `age` int unsigned comment '年龄',
                          `employ_date` date comment '聘用时间',
                          `work` varchar(30) comment '职责',
                          PRIMARY KEY (`secretary_id`) USING BTREE
);

insert into secretary value (1,2,'陈友谅','男',35,'2012-10-25','文件管理与简单业务');
insert into secretary value (2,1,'程欣','女',24,'2018-7-6','文件管理');
insert into secretary value (3,3,'Aril','女',29,'2017-4-11','实验记录');
insert into secretary value (4,4,'王后雄','男',40,'2009-1-2','项目对接与业务交流');
insert into secretary value (5,5,'李姐','女',31,'2023-12-7','业务交流');

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

insert into sci_project value (1,1,1,'华佗','Git Star','基于star的git实现',200000.0,'2023-10-15','2029-10-15');
insert into sci_project value (2,2,2,'李明','Git Lab','基于skt的gitlab实现',197600.0,'2021-7-5','2031-7-5');
insert into sci_project value (3,3,3,'王小明','Ku-py','基于python的ML实现',8000000.0,'2018-1-1','2038-1-1');
insert into sci_project value (4,4,4,'联邦','Unite','光的金属放射特性测验',55000.00,'2017-4-1','2019-4-1');
insert into sci_project value (5,5,5,'托德','Wheel','EV电动车中的轮轴特性及其改造',1940000,'2020-12-8','2023-12-8');

drop table if exists sub_project;
create table sub_project(
                            `sub_project_id` int unsigned not null unique auto_increment comment '子课题id',
                            `head_name` varchar(10) comment '子课题负责人名',
                            `sequence_id` int comment '序号',
                            `deadline` date comment '完成时间要求',
                            `fund` double comment '可支配经费',
                            `tech_indicator` varchar(50) comment '技术指标',
                            PRIMARY KEY (sub_project_id) USING BTREE
);

insert into sub_project value (1,'杰克',1,'2023-10-25',10000.0,'A');
insert into sub_project value (2,'岩雀',2,'2023-10-25',10000.0,'A+');
insert into sub_project value (3,'吕奉先',1,'2023-10-25',10000.0,'A');
insert into sub_project value (4,'高尔夫',2,'2023-10-25',10000.0,'A');
insert into sub_project value (5,'本杰明',1,'2023-10-25',10000.0,'A-');
insert into sub_project value (6,'艾利',2,'2023-10-25',10000.0,'A');
insert into sub_project value (7,'程心',1,'2023-10-25',10000.0,'A');
insert into sub_project value (8,'金克斯',2,'2023-10-25',10000.0,'A+');
insert into sub_project value (9,'沃德',3,'2023-10-25',10000.0,'A');
insert into sub_project value (10,'拉姆',1,'2023-10-25',10000.0,'A');
insert into sub_project value (11,'音符',2,'2023-10-25',10000.0,'A+');
insert into sub_project value (12,'青蛙',3,'2023-10-25',10000.0,'A');

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

insert into researcher_sci_project_record value (1,1,1,'2023-10-11','500h',20000.0);
insert into researcher_sci_project_record value (2,1,2,'2023-10-15','501h',19999.0);
insert into researcher_sci_project_record value (3,2,3,'2021-7-5','502h',19998.0);
insert into researcher_sci_project_record value (4,2,4,'2020-7-6','503h',19997.0);
insert into researcher_sci_project_record value (5,3,5,'2018-1-2','504h',19996.0);
insert into researcher_sci_project_record value (6,3,6,'2018-2-1','505h',19995.0);
insert into researcher_sci_project_record value (7,4,7,'2017-7-1','506h',19994.0);
insert into researcher_sci_project_record value (8,4,8,'2017-4-8','507h',19993.0);
insert into researcher_sci_project_record value (9,4,9,'2017-4-1','508h',19992.0);
insert into researcher_sci_project_record value (10,5,10,'2020-12-10','509h',19991.0);
insert into researcher_sci_project_record value (11,5,11,'2020-12-11','510h',19990.0);
insert into researcher_sci_project_record value (12,5,12,'2020-12-8','511h',19989.0);

drop table if exists sci_project_sub_project;
create table sci_project_sub_project(
                                        `id` int unsigned not null unique auto_increment comment 'id',
                                        PRIMARY KEY (id) ,
                                        `sci_project_id` int comment '科研项目id',
                                        `sub_project_id` int comment '子课题id'
);

insert into sci_project_sub_project value (1,1,1);
insert into sci_project_sub_project value (2,1,2);
insert into sci_project_sub_project value (3,2,3);
insert into sci_project_sub_project value (4,2,4);
insert into sci_project_sub_project value (5,3,5);
insert into sci_project_sub_project value (6,3,6);
insert into sci_project_sub_project value (7,4,7);
insert into sci_project_sub_project value (8,4,8);
insert into sci_project_sub_project value (9,4,9);
insert into sci_project_sub_project value (10,5,10);
insert into sci_project_sub_project value (11,5,11);
insert into sci_project_sub_project value (12,5,12);

drop table if exists payoffs;
create table payoffs(
                        `payoff_id` int unsigned not null unique auto_increment comment '科研成果id',
                        `type` varchar(10) comment '成果类型',-- 分为专利，论文和软件著作权，专利分为发明、实用新型、外观三种
                        `name` varchar(20) comment '成果名',
                        `date` date comment '取得时间',
                        `rank` int comment '排名',
                        PRIMARY KEY (payoff_id) USING BTREE
);

insert into payoffs value (1,'论文','光的金属放射性结论','2020-4-1',2);
insert into payoffs value (2,'实用新型专利','光的金属放射性结论','2020-4-1',1);
insert into payoffs value (3,'外观专利','EV轴承设计','2023-12-8',5);
insert into payoffs value (4,'软件著作权','EV智慧物联网软件设计模式','2023-12-8',4);
insert into payoffs value (5,'发明专利','EV轴承设计','2023-12-8',1);

drop table if exists sci_project_payoffs;
create table sci_project_payoffs(
                                    `id` int unsigned not null unique auto_increment comment 'id',
                                    PRIMARY KEY (id) ,
                                    `sci_project_id` int comment '科研项目id',
                                    `payoff_id` int comment '科研成果id'
);

insert into sci_project_payoffs value (1,4,1);
insert into sci_project_payoffs value (2,4,2);
insert into sci_project_payoffs value (3,5,3);
insert into sci_project_payoffs value (4,5,4);
insert into sci_project_payoffs value (5,5,5);

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

insert into client value (1,'ASh.loc','Unknown',1);
insert into client value (2,'华南光电','华南理工大学光电实验项目组B1',2);
insert into client value (3,'Unknown','Unknown',3);
insert into client value (4,'赛博','始祖星乐景花园A2-104',4);
insert into client value (5,'Com.cn','Rot-street Block 1',5);

drop table if exists partner;
create table partner(
                        `partner_id` int unsigned not null unique auto_increment comment '合作方id',
                        `name` varchar(50) comment '合作单位名',
                        `address` varchar(50) comment '单位地址',
                        `head_id` int comment '负责人id',
                        PRIMARY KEY (partner_id) USING BTREE
);

insert into partner value(1,'ASh.loc','Unknown',6);
insert into partner value(2,'Res','Unknown',7);
insert into partner value(3,'Ffs','Unknown',8);
insert into partner value(4,'qqr','Unknown',9);
insert into partner value(5,'dfc','Unknown',10);
insert into partner value(6,'ASh.loc','Unknown',11);
insert into partner value(7,'ASh','quit',12);

drop table if exists testing;
create table testing(
                        `testing_id` int unsigned not null unique auto_increment comment '检测方id',
                        `name` varchar(50) comment '检测单位名',
                        `address` varchar(50) comment '单位地址',
                        `head_id` int comment '负责人id',
                        PRIMARY KEY (testing_id) USING BTREE
);

insert into testing value(1,'Tl1','Unknown',13);
insert into testing value(2,'TT','Bl-104',14);
insert into testing value(3,'TL2','Unknown',15);
insert into testing value(4,'TL3','Wheel 2',16);
insert into testing value(5,'STL','Unknown',17);

drop table if exists heads;
create table heads(
                      `head_id` int unsigned not null unique auto_increment comment '负责人id',
                      `name` varchar(10) comment '姓名',
                      `office_number` varchar(20) comment '办公电话',
                      `mobile_number` varchar(20) comment '移动电话',
                      `email_address` varchar(30) comment '邮箱',
                      PRIMARY KEY (head_id) USING BTREE
);

insert into heads value(1,'Aaa','020-1111 1111','13011111111','Aaa1301111@kk.com');
insert into heads value(2,'Bbb','020-2222 2222','13022222222','Bbb1302222@kk.com');
insert into heads value(3,'Ccc','020-3333 3333','13033333333','Ccc1303333@kk.com');
insert into heads value(4,'Ddd','020-4444 4444','13044444444','Ddd1304444@kk.com');
insert into heads value(5,'Eee','020-5555 5555','13055555555','Eee1305555@kk.com');
insert into heads value(6,'Fff','020-6666 6666','13066666666','Fff1306666@kk.com');
insert into heads value(7,'Ggg','020-7777 7777','13077777777','Ggg1307777@kk.com');
insert into heads value(8,'Hhh','020-8888 8888','13088888888','Hhh1308888@kk.com');
insert into heads value(9,'Iii','020-9999 9999','13099999999','Iii1309999@kk.com');
insert into heads value(10,'Jjj','020-0000 0000','13000000000','Jjj1300000@kk.com');
insert into heads value(11,'Kkk','020-1111 1111','13011111111','Kkk1301111@kk.com');
insert into heads value(12,'Lll','020-2222 2222','13022222222','Lll1302222@kk.com');
insert into heads value(13,'Mmm','020-3333 3333','13033333333','Mmm1303333@kk.com');
insert into heads value(14,'Nnn','020-4444 4444','13044444444','Nnn1304444@kk.com');
insert into heads value(15,'Ooo','020-5555 5555','13055555555','Ooo1305555@kk.com');
insert into heads value(16,'Ppp','020-6666 6666','13066666666','Ooo1306666@kk.com');
insert into heads value(17,'Qqq','020-7777 7777','13077777777','Ooo1307777@kk.com');
insert into heads value(18,'Rrr','020-8888 8888','13088888888','Ooo1308888@kk.com');

drop table if exists links;
create table links(
                      `link_id` int unsigned not null unique auto_increment comment '联系人id',
                      `name` varchar(10) comment '姓名',
                      `office_number` varchar(20) comment '办公电话',
                      `mobile_number` varchar(20) comment '移动电话',
                      `email_address` varchar(30) comment '邮箱',
                      PRIMARY KEY (link_id) USING BTREE
);

insert into links value (1,'aAA','0755-1111 1111','18611111111','aAA1861111@llt.com');
insert into links value (2,'bBB','0755-2222 2222','18622222222','bBB1862222@llt.com');
insert into links value (3,'cCC','0755-3333 3333','18633333333','cCC1863333@llt.com');
insert into links value (4,'dDD','0755-4444 4444','18644444444','dDD1864444@llt.com');
insert into links value (5,'eEE','0755-5555 5555','18655555555','eEE1865555@llt.com');
insert into links value (6,'fFF','0755-6666 6666','18666666666','fFF186666@llt.com');
insert into links value (7,'gGG','0755-7777 7777','18677777777','gGG1867777@llt.com');
insert into links value (8,'hHH','0755-8888 8888','18688888888','hHH1868888@llt.com');
insert into links value (9,'iII','0755-9999 9999','18699999999','iII1869999@llt.com');
insert into links value (10,'jJJ','0755-0000 0000','18600000000','jJJ1860000@llt.com');
insert into links value (11,'kKK','0755-1111 1111','18611111111','kKK1861111@llt.com');
insert into links value (12,'lLL','0755-2222 2222','18622222222','lLL1862222@llt.com');
insert into links value (13,'mMM','0755-3333 3333','18633333333','mMM1863333@llt.com');
insert into links value (14,'nNN','0755-4444 4444','18644444444','nNN1864444@llt.com');
insert into links value (15,'oOO','0755-5555 5555','18655555555','oOO1865555@llt.com');
insert into links value (16,'pPP','0755-6666 6666','18666666666','pPP186666@llt.com');
insert into links value (17,'qQQ','0755-7777 7777','18677777777','qQQ1867777@llt.com');
insert into links value (18,'rRR','0755-8888 8888','18688888888','rRR1868888@llt.com');
insert into links value (19,'sSS','0755-9999 9999','18699999999','sSS1869999@llt.com');
insert into links value (20,'tTT','0755-0000 0000','18600000000','tTT1860000@llt.com');
insert into links value (21,'uUU','0755-1111 1111','18611111111','uUU1861111@llt.com');
insert into links value (22,'vVV','0755-2222 2222','18622222222','vVV1862222@llt.com');
insert into links value (23,'wWW','0755-3333 3333','18633333333','wWW1863333@llt.com');
insert into links value (24,'xXX','0755-4444 4444','18644444444','xXX1864444@llt.com');
insert into links value (25,'yYY','0755-5555 5555','18655555555','yYY1865555@llt.com');

drop table if exists client_link;
create table client_link(
                            `id` int unsigned not null unique auto_increment comment 'id',
                            PRIMARY KEY (id) ,
                            `client_id` int comment '委托方id',
                            `link_id` int comment '联系人id'
);

insert into client_link value (1,1,1);
insert into client_link value (2,2,2);
insert into client_link value (3,2,3);
insert into client_link value (4,3,4);
insert into client_link value (5,4,5);
insert into client_link value (6,5,6);
insert into client_link value (7,5,7);

drop table if exists partner_link;
create table partner_link(
                             `id` int unsigned not null unique auto_increment comment 'id',
                             PRIMARY KEY (id) ,
                             `partner_id` int comment '合作方id',
                             `link_id` int comment '联系人id'
);

insert into partner_link value (1,1,8);
insert into partner_link value (2,2,9);
insert into partner_link value (3,3,10);
insert into partner_link value (4,4,11);
insert into partner_link value (5,5,12);
insert into partner_link value (6,6,13);
insert into partner_link value (7,7,14);
insert into partner_link value (8,5,15);
insert into partner_link value (9,5,16);


drop table if exists testing_link;
create table testing_link(
                             `id` int unsigned not null unique auto_increment comment 'id',
                             PRIMARY KEY (id) ,
                             `testing_id` int comment '检测方id',
                             `link_id` int comment '联系人id'
);

insert into testing_link value (1,1,17);
insert into testing_link value (2,1,18);
insert into testing_link value (3,2,19);
insert into testing_link value (4,3,20);
insert into testing_link value (5,4,21);
insert into testing_link value (6,4,22);
insert into testing_link value (7,5,23);

drop table if exists user;
create table user(
                     `user_id` int unsigned not null unique auto_increment comment '用户id',
                     PRIMARY KEY (user_id),
                     `phone` varchar(20) comment '电话号码',
                     `name` varchar(20) comment '用户名',
                     `password` varchar(20) not null comment '密码'
);

insert into user value(1,'13113631363','admin','admin');

drop table if exists director;
create table director(
                         `director_id` int unsigned not null unique auto_increment comment '主任id',
                         PRIMARY KEY (director_id),
                         `lab_id` int comment '实验室id',
                         `researcher_id` int comment '作为科研人员的id',
                         `date` DATE comment '上任时间',
                         `tenure` varchar(10) comment '任期'
);

insert into director value (1,1,11,'2019-10-11','4年');
insert into director value (2,3,7,'2004-9-8','20年');
insert into director value (3,2,2,'2023-12-8','1年');
insert into director value (4,4,5,'2017-1-2','终身');
insert into director value (5,5,4,'2019-8-1','3年');
insert into director value (6,3,8,'2024-1-1','2个月');

drop table if exists project2partner;
create table project2partner(
                                `id` int unsigned not null unique auto_increment comment 'id',
                                PRIMARY KEY (id),
                                `sci_project_id` int comment '项目id',
                                `partner_id` int comment '合作方id'
);

insert into project2partner value(1,1,1);
insert into project2partner value(2,2,2);
insert into project2partner value(3,2,3);
insert into project2partner value(4,3,4);
insert into project2partner value(5,4,5);
insert into project2partner value(6,4,6);
insert into project2partner value(7,5,7);