/*
Navicat MySQL Data Transfer

Source Server         : 60.205.205.221_revolver
Source Server Version : 50723
Source Host           : 60.205.205.221:3306
Source Database       : survey

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2019-01-25 10:10:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admins
-- ----------------------------
DROP TABLE IF EXISTS `admins`;
CREATE TABLE `admins` (
  `ADMIN_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ADMIN_NAME` varchar(255) DEFAULT NULL,
  `ADMIN_PWD` varchar(255) DEFAULT NULL,
  `AUTHORITY_STR` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ADMIN_ID`),
  UNIQUE KEY `UK_668icv3i3o5rjiql9q6bybx8i` (`ADMIN_NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admins
-- ----------------------------
INSERT INTO `admins` VALUES ('1', 'SuperAdmin', 'admin', '536870911,3');
INSERT INTO `admins` VALUES ('2', '02VZ950', 'SV30bH69', null);
INSERT INTO `admins` VALUES ('3', 'B4UB873', 'DEWC4769', null);
INSERT INTO `admins` VALUES ('4', '3VXA471', 'RWJJ8487', null);
INSERT INTO `admins` VALUES ('5', 'caWUX50', 'XOXLZ266', null);
INSERT INTO `admins` VALUES ('6', 'F4HcE69', '3U3a1T78', null);
INSERT INTO `admins` VALUES ('7', 'YDV9M54', '9RK11M55', null);
INSERT INTO `admins` VALUES ('8', 'M4TSV81', 'SXUJXG71', null);
INSERT INTO `admins` VALUES ('9', 'LPHXZ54', 'W4KO8652', null);
INSERT INTO `admins` VALUES ('10', '1VXF297', '4B6ZCb75', null);
INSERT INTO `admins` VALUES ('11', '8J8MQ78', 'ASQXL886', null);

-- ----------------------------
-- Table structure for admin_role_inner
-- ----------------------------
DROP TABLE IF EXISTS `admin_role_inner`;
CREATE TABLE `admin_role_inner` (
  `ADMIN_ID` int(11) NOT NULL,
  `ROLE_ID` int(11) NOT NULL,
  PRIMARY KEY (`ADMIN_ID`,`ROLE_ID`),
  KEY `FK_rh09mfo7vcvierau6sb4yo50x` (`ROLE_ID`),
  KEY `FK_ep2s0123t3dlw39lp2h3mo3tx` (`ADMIN_ID`),
  CONSTRAINT `FK_ep2s0123t3dlw39lp2h3mo3tx` FOREIGN KEY (`ADMIN_ID`) REFERENCES `admins` (`ADMIN_ID`),
  CONSTRAINT `FK_rh09mfo7vcvierau6sb4yo50x` FOREIGN KEY (`ROLE_ID`) REFERENCES `roles` (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_role_inner
-- ----------------------------
INSERT INTO `admin_role_inner` VALUES ('1', '1');

-- ----------------------------
-- Table structure for answers
-- ----------------------------
DROP TABLE IF EXISTS `answers`;
CREATE TABLE `answers` (
  `ANSWER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `SURVEY_ID` int(11) DEFAULT NULL,
  `QUESTION_ID` int(11) DEFAULT NULL,
  `ANSWER_TIME` datetime DEFAULT NULL,
  `UUID` varchar(255) DEFAULT NULL,
  `MAIN_ANSWERS` varchar(255) DEFAULT NULL,
  `OTHER_ANSWERS` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ANSWER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of answers
-- ----------------------------
INSERT INTO `answers` VALUES ('1', '1', '1', '2019-01-25 09:57:32', 'c28e0e7a-88f5-44be-9925-45147bdb7466', '1_1,0_0,2_2', null);
INSERT INTO `answers` VALUES ('2', '2', '2', '2019-01-25 10:01:56', 'ef9e16ec-b3d2-4854-9d95-2b393373ecc6', '0_0,0_1,2_1,3_1', null);

-- ----------------------------
-- Table structure for authoritys
-- ----------------------------
DROP TABLE IF EXISTS `authoritys`;
CREATE TABLE `authoritys` (
  `AUTHORITY_ID` int(11) NOT NULL AUTO_INCREMENT,
  `AUTHORITY_NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`AUTHORITY_ID`),
  UNIQUE KEY `UK_aqwrh8mufnhg8e3q07sy2x3ud` (`AUTHORITY_NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of authoritys
-- ----------------------------
INSERT INTO `authoritys` VALUES ('1', '全部资源');

-- ----------------------------
-- Table structure for auth_role_inner
-- ----------------------------
DROP TABLE IF EXISTS `auth_role_inner`;
CREATE TABLE `auth_role_inner` (
  `ROLE_ID` int(11) NOT NULL,
  `AUTH_ID` int(11) NOT NULL,
  PRIMARY KEY (`ROLE_ID`,`AUTH_ID`),
  KEY `FK_if3r782jthor4kiu8js4ydjlc` (`AUTH_ID`),
  KEY `FK_cgdt96g591lwvg2d9337yo1qd` (`ROLE_ID`),
  CONSTRAINT `FK_cgdt96g591lwvg2d9337yo1qd` FOREIGN KEY (`ROLE_ID`) REFERENCES `roles` (`ROLE_ID`),
  CONSTRAINT `FK_if3r782jthor4kiu8js4ydjlc` FOREIGN KEY (`AUTH_ID`) REFERENCES `authoritys` (`AUTHORITY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_role_inner
-- ----------------------------
INSERT INTO `auth_role_inner` VALUES ('1', '1');
INSERT INTO `auth_role_inner` VALUES ('2', '1');

-- ----------------------------
-- Table structure for bags
-- ----------------------------
DROP TABLE IF EXISTS `bags`;
CREATE TABLE `bags` (
  `BAG_ID` int(11) NOT NULL AUTO_INCREMENT,
  `BAG_NAME` varchar(255) DEFAULT NULL,
  `BAG_ORDER` int(11) DEFAULT NULL,
  `SURVEY_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`BAG_ID`),
  KEY `FK_m4f4nk0r5krin9qx7qpthgkau` (`SURVEY_ID`),
  CONSTRAINT `FK_m4f4nk0r5krin9qx7qpthgkau` FOREIGN KEY (`SURVEY_ID`) REFERENCES `surveys` (`SURVEY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bags
-- ----------------------------
INSERT INTO `bags` VALUES ('1', 'asdf', '1', '1');
INSERT INTO `bags` VALUES ('2', '我的', '2', '2');

-- ----------------------------
-- Table structure for logs
-- ----------------------------
DROP TABLE IF EXISTS `logs`;
CREATE TABLE `logs` (
  `LOG_ID` int(11) NOT NULL AUTO_INCREMENT,
  `OPERATOR` varchar(255) DEFAULT NULL,
  `OPERATE_TIME` varchar(255) DEFAULT NULL,
  `METHOD_NAME` varchar(255) DEFAULT NULL,
  `METHOD_TYPE` varchar(255) DEFAULT NULL,
  `METHOD_ARGS` varchar(255) DEFAULT NULL,
  `METHOD_RETURN_VALUE` varchar(255) DEFAULT NULL,
  `METHOD_RESULT_MSG` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`LOG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of logs
-- ----------------------------

-- ----------------------------
-- Table structure for logs_2019_1
-- ----------------------------
DROP TABLE IF EXISTS `logs_2019_1`;
CREATE TABLE `logs_2019_1` (
  `LOG_ID` int(11) NOT NULL AUTO_INCREMENT,
  `OPERATOR` varchar(255) DEFAULT NULL,
  `OPERATE_TIME` varchar(255) DEFAULT NULL,
  `METHOD_NAME` varchar(255) DEFAULT NULL,
  `METHOD_TYPE` varchar(255) DEFAULT NULL,
  `METHOD_ARGS` varchar(255) DEFAULT NULL,
  `METHOD_RETURN_VALUE` varchar(255) DEFAULT NULL,
  `METHOD_RESULT_MSG` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`LOG_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=113 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of logs_2019_1
-- ----------------------------
INSERT INTO `logs_2019_1` VALUES ('1', '[游客]', '2019年01月24日 18:24:40', 'saveEntity', 'com.revolver.survey.base.i.BaseService', '[Resource [resourceName=前往页面_用户_注册, resPos=0, resCode=4]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('2', '[游客]', '2019年01月24日 18:24:59', 'saveEntity', 'com.revolver.survey.base.i.BaseService', '[Resource [resourceName=用户_注册, resPos=0, resCode=8]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('3', '[游客]', '2019年01月24日 18:25:18', 'saveEntity', 'com.revolver.survey.base.i.BaseService', '[Resource [resourceName=管理员_前往管理员主, resPos=0, resCode=16]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('4', '[游客]', '2019年01月25日 09:01:38', 'saveEntity', 'com.revolver.survey.base.i.BaseService', '[Resource [resourceName=管理员_登录, resPos=0, resCode=32]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('5', '[游客]', '2019年01月25日 09:01:38', 'login', 'com.revolver.survey.admin.component.service.i.AdminService', '[Admin [adminName=SuperAdmin, authorityStr=null]]', 'Admin [adminName=SuperAdmin, authorityStr=null]', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('6', '[管理员][SuperAdmin]', '2019年01月25日 09:01:46', 'saveEntity', 'com.revolver.survey.base.i.BaseService', '[Resource [resourceName=管理员_生成管理员, resPos=0, resCode=64]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('7', '[管理员][SuperAdmin]', '2019年01月25日 09:01:46', 'batchGeenrateAdmin', 'com.revolver.survey.admin.component.service.i.AdminService', '[]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('8', '[管理员][SuperAdmin]', '2019年01月25日 09:01:47', 'saveEntity', 'com.revolver.survey.base.i.BaseService', '[Resource [resourceName=管理员_显示管理员, resPos=0, resCode=128]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('9', '[管理员][SuperAdmin]', '2019年01月25日 09:01:52', 'saveEntity', 'com.revolver.survey.base.i.BaseService', '[Resource [resourceName=管理员_角色管理, resPos=0, resCode=256]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('10', '[管理员][SuperAdmin]', '2019年01月25日 09:01:59', 'saveEntity', 'com.revolver.survey.base.i.BaseService', '[Resource [resourceName=角色_前往创建页面, resPos=0, resCode=512]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('11', '[管理员][SuperAdmin]', '2019年01月25日 09:02:07', 'saveEntity', 'com.revolver.survey.base.i.BaseService', '[Resource [resourceName=权限_前往创建页面, resPos=0, resCode=1024]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('12', '[管理员][SuperAdmin]', '2019年01月25日 09:02:28', 'saveEntity', 'com.revolver.survey.base.i.BaseService', '[Resource [resourceName=资源_显示全部资源, resPos=0, resCode=2048]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('13', '[管理员][SuperAdmin]', '2019年01月25日 09:02:45', 'saveEntity', 'com.revolver.survey.base.i.BaseService', '[Resource [resourceName=日志_显示日志, resPos=0, resCode=4096]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('14', '[管理员][SuperAdmin]', '2019年01月25日 09:03:35', 'saveEntity', 'com.revolver.survey.base.i.BaseService', '[Resource [resourceName=用户_登录, resPos=0, resCode=8192]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('15', '[管理员][SuperAdmin]', '2019年01月25日 09:03:36', 'login', 'com.revolver.survey.guest.component.service.i.UserService', '[User [userId=null, userName=SuperAdmin, nickName=null, email=null, payStatus=false, authorityStr=null]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('16', '[管理员][SuperAdmin]', '2019年01月25日 09:03:43', 'login', 'com.revolver.survey.guest.component.service.i.UserService', '[User [userId=null, userName=SuperAdmin, nickName=null, email=null, payStatus=false, authorityStr=null]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('17', '[管理员][SuperAdmin]', '2019年01月25日 09:06:57', 'login', 'com.revolver.survey.admin.component.service.i.AdminService', '[Admin [adminName=SuperAdmin, authorityStr=null]]', 'Admin [adminName=SuperAdmin, authorityStr=null]', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('18', '[管理员][SuperAdmin]', '2019年01月25日 09:07:08', 'saveEntity', 'com.revolver.survey.base.i.BaseService', '[Resource [resourceName=权限_创建, resPos=0, resCode=16384]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('19', '[管理员][SuperAdmin]', '2019年01月25日 09:07:08', 'saveEntity', 'com.revolver.survey.base.i.BaseService', '[Authority [authorityId=null, authorityName=普通登录用户]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('20', '[管理员][SuperAdmin]', '2019年01月25日 09:07:12', 'saveEntity', 'com.revolver.survey.base.i.BaseService', '[Resource [resourceName=权限_显示权限, resPos=0, resCode=32768]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('21', '[管理员][SuperAdmin]', '2019年01月25日 09:07:15', 'saveEntity', 'com.revolver.survey.base.i.BaseService', '[Resource [resourceName=权限_资源管理, resPos=0, resCode=65536]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('22', '[管理员][SuperAdmin]', '2019年01月25日 09:07:52', 'saveEntity', 'com.revolver.survey.base.i.BaseService', '[Resource [resourceName=权限_批量保存资源, resPos=0, resCode=131072]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('23', '[管理员][SuperAdmin]', '2019年01月25日 09:07:52', 'batchSaveRes', 'com.revolver.survey.admin.component.service.i.AuthorityService', '[1, [3, 2, 13, 15, 11, 16, 17, 4, 14, 5, 8, 7, 6, 9, 10, 1, 12]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('24', '[管理员][SuperAdmin]', '2019年01月25日 09:08:15', 'saveEntity', 'com.revolver.survey.base.i.BaseService', '[Resource [resourceName=角色_创建, resPos=0, resCode=262144]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('25', '[管理员][SuperAdmin]', '2019年01月25日 09:08:15', 'saveEntity', 'com.revolver.survey.base.i.BaseService', '[Role [roleId=null, roleName=普通登录用户]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('26', '[管理员][SuperAdmin]', '2019年01月25日 09:08:19', 'saveEntity', 'com.revolver.survey.base.i.BaseService', '[Resource [resourceName=角色_显示角色, resPos=0, resCode=524288]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('27', '[管理员][SuperAdmin]', '2019年01月25日 09:08:21', 'saveEntity', 'com.revolver.survey.base.i.BaseService', '[Resource [resourceName=角色_权限管理, resPos=0, resCode=1048576]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('28', '[管理员][SuperAdmin]', '2019年01月25日 09:08:24', 'saveEntity', 'com.revolver.survey.base.i.BaseService', '[Resource [resourceName=角色_批量保存权限, resPos=0, resCode=2097152]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('29', '[管理员][SuperAdmin]', '2019年01月25日 09:08:25', 'batchSaveAuth', 'com.revolver.survey.admin.component.service.i.RoleService', '[1, [1]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('30', '[管理员][SuperAdmin]', '2019年01月25日 09:08:42', 'saveEntity', 'com.revolver.survey.base.i.BaseService', '[Resource [resourceName=管理员_退出登录, resPos=0, resCode=4194304]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('31', '[游客]', '2019年01月25日 09:09:11', 'login', 'com.revolver.survey.guest.component.service.i.UserService', '[User [userId=null, userName=abc, nickName=null, email=null, payStatus=false, authorityStr=null]]', 'User [userId=1, userName=abc, nickName=abc, email=abc, payStatus=false, authorityStr=131071]', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('32', '[用户][abc]', '2019年01月25日 09:09:32', 'saveEntity', 'com.revolver.survey.base.i.BaseService', '[Resource [resourceName=用户_退出登录, resPos=0, resCode=8388608]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('33', '[用户][abc]', '2019年01月25日 09:09:45', 'login', 'com.revolver.survey.admin.component.service.i.AdminService', '[Admin [adminName=SuperAdmin, authorityStr=null]]', 'Admin [adminName=SuperAdmin, authorityStr=null]', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('34', '[用户][abc]', '2019年01月25日 09:10:05', 'batchSaveRes', 'com.revolver.survey.admin.component.service.i.AuthorityService', '[1, [3, 2, 13, 15, 11, 18, 16, 17, 4, 14, 24, 5, 8, 7, 6, 9, 23, 19, 10, 22, 20, 21, 1, 12]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('35', '[用户][abc]', '2019年01月25日 09:10:24', 'saveEntity', 'com.revolver.survey.base.i.BaseService', '[Resource [resourceName=excel_显示全部调查, resPos=0, resCode=16777216]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('36', '[用户][abc]', '2019年01月25日 09:10:48', 'saveEntity', 'com.revolver.survey.base.i.BaseService', '[Resource [resourceName=前往页面_用户_个人中心, resPos=0, resCode=33554432]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('37', '[游客]', '2019年01月25日 09:11:36', 'login', 'com.revolver.survey.guest.component.service.i.UserService', '[User [userId=null, userName=abc, nickName=null, email=null, payStatus=false, authorityStr=null]]', 'User [userId=1, userName=abc, nickName=abc, email=abc, payStatus=false, authorityStr=131071]', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('38', '[用户][abc]', '2019年01月25日 09:11:55', 'login', 'com.revolver.survey.admin.component.service.i.AdminService', '[Admin [adminName=SuperAdmin, authorityStr=null]]', 'Admin [adminName=SuperAdmin, authorityStr=null]', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('39', '[用户][abc]', '2019年01月25日 09:12:37', 'batchSaveRes', 'com.revolver.survey.admin.component.service.i.AuthorityService', '[1, [25, 26, 3, 2, 13, 15, 11, 18, 16, 17, 4, 14, 24, 5, 8, 7, 6, 9, 23, 19, 10, 22, 20, 21, 1, 12]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('40', '[游客]', '2019年01月25日 09:14:05', 'login', 'com.revolver.survey.guest.component.service.i.UserService', '[User [userId=null, userName=abc, nickName=null, email=null, payStatus=false, authorityStr=null]]', 'User [userId=1, userName=abc, nickName=abc, email=abc, payStatus=false, authorityStr=131071]', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('41', '[用户][abc]', '2019年01月25日 09:14:26', 'saveEntity', 'com.revolver.survey.base.i.BaseService', '[Resource [resourceName=参与_查找全部可用的调查, resPos=0, resCode=67108864]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('42', '[用户][abc]', '2019年01月25日 09:14:37', 'login', 'com.revolver.survey.admin.component.service.i.AdminService', '[Admin [adminName=SuperAdmin, authorityStr=null]]', 'Admin [adminName=SuperAdmin, authorityStr=null]', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('43', '[用户][abc]', '2019年01月25日 09:14:44', 'saveEntity', 'com.revolver.survey.base.i.BaseService', '[Resource [resourceName=管理员_批量calculate权限, resPos=0, resCode=134217728]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('44', '[用户][abc]', '2019年01月25日 09:14:44', 'batchCalculateAuth', 'com.revolver.survey.admin.component.service.i.AdminService', '[]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('45', '[用户][abc]', '2019年01月25日 09:15:37', 'saveEntity', 'com.revolver.survey.base.i.BaseService', '[Resource [resourceName=管理员_批量保存角色, resPos=0, resCode=268435456]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('46', '[用户][abc]', '2019年01月25日 09:15:37', 'batchSaveRole', 'com.revolver.survey.admin.component.service.i.AdminService', '[1, [1]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('47', '[用户][abc]', '2019年01月25日 09:15:59', 'batchSaveRes', 'com.revolver.survey.admin.component.service.i.AuthorityService', '[1, [25, 26, 3, 2, 27, 13, 15, 11, 18, 16, 17, 4, 14, 24, 5, 28, 29, 8, 7, 6, 9, 23, 19, 10, 22, 20, 21, 1, 12]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('48', '[游客]', '2019年01月25日 09:21:49', 'login', 'com.revolver.survey.guest.component.service.i.UserService', '[User [userId=null, userName=abc, nickName=null, email=null, payStatus=false, authorityStr=null]]', 'User [userId=1, userName=abc, nickName=abc, email=abc, payStatus=false, authorityStr=67108863]', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('49', '[用户][abc]', '2019年01月25日 09:21:55', 'saveEntity', 'com.revolver.survey.base.i.BaseService', '[Resource [resourceName=前往页面_用户_充值, resPos=1, resCode=1]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('50', '[用户][abc]', '2019年01月25日 09:23:20', 'saveEntity', 'com.revolver.survey.base.i.BaseService', '[Resource [resourceName=前往页面_用户_续费, resPos=1, resCode=2]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('51', '[用户][abc]', '2019年01月25日 09:26:01', 'login', 'com.revolver.survey.admin.component.service.i.AdminService', '[Admin [adminName=SuperAdmin, authorityStr=null]]', 'Admin [adminName=SuperAdmin, authorityStr=null]', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('52', '[用户][abc]', '2019年01月25日 09:26:25', 'batchSaveRes', 'com.revolver.survey.admin.component.service.i.AuthorityService', '[1, [25, 26, 30, 3, 2, 31, 27, 13, 15, 11, 18, 16, 17, 4, 14, 24, 5, 28, 29, 8, 7, 6, 9, 23, 19, 10, 22, 20, 21, 1, 12]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('53', '[游客]', '2019年01月25日 09:26:49', 'login', 'com.revolver.survey.guest.component.service.i.UserService', '[User [userId=null, userName=abc, nickName=null, email=null, payStatus=false, authorityStr=null]]', 'User [userId=1, userName=abc, nickName=abc, email=abc, payStatus=false, authorityStr=67108863]', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('54', '[用户][abc]', '2019年01月25日 09:30:13', 'login', 'com.revolver.survey.admin.component.service.i.AdminService', '[Admin [adminName=SuperAdmin, authorityStr=null]]', 'Admin [adminName=SuperAdmin, authorityStr=null]', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('55', '[用户][abc]', '2019年01月25日 09:30:23', 'batchCalculateAuth', 'com.revolver.survey.admin.component.service.i.AdminService', '[]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('56', '[用户][abc]', '2019年01月25日 09:30:33', 'batchCalculateAuth', 'com.revolver.survey.admin.component.service.i.AdminService', '[]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('57', '[用户][abc]', '2019年01月25日 09:33:06', 'batchSaveRes', 'com.revolver.survey.admin.component.service.i.AuthorityService', '[1, [25, 26, 30, 3, 2, 31, 27, 13, 15, 11, 18, 16, 17, 4, 14, 24, 5, 28, 29, 8, 7, 6, 9, 23, 19, 10, 22, 20, 21, 1, 12]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('58', '[游客]', '2019年01月25日 09:35:38', 'login', 'com.revolver.survey.guest.component.service.i.UserService', '[User [userId=null, userName=abc, nickName=null, email=null, payStatus=false, authorityStr=null]]', 'User [userId=1, userName=abc, nickName=abc, email=abc, payStatus=false, authorityStr=536870911,3]', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('59', '[用户][abc]', '2019年01月25日 09:36:10', 'saveEntity', 'com.revolver.survey.base.i.BaseService', '[Resource [resourceName=用户_充值, resPos=1, resCode=4]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('60', '[用户][abc]', '2019年01月25日 09:36:28', 'login', 'com.revolver.survey.admin.component.service.i.AdminService', '[Admin [adminName=SuperAdmin, authorityStr=null]]', 'Admin [adminName=SuperAdmin, authorityStr=null]', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('61', '[用户][abc]', '2019年01月25日 09:36:43', 'batchSaveRes', 'com.revolver.survey.admin.component.service.i.AuthorityService', '[1, [25, 26, 30, 3, 2, 31, 27, 13, 15, 11, 18, 16, 17, 32, 4, 14, 24, 5, 28, 29, 8, 7, 6, 9, 23, 19, 10, 22, 20, 21, 1, 12]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('62', '[游客]', '2019年01月25日 09:36:52', 'login', 'com.revolver.survey.guest.component.service.i.UserService', '[User [userId=null, userName=SuperAdmin, nickName=null, email=null, payStatus=false, authorityStr=null]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('63', '[游客]', '2019年01月25日 09:37:00', 'login', 'com.revolver.survey.guest.component.service.i.UserService', '[User [userId=null, userName=abc, nickName=null, email=null, payStatus=false, authorityStr=null]]', 'User [userId=1, userName=abc, nickName=abc, email=abc, payStatus=false, authorityStr=536870911,3]', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('64', '[游客]', '2019年01月25日 09:38:14', 'login', 'com.revolver.survey.guest.component.service.i.UserService', '[User [userId=null, userName=abc, nickName=null, email=null, payStatus=false, authorityStr=null]]', 'User [userId=1, userName=abc, nickName=abc, email=abc, payStatus=false, authorityStr=536870911,3]', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('65', '[游客]', '2019年01月25日 09:38:43', 'login', 'com.revolver.survey.admin.component.service.i.AdminService', '[Admin [adminName=SuperAdmin, authorityStr=null]]', 'Admin [adminName=SuperAdmin, authorityStr=null]', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('66', '[游客]', '2019年01月25日 09:47:45', 'login', 'com.revolver.survey.guest.component.service.i.UserService', '[User [userId=null, userName=abc, nickName=null, email=null, payStatus=false, authorityStr=null]]', 'User [userId=1, userName=abc, nickName=abc, email=abc, payStatus=false, authorityStr=536870911,3]', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('67', '[游客]', '2019年01月25日 09:51:40', 'login', 'com.revolver.survey.guest.component.service.i.UserService', '[User [userId=null, userName=abc, nickName=null, email=null, payStatus=false, authorityStr=null]]', 'User [userId=1, userName=abc, nickName=abc, email=abc, payStatus=false, authorityStr=536870911,3]', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('68', '[用户][abc]', '2019年01月25日 09:51:50', 'updateEntity', 'com.revolver.survey.base.i.BaseService', '[User [userId=1, userName=abc, nickName=abc, email=abc, payStatus=false, authorityStr=536870911,3]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('69', '[用户][abc]', '2019年01月25日 09:51:56', 'saveEntity', 'com.revolver.survey.base.i.BaseService', '[Resource [resourceName=用户_续费, resPos=1, resCode=8]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('70', '[用户][abc]', '2019年01月25日 09:52:25', 'login', 'com.revolver.survey.admin.component.service.i.AdminService', '[Admin [adminName=SuperAdmin, authorityStr=null]]', 'Admin [adminName=SuperAdmin, authorityStr=null]', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('71', '[用户][abc]', '2019年01月25日 09:52:41', 'batchSaveRes', 'com.revolver.survey.admin.component.service.i.AuthorityService', '[1, [25, 26, 30, 3, 2, 31, 27, 13, 15, 11, 18, 16, 17, 32, 4, 14, 33, 24, 5, 28, 29, 8, 7, 6, 9, 23, 19, 10, 22, 20, 21, 1, 12]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('72', '[管理员][SuperAdmin]', '2019年01月25日 09:53:17', 'login', 'com.revolver.survey.guest.component.service.i.UserService', '[User [userId=null, userName=abc, nickName=null, email=null, payStatus=false, authorityStr=null]]', 'User [userId=1, userName=abc, nickName=abc, email=abc, payStatus=false, authorityStr=536870911,3]', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('73', '[用户][abc]', '2019年01月25日 09:54:15', 'login', 'com.revolver.survey.admin.component.service.i.AdminService', '[Admin [adminName=SuperAdmin, authorityStr=null]]', 'Admin [adminName=SuperAdmin, authorityStr=null]', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('74', '[用户][abc]', '2019年01月25日 09:54:20', 'saveEntity', 'com.revolver.survey.base.i.BaseService', '[Role [roleId=null, roleName=付费登录用户]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('75', '[用户][abc]', '2019年01月25日 09:54:34', 'batchSaveAuth', 'com.revolver.survey.admin.component.service.i.RoleService', '[2, [1]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('76', '[游客]', '2019年01月25日 09:54:47', 'login', 'com.revolver.survey.guest.component.service.i.UserService', '[User [userId=null, userName=abc, nickName=null, email=null, payStatus=false, authorityStr=null]]', 'User [userId=1, userName=abc, nickName=abc, email=abc, payStatus=false, authorityStr=536870911,3]', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('77', '[用户][abc]', '2019年01月25日 09:55:03', 'saveEntity', 'com.revolver.survey.base.i.BaseService', '[Resource [resourceName=调查_我的已完成的调查, resPos=1, resCode=16]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('78', '[用户][abc]', '2019年01月25日 09:55:08', 'saveEntity', 'com.revolver.survey.base.i.BaseService', '[Resource [resourceName=前往页面_调查_创建, resPos=1, resCode=32]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('79', '[用户][abc]', '2019年01月25日 09:55:15', 'saveEntity', 'com.revolver.survey.base.i.BaseService', '[Resource [resourceName=调查_保存, resPos=1, resCode=64]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('80', '[用户][abc]', '2019年01月25日 09:55:15', 'saveEntity', 'com.revolver.survey.base.i.BaseService', '[Survey [surveyId=null, title=asdf, completed=false, completedTime=null]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('81', '[用户][abc]', '2019年01月25日 09:55:15', 'saveEntity', 'com.revolver.survey.base.i.BaseService', '[Resource [resourceName=调查_我的未完成的, resPos=1, resCode=128]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('82', '[用户][abc]', '2019年01月25日 09:55:22', 'saveEntity', 'com.revolver.survey.base.i.BaseService', '[Resource [resourceName=调查_设计, resPos=1, resCode=256]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('83', '[用户][abc]', '2019年01月25日 09:55:30', 'saveEntity', 'com.revolver.survey.base.i.BaseService', '[Resource [resourceName=前往页面_调查_包裹_保存, resPos=1, resCode=512]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('84', '[用户][abc]', '2019年01月25日 09:55:34', 'saveEntity', 'com.revolver.survey.base.i.BaseService', '[Resource [resourceName=包裹_保存, resPos=1, resCode=1024]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('85', '[用户][abc]', '2019年01月25日 09:55:34', 'saveEntity', 'com.revolver.survey.base.i.BaseService', '[Bag [ bagName=asdf]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('86', '[用户][abc]', '2019年01月25日 09:55:37', 'saveEntity', 'com.revolver.survey.base.i.BaseService', '[Resource [resourceName=问题_前往类型选择, resPos=1, resCode=2048]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('87', '[用户][abc]', '2019年01月25日 09:55:44', 'saveEntity', 'com.revolver.survey.base.i.BaseService', '[Resource [resourceName=问题_前往问题设计, resPos=1, resCode=4096]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('88', '[用户][abc]', '2019年01月25日 09:56:33', 'saveEntity', 'com.revolver.survey.base.i.BaseService', '[Resource [resourceName=问题_保存或更新, resPos=1, resCode=8192]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('89', '[用户][abc]', '2019年01月25日 09:56:33', 'saveOrUpdateQuestion', 'com.revolver.survey.guest.component.service.i.QuestionService', '[Question [questionId=null, questionName=矩阵单选题 , questionType=4]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('90', '[用户][abc]', '2019年01月25日 09:56:57', 'saveEntity', 'com.revolver.survey.base.i.BaseService', '[Resource [resourceName=调查_完成调查, resPos=1, resCode=16384]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('91', '[用户][abc]', '2019年01月25日 09:57:10', 'saveEntity', 'com.revolver.survey.base.i.BaseService', '[Resource [resourceName=统计_显示摘要, resPos=1, resCode=32768]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('92', '[用户][abc]', '2019年01月25日 09:57:19', 'saveEntity', 'com.revolver.survey.base.i.BaseService', '[Resource [resourceName=参与_入口, resPos=1, resCode=65536]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('93', '[用户][abc]', '2019年01月25日 09:57:31', 'saveEntity', 'com.revolver.survey.base.i.BaseService', '[Resource [resourceName=参与_执行参与, resPos=1, resCode=131072]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('94', '[用户][abc]', '2019年01月25日 09:57:31', 'parseAndSave', 'com.revolver.survey.guest.component.service.i.AnswerService', '[1, {1={submit_done=[Ljava.lang.String;@5882a94c, bagId=[Ljava.lang.String;@180d3bc4, surveyId=[Ljava.lang.String;@fe7e1a6, question1_1=[Ljava.lang.String;@9f2da29, question1_0=[Ljava.lang.String;@4477da92, question1_2=[Ljava.lang.String;@35551ac3}}]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('95', '[用户][abc]', '2019年01月25日 09:57:31', 'saveEngage', 'com.revolver.survey.guest.component.service.i.SurveyService', '[1, 1]', null, '方法执行失败: could not execute statement');
INSERT INTO `logs_2019_1` VALUES ('96', '[用户][abc]', '2019年01月25日 09:57:33', 'saveEntity', 'com.revolver.survey.base.i.BaseService', '[Resource [resourceName=统计_显示常规矩阵, resPos=1, resCode=262144]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('97', '[用户][abc]', '2019年01月25日 09:57:34', 'saveEntity', 'com.revolver.survey.base.i.BaseService', '[Resource [resourceName=统计_显示常规矩阵图表, resPos=1, resCode=524288]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('98', '[游客]', '2019年01月25日 09:58:06', 'login', 'com.revolver.survey.admin.component.service.i.AdminService', '[Admin [adminName=SuperAdmin, authorityStr=null]]', 'Admin [adminName=SuperAdmin, authorityStr=null]', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('99', '[管理员][SuperAdmin]', '2019年01月25日 09:58:35', 'batchSaveRes', 'com.revolver.survey.admin.component.service.i.AuthorityService', '[1, [25, 26, 30, 3, 2, 31, 35, 39, 40, 46, 47, 27, 13, 15, 11, 18, 16, 17, 32, 4, 14, 33, 24, 5, 28, 29, 8, 7, 6, 9, 23, 48, 49, 45, 19, 10, 22, 20, 21, 36, 1, 44, 34, 37, 38, 12, 43, 41, 42]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('100', '[管理员][SuperAdmin]', '2019年01月25日 09:58:56', 'saveEntity', 'com.revolver.survey.base.i.BaseService', '[Resource [resourceName=权限_更新, resPos=1, resCode=1048576]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('101', '[管理员][SuperAdmin]', '2019年01月25日 09:58:56', 'updateEntity', 'com.revolver.survey.base.i.BaseService', '[Authority [authorityId=1, authorityName=全部资源]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('102', '[游客]', '2019年01月25日 09:59:48', 'login', 'com.revolver.survey.guest.component.service.i.UserService', '[User [userId=null, userName=abc, nickName=null, email=null, payStatus=false, authorityStr=null]]', 'User [userId=1, userName=abc, nickName=abc, email=abc, payStatus=true, authorityStr=536870911,15]', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('103', '[用户][abc]', '2019年01月25日 10:00:30', 'saveEntity', 'com.revolver.survey.base.i.BaseService', '[Survey [surveyId=null, title=我今天怎么了, completed=false, completedTime=null]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('104', '[用户][abc]', '2019年01月25日 10:00:52', 'saveEntity', 'com.revolver.survey.base.i.BaseService', '[Bag [ bagName=我的]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('105', '[用户][abc]', '2019年01月25日 10:01:24', 'saveOrUpdateQuestion', 'com.revolver.survey.guest.component.service.i.QuestionService', '[Question [questionId=null, questionName=矩阵多选题 , questionType=5]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('106', '[用户][abc]', '2019年01月25日 10:01:56', 'parseAndSave', 'com.revolver.survey.guest.component.service.i.AnswerService', '[2, {2={submit_done=[Ljava.lang.String;@418429b6, bagId=[Ljava.lang.String;@7f783cff, question2=[Ljava.lang.String;@169b79d9, surveyId=[Ljava.lang.String;@2e05dc74}}]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('107', '[用户][abc]', '2019年01月25日 10:01:56', 'saveEngage', 'com.revolver.survey.guest.component.service.i.SurveyService', '[1, 2]', null, '方法执行失败: could not execute statement');
INSERT INTO `logs_2019_1` VALUES ('108', '[游客]', '2019年01月25日 10:02:29', 'login', 'com.revolver.survey.admin.component.service.i.AdminService', '[Admin [adminName=SuperAdmin, authorityStr=null]]', 'Admin [adminName=SuperAdmin, authorityStr=null]', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('109', '[管理员][SuperAdmin]', '2019年01月25日 10:02:37', 'saveEntity', 'com.revolver.survey.base.i.BaseService', '[Resource [resourceName=excel_导出excel, resPos=1, resCode=2097152]]', '无', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('110', '[游客]', '2019年01月25日 10:03:21', 'login', 'com.revolver.survey.guest.component.service.i.UserService', '[User [userId=null, userName=abc, nickName=null, email=null, payStatus=false, authorityStr=null]]', 'User [userId=1, userName=abc, nickName=abc, email=abc, payStatus=true, authorityStr=536870911,15]', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('111', '[游客]', '2019年01月25日 10:08:06', 'login', 'com.revolver.survey.guest.component.service.i.UserService', '[User [userId=null, userName=abc, nickName=null, email=null, payStatus=false, authorityStr=null]]', 'User [userId=1, userName=abc, nickName=abc, email=abc, payStatus=true, authorityStr=536870911,15]', '方法执行成功!');
INSERT INTO `logs_2019_1` VALUES ('112', '[用户][abc]', '2019年01月25日 10:08:17', 'updateEntity', 'com.revolver.survey.base.i.BaseService', '[User [userId=1, userName=abc, nickName=abc, email=abc, payStatus=true, authorityStr=536870911,15]]', '无', '方法执行成功!');

-- ----------------------------
-- Table structure for logs_2019_2
-- ----------------------------
DROP TABLE IF EXISTS `logs_2019_2`;
CREATE TABLE `logs_2019_2` (
  `LOG_ID` int(11) NOT NULL AUTO_INCREMENT,
  `OPERATOR` varchar(255) DEFAULT NULL,
  `OPERATE_TIME` varchar(255) DEFAULT NULL,
  `METHOD_NAME` varchar(255) DEFAULT NULL,
  `METHOD_TYPE` varchar(255) DEFAULT NULL,
  `METHOD_ARGS` varchar(255) DEFAULT NULL,
  `METHOD_RETURN_VALUE` varchar(255) DEFAULT NULL,
  `METHOD_RESULT_MSG` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`LOG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of logs_2019_2
-- ----------------------------

-- ----------------------------
-- Table structure for logs_2019_3
-- ----------------------------
DROP TABLE IF EXISTS `logs_2019_3`;
CREATE TABLE `logs_2019_3` (
  `LOG_ID` int(11) NOT NULL AUTO_INCREMENT,
  `OPERATOR` varchar(255) DEFAULT NULL,
  `OPERATE_TIME` varchar(255) DEFAULT NULL,
  `METHOD_NAME` varchar(255) DEFAULT NULL,
  `METHOD_TYPE` varchar(255) DEFAULT NULL,
  `METHOD_ARGS` varchar(255) DEFAULT NULL,
  `METHOD_RETURN_VALUE` varchar(255) DEFAULT NULL,
  `METHOD_RESULT_MSG` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`LOG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of logs_2019_3
-- ----------------------------

-- ----------------------------
-- Table structure for questions
-- ----------------------------
DROP TABLE IF EXISTS `questions`;
CREATE TABLE `questions` (
  `QUESTION_ID` int(11) NOT NULL AUTO_INCREMENT,
  `QUESTION_NAME` varchar(255) DEFAULT NULL,
  `QUESTION_TYPE` int(11) DEFAULT NULL,
  `OPTIONS` varchar(255) DEFAULT NULL,
  `BR` tinyint(1) DEFAULT NULL,
  `HAS_OTHER` tinyint(1) DEFAULT NULL,
  `OTHER_TYPE` int(11) DEFAULT NULL,
  `MATRIX_ROW_TITLES` varchar(255) DEFAULT NULL,
  `MATRIX_COL_TITLES` varchar(255) DEFAULT NULL,
  `MATRIX_OPTIONS` varchar(255) DEFAULT NULL,
  `BAG_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`QUESTION_ID`),
  KEY `FK_jhx3c7cjtsu726sahxnsw9l03` (`BAG_ID`),
  CONSTRAINT `FK_jhx3c7cjtsu726sahxnsw9l03` FOREIGN KEY (`BAG_ID`) REFERENCES `bags` (`BAG_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of questions
-- ----------------------------
INSERT INTO `questions` VALUES ('1', '矩阵单选题 ', '4', null, '0', '0', '0', '行标题标签组,行标题标签组,行标题标签组', '列标题标签组,列标题标签组,列标题标签组', null, '1');
INSERT INTO `questions` VALUES ('2', '矩阵多选题 ', '5', null, '0', '0', '0', '行标题标签组,行标题标签组,行标题标签组,行标题标签组', '列标题标签组,列标题标签组,列标题标签组', null, '2');

-- ----------------------------
-- Table structure for resources
-- ----------------------------
DROP TABLE IF EXISTS `resources`;
CREATE TABLE `resources` (
  `RESOURCE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ACTION_NAME` varchar(255) DEFAULT NULL,
  `RESOURCE_NAME` varchar(255) DEFAULT NULL,
  `RES_POS` int(11) DEFAULT NULL,
  `RES_CODE` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`RESOURCE_ID`),
  UNIQUE KEY `UK_t1har2dv5gklrxw0jw6bhxsxp` (`ACTION_NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resources
-- ----------------------------
INSERT INTO `resources` VALUES ('1', 'SurveyAction_top10', '调查_前十', '0', '1');
INSERT INTO `resources` VALUES ('2', 'ToPageAction_user_login', '前往页面_用户_登录', '0', '2');
INSERT INTO `resources` VALUES ('3', 'ToPageAction_user_regist', '前往页面_用户_注册', '0', '4');
INSERT INTO `resources` VALUES ('4', 'UserAction_regist', '用户_注册', '0', '8');
INSERT INTO `resources` VALUES ('5', 'AdminAction_toAdminMain', '管理员_前往管理员主', '0', '16');
INSERT INTO `resources` VALUES ('6', 'AdminAction_login', '管理员_登录', '0', '32');
INSERT INTO `resources` VALUES ('7', 'AdminAction_generateAdmin', '管理员_生成管理员', '0', '64');
INSERT INTO `resources` VALUES ('8', 'AdminAction_showAdmins', '管理员_显示管理员', '0', '128');
INSERT INTO `resources` VALUES ('9', 'AdminAction_roleManager', '管理员_角色管理', '0', '256');
INSERT INTO `resources` VALUES ('10', 'RoleAction_toCreatePage', '角色_前往创建页面', '0', '512');
INSERT INTO `resources` VALUES ('11', 'AuthorityAction_toCreatePage', '权限_前往创建页面', '0', '1024');
INSERT INTO `resources` VALUES ('12', 'ResourceAction_showAllResources', '资源_显示全部资源', '0', '2048');
INSERT INTO `resources` VALUES ('13', 'LogAction_showLogs', '日志_显示日志', '0', '4096');
INSERT INTO `resources` VALUES ('14', 'UserAction_login', '用户_登录', '0', '8192');
INSERT INTO `resources` VALUES ('15', 'AuthorityAction_create', '权限_创建', '0', '16384');
INSERT INTO `resources` VALUES ('16', 'AuthorityAction_showAuthorities', '权限_显示权限', '0', '32768');
INSERT INTO `resources` VALUES ('17', 'AuthorityAction_resManager', '权限_资源管理', '0', '65536');
INSERT INTO `resources` VALUES ('18', 'AuthorityAction_batchSaveRes', '权限_批量保存资源', '0', '131072');
INSERT INTO `resources` VALUES ('19', 'RoleAction_create', '角色_创建', '0', '262144');
INSERT INTO `resources` VALUES ('20', 'RoleAction_showRoles', '角色_显示角色', '0', '524288');
INSERT INTO `resources` VALUES ('21', 'RoleAction_authManager', '角色_权限管理', '0', '1048576');
INSERT INTO `resources` VALUES ('22', 'RoleAction_batchSaveAuth', '角色_批量保存权限', '0', '2097152');
INSERT INTO `resources` VALUES ('23', 'AdminAction_logout', '管理员_退出登录', '0', '4194304');
INSERT INTO `resources` VALUES ('24', 'UserAction_logout', '用户_退出登录', '0', '8388608');
INSERT INTO `resources` VALUES ('25', 'ExcelAction_showAllSurvey', 'excel_显示全部调查', '0', '16777216');
INSERT INTO `resources` VALUES ('26', 'ToPageAction_user_myCenter', '前往页面_用户_个人中心', '0', '33554432');
INSERT INTO `resources` VALUES ('27', 'EngageAction_findAllAvailableSurvey', '参与_查找全部可用的调查', '0', '67108864');
INSERT INTO `resources` VALUES ('28', 'AdminAction_batchCalculateAuth', '管理员_批量calculate权限', '0', '134217728');
INSERT INTO `resources` VALUES ('29', 'AdminAction_batchSaveRole', '管理员_批量保存角色', '0', '268435456');
INSERT INTO `resources` VALUES ('30', 'ToPageAction_user_pay', '前往页面_用户_充值', '1', '1');
INSERT INTO `resources` VALUES ('31', 'ToPageAction_user_vip', '前往页面_用户_续费', '1', '2');
INSERT INTO `resources` VALUES ('32', 'UserAction_pay', '用户_充值', '1', '4');
INSERT INTO `resources` VALUES ('33', 'UserAction_vip', '用户_续费', '1', '8');
INSERT INTO `resources` VALUES ('34', 'SurveyAction_myCompletedSurvey', '调查_我的已完成的调查', '1', '16');
INSERT INTO `resources` VALUES ('35', 'ToPageAction_survey_create', '前往页面_调查_创建', '1', '32');
INSERT INTO `resources` VALUES ('36', 'SurveyAction_save', '调查_保存', '1', '64');
INSERT INTO `resources` VALUES ('37', 'SurveyAction_myUncompleted', '调查_我的未完成的', '1', '128');
INSERT INTO `resources` VALUES ('38', 'SurveyAction_design', '调查_设计', '1', '256');
INSERT INTO `resources` VALUES ('39', 'ToPageAction_survey_bag_add', '前往页面_调查_包裹_保存', '1', '512');
INSERT INTO `resources` VALUES ('40', 'BagAction_save', '包裹_保存', '1', '1024');
INSERT INTO `resources` VALUES ('41', 'QuestionAction_toTypeChosen', '问题_前往类型选择', '1', '2048');
INSERT INTO `resources` VALUES ('42', 'QuestionAction_toQuestionDesign', '问题_前往问题设计', '1', '4096');
INSERT INTO `resources` VALUES ('43', 'QuestionAction_saveOrUpdate', '问题_保存或更新', '1', '8192');
INSERT INTO `resources` VALUES ('44', 'SurveyAction_completeSurvey', '调查_完成调查', '1', '16384');
INSERT INTO `resources` VALUES ('45', 'StatisticsAction_showSummary', '统计_显示摘要', '1', '32768');
INSERT INTO `resources` VALUES ('46', 'EngageAction_entry', '参与_入口', '1', '65536');
INSERT INTO `resources` VALUES ('47', 'EngageAction_doEngage', '参与_执行参与', '1', '131072');
INSERT INTO `resources` VALUES ('48', 'StatisticsAction_showNormalMatrix', '统计_显示常规矩阵', '1', '262144');
INSERT INTO `resources` VALUES ('49', 'StatisticsAction_showNormalMatrixChart', '统计_显示常规矩阵图表', '1', '524288');
INSERT INTO `resources` VALUES ('50', 'AuthorityAction_update', '权限_更新', '1', '1048576');
INSERT INTO `resources` VALUES ('51', 'ExcelAction_exportExcel', 'excel_导出excel', '1', '2097152');

-- ----------------------------
-- Table structure for res_auth_inner
-- ----------------------------
DROP TABLE IF EXISTS `res_auth_inner`;
CREATE TABLE `res_auth_inner` (
  `AUTH_ID` int(11) NOT NULL,
  `RES_ID` int(11) NOT NULL,
  KEY `FK_o4x90u32ihh0ls8xiowx9j3bj` (`RES_ID`),
  KEY `FK_im878fswfx125cbjktwnuufn1` (`AUTH_ID`),
  CONSTRAINT `FK_im878fswfx125cbjktwnuufn1` FOREIGN KEY (`AUTH_ID`) REFERENCES `authoritys` (`AUTHORITY_ID`),
  CONSTRAINT `FK_o4x90u32ihh0ls8xiowx9j3bj` FOREIGN KEY (`RES_ID`) REFERENCES `resources` (`RESOURCE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of res_auth_inner
-- ----------------------------
INSERT INTO `res_auth_inner` VALUES ('1', '25');
INSERT INTO `res_auth_inner` VALUES ('1', '26');
INSERT INTO `res_auth_inner` VALUES ('1', '30');
INSERT INTO `res_auth_inner` VALUES ('1', '3');
INSERT INTO `res_auth_inner` VALUES ('1', '2');
INSERT INTO `res_auth_inner` VALUES ('1', '31');
INSERT INTO `res_auth_inner` VALUES ('1', '35');
INSERT INTO `res_auth_inner` VALUES ('1', '39');
INSERT INTO `res_auth_inner` VALUES ('1', '40');
INSERT INTO `res_auth_inner` VALUES ('1', '46');
INSERT INTO `res_auth_inner` VALUES ('1', '47');
INSERT INTO `res_auth_inner` VALUES ('1', '27');
INSERT INTO `res_auth_inner` VALUES ('1', '13');
INSERT INTO `res_auth_inner` VALUES ('1', '15');
INSERT INTO `res_auth_inner` VALUES ('1', '11');
INSERT INTO `res_auth_inner` VALUES ('1', '18');
INSERT INTO `res_auth_inner` VALUES ('1', '16');
INSERT INTO `res_auth_inner` VALUES ('1', '17');
INSERT INTO `res_auth_inner` VALUES ('1', '32');
INSERT INTO `res_auth_inner` VALUES ('1', '4');
INSERT INTO `res_auth_inner` VALUES ('1', '14');
INSERT INTO `res_auth_inner` VALUES ('1', '33');
INSERT INTO `res_auth_inner` VALUES ('1', '24');
INSERT INTO `res_auth_inner` VALUES ('1', '5');
INSERT INTO `res_auth_inner` VALUES ('1', '28');
INSERT INTO `res_auth_inner` VALUES ('1', '29');
INSERT INTO `res_auth_inner` VALUES ('1', '8');
INSERT INTO `res_auth_inner` VALUES ('1', '7');
INSERT INTO `res_auth_inner` VALUES ('1', '6');
INSERT INTO `res_auth_inner` VALUES ('1', '9');
INSERT INTO `res_auth_inner` VALUES ('1', '23');
INSERT INTO `res_auth_inner` VALUES ('1', '48');
INSERT INTO `res_auth_inner` VALUES ('1', '49');
INSERT INTO `res_auth_inner` VALUES ('1', '45');
INSERT INTO `res_auth_inner` VALUES ('1', '19');
INSERT INTO `res_auth_inner` VALUES ('1', '10');
INSERT INTO `res_auth_inner` VALUES ('1', '22');
INSERT INTO `res_auth_inner` VALUES ('1', '20');
INSERT INTO `res_auth_inner` VALUES ('1', '21');
INSERT INTO `res_auth_inner` VALUES ('1', '36');
INSERT INTO `res_auth_inner` VALUES ('1', '1');
INSERT INTO `res_auth_inner` VALUES ('1', '44');
INSERT INTO `res_auth_inner` VALUES ('1', '34');
INSERT INTO `res_auth_inner` VALUES ('1', '37');
INSERT INTO `res_auth_inner` VALUES ('1', '38');
INSERT INTO `res_auth_inner` VALUES ('1', '12');
INSERT INTO `res_auth_inner` VALUES ('1', '43');
INSERT INTO `res_auth_inner` VALUES ('1', '41');
INSERT INTO `res_auth_inner` VALUES ('1', '42');

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `ROLE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ROLE_NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ROLE_ID`),
  UNIQUE KEY `UK_hu987p8v0h1h21sd4n3bv3lp2` (`ROLE_NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES ('2', '付费登录用户');
INSERT INTO `roles` VALUES ('1', '普通登录用户');

-- ----------------------------
-- Table structure for surveys
-- ----------------------------
DROP TABLE IF EXISTS `surveys`;
CREATE TABLE `surveys` (
  `SURVEY_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(255) DEFAULT NULL,
  `LOGO_PATH` varchar(255) DEFAULT NULL,
  `COMPLETED` tinyint(1) DEFAULT NULL,
  `COMPLETED_TIME` datetime DEFAULT NULL,
  `USER_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`SURVEY_ID`),
  KEY `FK_nkbcv36cm3c4hxxlb7ylj51f0` (`USER_ID`),
  CONSTRAINT `FK_nkbcv36cm3c4hxxlb7ylj51f0` FOREIGN KEY (`USER_ID`) REFERENCES `users` (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of surveys
-- ----------------------------
INSERT INTO `surveys` VALUES ('1', 'asdf', '/resources_static/logo.gif', '1', '2019-01-25 09:56:58', '1');
INSERT INTO `surveys` VALUES ('2', '我今天怎么了', '/resources_static/logo.gif', '1', '2019-01-25 10:01:40', '1');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `USER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(255) DEFAULT NULL,
  `USER_PWD` varchar(255) DEFAULT NULL,
  `NICK_NAME` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `BALANCE` int(11) DEFAULT NULL,
  `PAY_STATUS` tinyint(1) DEFAULT NULL,
  `END_TIME` bigint(20) DEFAULT NULL,
  `AUTHORITY_STR` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `UK_21q8fvry4wix31petp1awxsx9` (`USER_NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'abc', '09100589c32df40b6d69f3d7821ef727', 'abc', 'abc', '7151', '1', '1641693291680', '536870911,1048575');

-- ----------------------------
-- Table structure for user_role_inner
-- ----------------------------
DROP TABLE IF EXISTS `user_role_inner`;
CREATE TABLE `user_role_inner` (
  `USER_ID` int(11) NOT NULL,
  `ROLE_ID` int(11) NOT NULL,
  PRIMARY KEY (`USER_ID`,`ROLE_ID`),
  KEY `FK_65b7n2spp5ktdnrajilcmha18` (`ROLE_ID`),
  KEY `FK_4n76qwi62j9vfejst7kjkay9n` (`USER_ID`),
  CONSTRAINT `FK_4n76qwi62j9vfejst7kjkay9n` FOREIGN KEY (`USER_ID`) REFERENCES `users` (`USER_ID`),
  CONSTRAINT `FK_65b7n2spp5ktdnrajilcmha18` FOREIGN KEY (`ROLE_ID`) REFERENCES `roles` (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role_inner
-- ----------------------------
INSERT INTO `user_role_inner` VALUES ('1', '1');
INSERT INTO `user_role_inner` VALUES ('1', '2');
