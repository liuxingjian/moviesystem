/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50519
 Source Host           : localhost:3306
 Source Schema         : movie

 Target Server Type    : MySQL
 Target Server Version : 50519
 File Encoding         : 65001

 Date: 11/11/2022 17:23:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `num` int(11) NULL DEFAULT 0,
  `id` int(3) NOT NULL AUTO_INCREMENT,
  INDEX `fk`(`name`) USING BTREE,
  INDEX `fd`(`id`) USING BTREE,
  CONSTRAINT `fk` FOREIGN KEY (`name`) REFERENCES `type` (`name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('仙侠', 11, 1);
INSERT INTO `book` VALUES ('喜剧', 20, 2);
INSERT INTO `book` VALUES ('动漫', 30, 3);
INSERT INTO `book` VALUES ('动作', 11, 4);
INSERT INTO `book` VALUES ('古装', 10, 5);
INSERT INTO `book` VALUES ('爱情', 10, 6);
INSERT INTO `book` VALUES ('科幻', 11, 7);
INSERT INTO `book` VALUES ('悬疑', 102, 8);
INSERT INTO `book` VALUES ('1', 0, 29);
INSERT INTO `book` VALUES ('2', 0, 33);

-- ----------------------------
-- Table structure for easyuser
-- ----------------------------
DROP TABLE IF EXISTS `easyuser`;
CREATE TABLE `easyuser`  (
  `id` int(24) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `state` tinyint(255) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of easyuser
-- ----------------------------
INSERT INTO `easyuser` VALUES (1, '55-张志毅', '049573', '3221467124@qq.com', '普通用户', 1);
INSERT INTO `easyuser` VALUES (3, '52-张赛威', '123456', '1394495377@qq.com', '普通用户', 1);
INSERT INTO `easyuser` VALUES (4, '54-张义龙', '123456', 'aaaa@163.com', '普通用户', 1);
INSERT INTO `easyuser` VALUES (5, 'admin', '123456', '23924093@qq.com', 'j普通用户', 1);

-- ----------------------------
-- Table structure for mainmenu
-- ----------------------------
DROP TABLE IF EXISTS `mainmenu`;
CREATE TABLE `mainmenu`  (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 601 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of mainmenu
-- ----------------------------
INSERT INTO `mainmenu` VALUES (100, '用户管理', '/admin');
INSERT INTO `mainmenu` VALUES (200, '电影管理', '/system');
INSERT INTO `mainmenu` VALUES (300, '新闻管理', '/news');
INSERT INTO `mainmenu` VALUES (400, '在线视频', '/welcome');
INSERT INTO `mainmenu` VALUES (500, '数据图表', '/charts');
INSERT INTO `mainmenu` VALUES (600, '工作交流', '/chat');

-- ----------------------------
-- Table structure for movie
-- ----------------------------
DROP TABLE IF EXISTS `movie`;
CREATE TABLE `movie`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '电影编号',
  `movieName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电影名称',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电影类型',
  `actor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '演员',
  `director` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '导演',
  `minS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电影片长',
  `movieDate` date NULL DEFAULT NULL COMMENT '上映日期',
  `film` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电影链接',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of movie
-- ----------------------------
INSERT INTO `movie` VALUES (1, '战狼2', '动作', '吴京，卢靖姗', '吴京', '123分钟', '2017-05-26', 'https://www.iqiyi.com/v_19rrnqxz7k.html?vfm=m_127_bdbk&fv=b4f024cc44de40f2');
INSERT INTO `movie` VALUES (2, '长津湖', '军事', '吴京、胡军、韩东君', '陈凯歌、徐克', '176分钟', '2021-09-30', 'https://www.iqiyi.com/v_pzqg2653vk.html?vfm=m_332_bing&fv=8c0aeeff542d8c03');
INSERT INTO `movie` VALUES (3, '大话西游之大圣娶妻', '喜剧', '周星驰，朱茵，吴孟达·', '刘镇伟', '95分钟', '1995-11-09', 'https://www.5yju.com/play-74114-1-1.html');
INSERT INTO `movie` VALUES (4, '分手的决心', '爱情', '汤唯、朴海日', '朴赞郁', '138分钟', '2022-05-23', 'https://www.thanju.com/play/1757/1-1.html');
INSERT INTO `movie` VALUES (5, '控方证人', '悬疑', '泰隆·鲍华、玛琳·黛德丽、查尔斯·劳顿', '比利·怀尔德', '116分钟', '1958-02-06', 'https://www.bilibili.com/bangumi/play/ss34318');
INSERT INTO `movie` VALUES (6, '怒火·重案', '动作', '甄子丹、谢霆锋', '陈木胜', '2小时', '2021-07-30', 'http://sskeikx');
INSERT INTO `movie` VALUES (9, '流浪地球', '科幻', '吴京', '吴京', '2', '2022-11-01', '1111');

-- ----------------------------
-- Table structure for news
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '新闻编号',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新闻标题',
  `newsType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新闻类别',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '新闻主要内容',
  `newsDate` date NULL DEFAULT NULL COMMENT '新闻发生时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of news
-- ----------------------------
INSERT INTO `news` VALUES (1, '俄乌战争全面爆发，世界级灾难来袭，所有人都无法置身事外', '军事', '就在刚刚，一件足以影响全人类的事情发生了！\n\n这绝不是在夸大，而是真真实实的事情。这场危机一旦来临，你我都会受到影响，所有人都无法置身事外。\n\n据央视财经报道，24日深夜，乌军与俄军在乌克兰东北部城市苏梅市中心发生激烈交火。\n\n乌克兰总统泽连斯基表示，在过去一昼夜中，共有137名乌克兰人死亡，316人受伤。\n\n消息一出，全世界都炸锅了。\n\n01\n\n俄罗斯乌克兰开战！\n\n真实战争画面曝光...\n\n\n乌克兰敖德萨军事仓库被导弹击中，画面曝光\n\n\n当地时间25日凌晨，乌克兰首都基辅发生两次巨大爆炸。乌军方表示，爆炸是由战术导弹袭击所致。\n\n一名老妇人情绪崩溃，当街哭泣着喊道：\n\n“我应该向哪跑？我应该去哪？谁能告诉我？天啊！”', '2022-02-28');
INSERT INTO `news` VALUES (2, '歼-20惊现珠海上空 并首次降落珠海国际航展中心', '时事新闻', '11月5日，第十四届中国航展开幕在即，封面新闻记者在现场看到，歼-20四机编队惊现珠海上空，并首次降落珠海国际航展中心。（封面新闻记者 邹阿江 杨峰 魏梦麒 黄芯瑜 赵奕 林珏瑶 珠海报道）\n\n', '2022-11-05');
INSERT INTO `news` VALUES (4, '加快构建网络空间命运共同体', '时事新闻', '11月9日，国家主席习近平向2022年世界互联网大会乌镇峰会致贺信。习近平主席强调，加快构建网络空间命运共同体，为世界和平发展和人类文明进步贡献智慧和力量。习近平主席的贺信引发热烈反响，与会嘉宾纷纷表示，习近平主席的贺信充分体现了对全球数字化、信息化发展浪潮的深刻思考，大家应以此为指引，加强对话交流、深化务实合作，携手构建网络空间命运共同体，开创人类更加美好的未来。\n\n国际社会应携手构建更加公平合理、开放包容、安全稳定、富有生机活力的网络空间\n\n习近平主席指出，面对数字化带来的机遇和挑战，国际社会应加强对话交流、深化务实合作，携手构建更加公平合理、开放包容、安全稳定、富有生机活力的网络空间。\n\n奇安信集团总裁吴云坤说，近年来，集团秉承着互利共赢的原则，与“一带一路”沿线国家和地区开展共筑网络空间安全的探索实践。集团一方面为率先“走出去”的中国企业高质量发展提供技术支持和保障，另一方面还将网络安全方面的先进数字技术与“一带一路”沿线国家和地区的政府与企业共享，为世界和平发展和人类文明进步贡献智慧和力量。\n\n中国现代国际关系研究院科技与网络安全研究所执行所长李艳说：“作为一名长期从事网络空间国际治理的学者，今后，我将继续加强相关问题的研究，为构建更加公平合理、开放包容、安全稳定、富有生机活力的网络空间贡献更多来自中国的智慧。”\n\n对于习近平主席关于“面对数字化带来的机遇和挑战，国际社会应加强对话交流、深化务实合作”的重要论述，外国嘉宾深感赞同。\n\n阿联酋人工智能、数字经济和远程办公实践国务部长欧曼尔·本·苏尔坦·欧莱麦说，面对数字化带来的机遇和挑战，国际社会应该加强对话合作，共同建设一个更加公平合理、开放包容的网络空间。不同国家间应当加强协同合作，让身处其中的人们生活更加安全稳定、美好幸福。\n\n加纳科网董事长尼·奎诺表示，我们拥有一个共同的数字未来，应该加快构建具有共同愿景和未来的网络空间命运共同体，为世界和平发展和人类文明进步贡献智慧和力量。\n\n加快构建网络空间命运共同体，为世界和平发展和人类文明进步贡献智慧和力量\n\n习近平主席强调，中国愿同世界各国一道，携手走出一条数字资源共建共享、数字经济活力迸发、数字治理精准高效、数字文化繁荣发展、数字安全保障有力、数字合作互利共赢的全球数字发展道路，加快构建网络空间命运共同体，为世界和平发展和人类文明进步贡献智慧和力量。\n\n“习近平主席的贺信充分体现了对构建网络空间命运共同体的深邃思考，从六个方面阐述了全球数字发展道路，为我们未来开展数字技术研究、深化数字领域交流合作指明了方向。”之江实验室主任朱世强说，之江实验室也将秉持开放创新的理念，构建智能计算开源生态，推动科学装置和实验平台开放共享，携手国际科技力量共谋数字技术发展，探路数字文明时代。\n\n“习近平主席的贺信向全世界展现了中国在携手构建网络空间命运共同体进程中的大国担当。我们将坚持安全可控和开放创新并重，为构建安全可信的数字世界、世界数字经济蓬勃发展贡献中国力量。”杭州安恒信息技术股份有限公司董事长范渊说。\n\n美中贸易全国委员会会长克雷格·艾伦说，世界需要数据互联，也必须平衡发展和安全，保证国与国之间数据的安全流动。中国近年来一直致力建设网络空间数据安全和隐私管理机制。我们期待加强与中国各界的接触对话，一起建设一个安全稳定的网络空间。\n\n数字技术日益融入经济社会发展各领域全过程，深刻改变着生产方式、生活方式和社会治理方式\n\n习近平主席指出，当今时代，数字技术作为世界科技革命和产业变革的先导力量，日益融入经济社会发展各领域全过程，深刻改变着生产方式、生活方式和社会治理方式。\n\n“习近平主席的贺信中关于数字技术的论述高瞻远瞩。”浙江清华长三角研究院智库中心主任李继春说，数字技术的快速发展，对科技成果转化等提出了更高要求。作为省校共建新型创新载体，浙江清华长三角研究院今后将进一步完善科技成果转化、产业培育等专业体系，助力数字技术融入经济社会发展各领域全过程，推动生产方式、生活方式和社会治理方式发展。\n\n“习近平主席强调，数字技术作为世界科技革命和产业变革的先导力量，日益融入经济社会发展各领域全过程。”浙江省医疗保障局党组成员、副局长曹岑表示，近年来，浙江省加快推进“互联网+医保”应用，贺信给浙江省进一步推动“智慧医保”建设指明了方向，浙江省相关部门将进一步聚焦数据资源共建共享，推动系统重塑、流程再造，使数据治理更精准高效、服务群众更便捷便利。\n\n世界互联网大会副理事长弗朗西斯·高锐说，数字技术改变了诸多社会特征，也为社会发展拓展了全新的维度。同时，互联网及数字技术还促进了国际科学合作，有助于实现更为丰富的科学创新。世界互联网大会聚集了多领域的国际专家，为讨论技术等领域问题提供了平台。\n\n数字技术给人类生产生活带来广泛而深刻的影响，相关产业的从业者们对此深有体会。阿里巴巴集团董事会主席兼首席执行官张勇说，阿里巴巴将深入贯彻习近平主席贺信要求，努力扎根实体经济，利用好数字技术帮助中小企业发展，推动产业变革加速到来。腾讯集团高级副总裁、首席人才官奚丹表示，习近平主席在贺信中指出，“数字技术作为世界科技革命和产业变革的先导力量，日益融入经济社会发展各领域全过程”，这让互联网从业者倍感振奋。未来腾讯将与数字经济行业生态建设者们一起，推动数字技术应用进一步普惠化，共同助力网络强国与数字中国建设。\n\n(本报记者江南、金歆、李晓宏、史哲、王洲、窦瀚洋)', '2021-11-10');
INSERT INTO `news` VALUES (7, '法国25人大名单出炉：姆巴佩领衔，博格巴坎特无缘，7大中卫在列', '体育', '今天凌晨，法国队公布了25人参加世界杯的大名单，这份名单基本上符合外界的预期，之前受伤的瓦拉内入围，而博格巴、坎特和迈尼昂则因伤缺阵，皇马边后卫门迪无缘。值得一提的是，在中后卫位置十分富裕的法国队，一下子带了7名中卫，反倒是边后卫有些缺人。\n\n法国25人大名单出炉：姆巴佩领衔，博格巴坎特无缘，7大中卫在列\n在大名单公布之前，就有消息称，尽管法国队人才济济，但是德尚将不会用满26人名额，最终悬念揭晓，德尚果然只用了25个名额。在世界杯前，多名法国队大将受伤，包括博格巴、坎特这样的绝对主力，最终这2名中场核心无法在世界杯前复出，无缘大名单，AC米兰门将迈尼昂同样如此。\n\n法国25人大名单出炉：姆巴佩领衔，博格巴坎特无缘，7大中卫在列\n与相对薄弱的中场相比，法国队的中后卫位置才是人才济济，瓦拉内、孔德、金彭贝、萨利巴、卢卡斯、科纳特和于帕梅卡诺等7名中卫入选，帕瓦尔也是中卫出身，在法国队踢主力右后卫，只有特奥一名纯正的边后卫入选，皇马左后卫门迪没有入围，有些意外。\n\n法国25人大名单出炉：姆巴佩领衔，博格巴坎特无缘，7大中卫在列\n锋线上，姆巴佩、本泽马、格里兹曼等人均顺利入选，其他名额给了科曼、吉鲁、登贝莱和恩昆库，阵容豪华。相对来说，中场几乎全是小鬼当家，相比上届世界杯时的博格巴、坎特和马图伊迪的攻守全面的组合，差距不小。\n\n法国25人大名单出炉：姆巴佩领衔，博格巴坎特无缘，7大中卫在列\n法国队大名单：\n\n门将：洛里（35岁/热刺）、曼丹达（37岁/雷恩）、阿雷奥拉（29岁/西汉姆）；\n\n后卫：帕瓦尔（26岁/拜仁）、孔德（23岁/巴萨）、瓦拉内（29岁/曼联）、金彭贝（27岁/巴黎）、萨利巴（21岁/阿森纳）、卢卡斯（26岁/拜仁）、特奥（25岁/AC米兰）、科纳特（23岁/利物浦）、于帕梅卡诺（24岁/拜仁）；\n\n中场：拉比奥特（27岁/尤文）、琼阿梅尼（22岁/皇马）、尤索夫-弗法纳（摩纳哥/23岁）、贡多齐（23岁/马赛）、 韦勒图（29岁/马赛）、卡马文加（20岁/皇马）；\n\n前锋：科曼（26岁/拜仁）、姆巴佩（23岁/巴黎）、本泽马（34岁/皇马）、吉鲁（36岁/AC米兰）、格列兹曼（31岁/马竞）、登贝莱（25岁/巴萨）、恩昆库（24岁/莱比锡）。', '2022-11-10');
INSERT INTO `news` VALUES (8, '国家卫健委：昨日新增1133例本土确诊病例、7691例本土无症状感染者', '时事新闻', '11月9日0—24时，31个省（自治区、直辖市）和新疆生产建设兵团报告新增确诊病例1185例。其中境外输入病例52例，含1例由无症状感染者转为确诊病例；本土病例1133例（广东500例，河南178例，重庆123例，内蒙古107例，四川48例，山西35例，北京34例，新疆32例，云南14例，湖南13例，陕西12例，黑龙江9例，甘肃7例，山东6例，辽宁4例，浙江3例，天津2例，福建2例，江苏1例，贵州1例，西藏1例，青海1例），含330例由无症状感染者转为确诊病例（广东300例，四川13例，重庆3例，甘肃3例，浙江2例，河南2例，湖南2例，内蒙古1例，福建1例，云南1例，西藏1例，陕西1例）。无新增死亡病例。无新增疑似病例。\n\n当日新增治愈出院病例343例，其中境外输入病例44例，本土病例299例（内蒙古108例，广东59例，福建22例，陕西17例，西藏15例，江苏12例，重庆12例，北京9例，山西8例，黑龙江8例，新疆7例，青海4例，宁夏4例，云南3例，辽宁2例，浙江2例，湖南2例，贵州2例，河北1例，河南1例，四川1例），解除医学观察的密切接触者64127人，重症病例较前一日增加4例。\n\n境外输入现有确诊病例520例（无重症病例），无现有疑似病例。累计确诊病例26256例，累计治愈出院病例25736例，无死亡病例。\n\n截至11月9日24时，据31个省（自治区、直辖市）和新疆生产建设兵团报告，现有确诊病例9155例（其中重症病例31例），累计治愈出院病例253163例，累计死亡病例5226例，累计报告确诊病例267544例，无现有疑似病例。累计追踪到密切接触者8598731人，尚在医学观察的密切接触者744011人。\n\n31个省（自治区、直辖市）和新疆生产建设兵团报告新增无症状感染者7820例，其中境外输入129例，本土7691例（广东2507例，河南1065例，内蒙古1019例，新疆657例，重庆633例，黑龙江325例，青海276例，甘肃237例，山西152例，湖北113例，湖南94例，河北91例，云南91例，四川74例，北京61例，山东53例，安徽43例，福建43例，天津37例，陕西37例，辽宁16例，江西12例，西藏11例，江苏9例，宁夏9例，贵州7例，吉林6例，广西6例，上海4例，浙江3例）。\n\n当日解除医学观察的无症状感染者1686例，其中境外输入124例，本土1562例（内蒙古488例，西藏383例，新疆134例，广东127例，山东50例，福建45例，黑龙江43例，云南43例，陕西40例，四川36例，河北26例，青海26例，天津25例，湖北20例，山西19例，江苏14例，上海9例，辽宁8例，重庆8例，浙江5例，安徽3例，河南3例，江西2例，广西2例，湖南1例，贵州1例，宁夏1例）；当日转为确诊病例331例（境外输入1例）；尚在医学观察的无症状感染者52411例（境外输入1119例）。\n\n累计收到港澳台地区通报确诊病例8395118例。其中，香港特别行政区440438例（出院93420例，死亡10492例），澳门特别行政区795例（出院789例，死亡6例），台湾地区7953885例（出院13742例，死亡13313例）。（总台央视记者龙晓勤）', '2022-11-10');
INSERT INTO `news` VALUES (9, '俄军宣布从这里撤军！战局转折点来了？', '军事', '　综合报道，当地时间9日，俄罗斯国防部长绍伊古下令俄军从第聂伯河右岸向左岸转移，撤出驻赫尔松的俄军。\n\n　　俄罗斯对乌特别军事行动区域联合部队总指挥苏罗维金表示，在全面评估现有情况后，建议沿第聂伯河左岸组织防御。\n\n 资料图：苏罗维金。资料图：苏罗维金。\n　　为何突然宣布撤军？\n\n　　苏罗维金称，乌克兰武装部队会继续炮击卡霍夫卡大坝，这可能会引发洪水并造成大量伤亡。为了避免在第聂伯河右岸的军队陷入孤立，决定将其撤离。他表示，这是一个“艰难的决定”。\n\n　　据俄媒介绍，不止是军队，10月18日至11月7日，赫尔松州还组织了第聂伯河右岸的平民向左岸转移。目前已有11.5万余人离开军事行动区。\n\n　　一直积极关注前线局势的俄罗斯车臣共和国领导人卡德罗夫对俄军撤退的决定表示赞同，他认为，在权衡各方意见后，可以说在“毫无意义牺牲和拯救士兵生命之间”做出的决定是正确的选择。\n\n　　法国国际广播电台（RFI）则称，这一行动是俄罗斯最重要的撤退之一，可以说是俄乌战场局势的潜在转折点。\n\n　　北约秘书长斯托尔滕贝格表示，已经知晓有关赫尔松的声明，正在等待当地局势发展的消息。\n\n　　美国总统拜登则表示，撤退意味着俄方在战场上存在“真正的问题”。他还表示，在第聂伯河将两军分开后，莫斯科和基辅将“舔舐伤口”，决定将在冬季采取哪些行动。\n\n 11月9日，乌军使用自行火炮射击。11月9日，乌军使用自行火炮射击。\n　　乌媒称赫尔松仍有俄军存在\n\n　　另据乌通社报道，俄方宣布撤军后，乌总统办公室顾问波多利亚克表示，现在讨论俄罗斯撤军还“为时过早”。\n\n　　乌通社在报道中援引消息人士称，该市有许多俄罗斯军队人员，他们大多位于城市的工业区，一些废弃工厂及周边，那里没有当地居民，没有通信，行踪不易暴露。此外，俄罗斯军方正在距离城市20公里处部署阵地。\n\n　　赫尔松还发生了什么？\n\n　　- 据俄媒报道，赫尔松副州长斯特列穆索夫9日在一场车祸中去世。斯特列穆索夫1976年出生于顿涅斯克地区，毕业于西乌克兰国立大学。今年以来，他成为俄罗斯控制的赫尔松州领导人之一，并于9月28日领取了俄罗斯联邦公民护照。\n\n　　斯特列穆索夫去世后，普京向其追授勇气勋章。\n\n 资料图：赫尔松州副州长斯特列穆索夫 图片来源：俄新社资料图：赫尔松州副州长斯特列穆索夫 图片来源：俄新社\n\n　　- 据乌媒消息，俄方宣布撤军的同日，乌克兰总统泽连斯基签署了一项关于在赫尔松地区建立四个军事管理机构的法令。\n\n责任编辑：张迪', '2022-11-10');

-- ----------------------------
-- Table structure for staff
-- ----------------------------
DROP TABLE IF EXISTS `staff`;
CREATE TABLE `staff`  (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `sex` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `tel` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of staff
-- ----------------------------
INSERT INTO `staff` VALUES (2, '2', '2', '2');
INSERT INTO `staff` VALUES (5, NULL, NULL, NULL);
INSERT INTO `staff` VALUES (6, NULL, NULL, NULL);
INSERT INTO `staff` VALUES (7, NULL, NULL, NULL);
INSERT INTO `staff` VALUES (8, NULL, NULL, NULL);
INSERT INTO `staff` VALUES (9, '10', '10', '10');

-- ----------------------------
-- Table structure for submenu
-- ----------------------------
DROP TABLE IF EXISTS `submenu`;
CREATE TABLE `submenu`  (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `mid` int(50) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `mid`(`mid`) USING BTREE,
  CONSTRAINT `submenu_ibfk_1` FOREIGN KEY (`mid`) REFERENCES `mainmenu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 602 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of submenu
-- ----------------------------
INSERT INTO `submenu` VALUES (101, '用户列表', '/user', 100);
INSERT INTO `submenu` VALUES (201, '类别列表', '/type', 200);
INSERT INTO `submenu` VALUES (202, '电影列表', '/movie', 200);
INSERT INTO `submenu` VALUES (301, '新闻列表', '/news', 300);
INSERT INTO `submenu` VALUES (401, '观看视频', '/welcome', 400);
INSERT INTO `submenu` VALUES (501, '流量图表', '/charts', 500);
INSERT INTO `submenu` VALUES (601, '聊天室', '/chat', 600);

-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '电影类别编号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电影类别名字',
  `createUser` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `createDate` date NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type` VALUES (8, '仙侠', '52-张义龙', '2022-11-03');
INSERT INTO `type` VALUES (12, '喜剧', '55-张志毅', '2022-11-03');
INSERT INTO `type` VALUES (13, '动漫', '52-张赛维', '2022-11-04');
INSERT INTO `type` VALUES (20, '动作', '55-张志毅', '2022-11-04');
INSERT INTO `type` VALUES (21, '古装', '52-张赛威', '2022-11-04');
INSERT INTO `type` VALUES (22, '爱情', '55-张志毅', '2022-11-03');
INSERT INTO `type` VALUES (23, '科幻', '55-张志毅', '2022-11-07');
INSERT INTO `type` VALUES (24, '悬疑', '54-张义龙', '2022-11-08');
INSERT INTO `type` VALUES (25, '军事', '55-张志毅', '2022-11-04');
INSERT INTO `type` VALUES (28, '1', '1', '2022-11-09');
INSERT INTO `type` VALUES (29, '1', '1', '2022-11-03');
INSERT INTO `type` VALUES (30, '2', '2', '2022-11-03');

SET FOREIGN_KEY_CHECKS = 1;
