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
  KEY `FK_425a408443454770afe2a424d8d` (`user_id`),
  KEY `FK_c886f0b3723e49d9b10d92f29cd` (`product_id`),
  CONSTRAINT `FK_425a408443454770afe2a424d8d` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`),
  CONSTRAINT `FK_c886f0b3723e49d9b10d92f29cd` FOREIGN KEY (`product_id`) REFERENCES `Product` (`id`)
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
  `date` datetime DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FK_4d927ecfa62a4a838683222fa06` (`REV`),
  CONSTRAINT `FK_4d927ecfa62a4a838683222fa06` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
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
  `fields_index` int(11) DEFAULT NULL,
  `isImportant` tinyint(1) NOT NULL,
  `isRequired` tinyint(1) NOT NULL,
  `max` double DEFAULT NULL,
  `min` double DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `targetValue` tinyint(1) DEFAULT NULL,
  `fieldset_id` bigint(20) NOT NULL,
  `field_index` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ebc3496076484f5bbce19ae1837` (`fieldset_id`),
  CONSTRAINT `FK_ebc3496076484f5bbce19ae1837` FOREIGN KEY (`fieldset_id`) REFERENCES `Fieldset` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Field`
--

LOCK TABLES `Field` WRITE;
/*!40000 ALTER TABLE `Field` DISABLE KEYS */;
INSERT INTO `Field` VALUES (1,'2013-05-23 08:12:03',4,'Pinnoilla ei silminnähtäviä jäämiä',NULL,0,1,NULL,NULL,'Visuaalinen puhtaustarkastus',1,1,0),(2,'2013-05-23 08:12:03',2,'RLU > 250',NULL,0,1,NULL,250,'Puhtaustason määritys pikamenetelmällä',NULL,1,1),(3,'2013-05-23 08:13:53',3,'',NULL,0,1,NULL,NULL,'Vastaanoton päivämäärä',NULL,2,0),(4,'2013-05-23 08:13:53',2,'',NULL,0,1,NULL,NULL,'Kerman määrä',NULL,2,1),(5,'2013-05-23 08:13:53',2,'',NULL,0,1,NULL,NULL,'Kuoritun maidon määrä',NULL,2,2),(6,'2013-05-23 08:13:53',3,'',NULL,0,1,NULL,NULL,'Raakamaidon määrä',NULL,2,3),(7,'2013-05-23 08:14:55',2,'%',NULL,0,1,NULL,NULL,'Rasvapitoisuus',NULL,3,0),(8,'2013-05-23 08:14:55',2,'%',NULL,0,1,NULL,NULL,'Kuiva-ainepitoisuus',NULL,3,1),(9,'2013-05-23 08:14:55',2,'%',NULL,0,1,NULL,NULL,'Kaseiini',NULL,3,2),(10,'2013-05-23 08:17:42',0,'°C/min',NULL,0,1,NULL,NULL,'Lämpötila/aika',NULL,4,0),(11,'2013-05-23 08:17:42',4,'negatiivinen',NULL,0,0,NULL,NULL,'Peroksidaasikoe',1,4,1),(12,'2013-05-23 08:17:42',4,'negatiivinen',NULL,0,0,NULL,NULL,'Fosfataasikoe',1,4,2),(13,'2013-05-23 08:17:42',2,'< 1 pmy/ml',NULL,0,0,1,NULL,'Enterobakteerit',NULL,4,3),(14,'2013-05-23 08:17:42',2,'%',NULL,0,0,NULL,NULL,'Rasvapitoisuus',NULL,4,4),(15,'2013-05-23 08:20:22',5,'Hapateen spesifikaatiosta',NULL,0,1,NULL,NULL,'Hapatteen nimi',NULL,5,0),(16,'2013-05-23 08:20:22',2,'% Hapateen spesifikaatiosta',NULL,0,1,NULL,NULL,'Siirrostus-%',NULL,5,1),(17,'2013-05-23 08:20:22',2,'g Hapateen spesifikaatiosta',NULL,0,1,NULL,NULL,'Siirrostus-%',NULL,5,2),(18,'2013-05-23 08:20:22',3,'',NULL,0,1,NULL,NULL,'Hapatteen valmistupäivä',NULL,5,3),(19,'2013-05-23 08:21:27',2,'< 1 pmy/g',NULL,0,1,1,NULL,'Enterobakteerit',NULL,6,0),(20,'2013-05-23 08:21:27',2,'< 1 pmy/g',NULL,0,0,1,NULL,'Hiivat ja homeet',NULL,6,1),(21,'2013-05-23 08:35:49',1,'',NULL,0,1,NULL,3,'Ulkonäkö',NULL,7,0),(22,'2013-05-23 08:35:49',1,'',NULL,0,1,NULL,3,'Rakenne',NULL,7,1),(23,'2013-05-23 08:35:49',1,'',NULL,0,1,NULL,3,'Maku + haju',NULL,7,2),(24,'2013-05-23 08:25:41',2,'tuotekohtainen',NULL,0,1,NULL,NULL,'pH',NULL,8,0),(25,'2013-05-23 08:25:41',2,'tuotekohtainen',NULL,0,1,NULL,NULL,'°SH',NULL,8,1),(26,'2013-05-23 08:25:41',2,'≤ 1 pmy/g',NULL,0,1,1,NULL,'Enterobakteerit',NULL,8,2),(27,'2013-05-23 08:25:41',2,'≤ 100 pmy /g',NULL,0,1,100,NULL,'Hiivat ja homeet',NULL,8,3),(28,'2013-05-23 08:26:47',5,'',NULL,0,1,NULL,NULL,'Hillo',NULL,9,0),(29,'2013-05-23 08:26:47',2,'Hillosta riippuen 10-16%',NULL,0,1,NULL,NULL,'Punnitus/ml',NULL,9,1),(30,'2013-05-23 08:27:33',2,'Vähintään -35°C',NULL,0,1,-35,NULL,'Lämpötilamittaus',NULL,10,0),(31,'2013-05-23 08:28:39',2,'Hap. +4- +6°C, \r\nVoi  +4- +6°C, \r\nJlö vähintään -18°C, \r\nJuustot +4- +8°C',NULL,0,1,NULL,NULL,'Lämpötilamittaus',NULL,11,0),(32,'2013-05-23 08:33:50',2,'Hap. < 1pmy/g, \r\nVoi < 10 pmy/g, \r\nJlö < 100 pmy/g, \r\nJuustot < 100pmy/g',NULL,0,1,NULL,NULL,'Enterobakteerit',NULL,12,0),(33,'2013-05-23 08:33:50',2,'Voi ja hap. < 100 pmy/g',NULL,0,1,NULL,NULL,'Hiivat ja homeet',NULL,12,1),(34,'2013-05-23 08:33:50',2,'Voi ja juustot < 100 pmy/g',NULL,0,1,NULL,NULL,'Staphylococcus aureus',NULL,12,2),(35,'2013-05-23 08:33:50',2,'Kerran lukukaudessa. Tutkimus suoritetaan\r\nakkreditoidussa laboratoriossa.',NULL,0,0,NULL,NULL,'Listeria monoc.',NULL,12,3),(36,'2013-05-23 08:33:50',2,'Juustot. Kerran lukukaudessa. Tutkimus suoritetaan\r\nakkreditoidussa laboratoriossa.',NULL,0,0,NULL,NULL,'Koagulaasi positiiviset stafylokokit',NULL,12,4),(37,'2013-05-23 08:33:50',2,'< 1 pmy/g. Juustot ja jlö. Kerran lukukaudessa. Tutkimus suoritetaan\r\nakkreditoidussa laboratoriossa.',NULL,0,0,1,NULL,'E.coli',NULL,12,5),(38,'2013-05-23 08:35:04',1,'',NULL,0,1,NULL,3,'Ulkonäkö',NULL,13,0),(39,'2013-05-23 08:35:04',1,'',NULL,0,1,NULL,3,'Rakenne',NULL,13,1),(40,'2013-05-23 08:35:04',1,'',NULL,0,1,NULL,3,'Maku ja haju',NULL,13,2);
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
  `help` longtext COLLATE utf8_unicode_ci,
  `fields_index` int(11) DEFAULT NULL,
  `isImportant` tinyint(1) DEFAULT NULL,
  `isRequired` tinyint(1) DEFAULT NULL,
  `max` double DEFAULT NULL,
  `min` double DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `targetValue` tinyint(1) DEFAULT NULL,
  `fieldset_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FK_aa50c898f755450b9f09d9cbd98` (`REV`),
  CONSTRAINT `FK_aa50c898f755450b9f09d9cbd98` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Field_AUD`
--

LOCK TABLES `Field_AUD` WRITE;
/*!40000 ALTER TABLE `Field_AUD` DISABLE KEYS */;
INSERT INTO `Field_AUD` VALUES (1,3,0,4,'Pinnoilla ei silminnähtäviä jäämiä',0,0,1,NULL,NULL,'Visuaalinen puhtaustarkastus',1,1),(2,3,0,2,'RLU > 250',1,0,1,NULL,250,'Puhtaustason määritys pikamenetelmällä',NULL,1),(3,4,0,3,'',0,0,1,NULL,NULL,'Vastaanoton päivämäärä',NULL,2),(4,4,0,2,'',1,0,1,NULL,NULL,'Kerman määrä',NULL,2),(5,4,0,2,'',2,0,1,NULL,NULL,'Kuoritun maidon määrä',NULL,2),(6,4,0,3,'',3,0,1,NULL,NULL,'Raakamaidon määrä',NULL,2),(7,5,0,2,'%',0,0,1,NULL,NULL,'Rasvapitoisuus',NULL,3),(8,5,0,2,'%',1,0,1,NULL,NULL,'Kuiva-ainepitoisuus',NULL,3),(9,5,0,2,'%',2,0,1,NULL,NULL,'Kaseiini',NULL,3),(10,6,0,0,'°C/min',0,0,1,NULL,NULL,'Lämpötila/aika',NULL,4),(11,6,0,4,'negatiivinen',1,0,0,NULL,NULL,'Peroksidaasikoe',1,4),(12,6,0,4,'negatiivinen',2,0,0,NULL,NULL,'Fosfataasikoe',1,4),(13,6,0,2,'< 1 pmy/ml',3,0,0,1,NULL,'Enterobakteerit',NULL,4),(14,6,0,2,'%',4,0,0,NULL,NULL,'Rasvapitoisuus',NULL,4),(15,7,0,5,'Hapateen spesifikaatiosta',0,0,1,NULL,NULL,'Hapatteen nimi',NULL,5),(16,7,0,2,'% Hapateen spesifikaatiosta',1,0,1,NULL,NULL,'Siirrostus-%',NULL,5),(17,7,0,2,'g Hapateen spesifikaatiosta',2,0,1,NULL,NULL,'Siirrostus-%',NULL,5),(18,7,0,3,'',3,0,1,NULL,NULL,'Hapatteen valmistupäivä',NULL,5),(19,8,0,2,'< 1 pmy/g',0,0,1,1,NULL,'Enterobakteerit',NULL,6),(20,8,0,2,'< 1 pmy/g',1,0,0,1,NULL,'Hiivat ja homeet',NULL,6),(21,9,0,1,'',0,0,1,NULL,3,'Ulkonäkö',NULL,7),(22,9,0,1,'',1,0,1,NULL,3,'Rakenne',NULL,7),(23,9,0,1,'',2,0,1,NULL,3,'Maku + haju',NULL,7),(24,10,0,2,'tuotekohtainen',0,0,1,NULL,NULL,'pH',NULL,8),(25,10,0,2,'tuotekohtainen',1,0,1,NULL,NULL,'°SH',NULL,8),(26,10,0,2,'≤ 1 pmy/g',2,0,1,1,NULL,'Enterobakteerit',NULL,8),(27,10,0,2,'≤ 100 pmy /g',3,0,1,100,NULL,'Hiivat ja homeet',NULL,8),(28,11,0,5,'',0,0,1,NULL,NULL,'Hillo',NULL,9),(29,11,0,2,'Hillosta riippuen 10-16%',1,0,1,NULL,NULL,'Punnitus/ml',NULL,9),(30,12,0,2,'Vähintään -35°C',0,0,1,-35,NULL,'Lämpötilamittaus',NULL,10),(31,13,0,2,'Hap. +4- +6°C, \r\nVoi  +4- +6°C, \r\nJlö vähintään -18°C, \r\nJuustot +4- +8°C',0,0,1,NULL,NULL,'Lämpötilamittaus',NULL,11),(32,14,0,2,'Hap. < 1pmy/g, \r\nVoi < 10 pmy/g, \r\nJlö < 100 pmy/g, \r\nJuustot < 100pmy/g',0,0,1,NULL,NULL,'Enterobakteerit',NULL,12),(33,14,0,2,'Voi ja hap. < 100 pmy/g',1,0,1,NULL,NULL,'Hiivat ja homeet',NULL,12),(34,14,0,2,'Voi ja juustot < 100 pmy/g',2,0,1,NULL,NULL,'Staphylococcus aureus',NULL,12),(35,14,0,2,'Kerran lukukaudessa. Tutkimus suoritetaan\r\nakkreditoidussa laboratoriossa.',3,0,0,NULL,NULL,'Listeria monoc.',NULL,12),(36,14,0,2,'Juustot. Kerran lukukaudessa. Tutkimus suoritetaan\r\nakkreditoidussa laboratoriossa.',4,0,0,NULL,NULL,'Koagulaasi positiiviset stafylokokit',NULL,12),(37,14,0,2,'< 1 pmy/g. Juustot ja jlö. Kerran lukukaudessa. Tutkimus suoritetaan\r\nakkreditoidussa laboratoriossa.',5,0,0,1,NULL,'E.coli',NULL,12),(38,15,0,1,'',0,0,1,NULL,3,'Ulkonäkö',NULL,13),(39,15,0,1,'',1,0,1,NULL,3,'Rakenne',NULL,13),(40,15,0,1,'',2,0,1,NULL,3,'Maku ja haju',NULL,13);
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
  KEY `FK_4757762b4eed4055bd097c9984f` (`user_id`),
  CONSTRAINT `FK_4757762b4eed4055bd097c9984f` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Fieldset`
