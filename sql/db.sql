-- MySQL dump 10.13  Distrib 5.5.31, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: omavalvonta
-- ------------------------------------------------------
-- Server version	5.5.31-0ubuntu0.13.04.1

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
  KEY `FK3CFE71A47140EFE` (`user_id`),
  KEY `FK3CFE71A63509FB6` (`product_id`),
  CONSTRAINT `FK3CFE71A63509FB6` FOREIGN KEY (`product_id`) REFERENCES `Product` (`id`),
  CONSTRAINT `FK3CFE71A47140EFE` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
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
  KEY `FK3D1EF7EBDF74E053` (`REV`),
  CONSTRAINT `FK3D1EF7EBDF74E053` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
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
  PRIMARY KEY (`id`),
  KEY `FK40BB0DAA76B7D34` (`fieldset_id`),
  CONSTRAINT `FK40BB0DAA76B7D34` FOREIGN KEY (`fieldset_id`) REFERENCES `Fieldset` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Field`
--

LOCK TABLES `Field` WRITE;
/*!40000 ALTER TABLE `Field` DISABLE KEYS */;
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
  PRIMARY KEY (`id`,`REV`),
  KEY `FKC2DFE1ABDF74E053` (`REV`),
  CONSTRAINT `FKC2DFE1ABDF74E053` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Field_AUD`
--

LOCK TABLES `Field_AUD` WRITE;
/*!40000 ALTER TABLE `Field_AUD` DISABLE KEYS */;
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
  PRIMARY KEY (`id`),
  KEY `FKCC7B254847140EFE` (`user_id`),
  CONSTRAINT `FKCC7B254847140EFE` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Fieldset`
--

LOCK TABLES `Fieldset` WRITE;
/*!40000 ALTER TABLE `Fieldset` DISABLE KEYS */;
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
  PRIMARY KEY (`id`,`REV`),
  KEY `FK24836F19DF74E053` (`REV`),
  CONSTRAINT `FK24836F19DF74E053` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Fieldset_AUD`
--

LOCK TABLES `Fieldset_AUD` WRITE;
/*!40000 ALTER TABLE `Fieldset_AUD` DISABLE KEYS */;
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
  PRIMARY KEY (`id`),
  KEY `FK75A286B947140EFE` (`user_id`),
  KEY `FK75A286B9453CDED6` (`batch_id`),
  KEY `FK75A286B99775DBF8` (`destiny_id`),
  KEY `FK75A286B93EC47346` (`unit_id`),
  CONSTRAINT `FK75A286B93EC47346` FOREIGN KEY (`unit_id`) REFERENCES `Term` (`id`),
  CONSTRAINT `FK75A286B9453CDED6` FOREIGN KEY (`batch_id`) REFERENCES `Batch` (`id`),
  CONSTRAINT `FK75A286B947140EFE` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`),
  CONSTRAINT `FK75A286B99775DBF8` FOREIGN KEY (`destiny_id`) REFERENCES `Term` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
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
  PRIMARY KEY (`id`,`REV`),
  KEY `FK3F4AB00ADF74E053` (`REV`),
  CONSTRAINT `FK3F4AB00ADF74E053` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
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
  PRIMARY KEY (`id`),
  KEY `FK2180E447140EFE` (`user_id`),
  CONSTRAINT `FK2180E447140EFE` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Form`
--

LOCK TABLES `Form` WRITE;
/*!40000 ALTER TABLE `Form` DISABLE KEYS */;
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
  PRIMARY KEY (`id`,`REV`),
  KEY `FK201A9CB5DF74E053` (`REV`),
  CONSTRAINT `FK201A9CB5DF74E053` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Form_AUD`
--

LOCK TABLES `Form_AUD` WRITE;
/*!40000 ALTER TABLE `Form_AUD` DISABLE KEYS */;
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
  KEY `FKB0D7964376FDE331` (`fieldsets_id`),
  KEY `FKB0D79643C3FD4B4` (`Form_id`),
  CONSTRAINT `FKB0D79643C3FD4B4` FOREIGN KEY (`Form_id`) REFERENCES `Form` (`id`),
  CONSTRAINT `FKB0D7964376FDE331` FOREIGN KEY (`fieldsets_id`) REFERENCES `Fieldset` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Form_Fieldset`
