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
  PRIMARY KEY (`id`),
  KEY `FK_9a72c1cbb53747d595db4aa3786` (`user_id`),
  KEY `FK_1ca40f01065c427b83a90359ebb` (`product_id`),
  CONSTRAINT `FK_1ca40f01065c427b83a90359ebb` FOREIGN KEY (`product_id`) REFERENCES `Product` (`id`),
  CONSTRAINT `FK_9a72c1cbb53747d595db4aa3786` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
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
  `lastModified` datetime DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FK_c627fb8edc7741b5beec26e43b8` (`REV`),
  CONSTRAINT `FK_c627fb8edc7741b5beec26e43b8` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
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
  `help` longtext COLLATE utf8_unicode_ci,
  `isImportant` tinyint(1) NOT NULL,
  `isRequired` tinyint(1) NOT NULL,
  `max` double DEFAULT NULL,
  `min` double DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `targetValue` tinyint(1) DEFAULT NULL,
  `fieldset_id` bigint(20) NOT NULL,
  `field_index` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_62a49e7ef29448fa8397dcdc991` (`fieldset_id`),
  CONSTRAINT `FK_62a49e7ef29448fa8397dcdc991` FOREIGN KEY (`fieldset_id`) REFERENCES `Fieldset` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Field`
--

LOCK TABLES `Field` WRITE;
/*!40000 ALTER TABLE `Field` DISABLE KEYS */;
INSERT INTO `Field` VALUES (1,'2013-06-01 11:04:08',4,'Pinnoilla ei silminnähtäviä jäämiä',0,1,NULL,NULL,'Visuaalinen puhtaustarkastus',1,1,0),(2,'2013-06-01 11:04:08',2,'RLU > 250',0,1,NULL,250,'Puhtaustason määritys pikamenetelmällä',NULL,1,1),(3,'2013-06-01 11:05:26',2,'%',0,1,NULL,NULL,'Rasvapitoisuus',NULL,2,0),(4,'2013-06-01 11:05:26',2,'%',0,1,NULL,NULL,'Kuiva-ainepitoisuus',NULL,2,1),(5,'2013-06-01 11:05:26',2,'%',0,1,NULL,NULL,'Kaseiini',NULL,2,2),(6,'2013-06-01 11:31:58',0,'°C/min',0,0,NULL,NULL,'Lämpötila/aika',NULL,3,0),(7,'2013-06-01 11:31:58',4,'Negatiivinen',0,0,NULL,NULL,'Peroksidaasikoe',1,3,1),(8,'2013-06-01 11:31:58',4,'Negatiivinen',0,0,NULL,NULL,'Fosfataasikoe',1,3,2),(9,'2013-06-01 11:31:58',2,'< 1 pmy/ml',0,0,1,NULL,'Enterobakteerit',NULL,3,3),(10,'2013-06-01 11:31:58',2,'%',0,0,NULL,NULL,'Rasvapitoisuus',NULL,3,4),(11,'2013-06-01 11:29:34',5,'Hapateen spesifikaatiosta',0,1,NULL,NULL,'Hapatteen nimi',NULL,4,0),(12,'2013-06-01 11:29:34',2,'% Hapateen spesifikaatiosta',0,1,NULL,NULL,'Siirrostus-%',NULL,4,1),(13,'2013-06-01 11:29:34',2,'g Hapateen spesifikaatiosta',0,1,NULL,NULL,'Siirrostus-%',NULL,4,2),(14,'2013-06-01 11:29:34',3,'Hapatekortista',0,1,NULL,NULL,'Hapatteen valm.pv.',NULL,4,3),(15,'2013-06-01 11:31:21',2,'< 1 pmy/g',0,1,1,NULL,'Enterobakteerit',NULL,5,0),(16,'2013-06-01 11:31:21',2,'< 1 pmy/g',0,1,1,NULL,'Hiivat ja homeet',NULL,5,1),(17,'2013-06-01 11:34:43',1,'',0,1,5,3,'ulkonäkö',NULL,6,0),(18,'2013-06-01 11:34:43',1,'',0,1,5,3,'rakenne',NULL,6,1),(19,'2013-06-01 11:34:43',1,'',0,1,5,3,'maku + haju',NULL,6,2),(20,'2013-06-01 11:37:29',2,'Tuotekohtainen',0,1,NULL,NULL,'pH',NULL,7,0),(21,'2013-06-01 11:37:29',2,'Tuotekohtainen',0,1,NULL,NULL,'°SH',NULL,7,1),(22,'2013-06-01 11:37:29',2,'≤ 1 pmy/g',0,1,1,NULL,'enterobakteerit',NULL,7,2),(23,'2013-06-01 11:37:29',2,'≤ 100 pmy /g',0,1,100,NULL,'hiivat ja homeet',NULL,7,3),(24,'2013-06-01 11:39:17',5,'',0,0,NULL,NULL,'Hillo',NULL,8,0),(25,'2013-06-01 11:39:17',2,'%',0,1,NULL,NULL,'Punnitus/ ml',NULL,8,1),(26,'2013-06-01 11:39:17',2,'g',0,1,NULL,NULL,'Punnitus/ ml',NULL,8,2),(27,'2013-06-01 11:41:33',2,'°C',0,1,-35,NULL,'lämpötilamittaus',NULL,9,0),(28,'2013-06-01 11:42:20',2,'°C',0,1,NULL,NULL,'lämpötilamittaus',NULL,10,0),(29,'2013-06-01 11:47:01',2,'pmy/g',0,1,NULL,NULL,'Entero-bakteerit',NULL,11,0),(30,'2013-06-01 11:47:01',2,'pmy/g',0,1,NULL,NULL,'Hiivat ja homeet',NULL,11,1),(31,'2013-06-01 11:47:01',2,'pmy/g',0,1,NULL,NULL,'Staphylococcus aureus',NULL,11,2),(32,'2013-06-01 11:47:01',2,'Tutkimus suoritetaan\r\nakkreditoidussa laboratoriossa kerran lukukaudessa',0,0,NULL,NULL,'Listeria monoc.',NULL,11,3),(33,'2013-06-01 11:47:01',2,'Tutkimus suoritetaan\r\nakkreditoidussa laboratoriossa kerran lukukaudessa',0,0,NULL,NULL,'Koagulaasi positiiviset stafylokokit',NULL,11,4),(34,'2013-06-01 11:47:01',2,'Tutkimus suoritetaan\r\nakkreditoidussa laboratoriossa kerran lukukaudessa',0,0,NULL,NULL,'E.coli',NULL,11,5),(35,'2013-06-01 11:50:50',1,'',0,1,5,3,'ulkonäkö',NULL,12,0),(36,'2013-06-01 11:50:50',1,'',0,1,5,3,'rakenne',NULL,12,1),(37,'2013-06-01 11:50:50',1,'',0,1,5,3,'maku ja haju',NULL,12,2);
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
  `lastModified` datetime DEFAULT NULL,
  `fieldType` int(11) DEFAULT NULL,
  `help` longtext COLLATE utf8_unicode_ci,
  `isImportant` tinyint(1) DEFAULT NULL,
  `isRequired` tinyint(1) DEFAULT NULL,
  `max` double DEFAULT NULL,
  `min` double DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `targetValue` tinyint(1) DEFAULT NULL,
  `fieldset_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FK_ca219a412809468c87f30f817d2` (`REV`),
  CONSTRAINT `FK_ca219a412809468c87f30f817d2` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Field_AUD`
