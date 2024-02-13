-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: hcms
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `admin_id` int NOT NULL AUTO_INCREMENT COMMENT '管理员id,主键',
  `admin_name` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '管理员姓名',
  `admin_gender` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '管理员性别',
  `admin_position` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '管理员职位',
  `admin_institution` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '管理员工作单位',
  `wh_id` int NOT NULL COMMENT '管理员管理仓库id,外键',
  `admin_tel` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '管理员电话',
  `admin_acct` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '管理员登录账号,一般来说也是手机号',
  `admin_pwd` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '管理员登录密码',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间',
  `isDelete` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`admin_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'王五','男','讲师','广州中医药大学中药学院',2,'13198753080','14754856065','12345678','2024-02-10 19:55:53','2024-02-10 20:45:16',0),(2,'王五','男','教授','广州中医药大学中药学院',1,'14754856065','13198753080','12345678','2024-02-11 22:04:41','2024-02-11 22:04:41',0);
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ann`
--

DROP TABLE IF EXISTS `ann`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ann` (
  `ann_id` int NOT NULL COMMENT '公告的id,主键',
  `admin_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公告的发布者',
  `admin_id` int NOT NULL COMMENT '管理员的id,外键',
  `content` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '发布内容',
  `publishdate` datetime NOT NULL COMMENT '发布日期',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间',
  `isDelete` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`ann_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ann`
--