--

LOCK TABLES `Fieldset` WRITE;
/*!40000 ALTER TABLE `Fieldset` DISABLE KEYS */;
INSERT INTO `Fieldset` VALUES (1,'2013-05-23 08:12:03','','Ennen töiden aloittamista / laitteisto',1),(2,'2013-05-23 08:13:53','','Käytettävä raaka-aine',1),(3,'2013-05-23 08:14:55','','Maidon vakiointi',1),(4,'2013-05-23 08:17:42','','Maidon pastörointi',1),(5,'2013-05-23 08:20:22','','Hapatelisäys',1),(6,'2013-05-23 08:21:27','','Lisäyksen jälkeen/siirron jälkeen juustokattilasta',1),(7,'2013-05-23 08:35:49','Pistein 1-5','Hapatettu tuote (ennen maustamista/kirnuamista) aistinvarainen arviointi',1),(8,'2013-05-23 08:25:41','','Hapatettu tuote (ennen maustamista/kirnuamista)',1),(9,'2013-05-23 08:26:47','','Hillolisäys',1),(10,'2013-05-23 08:27:33','','Karkaisu',1),(11,'2013-05-23 08:28:39','','Varastointi',1),(12,'2013-05-23 08:33:50','','Valmis tuote',1),(13,'2013-05-23 08:35:04','Pistein 1-5','Aistinvarainen arviointi',1);
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
  `description` longtext COLLATE utf8_unicode_ci,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FK_c7158db72a4d4110bad011d1a23` (`REV`),
  CONSTRAINT `FK_c7158db72a4d4110bad011d1a23` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Fieldset_AUD`
