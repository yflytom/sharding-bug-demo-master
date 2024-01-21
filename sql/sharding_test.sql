

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sharding_test
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
                        `id` int(11) NOT NULL AUTO_INCREMENT,
                        `username` varchar(30) DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS `user`;
CREATE TABLE `books_202401` (
                                `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
                                `title` varchar(100) NOT NULL COMMENT '书名',
                                `price` decimal(10,2) DEFAULT NULL COMMENT '价格',
                                `publishDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '出版日期',
                                PRIMARY KEY (`id`),
                                UNIQUE KEY `title` (`title`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS `user`;
CREATE TABLE `books_202402` (
                                `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
                                `title` varchar(100) NOT NULL COMMENT '书名',
                                `price` decimal(10,2) DEFAULT NULL COMMENT '价格',
                                `publishDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '出版日期',
                                PRIMARY KEY (`id`),
                                UNIQUE KEY `title` (`title`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
SET FOREIGN_KEY_CHECKS = 1;