--

LOCK TABLES `Form_Fieldset` WRITE;
/*!40000 ALTER TABLE `Form_Fieldset` DISABLE KEYS */;
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
  KEY `FKB2F8EA94DF74E053` (`REV`),
  CONSTRAINT `FKB2F8EA94DF74E053` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Form_Fieldset_AUD`
--

LOCK TABLES `Form_Fieldset_AUD` WRITE;
/*!40000 ALTER TABLE `Form_Fieldset_AUD` DISABLE KEYS */;
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
  PRIMARY KEY (`id`),
  KEY `FK6A844DF147140EFE` (`user_id`),
  KEY `FK6A844DF1C3FD4B4` (`form_id`),
  CONSTRAINT `FK6A844DF1C3FD4B4` FOREIGN KEY (`form_id`) REFERENCES `Form` (`id`),
  CONSTRAINT `FK6A844DF147140EFE` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
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
  KEY `FK185BEA4047140EFE` (`user_id`),
  KEY `FK185BEA40E7459E3E` (`ingredient_id`),
  KEY `FK185BEA40F62A1A78` (`producer_id`),
  KEY `FK185BEA403EC47346` (`unit_id`),
  CONSTRAINT `FK185BEA403EC47346` FOREIGN KEY (`unit_id`) REFERENCES `Term` (`id`),
  CONSTRAINT `FK185BEA4047140EFE` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`),
  CONSTRAINT `FK185BEA40E7459E3E` FOREIGN KEY (`ingredient_id`) REFERENCES `Ingredient` (`id`),
  CONSTRAINT `FK185BEA40F62A1A78` FOREIGN KEY (`producer_id`) REFERENCES `Term` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
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
  KEY `FK5808F811DF74E053` (`REV`),
  CONSTRAINT `FK5808F811DF74E053` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
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
  KEY `FK79BF845B453CDED6` (`batch_id`),
  KEY `FK79BF845BD01BBCFE` (`ingredientSupply_id`),
  CONSTRAINT `FK79BF845BD01BBCFE` FOREIGN KEY (`ingredientSupply_id`) REFERENCES `IngredientSupply` (`id`),
  CONSTRAINT `FK79BF845B453CDED6` FOREIGN KEY (`batch_id`) REFERENCES `Batch` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
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
  KEY `FKCB8C0CACDF74E053` (`REV`),
  CONSTRAINT `FKCB8C0CACDF74E053` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
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
  PRIMARY KEY (`id`,`REV`),
  KEY `FKD3061B42DF74E053` (`REV`),
  CONSTRAINT `FKD3061B42DF74E053` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
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
  `name` varchar(255) NOT NULL,
  `story` longtext NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK24FEF347140EFE` (`user_id`),
  CONSTRAINT `FK24FEF347140EFE` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
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
  PRIMARY KEY (`id`,`REV`),
  KEY `FK57027B44DF74E053` (`REV`),
  CONSTRAINT `FK57027B44DF74E053` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
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
  PRIMARY KEY (`id`),
  KEY `FK50C664CF47140EFE` (`user_id`),
  CONSTRAINT `FK50C664CF47140EFE` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
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
  `description` longtext,
  `name` varchar(255) DEFAULT NULL,
  `no` int(11) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FKA859320DF74E053` (`REV`),
  CONSTRAINT `FKA859320DF74E053` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
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
  KEY `FK462F7E5450EA9969` (`forms_id`),
  KEY `FK462F7E5463509FB6` (`Product_id`),
  CONSTRAINT `FK462F7E5463509FB6` FOREIGN KEY (`Product_id`) REFERENCES `Product` (`id`),
  CONSTRAINT `FK462F7E5450EA9969` FOREIGN KEY (`forms_id`) REFERENCES `Form` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
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
  KEY `FK8B0C6225DF74E053` (`REV`),
  CONSTRAINT `FK8B0C6225DF74E053` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
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
  KEY `FKE01A7761AB4215AD` (`ingredients_id`),
  KEY `FKE01A776163509FB6` (`Product_id`),
  CONSTRAINT `FKE01A776163509FB6` FOREIGN KEY (`Product_id`) REFERENCES `Product` (`id`),
  CONSTRAINT `FKE01A7761AB4215AD` FOREIGN KEY (`ingredients_id`) REFERENCES `Ingredient` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
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
  KEY `FKD58A0CB2DF74E053` (`REV`),
  CONSTRAINT `FKD58A0CB2DF74E053` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `REVINFO`
