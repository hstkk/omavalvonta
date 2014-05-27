-- MySQL dump 10.13  Distrib 5.5.35, for debian-linux-gnu (i686)
--
-- Host: localhost    Database: omavalvonta
-- ------------------------------------------------------
-- Server version	5.5.35-1ubuntu1

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
  KEY `FK_2gf13rk8qe43a8trobm98bb46` (`user_id`),
  KEY `FK_l4pkpv3em77xb6taa9q0e0880` (`product_id`),
  CONSTRAINT `FK_2gf13rk8qe43a8trobm98bb46` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`),
  CONSTRAINT `FK_l4pkpv3em77xb6taa9q0e0880` FOREIGN KEY (`product_id`) REFERENCES `Product` (`id`)
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
  KEY `FK_oljrkyaw3i6h360a5dbq0p8k` (`REV`),
  CONSTRAINT `FK_oljrkyaw3i6h360a5dbq0p8k` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
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
  `isImportant` bit(1) NOT NULL,
  `isRequired` bit(1) NOT NULL,
  `max` double DEFAULT NULL,
  `min` double DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `targetValue` bit(1) DEFAULT NULL,
  `fieldset_id` bigint(20) NOT NULL,
  `field_index` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_emjvb9vju6xqglbhg7n7kyexy` (`fieldset_id`),
  CONSTRAINT `FK_emjvb9vju6xqglbhg7n7kyexy` FOREIGN KEY (`fieldset_id`) REFERENCES `Fieldset` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Field`
--

LOCK TABLES `Field` WRITE;
/*!40000 ALTER TABLE `Field` DISABLE KEYS */;
INSERT INTO `Field` VALUES (1,'2014-05-28 00:34:25',4,'Pinnoilla ei silminnähtäviä jäämiä','\0','',NULL,NULL,'Visuaalinen puhtaustarkastus','',1,0),(2,'2014-05-28 00:34:25',2,'RLU > 250','\0','\0',NULL,250,'Puhtaustason määritys pikamenetelmällä',NULL,1,1),(3,'2014-05-28 00:37:29',2,'Gerber','\0','',NULL,NULL,'Rasvapitoisuus',NULL,2,0),(4,'2014-05-28 00:40:57',0,'','\0','',NULL,NULL,'Lämpötila/aika- tarkkailu',NULL,3,0),(5,'2014-05-28 00:42:58',2,'Vähintään -35°C','\0','',-35,NULL,'lämpötilamittaus',NULL,4,0),(6,'2014-05-28 00:53:59',2,'Vähintään -18°C','\0','',-18,NULL,'lämpötilamittaus',NULL,5,0),(7,'2014-05-28 00:46:27',2,'Tutkimus suoritetaan\r\nakkreditoidussa laboratoriossa kerran lukukaudessa','\0','\0',NULL,NULL,'Listeria monoc.',NULL,6,0),(8,'2014-05-28 00:46:27',2,'< 100 pmy/g','\0','',100,NULL,'Enterobakteerit',NULL,6,1),(9,'2014-05-28 00:48:55',1,'','\0','',5,3,'Ulkonäkö',NULL,7,0),(10,'2014-05-28 00:48:55',1,'','\0','',5,3,'Rakenne',NULL,7,1),(11,'2014-05-28 00:48:55',1,'','\0','',5,3,'Maku ja haju',NULL,7,2),(12,'2014-05-28 00:56:47',4,'','\0','',NULL,NULL,'Kirnu',NULL,8,0),(13,'2014-05-28 00:56:47',4,'','\0','',NULL,NULL,'Voipistooli',NULL,8,1),(14,'2014-05-28 01:01:43',3,'','\0','',NULL,NULL,'Valmistuspäivämäärä',NULL,9,0),(15,'2014-05-28 01:01:43',0,'','\0','',NULL,NULL,'Hapatteen nimi',NULL,9,1),(16,'2014-05-28 01:01:43',2,'%','\0','',NULL,NULL,'Siirrostusmäärä',NULL,9,2),(17,'2014-05-28 01:01:43',2,'g','\0','',NULL,NULL,'Siirrostusmäärä',NULL,9,3),(18,'2014-05-28 01:05:34',2,'%. 30-43 %','\0','',43,30,'Rasvapitoisuus',NULL,10,0),(19,'2014-05-28 01:05:34',2,'pmy/g. < 1 pmy/g','\0','',1,NULL,'Enterobakteerit',NULL,10,1),(20,'2014-05-28 01:05:34',4,'','\0','',NULL,NULL,'Peroksidaasikoe','\0',10,2),(21,'2014-05-28 01:10:57',2,'pmy/g. < 1 pmy/g','\0','',1,NULL,'Enterobakteerit',NULL,11,0),(22,'2014-05-28 01:10:57',2,'pmy/g. < 10 pmy/g','\0','',10,NULL,'Hiivat ja homeet',NULL,11,1),(23,'2014-05-28 01:10:57',2,'4,4 - 5,0','\0','',5,4.4,'pH-luku',NULL,11,2),(24,'2014-05-28 01:10:57',2,'> 25','\0','',NULL,25,'oSH-aste /r.o',NULL,11,3),(25,'2014-05-28 01:13:11',2,'pmy/g. < 1 pmy/g','\0','',1,NULL,'Enterobakteerit',NULL,12,0),(26,'2014-05-28 01:18:55',2,'pmy/g. < 10 pmy/g','\0','',10,NULL,'Enterobakteerit',NULL,13,0),(27,'2014-05-28 01:18:55',2,'pmy/g. < 1000 pmy/g','\0','',1000,NULL,'Hiivat',NULL,13,1),(28,'2014-05-28 01:18:55',2,'pmy/g. < 100 pmy/g','\0','',100,NULL,'Homeet ',NULL,13,2),(29,'2014-05-28 01:18:55',2,'< 100 pmy/g. Tutkimus suoritetaan\r\nakkreditoidussa laboratoriossa kerran lukukaudessa','\0','\0',100,NULL,'Staphylococcus aureus',NULL,13,3),(30,'2014-05-28 01:18:55',2,'n = 5 , ei todettavissa / 1 g. Tutkimus suoritetaan\r\nakkreditoidussa laboratoriossa kerran lukukaudessa','\0','\0',NULL,NULL,'Listeria monogytogenes',NULL,13,4),(31,'2014-05-28 01:23:50',2,'°C. +4- +6°C','\0','',6,4,'lämpötilamittaus',NULL,14,0);
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
  `isImportant` bit(1) DEFAULT NULL,
  `isRequired` bit(1) DEFAULT NULL,
  `max` double DEFAULT NULL,
  `min` double DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `targetValue` bit(1) DEFAULT NULL,
  `fieldset_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FK_5m03gok1snphp99j55vcau67c` (`REV`),
  CONSTRAINT `FK_5m03gok1snphp99j55vcau67c` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Field_AUD`