--

LOCK TABLES `Field_AUD` WRITE;
/*!40000 ALTER TABLE `Field_AUD` DISABLE KEYS */;
INSERT INTO `Field_AUD` VALUES (1,14,0,'2013-06-01 11:04:08',4,'Pinnoilla ei silminnähtäviä jäämiä',0,1,NULL,NULL,'Visuaalinen puhtaustarkastus',1,1),(2,14,0,'2013-06-01 11:04:08',2,'RLU > 250',0,1,NULL,250,'Puhtaustason määritys pikamenetelmällä',NULL,1),(3,15,0,'2013-06-01 11:05:26',2,'%',0,1,NULL,NULL,'Rasvapitoisuus',NULL,2),(4,15,0,'2013-06-01 11:05:26',2,'%',0,1,NULL,NULL,'Kuiva-ainepitoisuus',NULL,2),(5,15,0,'2013-06-01 11:05:26',2,'%',0,1,NULL,NULL,'Kaseiini',NULL,2),(6,17,0,'2013-06-01 11:24:38',0,'°C/min',0,0,NULL,NULL,'Lämpötila/aika',NULL,3),(6,20,1,'2013-06-01 11:31:58',0,'°C/min',0,0,NULL,NULL,'Lämpötila/aika',NULL,3),(7,17,0,'2013-06-01 11:24:38',4,'Negatiivinen',0,0,NULL,NULL,'Peroksidaasikoe',1,3),(7,20,1,'2013-06-01 11:31:58',4,'Negatiivinen',0,0,NULL,NULL,'Peroksidaasikoe',1,3),(8,17,0,'2013-06-01 11:24:38',4,'Negatiivinen',0,0,NULL,NULL,'Fosfataasikoe',1,3),(8,20,1,'2013-06-01 11:31:58',4,'Negatiivinen',0,0,NULL,NULL,'Fosfataasikoe',1,3),(9,17,0,'2013-06-01 11:24:38',2,'< 1 pmy/ml',0,0,NULL,NULL,'Enterobakteerit',NULL,3),(9,20,1,'2013-06-01 11:31:58',2,'< 1 pmy/ml',0,0,1,NULL,'Enterobakteerit',NULL,3),(10,17,0,'2013-06-01 11:24:38',2,'%',0,0,NULL,NULL,'Rasvapitoisuus',NULL,3),(10,20,1,'2013-06-01 11:31:58',2,'%',0,0,NULL,NULL,'Rasvapitoisuus',NULL,3),(11,18,0,'2013-06-01 11:29:34',5,'Hapateen spesifikaatiosta',0,1,NULL,NULL,'Hapatteen nimi',NULL,4),(12,18,0,'2013-06-01 11:29:34',2,'% Hapateen spesifikaatiosta',0,1,NULL,NULL,'Siirrostus-%',NULL,4),(13,18,0,'2013-06-01 11:29:34',2,'g Hapateen spesifikaatiosta',0,1,NULL,NULL,'Siirrostus-%',NULL,4),(14,18,0,'2013-06-01 11:29:34',3,'Hapatekortista',0,1,NULL,NULL,'Hapatteen valm.pv.',NULL,4),(15,19,0,'2013-06-01 11:31:21',2,'< 1 pmy/g',0,1,1,NULL,'Enterobakteerit',NULL,5),(16,19,0,'2013-06-01 11:31:21',2,'< 1 pmy/g',0,1,1,NULL,'Hiivat ja homeet',NULL,5),(17,21,0,'2013-06-01 11:34:43',1,'',0,1,5,3,'ulkonäkö',NULL,6),(18,21,0,'2013-06-01 11:34:43',1,'',0,1,5,3,'rakenne',NULL,6),(19,21,0,'2013-06-01 11:34:43',1,'',0,1,5,3,'maku + haju',NULL,6),(20,22,0,'2013-06-01 11:37:29',2,'Tuotekohtainen',0,1,NULL,NULL,'pH',NULL,7),(21,22,0,'2013-06-01 11:37:29',2,'Tuotekohtainen',0,1,NULL,NULL,'°SH',NULL,7),(22,22,0,'2013-06-01 11:37:29',2,'≤ 1 pmy/g',0,1,1,NULL,'enterobakteerit',NULL,7),(23,22,0,'2013-06-01 11:37:29',2,'≤ 100 pmy /g',0,1,100,NULL,'hiivat ja homeet',NULL,7),(24,23,0,'2013-06-01 11:39:17',5,'',0,0,NULL,NULL,'Hillo',NULL,8),(25,23,0,'2013-06-01 11:39:17',2,'%',0,1,NULL,NULL,'Punnitus/ ml',NULL,8),(26,23,0,'2013-06-01 11:39:17',2,'g',0,1,NULL,NULL,'Punnitus/ ml',NULL,8),(27,24,0,'2013-06-01 11:41:33',2,'°C',0,1,-35,NULL,'lämpötilamittaus',NULL,9),(28,25,0,'2013-06-01 11:42:20',2,'°C',0,1,NULL,NULL,'lämpötilamittaus',NULL,10),(29,26,0,'2013-06-01 11:47:01',2,'pmy/g',0,1,NULL,NULL,'Entero-bakteerit',NULL,11),(30,26,0,'2013-06-01 11:47:01',2,'pmy/g',0,1,NULL,NULL,'Hiivat ja homeet',NULL,11),(31,26,0,'2013-06-01 11:47:01',2,'pmy/g',0,1,NULL,NULL,'Staphylococcus aureus',NULL,11),(32,26,0,'2013-06-01 11:47:01',2,'Tutkimus suoritetaan\r\nakkreditoidussa laboratoriossa kerran lukukaudessa',0,0,NULL,NULL,'Listeria monoc.',NULL,11),(33,26,0,'2013-06-01 11:47:01',2,'Tutkimus suoritetaan\r\nakkreditoidussa laboratoriossa kerran lukukaudessa',0,0,NULL,NULL,'Koagulaasi positiiviset stafylokokit',NULL,11),(34,26,0,'2013-06-01 11:47:01',2,'Tutkimus suoritetaan\r\nakkreditoidussa laboratoriossa kerran lukukaudessa',0,0,NULL,NULL,'E.coli',NULL,11),(35,28,0,'2013-06-01 11:50:50',1,'',0,1,5,3,'ulkonäkö',NULL,12),(36,28,0,'2013-06-01 11:50:50',1,'',0,1,5,3,'rakenne',NULL,12),(37,28,0,'2013-06-01 11:50:50',1,'',0,1,5,3,'maku ja haju',NULL,12);
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
  `description` longtext COLLATE utf8_unicode_ci,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_b600ee9ffc454d16b983f2c722e` (`user_id`),
  CONSTRAINT `FK_b600ee9ffc454d16b983f2c722e` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Fieldset`
