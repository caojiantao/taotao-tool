SET NAMES utf8mb4;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `system_user`
(
    `id`           int          NOT     NULL AUTO_INCREMENT,
    `username`     varchar(50)  NOT     NULL,
    `password`     varchar(50)  NOT     NULL,
    `nickname`     varchar(100) DEFAULT NULL,
    `avatar`       varchar(100) DEFAULT NULL,
    `gmt_create`   datetime     NOT     NULL,
    `gmt_modified` datetime     NOT     NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `system_user`
VALUES (1, 'caojiantao', '4f563da976e9e3cecbdc9f2ad815d57c', '涛涛', '98bdc67b-6caa-4b18-abef-c55be2434db4.jpg', '2022-10-25 20:40:36', '2022-10-25 20:40:38');
COMMIT;

-- ----------------------------
-- Table structure for system_media
-- ----------------------------
DROP TABLE IF EXISTS `system_media`;
CREATE TABLE `system_media`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `bucket` varchar(50) NOT NULL,
  `media_type` varchar(20) NOT NULL,
  `filename` varchar(100) NOT NULL,
  `content_md5` varchar(100) NOT NULL,
  `content_length` bigint(0) NOT NULL,
  `content_json` text NULL,
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB;

CREATE TABLE `anniv` (
  `id` int NOT NULL AUTO_INCREMENT,
  `anniv_type` varchar(32) NOT NULL,
  `anniv_date` date NOT NULL,
  `lunar` tinyint(1) NOT NULL DEFAULT '0',
  `remark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE `todo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `group_id` int NOT NULL,
  `title` varchar(200) NOT NULL,
  `content` text,
  `state` int NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;