--

LOCK TABLES `REVINFO` WRITE;
/*!40000 ALTER TABLE `REVINFO` DISABLE KEYS */;
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
  PRIMARY KEY (`id`),
  KEY `FK91B2B43D47140EFE` (`user_id`),
  KEY `FK91B2B43D28177D40` (`field_id`),
  KEY `FK91B2B43D27F20286` (`reason_id`),
  KEY `FK91B2B43DB0897240` (`results_id`),
  CONSTRAINT `FK91B2B43DB0897240` FOREIGN KEY (`results_id`) REFERENCES `Results` (`id`),
  CONSTRAINT `FK91B2B43D27F20286` FOREIGN KEY (`reason_id`) REFERENCES `Term` (`id`),
  CONSTRAINT `FK91B2B43D28177D40` FOREIGN KEY (`field_id`) REFERENCES `Field` (`id`),
  CONSTRAINT `FK91B2B43D47140EFE` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
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
  PRIMARY KEY (`id`,`REV`),
  KEY `FK54C07B8EDF74E053` (`REV`),
  CONSTRAINT `FK54C07B8EDF74E053` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
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
  KEY `FKA4A3D3D6C3FD4B4` (`form_id`),
  KEY `FKA4A3D3D681D6450C` (`form_id`),
  CONSTRAINT `FKA4A3D3D681D6450C` FOREIGN KEY (`form_id`) REFERENCES `Batch` (`id`),
  CONSTRAINT `FKA4A3D3D6C3FD4B4` FOREIGN KEY (`form_id`) REFERENCES `Form` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
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
  KEY `FK447626A7DF74E053` (`REV`),
  CONSTRAINT `FK447626A7DF74E053` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
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
  KEY `FKFF9B65719A90E1E8` (`batches_id`),
  KEY `FKFF9B6571B0897240` (`Results_id`),
  CONSTRAINT `FKFF9B6571B0897240` FOREIGN KEY (`Results_id`) REFERENCES `Results` (`id`),
  CONSTRAINT `FKFF9B65719A90E1E8` FOREIGN KEY (`batches_id`) REFERENCES `Batch` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
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
  KEY `FK4FC572C2DF74E053` (`REV`),
  CONSTRAINT `FK4FC572C2DF74E053` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
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
  KEY `FK96E95B97DF74E053` (`REV`),
  CONSTRAINT `FK96E95B97DF74E053` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
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
  PRIMARY KEY (`id`),
  KEY `FK27B88C47140EFE` (`user_id`),
  CONSTRAINT `FK27B88C47140EFE` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
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
  `name` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FKBD6CC05DDF74E053` (`REV`),
  CONSTRAINT `FKBD6CC05DDF74E053` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
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
  `email` varchar(255) NOT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
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
  PRIMARY KEY (`id`,`REV`),
  KEY `FKF3FCA03CDF74E053` (`REV`),
  CONSTRAINT `FKF3FCA03CDF74E053` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User_AUD`
--

LOCK TABLES `User_AUD` WRITE;
/*!40000 ALTER TABLE `User_AUD` DISABLE KEYS */;
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

-- Dump completed on 2013-05-17  7:07:04