--

LOCK TABLES `Fieldset` WRITE;
/*!40000 ALTER TABLE `Fieldset` DISABLE KEYS */;
INSERT INTO `Fieldset` VALUES (1,'2013-06-01 11:04:08','','Ennen töiden aloittamista / laitteisto',1),(2,'2013-06-01 11:05:26','','Maidon vakiointi',1),(3,'2013-06-01 11:31:58','','Maidon pastörointi',1),(4,'2013-06-01 11:29:34','','Hapatelisäys',1),(5,'2013-06-01 11:31:21','','Lisäyksen jälkeen/ siirron jälkeen juustokattilasta',1),(6,'2013-06-01 11:34:43','Pisteet 1-5','Hapatettu tuote (ennen maustamista/ kirnuamista) aistinvarainen arviointi',1),(7,'2013-06-01 11:37:29','','Hapatettu tuote (ennen maustamista/ kirnuamista)',1),(8,'2013-06-01 11:39:17','','Hillolisäys',1),(9,'2013-06-01 11:41:33','','Karkaisu',1),(10,'2013-06-01 11:42:20','','Varastointi',1),(11,'2013-06-01 11:47:01','','Valmis tuote',1),(12,'2013-06-01 11:50:50','Pistein 1-5','Aistinvarainen arviointi',1);
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
  `lastModified` datetime DEFAULT NULL,
  `description` longtext COLLATE utf8_unicode_ci,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FK_7561584c18b84516b222213fc87` (`REV`),
  CONSTRAINT `FK_7561584c18b84516b222213fc87` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Fieldset_AUD`
