/*
Navicat MySQL Data Transfer

Source Server         : ding
Source Server Version : 50625
Source Host           : localhost:3306
Source Database       : onlineclass

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2017-08-13 13:52:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `adminuser`
-- ----------------------------
DROP TABLE IF EXISTS `adminuser`;
CREATE TABLE `adminuser` (
  `pk_ANumber` varchar(6) NOT NULL,
  `AKey` varchar(16) DEFAULT NULL,
  `AName` varchar(10) DEFAULT NULL,
  `Power` tinyint(1) DEFAULT '4',
  `Url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`pk_ANumber`),
  KEY `FK_adminuser` (`Power`),
  CONSTRAINT `FK_adminuser` FOREIGN KEY (`Power`) REFERENCES `powerform` (`PCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of adminuser
-- ----------------------------
INSERT INTO `adminuser` VALUES ('vip', '0', '管理员', '4', null);

-- ----------------------------
-- Table structure for `department`
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `DName` varchar(20) DEFAULT NULL,
  `pk_DId` varchar(50) NOT NULL,
  PRIMARY KEY (`pk_DId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('更多', '-1');
INSERT INTO `department` VALUES ('互联网系', '0');
INSERT INTO `department` VALUES ('金融系', '1');
INSERT INTO `department` VALUES ('保险系', '10');
INSERT INTO `department` VALUES ('经贸系', '11');
INSERT INTO `department` VALUES ('劳经系', '12');
INSERT INTO `department` VALUES ('工管系', '2');
INSERT INTO `department` VALUES ('外语系', '3');
INSERT INTO `department` VALUES ('法律系', '4');
INSERT INTO `department` VALUES ('财传系', '5');
INSERT INTO `department` VALUES ('应数系', '6');
INSERT INTO `department` VALUES ('公管系', '7');
INSERT INTO `department` VALUES ('信管系', '8');
INSERT INTO `department` VALUES ('会计系', '9');

-- ----------------------------
-- Table structure for `lesson`
-- ----------------------------
DROP TABLE IF EXISTS `lesson`;
CREATE TABLE `lesson` (
  `pk_LId` varchar(50) NOT NULL,
  `LName` varchar(20) DEFAULT NULL,
  `LInfo` varchar(50) DEFAULT NULL,
  `LIcon` varchar(100) DEFAULT NULL,
  `fk_L_TPhone` char(11) DEFAULT NULL,
  `LCount` int(255) DEFAULT NULL,
  `fk_DId` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`pk_LId`),
  KEY `FK_lesson` (`fk_L_TPhone`),
  KEY `FK_lesson1` (`fk_DId`),
  CONSTRAINT `FK_lesson` FOREIGN KEY (`fk_L_TPhone`) REFERENCES `teacheruser` (`pk_TPhone`),
  CONSTRAINT `FK_lesson1` FOREIGN KEY (`fk_DId`) REFERENCES `department` (`pk_DId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lesson
-- ----------------------------
INSERT INTO `lesson` VALUES ('13531188587C0001', '中级财务会计', null, null, '13531188587', null, '8');
INSERT INTO `lesson` VALUES ('13531188587C0002', '税法', null, null, '13531188587', null, '8');
INSERT INTO `lesson` VALUES ('13531188587C0003', '项目评估', null, null, '13531188587', null, '8');
INSERT INTO `lesson` VALUES ('15816643146C12001', '企业文化管理', null, null, '15816643146', null, '12');
INSERT INTO `lesson` VALUES ('15816643146C12002', '劳动关系学', null, null, '15816643146', null, '12');
INSERT INTO `lesson` VALUES ('15816643146C12003', '薪酬管理', null, null, '15816643146', null, '12');
INSERT INTO `lesson` VALUES ('18826077893C04001', '法学导论', '1', null, '18826077893', null, '4');
INSERT INTO `lesson` VALUES ('18826077893C04002', '法理学', '1', null, '18826077893', null, '4');
INSERT INTO `lesson` VALUES ('18826077893C04003', '民法学1', '1', null, '18826077893', null, '4');
INSERT INTO `lesson` VALUES ('18826077893C04004', '民法2', '1', null, '18826077893', null, '4');
INSERT INTO `lesson` VALUES ('18826077893C04005', '刑事诉讼法学', '1', null, '18826077893', null, '4');
INSERT INTO `lesson` VALUES ('18826077893C04006', '刑法学2', '1', null, '18826077893', null, '4');
INSERT INTO `lesson` VALUES ('18826077893C04007', '刑法学1', '1', null, '18826077893', null, '4');
INSERT INTO `lesson` VALUES ('18826077893C04008', '行政法与行政诉讼法学', '1', null, '18826077893', null, '4');
INSERT INTO `lesson` VALUES ('18826077893C04009', '宪法学', '1', null, '18826077893', null, '4');
INSERT INTO `lesson` VALUES ('18826077893C04010', '民事诉讼法学', '1', null, '18826077893', null, '4');
INSERT INTO `lesson` VALUES ('18826077893C04011', '中国法制史', '1', null, '18826077893', null, '4');
INSERT INTO `lesson` VALUES ('18826077893C04012', '国际商法', '1', null, '18826077893', null, '4');
INSERT INTO `lesson` VALUES ('18826077893C04013', '世界贸易组织法概论', '1', null, '18826077893', null, '4');
INSERT INTO `lesson` VALUES ('18826077893C04014', '法律经济学', '1', null, '18826077893', null, '4');
INSERT INTO `lesson` VALUES ('18826077893C04015', '婚姻家庭法', '1', null, '18826077893', null, '4');
INSERT INTO `lesson` VALUES ('18826077893C04016', '法律英语', '1', null, '18826077893', null, '4');
INSERT INTO `lesson` VALUES ('18826077893C04017', '商法学', '1', null, '18826077893', null, '4');
INSERT INTO `lesson` VALUES ('18826077893C04018', '国际法学', '1', null, '18826077893', null, '4');
INSERT INTO `lesson` VALUES ('18826077893C04019', '国际经济法学', '1', null, '18826077893', null, '4');
INSERT INTO `lesson` VALUES ('18826077893C04020', '国际私法学', '1', null, '18826077893', null, '4');
INSERT INTO `lesson` VALUES ('18826077893C04021', '知识产权法学', '1', null, '18826077893', null, '4');
INSERT INTO `lesson` VALUES ('18826077893C04022', '环境资源法学', '1', null, '18826077893', null, '4');
INSERT INTO `lesson` VALUES ('18826077893C04023', '劳动与社会保障法学', '1', null, '18826077893', null, '4');
INSERT INTO `lesson` VALUES ('18826077893C04024', '经济法学', '1', null, '18826077893', null, '4');
INSERT INTO `lesson` VALUES ('18826077893C04025', '法律文书写作', '1', null, '18826077893', null, '4');
INSERT INTO `lesson` VALUES ('18826077893C04026', '仲裁法律实务', '1', null, '18826077893', null, '4');
INSERT INTO `lesson` VALUES ('18826077893C04027', '司法执业技能拓展', '1', null, '18826077893', null, '4');
INSERT INTO `lesson` VALUES ('18826077893C04028', '企业法律实务', '1', null, '18826077893', null, '4');
INSERT INTO `lesson` VALUES ('18826077893C04029', '律师实务综合案例', '1', null, '18826077893', null, '4');
INSERT INTO `lesson` VALUES ('18826077893C04030', '金融法综合案例', '1', null, '18826077893', null, '4');
INSERT INTO `lesson` VALUES ('18826077893C04031', '证据法综合案例', '1', null, '18826077893', null, '4');
INSERT INTO `lesson` VALUES ('18826077893C04032', '模拟法庭综合案例', '1', null, '18826077893', null, '4');
INSERT INTO `lesson` VALUES ('18826077893C04033', '法律诊所综合案例', '1', null, '18826077893', null, '4');
INSERT INTO `lesson` VALUES ('18826077893C04034', '网络知识产权法', '1', null, '18826077893', null, '4');
INSERT INTO `lesson` VALUES ('18826077893C05001', '基础写作', '1', null, '18826077893', null, '5');
INSERT INTO `lesson` VALUES ('18826077893C05002', '现代汉语', '1', null, '18826077893', null, '5');
INSERT INTO `lesson` VALUES ('18826077893C05003', '中国现代文学史1', '1', null, '18826077893', null, '5');
INSERT INTO `lesson` VALUES ('18826077893C05004', '中国古代文学名著选读', '1', null, '18826077893', null, '5');
INSERT INTO `lesson` VALUES ('18826077893C05005', '外国文学名著选读', '1', null, '18826077893', null, '5');
INSERT INTO `lesson` VALUES ('18826077893C05006', '国学原典导读', '1', null, '18826077893', null, '5');
INSERT INTO `lesson` VALUES ('18826077893C05007', '中国现代文学史2', '1', null, '18826077893', null, '5');
INSERT INTO `lesson` VALUES ('18826077893C05008', '文学概论', '1', null, '18826077893', null, '5');
INSERT INTO `lesson` VALUES ('18826077893C05009', '语言学概论', '1', null, '18826077893', null, '5');
INSERT INTO `lesson` VALUES ('18826077893C05010', '当代文化评论', '1', null, '18826077893', null, '5');
INSERT INTO `lesson` VALUES ('18826077893C05011', 'l逻辑学', '1', null, '18826077893', null, '5');
INSERT INTO `lesson` VALUES ('18826077893C05012', '美学原理', '1', null, '18826077893', null, '5');
INSERT INTO `lesson` VALUES ('18826077893C05013', '书法', '1', null, '18826077893', null, '5');
INSERT INTO `lesson` VALUES ('18826077893C05014', '沟通与合作', '1', null, '18826077893', null, '5');
INSERT INTO `lesson` VALUES ('18826077893C05015', '社会学思想与方法', '1', null, '18826077893', null, '5');
INSERT INTO `lesson` VALUES ('18826077893C05016', '科学技术史', '1', null, '18826077893', null, '5');
INSERT INTO `lesson` VALUES ('18826077893C05017', '逻辑与批判思维', '1', null, '18826077893', null, '5');
INSERT INTO `lesson` VALUES ('18826077893C05018', '中国当代文学史1', '1', null, '18826077893', null, '5');
INSERT INTO `lesson` VALUES ('18826077893C05019', '先秦两汉魏晋南北朝文学', '1', null, '18826077893', null, '5');
INSERT INTO `lesson` VALUES ('18826077893C05020', '中国当代文学史2', '1', null, '18826077893', null, '5');
INSERT INTO `lesson` VALUES ('18826077893C05021', '唐宋文学', '1', null, '18826077893', null, '5');
INSERT INTO `lesson` VALUES ('18826077893C05022', '古代汉语', '1', null, '18826077893', null, '5');
INSERT INTO `lesson` VALUES ('18826077893C05023', '外国文学史', '1', null, '18826077893', null, '5');
INSERT INTO `lesson` VALUES ('18826077893C05024', '元明清文学', '1', null, '18826077893', null, '5');
INSERT INTO `lesson` VALUES ('18826077893C05025', '比较文学', '1', null, '18826077893', null, '5');
INSERT INTO `lesson` VALUES ('18826077893C05026', '民间文学', '1', null, '18826077893', null, '5');
INSERT INTO `lesson` VALUES ('18826077893C05027', '影视文化', '1', null, '18826077893', null, '5');
INSERT INTO `lesson` VALUES ('18826077893C05028', '应用文写作', '1', null, '18826077893', null, '5');
INSERT INTO `lesson` VALUES ('18826077893C05029', '广告策划与文案', '1', null, '18826077893', null, '5');
INSERT INTO `lesson` VALUES ('18826077893C05030', '中国古代文学批判', '1', null, '18826077893', null, '5');
INSERT INTO `lesson` VALUES ('18826077893C06001', '数学分析3', '1', null, '18826077893', null, '6');
INSERT INTO `lesson` VALUES ('18826077893C06002', '数学分析2', '1', null, '18826077893', null, '6');
INSERT INTO `lesson` VALUES ('18826077893C06003', '数学分析1', '1', null, '18826077893', null, '6');
INSERT INTO `lesson` VALUES ('18826077893C06004', '高等代数与解析几何1', '1', null, '18826077893', null, '6');
INSERT INTO `lesson` VALUES ('18826077893C06005', '高等代数与解析几何2', '1', null, '18826077893', null, '6');
INSERT INTO `lesson` VALUES ('18826077893C06006', '概率论与数理统计1', '1', null, '18826077893', null, '6');
INSERT INTO `lesson` VALUES ('18826077893C06007', '概率论与数理统计2', '1', null, '18826077893', null, '6');
INSERT INTO `lesson` VALUES ('18826077893C06008', '统计学原理', '1', null, '18826077893', null, '6');
INSERT INTO `lesson` VALUES ('18826077893C06009', '经济学C1', '1', null, '18826077893', null, '6');
INSERT INTO `lesson` VALUES ('18826077893C06010', '经济学C2', '1', null, '18826077893', null, '6');
INSERT INTO `lesson` VALUES ('18826077893C06011', '微分方程', '1', null, '18826077893', null, '6');
INSERT INTO `lesson` VALUES ('18826077893C06012', '运筹学', '1', null, '18826077893', null, '6');
INSERT INTO `lesson` VALUES ('18826077893C06013', '数学建模', '1', null, '18826077893', null, '6');
INSERT INTO `lesson` VALUES ('18826077893C06014', '应用随机课程', '1', null, '18826077893', null, '6');
INSERT INTO `lesson` VALUES ('18826077893C06015', '计量经济学', '1', null, '18826077893', null, '6');
INSERT INTO `lesson` VALUES ('18826077893C06016', '金融数学', '1', null, '18826077893', null, '6');
INSERT INTO `lesson` VALUES ('18826077893C06017', '金融风险管理', '1', null, '18826077893', null, '6');
INSERT INTO `lesson` VALUES ('18826077893C06018', '投资学', '1', null, '18826077893', null, '6');
INSERT INTO `lesson` VALUES ('18826077893C06019', '应用时间序列分析', '1', null, '18826077893', null, '6');
INSERT INTO `lesson` VALUES ('18826077893C06020', '应用多元统计分析', '1', null, '18826077893', null, '6');
INSERT INTO `lesson` VALUES ('18826077893C06021', '应用回归分析', '1', null, '18826077893', null, '6');
INSERT INTO `lesson` VALUES ('18826077893C06022', '财务会计', '1', null, '18826077893', null, '6');
INSERT INTO `lesson` VALUES ('18826077893C06023', '金融数据挖掘', '1', null, '18826077893', null, '6');
INSERT INTO `lesson` VALUES ('18826077893C06024', '证券投资分析', '1', null, '18826077893', null, '6');
INSERT INTO `lesson` VALUES ('18826077893C06025', '证券投资组合优化', '1', null, '18826077893', null, '6');
INSERT INTO `lesson` VALUES ('18826077893C06026', '衍生品定价模型', '1', null, '18826077893', null, '6');
INSERT INTO `lesson` VALUES ('18826077893C06027', '最优化方法', '1', null, '18826077893', null, '6');
INSERT INTO `lesson` VALUES ('18826077893C06028', 'MATLAB软件与应用', '1', null, '18826077893', null, '6');
INSERT INTO `lesson` VALUES ('18826077893C06029', '金融投资实务', '1', null, '18826077893', null, '6');
INSERT INTO `lesson` VALUES ('18826077893C06030', '商业银行综合业务试验', '1', null, '18826077893', null, '6');
INSERT INTO `lesson` VALUES ('18826077893C07001', '广告学', '1', null, '18826077893', null, '7');
INSERT INTO `lesson` VALUES ('18826077893C07002', '营销渠道管理', '1', null, '18826077893', null, '7');
INSERT INTO `lesson` VALUES ('18826077893C07003', '市场营销学', '1', null, '18826077893', null, '7');
INSERT INTO `lesson` VALUES ('18826077893C07004', '营销策划', '1', null, '18826077893', null, '7');
INSERT INTO `lesson` VALUES ('18826077893C07005', '市场调查', '1', null, '18826077893', null, '7');
INSERT INTO `lesson` VALUES ('18826077893C07006', '推销与销售管理', '1', null, '18826077893', null, '7');
INSERT INTO `lesson` VALUES ('18826077893C07007', '消费者行为学', '1', null, '18826077893', null, '7');
INSERT INTO `lesson` VALUES ('18826077893C07008', '物流管理', '1', null, '18826077893', null, '7');
INSERT INTO `lesson` VALUES ('18826077893C07009', '商务谈判', '1', null, '18826077893', null, '7');
INSERT INTO `lesson` VALUES ('18826077893C07010', '品牌营销', '1', null, '18826077893', null, '7');
INSERT INTO `lesson` VALUES ('18826077893C07011', '电子商务', '1', '/ClassOnline/dian_zi_shang_wu_gai_lun.png', '18826077893', null, '7');
INSERT INTO `lesson` VALUES ('18826077893C07012', '网络营销', '1', null, '18826077893', null, '7');
INSERT INTO `lesson` VALUES ('18826077893C07013', '客户关系管理', '1', null, '18826077893', null, '7');
INSERT INTO `lesson` VALUES ('18826077893C07014', '组织市场营销', '1', null, '18826077893', null, '7');
INSERT INTO `lesson` VALUES ('18826077893C07015', '服务营销', '1', null, '18826077893', null, '7');
INSERT INTO `lesson` VALUES ('18826077893C07016', '供应链管理', '1', null, '18826077893', null, '7');
INSERT INTO `lesson` VALUES ('18826077893C07017', '金融营销学', '1', null, '18826077893', null, '7');
INSERT INTO `lesson` VALUES ('18826077893C07018', '国际市场营销', '1', null, '18826077893', null, '7');
INSERT INTO `lesson` VALUES ('18826077893C07019', '产品开发与管理', '1', null, '18826077893', null, '7');
INSERT INTO `lesson` VALUES ('18826077893C07020', '公共关系学', '1', null, '18826077893', null, '7');
INSERT INTO `lesson` VALUES ('18826077893C07021', '零售学', '1', null, '18826077893', null, '7');
INSERT INTO `lesson` VALUES ('18826077893C07022', '公共关系学', '1', null, '18826077893', null, '7');
INSERT INTO `lesson` VALUES ('18826077893C07023', '金融理财规划', '1', null, '18826077893', null, '7');
INSERT INTO `lesson` VALUES ('18826077893C07024', '金融客户经理实务', '1', null, '18826077893', null, '7');
INSERT INTO `lesson` VALUES ('18826077893C07025', '金融理财综合试验', '1', null, '18826077893', null, '7');
INSERT INTO `lesson` VALUES ('18826077893C07026', '营销案例分析', '1', null, '18826077893', null, '7');
INSERT INTO `lesson` VALUES ('18826077893C07027', '市场调查与预测综合案例', '1', null, '18826077893', null, '7');
INSERT INTO `lesson` VALUES ('18826077893C07028', 'ERP综合试验', '1', null, '18826077893', null, '7');
INSERT INTO `lesson` VALUES ('18826077893C07029', '企业经营沙盘模拟', '1', null, '18826077893', null, '7');
INSERT INTO `lesson` VALUES ('18826077893C07030', 'SPSS软件应用', '1', null, '18826077893', null, '7');
INSERT INTO `lesson` VALUES ('18826079509C0006', '保险法', null, null, '18826079509', null, '10');
INSERT INTO `lesson` VALUES ('18826079509C0007', '人身保险', null, null, '18826079509', null, '10');
INSERT INTO `lesson` VALUES ('18924786526C0004', '成本会计', null, null, '18924786526', null, '9');
INSERT INTO `lesson` VALUES ('18924786526C0005', '银行会计', null, null, '18924786526', null, '9');
INSERT INTO `lesson` VALUES ('18934161756C11001', '世界贸易组织概论', null, null, '18934161756', null, '11');
INSERT INTO `lesson` VALUES ('18934161756C11002', '跨国公司概论', null, null, '18934161756', null, '11');
INSERT INTO `lesson` VALUES ('C00001', '安卓程序设计', 'Sunndy', '/ClassOnline/images/Android.jpg', '13106640371', null, '0');
INSERT INTO `lesson` VALUES ('C00002', 'JAVAWEB程序设计', 'Diny', '/ClassOnline/images/JAVAWEB.jpg', '13106640371', null, '0');
INSERT INTO `lesson` VALUES ('C01001', '金融与生活', '陈国君', '/ClassOnline/images/Finance.jpg', '13286999075', null, '1');
INSERT INTO `lesson` VALUES ('C02001', '工商管理导论', '李观印', '/ClassOnline/images/Commond.jpg', '15017387891', null, '2');
INSERT INTO `lesson` VALUES ('C03001', '英语语言基础', '李天才', '/ClassOnline/images/English.jpg', '15017387891', null, '3');

-- ----------------------------
-- Table structure for `part`
-- ----------------------------
DROP TABLE IF EXISTS `part`;
CREATE TABLE `part` (
  `pk_PId` varchar(36) NOT NULL,
  `fk_SId` varchar(34) DEFAULT NULL,
  `PName` varchar(20) DEFAULT NULL,
  `PVideoPath` varchar(100) DEFAULT NULL,
  `PTime` varchar(100) DEFAULT NULL,
  `PIcon` varchar(100) DEFAULT NULL,
  `PCount` int(255) DEFAULT NULL,
  PRIMARY KEY (`pk_PId`),
  KEY `FK_part` (`fk_SId`),
  CONSTRAINT `FK_part` FOREIGN KEY (`fk_SId`) REFERENCES `section` (`pk_SId`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of part
-- ----------------------------
INSERT INTO `part` VALUES ('13531188587C0001_1_1', '13531188587C0001_1', '1.1算钱', null, null, null, null);
INSERT INTO `part` VALUES ('13531188587C0001_1_2', '13531188587C0001_1', '1.2收钱', null, null, null, null);
INSERT INTO `part` VALUES ('13531188587C0001_1_3', '13531188587C0001_1', '1.3抢钱', null, null, null, null);
INSERT INTO `part` VALUES ('13531188587C0001_2_1', '13531188587C0001_2', '2.1省钱', null, null, null, null);
INSERT INTO `part` VALUES ('13531188587C0001_2_2', '13531188587C0001_2', '2.2生钱', null, null, null, null);
INSERT INTO `part` VALUES ('13531188587C0001_3_1', '13531188587C0001_3', '3.1到处是钱', null, null, null, null);
INSERT INTO `part` VALUES ('13531188587C0001_3_2', '13531188587C0001_3', '3.2何处怕没钱', null, null, null, null);
INSERT INTO `part` VALUES ('13531188587C0002_1_1', '13531188587C0002_1', '1.1税常识', null, null, null, null);
INSERT INTO `part` VALUES ('13531188587C0002_1_2', '13531188587C0002_1', '1.2税技巧', null, null, null, null);
INSERT INTO `part` VALUES ('13531188587C0002_2_1', '13531188587C0002_2', '2.1偷税', null, null, null, null);
INSERT INTO `part` VALUES ('13531188587C0002_2_2', '13531188587C0002_2', '2.2漏税', null, null, null, null);
INSERT INTO `part` VALUES ('13531188587C0002_3_1', '13531188587C0002_3', '2.3税666', null, null, null, null);
INSERT INTO `part` VALUES ('13531188587C0003_1_1', '13531188587C0003_1', '3.1我的项目', null, null, null, null);
INSERT INTO `part` VALUES ('15816643146C12001_1_1', '15816643146C12001_1', '1.1什么是企业文化', null, null, null, null);
INSERT INTO `part` VALUES ('15816643146C12001_1_2', '15816643146C12001_1', '1.2为什么有企业文化', null, null, null, null);
INSERT INTO `part` VALUES ('15816643146C12001_1_3', '15816643146C12001_1', '1.3企业文化的作用', null, null, null, null);
INSERT INTO `part` VALUES ('15816643146C12001_2_1', '15816643146C12001_2', '2.1如何发展企业文化', null, null, null, null);
INSERT INTO `part` VALUES ('15816643146C12001_2_2', '15816643146C12001_2', '2.2展望未来', null, null, null, null);
INSERT INTO `part` VALUES ('15816643146C12002_1_1', '15816643146C12002_1', '1.1什么是劳动经济', null, null, null, null);
INSERT INTO `part` VALUES ('15816643146C12002_1_2', '15816643146C12002_1', '1.2劳动经济常识', null, null, null, null);
INSERT INTO `part` VALUES ('15816643146C12002_2_1', '15816643146C12002_2', '2.1分配数学常识', null, null, null, null);
INSERT INTO `part` VALUES ('15816643146C12002_2_2', '15816643146C12002_2', '2.2分配效益最大化', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04001_1_1', '18826077893C04001_1', '第一小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04001_1_2', '18826077893C04001_1', '第二小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04001_1_3', '18826077893C04001_1', '第三小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04001_1_4', '18826077893C04001_1', '第四小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04001_1_5', '18826077893C04001_1', '第五小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04001_2_1', '18826077893C04001_2', '第一小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04001_2_2', '18826077893C04001_2', '第二小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04001_2_3', '18826077893C04001_2', '第三小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04001_2_4', '18826077893C04001_2', '第四小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04001_2_5', '18826077893C04001_2', '第五小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04001_3_1', '18826077893C04001_3', '第一小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04001_3_2', '18826077893C04001_3', '第二小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04001_3_3', '18826077893C04001_3', '第三小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04001_3_4', '18826077893C04001_3', '第四小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04001_3_5', '18826077893C04001_3', '第五小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04001_4_1', '18826077893C04001_4', '第一小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04001_4_2', '18826077893C04001_4', '第二小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04001_4_3', '18826077893C04001_4', '第三小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04001_4_4', '18826077893C04001_4', '第四小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04001_4_5', '18826077893C04001_4', '第五小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04001_5_1', '18826077893C04001_5', '第一小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04001_5_2', '18826077893C04001_5', '第二小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04001_5_3', '18826077893C04001_5', '第三小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04001_5_4', '18826077893C04001_5', '第四小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04001_5_5', '18826077893C04001_5', '第五小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04002_1_1', '18826077893C04002_1', '第一小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04002_1_2', '18826077893C04002_1', '第二小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04002_1_3', '18826077893C04002_1', '第三小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04002_1_4', '18826077893C04002_1', '第四小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04002_1_5', '18826077893C04002_1', '第五小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04002_2_1', '18826077893C04002_2', '第一小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04002_2_2', '18826077893C04002_2', '第二小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04002_2_3', '18826077893C04002_2', '第三小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04002_2_4', '18826077893C04002_2', '第四小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04002_2_5', '18826077893C04002_2', '第五小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04002_3_1', '18826077893C04002_3', '第一小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04002_3_2', '18826077893C04002_3', '第二小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04002_3_3', '18826077893C04002_3', '第三小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04002_3_4', '18826077893C04002_3', '第四小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04002_3_5', '18826077893C04002_3', '第五小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04002_4_1', '18826077893C04002_4', '第一小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04002_4_2', '18826077893C04002_4', '第二小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04002_4_3', '18826077893C04002_4', '第三小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04002_4_4', '18826077893C04002_4', '第四小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04002_4_5', '18826077893C04002_4', '第五小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04002_5_1', '18826077893C04002_5', '第一小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04002_5_2', '18826077893C04002_5', '第二小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04002_5_3', '18826077893C04002_5', '第三小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04002_5_4', '18826077893C04002_5', '第四小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04002_5_5', '18826077893C04002_5', '第五小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04003_1_1', '18826077893C04003_1', '第一小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04003_1_2', '18826077893C04003_1', '第二小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04003_1_3', '18826077893C04003_1', '第三小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04003_1_4', '18826077893C04003_1', '第四小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04003_1_5', '18826077893C04003_1', '第五小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04003_2_1', '18826077893C04003_2', '第一小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04003_2_2', '18826077893C04003_2', '第二小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04003_2_3', '18826077893C04003_2', '第三小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04003_2_4', '18826077893C04003_2', '第四小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04003_2_5', '18826077893C04003_2', '第五小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04003_3_1', '18826077893C04003_3', '第一小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04003_3_2', '18826077893C04003_3', '第二小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04003_3_3', '18826077893C04003_3', '第三小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04003_3_4', '18826077893C04003_3', '第四小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04003_3_5', '18826077893C04003_3', '第五小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04003_4_1', '18826077893C04003_4', '第一小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04003_4_2', '18826077893C04003_4', '第二小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04003_4_3', '18826077893C04003_4', '第三小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04003_4_4', '18826077893C04003_4', '第四小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04003_4_5', '18826077893C04003_4', '第五小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04003_5_1', '18826077893C04003_5', '第一小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04003_5_2', '18826077893C04003_5', '第二小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04003_5_3', '18826077893C04003_5', '第三小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04003_5_4', '18826077893C04003_5', '第四小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826077893C04003_5_5', '18826077893C04003_5', '第五小节', null, null, null, null);
INSERT INTO `part` VALUES ('18826079509C0006_1_1', '18826079509C0006_1', '1.1安全入门', null, null, null, null);
INSERT INTO `part` VALUES ('18826079509C0006_1_2', '18826079509C0006_1', '1.2安全出门', null, null, null, null);
INSERT INTO `part` VALUES ('18924786526C0004_1_1', '18924786526C0004_1', '1.1银行的钱', null, null, null, null);
INSERT INTO `part` VALUES ('18924786526C0004_1_2', '18924786526C0004_1', '1.2我的钱', null, null, null, null);
INSERT INTO `part` VALUES ('18924786526C0004_2_1', '18924786526C0004_2', '2.1你的钱', null, null, null, null);
INSERT INTO `part` VALUES ('18924786526C0005_1_1', '18924786526C0005_1', '1.1银行的钱', null, null, null, null);
INSERT INTO `part` VALUES ('18924786526C0005_1_2', '18924786526C0005_1', '1.2银行没钱', null, null, null, null);
INSERT INTO `part` VALUES ('18934161756C11001_1_1', '18934161756C11001_1', '1.1从0开始概述入门', null, null, null, null);
INSERT INTO `part` VALUES ('18934161756C11001_1_2', '18934161756C11001_1', '1.2你走出世界要护照', null, null, null, null);
INSERT INTO `part` VALUES ('18934161756C11001_2_1', '18934161756C11001_2', '2.1怎么出国赚钱', null, null, null, null);
INSERT INTO `part` VALUES ('18934161756C11001_2_2', '18934161756C11001_2', '2.2世界贸易精髓', null, null, null, null);
INSERT INTO `part` VALUES ('18934161756C11002_1_1', '18934161756C11002_1', '1.1古代也有跨国', null, null, null, null);
INSERT INTO `part` VALUES ('18934161756C11002_1_2', '18934161756C11002_1', '1.2现代肯定有跨国', null, null, null, null);
INSERT INTO `part` VALUES ('18934161756C11002_1_3', '18934161756C11002_1', '1.3为什么要跨国', null, null, null, null);
INSERT INTO `part` VALUES ('18934161756C11002_2_1', '18934161756C11002_2', '2.1跨国怎么做', null, null, null, null);
INSERT INTO `part` VALUES ('18934161756C11002_2_2', '18934161756C11002_2', '2.2为什么这么做', null, null, null, null);
INSERT INTO `part` VALUES ('C00001_01_01', 'C00001_01', '开发环境搭建和配置', '/video/android_one.mp4', null, null, null);
INSERT INTO `part` VALUES ('C00001_01_02', 'C00001_01', 'JDK的安装', '/video/android_two.mp4', null, null, null);
INSERT INTO `part` VALUES ('C00001_01_03', 'C00001_01', 'ADT的安装', '/video/android_three.mp4', null, null, null);
INSERT INTO `part` VALUES ('C00001_01_04', 'C00001_01', '配置JDK环境变量', '/video/android_four.mp4', null, null, null);
INSERT INTO `part` VALUES ('C00001_01_05', 'C00001_01', 'Eclipse常规配置', '/video/android_five.mp4', null, null, null);
INSERT INTO `part` VALUES ('C00001_01_06', 'C00001_01', 'Android SDK Manager', '/video/android_six.mp4', null, null, null);
INSERT INTO `part` VALUES ('C00002_01_01', 'C00002_01', '课程介绍', '/video/javaweb_one.m4v', null, null, null);
INSERT INTO `part` VALUES ('C00002_01_02', 'C00002_01', 'HTML的语法', '/video/javaweb_two.m4v', null, null, null);
INSERT INTO `part` VALUES ('C00002_01_03', 'C00002_01', 'HTML的基本结构', '/video/javaweb_three.m4v', null, null, null);
INSERT INTO `part` VALUES ('C00002_01_04', 'C00002_01', 'HTML的文档设置（上）', '/video/javaweb_four.m4v', null, null, null);
INSERT INTO `part` VALUES ('C00002_01_05', 'C00002_01', 'HTML的文档设置（下）', '/video/javaweb_five.m4v', null, null, null);
INSERT INTO `part` VALUES ('C00002_01_06', 'C00002_01', 'HTML图像标记img', '/video/javaweb_six.m4v', null, null, null);
INSERT INTO `part` VALUES ('C00002_01_07', 'C00002_01', 'HTML超链接的使用', '/video/javaweb_seven.m4v', null, null, null);
INSERT INTO `part` VALUES ('C01001_01_01', 'C01001_01', '生活中的金融', null, null, null, null);
INSERT INTO `part` VALUES ('C01001_01_02', 'C01001_01', '货币的演变', null, null, null, null);
INSERT INTO `part` VALUES ('C01001_01_03', 'C01001_01', '货币的价格', null, null, null, null);
INSERT INTO `part` VALUES ('C01001_01_04', 'C01001_01', '货币的兑换', null, null, null, null);
INSERT INTO `part` VALUES ('C01001_01_05', 'C01001_01', '汇率的波动', null, null, null, null);
INSERT INTO `part` VALUES ('C01001_01_06', 'C01001_01', '货币经营的金融机构', null, null, null, null);
INSERT INTO `part` VALUES ('C01001_01_07', 'C01001_01', '货币的困惑', null, null, null, null);
INSERT INTO `part` VALUES ('C02001_01_01', 'C02001_01', '工商管理导论', null, null, null, null);
INSERT INTO `part` VALUES ('C02001_01_02', 'C02001_01', '会计学', null, null, null, null);
INSERT INTO `part` VALUES ('C02001_01_03', 'C02001_01', '公司财务', null, null, null, null);
INSERT INTO `part` VALUES ('C02001_01_04', 'C02001_01', '市场营销', null, null, null, null);
INSERT INTO `part` VALUES ('C02001_01_05', 'C02001_01', '人力资源管理', null, null, null, null);
INSERT INTO `part` VALUES ('C02001_01_06', 'C02001_01', '电子商务', null, null, null, null);
INSERT INTO `part` VALUES ('C02001_01_07', 'C02001_01', '国际商务', null, null, null, null);
INSERT INTO `part` VALUES ('C02001_01_08', 'C02001_01', '服务管理', null, null, null, null);
INSERT INTO `part` VALUES ('C02001_01_09', 'C02001_01', '战略，创业与创新', null, null, null, null);
INSERT INTO `part` VALUES ('C03001_01_01', 'C03001_01', '英语的演变', null, null, null, null);
INSERT INTO `part` VALUES ('C03001_01_02', 'C03001_01', '英语词的构成', null, null, null, null);
INSERT INTO `part` VALUES ('C03001_01_03', 'C03001_01', '英语语法', null, null, null, null);

-- ----------------------------
-- Table structure for `partexchange`
-- ----------------------------
DROP TABLE IF EXISTS `partexchange`;
CREATE TABLE `partexchange` (
  `fk_PId` varchar(36) DEFAULT NULL,
  `pk_PEId` varchar(32) NOT NULL,
  `PEByWho` varchar(10) DEFAULT NULL,
  `PEByWho_Id` char(11) DEFAULT NULL,
  `PEContent` varchar(80) DEFAULT NULL,
  `PETime` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`pk_PEId`),
  KEY `FK_partexchange` (`fk_PId`),
  CONSTRAINT `FK_partexchange` FOREIGN KEY (`fk_PId`) REFERENCES `part` (`pk_PId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of partexchange
-- ----------------------------
INSERT INTO `partexchange` VALUES ('C00001_01_01', '17AC464F8E3B44479CE136D7474224F1', '老李', null, '啊啊啊啊', '2015年12月04日   19:55:41     ');
INSERT INTO `partexchange` VALUES ('13531188587C0001_1_1', '222', null, '12222212345', '你好啊哈哈哈', null);
INSERT INTO `partexchange` VALUES ('C00001_01_06', '3343F8EDFD054D93BDEA968609A19BF5', '老李', null, 'aa', '2015年12月04日   01:48:04     ');
INSERT INTO `partexchange` VALUES ('C00001_01_01', '759D2041E9A846C6B7165C2D3E23FBF9', '老李', null, 'aaa', '2015年12月04日   13:40:35     ');
INSERT INTO `partexchange` VALUES ('C00001_01_06', '7F72795F24FA4372A4E25D7C0E1B6EF2', '老李', null, 'bb', '2015年12月04日   01:48:14     ');
INSERT INTO `partexchange` VALUES ('C00001_01_06', 'D98EA92C2C2D49AA917E7EB9B99CD807', '老李', null, 'A', '2015年12月04日   02:01:21     ');

-- ----------------------------
-- Table structure for `partresponse`
-- ----------------------------
DROP TABLE IF EXISTS `partresponse`;
CREATE TABLE `partresponse` (
  `fk_PEId` char(32) DEFAULT NULL,
  `pk_PRId` char(32) NOT NULL,
  `PR_ToWho` char(11) DEFAULT NULL,
  `PR_ByWho` char(11) DEFAULT NULL,
  `PRContent` varchar(80) DEFAULT NULL,
  `PR_ByWho_Name` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`pk_PRId`),
  KEY `FK_partresponse` (`fk_PEId`),
  CONSTRAINT `FK_partresponse` FOREIGN KEY (`fk_PEId`) REFERENCES `partexchange` (`pk_PEId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of partresponse
-- ----------------------------
INSERT INTO `partresponse` VALUES ('222', '333', null, null, null, null);
INSERT INTO `partresponse` VALUES ('222', 'C306', null, null, null, null);
INSERT INTO `partresponse` VALUES ('222', 'C3069', null, null, null, null);

-- ----------------------------
-- Table structure for `powerform`
-- ----------------------------
DROP TABLE IF EXISTS `powerform`;
CREATE TABLE `powerform` (
  `PCode` tinyint(1) NOT NULL,
  `PName` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`PCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of powerform
-- ----------------------------
INSERT INTO `powerform` VALUES ('0', '学生');
INSERT INTO `powerform` VALUES ('1', '学生助');
INSERT INTO `powerform` VALUES ('2', '教师');
INSERT INTO `powerform` VALUES ('3', '二级a');
INSERT INTO `powerform` VALUES ('4', 'adm');

-- ----------------------------
-- Table structure for `powerurlform`
-- ----------------------------
DROP TABLE IF EXISTS `powerurlform`;
CREATE TABLE `powerurlform` (
  `PCode` tinyint(1) DEFAULT NULL,
  `PUrl` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of powerurlform
-- ----------------------------

-- ----------------------------
-- Table structure for `section`
-- ----------------------------
DROP TABLE IF EXISTS `section`;
CREATE TABLE `section` (
  `pk_SId` varchar(34) NOT NULL,
  `fk_LId` varchar(32) DEFAULT NULL,
  `SName` varchar(20) DEFAULT NULL,
  `SInfo` varchar(50) DEFAULT NULL,
  `SCount` int(255) DEFAULT NULL,
  `SIcon` varchar(100) DEFAULT NULL,
  `STime` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`pk_SId`),
  KEY `FK_section` (`fk_LId`),
  CONSTRAINT `FK_section` FOREIGN KEY (`fk_LId`) REFERENCES `lesson` (`pk_LId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of section
-- ----------------------------
INSERT INTO `section` VALUES ('13531188587C0001_1', '13531188587C0001', '1财会入门', null, null, null, null);
INSERT INTO `section` VALUES ('13531188587C0001_2', '13531188587C0001', '2财会精通', null, null, null, null);
INSERT INTO `section` VALUES ('13531188587C0001_3', '13531188587C0001', '3财会毕业', null, null, null, null);
INSERT INTO `section` VALUES ('13531188587C0002_1', '13531188587C0002', '1税收常识', null, null, null, null);
INSERT INTO `section` VALUES ('13531188587C0002_2', '13531188587C0002', '2税务精通', null, null, null, null);
INSERT INTO `section` VALUES ('13531188587C0002_3', '13531188587C0002', '3税务毕业', null, null, null, null);
INSERT INTO `section` VALUES ('13531188587C0003_1', '13531188587C0003', '1项目经理AVI', null, null, null, null);
INSERT INTO `section` VALUES ('15816643146C12001_1', '15816643146C12001', '第一章:企业文化概述', null, null, null, null);
INSERT INTO `section` VALUES ('15816643146C12001_2', '15816643146C12001', '第二章:企业文化发展', null, null, null, null);
INSERT INTO `section` VALUES ('15816643146C12002_1', '15816643146C12002', '第一章：劳动经济概述', null, null, null, null);
INSERT INTO `section` VALUES ('15816643146C12002_2', '15816643146C12002', '第二章：劳动经济分配', null, null, null, null);
INSERT INTO `section` VALUES ('18826077893C04001_1', '18826077893C04001', '第一章', null, null, null, null);
INSERT INTO `section` VALUES ('18826077893C04001_2', '18826077893C04001', '第二章', null, null, null, null);
INSERT INTO `section` VALUES ('18826077893C04001_3', '18826077893C04001', '第三章', null, null, null, null);
INSERT INTO `section` VALUES ('18826077893C04001_4', '18826077893C04001', '第四章', null, null, null, null);
INSERT INTO `section` VALUES ('18826077893C04001_5', '18826077893C04001', '第五章', null, null, null, null);
INSERT INTO `section` VALUES ('18826077893C04002_1', '18826077893C04002', '第一章', null, null, null, null);
INSERT INTO `section` VALUES ('18826077893C04002_2', '18826077893C04002', '第二章', null, null, null, null);
INSERT INTO `section` VALUES ('18826077893C04002_3', '18826077893C04002', '第三章', null, null, null, null);
INSERT INTO `section` VALUES ('18826077893C04002_4', '18826077893C04002', '第四章', null, null, null, null);
INSERT INTO `section` VALUES ('18826077893C04002_5', '18826077893C04002', '第五章', null, null, null, null);
INSERT INTO `section` VALUES ('18826077893C04003_1', '18826077893C04003', '第一章', null, null, null, null);
INSERT INTO `section` VALUES ('18826077893C04003_2', '18826077893C04003', '第二章', null, null, null, null);
INSERT INTO `section` VALUES ('18826077893C04003_3', '18826077893C04003', '第三章', null, null, null, null);
INSERT INTO `section` VALUES ('18826077893C04003_4', '18826077893C04003', '第四章', null, null, null, null);
INSERT INTO `section` VALUES ('18826077893C04003_5', '18826077893C04003', '第五章', null, null, null, null);
INSERT INTO `section` VALUES ('18826079509C0006_1', '18826079509C0006', '第1章:你安全吗', null, null, null, null);
INSERT INTO `section` VALUES ('18826079509C0006_2', '18826079509C0006', '第2章:你安全', null, null, null, null);
INSERT INTO `section` VALUES ('18826079509C0007_1', '18826079509C0007', '1：你的人值多少钱', null, null, null, null);
INSERT INTO `section` VALUES ('18826079509C0007_2', '18826079509C0007', '2：人生安全', null, null, null, null);
INSERT INTO `section` VALUES ('18924786526C0004_1', '18924786526C0004', '1算钱入门', null, null, null, null);
INSERT INTO `section` VALUES ('18924786526C0004_2', '18924786526C0004', '2算钱中级', null, null, null, null);
INSERT INTO `section` VALUES ('18924786526C0005_1', '18924786526C0005', '第1章:银行账单', null, null, null, null);
INSERT INTO `section` VALUES ('18924786526C0005_2', '18924786526C0005', '第2章:财务系统', null, null, null, null);
INSERT INTO `section` VALUES ('18934161756C11001_1', '18934161756C11001', '第一章:走出世界', null, null, null, null);
INSERT INTO `section` VALUES ('18934161756C11001_2', '18934161756C11001', '第二章:从世界走回来', null, null, null, null);
INSERT INTO `section` VALUES ('18934161756C11002_1', '18934161756C11002', '第一章:跨国通史', null, null, null, null);
INSERT INTO `section` VALUES ('18934161756C11002_2', '18934161756C11002', '第二章:现代丝绸之路', null, null, null, null);
INSERT INTO `section` VALUES ('C00001_01', 'C00001', 'Android App开发环境搭建和配置', null, null, null, null);
INSERT INTO `section` VALUES ('C00002_01', 'C00002', 'Web前端开发之HTML+CSS基础入门', null, null, null, null);
INSERT INTO `section` VALUES ('C01001_01', 'C01001', '金融与生活', null, null, null, null);
INSERT INTO `section` VALUES ('C02001_01', 'C02001', '工商管理导论', null, null, null, null);
INSERT INTO `section` VALUES ('C03001_01', 'C03001', '英语语言基础', null, null, null, null);

-- ----------------------------
-- Table structure for `studentuser`
-- ----------------------------
DROP TABLE IF EXISTS `studentuser`;
CREATE TABLE `studentuser` (
  `pk_SPhone` char(11) NOT NULL,
  `SKey` varchar(16) DEFAULT NULL,
  `Url` varchar(255) DEFAULT NULL,
  `Power` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`pk_SPhone`),
  KEY `FK_studentuser` (`Power`),
  CONSTRAINT `FK_studentuser` FOREIGN KEY (`Power`) REFERENCES `powerform` (`PCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of studentuser
-- ----------------------------
INSERT INTO `studentuser` VALUES ('', '', null, '0');
INSERT INTO `studentuser` VALUES ('13511111111', '111111', null, '0');
INSERT INTO `studentuser` VALUES ('13531155452', '111111', null, '0');
INSERT INTO `studentuser` VALUES ('13531155453', '111111', null, '0');
INSERT INTO `studentuser` VALUES ('13531155457', '111111', null, '0');
INSERT INTO `studentuser` VALUES ('13532255457', 'aaaaaa', null, '0');
INSERT INTO `studentuser` VALUES ('1555554578', '2', null, '0');
INSERT INTO `studentuser` VALUES ('18654575845', '222222', null, '0');
INSERT INTO `studentuser` VALUES ('18826622245', '111111', null, '0');
INSERT INTO `studentuser` VALUES ('18826655555', '111111', null, '0');
INSERT INTO `studentuser` VALUES ('1888888882', '222222', null, '0');
INSERT INTO `studentuser` VALUES ('1888888883', '222222', null, '0');
INSERT INTO `studentuser` VALUES ('1888888884', '222222', null, '0');
INSERT INTO `studentuser` VALUES ('1888888885', '222222', null, '0');

-- ----------------------------
-- Table structure for `studentuserinfo`
-- ----------------------------
DROP TABLE IF EXISTS `studentuserinfo`;
CREATE TABLE `studentuserinfo` (
  `pk_SPhone` char(11) NOT NULL,
  `SNo` char(9) DEFAULT NULL,
  `SName` varchar(10) DEFAULT NULL,
  `SHeadIcon` varchar(100) DEFAULT NULL,
  `SAge` tinyint(3) DEFAULT NULL,
  `SGender` tinyint(1) DEFAULT NULL,
  `SNickName` varchar(20) DEFAULT NULL,
  `SSchool` varchar(20) DEFAULT NULL,
  `SDepartment` varchar(50) DEFAULT NULL,
  `SMajor` varchar(10) DEFAULT NULL,
  `SClass` varchar(10) DEFAULT NULL,
  `SDefaultPhone` char(11) DEFAULT NULL,
  `SEmail` varchar(35) DEFAULT NULL,
  `SRegistTime` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`pk_SPhone`),
  KEY `FK_studentuserinfo` (`SDepartment`),
  CONSTRAINT `AA_studentuserinfo` FOREIGN KEY (`pk_SPhone`) REFERENCES `studentuser` (`pk_SPhone`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_studentuserinfo` FOREIGN KEY (`SDepartment`) REFERENCES `department` (`pk_DId`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of studentuserinfo
-- ----------------------------
INSERT INTO `studentuserinfo` VALUES ('', null, '', null, '0', '0', null, null, '7', null, null, null, null, null);
INSERT INTO `studentuserinfo` VALUES ('13511111111', null, '老李', null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `studentuserinfo` VALUES ('13532255457', null, '呜呜呜', null, '0', '0', null, null, '8', null, null, null, null, null);
INSERT INTO `studentuserinfo` VALUES ('18654575845', null, '去去去', null, '0', '0', null, null, '8', null, null, null, null, null);
INSERT INTO `studentuserinfo` VALUES ('1888888882', null, '老李', null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `studentuserinfo` VALUES ('1888888883', null, '大李', null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `studentuserinfo` VALUES ('1888888884', null, '杀杀杀', null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `studentuserinfo` VALUES ('1888888885', null, '啊啊啊', null, null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `student_lesson`
-- ----------------------------
DROP TABLE IF EXISTS `student_lesson`;
CREATE TABLE `student_lesson` (
  `LId` varchar(32) DEFAULT NULL,
  `SPhone` char(11) DEFAULT NULL,
  KEY `FK_student_lesson` (`LId`),
  KEY `FK_student_lesson_` (`SPhone`),
  CONSTRAINT `FK_student_lesson` FOREIGN KEY (`LId`) REFERENCES `lesson` (`pk_LId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_student_lesson_` FOREIGN KEY (`SPhone`) REFERENCES `studentuser` (`pk_SPhone`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student_lesson
-- ----------------------------
INSERT INTO `student_lesson` VALUES ('13531188587C0001', '1888888882');
INSERT INTO `student_lesson` VALUES ('13531188587C0001', '1888888883');
INSERT INTO `student_lesson` VALUES ('13531188587C0001', '1888888884');
INSERT INTO `student_lesson` VALUES ('13531188587C0001', '1888888885');
INSERT INTO `student_lesson` VALUES ('13531188587C0001', '18826655555');
INSERT INTO `student_lesson` VALUES ('13531188587C0001', '18826622245');
INSERT INTO `student_lesson` VALUES ('13531188587C0001', '1555554578');
INSERT INTO `student_lesson` VALUES ('13531188587C0002', '13531155457');
INSERT INTO `student_lesson` VALUES ('13531188587C0002', '13531155452');
INSERT INTO `student_lesson` VALUES ('13531188587C0002', '13531155452');
INSERT INTO `student_lesson` VALUES ('13531188587C0002', '13531155452');
INSERT INTO `student_lesson` VALUES ('13531188587C0002', '13531155453');
INSERT INTO `student_lesson` VALUES ('13531188587C0003', '13532255457');
INSERT INTO `student_lesson` VALUES ('13531188587C0001', '18654575845');
INSERT INTO `student_lesson` VALUES ('15816643146C12003', '13511111111');
INSERT INTO `student_lesson` VALUES ('18826077893C04002', '13511111111');
INSERT INTO `student_lesson` VALUES ('C00001', '13511111111');
INSERT INTO `student_lesson` VALUES ('C00002', '13511111111');
INSERT INTO `student_lesson` VALUES ('18826077893C07022', '');
INSERT INTO `student_lesson` VALUES ('18826077893C07022', '');
INSERT INTO `student_lesson` VALUES ('18826077893C07022', '');
INSERT INTO `student_lesson` VALUES ('18826077893C07022', '');
INSERT INTO `student_lesson` VALUES ('18826077893C07022', '');

-- ----------------------------
-- Table structure for `teacheruser`
-- ----------------------------
DROP TABLE IF EXISTS `teacheruser`;
CREATE TABLE `teacheruser` (
  `pk_TPhone` char(11) NOT NULL,
  `TKey` varchar(16) DEFAULT NULL,
  `TName` varchar(10) DEFAULT NULL,
  `Power` tinyint(1) DEFAULT NULL,
  `Url` varchar(100) DEFAULT NULL,
  `TIsPass` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`pk_TPhone`),
  KEY `FK_teacheruser` (`Power`),
  CONSTRAINT `FK_teacheruser` FOREIGN KEY (`Power`) REFERENCES `powerform` (`PCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacheruser
-- ----------------------------
INSERT INTO `teacheruser` VALUES ('13106640371', 'LGY13106640371', '李观印', '2', null, '1');
INSERT INTO `teacheruser` VALUES ('13286999075', 'LGY13106640371', '李天才', '2', null, '1');
INSERT INTO `teacheruser` VALUES ('13531188587', '222222', '王伟森', '3', null, '1');
INSERT INTO `teacheruser` VALUES ('13532265656', '111111', '啊啊', '0', null, '0');
INSERT INTO `teacheruser` VALUES ('13532266564', '111111', '老大哈哈', '0', null, '0');
INSERT INTO `teacheruser` VALUES ('13545565758', '222222', '老李啊', '0', null, '0');
INSERT INTO `teacheruser` VALUES ('13555555554', '123456', '老李', '2', null, '1');
INSERT INTO `teacheruser` VALUES ('13599193221', '111111', '1359919322', '0', null, '0');
INSERT INTO `teacheruser` VALUES ('15017387891', 'LGY13106640371', '李狗哥', '2', null, '1');
INSERT INTO `teacheruser` VALUES ('15816643146', '222222', '某某', '2', null, '1');
INSERT INTO `teacheruser` VALUES ('18826077893', '123', '丁永辉', '3', null, '1');
INSERT INTO `teacheruser` VALUES ('18826079509', '222222', '王韦森', '2', null, '1');
INSERT INTO `teacheruser` VALUES ('18924786526', '222222', '森森', '2', null, '1');
INSERT INTO `teacheruser` VALUES ('18934161756', '222222', '王森', '2', null, '1');
INSERT INTO `teacheruser` VALUES ('18934161788', '1234567', '呃呃呃呃', '0', null, '1');
INSERT INTO `teacheruser` VALUES ('18934256465', '111111', '老师', '0', null, '0');
INSERT INTO `teacheruser` VALUES ('18999999999', '1111111', '1111111', '2', null, '1');

-- ----------------------------
-- Table structure for `teacher_department`
-- ----------------------------
DROP TABLE IF EXISTS `teacher_department`;
CREATE TABLE `teacher_department` (
  `TPhone` char(11) NOT NULL,
  `DId` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`TPhone`),
  KEY `FK_teacher_lesson1` (`DId`),
  CONSTRAINT `FK_teacher_lesson` FOREIGN KEY (`TPhone`) REFERENCES `teacheruser` (`pk_TPhone`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_teacher_lesson1` FOREIGN KEY (`DId`) REFERENCES `department` (`pk_DId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher_department
-- ----------------------------
INSERT INTO `teacher_department` VALUES ('13532265656', '0');
INSERT INTO `teacher_department` VALUES ('13532266564', '0');
INSERT INTO `teacher_department` VALUES ('13545565758', '0');
INSERT INTO `teacher_department` VALUES ('13599193221', '0');
INSERT INTO `teacher_department` VALUES ('18934256465', '0');
INSERT INTO `teacher_department` VALUES ('18999999999', '0');
INSERT INTO `teacher_department` VALUES ('18826079509', '10');
INSERT INTO `teacher_department` VALUES ('18934161756', '11');
INSERT INTO `teacher_department` VALUES ('15816643146', '12');
INSERT INTO `teacher_department` VALUES ('18826077893', '4');
INSERT INTO `teacher_department` VALUES ('13531188587', '8');
INSERT INTO `teacher_department` VALUES ('18924786526', '9');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` varchar(255) NOT NULL,
  `user_password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('123qsc', 'sdfdf');
INSERT INTO `user` VALUES ('2332rtre', 'dfgdfs');
INSERT INTO `user` VALUES ('324876tytu', 'fgfdgse543');
INSERT INTO `user` VALUES ('436dfe', 'ewrwe43534');
INSERT INTO `user` VALUES ('45645ytuty', 'erwqrwqcrw343');
INSERT INTO `user` VALUES ('768yi', '234ere');
INSERT INTO `user` VALUES ('ding', '123');
INSERT INTO `user` VALUES ('dingqweert', 'qwer123');
INSERT INTO `user` VALUES ('qqqq', 'qqq123');
INSERT INTO `user` VALUES ('qwertyuiop', 'qwertyuiop');
INSERT INTO `user` VALUES ('retertedfgsd', 'reteerte');
INSERT INTO `user` VALUES ('sssssss', 'qqqqqqq');
INSERT INTO `user` VALUES ('tyruyt56756', '56756tu');
INSERT INTO `user` VALUES ('uiouoi', 'jdsjkhfjsk');
INSERT INTO `user` VALUES ('zxcvbnm', 'dfgdfg');

-- ----------------------------
-- Table structure for `user_info`
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `user_id` varchar(255) NOT NULL,
  `user_email` varchar(255) NOT NULL,
  `user_birth` varchar(10) NOT NULL,
  `user_head` varchar(255) NOT NULL,
  `user_homepage` varchar(255) NOT NULL,
  `user_language` varchar(255) NOT NULL,
  `user_language_count` int(11) NOT NULL,
  `user_phone` varchar(11) NOT NULL,
  `user_nationality` varchar(255) NOT NULL COMMENT '国籍',
  `user_postcode` int(11) NOT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('123qsc', '1661780928@qq.com', '1995/07/17', '论文格式_标准模板.doc', 'hao123.com', 'French-Spanish-Chinese-', '3', '18826077893', 'AL', '525300', 'dsfsdf');
INSERT INTO `user_info` VALUES ('2332rtre', '1661780928@qq.com', '1995/07/17', '论文调研报告.doc', 'hao123.com', 'French-Spanish-Chinese-', '2', '18826077893', 'AK', '525300', 'dfdsa');
INSERT INTO `user_info` VALUES ('324876tytu', '1661780928@qq.com', '1995/07/17', '论文：调研报告格式.doc', '4353', 'Spanish-Chinese-', '2', '18826077893', 'AR', '525300', null);
INSERT INTO `user_info` VALUES ('436dfe', '1661780928@qq.com', '1995/07/17', '200字项目简介.docx', '4354353', '-Chinese-Spanish', '1', '18826077893', 'AK', '324234', '435345');
INSERT INTO `user_info` VALUES ('45645ytuty', '1661780928@qq.com', '1995/07/17', '调研报告论文_参考.doc', 'hao123.com', 'Spanish-Chinese-', '2', '18826077893', 'AL', '525300', '23421');
INSERT INTO `user_info` VALUES ('768yi', '1661780928@qq.com', '1995/07/17', '市场调查分析论文.doc', 'hao123.com', 'French-Spanish-Chinese-', '3', '18826077893', 'AK', '525300', 'dfdsa');
INSERT INTO `user_info` VALUES ('dingqweert', '1661780928@qq.com', '1995/07/17', '200字项目简介.docx', 'hao123.com', '[Ljava.lang.String;@2f3985ba', '2', '18826077893', 'AK', '525300', 'WERWER');
INSERT INTO `user_info` VALUES ('qwertyuiop', '1661780928@qq.com', '1995/07/17', '论文格式_标准模板.doc', 'hao123.com', 'French-Spanish-Chinese-English-', '4', '18826077893', 'AL', '525300', 'xcgdfgdg');
INSERT INTO `user_info` VALUES ('retertedfgsd', '1661780928@qq.com', '1995/07/17', '论文格式_标准模板.doc', 'hao123.com', 'Spanish-Chinese-', '2', '18826077893', 'AK', '525300', '固定童儿童');
INSERT INTO `user_info` VALUES ('sssssss', '1661780928@qq.com', '1995/07/17', '市场调查分析论文.doc', '4353', 'Spanish-Chinese-', '2', '18826077893', 'AR', '525300', null);
INSERT INTO `user_info` VALUES ('tyruyt56756', '1661780928@qq.com', '1995/07/17', '调研报告、论文.doc', 'hao123.com', 'Spanish-Chinese-', '2', '18826077893', 'AL', '525300', '天热也热吧');
INSERT INTO `user_info` VALUES ('uiouoi', '1661780928@qq.com', '1995/07/17', '论文格式_标准模板.doc', 'hao123.com', 'Spanish-Chinese-', '2', '18826077893', 'AK', '525300', ' ');
INSERT INTO `user_info` VALUES ('zxcvbnm', '1661780928@qq.com', '1995/07/17', '调研报告、论文.doc', 'hao123.com', 'Spanish-Chinese-', '2', '18826077893', 'CO', '525300', '规范的帅哥');
