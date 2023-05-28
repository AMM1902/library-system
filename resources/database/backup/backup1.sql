-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: library
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `library`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `library` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `library`;

--
-- Table structure for table `authors`
--

DROP TABLE IF EXISTS `authors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authors` (
  `author_id` int NOT NULL AUTO_INCREMENT,
  `author_name` varchar(30) NOT NULL,
  `author_email` varchar(50) NOT NULL,
  `author_phone` varchar(20) NOT NULL,
  PRIMARY KEY (`author_id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authors`
--

LOCK TABLES `authors` WRITE;
/*!40000 ALTER TABLE `authors` DISABLE KEYS */;
INSERT INTO `authors` VALUES (1,'J.K. Rowling','jkrowling@gmail.com','09876543210'),(2,'Stephen King','stephenking@gmail.com','01234567890'),(3,'Agatha Christie','agathachristie@gmail.com','02345678901'),(4,'George Orwell','georgeorwell@gmail.com','03456789012'),(5,'Jane Austen','janeausten@gmail.com','04567890123'),(6,'Charles Dickens','charlesdickens@gmail.com','05678901234'),(7,'Ernest Hemingway','ernesthemingway@gmail.com','06789012345'),(8,'Mark Twain','marktwain@gmail.com','07890123456'),(9,'William Shakespeare','williamshakespeare@gmail.com','08901234567'),(10,'F. Scott Fitzgerald','fscottfitzgerald@gmail.com','09012345678'),(11,'Gabriel Garcia Marquez','gabrielgarciamarquez@gmail.com','00123456789'),(12,'Harper Lee','harperlee@gmail.com','01234567890'),(13,'Leo Tolstoy','leotolstoy@gmail.com','12345678901'),(14,'Toni Morrison','tonimorrison@gmail.com','23456789012'),(15,'John Steinbeck','johnsteinbeck@gmail.com','34567890123'),(16,'Kurt Vonnegut','kurtvonnegut@gmail.com','45678901234'),(17,'Hermann Hesse','hermannhesse@gmail.com','56789012345'),(18,'Margaret Atwood','margaretatwood@gmail.com','67890123456'),(19,'Virginia Woolf','virginiawoolf@gmail.com','78901234567'),(20,'Yann Martel','yannmartel@gmail.com','89012345678'),(21,'Kazuo Ishiguro','kazuoishiguro@gmail.com','90123456789'),(22,'Octavia Butler','octaviabutler@gmail.com','01234567890'),(23,'Ray Bradbury','raybradbury@gmail.com','12345678901'),(24,'Sylvia Plath','sylviaplath@gmail.com','23456789012'),(25,'Ursula K. Le Guin','ursulakleguin@gmail.com','34567890123'),(26,'Albert Camus','albertcamus@gmail.com','45678901234'),(27,'Aldous Huxley','aldoushuxley@gmail.com','56789012345'),(28,'Amy Tan','amytan@gmail.com','67890123456'),(29,'Chimamanda Ngozi Adichie','chimamandangoziadichie@gmail.com','78901234567'),(30,'David Foster Wallace','davidfosterwallace@gmail.com','89012345678'),(31,'Donna Tartt','donnatartt@gmail.com','90123456789'),(32,'Haruki Murakami','harukimurakami@gmail.com','01234567890'),(33,'Jhumpa Lahiri','jhumpalahiri@gmail.com','12345678901'),(34,'Jorge Luis Borges','jorgeluisborges@gmail.com','23456789012'),(35,'Junot Diaz','junotdiaz@gmail.com','34567890147'),(36,'Arundhati Roy','arundhatiroy@gmail.com','45678901234'),(37,'Bret Easton Ellis','breteastonellis@gmail.com','56789012345'),(38,'Charlotte Bronte','charlottebronte@gmail.com','67890123456'),(39,'Cormac McCarthy','cormacmccarthy@gmail.com','78901234567'),(40,'Dante Alighieri','dantealighieri@gmail.com','89012345678'),(41,'Edgar Allan Poe','edgarallanpoe@gmail.com','90123456789'),(42,'Emily Bronte','emilybronte@gmail.com','01234567890'),(43,'Andy Weir','andyweir@gmail.com','93384827387');
/*!40000 ALTER TABLE `authors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `books` (
  `book_id` int NOT NULL AUTO_INCREMENT,
  `book_name` varchar(100) NOT NULL,
  `book_genre` varchar(40) NOT NULL,
  `release_date` date NOT NULL,
  `book_location` int NOT NULL,
  `author_id` int NOT NULL,
  `publisher_id` int NOT NULL,
  `librarian_id` int NOT NULL,
  PRIMARY KEY (`book_id`),
  KEY `author_id` (`author_id`),
  KEY `publisher_id` (`publisher_id`),
  KEY `librarian_id` (`librarian_id`),
  CONSTRAINT `books_ibfk_1` FOREIGN KEY (`author_id`) REFERENCES `authors` (`author_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `books_ibfk_2` FOREIGN KEY (`publisher_id`) REFERENCES `publishers` (`publisher_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `books_ibfk_3` FOREIGN KEY (`librarian_id`) REFERENCES `librarians` (`librarian_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (1,'The Great Gatsby','Classic Literature','1925-05-10',23,1,1,3),(2,'To Kill a Mockingbird','Classic Fiction','1960-07-11',12,2,2,2),(3,'1984','Dystopian Fiction','1949-06-08',7,3,3,4),(4,'The Catcher in the Rye','Fiction','1951-07-15',15,4,4,1),(5,'The Hunger Games','Fiction','2008-09-14',34,5,5,2),(6,'The Da Vinci Code','Mystery Thriller','2003-03-18',19,6,6,5),(7,'Harry Potter and the Philosopher\'s Stone','Fantasy Fiction','1997-06-26',8,7,7,1),(8,'The Lord of the Rings','Fantasy Fiction','1954-07-29',44,8,8,3),(9,'The Girl with the Dragon Tattoo','Crime Thriller','2005-08-19',28,9,9,4),(10,'Pride and Prejudice','Classic Romance','1813-01-28',11,10,10,5),(11,'The Picture of Dorian Gray','Gothic Horror','1890-06-20',42,11,11,2),(12,'The Adventures of Huckleberry Finn','Adventure Fiction','1884-12-10',5,12,12,1),(13,'The Hobbit','Fantasy Fiction','1927-09-21',30,13,13,3),(14,'The Girl on the Train','Thriller','2015-01-13',26,14,14,4),(15,'The Book Thief','Historical Fiction','2005-03-14',16,15,15,2),(16,'The Hitchhiker\'s Guide to the Galaxy','Science Fiction','1979-10-12',41,16,16,5),(17,'The Shining','Horror Fiction','1977-01-28',48,17,17,1),(18,'The Alchemist','Fable','1988-06-01',21,18,18,3),(19,'The Chronicles of Narnia','Fantasy Fiction','1956-01-01',32,19,19,2),(20,'The Silence of the Lambs','Thriller','1988-05-01',14,20,20,4),(21,'The Kite Runner','Historical Fiction','2003-05-29',24,21,21,1),(22,'The Stand','Fiction','1978-09-01',37,22,22,3),(23,'One Hundred Years of Solitude','Magical Realism','1967-06-05',42,23,23,6),(24,'Beloved','Historical Fiction','1987-09-02',48,24,24,6),(25,'The Brothers Karamazov','Philosophical Novel','1880-11-26',2,25,25,3),(26,'The Sound and the Fury','Modernist Novel','1929-10-07',40,26,26,5),(27,'Heart of Darkness','Psychological Fiction','1899-02-10',18,27,27,1),(28,'The Sun Also Rises','Modernist Novel','1926-10-22',27,28,28,2),(29,'Wuthering Heights','Gothic Fiction','1847-12-29',5,29,29,1),(30,'The Grapes of Wrath','Realistic Fiction','1939-04-14',23,30,30,1),(31,'The Color Purple','Epistolary Novel','1982-02-01',36,31,28,4),(32,'Moby-Dick','Adventure Fiction','1851-10-18',3,32,21,4),(33,'The Scarlet Letter','Romanticism','1850-03-16',17,33,27,5),(34,'The Canterbury Tales','Poetry','1387-01-01',42,34,28,6),(35,'The Secret Garden','Post-Apocalyptic Fiction','1996-05-01',14,35,23,6),(36,'Brave New World','Dystopian Fiction','1932-10-27',15,36,25,2),(37,'Gone with the Wind','Historical Fiction','1936-06-30',49,37,28,3),(38,'The Name of the Wind','Fantasy Fiction','2007-03-27',7,38,21,7),(39,'Slaughterhouse-Five','Science Fiction','1969-03-31',33,39,17,7),(40,'The Fault in Our Stars','Fiction','2012-01-10',20,40,30,2),(41,'The Road','Fiction','2006-09-26',5,41,29,2),(42,'The Power of Now','Self-Help','2010-08-01',35,42,19,7),(43,'The Martian','Science Fiction','2014-02-11',21,43,30,1);
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `borrowed_books`
--

DROP TABLE IF EXISTS `borrowed_books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `borrowed_books` (
  `issue_id` int NOT NULL AUTO_INCREMENT,
  `book_id` int NOT NULL,
  `member_id` int NOT NULL,
  `issuer_id` int NOT NULL,
  `issued_date` date NOT NULL,
  `std_return_date` date GENERATED ALWAYS AS (cast((`issued_date` + interval 2 week) as date)) VIRTUAL,
  `collector_id` int DEFAULT NULL,
  `return_date` date DEFAULT NULL,
  `fine_mmk` int DEFAULT '0',
  PRIMARY KEY (`issue_id`),
  KEY `book_id` (`book_id`),
  KEY `member_id` (`member_id`),
  KEY `issuer_id` (`issuer_id`),
  KEY `collector_id` (`collector_id`),
  CONSTRAINT `borrowed_books_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `books` (`book_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `borrowed_books_ibfk_2` FOREIGN KEY (`member_id`) REFERENCES `members` (`member_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `borrowed_books_ibfk_3` FOREIGN KEY (`issuer_id`) REFERENCES `librarians` (`librarian_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `borrowed_books_ibfk_4` FOREIGN KEY (`collector_id`) REFERENCES `librarians` (`librarian_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `borrowed_books`
--

LOCK TABLES `borrowed_books` WRITE;
/*!40000 ALTER TABLE `borrowed_books` DISABLE KEYS */;
INSERT INTO `borrowed_books` (`issue_id`, `book_id`, `member_id`, `issuer_id`, `issued_date`, `collector_id`, `return_date`, `fine_mmk`) VALUES (1,32,12,1,'2023-04-01',3,'2023-04-10',0),(2,28,2,3,'2023-04-01',2,'2023-04-12',0),(3,2,1,1,'2023-04-01',2,'2023-04-11',0),(4,4,11,2,'2023-04-02',3,'2023-04-16',0),(5,7,9,2,'2023-04-03',1,'2023-04-19',1000),(6,3,17,4,'2023-04-04',3,'2023-04-16',0),(7,11,2,1,'2023-04-05',4,'2023-04-19',0),(8,1,3,2,'2023-04-05',1,'2023-04-27',8000),(9,5,5,4,'2023-04-06',3,'2023-04-20',0),(10,6,6,5,'2023-04-07',6,'2023-04-20',0),(11,8,7,3,'2023-04-07',4,'2023-04-20',0),(12,16,13,2,'2023-04-08',3,'2023-04-22',0),(13,22,53,4,'2023-04-08',5,'2023-04-23',500),(14,14,55,1,'2023-04-08',2,'2023-04-22',0),(15,20,21,4,'2023-04-09',1,'2023-04-21',0),(16,18,39,2,'2023-04-10',4,'2023-04-22',0),(17,17,23,4,'2023-04-10',2,'2023-04-23',0),(18,43,26,3,'2023-04-11',2,'2023-04-24',0),(19,15,26,2,'2023-04-12',5,'2023-04-28',1000),(20,36,64,1,'2023-04-13',6,'2023-04-20',0),(21,30,42,6,'2023-04-13',2,'2023-04-25',0),(22,26,24,6,'2023-04-14',1,'2023-04-27',0),(23,25,35,7,'2023-04-15',5,'2023-05-03',2000),(24,21,65,4,'2023-04-15',7,'2023-04-26',0),(25,12,26,6,'2023-04-15',4,'2023-04-30',500),(26,9,31,7,'2023-04-16',5,'2023-04-30',0),(27,38,57,1,'2023-04-16',2,'2023-04-30',0),(28,41,8,3,'2023-04-17',1,'2023-04-30',0),(29,11,3,5,'2023-04-17',4,'2023-04-29',0),(30,19,45,3,'2023-04-18',6,'2023-05-01',0),(31,33,32,2,'2023-04-19',5,'2023-05-01',0),(32,27,11,7,'2023-04-21',7,'2023-05-02',0),(33,20,16,5,'2023-04-23',3,'2023-05-03',0),(34,13,2,6,'2023-04-26',NULL,NULL,0),(35,16,6,4,'2023-04-27',NULL,NULL,0),(36,4,9,2,'2023-04-30',NULL,NULL,0),(37,8,19,1,'2023-04-30',NULL,NULL,0),(38,6,24,5,'2023-05-01',NULL,NULL,0),(39,7,64,6,'2023-05-01',NULL,NULL,0),(40,2,22,7,'2023-05-03',NULL,NULL,0),(41,35,62,4,'2023-05-04',NULL,NULL,0),(42,26,70,1,'2023-05-05',NULL,NULL,0),(43,15,23,3,'2023-05-07',NULL,NULL,0),(44,39,43,4,'2023-05-08',NULL,NULL,0),(45,32,55,2,'2023-05-08',NULL,NULL,0),(46,17,13,3,'2023-05-09',NULL,NULL,0),(47,24,28,6,'2023-04-09',NULL,NULL,0);
/*!40000 ALTER TABLE `borrowed_books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `librarians`
--

DROP TABLE IF EXISTS `librarians`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `librarians` (
  `librarian_id` int NOT NULL AUTO_INCREMENT,
  `librarian_name` varchar(30) NOT NULL,
  `librarian_password` varchar(20) NOT NULL,
  `librarian_email` varchar(50) NOT NULL,
  `librarian_phone` varchar(20) NOT NULL,
  `librarian_address` varchar(100) NOT NULL,
  PRIMARY KEY (`librarian_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `librarians`
--

LOCK TABLES `librarians` WRITE;
/*!40000 ALTER TABLE `librarians` DISABLE KEYS */;
INSERT INTO `librarians` VALUES (1,'Aung Ko','password123','aungko@gmail.com','09987654321','Bogyoke Road, Yangon'),(2,'Su Su','1234abcd','susu@gmail.com','09123456789','Anawrahta Road, Mandalay'),(3,'Tun Tun','tuntun123','tuntun@gmail.com','09765432109','Bo Aung Kyaw Road, Yangon'),(4,'May Myat','maymyat456','maymyat@gmail.com','09234567890','Phaung Taw Oo Street, Naypyidaw'),(5,'Thet Hnin','thet1234','thethnin@gmail.com','09567890123','Lanmadaw Road, Yangon'),(6,'Aung Aung','11111111','aungaung@gmail.com','09773328282','Strand Road, Pathein'),(7,'Ko Latt','latt4567','kolatt@gmail.com','09345678901','Shwe Dagon Pagoda Road, Yangon');
/*!40000 ALTER TABLE `librarians` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `members`
--

DROP TABLE IF EXISTS `members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `members` (
  `member_id` int NOT NULL AUTO_INCREMENT,
  `member_name` varchar(30) NOT NULL,
  `member_password` varchar(20) NOT NULL,
  `member_email` varchar(50) NOT NULL,
  `member_phone` varchar(20) NOT NULL,
  `member_address` varchar(100) NOT NULL,
  PRIMARY KEY (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `members`
--

LOCK TABLES `members` WRITE;
/*!40000 ALTER TABLE `members` DISABLE KEYS */;
INSERT INTO `members` VALUES (1,'Hla Hla','abc123456','hlahla@gmail.com','09123456789','Pyay Road, Yangon'),(2,'Tun Tun','def123456','tuntun@gmail.com','09234567890','Baho Road, Mandalay'),(3,'Yin Yin','ghi123456','yinyin@gmail.com','09345678901','Anawrahta Road, Bagan'),(4,'Min Min','jkl123456','minmin@gmail.com','09456789012','Kaba Aye Pagoda Road, Yangon'),(5,'Zaw Zaw','mno123456','zawzaw@gmail.com','09567890123','Bogyoke Aung San Road, Mandalay'),(6,'Kyaw Kyaw','pqr123456','kyawkyaw@gmail.com','09678901234','Anawrahta Road, Yangon'),(7,'Su Su','stu123456','susu@gmail.com','09789012345','Dhammazedi Road, Yangon'),(8,'Htet Htet','vwx123456','htethtet@gmail.com','09890123456','Bo Aung Kyaw Street, Yangon'),(9,'Myo Myo','yz01abcdef','myomyo@gmail.com','09901234567','Kyaik Ka San Pagoda Road, Yangon'),(10,'Tin Tin','ghijk12345','tintin@gmail.com','09112345678','Pyin Oo Lwin Road, Mandalay'),(11,'Thant Thant','lmnop12345','thantthant@gmail.com','09223456789','Anawrahta Road, Bagan'),(12,'Mya Mya','qrstuv12345','myamya@gmail.com','09334567890','University Avenue Road, Yangon'),(13,'Phyu Phyu','wxyz12345','phyuphyu@gmail.com','09445678901','Inya Road, Yangon'),(14,'Win Win','abcde12345','winwin@gmail.com','09556789012','Mahabandoola Road, Yangon'),(15,'Khin Khin','fghij12345','khinkhin@gmail.com','09667890123','Yankin Road, Yangon'),(16,'Nay Nay','klmno12345','naynay@gmail.com','09778901234','Sule Pagoda Road, Yangon'),(17,'Aye Aye','pqrst12345','ayeaye@gmail.com','09889012345','Bogyoke Aung San Road, Yangon'),(18,'Thet Thet','uvwxy12345','thetthet@gmail.com','09990123456','Myanmar Plaza, Yangon'),(19,'Myat Myat','z01abcde123','myatmyat@gmail.com','09101234567','Kandawgyi Lake, Yangon'),(20,'Hnin Hnin','fghijk67890','hninhnin@gmail.com','09212345678','Shwedagon Pagoda Road, Yangon'),(21,'Ei Ei','lmnop67890','eiei@gmail.com','09323456789','Bagan Kyi Road, Bago'),(22,'Khin Hnin Wai','password1','khinhninwai@gmail.com','09789012345','Shwegondine, Yangon'),(23,'Min Thet Naing','password2','minthetnaing@gmail.com','09789023456','Mawlamyine, Mon State'),(24,'Hnin Ei Phyu','password3','hnineiphyu@gmail.com','09789034567','Pyin Oo Lwin, Mandalay Region'),(25,'Thet Wai Tun','password4','thetwaitun@gmail.com','09789045678','Taunggyi, Shan State'),(26,'Wai Yan Myo','password5','waiyanmyo@gmail.com','09789056789','Myitkyina, Kachin State'),(27,'Naw Kham','password6','nawkham@gmail.com','09789067890','Myeik, Tanintharyi Region'),(28,'Su Myat Mon','password7','sumyatmon@gmail.com','09789078901','Sittwe, Rakhine State'),(29,'Aung Ye Htike','password8','aungyektike@gmail.com','09789089012','Loikaw, Kayah State'),(30,'Khin Sandar Tun','password9','khinsandartun@gmail.com','09789090123','Lashio, Shan State'),(31,'Thaw Zin Aung','password10','thawzinaung@gmail.com','09789101234','Myawaddy, Kayin State'),(32,'Zin Mar Win','password11','zinmarwin@gmail.com','09789112345','Dawei, Tanintharyi Region'),(33,'Nyein Chan Htet','password12','nyeinchanhtet@gmail.com','09789123456','Kyaikhto, Mon State'),(34,'Khant Thu Linn','password13','khantthulinn@gmail.com','09789134567','Myeik, Tanintharyi Region'),(35,'Thet Naing Soe','password14','thetnaingsoe@gmail.com','09789145678','Taunggyi, Shan State'),(36,'Khin San Htwe','password15','khinsanhtwe@gmail.com','09789156789','Hpa-An, Kayin State'),(37,'Htet Wai Yan','password16','htetwaiyan@gmail.com','09789167890','Loikaw, Kayah State'),(38,'Hnin Oo Wai','password17','hninoowai@gmail.com','09789178901','Shwebo, Sagaing Region'),(39,'Thant Sin Aung','password18','thantsinaung@gmail.com','09789189012','Pyay, Bago Region'),(40,'Win Ko Ko','password19','winkoko@gmail.com','09789190123','Myitkyina, Kachin State'),(41,'Yamin Tun','password20','yamintun@gmail.com','09789201234','Magway, Magway Region'),(42,'Thet Htar Swe','password21','thethtarswe@gmail.com','09789212345','Monywa, Sagaing Region'),(43,'Yi Yi Mon','password22','yiyimon@gmail.com','9825553334','Merchant Road, Pathein'),(44,'Ei Phyu','qweasdzxc123','eiphyu@gmail.com','09457896521','83rd St, Mandalay'),(45,'Htet Wai','password123','htetwai@gmail.com','09784523156','Bogyoke St, Yangon'),(46,'Thant Zin','thantzin123','thantzin@gmail.com','09459687421','Baho Rd, Mandalay'),(47,'Aye Chan','ayechan123','ayechan@gmail.com','09587459621','Sule Pagoda Rd, Yangon'),(48,'Hla Myo','hlamyo123','hlamyo@gmail.com','09781234569','34th St, Yangon'),(49,'Nyein Chan','nyeinchan123','nyeinchan@gmail.com','09784512365','80th St, Mandalay'),(50,'Ye Naing','yenaing123','yenaing@gmail.com','09587459621','Baho Rd, Yangon'),(51,'Khaing Khaing','khaingkhaing123','khaingkhaing@gmail.com','09784569875','Maha Bandoola Rd, Yangon'),(52,'Min Thu','minthu123','minthu@gmail.com','09458632145','78th St, Mandalay'),(53,'Htet Wai Yan','htetwaiyan123','htetwaiyan@gmail.com','09451236547','Naypyidaw'),(54,'Myat Thura','myatthura123','myatthura@gmail.com','09784563218','54th St, Mandalay'),(55,'Thura Aung','thuraaung123','thuraaung@gmail.com','09458632541','Pyin Oo Lwin'),(56,'Soe Pyae','soepyae123','soepyae@gmail.com','09587456932','Bago'),(57,'Myat Thu','myatthu123','myatthu@gmail.com','09784563218','22nd St, Mandalay'),(58,'Khin Thuzar','khinthuzar123','khinthuzar@gmail.com','09458632145','Kawthaung'),(59,'Thuzar Kyaw','thuzarkyaw123','thuzarkyaw@gmail.com','09784569875','25th St, Mandalay'),(60,'Nyein Nyein','nyeinnyein123','nyeinnyein@gmail.com','09458632541','Loikaw'),(61,'Aung Min','aungmin123','aungmin@gmail.com','09587456932','Naypyidaw'),(62,'Myo Win','myowin123','myowin@gmail.com','09784563218','Yangon'),(63,'Nan Su','nansu123','nansu@gmail.com','09458632145','Bagan'),(64,'Kaung Set','kaungset123','kaungset@gmail.com','09784569875','48th St, Mandalay'),(65,'Yin Min','yinmin123','yinmin@gmail.com','09458632541','Hpa-an'),(66,'Khin Khin','khinkhin123','khinkhin@gmail.com','09587456932','Mahar Road, Pa City'),(67,'Zaw Zaw','Pass1234','zawzaw@gmail.com','09123456789','Bogyoke Road, Yangon'),(68,'Mya Mya','Password123','myamya@gmail.com','09234567890','Sanchaung, Yangon'),(69,'Htet Htet','Htet1234','htethtet@gmail.com','09345678901','Mawtin, Yangon'),(70,'Aung Aung','AungAung123','aungaung@gmail.com','09456789012','Dagon Myothit, Yangon'),(71,'Khin Khin','KhinKhin123','khinkhin@gmail.com','09567890123','Hledan, Yangon');
/*!40000 ALTER TABLE `members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publishers`
--

DROP TABLE IF EXISTS `publishers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `publishers` (
  `publisher_id` int NOT NULL AUTO_INCREMENT,
  `publisher_name` varchar(30) NOT NULL,
  `publisher_email` varchar(50) NOT NULL,
  `publisher_phone` varchar(20) NOT NULL,
  PRIMARY KEY (`publisher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publishers`
--

LOCK TABLES `publishers` WRITE;
/*!40000 ALTER TABLE `publishers` DISABLE KEYS */;
INSERT INTO `publishers` VALUES (1,'Bloomsbury','bloomsbury@gmail.com','09876543210'),(2,'Hodder & Stoughton','hodderstoughton@gmail.com','01234567890'),(3,'HarperCollins','harpercollins@gmail.com','02345678901'),(4,'Penguin Random House','penguinrandomhouse@gmail.com','03456789012'),(5,'Vintage','vintage@gmail.com','04567890123'),(6,'Knopf Doubleday','knopfdoubleday@gmail.com','05678901234'),(7,'Simon & Schuster','simonschuster@gmail.com','06789012345'),(8,'Hachette Livre','hachettelivre@gmail.com','07890123456'),(9,'Scholastic','scholastic@gmail.com','08901234567'),(10,'Macmillan Publishers','macmillanpublishers@gmail.com','09012345678'),(11,'Random House','randomhouse@gmail.com','00123456789'),(12,'Little, Brown and Company','littlebrownandcompany@gmail.com','01234567890'),(13,'Faber and Faber','faberandfaber@gmail.com','12345678901'),(14,'Harvill Secker','harvillsecker@gmail.com','23456789012'),(15,'Vintage Classics','vintageclassics@gmail.com','34567890123'),(16,'William Morrow','williammorrow@gmail.com','45678901234'),(17,'Riverhead Books','riverheadbooks@gmail.com','56789012345'),(18,'Grove Press','grovepress@gmail.com','67890123456'),(19,'Canongate Books','canongatebooks@gmail.com','78901234567'),(20,'New Directions Publishing','newdirectionspublishing@gmail.com','89012345678'),(21,'Bantam Books','bantambooks@gmail.com','90123456789'),(22,'Sceptre','sceptre@gmail.com','01234567890'),(23,'Hogarth Press','hogarthpress@gmail.com','12345678901'),(24,'Fourth Estate','fourthestate@gmail.com','23456789012'),(25,'G.P. Putnam\'s Sons','gpputnamssons@gmail.com','34567890123'),(26,'Picador','picador@gmail.com','45678901234'),(27,'Alfred A. Knopf','alfredaknopf@gmail.com','56789012345'),(28,'W. W. Norton & Company','wwnortoncompany@gmail.com','67890123456'),(29,'Anchor Books','anchorbooks@gmail.com','78901234567'),(30,'Crown','crowngroup@gmail.com','28372948439');
/*!40000 ALTER TABLE `publishers` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-17 16:28:05