--

LOCK TABLES `Fieldset_AUD` WRITE;
/*!40000 ALTER TABLE `Fieldset_AUD` DISABLE KEYS */;
INSERT INTO `Fieldset_AUD` VALUES (1,14,0,'2013-06-01 11:04:08','','Ennen töiden aloittamista / laitteisto',1),(2,15,0,'2013-06-01 11:05:26','','Maidon vakiointi',1),(3,17,0,'2013-06-01 11:24:38','','Maidon pastörointi',1),(3,20,1,'2013-06-01 11:31:58','','Maidon pastörointi',1),(4,18,0,'2013-06-01 11:29:34','','Hapatelisäys',1),(5,19,0,'2013-06-01 11:31:21','','Lisäyksen jälkeen/ siirron jälkeen juustokattilasta',1),(6,21,0,'2013-06-01 11:34:43','Pisteet 1-5','Hapatettu tuote (ennen maustamista/ kirnuamista) aistinvarainen arviointi',1),(7,22,0,'2013-06-01 11:37:29','','Hapatettu tuote (ennen maustamista/ kirnuamista)',1),(8,23,0,'2013-06-01 11:39:17','','Hillolisäys',1),(9,24,0,'2013-06-01 11:41:33','','Karkaisu',1),(10,25,0,'2013-06-01 11:42:20','','Varastointi',1),(11,26,0,'2013-06-01 11:47:01','','Valmis tuote',1),(12,28,0,'2013-06-01 11:50:50','Pistein 1-5','Aistinvarainen arviointi',1);
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
  `comment` longtext COLLATE utf8_unicode_ci,
  `date` datetime DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `batch_id` bigint(20) DEFAULT NULL,
  `destiny_id` bigint(20) DEFAULT NULL,
  `unit_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_2ccf61c827f84fb3b59cfb30b03` (`user_id`),
  KEY `FK_c39073b0638f4a2083ffabfc725` (`batch_id`),
  KEY `FK_3aea1615510a4ec8952d3e888b0` (`destiny_id`),
  KEY `FK_0152b659387c4c6f9954c2f164e` (`unit_id`),
  CONSTRAINT `FK_0152b659387c4c6f9954c2f164e` FOREIGN KEY (`unit_id`) REFERENCES `Term` (`id`),
  CONSTRAINT `FK_2ccf61c827f84fb3b59cfb30b03` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`),
  CONSTRAINT `FK_3aea1615510a4ec8952d3e888b0` FOREIGN KEY (`destiny_id`) REFERENCES `Term` (`id`),
  CONSTRAINT `FK_c39073b0638f4a2083ffabfc725` FOREIGN KEY (`batch_id`) REFERENCES `Batch` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
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
  `lastModified` datetime DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `comment` longtext COLLATE utf8_unicode_ci,
  `date` datetime DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `batch_id` bigint(20) DEFAULT NULL,
  `destiny_id` bigint(20) DEFAULT NULL,
  `unit_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FK_beaecec40f0a40dabea4521f4f5` (`REV`),
  CONSTRAINT `FK_beaecec40f0a40dabea4521f4f5` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
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
  `description` longtext COLLATE utf8_unicode_ci,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_cf605ac78ab74f779df932f50fc` (`user_id`),
  CONSTRAINT `FK_cf605ac78ab74f779df932f50fc` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Form`
--

LOCK TABLES `Form` WRITE;
/*!40000 ALTER TABLE `Form` DISABLE KEYS */;
INSERT INTO `Form` VALUES (1,'2013-06-01 11:51:26','','Tuotekortti',1);
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
  `lastModified` datetime DEFAULT NULL,
  `description` longtext COLLATE utf8_unicode_ci,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FK_39ccd90f1d4044efad270843db8` (`REV`),
  CONSTRAINT `FK_39ccd90f1d4044efad270843db8` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Form_AUD`
--

LOCK TABLES `Form_AUD` WRITE;
/*!40000 ALTER TABLE `Form_AUD` DISABLE KEYS */;
INSERT INTO `Form_AUD` VALUES (1,13,0,'2013-06-01 11:02:14','','Tuotekortti',1),(1,29,1,'2013-06-01 11:51:26','','Tuotekortti',1);
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
  PRIMARY KEY (`Form_id`,`form_index`),
  KEY `FK_6eda66b6987143b0bd384bcd87f` (`fieldsets_id`),
  KEY `FK_c7bde84a222a416888a8043e6ac` (`Form_id`),
  CONSTRAINT `FK_c7bde84a222a416888a8043e6ac` FOREIGN KEY (`Form_id`) REFERENCES `Form` (`id`),
  CONSTRAINT `FK_6eda66b6987143b0bd384bcd87f` FOREIGN KEY (`fieldsets_id`) REFERENCES `Fieldset` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Form_Fieldset`
