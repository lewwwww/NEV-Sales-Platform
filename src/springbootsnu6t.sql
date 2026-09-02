/*
 Navicat Premium Data Transfer

 Source Server         : mysql80
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : springbootsnu6t

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 08/01/2026 09:46:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for aboutus
-- ----------------------------
DROP TABLE IF EXISTS `aboutus`;
CREATE TABLE `aboutus`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `title` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题',
  `subtitle` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '副标题',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容',
  `picture1` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '图片1',
  `picture2` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '图片2',
  `picture3` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '图片3',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '关于我们' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of aboutus
-- ----------------------------
INSERT INTO `aboutus` VALUES (1, '2025-12-02 11:35:16', '关于我们', 'ABOUT US', '不管你想要怎样的生活，你都要去努力争取，不多尝试一些事情怎么知道自己适合什么、不适合什么呢?\n你说你喜欢读书，让我给你列书单，你还问我哪里有那么多时间看书;你说自己梦想的职业是广告文案，问我如何成为一个文案，应该具备哪些素质;你说你计划晨跑，但总是因为学习、工作辛苦或者身体不舒服第二天起不了床;你说你一直梦想一个人去长途旅行，但是没钱，父母觉得危险。其实，我已经厌倦了你这样说说而已的把戏，我觉得就算我告诉你如何去做，你也不会照做，因为你根本什么都不做。', 'upload/aboutus_picture1.jpg', 'upload/aboutus_picture2.jpg', 'upload/aboutus_picture3.jpg');

-- ----------------------------
-- Table structure for baoyangdingdan
-- ----------------------------
DROP TABLE IF EXISTS `baoyangdingdan`;
CREATE TABLE `baoyangdingdan`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `dingdanbianhao` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单编号',
  `cheming` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车名',
  `pinpaileibie` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '品牌类别',
  `yingfujine` float NULL DEFAULT NULL COMMENT '应付金额',
  `zhanghao` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `xingming` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `shoujihaoma` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `xiadanshijian` datetime NULL DEFAULT NULL COMMENT '预约时间',
  `xiadanbeizhu` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预约备注',
  `sfsh` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '待保养' COMMENT '保养状态',
  `shhf` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '保养说明',
  `ispay` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '未支付' COMMENT '是否支付',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1743093698759 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '汽车订单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of baoyangdingdan
-- ----------------------------
INSERT INTO `baoyangdingdan` VALUES (41, '2025-11-26 11:35:16', '1111111111', '车名1', '品牌类别1', 100, '账号1', '姓名1', '手机号码1', '2025-11-26 11:35:16', '下单备注1', '已保养', '1000000', '已支付');
INSERT INTO `baoyangdingdan` VALUES (42, '2025-11-26 11:35:16', '2222222222', '车名2', '品牌类别2', 2, '账号2', '姓名2', '手机号码2', '2025-11-26 11:35:16', '下单备注2', '待保养', '', '未支付');
INSERT INTO `baoyangdingdan` VALUES (43, '2025-11-26 11:35:16', '3333333333', '车名3', '品牌类别3', 3, '账号3', '姓名3', '手机号码3', '2025-11-26 11:35:16', '下单备注3', '待保养', '', '未支付');
INSERT INTO `baoyangdingdan` VALUES (44, '2025-11-26 11:35:16', '4444444444', '车名4', '品牌类别4', 4, '账号4', '姓名4', '手机号码4', '2025-11-26 11:35:16', '下单备注4', '待保养', '', '未支付');
INSERT INTO `baoyangdingdan` VALUES (45, '2025-11-26 11:35:16', '5555555555', '车名5', '品牌类别5', 5, '账号5', '姓名5', '手机号码5', '2025-11-26 11:35:16', '下单备注5', '待保养', '', '未支付');
INSERT INTO `baoyangdingdan` VALUES (46, '2025-11-26 11:35:16', '6666666666', '车名6', '品牌类别6', 6, '账号6', '姓名6', '手机号码6', '2025-11-26 11:35:16', '下单备注6', '待保养', '', '未支付');
INSERT INTO `baoyangdingdan` VALUES (47, '2025-11-26 11:35:16', '7777777777', '车名7', '品牌类别7', 7, '账号7', '姓名7', '手机号码7', '2025-11-26 11:35:16', '下单备注7', '待保养', '', '未支付');
INSERT INTO `baoyangdingdan` VALUES (48, '2025-11-26 11:35:16', '8888888888', '车名8', '品牌类别8', 88, '账号8', '姓名8', '手机号码8', '2025-11-26 11:35:16', '下单备注8', '待保养', '保养说明', '未支付');
INSERT INTO `baoyangdingdan` VALUES (1743093514193, '2025-11-26 11:35:16', '1111111111', '车名1', '品牌类别1', 400, '账号1', '姓名1', '13823888881', '2025-11-26 11:35:16', '1111', '已保养', '保养说明111111', '已支付');
INSERT INTO `baoyangdingdan` VALUES (1743093698759, '2025-11-26 11:35:16', '1111111111', '车名1', '品牌类别1', 0, '账号1', '姓名1', '13823888881', '2025-11-26 11:35:16', '保养备注', '待保养', '', '未支付');

-- ----------------------------
-- Table structure for chat
-- ----------------------------
DROP TABLE IF EXISTS `chat`;
CREATE TABLE `chat`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `userid` bigint NOT NULL COMMENT '用户id',
  `adminid` bigint NULL DEFAULT NULL COMMENT '管理员id',
  `ask` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '提问',
  `reply` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '回复',
  `isreply` int NULL DEFAULT NULL COMMENT '是否回复',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1764321814345 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '在线咨询' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of chat
-- ----------------------------
INSERT INTO `chat` VALUES (81, '2025-11-26 11:35:16', 1, 1, '提问1', '回复1', 0);
INSERT INTO `chat` VALUES (82, '2025-11-26 11:35:16', 2, 2, '提问2', '回复2', 2);
INSERT INTO `chat` VALUES (83, '2025-11-26 11:35:16', 3, 3, '提问3', '回复3', 3);
INSERT INTO `chat` VALUES (84, '2025-11-26 11:35:16', 4, 4, '提问4', '回复4', 4);
INSERT INTO `chat` VALUES (85, '2025-11-26 11:35:16', 5, 5, '提问5', '回复5', 5);
INSERT INTO `chat` VALUES (86, '2025-11-26 11:35:16', 6, 6, '提问6', '回复6', 6);
INSERT INTO `chat` VALUES (87, '2025-11-26 11:35:16', 7, 7, '提问7', '回复7', 7);
INSERT INTO `chat` VALUES (88, '2025-11-26 11:35:16', 8, 8, '提问8', '回复8', 8);
INSERT INTO `chat` VALUES (1763957507990, '2025-11-26 11:35:16', 11, NULL, '1', NULL, 0);
INSERT INTO `chat` VALUES (1763962919281, '2025-11-26 11:35:16', 11, NULL, '1', NULL, 0);
INSERT INTO `chat` VALUES (1763962942654, '2025-11-26 11:35:16', 11, NULL, '1', NULL, 0);
INSERT INTO `chat` VALUES (1764058514734, '2025-11-26 11:35:16', 11, NULL, '请问售后怎么走流程', NULL, 1);
INSERT INTO `chat` VALUES (1764321390531, '2025-11-28 17:16:30', 1, 1, NULL, '666', NULL);
INSERT INTO `chat` VALUES (1764321782559, '2025-11-28 17:23:02', 1764321262665, NULL, '小米su7有吗', NULL, 0);
INSERT INTO `chat` VALUES (1764321814345, '2025-11-28 17:23:34', 1764321262665, 1, NULL, '有', NULL);

-- ----------------------------
-- Table structure for config
-- ----------------------------
DROP TABLE IF EXISTS `config`;
CREATE TABLE `config`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '配置参数名称',
  `value` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '配置参数值',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '配置文件' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of config
-- ----------------------------
INSERT INTO `config` VALUES (1, 'picture1', 'upload/picture1.jpg');
INSERT INTO `config` VALUES (2, 'picture2', 'upload/picture2.jpg');
INSERT INTO `config` VALUES (3, 'picture3', 'upload/picture3.jpg');

-- ----------------------------
-- Table structure for discussxinnengyuanqiche
-- ----------------------------
DROP TABLE IF EXISTS `discussxinnengyuanqiche`;
CREATE TABLE `discussxinnengyuanqiche`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `refid` bigint NOT NULL COMMENT '关联表id',
  `userid` bigint NOT NULL COMMENT '用户id',
  `avatarurl` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '头像',
  `nickname` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论内容',
  `reply` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '回复内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1764321571005 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '新能源汽车评论表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of discussxinnengyuanqiche
-- ----------------------------
INSERT INTO `discussxinnengyuanqiche` VALUES (1764321571005, '2025-11-28 17:19:30', 38, 1764321262665, 'upload/1764321255219.jpg', '002', '很好', '666');

-- ----------------------------
-- Table structure for news
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `title` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题',
  `introduction` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '简介',
  `picture` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 108 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '公告信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of news
-- ----------------------------
INSERT INTO `news` VALUES (101, '2025-11-26 11:35:16', '有梦想，就要努力去实现', '不管你想要怎样的生活，你都要去努力争取，不多尝试一些事情怎么知道自己适合什么、不适合什么呢?你说你喜欢读书，让我给你列书单，你还问我哪里有那么多时间看书;你说自己梦想的职业是广告文案，问我如何成为一个文案，应该具备哪些素质;你说你计划晨跑，但总是因为学习、工作辛苦或者身体不舒服第二天起不了床;你说你一直梦想一个人去长途旅行，但是没钱，父母觉得危险。', 'upload/news_picture1.jpg', '<p>不管你想要怎样的生活，你都要去努力争取，不多尝试一些事情怎么知道自己适合什么、不适合什么呢?</p><p>你说你喜欢读书，让我给你列书单，你还问我哪里有那么多时间看书;你说自己梦想的职业是广告文案，问我如何成为一个文案，应该具备哪些素质;你说你计划晨跑，但总是因为学习、工作辛苦或者身体不舒服第二天起不了床;你说你一直梦想一个人去长途旅行，但是没钱，父母觉得危险。其实，我已经厌倦了你这样说说而已的把戏，我觉得就算我告诉你如何去做，你也不会照做，因为你根本什么都不做。</p><p>真正有行动力的人不需要别人告诉他如何做，因为他已经在做了。就算碰到问题，他也会自己想办法，自己动手去解决或者主动寻求可以帮助他的人，而不是等着别人为自己解决问题。</p><p>首先要学习独立思考。花一点时间想一下自己喜欢什么，梦想是什么，不要别人说想环游世界，你就说你的梦想是环游世界。</p><p>很多人说现实束缚了自己，其实在这个世界上，我们一直都可以有很多选择，生活的决定权也—直都在自己手上，只是我们缺乏行动力而已。</p><p>如果你觉得安于现状是你想要的，那选择安于现状就会让你幸福和满足;如果你不甘平庸，选择一条改变、进取和奋斗的道路，在这个追求的过程中，你也一样会感到快乐。所谓的成功，即是按照自己想要的生活方式生活。最糟糕的状态，莫过于当你想要选择一条不甘平庸、改变、进取和奋斗的道路时，却以一种安于现状的方式生活，最后抱怨自己没有得到想要的人生。</p><p>因为喜欢，你不是在苦苦坚持，也因为喜欢，你愿意投入时间、精力，长久以往，获得成功就是自然而然的事情。</p>');
INSERT INTO `news` VALUES (102, '2025-11-26 11:35:16', '又是一年毕业季', '又是一年毕业季，感慨万千，还记的自己刚进学校那时候的情景，我拖着沉重的行李箱站在偌大的教学楼前面，感叹自己未来的日子即将在这个陌生的校园里度过，而如今斗转星移，浮光掠影，弹指之间，那些青葱岁月如同白驹过隙般悄然从指缝溜走。过去的种种在胸口交集纠结，像打翻的五味瓶，甜蜜，酸楚，苦涩，一并涌上心头。', 'upload/news_picture2.jpg', '<p>又是一年毕业季，感慨万千，还记的自己刚进学校那时候的情景，我拖着沉重的行李箱站在偌大的教学楼前面，感叹自己未来的日子即将在这个陌生的校园里度过，而如今斗转星移，浮光掠影，弹指之间，那些青葱岁月如同白驹过隙般悄然从指缝溜走。</p><p>过去的种种在胸口交集纠结，像打翻的五味瓶，甜蜜，酸楚，苦涩，一并涌上心头。一直都是晚会的忠实参与者，无论是台前还是幕后，忽然间，角色转变，那种感觉确实难以用语言表达。</p><p>	过去的三年，总是默默地期盼着这个好雨时节，因为这时候，会有灿烂的阳光，会有满目的百花争艳，会有香甜的冰激凌，这是个毕业的季节，当时不经世事的我们会殷切地期待学校那一大堆的活动，期待穿上绚丽的演出服或者礼仪服，站在大礼堂镁光灯下尽情挥洒我们的澎拜的激情。</p><p>百感交集，隔岸观火与身临其境的感觉竟是如此不同。从来没想过一场晚会送走的是我们自己的时候会是怎样的感情，毕业就真的意味着结束吗?倔强的我们不愿意承认，谢谢学弟学妹们慷慨的将这次的主题定为“我们在这里”。我知道，这可能是他们对我们这些过来人的尊敬和施舍。</p><p>没有为这场晚会排练、奔波，没有为班级、学生会、文学院出点力，还真有点不习惯，百般无奈中，用“工作忙”个万能的借口来搪塞自己，欺骗别人。其实自己心里明白，那只是在逃避，只是不愿面对繁华落幕后的萧条和落寞。大四了，大家各奔东西，想凑齐班上的人真的是难上加难，敏燕从越南回来，刚落地就匆匆回了学校，那么恋家的人也启程回来了，睿睿学姐也是从家赶来跟我们团圆。大家—如既往的寒暄、打趣、调侃对方，似乎一切又回到了当初的单纯美好。</p><p>看着舞台上活泼可爱的学弟学妹们，如同一群机灵的小精灵，清澈的眼神，稚嫩的肢体，轻快地步伐，用他们那热情洋溢的舞姿渲染着在场的每一个人，我知道，我不应该羡慕嫉妒他们，不应该顾自怜惜逝去的青春，不应该感叹夕阳无限好，曾经，我们也拥有过，曾经，我们也年轻过，曾经，我们也灿烂过。我深深地告诉自己，人生的每个阶段都是美的，年轻有年轻的活力，成熟也有成熟的魅力。多—份稳重、淡然、优雅，也是漫漫时光掠影遗留下的.珍贵赏赐。</p>');
INSERT INTO `news` VALUES (103, '2025-11-26 11:35:16', '挫折路上，坚持常在心间', '回头看看，你会不会发现，曾经的你在这里摔倒过;回头看看，你是否发现，一次次地重复着，却从没爬起过。而如今，让我们把视线转向前方，那一道道金色的弧线，是流星飞逝的痕迹，或是成功运行的轨道。今天的你，是否要扬帆起航，让幸福来敲门?清晨的太阳撒向大地，神奇的宇宙赋予它神奇的色彩，大自然沐浴着春光，世界因太阳的照射而精彩，林中百鸟啾啾，河水轻轻流淌，汇成清宁的山间小调。', 'upload/news_picture3.jpg', '<p>回头看看，你会不会发现，曾经的你在这里摔倒过;回头看看，你是否发现，一次次地重复着，却从没爬起过。而如今，让我们把视线转向前方，那一道道金色的弧线，是流星飞逝的痕迹，或是成功运行的轨道。今天的你，是否要扬帆起航，让幸福来敲门?</p><p>清晨的太阳撒向大地，神奇的宇宙赋予它神奇的色彩，大自然沐浴着春光，世界因太阳的照射而精彩，林中百鸟啾啾，河水轻轻流淌，汇成清宁的山间小调。</p><p>是的，面对道途上那无情的嘲讽，面对步伐中那重复的摔跤，面对激流与硬石之间猛烈的碰撞，我们必须选择那富于阴雨，却最终见到彩虹的荆棘路。也许，经历了那暴风雨的洗礼，我们便会变得自信，幸福也随之而来。</p><p>司马迁屡遭羞辱，却依然在狱中撰写《史记》，作为一名史学家，不因王权而极度赞赏，也不因卑微而极度批判，然而他在坚持自己操守的同时，却依然要受统治阶级的阻碍，他似乎无权选择自己的本职。但是，他不顾于此，只是在面对道途的阻隔之时，他依然选择了走下去的信念。终于一部开山巨作《史记》诞生，为后人留下一份馈赠，也许在他完成毕生的杰作之时，他微微地笑了，没有什么比梦想实现更快乐的了......</p><p>	或许正如“长风破浪会有时，直挂云帆济沧海”一般，欣欣然地走向看似深渊的崎岖路，而在一番耕耘之后，便会发现这里另有一番天地。也许这就是困难与快乐的交融。</p><p>也许在形形色色的社会中，我们常能看到一份坚持，一份自信，但这里却还有一类人。这类人在暴风雨来临之际，只会闪躲，从未懂得这也是一种历炼，这何尝不是一份快乐。在阴暗的角落里，总是独自在哭，带着伤愁，看不到一点希望。</p><p>我们不能堕落于此，而要像海燕那般，在苍茫的大海上，高傲地飞翔，任何事物都无法阻挡，任何事都是幸福快乐的。</p>');
INSERT INTO `news` VALUES (104, '2025-11-26 11:35:16', '挫折是另一个生命的开端', '当遇到挫折或失败，你是看见失败还是看见机会?挫折是我们每个人成长的必经之路，它不是你想有就有，想没有就没有的。有句名言说的好，如果你想一生摆脱苦难，你就得是神或者是死尸。这句话形象地说明了挫折是伴随着人生的，是谁都逃不掉的。', 'upload/news_picture4.jpg', '<p>当遇到挫折或失败，你是看见失败还是看见机会?</p><p>挫折是我们每个人成长的必经之路，它不是你想有就有，想没有就没有的。有句名言说的好，如果你想一生摆脱苦难，你就得是神或者是死尸。这句话形象地说明了挫折是伴随着人生的，是谁都逃不掉的。</p><p>人生在世，从古到今，不分天子平民，机遇虽有不同，但总不免有身陷困境或遭遇难题之处，这时候唯有通权达变，才能使人转危为安，甚至反败为胜。</p><p>大部分的人，一生当中，最痛苦的经验是失去所爱的人，其次是丢掉一份工作。其实，经得起考验的人，就算是被开除也不会惊慌，要学会面对。</p><p>	“塞翁失马，焉知非福。”人生的道路，并不是每一步都迈向成功，这就是追求的意义。我们还要认识到一点，挫折作为一种情绪状态和一种个人体验，各人的耐受性是大不相同的，有的人经历了一次次挫折，就能够坚忍不拔，百折不挠;有的人稍遇挫折便意志消沉，一蹶不振。所以，挫折感是一种主观感受，因为人的目的和需要不同，成功标准不同，所以同一种活动对于不同的人可能会造成不同的挫折感受。</p><p>凡事皆以平常心来看待，对于生命顺逆不要太执著。能够“破我执”是很高层的人生境界。</p><p>人事的艰难就是一种考验。就像—支剑要有磨刀来磨，剑才会利:一块璞玉要有粗石来磨，才会发出耀眼的光芒。我们能够做到的，只是如何减少、避免那些由于自身的原因所造成的挫折，而在遇到痛苦和挫折之后，则力求化解痛苦，争取幸福。我们要知道，痛苦和挫折是双重性的，它既是我们人生中难以完全避免的，也是我们在争取成功时，不可缺少的一种动力。因为我认为，推动我们奋斗的力量，不仅仅是对成功的渴望，还有为摆脱痛苦和挫折而进行的奋斗。</p>');
INSERT INTO `news` VALUES (105, '2025-11-26 11:35:16', '你要去相信，没有到不了的明天', '有梦想就去努力，因为在这一辈子里面，现在不去勇敢的努力，也许就再也没有机会了。你要去相信，一定要相信，没有到不了的明天。不要被命运打败，让自己变得更强大。不管你现在是一个人走在异乡的街道上始终没有找到一丝归属感，还是你在跟朋友们一起吃饭开心址笑着的时候闪过一丝落寞。', 'upload/news_picture5.jpg', '<p>有梦想就去努力，因为在这一辈子里面，现在不去勇敢的努力，也许就再也没有机会了。你要去相信，一定要相信，没有到不了的明天。不要被命运打败，让自己变得更强大。</p><p>不管你现在是一个人走在异乡的街道上始终没有找到一丝归属感，还是你在跟朋友们一起吃饭开心址笑着的时候闪过一丝落寞。</p><p>	不管你现在是在图书馆里背着怎么也看不进去的英语单词，还是你现在迷茫地看不清未来的方向不知道要往哪走。</p><p>不管你现在是在努力着去实现梦想却没能拉近与梦想的距离，还是你已经慢慢地找不到自己的梦想了。</p><p>你都要去相信，没有到不了的明天。</p><p>	有的时候你的梦想太大，别人说你的梦想根本不可能实现;有的时候你的梦想又太小，又有人说你胸无大志;有的时候你对死党说着将来要去环游世界的梦想，却换来他的不屑一顾，于是你再也不提自己的梦想;有的时候你突然说起将来要开个小店的愿望，却发现你讲述的那个人，并没有听到你在说什么。</p><p>不过又能怎么样呢，未来始终是自己的，梦想始终是自己的，没有人会来帮你实现它。</p><p>也许很多时候我们只是需要朋友的一句鼓励，一句安慰，却也得不到。但是相信我，世界上还有很多人，只是想要和你说说话。</p><p>因为我们都一样。一样的被人说成固执，一样的在追逐他们眼里根本不在意的东西。</p><p>所以，又有什么关系呢，别人始终不是你、不能懂你的心情，你又何必多去解释呢。这个世界会来阻止你，困难也会接踵而至，其实真正关键的只有自己，有没有那个倔强。</p><p>这个世界上没有不带伤的人，真正能治愈自己的，只有自己。</p>');
INSERT INTO `news` VALUES (106, '2025-11-26 11:35:16', '离开是一种痛苦，是一种勇气，但同样也是一个考验，是一个新的开端', '无穷无尽是离愁，天涯海角遍寻思。当离别在即之时，当面对着相濡以沫兄弟般的朋友时，当面对着经历了四年的磨合而形成的真挚友谊之时，我内心激动无语，说一声再见，道一声珍重都很难出口。回想自己四年大学的风风雨雨，回想我们曾经共同经历的岁月流年，我感谢大家的相扶相依，感谢朋友们的莫大支持与帮助。虽然舍不得，但离别的脚步却不因我们的挚情而停滞。', 'upload/news_picture6.jpg', '<p>无穷无尽是离愁，天涯海角遍寻思。当离别在即之时，当面对着相濡以沫兄弟般的朋友时，当面对着经历了四年的磨合而形成的真挚友谊之时，我内心激动无语，说一声再见，道一声珍重都很难出口。回想自己四年大学的风风雨雨，回想我们曾经共同经历的岁月流年，我感谢大家的相扶相依，感谢朋友们的莫大支持与帮助。虽然舍不得，但离别的脚步却不因我们的挚情而停滞。离别的确是一种痛苦，但同样也是我们走入社会，走向新环境、新领域的一个开端，希望大家在以后新的工作岗位上能够确定自己的新起点，坚持不懈，向着更新、更高的目标前进，因为人生最美好的东西永远都在最前方!</p><p>忆往昔峥嵘岁月，看今朝潮起潮落，望未来任重而道远。作为新时代的我们，就应在失败时，能拼搏奋起，去谱写人生的辉煌。在成功时，亦能居安思危，不沉湎于一时的荣耀、鲜花和掌声中，时时刻刻怀着一颗积极寻找自己新的奶酪的心，处变不惊、成败不渝，始终踏着自己坚实的步伐，从零开始，不断向前迈进，这样才能在这风起云涌、变幻莫测的社会大潮中成为真正的弄潮儿!</p>');
INSERT INTO `news` VALUES (107, '2025-11-26 11:35:16', 'Leave未必是一种痛苦', '无穷无尽是离愁，天涯海角遍寻思。当离别在即之时，当面对着相濡以沫兄弟般的朋友时，当面对着经历了四年的磨合而形成的真挚友谊之时，我内心激动无语，说一声再见，道一声珍重都很难出口。回想自己四年大学的风风雨雨，回想我们曾经共同经历的岁月流年，我感谢大家的相扶相依，感谢朋友们的莫大支持与帮助。虽然舍不得，但离别的脚步却不因我们的挚情而停滞。', 'upload/news_picture7.jpg', '<p>无穷无尽是离愁，天涯海角遍寻思。当离别在即之时，当面对着相濡以沫兄弟般的朋友时，当面对着经历了四年的磨合而形成的真挚友谊之时，我内心激动无语，说一声再见，道一声珍重都很难出口。回想自己四年大学的风风雨雨，回想我们曾经共同经历的岁月流年，我感谢大家的相扶相依，感谢朋友们的莫大支持与帮助。虽然舍不得，但离别的脚步却不因我们的挚情而停滞。离别的确是一种痛苦，但同样也是我们走入社会，走向新环境、新领域的一个开端，希望大家在以后新的工作岗位上能够确定自己的新起点，坚持不懈，向着更新、更高的目标前进，因为人生最美好的东西永远都在最前方!</p><p>忆往昔峥嵘岁月，看今朝潮起潮落，望未来任重而道远。作为新时代的我们，就应在失败时，能拼搏奋起，去谱写人生的辉煌。在成功时，亦能居安思危，不沉湎于一时的荣耀、鲜花和掌声中，时时刻刻怀着一颗积极寻找自己新的奶酪的心，处变不惊、成败不渝，始终踏着自己坚实的步伐，从零开始，不断向前迈进，这样才能在这风起云涌、变幻莫测的社会大潮中成为真正的弄潮儿!</p>');
INSERT INTO `news` VALUES (108, '2025-11-26 11:35:16', '坚持才会成功', '回头看看，你会不会发现，曾经的你在这里摔倒过;回头看看，你是否发现，一次次地重复着，却从没爬起过。而如今，让我们把视线转向前方，那一道道金色的弧线，是流星飞逝的痕迹，或是成功运行的轨道。今天的你，是否要扬帆起航，让幸福来敲门?清晨的太阳撒向大地，神奇的宇宙赋予它神奇的色彩，大自然沐浴着春光，世界因太阳的照射而精彩，林中百鸟啾啾，河水轻轻流淌，汇成清宁的山间小调。', 'upload/news_picture8.jpg', '<p>回头看看，你会不会发现，曾经的你在这里摔倒过;回头看看，你是否发现，一次次地重复着，却从没爬起过。而如今，让我们把视线转向前方，那一道道金色的弧线，是流星飞逝的痕迹，或是成功运行的轨道。今天的你，是否要扬帆起航，让幸福来敲门?</p><p>清晨的太阳撒向大地，神奇的宇宙赋予它神奇的色彩，大自然沐浴着春光，世界因太阳的照射而精彩，林中百鸟啾啾，河水轻轻流淌，汇成清宁的山间小调。</p><p>是的，面对道途上那无情的嘲讽，面对步伐中那重复的摔跤，面对激流与硬石之间猛烈的碰撞，我们必须选择那富于阴雨，却最终见到彩虹的荆棘路。也许，经历了那暴风雨的洗礼，我们便会变得自信，幸福也随之而来。</p><p>司马迁屡遭羞辱，却依然在狱中撰写《史记》，作为一名史学家，不因王权而极度赞赏，也不因卑微而极度批判，然而他在坚持自己操守的同时，却依然要受统治阶级的阻碍，他似乎无权选择自己的本职。但是，他不顾于此，只是在面对道途的阻隔之时，他依然选择了走下去的信念。终于一部开山巨作《史记》诞生，为后人留下一份馈赠，也许在他完成毕生的杰作之时，他微微地笑了，没有什么比梦想实现更快乐的了......</p><p>	或许正如“长风破浪会有时，直挂云帆济沧海”一般，欣欣然地走向看似深渊的崎岖路，而在一番耕耘之后，便会发现这里另有一番天地。也许这就是困难与快乐的交融。</p><p>也许在形形色色的社会中，我们常能看到一份坚持，一份自信，但这里却还有一类人。这类人在暴风雨来临之际，只会闪躲，从未懂得这也是一种历炼，这何尝不是一份快乐。在阴暗的角落里，总是独自在哭，带着伤愁，看不到一点希望。</p><p>我们不能堕落于此，而要像海燕那般，在苍茫的大海上，高傲地飞翔，任何事物都无法阻挡，任何事都是幸福快乐的。</p>');

-- ----------------------------
-- Table structure for peisongdingdan
-- ----------------------------
DROP TABLE IF EXISTS `peisongdingdan`;
CREATE TABLE `peisongdingdan`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `dingdanbianhao` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单编号',
  `cheming` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车名',
  `pinpaileibie` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '品牌类别',
  `yanse` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '颜色',
  `jiage` float NULL DEFAULT NULL COMMENT '价格',
  `shuliang` int NULL DEFAULT NULL COMMENT '数量',
  `yingfujine` float NULL DEFAULT NULL COMMENT '应付金额',
  `changshang` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '厂商',
  `chundianxuhang` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '纯电续航',
  `nengyuanleixing` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '能源类型',
  `zhanghao` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `xingming` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `shoujihaoma` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `shouhuodizhi` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货地址',
  `xiadanshijian` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '下单时间',
  `xiadanbeizhu` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '下单备注',
  `jiedanshijian` datetime NULL DEFAULT NULL COMMENT '接单时间',
  `jiedanbeizhu` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接单备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1764321656983 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '配送订单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of peisongdingdan
-- ----------------------------
INSERT INTO `peisongdingdan` VALUES (51, '2025-11-26 11:35:16', '订单编号1', '车名1', '品牌类别1', '颜色1', 1, 1, 1, '厂商1', '纯电续航1', '能源类型1', '账号1', '姓名1', '手机号码1', '收货地址1', '下单时间1', '下单备注1', '2025-11-26 11:35:16', '接单备注1');
INSERT INTO `peisongdingdan` VALUES (52, '2025-11-26 11:35:16', '订单编号2', '车名2', '品牌类别2', '颜色2', 2, 2, 2, '厂商2', '纯电续航2', '能源类型2', '账号2', '姓名2', '手机号码2', '收货地址2', '下单时间2', '下单备注2', '2025-11-26 11:35:16', '接单备注2');
INSERT INTO `peisongdingdan` VALUES (53, '2025-11-26 11:35:16', '订单编号3', '车名3', '品牌类别3', '颜色3', 3, 3, 3, '厂商3', '纯电续航3', '能源类型3', '账号3', '姓名3', '手机号码3', '收货地址3', '下单时间3', '下单备注3', '2025-11-26 11:35:16', '接单备注3');
INSERT INTO `peisongdingdan` VALUES (54, '2025-11-26 11:35:16', '订单编号4', '车名4', '品牌类别4', '颜色4', 4, 4, 4, '厂商4', '纯电续航4', '能源类型4', '账号4', '姓名4', '手机号码4', '收货地址4', '下单时间4', '下单备注4', '2025-11-26 11:35:16', '接单备注4');
INSERT INTO `peisongdingdan` VALUES (55, '2025-11-26 11:35:16', '订单编号5', '车名5', '品牌类别5', '颜色5', 5, 5, 5, '厂商5', '纯电续航5', '能源类型5', '账号5', '姓名5', '手机号码5', '收货地址5', '下单时间5', '下单备注5', '2025-11-26 11:35:16', '接单备注5');
INSERT INTO `peisongdingdan` VALUES (56, '2025-11-26 11:35:16', '订单编号6', '车名6', '品牌类别6', '颜色6', 6, 6, 6, '厂商6', '纯电续航6', '能源类型6', '账号6', '姓名6', '手机号码6', '收货地址6', '下单时间6', '下单备注6', '2025-11-26 11:35:16', '接单备注6');
INSERT INTO `peisongdingdan` VALUES (57, '2025-11-26 11:35:16', '订单编号7', '车名7', '品牌类别7', '颜色7', 7, 7, 7, '厂商7', '纯电续航7', '能源类型7', '账号7', '姓名7', '手机号码7', '收货地址7', '下单时间7', '下单备注7', '2025-11-26 11:35:16', '接单备注7');
INSERT INTO `peisongdingdan` VALUES (58, '2025-11-26 11:35:16', '订单编号8', '车名8', '品牌类别8', '颜色8', 8, 8, 8, '厂商8', '纯电续航8', '能源类型8', '账号8', '姓名8', '手机号码8', '收货地址8', '下单时间8', '下单备注8', '2025-11-26 11:35:16', '接单备注8');
INSERT INTO `peisongdingdan` VALUES (1764321656983, '2025-11-28 17:20:56', '1764321523282', '车名8', '品牌类别8', '颜色8', 8, 0, 0, '厂商8', '纯电续航8', '纯电动', '002', '李可以', '18037151521', '千锋郑州', '2025-11-28 17:18:43', '好好送', '2025-11-28 17:20:39', '配送');

-- ----------------------------
-- Table structure for peisongxinxi
-- ----------------------------
DROP TABLE IF EXISTS `peisongxinxi`;
CREATE TABLE `peisongxinxi`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `dingdanbianhao` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单编号',
  `cheming` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车名',
  `pinpaileibie` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '品牌类别',
  `yanse` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '颜色',
  `jiage` float NULL DEFAULT NULL COMMENT '价格',
  `shuliang` int NULL DEFAULT NULL COMMENT '数量',
  `yingfujine` float NULL DEFAULT NULL COMMENT '应付金额',
  `changshang` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '厂商',
  `chundianxuhang` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '纯电续航',
  `zhanghao` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `xingming` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `shoujihaoma` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `shouhuodizhi` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货地址',
  `xiadanshijian` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '下单时间',
  `gengxinshijian` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `gengxinneirong` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '更新内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1764321680313 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '配送信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of peisongxinxi
-- ----------------------------
INSERT INTO `peisongxinxi` VALUES (61, '2025-11-26 11:35:16', '订单编号1', '车名1', '品牌类别1', '颜色1', 1, 1, 1, '厂商1', '纯电续航1', '账号1', '姓名1', '手机号码1', '收货地址1', '下单时间1', '2025-11-26 11:35:16', '更新内容1');
INSERT INTO `peisongxinxi` VALUES (62, '2025-11-26 11:35:16', '订单编号2', '车名2', '品牌类别2', '颜色2', 2, 2, 2, '厂商2', '纯电续航2', '账号2', '姓名2', '手机号码2', '收货地址2', '下单时间2', '2025-11-26 11:35:16', '更新内容2');
INSERT INTO `peisongxinxi` VALUES (63, '2025-11-26 11:35:16', '订单编号3', '车名3', '品牌类别3', '颜色3', 3, 3, 3, '厂商3', '纯电续航3', '账号3', '姓名3', '手机号码3', '收货地址3', '下单时间3', '2025-11-26 11:35:16', '更新内容3');
INSERT INTO `peisongxinxi` VALUES (64, '2025-11-26 11:35:16', '订单编号4', '车名4', '品牌类别4', '颜色4', 4, 4, 4, '厂商4', '纯电续航4', '账号4', '姓名4', '手机号码4', '收货地址4', '下单时间4', '2025-11-26 11:35:16', '更新内容4');
INSERT INTO `peisongxinxi` VALUES (65, '2025-11-26 11:35:16', '订单编号5', '车名5', '品牌类别5', '颜色5', 5, 5, 5, '厂商5', '纯电续航5', '账号5', '姓名5', '手机号码5', '收货地址5', '下单时间5', '2025-11-26 11:35:16', '更新内容5');
INSERT INTO `peisongxinxi` VALUES (66, '2025-11-26 11:35:16', '订单编号6', '车名6', '品牌类别6', '颜色6', 6, 6, 6, '厂商6', '纯电续航6', '账号6', '姓名6', '手机号码6', '收货地址6', '下单时间6', '2025-11-26 11:35:16', '更新内容6');
INSERT INTO `peisongxinxi` VALUES (67, '2025-11-26 11:35:16', '订单编号7', '车名7', '品牌类别7', '颜色7', 7, 7, 7, '厂商7', '纯电续航7', '账号7', '姓名7', '手机号码7', '收货地址7', '下单时间7', '2025-11-26 11:35:16', '更新内容7');
INSERT INTO `peisongxinxi` VALUES (68, '2025-11-26 11:35:16', '订单编号8', '车名8', '品牌类别8', '颜色8', 8, 8, 18, '厂商8', '纯电续航8', '账号8', '姓名8', '手机号码8', '收货地址8', '下单时间8', '2025-11-26 11:35:16', '<p>更新内容8</p>');
INSERT INTO `peisongxinxi` VALUES (1764321680007, '2025-11-28 17:21:19', '1764321523282', '车名8', '品牌类别8', '颜色8', 8, 0, 0, '厂商8', '纯电续航8', '002', '李可以', '18037151521', '千锋郑州', '2025-11-28 17:18:43', '2025-11-28 17:21:13', '');
INSERT INTO `peisongxinxi` VALUES (1764321680313, '2025-11-28 17:21:19', '1764321523282', '车名8', '品牌类别8', '颜色8', 8, 0, 0, '厂商8', '纯电续航8', '002', '李可以', '18037151521', '千锋郑州', '2025-11-28 17:18:43', '2025-11-28 17:21:13', '');

-- ----------------------------
-- Table structure for pinpaileibie
-- ----------------------------
DROP TABLE IF EXISTS `pinpaileibie`;
CREATE TABLE `pinpaileibie`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `pinpaileibie` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '品牌类别',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1764148551197 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '品牌类别' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of pinpaileibie
-- ----------------------------
INSERT INTO `pinpaileibie` VALUES (21, '2025-11-26 11:35:16', '品牌类别1');
INSERT INTO `pinpaileibie` VALUES (22, '2025-11-26 11:35:16', '品牌类别2');
INSERT INTO `pinpaileibie` VALUES (23, '2025-11-26 11:35:16', '品牌类别3');
INSERT INTO `pinpaileibie` VALUES (24, '2025-11-26 11:35:16', '品牌类别4');
INSERT INTO `pinpaileibie` VALUES (25, '2025-11-26 11:35:16', '品牌类别5');
INSERT INTO `pinpaileibie` VALUES (26, '2025-11-26 11:35:16', '品牌类别6');
INSERT INTO `pinpaileibie` VALUES (27, '2025-11-26 11:35:16', '品牌类别7');
INSERT INTO `pinpaileibie` VALUES (28, '2025-11-26 11:35:16', '品牌类别8');

-- ----------------------------
-- Table structure for qichedingdan
-- ----------------------------
DROP TABLE IF EXISTS `qichedingdan`;
CREATE TABLE `qichedingdan`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `dingdanbianhao` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单编号',
  `cheming` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车名',
  `pinpaileibie` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '品牌类别',
  `yanse` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '颜色',
  `jiage` float NULL DEFAULT NULL COMMENT '价格',
  `shuliang` int NULL DEFAULT NULL COMMENT '数量',
  `yingfujine` float NULL DEFAULT NULL COMMENT '应付金额',
  `changshang` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '厂商',
  `chundianxuhang` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '纯电续航',
  `nengyuanleixing` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '能源类型',
  `zhanghao` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `xingming` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `shoujihaoma` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `shouhuodizhi` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货地址',
  `xiadanshijian` datetime NULL DEFAULT NULL COMMENT '下单时间',
  `xiadanbeizhu` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '下单备注',
  `dingdanzhuangtai` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单状态',
  `sfsh` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '待审核' COMMENT '是否审核',
  `shhf` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '审核回复',
  `ispay` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '未支付' COMMENT '是否支付',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `dingdanbianhao`(`dingdanbianhao` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1764321543988 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '汽车订单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qichedingdan
-- ----------------------------
INSERT INTO `qichedingdan` VALUES (41, '2025-11-26 11:35:16', '1111111111', '车名1', '品牌类别1', '颜色1', 1, 1, 1, '厂商1', '纯电续航1', '能源类型1', '账号1', '姓名1', '手机号码1', '收货地址1', '2025-11-26 11:35:16', '下单备注1', '已发货', '是', '', '未支付');
INSERT INTO `qichedingdan` VALUES (42, '2025-11-26 11:35:16', '2222222222', '车名2', '品牌类别2', '颜色2', 2, 2, 2, '厂商2', '纯电续航2', '能源类型2', '账号2', '姓名2', '手机号码2', '收货地址2', '2025-11-26 11:35:16', '下单备注2', '已发货', '是', '', '未支付');
INSERT INTO `qichedingdan` VALUES (43, '2025-11-26 11:35:16', '3333333333', '车名3', '品牌类别3', '颜色3', 3, 3, 3, '厂商3', '纯电续航3', '能源类型3', '账号3', '姓名3', '手机号码3', '收货地址3', '2025-11-26 11:35:16', '下单备注3', '已发货', '是', '', '未支付');
INSERT INTO `qichedingdan` VALUES (44, '2025-11-26 11:35:16', '4444444444', '车名4', '品牌类别4', '颜色4', 4, 4, 4, '厂商4', '纯电续航4', '能源类型4', '账号4', '姓名4', '手机号码4', '收货地址4', '2025-11-26 11:35:16', '下单备注4', '已发货', '是', '', '未支付');
INSERT INTO `qichedingdan` VALUES (45, '2025-11-26 11:35:16', '5555555555', '车名5', '品牌类别5', '颜色5', 5, 5, 5, '厂商5', '纯电续航5', '能源类型5', '账号5', '姓名5', '手机号码5', '收货地址5', '2025-11-26 11:35:16', '下单备注5', '已发货', '是', '', '未支付');
INSERT INTO `qichedingdan` VALUES (46, '2025-11-26 11:35:16', '6666666666', '车名6', '品牌类别6', '颜色6', 6, 6, 6, '厂商6', '纯电续航6', '能源类型6', '账号6', '姓名6', '手机号码6', '收货地址6', '2025-11-26 11:35:16', '下单备注6', '已发货', '是', '', '未支付');
INSERT INTO `qichedingdan` VALUES (47, '2025-11-26 11:35:16', '7777777777', '车名7', '品牌类别7', '颜色7', 7, 7, 7, '厂商7', '纯电续航7', '能源类型7', '账号7', '姓名7', '手机号码7', '收货地址7', '2025-11-26 11:35:16', '下单备注7', '已发货', '是', '', '未支付');
INSERT INTO `qichedingdan` VALUES (48, '2025-11-26 11:35:16', '8888888888', '车名8', '品牌类别8', '颜色8', 8, 0, 8, '厂商8', '纯电续航8', '能源类型8', '账号8', '姓名8', '手机号码8', '收货地址8', '2025-11-26 11:35:16', '下单备注8', '已发货', '是', '111', '未支付');
INSERT INTO `qichedingdan` VALUES (1764321543988, '2025-11-28 17:19:03', '1764321523282', '车名8', '品牌类别8', '颜色8', 8, 0, 0, '厂商8', '纯电续航8', '纯电动', '002', '李可以', '18037151521', '千锋郑州', '2025-11-28 17:18:43', '好好送', '已发货', '是', '有存货', '已支付');

-- ----------------------------
-- Table structure for storeup
-- ----------------------------
DROP TABLE IF EXISTS `storeup`;
CREATE TABLE `storeup`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `userid` bigint NOT NULL COMMENT '用户id',
  `refid` bigint NULL DEFAULT NULL COMMENT '商品id',
  `tablename` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表名',
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `picture` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片',
  `type` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '类型(1:收藏,21:赞,22:踩,31:竞拍参与,41:关注)',
  `inteltype` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '推荐类型',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1764321480158 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '收藏表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of storeup
-- ----------------------------
INSERT INTO `storeup` VALUES (1743005700054, '2025-11-26 11:35:16', 11, 31, 'xinnengyuanqiche', '车名1', 'upload/xinnengyuanqiche_qichetupian1.jpg', '1', '品牌类别1', NULL);
INSERT INTO `storeup` VALUES (1764321480158, '2025-11-28 17:17:59', 1764321262665, 38, 'xinnengyuanqiche', '车名8', 'upload/xinnengyuanqiche_qichetupian8.jpg', '1', '品牌类别8', NULL);

-- ----------------------------
-- Table structure for systemintro
-- ----------------------------
DROP TABLE IF EXISTS `systemintro`;
CREATE TABLE `systemintro`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `title` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题',
  `subtitle` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '副标题',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容',
  `picture1` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '图片1',
  `picture2` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '图片2',
  `picture3` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '图片3',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '关于我们' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of systemintro
-- ----------------------------
INSERT INTO `systemintro` VALUES (1, '2025-11-26 11:35:16', '系统简介', 'SYSTEM INTRODUCTION', '当遇到挫折或失败，你是看见失败还是看见机会?挫折是我们每个人成长的必经之路，它不是你想有就有，想没有就没有的。有句名言说的好，如果你想一生摆脱苦难，你就得是神或者是死尸。这句话形象地说明了挫折是伴随着人生的，是谁都逃不掉的。人生在世，从古到今，不分天子平民，机遇虽有不同，但总不免有身陷困境或遭遇难题之处，这时候唯有通权达变，才能使人转危为安，甚至反败为胜。大部分的人，一生当中，最痛苦的经验是失去所爱的人，其次是丢掉一份工作。其实，经得起考验的人，就算是被开除也不会惊慌，要学会面对。', 'upload/systemintro_picture1.jpg', 'upload/systemintro_picture2.jpg', 'upload/systemintro_picture3.jpg');

-- ----------------------------
-- Table structure for token
-- ----------------------------
DROP TABLE IF EXISTS `token`;
CREATE TABLE `token`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userid` bigint NOT NULL COMMENT '用户id',
  `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `tablename` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表名',
  `role` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色',
  `token` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  `expiratedtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '过期时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'token表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of token
-- ----------------------------
INSERT INTO `token` VALUES (1, 11, '账号1', 'yonghu', '用户', '0webvaqco4mr679iy1pxw9sgy23lw9fp', '2025-03-27 00:12:51', '2025-12-13 13:42:53');
INSERT INTO `token` VALUES (2, 1, 'admin', 'users', '管理员', '32l4haiir2yd7n6na1yw1uym0bk4qg1x', '2025-03-27 22:03:50', '2025-12-13 13:41:54');
INSERT INTO `token` VALUES (3, 1764321262665, '002', 'yonghu', '用户', 'hwhd4on0adt3vnanqw519wgncptcgm1q', '2025-11-28 17:14:38', '2025-11-28 18:26:11');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `role` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '管理员' COMMENT '角色',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'admin', 'admin', '管理员', '2025-11-26 11:35:16');

-- ----------------------------
-- Table structure for weixiudingdan
-- ----------------------------
DROP TABLE IF EXISTS `weixiudingdan`;
CREATE TABLE `weixiudingdan`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `dingdanbianhao` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单编号',
  `cheming` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车名',
  `pinpaileibie` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '品牌类别',
  `yingfujine` float NULL DEFAULT NULL COMMENT '应付金额',
  `zhanghao` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `xingming` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `shoujihaoma` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `xiadanshijian` datetime NULL DEFAULT NULL COMMENT '预约时间',
  `xiadanbeizhu` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预约备注',
  `sfsh` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '待维修' COMMENT '维修状态',
  `shhf` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '维修说明',
  `ispay` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '未支付' COMMENT '是否支付',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1743087969827 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '汽车订单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of weixiudingdan
-- ----------------------------
INSERT INTO `weixiudingdan` VALUES (41, '2025-11-26 11:35:16', '1111111111', '车名1', '品牌类别1', 200, '账号1', '姓名1', '手机号码1', '2025-11-26 11:35:16', '下单备注1', '已维修', '已经维修好了 ', '未支付');
INSERT INTO `weixiudingdan` VALUES (42, '2025-11-26 11:35:16', '2222222222', '车名2', '品牌类别2', 2, '账号2', '姓名2', '手机号码2', '2025-11-26 11:35:16', '下单备注2', '待维修', '', '未支付');
INSERT INTO `weixiudingdan` VALUES (43, '2025-11-26 11:35:16', '3333333333', '车名3', '品牌类别3', 3, '账号3', '姓名3', '手机号码3', '2025-11-26 11:35:16', '下单备注3', '待维修', '', '未支付');
INSERT INTO `weixiudingdan` VALUES (44, '2025-11-26 11:35:16', '4444444444', '车名4', '品牌类别4', 4, '账号4', '姓名4', '手机号码4', '2025-11-26 11:35:16', '下单备注4', '待维修', '', '未支付');
INSERT INTO `weixiudingdan` VALUES (45, '2025-11-26 11:35:16', '5555555555', '车名5', '品牌类别5', 5, '账号5', '姓名5', '手机号码5', '2025-11-26 11:35:16', '下单备注5', '待维修', '', '未支付');
INSERT INTO `weixiudingdan` VALUES (46, '2025-11-26 11:35:16', '6666666666', '车名6', '品牌类别6', 6, '账号6', '姓名6', '手机号码6', '2025-11-26 11:35:16', '下单备注6', '待维修', '', '未支付');
INSERT INTO `weixiudingdan` VALUES (47, '2025-11-26 11:35:16', '7777777777', '车名7', '品牌类别7', 7, '账号7', '姓名7', '手机号码7', '2025-11-26 11:35:16', '下单备注7', '待维修', '', '未支付');
INSERT INTO `weixiudingdan` VALUES (48, '2025-11-26 11:35:16', '8888888888', '车名8', '品牌类别8', 8, '账号8', '姓名8', '手机号码8', '2025-11-26 11:35:16', '下单备注8', '待维修', '', '未支付');
INSERT INTO `weixiudingdan` VALUES (1743083948986, '2025-11-26 11:35:16', '1111111111', '车名1', '品牌类别1', 100, '账号1', '姓名1', '13823888881', '2025-11-26 11:35:16', '预约小备注', '已维修', '111', '已支付');
INSERT INTO `weixiudingdan` VALUES (1743087969827, '2025-11-26 11:35:16', '1111111111', '车名1', '品牌类别1', 0, '账号1', '姓名1', '13823888881', '2025-11-26 11:35:16', '维修', '待维修', '', '未支付');

-- ----------------------------
-- Table structure for xinnengyuanqiche
-- ----------------------------
DROP TABLE IF EXISTS `xinnengyuanqiche`;
CREATE TABLE `xinnengyuanqiche`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `cheming` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '车名',
  `pinpaileibie` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '品牌类别',
  `qichetupian` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '汽车图片',
  `yanse` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '颜色',
  `jiage` float NULL DEFAULT NULL COMMENT '价格',
  `shuliang` int NULL DEFAULT NULL COMMENT '数量',
  `changshang` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '厂商',
  `youwutianchuang` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '有无天窗',
  `nengyuanleixing` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '能源类型',
  `qichepeizhi` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '汽车配置',
  `chundianxuhang` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '纯电续航',
  `jibencanshu` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '基本参数',
  `clicktime` datetime NULL DEFAULT NULL COMMENT '最近点击时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '新能源汽车' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of xinnengyuanqiche
-- ----------------------------
INSERT INTO `xinnengyuanqiche` VALUES (31, '2025-11-26 11:35:16', '车名1', '品牌类别1', 'upload/xinnengyuanqiche_qichetupian1.jpg,upload/xinnengyuanqiche_qichetupian2.jpg,upload/xinnengyuanqiche_qichetupian3.jpg', '颜色1', 1, 1, '厂商1', '有', '纯电动', '汽车配置1', '纯电续航1', '基本参数1', '2025-11-26 11:35:16');
INSERT INTO `xinnengyuanqiche` VALUES (32, '2025-11-26 11:35:16', '车名2', '品牌类别2', 'upload/xinnengyuanqiche_qichetupian2.jpg,upload/xinnengyuanqiche_qichetupian3.jpg,upload/xinnengyuanqiche_qichetupian4.jpg', '颜色2', 2, 2, '厂商2', '有', '纯电动', '汽车配置2', '纯电续航2', '基本参数2', '2025-11-26 11:35:16');
INSERT INTO `xinnengyuanqiche` VALUES (33, '2025-11-26 11:35:16', '车名3', '品牌类别3', 'upload/xinnengyuanqiche_qichetupian3.jpg,upload/xinnengyuanqiche_qichetupian4.jpg,upload/xinnengyuanqiche_qichetupian5.jpg', '颜色3', 3, 3, '厂商3', '有', '纯电动', '汽车配置3', '纯电续航3', '基本参数3', '2025-11-26 11:35:16');
INSERT INTO `xinnengyuanqiche` VALUES (34, '2025-11-26 11:35:16', '车名4', '品牌类别4', 'upload/xinnengyuanqiche_qichetupian4.jpg,upload/xinnengyuanqiche_qichetupian5.jpg,upload/xinnengyuanqiche_qichetupian6.jpg', '颜色4', 4, 4, '厂商4', '有', '纯电动', '汽车配置4', '纯电续航4', '基本参数4', '2025-11-26 11:35:16');
INSERT INTO `xinnengyuanqiche` VALUES (35, '2025-11-26 11:35:16', '车名5', '品牌类别5', 'upload/xinnengyuanqiche_qichetupian5.jpg,upload/xinnengyuanqiche_qichetupian6.jpg,upload/xinnengyuanqiche_qichetupian7.jpg', '颜色5', 5, 5, '厂商5', '有', '纯电动', '汽车配置5', '纯电续航5', '基本参数5', '2025-11-26 11:35:16');
INSERT INTO `xinnengyuanqiche` VALUES (36, '2025-11-26 11:35:16', '车名6', '品牌类别6', 'upload/xinnengyuanqiche_qichetupian6.jpg,upload/xinnengyuanqiche_qichetupian7.jpg,upload/xinnengyuanqiche_qichetupian8.jpg', '颜色6', 6, 6, '厂商6', '有', '纯电动', '汽车配置6', '纯电续航6', '基本参数6', '2025-11-26 11:35:16');
INSERT INTO `xinnengyuanqiche` VALUES (37, '2025-11-26 11:35:16', '车名7', '品牌类别7', 'upload/xinnengyuanqiche_qichetupian7.jpg,upload/xinnengyuanqiche_qichetupian8.jpg,upload/xinnengyuanqiche_qichetupian9.jpg', '颜色7', 7, 7, '厂商7', '有', '纯电动', '汽车配置7', '纯电续航7', '基本参数7', '2025-11-26 11:35:16');
INSERT INTO `xinnengyuanqiche` VALUES (38, '2025-11-26 11:35:16', '车名8', '品牌类别8', 'upload/xinnengyuanqiche_qichetupian8.jpg,upload/xinnengyuanqiche_qichetupian9.jpg,upload/xinnengyuanqiche_qichetupian10.jpg', '颜色8', 8, 8, '厂商8', '有', '纯电动', '汽车配置8', '纯电续航8', '基本参数8', '2025-11-28 17:19:05');

-- ----------------------------
-- Table structure for yonghu
-- ----------------------------
DROP TABLE IF EXISTS `yonghu`;
CREATE TABLE `yonghu`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `zhanghao` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `mima` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `xingming` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `xingbie` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `shenfenzheng` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证',
  `shoujihaoma` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `touxiang` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '头像',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `zhanghao`(`zhanghao` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1764321262665 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of yonghu
-- ----------------------------
INSERT INTO `yonghu` VALUES (11, '2025-11-26 11:35:16', '账号1', 'e10adc3949ba59abbe56e057f20f883e', '姓名1', '女', '440300199101010001', '13823888881', 'upload/yonghu_touxiang1.jpg');
INSERT INTO `yonghu` VALUES (12, '2025-11-26 11:35:16', '账号2', 'e10adc3949ba59abbe56e057f20f883e', '姓名2', '男', '440300199202020002', '13823888882', 'upload/yonghu_touxiang2.jpg');
INSERT INTO `yonghu` VALUES (13, '2025-11-26 11:35:16', '账号3', 'e10adc3949ba59abbe56e057f20f883e', '姓名3', '男', '440300199303030003', '13823888883', 'upload/yonghu_touxiang3.jpg');
INSERT INTO `yonghu` VALUES (14, '2025-11-26 11:35:16', '账号4', 'e10adc3949ba59abbe56e057f20f883e', '姓名4', '男', '440300199404040004', '13823888884', 'upload/yonghu_touxiang4.jpg');
INSERT INTO `yonghu` VALUES (15, '2025-11-26 11:35:16', '账号5', 'e10adc3949ba59abbe56e057f20f883e', '姓名5', '男', '440300199505050005', '13823888885', 'upload/yonghu_touxiang5.jpg');
INSERT INTO `yonghu` VALUES (16, '2025-11-26 11:35:16', '账号6', 'e10adc3949ba59abbe56e057f20f883e', '姓名6', '男', '440300199606060006', '13823888886', 'upload/yonghu_touxiang6.jpg');
INSERT INTO `yonghu` VALUES (17, '2025-11-26 11:35:16', '账号7', 'e10adc3949ba59abbe56e057f20f883e', '姓名7', '男', '440300199707070007', '13823888887', 'upload/yonghu_touxiang7.jpg');
INSERT INTO `yonghu` VALUES (18, '2025-11-26 11:35:16', '账号8', 'e10adc3949ba59abbe56e057f20f883e', '姓名8', '男', '440300199808080008', '13823888888', 'upload/yonghu_touxiang8.jpg');
INSERT INTO `yonghu` VALUES (1764143812139, '2025-11-26 11:35:16', '001', 'dc5c7986daef50c1e02ab09b442ee34f', '李', '男', '412724199613230311', '18037151521', 'upload/1764143808052.jpg');
INSERT INTO `yonghu` VALUES (1764321262665, '2025-11-28 17:14:22', '002', 'e10adc3949ba59abbe56e057f20f883e', '李可以', '男', '412724199512260611', '18037151521', 'upload/1764321255219.jpg');

-- ----------------------------
-- Table structure for yuyuekanche
-- ----------------------------
DROP TABLE IF EXISTS `yuyuekanche`;
CREATE TABLE `yuyuekanche`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `yuyuebianhao` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预约编号',
  `cheming` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车名',
  `pinpaileibie` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '品牌类别',
  `yanse` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '颜色',
  `jiage` float NULL DEFAULT NULL COMMENT '价格',
  `chundianxuhang` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '纯电续航',
  `nengyuanleixing` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '能源类型',
  `zhanghao` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `xingming` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `shoujihaoma` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `yuyueshijian` datetime NULL DEFAULT NULL COMMENT '预约时间',
  `yuyuebeizhu` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预约备注',
  `sfsh` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '待审核' COMMENT '是否审核',
  `shhf` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '审核回复',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `yuyuebianhao`(`yuyuebianhao` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1764321520537 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '预约看车' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of yuyuekanche
-- ----------------------------
INSERT INTO `yuyuekanche` VALUES (71, '2025-11-26 11:35:16', '1111111111', '车名1', '品牌类别1', '颜色1', 1, '纯电续航1', '能源类型1', '账号1', '姓名1', '手机号码1', '2025-11-26 11:35:16', '预约备注1', '是', '');
INSERT INTO `yuyuekanche` VALUES (72, '2025-11-26 11:35:16', '2222222222', '车名2', '品牌类别2', '颜色2', 2, '纯电续航2', '能源类型2', '账号2', '姓名2', '手机号码2', '2025-11-26 11:35:16', '预约备注2', '是', '');
INSERT INTO `yuyuekanche` VALUES (73, '2025-11-26 11:35:16', '3333333333', '车名3', '品牌类别3', '颜色3', 3, '纯电续航3', '能源类型3', '账号3', '姓名3', '手机号码3', '2025-11-26 11:35:16', '预约备注3', '是', '');
INSERT INTO `yuyuekanche` VALUES (74, '2025-11-26 11:35:16', '4444444444', '车名4', '品牌类别4', '颜色4', 4, '纯电续航4', '能源类型4', '账号4', '姓名4', '手机号码4', '2025-11-26 11:35:16', '预约备注4', '是', '');
INSERT INTO `yuyuekanche` VALUES (75, '2025-11-26 11:35:16', '5555555555', '车名5', '品牌类别5', '颜色5', 5, '纯电续航5', '能源类型5', '账号5', '姓名5', '手机号码5', '2025-11-26 11:35:16', '预约备注5', '是', '');
INSERT INTO `yuyuekanche` VALUES (76, '2025-11-26 11:35:16', '6666666666', '车名6', '品牌类别6', '颜色6', 6, '纯电续航6', '能源类型6', '账号6', '姓名6', '手机号码6', '2025-11-26 11:35:16', '预约备注6', '是', '');
INSERT INTO `yuyuekanche` VALUES (77, '2025-11-26 11:35:16', '7777777777', '车名7', '品牌类别7', '颜色7', 7, '纯电续航7', '能源类型7', '账号7', '姓名7', '手机号码7', '2025-11-26 11:35:16', '预约备注7', '是', '');
INSERT INTO `yuyuekanche` VALUES (78, '2025-11-26 11:35:16', '8888888888', '车名8', '品牌类别8', '颜色8', 8, '纯电续航8', '能源类型8', '账号8', '姓名8', '手机号码8', '2025-11-26 11:35:16', '预约备注8', '是', '');
INSERT INTO `yuyuekanche` VALUES (1764321520537, '2025-11-28 17:18:40', '1764321511746', '车名8', '品牌类别8', '颜色8', 8, '纯电续航8', '纯电动', '002', '李可以', '18037151521', '2025-11-28 17:18:34', 'kanche', '是', '666');

SET FOREIGN_KEY_CHECKS = 1;