--

LOCK TABLES `Field_AUD` WRITE;
/*!40000 ALTER TABLE `Field_AUD` DISABLE KEYS */;
INSERT INTO `Field_AUD` VALUES (1,2,0,'2014-05-28 00:34:25',4,'Pinnoilla ei silminnähtäviä jäämiä','\0','',NULL,NULL,'Visuaalinen puhtaustarkastus','',1),(2,2,0,'2014-05-28 00:34:25',2,'RLU > 250','\0','\0',NULL,250,'Puhtaustason määritys pikamenetelmällä',NULL,1),(3,3,0,'2014-05-28 00:37:29',2,'Gerber','\0','',NULL,NULL,'Rasvapitoisuus',NULL,2),(4,4,0,'2014-05-28 00:40:57',0,'','\0','',NULL,NULL,'Lämpötila/aika- tarkkailu',NULL,3),(5,5,0,'2014-05-28 00:42:58',2,'Vähintään -35°C','\0','',-35,NULL,'lämpötilamittaus',NULL,4),(6,6,0,'2014-05-28 00:44:14',2,'Vähintään -18°C','\0','',-18,NULL,'lämpötilamittaus',NULL,5),(6,10,1,'2014-05-28 00:53:59',2,'Vähintään -18°C','\0','',-18,NULL,'lämpötilamittaus',NULL,5),(7,7,0,'2014-05-28 00:46:27',2,'Tutkimus suoritetaan\r\nakkreditoidussa laboratoriossa kerran lukukaudessa','\0','\0',NULL,NULL,'Listeria monoc.',NULL,6),(8,7,0,'2014-05-28 00:46:27',2,'< 100 pmy/g','\0','',100,NULL,'Enterobakteerit',NULL,6),(9,8,0,'2014-05-28 00:48:55',1,'','\0','',5,3,'Ulkonäkö',NULL,7),(10,8,0,'2014-05-28 00:48:55',1,'','\0','',5,3,'Rakenne',NULL,7),(11,8,0,'2014-05-28 00:48:55',1,'','\0','',5,3,'Maku ja haju',NULL,7),(12,11,0,'2014-05-28 00:56:47',4,'','\0','',NULL,NULL,'Kirnu',NULL,8),(13,11,0,'2014-05-28 00:56:47',4,'','\0','',NULL,NULL,'Voipistooli',NULL,8),(14,13,0,'2014-05-28 01:00:05',3,'','\0','',NULL,NULL,'Valmistuspäivämäärä',NULL,9),(14,14,1,'2014-05-28 01:01:43',3,'','\0','',NULL,NULL,'Valmistuspäivämäärä',NULL,9),(15,13,0,'2014-05-28 01:00:05',0,'','\0','',NULL,NULL,'Hapatteen nimi',NULL,9),(15,14,1,'2014-05-28 01:01:43',0,'','\0','',NULL,NULL,'Hapatteen nimi',NULL,9),(16,13,0,'2014-05-28 01:00:05',2,'','\0','',NULL,NULL,'Siirrostusmäärä',NULL,9),(16,14,1,'2014-05-28 01:01:43',2,'%','\0','',NULL,NULL,'Siirrostusmäärä',NULL,9),(17,14,0,'2014-05-28 01:01:43',2,'g','\0','',NULL,NULL,'Siirrostusmäärä',NULL,9),(18,16,0,'2014-05-28 01:05:34',2,'%. 30-43 %','\0','',43,30,'Rasvapitoisuus',NULL,10),(19,16,0,'2014-05-28 01:05:34',2,'pmy/g. < 1 pmy/g','\0','',1,NULL,'Enterobakteerit',NULL,10),(20,16,0,'2014-05-28 01:05:34',4,'','\0','',NULL,NULL,'Peroksidaasikoe','\0',10),(21,18,0,'2014-05-28 01:10:57',2,'pmy/g. < 1 pmy/g','\0','',1,NULL,'Enterobakteerit',NULL,11),(22,18,0,'2014-05-28 01:10:57',2,'pmy/g. < 10 pmy/g','\0','',10,NULL,'Hiivat ja homeet',NULL,11),(23,18,0,'2014-05-28 01:10:57',2,'4,4 - 5,0','\0','',5,4.4,'pH-luku',NULL,11),(24,18,0,'2014-05-28 01:10:57',2,'> 25','\0','',NULL,25,'oSH-aste /r.o',NULL,11),(25,20,0,'2014-05-28 01:13:11',2,'pmy/g. < 1 pmy/g','\0','',1,NULL,'Enterobakteerit',NULL,12),(26,22,0,'2014-05-28 01:18:55',2,'pmy/g. < 10 pmy/g','\0','',10,NULL,'Enterobakteerit',NULL,13),(27,22,0,'2014-05-28 01:18:55',2,'pmy/g. < 1000 pmy/g','\0','',1000,NULL,'Hiivat',NULL,13),(28,22,0,'2014-05-28 01:18:55',2,'pmy/g. < 100 pmy/g','\0','',100,NULL,'Homeet ',NULL,13),(29,22,0,'2014-05-28 01:18:55',2,'< 100 pmy/g. Tutkimus suoritetaan\r\nakkreditoidussa laboratoriossa kerran lukukaudessa','\0','\0',100,NULL,'Staphylococcus aureus',NULL,13),(30,22,0,'2014-05-28 01:18:55',2,'n = 5 , ei todettavissa / 1 g. Tutkimus suoritetaan\r\nakkreditoidussa laboratoriossa kerran lukukaudessa','\0','\0',NULL,NULL,'Listeria monogytogenes',NULL,13),(31,25,0,'2014-05-28 01:23:50',2,'°C. +4- +6°C','\0','',6,4,'lämpötilamittaus',NULL,14);
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
  KEY `FK_f4818gtutr93vhkj9agofkwja` (`user_id`),
  CONSTRAINT `FK_f4818gtutr93vhkj9agofkwja` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Fieldset`
--

LOCK TABLES `Fieldset` WRITE;
/*!40000 ALTER TABLE `Fieldset` DISABLE KEYS */;
INSERT INTO `Fieldset` VALUES (1,'2014-05-28 00:34:25','','Ennen töiden aloittamista / laitteisto',1),(2,'2014-05-28 00:37:29','','Jäätelömassan valmistus',1),(3,'2014-05-28 00:40:57','','Pastörointi',1),(4,'2014-05-28 00:42:58','Merkintä myös pakastimen lt-seuranta-kaavakkeeseen','Karkaisu',1),(5,'2014-05-28 00:53:59','Merkintä myös pakastimen /kylmiön lt-seuranta-kaavakkeeseen','Jäätelön varastointi',1),(6,'2014-05-28 00:46:27','','Valmis tuote',1),(7,'2014-05-28 00:48:55','Pistein 1-5','Aistinvarainen arviointi',1),(8,'2014-05-28 00:56:47','','Valmistuslaitteisto',1),(9,'2014-05-28 01:01:43','Hapatetiedot hapatelaboratorion kansiossa!','Käyttöhapate',1),(10,'2014-05-28 01:05:34','','Pastöroitu kerma hapatelisäyksen jälkeen',1),(11,'2014-05-28 01:10:57','','Hapatettu kerma tankissa',1),(12,'2014-05-28 01:13:11','','Hapatettu kerma kirnussa',1),(13,'2014-05-28 01:18:55','','Valmis tuote',1),(14,'2014-05-28 01:23:50','','Voin varastointi',1);
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
  KEY `FK_gxwf67sklngxm0byvwcqann07` (`REV`),
  CONSTRAINT `FK_gxwf67sklngxm0byvwcqann07` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Fieldset_AUD`
