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
-- Table structure for table `file_chunk`
--

DROP TABLE IF EXISTS `file_chunk`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `file_chunk` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `file_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '文件名',
  `chunk_number` int DEFAULT NULL COMMENT '当前分片，从1开始',
  `chunk_size` decimal(12,0) DEFAULT NULL COMMENT '分片大小',
  `current_chunk_size` decimal(12,0) DEFAULT NULL COMMENT '当前分片大小',
  `total_size` decimal(12,0) DEFAULT NULL COMMENT '文件总大小',
  `total_chunk` int DEFAULT NULL COMMENT '总分片数',
  `identifier` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '文件标识',
  `relative_path` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT 'md5校验码',
  `create_time` datetime,
  `update_time` datetime,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1533 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `file_chunk`
--

LOCK TABLES `file_chunk` WRITE;
/*!40000 ALTER TABLE `file_chunk` DISABLE KEYS */;
INSERT INTO `file_chunk` VALUES (1529,'aaa.zip',2,20971520,20971520,127359793,6,'59cecb53285a9c6ec8f9f430b2cb5512','aaa.zip','2022-10-12 17:18:24','2022-10-12 17:18:24'),(1530,'aaa.zip',4,20971520,20971520,127359793,6,'59cecb53285a9c6ec8f9f430b2cb5512','aaa.zip','2022-10-12 17:18:24','2022-10-12 17:18:24'),(1531,'aaa.zip',5,20971520,20971520,127359793,6,'59cecb53285a9c6ec8f9f430b2cb5512','aaa.zip','2022-10-12 17:18:24','2022-10-12 17:18:24'),(1532,'aaa.zip',6,20971520,22502193,127359793,6,'59cecb53285a9c6ec8f9f430b2cb5512','aaa.zip','2022-10-12 17:18:27','2022-10-12 17:18:27');
/*!40000 ALTER TABLE `file_chunk` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `file_local_storage`
--

DROP TABLE IF EXISTS `file_local_storage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `file_local_storage` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `real_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '文件真实的名称',
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '文件名',
  `suffix` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '后缀',
  `path` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '路径',
  `type` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '类型',
  `size` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '大小',
  `identifier` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT 'md5校验码\r\n',
  `create_by` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '更新者',
  `create_time` datetime,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3361 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT COMMENT='文件存储';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `file_local_storage`
--

LOCK TABLES `file_local_storage` WRITE;
/*!40000 ALTER TABLE `file_local_storage` DISABLE KEYS */;
INSERT INTO `file_local_storage` VALUES (3360,'aaa.zip','aaa.zip','.zip','aaa.zip','其他','1GB   ','59cecb53285a9c6ec8f9f430b2cb5512','hopeurl-file-center','hopeurl-file-center','2022-10-12 17:18:27','2022-10-12 17:18:27');
/*!40000 ALTER TABLE `file_local_storage` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='消息仓库';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message_store`
--