--

LOCK TABLES `Form_Fieldset` WRITE;
/*!40000 ALTER TABLE `Form_Fieldset` DISABLE KEYS */;
INSERT INTO `Form_Fieldset` VALUES (1,1,0),(1,2,1),(1,3,2),(1,4,3),(1,5,4),(1,6,5),(1,7,6),(1,8,7),(1,9,8),(1,10,9),(1,11,10),(1,12,11);
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
  PRIMARY KEY (`REV`,`Form_id`,`fieldsets_id`,`form_index`),
  KEY `FK_9ab53d465cec4536b0fdd897c26` (`REV`),
  CONSTRAINT `FK_9ab53d465cec4536b0fdd897c26` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Form_Fieldset_AUD`
--

LOCK TABLES `Form_Fieldset_AUD` WRITE;
/*!40000 ALTER TABLE `Form_Fieldset_AUD` DISABLE KEYS */;
INSERT INTO `Form_Fieldset_AUD` VALUES (29,1,1,0,0),(29,1,2,1,0),(29,1,3,2,0),(29,1,4,3,0),(29,1,5,4,0),(29,1,6,5,0),(29,1,7,6,0),(29,1,8,7,0),(29,1,9,8,0),(29,1,10,9,0),(29,1,11,10,0),(29,1,12,11,0);
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
  `description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `form_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_d8a1cfddab5b4a7aa47433aee10` (`name`),
  KEY `FK_54abe3470d07405fa4facc56977` (`user_id`),
  KEY `FK_2d07ab2825e64282b1c28eaedd2` (`form_id`),
  CONSTRAINT `FK_2d07ab2825e64282b1c28eaedd2` FOREIGN KEY (`form_id`) REFERENCES `Form` (`id`),
  CONSTRAINT `FK_54abe3470d07405fa4facc56977` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Ingredient`
--

LOCK TABLES `Ingredient` WRITE;
/*!40000 ALTER TABLE `Ingredient` DISABLE KEYS */;
INSERT INTO `Ingredient` VALUES (1,'2013-06-01 11:01:43',14,'','Kerma',1,NULL),(2,'2013-06-01 11:01:50',14,'','Maito',1,NULL);
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
  PRIMARY KEY (`id`),
  KEY `FK_9bfd6e1496a542e682c64933775` (`user_id`),
  KEY `FK_55e0a220a07646c1bb57da65192` (`ingredient_id`),
  KEY `FK_edf7d8cbaa7c4182997f8f03914` (`producer_id`),
  KEY `FK_0d89353623e24017931d35eab3d` (`unit_id`),
  CONSTRAINT `FK_0d89353623e24017931d35eab3d` FOREIGN KEY (`unit_id`) REFERENCES `Term` (`id`),
  CONSTRAINT `FK_55e0a220a07646c1bb57da65192` FOREIGN KEY (`ingredient_id`) REFERENCES `Ingredient` (`id`),
  CONSTRAINT `FK_9bfd6e1496a542e682c64933775` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`),
  CONSTRAINT `FK_edf7d8cbaa7c4182997f8f03914` FOREIGN KEY (`producer_id`) REFERENCES `Term` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
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
  `lastModified` datetime DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `received` datetime DEFAULT NULL,
  `used` double DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `ingredient_id` bigint(20) DEFAULT NULL,
  `producer_id` bigint(20) DEFAULT NULL,
  `unit_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FK_e8bfc7aaf34b4b86ad4f2b6b6cd` (`REV`),
  CONSTRAINT `FK_e8bfc7aaf34b4b86ad4f2b6b6cd` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
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
  PRIMARY KEY (`id`),
  KEY `FK_3d9acad5d4b94e8b9d719e487c9` (`batch_id`),
  KEY `FK_481cd73ff3b74150919fec5be84` (`ingredientSupply_id`),
  CONSTRAINT `FK_481cd73ff3b74150919fec5be84` FOREIGN KEY (`ingredientSupply_id`) REFERENCES `IngredientSupply` (`id`),
  CONSTRAINT `FK_3d9acad5d4b94e8b9d719e487c9` FOREIGN KEY (`batch_id`) REFERENCES `Batch` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
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
  PRIMARY KEY (`id`,`REV`),
  KEY `FK_a1b5bf434d4b48aea06553edf2c` (`REV`),
  CONSTRAINT `FK_a1b5bf434d4b48aea06553edf2c` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
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
  `lastModified` datetime DEFAULT NULL,
  `bestBefore` int(11) DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `form_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FK_527bce2a85d4487d84e488dc49e` (`REV`),
  CONSTRAINT `FK_527bce2a85d4487d84e488dc49e` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Ingredient_AUD`
--

LOCK TABLES `Ingredient_AUD` WRITE;
/*!40000 ALTER TABLE `Ingredient_AUD` DISABLE KEYS */;
INSERT INTO `Ingredient_AUD` VALUES (1,10,0,'2013-06-01 11:01:43',14,'','Kerma',1,NULL),(2,11,0,'2013-06-01 11:01:50',14,'','Maito',1,NULL);
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
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `story` longtext COLLATE utf8_unicode_ci NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_73c7bac7cda3498f92bdaba75f5` (`user_id`),
  CONSTRAINT `FK_73c7bac7cda3498f92bdaba75f5` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
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
  `lastModified` datetime DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `story` longtext COLLATE utf8_unicode_ci,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FK_9bbace42f85b467192d1115be1f` (`REV`),
  CONSTRAINT `FK_9bbace42f85b467192d1115be1f` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
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
  `description` longtext COLLATE utf8_unicode_ci,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `no` int(11) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_e55e05f29cf548379faf29bd650` (`no`),
  KEY `FK_d3bad7d97e7b43b788d847684b8` (`user_id`),
  CONSTRAINT `FK_d3bad7d97e7b43b788d847684b8` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Product`
--

LOCK TABLES `Product` WRITE;
/*!40000 ALTER TABLE `Product` DISABLE KEYS */;
INSERT INTO `Product` VALUES (1,'2013-06-01 11:51:45','','Jäätelö',123,1);
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
  `lastModified` datetime DEFAULT NULL,
  `description` longtext COLLATE utf8_unicode_ci,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `no` int(11) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FK_15e111eac6c54657b18f0af20f2` (`REV`),
  CONSTRAINT `FK_15e111eac6c54657b18f0af20f2` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Product_AUD`
--

LOCK TABLES `Product_AUD` WRITE;
/*!40000 ALTER TABLE `Product_AUD` DISABLE KEYS */;
INSERT INTO `Product_AUD` VALUES (1,12,0,'2013-06-01 11:02:05','','Jäätelö',123,1),(1,30,1,'2013-06-01 11:51:45','','Jäätelö',123,1);
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
  `forms_id` bigint(20) NOT NULL,
  KEY `FK_33cc25f5fd814c9f9f528e2fa58` (`forms_id`),
  KEY `FK_7d3c7407911d4b2181345d4d30e` (`Product_id`),
  CONSTRAINT `FK_7d3c7407911d4b2181345d4d30e` FOREIGN KEY (`Product_id`) REFERENCES `Product` (`id`),
  CONSTRAINT `FK_33cc25f5fd814c9f9f528e2fa58` FOREIGN KEY (`forms_id`) REFERENCES `Form` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Product_Form`