--

LOCK TABLES `Fieldset_AUD` WRITE;
/*!40000 ALTER TABLE `Fieldset_AUD` DISABLE KEYS */;
INSERT INTO `Fieldset_AUD` VALUES (1,2,0,'2014-05-28 00:34:25','','Ennen töiden aloittamista / laitteisto',1),(2,3,0,'2014-05-28 00:37:29','','Jäätelömassan valmistus',1),(3,4,0,'2014-05-28 00:40:57','','Pastörointi',1),(4,5,0,'2014-05-28 00:42:58','Merkintä myös pakastimen lt-seuranta-kaavakkeeseen','Karkaisu',1),(5,6,0,'2014-05-28 00:44:14','Merkintä myös pakastimen /kylmiön lt-seuranta-kaavakkeeseen','Varastointi',1),(5,10,1,'2014-05-28 00:53:59','Merkintä myös pakastimen /kylmiön lt-seuranta-kaavakkeeseen','Jäätelön varastointi',1),(6,7,0,'2014-05-28 00:46:27','','Valmis tuote',1),(7,8,0,'2014-05-28 00:48:55','Pistein 1-5','Aistinvarainen arviointi',1),(8,11,0,'2014-05-28 00:56:47','','Valmistuslaitteisto',1),(9,13,0,'2014-05-28 01:00:05','Hapatetiedot hapatelaboratorion kansiossa!','Käyttöhapate',1),(9,14,1,'2014-05-28 01:01:43','Hapatetiedot hapatelaboratorion kansiossa!','Käyttöhapate',1),(10,16,0,'2014-05-28 01:05:34','','Pastöroitu kerma hapatelisäyksen jälkeen',1),(11,18,0,'2014-05-28 01:10:57','','Hapatettu kerma tankissa',1),(12,20,0,'2014-05-28 01:13:11','','Hapatettu kerma kirnussa',1),(13,22,0,'2014-05-28 01:18:55','','Valmis tuote',1),(14,25,0,'2014-05-28 01:23:50','','Voin varastointi',1);
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
  KEY `FK_8cby524g3s0tjsdsm9r40ytpr` (`user_id`),
  KEY `FK_3bftuhubr1yu5slfne1qo6qcu` (`batch_id`),
  KEY `FK_kj1gfnkk0776conlwxq1p62wb` (`destiny_id`),
  KEY `FK_42ihah3qx1kdmm1qumgrd3ajv` (`unit_id`),
  CONSTRAINT `FK_3bftuhubr1yu5slfne1qo6qcu` FOREIGN KEY (`batch_id`) REFERENCES `Batch` (`id`),
  CONSTRAINT `FK_42ihah3qx1kdmm1qumgrd3ajv` FOREIGN KEY (`unit_id`) REFERENCES `Term` (`id`),
  CONSTRAINT `FK_8cby524g3s0tjsdsm9r40ytpr` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`),
  CONSTRAINT `FK_kj1gfnkk0776conlwxq1p62wb` FOREIGN KEY (`destiny_id`) REFERENCES `Term` (`id`)
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
  KEY `FK_jrfjgsqcgtw58145npdrn93bt` (`REV`),
  CONSTRAINT `FK_jrfjgsqcgtw58145npdrn93bt` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
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
  KEY `FK_cjgarer65gx2nri55giw00gat` (`user_id`),
  CONSTRAINT `FK_cjgarer65gx2nri55giw00gat` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Form`