--

LOCK TABLES `Fieldset_AUD` WRITE;
/*!40000 ALTER TABLE `Fieldset_AUD` DISABLE KEYS */;
INSERT INTO `Fieldset_AUD` VALUES (1,3,0,'','Ennen töiden aloittamista / laitteisto',1),(2,4,0,'','Käytettävä raaka-aine',1),(3,5,0,'','Maidon vakiointi',1),(4,6,0,'','Maidon pastörointi',1),(5,7,0,'','Hapatelisäys',1),(6,8,0,'','Lisäyksen jälkeen/siirron jälkeen juustokattilasta',1),(7,9,0,'','Hapatettu tuote (ennen maustamista/kirnuamista) aistinvarainen arviointi',1),(7,17,1,'Pistein 1-5','Hapatettu tuote (ennen maustamista/kirnuamista) aistinvarainen arviointi',1),(8,10,0,'','Hapatettu tuote (ennen maustamista/kirnuamista)',1),(9,11,0,'','Hillolisäys',1),(10,12,0,'','Karkaisu',1),(11,13,0,'','Varastointi',1),(12,14,0,'','Valmis tuote',1),(13,15,0,'Pistein 1-5','Aistinvarainen arviointi',1);
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
  KEY `FK_b0904bcfc2d847589c92d7b120f` (`user_id`),
  KEY `FK_0e82466853c54d29a5c3c9a58c4` (`batch_id`),
  KEY `FK_16c82149fb6e4a36b67dec0ee04` (`destiny_id`),
  KEY `FK_a511fbee41fe464ea0074e52a8e` (`unit_id`),
  CONSTRAINT `FK_0e82466853c54d29a5c3c9a58c4` FOREIGN KEY (`batch_id`) REFERENCES `Batch` (`id`),
  CONSTRAINT `FK_16c82149fb6e4a36b67dec0ee04` FOREIGN KEY (`destiny_id`) REFERENCES `Term` (`id`),
  CONSTRAINT `FK_a511fbee41fe464ea0074e52a8e` FOREIGN KEY (`unit_id`) REFERENCES `Term` (`id`),
  CONSTRAINT `FK_b0904bcfc2d847589c92d7b120f` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
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
  `amount` double DEFAULT NULL,
  `comment` longtext COLLATE utf8_unicode_ci,
  `date` datetime DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `batch_id` bigint(20) DEFAULT NULL,
  `destiny_id` bigint(20) DEFAULT NULL,
  `unit_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FK_622ec4c42fd64fc3bc387f98304` (`REV`),
  CONSTRAINT `FK_622ec4c42fd64fc3bc387f98304` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
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
  KEY `FK_e493ef15fe0f482f9f7c58f8bc8` (`user_id`),
  CONSTRAINT `FK_e493ef15fe0f482f9f7c58f8bc8` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Form`
--

LOCK TABLES `Form` WRITE;
/*!40000 ALTER TABLE `Form` DISABLE KEYS */;
INSERT INTO `Form` VALUES (1,'2013-05-23 08:35:20','','Tuotekortti',1);
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
  `description` longtext COLLATE utf8_unicode_ci,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FK_2747037441af40b598ecb9e1518` (`REV`),
  CONSTRAINT `FK_2747037441af40b598ecb9e1518` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Form_AUD`
--

LOCK TABLES `Form_AUD` WRITE;
/*!40000 ALTER TABLE `Form_AUD` DISABLE KEYS */;
INSERT INTO `Form_AUD` VALUES (1,2,0,'','Tuotekortti',1),(1,16,1,'','Tuotekortti',1);
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
  KEY `FK_54e57e75c5f6449da2e25d19d1f` (`fieldsets_id`),
  KEY `FK_7c3703611aec482daae863c98e6` (`Form_id`),
  CONSTRAINT `FK_54e57e75c5f6449da2e25d19d1f` FOREIGN KEY (`fieldsets_id`) REFERENCES `Fieldset` (`id`),
  CONSTRAINT `FK_7c3703611aec482daae863c98e6` FOREIGN KEY (`Form_id`) REFERENCES `Form` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Form_Fieldset`
