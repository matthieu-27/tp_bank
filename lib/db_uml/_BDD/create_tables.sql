-- --------------------------------------------------------
-- Hôte:                         127.0.0.1
-- Version du serveur:           12.1.2-MariaDB - MariaDB Server
-- SE du serveur:                Win64
-- HeidiSQL Version:             12.11.0.7065
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Listage de la structure de la base pour tp_bank
CREATE DATABASE IF NOT EXISTS `tp_bank` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin */;
USE `tp_bank`;

-- Listage de la structure de table tp_bank. account
CREATE TABLE IF NOT EXISTS `account` (
  `number` varchar(50) NOT NULL,
  `balance` decimal(15,2) DEFAULT NULL,
  `bank_id` int(11) NOT NULL,
  PRIMARY KEY (`number`),
  KEY `bank_id` (`bank_id`),
  CONSTRAINT `1` FOREIGN KEY (`bank_id`) REFERENCES `bank` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- Les données exportées n'étaient pas sélectionnées.

-- Listage de la structure de table tp_bank. bank
CREATE TABLE IF NOT EXISTS `bank` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- Les données exportées n'étaient pas sélectionnées.

-- Listage de la structure de table tp_bank. client
CREATE TABLE IF NOT EXISTS `client` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `address` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- Les données exportées n'étaient pas sélectionnées.

-- Listage de la structure de table tp_bank. client_accounts
CREATE TABLE IF NOT EXISTS `client_accounts` (
  `client_id` int(11) NOT NULL,
  `account_number` varchar(50) NOT NULL,
  PRIMARY KEY (`client_id`,`account_number`),
  KEY `account_number` (`account_number`),
  CONSTRAINT `1` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`),
  CONSTRAINT `2` FOREIGN KEY (`account_number`) REFERENCES `account` (`number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- Les données exportées n'étaient pas sélectionnées.

-- Listage de la structure de table tp_bank. limited_account
CREATE TABLE IF NOT EXISTS `limited_account` (
  `id` varchar(50) NOT NULL AUTO_INCREMENT,
  `max_limit` decimal(14,2) NOT NULL,
  `account_number` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `account_number` (`account_number`),
  CONSTRAINT `1` FOREIGN KEY (`account_number`) REFERENCES `account` (`number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- Les données exportées n'étaient pas sélectionnées.

-- Listage de la structure de table tp_bank. transaction
CREATE TABLE IF NOT EXISTS `transaction` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `datetime` datetime NOT NULL,
  `amount` decimal(14,2) NOT NULL,
  `source_account` varchar(50) NOT NULL,
  `destination_account` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `source_account` (`source_account`),
  KEY `destination_account` (`destination_account`),
  CONSTRAINT `1` FOREIGN KEY (`source_account`) REFERENCES `account` (`number`),
  CONSTRAINT `2` FOREIGN KEY (`destination_account`) REFERENCES `account` (`number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- Les données exportées n'étaient pas sélectionnées.

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