--

LOCK TABLES `Form` WRITE;
/*!40000 ALTER TABLE `Form` DISABLE KEYS */;
INSERT INTO `Form` VALUES (1,'2014-05-28 00:50:38','','Jäätelön tuotekortti',1),(2,'2014-05-28 01:24:08','Valmiste voi','Ravintorasvojen tuotekortti',1);
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
  KEY `FK_r2e7038yncefc1nrxvykovgmw` (`REV`),
  CONSTRAINT `FK_r2e7038yncefc1nrxvykovgmw` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Form_AUD`
--

LOCK TABLES `Form_AUD` WRITE;
/*!40000 ALTER TABLE `Form_AUD` DISABLE KEYS */;
INSERT INTO `Form_AUD` VALUES (1,9,0,'2014-05-28 00:50:38','','Jäätelön tuotekortti',1),(2,12,0,'2014-05-28 00:58:04','Valmiste voi','Ravintorasvojen tuotekortti',1),(2,15,1,'2014-05-28 01:02:03','Valmiste voi','Ravintorasvojen tuotekortti',1),(2,17,1,'2014-05-28 01:05:58','Valmiste voi','Ravintorasvojen tuotekortti',1),(2,19,1,'2014-05-28 01:11:41','Valmiste voi','Ravintorasvojen tuotekortti',1),(2,21,1,'2014-05-28 01:13:24','Valmiste voi','Ravintorasvojen tuotekortti',1),(2,23,1,'2014-05-28 01:19:09','Valmiste voi','Ravintorasvojen tuotekortti',1),(2,24,1,'2014-05-28 01:19:30','Valmiste voi','Ravintorasvojen tuotekortti',1),(2,26,1,'2014-05-28 01:24:08','Valmiste voi','Ravintorasvojen tuotekortti',1);
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
  KEY `FK_kub9fnwuf3uc0mc94d1aaxhrp` (`fieldsets_id`),
  CONSTRAINT `FK_7agrhu72fuxvulay61suf9u1` FOREIGN KEY (`Form_id`) REFERENCES `Form` (`id`),
  CONSTRAINT `FK_kub9fnwuf3uc0mc94d1aaxhrp` FOREIGN KEY (`fieldsets_id`) REFERENCES `Fieldset` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Form_Fieldset`
