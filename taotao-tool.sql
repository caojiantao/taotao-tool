SET NAMES utf8mb4;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`           int         NOT NULL AUTO_INCREMENT,
    `username`     varchar(50) NOT NULL,
    `password`     varchar(50) NOT NULL,
    `gmt_create`   datetime    NOT NULL,
    `gmt_modified` datetime    NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user`
VALUES (1, 'caojiantao', '4f563da976e9e3cecbdc9f2ad815d57c', '2022-10-25 20:40:36', '2022-10-25 20:40:38');
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
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB;

CREATE TABLE `love_note_user`
(
    `id`          int NOT NULL AUTO_INCREMENT,
    `openid`      varchar(100) DEFAULT NULL,
    `avatar_url`  varchar(100) DEFAULT NULL,
    `nickname`    varchar(200) DEFAULT NULL,
    `gender`      int(1) DEFAULT NULL,
    `create_time` datetime     DEFAULT CURRENT_TIMESTAMP,
    `update_time` datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `love_note_cp`
(
    `id`             int NOT NULL AUTO_INCREMENT,
    `inviter`        varchar(100) DEFAULT NULL,
    `invitee`        varchar(100) DEFAULT NULL,
    `cp_name`        varchar(100) DEFAULT NULL,
    `cp_description` varchar(100) DEFAULT NULL,
    `create_time`    datetime     DEFAULT CURRENT_TIMESTAMP,
    `update_time`    datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `love_note_trend`
(
    `id`          int NOT NULL AUTO_INCREMENT,
    `cp_id`       int NOT NULL,
    `openid`      varchar(100) DEFAULT NULL,
    `content`     text,
    `create_time` datetime     DEFAULT CURRENT_TIMESTAMP,
    `update_time` datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `love_note_trend_media`
(
    `id`          int NOT NULL AUTO_INCREMENT,
    `trend_id`    int          DEFAULT NULL,
    `cp_id`       int          DEFAULT NULL,
    `openid`      varchar(255) DEFAULT NULL,
    `type`        varchar(20)  DEFAULT NULL,
    `content`     text,
    `create_time` datetime     DEFAULT CURRENT_TIMESTAMP,
    `update_time` datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;



-- ----------------------------
-- Table structure for carpool_driver
-- ----------------------------
DROP TABLE IF EXISTS `carpool_driver`;
CREATE TABLE `carpool_driver`
(
    `id`             int          NOT NULL AUTO_INCREMENT,
    `openid`         varchar(100) NOT NULL,
    `real_name`      varchar(100) NOT NULL,
    `id_card`        varchar(100) NOT NULL,
    `phone`          varchar(100) NOT NULL,
    `driver_license` varchar(100) NOT NULL,
    `car_no`         varchar(20)  NOT NULL,
    `car_permit`     varchar(100) NOT NULL,
    `create_time`    datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time`    datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    KEY              `idx_openid` (`openid`) USING BTREE
) ENGINE=InnoDB;

-- ----------------------------
-- Table structure for carpool_line
-- ----------------------------
DROP TABLE IF EXISTS `carpool_line`;
CREATE TABLE `carpool_line`
(
    `id`               int NOT NULL AUTO_INCREMENT,
    `openid`           varchar(100) DEFAULT NULL,
    `home_position`    text,
    `pathway_position` text,
    `work_position`    text,
    `work_time`        time         DEFAULT NULL,
    `home_time`        time         DEFAULT NULL,
    `replay`           varchar(100) DEFAULT NULL,
    `price`            int          DEFAULT NULL,
    `remark`           varchar(200) DEFAULT NULL,
    `create_time`      datetime     DEFAULT CURRENT_TIMESTAMP,
    `update_time`      datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    KEY                `idx_openid` (`openid`) USING BTREE
) ENGINE=InnoDB;

-- ----------------------------
-- Table structure for carpool_line_point
-- ----------------------------
DROP TABLE IF EXISTS `carpool_line_point`;
CREATE TABLE `carpool_line_point`
(
    `id`          int NOT NULL AUTO_INCREMENT,
    `openid`      varchar(100)   DEFAULT NULL,
    `lat`         decimal(10, 6) DEFAULT NULL,
    `lon`         decimal(10, 6) DEFAULT NULL,
    `create_time` datetime       DEFAULT CURRENT_TIMESTAMP,
    `update_time` datetime       DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    KEY           `idx_openid` (`openid`),
    KEY           `idx_lat_lon` (`lat`,`lon`)
) ENGINE=InnoDB AUTO_INCREMENT=3;

-- ----------------------------
-- Table structure for carpool_media
-- ----------------------------
DROP TABLE IF EXISTS `carpool_media`;
CREATE TABLE `carpool_media`
(
    `id`          int NOT NULL AUTO_INCREMENT,
    `openid`      varchar(100) DEFAULT NULL,
    `type`        varchar(20)  DEFAULT NULL,
    `content`     text,
    `create_time` datetime     DEFAULT CURRENT_TIMESTAMP,
    `update_time` datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    KEY           `idx_openid` (`openid`) USING BTREE
) ENGINE=InnoDB;

-- ----------------------------
-- Table structure for carpool_user
-- ----------------------------
DROP TABLE IF EXISTS `carpool_user`;
CREATE TABLE `carpool_user`
(
    `id`          int NOT NULL AUTO_INCREMENT,
    `openid`      varchar(100) DEFAULT NULL,
    `avatar`      varchar(100) DEFAULT NULL,
    `nickname`    varchar(200) DEFAULT NULL,
    `create_time` datetime     DEFAULT CURRENT_TIMESTAMP,
    `update_time` datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    KEY           `idx_openid` (`openid`) USING BTREE
) ENGINE=InnoDB;
