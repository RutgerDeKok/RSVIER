-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: boer_piets_kaas_relaas
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `bestellingen`
--

DROP TABLE IF EXISTS `bestellingen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bestellingen` (
  `bestelling_id` int(11) NOT NULL,
  `datum bestelling` date DEFAULT NULL,
  `klant_id` int(11) NOT NULL,
  `product_A_id` int(5) NOT NULL,
  `product_A_naam` varchar(45) NOT NULL,
  `product_A_aantal` int(5) NOT NULL,
  `product_B_id` int(5) DEFAULT NULL,
  `product_B_naam` varchar(45) DEFAULT NULL,
  `product_B_aantal` int(5) DEFAULT NULL,
  `product_C_id` int(5) DEFAULT NULL,
  `product_C_naam` varchar(45) DEFAULT NULL,
  `product_C_aantal` int(5) DEFAULT NULL,
  `totaal bedrag best` float NOT NULL,
  `bestelling_comment` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`bestelling_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bestellingen`
--

LOCK TABLES `bestellingen` WRITE;
/*!40000 ALTER TABLE `bestellingen` DISABLE KEYS */;
/*!40000 ALTER TABLE `bestellingen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `klanten`
--

DROP TABLE IF EXISTS `klanten`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `klanten` (
  `klant_id` int(11) NOT NULL AUTO_INCREMENT,
  `klant_voornaam` varchar(45) NOT NULL,
  `klant_achternaam` varchar(45) NOT NULL,
  `klant_straat` varchar(45) DEFAULT NULL,
  `klant_huisnummer` int(5) DEFAULT NULL,
  `klant_hn_toevoeging` varchar(45) DEFAULT NULL,
  `klant_postcode` varchar(45) DEFAULT NULL,
  `klant_woonplaats` varchar(45) DEFAULT NULL,
  `klant_telefoonnr` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`klant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `klanten`
--

LOCK TABLES `klanten` WRITE;
/*!40000 ALTER TABLE `klanten` DISABLE KEYS */;
/*!40000 ALTER TABLE `klanten` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medewerkers`
--

DROP TABLE IF EXISTS `medewerkers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medewerkers` (
  `medew_id` int(5) NOT NULL,
  `medew_voornaam` varchar(45) NOT NULL,
  `medew_achternaam` varchar(45) NOT NULL,
  `medew_functie` varchar(45) DEFAULT NULL,
  `medew_straat` varchar(45) NOT NULL,
  `medew_huisnummer` int(5) NOT NULL,
  `medew_hn_toevoeging` varchar(45) DEFAULT NULL,
  `medewt_postcode` varchar(45) NOT NULL,
  `medew_woonplaats` varchar(45) NOT NULL,
  `medew_telefoonnr` varchar(45) NOT NULL,
  `medew_toegang` varchar(45) NOT NULL,
  PRIMARY KEY (`medew_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medewerkers`
--

LOCK TABLES `medewerkers` WRITE;
/*!40000 ALTER TABLE `medewerkers` DISABLE KEYS */;
INSERT INTO `medewerkers` VALUES (1,'Boer','Piet','Baas','Melkweg',10,NULL,'1450AA','Kaasdam','012-456789','administrator'),(2,'Rutger','Kok, de','Proever','Dorpstraat',1,'bis','1449AB','Stremsel','06-34567890','basic');
/*!40000 ALTER TABLE `medewerkers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producten`
--

DROP TABLE IF EXISTS `producten`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `producten` (
  `product_id` int(11) NOT NULL,
  `product_naam` varchar(45) NOT NULL,
  `product_prijs` float NOT NULL,
  `product_op_voorraad` int(11) NOT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producten`
--

LOCK TABLES `producten` WRITE;
/*!40000 ALTER TABLE `producten` DISABLE KEYS */;
/*!40000 ALTER TABLE `producten` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-12-19  1:36:16