--

LOCK TABLES `Form_Fieldset` WRITE;
/*!40000 ALTER TABLE `Form_Fieldset` DISABLE KEYS */;
INSERT INTO `Form_Fieldset` VALUES (1,1,0),(2,1,1),(1,2,1),(1,3,2),(1,4,3),(1,5,4),(1,6,5),(1,7,6),(2,7,8),(2,8,0),(2,9,2),(2,10,3),(2,11,4),(2,12,5),(2,13,7),(2,14,6);
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
  CONSTRAINT `FK_tbrh37dek2e2hvj6a3alro7fp` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Form_Fieldset_AUD`
--

LOCK TABLES `Form_Fieldset_AUD` WRITE;
/*!40000 ALTER TABLE `Form_Fieldset_AUD` DISABLE KEYS */;
INSERT INTO `Form_Fieldset_AUD` VALUES (9,1,1,0,0),(9,1,2,1,0),(9,1,3,2,0),(9,1,4,3,0),(9,1,5,4,0),(9,1,6,5,0),(9,1,7,6,0),(12,2,1,1,0),(12,2,8,0,0),(15,2,9,2,0),(17,2,10,3,0),(19,2,11,4,0),(21,2,12,5,0),(23,2,13,6,0),(24,2,7,7,0),(26,2,7,7,2),(26,2,7,8,0),(26,2,13,6,2),(26,2,13,7,0),(26,2,14,6,0);
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
  UNIQUE KEY `UK_etn0e357qi11y18ef475hxl7u` (`name`),
  KEY `FK_87m4mo2uinog5hix2ne3asjst` (`user_id`),
  KEY `FK_mdi0xmkyk3axhyxdtadoxh2cm` (`form_id`),
  CONSTRAINT `FK_87m4mo2uinog5hix2ne3asjst` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`),
  CONSTRAINT `FK_mdi0xmkyk3axhyxdtadoxh2cm` FOREIGN KEY (`form_id`) REFERENCES `Form` (`id`)
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
  KEY `FK_ctfbbbq9shdx4h7wcgi4j2d9c` (`user_id`),
  KEY `FK_3nbvtavjtupgxwmyg4moete66` (`ingredient_id`),
  KEY `FK_kvris9x1jnae051nwngudmw8s` (`producer_id`),
  KEY `FK_8ai55h869dxbbxmtpwp8w7a33` (`unit_id`),
  CONSTRAINT `FK_3nbvtavjtupgxwmyg4moete66` FOREIGN KEY (`ingredient_id`) REFERENCES `Ingredient` (`id`),
  CONSTRAINT `FK_8ai55h869dxbbxmtpwp8w7a33` FOREIGN KEY (`unit_id`) REFERENCES `Term` (`id`),
  CONSTRAINT `FK_ctfbbbq9shdx4h7wcgi4j2d9c` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`),
  CONSTRAINT `FK_kvris9x1jnae051nwngudmw8s` FOREIGN KEY (`producer_id`) REFERENCES `Term` (`id`)
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
  KEY `FK_27dkvmln12keeh9ci13l8ubtn` (`REV`),
  CONSTRAINT `FK_27dkvmln12keeh9ci13l8ubtn` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
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
  KEY `FK_eo6tw7oc7tybkpewb5ffgkxa2` (`batch_id`),
  KEY `FK_4incuilbbhwykn3dew8rhdf3l` (`ingredientSupply_id`),
  CONSTRAINT `FK_4incuilbbhwykn3dew8rhdf3l` FOREIGN KEY (`ingredientSupply_id`) REFERENCES `IngredientSupply` (`id`),
  CONSTRAINT `FK_eo6tw7oc7tybkpewb5ffgkxa2` FOREIGN KEY (`batch_id`) REFERENCES `Batch` (`id`)
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
  KEY `FK_a9uo2rt9olpajn7qy3s33x0fb` (`REV`),
  CONSTRAINT `FK_a9uo2rt9olpajn7qy3s33x0fb` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
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
  KEY `FK_sxxwy1viwfdntpkemdvinlfou` (`REV`),
  CONSTRAINT `FK_sxxwy1viwfdntpkemdvinlfou` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
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
  KEY `FK_ooj4pf7nkahhlylkx3v00iw5w` (`user_id`),
  CONSTRAINT `FK_ooj4pf7nkahhlylkx3v00iw5w` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
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
  KEY `FK_3xbvfhmqm4ph71jgegx5ws581` (`REV`),
  CONSTRAINT `FK_3xbvfhmqm4ph71jgegx5ws581` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
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
  UNIQUE KEY `UK_hok78lpr6tmtd1elhmi3k21ci` (`no`),
  KEY `FK_449ago0t8nh76gb17e2f59f44` (`user_id`),
  CONSTRAINT `FK_449ago0t8nh76gb17e2f59f44` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
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
  `lastModified` datetime DEFAULT NULL,
  `description` longtext COLLATE utf8_unicode_ci,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `no` int(11) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FK_imojayr2c18mxekbske8w946k` (`REV`),
  CONSTRAINT `FK_imojayr2c18mxekbske8w946k` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
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
  KEY `FK_cg9xo5japy1u8o2b5tu3k49yl` (`forms_id`),
  KEY `FK_knl0ek1s56eaemsw5jxg0jaqj` (`Product_id`),
  CONSTRAINT `FK_cg9xo5japy1u8o2b5tu3k49yl` FOREIGN KEY (`forms_id`) REFERENCES `Form` (`id`),
  CONSTRAINT `FK_knl0ek1s56eaemsw5jxg0jaqj` FOREIGN KEY (`Product_id`) REFERENCES `Product` (`id`)
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
  CONSTRAINT `FK_ejydrl4jfwprr24aw78oi2ppj` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
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
  KEY `FK_s44cqyd8e4uo6gowaqmdqisvr` (`ingredients_id`),
  KEY `FK_67xavj60bgpejr5l2ooiugaa1` (`Product_id`),
  CONSTRAINT `FK_67xavj60bgpejr5l2ooiugaa1` FOREIGN KEY (`Product_id`) REFERENCES `Product` (`id`),
  CONSTRAINT `FK_s44cqyd8e4uo6gowaqmdqisvr` FOREIGN KEY (`ingredients_id`) REFERENCES `Ingredient` (`id`)
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
  CONSTRAINT `FK_jmw1b2hwlt446o7v899cgfrn8` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
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
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `REVINFO`
--