--

LOCK TABLES `Form_Fieldset` WRITE;
/*!40000 ALTER TABLE `Form_Fieldset` DISABLE KEYS */;
INSERT INTO `Form_Fieldset` VALUES (1,1,0),(1,2,1),(1,3,2),(1,4,3),(1,5,4),(1,6,5),(1,7,6),(1,8,7),(1,9,8),(1,10,9),(1,11,10),(1,12,11),(1,13,12);
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
  KEY `FK_540cea6cee114f1a9a3ec9c7cc2` (`REV`),
  CONSTRAINT `FK_540cea6cee114f1a9a3ec9c7cc2` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Form_Fieldset_AUD`
--

LOCK TABLES `Form_Fieldset_AUD` WRITE;
/*!40000 ALTER TABLE `Form_Fieldset_AUD` DISABLE KEYS */;
INSERT INTO `Form_Fieldset_AUD` VALUES (16,1,1,0,0),(16,1,2,1,0),(16,1,3,2,0),(16,1,4,3,0),(16,1,5,4,0),(16,1,6,5,0),(16,1,7,6,0),(16,1,8,7,0),(16,1,9,8,0),(16,1,10,9,0),(16,1,11,10,0),(16,1,12,11,0),(16,1,13,12,0);
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
  UNIQUE KEY `UK_8a8021f53c2e4376929439beae8` (`name`),
  KEY `FK_68acffc452b14764bfe703b78f5` (`user_id`),
  KEY `FK_8a7c7890b5754398bcfa4daa083` (`form_id`),
  CONSTRAINT `FK_68acffc452b14764bfe703b78f5` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`),
  CONSTRAINT `FK_8a7c7890b5754398bcfa4daa083` FOREIGN KEY (`form_id`) REFERENCES `Form` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Ingredient`
--

LOCK TABLES `Ingredient` WRITE;
/*!40000 ALTER TABLE `Ingredient` DISABLE KEYS */;
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
  KEY `FK_de875c414b144558b95e62f7644` (`user_id`),
  KEY `FK_189316febb8847e1bae00b4c362` (`ingredient_id`),
  KEY `FK_6e2ef4d842ea496f88325e39aa9` (`producer_id`),
  KEY `FK_7450a2c065fe4a868d1003b8498` (`unit_id`),
  CONSTRAINT `FK_189316febb8847e1bae00b4c362` FOREIGN KEY (`ingredient_id`) REFERENCES `Ingredient` (`id`),
  CONSTRAINT `FK_6e2ef4d842ea496f88325e39aa9` FOREIGN KEY (`producer_id`) REFERENCES `Term` (`id`),
  CONSTRAINT `FK_7450a2c065fe4a868d1003b8498` FOREIGN KEY (`unit_id`) REFERENCES `Term` (`id`),
  CONSTRAINT `FK_de875c414b144558b95e62f7644` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
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
  `amount` double DEFAULT NULL,
  `received` datetime DEFAULT NULL,
  `used` double DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `ingredient_id` bigint(20) DEFAULT NULL,
  `producer_id` bigint(20) DEFAULT NULL,
  `unit_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FK_0f2a5ebe0f1c4f76ac6ba57443d` (`REV`),
  CONSTRAINT `FK_0f2a5ebe0f1c4f76ac6ba57443d` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
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
  KEY `FK_540b326198ac4399a2eb9f45713` (`batch_id`),
  KEY `FK_247268cc8df54402879ae9bd2c9` (`ingredientSupply_id`),
  CONSTRAINT `FK_247268cc8df54402879ae9bd2c9` FOREIGN KEY (`ingredientSupply_id`) REFERENCES `IngredientSupply` (`id`),
  CONSTRAINT `FK_540b326198ac4399a2eb9f45713` FOREIGN KEY (`batch_id`) REFERENCES `Batch` (`id`)
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
  KEY `FK_8cecabc2e350427c96246e15e5e` (`REV`),
  CONSTRAINT `FK_8cecabc2e350427c96246e15e5e` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
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
  `bestBefore` int(11) DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `form_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FK_7d711d9f3dfd4d3894b4cc0f643` (`REV`),
  CONSTRAINT `FK_7d711d9f3dfd4d3894b4cc0f643` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Ingredient_AUD`
