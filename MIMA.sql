CREATE DATABASE  IF NOT EXISTS `mima` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `mima`;
-- MySQL dump 10.13  Distrib 8.0.15, for macos10.14 (x86_64)
--
-- Host: localhost    Database: mima
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `istruzioni`
--

DROP TABLE IF EXISTS `istruzioni`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `istruzioni` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_tasks` int(11) NOT NULL,
  `nome_istruzioni` varchar(45) NOT NULL,
  `durata` int(11) NOT NULL DEFAULT '0',
  `gcode_file` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_task` (`id_tasks`),
  CONSTRAINT `fk_task` FOREIGN KEY (`id_tasks`) REFERENCES `tasks` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `istruzioni`
--

LOCK TABLES `istruzioni` WRITE;
/*!40000 ALTER TABLE `istruzioni` DISABLE KEYS */;
INSERT INTO `istruzioni` VALUES (3,12,'input',2,'file1232'),(4,12,'verniciatura laterale',50,'file3244'),(5,12,'verniciatura posteriore',40,'file2312'),(6,12,'output',2,'file2311');
/*!40000 ALTER TABLE `istruzioni` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `items`
--

DROP TABLE IF EXISTS `items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `items` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descrizione` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items`
--

LOCK TABLES `items` WRITE;
/*!40000 ALTER TABLE `items` DISABLE KEYS */;
INSERT INTO `items` VALUES (1,'telaio');
/*!40000 ALTER TABLE `items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lavorazioni`
--

DROP TABLE IF EXISTS `lavorazioni`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `lavorazioni` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_item` int(11) NOT NULL,
  `id_istruzione` int(11) NOT NULL,
  `durata` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `id_item` (`id_item`),
  KEY `id_istruzione` (`id_istruzione`),
  CONSTRAINT `fk_istruzione` FOREIGN KEY (`id_istruzione`) REFERENCES `istruzioni` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_item` FOREIGN KEY (`id_item`) REFERENCES `items` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lavorazioni`
--

LOCK TABLES `lavorazioni` WRITE;
/*!40000 ALTER TABLE `lavorazioni` DISABLE KEYS */;
INSERT INTO `lavorazioni` VALUES (1,1,3,2),(2,1,4,50),(3,1,5,40),(4,1,6,2);
/*!40000 ALTER TABLE `lavorazioni` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `macchinari`
--

DROP TABLE IF EXISTS `macchinari`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `macchinari` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `modello` varchar(45) NOT NULL,
  `id_utente` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_utente` (`id_utente`),
  CONSTRAINT `fk_utente` FOREIGN KEY (`id_utente`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `macchinari`
--

LOCK TABLES `macchinari` WRITE;
/*!40000 ALTER TABLE `macchinari` DISABLE KEYS */;
INSERT INTO `macchinari` VALUES (5,'Verniciatrice','AK22',9),(6,'Fresatrice','J4453',9);
/*!40000 ALTER TABLE `macchinari` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operazioni_schedulazione`
--

DROP TABLE IF EXISTS `operazioni_schedulazione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `operazioni_schedulazione` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ordine` int(3) NOT NULL DEFAULT '0',
  `id_task` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_task` (`id_task`),
  CONSTRAINT `fk2_task` FOREIGN KEY (`id_task`) REFERENCES `tasks` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operazioni_schedulazione`
--

LOCK TABLES `operazioni_schedulazione` WRITE;
/*!40000 ALTER TABLE `operazioni_schedulazione` DISABLE KEYS */;
INSERT INTO `operazioni_schedulazione` VALUES (1,1,12),(2,2,13);
/*!40000 ALTER TABLE `operazioni_schedulazione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedulazione`
--

DROP TABLE IF EXISTS `schedulazione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `schedulazione` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `timestamp_inizio` int(11) NOT NULL,
  `timestamp_fine` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedulazione`
--

LOCK TABLES `schedulazione` WRITE;
/*!40000 ALTER TABLE `schedulazione` DISABLE KEYS */;
INSERT INTO `schedulazione` VALUES (1,'Schedulazione 1',1547724209,1547725000),(2,'Schedulazione 2',1547725000,1547726050);
/*!40000 ALTER TABLE `schedulazione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tasks`
--

DROP TABLE IF EXISTS `tasks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tasks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descrizione` varchar(45) NOT NULL,
  `id_macchinario` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `descrizione` (`descrizione`,`id_macchinario`),
  KEY `idx_macchinario` (`id_macchinario`),
  CONSTRAINT `tasks_ibfk_1` FOREIGN KEY (`id_macchinario`) REFERENCES `macchinari` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tasks`
--

LOCK TABLES `tasks` WRITE;
/*!40000 ALTER TABLE `tasks` DISABLE KEYS */;
INSERT INTO `tasks` VALUES (13,'asciugatura',5),(12,'verniciatura',5);
/*!40000 ALTER TABLE `tasks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `phone` varchar(45) NOT NULL,
  `rank` tinyint(4) NOT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (8,'pluto','pippo','pippo','pippo',1,'pippo','pippo'),(9,'prova','prova','email2','yeyeeyrysrd',0,'prova','prova'),(10,'prova','prova','prova2','prova2',1,'prova2','prova2');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-02-27 16:31:34
