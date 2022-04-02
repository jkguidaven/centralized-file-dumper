-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jun 26, 2014 at 10:04 AM
-- Server version: 5.5.32
-- PHP Version: 5.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `primerem_filesys_db`
--
CREATE DATABASE IF NOT EXISTS `primerem_filesys_db` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `primerem_filesys_db`;

-- --------------------------------------------------------

--
-- Table structure for table `download_reference_table`
--

CREATE TABLE IF NOT EXISTS `download_reference_table` (
  `DownloadID` varchar(10) NOT NULL,
  `UploadID` bigint(11) NOT NULL,
  `DownloadTS` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `OnSync` tinyint(1) NOT NULL DEFAULT '0',
  KEY `DownloadID` (`DownloadID`),
  KEY `UploadID` (`UploadID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `file_blob_table`
--

CREATE TABLE IF NOT EXISTS `file_blob_table` (
  `BlobID` bigint(20) NOT NULL AUTO_INCREMENT,
  `BlobData` longblob NOT NULL,
  PRIMARY KEY (`BlobID`),
  KEY `blobID` (`BlobID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `file_server_blob_table`
--

CREATE TABLE IF NOT EXISTS `file_server_blob_table` (
  `BlobID` bigint(20) NOT NULL AUTO_INCREMENT,
  `BlobData` longblob NOT NULL,
  PRIMARY KEY (`BlobID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `file_server_upload_tabe`
--

CREATE TABLE IF NOT EXISTS `file_server_upload_tabe` (
  `UploadID` bigint(20) NOT NULL AUTO_INCREMENT,
  `BlobID` bigint(20) NOT NULL,
  `BranchCode` varchar(10) NOT NULL,
  `UploadDesc` varchar(100) NOT NULL,
  `UploadSize` bigint(20) NOT NULL,
  `UploadTS` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UploadGroup` varchar(50) NOT NULL,
  `UploadMIME` varchar(30) NOT NULL,
  PRIMARY KEY (`UploadID`),
  KEY `BlobID` (`BlobID`),
  KEY `UploadGroup` (`UploadGroup`),
  KEY `UploadGroup_2` (`UploadGroup`),
  KEY `BranchCode` (`BranchCode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `file_upload_table`
--

CREATE TABLE IF NOT EXISTS `file_upload_table` (
  `UploadID` bigint(20) NOT NULL AUTO_INCREMENT,
  `BlobID` bigint(20) NOT NULL,
  `UploadDesc` varchar(100) NOT NULL,
  `UploadSize` bigint(20) NOT NULL,
  `UploadTS` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `BranchCode` varchar(10) NOT NULL,
  `UploadMIME` varchar(30) NOT NULL,
  PRIMARY KEY (`UploadID`),
  KEY `BlobID` (`BlobID`),
  KEY `BranchCode` (`BranchCode`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `server_reference_table`
--

CREATE TABLE IF NOT EXISTS `server_reference_table` (
  `LatestFileServerGroup` varchar(50) NOT NULL,
  KEY `LatestFileServerGroup` (`LatestFileServerGroup`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `download_reference_table`
--
ALTER TABLE `download_reference_table`
  ADD CONSTRAINT `download_reference_table_ibfk_2` FOREIGN KEY (`DownloadID`) REFERENCES `primerem_branch_db`.`branch_table` (`BranchCode`),
  ADD CONSTRAINT `download_reference_table_ibfk_1` FOREIGN KEY (`UploadID`) REFERENCES `file_upload_table` (`UploadID`);

--
-- Constraints for table `file_server_upload_tabe`
--
ALTER TABLE `file_server_upload_tabe`
  ADD CONSTRAINT `file_server_upload_tabe_ibfk_2` FOREIGN KEY (`BranchCode`) REFERENCES `primerem_branch_db`.`branch_table` (`BranchCode`),
  ADD CONSTRAINT `file_server_upload_tabe_ibfk_1` FOREIGN KEY (`BlobID`) REFERENCES `file_server_blob_table` (`BlobID`);

--
-- Constraints for table `file_upload_table`
--
ALTER TABLE `file_upload_table`
  ADD CONSTRAINT `file_upload_table_ibfk_2` FOREIGN KEY (`BranchCode`) REFERENCES `primerem_branch_db`.`branch_table` (`BranchCode`),
  ADD CONSTRAINT `file_upload_table_ibfk_1` FOREIGN KEY (`BlobID`) REFERENCES `file_blob_table` (`blobID`);

--
-- Constraints for table `server_reference_table`
--
ALTER TABLE `server_reference_table`
  ADD CONSTRAINT `server_reference_table_ibfk_1` FOREIGN KEY (`LatestFileServerGroup`) REFERENCES `file_server_upload_tabe` (`UploadGroup`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
