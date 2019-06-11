CREATE TABLE `people` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) NOT NULL,
  `age` varchar(45) DEFAULT NULL,
  `hobby` varchar(255) DEFAULT NULL,
  `es_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `up_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='这个表是用来写着玩的';
