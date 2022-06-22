/*
SQLyog Ultimate v13.1.1 (64 bit)
MySQL - 5.7.35-log : Database - yeb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`yeb` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `yeb`;

/*Table structure for table `t_admin` */

DROP TABLE IF EXISTS `t_admin`;

CREATE TABLE `t_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(32) DEFAULT NULL COMMENT '姓名',
  `posName` varchar(100) DEFAULT NULL COMMENT '职位名字',
  `phone` char(11) DEFAULT NULL COMMENT '手机号码',
  `address` varchar(64) DEFAULT NULL COMMENT '联系地址',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `enabled` tinyint(1) DEFAULT '1' COMMENT '是否启用',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `posId` int(11) DEFAULT NULL COMMENT '职位id',
  `departmentId` int(11) DEFAULT NULL COMMENT '部门ID',
  `departmentName` varchar(100) DEFAULT NULL COMMENT '部门名称',
  `salaryId` int(11) DEFAULT NULL COMMENT '工资表ID',
  `userFace` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `parentId` bigint(20) DEFAULT NULL COMMENT '上司id',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `telephone` varchar(16) DEFAULT NULL COMMENT '住宅电话',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `t_admin_FK` (`posId`),
  KEY `t_admin_FK_1` (`salaryId`),
  KEY `t_admin_FK_2` (`departmentId`),
  CONSTRAINT `t_admin_FK` FOREIGN KEY (`posId`) REFERENCES `t_position` (`id`),
  CONSTRAINT `t_admin_FK_1` FOREIGN KEY (`salaryId`) REFERENCES `t_salary` (`id`),
  CONSTRAINT `t_admin_FK_2` FOREIGN KEY (`departmentId`) REFERENCES `t_department` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `t_admin` */

insert  into `t_admin`(`id`,`name`,`posName`,`phone`,`address`,`email`,`enabled`,`username`,`password`,`posId`,`departmentId`,`departmentName`,`salaryId`,`userFace`,`parentId`,`remark`,`telephone`) values 
(1,'系统管理员','系统管理员','18756693395','安徽省合肥市蜀山区锦绣大道莲花路1688号','1005@outlook.com',1,'admin','$2a$10$7v3KxY5S3wq5J0RCm1grmuNCCC7QLbJbkVAp4a7IJPwZIz0sgU/sy',6,1,'董事会',5,'http://47.97.120.165:80/group1/M00/00/00/L2F4pWIlX8-ARZACAACFph-uAUY33.jpeg',1,'系统管理员',''),
(2,'王二','总经理','18875971675','安徽省合肥市蜀山区锦绣大道莲花路1688号','1005@outlook.com',1,'wanger','$2a$10$kcptCkCWM0o5gUYmRXFCnukAdfydfVkSC0oF1JpTn3sjefBVqkhn.',6,3,'总办',5,'http://47.97.120.165:80/group1/M00/00/00/L2F4pWHVRrqAFotMAABrDYve1r4152.jpg',2,'总经理',''),
(3,'张三','财务部主管','14588110811','安徽省合肥市蜀山区锦绣大道莲花路1688号','1005@outlook.com',1,'zhangsan','$2a$10$oE39aG10kB/rFu2vQeCJTu/V/v4n6DRR0f8WyXRiAYvBpmadoOBE.',8,4,'财务部',4,'http://47.97.120.165:80/group1/M00/00/00/L2F4pWIlYFiAeLV6AABsgHWeXO036.jpeg',2,'财务部主管',''),
(4,'李四','市场部主管','15761248727','安徽省合肥市蜀山区锦绣大道莲花路1688号','1005@outlook.com',1,'lisi','$2a$10$oE39aG10kB/rFu2vQeCJTu/V/v4n6DRR0f8WyXRiAYvBpmadoOBE.',10,5,'市场部',1,'http://47.97.120.165:80/group1/M00/00/00/L2F4pWHChraABXBMAADNF4_r6Gw08.jpeg',2,'市场部主管',''),
(5,'姜五','研发部主管','18030710396','安徽省合肥市蜀山区锦绣大道莲花路1688号','1005@outlook.com',1,'jiangwu','$2a$10$oE39aG10kB/rFu2vQeCJTu/V/v4n6DRR0f8WyXRiAYvBpmadoOBE.',7,12,'研发部',3,'http://47.97.120.165:80/group1/M00/00/00/L2F4pWHPCKOAFkbiAAA5PvtaCZc17.jpeg',2,'研发部主管',''),
(6,'赵六','人事部主管','18756693395','安徽省合肥市蜀山区锦绣大道莲花路1688号','1005@outlook.com',1,'zhaoliu','$2a$10$oE39aG10kB/rFu2vQeCJTu/V/v4n6DRR0f8WyXRiAYvBpmadoOBE.',9,14,'人事部',2,'http://47.97.120.165:80/group1/M00/00/00/L2F4pWIlYECAbEl9AAESyTU-Voc68.jpeg',2,'人事部主管',''),
(7,'孙七','会计师','18756693395','安徽省合肥市蜀山区锦绣大道莲花路1688号','1005@outlook.com',1,'sunqi','$2a$10$oE39aG10kB/rFu2vQeCJTu/V/v4n6DRR0f8WyXRiAYvBpmadoOBE.',12,4,'财务部',4,'http://47.97.120.165:80/group1/M00/00/00/9ee181f9a7100efd8f25228b41767969.jpeg',3,'会计师',''),
(8,'陈八','人事专员','18756693395','安徽省合肥市蜀山区锦绣大道莲花路1688号','1005@outlook.com',1,'chenba','$2a$10$oE39aG10kB/rFu2vQeCJTu/V/v4n6DRR0f8WyXRiAYvBpmadoOBE.',14,14,'人事部',2,'http://47.97.120.165:80/group1/M00/00/00/ef2004b61e6e1752968e293232000d30.jpeg',6,'人事专员',''),
(9,'钱九','销售','18756693395','安徽省合肥市蜀山区锦绣大道莲花路1688号','1005@outlook.com',1,'qianjiu','$2a$10$oE39aG10kB/rFu2vQeCJTu/V/v4n6DRR0f8WyXRiAYvBpmadoOBE.',13,5,'市场部',1,'http://47.97.120.165:80/group1/M00/00/00/df23ac87f32a8f2097e864490ab48e07.jpeg',4,'销售',''),
(10,'肖十','程序员','18756693395','安徽省合肥市蜀山区锦绣大道莲花路1688号','1005@outlook.com',1,'xiaoshi','$2a$10$55XTJ9j.xP5eeDmKXHYKaeZ7TL/qvr1lvg1CYiHR6Z/IrSpX3zm2q',4,12,'研发部',3,'http://47.97.120.165:80/group1/M00/00/00/L2F4pWIlYAuAb8UsAADJQoYwUK470.jpeg',5,'程序员',''),
(32,'牛十一','人事专员','19867893456','安徽省合肥市蜀山区锦绣大道莲花路1688号','1005@outlook.com',1,'niushiyi','$2a$10$0UxP48w3W929vkgGemRn5.ve2jh4AL/F0on66GSnuOgxQEnMIxI7W',14,14,'人事部',2,'http://47.97.120.165:80/group1/M00/00/00/L2F4pWJTgcCADgZSAAFMTSIyYbw026.png',6,'无',NULL);

/*Table structure for table `t_admin_role` */

DROP TABLE IF EXISTS `t_admin_role`;

CREATE TABLE `t_admin_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `adminId` int(11) DEFAULT NULL COMMENT '用户id',
  `rid` int(11) DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `rid` (`rid`) USING BTREE,
  KEY `adminId` (`adminId`) USING BTREE,
  CONSTRAINT `t_admin_role_ibfk_1` FOREIGN KEY (`adminId`) REFERENCES `t_admin` (`id`) ON DELETE CASCADE,
  CONSTRAINT `t_admin_role_ibfk_2` FOREIGN KEY (`rid`) REFERENCES `t_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `t_admin_role` */

insert  into `t_admin_role`(`id`,`adminId`,`rid`) values 
(4,5,13),
(6,4,11),
(7,6,12),
(12,10,9),
(16,1,6),
(57,3,10),
(58,2,1),
(59,8,2),
(62,7,15),
(64,9,16),
(80,32,2);

/*Table structure for table `t_bursement` */

DROP TABLE IF EXISTS `t_bursement`;

CREATE TABLE `t_bursement` (
  `bursement_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '报销表id',
  `all_money` double DEFAULT NULL COMMENT '总计金额',
  `allinvoices` int(11) DEFAULT NULL COMMENT '票据总数',
  `burse_time` datetime DEFAULT NULL COMMENT '报销日期',
  `financial_advice` varchar(255) DEFAULT NULL COMMENT '财务总监意见',
  `manager_advice` varchar(255) DEFAULT NULL COMMENT '部门主管意见',
  `type_id` bigint(20) DEFAULT NULL COMMENT '报销方式',
  `operation_name` bigint(20) DEFAULT NULL COMMENT '报销人员',
  `user_name` bigint(20) DEFAULT NULL COMMENT '证明人',
  `pro_id` bigint(20) DEFAULT NULL COMMENT '流程id',
  `name` varchar(255) DEFAULT NULL COMMENT '客户名称',
  PRIMARY KEY (`bursement_id`),
  KEY `t_bursement_FK` (`pro_id`),
  CONSTRAINT `t_bursement_FK` FOREIGN KEY (`pro_id`) REFERENCES `t_process_list` (`process_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_bursement` */

insert  into `t_bursement`(`bursement_id`,`all_money`,`allinvoices`,`burse_time`,`financial_advice`,`manager_advice`,`type_id`,`operation_name`,`user_name`,`pro_id`,`name`) values 
(13,530,2,'2022-01-27 14:42:17','同意','同意',25,3,5,47,'李康'),
(14,530,2,'2022-01-27 14:45:34',NULL,'同意',25,3,5,48,'李康'),
(15,123123,1,NULL,NULL,'同意了',25,NULL,1,74,'李康'),
(16,1001,5,NULL,NULL,'批准了',25,NULL,1,78,'李康'),
(17,2364,3,'2022-04-12 12:52:01','答辩了答辩了','答辩了答辩了',26,3,1,80,'李康'),
(18,500,3,'2022-05-19 13:54:07','同意','111',25,3,1,86,'某某某'),
(19,1556,24,NULL,NULL,'1321',25,NULL,1,90,'撒');

/*Table structure for table `t_department` */

DROP TABLE IF EXISTS `t_department`;

CREATE TABLE `t_department` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(32) DEFAULT NULL COMMENT '部门名称',
  `parentId` int(11) DEFAULT NULL COMMENT '父id',
  `depPath` varchar(255) DEFAULT NULL COMMENT '路径',
  `enabled` tinyint(1) DEFAULT '1' COMMENT '是否启用',
  `isParent` tinyint(1) DEFAULT '0' COMMENT '是否上级',
  `depManager` int(11) DEFAULT NULL COMMENT '部门经理id',
  `salaryId` int(11) DEFAULT NULL COMMENT '工资账套ID',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `t_department_FK` (`depManager`),
  KEY `t_department_FK_1` (`salaryId`),
  CONSTRAINT `t_department_FK` FOREIGN KEY (`depManager`) REFERENCES `t_admin` (`id`),
  CONSTRAINT `t_department_FK_1` FOREIGN KEY (`salaryId`) REFERENCES `t_salary` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `t_department` */

insert  into `t_department`(`id`,`name`,`parentId`,`depPath`,`enabled`,`isParent`,`depManager`,`salaryId`) values 
(1,'股东会',-1,'.1',1,1,1,NULL),
(2,'董事会',1,'.1.2',1,1,1,NULL),
(3,'总办',2,'.1.2.3',1,1,2,5),
(4,'财务部',3,'.1.2.3.4',1,0,3,4),
(5,'市场部',3,'.1.2.3.5',1,1,4,1),
(12,'研发部',3,'.1.2.3.12',1,0,5,3),
(14,'人事部',3,'.1.2.3.14',1,0,6,2);

/*Table structure for table `t_detailsburse` */

DROP TABLE IF EXISTS `t_detailsburse`;

CREATE TABLE `t_detailsburse` (
  `detailsburse_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descript` varchar(255) DEFAULT NULL,
  `detailmoney` double NOT NULL,
  `invoices` int(11) DEFAULT NULL,
  `produce_time` datetime DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `bursment_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`detailsburse_id`),
  KEY `t_detailsburse_FK` (`bursment_id`),
  CONSTRAINT `t_detailsburse_FK` FOREIGN KEY (`bursment_id`) REFERENCES `t_bursement` (`bursement_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_detailsburse` */

insert  into `t_detailsburse`(`detailsburse_id`,`descript`,`detailmoney`,`invoices`,`produce_time`,`subject`,`bursment_id`) values 
(16,'肖十请客户吃饭的费用',500,1,'2022-01-27 09:13:40','招待费',13),
(17,'肖十接客户的油费',30,1,'2022-01-27 09:13:40','油费',13),
(18,'孙七请客户吃饭的费用',500,1,'2022-01-27 09:13:40','招待费',14),
(19,'孙七接客户的油费',30,1,'2022-01-27 14:19:21','油费',14),
(20,'测试报销前端页面',123123,1,'2022-03-07 16:46:50','采购费',15),
(21,'请客户吃饭',768,3,'2022-03-07 16:46:50','招待费',16),
(22,'接客户的打车费',233,2,'2022-03-07 16:47:02','招待费',16),
(23,'中期答辩招待客户',231,2,'2022-04-12 12:38:53','招待费',17),
(24,'中期答辩购买礼品',2133,1,'2022-04-12 12:39:13','采购费',17),
(25,'住宿费',200,1,'2022-05-19 13:35:06','出差费',18),
(26,'路费',300,2,'2022-05-19 13:35:08','预付款',18),
(27,'费用说明费用说明',1555,1,'2022-05-19 13:43:47','管理费用',19),
(28,'说明',1,23,'2022-05-19 13:43:51','销售费',19);

/*Table structure for table `t_employee` */

DROP TABLE IF EXISTS `t_employee`;

CREATE TABLE `t_employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '员工编号',
  `name` varchar(10) DEFAULT NULL COMMENT '员工姓名',
  `gender` char(4) DEFAULT NULL COMMENT '性别',
  `birthday` date DEFAULT NULL COMMENT '出生日期',
  `idCard` char(18) DEFAULT NULL COMMENT '身份证号',
  `wedlock` enum('已婚','未婚','离异') DEFAULT NULL COMMENT '婚姻状况',
  `nationId` int(8) DEFAULT NULL COMMENT '民族',
  `nativePlace` varchar(20) DEFAULT NULL COMMENT '籍贯',
  `politicId` int(8) DEFAULT NULL COMMENT '政治面貌',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(11) DEFAULT NULL COMMENT '电话号码',
  `address` varchar(64) DEFAULT NULL COMMENT '联系地址',
  `departmentId` int(11) DEFAULT NULL COMMENT '所属部门',
  `jobLevelId` int(11) DEFAULT NULL COMMENT '职称ID',
  `posId` int(11) DEFAULT NULL COMMENT '职位ID',
  `engageForm` varchar(8) DEFAULT NULL COMMENT '聘用形式',
  `tiptopDegree` enum('博士','硕士','本科','大专','高中','初中','小学','其他') DEFAULT NULL COMMENT '最高学历',
  `specialty` varchar(32) DEFAULT NULL COMMENT '所属专业',
  `school` varchar(32) DEFAULT NULL COMMENT '毕业院校',
  `beginDate` date DEFAULT NULL COMMENT '入职日期',
  `workState` enum('在职','离职') DEFAULT '在职' COMMENT '在职状态',
  `workID` char(8) DEFAULT NULL COMMENT '工号',
  `contractTerm` double DEFAULT NULL COMMENT '合同期限',
  `conversionTime` date DEFAULT NULL COMMENT '转正日期',
  `notWorkDate` date DEFAULT NULL COMMENT '离职日期',
  `beginContract` date DEFAULT NULL COMMENT '合同起始日期',
  `endContract` date DEFAULT NULL COMMENT '合同终止日期',
  `workAge` int(11) DEFAULT NULL COMMENT '工龄',
  `salaryId` int(11) DEFAULT NULL COMMENT '工资账套ID',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `departmentId` (`departmentId`) USING BTREE,
  KEY `jobId` (`jobLevelId`) USING BTREE,
  KEY `posId` (`posId`) USING BTREE,
  KEY `nationId` (`nationId`) USING BTREE,
  KEY `politicId` (`politicId`) USING BTREE,
  KEY `workId` (`workID`) USING BTREE,
  KEY `salaryId` (`salaryId`) USING BTREE,
  CONSTRAINT `t_employee_ibfk_1` FOREIGN KEY (`departmentId`) REFERENCES `t_department` (`id`),
  CONSTRAINT `t_employee_ibfk_2` FOREIGN KEY (`jobLevelId`) REFERENCES `t_joblevel` (`id`),
  CONSTRAINT `t_employee_ibfk_3` FOREIGN KEY (`posId`) REFERENCES `t_position` (`id`),
  CONSTRAINT `t_employee_ibfk_4` FOREIGN KEY (`nationId`) REFERENCES `t_nation` (`id`),
  CONSTRAINT `t_employee_ibfk_5` FOREIGN KEY (`politicId`) REFERENCES `t_politics_status` (`id`),
  CONSTRAINT `t_employee_ibfk_6` FOREIGN KEY (`salaryId`) REFERENCES `t_salary` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `t_employee` */

insert  into `t_employee`(`id`,`name`,`gender`,`birthday`,`idCard`,`wedlock`,`nationId`,`nativePlace`,`politicId`,`email`,`phone`,`address`,`departmentId`,`jobLevelId`,`posId`,`engageForm`,`tiptopDegree`,`specialty`,`school`,`beginDate`,`workState`,`workID`,`contractTerm`,`conversionTime`,`notWorkDate`,`beginContract`,`endContract`,`workAge`,`salaryId`) values 
(1,'韦梅','女','1999-11-18','341503198810196427','已婚',1,'合肥市',2,'xia53@gan23ing.cn','15567487642','贵州省洁市清城汕尾街d座 502246',3,NULL,6,'劳动合同','本科','电子工程1','中国科学技术大学','2018-10-16','在职','00000001',9.31,'2018-08-29',NULL,'2017-09-04','2019-08-27',NULL,4),
(2,'王丹','女','1992-03-25','350481197304037905','未婚',1,'关岭市',2,'jieqian@yahoo.com','18762780051','山西省合肥县西峰香港街C座 302114',4,NULL,4,'劳动合同','硕士','无','北京大学','2018-06-25','在职','00000002',9.5,'2017-08-05',NULL,'2017-05-31','2020-01-06',NULL,4),
(3,'刘俊','男','1996-07-18','130224197009132687','未婚',1,'萍县',13,'qiangfang@yahoo.com','18663579680','西藏自治区秀荣市海陵张家港街m座 576579',1,NULL,5,'劳务合同','博士','护理学','南京大学','2017-08-15','在职','00000003',2.53,'2016-05-25',NULL,'2015-12-21','2019-04-21',NULL,4),
(4,'刘玉珍','女','1993-07-10','512000196701014288','未婚',1,'雷县',5,'fangyang@pinggang.cn','13972309788','西藏自治区张家港市双滦凌街V座 614280',5,NULL,6,'劳动合同','本科','市场营销','上海交通大学','2019-02-21','在职','00000004',5.68,'2018-06-26',NULL,'2019-02-27','2019-11-06',NULL,1),
(5,'孟秀兰','女','2000-01-08','130601195204145457','未婚',1,'荆门市',10,'xliu@yahoo.com','13319975239','天津市辽阳市金平傅街j座 850761',5,NULL,7,'劳动合同','博士','电子工程','浙江大学','2016-12-13','在职','00000005',9.33,'2018-03-09',NULL,'2016-11-28','2019-06-25',NULL,4),
(6,'袁秀英','男','1990-05-07','110107198510132428','未婚',1,'合肥市',4,'nawu@hotmail.com','15984781796','重庆市兰英市高明沈阳街d座 430329',4,NULL,8,'劳务合同','博士','无','中国人民大学','2015-10-15','在职','00000006',9.2,'2016-08-30',NULL,'2019-03-21','2019-11-30',NULL,1),
(7,'沈璐','男','1992-05-07','520400196705056989','未婚',1,'合山县',8,'gaotao@hotmail.com','14715158775','浙江省坤县西峰余街C座 221697',5,NULL,4,'劳务合同','博士','室内装修设计','中国科学院大学','2016-07-30','在职','00000007',7.96,'2018-12-22',NULL,'2016-09-28','2020-02-05',NULL,4),
(8,'丁艳','男','2001-01-29','542100199401152966','未婚',1,'玲市',10,'yong36@gmail.com','18878249984','江西省南昌县双滦王路E座 490503',5,NULL,9,'劳动合同','博士','信息管理与信息系统','中国科学技术大学','2016-05-21','在职','00000008',0.25,'2017-05-06',NULL,'2015-07-28','2019-06-05',NULL,1),
(9,'张瑞','男','1994-01-11','330782198309077942','未婚',1,'银川市',10,'ping21@zengyi.net','15510694655','江西省太原市翔安刘街J座 815655',12,NULL,10,'劳务合同','博士','信息管理与信息系统','上海交通大学','2018-01-15','在职','00000009',4.54,'2016-04-30',NULL,'2017-02-03','2019-08-04',NULL,1),
(10,'黄柳','男','1999-10-03','45142219420818691X','未婚',1,'北镇市',3,'juanliu@jieming.cn','14523008198','江苏省宁县南溪王路x座 227133',1,NULL,11,'劳务合同','博士','市场营销','南京大学','2017-06-17','在职','00000010',1.46,'2016-11-03',NULL,'2016-06-07','2019-08-24',NULL,1),
(11,'王慧','女','1994-07-09','441223196008184592','未婚',1,'贵阳',8,'xiahu@dengpan.net','18288493616','青海省长沙县山亭廖街v座 439792',5,NULL,12,'劳务合同','博士','电子工程','浙江大学','2015-10-22','在职','00000011',9.54,'2016-07-28',NULL,'2018-03-02','2019-09-11',NULL,4),
(12,'田龙','男','1991-07-21','621026195512050072','未婚',1,'敏县',11,'epan@hotmail.com','13130911244','安徽省帆市东城唐路d座 819867',2,NULL,13,'劳务合同','博士','电子工程','中国科学技术大学','2016-06-13','在职','00000012',4.95,'2019-02-15',NULL,'2018-07-28','2019-08-27',NULL,1),
(13,'徐桂香','男','1997-03-06','220101195011153893','未婚',1,'桂芝市',8,'mingxiong@guiyingna.cn','15239681245','广东省巢湖县和平王路V座 418151',4,NULL,14,'劳务合同','博士','信息管理与信息系统','中国人民大学','2018-03-01','在职','00000013',8.58,'2018-04-13',NULL,'2018-01-09','2020-03-29',NULL,1),
(14,'韩桂花','女','1993-03-13','451200193609248217','已婚',1,'丽丽市',13,'vzhao@la.cn','13091676162','澳门特别行政区东莞市高港关岭街Z座 113957',4,NULL,4,'劳务合同','博士','护理学','清华大学','2015-08-09','在职','00000014',5.9,'2016-08-08',NULL,'2017-09-04','2019-06-23',NULL,1),
(15,'方勇','女','1993-03-31','50022619560729008X','已婚',1,'银川',12,'juan44@hotmail.com','13599861266','吉林省芳县南湖兰路y座 907387',4,NULL,4,'劳务合同','硕士','室内装修设计','北京大学','2016-01-17','在职','00000015',7.9,'2018-11-06',NULL,'2017-09-22','2019-12-16',NULL,1),
(16,'徐桂芝','女','1999-12-07','350627193810292241','已婚',1,'玉梅县',2,'min11@hotmail.com','18060545344','海南省桂荣县锡山拉萨路m座 889598',3,NULL,4,'劳动合同','硕士','市场营销','南京大学','2015-10-11','在职','00000016',8.5,'2017-10-08',NULL,'2016-01-25','2019-09-07',NULL,1),
(17,'郭玉英','女','1991-05-13','532626196803147428','已婚',1,'荆门市',5,'taozhang@hotmail.com','13498131990','贵州省玉英市长寿席路j座 607847',4,NULL,4,'劳动合同','硕士','护理学','清华大学','2017-09-23','在职','00000017',6.9,'2019-03-31',NULL,'2017-10-23','2019-07-17',NULL,1),
(18,'张波','男','1995-07-20','341523193305302515','已婚',1,'淑英县',4,'chao28@yahoo.com','13198353039','甘肃省勇县金平合山街V座 343550',4,NULL,5,'劳务合同','硕士','信息管理与信息系统','中国科学院大学','2015-05-24','在职','00000018',6.51,'2018-04-10',NULL,'2016-07-14','2019-07-21',NULL,1),
(19,'陈桂英','女','1998-07-24','320300198302021032','已婚',1,'西宁市',12,'leixiuying@lijun.cn','15196790642','江西省梧州县西峰林街g座 890108',12,NULL,5,'劳务合同','硕士','室内装修设计','中国人民大学','2019-02-19','在职','00000019',9.4,'2018-01-31',NULL,'2016-04-20','2019-12-24',NULL,1),
(20,'郭慧','男','1997-12-27','370784196907163913','已婚',1,'冬梅市',5,'jingyi@lilong.cn','18748925191','浙江省淮安市西峰周路Q座 231298',1,NULL,4,'劳务合同','硕士','室内装修设计','复旦大学','2018-05-20','在职','00000020',1.23,'2018-02-28',NULL,'2016-02-03','2019-05-13',NULL,1),
(21,'王兰英','女','2001-01-14','13062819460201540X','已婚',1,'北镇县',2,'nren@kc.cn','13697605585','河北省荆门县东丽徐路w座 733493',4,NULL,14,'劳动合同','硕士','市场营销','国防科技大学','2017-11-22','在职','00000021',7.2,'2017-09-14',NULL,'2018-05-04','2019-10-11',NULL,1),
(22,'张丽丽','女','1993-01-11','44011419750119469X','已婚',1,'桂花市',13,'vcao@hotmail.com','13499132244','江苏省颖县黄浦吴路f座 348086',1,NULL,4,'劳动合同','硕士','信息管理与信息系统','中国科学技术大学','2018-07-27','在职','00000022',1.57,'2016-08-17',NULL,'2019-03-29','2019-12-01',NULL,1),
(23,'陈红','女','1991-05-07','120101194008275509','已婚',1,'永安市',4,'fgu@hotmail.com','14504492015','宁夏回族自治区帆县山亭黄路Q座 528477',5,NULL,4,'劳动合同','硕士','市场营销','南京大学','2018-06-24','在职','00000023',2.32,'2016-11-21',NULL,'2018-09-15','2020-02-11',NULL,1),
(24,'范凤英','女','1994-11-12','510122194703163917','已婚',1,'秀兰县',6,'yong19@34.cn','13973512992','海南省梧州市滨城李路f座 504377',5,NULL,4,'劳动合同','硕士','电子工程','北京大学','2017-07-23','在职','00000024',3.52,'2017-07-30',NULL,'2016-02-23','2019-12-07',NULL,1),
(25,'张兵','男','1990-09-06','420701196603064012','已婚',1,'宜都市',3,'edeng@rd.cn','15904360492','西藏自治区桂珍市友好昆明街Y座 634021',5,NULL,13,'劳务合同','硕士','市场营销','中国人民大学','2017-03-20','在职','00000025',0.5,'2016-11-07',NULL,'2017-06-02','2020-01-24',NULL,1),
(26,'黄宁','男','1995-06-12','510303198712060557','已婚',1,'玉英县',11,'xiuyingpan@gmail.com','13377122856','广东省莉市朝阳台北街x座 190715',5,NULL,4,'劳动合同','硕士','护理学','中国科学院大学','2017-05-09','在职','00000026',7.72,'2017-01-24',NULL,'2018-06-07','2019-10-31',NULL,1),
(27,'黄荣','女','1997-08-04','14060319870325316X','已婚',1,'大冶市',1,'ihao@yahoo.com','14528832529','江西省六安县和平永安街r座 137243',5,NULL,12,'劳务合同','硕士','无','浙江大学','2018-09-29','在职','00000027',6.16,'2017-11-25',NULL,'2017-12-14','2019-12-29',NULL,1),
(28,'周雷','男','2001-12-03','411201197212305874','已婚',1,'六安市',7,'ping89@wg.cn','14550266014','浙江省丹县黄浦北镇路G座 186275',5,NULL,11,'劳动合同','硕士','电子工程','中国科学院大学','2017-10-04','在职','00000028',8.14,'2018-04-18',NULL,'2018-03-07','2020-03-20',NULL,3),
(29,'周静','女','2001-03-14','640105199111105559','已婚',1,'雪梅县',6,'xiajing@yahoo.com','18912358599','山西省瑜县怀柔北京路p座 691913',5,NULL,4,'劳务合同','硕士','护理学','北京大学','2018-02-28','在职','00000029',1.4,'2018-10-20',NULL,'2017-05-04','2020-01-05',NULL,1);

/*Table structure for table `t_holiday` */

DROP TABLE IF EXISTS `t_holiday`;

CREATE TABLE `t_holiday` (
  `holiday_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `leave_days` decimal(5,2) DEFAULT NULL COMMENT '请假天数',
  `type_id` bigint(20) DEFAULT NULL COMMENT '请假类型',
  `pro_id` bigint(20) DEFAULT NULL COMMENT '流程主表id',
  `manager_advice` varchar(255) DEFAULT NULL COMMENT '经理意见及说明',
  `personnel_advice` varchar(255) DEFAULT NULL COMMENT '人事部意见及说明',
  PRIMARY KEY (`holiday_id`),
  KEY `pro_id` (`pro_id`),
  CONSTRAINT `t_holiday_ibfk_1` FOREIGN KEY (`pro_id`) REFERENCES `t_process_list` (`process_id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_holiday` */

insert  into `t_holiday`(`holiday_id`,`leave_days`,`type_id`,`pro_id`,`manager_advice`,`personnel_advice`) values 
(21,2.08,38,36,'批准','同意'),
(22,3.00,38,37,'李四批准','赵六同意'),
(23,3.00,38,38,'同意',NULL),
(24,3.00,38,39,'111',NULL),
(25,3.00,38,40,'王二同意张三的请假','赵六同意张三的请假'),
(26,3.00,38,41,'jkl',NULL),
(27,3.00,38,42,NULL,NULL),
(28,3.00,38,43,'同意','同意'),
(34,2.00,38,62,'王二同意张三的请假','赵六不准你请假'),
(35,2.00,38,63,NULL,NULL),
(36,5.00,37,64,NULL,NULL),
(37,5.00,37,65,NULL,NULL),
(38,5.00,40,66,NULL,NULL),
(39,5.00,40,67,NULL,NULL),
(40,5.00,39,68,NULL,NULL),
(41,2.00,38,76,'姜五同意肖十的请假申请','赵六同意肖十的请假'),
(42,2.00,38,77,NULL,NULL),
(43,1.00,38,79,NULL,NULL),
(44,0.00,38,81,'答辩了答辩了',NULL),
(45,3.00,38,84,NULL,NULL),
(46,1.00,38,85,NULL,NULL),
(47,0.00,38,88,'111','同意'),
(48,0.00,38,89,'12',NULL);

/*Table structure for table `t_joblevel` */

DROP TABLE IF EXISTS `t_joblevel`;

CREATE TABLE `t_joblevel` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(32) DEFAULT NULL COMMENT '职称名称',
  `titleLevel` enum('正高级','副高级','中级','初级','高级') DEFAULT NULL COMMENT '职称等级',
  `createDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `enabled` tinyint(1) DEFAULT '1' COMMENT '是否启用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `t_joblevel` */

insert  into `t_joblevel`(`id`,`name`,`titleLevel`,`createDate`,`enabled`) values 
(1,'教授','正高级','2020-03-31 16:20:34',1),
(2,'副教授','副高级','2020-03-31 16:20:34',1),
(4,'讲师','中级','2020-03-31 16:20:34',1),
(5,'初级工程师','初级','2020-03-31 16:20:34',1),
(6,'中级工程师','中级','2020-03-31 16:20:34',1),
(7,'高级工程师','副高级','2020-03-31 16:20:34',1);

/*Table structure for table `t_mail_log` */

DROP TABLE IF EXISTS `t_mail_log`;

CREATE TABLE `t_mail_log` (
  `msgId` varchar(64) NOT NULL COMMENT '消息id',
  `eid` int(11) DEFAULT NULL COMMENT '接收员工id',
  `status` int(1) DEFAULT NULL COMMENT '状态（0:消息投递中 1:投递成功 2:投递失败）',
  `routeKey` varchar(20) DEFAULT NULL COMMENT '路由键',
  `exchange` varchar(20) DEFAULT NULL COMMENT '交换机',
  `count` int(1) DEFAULT NULL COMMENT '重试次数',
  `tryTime` datetime DEFAULT NULL COMMENT '重试时间',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  UNIQUE KEY `msgId` (`msgId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `t_mail_log` */

insert  into `t_mail_log`(`msgId`,`eid`,`status`,`routeKey`,`exchange`,`count`,`tryTime`,`createTime`,`updateTime`) values 
('058e9f8b-dc26-41b5-a156-e0d27184cb4b',24,1,'mail.routing.key','mail.exchange',0,'2022-04-08 14:10:43','2022-04-08 14:09:43','2022-04-08 14:09:43'),
('0eafba54-e9b5-4d7c-9f52-d14fc726fd46',23,1,'mail.routing.key','mail.exchange',0,'2022-04-08 14:05:02','2022-04-08 14:04:02','2022-04-08 14:04:02'),
('134c4fab-9393-414f-bcce-e8ccb141a38b',31,2,'mail.routing.key','mail.exchange',4,'2022-04-12 10:01:50','2022-04-12 09:56:47','2022-04-12 10:00:50'),
('1e92e848-b19b-42fa-aea0-572f2c877a72',18,1,'mail.routing.key','mail.exchange',2,'2022-04-08 09:43:50','2022-04-08 09:38:11','2022-04-08 09:42:50'),
('4e2ff54d-5c6c-48b8-b970-a089baead944',17,1,'mail.routing.key','mail.exchange',3,'2022-04-08 09:43:00','2022-04-08 09:33:22','2022-04-08 09:42:00'),
('5d7feaf0-f6d7-4f81-8c0c-36d002e15f5e',32,1,'mail.routing.key','mail.exchange',0,'2022-04-12 16:40:53','2022-04-12 16:39:53','2022-04-12 16:39:53'),
('6534cd12-1088-4e03-b956-96456eeee189',29,2,'mail.routing.key','mail.exchange',4,'2022-04-11 19:45:00','2022-04-11 19:34:25','2022-04-11 19:44:00'),
('75c52402-f6bb-4ff3-8bf3-2279129b7466',20,1,'mail.routing.key','mail.exchange',0,'2022-04-08 11:06:38','2022-04-08 11:05:38','2022-04-08 11:05:38'),
('9f122781-3503-4d1a-aa7e-e1bf90a1653d',33,1,'mail.routing.key','mail.exchange',0,'2022-04-15 09:06:00','2022-04-15 09:05:00','2022-04-15 09:05:00'),
('a7ad7a21-c070-435a-bfae-5d59bece818e',19,1,'mail.routing.key','mail.exchange',0,'2022-04-08 09:44:56','2022-04-08 09:43:56','2022-04-08 09:43:56'),
('a8065faa-a69c-4a29-9837-83542664bbc4',21,1,'mail.routing.key','mail.exchange',0,'2022-04-08 13:31:28','2022-04-08 13:30:28','2022-04-08 13:30:28'),
('bb86155b-3f45-4aa7-af04-1d66f34c73c2',26,1,'mail.routing.key','mail.exchange',0,'2022-04-11 11:13:23','2022-04-11 11:12:23','2022-04-11 11:12:23'),
('c310265e-1464-4657-a6c4-8118c3086e74',22,1,'mail.routing.key','mail.exchange',0,'2022-04-08 13:47:41','2022-04-08 13:46:41','2022-04-08 13:46:41'),
('d3c05f75-cdc7-47e4-990d-fc93c92b6d32',27,1,'mail.routing.key','mail.exchange',0,'2022-04-11 11:19:53','2022-04-11 11:18:53','2022-04-11 11:18:53'),
('e3376d54-33cd-4eb2-8f7e-0c2838b7822c',34,1,'mail.routing.key','mail.exchange',0,'2022-04-15 14:20:07','2022-04-15 14:19:07','2022-04-15 14:19:07'),
('e52a162f-7151-4ef9-9b89-c371800e70ef',35,1,'mail.routing.key','mail.exchange',0,'2022-04-15 14:21:22','2022-04-15 14:20:22','2022-04-15 14:20:22'),
('ece00aad-9375-4320-8ef4-483c893a7232',28,1,'mail.routing.key','mail.exchange',0,'2022-04-11 13:18:03','2022-04-11 13:17:03','2022-04-11 13:17:03'),
('ef6d0b79-6239-4a1d-a6a9-24dfb095d701',25,1,'mail.routing.key','mail.exchange',0,'2022-04-08 14:11:54','2022-04-08 14:10:54','2022-04-08 14:10:54'),
('f1cfe365-3cf4-4996-bde1-ad7e40631c04',30,2,'mail.routing.key','mail.exchange',4,'2022-04-12 09:02:40','2022-04-12 08:57:40','2022-04-12 09:01:40');

/*Table structure for table `t_menu` */

DROP TABLE IF EXISTS `t_menu`;

CREATE TABLE `t_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `url` varchar(64) DEFAULT NULL COMMENT 'url',
  `path` varchar(64) DEFAULT NULL COMMENT 'path',
  `component` varchar(64) DEFAULT NULL COMMENT '组件',
  `name` varchar(64) DEFAULT NULL COMMENT '菜单名',
  `iconCls` varchar(64) DEFAULT NULL COMMENT '图标',
  `keepAlive` tinyint(1) DEFAULT NULL COMMENT '是否保持激活',
  `requireAuth` tinyint(1) DEFAULT NULL COMMENT '是否要求权限',
  `parentId` int(11) DEFAULT NULL COMMENT '父id',
  `enabled` tinyint(1) DEFAULT '1' COMMENT '是否启用',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `parentId` (`parentId`) USING BTREE,
  CONSTRAINT `menu_ibfk_1` FOREIGN KEY (`parentId`) REFERENCES `t_menu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `t_menu` */

insert  into `t_menu`(`id`,`url`,`path`,`component`,`name`,`iconCls`,`keepAlive`,`requireAuth`,`parentId`,`enabled`) values 
(1,'/',NULL,NULL,'所有',NULL,NULL,NULL,NULL,1),
(3,'/','/home','Home','人事管理','fa fa-address-book-o',NULL,1,1,1),
(4,'/','/home','Home','薪资管理','fa fa-money',NULL,1,1,1),
(6,'/','/home','Home','系统管理','fa fa-windows',NULL,1,1,1),
(9,'/personnel/emp/**','/emp/basic','EmpBasic','员工资料',NULL,NULL,1,3,1),
(14,'/salary/sob/**','/sal/sob','SalSob','部门工资模板',NULL,NULL,1,4,1),
(15,'/salary/sobcfg/**','/sal/sobcfg','SalSobCfg','员工工资管理',NULL,NULL,1,4,1),
(23,'/system/basic/**','/sys/basic','SysBasic','基础设置',NULL,NULL,1,6,1),
(26,'/system/admin/**','/sys/admin','SysAdmin','用户管理',NULL,NULL,1,6,1),
(29,'/','/home','Home','流程管理','fa fa-list-ul',NULL,1,1,1),
(30,'/process/holiday/**','/pro/holiday','ProSubmitHoliday','请假流程','',NULL,1,29,1),
(32,'/process/reimburse/**','/pro/reimburse','ProReimburse','报销流程','',NULL,1,29,1),
(33,'/process/resign/**','/pro/resign','ProResign','离职流程','',NULL,1,29,1),
(34,'/process/myProcess/**','/pro/myProcess','ProMy','我的流程','',NULL,1,29,1),
(35,'/process/auditList/**','/pro/auditList','ProAuditList','审核列表','',NULL,1,29,1),
(36,'/','/home','Home','在线聊天','fa fa-commenting-o',NULL,1,1,1),
(37,'/chat/friendChat/**','/chat/FriendChat','FriendChat','在线聊天',NULL,NULL,1,36,1),
(38,'/','/home','Home','客户管理','fa fa-user-circle-o',NULL,1,1,1),
(39,'/cust/CustMana/**','/cust/CustManagement','CustManagement','客户资料','',NULL,1,38,1);

/*Table structure for table `t_menu_role` */

DROP TABLE IF EXISTS `t_menu_role`;

CREATE TABLE `t_menu_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `mid` int(11) DEFAULT NULL COMMENT '菜单id',
  `rid` int(11) DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `mid` (`mid`) USING BTREE,
  KEY `rid` (`rid`) USING BTREE,
  CONSTRAINT `t_menu_role_ibfk_1` FOREIGN KEY (`mid`) REFERENCES `t_menu` (`id`),
  CONSTRAINT `t_menu_role_ibfk_2` FOREIGN KEY (`rid`) REFERENCES `t_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1399 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `t_menu_role` */

insert  into `t_menu_role`(`id`,`mid`,`rid`) values 
(2,9,6),
(3,14,6),
(4,15,6),
(5,23,6),
(7,26,6),
(8,30,6),
(9,32,6),
(10,33,6),
(11,34,6),
(12,35,6),
(13,37,6),
(14,39,6),
(1268,14,6),
(1269,15,6),
(1270,23,6),
(1272,26,6),
(1273,30,6),
(1274,32,6),
(1275,33,6),
(1276,34,6),
(1277,35,6),
(1278,37,6),
(1279,39,6),
(1292,9,1),
(1293,14,1),
(1294,15,1),
(1295,23,1),
(1296,26,1),
(1297,30,1),
(1298,32,1),
(1299,33,1),
(1300,34,1),
(1301,35,1),
(1302,37,1),
(1303,39,1),
(1334,14,10),
(1335,15,10),
(1336,30,10),
(1337,32,10),
(1338,33,10),
(1339,34,10),
(1340,35,10),
(1341,37,10),
(1342,9,12),
(1343,30,12),
(1344,32,12),
(1345,33,12),
(1346,34,12),
(1347,35,12),
(1348,37,12),
(1349,30,13),
(1350,32,13),
(1351,33,13),
(1352,34,13),
(1353,35,13),
(1354,37,13),
(1355,14,15),
(1356,15,15),
(1357,30,15),
(1358,32,15),
(1359,33,15),
(1360,34,15),
(1361,37,15),
(1362,30,11),
(1363,32,11),
(1364,33,11),
(1365,34,11),
(1366,35,11),
(1367,37,11),
(1368,39,11),
(1369,30,9),
(1370,32,9),
(1371,33,9),
(1372,34,9),
(1373,37,9),
(1374,9,2),
(1375,30,2),
(1376,32,2),
(1377,33,2),
(1378,34,2),
(1379,37,2),
(1393,30,16),
(1394,32,16),
(1395,33,16),
(1396,34,16),
(1397,37,16),
(1398,39,16);

/*Table structure for table `t_nation` */

DROP TABLE IF EXISTS `t_nation`;

CREATE TABLE `t_nation` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(32) DEFAULT NULL COMMENT '民族',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `t_nation` */

insert  into `t_nation`(`id`,`name`) values 
(1,'汉族'),
(2,'蒙古族'),
(3,'回族'),
(4,'藏族'),
(5,'维吾尔族'),
(6,'苗族'),
(7,'彝族'),
(8,'壮族'),
(9,'布依族'),
(10,'朝鲜族'),
(11,'满族'),
(12,'侗族'),
(13,'瑶族'),
(14,'白族'),
(15,'土家族'),
(16,'哈尼族'),
(17,'哈萨克族'),
(18,'傣族'),
(19,'黎族'),
(20,'傈僳族'),
(21,'佤族'),
(22,'畲族'),
(23,'高山族'),
(24,'拉祜族'),
(25,'水族'),
(26,'东乡族'),
(27,'纳西族'),
(28,'景颇族'),
(29,'柯尔克孜族'),
(30,'土族'),
(31,'达斡尔族'),
(32,'仫佬族'),
(33,'羌族'),
(34,'布朗族'),
(35,'撒拉族'),
(36,'毛难族'),
(37,'仡佬族'),
(38,'锡伯族'),
(39,'阿昌族'),
(40,'普米族'),
(41,'塔吉克族'),
(42,'怒族'),
(43,'乌孜别克族'),
(44,'俄罗斯族'),
(45,'鄂温克族'),
(46,'崩龙族'),
(47,'保安族'),
(48,'裕固族'),
(49,'京族'),
(50,'塔塔尔族'),
(51,'独龙族'),
(52,'鄂伦春族'),
(53,'赫哲族'),
(54,'门巴族'),
(55,'珞巴族'),
(56,'基诺族');

/*Table structure for table `t_politics_status` */

DROP TABLE IF EXISTS `t_politics_status`;

CREATE TABLE `t_politics_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(32) DEFAULT NULL COMMENT '政治面貌',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `t_politics_status` */

insert  into `t_politics_status`(`id`,`name`) values 
(1,'中共党员'),
(2,'中共预备党员'),
(3,'共青团员'),
(4,'民革党员'),
(5,'民盟盟员'),
(6,'民建会员'),
(7,'民进会员'),
(8,'农工党党员'),
(9,'致公党党员'),
(10,'九三学社社员'),
(11,'台盟盟员'),
(12,'无党派民主人士'),
(13,'普通公民');

/*Table structure for table `t_position` */

DROP TABLE IF EXISTS `t_position`;

CREATE TABLE `t_position` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(32) DEFAULT NULL COMMENT '职位',
  `createDate` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `enabled` tinyint(1) DEFAULT '1' COMMENT '是否启用',
  `departmentId` int(11) DEFAULT NULL COMMENT '部门id',
  `roleId` int(11) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `name` (`name`) USING BTREE,
  KEY `t_position_FK` (`departmentId`),
  KEY `t_position_FK_1` (`roleId`),
  CONSTRAINT `t_position_FK` FOREIGN KEY (`departmentId`) REFERENCES `t_department` (`id`),
  CONSTRAINT `t_position_FK_1` FOREIGN KEY (`roleId`) REFERENCES `t_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `t_position` */

insert  into `t_position`(`id`,`name`,`createDate`,`enabled`,`departmentId`,`roleId`) values 
(4,'程序员','2022-01-19 14:08:44',1,12,9),
(5,'运维工程师','2022-01-19 14:08:44',1,12,9),
(6,'总经理','2022-01-06 12:26:14',1,3,1),
(7,'研发部主管','2022-01-19 14:07:46',1,12,13),
(8,'财务部主管','2022-01-19 14:07:46',1,4,10),
(9,'人事部主管','2022-01-19 14:07:46',1,14,2),
(10,'市场部主管','2022-01-19 14:07:46',1,5,11),
(11,'出纳员','2022-01-19 14:08:44',1,4,15),
(12,'会计师','2022-01-19 14:08:45',1,4,15),
(13,'销售','2022-01-19 14:09:41',1,5,16),
(14,'人事专员','2022-01-19 14:09:41',1,14,2);

/*Table structure for table `t_process_list` */

DROP TABLE IF EXISTS `t_process_list`;

CREATE TABLE `t_process_list` (
  `process_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '流程id',
  `apply_time` datetime DEFAULT NULL COMMENT '流程申请时间',
  `deeply_id` bigint(20) DEFAULT NULL COMMENT '紧急程度',
  `end_time` datetime DEFAULT NULL COMMENT '流程结束时间',
  `process_user_id` int(11) DEFAULT NULL COMMENT '流程申请人id',
  `process_des` text COMMENT '流程申请原因内容',
  `process_name` varchar(255) DEFAULT NULL COMMENT '标题',
  `process_days` int(11) DEFAULT NULL COMMENT '流程总天数',
  `is_checked` int(10) DEFAULT NULL COMMENT '流程是否被驳回',
  `start_time` datetime DEFAULT NULL COMMENT '流程开始时间',
  `status_id` bigint(20) DEFAULT NULL COMMENT '流程审核状态id',
  `type_name` varchar(255) DEFAULT NULL COMMENT '流程申请类型',
  `shenuser` varchar(255) DEFAULT NULL COMMENT '审核人员',
  `del` tinyint(4) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`process_id`),
  KEY `process_user_id` (`process_user_id`),
  CONSTRAINT `t_process_list_ibfk_1` FOREIGN KEY (`process_user_id`) REFERENCES `t_admin` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_process_list` */

insert  into `t_process_list`(`process_id`,`apply_time`,`deeply_id`,`end_time`,`process_user_id`,`process_des`,`process_name`,`process_days`,`is_checked`,`start_time`,`status_id`,`type_name`,`shenuser`,`del`) values 
(36,'2022-01-25 09:35:25',24,'2022-01-20 16:58:15',10,'肖十的请假','请假',3,0,'2022-01-20 16:58:15',25,'请假申请','姜五',0),
(37,'2022-01-25 09:35:45',24,'2022-01-20 16:58:15',9,'钱九的请假','请假',3,0,'2022-01-20 16:58:15',25,'请假申请','李四;赵六',0),
(38,'2022-01-25 09:36:30',24,'2022-01-20 16:58:15',8,'陈八的请假','请假',3,0,'2022-01-20 16:58:15',25,'请假申请','赵六',0),
(39,'2022-01-25 09:36:59',24,'2022-01-20 16:58:15',7,'孙七的请假','请假',3,0,'2022-01-20 16:58:15',24,'请假申请','张三',0),
(40,'2022-01-25 09:37:24',24,'2022-01-20 16:58:15',6,'赵六的请假','请假',3,0,'2022-01-20 16:58:15',25,'请假申请','王二;赵六',0),
(41,'2022-01-25 09:37:41',24,'2022-01-20 16:58:15',5,'姜五的请假','请假',3,0,'2022-01-20 16:58:15',24,'请假申请','王二',0),
(42,'2022-01-25 09:37:52',24,'2022-01-20 16:58:15',4,'李四的请假','请假',3,0,'2022-01-20 16:58:15',23,'请假申请','王二',0),
(43,'2022-01-25 09:38:05',24,'2022-01-20 16:58:15',3,'张三的请假','请假',3,0,'2022-01-20 16:58:15',25,'请假申请','王二',0),
(47,'2022-01-27 14:20:26',24,NULL,10,'招待客户的费用报销','肖十的费用报销',NULL,0,NULL,25,'费用报销','姜五',0),
(48,'2022-01-27 14:44:57',24,NULL,7,'招待客户的费用报销','孙七的费用报销',NULL,0,NULL,25,'费用报销','张三',0),
(49,'2022-01-28 11:20:51',24,NULL,10,'不干了，我是亿万富翁我摊牌了！','肖十的离职申请',NULL,0,NULL,26,'离职申请','姜五',0),
(50,'2022-01-29 10:40:04',24,NULL,9,'不干了，财富自由了！','钱九的离职申请',NULL,0,NULL,23,'离职申请','李四;赵六',0),
(53,'2022-01-29 10:45:13',24,NULL,9,'不干了，财富自由了！','钱九的离职申请',NULL,0,NULL,23,'离职申请','李四',0),
(54,'2022-01-29 12:58:31',24,NULL,8,'不干了！','陈八的离职申请',NULL,0,NULL,26,'离职申请','赵六',0),
(55,'2022-01-29 13:01:20',24,NULL,7,'不干了！','孙七的离职申请',NULL,0,NULL,25,'离职申请','张三;赵六',0),
(56,'2022-01-29 14:26:47',24,NULL,7,'跳槽不干了！','孙七的离职申请',NULL,0,NULL,25,'离职申请','张三;赵六',0),
(62,'2022-03-02 14:33:57',22,'2022-03-11 00:00:00',3,'家里有事情','张三的请假',2,0,'2022-03-09 00:00:00',26,'请假申请','王二;赵六',0),
(63,'2022-03-02 15:35:58',22,'2022-03-11 00:00:00',3,'张三的请假','张三的请假1',2,0,'2022-03-09 00:00:00',23,'请假申请','王二',0),
(64,'2022-03-02 15:36:19',23,'2022-03-15 00:00:00',3,'张三的请假2','张三的请假2',5,0,'2022-03-10 00:00:00',23,'请假申请','王二',0),
(65,'2022-03-02 15:36:26',24,'2022-03-15 00:00:00',3,'张三的请假3','张三的请假3',5,0,'2022-03-10 00:00:00',23,'请假申请','王二',0),
(66,'2022-03-02 15:36:36',22,'2022-03-15 00:00:00',3,'张三的请假4','张三的请假4',5,0,'2022-03-10 00:00:00',23,'请假申请','王二',0),
(67,'2022-03-02 15:36:47',23,'2022-03-15 00:00:00',3,'张三的请假5','张三的请假5',5,0,'2022-03-10 00:00:00',23,'请假申请','王二',0),
(68,'2022-03-02 15:36:57',24,'2022-03-15 00:00:00',3,'张三的请假6','张三的请假6',5,0,'2022-03-10 00:00:00',23,'请假申请','王二',0),
(74,'2022-03-03 12:31:41',22,NULL,2,'测试报销前端页面','王二的报销申请',NULL,0,NULL,26,'费用报销','王二',0),
(75,'2022-03-03 14:43:34',22,NULL,2,'测试离职申请前端页面','王二的离职申请',NULL,0,NULL,23,'离职申请','王二',0),
(76,'2022-03-04 13:35:49',22,'2022-03-07 00:00:00',10,'测试多级审核页面','肖十的请假申请',2,0,'2022-03-05 00:00:00',25,'请假申请','姜五;赵六',0),
(77,'2022-03-04 16:04:44',22,'2022-03-07 00:00:00',9,'测试请假申请','钱九的请假申请',2,0,'2022-03-05 00:00:00',23,'请假申请','李四',0),
(78,'2022-03-07 16:47:23',22,NULL,2,'招待客户的费用报销','王二的报销申请',NULL,0,NULL,24,'费用报销','王二',0),
(79,'2022-04-12 12:37:58',22,'2022-04-14 00:00:00',3,'中期答辩功能测试','张三的请假中期答辩',1,0,'2022-04-13 00:00:00',23,'请假申请','王二',1),
(80,'2022-04-12 12:39:35',23,NULL,3,'报销申请中期答辩','张三的报销中期答辩',NULL,0,NULL,25,'费用报销','王二;张三',0),
(81,'2022-04-12 12:41:21',23,'2022-04-12 12:41:10',3,'中期答辩中期答辩','张三的请假中期答辩',0,0,'2022-04-12 12:41:06',26,'请假申请','王二',0),
(82,'2022-04-12 12:45:20',22,NULL,3,'答辩答辩答辩答辩','张三的离职中期答辩',NULL,0,NULL,26,'离职申请','王二',0),
(83,'2022-04-13 09:47:09',22,NULL,8,'中期答辩','中期答辩演示',NULL,0,NULL,26,'离职申请','赵六',0),
(84,'2022-04-15 10:42:41',22,'2022-04-18 00:00:00',10,'中期答辩','中期答辩',3,0,'2022-04-15 00:00:00',23,'请假申请','姜五',0),
(85,'2022-04-22 21:52:05',24,'2022-04-23 21:51:19',8,'测试测试测试','测试',1,0,'2022-04-22 21:51:16',23,'请假申请','赵六',0),
(86,'2022-05-19 13:35:32',24,NULL,10,'答辩了答辩了','报销',NULL,0,NULL,25,'费用报销','姜五;张三',0),
(87,'2022-05-19 13:36:17',22,NULL,10,'不告诉你','离职测试离职离职',NULL,0,NULL,25,'离职申请','姜五;赵六',0),
(88,'2022-05-19 13:37:00',24,'2022-05-20 23:36:37',10,'答辩答辩答辩','请假了',0,0,'2022-05-20 13:36:38',25,'请假申请','姜五;赵六',0),
(89,'2022-05-19 13:43:25',23,'2022-05-20 13:43:14',10,'随便','随便',0,0,'2022-05-19 13:43:15',26,'请假申请','姜五',0),
(90,'2022-05-19 13:44:16',22,NULL,10,'萨达','随便2',NULL,0,NULL,26,'费用报销','姜五',0),
(91,'2022-05-19 13:45:01',22,NULL,10,'123','1231',NULL,0,NULL,26,'离职申请','姜五',0);

/*Table structure for table `t_resign` */

DROP TABLE IF EXISTS `t_resign`;

CREATE TABLE `t_resign` (
  `resign_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `financial_advice` varchar(255) DEFAULT NULL COMMENT '财务部经理意见',
  `is_finish` bit(1) DEFAULT NULL COMMENT '是否还有费用报销未完成',
  `nofinish` varchar(255) DEFAULT NULL COMMENT '未完成的工作',
  `personnel_advice` varchar(255) DEFAULT NULL COMMENT '人事部意见',
  `suggest` varchar(255) DEFAULT NULL COMMENT '对公司或部门的建议',
  `hand_user` int(11) DEFAULT NULL COMMENT '工作交接人',
  `pro_id` bigint(20) DEFAULT NULL COMMENT '流程id',
  `manager_advice` varchar(255) DEFAULT NULL COMMENT '部门经理意见',
  PRIMARY KEY (`resign_id`),
  KEY `t_resign_FK` (`pro_id`),
  KEY `t_resign_FK_1` (`hand_user`),
  CONSTRAINT `t_resign_FK` FOREIGN KEY (`pro_id`) REFERENCES `t_process_list` (`process_id`),
  CONSTRAINT `t_resign_FK_1` FOREIGN KEY (`hand_user`) REFERENCES `t_admin` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_resign` */

insert  into `t_resign`(`resign_id`,`financial_advice`,`is_finish`,`nofinish`,`personnel_advice`,`suggest`,`hand_user`,`pro_id`,`manager_advice`) values 
(3,NULL,'\0','触发器升级项目部分接口未完成','不同意','希望不加班',5,49,'同意'),
(4,NULL,'\0','客户还没有搞定',NULL,'希望涨薪资',4,50,NULL),
(5,NULL,'\0','客户还没有搞定',NULL,'希望涨薪资',4,53,NULL),
(6,NULL,'\0','招聘指标还没有达到',NULL,'希望涨薪资',6,54,'赵六同意了'),
(7,NULL,'\0','有些报销还没有处理','我赵六也同意了','希望涨薪资',3,55,'我张三同意了'),
(8,NULL,'\0','有些报销还没有处理','我张三同意了','希望少加班',3,56,'我张三同意了'),
(9,NULL,'\0','公司oa系统平台搭建项目未完成',NULL,'测试离职申请前端页面',1,75,NULL),
(10,NULL,'\0','答辩答辩答辩答辩',NULL,'答辩答辩答辩答辩',1,82,'答辩了答辩了'),
(11,NULL,'\0','中期答辩',NULL,'中期答辩',1,83,'中期答辩'),
(12,NULL,'\0','事情干不完也要走','同意','111',1,87,'可以'),
(13,NULL,'\0','问问',NULL,'123',1,91,'111');

/*Table structure for table `t_reviewed` */

DROP TABLE IF EXISTS `t_reviewed`;

CREATE TABLE `t_reviewed` (
  `reviewed_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `advice` varchar(255) DEFAULT NULL,
  `reviewed_time` datetime DEFAULT NULL,
  `status_id` bigint(20) DEFAULT NULL,
  `pro_id` bigint(20) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `del` tinyint(4) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`reviewed_id`),
  KEY `user_id` (`user_id`),
  KEY `pro_id` (`pro_id`),
  CONSTRAINT `t_reviewed_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `t_admin` (`id`),
  CONSTRAINT `t_reviewed_ibfk_2` FOREIGN KEY (`pro_id`) REFERENCES `t_process_list` (`process_id`)
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_reviewed` */

insert  into `t_reviewed`(`reviewed_id`,`advice`,`reviewed_time`,`status_id`,`pro_id`,`user_id`,`del`) values 
(19,'批准','2022-01-25 15:09:12',25,36,5,0),
(20,'李四批准','2022-03-04 16:05:31',25,37,4,0),
(21,'同意','2022-01-25 15:40:22',25,38,6,0),
(22,'111','2022-04-22 16:53:35',25,39,3,0),
(23,'王二同意赵六的请假','2022-03-04 10:29:50',25,40,2,0),
(24,'jkl','2022-04-06 16:04:31',25,41,2,0),
(25,NULL,NULL,23,42,2,0),
(26,'同意','2022-01-25 15:34:17',25,43,2,0),
(29,'同意','2022-01-25 15:26:49',25,36,6,0),
(30,'同意','2022-01-25 15:35:15',25,43,6,0),
(34,'同意','2022-01-27 14:31:47',25,47,5,0),
(35,'同意','2022-01-27 14:42:17',25,47,3,0),
(36,'同意','2022-01-27 14:45:34',25,48,3,0),
(37,'同意','2022-01-28 12:12:21',25,49,5,0),
(38,NULL,NULL,23,50,4,0),
(39,NULL,NULL,23,53,4,0),
(40,'不同意','2022-01-29 12:12:21',26,49,6,0),
(41,'赵六同意了','2022-01-29 12:59:43',26,54,6,0),
(42,'我张三同意了','2022-01-29 13:01:54',25,55,3,0),
(43,'我赵六也同意了','2022-01-29 13:02:30',25,55,6,0),
(44,'我张三同意了','2022-01-29 14:27:20',25,56,3,0),
(45,'我赵六同意了','2022-01-29 14:27:24',25,56,6,0),
(51,'王二同意张三的请假','2022-03-04 10:30:35',25,62,2,1),
(52,NULL,'2022-03-04 10:30:35',23,63,2,0),
(53,NULL,NULL,23,64,2,0),
(54,NULL,NULL,23,65,2,0),
(55,NULL,NULL,23,66,2,0),
(56,NULL,NULL,23,67,2,0),
(57,NULL,NULL,23,68,2,0),
(59,'你应该自掏腰包','2022-03-04 13:48:15',26,74,2,0),
(60,NULL,NULL,23,75,2,0),
(61,'赵六同意张三的请假','2022-03-04 12:59:37',25,40,6,0),
(62,'赵六不准你请假','2022-03-08 14:32:36',26,62,6,0),
(63,'姜五同意肖十的请假申请','2022-03-04 13:37:00',25,76,5,0),
(64,'赵六同意肖十的请假','2022-03-04 13:38:12',25,76,6,0),
(65,NULL,NULL,23,77,4,0),
(66,'赵六同意','2022-03-04 16:06:54',25,37,6,0),
(67,'我批准我自己的报销','2022-03-08 15:08:41',25,78,2,0),
(68,NULL,NULL,23,78,3,0),
(69,NULL,NULL,23,41,6,0),
(70,NULL,NULL,23,79,2,0),
(71,'答辩了答辩了','2022-04-12 12:48:42',25,80,2,0),
(72,'答辩了答辩了','2022-04-12 12:49:13',26,81,2,0),
(73,'答辩了答辩了','2022-04-12 12:49:20',26,82,2,0),
(74,'答辩了答辩了','2022-04-12 12:52:01',25,80,3,0),
(75,'中期答辩','2022-04-13 09:47:29',26,83,6,0),
(76,NULL,NULL,23,84,5,0),
(77,NULL,NULL,23,39,6,0),
(78,NULL,NULL,23,85,6,0),
(79,'111','2022-05-19 13:42:42',25,86,5,0),
(80,'可以','2022-05-19 13:42:18',25,87,5,0),
(81,'111','2022-05-19 13:42:55',25,88,5,0),
(82,'同意','2022-05-19 13:58:25',25,87,6,0),
(83,'同意','2022-05-19 13:54:07',25,86,3,0),
(84,'同意','2022-05-19 13:58:34',25,88,6,0),
(85,'12','2022-05-19 13:45:29',26,89,5,0),
(86,'1321','2022-05-19 13:45:21',26,90,5,0),
(87,'111','2022-05-19 13:45:35',26,91,5,0);

/*Table structure for table `t_role` */

DROP TABLE IF EXISTS `t_role`;

CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(64) DEFAULT NULL COMMENT '名称',
  `nameZh` varchar(64) DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `t_role` */

insert  into `t_role`(`id`,`name`,`nameZh`) values 
(1,'ROLE_manager','总经理'),
(2,'ROLE_personnel','人事专员'),
(6,'ROLE_admin','系统管理员'),
(9,'ROLE_user','开发人员'),
(10,'ROLE_Financial','财务主管'),
(11,'ROLE_Marketing','市场主管'),
(12,'ROLE_PersonnelSupervisor','人事主管'),
(13,'ROLE_Research','研发主管'),
(15,'ROLE_accountant','财务专员'),
(16,'ROLE_saler','销售');

/*Table structure for table `t_salary` */

DROP TABLE IF EXISTS `t_salary`;

CREATE TABLE `t_salary` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `basicSalary` int(11) DEFAULT NULL COMMENT '基本工资',
  `bonus` int(11) DEFAULT NULL COMMENT '奖金',
  `lunchSalary` int(11) DEFAULT NULL COMMENT '午餐补助',
  `trafficSalary` int(11) DEFAULT NULL COMMENT '交通补助',
  `allSalary` int(11) DEFAULT NULL COMMENT '应发工资',
  `pensionBase` int(11) DEFAULT NULL COMMENT '养老金基数',
  `pensionPer` float DEFAULT NULL COMMENT '养老金比率',
  `createDate` timestamp NULL DEFAULT NULL COMMENT '启用时间',
  `medicalBase` int(11) DEFAULT NULL COMMENT '医疗基数',
  `medicalPer` float DEFAULT NULL COMMENT '医疗保险比率',
  `accumulationFundBase` int(11) DEFAULT NULL COMMENT '公积金基数',
  `accumulationFundPer` float DEFAULT NULL COMMENT '公积金比率',
  `name` varchar(32) DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `t_salary` */

insert  into `t_salary`(`id`,`basicSalary`,`bonus`,`lunchSalary`,`trafficSalary`,`allSalary`,`pensionBase`,`pensionPer`,`createDate`,`medicalBase`,`medicalPer`,`accumulationFundBase`,`accumulationFundPer`,`name`) values 
(1,8000,500,800,400,NULL,1000,0.06,'2018-01-24 00:00:00',1000,0.06,1000,0.06,'市场部工资账套'),
(2,4500,500,500,500,NULL,1800,0.06,'2018-01-01 00:00:00',2200,0.06,3200,0.06,'人事部工资账套'),
(3,9000,500,1000,1000,NULL,3000,0.06,'2018-01-25 00:00:00',3000,0.06,3000,0.06,'研发部工资账套'),
(4,5000,500,500,500,NULL,500,0.06,'2020-04-10 14:15:45',500,0.06,500,0.06,'财务部工资账套'),
(5,8000,30000,400,400,NULL,4000,0.06,'2022-04-08 10:16:06',4000,0.06,3000,0.06,'总裁办工资账套');

/*Table structure for table `t_status_list` */

DROP TABLE IF EXISTS `t_status_list`;

CREATE TABLE `t_status_list` (
  `status_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '状态id',
  `status_color` varchar(255) DEFAULT NULL COMMENT '状态颜色',
  `status_model` varchar(255) DEFAULT NULL COMMENT '状态模块',
  `status_name` varchar(255) DEFAULT NULL COMMENT '状态名称',
  `sort_value` int(11) DEFAULT NULL COMMENT '状态排序值',
  `sort_precent` varchar(255) DEFAULT NULL COMMENT '百分比',
  PRIMARY KEY (`status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_status_list` */

insert  into `t_status_list`(`status_id`,`status_color`,`status_model`,`status_name`,`sort_value`,`sort_precent`) values 
(1,'label-success','aoa_mailnumber','有效',0,''),
(2,'label-default','aoa_mailnumber','无效',1,''),
(3,'label-warning','aoa_task_list','新任务',0,'20%'),
(4,'label-info','aoa_task_list','已接收',1,'40%'),
(5,'label-primary','aoa_task_list','进行中',2,'60%'),
(6,'label-danger','aoa_task_list','已提交',3,'80%'),
(7,'label-success','aoa_task_list','已完成',4,'100%'),
(8,'label-info','aoa_note_list','一般',0,''),
(9,'label-danger','aoa_note_list','重要',1,''),
(10,'label-info','aoa_attends_list','正常',0,''),
(11,'label-warning','aoa_attends_list','迟到',1,''),
(12,'label-danger','aoa_attends_list','早退',2,''),
(13,'label-danger','a','旷工',3,''),
(14,'label-primary','inform','一般',0,''),
(15,'label-warning','inform','重要',1,''),
(16,'label-danger','inform','紧急',2,''),
(17,'label-warning','aoa_plan_list','未完成',0,''),
(18,'label-success','aoa_plan_list','已完成',1,''),
(19,'label-info','aoa_plan_list','已取消',2,''),
(20,'label-primary','aoa_in_mail_list','一般',0,''),
(21,'label-warning','aoa_in_mail_list','重要',1,''),
(22,'label-danger','aoa_in_mail_list','紧急',2,''),
(23,'label-info','aoa_process_list','未处理',0,''),
(24,'label-primary','aoa_process_list','处理中',1,''),
(25,'label-success','aoa_process_list','已批准',2,''),
(26,'label-danger','aoa_process_list','未通过',3,''),
(27,'label-primary','aoa_schedule_list','一般',0,''),
(28,'label-warning','aoa_schedule_list','重要',1,''),
(29,'label-danger','aoa_schedule_list','紧急',2,'');

/*Table structure for table `t_sys_msg` */

DROP TABLE IF EXISTS `t_sys_msg`;

CREATE TABLE `t_sys_msg` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `mid` int(11) DEFAULT NULL COMMENT '消息id',
  `type` int(11) DEFAULT '0' COMMENT '0表示群发消息',
  `adminid` int(11) DEFAULT NULL COMMENT '这条消息是给谁的',
  `state` int(11) DEFAULT '0' COMMENT '0 未读 1 已读',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `adminid` (`adminid`) USING BTREE,
  KEY `mid` (`mid`) USING BTREE,
  CONSTRAINT `t_sys_msg_ibfk_1` FOREIGN KEY (`mid`) REFERENCES `t_sys_msg_content` (`id`),
  CONSTRAINT `t_sys_msg_ibfk_2` FOREIGN KEY (`adminid`) REFERENCES `t_admin` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `t_sys_msg` */

insert  into `t_sys_msg`(`id`,`mid`,`type`,`adminid`,`state`) values 
(1,1,0,1,1),
(2,1,0,2,1),
(3,1,0,3,1),
(4,1,0,4,0),
(5,1,0,5,0),
(6,2,0,1,1),
(7,2,0,2,1),
(8,2,0,3,1),
(9,2,0,4,0),
(10,2,0,5,0),
(11,3,0,1,1),
(12,3,0,2,1),
(13,3,0,3,1),
(14,3,0,4,0),
(15,3,0,5,0);

/*Table structure for table `t_sys_msg_content` */

DROP TABLE IF EXISTS `t_sys_msg_content`;

CREATE TABLE `t_sys_msg_content` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(64) DEFAULT NULL COMMENT '标题',
  `message` varchar(255) DEFAULT NULL COMMENT '内容',
  `createDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `t_sys_msg_content` */

insert  into `t_sys_msg_content`(`id`,`title`,`message`,`createDate`) values 
(1,'通知标题1','通知内容1','2020-03-31 16:20:34'),
(2,'通知标题2','通知内容2','2020-03-31 16:20:34'),
(3,'通知标题3','通知内容3','2020-03-31 16:20:34');

/*Table structure for table `t_type_list` */

DROP TABLE IF EXISTS `t_type_list`;

CREATE TABLE `t_type_list` (
  `type_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type_color` varchar(255) DEFAULT NULL,
  `type_model` varchar(255) DEFAULT NULL,
  `type_name` varchar(255) DEFAULT NULL,
  `sort_value` int(11) DEFAULT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_type_list` */

insert  into `t_type_list`(`type_id`,`type_color`,`type_model`,`type_name`,`sort_value`) values 
(1,'red','aoa_mailnumber','系统邮件',0),
(2,'red','aoa_mailnumber','公司邮件',1),
(3,'red','aoa_task_list','公事',0),
(4,'red','aoa_task_list','私事',1),
(5,'red','aoa_note_list','我的笔记',0),
(6,'red','aoa_note_list','公司笔记',1),
(7,'red','aoa_note_list','共享笔记',2),
(8,'red','aoa_attends_list','上班',1),
(9,'red','aoa_attends_list','下班',2),
(10,'red','inform','公告',0),
(11,'red','inform','通知',1),
(12,'red','inform','投票',2),
(13,'red','aoa_plan_list','日计划',0),
(14,'red','aoa_plan_list','周计划',1),
(15,'red','aoa_plan_list','月计划',2),
(16,'red','aoa_in_mail_list','邮件',0),
(17,'red','aoa_in_mail_list','通知',1),
(18,'red','aoa_in_mail_list','公告',2),
(19,'label-danger','chat','公告',0),
(20,'label-success','chat','讨论',1),
(21,'label-warning','chat','投票',2),
(22,'red','aoa_process_list','正常',0),
(23,'red','aoa_process_list','重要',1),
(24,'red','aoa_process_list','紧急',2),
(25,'red','aoa_bursement','银行卡',0),
(26,'red','aoa_bursement','现金',1),
(27,'red','aoa_bursement','其他',2),
(28,'red','aoa_evection','销售拜访',0),
(29,'red','aoa_evection','售前支持',1),
(30,'red','aoa_evection','项目支持',2),
(31,'red','aoa_evection','客服外出',3),
(32,'red','aoa_evection','其他',4),
(33,'red','aoa_overtime','工作日',0),
(34,'red','aoa_overtime','休息日',1),
(35,'red','aoa_overtime','节假日',2),
(36,'red','aoa_overtime','其他',3),
(37,'1、年假：工作满一年以上每年可享受带薪年假，当年的年假使用期至次年3月1日止；未经用人单位批准，员工不得自行休假。','aoa_holiday','年假',7),
(38,'2、事假：按照平均工作日扣发薪资（包含工资及各类补贴福利）；单次事假原则上不应超过3天，当年累计事假超过15天的视为主动放弃继续履行岗位职责的权利，特殊情况除外。  ','aoa_holiday','事假',4),
(39,'3、病假：两天及以上病假必须持工作地二级甲等以上医院相关病假证明单或就医记录证明，否则无法享受病假工资；单次病假超过5天，应当持有工作所在地三级甲等医院提供的病假证明单。原则上当年累计病假超过15天应视为不具备继续履行岗位职责身体条件，特殊情况除外。  ','aoa_holiday','病假',NULL),
(40,'4、婚假：婚假为十天，包含休息日，请婚假必须持结婚证明，结婚证必须在泛微工作期间领取，且婚假必须在一年内使用完毕，不得分次休假，国家或地方政府有其他规定的按照当地要求执行；  ','aoa_holiday','婚假',10),
(41,'5、产假及哺乳假：按国家及地方法律法规执行，包含休息日与法定节假日；  ','aoa_holiday','产假及哺乳假',180),
(42,'6、陪产假：泛微正式男性员工在工作期间配偶生育的，可凭子女出生证明，享受十个自然日陪产假。  ','aoa_holiday','陪产假',10),
(43,'7、丧假：直系亲属：配偶、子女、父母可享受三天，岳父母、祖父母、外祖父母可享受一天','aoa_holiday','丧假',NULL),
(44,'','aoa_schedule_list','日程提醒',0),
(45,'','aoa_schedule_list','假日安排',1),
(46,'red','aoa_attends_list','请假',3),
(47,'red','aoa_attends_list','出差',4);

/* Procedure structure for procedure `addDep` */

/*!50003 DROP PROCEDURE IF EXISTS  `addDep` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `addDep`(in depName varchar(32),in parentId int,in enabled boolean,out result int,out result2 int)
begin
  declare did int;
  declare pDepPath varchar(64);
  insert into t_department set name=depName,parentId=parentId,enabled=enabled;
  select row_count() into result;
  select last_insert_id() into did;
  set result2=did;
  select depPath into pDepPath from t_department where id=parentId;
  update t_department set depPath=concat(pDepPath,'.',did) where id=did;
  update t_department set isParent=true where id=parentId;
end */$$
DELIMITER ;

/* Procedure structure for procedure `deleteDep` */

/*!50003 DROP PROCEDURE IF EXISTS  `deleteDep` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteDep`(in did int,out result int)
begin
  declare ecount int;
  declare pid int;
  declare pcount int;
  declare a int;
  select count(*) into a from t_department where id=did and isParent=false;
  if a=0 then set result=-2;
  else
  select count(*) into ecount from t_admin where departmentId=did;
  if ecount>0 then set result=-1;
  else 
  select parentId into pid from t_department where id=did;
  delete from t_department where id=did and isParent=false;
  select row_count() into result;
  select count(*) into pcount from t_department where parentId=pid;
  if pcount=0 then update t_department set isParent=false where id=pid;
  end if;
  end if;
  end if;
end */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