--

LOCK TABLES `Product_Form` WRITE;
/*!40000 ALTER TABLE `Product_Form` DISABLE KEYS */;
INSERT INTO `Product_Form` VALUES (1,1);
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
  PRIMARY KEY (`REV`,`Product_id`,`forms_id`),
  KEY `FK_86a1d36d74db42b19a3920b12e6` (`REV`),
  CONSTRAINT `FK_86a1d36d74db42b19a3920b12e6` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Product_Form_AUD`
--

LOCK TABLES `Product_Form_AUD` WRITE;
/*!40000 ALTER TABLE `Product_Form_AUD` DISABLE KEYS */;
INSERT INTO `Product_Form_AUD` VALUES (30,1,1,0);
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
  `ingredients_id` bigint(20) NOT NULL,
  KEY `FK_b6521259634f44eba898360895d` (`ingredients_id`),
  KEY `FK_319e5f51951c4ffcbef5b97d4a5` (`Product_id`),
  CONSTRAINT `FK_319e5f51951c4ffcbef5b97d4a5` FOREIGN KEY (`Product_id`) REFERENCES `Product` (`id`),
  CONSTRAINT `FK_b6521259634f44eba898360895d` FOREIGN KEY (`ingredients_id`) REFERENCES `Ingredient` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
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
  PRIMARY KEY (`REV`,`Product_id`,`ingredients_id`),
  KEY `FK_475b0af4c75f4e88840b5f0711b` (`REV`),
  CONSTRAINT `FK_475b0af4c75f4e88840b5f0711b` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Product_Ingredient_AUD`