LOCK TABLES `REVINFO` WRITE;
/*!40000 ALTER TABLE `REVINFO` DISABLE KEYS */;
INSERT INTO `REVINFO` VALUES (1,1401226292130),(2,1401226465923),(3,1401226649644),(4,1401226857467),(5,1401226978386),(6,1401227054469),(7,1401227187707),(8,1401227335167),(9,1401227438068),(10,1401227639229),(11,1401227807489),(12,1401227884634),(13,1401228005695),(14,1401228103706),(15,1401228123025),(16,1401228334431),(17,1401228358609),(18,1401228657579),(19,1401228701201),(20,1401228791834),(21,1401228804423),(22,1401229135275),(23,1401229149899),(24,1401229170173),(25,1401229430847),(26,1401229448568);
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
  `valueBoolean` bit(1) DEFAULT NULL,
  `valueDate` datetime DEFAULT NULL,
  `valueDouble` double DEFAULT NULL,
  `valueInt` int(11) DEFAULT NULL,
  `valueString` longtext COLLATE utf8_unicode_ci,
  `user_id` bigint(20) DEFAULT NULL,
  `field_id` bigint(20) NOT NULL,
  `reason_id` bigint(20) DEFAULT NULL,
  `results_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_nsj6avfmh039xlgof62dsntsm` (`user_id`),
  KEY `FK_csjia90cf2qb6u4qffbqs3v2g` (`field_id`),
  KEY `FK_g6frdujf2n10t8budepuxnaqv` (`reason_id`),
  KEY `FK_r19io8if0w3gqgno4qi0kakpd` (`results_id`),
  CONSTRAINT `FK_csjia90cf2qb6u4qffbqs3v2g` FOREIGN KEY (`field_id`) REFERENCES `Field` (`id`),
  CONSTRAINT `FK_g6frdujf2n10t8budepuxnaqv` FOREIGN KEY (`reason_id`) REFERENCES `Term` (`id`),
  CONSTRAINT `FK_nsj6avfmh039xlgof62dsntsm` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`),
  CONSTRAINT `FK_r19io8if0w3gqgno4qi0kakpd` FOREIGN KEY (`results_id`) REFERENCES `Results` (`id`)
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
  `valueBoolean` bit(1) DEFAULT NULL,
  `valueDate` datetime DEFAULT NULL,
  `valueDouble` double DEFAULT NULL,
  `valueInt` int(11) DEFAULT NULL,
  `valueString` longtext COLLATE utf8_unicode_ci,
  `user_id` bigint(20) DEFAULT NULL,
  `field_id` bigint(20) DEFAULT NULL,
  `reason_id` bigint(20) DEFAULT NULL,
  `results_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FK_4qc0i8cs2ibc95yqs0s509vpu` (`REV`),
  CONSTRAINT `FK_4qc0i8cs2ibc95yqs0s509vpu` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
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
  KEY `FK_7kbaf22j02tv5ldskpr4f49me` (`form_id`),
  CONSTRAINT `FK_7kbaf22j02tv5ldskpr4f49me` FOREIGN KEY (`form_id`) REFERENCES `Form` (`id`)
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
  KEY `FK_195ep7wtvok6x0q7b7t6guswt` (`REV`),
  CONSTRAINT `FK_195ep7wtvok6x0q7b7t6guswt` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
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
  KEY `FK_ri5trooteunefx9wduik8nl3` (`batches_id`),
  KEY `FK_ae6vyl3s51p61iq3gdfvee1xs` (`results_id`),
  CONSTRAINT `FK_ae6vyl3s51p61iq3gdfvee1xs` FOREIGN KEY (`results_id`) REFERENCES `Results` (`id`),
  CONSTRAINT `FK_ri5trooteunefx9wduik8nl3` FOREIGN KEY (`batches_id`) REFERENCES `Batch` (`id`)
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
  CONSTRAINT `FK_8mo9eyyyg3dtur8ujbdi0ucwx` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
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
  KEY `FK_hv9i5l4cib95a8jn4j1nf46i1` (`user_id`),
  CONSTRAINT `FK_hv9i5l4cib95a8jn4j1nf46i1` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
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
  `lastModified` datetime DEFAULT NULL,
  `category` int(11) DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FK_r3fg9eir496ev5ddhvr0vxnlx` (`REV`),
  CONSTRAINT `FK_r3fg9eir496ev5ddhvr0vxnlx` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
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
  UNIQUE KEY `UK_e6gkqunxajvyxl5uctpl2vl2p` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (1,'2014-05-28 00:31:32','myself@testshib.org','Me Myself','And I','Member;Staff');
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
  KEY `FK_97pph94d4cb7qah5aygmmll2y` (`REV`),
  CONSTRAINT `FK_97pph94d4cb7qah5aygmmll2y` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User_AUD`
--

LOCK TABLES `User_AUD` WRITE;
/*!40000 ALTER TABLE `User_AUD` DISABLE KEYS */;
INSERT INTO `User_AUD` VALUES (1,1,0,'2014-05-28 00:31:32','myself@testshib.org','Me Myself','And I','Member;Staff');
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

-- Dump completed on 2014-05-27 18:24:20
