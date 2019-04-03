USE mima;

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
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,2,NULL,3);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `inputs`
--

LOCK TABLES `inputs` WRITE;
/*!40000 ALTER TABLE `inputs` DISABLE KEYS */;
INSERT INTO `inputs` VALUES (5,5),(6,5),(7,5);
/*!40000 ALTER TABLE `inputs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `instructions`
--

LOCK TABLES `instructions` WRITE;
/*!40000 ALTER TABLE `instructions` DISABLE KEYS */;
INSERT INTO `instructions` VALUES (1,120,'creazionebase.gcode','Creazione base',1),(2,60,'creazionebuchipilastri.gcode','Creazione buchi pilastri',1);
/*!40000 ALTER TABLE `instructions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `items`
--

LOCK TABLES `items` WRITE;
/*!40000 ALTER TABLE `items` DISABLE KEYS */;
INSERT INTO `items` VALUES (1,'Edificio',NULL,1),(2,'Struttura',1,1),(3,'Finiture',1,1),(4,'Impianti',1,1),(5,'Fondazioni',2,1),(6,'Travi',2,1),(7,'Solai',2,1),(8,'Opere murarie',3,1),(9,'Rivestimenti',3,1),(10,'Pavimentazioni',3,1),(11,'Solai',3,1),(12,'Muri',8,1),(13,'Intonaci',8,1),(14,'Idrico',4,1),(15,'Elettrico',4,1),(16,'Riscaldamento',4,1);
/*!40000 ALTER TABLE `items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `items_manufacturings`
--

LOCK TABLES `items_manufacturings` WRITE;
/*!40000 ALTER TABLE `items_manufacturings` DISABLE KEYS */;
/*!40000 ALTER TABLE `items_manufacturings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `machines`
--

LOCK TABLES `machines` WRITE;
/*!40000 ALTER TABLE `machines` DISABLE KEYS */;
INSERT INTO `machines` VALUES (1,'L34JHG3','Home Builder 3D',2),(2,'D4JOUIH4','Car Builder 3D',2);
/*!40000 ALTER TABLE `machines` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `manufacturings`
--

LOCK TABLES `manufacturings` WRITE;
/*!40000 ALTER TABLE `manufacturings` DISABLE KEYS */;
/*!40000 ALTER TABLE `manufacturings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `schedulings`
--

LOCK TABLES `schedulings` WRITE;
/*!40000 ALTER TABLE `schedulings` DISABLE KEYS */;
INSERT INTO `schedulings` VALUES (1,'31/3/2019, 00:59:00','Creazioni struttura','18/3/2019, 23:59:00',1);
/*!40000 ALTER TABLE `schedulings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `task_scheduled`
--

LOCK TABLES `task_scheduled` WRITE;
/*!40000 ALTER TABLE `task_scheduled` DISABLE KEYS */;
INSERT INTO `task_scheduled` VALUES (5,NULL,1,4),(6,NULL,1,1),(7,NULL,1,2),(8,NULL,1,3),(9,2,1,5);
/*!40000 ALTER TABLE `task_scheduled` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `task_scheduled_relations`
--

LOCK TABLES `task_scheduled_relations` WRITE;
/*!40000 ALTER TABLE `task_scheduled_relations` DISABLE KEYS */;
INSERT INTO `task_scheduled_relations` VALUES (6,5),(7,5),(8,5),(9,6),(9,7),(9,8);
/*!40000 ALTER TABLE `task_scheduled_relations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tasks`
--

LOCK TABLES `tasks` WRITE;
/*!40000 ALTER TABLE `tasks` DISABLE KEYS */;
INSERT INTO `tasks` VALUES (1,'Costruzione fondazioni',1),(2,'Costruzione pilastri',1),(3,'Costruzione solai',1),(4,'Accensione macchinario',1),(5,'Assemblamento struttura',1);
/*!40000 ALTER TABLE `tasks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'m@m.it','admin','admin','3',1,'admin','admin'),(2,'m@m.it','user','user','3',0,'user','user'),(3,'m@m.it','employee','employee','3',2,'employee','employee');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `wbs`
--

LOCK TABLES `wbs` WRITE;
/*!40000 ALTER TABLE `wbs` DISABLE KEYS */;
INSERT INTO `wbs` VALUES (1,'Edificio',2);
/*!40000 ALTER TABLE `wbs` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-18 10:58:41
