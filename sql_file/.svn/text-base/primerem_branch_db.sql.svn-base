-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jul 17, 2014 at 07:36 PM
-- Server version: 5.5.32
-- PHP Version: 5.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `primerem_branch_db`
--
CREATE DATABASE IF NOT EXISTS `primerem_branch_db` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `primerem_branch_db`;

-- --------------------------------------------------------

--
-- Table structure for table `branch_table`
--

CREATE TABLE IF NOT EXISTS `branch_table` (
  `BranchCode` varchar(10) NOT NULL,
  `BranchPassCode` varbinary(5) NOT NULL,
  `BranchDesc` varchar(50) NOT NULL,
  `BranchAddr1` varchar(50) DEFAULT NULL,
  `BranchAddr2` varchar(50) DEFAULT NULL,
  `BranchAddr3` varchar(50) DEFAULT NULL,
  `BranchAddr4` varchar(50) DEFAULT NULL,
  `BranchEmail` varchar(100) DEFAULT NULL,
  `BranchContactP` varchar(50) DEFAULT NULL,
  `BranchContact` varchar(50) DEFAULT NULL,
  `BranchIPAddr` varchar(15) DEFAULT NULL,
  `BranchDynamicIP` tinyint(1) NOT NULL DEFAULT '0',
  `Active` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`BranchCode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `branch_table`
--

INSERT INTO `branch_table` (`BranchCode`, `BranchPassCode`, `BranchDesc`, `BranchAddr1`, `BranchAddr2`, `BranchAddr3`, `BranchAddr4`, `BranchEmail`, `BranchContactP`, `BranchContact`, `BranchIPAddr`, `BranchDynamicIP`, `Active`) VALUES
('CN', '12345', 'Colon 1', 'Colon St. ', 'Cebu City', NULL, NULL, NULL, NULL, '(032)-416-8773', NULL, 0, 1),
('LHG', '12345', 'Lahug', 'JY square Lahug ', 'Cebu City', NULL, NULL, NULL, NULL, '(032)-414-7343', NULL, 0, 1);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