--

LOCK TABLES `Ingredient_AUD` WRITE;
/*!40000 ALTER TABLE `Ingredient_AUD` DISABLE KEYS */;
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
  KEY `FK_8c82d4b206ca4807b559efedaf2` (`user_id`),
  CONSTRAINT `FK_8c82d4b206ca4807b559efedaf2` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
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
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `story` longtext COLLATE utf8_unicode_ci,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FK_485293bc2c1b464f95246a22fef` (`REV`),
  CONSTRAINT `FK_485293bc2c1b464f95246a22fef` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
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
  UNIQUE KEY `UK_2c4ca95e084f4488a6493bdbf82` (`no`),
  KEY `FK_018cccca169840b588c1322ef98` (`user_id`),
  CONSTRAINT `FK_018cccca169840b588c1322ef98` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Product`
--

LOCK TABLES `Product` WRITE;
/*!40000 ALTER TABLE `Product` DISABLE KEYS */;
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
  `description` longtext COLLATE utf8_unicode_ci,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `no` int(11) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FK_f6e8123f82f44892a84bf73d272` (`REV`),
  CONSTRAINT `FK_f6e8123f82f44892a84bf73d272` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Product_AUD`
--

LOCK TABLES `Product_AUD` WRITE;
/*!40000 ALTER TABLE `Product_AUD` DISABLE KEYS */;
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
  KEY `FK_4d8eed31c6aa472c98f4087c2ea` (`forms_id`),
  KEY `FK_fee1630f8ee64153a2d2dc3a9c2` (`Product_id`),
  CONSTRAINT `FK_4d8eed31c6aa472c98f4087c2ea` FOREIGN KEY (`forms_id`) REFERENCES `Form` (`id`),
  CONSTRAINT `FK_fee1630f8ee64153a2d2dc3a9c2` FOREIGN KEY (`Product_id`) REFERENCES `Product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Product_Form`
