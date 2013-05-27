-- MySQL dump 10.13  Distrib 5.5.31, for debian-linux-gnu (i686)
--
-- Host: localhost    Database: omavalvonta
-- ------------------------------------------------------
-- Server version	5.5.31-0ubuntu0.12.04.1

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
-- Table structure for table `Batch`
--

DROP TABLE IF EXISTS `Batch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Batch` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `lastModified` datetime NOT NULL,
  `date` datetime DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Batch`
--

LOCK TABLES `Batch` WRITE;
/*!40000 ALTER TABLE `Batch` DISABLE KEYS */;
/*!40000 ALTER TABLE `Batch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Batch_AUD`
--

DROP TABLE IF EXISTS `Batch_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Batch_AUD` (
  `id` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Batch_AUD`
--

LOCK TABLES `Batch_AUD` WRITE;
/*!40000 ALTER TABLE `Batch_AUD` DISABLE KEYS */;
/*!40000 ALTER TABLE `Batch_AUD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Field`
--

DROP TABLE IF EXISTS `Field`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Field` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `lastModified` datetime NOT NULL,
  `fieldType` int(11) NOT NULL,
  `help` longtext,
  `fields_index` int(11) DEFAULT NULL,
  `isImportant` tinyint(1) NOT NULL,
  `isRequired` tinyint(1) NOT NULL,
  `max` double DEFAULT NULL,
  `min` double DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `targetValue` tinyint(1) DEFAULT NULL,
  `fieldset_id` bigint(20) NOT NULL,
  `field_index` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Field`
--

LOCK TABLES `Field` WRITE;
/*!40000 ALTER TABLE `Field` DISABLE KEYS */;
INSERT INTO `Field` VALUES (1,'2013-05-23 08:12:03',4,'Pinnoilla ei silminnähtäviä jäämiä',NULL,0,1,NULL,NULL,'Visuaalinen puhtaustarkastus',1,1,0),(2,'2013-05-23 08:12:03',2,'RLU > 250',NULL,0,1,NULL,250,'Puhtaustason määritys pikamenetelmällä',NULL,1,1),(3,'2013-05-23 08:13:53',3,'',NULL,0,1,NULL,NULL,'Vastaanoton päivämäärä',NULL,2,0),(4,'2013-05-23 08:13:53',2,'',NULL,0,1,NULL,NULL,'Kerman määrä',NULL,2,1),(5,'2013-05-23 08:13:53',2,'',NULL,0,1,NULL,NULL,'Kuoritun maidon määrä',NULL,2,2),(6,'2013-05-23 08:13:53',3,'',NULL,0,1,NULL,NULL,'Raakamaidon määrä',NULL,2,3),(7,'2013-05-23 08:14:55',2,'%',NULL,0,1,NULL,NULL,'Rasvapitoisuus',NULL,3,0),(8,'2013-05-23 08:14:55',2,'%',NULL,0,1,NULL,NULL,'Kuiva-ainepitoisuus',NULL,3,1),(9,'2013-05-23 08:14:55',2,'%',NULL,0,1,NULL,NULL,'Kaseiini',NULL,3,2),(10,'2013-05-23 08:17:42',0,'°C/min',NULL,0,1,NULL,NULL,'Lämpötila/aika',NULL,4,0),(11,'2013-05-23 08:17:42',4,'negatiivinen',NULL,0,0,NULL,NULL,'Peroksidaasikoe',1,4,1),(12,'2013-05-23 08:17:42',4,'negatiivinen',NULL,0,0,NULL,NULL,'Fosfataasikoe',1,4,2),(13,'2013-05-23 08:17:42',2,'< 1 pmy/ml',NULL,0,0,1,NULL,'Enterobakteerit',NULL,4,3),(14,'2013-05-23 08:17:42',2,'%',NULL,0,0,NULL,NULL,'Rasvapitoisuus',NULL,4,4),(15,'2013-05-23 08:20:22',5,'Hapateen spesifikaatiosta',NULL,0,1,NULL,NULL,'Hapatteen nimi',NULL,5,0),(16,'2013-05-23 08:20:22',2,'% Hapateen spesifikaatiosta',NULL,0,1,NULL,NULL,'Siirrostus-%',NULL,5,1),(17,'2013-05-23 08:20:22',2,'g Hapateen spesifikaatiosta',NULL,0,1,NULL,NULL,'Siirrostus-%',NULL,5,2),(18,'2013-05-23 08:20:22',3,'',NULL,0,1,NULL,NULL,'Hapatteen valmistupäivä',NULL,5,3),(19,'2013-05-23 08:21:27',2,'< 1 pmy/g',NULL,0,1,1,NULL,'Enterobakteerit',NULL,6,0),(20,'2013-05-23 08:21:27',2,'< 1 pmy/g',NULL,0,0,1,NULL,'Hiivat ja homeet',NULL,6,1),(21,'2013-05-23 08:35:49',1,'',NULL,0,1,NULL,3,'Ulkonäkö',NULL,7,0),(22,'2013-05-23 08:35:49',1,'',NULL,0,1,NULL,3,'Rakenne',NULL,7,1),(23,'2013-05-23 08:35:49',1,'',NULL,0,1,NULL,3,'Maku + haju',NULL,7,2),(24,'2013-05-23 08:25:41',2,'tuotekohtainen',NULL,0,1,NULL,NULL,'pH',NULL,8,0),(25,'2013-05-23 08:25:41',2,'tuotekohtainen',NULL,0,1,NULL,NULL,'°SH',NULL,8,1),(26,'2013-05-23 08:25:41',2,'≤ 1 pmy/g',NULL,0,1,1,NULL,'Enterobakteerit',NULL,8,2),(27,'2013-05-23 08:25:41',2,'≤ 100 pmy /g',NULL,0,1,100,NULL,'Hiivat ja homeet',NULL,8,3),(28,'2013-05-23 08:26:47',5,'',NULL,0,1,NULL,NULL,'Hillo',NULL,9,0),(29,'2013-05-23 08:26:47',2,'Hillosta riippuen 10-16%',NULL,0,1,NULL,NULL,'Punnitus/ml',NULL,9,1),(30,'2013-05-23 08:27:33',2,'Vähintään -35°C',NULL,0,1,-35,NULL,'Lämpötilamittaus',NULL,10,0),(31,'2013-05-23 08:28:39',2,'Hap. +4- +6°C, \r\nVoi  +4- +6°C, \r\nJlö vähintään -18°C, \r\nJuustot +4- +8°C',NULL,0,1,NULL,NULL,'Lämpötilamittaus',NULL,11,0),(32,'2013-05-23 08:33:50',2,'Hap. < 1pmy/g, \r\nVoi < 10 pmy/g, \r\nJlö < 100 pmy/g, \r\nJuustot < 100pmy/g',NULL,0,1,NULL,NULL,'Enterobakteerit',NULL,12,0),(33,'2013-05-23 08:33:50',2,'Voi ja hap. < 100 pmy/g',NULL,0,1,NULL,NULL,'Hiivat ja homeet',NULL,12,1),(34,'2013-05-23 08:33:50',2,'Voi ja juustot < 100 pmy/g',NULL,0,1,NULL,NULL,'Staphylococcus aureus',NULL,12,2),(35,'2013-05-23 08:33:50',2,'Kerran lukukaudessa. Tutkimus suoritetaan\r\nakkreditoidussa laboratoriossa.',NULL,0,0,NULL,NULL,'Listeria monoc.',NULL,12,3),(36,'2013-05-23 08:33:50',2,'Juustot. Kerran lukukaudessa. Tutkimus suoritetaan\r\nakkreditoidussa laboratoriossa.',NULL,0,0,NULL,NULL,'Koagulaasi positiiviset stafylokokit',NULL,12,4),(37,'2013-05-23 08:33:50',2,'< 1 pmy/g. Juustot ja jlö. Kerran lukukaudessa. Tutkimus suoritetaan\r\nakkreditoidussa laboratoriossa.',NULL,0,0,1,NULL,'E.coli',NULL,12,5),(38,'2013-05-23 08:35:04',1,'',NULL,0,1,NULL,3,'Ulkonäkö',NULL,13,0),(39,'2013-05-23 08:35:04',1,'',NULL,0,1,NULL,3,'Rakenne',NULL,13,1),(40,'2013-05-23 08:35:04',1,'',NULL,0,1,NULL,3,'Maku ja haju',NULL,13,2),(41,'2013-05-23 08:42:49',3,'',NULL,0,1,NULL,NULL,'Näytteet otettu',NULL,14,0),(42,'2013-05-23 08:48:59',2,'VRBG',NULL,0,0,NULL,NULL,'Kasvusto maljalla (pesäkkeiden määrä)',NULL,15,0),(43,'2013-05-23 08:48:59',2,'pmy / ml',NULL,0,0,100,10,'Enterobakteerit',NULL,15,1),(44,'2013-05-23 08:48:59',2,'°C',NULL,0,0,NULL,NULL,'Lämpötila',NULL,15,2),(45,'2013-05-23 08:49:31',2,'VRBG',NULL,0,0,NULL,NULL,'Kasvusto maljalla (pesäkkeiden määrä)',NULL,16,0),(46,'2013-05-23 08:49:31',2,'pmy / ml',NULL,0,0,100,10,'Enterobakteerit',NULL,16,1),(47,'2013-05-23 08:49:31',2,'°C',NULL,0,0,NULL,NULL,'Lämpötila',NULL,16,2),(48,'2013-05-23 08:49:12',2,'VRBG',NULL,0,0,NULL,NULL,'Kasvusto maljalla (pesäkkeiden määrä)',NULL,17,0),(49,'2013-05-23 08:49:12',2,'pmy / ml',NULL,0,0,100,10,'Enterobakteerit',NULL,17,1),(50,'2013-05-23 08:49:12',2,'°C',NULL,0,0,NULL,NULL,'Lämpötila',NULL,17,2),(51,'2013-05-23 09:00:39',4,'',NULL,0,1,NULL,NULL,'Lattiat',1,18,0),(52,'2013-05-23 09:00:39',4,'',NULL,0,1,NULL,NULL,'Käsienpesualtaat ja hanat',NULL,18,1),(53,'2013-05-23 09:00:39',4,'',NULL,0,0,NULL,NULL,'Lattiakaivot',1,18,2),(54,'2013-05-23 09:00:39',4,'',NULL,0,1,NULL,NULL,'Kaikki välineet ja tarvikkeet (ennen)',1,18,3),(55,'2013-05-23 09:00:39',4,'',NULL,0,0,NULL,NULL,'Kaikki välineet ja tarvikkeet (jälkeen)',1,18,4),(56,'2013-05-23 09:00:39',4,'',NULL,0,1,NULL,NULL,'Vispari (ennen)',1,18,5),(57,'2013-05-23 09:00:39',4,'',NULL,0,1,NULL,NULL,'Vispari (jälkeen)',1,18,6),(58,'2013-05-23 09:00:39',4,'',NULL,0,1,NULL,NULL,'Homogenisaattori (ennen)',NULL,18,7),(59,'2013-05-23 09:00:39',4,'',NULL,0,0,NULL,NULL,'Homogenisaattori (jälkeen)',1,18,8),(60,'2013-05-23 09:00:39',4,'',NULL,0,1,NULL,NULL,'Työtasot ja liedet (ennen)',1,18,9),(61,'2013-05-23 09:00:39',4,'',NULL,0,1,NULL,NULL,'Työtasot ja liedet (jälkeen)',1,18,10),(62,'2013-05-23 09:00:39',4,'',NULL,0,1,NULL,NULL,'Vaa´at (ennen)',1,18,11),(63,'2013-05-23 09:00:39',4,'',NULL,0,1,NULL,NULL,'Vaa´at (jälkeen)',1,18,12),(64,'2013-05-23 09:00:39',4,'jos tilassa sellainen on',NULL,0,0,NULL,NULL,'Jääkaappi (ennen)',1,18,13),(65,'2013-05-23 09:00:39',4,'jos tilassa sellainen on',NULL,0,0,NULL,NULL,'Jääkaappi (jälkeen)',1,18,14);
/*!40000 ALTER TABLE `Field` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Field_AUD`
--

DROP TABLE IF EXISTS `Field_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Field_AUD` (
  `id` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `fieldType` int(11) DEFAULT NULL,
  `help` longtext,
  `fields_index` int(11) DEFAULT NULL,
  `isImportant` tinyint(1) DEFAULT NULL,
  `isRequired` tinyint(1) DEFAULT NULL,
  `max` double DEFAULT NULL,
  `min` double DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `targetValue` tinyint(1) DEFAULT NULL,
  `fieldset_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Field_AUD`
--

LOCK TABLES `Field_AUD` WRITE;
/*!40000 ALTER TABLE `Field_AUD` DISABLE KEYS */;
INSERT INTO `Field_AUD` VALUES (1,3,0,4,'Pinnoilla ei silminnähtäviä jäämiä',0,0,1,NULL,NULL,'Visuaalinen puhtaustarkastus',1,1),(2,3,0,2,'RLU > 250',1,0,1,NULL,250,'Puhtaustason määritys pikamenetelmällä',NULL,1),(3,4,0,3,'',0,0,1,NULL,NULL,'Vastaanoton päivämäärä',NULL,2),(4,4,0,2,'',1,0,1,NULL,NULL,'Kerman määrä',NULL,2),(5,4,0,2,'',2,0,1,NULL,NULL,'Kuoritun maidon määrä',NULL,2),(6,4,0,3,'',3,0,1,NULL,NULL,'Raakamaidon määrä',NULL,2),(7,5,0,2,'%',0,0,1,NULL,NULL,'Rasvapitoisuus',NULL,3),(8,5,0,2,'%',1,0,1,NULL,NULL,'Kuiva-ainepitoisuus',NULL,3),(9,5,0,2,'%',2,0,1,NULL,NULL,'Kaseiini',NULL,3),(10,6,0,0,'°C/min',0,0,1,NULL,NULL,'Lämpötila/aika',NULL,4),(11,6,0,4,'negatiivinen',1,0,0,NULL,NULL,'Peroksidaasikoe',1,4),(12,6,0,4,'negatiivinen',2,0,0,NULL,NULL,'Fosfataasikoe',1,4),(13,6,0,2,'< 1 pmy/ml',3,0,0,1,NULL,'Enterobakteerit',NULL,4),(14,6,0,2,'%',4,0,0,NULL,NULL,'Rasvapitoisuus',NULL,4),(15,7,0,5,'Hapateen spesifikaatiosta',0,0,1,NULL,NULL,'Hapatteen nimi',NULL,5),(16,7,0,2,'% Hapateen spesifikaatiosta',1,0,1,NULL,NULL,'Siirrostus-%',NULL,5),(17,7,0,2,'g Hapateen spesifikaatiosta',2,0,1,NULL,NULL,'Siirrostus-%',NULL,5),(18,7,0,3,'',3,0,1,NULL,NULL,'Hapatteen valmistupäivä',NULL,5),(19,8,0,2,'< 1 pmy/g',0,0,1,1,NULL,'Enterobakteerit',NULL,6),(20,8,0,2,'< 1 pmy/g',1,0,0,1,NULL,'Hiivat ja homeet',NULL,6),(21,9,0,1,'',0,0,1,NULL,3,'Ulkonäkö',NULL,7),(22,9,0,1,'',1,0,1,NULL,3,'Rakenne',NULL,7),(23,9,0,1,'',2,0,1,NULL,3,'Maku + haju',NULL,7),(24,10,0,2,'tuotekohtainen',0,0,1,NULL,NULL,'pH',NULL,8),(25,10,0,2,'tuotekohtainen',1,0,1,NULL,NULL,'°SH',NULL,8),(26,10,0,2,'≤ 1 pmy/g',2,0,1,1,NULL,'Enterobakteerit',NULL,8),(27,10,0,2,'≤ 100 pmy /g',3,0,1,100,NULL,'Hiivat ja homeet',NULL,8),(28,11,0,5,'',0,0,1,NULL,NULL,'Hillo',NULL,9),(29,11,0,2,'Hillosta riippuen 10-16%',1,0,1,NULL,NULL,'Punnitus/ml',NULL,9),(30,12,0,2,'Vähintään -35°C',0,0,1,-35,NULL,'Lämpötilamittaus',NULL,10),(31,13,0,2,'Hap. +4- +6°C, \r\nVoi  +4- +6°C, \r\nJlö vähintään -18°C, \r\nJuustot +4- +8°C',0,0,1,NULL,NULL,'Lämpötilamittaus',NULL,11),(32,14,0,2,'Hap. < 1pmy/g, \r\nVoi < 10 pmy/g, \r\nJlö < 100 pmy/g, \r\nJuustot < 100pmy/g',0,0,1,NULL,NULL,'Enterobakteerit',NULL,12),(33,14,0,2,'Voi ja hap. < 100 pmy/g',1,0,1,NULL,NULL,'Hiivat ja homeet',NULL,12),(34,14,0,2,'Voi ja juustot < 100 pmy/g',2,0,1,NULL,NULL,'Staphylococcus aureus',NULL,12),(35,14,0,2,'Kerran lukukaudessa. Tutkimus suoritetaan\r\nakkreditoidussa laboratoriossa.',3,0,0,NULL,NULL,'Listeria monoc.',NULL,12),(36,14,0,2,'Juustot. Kerran lukukaudessa. Tutkimus suoritetaan\r\nakkreditoidussa laboratoriossa.',4,0,0,NULL,NULL,'Koagulaasi positiiviset stafylokokit',NULL,12),(37,14,0,2,'< 1 pmy/g. Juustot ja jlö. Kerran lukukaudessa. Tutkimus suoritetaan\r\nakkreditoidussa laboratoriossa.',5,0,0,1,NULL,'E.coli',NULL,12),(38,15,0,1,'',0,0,1,NULL,3,'Ulkonäkö',NULL,13),(39,15,0,1,'',1,0,1,NULL,3,'Rakenne',NULL,13),(40,15,0,1,'',2,0,1,NULL,3,'Maku ja haju',NULL,13),(41,29,0,3,'',0,0,1,NULL,NULL,'Näytteet otettu',NULL,14),(42,30,0,2,'VRBG',0,0,0,NULL,NULL,'Kasvusto maljalla (pesäkkeiden määrä)',NULL,15),(43,30,0,2,'pmy / ml',1,0,0,NULL,NULL,'Enterobakteerit',NULL,15),(43,33,1,2,'pmy / ml',NULL,0,0,100,10,'Enterobakteerit',NULL,15),(44,30,0,2,'°C',2,0,0,NULL,NULL,'Lämpötila',NULL,15),(45,31,0,2,'VRBG',0,0,0,NULL,NULL,'Kasvusto maljalla (pesäkkeiden määrä)',NULL,16),(46,31,0,2,'pmy / ml',1,0,0,NULL,NULL,'Enterobakteerit',NULL,16),(46,35,1,2,'pmy / ml',NULL,0,0,100,10,'Enterobakteerit',NULL,16),(47,31,0,2,'°C',2,0,0,NULL,NULL,'Lämpötila',NULL,16),(48,32,0,2,'VRBG',0,0,0,NULL,NULL,'Kasvusto maljalla (pesäkkeiden määrä)',NULL,17),(49,32,0,2,'pmy / ml',1,0,0,NULL,NULL,'Enterobakteerit',NULL,17),(49,34,1,2,'pmy / ml',NULL,0,0,100,10,'Enterobakteerit',NULL,17),(50,32,0,2,'°C',2,0,0,NULL,NULL,'Lämpötila',NULL,17),(51,39,0,4,'Pesumenetelmä: Matalavaahtopesu, Pesu- ja sanitointiaineet: Fet 25 Vape ja 2-4 % liuos, Pesutiheys: Työpäivän päätteeksi sekä tarvittaessa',0,0,1,NULL,NULL,'Lattiat',1,18),(51,40,1,4,'Pesty',NULL,0,1,NULL,NULL,'Lattiat',1,18),(51,41,1,4,'',NULL,0,1,NULL,NULL,'Lattiat',1,18),(52,41,0,4,'',1,0,1,NULL,NULL,'Käsienpesualtaat ja hanat',NULL,18),(53,41,0,4,'',2,0,0,NULL,NULL,'Lattiakaivot',1,18),(54,41,0,4,'',3,0,1,NULL,NULL,'Kaikki välineet ja tarvikkeet (ennen)',1,18),(55,41,0,4,'',4,0,0,NULL,NULL,'Kaikki välineet ja tarvikkeet (jälkeen)',1,18),(56,41,0,4,'',5,0,1,NULL,NULL,'Vispari (ennen)',1,18),(57,41,0,4,'',6,0,1,NULL,NULL,'Vispari (jälkeen)',1,18),(58,41,0,4,'',7,0,1,NULL,NULL,'Homogenisaattori (ennen)',NULL,18),(59,41,0,4,'',8,0,0,NULL,NULL,'Homogenisaattori (jälkeen)',1,18),(60,41,0,4,'',9,0,1,NULL,NULL,'Työtasot ja liedet (ennen)',1,18),(61,41,0,4,'',10,0,1,NULL,NULL,'Työtasot ja liedet (jälkeen)',1,18),(62,41,0,4,'',11,0,1,NULL,NULL,'Vaa´at (ennen)',1,18),(63,41,0,4,'',12,0,1,NULL,NULL,'Vaa´at (jälkeen)',1,18),(64,41,0,4,'jos tilassa sellainen on',13,0,0,NULL,NULL,'Jääkaappi (ennen)',1,18),(65,41,0,4,'jos tilassa sellainen on',14,0,0,NULL,NULL,'Jääkaappi (jälkeen)',1,18);
/*!40000 ALTER TABLE `Field_AUD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Fieldset`
--

DROP TABLE IF EXISTS `Fieldset`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Fieldset` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `lastModified` datetime NOT NULL,
  `description` longtext,
  `name` varchar(255) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Fieldset`
--

LOCK TABLES `Fieldset` WRITE;
/*!40000 ALTER TABLE `Fieldset` DISABLE KEYS */;
INSERT INTO `Fieldset` VALUES (1,'2013-05-23 08:12:03','','Ennen töiden aloittamista / laitteisto',1),(2,'2013-05-23 08:13:53','','Käytettävä raaka-aine',1),(3,'2013-05-23 08:14:55','','Maidon vakiointi',1),(4,'2013-05-23 08:17:42','','Maidon pastörointi',1),(5,'2013-05-23 08:20:22','','Hapatelisäys',1),(6,'2013-05-23 08:21:27','','Lisäyksen jälkeen/siirron jälkeen juustokattilasta',1),(7,'2013-05-23 08:35:49','Pistein 1-5','Hapatettu tuote (ennen maustamista/kirnuamista) aistinvarainen arviointi',1),(8,'2013-05-23 08:25:41','','Hapatettu tuote (ennen maustamista/kirnuamista)',1),(9,'2013-05-23 08:26:47','','Hillolisäys',1),(10,'2013-05-23 08:27:33','','Karkaisu',1),(11,'2013-05-23 08:28:39','','Varastointi',1),(12,'2013-05-23 08:33:50','','Valmis tuote',1),(13,'2013-05-23 08:35:04','Pistein 1-5','Aistinvarainen arviointi',1),(14,'2013-05-23 08:42:49','','Jäätelön puhtaustarkkailun tulokset',1),(15,'2013-05-23 08:48:59','Näytteenottotiheys 1 kerta /kk','Työskentelytason pinnat',1),(16,'2013-05-23 08:49:31','Näytteenottotiheys ennen vispausta työpäivän alussa','Vispari',1),(17,'2013-05-23 08:49:12','Näytteenottotiheys joka erä','Valmis tuote',1),(18,'2013-05-23 09:00:39','','Jäätelö pesukohteet',1);
/*!40000 ALTER TABLE `Fieldset` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Fieldset_AUD`
--

DROP TABLE IF EXISTS `Fieldset_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Fieldset_AUD` (
  `id` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `description` longtext,
  `name` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Fieldset_AUD`
--

LOCK TABLES `Fieldset_AUD` WRITE;
/*!40000 ALTER TABLE `Fieldset_AUD` DISABLE KEYS */;
INSERT INTO `Fieldset_AUD` VALUES (1,3,0,'','Ennen töiden aloittamista / laitteisto',1),(2,4,0,'','Käytettävä raaka-aine',1),(3,5,0,'','Maidon vakiointi',1),(4,6,0,'','Maidon pastörointi',1),(5,7,0,'','Hapatelisäys',1),(6,8,0,'','Lisäyksen jälkeen/siirron jälkeen juustokattilasta',1),(7,9,0,'','Hapatettu tuote (ennen maustamista/kirnuamista) aistinvarainen arviointi',1),(7,17,1,'Pistein 1-5','Hapatettu tuote (ennen maustamista/kirnuamista) aistinvarainen arviointi',1),(8,10,0,'','Hapatettu tuote (ennen maustamista/kirnuamista)',1),(9,11,0,'','Hillolisäys',1),(10,12,0,'','Karkaisu',1),(11,13,0,'','Varastointi',1),(12,14,0,'','Valmis tuote',1),(13,15,0,'Pistein 1-5','Aistinvarainen arviointi',1),(14,29,0,'','Jäätelön puhtaustarkkailun tulokset',1),(15,30,0,'Näytteenottotiheys 1 kerta /kk','Työskentelytason pinnat',1),(15,33,1,'Näytteenottotiheys 1 kerta /kk','Työskentelytason pinnat',1),(16,31,0,'Näytteenottotiheys ennen vispausta työpäivän alussa','Vispari',1),(16,35,1,'Näytteenottotiheys ennen vispausta työpäivän alussa','Vispari',1),(17,32,0,'Näytteenottotiheys joka erä','Valmis tuote',1),(17,34,1,'Näytteenottotiheys joka erä','Valmis tuote',1),(18,39,0,'','Jäätelö pesukohteet',1),(18,40,1,'','Jäätelö pesukohteet',1),(18,41,1,'','Jäätelö pesukohteet',1);
/*!40000 ALTER TABLE `Fieldset_AUD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FinalProduct`
--

DROP TABLE IF EXISTS `FinalProduct`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FinalProduct` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `lastModified` datetime NOT NULL,
  `amount` double DEFAULT NULL,
  `comment` longtext,
  `date` datetime DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `batch_id` bigint(20) DEFAULT NULL,
  `destiny_id` bigint(20) DEFAULT NULL,
  `unit_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FinalProduct`
--

LOCK TABLES `FinalProduct` WRITE;
/*!40000 ALTER TABLE `FinalProduct` DISABLE KEYS */;
/*!40000 ALTER TABLE `FinalProduct` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FinalProduct_AUD`
--

DROP TABLE IF EXISTS `FinalProduct_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FinalProduct_AUD` (
  `id` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `comment` longtext,
  `date` datetime DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `batch_id` bigint(20) DEFAULT NULL,
  `destiny_id` bigint(20) DEFAULT NULL,
  `unit_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FinalProduct_AUD`
--

LOCK TABLES `FinalProduct_AUD` WRITE;
/*!40000 ALTER TABLE `FinalProduct_AUD` DISABLE KEYS */;
/*!40000 ALTER TABLE `FinalProduct_AUD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Form`
--

DROP TABLE IF EXISTS `Form`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Form` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `lastModified` datetime NOT NULL,
  `description` longtext,
  `name` varchar(255) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Form`
--

LOCK TABLES `Form` WRITE;
/*!40000 ALTER TABLE `Form` DISABLE KEYS */;
INSERT INTO `Form` VALUES (1,'2013-05-23 08:35:20','','Tuotekortti',1),(2,'2013-05-23 08:49:44','','Jäätelön puhtaustarkkailu',1),(3,'2013-05-23 09:00:53','','Jäätelönvalmistuslaitteiston ja -tilojen pesuohjelma',1);
/*!40000 ALTER TABLE `Form` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Form_AUD`
--

DROP TABLE IF EXISTS `Form_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Form_AUD` (
  `id` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `description` longtext,
  `name` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Form_AUD`
--

LOCK TABLES `Form_AUD` WRITE;
/*!40000 ALTER TABLE `Form_AUD` DISABLE KEYS */;
INSERT INTO `Form_AUD` VALUES (1,2,0,'','Tuotekortti',1),(1,16,1,'','Tuotekortti',1),(2,28,0,'','Jäätelön puhtaustarkkailu',1),(2,36,1,'','Jäätelön puhtaustarkkailu',1),(3,38,0,'','Jäätelönvalmistuslaitteiston ja -tilojen pesuohjelma',1),(3,42,1,'','Jäätelönvalmistuslaitteiston ja -tilojen pesuohjelma',1);
/*!40000 ALTER TABLE `Form_AUD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Form_Fieldset`
--

DROP TABLE IF EXISTS `Form_Fieldset`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Form_Fieldset` (
  `Form_id` bigint(20) NOT NULL,
  `fieldsets_id` bigint(20) NOT NULL,
  `form_index` int(11) NOT NULL,
  PRIMARY KEY (`Form_id`,`form_index`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Form_Fieldset`
--

LOCK TABLES `Form_Fieldset` WRITE;
/*!40000 ALTER TABLE `Form_Fieldset` DISABLE KEYS */;
INSERT INTO `Form_Fieldset` VALUES (1,1,0),(1,2,1),(1,3,2),(1,4,3),(1,5,4),(1,6,5),(1,7,6),(1,8,7),(1,9,8),(1,10,9),(1,11,10),(1,12,11),(1,13,12),(2,14,0),(2,15,1),(2,16,2),(2,17,3),(3,18,0);
/*!40000 ALTER TABLE `Form_Fieldset` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Form_Fieldset_AUD`
--

DROP TABLE IF EXISTS `Form_Fieldset_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Form_Fieldset_AUD` (
  `REV` int(11) NOT NULL,
  `Form_id` bigint(20) NOT NULL,
  `fieldsets_id` bigint(20) NOT NULL,
  `form_index` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`REV`,`Form_id`,`fieldsets_id`,`form_index`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Form_Fieldset_AUD`
--

LOCK TABLES `Form_Fieldset_AUD` WRITE;
/*!40000 ALTER TABLE `Form_Fieldset_AUD` DISABLE KEYS */;
INSERT INTO `Form_Fieldset_AUD` VALUES (16,1,1,0,0),(16,1,2,1,0),(16,1,3,2,0),(16,1,4,3,0),(16,1,5,4,0),(16,1,6,5,0),(16,1,7,6,0),(16,1,8,7,0),(16,1,9,8,0),(16,1,10,9,0),(16,1,11,10,0),(16,1,12,11,0),(16,1,13,12,0),(36,2,14,0,0),(36,2,15,1,0),(36,2,16,2,0),(36,2,17,3,0),(42,3,18,0,0);
/*!40000 ALTER TABLE `Form_Fieldset_AUD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Ingredient`
--

DROP TABLE IF EXISTS `Ingredient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Ingredient` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `lastModified` datetime NOT NULL,
  `bestBefore` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `form_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Ingredient`
--

LOCK TABLES `Ingredient` WRITE;
/*!40000 ALTER TABLE `Ingredient` DISABLE KEYS */;
INSERT INTO `Ingredient` VALUES (1,'2013-05-23 08:36:51',14,'','Kerma',1,NULL),(2,'2013-05-23 08:37:00',7,'','Maito',1,NULL);
/*!40000 ALTER TABLE `Ingredient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `IngredientSupply`
--

DROP TABLE IF EXISTS `IngredientSupply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `IngredientSupply` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `lastModified` datetime NOT NULL,
  `amount` double NOT NULL,
  `received` datetime NOT NULL,
  `used` double DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `ingredient_id` bigint(20) NOT NULL,
  `producer_id` bigint(20) NOT NULL,
  `unit_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `IngredientSupply`
--

LOCK TABLES `IngredientSupply` WRITE;
/*!40000 ALTER TABLE `IngredientSupply` DISABLE KEYS */;
/*!40000 ALTER TABLE `IngredientSupply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `IngredientSupply_AUD`
--

DROP TABLE IF EXISTS `IngredientSupply_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `IngredientSupply_AUD` (
  `id` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `received` datetime DEFAULT NULL,
  `used` double DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `ingredient_id` bigint(20) DEFAULT NULL,
  `producer_id` bigint(20) DEFAULT NULL,
  `unit_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `IngredientSupply_AUD`
--

LOCK TABLES `IngredientSupply_AUD` WRITE;
/*!40000 ALTER TABLE `IngredientSupply_AUD` DISABLE KEYS */;
/*!40000 ALTER TABLE `IngredientSupply_AUD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `IngredientSupply_Batch`
--

DROP TABLE IF EXISTS `IngredientSupply_Batch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `IngredientSupply_Batch` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` double NOT NULL,
  `batch_id` bigint(20) DEFAULT NULL,
  `ingredientSupply_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `IngredientSupply_Batch`
--

LOCK TABLES `IngredientSupply_Batch` WRITE;
/*!40000 ALTER TABLE `IngredientSupply_Batch` DISABLE KEYS */;
/*!40000 ALTER TABLE `IngredientSupply_Batch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `IngredientSupply_Batch_AUD`
--

DROP TABLE IF EXISTS `IngredientSupply_Batch_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `IngredientSupply_Batch_AUD` (
  `id` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `batch_id` bigint(20) DEFAULT NULL,
  `ingredientSupply_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `IngredientSupply_Batch_AUD`
--

LOCK TABLES `IngredientSupply_Batch_AUD` WRITE;
/*!40000 ALTER TABLE `IngredientSupply_Batch_AUD` DISABLE KEYS */;
/*!40000 ALTER TABLE `IngredientSupply_Batch_AUD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Ingredient_AUD`
--

DROP TABLE IF EXISTS `Ingredient_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Ingredient_AUD` (
  `id` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `bestBefore` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `form_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Ingredient_AUD`
--

LOCK TABLES `Ingredient_AUD` WRITE;
/*!40000 ALTER TABLE `Ingredient_AUD` DISABLE KEYS */;
INSERT INTO `Ingredient_AUD` VALUES (1,18,0,14,'','Kerma',1,NULL),(2,19,0,7,'','Maito',1,NULL);
/*!40000 ALTER TABLE `Ingredient_AUD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `News`
--

DROP TABLE IF EXISTS `News`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `News` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `lastModified` datetime NOT NULL,
  `name` varchar(255) NOT NULL,
  `story` longtext NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `News`
--

LOCK TABLES `News` WRITE;
/*!40000 ALTER TABLE `News` DISABLE KEYS */;
/*!40000 ALTER TABLE `News` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `News_AUD`
--

DROP TABLE IF EXISTS `News_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `News_AUD` (
  `id` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `story` longtext,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `News_AUD`
--

LOCK TABLES `News_AUD` WRITE;
/*!40000 ALTER TABLE `News_AUD` DISABLE KEYS */;
/*!40000 ALTER TABLE `News_AUD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Product`
--

DROP TABLE IF EXISTS `Product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `lastModified` datetime NOT NULL,
  `description` longtext,
  `name` varchar(255) NOT NULL,
  `no` int(11) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Product`
--

LOCK TABLES `Product` WRITE;
/*!40000 ALTER TABLE `Product` DISABLE KEYS */;
INSERT INTO `Product` VALUES (1,'2013-05-23 09:01:21','','Jäätelö',123,1);
/*!40000 ALTER TABLE `Product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Product_AUD`
--

DROP TABLE IF EXISTS `Product_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Product_AUD` (
  `id` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `description` longtext,
  `name` varchar(255) DEFAULT NULL,
  `no` int(11) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Product_AUD`
--

LOCK TABLES `Product_AUD` WRITE;
/*!40000 ALTER TABLE `Product_AUD` DISABLE KEYS */;
INSERT INTO `Product_AUD` VALUES (1,27,0,'','Jäätelö',123,1),(1,37,1,'','Jäätelö',123,1),(1,43,1,'','Jäätelö',123,1);
/*!40000 ALTER TABLE `Product_AUD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Product_Form`
--

DROP TABLE IF EXISTS `Product_Form`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Product_Form` (
  `Product_id` bigint(20) NOT NULL,
  `forms_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Product_Form`
--

LOCK TABLES `Product_Form` WRITE;
/*!40000 ALTER TABLE `Product_Form` DISABLE KEYS */;
INSERT INTO `Product_Form` VALUES (1,1),(1,2),(1,3);
/*!40000 ALTER TABLE `Product_Form` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Product_Form_AUD`
--

DROP TABLE IF EXISTS `Product_Form_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Product_Form_AUD` (
  `REV` int(11) NOT NULL,
  `Product_id` bigint(20) NOT NULL,
  `forms_id` bigint(20) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`REV`,`Product_id`,`forms_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Product_Form_AUD`
--

LOCK TABLES `Product_Form_AUD` WRITE;
/*!40000 ALTER TABLE `Product_Form_AUD` DISABLE KEYS */;
INSERT INTO `Product_Form_AUD` VALUES (27,1,1,0),(37,1,2,0),(43,1,3,0);
/*!40000 ALTER TABLE `Product_Form_AUD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Product_Ingredient`
--

DROP TABLE IF EXISTS `Product_Ingredient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Product_Ingredient` (
  `Product_id` bigint(20) NOT NULL,
  `ingredients_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Product_Ingredient`
--

LOCK TABLES `Product_Ingredient` WRITE;
/*!40000 ALTER TABLE `Product_Ingredient` DISABLE KEYS */;
INSERT INTO `Product_Ingredient` VALUES (1,1),(1,2);
/*!40000 ALTER TABLE `Product_Ingredient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Product_Ingredient_AUD`
--

DROP TABLE IF EXISTS `Product_Ingredient_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Product_Ingredient_AUD` (
  `REV` int(11) NOT NULL,
  `Product_id` bigint(20) NOT NULL,
  `ingredients_id` bigint(20) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`REV`,`Product_id`,`ingredients_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Product_Ingredient_AUD`
--

LOCK TABLES `Product_Ingredient_AUD` WRITE;
/*!40000 ALTER TABLE `Product_Ingredient_AUD` DISABLE KEYS */;
INSERT INTO `Product_Ingredient_AUD` VALUES (27,1,1,0),(27,1,2,0);
/*!40000 ALTER TABLE `Product_Ingredient_AUD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `REVINFO`
--

DROP TABLE IF EXISTS `REVINFO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `REVINFO` (
  `REV` int(11) NOT NULL AUTO_INCREMENT,
  `REVTSTMP` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`REV`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `REVINFO`
--

LOCK TABLES `REVINFO` WRITE;
/*!40000 ALTER TABLE `REVINFO` DISABLE KEYS */;
INSERT INTO `REVINFO` VALUES (1,1369285821981),(2,1369285847544),(3,1369285923674),(4,1369286033883),(5,1369286095462),(6,1369286262241),(7,1369286422506),(8,1369286487562),(9,1369286591974),(10,1369286741700),(11,1369286807806),(12,1369286853384),(13,1369286919407),(14,1369287230920),(15,1369287304390),(16,1369287320898),(17,1369287349280),(18,1369287411163),(19,1369287420344),(20,1369287442371),(21,1369287447668),(22,1369287458367),(23,1369287467608),(24,1369287483731),(25,1369287496693),(26,1369287508007),(27,1369287526222),(28,1369287671490),(29,1369287769495),(30,1369287918144),(31,1369288023009),(32,1369288098133),(33,1369288139520),(34,1369288152352),(35,1369288171968),(36,1369288184829),(37,1369288331351),(38,1369288417447),(39,1369288524377),(40,1369288568957),(41,1369288839060),(42,1369288853456),(43,1369288881629);
/*!40000 ALTER TABLE `REVINFO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Result`
--

DROP TABLE IF EXISTS `Result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Result` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `lastModified` datetime NOT NULL,
  `comment` longtext,
  `valueBoolean` tinyint(1) DEFAULT NULL,
  `valueDate` datetime DEFAULT NULL,
  `valueDouble` double DEFAULT NULL,
  `valueInt` int(11) DEFAULT NULL,
  `valueString` longtext,
  `user_id` bigint(20) DEFAULT NULL,
  `field_id` bigint(20) NOT NULL,
  `reason_id` bigint(20) DEFAULT NULL,
  `results_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Result`
--

LOCK TABLES `Result` WRITE;
/*!40000 ALTER TABLE `Result` DISABLE KEYS */;
/*!40000 ALTER TABLE `Result` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Result_AUD`
--

DROP TABLE IF EXISTS `Result_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Result_AUD` (
  `id` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `comment` longtext,
  `valueBoolean` tinyint(1) DEFAULT NULL,
  `valueDate` datetime DEFAULT NULL,
  `valueDouble` double DEFAULT NULL,
  `valueInt` int(11) DEFAULT NULL,
  `valueString` longtext,
  `user_id` bigint(20) DEFAULT NULL,
  `field_id` bigint(20) DEFAULT NULL,
  `reason_id` bigint(20) DEFAULT NULL,
  `results_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Result_AUD`
--

LOCK TABLES `Result_AUD` WRITE;
/*!40000 ALTER TABLE `Result_AUD` DISABLE KEYS */;
/*!40000 ALTER TABLE `Result_AUD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Results`
--

DROP TABLE IF EXISTS `Results`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Results` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime DEFAULT NULL,
  `form_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Results`
--

LOCK TABLES `Results` WRITE;
/*!40000 ALTER TABLE `Results` DISABLE KEYS */;
/*!40000 ALTER TABLE `Results` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Results_AUD`
--

DROP TABLE IF EXISTS `Results_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Results_AUD` (
  `id` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `form_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Results_AUD`
--

LOCK TABLES `Results_AUD` WRITE;
/*!40000 ALTER TABLE `Results_AUD` DISABLE KEYS */;
/*!40000 ALTER TABLE `Results_AUD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Results_Batch`
--

DROP TABLE IF EXISTS `Results_Batch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Results_Batch` (
  `results_id` bigint(20) NOT NULL,
  `batches_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Results_Batch`
--

LOCK TABLES `Results_Batch` WRITE;
/*!40000 ALTER TABLE `Results_Batch` DISABLE KEYS */;
/*!40000 ALTER TABLE `Results_Batch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Results_Batch_AUD`
--

DROP TABLE IF EXISTS `Results_Batch_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Results_Batch_AUD` (
  `REV` int(11) NOT NULL,
  `results_id` bigint(20) NOT NULL,
  `batches_id` bigint(20) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`REV`,`results_id`,`batches_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Results_Batch_AUD`
--

LOCK TABLES `Results_Batch_AUD` WRITE;
/*!40000 ALTER TABLE `Results_Batch_AUD` DISABLE KEYS */;
/*!40000 ALTER TABLE `Results_Batch_AUD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Results_Result_AUD`
--

DROP TABLE IF EXISTS `Results_Result_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Results_Result_AUD` (
  `REV` int(11) NOT NULL,
  `results_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`REV`,`results_id`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Results_Result_AUD`
--

LOCK TABLES `Results_Result_AUD` WRITE;
/*!40000 ALTER TABLE `Results_Result_AUD` DISABLE KEYS */;
/*!40000 ALTER TABLE `Results_Result_AUD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Term`
--

DROP TABLE IF EXISTS `Term`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Term` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `lastModified` datetime NOT NULL,
  `category` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Term`
--

LOCK TABLES `Term` WRITE;
/*!40000 ALTER TABLE `Term` DISABLE KEYS */;
INSERT INTO `Term` VALUES (1,'2013-05-23 08:37:22',3,'Hämeenlinnan osuusmeijeri',1),(2,'2013-05-23 08:37:27',1,'Kg',1),(3,'2013-05-23 08:37:38',1,'Litra',1),(4,'2013-05-23 08:37:47',2,'Myyntiin',1),(5,'2013-05-23 08:38:03',2,'Hylätty',1),(6,'2013-05-23 08:38:16',0,'Kirjoitusvirhe',1),(7,'2013-05-23 08:38:28',0,'Uusi mittaus',1);
/*!40000 ALTER TABLE `Term` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Term_AUD`
--

DROP TABLE IF EXISTS `Term_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Term_AUD` (
  `id` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `category` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Term_AUD`
--

LOCK TABLES `Term_AUD` WRITE;
/*!40000 ALTER TABLE `Term_AUD` DISABLE KEYS */;
INSERT INTO `Term_AUD` VALUES (1,20,0,3,'Hämeenlinnan osuusmeijeri',1),(2,21,0,1,'Kg',1),(3,22,0,1,'Litra',1),(4,23,0,2,'Myyntiin',1),(5,24,0,2,'Hylätty',1),(6,25,0,0,'Kirjoitusvirhe',1),(7,26,0,0,'Uusi mittaus',1);
/*!40000 ALTER TABLE `Term_AUD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `lastModified` datetime NOT NULL,
  `email` varchar(255) NOT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (1,'2013-05-23 08:40:56','myself@testshib.org','Me Myself','And I','Member');
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User_AUD`
--

DROP TABLE IF EXISTS `User_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User_AUD` (
  `id` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User_AUD`
--

LOCK TABLES `User_AUD` WRITE;
/*!40000 ALTER TABLE `User_AUD` DISABLE KEYS */;
INSERT INTO `User_AUD` VALUES (1,1,0,'myself@testshib.org','Me Myself','And I','Member');
/*!40000 ALTER TABLE `User_AUD` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-05-27  5:40:12