--

LOCK TABLES `Product_Ingredient_AUD` WRITE;
/*!40000 ALTER TABLE `Product_Ingredient_AUD` DISABLE KEYS */;
INSERT INTO `Product_Ingredient_AUD` VALUES (12,1,1,0),(12,1,2,0);
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
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `REVINFO`
--

LOCK TABLES `REVINFO` WRITE;
/*!40000 ALTER TABLE `REVINFO` DISABLE KEYS */;
INSERT INTO `REVINFO` VALUES (1,1370073536042),(2,1370073611682),(3,1370073624521),(4,1370073640801),(5,1370073648130),(6,1370073660015),(7,1370073671830),(8,1370073683155),(9,1370073693384),(10,1370073703894),(11,1370073710772),(12,1370073725669),(13,1370073734409),(14,1370073848325),(15,1370073926131),(16,1370074717555),(17,1370075078938),(18,1370075374890),(19,1370075481065),(20,1370075518267),(21,1370075683196),(22,1370075849489),(23,1370075957412),(24,1370076093381),(25,1370076140377),(26,1370076421929),(27,1370076553149),(28,1370076650357),(29,1370076686097),(30,1370076705531);
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
  `comment` longtext COLLATE utf8_unicode_ci,
  `valueBoolean` tinyint(1) DEFAULT NULL,
  `valueDate` datetime DEFAULT NULL,
  `valueDouble` double DEFAULT NULL,
  `valueInt` int(11) DEFAULT NULL,
  `valueString` longtext COLLATE utf8_unicode_ci,
  `user_id` bigint(20) DEFAULT NULL,
  `field_id` bigint(20) NOT NULL,
  `reason_id` bigint(20) DEFAULT NULL,
  `results_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_e568ea6713714e26b775e30dce4` (`user_id`),
  KEY `FK_4e3c8c85dfdd430bb99ac48d346` (`field_id`),
  KEY `FK_fa0f61d8bd2e4b8f97f94d8449a` (`reason_id`),
  KEY `FK_452fd785ff5b4a0a8c736083a27` (`results_id`),
  CONSTRAINT `FK_452fd785ff5b4a0a8c736083a27` FOREIGN KEY (`results_id`) REFERENCES `Results` (`id`),
  CONSTRAINT `FK_4e3c8c85dfdd430bb99ac48d346` FOREIGN KEY (`field_id`) REFERENCES `Field` (`id`),
  CONSTRAINT `FK_e568ea6713714e26b775e30dce4` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`),
  CONSTRAINT `FK_fa0f61d8bd2e4b8f97f94d8449a` FOREIGN KEY (`reason_id`) REFERENCES `Term` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
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
  `lastModified` datetime DEFAULT NULL,
  `comment` longtext COLLATE utf8_unicode_ci,
  `valueBoolean` tinyint(1) DEFAULT NULL,
  `valueDate` datetime DEFAULT NULL,
  `valueDouble` double DEFAULT NULL,
  `valueInt` int(11) DEFAULT NULL,
  `valueString` longtext COLLATE utf8_unicode_ci,
  `user_id` bigint(20) DEFAULT NULL,
  `field_id` bigint(20) DEFAULT NULL,
  `reason_id` bigint(20) DEFAULT NULL,
  `results_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FK_2cd4582e5368462585d664eb11f` (`REV`),
  CONSTRAINT `FK_2cd4582e5368462585d664eb11f` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
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
  PRIMARY KEY (`id`),
  KEY `FK_08b281c66c6d4540a15a3bc00c0` (`form_id`),
  CONSTRAINT `FK_08b281c66c6d4540a15a3bc00c0` FOREIGN KEY (`form_id`) REFERENCES `Form` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
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
  `created` datetime DEFAULT NULL,
  `form_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FK_4d7b20ca51d747158023bce1383` (`REV`),
  CONSTRAINT `FK_4d7b20ca51d747158023bce1383` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
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
  `batches_id` bigint(20) NOT NULL,
  KEY `FK_d800acc399314c16bf2e895000e` (`batches_id`),
  KEY `FK_6f13b3d81ba74919b081e3afabb` (`results_id`),
  CONSTRAINT `FK_6f13b3d81ba74919b081e3afabb` FOREIGN KEY (`results_id`) REFERENCES `Results` (`id`),
  CONSTRAINT `FK_d800acc399314c16bf2e895000e` FOREIGN KEY (`batches_id`) REFERENCES `Batch` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
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
  PRIMARY KEY (`REV`,`results_id`,`batches_id`),
  KEY `FK_f3a488121be543ca996ad44db12` (`REV`),
  CONSTRAINT `FK_f3a488121be543ca996ad44db12` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Results_Batch_AUD`
