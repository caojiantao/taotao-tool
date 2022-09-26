SET NAMES utf8mb4;

-- ----------------------------
-- Table structure for album
-- ----------------------------
DROP TABLE IF EXISTS `album`;
CREATE TABLE `album` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cover_id` int NOT NULL,
  `name` varchar(50) COLLATE utf8mb4_bin NOT NULL,
  `description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for album_file
-- ----------------------------
DROP TABLE IF EXISTS `album_file`;
CREATE TABLE `album_file` (
  `id` int NOT NULL AUTO_INCREMENT,
  `album_id` int NOT NULL,
  `file_id` int NOT NULL,
  `file_type` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_album_id` (`album_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for pic
-- ----------------------------
DROP TABLE IF EXISTS `pic`;
CREATE TABLE `pic` (
  `id` int NOT NULL AUTO_INCREMENT,
  `filename` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `md5` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `bytes` bigint NOT NULL,
  `content_type` varchar(50) COLLATE utf8mb4_bin NOT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_filename` (`filename`) USING BTREE,
  UNIQUE KEY `uniq_md5` (`md5`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for video
-- ----------------------------
DROP TABLE IF EXISTS `video`;
CREATE TABLE `video` (
  `id` int NOT NULL AUTO_INCREMENT,
  `filename` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `md5` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `seconds` bigint NOT NULL,
  `bytes` bigint NOT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_filename` (`filename`) USING BTREE,
  UNIQUE KEY `uniq_md5` (`md5`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