--

LOCK TABLES `Product_Form` WRITE;
/*!40000 ALTER TABLE `Product_Form` DISABLE KEYS */;
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
  KEY `FK_1ec20ea70cc9479b9fa0b19e952` (`REV`),
  CONSTRAINT `FK_1ec20ea70cc9479b9fa0b19e952` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Product_Form_AUD`
--

LOCK TABLES `Product_Form_AUD` WRITE;
/*!40000 ALTER TABLE `Product_Form_AUD` DISABLE KEYS */;
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
  KEY `FK_7df22ea3fedd4c138eec3d3bbc1` (`ingredients_id`),
  KEY `FK_1389af20f0784863ae0e2f43d2f` (`Product_id`),
  CONSTRAINT `FK_1389af20f0784863ae0e2f43d2f` FOREIGN KEY (`Product_id`) REFERENCES `Product` (`id`),
  CONSTRAINT `FK_7df22ea3fedd4c138eec3d3bbc1` FOREIGN KEY (`ingredients_id`) REFERENCES `Ingredient` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Product_Ingredient`
--

LOCK TABLES `Product_Ingredient` WRITE;
/*!40000 ALTER TABLE `Product_Ingredient` DISABLE KEYS */;
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
  KEY `FK_143f1035ecfd4b02aab36459766` (`REV`),
  CONSTRAINT `FK_143f1035ecfd4b02aab36459766` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Product_Ingredient_AUD`
--

LOCK TABLES `Product_Ingredient_AUD` WRITE;
/*!40000 ALTER TABLE `Product_Ingredient_AUD` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `REVINFO`
--

LOCK TABLES `REVINFO` WRITE;
/*!40000 ALTER TABLE `REVINFO` DISABLE KEYS */;
INSERT INTO `REVINFO` VALUES (1,1369285821981),(2,1369285847544),(3,1369285923674),(4,1369286033883),(5,1369286095462),(6,1369286262241),(7,1369286422506),(8,1369286487562),(9,1369286591974),(10,1369286741700),(11,1369286807806),(12,1369286853384),(13,1369286919407),(14,1369287230920),(15,1369287304390),(16,1369287320898),(17,1369287349280);
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
  KEY `FK_9180da807f044ddfaef3b65c8f9` (`user_id`),
  KEY `FK_7387b173e1b34e8e9c5a87e0ad9` (`field_id`),
  KEY `FK_0c33445c25bc46efaaa3aa31c6c` (`reason_id`),
  KEY `FK_0a7ffb5b933644f4a67427c6836` (`results_id`),
  CONSTRAINT `FK_0a7ffb5b933644f4a67427c6836` FOREIGN KEY (`results_id`) REFERENCES `Results` (`id`),
  CONSTRAINT `FK_0c33445c25bc46efaaa3aa31c6c` FOREIGN KEY (`reason_id`) REFERENCES `Term` (`id`),
  CONSTRAINT `FK_7387b173e1b34e8e9c5a87e0ad9` FOREIGN KEY (`field_id`) REFERENCES `Field` (`id`),
  CONSTRAINT `FK_9180da807f044ddfaef3b65c8f9` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
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
  KEY `FK_425d8a3b061e41a5b0a7028e6f5` (`REV`),
  CONSTRAINT `FK_425d8a3b061e41a5b0a7028e6f5` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
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
  KEY `FK_5648b979622f472f89d2e8c2915` (`form_id`),
  KEY `FK_c166f36b7ec64f23b70689bc164` (`form_id`),
  CONSTRAINT `FK_5648b979622f472f89d2e8c2915` FOREIGN KEY (`form_id`) REFERENCES `Form` (`id`),
  CONSTRAINT `FK_c166f36b7ec64f23b70689bc164` FOREIGN KEY (`form_id`) REFERENCES `Batch` (`id`)
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
  `form_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FK_7d34770da6764f9fa7b1f6428f3` (`REV`),
  CONSTRAINT `FK_7d34770da6764f9fa7b1f6428f3` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
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
  `Results_id` bigint(20) NOT NULL,
  `batches_id` bigint(20) NOT NULL,
  KEY `FK_cc907921c9a343c1b1354f809e6` (`batches_id`),
  KEY `FK_1a02f420eac54bb4b53e7432655` (`Results_id`),
  CONSTRAINT `FK_1a02f420eac54bb4b53e7432655` FOREIGN KEY (`Results_id`) REFERENCES `Results` (`id`),
  CONSTRAINT `FK_cc907921c9a343c1b1354f809e6` FOREIGN KEY (`batches_id`) REFERENCES `Batch` (`id`)
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
  `Results_id` bigint(20) NOT NULL,
  `batches_id` bigint(20) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`REV`,`Results_id`,`batches_id`),
  KEY `FK_e6253c2aee6f437a830caf38190` (`REV`),
  CONSTRAINT `FK_e6253c2aee6f437a830caf38190` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
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
  PRIMARY KEY (`REV`,`results_id`,`id`),
  KEY `FK_08011f8b9ba24616aa83ff0599c` (`REV`),
  CONSTRAINT `FK_08011f8b9ba24616aa83ff0599c` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
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
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_5dabca4182044806ad0ad3a530a` (`user_id`),
  CONSTRAINT `FK_5dabca4182044806ad0ad3a530a` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Term`
--

LOCK TABLES `Term` WRITE;
/*!40000 ALTER TABLE `Term` DISABLE KEYS */;
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
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FK_a5f20c40f4ab4f649363838c6cb` (`REV`),
  CONSTRAINT `FK_a5f20c40f4ab4f649363838c6cb` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Term_AUD`
--

LOCK TABLES `Term_AUD` WRITE;
/*!40000 ALTER TABLE `Term_AUD` DISABLE KEYS */;
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
  UNIQUE KEY `UK_60b26cd238cd4266b7a40add572` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (1,'2013-05-23 08:10:21','myself@testshib.org','Me Myself','And I','Member');
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
  `email` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `firstName` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lastName` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `role` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FK_073c83604ca242249133a31909c` (`REV`),
  CONSTRAINT `FK_073c83604ca242249133a31909c` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
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

-- Dump completed on 2013-05-23  5:36:11
