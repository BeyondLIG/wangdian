-- MySQL dump 10.13  Distrib 5.7.18, for Linux (x86_64)
--
-- Host: localhost    Database: wangdian
-- ------------------------------------------------------
-- Server version	5.7.18-0ubuntu0.16.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `wd_admin`
--

DROP TABLE IF EXISTS `wd_admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wd_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `isDel` int(11) DEFAULT NULL,
  `loginCount` int(11) DEFAULT NULL,
  `loginTime` datetime DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wd_admin`
--

LOCK TABLES `wd_admin` WRITE;
/*!40000 ALTER TABLE `wd_admin` DISABLE KEYS */;
INSERT INTO `wd_admin` VALUES (1,0,132,'2017-04-28 14:17:06','秋殇文月','1000:1c922d1d6dec563685bb9ad512868da5:0c88cd06b6037e57cfb37bbaff0a795f514fc5c31f16763d0fec5d9b833cd17775894b46ec5fd60215ab2c19238eaa50f0075202ffd6b986aa23929353e00967',0,'yang'),(2,0,3,'2016-09-16 11:14:31','小白','1000:1c922d1d6dec563685bb9ad512868da5:0c88cd06b6037e57cfb37bbaff0a795f514fc5c31f16763d0fec5d9b833cd17775894b46ec5fd60215ab2c19238eaa50f0075202ffd6b986aa23929353e00967',0,'xiaobai');
/*!40000 ALTER TABLE `wd_admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wd_adminlogin`
--

DROP TABLE IF EXISTS `wd_adminlogin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wd_adminlogin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `adminId` int(11) DEFAULT NULL,
  `loginTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=136 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wd_adminlogin`
--

LOCK TABLES `wd_adminlogin` WRITE;
/*!40000 ALTER TABLE `wd_adminlogin` DISABLE KEYS */;
INSERT INTO `wd_adminlogin` VALUES (1,1,'2016-09-15 17:16:48'),(2,1,'2016-09-15 17:32:18'),(3,1,'2016-09-15 17:35:05'),(4,1,'2016-09-15 21:20:40'),(5,1,'2016-09-15 22:06:45'),(6,1,'2016-09-16 01:06:19'),(7,2,'2016-09-16 01:32:22'),(8,1,'2016-09-16 02:23:30'),(9,1,'2016-09-16 03:07:54'),(10,1,'2016-09-16 10:58:49'),(11,1,'2016-09-16 11:14:11'),(12,2,'2016-09-16 11:14:31'),(13,1,'2016-09-16 14:43:44'),(14,1,'2016-09-16 19:14:47'),(15,1,'2016-09-17 00:38:57'),(16,1,'2016-09-17 01:11:41'),(17,1,'2016-09-17 13:04:18'),(18,1,'2016-09-17 15:23:48'),(19,1,'2016-09-17 16:18:06'),(20,1,'2016-09-17 16:20:27'),(21,1,'2016-09-17 16:22:10'),(22,1,'2016-09-17 20:00:22'),(23,1,'2016-09-17 20:04:02'),(24,1,'2016-09-17 20:15:21'),(25,1,'2016-09-17 20:18:40'),(26,1,'2016-09-17 20:33:25'),(27,1,'2016-09-17 20:35:17'),(28,1,'2016-09-17 20:40:52'),(29,1,'2016-09-17 20:52:05'),(30,2,'2016-09-17 20:54:11'),(31,1,'2016-09-17 21:01:26'),(32,1,'2016-09-17 21:04:36'),(33,1,'2016-09-17 21:06:32'),(34,1,'2016-09-18 18:23:31'),(35,1,'2016-09-18 18:35:12'),(36,1,'2016-09-18 18:56:56'),(37,1,'2016-09-18 19:00:34'),(38,1,'2016-09-18 19:05:05'),(39,1,'2016-09-18 19:16:29'),(40,1,'2016-09-18 19:19:15'),(41,1,'2016-09-18 19:26:19'),(42,1,'2016-09-18 22:46:31'),(43,1,'2016-09-18 23:35:27'),(44,1,'2016-09-19 02:31:23'),(45,1,'2016-09-19 03:15:27'),(46,1,'2016-09-19 11:44:11'),(47,1,'2016-09-19 22:04:44'),(48,1,'2016-09-20 02:05:21'),(49,1,'2016-09-20 09:32:57'),(50,1,'2016-09-20 09:41:01'),(51,1,'2016-09-20 12:37:42'),(52,1,'2016-09-20 16:58:24'),(53,1,'2016-09-20 21:30:36'),(54,1,'2016-09-21 01:00:07'),(55,1,'2016-09-21 02:31:57'),(56,1,'2016-09-21 09:21:34'),(57,1,'2016-09-21 09:25:13'),(58,1,'2016-09-21 09:50:37'),(59,1,'2016-09-21 09:59:22'),(60,1,'2016-09-21 10:27:48'),(61,1,'2016-09-21 10:33:02'),(62,1,'2016-09-21 10:41:15'),(63,1,'2016-09-21 10:44:30'),(64,1,'2016-09-21 22:28:07'),(65,1,'2016-09-22 23:23:15'),(66,1,'2016-09-24 11:16:12'),(67,1,'2016-09-24 23:11:56'),(68,1,'2016-09-25 02:23:20'),(69,1,'2016-09-25 02:58:08'),(70,1,'2016-10-10 00:11:21'),(71,1,'2016-10-10 01:39:40'),(72,1,'2016-10-15 13:03:24'),(73,1,'2016-10-15 13:27:37'),(74,1,'2016-10-15 17:09:03'),(75,1,'2016-10-19 00:04:48'),(76,1,'2016-10-19 01:29:52'),(77,1,'2016-10-19 01:42:19'),(78,1,'2016-10-21 16:36:43'),(79,1,'2016-10-21 21:59:47'),(80,1,'2016-10-24 15:32:42'),(81,1,'2016-10-24 15:59:19'),(82,1,'2016-10-25 22:37:23'),(83,1,'2016-10-30 18:28:46'),(84,1,'2016-10-31 10:17:27'),(85,1,'2016-11-01 18:20:47'),(86,1,'2016-11-01 18:27:15'),(87,1,'2016-11-01 18:47:31'),(88,1,'2016-11-01 19:03:52'),(89,1,'2016-11-03 13:18:04'),(90,1,'2016-11-03 13:27:58'),(91,1,'2016-11-03 13:34:18'),(92,1,'2016-11-04 22:51:32'),(93,1,'2016-11-04 23:17:09'),(94,1,'2016-11-04 23:32:07'),(95,1,'2016-11-04 23:38:48'),(96,1,'2016-11-22 16:49:33'),(97,1,'2016-11-22 17:15:25'),(98,1,'2016-11-22 17:57:51'),(99,1,'2016-11-23 23:51:50'),(100,1,'2016-11-30 19:10:20'),(101,1,'2017-01-08 17:39:28'),(102,1,'2017-01-08 18:04:33'),(103,1,'2017-01-08 18:35:21'),(104,1,'2017-01-08 18:37:32'),(105,1,'2017-01-09 16:28:45'),(106,1,'2017-01-09 17:24:18'),(107,1,'2017-01-09 17:33:46'),(108,1,'2017-01-09 18:13:43'),(109,1,'2017-01-10 15:35:01'),(110,1,'2017-01-10 15:43:12'),(111,1,'2017-04-27 21:21:34'),(112,1,'2017-04-27 21:33:25'),(113,1,'2017-04-27 21:48:35'),(114,1,'2017-04-27 21:55:55'),(115,1,'2017-04-27 23:03:08'),(116,1,'2017-04-27 23:07:28'),(117,1,'2017-04-27 23:13:35'),(118,1,'2017-04-27 23:14:15'),(119,1,'2017-04-27 23:29:08'),(120,1,'2017-04-27 23:32:20'),(121,1,'2017-04-27 23:34:41'),(122,1,'2017-04-27 23:35:45'),(123,1,'2017-04-28 00:07:42'),(124,1,'2017-04-28 07:53:39'),(125,1,'2017-04-28 08:31:26'),(126,1,'2017-04-28 10:11:08'),(127,1,'2017-04-28 10:16:49'),(128,1,'2017-04-28 10:50:44'),(129,1,'2017-04-28 11:19:07'),(130,1,'2017-04-28 11:32:33'),(131,1,'2017-04-28 12:08:43'),(132,1,'2017-04-28 12:47:59'),(133,1,'2017-04-28 13:00:56'),(134,1,'2017-04-28 14:17:06'),(135,1,'2017-04-28 15:39:01');
/*!40000 ALTER TABLE `wd_adminlogin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wd_annualfee`
--

DROP TABLE IF EXISTS `wd_annualfee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wd_annualfee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fee` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wd_annualfee`
--

LOCK TABLES `wd_annualfee` WRITE;
/*!40000 ALTER TABLE `wd_annualfee` DISABLE KEYS */;
INSERT INTO `wd_annualfee` VALUES (1,365);
/*!40000 ALTER TABLE `wd_annualfee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wd_annualfee_order`
--

DROP TABLE IF EXISTS `wd_annualfee_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wd_annualfee_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fee` float DEFAULT NULL,
  `orderId` varchar(255) DEFAULT NULL,
  `shopKeeper` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `submitTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wd_annualfee_order`
--

LOCK TABLES `wd_annualfee_order` WRITE;
/*!40000 ALTER TABLE `wd_annualfee_order` DISABLE KEYS */;
INSERT INTO `wd_annualfee_order` VALUES (1,365,'lqro201704250','g10',1,'2017-04-25 16:47:16'),(2,365,'bspj201704251','gg',1,'2017-04-25 17:27:23'),(3,365,'cazw201704251811062','gg',1,'2017-04-25 18:11:07'),(4,365,'hknf201704251816533','gg',1,'2017-04-25 18:16:53'),(5,365,'ldvu201704251824134','fffff',1,'2017-04-25 18:24:13'),(6,365,'yola201704251830505','fffff',1,'2017-04-25 18:30:50'),(7,365,'yirm201704251834526','ssss',1,'2017-04-25 18:34:53'),(8,365,'mkki201704271656510','tttttt',0,'2017-04-27 16:56:51');
/*!40000 ALTER TABLE `wd_annualfee_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wd_benefit`
--

DROP TABLE IF EXISTS `wd_benefit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wd_benefit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `a` float NOT NULL,
  `b` float NOT NULL,
  `c` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wd_benefit`
--

LOCK TABLES `wd_benefit` WRITE;
/*!40000 ALTER TABLE `wd_benefit` DISABLE KEYS */;
INSERT INTO `wd_benefit` VALUES (1,0,0.4,0.6),(2,110,40,70);
/*!40000 ALTER TABLE `wd_benefit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wd_contact`
--

DROP TABLE IF EXISTS `wd_contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wd_contact` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `qq` varchar(255) DEFAULT NULL,
  `telePhone` varchar(255) DEFAULT NULL,
  `weiXin` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wd_contact`
--

LOCK TABLES `wd_contact` WRITE;
/*!40000 ALTER TABLE `wd_contact` DISABLE KEYS */;
/*!40000 ALTER TABLE `wd_contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wd_fenrun`
--

DROP TABLE IF EXISTS `wd_fenrun`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wd_fenrun` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `a` float NOT NULL,
  `b` float NOT NULL,
  `c` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wd_fenrun`
--

LOCK TABLES `wd_fenrun` WRITE;
/*!40000 ALTER TABLE `wd_fenrun` DISABLE KEYS */;
INSERT INTO `wd_fenrun` VALUES (1,0,0.3,0.6);
/*!40000 ALTER TABLE `wd_fenrun` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wd_firstphoto`
--

DROP TABLE IF EXISTS `wd_firstphoto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wd_firstphoto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `inTime` datetime DEFAULT NULL,
  `isDel` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wd_firstphoto`
--

LOCK TABLES `wd_firstphoto` WRITE;
/*!40000 ALTER TABLE `wd_firstphoto` DISABLE KEYS */;
INSERT INTO `wd_firstphoto` VALUES (1,'2016-09-19 03:16:15',0,'图片1','uploads/2016091903/homepage-img1.jpg'),(2,'2016-09-19 03:16:40',0,'图片2','uploads/2016091903/homepage-img2.jpg'),(3,'2016-09-19 03:16:57',0,'图片3','uploads/2016091903/homepage-img1.jpg'),(4,'2016-09-19 03:17:15',0,'图片4','uploads/2016091903/homepage-img2.jpg');
/*!40000 ALTER TABLE `wd_firstphoto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wd_orders`
--

DROP TABLE IF EXISTS `wd_orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wd_orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `firstCost` float NOT NULL,
  `isDel` int(11) DEFAULT NULL,
  `kuaiDiDanHao` varchar(255) DEFAULT NULL,
  `liuYan` varchar(255) DEFAULT NULL,
  `ordersCost` float NOT NULL,
  `profits` float NOT NULL,
  `shopKeeper` varchar(255) DEFAULT NULL,
  `shopOrderId` varchar(255) DEFAULT NULL,
  `shopOrderMan` varchar(255) DEFAULT NULL,
  `shopOrderTime` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `yunFei` float NOT NULL,
  `receiver` varchar(255) DEFAULT NULL,
  `receiverPhone` varchar(255) DEFAULT NULL,
  `zipCode` varchar(255) DEFAULT NULL,
  `isDelFromUser` int(11) DEFAULT NULL,
  `user` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wd_orders`
--

LOCK TABLES `wd_orders` WRITE;
/*!40000 ALTER TABLE `wd_orders` DISABLE KEYS */;
INSERT INTO `wd_orders` VALUES (1,'湖北省武汉市洪山区珞南街道文秀街升升公寓',100.2,0,'',NULL,120.5,20.3,'yang','tnbl201610240001','yang','2016-10-24 15:51:34',0,'13212703452',0,'yang','13212703452',NULL,0,NULL),(2,'13212703452',100.2,0,'',NULL,120.5,20.3,'yang','hrwt201610250001','yang','2016-10-25 20:21:46',0,'13212703452',0,'yang','13212703452',NULL,0,NULL),(3,'湖北省武汉市洪山区珞南街道文秀街升升公寓',100.2,0,'',NULL,120.5,20.3,'yang','ieja201610250002','yang','2016-10-25 22:32:36',0,'13212703452',0,'小黑','13212703452',NULL,0,NULL),(4,'湖北省武汉市洪山区珞南街道文秀街升升公寓',100.2,0,'12334566699','湖北省武汉市洪山区珞南街道文秀街升升公寓',120.5,20.3,'yang','gwry201611040001','yang','2016-11-04 22:53:15',3,'13212703452',0,'小黑','13212703452',NULL,0,NULL),(5,'北京市东城区东华门街道升升',150.4,0,NULL,'',200.8,50.4,'yang','rrde201701080001','yang','2017-01-08 20:50:05',0,NULL,0,'杨剑秋','13212703452',NULL,0,NULL),(6,'北京市东城区东华门街道werwere',100.2,0,NULL,'Hello Woirld',115,20.3,NULL,'coez201704270001','g10guang','2017-04-27 20:10:06',6,'15871489335',0,'g10guang','15871489223',NULL,0,NULL),(7,'北京市东城区东华门街道werwere',0,0,NULL,'                                ',8,0,NULL,'cobu201704270002','g10guang','2017-04-27 20:16:24',6,'15871489335',8,'g10guang','15871489223',NULL,1,NULL),(8,'北京市东城区东华门街道werwere',100.2,0,NULL,'Hello World.',115,20.3,NULL,'zywa201704270003','g10guang','2017-04-27 20:23:26',6,'15871489335',0,'g10guang','15871489223',NULL,0,NULL),(9,'北京市东城区东华门街道werwere',100.2,0,NULL,'                                ',115,20.3,NULL,'qtek201704270004','g10guang','2017-04-27 22:14:09',0,'15871489335',0,'g10guang','15871489223',NULL,0,NULL),(10,'北京市东城区东华门街道werwere',100.2,0,NULL,'                                ',115,20.3,NULL,'upkm201704270005','g10guang','2017-04-27 22:18:11',0,'15871489335',0,'g10guang','15871489223',NULL,0,NULL),(11,'北京市东城区东华门街道werwere',199,0,NULL,'',280,81,NULL,'wmmu201704280001','g10guang','2017-04-28 09:59:39',0,'15871489335',0,'g10guang','15871489223',NULL,0,NULL),(12,'北京市东城区东华门街道werwere',199,0,NULL,'',280,81,NULL,'ecrx201704280002','g10guang','2017-04-28 15:11:26',0,'15871489335',0,'g10guang','15871489223',NULL,0,NULL),(13,'北京市东城区东华门街道werwere',199,0,NULL,'',280,81,NULL,'ibeq201704280003','g10guang','2017-04-28 17:28:47',0,'15871489335',0,'g10guang','15871489223',NULL,0,NULL);
/*!40000 ALTER TABLE `wd_orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wd_shop`
--

DROP TABLE IF EXISTS `wd_shop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wd_shop` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstCost` float NOT NULL,
  `isDel` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `secondCost` float NOT NULL,
  `shopDescribe` varchar(255) DEFAULT NULL,
  `shopModel` varchar(255) DEFAULT NULL,
  `shopType` varchar(255) NOT NULL,
  `status` int(11) DEFAULT NULL,
  `firstPhoto` varchar(255) DEFAULT NULL,
  `isRecommend` int(11) DEFAULT NULL,
  `inTime` datetime DEFAULT NULL,
  `shopkeeperPrice` float NOT NULL,
  `vipPrice` float NOT NULL,
  `shopkeeper_id` int(11) DEFAULT NULL,
  `pastPrice` float NOT NULL,
  `shopType_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_k3wogqhbuv3o9qjp6xrcn1i5` (`shopkeeper_id`),
  KEY `FK_bp747oobqbwad7qbm13xwjr10` (`shopType_id`),
  CONSTRAINT `FK_bp747oobqbwad7qbm13xwjr10` FOREIGN KEY (`shopType_id`) REFERENCES `wd_shopType` (`id`),
  CONSTRAINT `FK_k3wogqhbuv3o9qjp6xrcn1i5` FOREIGN KEY (`shopkeeper_id`) REFERENCES `wd_shopkeeper` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wd_shop`
--

LOCK TABLES `wd_shop` WRITE;
/*!40000 ALTER TABLE `wd_shop` DISABLE KEYS */;
INSERT INTO `wd_shop` VALUES (1,100.2,0,'英雄牌自行车','BH1306',120.5,'好自行车','山地车','自行车',1,'uploads/2017042810/0ba10550ce1d44babb18daa524c57951.jpg',1,'2016-09-21 11:17:06',110,115,NULL,2861,NULL),(2,150.4,0,'英雄zi13232','BH1307',200.8,'好山地车','山地车','自行车',0,'uploads/2016092018/flash-sale-img1.jpg',0,'2016-09-21 11:17:11',170,180,NULL,2861,1),(3,100.2,0,'英雄牌自行车','BH1306',120.5,'好自行车','山地车','家具生活',0,'uploads\\/2016102122\\/931863e4.jpg',1,'2016-09-21 11:17:06',110,115,NULL,2861,NULL),(4,150.4,0,'英雄232','BH1307',200.8,'好山地车','山地车','数码家电',0,'uploads\\/2016092018\\/flash-sale-img1.jpg',1,'2016-09-21 11:17:11',170,180,NULL,199,1),(5,26,0,'花瑶花化妆棉','HZ1001',35,'这是一个神奇的商品','化妆棉','美妆个体',0,'uploads/2017042800/c9e947d6615549fd89a1330001d3a435.jpg',1,'2017-04-28 00:54:54',29,29,NULL,49,NULL),(6,24,0,'美宝莲眼唇卸妆液70ml','HZ1002',30,'你值得拥有','卸妆液','美妆个体',0,'uploads/2017042801/c27e69febdf24b4d90d57a5de77f15fb.jpg',1,'2017-04-28 00:59:40',24,26,NULL,39,1),(7,48,0,'Za隔离霜70g','HZ1003',65,'你值得拥有','隔离霜','美妆个体',0,'uploads/2017042801/c27e69febdf24b4d90d57a5de77f15fb.jpg',1,'2017-04-28 01:04:08',50,53,NULL,78,1),(8,12,0,'修眉刀','HZ1004',20,'修好你的美','修眉刀','美妆个体',0,'uploads/2017042801/890781c77b0741f6b28a2c685abb9ad5.jpg',1,'2017-04-28 01:06:50',15.5,17.5,NULL,29,1),(9,18,0,'韩束玻尿酸补水面膜','HZ1006',30,'发送到方法收到','面膜','美妆个体',0,'uploads/2017042801/10b38b9933434fe9af3e636af629cedd.jpg',1,'2017-04-28 01:09:38',22,24,NULL,45,1),(10,15,0,'BOB腮红',' HZ1008',30,'BOB腮红BOB腮红BOB腮红BOB腮红','BOB腮红','美妆个体',0,'uploads/2017042801/c27e69febdf24b4d90d57a5de77f15fb.jpg',1,'2017-04-28 01:12:14',20,28,NULL,35,1),(11,29,0,'自然乐园补水保湿芦荟胶300ml','HZ1009',39,'没法理解弗兰克','自然','美妆个体',0,'uploads/2017042801/c27e69febdf24b4d90d57a5de77f15fb.jpg',1,'2017-04-28 01:14:36',30,34,NULL,45,1),(12,25,0,'limi里美酪梨清新身体磨砂膏','HZ1010',57,'发斯蒂芬','limi里美酪梨清新身体磨砂膏','食品饮料',0,'uploads/2017042801/ac77afd4a5804cba989404b362f1fdbf.jpg',1,'2017-04-28 01:15:58',39,45,NULL,69,1),(13,67,0,'美宝莲矿物水感亲肤散粉5.5g','HZ1012',90,'发斯蒂芬大师傅','美宝莲矿物水感亲肤散粉5.5g','服饰箱包',0,'uploads/2017042801/e9c33f290b3948be8a6bc691dcb9b899.jpg',1,'2017-04-28 01:17:33',80,84,NULL,99,1),(14,25,0,'相宜本草去死皮膏 68g','HZ1013',45,'发士大夫撒旦','相宜本草去死皮膏 68g','母婴玩具',0,'uploads/2017042801/b7ec77ae6b534895be7403d1e0ce448e.jpg',1,'2017-04-28 01:19:14',35,40,NULL,59,1),(15,199,0,'BOB透亮恒采粉饼','HZ1011',299,'发送到发多少','BOB透亮恒采粉饼','数码家电',0,'uploads/2017042801/7179662d59624ad58cf1c99bc8a4de30.jpg',1,'2017-04-28 01:22:46',260,280,NULL,319,1),(16,199,0,'BOB透亮恒采粉饼','HZ10123',298,'发送到发多','BOB透亮恒采粉','数码家电',0,'uploads/2017042801/7179662d59624ad58cf1c99bc8a4de30.jpg',1,'2017-04-28 01:22:46',260,280,NULL,319,1),(17,199,0,'BOB透亮恒采粉饼','HZ10124',298,'发送到发多','BOB透亮恒采粉','数码家电',0,'uploads/2017042801/7179662d59624ad58cf1c99bc8a4de30.jpg',1,'2017-04-28 01:22:46',260,280,NULL,319,1),(18,25,0,'limi里美酪梨清新身体磨砂膏','HZ10104',57,'发斯蒂芬','limi里美酪梨清新身体磨砂膏','食品饮料',0,'uploads/2017042801/ac77afd4a5804cba989404b362f1fdbf.jpg',1,'2017-04-28 01:15:58',39,45,NULL,69,1),(19,24,0,'美宝莲眼唇卸妆液70ml','HZ10026',30,'你值得拥有','卸妆液','美妆个体',0,'uploads/2017042801/c27e69febdf24b4d90d57a5de77f15fb.jpg',1,'2017-04-28 00:59:40',24,26,NULL,39,1),(20,18,0,'韩束玻尿酸补水面膜','HZ10067',30,'发送到方法收到','面膜','美妆个体',0,'uploads/2017042801/10b38b9933434fe9af3e636af629cedd.jpg',1,'2017-04-28 01:09:38',22,24,NULL,45,1),(21,12,0,'修眉刀','HZ10049',20,'修好你的美','修眉刀','美妆个体',0,'uploads/2017042801/890781c77b0741f6b28a2c685abb9ad5.jpg',1,'2017-04-28 01:06:50',15.5,17.5,NULL,29,1),(22,150.4,0,'英雄232','BH13072',200.8,'好山地车','山地车','数码家电',0,'uploads\\/2016092018\\/flash-sale-img1.jpg',1,'2016-09-21 11:17:11',170,180,NULL,199,1),(23,26,0,'花瑶花化妆棉','HZ100112',35,'这是一个神奇的商品','化妆棉','美妆个体',0,'uploads/2017042800/c9e947d6615549fd89a1330001d3a435.jpg',1,'2017-04-28 00:54:54',29,29,NULL,49,1),(24,24,0,'美宝莲眼唇卸妆液70ml','HZ100262',30,'你值得拥有','卸妆液','美妆个体',0,'uploads/2017042801/c27e69febdf24b4d90d57a5de77f15fb.jpg',1,'2017-04-28 00:59:40',24,26,NULL,39,1),(25,24,0,'f发送到','HZ100264',30,'你值得拥有','卸妆液','美妆个体',0,'uploads/2017042801/c27e69febdf24b4d90d57a5de77f15fb.jpg',1,'2017-04-28 00:59:40',24,26,NULL,39,1),(26,12,0,'修眉刀','HZ10046',20,'修好你的美','修眉刀','美妆个体',0,'uploads/2017042801/890781c77b0741f6b28a2c685abb9ad5.jpg',1,'2017-04-28 01:06:50',15.5,17.5,NULL,29,1),(27,29,0,'自然乐园补水保湿芦荟胶300ml','HZ10096',39,'没法理解弗兰克','自然','美妆个体',0,'uploads/2017042801/c27e69febdf24b4d90d57a5de77f15fb.jpg',1,'2017-04-28 01:14:36',30,34,NULL,45,1),(28,25,0,'limi里美酪梨清新身体磨砂膏','HZ101067',57,'发斯蒂芬','limi里美酪梨清新身体磨砂膏','食品饮料',0,'uploads/2017042801/ac77afd4a5804cba989404b362f1fdbf.jpg',1,'2017-04-28 01:15:58',39,45,NULL,69,1),(29,25,0,'相宜本草去死皮膏 68g','HZ10137',45,'发士大夫撒旦','相宜本草去死皮膏 68g','母婴玩具',0,'uploads/2017042801/b7ec77ae6b534895be7403d1e0ce448e.jpg',1,'2017-04-28 01:19:14',35,40,NULL,59,1),(30,199,0,'BOB透亮恒采粉饼','HZ101236',298,'发送到发多','BOB透亮恒采粉','数码家电',0,'uploads/2017042801/7179662d59624ad58cf1c99bc8a4de30.jpg',1,'2017-04-28 01:22:46',260,280,NULL,319,1),(31,48,0,'Za隔离霜70g','HZ10039',65,'你值得拥有','隔离霜','美妆个体',0,'uploads/2017042801/c27e69febdf24b4d90d57a5de77f15fb.jpg',1,'2017-04-28 01:04:08',50,53,NULL,78,1),(32,25,0,'limi里美酪梨清新身体磨砂膏','HZ101045',57,'发斯蒂芬','limi里美酪梨清新身体磨砂膏','食品饮料',0,'uploads/2017042801/ac77afd4a5804cba989404b362f1fdbf.jpg',1,'2017-04-28 01:15:58',39,45,NULL,69,1);
/*!40000 ALTER TABLE `wd_shop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wd_shopType`
--

DROP TABLE IF EXISTS `wd_shopType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wd_shopType` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`,`name`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wd_shopType`
--

LOCK TABLES `wd_shopType` WRITE;
/*!40000 ALTER TABLE `wd_shopType` DISABLE KEYS */;
INSERT INTO `wd_shopType` VALUES (1,'自行车');
/*!40000 ALTER TABLE `wd_shopType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wd_shop_wd_user`
--

DROP TABLE IF EXISTS `wd_shop_wd_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wd_shop_wd_user` (
  `user_id` int(11) NOT NULL,
  `shop_id` int(11) NOT NULL,
  `wd_shop_id` int(11) NOT NULL,
  `userList_id` int(11) NOT NULL,
  UNIQUE KEY `user_id` (`user_id`,`shop_id`),
  KEY `FK_2jj75y7n54cp82o8eobnvtevm` (`shop_id`),
  CONSTRAINT `FK_2jj75y7n54cp82o8eobnvtevm` FOREIGN KEY (`shop_id`) REFERENCES `wd_shop` (`id`),
  CONSTRAINT `FK_qpc6i9icjoidqatxyfaj1qsrh` FOREIGN KEY (`user_id`) REFERENCES `wd_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wd_shop_wd_user`
--

LOCK TABLES `wd_shop_wd_user` WRITE;
/*!40000 ALTER TABLE `wd_shop_wd_user` DISABLE KEYS */;
INSERT INTO `wd_shop_wd_user` VALUES (28,1,0,0);
/*!40000 ALTER TABLE `wd_shop_wd_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wd_shopattributes`
--

DROP TABLE IF EXISTS `wd_shopattributes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wd_shopattributes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `isDel` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `shopId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wd_shopattributes`
--

LOCK TABLES `wd_shopattributes` WRITE;
/*!40000 ALTER TABLE `wd_shopattributes` DISABLE KEYS */;
INSERT INTO `wd_shopattributes` VALUES (1,1,'颜色',1),(2,1,'长度',1),(3,0,'颜色',1),(4,0,'长度',1),(5,0,'数量',3),(6,0,'规格',4),(7,0,'规格',5),(8,0,'规格',6),(9,0,'规格',7),(10,0,'个数',8),(11,0,'规格',9),(12,0,'发送到',10),(13,0,'发送到',11),(14,0,'发斯蒂芬',11),(15,0,'发送',12),(16,0,'尺寸',13);
/*!40000 ALTER TABLE `wd_shopattributes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wd_shopattributesvalue`
--

DROP TABLE IF EXISTS `wd_shopattributesvalue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wd_shopattributesvalue` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `attributesValue` varchar(255) DEFAULT NULL,
  `isDel` int(11) DEFAULT NULL,
  `shopAttributes_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_4h5vfh7j62x21ek9gmika1oj5` (`shopAttributes_id`),
  CONSTRAINT `FK_4h5vfh7j62x21ek9gmika1oj5` FOREIGN KEY (`shopAttributes_id`) REFERENCES `wd_shopattributes` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wd_shopattributesvalue`
--

LOCK TABLES `wd_shopattributesvalue` WRITE;
/*!40000 ALTER TABLE `wd_shopattributesvalue` DISABLE KEYS */;
INSERT INTO `wd_shopattributesvalue` VALUES (1,'白色',0,1),(2,'黑色',0,1),(3,'1',0,2),(4,'白色',0,1),(5,'黑色',0,1),(6,'1',0,2),(7,'50/盒',0,3),(8,'70g/ml',0,4),(9,'70g/瓶',0,5),(10,'1支',0,6),(11,'10/片',0,7),(12,'4',0,8),(13,'发多少',0,9),(14,'发送到',0,10),(15,'发斯蒂芬',0,11),(16,'范德萨发改好地方',0,12),(17,'发送',0,13),(18,'XL ',0,14),(19,'XXL',0,14);
/*!40000 ALTER TABLE `wd_shopattributesvalue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wd_shopcart`
--

DROP TABLE IF EXISTS `wd_shopcart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wd_shopcart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `allFirstCost` float NOT NULL,
  `allProfits` float NOT NULL,
  `allSecondCost` float NOT NULL,
  `count` int(11) DEFAULT NULL,
  `detail` varchar(255) DEFAULT NULL,
  `firstCost` float NOT NULL,
  `isDel` int(11) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `profits` float NOT NULL,
  `secondCost` float NOT NULL,
  `shopId` int(11) DEFAULT NULL,
  `shopModel` varchar(255) DEFAULT NULL,
  `shopName` varchar(255) DEFAULT NULL,
  `shopNumber` varchar(255) DEFAULT NULL,
  `shopType` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_9oomu75agdbfoo1ax05xdlfgu` (`order_id`),
  CONSTRAINT `FK_9oomu75agdbfoo1ax05xdlfgu` FOREIGN KEY (`order_id`) REFERENCES `wd_orders` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wd_shopcart`
--

LOCK TABLES `wd_shopcart` WRITE;
/*!40000 ALTER TABLE `wd_shopcart` DISABLE KEYS */;
INSERT INTO `wd_shopcart` VALUES (2,100.2,20.3,120.5,1,'长度：1、颜色：黑色',100.2,0,'uploads\\/2016102122\\/931863e4.jpg',20.3,120.5,1,'山地车','英雄牌自行车','BH1306','自行车',2,'shop',3,1),(4,100.2,20.3,120.5,1,'长度：1、颜色：黑色',100.2,0,'uploads\\/2016102122\\/931863e4.jpg',20.3,120.5,1,'山地车','英雄牌自行车','BH1306','自行车',2,'shop',3,2),(5,100.2,20.3,120.5,1,'长度：1、颜色：黑色',100.2,0,'uploads\\/2016102122\\/931863e4.jpg',20.3,120.5,1,'山地车','英雄牌自行车','BH1306','自行车',0,'shop',3,NULL),(7,100.2,20.3,120.5,1,'长度：1、颜色：黑色',100.2,0,'uploads\\/2016102122\\/931863e4.jpg',20.3,120.5,1,'山地车','英雄牌自行车','BH1306','自行车',2,'shop',3,3),(9,100.2,20.3,120.5,1,'长度：1、颜色：黑色',100.2,0,'uploads\\/2016102122\\/931863e4.jpg',20.3,120.5,1,'山地车','英雄牌自行车','BH1306','自行车',2,'shop',3,4),(10,100.2,20.3,120.5,1,'长度：1、颜色：黑色',100.2,0,'uploads\\/2016102122\\/931863e4.jpg',20.3,120.5,1,'山地车','英雄牌自行车','BH1306','自行车',1,'shop',3,NULL),(11,201.2,18.8,220,2,'',100.6,0,'uploads\\/2016092011\\/flash-sale-img1.jpg',9.4,110,1,'山地车','英雄自行车','BH123456','自行车',1,'shunShop',3,NULL),(13,150.4,50.4,200.8,1,'',150.4,0,'uploads\\/2016092018\\/flash-sale-img1.jpg',50.4,200.8,2,'山地车','英雄zi13232','BH1307','自行车',2,'shop',3,5),(14,100.2,20.3,120.5,1,'长度：1、颜色：黑色',100.2,0,'uploads\\/2016102122\\/931863e4.jpg',20.3,120.5,1,'山地车','英雄牌自行车','BH1306','自行车',1,'shop',3,NULL),(15,100.2,20.3,120.5,1,'长度：1、颜色：白色',100.2,0,'uploads\\/2016102122\\/931863e4.jpg',20.3,120.5,1,'山地车','英雄牌自行车','BH1306','自行车',0,'shop',4,NULL),(16,100.2,20.3,120.5,1,'长度：1、颜色：白色',100.2,0,'uploads\\/2016102122\\/931863e4.jpg',20.3,120.5,1,'山地车','英雄牌自行车','BH1306','自行车',0,'shop',4,NULL),(17,100.2,20.3,120.5,1,'长度：1、颜色：白色',100.2,0,'uploads\\/2016102122\\/931863e4.jpg',20.3,120.5,1,'山地车','英雄牌自行车','BH1306','自行车',0,'shop',4,NULL),(18,100.2,20.3,120.5,1,'长度：1、颜色：白色',100.2,0,'uploads\\/2016102122\\/931863e4.jpg',20.3,120.5,1,'山地车','英雄牌自行车','BH1306','自行车',0,'shop',4,NULL),(19,100.2,20.3,120.5,1,'长度：1、颜色：白色',100.2,0,'uploads\\/2016102122\\/931863e4.jpg',20.3,120.5,1,'山地车','英雄牌自行车','BH1306','自行车',0,'shop',4,NULL),(20,100.2,20.3,120.5,1,'长度：1、颜色：白色',100.2,0,'uploads\\/2016102122\\/931863e4.jpg',20.3,120.5,1,'山地车','英雄牌自行车','BH1306','自行车',0,'shop',4,NULL),(21,150.4,50.4,200.8,1,'',150.4,0,'uploads\\/2016092018\\/flash-sale-img1.jpg',50.4,200.8,2,'山地车','英雄zi13232','BH1307','自行车',0,'shop',4,NULL),(22,150.4,50.4,200.8,1,'',150.4,0,'uploads\\/2016092018\\/flash-sale-img1.jpg',50.4,200.8,2,'山地车','英雄zi13232','BH1307','自行车',0,'shop',31,NULL),(23,150.4,50.4,200.8,1,'',150.4,0,'uploads\\/2016092018\\/flash-sale-img1.jpg',50.4,200.8,2,'山地车','英雄zi13232','BH1307','自行车',0,'shop',31,NULL),(24,150.4,50.4,200.8,1,'',150.4,0,'uploads\\/2016092018\\/flash-sale-img1.jpg',50.4,200.8,2,'山地车','英雄zi13232','BH1307','自行车',0,'shop',29,NULL),(25,100.2,20.3,120.5,1,'长度：1、颜色：白色',100.2,0,'uploads\\/2016102122\\/931863e4.jpg',20.3,120.5,1,'山地车','英雄牌自行车','BH1306','自行车',0,'shop',30,NULL),(27,100.2,20.3,115,1,'长度：1、颜色：白色',100.2,0,'uploads\\/2016102122\\/931863e4.jpg',20.3,120.5,1,'山地车','英雄牌自行车','BH1306','自行车',2,'shop',30,6),(29,100.2,20.3,115,1,'长度：1、颜色：白色',100.2,0,'uploads\\/2016102122\\/931863e4.jpg',20.3,120.5,1,'山地车','英雄牌自行车','BH1306','自行车',2,'shop',30,8),(30,100.2,20.3,120.5,1,'长度：1、颜色：白色',100.2,0,'uploads\\/2016102122\\/931863e4.jpg',20.3,120.5,1,'山地车','英雄牌自行车','BH1306','自行车',0,'shop',30,NULL),(31,100.2,20.3,120.5,1,'长度：1、颜色：白色',100.2,0,'uploads\\/2016102122\\/931863e4.jpg',20.3,120.5,1,'山地车','英雄牌自行车','BH1306','自行车',0,'shop',30,NULL),(32,100.2,20.3,120.5,1,'长度：1、颜色：白色',100.2,0,'uploads\\/2016102122\\/931863e4.jpg',20.3,120.5,1,'山地车','英雄牌自行车','BH1306','自行车',0,'shop',30,NULL),(33,100.2,20.3,120.5,1,'长度：1、颜色：白色',100.2,0,'uploads\\/2016102122\\/931863e4.jpg',20.3,120.5,1,'山地车','英雄牌自行车','BH1306','自行车',0,'shop',30,NULL),(34,100.2,20.3,120.5,1,'长度：1、颜色：白色',100.2,0,'uploads\\/2016102122\\/931863e4.jpg',20.3,120.5,1,'山地车','英雄牌自行车','BH1306','自行车',0,'shop',30,NULL),(35,100.2,20.3,120.5,1,'长度：1、颜色：白色',100.2,0,'uploads\\/2016102122\\/931863e4.jpg',20.3,120.5,1,'山地车','英雄牌自行车','BH1306','自行车',0,'shop',30,NULL),(36,100.2,20.3,120.5,1,'长度：1、颜色：白色',100.2,0,'uploads\\/2016102122\\/931863e4.jpg',20.3,120.5,1,'山地车','英雄牌自行车','BH1306','自行车',0,'shop',30,NULL),(37,100.2,20.3,120.5,1,'长度：1、颜色：白色',100.2,0,'uploads\\/2016102122\\/931863e4.jpg',20.3,120.5,1,'山地车','英雄牌自行车','BH1306','自行车',0,'shop',30,NULL),(38,100.2,20.3,120.5,1,'长度：1、颜色：白色',100.2,0,'uploads\\/2016102122\\/931863e4.jpg',20.3,120.5,1,'山地车','英雄牌自行车','BH1306','自行车',0,'shop',30,NULL),(39,100.2,20.3,120.5,1,'长度：1、颜色：白色',100.2,0,'uploads\\/2016102122\\/931863e4.jpg',20.3,120.5,1,'山地车','英雄牌自行车','BH1306','自行车',0,'shop',30,NULL),(40,100.2,20.3,120.5,1,'长度：1、颜色：白色',100.2,0,'uploads\\/2016102122\\/931863e4.jpg',20.3,120.5,1,'山地车','英雄牌自行车','BH1306','自行车',0,'shop',30,NULL),(41,100.2,20.3,120.5,1,'长度：1、颜色：白色',100.2,0,'uploads\\/2016102122\\/931863e4.jpg',20.3,120.5,1,'山地车','英雄牌自行车','BH1306','自行车',0,'shop',30,NULL),(42,100.2,20.3,120.5,1,'长度：1、颜色：白色',100.2,0,'uploads\\/2016102122\\/931863e4.jpg',20.3,120.5,1,'山地车','英雄牌自行车','BH1306','自行车',0,'shop',30,NULL),(44,100.2,20.3,115,1,'长度：1、颜色：白色',100.2,0,'uploads\\/2016102122\\/931863e4.jpg',20.3,120.5,1,'山地车','英雄牌自行车','BH1306','自行车',2,'shop',30,9),(46,100.2,20.3,115,1,'长度：1、颜色：黑色',100.2,0,'uploads\\/2016102122\\/931863e4.jpg',20.3,120.5,1,'山地车','英雄牌自行车','BH1306','自行车',2,'shop',30,10),(48,199,81,280,1,'',199,0,'uploads/2017042801/7179662d59624ad58cf1c99bc8a4de30.jpg',100,299,15,'BOB透亮恒采粉饼','BOB透亮恒采粉饼','HZ1011','数码家电',2,'shop',30,11),(49,199,100,299,1,'',199,0,'uploads/2017042801/7179662d59624ad58cf1c99bc8a4de30.jpg',100,299,15,'BOB透亮恒采粉饼','BOB透亮恒采粉饼','HZ1011','数码家电',0,'shop',30,NULL),(50,199,100,299,1,'',199,0,'uploads/2017042801/7179662d59624ad58cf1c99bc8a4de30.jpg',100,299,15,'BOB透亮恒采粉饼','BOB透亮恒采粉饼','HZ1011','数码家电',0,'shop',30,NULL),(51,199,100,299,1,'',199,0,'uploads/2017042801/7179662d59624ad58cf1c99bc8a4de30.jpg',100,299,15,'BOB透亮恒采粉饼','BOB透亮恒采粉饼','HZ1011','数码家电',0,'shop',30,NULL),(53,199,81,280,1,'',199,0,'uploads/2017042801/7179662d59624ad58cf1c99bc8a4de30.jpg',81,280,15,'BOB透亮恒采粉饼','BOB透亮恒采粉饼','HZ1011','数码家电',2,'shop',30,12),(54,199,81,280,1,'',199,0,'uploads/2017042801/7179662d59624ad58cf1c99bc8a4de30.jpg',81,280,15,'BOB透亮恒采粉饼','BOB透亮恒采粉饼','HZ1011','数码家电',1,'shop',30,NULL),(55,199,81,280,1,'',199,0,'uploads/2017042801/7179662d59624ad58cf1c99bc8a4de30.jpg',81,280,15,'BOB透亮恒采粉饼','BOB透亮恒采粉饼','HZ1011','数码家电',1,'shop',30,NULL),(57,199,81,280,1,'',199,0,'uploads/2017042801/7179662d59624ad58cf1c99bc8a4de30.jpg',81,280,15,'BOB透亮恒采粉饼','BOB透亮恒采粉饼','HZ1011','数码家电',2,'shop',30,13),(58,199,81,280,1,'',199,0,'uploads/2017042801/7179662d59624ad58cf1c99bc8a4de30.jpg',81,280,15,'BOB透亮恒采粉饼','BOB透亮恒采粉饼','HZ1011','数码家电',1,'shop',30,NULL),(59,199,81,280,1,'',199,0,'uploads/2017042801/7179662d59624ad58cf1c99bc8a4de30.jpg',81,280,15,'BOB透亮恒采粉饼','BOB透亮恒采粉饼','HZ1011','数码家电',1,'shop',30,NULL),(60,199,81,280,1,'',199,0,'uploads/2017042801/7179662d59624ad58cf1c99bc8a4de30.jpg',81,280,15,'BOB透亮恒采粉饼','BOB透亮恒采粉饼','HZ1011','数码家电',1,'shop',30,NULL),(61,199,81,280,1,'',199,0,'uploads/2017042801/7179662d59624ad58cf1c99bc8a4de30.jpg',81,280,15,'BOB透亮恒采粉饼','BOB透亮恒采粉饼','HZ1011','数码家电',1,'shop',30,NULL);
/*!40000 ALTER TABLE `wd_shopcart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wd_shopkeeper`
--

DROP TABLE IF EXISTS `wd_shopkeeper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wd_shopkeeper` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `isDel` int(11) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `zhifubao` varchar(255) DEFAULT NULL,
  `webUrl` varchar(255) DEFAULT NULL,
  `allProfit` float NOT NULL,
  `yiTiXian` float NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `allUserNumber` int(11) NOT NULL DEFAULT '0',
  `belong` int(11) NOT NULL DEFAULT '0',
  `directUserNumber` int(11) NOT NULL DEFAULT '0',
  `level` int(11) DEFAULT NULL,
  `allShopkeeperNumber` int(11) NOT NULL DEFAULT '0',
  `directShopkeeperNumber` int(11) NOT NULL DEFAULT '0',
  `registerTime` datetime NOT NULL,
  `deathTime` datetime NOT NULL,
  `isNew` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wd_shopkeeper`
--

LOCK TABLES `wd_shopkeeper` WRITE;
/*!40000 ALTER TABLE `wd_shopkeeper` DISABLE KEYS */;
INSERT INTO `wd_shopkeeper` VALUES (6,'10011@164.com',0,NULL,'1000:904393e9d952362b4de2656d5c0ac1e4:733a8520f3941d03dc203a4b576ab1f203f33f89e3768623cf35eddc916280aa973491cd56d4112f36b621a980a35bec95c286482f15f656866ab3274c95d25f',0,'g10','15871489221',NULL,416.22,0,NULL,'15871489221',14,0,2,2,7,2,'2017-04-24 21:54:01','2018-04-24 21:54:02',0),(7,'10011@163.com',0,NULL,'1000:904393e9d952362b4de2656d5c0ac1e4:733a8520f3941d03dc203a4b576ab1f203f33f89e3768623cf35eddc916280aa973491cd56d4112f36b621a980a35bec95c286482f15f656866ab3274c95d25f',0,'gg','15871489222',NULL,283.04,0,NULL,'15871489222',12,6,6,1,5,2,'2017-04-24 22:04:38','2020-04-25 17:37:22',0),(8,'eeee@163.com',0,NULL,'1000:904393e9d952362b4de2656d5c0ac1e4:733a8520f3941d03dc203a4b576ab1f203f33f89e3768623cf35eddc916280aa973491cd56d4112f36b621a980a35bec95c286482f15f656866ab3274c95d25f',0,'fffff','15871489230',NULL,0,0,NULL,'15871489230',6,7,0,0,3,1,'2017-04-24 23:55:55','2019-04-25 18:26:23',0),(9,'ggggg@163.com',0,NULL,'1000:904393e9d952362b4de2656d5c0ac1e4:733a8520f3941d03dc203a4b576ab1f203f33f89e3768623cf35eddc916280aa973491cd56d4112f36b621a980a35bec95c286482f15f656866ab3274c95d25f',0,'ggggg','15871489231',NULL,0,0,NULL,'15871489231',6,8,5,0,2,1,'2017-04-25 00:01:30','2018-04-25 00:01:30',0),(10,'hhhhhh@163.com',1,NULL,'1000:904393e9d952362b4de2656d5c0ac1e4:733a8520f3941d03dc203a4b576ab1f203f33f89e3768623cf35eddc916280aa973491cd56d4112f36b621a980a35bec95c286482f15f656866ab3274c95d25f',0,'hhhhh','15871489232',NULL,0,0,NULL,'15871489232',0,0,0,0,0,0,'2017-04-25 00:11:04','2018-04-25 00:11:04',0),(11,'pppppp@163.com',0,NULL,'1000:904393e9d952362b4de2656d5c0ac1e4:733a8520f3941d03dc203a4b576ab1f203f33f89e3768623cf35eddc916280aa973491cd56d4112f36b621a980a35bec95c286482f15f656866ab3274c95d25f',0,'ppppp','15871489239',NULL,0,0,NULL,'15871489239',0,0,0,0,0,0,'2017-04-25 16:28:36','2018-04-25 16:28:38',0),(12,'qqqqq@163.com',0,NULL,'1000:904393e9d952362b4de2656d5c0ac1e4:733a8520f3941d03dc203a4b576ab1f203f33f89e3768623cf35eddc916280aa973491cd56d4112f36b621a980a35bec95c286482f15f656866ab3274c95d25f',0,'qqqqq','15871489240',NULL,0,0,NULL,'15871489240',1,9,0,0,1,1,'2017-04-25 16:30:55','2018-04-25 16:30:55',0),(13,'rrrrrr@163.com',0,NULL,'1000:904393e9d952362b4de2656d5c0ac1e4:733a8520f3941d03dc203a4b576ab1f203f33f89e3768623cf35eddc916280aa973491cd56d4112f36b621a980a35bec95c286482f15f656866ab3274c95d25f',0,'rrrrrr','15871489241',NULL,222,0,NULL,'15871489241',1,12,1,0,1,0,'2017-04-25 16:34:08','2018-04-25 16:34:08',0),(14,'ssss@163.com',0,NULL,'1000:904393e9d952362b4de2656d5c0ac1e4:733a8520f3941d03dc203a4b576ab1f203f33f89e3768623cf35eddc916280aa973491cd56d4112f36b621a980a35bec95c286482f15f656866ab3274c95d25f',0,'ssss','15871489242',NULL,0,0,NULL,'15871489242',0,7,0,0,0,0,'2017-04-25 16:36:56','2019-04-25 18:36:28',0),(15,'ttt@qq.com',0,NULL,'1000:c6a16722f1a3e7d1edcef8ecfe0d4a18:c4e94d9148193169a609cf4d0f70536fda4d946159967876e91922497d5cc2d3bfa5ece6fb02102fc147886fb3c0505749417ccc28f69b9119759e6e3bb26130',1,'tttttt','15871682511',NULL,0,0,NULL,'15871682511',0,6,0,0,0,0,'2017-04-27 16:56:44','2017-04-27 16:56:44',0);
/*!40000 ALTER TABLE `wd_shopkeeper` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wd_shopkeeperprofit`
--

DROP TABLE IF EXISTS `wd_shopkeeperprofit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wd_shopkeeperprofit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `isDel` int(11) DEFAULT NULL,
  `money` float NOT NULL,
  `shopKeeper` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `tiXianTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wd_shopkeeperprofit`
--

LOCK TABLES `wd_shopkeeperprofit` WRITE;
/*!40000 ALTER TABLE `wd_shopkeeperprofit` DISABLE KEYS */;
INSERT INTO `wd_shopkeeperprofit` VALUES (1,0,12,'yang',2,'2016-09-21 09:20:41'),(2,1,5,'yang',0,'2016-09-21 09:24:20'),(3,0,6,'yang',1,'2016-09-21 09:24:44'),(4,0,12,'yang',2,'2016-09-21 09:45:34'),(5,0,2,'yang',0,'2016-09-21 10:40:33'),(6,0,2,'yang',0,'2016-09-21 10:44:01'),(8,0,20.3,'yang',1,'2016-11-01 19:03:16'),(9,0,20.3,'yang',1,'2016-11-01 19:07:47'),(10,0,0,'yang',0,'2016-11-22 17:15:07');
/*!40000 ALTER TABLE `wd_shopkeeperprofit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wd_shopphotos`
--

DROP TABLE IF EXISTS `wd_shopphotos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wd_shopphotos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `shopId` int(11) DEFAULT NULL,
  `urlPath` varchar(255) DEFAULT NULL,
  `isDel` int(11) DEFAULT NULL,
  `photoType` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wd_shopphotos`
--

LOCK TABLES `wd_shopphotos` WRITE;
/*!40000 ALTER TABLE `wd_shopphotos` DISABLE KEYS */;
INSERT INTO `wd_shopphotos` VALUES (1,1,'uploads/2016092017/adds-img1.jpg',0,1),(2,1,'uploads/2016092017/adds-img1.jpg',0,1),(3,1,'uploads/2016092019/adds-img1.jpg',1,1),(7,1,'uploads/2016092019/adds-img2.jpg',1,0),(35,1,'uploads/2016092019/adds-img1.jpg',0,0),(36,1,'uploads/2016092019/adds-img2.jpg',0,0),(37,1,'uploads/2016101000/ae43f28d.jpg',1,1),(38,1,'uploads/2016101000/ae43f28d.jpg',1,1),(39,3,'uploads/2016103110/116b192e-43df-40fd-b622-63918981cf22.png',0,1),(40,1,'uploads\\/2016092017\\/adds-img1.jpg',0,1),(41,1,'uploads\\/2016092017\\/adds-img1.jpg',0,1),(42,1,'uploads\\/2016092019\\/adds-img1.jpg',0,1),(43,1,'uploads\\/2016092019\\/adds-img2.jpg',0,0),(44,1,'uploads\\/2016092019\\/adds-img1.jpg',0,0),(45,1,'uploads\\/2016092019\\/adds-img2.jpg',0,0),(46,1,'uploads\\/2016101000\\/ae43f28d.jpg',1,1),(47,1,'uploads\\/2016101000\\/ae43f28d.jpg',1,1),(48,3,'uploads\\/2016103110\\/116b192e-43df-40fd-b622-63918981cf22.png',0,1),(49,2,'uploads/2017042800/9aa271003eb84c0bb852a066fd7eddc6.jpg',0,0),(50,2,'uploads/2017042800/678aed14d0c5476f9d51a319dd265a4d.jpg',0,0),(51,2,'uploads/2017042800/9abfb5ded90042a5be0a91e6b92dbe31.jpg',0,1),(52,2,'uploads/2017042800/7aa046cecde64ca29ba1d874b7f9cf3c.jpg',0,1),(53,3,'uploads/2017042800/a4aff024afe34ffaa660c184a2ace442.jpg',0,0),(54,3,'uploads/2017042800/15dffb7e595c4796ab7803f8c8e4135e.jpg',0,0),(55,3,'uploads/2017042800/9691b1376278489da3be233f3c552ec3.jpg',0,1),(56,3,'uploads/2017042800/7fdf53e5dd3b4f3392a15506f4aaf6bf.jpg',0,1),(57,3,'uploads/2017042800/307569f31cb644a9817694343a9193a3.jpg',0,1),(58,3,'uploads/2017042800/bf257fcf7b2c44afa415bf20a47073ef.jpg',0,1),(59,3,'uploads/2017042800/60a514316d7f4b958b9a548873aa2972.jpg',0,1),(60,3,'uploads/2017042800/ef19275d5ff641e7b8b83d51f32c3e55.jpg',0,1),(61,4,'uploads/2017042801/d9c66bf90bbf45da808b3731b5946b51.jpg',0,0),(62,4,'uploads/2017042801/7ba3b52f11684be7a6946d5f94258f55.jpg',0,0),(63,4,'uploads/2017042801/25dcc89328504b27910f944ffc5fa896.jpg',0,1),(64,4,'uploads/2017042801/d019e2907f334ce7812a5dadc193ff8b.jpg',0,1),(65,4,'uploads/2017042801/fe675087566e47d1b601a43e7a4fd6d6.jpg',0,1),(66,5,'uploads/2017042801/bc5aeba85bb14242aa030a58b677ea7f.jpg',0,0),(67,5,'uploads/2017042801/4ebecbdf812849fa99b85e3365ca7bdf.jpg',0,0),(68,5,'uploads/2017042801/7602869820094173baab95a472904db7.jpg',0,1),(69,5,'uploads/2017042801/78a6a5eb8bd148bb80199134a428f7da.jpg',0,1),(70,5,'uploads/2017042801/04f7237924c647b584526f2da589f44b.jpg',0,1),(71,5,'uploads/2017042801/de53fc8c524e4425a031701ad07f139d.jpg',0,1),(72,6,'uploads/2017042801/c103ee2c3c8b45b482ea434e4b89e29e.jpg',0,0),(73,6,'uploads/2017042801/28b6c1fd634f4f57900e8db6fa757b1a.jpg',0,0),(74,6,'uploads/2017042801/fe32006a2d1945a7ab9c3180da7e3404.jpg',0,1),(75,6,'uploads/2017042801/0d413c84df0048ddb6b56e1d3cfb570f.jpg',0,1),(76,7,'uploads/2017042801/0fd898818074489d860a832c4f8df79e.jpg',0,0),(77,7,'uploads/2017042801/329dc4fedea742e19eed96117978d613.jpg',0,0),(78,7,'uploads/2017042801/ee12d5ad0ae34d898e7987f774a49237.jpg',0,1),(79,7,'uploads/2017042801/e0f1422342c14b0fb072eab70f262702.jpg',0,1),(80,7,'uploads/2017042801/057c5fe4d6444601b5391d9511c7e070.jpg',0,1),(81,8,'uploads/2017042801/6903d6b591f549e0a9b2c9186323e400.jpg',0,0),(82,8,'uploads/2017042801/706d3e8f14ee4ae6b2e4953fa4fddeac.jpg',0,0),(83,8,'uploads/2017042801/85d263dbb08a42639f3db69c614a8d91.jpg',0,1),(84,8,'uploads/2017042801/65dcc2b39e8c4fe28b6ade4b25cd417d.jpg',0,1),(85,8,'uploads/2017042801/d8def7e3e330421d838820b5614517d2.jpg',0,1),(86,10,'uploads/2017042801/abe116e7dcde42b486ec0d870452fc50.jpg',0,0),(87,10,'uploads/2017042801/cf9b3a8d34c84275a9453656ae82de21.jpg',0,1),(88,11,'uploads/2017042801/b531d10c78d74a91837c25df1f3f73a6.jpg',0,0),(89,11,'uploads/2017042801/4d819f7bab3c47449e4156fdb9b6b6f2.jpg',0,1),(90,12,'uploads/2017042801/88b4d3ea71d44d5d87bd0b60ff264436.jpg',0,0),(91,12,'uploads/2017042801/19456122b06844f69607e401bc088713.jpg',0,1),(92,13,'uploads/2017042801/e03cc84a8bb34858b3a6e5e37ba18249.jpg',0,0),(93,13,'uploads/2017042801/ff0131279a1c4f38a4f7a8ec2af1c277.jpg',0,1);
/*!40000 ALTER TABLE `wd_shopphotos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wd_shunshop`
--

DROP TABLE IF EXISTS `wd_shunshop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wd_shunshop` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `day` int(11) DEFAULT NULL,
  `firstCost` float NOT NULL,
  `firstPhoto` varchar(255) DEFAULT NULL,
  `hours` int(11) DEFAULT NULL,
  `isDel` int(11) DEFAULT NULL,
  `minutes` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `secondCost` float NOT NULL,
  `seconds` int(11) DEFAULT NULL,
  `shopDescribe` varchar(255) DEFAULT NULL,
  `shopModel` varchar(255) DEFAULT NULL,
  `shopType` varchar(255) DEFAULT NULL,
  `startTime` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `thirdCost` float NOT NULL,
  `endTime` datetime DEFAULT NULL,
  `inTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wd_shunshop`
--

LOCK TABLES `wd_shunshop` WRITE;
/*!40000 ALTER TABLE `wd_shunshop` DISABLE KEYS */;
INSERT INTO `wd_shunshop` VALUES (1,0,100.6,'uploads/2016092011/flash-sale-img1.jpg',22,0,0,'英雄自行车','BH123456',120.8,0,'好车','山地车','自行车','2016-09-20 01:00:00',0,110,'2018-09-20 23:00:00','2016-10-15 17:33:19'),(2,1,100,'uploads/2017042811/5998882a82544511a3748f29df448949.jpg',0,0,16,'冠军T恤','B222',200,17,'值得拥有','T恤','衣服','2017-04-28 00:00:00',0,120,'2017-04-29 00:16:17','2017-04-28 11:19:50');
/*!40000 ALTER TABLE `wd_shunshop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wd_shunshopattributes`
--

DROP TABLE IF EXISTS `wd_shunshopattributes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wd_shunshopattributes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `isDel` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `shopId` int(11) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wd_shunshopattributes`
--

LOCK TABLES `wd_shunshopattributes` WRITE;
/*!40000 ALTER TABLE `wd_shunshopattributes` DISABLE KEYS */;
/*!40000 ALTER TABLE `wd_shunshopattributes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wd_shunshopattributesvalue`
--

DROP TABLE IF EXISTS `wd_shunshopattributesvalue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wd_shunshopattributesvalue` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `attributesValue` varchar(255) DEFAULT NULL,
  `isDel` int(11) DEFAULT NULL,
  `shunshopAttributes_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_75vk1vmlfic0me67wj0tdw1py` (`shunshopAttributes_id`),
  CONSTRAINT `FK_75vk1vmlfic0me67wj0tdw1py` FOREIGN KEY (`shunshopAttributes_id`) REFERENCES `wd_shunshopattributes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wd_shunshopattributesvalue`
--

LOCK TABLES `wd_shunshopattributesvalue` WRITE;
/*!40000 ALTER TABLE `wd_shunshopattributesvalue` DISABLE KEYS */;
/*!40000 ALTER TABLE `wd_shunshopattributesvalue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wd_shunshopphotos`
--

DROP TABLE IF EXISTS `wd_shunshopphotos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wd_shunshopphotos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `isDel` int(11) DEFAULT NULL,
  `photoType` int(11) DEFAULT NULL,
  `shopId` int(11) DEFAULT NULL,
  `urlPath` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wd_shunshopphotos`
--

LOCK TABLES `wd_shunshopphotos` WRITE;
/*!40000 ALTER TABLE `wd_shunshopphotos` DISABLE KEYS */;
INSERT INTO `wd_shunshopphotos` VALUES (1,0,0,1,'uploads/2016092022/adds-img2.jpg'),(2,0,0,1,'uploads/2016092022/adds-img1.jpg'),(16,0,1,1,'uploads/2016092022/adds-img1.jpg'),(17,0,1,1,'uploads/2016092022/adds-img1.jpg'),(18,0,1,1,'uploads/2016092022/adds-img1.jpg'),(19,0,0,1,'uploads/2016092022/adds-img2.jpg'),(20,0,0,1,'uploads/2016092022/adds-img1.jpg'),(21,0,1,1,'uploads/2016092022/adds-img1.jpg'),(22,0,1,1,'uploads/2016092022/adds-img1.jpg'),(23,0,1,1,'uploads/2016092022/adds-img1.jpg'),(24,0,1,2,'uploads/2017042811/25986130ff4a40fe9d70c469751ca2d2.jpg');
/*!40000 ALTER TABLE `wd_shunshopphotos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wd_tixian_record`
--

DROP TABLE IF EXISTS `wd_tixian_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wd_tixian_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `amount` float DEFAULT NULL,
  `inTime` datetime DEFAULT NULL,
  `orderId` varchar(255) DEFAULT NULL,
  `shopKeeper` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wd_tixian_record`
--

LOCK TABLES `wd_tixian_record` WRITE;
/*!40000 ALTER TABLE `wd_tixian_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `wd_tixian_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wd_user`
--

DROP TABLE IF EXISTS `wd_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wd_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `isDel` int(11) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `vip` int(11) DEFAULT NULL,
  `shopkeeper_id` int(11) DEFAULT NULL,
  `pay` float NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_mnb1ky1qi1cf898jedncv96nd` (`shopkeeper_id`),
  CONSTRAINT `FK_mnb1ky1qi1cf898jedncv96nd` FOREIGN KEY (`shopkeeper_id`) REFERENCES `wd_shopkeeper` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wd_user`
--

LOCK TABLES `wd_user` WRITE;
/*!40000 ALTER TABLE `wd_user` DISABLE KEYS */;
INSERT INTO `wd_user` VALUES (4,'10011@164.com',0,NULL,'1000:904393e9d952362b4de2656d5c0ac1e4:733a8520f3941d03dc203a4b576ab1f203f33f89e3768623cf35eddc916280aa973491cd56d4112f36b621a980a35bec95c286482f15f656866ab3274c95d25f',0,'g10','15871489221',2,NULL,0),(5,'10011@163.com',0,NULL,'1000:904393e9d952362b4de2656d5c0ac1e4:733a8520f3941d03dc203a4b576ab1f203f33f89e3768623cf35eddc916280aa973491cd56d4112f36b621a980a35bec95c286482f15f656866ab3274c95d25f',0,'gg','15871489222',2,NULL,0),(6,'111@163.com',0,NULL,'1000:904393e9d952362b4de2656d5c0ac1e4:733a8520f3941d03dc203a4b576ab1f203f33f89e3768623cf35eddc916280aa973491cd56d4112f36b621a980a35bec95c286482f15f656866ab3274c95d25f',0,'yy','15871489223',1,6,0),(7,'222@163.com',0,NULL,'1000:904393e9d952362b4de2656d5c0ac1e4:733a8520f3941d03dc203a4b576ab1f203f33f89e3768623cf35eddc916280aa973491cd56d4112f36b621a980a35bec95c286482f15f656866ab3274c95d25f',0,'xxx','15871489224',1,7,0),(8,'3332@163.com',0,NULL,'1000:904393e9d952362b4de2656d5c0ac1e4:733a8520f3941d03dc203a4b576ab1f203f33f89e3768623cf35eddc916280aa973491cd56d4112f36b621a980a35bec95c286482f15f656866ab3274c95d25f',0,'zzz','15871489225',1,7,0),(9,'4432@163.com',0,NULL,'1000:904393e9d952362b4de2656d5c0ac1e4:733a8520f3941d03dc203a4b576ab1f203f33f89e3768623cf35eddc916280aa973491cd56d4112f36b621a980a35bec95c286482f15f656866ab3274c95d25f',0,'aaa','15871489226',1,7,0),(11,'bbbb@163.com',0,NULL,'1000:904393e9d952362b4de2656d5c0ac1e4:733a8520f3941d03dc203a4b576ab1f203f33f89e3768623cf35eddc916280aa973491cd56d4112f36b621a980a35bec95c286482f15f656866ab3274c95d25f',0,'bbb','15871489227',1,7,0),(12,'cccc@163.com',0,NULL,'1000:904393e9d952362b4de2656d5c0ac1e4:733a8520f3941d03dc203a4b576ab1f203f33f89e3768623cf35eddc916280aa973491cd56d4112f36b621a980a35bec95c286482f15f656866ab3274c95d25f',0,'ccc','15871489228',1,7,0),(13,'dddd@163.com',0,NULL,'1000:904393e9d952362b4de2656d5c0ac1e4:733a8520f3941d03dc203a4b576ab1f203f33f89e3768623cf35eddc916280aa973491cd56d4112f36b621a980a35bec95c286482f15f656866ab3274c95d25f',0,'dddd','15871489229',1,7,0),(15,'eeee@163.com',0,NULL,'1000:904393e9d952362b4de2656d5c0ac1e4:733a8520f3941d03dc203a4b576ab1f203f33f89e3768623cf35eddc916280aa973491cd56d4112f36b621a980a35bec95c286482f15f656866ab3274c95d25f',0,'fffff','15871489230',2,NULL,0),(16,'ggggg@163.com',0,NULL,'1000:904393e9d952362b4de2656d5c0ac1e4:733a8520f3941d03dc203a4b576ab1f203f33f89e3768623cf35eddc916280aa973491cd56d4112f36b621a980a35bec95c286482f15f656866ab3274c95d25f',0,'ggggg','15871489231',2,NULL,0),(17,'hhhhhh@163.com',0,NULL,'1000:904393e9d952362b4de2656d5c0ac1e4:733a8520f3941d03dc203a4b576ab1f203f33f89e3768623cf35eddc916280aa973491cd56d4112f36b621a980a35bec95c286482f15f656866ab3274c95d25f',0,'hhhhh','15871489232',2,NULL,0),(18,'iiiiiii@163.com',0,NULL,'1000:904393e9d952362b4de2656d5c0ac1e4:733a8520f3941d03dc203a4b576ab1f203f33f89e3768623cf35eddc916280aa973491cd56d4112f36b621a980a35bec95c286482f15f656866ab3274c95d25f',0,'iiiiiiii','15871489233',1,9,0),(20,'kkkkkki@163.com',0,NULL,'1000:904393e9d952362b4de2656d5c0ac1e4:733a8520f3941d03dc203a4b576ab1f203f33f89e3768623cf35eddc916280aa973491cd56d4112f36b621a980a35bec95c286482f15f656866ab3274c95d25f',0,'kkkkkk','15871489234',1,9,0),(21,'jjjjjjjjjji@163.com',0,NULL,'1000:904393e9d952362b4de2656d5c0ac1e4:733a8520f3941d03dc203a4b576ab1f203f33f89e3768623cf35eddc916280aa973491cd56d4112f36b621a980a35bec95c286482f15f656866ab3274c95d25f',0,'jjjjj','15871489235',1,9,0),(22,'lllllllli@163.com',0,NULL,'1000:904393e9d952362b4de2656d5c0ac1e4:733a8520f3941d03dc203a4b576ab1f203f33f89e3768623cf35eddc916280aa973491cd56d4112f36b621a980a35bec95c286482f15f656866ab3274c95d25f',0,'lllllll','15871489236',1,9,0),(23,'mmmmm@163.com',0,NULL,'1000:904393e9d952362b4de2656d5c0ac1e4:733a8520f3941d03dc203a4b576ab1f203f33f89e3768623cf35eddc916280aa973491cd56d4112f36b621a980a35bec95c286482f15f656866ab3274c95d25f',0,'mmmmmm','15871489237',1,6,0),(24,'nnnnn@163.com',0,NULL,'1000:904393e9d952362b4de2656d5c0ac1e4:733a8520f3941d03dc203a4b576ab1f203f33f89e3768623cf35eddc916280aa973491cd56d4112f36b621a980a35bec95c286482f15f656866ab3274c95d25f',0,'nnnnn','15871489238',1,9,0),(25,'pppppp@163.com',0,NULL,'1000:904393e9d952362b4de2656d5c0ac1e4:733a8520f3941d03dc203a4b576ab1f203f33f89e3768623cf35eddc916280aa973491cd56d4112f36b621a980a35bec95c286482f15f656866ab3274c95d25f',0,'ppppp','15871489239',2,NULL,0),(26,'qqqqq@163.com',0,NULL,'1000:904393e9d952362b4de2656d5c0ac1e4:733a8520f3941d03dc203a4b576ab1f203f33f89e3768623cf35eddc916280aa973491cd56d4112f36b621a980a35bec95c286482f15f656866ab3274c95d25f',0,'qqqqq','15871489240',2,NULL,0),(27,'rrrrrr@163.com',0,NULL,'1000:904393e9d952362b4de2656d5c0ac1e4:733a8520f3941d03dc203a4b576ab1f203f33f89e3768623cf35eddc916280aa973491cd56d4112f36b621a980a35bec95c286482f15f656866ab3274c95d25f',0,'rrrrrr','15871489241',2,NULL,0),(28,'ssss@163.com',0,NULL,'1000:904393e9d952362b4de2656d5c0ac1e4:733a8520f3941d03dc203a4b576ab1f203f33f89e3768623cf35eddc916280aa973491cd56d4112f36b621a980a35bec95c286482f15f656866ab3274c95d25f',0,'ssss','15871489242',2,NULL,0),(29,'zzz@qq.com',0,NULL,'1000:904393e9d952362b4de2656d5c0ac1e4:733a8520f3941d03dc203a4b576ab1f203f33f89e3768623cf35eddc916280aa973491cd56d4112f36b621a980a35bec95c286482f15f656866ab3274c95d25f',0,'zzzz','15871489243',0,NULL,0),(30,'g10guang@qq.com',0,NULL,'1000:81e7fe62a216ffdc7803ce00da6122c9:5ef5004654eaeb4b825dfd6666cb3e73bf2af2e26a658b7f004b23da53076c0a6f06f9babf82d24b8d47b46d8e0405576424248727b5c12e451f868a289d047f',0,'g10guang','15871489335',1,13,0),(31,'ttt@qq.com',0,NULL,'1000:c6a16722f1a3e7d1edcef8ecfe0d4a18:c4e94d9148193169a609cf4d0f70536fda4d946159967876e91922497d5cc2d3bfa5ece6fb02102fc147886fb3c0505749417ccc28f69b9119759e6e3bb26130',0,'tttttt','15871682511',2,NULL,0);
/*!40000 ALTER TABLE `wd_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wd_useraddress`
--

DROP TABLE IF EXISTS `wd_useraddress`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wd_useraddress` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `adddetail` varchar(255) DEFAULT NULL,
  `area` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `isDel` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `town` varchar(255) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `zipcode` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wd_useraddress`
--

LOCK TABLES `wd_useraddress` WRITE;
/*!40000 ALTER TABLE `wd_useraddress` DISABLE KEYS */;
INSERT INTO `wd_useraddress` VALUES (2,'文秀街升升公寓','洪山区','武汉市',0,'小黑','13212703452','湖北省','珞南街道',3,NULL,1),(3,'升升','','东城区',0,'杨剑秋','13212703452','北京市','东华门街道',3,NULL,1),(4,'734','','东城区',0,'刘','15971492201','北京市','东华门街道',4,NULL,1),(5,'武汉理工大学','','东城区',0,'g10guang','15871498821','北京市','东华门街道',31,NULL,1),(6,'werwere','','东城区',0,'g10guang','15871489223','北京市','东华门街道',30,NULL,1);
/*!40000 ALTER TABLE `wd_useraddress` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wd_yunfei`
--

DROP TABLE IF EXISTS `wd_yunfei`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wd_yunfei` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mianYunFei` int(11) DEFAULT NULL,
  `yunFei` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wd_yunfei`
--

LOCK TABLES `wd_yunfei` WRITE;
/*!40000 ALTER TABLE `wd_yunfei` DISABLE KEYS */;
INSERT INTO `wd_yunfei` VALUES (1,100,8);
/*!40000 ALTER TABLE `wd_yunfei` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-04-29  8:21:47
