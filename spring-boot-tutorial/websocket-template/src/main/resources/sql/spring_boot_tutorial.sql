-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: spring_boot_tutorial
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `message_store`
--

DROP TABLE IF EXISTS `message_store`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message_store` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '消息编号',
  `title` varchar(40) DEFAULT NULL COMMENT '消息标题',
  `content` varchar(1024) DEFAULT NULL COMMENT '消息内容',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` int DEFAULT NULL COMMENT '创建人',
  `last_update_time` datetime DEFAULT NULL COMMENT '最近更新时间',
  `last_update_by` datetime DEFAULT NULL COMMENT '最近更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='消息仓库';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message_store`
--

LOCK TABLES `message_store` WRITE;
/*!40000 ALTER TABLE `message_store` DISABLE KEYS */;
/*!40000 ALTER TABLE `message_store` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_param`
--

DROP TABLE IF EXISTS `system_param`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_param` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '参数编号',
  `param_name` varchar(128) DEFAULT NULL COMMENT '参数名称',
  `param_desc` varchar(256) DEFAULT NULL COMMENT '参数描述',
  `param_value` varchar(40) DEFAULT NULL COMMENT '参数值',
  `param_status` char(1) DEFAULT NULL COMMENT '参数状态[0=在使用(默认)/1=未使用/2=已废弃]',
  `version` varchar(10) DEFAULT NULL COMMENT '从哪个版本开始',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(40) DEFAULT NULL COMMENT '创建人',
  `last_update_time` datetime DEFAULT NULL COMMENT '最近更新时间',
  `last_update_by` varchar(40) DEFAULT NULL COMMENT '最近更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统参数';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_param`
--

LOCK TABLES `system_param` WRITE;
/*!40000 ALTER TABLE `system_param` DISABLE KEYS */;
INSERT INTO `system_param` (`id`, `param_name`, `param_desc`, `param_value`, `param_status`, `version`, `create_time`, `create_by`, `last_update_time`, `last_update_by`) VALUES (1,'multiple_login','是否允许同一账号可同时在不同设备上进行登录','1','0','1.0.0','2022-09-22 20:51:08','shiping.song','2022-09-22 20:51:08','shiping.song'),(2,'async_message','是否开启异步消息通知','0','0',NULL,'2022-09-22 21:32:16','shiping.song','2022-09-22 21:32:16','shiping.song');
/*!40000 ALTER TABLE `system_param` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `username` varchar(40) DEFAULT NULL COMMENT '用户名',
  `password` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `username`, `password`) VALUES (1,'admin','64p4913h805p4e10rj32gj9nn6b14r3');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_message`
--

DROP TABLE IF EXISTS `user_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_message` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL COMMENT '用户编号',
  `message_id` bigint DEFAULT NULL COMMENT '消息编号',
  `push_flag` char(1) DEFAULT NULL COMMENT '是否已推送[0=否(默认)/1=是]',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_message_user_id_message_id_uindex` (`user_id`,`message_id`) COMMENT '用户编号_消息编号唯一索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户消息中间表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_message`
--

LOCK TABLES `user_message` WRITE;
/*!40000 ALTER TABLE `user_message` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_message` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-09-22 23:12:25
