-- MySQL dump 10.13  Distrib 5.5.38, for debian-linux-gnu (i686)
--
-- Host: localhost    Database: AutoServe
-- ------------------------------------------------------
-- Server version	5.5.38-0ubuntu0.12.04.1

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
-- Table structure for table `Complain`
--

DROP TABLE IF EXISTS `Complain`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Complain` (
  `idx` int(11) DEFAULT NULL,
  `tabNum` int(11) DEFAULT NULL,
  `content` varchar(512) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Complain`
--

LOCK TABLES `Complain` WRITE;
/*!40000 ALTER TABLE `Complain` DISABLE KEYS */;
/*!40000 ALTER TABLE `Complain` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `OrderList`
--

DROP TABLE IF EXISTS `OrderList`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `OrderList` (
  `menuIdx` int(11) DEFAULT NULL,
  `orderIdx` int(11) DEFAULT NULL,
  `tabNum` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OrderList`
--

LOCK TABLES `OrderList` WRITE;
/*!40000 ALTER TABLE `OrderList` DISABLE KEYS */;
/*!40000 ALTER TABLE `OrderList` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SelectedMenuList`
--

DROP TABLE IF EXISTS `SelectedMenuList`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SelectedMenuList` (
  `menuIdx` int(11) DEFAULT NULL,
  `orderIdx` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SelectedMenuList`
--

LOCK TABLES `SelectedMenuList` WRITE;
/*!40000 ALTER TABLE `SelectedMenuList` DISABLE KEYS */;
/*!40000 ALTER TABLE `SelectedMenuList` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `name` char(30) NOT NULL,
  `age` int(11) DEFAULT NULL,
  `id` char(15) NOT NULL,
  `pass` char(15) NOT NULL,
  `type` char(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES ('Sindhura',30,'admin','1234','M'),('Henry',23,'waiter01','1234','W'),('GyuHyeon',25,'ka01','1234','KA'),('John',30,'temp01','1234','P');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingredient`
--

DROP TABLE IF EXISTS `ingredient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ingredient` (
  `ingName` varchar(20) DEFAULT NULL,
  `quantity` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredient`
--

LOCK TABLES `ingredient` WRITE;
/*!40000 ALTER TABLE `ingredient` DISABLE KEYS */;
INSERT INTO `ingredient` VALUES ('patty',0),('burgerBread',5),('onion',10),('lettuce',30),('tomato',15),('cheese',5),('pizzaBread',5),('pepperoni',10),('lobster',0),('beef',10),('chicken',5);
/*!40000 ALTER TABLE `ingredient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingredientForMenu`
--

DROP TABLE IF EXISTS `ingredientForMenu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ingredientForMenu` (
  `menuName` varchar(20) DEFAULT NULL,
  `ingName` varchar(20) DEFAULT NULL,
  `quantity` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredientForMenu`
--

LOCK TABLES `ingredientForMenu` WRITE;
/*!40000 ALTER TABLE `ingredientForMenu` DISABLE KEYS */;
INSERT INTO `ingredientForMenu` VALUES ('Hamburger','patty',1),('Hamburger','burgerBread',1),('Pizza','cheese',0.5),('Pizza','pepperoni',0.1),('Lobster','lobster',1),('Steak','beef',1),('Chicken','chicken',1);
/*!40000 ALTER TABLE `ingredientForMenu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu` (
  `menuName` varchar(20) DEFAULT NULL,
  `price` double(5,2) DEFAULT NULL,
  `img_ref` varchar(128) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES ('Hamburger',5.00,'../img/hamburger.png'),('Pizza',20.00,'../img/pizza.png'),('Chicken',15.00,'../img/chicken.png'),('Steak',30.00,'../img/stake.png'),('Lobster',50.00,'../img/lobster.png');
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tableInfo`
--

DROP TABLE IF EXISTS `tableInfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tableInfo` (
  `tableNum` int(11) DEFAULT NULL,
  `xCor` int(11) DEFAULT NULL,
  `yCor` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tableInfo`
--

LOCK TABLES `tableInfo` WRITE;
/*!40000 ALTER TABLE `tableInfo` DISABLE KEYS */;
INSERT INTO `tableInfo` VALUES (1,55,55),(2,55,135),(3,55,216),(4,55,297),(5,147,70),(6,219,70),(7,292,70),(8,147,168),(9,220,168),(10,293,168),(11,147,213),(12,220,213),(13,293,213),(14,383,67),(15,383,144),(16,383,220),(17,457,56),(18,457,122),(19,457,189),(20,457,255);
/*!40000 ALTER TABLE `tableInfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trendMenu`
--

DROP TABLE IF EXISTS `trendMenu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trendMenu` (
  `menuName` varchar(20) DEFAULT NULL,
  `rating` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trendMenu`
--

LOCK TABLES `trendMenu` WRITE;
/*!40000 ALTER TABLE `trendMenu` DISABLE KEYS */;
INSERT INTO `trendMenu` VALUES ('Hamburger',100),('Lobster',99);
/*!40000 ALTER TABLE `trendMenu` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-11-07 13:57:54
