-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 22 mai 2025 à 11:28
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `socialmediadb`
--

-- --------------------------------------------------------

--
-- Structure de la table `comments`
--

CREATE TABLE `comments` (
  `id` bigint(20) NOT NULL,
  `content` varchar(255) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `post_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `comments`
--

INSERT INTO `comments` (`id`, `content`, `created_at`, `post_id`, `user_id`) VALUES
(15, 'hi', '2025-05-20 12:11:48.000000', 3, 1),
(17, 's', '2025-05-20 15:36:09.000000', 1, 1),
(19, 'jh', '2025-05-21 12:19:12.000000', 4, 1),
(20, 'Félicitations pour ton projet ! Le design est superbe.', '2025-05-18 10:30:00.000000', 23, 4),
(21, 'C\'est où exactement ? Ça donne vraiment envie !', '2025-05-18 09:15:00.000000', 24, 1),
(22, 'Moi je serais très intéressé par ce tutoriel !', '2025-05-19 15:10:00.000000', 25, 6),
(23, 'J\'ai déjà testé ce restaurant, je confirme c\'est délicieux !', '2025-05-19 21:05:00.000000', 26, 2),
(24, 'Quel écran utilises-tu ? Il a l\'air génial.', '2025-05-20 09:45:00.000000', 27, 3),
(25, 'Ces designs sont magnifiques, où peut-on les trouver ?', '2025-05-20 12:20:00.000000', 28, 7),
(26, 'Bravo pour ta motivation ! Tu utilises quelle application pour suivre tes performances ?', '2025-05-21 08:10:00.000000', 29, 5),
(27, 'J\'ai commencé à l\'utiliser la semaine dernière, c\'est très prometteur !', '2025-05-21 10:45:00.000000', 30, 3),
(28, 'Quel groupe as-tu vu ? Ça avait l\'air incroyable !', '2025-05-21 10:00:00.000000', 31, 4),
(29, 'Superbes photos ! Tu utilises quel appareil ?', '2025-05-21 14:00:00.000000', 32, 2);

-- --------------------------------------------------------

--
-- Structure de la table `hashtags`
--

CREATE TABLE `hashtags` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `hashtags`
--

INSERT INTO `hashtags` (`id`, `name`) VALUES
(1, 'design'),
(7, 'developpement'),
(12, 'fitness'),
(10, 'food'),
(8, 'javascript'),
(11, 'mode'),
(13, 'musique'),
(6, 'photo'),
(9, 'react'),
(3, 'socialmedia'),
(4, 'tech'),
(5, 'voyage'),
(2, 'webdev');

-- --------------------------------------------------------

--
-- Structure de la table `likes`
--

CREATE TABLE `likes` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `comment_id` bigint(20) DEFAULT NULL,
  `post_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `likes`
--

INSERT INTO `likes` (`id`, `created_at`, `comment_id`, `post_id`, `user_id`) VALUES
(11, '2025-05-20 11:48:55.000000', NULL, 3, 1),
(13, '2025-05-20 12:02:55.000000', NULL, 1, 1),
(34, '2025-05-20 15:43:24.000000', 17, NULL, 1),
(35, '2025-05-20 15:43:27.000000', NULL, 2, 1),
(37, '2025-05-21 13:41:55.000000', NULL, 23, 2),
(38, '2025-05-21 13:41:55.000000', NULL, 23, 4),
(39, '2025-05-21 13:41:55.000000', NULL, 23, 5),
(40, '2025-05-21 13:41:55.000000', NULL, 24, 1),
(41, '2025-05-21 13:41:55.000000', NULL, 24, 6),
(42, '2025-05-21 13:41:55.000000', NULL, 25, 2),
(43, '2025-05-21 13:41:55.000000', NULL, 25, 7),
(44, '2025-05-21 13:41:55.000000', NULL, 26, 3),
(45, '2025-05-21 13:41:55.000000', NULL, 26, 5),
(46, '2025-05-21 13:41:55.000000', NULL, 27, 4),
(47, '2025-05-21 13:41:55.000000', NULL, 28, 1),
(48, '2025-05-21 13:41:55.000000', NULL, 29, 6),
(49, '2025-05-21 13:41:55.000000', NULL, 30, 7),
(50, '2025-05-21 13:41:55.000000', NULL, 31, 2),
(51, '2025-05-21 13:41:55.000000', NULL, 32, 3),
(52, '2025-05-21 13:41:55.000000', 20, NULL, 3),
(53, '2025-05-21 13:41:55.000000', 20, NULL, 6),
(54, '2025-05-21 13:41:55.000000', 21, NULL, 2),
(55, '2025-05-21 13:41:55.000000', 22, NULL, 1),
(56, '2025-05-21 13:41:55.000000', 23, NULL, 7),
(57, '2025-05-21 13:41:55.000000', 24, NULL, 6),
(58, '2025-05-21 13:41:55.000000', 25, NULL, 8),
(59, '2025-05-21 13:41:55.000000', 26, NULL, 1),
(60, '2025-05-21 13:41:55.000000', 27, NULL, 5),
(61, '2025-05-21 13:41:55.000000', 29, NULL, 6);

-- --------------------------------------------------------

--
-- Structure de la table `login_records`
--

CREATE TABLE `login_records` (
  `id` bigint(20) NOT NULL,
  `ip_address` varchar(255) DEFAULT NULL,
  `login_time` datetime(6) NOT NULL,
  `user_agent` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `login_records`
--

INSERT INTO `login_records` (`id`, `ip_address`, `login_time`, `user_agent`, `user_id`) VALUES
(26, '0:0:0:0:0:0:0:1', '2025-05-17 11:48:01.000000', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36', 2),
(27, '0:0:0:0:0:0:0:1', '2025-05-17 11:58:54.000000', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36', 2),
(28, '0:0:0:0:0:0:0:1', '2025-05-17 12:12:00.000000', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36', 2),
(29, '0:0:0:0:0:0:0:1', '2025-05-17 22:11:30.000000', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36', 2),
(30, '0:0:0:0:0:0:0:1', '2025-05-18 12:58:09.000000', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36', 2),
(31, '0:0:0:0:0:0:0:1', '2025-05-18 13:09:30.000000', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36', 2),
(32, '0:0:0:0:0:0:0:1', '2025-05-18 13:33:38.000000', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36', 2),
(33, '0:0:0:0:0:0:0:1', '2025-05-18 13:34:26.000000', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36', 2),
(34, '0:0:0:0:0:0:0:1', '2025-05-18 13:55:52.000000', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36', 2),
(35, '0:0:0:0:0:0:0:1', '2025-05-18 13:58:28.000000', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36', 2),
(36, '0:0:0:0:0:0:0:1', '2025-05-18 14:13:20.000000', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36', 2),
(37, '0:0:0:0:0:0:0:1', '2025-05-18 14:18:39.000000', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36', 2),
(38, '0:0:0:0:0:0:0:1', '2025-05-18 14:40:12.000000', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36', 2),
(39, '0:0:0:0:0:0:0:1', '2025-05-18 14:44:40.000000', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36', 2),
(40, '0:0:0:0:0:0:0:1', '2025-05-18 14:47:29.000000', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36', 2),
(41, '0:0:0:0:0:0:0:1', '2025-05-18 14:55:47.000000', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36', 2),
(42, '0:0:0:0:0:0:0:1', '2025-05-18 17:20:19.000000', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36', 2),
(43, '0:0:0:0:0:0:0:1', '2025-05-18 18:03:52.000000', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36', 2),
(44, '0:0:0:0:0:0:0:1', '2025-05-18 18:18:48.000000', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36', 2),
(45, '0:0:0:0:0:0:0:1', '2025-05-18 18:24:50.000000', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36', 2),
(46, '0:0:0:0:0:0:0:1', '2025-05-18 18:30:54.000000', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36', 2),
(47, '0:0:0:0:0:0:0:1', '2025-05-18 18:58:40.000000', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36', 2),
(48, '0:0:0:0:0:0:0:1', '2025-05-18 19:04:30.000000', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36', 2),
(49, '0:0:0:0:0:0:0:1', '2025-05-18 19:09:26.000000', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36', 2),
(50, '0:0:0:0:0:0:0:1', '2025-05-18 19:17:12.000000', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36', 2),
(51, '0:0:0:0:0:0:0:1', '2025-05-19 18:04:24.000000', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36', 2),
(52, '0:0:0:0:0:0:0:1', '2025-05-19 22:23:38.000000', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36', 2),
(53, '0:0:0:0:0:0:0:1', '2025-05-19 22:32:24.000000', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36', 2),
(54, '0:0:0:0:0:0:0:1', '2025-05-19 22:36:05.000000', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36', 2),
(55, '0:0:0:0:0:0:0:1', '2025-05-19 22:37:17.000000', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36', 1),
(56, '0:0:0:0:0:0:0:1', '2025-05-19 22:38:41.000000', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36', 1),
(57, '0:0:0:0:0:0:0:1', '2025-05-19 22:40:06.000000', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36', 2),
(58, '0:0:0:0:0:0:0:1', '2025-05-19 22:44:42.000000', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36', 2),
(59, '0:0:0:0:0:0:0:1', '2025-05-19 22:46:30.000000', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36', 2),
(60, '0:0:0:0:0:0:0:1', '2025-05-19 22:49:35.000000', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36', 2),
(61, '0:0:0:0:0:0:0:1', '2025-05-19 22:52:02.000000', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36', 1),
(62, '0:0:0:0:0:0:0:1', '2025-05-19 22:54:53.000000', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36', 1),
(63, '0:0:0:0:0:0:0:1', '2025-05-19 22:57:38.000000', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36', 1),
(64, '0:0:0:0:0:0:0:1', '2025-05-19 23:01:58.000000', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36', 1),
(65, '0:0:0:0:0:0:0:1', '2025-05-19 23:06:37.000000', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36', 1),
(66, '0:0:0:0:0:0:0:1', '2025-05-19 23:17:57.000000', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36', 1),
(67, '0:0:0:0:0:0:0:1', '2025-05-19 23:20:48.000000', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36', 1),
(68, '0:0:0:0:0:0:0:1', '2025-05-19 23:23:46.000000', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36', 1),
(69, '0:0:0:0:0:0:0:1', '2025-05-19 23:27:37.000000', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36', 2),
(70, '0:0:0:0:0:0:0:1', '2025-05-19 23:33:03.000000', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36', 2),
(71, '0:0:0:0:0:0:0:1', '2025-05-19 23:33:30.000000', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36', 1),
(72, '0:0:0:0:0:0:0:1', '2025-05-19 23:35:48.000000', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36', 1),
(73, '0:0:0:0:0:0:0:1', '2025-05-20 11:29:33.000000', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36', 1),
(74, '0:0:0:0:0:0:0:1', '2025-05-20 14:20:39.000000', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36', 1),
(75, '0:0:0:0:0:0:0:1', '2025-05-20 16:00:40.000000', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36', 1),
(76, '0:0:0:0:0:0:0:1', '2025-05-20 16:25:24.000000', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36', 2),
(77, '0:0:0:0:0:0:0:1', '2025-05-20 16:29:16.000000', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36', 1),
(78, '0:0:0:0:0:0:0:1', '2025-05-21 12:18:38.000000', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36', 1),
(79, '192.168.1.100', '2025-05-20 08:15:00.000000', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36', 5),
(80, '172.16.0.25', '2025-05-20 09:30:00.000000', 'Mozilla/5.0 (iPhone; CPU iPhone OS 17_5 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/17.5 Mobile/15E148 Safari/604.1', 6),
(81, '10.0.0.15', '2025-05-20 10:45:00.000000', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36 Edg/136.0.0.0', 7),
(82, '192.168.0.10', '2025-05-20 12:20:00.000000', 'Mozilla/5.0 (Linux; Android 14; Pixel 7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Mobile Safari/537.36', 8),
(83, '192.168.1.100', '2025-05-21 07:30:00.000000', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36', 5),
(84, '172.16.0.25', '2025-05-21 08:45:00.000000', 'Mozilla/5.0 (iPhone; CPU iPhone OS 17_5 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/17.5 Mobile/15E148 Safari/604.1', 6),
(85, '10.0.0.15', '2025-05-21 09:15:00.000000', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36 Edg/136.0.0.0', 7),
(86, '192.168.0.10', '2025-05-21 11:05:00.000000', 'Mozilla/5.0 (Linux; Android 14; Pixel 7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Mobile Safari/537.36', 8),
(87, '0:0:0:0:0:0:0:1', '2025-05-21 12:45:09.000000', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36', 1),
(88, '0:0:0:0:0:0:0:1', '2025-05-22 09:25:02.000000', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36', 1);

-- --------------------------------------------------------

--
-- Structure de la table `posts`
--

CREATE TABLE `posts` (
  `id` bigint(20) NOT NULL,
  `content` varchar(280) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  `author` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `posts`
--

INSERT INTO `posts` (`id`, `content`, `created_at`, `image_url`, `user_id`, `author`) VALUES
(1, 'La nature est mon meilleur refuge #photo #voyage', '2025-05-11 19:21:24.000000', '/images/202f1005-732c-4c91-bd40-9e7851e25521.png', 2, NULL),
(2, 'Bonjour tout le monde ! Ravi de rejoindre cette communauté #socialmedia #nouveauté', '2025-05-17 10:00:00.000000', '/images/202f1005-732c-4c91-bd40-9e7851e25521.png', 2, NULL),
(3, 'Un nouveau design en cours... Qu\'en pensez-vous ? #design #webdesign #ux', '2025-05-17 11:30:00.000000', '/images/202f1005-732c-4c91-bd40-9e7851e25521.png', 2, NULL),
(4, 'Quelqu\'un connaît un bon framework JavaScript ? Je débute avec React #webdev #javascript #react', '2025-05-17 12:15:00.000000', '/images/202f1005-732c-4c91-bd40-9e7851e25521.png', 3, NULL),
(23, 'Je viens de terminer mon dernier projet React ! Vraiment fier du résultat #webdev #javascript', '2025-05-18 10:15:00.000000', '/images/202f1005-732c-4c91-bd40-9e7851e25521.png', 3, NULL),
(24, 'Vue incroyable depuis mon hôtel ce matin ! #voyage', '2025-05-18 08:30:00.000000', '/images/202f1005-732c-4c91-bd40-9e7851e25521.png', 2, NULL),
(25, 'Qui serait intéressé par un tutoriel sur les API REST ?', '2025-05-19 14:45:00.000000', NULL, 3, NULL),
(26, 'Je recommande ce restaurant, les plats sont délicieux ! #food', '2025-05-19 20:25:00.000000', '/images/202f1005-732c-4c91-bd40-9e7851e25521.png', 4, NULL),
(27, 'Mon setup de développement après quelques améliorations #tech #developpement', '2025-05-20 09:12:00.000000', '/images/202f1005-732c-4c91-bd40-9e7851e25521.png', 1, NULL),
(28, 'Nouvelle collection de printemps, j\'adore ces designs ! #mode', '2025-05-20 11:35:00.000000', '/images/202f1005-732c-4c91-bd40-9e7851e25521.png', 5, NULL),
(29, '5km de course ce matin, belle journée qui commence ! #fitness', '2025-05-21 07:45:00.000000', NULL, 7, NULL),
(30, 'Ce nouveau framework JavaScript a l\'air prometteur. Quelqu\'un l\'a déjà testé ?', '2025-05-21 10:20:00.000000', NULL, 6, NULL),
(31, 'Concert incroyable hier soir ! #musique', '2025-05-21 09:30:00.000000', '/images/202f1005-732c-4c91-bd40-9e7851e25521.png', 8, NULL),
(32, 'Mes photos de la randonnée du weekend dernier #photo #voyage', '2025-05-21 13:15:00.000000', '/images/202f1005-732c-4c91-bd40-9e7851e25521.png', 5, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `post_hashtags`
--

CREATE TABLE `post_hashtags` (
  `hashtag_id` bigint(20) NOT NULL,
  `post_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `post_hashtags`
--

INSERT INTO `post_hashtags` (`hashtag_id`, `post_id`) VALUES
(1, 3),
(2, 4),
(3, 2),
(2, 23),
(6, 23),
(5, 24),
(8, 26),
(4, 27),
(7, 27),
(9, 28),
(10, 29),
(6, 30),
(11, 31),
(6, 32),
(5, 32),
(6, 1),
(5, 1),
(3, 2),
(1, 3),
(2, 4),
(6, 4),
(7, 4);

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `bio` varchar(255) DEFAULT NULL,
  `display_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `profile_image_url` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`id`, `is_active`, `bio`, `display_name`, `email`, `password`, `profile_image_url`, `username`) VALUES
(1, b'1', 'Administrateur système & développeur web passionné | J\'aime partager mes connaissances', 'Nora', 'a@gmail.com', '$2a$10$jAliv0o168/1GpTXqfJ6JuidxI1J0L7ogp77Z2ylKAapOg5Nowwda', '/images/4b8d760b-992e-42a5-8b09-6468a303b3d8.png', 'admin'),
(2, b'1', 'Photographe amateur & voyageur | À la recherche de nouveaux horizons', 'ayato', 'aya@gmail.com', '$2a$10$7N8WtFF6oOOU3R6zM.TCIuz.sRIvD6zW/mP4YfEvhea10Xe0waQ66', '/images/4b8d760b-992e-42a5-8b09-6468a303b3d8.png', 'aya'),
(3, b'1', 'Designer graphique | Spécialiste en UI/UX | Freelance depuis 3 ans', 'farah', 'farah@gmaill.com', '$2a$10$hiA5NfClwmd2ym5cNAvFpubZiTcGpVhvEQiOBhHPCd1/FsaeyAHxO', '/images/4b8d760b-992e-42a5-8b09-6468a303b3d8.png', 'farah'),
(4, b'1', 'Développeur backend | Passionné de nouvelles technologies et d\'IA', 'khalid', 'khalid@gmail.com', '$2a$10$j7mf.ZX9QOXS4FX0icut4ejcrciKyOIcendvAqLCHZ4LV9jeVsx9e', '/images/4b8d760b-992e-42a5-8b09-6468a303b3d8.png', 'khalid'),
(5, b'1', 'Passionné de photographie et de voyages', 'Thomas', 'thomas@gmail.com', '$2a$10$hiA5NfClwmd2ym5cNAvFpubZiTcGpVhvEQiOBhHPCd1/FsaeyAHxO', '/images/4b8d760b-992e-42a5-8b09-6468a303b3d8.png', 'thomas'),
(6, b'1', 'Designer UX/UI | Freelance', 'Sophie', 'sophie@gmail.com', '$2a$10$hiA5NfClwmd2ym5cNAvFpubZiTcGpVhvEQiOBhHPCd1/FsaeyAHxO', '/images/4b8d760b-992e-42a5-8b09-6468a303b3d8.png', 'sophie'),
(7, b'1', 'Développeur web full-stack | JavaScript enthusiast', 'Marco', 'marco@gmail.com', '$2a$10$hiA5NfClwmd2ym5cNAvFpubZiTcGpVhvEQiOBhHPCd1/FsaeyAHxO', '/images/4b8d760b-992e-42a5-8b09-6468a303b3d8.png', 'marco'),
(8, b'1', 'Étudiante en informatique | Passionnée de jeux vidéo', 'Léa', 'lea@gmail.com', '$2a$10$hiA5NfClwmd2ym5cNAvFpubZiTcGpVhvEQiOBhHPCd1/FsaeyAHxO', '/images/4b8d760b-992e-42a5-8b09-6468a303b3d8.png', 'lea'),
(9, b'0', 'Compte désactivé temporairement', 'Alex', 'alex@gmail.com', '$2a$10$hiA5NfClwmd2ym5cNAvFpubZiTcGpVhvEQiOBhHPCd1/FsaeyAHxO', '/images/4b8d760b-992e-42a5-8b09-6468a303b3d8.png', 'alex');

-- --------------------------------------------------------

--
-- Structure de la table `user_blocks`
--

CREATE TABLE `user_blocks` (
  `user_id` bigint(20) NOT NULL,
  `blocked_user_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `user_blocks`
--

INSERT INTO `user_blocks` (`user_id`, `blocked_user_id`) VALUES
(1, 2),
(3, 5),
(7, 4),
(6, 9),
(8, 2);

-- --------------------------------------------------------

--
-- Structure de la table `user_followers`
--

CREATE TABLE `user_followers` (
  `user_id` bigint(20) NOT NULL,
  `follower_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `user_followers`
--

INSERT INTO `user_followers` (`user_id`, `follower_id`) VALUES
(2, 1),
(4, 3),
(1, 3),
(3, 4),
(3, 1),
(5, 1),
(5, 2),
(6, 3),
(6, 4),
(6, 1),
(7, 5),
(7, 6),
(7, 2),
(8, 7),
(8, 4),
(5, 6),
(5, 7),
(6, 5),
(7, 8),
(1, 5),
(2, 6),
(3, 7),
(4, 8);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKh4c7lvsc298whoyd4w9ta25cr` (`post_id`),
  ADD KEY `FK8omq0tc18jd43bu5tjh6jvraq` (`user_id`);

--
-- Index pour la table `hashtags`
--
ALTER TABLE `hashtags`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_oed8qhhrhflqj7olh3oeii6ym` (`name`);

--
-- Index pour la table `likes`
--
ALTER TABLE `likes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKe4guax66lb963pf27kvm7ikik` (`comment_id`),
  ADD KEY `FKry8tnr4x2vwemv2bb0h5hyl0x` (`post_id`),
  ADD KEY `FKnvx9seeqqyy71bij291pwiwrg` (`user_id`);

--
-- Index pour la table `login_records`
--
ALTER TABLE `login_records`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKhahil5ikkymki7li2o4s8ksq2` (`user_id`);

--
-- Index pour la table `posts`
--
ALTER TABLE `posts`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK5lidm6cqbc7u4xhqpxm898qme` (`user_id`);

--
-- Index pour la table `post_hashtags`
--
ALTER TABLE `post_hashtags`
  ADD KEY `FKrrlq793bvaswhomm900i71ac5` (`post_id`),
  ADD KEY `FKb8j4xx456a7584d8blc604pqg` (`hashtag_id`);

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`),
  ADD UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`);

--
-- Index pour la table `user_blocks`
--
ALTER TABLE `user_blocks`
  ADD KEY `FK88ycaua2bsajvea7bx6yg3jw2` (`blocked_user_id`),
  ADD KEY `FKqjk3v8efx104494evnp1yxaxj` (`user_id`);

--
-- Index pour la table `user_followers`
--
ALTER TABLE `user_followers`
  ADD KEY `FKsauvjgnbgys3gbeharkga2omh` (`follower_id`),
  ADD KEY `FKox7c2m7d9qhhpu45d83luq19q` (`user_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `comments`
--
ALTER TABLE `comments`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT pour la table `hashtags`
--
ALTER TABLE `hashtags`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT pour la table `likes`
--
ALTER TABLE `likes`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=62;

--
-- AUTO_INCREMENT pour la table `login_records`
--
ALTER TABLE `login_records`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=89;

--
-- AUTO_INCREMENT pour la table `posts`
--
ALTER TABLE `posts`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT pour la table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `comments`
--
ALTER TABLE `comments`
  ADD CONSTRAINT `FK8omq0tc18jd43bu5tjh6jvraq` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKh4c7lvsc298whoyd4w9ta25cr` FOREIGN KEY (`post_id`) REFERENCES `posts` (`id`);

--
-- Contraintes pour la table `likes`
--
ALTER TABLE `likes`
  ADD CONSTRAINT `FKe4guax66lb963pf27kvm7ikik` FOREIGN KEY (`comment_id`) REFERENCES `comments` (`id`),
  ADD CONSTRAINT `FKnvx9seeqqyy71bij291pwiwrg` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKry8tnr4x2vwemv2bb0h5hyl0x` FOREIGN KEY (`post_id`) REFERENCES `posts` (`id`);

--
-- Contraintes pour la table `login_records`
--
ALTER TABLE `login_records`
  ADD CONSTRAINT `FKhahil5ikkymki7li2o4s8ksq2` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Contraintes pour la table `posts`
--
ALTER TABLE `posts`
  ADD CONSTRAINT `FK5lidm6cqbc7u4xhqpxm898qme` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Contraintes pour la table `post_hashtags`
--
ALTER TABLE `post_hashtags`
  ADD CONSTRAINT `FKb8j4xx456a7584d8blc604pqg` FOREIGN KEY (`hashtag_id`) REFERENCES `hashtags` (`id`),
  ADD CONSTRAINT `FKrrlq793bvaswhomm900i71ac5` FOREIGN KEY (`post_id`) REFERENCES `posts` (`id`);

--
-- Contraintes pour la table `user_blocks`
--
ALTER TABLE `user_blocks`
  ADD CONSTRAINT `FK88ycaua2bsajvea7bx6yg3jw2` FOREIGN KEY (`blocked_user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKqjk3v8efx104494evnp1yxaxj` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Contraintes pour la table `user_followers`
--
ALTER TABLE `user_followers`
  ADD CONSTRAINT `FKox7c2m7d9qhhpu45d83luq19q` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKsauvjgnbgys3gbeharkga2omh` FOREIGN KEY (`follower_id`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