--

LOCK TABLES `Results_Batch_AUD` WRITE;
/*!40000 ALTER TABLE `Results_Batch_AUD` DISABLE KEYS */;
/*!40000 ALTER TABLE `Results_Batch_AUD` ENABLE KEYS */;
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
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_886cafa2a7ec422f837f1fdf7e2` (`user_id`),
  CONSTRAINT `FK_886cafa2a7ec422f837f1fdf7e2` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Term`
--

LOCK TABLES `Term` WRITE;
/*!40000 ALTER TABLE `Term` DISABLE KEYS */;
INSERT INTO `Term` VALUES (1,'2013-06-01 11:00:24',3,'Hämeenlinnan osuusmeijeri',1),(2,'2013-06-01 11:00:40',1,'Kg',1),(3,'2013-06-01 11:00:48',1,'Litra',1),(4,'2013-06-01 11:01:00',2,'Myyntiin',1),(5,'2013-06-01 11:01:11',2,'Hylätty',1),(6,'2013-06-01 11:01:23',0,'Kirjoitusvirhe',1),(7,'2013-06-01 11:01:33',0,'Uusi mittaus',1);
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
  `lastModified` datetime DEFAULT NULL,
  `category` int(11) DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FK_832d60c2728b457bb65918dd68a` (`REV`),
  CONSTRAINT `FK_832d60c2728b457bb65918dd68a` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Term_AUD`
--

LOCK TABLES `Term_AUD` WRITE;
/*!40000 ALTER TABLE `Term_AUD` DISABLE KEYS */;
INSERT INTO `Term_AUD` VALUES (1,3,0,'2013-06-01 11:00:24',3,'Hämeenlinnan osuusmeijeri',1),(2,4,0,'2013-06-01 11:00:40',1,'Kg',1),(3,5,0,'2013-06-01 11:00:48',1,'Litra',1),(4,6,0,'2013-06-01 11:01:00',2,'Myyntiin',1),(5,7,0,'2013-06-01 11:01:11',2,'Hylätty',1),(6,8,0,'2013-06-01 11:01:23',0,'Kirjoitusvirhe',1),(7,9,0,'2013-06-01 11:01:33',0,'Uusi mittaus',1);
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
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `firstName` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lastName` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `role` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_cd04f50ea1ce4d23994ed8ad851` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (1,'2013-06-01 11:49:13','myself@testshib.org','Me Myself','And I','Member');
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
  `lastModified` datetime DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `firstName` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lastName` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `role` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FK_dd263d376d4f44f98f9d8e82db0` (`REV`),
  CONSTRAINT `FK_dd263d376d4f44f98f9d8e82db0` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User_AUD`
--

LOCK TABLES `User_AUD` WRITE;
/*!40000 ALTER TABLE `User_AUD` DISABLE KEYS */;
INSERT INTO `User_AUD` VALUES (1,1,0,'2013-06-01 10:58:56','myself@testshib.org','Me Myself','And I','Member'),(1,2,1,'2013-06-01 11:00:11','myself@testshib.org','Me Myself','And I','Member'),(1,16,1,'2013-06-01 11:18:37','myself@testshib.org','Me Myself','And I','Member'),(1,27,1,'2013-06-01 11:49:13','myself@testshib.org','Me Myself','And I','Member');
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

-- Dump completed on 2013-06-03 11:01:43
