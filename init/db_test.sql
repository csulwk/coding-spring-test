CREATE DATABASE `db_test` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

DROP TABLE IF EXISTS user_info;
CREATE TABLE `user_info` (
  `ui_id` varchar(18) NOT NULL COMMENT '用户证件号',
  `ui_name` varchar(64) NOT NULL COMMENT '姓名',
	`ui_age` int(9) DEFAULT 0 COMMENT '年龄',
  PRIMARY KEY (`ui_id`) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `user_info` VALUES (201912290001, 'Tom', '10');
INSERT INTO `user_info` VALUES (201912290002, 'Ley', '20');
INSERT INTO `user_info` VALUES (201912290003, 'Kong', '30');
INSERT INTO `user_info` VALUES (201912290004, 'Miya', '40');
INSERT INTO `user_info` VALUES (201912290005, '测试用户', '50');

DROP TABLE IF EXISTS good_info;
CREATE TABLE `good_info` (
  `gi_id` int(4) NOT NULL COMMENT '商品ID',
  `gi_name` varchar(45) NOT NULL COMMENT '商品名称',
  `gi_desc` varchar(45) DEFAULT '' COMMENT '商品描述信息',
  PRIMARY KEY (`gi_id`) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `good_info` VALUES (1001, 'movie', '电影');
INSERT INTO `good_info` VALUES (1002, 'music', '音乐');
INSERT INTO `good_info` VALUES (1003, 'food', '食物');
INSERT INTO `good_info` VALUES (1004, 'test', '');

DROP TABLE IF EXISTS order_info;
CREATE TABLE `order_info` (
  `oi_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `oi_user_id` varchar(18) NOT NULL COMMENT '用户证件号',
  `oi_good_id` varchar(4) NOT NULL COMMENT '商品ID',
  `oi_desc` varchar(45) DEFAULT '' COMMENT '订单描述信息',
  PRIMARY KEY (`oi_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

INSERT INTO `order_info` VALUES (1, 201912290001, 1001, 'Tom喜欢电影');
INSERT INTO `order_info` VALUES (2, 201912290001, 1002, 'Tom喜欢音乐');
INSERT INTO `order_info` VALUES (3, 201912290004, 1003, 'Miya喜欢食物');
