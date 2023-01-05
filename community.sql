-- MariaDB dump 10.19  Distrib 10.9.4-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: community
-- ------------------------------------------------------
-- Server version	10.9.4-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(16) NOT NULL,
  `description` varchar(32) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES
(1,'Question','Ask Question','2022-12-24 16:50:30','2022-12-24 16:50:30'),
(2,'Advice','Make Advice','2022-12-24 16:50:30','2022-12-24 16:50:30'),
(3,'Post','Publish Post','2022-12-24 16:50:30','2022-12-24 16:50:30');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `post_id` bigint(20) NOT NULL,
  `content` text NOT NULL,
  `vote_up` int(11) NOT NULL DEFAULT 0,
  `vote_down` int(11) NOT NULL DEFAULT 0,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `del` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES
(1,1,1,'First Comment',12,0,'2023-01-03 19:39:56','2023-01-04 14:02:02',0),
(2,1,1,'First Comment',5,10,'2023-01-03 19:40:22','2023-01-04 14:02:02',0),
(3,1,1,'123',7,0,'2023-01-04 11:00:16','2023-01-04 14:02:01',0),
(4,1,1,'123',0,0,'2023-01-04 12:52:29','2023-01-04 14:02:01',0),
(5,1,1,'123',0,0,'2023-01-04 12:52:30','2023-01-04 14:02:00',0),
(6,1,1,'123',0,0,'2023-01-04 12:52:30','2023-01-04 13:56:03',0),
(7,1,1,'123',0,0,'2023-01-04 12:52:31','2023-01-04 12:52:31',0),
(8,1,1,'123',0,0,'2023-01-04 12:52:31','2023-01-04 13:55:25',0),
(9,1,1,'123',0,0,'2023-01-04 12:52:31','2023-01-04 12:52:31',0),
(10,1,1,'123',0,0,'2023-01-04 12:52:32','2023-01-04 12:52:32',0),
(11,1,1,'123',0,0,'2023-01-04 12:52:32','2023-01-04 12:52:32',0),
(12,1,1,'123',0,0,'2023-01-04 12:52:32','2023-01-04 12:52:32',0),
(13,1,1,'123',0,0,'2023-01-04 12:52:33','2023-01-04 12:52:33',0),
(14,1,1,'123',0,0,'2023-01-04 12:52:33','2023-01-04 12:52:33',0),
(15,1,1,'123',0,0,'2023-01-04 12:52:34','2023-01-04 12:52:34',0),
(16,2,5,'123',0,0,'2023-01-04 13:48:38','2023-01-04 13:48:38',0),
(17,2,5,'456',0,0,'2023-01-04 13:48:44','2023-01-04 13:48:44',0),
(18,1,5,'123',0,0,'2023-01-04 13:49:07','2023-01-04 13:49:07',0),
(19,2,11,'123',0,0,'2023-01-04 13:53:23','2023-01-04 13:53:23',0),
(20,1,18,'1123',0,0,'2023-01-04 19:14:42','2023-01-04 19:14:42',0),
(21,1,18,'123',0,0,'2023-01-04 19:57:09','2023-01-05 10:56:37',0),
(22,1,18,'1234',0,0,'2023-01-04 20:54:00','2023-01-04 20:54:00',0),
(23,1,43,'123',0,0,'2023-01-05 10:03:59','2023-01-05 10:03:59',0),
(24,1,43,'123',7,0,'2023-01-05 10:06:24','2023-01-05 10:40:23',0),
(25,1,1,'123',0,0,'2023-01-05 10:22:49','2023-01-05 10:22:49',0),
(26,1,44,'comment',15,14,'2023-01-05 10:58:03','2023-01-05 11:02:31',0),
(27,1,8,'hello',5,0,'2023-01-05 11:08:46','2023-01-05 11:08:52',0),
(28,1,8,'1231231231',0,0,'2023-01-05 11:15:45','2023-01-05 11:15:45',0),
(29,1,8,'qwrqwrqwrwq',0,0,'2023-01-05 11:17:10','2023-01-05 11:17:10',0);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(16) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES
(1,'post.query'),
(2,'post.view'),
(3,'post.commit'),
(4,'post.publish'),
(5,'post.delete'),
(6,'comment.delete'),
(7,'user.add'),
(8,'user.ban'),
(9,'user.query'),
(10,'post.view.draft'),
(11,'post.view.delete'),
(12,'comment.publish');
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `post` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `category_id` int(11) NOT NULL,
  `title` varchar(32) NOT NULL,
  `content` text NOT NULL,
  `draft` tinyint(1) NOT NULL,
  `vote_up` int(11) NOT NULL DEFAULT 0,
  `vote_down` int(11) NOT NULL DEFAULT 0,
  `view_count` int(11) NOT NULL DEFAULT 0,
  `comment_count` int(11) NOT NULL DEFAULT 0,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `del` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES
(1,1,1,'First Quest','# First Quest\n\n```java\npublic static void main(String[] args) {\n  System.out.println(\"First Post\")\n}\n```',0,0,0,95,16,'2022-12-25 13:08:03','2023-01-05 10:27:48',0),
(2,1,2,'First Advice','# First Advice',0,0,0,5,0,'2022-12-26 09:54:23','2022-12-26 18:10:36',0),
(3,1,3,'First Post','# First Post',0,0,0,6,0,'2022-12-26 12:04:16','2022-12-26 12:04:16',0),
(4,2,1,'How to use Markdown Editor','# Hello ',0,0,0,19,0,'2022-12-26 16:57:09','2023-01-04 14:01:58',0),
(5,1,1,'Hello Markdown Editor','# Hello Markdown Editor',0,0,0,11,3,'2022-12-26 18:10:16','2023-01-04 13:27:40',0),
(6,1,1,'Hello Markdown Editor','# Hello Markdown Editor',0,0,0,1,0,'2022-12-26 18:10:25','2022-12-26 18:10:25',0),
(7,1,1,'Markdown','# H1\n## H2\n### H3\n#### H4\n##### H5\n###### H6',0,0,0,3,0,'2022-12-26 18:13:26','2022-12-26 18:13:26',0),
(8,1,1,'Markdown','# H1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n## H2\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n### H3\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n#### H4\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n##### H5\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n###### H6\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1',0,4,0,15,3,'2022-12-26 18:13:29','2023-01-05 11:17:10',0),
(9,1,1,'Markdown','# H1\n## H2\n### H3\n#### H4\n##### H5\n###### H6',0,0,0,2,0,'2022-12-26 18:13:53','2022-12-26 18:13:53',0),
(10,1,1,'Markdown','# H1\n## H2\n### H3\n#### H4\n##### H5\n###### H6',0,0,0,3,0,'2022-12-26 18:14:09','2022-12-26 18:14:09',0),
(11,1,1,'Markdown','# H1\n## H2\n### H3\n#### H4\n##### H5\n###### H6',0,0,0,3,1,'2022-12-26 18:14:11','2022-12-26 18:14:11',0),
(12,1,1,'Markdown','# H1\n## H2\n### H3\n#### H4\n##### H5\n###### H6',0,0,0,0,0,'2022-12-26 18:14:11','2022-12-26 18:14:11',0),
(13,1,1,'Markdown','# H1\n## H2\n### H3\n#### H4\n##### H5\n###### H6',0,0,0,0,0,'2022-12-26 18:14:12','2022-12-26 18:14:12',0),
(14,1,1,'Markdown','# H1\n## H2\n### H3\n#### H4\n##### H5\n###### H6',0,0,0,0,0,'2022-12-26 18:14:12','2022-12-26 18:14:12',0),
(15,1,1,'Markdown','# H1\n## H2\n### H3\n#### H4\n##### H5\n###### H6',0,0,0,0,0,'2022-12-26 18:14:12','2022-12-26 18:14:12',0),
(16,1,1,'Markdown','# H1\n## H2\n### H3\n#### H4\n##### H5\n###### H6',0,0,0,0,0,'2022-12-26 18:14:12','2022-12-26 18:14:12',0),
(17,1,1,'Markdown','# H1\n## H2\n### H3\n#### H4\n##### H5\n###### H6',0,0,0,0,0,'2022-12-26 18:14:13','2022-12-26 18:14:13',0),
(18,1,1,'Markdown','# H1\n## H2\n### H3\n#### H4\n##### H5\n###### H6',0,0,0,12,3,'2022-12-26 18:14:13','2023-01-04 20:54:00',0),
(19,1,1,'Markdown','# H1\n## H2\n### H3\n#### H4\n##### H5\n###### H6',0,0,0,0,0,'2022-12-26 18:14:13','2022-12-26 18:14:13',0),
(20,1,1,'Markdown','# H1\n## H2\n### H3\n#### H4\n##### H5\n###### H6',0,0,0,0,0,'2022-12-26 18:14:14','2022-12-26 18:14:14',0),
(21,1,1,'Markdown','# H1\n## H2\n### H3\n#### H4\n##### H5\n###### H6',0,0,0,0,0,'2022-12-26 18:14:14','2022-12-26 18:14:14',0),
(22,1,1,'Markdown','# H1\n## H2\n### H3\n#### H4\n##### H5\n###### H6',0,0,0,0,0,'2022-12-26 18:14:14','2022-12-26 18:14:14',0),
(23,1,1,'Markdown','# H1\n## H2\n### H3\n#### H4\n##### H5\n###### H6',0,0,0,0,0,'2022-12-26 18:14:15','2022-12-26 18:14:15',0),
(24,1,2,'Markdown','# H1\n## H2\n### H3\n#### H4\n##### H5\n###### H6',0,0,0,0,0,'2022-12-26 18:14:22','2022-12-26 18:14:22',0),
(25,1,2,'Markdown','# H1\n## H2\n### H3\n#### H4\n##### H5\n###### H6',0,0,0,0,0,'2022-12-26 18:14:22','2022-12-26 18:14:22',0),
(26,1,2,'Markdown','# H1\n## H2\n### H3\n#### H4\n##### H5\n###### H6',0,0,0,0,0,'2022-12-26 18:14:22','2022-12-26 18:14:22',0),
(27,1,2,'Markdown','# H1\n## H2\n### H3\n#### H4\n##### H5\n###### H6',0,0,0,0,0,'2022-12-26 18:14:22','2022-12-26 18:14:22',0),
(28,1,2,'Markdown','# H1\n## H2\n### H3\n#### H4\n##### H5\n###### H6',0,0,0,0,0,'2022-12-26 18:14:23','2022-12-26 18:14:23',0),
(29,1,2,'Markdown','# H1\n## H2\n### H3\n#### H4\n##### H5\n###### H6',0,0,0,0,0,'2022-12-26 18:14:23','2022-12-26 18:14:23',0),
(30,1,2,'Markdown','# H1\n## H2\n### H3\n#### H4\n##### H5\n###### H6',0,0,0,0,0,'2022-12-26 18:14:23','2022-12-26 18:14:23',0),
(31,1,2,'Markdown','# H1\n## H2\n### H3\n#### H4\n##### H5\n###### H6',0,0,0,0,0,'2022-12-26 18:14:24','2022-12-26 18:14:24',0),
(32,1,2,'Markdown','# H1\n## H2\n### H3\n#### H4\n##### H5\n###### H6',0,0,0,0,0,'2022-12-26 18:14:24','2022-12-26 18:14:24',0),
(33,1,3,'Markdown','# H1\n## H2\n### H3\n#### H4\n##### H5\n###### H6',0,0,0,0,0,'2022-12-26 18:14:27','2022-12-26 18:14:27',0),
(34,1,3,'Markdown','# H1\n## H2\n### H3\n#### H4\n##### H5\n###### H6',0,0,0,0,0,'2022-12-26 18:14:27','2022-12-26 18:14:27',0),
(35,1,3,'Markdown','# H1\n## H2\n### H3\n#### H4\n##### H5\n###### H6',0,0,0,0,0,'2022-12-26 18:14:27','2022-12-26 18:14:27',0),
(36,1,3,'Markdown','# H1\n## H2\n### H3\n#### H4\n##### H5\n###### H6',0,0,0,0,0,'2022-12-26 18:14:27','2022-12-26 18:14:27',0),
(37,1,3,'Markdown','# H1\n## H2\n### H3\n#### H4\n##### H5\n###### H6',0,0,0,0,0,'2022-12-26 18:14:28','2022-12-26 18:14:28',0),
(38,1,3,'Markdown','# H1\n## H2\n### H3\n#### H4\n##### H5\n###### H6',0,0,0,0,0,'2022-12-26 18:14:28','2022-12-26 18:14:28',0),
(39,1,3,'Markdown','# H1\n## H2\n### H3\n#### H4\n##### H5\n###### H6',0,0,0,0,0,'2022-12-26 18:14:28','2022-12-26 18:14:28',0),
(40,1,3,'Markdown','# H1\n## H2\n### H3\n#### H4\n##### H5\n###### H6',0,0,0,0,0,'2022-12-26 18:14:29','2022-12-26 18:14:29',0),
(41,1,3,'Markdown','# H1\n## H2\n### H3\n#### H4\n##### H5\n###### H6',0,0,0,0,0,'2022-12-26 18:14:29','2022-12-26 18:14:29',0),
(42,1,3,'Markdown','# H1\n## H2\n### H3\n#### H4\n##### H5\n###### H6',0,0,0,0,0,'2022-12-26 18:14:29','2022-12-26 18:14:29',0),
(43,1,1,'Hello','# Hello Markdown Editor',0,9,4,27,2,'2023-01-05 09:46:59','2023-01-05 10:32:27',0),
(44,1,1,'Hello','# Question',0,6,0,15,1,'2023-01-05 10:57:34','2023-01-05 10:58:03',0);
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(16) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES
(1,'super_admin'),
(2,'admin'),
(3,'user'),
(4,'banned');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_permission_relation`
--

DROP TABLE IF EXISTS `role_permission_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_permission_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_permission_relation`
--

LOCK TABLES `role_permission_relation` WRITE;
/*!40000 ALTER TABLE `role_permission_relation` DISABLE KEYS */;
INSERT INTO `role_permission_relation` VALUES
(1,1,1),
(2,1,2),
(3,1,3),
(4,1,4),
(5,1,5),
(6,1,6),
(7,1,7),
(8,1,8),
(9,2,1),
(10,2,2),
(11,2,3),
(12,2,4),
(13,2,5),
(14,2,6),
(15,3,1),
(16,3,2),
(17,3,3),
(18,3,4),
(19,1,9),
(20,2,9),
(21,1,10),
(22,1,11),
(23,2,10),
(24,2,11),
(25,1,12),
(26,2,12),
(27,3,12);
/*!40000 ALTER TABLE `role_permission_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(16) NOT NULL,
  `password` varchar(32) NOT NULL,
  `avatar` varchar(128) DEFAULT NULL,
  `post_count` int(11) NOT NULL DEFAULT 0,
  `comment_count` int(11) NOT NULL DEFAULT 0,
  `banned` tinyint(1) NOT NULL DEFAULT 0,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES
(1,'enaium','608231f12a6d311fce5d0af010414d4b','https://www.enaium.cn/logo.png',43,26,0,'2022-12-20 20:08:58','2023-01-05 10:56:16'),
(2,'normal','608231f12a6d311fce5d0af010414d4b',NULL,1,0,0,'2022-12-26 16:21:14','2023-01-05 10:57:00'),
(3,'user','608231f12a6d311fce5d0af010414d4b',NULL,0,0,0,'2023-01-02 18:26:59','2023-01-05 10:57:01'),
(4,'user1','608231f12a6d311fce5d0af010414d4b',NULL,0,0,0,'2023-01-02 18:27:32','2023-01-02 18:27:32'),
(5,'user2','608231f12a6d311fce5d0af010414d4b',NULL,0,0,0,'2023-01-02 18:27:35','2023-01-02 18:27:35'),
(6,'user3','608231f12a6d311fce5d0af010414d4b',NULL,0,0,0,'2023-01-02 18:27:37','2023-01-02 18:27:37'),
(7,'user4','608231f12a6d311fce5d0af010414d4b',NULL,0,0,0,'2023-01-02 18:27:40','2023-01-02 18:27:40'),
(8,'normal1','608231f12a6d311fce5d0af010414d4b',NULL,0,0,0,'2023-01-02 18:28:17','2023-01-02 18:28:17'),
(9,'normal2','608231f12a6d311fce5d0af010414d4b',NULL,0,0,0,'2023-01-02 18:28:23','2023-01-02 18:28:23'),
(10,'normal3','608231f12a6d311fce5d0af010414d4b',NULL,0,0,0,'2023-01-02 18:28:26','2023-01-02 18:28:26'),
(11,'normal4','608231f12a6d311fce5d0af010414d4b',NULL,0,0,0,'2023-01-02 18:28:29','2023-01-02 18:28:29'),
(12,'normal5','608231f12a6d311fce5d0af010414d4b',NULL,0,0,0,'2023-01-02 18:28:31','2023-01-02 18:28:31');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role_relation`
--

DROP TABLE IF EXISTS `user_role_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `role_id` int(11) NOT NULL DEFAULT 3,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role_relation`
--

LOCK TABLES `user_role_relation` WRITE;
/*!40000 ALTER TABLE `user_role_relation` DISABLE KEYS */;
INSERT INTO `user_role_relation` VALUES
(1,1,1),
(2,2,3),
(3,3,3),
(4,4,3),
(5,5,3),
(6,6,3),
(7,7,3),
(8,8,3),
(9,9,3),
(10,10,3),
(11,11,3),
(12,12,3);
/*!40000 ALTER TABLE `user_role_relation` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-05  3:53:29