LOCK TABLES `message_store` WRITE;
/*!40000 ALTER TABLE `message_store` DISABLE KEYS */;
INSERT INTO `message_store` VALUES (1,'测试标题','12312321',NULL,NULL,NULL,NULL),(2,'测试标题','12312321',NULL,NULL,NULL,NULL),(3,'测试标题','我是测试内容',NULL,NULL,NULL,NULL),(4,'测试标题','我是测试内容',NULL,NULL,NULL,NULL),(5,'测试标题','我能收到消息吗？',NULL,NULL,NULL,NULL),(6,'测试标题','我能收到消息吗？',NULL,NULL,NULL,NULL),(7,'测试标题','我能收到消息吗？',NULL,NULL,NULL,NULL),(8,'测试标题','我能收到消息吗？',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `message_store` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sec_interface`
--

DROP TABLE IF EXISTS `sec_interface`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sec_interface` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '接口编号',
  `module_path` varchar(128) DEFAULT NULL COMMENT '模块路径',
  `module_name` varchar(40) DEFAULT NULL COMMENT '模块名称',
  `path` varchar(256) DEFAULT NULL COMMENT '接口路径',
  `perm_type` char(1) DEFAULT '1' COMMENT '权限type[1=同时满足(默认)/0=满足其中一个即可]',
  `description` varchar(256) DEFAULT NULL COMMENT '接口描述',
  `version` varchar(10) DEFAULT NULL COMMENT '版本信息',
  `use_flag` char(1) DEFAULT '1' COMMENT '是否可用[0=不可用/1=可用(默认)]',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='接口表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sec_interface`
--

LOCK TABLES `sec_interface` WRITE;
/*!40000 ALTER TABLE `sec_interface` DISABLE KEYS */;
INSERT INTO `sec_interface` VALUES (1,'/test','测试模块','/sayHello','1','你好啊','1.0.0','1'),(2,'/test','测试模块','/doUpdateInfo','1','更新用户信息','1.0.0','1'),(3,'/test','测试模块','/testPerms','0','测试权限','1.0.0','1');
/*!40000 ALTER TABLE `sec_interface` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sec_interface_perm`
--

DROP TABLE IF EXISTS `sec_interface_perm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sec_interface_perm` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '授权编号',
  `menu_id` int DEFAULT NULL COMMENT '菜单编号',
  `interface_id` int DEFAULT NULL COMMENT '接口编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='接口权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sec_interface_perm`
--

LOCK TABLES `sec_interface_perm` WRITE;
/*!40000 ALTER TABLE `sec_interface_perm` DISABLE KEYS */;
INSERT INTO `sec_interface_perm` VALUES (1,3,1),(2,4,1),(3,5,1),(4,6,1),(5,3,2),(6,4,2),(7,5,2),(8,3,3),(9,4,3),(10,5,3),(11,7,3);
/*!40000 ALTER TABLE `sec_interface_perm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sec_menu`
--

DROP TABLE IF EXISTS `sec_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sec_menu` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '菜单编号',
  `name` varchar(256) DEFAULT NULL COMMENT '菜单名称',
  `path` varchar(256) DEFAULT NULL COMMENT '路由地址',
  `perms` varchar(128) DEFAULT NULL COMMENT '权限信息',
  `type` char(1) DEFAULT NULL COMMENT '菜单类型[0=顶级菜单/1=二级菜单/3=按钮]',
  `sort` tinyint DEFAULT NULL COMMENT '菜单排序',
  `parent_id` int DEFAULT NULL COMMENT '父类菜单',
  `use_flag` char(1) DEFAULT NULL COMMENT '是否在用[0=否/1=是(默认)]',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='菜单项';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sec_menu`
--

LOCK TABLES `sec_menu` WRITE;
/*!40000 ALTER TABLE `sec_menu` DISABLE KEYS */;
INSERT INTO `sec_menu` VALUES (1,'用户管理','/user',NULL,'1',1,NULL,'1'),(2,'个人中心','/user/center',NULL,'2',1,1,'1'),(3,'浏览',NULL,'user:center:view','3',1,2,'1'),(4,'增加',NULL,'user:center:add','3',2,2,'1'),(5,'修改',NULL,'user:center:update','3',3,2,'1'),(6,'删除',NULL,'user:center:delete','3',4,2,'1'),(7,'测试',NULL,'user:center:test','3',5,2,'1');
/*!40000 ALTER TABLE `sec_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sec_menu_role`
--

DROP TABLE IF EXISTS `sec_menu_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sec_menu_role` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '记录编号',
  `role_id` int DEFAULT NULL COMMENT '角色编号',
  `menu_id` int DEFAULT NULL COMMENT '菜单编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='菜单角色中间表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sec_menu_role`
--

LOCK TABLES `sec_menu_role` WRITE;
/*!40000 ALTER TABLE `sec_menu_role` DISABLE KEYS */;
INSERT INTO `sec_menu_role` VALUES (1,1,3),(2,1,4),(3,2,3),(4,1,5),(5,1,6),(6,2,4);
/*!40000 ALTER TABLE `sec_menu_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sec_role`
--

DROP TABLE IF EXISTS `sec_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sec_role` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '角色编号',
  `name` varchar(128) DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sec_role`
--

LOCK TABLES `sec_role` WRITE;
/*!40000 ALTER TABLE `sec_role` DISABLE KEYS */;
INSERT INTO `sec_role` VALUES (1,'admin'),(2,'dev'),(8,'docs');
/*!40000 ALTER TABLE `sec_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sec_role_user`
--

DROP TABLE IF EXISTS `sec_role_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sec_role_user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '记录行编号',
  `role_id` int DEFAULT NULL COMMENT '角色编号',
  `user_id` int DEFAULT NULL COMMENT '用户编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色用户中间表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sec_role_user`
--

LOCK TABLES `sec_role_user` WRITE;
/*!40000 ALTER TABLE `sec_role_user` DISABLE KEYS */;
INSERT INTO `sec_role_user` VALUES (1,1,1);
/*!40000 ALTER TABLE `sec_role_user` ENABLE KEYS */;
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
INSERT INTO `system_param` VALUES (1,'multiple_login','是否允许同一账号可同时在不同设备上进行登录','1','0','1.0.0','2022-09-22 20:51:08','shiping.song','2022-09-22 20:51:08','shiping.song'),(2,'async_message','是否开启异步消息通知','0','0','1.0.0','2022-09-22 21:32:16','shiping.song','2022-09-22 21:32:16','shiping.song');
/*!40000 ALTER TABLE `system_param` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test_top`
--

DROP TABLE IF EXISTS `test_top`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `test_top` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '编号',
  `time` varchar(128) DEFAULT NULL COMMENT '时间格式化',
  `name` varchar(30) DEFAULT NULL COMMENT '名称',
  `count` int DEFAULT NULL COMMENT '计数标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='测试库';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_top`
--

LOCK TABLES `test_top` WRITE;
/*!40000 ALTER TABLE `test_top` DISABLE KEYS */;
INSERT INTO `test_top` VALUES (1,'202209140946','张三',12),(2,'202209140947','李四',12),(3,'202209140952','张三',15),(4,'202209151000','王五',22),(5,'202209151001','张三',12),(6,'202209151003','王五',23),(7,'202209151004','赵六',12),(8,'202209151104','赵六',32);
/*!40000 ALTER TABLE `test_top` ENABLE KEYS */;
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
INSERT INTO `user` VALUES (1,'admin','$2a$10$6W.o389nrrePP8F7M1fv..vUbCNuOD30/ZCvbTDFV5bVIc4TbgYUO');
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
  `message_id` int DEFAULT NULL COMMENT '消息编号',
  `push_flag` char(1) DEFAULT NULL COMMENT '是否已推送[0=否(默认)/1=是]',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户消息中间表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_message`
--

LOCK TABLES `user_message` WRITE;
/*!40000 ALTER TABLE `user_message` DISABLE KEYS */;
INSERT INTO `user_message` VALUES (1,1,3,'0'),(2,1,4,'0'),(3,1,5,'0'),(4,1,6,'1'),(5,1,7,'1'),(6,1,8,'1');
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

-- Dump completed on 2022-10-12 17:48:09
