/*
Navicat MySQL Data Transfer

Source Server         : ali_ehealth
Source Server Version : 50634
Source Host           : rm-m5e1znw49jwui6887.mysql.rds.aliyuncs.com:3306
Source Database       : ehealth

Target Server Type    : MYSQL
Target Server Version : 50634
File Encoding         : 65001

Date: 2017-02-15 10:14:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for s_applogs
-- ----------------------------
DROP TABLE IF EXISTS `s_applogs`;
CREATE TABLE `s_applogs` (
  `UID_` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `CorpNo_` varchar(10) NOT NULL,
  `Service_` varchar(80) NOT NULL,
  `Trans_` bit(1) NOT NULL DEFAULT b'0',
  `TickCount_` decimal(18,6) NOT NULL,
  `AppUser_` varchar(100) NOT NULL,
  `AppDate_` datetime NOT NULL,
  `Client_` bit(1) NOT NULL,
  `DataIn_` text,
  PRIMARY KEY (`UID_`)
) ENGINE=InnoDB AUTO_INCREMENT=3104242 DEFAULT CHARSET=utf8 COMMENT='服务调用记录表';

-- ----------------------------
-- Table structure for s_appmenus
-- ----------------------------
DROP TABLE IF EXISTS `s_appmenus`;
CREATE TABLE `s_appmenus` (
  `It_` int(11) NOT NULL DEFAULT '0' COMMENT '菜单序',
  `VerList_` varchar(20) NOT NULL COMMENT '允许版本列表',
  `ProcCode_` varchar(30) NOT NULL COMMENT '权限代码',
  `Code_` varchar(30) NOT NULL COMMENT '菜单代码',
  `Name_` varchar(60) NOT NULL COMMENT '菜单名称',
  `Win_` bit(1) NOT NULL DEFAULT b'1',
  `Web_` bit(1) NOT NULL DEFAULT b'0',
  `Security_` bit(1) NOT NULL COMMENT 'Security_',
  `Hide_` bit(1) NOT NULL COMMENT 'Hide_',
  `Folder_` bit(1) NOT NULL COMMENT 'Folder_',
  `Parent_` varchar(30) DEFAULT NULL COMMENT '父阶菜单',
  `Update_` datetime DEFAULT NULL COMMENT '更新时间',
  `UpdateKey_` varchar(38) NOT NULL COMMENT '更新ID',
  `Class_` varchar(80) NOT NULL DEFAULT '' COMMENT '类路径',
  `Image_` varchar(100) DEFAULT NULL COMMENT '图标文件名',
  `Custom_` bit(1) DEFAULT b'0',
  `Group_` varchar(30) DEFAULT NULL COMMENT 'Group_',
  `Lock_` int(11) DEFAULT NULL COMMENT 'Lock_',
  `Approve_` varchar(20) DEFAULT NULL COMMENT 'Approve_',
  `FormNo_` int(11) DEFAULT '0' COMMENT '页面编号',
  `AppDate_` datetime DEFAULT NULL,
  `Database_` varchar(10) NOT NULL DEFAULT '' COMMENT '专用帐套代码',
  `WorkDay_` int(11) DEFAULT NULL COMMENT 'WorkDay_',
  `ExeName_` varchar(30) DEFAULT NULL COMMENT '所在bpl',
  `ID_` varchar(38) NOT NULL COMMENT 'ID_',
  `Param_` text COMMENT 'Param_',
  `Author_` varchar(10) DEFAULT NULL COMMENT 'Author_',
  `Status_` int(11) NOT NULL COMMENT 'Status_',
  `Remark_` text COMMENT '备注',
  PRIMARY KEY (`Code_`),
  KEY `FK_SysFormDef_ProcCode_` (`ProcCode_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统菜单库';

-- ----------------------------
-- Table structure for s_bookinfo
-- ----------------------------
DROP TABLE IF EXISTS `s_bookinfo`;
CREATE TABLE `s_bookinfo` (
  `UID_` int(255) NOT NULL AUTO_INCREMENT,
  `corpType_` int(11) NOT NULL COMMENT '取值范围：0.总运营商；1.城市',
  `corpNo_` varchar(10) NOT NULL COMMENT '存放城市区号',
  `shortName_` varchar(30) NOT NULL COMMENT '存放城市简称：如铜川市',
  `name_` varchar(80) NOT NULL COMMENT '存放城市全称：如陕西省铜川市',
  `wx_account_` varchar(50) DEFAULT NULL COMMENT '微信公众号',
  `wx_appid_` varchar(32) DEFAULT NULL,
  `status_` int(11) DEFAULT '1' COMMENT '取值范围：0.未启用，1.使用中，2.已停用',
  `remark_` varchar(100) DEFAULT NULL COMMENT '备注',
  `AppUser_` varchar(100) DEFAULT NULL,
  `cretateDate_` datetime DEFAULT CURRENT_TIMESTAMP,
  `updateUser_` varchar(100) DEFAULT NULL,
  `updateDate_` datetime DEFAULT NULL,
  PRIMARY KEY (`UID_`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='帐号编号登记表';

-- ----------------------------
-- Table structure for s_bookoptions
-- ----------------------------
DROP TABLE IF EXISTS `s_bookoptions`;
CREATE TABLE `s_bookoptions` (
  `CorpNo_` varchar(10) NOT NULL COMMENT '帐套代码',
  `Code_` varchar(30) NOT NULL COMMENT '参数代码',
  `Name_` varchar(80) NOT NULL COMMENT '参数名称',
  `Value_` varchar(500) NOT NULL DEFAULT ' ' COMMENT '参数数值',
  `UpdateKey_` varchar(38) NOT NULL COMMENT '更新ID',
  PRIMARY KEY (`CorpNo_`,`Code_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='帐套参数档';

-- ----------------------------
-- Table structure for s_currentuser
-- ----------------------------
DROP TABLE IF EXISTS `s_currentuser`;
CREATE TABLE `s_currentuser` (
  `UID_` int(11) NOT NULL AUTO_INCREMENT,
  `LoginID_` varchar(50) NOT NULL COMMENT 'SessionID',
  `CorpNo_` varchar(10) DEFAULT NULL COMMENT '帐套编号',
  `Account_` varchar(30) NOT NULL COMMENT '用户帐号',
  `clientVersion_` varchar(10) DEFAULT NULL COMMENT '版本号',
  `clientOS_` varchar(20) DEFAULT NULL COMMENT 'os版本号',
  `clientIP_` varchar(15) DEFAULT NULL,
  `Screen_` varchar(10) DEFAULT NULL COMMENT '屏幕分辨率',
  `Computer_` varchar(30) DEFAULT NULL COMMENT '电脑名称',
  `LoginServer_` varchar(80) DEFAULT NULL COMMENT 'LoginServer_',
  `LoginTime_` datetime DEFAULT NULL COMMENT 'LoginTime_',
  `LogoutTime_` datetime DEFAULT NULL COMMENT 'LogoutTime_',
  `KeyCardID_` varchar(38) DEFAULT NULL COMMENT 'KeyCardID_',
  `ParamValue_` varchar(10) DEFAULT NULL COMMENT 'ParamValue_',
  `Viability_` int(11) DEFAULT NULL COMMENT '生命值',
  `UserID_` varchar(38) NOT NULL COMMENT '用户ID',
  PRIMARY KEY (`UID_`),
  KEY `Viability_` (`Viability_`),
  KEY `FK_CurrentUser_UserCode_` (`Account_`)
) ENGINE=InnoDB AUTO_INCREMENT=55407 DEFAULT CHARSET=utf8 COMMENT='在下用户表';

-- ----------------------------
-- Table structure for s_custommenus
-- ----------------------------
DROP TABLE IF EXISTS `s_custommenus`;
CREATE TABLE `s_custommenus` (
  `UID_` int(11) NOT NULL AUTO_INCREMENT,
  `CorpNo_` varchar(10) NOT NULL COMMENT '公司别',
  `Code_` varchar(30) NOT NULL COMMENT '菜单名',
  `Remark_` text COMMENT '备注',
  `AppUser_` varchar(100) NOT NULL COMMENT '建档人员',
  `AppDate_` datetime NOT NULL COMMENT '建档日期',
  PRIMARY KEY (`UID_`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for s_mgtosql
-- ----------------------------
DROP TABLE IF EXISTS `s_mgtosql`;
CREATE TABLE `s_mgtosql` (
  `UID_` int(11) NOT NULL AUTO_INCREMENT,
  `c1` varchar(32) DEFAULT NULL,
  `c2` varchar(32) DEFAULT NULL,
  `c3` varchar(32) DEFAULT NULL,
  `c4` varchar(32) DEFAULT NULL,
  `c5` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`UID_`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for s_pagelogs
-- ----------------------------
DROP TABLE IF EXISTS `s_pagelogs`;
CREATE TABLE `s_pagelogs` (
  `UID_` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `corpNo_` varchar(10) DEFAULT NULL,
  `page_` varchar(80) NOT NULL,
  `dataIn_` text,
  `tickCount_` double NOT NULL,
  `AppUser_` varchar(100) DEFAULT NULL,
  `createTime_` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`UID_`),
  KEY `Page_CorpNo` (`page_`,`corpNo_`,`tickCount_`)
) ENGINE=InnoDB AUTO_INCREMENT=8825 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for s_userinfo
-- ----------------------------
DROP TABLE IF EXISTS `s_userinfo`;
CREATE TABLE `s_userinfo` (
  `UID_` int(11) NOT NULL AUTO_INCREMENT,
  `corpCode_` varchar(10) DEFAULT 'EasyERP' COMMENT '公司别',
  `id_` varchar(38) DEFAULT NULL COMMENT '用户ID',
  `code_` varchar(255) DEFAULT NULL COMMENT '用户Code',
  `name_` varchar(30) DEFAULT NULL COMMENT '用户姓名',
  `roleCode_` varchar(10) DEFAULT NULL COMMENT '角色代码',
  `diyRole_` bit(1) DEFAULT b'0' COMMENT '自定义权限',
  `enabled_` bit(1) DEFAULT b'1' COMMENT '启用否',
  `emailAccount_` varchar(20) DEFAULT NULL COMMENT 'EmailAccount_',
  `remark_` text COMMENT '备注',
  `mobile_` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `qq_` varchar(18) DEFAULT NULL COMMENT 'QQ号码',
  `superUser_` bit(1) DEFAULT NULL COMMENT '超级用户',
  `corpNo_` varchar(10) NOT NULL COMMENT '企业编号',
  `ProxyUsers_` varchar(10) DEFAULT NULL COMMENT '代理用户',
  `updateUser_` varchar(100) DEFAULT NULL,
  `updateTime_` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createUser_` varchar(100) DEFAULT NULL,
  `createTime_` timestamp NULL DEFAULT NULL,
  `password_` varchar(50) DEFAULT NULL,
  `isBlack_` bit(1) DEFAULT b'0',
  `isCheckPwd_` bit(1) DEFAULT b'0' COMMENT '是否保存密码',
  `isManager_` bit(1) DEFAULT b'0' COMMENT '0:不是后台管理元员，1:是后台管理员',
  `idCard_` varchar(255) DEFAULT NULL,
  `hosId_` int(10) DEFAULT NULL COMMENT '医院ID',
  PRIMARY KEY (`UID_`),
  UNIQUE KEY `IX_Account_2` (`id_`),
  KEY `IX_Account_1` (`corpNo_`),
  KEY `UID_` (`UID_`)
) ENGINE=InnoDB AUTO_INCREMENT=3573 DEFAULT CHARSET=utf8 COMMENT='用户帐号表';

-- ----------------------------
-- Table structure for s_userlogs
-- ----------------------------
DROP TABLE IF EXISTS `s_userlogs`;
CREATE TABLE `s_userlogs` (
  `UID_` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `CorpNo_` varchar(30) DEFAULT NULL,
  `Level_` int(11) DEFAULT NULL,
  `Log_` varchar(240) DEFAULT NULL,
  `AppUser_` varchar(100) DEFAULT NULL,
  `AppDate_` datetime DEFAULT NULL,
  `UpdateKey_` varchar(114) DEFAULT NULL,
  PRIMARY KEY (`UID_`),
  KEY `CorpNo_` (`CorpNo_`,`Level_`),
  KEY `CorpNo__2` (`CorpNo_`,`AppUser_`),
  KEY `CorpNo__3` (`CorpNo_`,`AppDate_`)
) ENGINE=InnoDB AUTO_INCREMENT=188752 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for s_usermenus
-- ----------------------------
DROP TABLE IF EXISTS `s_usermenus`;
CREATE TABLE `s_usermenus` (
  `UserCode_` varchar(100) NOT NULL COMMENT '用户帐号',
  `MenuCode_` varchar(30) NOT NULL COMMENT '菜单代码',
  `It_` int(11) NOT NULL COMMENT '序',
  PRIMARY KEY (`UserCode_`,`MenuCode_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户自定义菜单';

-- ----------------------------
-- Table structure for s_usermessages
-- ----------------------------
DROP TABLE IF EXISTS `s_usermessages`;
CREATE TABLE `s_usermessages` (
  `UID_` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增UID',
  `CorpNo_` varchar(10) NOT NULL COMMENT '帐套编号',
  `UserCode_` varchar(100) NOT NULL COMMENT '接收用户',
  `Level_` int(11) NOT NULL COMMENT '消息等级',
  `Process_` int(11) DEFAULT NULL,
  `Subject_` varchar(80) DEFAULT NULL,
  `Content_` text COMMENT '内容',
  `Status_` int(11) NOT NULL DEFAULT '0' COMMENT '状态',
  `AppDate_` datetime NOT NULL COMMENT '发送时间',
  `Final_` bit(1) NOT NULL DEFAULT b'0' COMMENT '确认否',
  `AppUser_` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`UID_`),
  KEY `CORPNO_USERCODE` (`CorpNo_`,`UserCode_`),
  KEY `Level_` (`Level_`,`Process_`)
) ENGINE=InnoDB AUTO_INCREMENT=6752 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for s_useroptions
-- ----------------------------
DROP TABLE IF EXISTS `s_useroptions`;
CREATE TABLE `s_useroptions` (
  `UserCode_` varchar(100) NOT NULL,
  `Code_` varchar(30) NOT NULL,
  `Name_` varchar(80) NOT NULL,
  `Value_` varchar(255) NOT NULL,
  `UpdateUser_` varchar(100) NOT NULL,
  `UpdateDate_` datetime NOT NULL,
  `AppUser_` varchar(100) NOT NULL,
  `AppDate_` datetime NOT NULL,
  `UpdateKey_` varchar(38) NOT NULL,
  PRIMARY KEY (`UserCode_`,`Code_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户设置档';

-- ----------------------------
-- Table structure for s_userroles
-- ----------------------------
DROP TABLE IF EXISTS `s_userroles`;
CREATE TABLE `s_userroles` (
  `UID_` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `CorpType_` int(11) DEFAULT NULL COMMENT '所属版本',
  `Code_` varchar(10) DEFAULT NULL COMMENT '角色代码',
  `Name_` varchar(30) NOT NULL COMMENT '角色名称',
  `StandardRole_` varchar(10) DEFAULT NULL COMMENT '参考角色',
  `Remark_` varchar(50) DEFAULT NULL COMMENT '备注',
  `isLive_` varchar(5) DEFAULT NULL COMMENT '启停状态',
  `System_` bit(1) DEFAULT NULL COMMENT '内置角色否',
  `Default_` bit(1) DEFAULT NULL COMMENT '新用户默认',
  `UpdateUser_` varchar(100) DEFAULT NULL COMMENT '更新人员',
  `UpdateDate_` datetime DEFAULT NULL COMMENT '更新时间',
  `AppUser_` varchar(100) DEFAULT NULL COMMENT '建档人员',
  `AppDate_` datetime DEFAULT NULL COMMENT '建档时间',
  `UpdateKey_` varchar(38) DEFAULT NULL COMMENT '更新ID',
  `Hide_` bit(1) DEFAULT b'0' COMMENT '是否为隐藏角色',
  PRIMARY KEY (`UID_`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Table structure for s_userterminal
-- ----------------------------
DROP TABLE IF EXISTS `s_userterminal`;
CREATE TABLE `s_userterminal` (
  `UID_` int(11) NOT NULL AUTO_INCREMENT,
  `terminal_` varchar(50) DEFAULT NULL COMMENT '终端ID',
  `userCode_` varchar(100) DEFAULT NULL COMMENT '用户',
  `status_` bit(1) DEFAULT b'0' COMMENT '状态(0:正常，1：停用)',
  `corpNo_` varchar(10) DEFAULT NULL,
  `createTime_` datetime DEFAULT NULL COMMENT '建档时间',
  `updateTime_` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `updateUser_` varchar(100) DEFAULT NULL,
  `type_` varchar(10) DEFAULT NULL COMMENT '终端类型（app:手机APP,weixin:微信 ，codeLogin:验证码登录）',
  PRIMARY KEY (`UID_`)
) ENGINE=InnoDB AUTO_INCREMENT=1191 DEFAULT CHARSET=utf8 COMMENT='用户终端表';