LOCK TABLES `ann` WRITE;
/*!40000 ALTER TABLE `ann` DISABLE KEYS */;
/*!40000 ALTER TABLE `ann` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `apply`
--

DROP TABLE IF EXISTS `apply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `apply` (
  `apply_id` int NOT NULL COMMENT '申请表id,主键',
  `hctype_list` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '危化品类型申请信息列表(危化品类型id,申请数,核发数)',
  `user_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '申请人姓名',
  `user_tel` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '申请人联系电话',
  `apply_content` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '申请内容',
  `apply_purpose` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '申请用途(教学实验)',
  `resvdate` datetime NOT NULL COMMENT '预约发料日期(精确到天,类似用户向平台预约时间)',
  `apply_type` int NOT NULL COMMENT '申请类型(领用 调配使用)',
  `apply_status` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '批准状态(未通过, 批准中 ,已同意)',
  `apply_reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '审评意见',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间',
  `isDelete` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`apply_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apply`
--

LOCK TABLES `apply` WRITE;
/*!40000 ALTER TABLE `apply` DISABLE KEYS */;
/*!40000 ALTER TABLE `apply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hc`
--

DROP TABLE IF EXISTS `hc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hc` (
  `hc_id` int NOT NULL AUTO_INCREMENT COMMENT '危化品id,主键',
  `states` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '危化品的物理状态(固体,液体,气体)',
  `hc_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '危化品的名称',
  `hc_formula` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '危化品的分子式',
  `hc_remnant` int NOT NULL COMMENT '危化品的余量',
  `price` decimal(10,2) NOT NULL COMMENT '危化品单价',
  `profile` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '危化品的简介',
  `producationdate` datetime NOT NULL COMMENT '危化品的生产日期',
  `cas` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '危化品的cas编号',
  `hctype_id` int NOT NULL COMMENT '危化品类型id,外键',
  `shelflife` int NOT NULL COMMENT '危化品的保质期,以月为单位',
  `pur_id` int NOT NULL COMMENT '采购表id,外键',
  `borrowed` tinyint NOT NULL COMMENT '危化品是否借用,用以判断是否达到最大存储期限(一年)',
  `wh_id` int NOT NULL COMMENT '所在仓库id,外键',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '危化品所处状态,枚举(在存,运输,使用)',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间',
  `isDelete` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  `hc_productor` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '生产商',
  `hc_enname` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '英文名',
  PRIMARY KEY (`hc_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hc`
--

LOCK TABLES `hc` WRITE;
/*!40000 ALTER TABLE `hc` DISABLE KEYS */;
INSERT INTO `hc` VALUES (6,'','第1个危化品名','',0,0.00,'','2024-02-09 10:53:33','第1个危化品cas编号',1,0,1,0,1,'','2024-02-09 10:53:33','2024-02-09 10:53:33',0,'','first'),(7,'','第2个危化品名','',0,0.00,'','2024-02-09 10:53:33','第2个危化品cas编号',1,0,1,0,1,'','2024-02-09 10:53:33','2024-02-09 10:53:33',0,'','first'),(8,'','第3个危化品名','',0,0.00,'','2024-02-09 10:53:33','第3个危化品cas编号',1,0,1,0,1,'','2024-02-09 10:53:33','2024-02-09 10:53:33',0,'','first'),(9,'','第4个危化品名','',0,0.00,'','2024-02-09 10:53:33','第4个危化品cas编号',1,0,1,0,1,'','2024-02-09 10:53:33','2024-02-09 10:53:33',0,'','first'),(10,'','第5个危化品名','',0,0.00,'','2024-02-09 10:53:33','第5个危化品cas编号',1,0,1,0,1,'','2024-02-09 10:53:33','2024-02-09 10:53:33',0,'','first'),(11,'','第1个危化品名','',0,0.00,'','2024-02-09 10:55:10','第1个危化品cas编号',1,0,1,0,1,'','2024-02-09 10:55:10','2024-02-09 10:55:10',0,'','first'),(12,'','第2个危化品名','',0,0.00,'','2024-02-09 10:55:10','第2个危化品cas编号',1,0,1,0,1,'','2024-02-09 10:55:10','2024-02-09 10:55:10',0,'','first'),(13,'','第3个危化品名','',0,0.00,'','2024-02-09 10:55:10','第3个危化品cas编号',1,0,1,0,1,'','2024-02-09 10:55:10','2024-02-09 10:55:10',0,'','first'),(14,'','第4个危化品名','',0,0.00,'','2024-02-09 10:55:10','第4个危化品cas编号',1,0,1,0,1,'','2024-02-09 10:55:10','2024-02-09 10:55:10',0,'','first'),(15,'','第5个危化品名','',0,0.00,'','2024-02-09 10:55:10','第5个危化品cas编号',1,0,1,0,1,'','2024-02-09 10:55:10','2024-02-09 10:55:10',0,'','first'),(16,'液体','4-甲氧基苯甲酰氯','',425,40.00,'','2023-12-04 08:00:00','',3,12,2,0,1,'在存','2024-02-11 23:42:30','2024-02-11 23:42:30',0,'吉利制造',''),(17,'液体','4-甲氧基苯甲酰氯','',25,40.00,'','2023-12-04 08:00:00','',3,12,2,0,1,'在存','2024-02-11 23:42:30','2024-02-11 23:42:30',0,'吉利制造',''),(18,'液体','4-甲氧基苯甲酰氯','',25,40.00,'','2023-12-04 08:00:00','',3,12,2,0,1,'在存','2024-02-11 23:42:30','2024-02-11 23:42:30',0,'吉利制造',''),(19,'液体','4-甲氧基苯甲酰氯','',25,40.00,'','2023-12-04 08:00:00','',3,12,2,0,1,'在存','2024-02-11 23:42:30','2024-02-11 23:42:30',0,'吉利制造',''),(20,'液体','4-甲氧基苯甲酰氯','',25,40.00,'','2023-12-04 08:00:00','',3,12,2,0,1,'在存','2024-02-11 23:42:30','2024-02-11 23:42:30',0,'吉利制造','');
/*!40000 ALTER TABLE `hc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcib`
--

DROP TABLE IF EXISTS `hcib`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hcib` (
  `hcib_id` int NOT NULL AUTO_INCREMENT COMMENT '危化品入库记录id 主键',
  `hc_id` int NOT NULL COMMENT '危化品id,外键',
  `hctype_id` int NOT NULL COMMENT '危化品类型id,外键',
  `ib_id` int NOT NULL COMMENT '入库记录id,外键',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间',
  `isDelete` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`hcib_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcib`
--

LOCK TABLES `hcib` WRITE;
/*!40000 ALTER TABLE `hcib` DISABLE KEYS */;
INSERT INTO `hcib` VALUES (1,16,3,1,'2024-02-11 23:42:30','2024-02-12 14:56:27',0),(2,17,3,1,'2024-02-11 23:42:30','2024-02-12 14:56:27',0),(3,18,3,1,'2024-02-11 23:42:30','2024-02-12 14:56:27',0),(4,19,3,1,'2024-02-11 23:42:30','2024-02-12 14:56:27',0),(5,20,3,1,'2024-02-11 23:42:30','2024-02-12 14:56:27',0),(6,16,3,1,'2024-02-12 00:02:31','2024-02-12 14:56:27',0);
/*!40000 ALTER TABLE `hcib` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcob`
--

DROP TABLE IF EXISTS `hcob`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hcob` (
  `hcob_id` int NOT NULL COMMENT '危化品出库记录id,主键',
  `hc_id` int NOT NULL COMMENT '危化品id,外键',
  `hctype_id` int NOT NULL COMMENT '危化品类型id,外键',
  `ob_id` int NOT NULL COMMENT '出库记录id,外键',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间',
  `isDelete` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`hcob_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcob`
--

LOCK TABLES `hcob` WRITE;
/*!40000 ALTER TABLE `hcob` DISABLE KEYS */;
/*!40000 ALTER TABLE `hcob` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hctype`
--

DROP TABLE IF EXISTS `hctype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hctype` (
  `hctype_id` int NOT NULL AUTO_INCREMENT COMMENT '危化品类型id,主键',
  `hc_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '危化品名',
  `hc_spec` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '危化品规格',
  `hctype_limit` int NOT NULL COMMENT '危化品限制数量',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间',
  `isDelete` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  `hc_unit` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '危化品的单位(ml,g)',
  `cas` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'cas编号',
  `hc_formula` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '分子式',
  `hc_enname` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '英文名',
  `profile` varchar(1000) COLLATE utf8mb4_general_ci NOT NULL COMMENT '危化品简介',
  PRIMARY KEY (`hctype_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hctype`
--

LOCK TABLES `hctype` WRITE;
/*!40000 ALTER TABLE `hctype` DISABLE KEYS */;
INSERT INTO `hctype` VALUES (1,'盐酸','500',400,'2024-01-22 23:37:55','2024-01-22 23:37:55',0,'ml','','','',''),(2,'硫酸','500',300,'2024-01-22 23:39:37','2024-01-22 23:39:37',0,'ml','','','',''),(3,'4-甲氧基苯甲酰氯','25',100,'2024-01-22 23:41:47','2024-01-22 23:41:47',0,'g','','','',''),(4,'四氢呋喃(不含稳定剂)，ACS/HPLC级','4',50,'2024-01-22 23:42:29','2024-01-22 23:42:29',0,'L','','','',''),(7,'甲酸笨乙酯','500',400,'2024-02-11 21:13:57','2024-02-11 21:35:47',0,'ml','64-17-5','C9H10O2','Ethyl phenyl formate','这是甲酸笨乙酯的简介');
/*!40000 ALTER TABLE `hctype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ib`
--

DROP TABLE IF EXISTS `ib`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ib` (
  `ib_id` int NOT NULL AUTO_INCREMENT COMMENT '入库记录id,主键',
  `whstart_id` int NOT NULL COMMENT '起始仓库id,-1表示无,外键',
  `whend_id` int NOT NULL COMMENT '目的仓库id,-1表示无,外键',
  `teacher_id` int NOT NULL COMMENT '指导老师id,外键',
  `admina_id` int NOT NULL COMMENT '管理员a id,外键',
  `adminb_id` int NOT NULL COMMENT '管理员b id,外键',
  `user_id` int NOT NULL COMMENT '使用人id,外键',
  `hctype_id` int NOT NULL COMMENT '危化品类型id,外键',
  `ib_time` datetime NOT NULL COMMENT '入库时间',
  `ib_nums` int DEFAULT NULL COMMENT '入库数量(入了多少瓶,多少个最小单位)',
  `ib_quantity` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '入库总量(入了多少总量,500ml*3+200ml)',
  `wh_remnant` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '仓库余量(剩余多少总量,500ml*3+200ml)',
  `ib_content` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '入库目的(水仙花实验)',
  `ib_purpose` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用途,枚举类型:教学实验,期末归库,调配使用,采购入库',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
  `updataTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间',
  `isDelete` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`ib_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ib`
--

LOCK TABLES `ib` WRITE;
/*!40000 ALTER TABLE `ib` DISABLE KEYS */;
INSERT INTO `ib` VALUES (1,1,2,2,1,2,1,4,'2024-02-11 22:32:01',5,'250','50','水仙花实验','教学实验','2024-02-11 22:32:00','2024-02-12 14:56:37',0),(5,1,2,2,1,2,1,4,'2024-02-11 22:48:49',NULL,NULL,NULL,'水仙花实验2','教学实验','2024-02-11 22:48:49','2024-02-11 22:48:49',0);
/*!40000 ALTER TABLE `ib` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lab`
--

DROP TABLE IF EXISTS `lab`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lab` (
  `lab_id` int NOT NULL COMMENT '实验室id,主键',
  `lab_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '实验室名称',
  `wh_id` int NOT NULL COMMENT '所在仓库id,外键',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间',
  `isDelete` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`lab_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lab`
--

LOCK TABLES `lab` WRITE;
/*!40000 ALTER TABLE `lab` DISABLE KEYS */;
INSERT INTO `lab` VALUES (1,'药科楼B202',2,'2024-01-22 23:36:59','2024-01-22 23:36:59',0);
/*!40000 ALTER TABLE `lab` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ob`
--

DROP TABLE IF EXISTS `ob`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ob` (
  `ob_id` int NOT NULL COMMENT '出库记录id,主键',
  `whstart_id` int NOT NULL COMMENT '起始仓库id,-1表示无,外键',
  `whend_id` int NOT NULL COMMENT '目的仓库id,-1表示无,外键',
  `teacher_id` int NOT NULL COMMENT '指导老师id,外键',
  `admina_id` int NOT NULL COMMENT '管理员a id,外键',
  `adminb_id` int NOT NULL COMMENT '管理员b id,外键',
  `user_id` int NOT NULL COMMENT '使用人id,外键',
  `hctype_id` int NOT NULL COMMENT '危化品类型id,外键',
  `ob_time` datetime NOT NULL COMMENT '出库时间',
  `ob_nums` int NOT NULL COMMENT '出库数量(出了多少瓶,多少个最小单位)',
  `ob_quantity` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '出库总量(出了多少总量,500ml*3+200ml)',
  `wh_remnant` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '仓库余量(剩余多少总量,500ml*3+200ml)',
  `ob_content` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '出库目的(水仙花实验)',
  `ranout` tinyint NOT NULL DEFAULT '0' COMMENT '是否用完,只用与用户从总库领取危化品的查询',
  `ob_purpose` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用途,枚举类型:教学实验,期末归库,调配使用',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
  `updataTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间',
  `isDelete` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`ob_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ob`
--

LOCK TABLES `ob` WRITE;
/*!40000 ALTER TABLE `ob` DISABLE KEYS */;
/*!40000 ALTER TABLE `ob` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pur`
--

DROP TABLE IF EXISTS `pur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pur` (
  `pur_id` int NOT NULL AUTO_INCREMENT COMMENT '采购表的id,主键',
  `user_id` int NOT NULL COMMENT '用户表的id,外键',
  `hctype_list` varchar(1000) COLLATE utf8mb4_general_ci NOT NULL COMMENT '采购危化品类型列表(危化品品名,危险品规格采购数量,采购单价)',
  `totalprice` decimal(10,2) NOT NULL COMMENT '采购总价',
  `file` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '已通过的审批材料',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间',
  `isDelete` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`pur_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pur`
--

LOCK TABLES `pur` WRITE;
/*!40000 ALTER TABLE `pur` DISABLE KEYS */;
INSERT INTO `pur` VALUES (3,2,'[{\"list_id\":null,\"hctype_name\":\"硫酸\",\"hctype_spec\":\"500ml\",\"price\":30,\"count\":2},{\"list_id\":null,\"hctype_name\":\"盐酸\",\"hctype_spec\":\"500ml\",\"price\":30,\"count\":2}]',0.00,'https://web-louis.oss-cn-guangzhou.aliyuncs.com/eb1d0733-44de-4bc6-a0a1-ddeacd42a4bb.jpg','2024-02-11 20:49:03','2024-02-11 20:49:03',0),(4,2,'[{\"list_id\":null,\"hctype_name\":\"硫酸\",\"hctype_spec\":\"500ml\",\"price\":30,\"count\":2},{\"list_id\":null,\"hctype_name\":\"盐酸\",\"hctype_spec\":\"500ml\",\"price\":30,\"count\":2}]',120.00,'https://web-louis.oss-cn-guangzhou.aliyuncs.com/eb1d0733-44de-4bc6-a0a1-ddeacd42a4bb.jpg','2024-02-11 20:58:04','2024-02-11 20:58:04',0),(5,2,'[{\"list_id\":null,\"hctype_name\":\"硫酸\",\"hctype_spec\":\"500ml\",\"price\":30,\"count\":2},{\"list_id\":null,\"hctype_name\":\"盐酸\",\"hctype_spec\":\"500ml\",\"price\":30,\"count\":2}]',120.00,'https://web-louis.oss-cn-guangzhou.aliyuncs.com/eb1d0733-44de-4bc6-a0a1-ddeacd42a4bb.jpg','2024-02-11 20:58:04','2024-02-11 20:58:04',0),(6,2,'[{\"list_id\":null,\"hctype_name\":\"硫酸\",\"hctype_spec\":\"500ml\",\"price\":30,\"count\":2},{\"list_id\":null,\"hctype_name\":\"盐酸\",\"hctype_spec\":\"500ml\",\"price\":30,\"count\":2}]',120.00,'https://web-louis.oss-cn-guangzhou.aliyuncs.com/eb1d0733-44de-4bc6-a0a1-ddeacd42a4bb.jpg','2024-02-11 20:58:04','2024-02-11 20:58:04',0),(7,2,'[{\"list_id\":null,\"hctype_name\":\"硫酸\",\"hctype_spec\":\"500ml\",\"price\":30,\"count\":2},{\"list_id\":null,\"hctype_name\":\"盐酸\",\"hctype_spec\":\"500ml\",\"price\":30,\"count\":2}]',120.00,'https://web-louis.oss-cn-guangzhou.aliyuncs.com/eb1d0733-44de-4bc6-a0a1-ddeacd42a4bb.jpg','2024-02-11 20:58:04','2024-02-11 20:58:04',0),(8,2,'[{\"list_id\":null,\"hctype_name\":\"硫酸\",\"hctype_spec\":\"500ml\",\"price\":30,\"count\":2},{\"list_id\":null,\"hctype_name\":\"盐酸\",\"hctype_spec\":\"500ml\",\"price\":30,\"count\":2}]',120.00,'https://web-louis.oss-cn-guangzhou.aliyuncs.com/eb1d0733-44de-4bc6-a0a1-ddeacd42a4bb.jpg','2024-02-11 20:58:04','2024-02-11 20:58:04',0),(9,2,'[{\"list_id\":null,\"hctype_name\":\"硫酸\",\"hctype_spec\":\"500ml\",\"price\":30,\"count\":2},{\"list_id\":null,\"hctype_name\":\"盐酸\",\"hctype_spec\":\"500ml\",\"price\":30,\"count\":2}]',120.00,'https://web-louis.oss-cn-guangzhou.aliyuncs.com/eb1d0733-44de-4bc6-a0a1-ddeacd42a4bb.jpg','2024-02-11 20:58:04','2024-02-11 20:58:04',0);
/*!40000 ALTER TABLE `pur` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT COMMENT '用户id,主键',
  `user_name` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户姓名',
  `user_gender` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '男' COMMENT '用户性别',
  `user_position` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户职位',
  `user_institution` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户工作单位',
  `lab_id` int NOT NULL COMMENT '所属实验室id,是外键,但是并没有在数据库设计中体现',
  `user_tel` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '电话号码',
  `user_acct` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户登录账号,一般来说就是手机号',
  `user_pwd` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户登录密码',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间',
  `isDelete` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'hjy','男','实验室负责人','广中医中药学院',1,'12345678','12345678','12345678','2024-01-23 14:52:22','2024-01-23 14:52:50',0),(2,'李四','男',NULL,'广州中医药大学中药学院',1,'14754856065','14754856065','65487932bcfa8409b85ee9f3f86f2657','2024-02-10 21:30:06','2024-02-10 21:30:06',0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wh`
--

DROP TABLE IF EXISTS `wh`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wh` (
  `wh_id` int NOT NULL COMMENT '仓库id,主键',
  `wh_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '仓库名字',
  `a_id` int NOT NULL COMMENT '管理员A的id,外键',
  `b_id` int NOT NULL COMMENT '管理员B的id,外键',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间',
  `isDelete` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`wh_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wh`
--

LOCK TABLES `wh` WRITE;
/*!40000 ALTER TABLE `wh` DISABLE KEYS */;
INSERT INTO `wh` VALUES (1,'C536',1,2,'2024-01-22 23:34:36','2024-01-22 23:34:36',0),(2,'药科楼B202',3,4,'2024-01-22 23:35:17','2024-01-22 23:35:17',0);
/*!40000 ALTER TABLE `wh` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-12 14:58:59
