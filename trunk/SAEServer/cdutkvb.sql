-- phpMyAdmin SQL Dump
-- version 2.11.6
-- http://www.phpmyadmin.net
--
-- 主机: localhost
-- 生成日期: 2010 年 08 月 17 日 09:44
-- 服务器版本: 5.1.41
-- PHP 版本: 5.2.12

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- 数据库: `cdutkvb`
--
CREATE DATABASE `cdutkvb` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `cdutkvb`;

-- --------------------------------------------------------

--
-- 表的结构 `crawlstatu`
--

CREATE TABLE IF NOT EXISTS `crawlstatu` (
  `id` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL COMMENT '学号',
  `statu` enum('1','2','3','4') NOT NULL COMMENT '状态代码 1登陆ok 2课表urlok 3课表htmlok 4其他',
  `kbhtml` text NOT NULL COMMENT '课表的html',
  `lstime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '上次操作的时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- 导出表中的数据 `crawlstatu`
--


-- --------------------------------------------------------

--
-- 表的结构 `productlock`
--

CREATE TABLE IF NOT EXISTS `productlock` (
  `id` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `pstring` char(32) NOT NULL COMMENT '产品的id',
  `uid` char(15) NOT NULL COMMENT '用户学号',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- 导出表中的数据 `productlock`
--

INSERT INTO `productlock` (`id`, `pstring`, `uid`) VALUES
(2, '047895516dd3527b5703178ef4e99639', '200805030326');
