-- ============================================================
-- 拾词·小学英语词汇数据（人教版PEP 3-6年级）
-- 共 38 章，661 词
-- ============================================================

INSERT INTO wp_category (id, name, icon, sort) VALUES
(1, '小学', NULL, 1);

INSERT INTO wp_chapter (id, category_id, name, sort) VALUES
( 1, 1, '（一）学习文具与教室',  1),
( 2, 1, '（二）身体部位',  2),
( 3, 1, '（三）颜色与数字',  3),
( 4, 1, '（四）动物（宠物与野生）',  4),
( 5, 1, '（五）食物与饮料',  5),
( 6, 1, '（六）水果与蔬菜',  6),
( 7, 1, '（七）家庭成员',  7),
( 8, 1, '（八）家的房间与家具',  8),
( 9, 1, '（九）方位与位置',  9),
(10, 1, '（十）学校场所', 10),
(11, 1, '（十一）时间与作息', 11),
(12, 1, '（十二）天气与季节', 12),
(13, 1, '（十三）服装', 13),
(14, 1, '（十四）购物与价格', 14),
(15, 1, '（十五）描述外貌与性格', 15),
(16, 1, '（十六）能力与家务', 16),
(17, 1, '（十七）自然景物', 17),
(18, 1, '（十八）月份、星期与节假日', 18),
(19, 1, '（十九）问路、交通与方向', 19),
(20, 1, '（二十）兴趣爱好与职业', 20),
(21, 1, '（二十一）学科与课程', 21),
(22, 1, '（二十二）运动与体育', 22),
(23, 1, '（二十三）情绪与感受', 23),
(24, 1, '（二十四）国家与语言', 24),
(25, 1, '（二十五）健康与疾病', 25),
(26, 1, '（二十六）问候与日常用语', 26),
(27, 1, '（二十七）数字扩展（十三至一百）', 27),
(28, 1, '（二十八）公共场所与社区', 28),
(29, 1, '（二十九）人物与称谓', 29),
(30, 1, '（三十）常用形容词与副词', 30),
(31, 1, '（三十一）基础动词', 31),
(32, 1, '（三十二）疑问词', 32),
(33, 1, '（三十三）核心动词（续）', 33),
(34, 1, '（三十四）基础形容词（续）', 34),
(35, 1, '（三十五）时间词（续）', 35),
(36, 1, '（三十六）日用品', 36),
(37, 1, '（三十七）更多食物', 37),
(38, 1, '（三十八）更多动物', 38);

-- ------------------------------------------------------------
-- 第1章：学习文具与教室
-- ------------------------------------------------------------
INSERT INTO wp_word (id, chapter_id, word, phonetic_us, phonetic_uk, sort) VALUES
(  1,  1, 'pen', '/pɛn/', '/pɛn/',  1),
(  2,  1, 'pencil', '/ˈpɛnsəl/', '/ˈpɛnsɪl/',  2),
(  3,  1, 'pencil-case', '/ˈpɛnsəl keɪs/', '/ˈpɛnsɪl keɪs/',  3),
(  4,  1, 'ruler', '/ˈruːlər/', '/ˈruːlə/',  4),
(  5,  1, 'eraser', '/ɪˈreɪzər/', '/ɪˈreɪzə/',  5),
(  6,  1, 'crayon', '/ˈkreɪɑːn/', '/ˈkreɪən/',  6),
(  7,  1, 'book', '/bʊk/', '/bʊk/',  7),
(  8,  1, 'bag', '/bæɡ/', '/bæɡ/',  8),
(  9,  1, 'sharpener', '/ˈʃɑːrpənər/', '/ˈʃɑːpənə/',  9),
( 10,  1, 'school', '/skuːl/', '/skuːl/', 10),
( 11,  1, 'window', '/ˈwɪndoʊ/', '/ˈwɪndəʊ/', 11),
( 12,  1, 'blackboard', '/ˈblækbɔːrd/', '/ˈblækbɔːd/', 12),
( 13,  1, 'light', '/laɪt/', '/laɪt/', 13),
( 14,  1, 'picture', '/ˈpɪktʃər/', '/ˈpɪktʃə/', 14),
( 15,  1, 'door', '/dɔːr/', '/dɔː/', 15),
( 16,  1, 'floor', '/flɔːr/', '/flɔː/', 16),
( 17,  1, 'classroom', '/ˈklæsruːm/', '/ˈklɑːsruːm/', 17),
( 18,  1, 'computer', '/kəmˈpjuːtər/', '/kəmˈpjuːtə/', 18),
( 19,  1, 'desk', '/dɛsk/', '/dɛsk/', 19),
( 20,  1, 'chair', '/tʃɛr/', '/tʃɛə/', 20),
( 21,  1, 'wall', '/wɔːl/', '/wɔːl/', 21),
( 22,  1, 'fan', '/fæn/', '/fæn/', 22),
( 23,  1, 'notebook', '/ˈnoʊtbʊk/', '/ˈnəʊtbʊk/', 23),
( 24,  1, 'Chinese book', '/ˈtʃaɪniːz bʊk/', '/ˈtʃaɪniːz bʊk/', 24),
( 25,  1, 'English book', '/ˈɪŋɡlɪʃ bʊk/', '/ˈɪŋɡlɪʃ bʊk/', 25);

INSERT INTO wp_word_sense (id, word_id, pos, meaning, sort) VALUES
(  1,   1, 'n.', '钢笔', 1),
(  2,   2, 'n.', '铅笔', 1),
(  3,   3, 'n.', '铅笔盒', 1),
(  4,   4, 'n.', '尺子', 1),
(  5,   5, 'n.', '橡皮擦', 1),
(  6,   6, 'n.', '蜡笔', 1),
(  7,   7, 'n.', '书', 1),
(  8,   8, 'n.', '书包', 1),
(  9,   9, 'n.', '卷笔刀', 1),
( 10,  10, 'n.', '学校', 1),
( 11,  11, 'n.', '窗户', 1),
( 12,  12, 'n.', '黑板', 1),
( 13,  13, 'n.', '灯', 1),
( 14,  14, 'n.', '图画；图片', 1),
( 15,  15, 'n.', '门', 1),
( 16,  16, 'n.', '地板', 1),
( 17,  17, 'n.', '教室', 1),
( 18,  18, 'n.', '电脑', 1),
( 19,  19, 'n.', '课桌', 1),
( 20,  20, 'n.', '椅子', 1),
( 21,  21, 'n.', '墙', 1),
( 22,  22, 'n.', '电扇', 1),
( 23,  23, 'n.', '笔记本', 1),
( 24,  24, 'n.', '语文书', 1),
( 25,  25, 'n.', '英语书', 1);

INSERT INTO wp_word_example (id, word_id, sentence, translation, sort) VALUES
(  1,   1, 'This is my pen.', '这是我的钢笔。', 1),
(  2,   2, 'I have a pencil in my hand.', '我手里有一支铅笔。', 1),
(  3,   3, 'My pencil-case is blue.', '我的铅笔盒是蓝色的。', 1),
(  4,   4, 'Can I use your ruler?', '我可以用一下你的尺子吗？', 1),
(  5,   5, 'I need an eraser.', '我需要一块橡皮。', 1),
(  6,   6, 'She is drawing with a crayon.', '她正在用蜡笔画画。', 1),
(  7,   7, 'Please open your book.', '请打开你的书。', 1),
(  8,   8, 'My bag is heavy today.', '我今天的书包很重。', 1),
(  9,   9, 'Where is my sharpener?', '我的卷笔刀在哪里？', 1),
( 10,  10, 'I go to school every day.', '我每天去上学。', 1),
( 11,  11, 'Please open the window.', '请把窗户打开。', 1),
( 12,  12, 'Look at the blackboard, please.', '请看黑板。', 1),
( 13,  13, 'Please turn on the light.', '请把灯打开。', 1),
( 14,  14, 'There is a picture on the wall.', '墙上有一幅图画。', 1),
( 15,  15, 'Close the door, please.', '请关上门。', 1),
( 16,  16, 'The floor is clean.', '地板很干净。', 1),
( 17,  17, 'This is our classroom.', '这是我们的教室。', 1),
( 18,  18, 'We have a computer in our classroom.', '我们教室里有一台电脑。', 1),
( 19,  19, 'Put your book on the desk.', '把书放在课桌上。', 1),
( 20,  20, 'Please sit on the chair.', '请坐在椅子上。', 1),
( 21,  21, 'The map is on the wall.', '地图在墙上。', 1),
( 22,  22, 'Turn on the fan, please.', '请把电扇打开。', 1),
( 23,  23, 'I write notes in my notebook.', '我在笔记本上记笔记。', 1),
( 24,  24, 'I left my Chinese book at home.', '我把语文书忘在家里了。', 1),
( 25,  25, 'Do you have your English book?', '你带英语书了吗？', 1);

-- ------------------------------------------------------------
-- 第2章：身体部位
-- ------------------------------------------------------------
INSERT INTO wp_word (id, chapter_id, word, phonetic_us, phonetic_uk, sort) VALUES
( 26,  2, 'head', '/hɛd/', '/hɛd/',  1),
( 27,  2, 'face', '/feɪs/', '/feɪs/',  2),
( 28,  2, 'nose', '/noʊz/', '/nəʊz/',  3),
( 29,  2, 'mouth', '/maʊθ/', '/maʊθ/',  4),
( 30,  2, 'eye', '/aɪ/', '/aɪ/',  5),
( 31,  2, 'ear', '/ɪr/', '/ɪə/',  6),
( 32,  2, 'arm', '/ɑrm/', '/ɑːm/',  7),
( 33,  2, 'finger', '/ˈfɪŋɡər/', '/ˈfɪŋɡə/',  8),
( 34,  2, 'leg', '/lɛɡ/', '/lɛɡ/',  9),
( 35,  2, 'foot', '/fʊt/', '/fʊt/', 10),
( 36,  2, 'body', '/ˈbɑdi/', '/ˈbɒdi/', 11),
( 37,  2, 'hand', '/hænd/', '/hænd/', 12),
( 38,  2, 'tooth', '/tuːθ/', '/tuːθ/', 13),
( 39,  2, 'hair', '/hɛr/', '/hɛə/', 14),
( 40,  2, 'neck', '/nɛk/', '/nɛk/', 15),
( 41,  2, 'shoulder', '/ˈʃoʊldər/', '/ˈʃəʊldə/', 16),
( 42,  2, 'knee', '/niː/', '/niː/', 17),
( 43,  2, 'back', '/bæk/', '/bæk/', 18),
( 44,  2, 'toe', '/toʊ/', '/təʊ/', 19),
( 45,  2, 'stomach', '/ˈstʌmək/', '/ˈstʌmək/', 20);

INSERT INTO wp_word_sense (id, word_id, pos, meaning, sort) VALUES
( 26,  26, 'n.', '头', 1),
( 27,  27, 'n.', '脸', 1),
( 28,  28, 'n.', '鼻子', 1),
( 29,  29, 'n.', '嘴', 1),
( 30,  30, 'n.', '眼睛', 1),
( 31,  31, 'n.', '耳朵', 1),
( 32,  32, 'n.', '手臂', 1),
( 33,  33, 'n.', '手指', 1),
( 34,  34, 'n.', '腿', 1),
( 35,  35, 'n.', '脚', 1),
( 36,  36, 'n.', '身体', 1),
( 37,  37, 'n.', '手', 1),
( 38,  38, 'n.', '牙齿', 1),
( 39,  39, 'n.', '头发', 1),
( 40,  40, 'n.', '脖子', 1),
( 41,  41, 'n.', '肩膀', 1),
( 42,  42, 'n.', '膝盖', 1),
( 43,  43, 'n.', '背部', 1),
( 44,  44, 'n.', '脚趾', 1),
( 45,  45, 'n.', '肚子', 1);

INSERT INTO wp_word_example (id, word_id, sentence, translation, sort) VALUES
( 26,  26, 'I have a big head.', '我有一个大脑袋。', 1),
( 27,  27, 'She has a round face.', '她有一张圆脸。', 1),
( 28,  28, 'My nose is small.', '我的鼻子很小。', 1),
( 29,  29, 'Open your mouth, please.', '请张开你的嘴。', 1),
( 30,  30, 'I have two eyes.', '我有两只眼睛。', 1),
( 31,  31, 'The rabbit has long ears.', '兔子有长耳朵。', 1),
( 32,  32, 'Raise your right arm.', '举起你的右臂。', 1),
( 33,  33, 'I have ten fingers.', '我有十根手指。', 1),
( 34,  34, 'My leg hurts.', '我的腿疼。', 1),
( 35,  35, 'My left foot is big.', '我的左脚很大。', 1),
( 36,  36, 'Touch your body.', '摸摸你的身体。', 1),
( 37,  37, 'Wash your hands before eating.', '吃饭前洗手。', 1),
( 38,  38, 'Brush your teeth every day.', '每天刷牙。', 1),
( 39,  39, 'She has long black hair.', '她有一头乌黑的长发。', 1),
( 40,  40, 'My neck is sore.', '我的脖子酸痛。', 1),
( 41,  41, 'He pats me on the shoulder.', '他拍拍我的肩膀。', 1),
( 42,  42, 'Bend your knees.', '弯曲你的膝盖。', 1),
( 43,  43, 'I have a pain in my back.', '我背部疼痛。', 1),
( 44,  44, 'I have five toes on each foot.', '我每只脚有五个脚趾。', 1),
( 45,  45, 'My stomach is full.', '我的肚子饱了。', 1);

-- ------------------------------------------------------------
-- 第3章：颜色与数字
-- ------------------------------------------------------------
INSERT INTO wp_word (id, chapter_id, word, phonetic_us, phonetic_uk, sort) VALUES
( 46,  3, 'red', '/rɛd/', '/rɛd/',  1),
( 47,  3, 'yellow', '/ˈjɛloʊ/', '/ˈjɛləʊ/',  2),
( 48,  3, 'green', '/ɡriːn/', '/ɡriːn/',  3),
( 49,  3, 'blue', '/bluː/', '/bluː/',  4),
( 50,  3, 'purple', '/ˈpɜrpəl/', '/ˈpɜːpəl/',  5),
( 51,  3, 'white', '/waɪt/', '/waɪt/',  6),
( 52,  3, 'black', '/blæk/', '/blæk/',  7),
( 53,  3, 'orange', '/ˈɔrɪndʒ/', '/ˈɒrɪndʒ/',  8),
( 54,  3, 'pink', '/pɪŋk/', '/pɪŋk/',  9),
( 55,  3, 'brown', '/braʊn/', '/braʊn/', 10),
( 56,  3, 'one', '/wʌn/', '/wʌn/', 11),
( 57,  3, 'two', '/tuː/', '/tuː/', 12),
( 58,  3, 'three', '/θriː/', '/θriː/', 13),
( 59,  3, 'four', '/fɔr/', '/fɔː/', 14),
( 60,  3, 'five', '/faɪv/', '/faɪv/', 15),
( 61,  3, 'six', '/sɪks/', '/sɪks/', 16),
( 62,  3, 'seven', '/ˈsɛvən/', '/ˈsɛvən/', 17),
( 63,  3, 'eight', '/eɪt/', '/eɪt/', 18),
( 64,  3, 'nine', '/naɪn/', '/naɪn/', 19),
( 65,  3, 'ten', '/tɛn/', '/tɛn/', 20),
( 66,  3, 'eleven', '/ɪˈlɛvən/', '/ɪˈlɛvən/', 21),
( 67,  3, 'twelve', '/twɛlv/', '/twɛlv/', 22);

INSERT INTO wp_word_sense (id, word_id, pos, meaning, sort) VALUES
( 46,  46, 'adj.', '红色的', 1),
( 47,  47, 'adj.', '黄色的', 1),
( 48,  48, 'adj.', '绿色的', 1),
( 49,  49, 'adj.', '蓝色的', 1),
( 50,  50, 'adj.', '紫色的', 1),
( 51,  51, 'adj.', '白色的', 1),
( 52,  52, 'adj.', '黑色的', 1),
( 53,  53, 'adj.', '橙色的', 1),
( 54,  54, 'adj.', '粉红色的', 1),
( 55,  55, 'adj.', '棕色的', 1),
( 56,  56, 'num.', '一', 1),
( 57,  57, 'num.', '二', 1),
( 58,  58, 'num.', '三', 1),
( 59,  59, 'num.', '四', 1),
( 60,  60, 'num.', '五', 1),
( 61,  61, 'num.', '六', 1),
( 62,  62, 'num.', '七', 1),
( 63,  63, 'num.', '八', 1),
( 64,  64, 'num.', '九', 1),
( 65,  65, 'num.', '十', 1),
( 66,  66, 'num.', '十一', 1),
( 67,  67, 'num.', '十二', 1);

INSERT INTO wp_word_example (id, word_id, sentence, translation, sort) VALUES
( 46,  46, 'The apple is red.', '苹果是红色的。', 1),
( 47,  47, 'The sun is yellow.', '太阳是黄色的。', 1),
( 48,  48, 'The grass is green.', '草是绿色的。', 1),
( 49,  49, 'The sky is blue.', '天空是蓝色的。', 1),
( 50,  50, 'Her bag is purple.', '她的包是紫色的。', 1),
( 51,  51, 'Snow is white.', '雪是白色的。', 1),
( 52,  52, 'My cat is black.', '我的猫是黑色的。', 1),
( 53,  53, 'I like orange juice.', '我喜欢橙汁。', 1),
( 54,  54, 'She wears a pink dress.', '她穿一件粉红色的裙子。', 1),
( 55,  55, 'The bear is brown.', '那只熊是棕色的。', 1),
( 56,  56, 'I have one book.', '我有一本书。', 1),
( 57,  57, 'There are two cats.', '有两只猫。', 1),
( 58,  58, 'I can see three birds.', '我能看见三只鸟。', 1),
( 59,  59, 'She has four pencils.', '她有四支铅笔。', 1),
( 60,  60, 'We have five fingers on one hand.', '我们一只手有五根手指。', 1),
( 61,  61, 'There are six eggs in the box.', '盒子里有六个鸡蛋。', 1),
( 62,  62, 'Seven days make a week.', '七天是一周。', 1),
( 63,  63, 'I am eight years old.', '我八岁了。', 1),
( 64,  64, 'There are nine apples on the table.', '桌子上有九个苹果。', 1),
( 65,  65, 'I can count to ten.', '我能数到十。', 1),
( 66,  66, 'There are eleven students in the room.', '房间里有十一个学生。', 1),
( 67,  67, 'December is the twelfth month.', '十二月是第十二个月。', 1);

-- ------------------------------------------------------------
-- 第4章：动物（宠物与野生
-- ------------------------------------------------------------
INSERT INTO wp_word (id, chapter_id, word, phonetic_us, phonetic_uk, sort) VALUES
( 68,  4, 'cat', '/kæt/', '/kæt/',  1),
( 69,  4, 'dog', '/dɔɡ/', '/dɒɡ/',  2),
( 70,  4, 'monkey', '/ˈmʌŋki/', '/ˈmʌŋki/',  3),
( 71,  4, 'panda', '/ˈpændə/', '/ˈpændə/',  4),
( 72,  4, 'rabbit', '/ˈræbɪt/', '/ˈræbɪt/',  5),
( 73,  4, 'duck', '/dʌk/', '/dʌk/',  6),
( 74,  4, 'pig', '/pɪɡ/', '/pɪɡ/',  7),
( 75,  4, 'bird', '/bɜrd/', '/bɜːd/',  8),
( 76,  4, 'bear', '/bɛr/', '/bɛə/',  9),
( 77,  4, 'elephant', '/ˈɛləfənt/', '/ˈɛlɪfənt/', 10),
( 78,  4, 'mouse', '/maʊs/', '/maʊs/', 11),
( 79,  4, 'fish', '/fɪʃ/', '/fɪʃ/', 12),
( 80,  4, 'tiger', '/ˈtaɪɡər/', '/ˈtaɪɡə/', 13),
( 81,  4, 'lion', '/ˈlaɪən/', '/ˈlaɪən/', 14),
( 82,  4, 'giraffe', '/dʒəˈræf/', '/dʒɪˈrɑːf/', 15),
( 83,  4, 'horse', '/hɔrs/', '/hɔːs/', 16),
( 84,  4, 'cow', '/kaʊ/', '/kaʊ/', 17),
( 85,  4, 'hen', '/hɛn/', '/hɛn/', 18),
( 86,  4, 'sheep', '/ʃiːp/', '/ʃiːp/', 19),
( 87,  4, 'frog', '/frɔɡ/', '/frɒɡ/', 20);

INSERT INTO wp_word_sense (id, word_id, pos, meaning, sort) VALUES
( 68,  68, 'n.', '猫', 1),
( 69,  69, 'n.', '狗', 1),
( 70,  70, 'n.', '猴子', 1),
( 71,  71, 'n.', '熊猫', 1),
( 72,  72, 'n.', '兔子', 1),
( 73,  73, 'n.', '鸭子', 1),
( 74,  74, 'n.', '猪', 1),
( 75,  75, 'n.', '鸟', 1),
( 76,  76, 'n.', '熊', 1),
( 77,  77, 'n.', '大象', 1),
( 78,  78, 'n.', '老鼠', 1),
( 79,  79, 'n.', '鱼', 1),
( 80,  80, 'n.', '老虎', 1),
( 81,  81, 'n.', '狮子', 1),
( 82,  82, 'n.', '长颈鹿', 1),
( 83,  83, 'n.', '马', 1),
( 84,  84, 'n.', '奶牛', 1),
( 85,  85, 'n.', '母鸡', 1),
( 86,  86, 'n.', '绵羊', 1),
( 87,  87, 'n.', '青蛙', 1);

INSERT INTO wp_word_example (id, word_id, sentence, translation, sort) VALUES
( 68,  68, 'The cat is on the sofa.', '猫在沙发上。', 1),
( 69,  69, 'I have a pet dog.', '我有一只宠物狗。', 1),
( 70,  70, 'The monkey likes bananas.', '猴子喜欢香蕉。', 1),
( 71,  71, 'The panda is from China.', '熊猫来自中国。', 1),
( 72,  72, 'The rabbit has white fur.', '兔子有白色的毛。', 1),
( 73,  73, 'The duck swims in the pond.', '鸭子在池塘里游泳。', 1),
( 74,  74, 'The pig is fat and pink.', '猪又胖又粉。', 1),
( 75,  75, 'A bird is singing in the tree.', '一只鸟在树上唱歌。', 1),
( 76,  76, 'The bear likes honey.', '熊喜欢蜂蜜。', 1),
( 77,  77, 'The elephant has a long nose.', '大象有一个长鼻子。', 1),
( 78,  78, 'The mouse is very small.', '老鼠非常小。', 1),
( 79,  79, 'I can see fish in the water.', '我能看到水里的鱼。', 1),
( 80,  80, 'The tiger is a big cat.', '老虎是一种大型猫科动物。', 1),
( 81,  81, 'The lion is the king of animals.', '狮子是动物之王。', 1),
( 82,  82, 'The giraffe has a very long neck.', '长颈鹿有一条非常长的脖子。', 1),
( 83,  83, 'The horse runs very fast.', '马跑得很快。', 1),
( 84,  84, 'The cow gives us milk.', '奶牛给我们提供牛奶。', 1),
( 85,  85, 'The hen lays eggs every day.', '母鸡每天下蛋。', 1),
( 86,  86, 'The sheep has soft white wool.', '绵羊有柔软的白色羊毛。', 1),
( 87,  87, 'The frog jumps into the pond.', '青蛙跳进了池塘。', 1);

-- ------------------------------------------------------------
-- 第5章：食物与饮料
-- ------------------------------------------------------------
INSERT INTO wp_word (id, chapter_id, word, phonetic_us, phonetic_uk, sort) VALUES
( 88,  5, 'cake', '/keɪk/', '/keɪk/',  1),
( 89,  5, 'bread', '/brɛd/', '/brɛd/',  2),
( 90,  5, 'hot dog', '/ˈhɑt dɔɡ/', '/ˈhɒt dɒɡ/',  3),
( 91,  5, 'hamburger', '/ˈhæmbɜrɡər/', '/ˈhæmbɜːɡə/',  4),
( 92,  5, 'chicken', '/ˈtʃɪkɪn/', '/ˈtʃɪkɪn/',  5),
( 93,  5, 'French fries', '/frɛntʃ fraɪz/', '/frɛntʃ fraɪz/',  6),
( 94,  5, 'Coke', '/koʊk/', '/kəʊk/',  7),
( 95,  5, 'juice', '/dʒuːs/', '/dʒuːs/',  8),
( 96,  5, 'milk', '/mɪlk/', '/mɪlk/',  9),
( 97,  5, 'water', '/ˈwɔtər/', '/ˈwɔːtə/', 10),
( 98,  5, 'tea', '/tiː/', '/tiː/', 11),
( 99,  5, 'coffee', '/ˈkɔfi/', '/ˈkɒfi/', 12),
(100,  5, 'rice', '/raɪs/', '/raɪs/', 13),
(102,  5, 'soup', '/suːp/', '/suːp/', 15),
(103,  5, 'noodles', '/ˈnuːdəlz/', '/ˈnuːdəlz/', 16),
(104,  5, 'egg', '/ɛɡ/', '/ɛɡ/', 17),
(105,  5, 'tofu', '/ˈtoʊfuː/', '/ˈtəʊfuː/', 18),
(106,  5, 'pork', '/pɔrk/', '/pɔːk/', 19),
(107,  5, 'beef', '/biːf/', '/biːf/', 20);

INSERT INTO wp_word_sense (id, word_id, pos, meaning, sort) VALUES
( 88,  88, 'n.', '蛋糕', 1),
( 89,  89, 'n.', '面包', 1),
( 90,  90, 'n.', '热狗', 1),
( 91,  91, 'n.', '汉堡包', 1),
( 92,  92, 'n.', '鸡肉', 1),
( 93,  93, 'n.', '炸薯条', 1),
( 94,  94, 'n.', '可乐', 1),
( 95,  95, 'n.', '果汁', 1),
( 96,  96, 'n.', '牛奶', 1),
( 97,  97, 'n.', '水', 1),
( 98,  98, 'n.', '茶', 1),
( 99,  99, 'n.', '咖啡', 1),
(100, 100, 'n.', '米饭', 1),
(102, 102, 'n.', '汤', 1),
(103, 103, 'n.', '面条', 1),
(104, 104, 'n.', '鸡蛋', 1),
(105, 105, 'n.', '豆腐', 1),
(106, 106, 'n.', '猪肉', 1),
(107, 107, 'n.', '牛肉', 1);

INSERT INTO wp_word_example (id, word_id, sentence, translation, sort) VALUES
( 88,  88, 'I like birthday cake.', '我喜欢生日蛋糕。', 1),
( 89,  89, 'I eat bread for breakfast.', '我早餐吃面包。', 1),
( 90,  90, 'He wants a hot dog.', '他想要一个热狗。', 1),
( 91,  91, 'Can I have a hamburger, please?', '请给我一个汉堡包好吗？', 1),
( 92,  92, 'I like chicken and rice.', '我喜欢鸡肉配米饭。', 1),
( 93,  93, 'French fries are my favourite.', '炸薯条是我的最爱。', 1),
( 94,  94, 'I drink Coke at the party.', '我在派对上喝可乐。', 1),
( 95,  95, 'Would you like some juice?', '你想喝一些果汁吗？', 1),
( 96,  96, 'I drink milk every morning.', '我每天早上喝牛奶。', 1),
( 97,  97, 'Please give me a glass of water.', '请给我一杯水。', 1),
( 98,  98, 'My mum drinks tea every day.', '我妈妈每天喝茶。', 1),
( 99,  99, 'Dad has a cup of coffee.', '爸爸喝一杯咖啡。', 1),
(100, 100, 'I eat rice for lunch.', '我午餐吃米饭。', 1),
(102, 102, 'The soup is hot.', '汤很烫。', 1),
(103, 103, 'I love noodles.', '我很喜欢面条。', 1),
(104, 104, 'There is an egg in my bowl.', '我碗里有一个鸡蛋。', 1),
(105, 105, 'Tofu is soft and healthy.', '豆腐软软的，很健康。', 1),
(106, 106, 'We eat pork dumplings.', '我们吃猪肉饺子。', 1),
(107, 107, 'Beef noodles are delicious.', '牛肉面很好吃。', 1);

-- ------------------------------------------------------------
-- 第6章：水果与蔬菜
-- ------------------------------------------------------------
INSERT INTO wp_word (id, chapter_id, word, phonetic_us, phonetic_uk, sort) VALUES
(108,  6, 'apple', '/ˈæpəl/', '/ˈæpəl/',  1),
(109,  6, 'pear', '/pɛr/', '/pɛə/',  2),
(110,  6, 'orange', '/ˈɔrɪndʒ/', '/ˈɒrɪndʒ/',  3),
(111,  6, 'banana', '/bəˈnænə/', '/bəˈnɑːnə/',  4),
(112,  6, 'grape', '/ɡreɪp/', '/ɡreɪp/',  5),
(113,  6, 'watermelon', '/ˈwɔtərˌmɛlən/', '/ˈwɔːtəˌmɛlən/',  6),
(114,  6, 'mango', '/ˈmæŋɡoʊ/', '/ˈmæŋɡəʊ/',  7),
(115,  6, 'strawberry', '/ˈstrɔˌbɛri/', '/ˈstrɔːbəri/',  8),
(116,  6, 'peach', '/piːtʃ/', '/piːtʃ/',  9),
(117,  6, 'lemon', '/ˈlɛmən/', '/ˈlɛmən/', 10),
(118,  6, 'potato', '/pəˈteɪtoʊ/', '/pəˈteɪtəʊ/', 11),
(119,  6, 'tomato', '/təˈmeɪtoʊ/', '/təˈmɑːtəʊ/', 12),
(120,  6, 'corn', '/kɔrn/', '/kɔːn/', 13),
(121,  6, 'carrot', '/ˈkærət/', '/ˈkærət/', 14),
(122,  6, 'onion', '/ˈʌnjən/', '/ˈʌnjən/', 15),
(123,  6, 'cucumber', '/ˈkjuːˌkʌmbər/', '/ˈkjuːˌkʌmbə/', 16),
(124,  6, 'cabbage', '/ˈkæbɪdʒ/', '/ˈkæbɪdʒ/', 17),
(125,  6, 'peas', '/piːz/', '/piːz/', 18);

INSERT INTO wp_word_sense (id, word_id, pos, meaning, sort) VALUES
(108, 108, 'n.', '苹果', 1),
(109, 109, 'n.', '梨', 1),
(110, 110, 'n.', '橙子', 1),
(111, 111, 'n.', '香蕉', 1),
(112, 112, 'n.', '葡萄', 1),
(113, 113, 'n.', '西瓜', 1),
(114, 114, 'n.', '芒果', 1),
(115, 115, 'n.', '草莓', 1),
(116, 116, 'n.', '桃子', 1),
(117, 117, 'n.', '柠檬', 1),
(118, 118, 'n.', '土豆', 1),
(119, 119, 'n.', '西红柿', 1),
(120, 120, 'n.', '玉米', 1),
(121, 121, 'n.', '胡萝卜', 1),
(122, 122, 'n.', '洋葱', 1),
(123, 123, 'n.', '黄瓜', 1),
(124, 124, 'n.', '卷心菜', 1),
(125, 125, 'n.', '豌豆', 1);

INSERT INTO wp_word_example (id, word_id, sentence, translation, sort) VALUES
(108, 108, 'An apple a day keeps the doctor away.', '每天一个苹果，医生远离我。', 1),
(109, 109, 'The pear is sweet and juicy.', '这个梨甜而多汁。', 1),
(110, 110, 'I peel the orange and eat it.', '我剥橙子吃。', 1),
(111, 111, 'Monkeys love to eat bananas.', '猴子喜欢吃香蕉。', 1),
(112, 112, 'I like to eat grapes.', '我喜欢吃葡萄。', 1),
(113, 113, 'Watermelon is red inside.', '西瓜里面是红色的。', 1),
(114, 114, 'Mango is my favourite fruit.', '芒果是我最喜欢的水果。', 1),
(115, 115, 'Strawberries are red and sweet.', '草莓是红色的，味道甜。', 1),
(116, 116, 'The peach is soft and sweet.', '桃子又软又甜。', 1),
(117, 117, 'Lemons taste very sour.', '柠檬尝起来非常酸。', 1),
(118, 118, 'I like mashed potato.', '我喜欢土豆泥。', 1),
(119, 119, 'The tomato is red and round.', '西红柿是红色圆形的。', 1),
(120, 120, 'I love eating corn.', '我喜欢吃玉米。', 1),
(121, 121, 'Rabbits like to eat carrots.', '兔子喜欢吃胡萝卜。', 1),
(122, 122, 'Onions can make your eyes water.', '洋葱会让你的眼睛流泪。', 1),
(123, 123, 'The cucumber is green and cool.', '黄瓜是绿色的，清爽可口。', 1),
(124, 124, 'We eat cabbage in soup.', '我们把卷心菜放在汤里吃。', 1),
(125, 125, 'Peas are small and green.', '豌豆小小的，绿绿的。', 1);

-- ------------------------------------------------------------
-- 第7章：家庭成员
-- ------------------------------------------------------------
INSERT INTO wp_word (id, chapter_id, word, phonetic_us, phonetic_uk, sort) VALUES
(126,  7, 'father', '/ˈfɑðər/', '/ˈfɑːðə/',  1),
(127,  7, 'mother', '/ˈmʌðər/', '/ˈmʌðə/',  2),
(128,  7, 'brother', '/ˈbrʌðər/', '/ˈbrʌðə/',  3),
(129,  7, 'sister', '/ˈsɪstər/', '/ˈsɪstə/',  4),
(130,  7, 'grandfather', '/ˈɡrændˌfɑðər/', '/ˈɡrændˌfɑːðə/',  5),
(131,  7, 'grandmother', '/ˈɡrændˌmʌðər/', '/ˈɡrændˌmʌðə/',  6),
(132,  7, 'uncle', '/ˈʌŋkəl/', '/ˈʌŋkəl/',  7),
(133,  7, 'aunt', '/ænt/', '/ɑːnt/',  8),
(134,  7, 'cousin', '/ˈkʌzən/', '/ˈkʌzən/',  9),
(135,  7, 'family', '/ˈfæməli/', '/ˈfæməli/', 10),
(136,  7, 'baby', '/ˈbeɪbi/', '/ˈbeɪbi/', 11),
(137,  7, 'parents', '/ˈpɛrənts/', '/ˈpeərənts/', 12),
(138,  7, 'son', '/sʌn/', '/sʌn/', 13),
(139,  7, 'daughter', '/ˈdɔtər/', '/ˈdɔːtə/', 14),
(140,  7, 'husband', '/ˈhʌzbənd/', '/ˈhʌzbənd/', 15),
(141,  7, 'wife', '/waɪf/', '/waɪf/', 16);

INSERT INTO wp_word_sense (id, word_id, pos, meaning, sort) VALUES
(126, 126, 'n.', '父亲', 1),
(127, 127, 'n.', '母亲', 1),
(128, 128, 'n.', '兄弟', 1),
(129, 129, 'n.', '姐妹', 1),
(130, 130, 'n.', '祖父', 1),
(131, 131, 'n.', '祖母', 1),
(132, 132, 'n.', '叔叔；舅舅', 1),
(133, 133, 'n.', '阿姨；姑姑', 1),
(134, 134, 'n.', '堂（表）兄弟姐妹', 1),
(135, 135, 'n.', '家庭', 1),
(136, 136, 'n.', '婴儿', 1),
(137, 137, 'n.', '父母', 1),
(138, 138, 'n.', '儿子', 1),
(139, 139, 'n.', '女儿', 1),
(140, 140, 'n.', '丈夫', 1),
(141, 141, 'n.', '妻子', 1);

INSERT INTO wp_word_example (id, word_id, sentence, translation, sort) VALUES
(126, 126, 'My father works in a school.', '我的父亲在一所学校工作。', 1),
(127, 127, 'My mother cooks lunch for me.', '我的母亲为我做午饭。', 1),
(128, 128, 'My brother likes playing football.', '我的哥哥喜欢踢足球。', 1),
(129, 129, 'My sister and I share a room.', '我和姐姐共用一个房间。', 1),
(130, 130, 'My grandfather tells me stories.', '我的爷爷给我讲故事。', 1),
(131, 131, 'My grandmother makes delicious dumplings.', '我的奶奶做的饺子很好吃。', 1),
(132, 132, 'My uncle lives in Beijing.', '我的叔叔住在北京。', 1),
(133, 133, 'My aunt has a cat.', '我的阿姨有一只猫。', 1),
(134, 134, 'My cousin is the same age as me.', '我的表弟和我同岁。', 1),
(135, 135, 'My family has four people.', '我的家庭有四口人。', 1),
(136, 136, 'The baby is sleeping.', '婴儿正在睡觉。', 1),
(137, 137, 'My parents love me very much.', '我的父母非常爱我。', 1),
(138, 138, 'They have a son and a daughter.', '他们有一个儿子和一个女儿。', 1),
(139, 139, 'She is a good daughter.', '她是一个好女儿。', 1),
(140, 140, 'Her husband is a doctor.', '她的丈夫是一名医生。', 1),
(141, 141, 'His wife is a teacher.', '他的妻子是一名教师。', 1);

-- ------------------------------------------------------------
-- 第8章：家的房间与家具
-- ------------------------------------------------------------
INSERT INTO wp_word (id, chapter_id, word, phonetic_us, phonetic_uk, sort) VALUES
(162,  8, 'living room', '/ˈlɪvɪŋ ruːm/', '/ˈlɪvɪŋ ruːm/',  1),
(163,  8, 'bedroom', '/ˈbedruːm/', '/ˈbedruːm/',  2),
(164,  8, 'bathroom', '/ˈbæθruːm/', '/ˈbɑːθruːm/',  3),
(165,  8, 'kitchen', '/ˈkɪtʃɪn/', '/ˈkɪtʃɪn/',  4),
(166,  8, 'study', '/ˈstʌdi/', '/ˈstʌdi/',  5),
(167,  8, 'sofa', '/ˈsoʊfə/', '/ˈsəʊfə/',  6),
(168,  8, 'shelf', '/ʃelf/', '/ʃelf/',  7),
(169,  8, 'fridge', '/frɪdʒ/', '/frɪdʒ/',  8),
(170,  8, 'table', '/ˈteɪbl/', '/ˈteɪbl/',  9),
(171,  8, 'phone', '/foʊn/', '/fəʊn/', 10),
(172,  8, 'curtain', '/ˈkɜːrtn/', '/ˈkɜːtn/', 11),
(173,  8, 'bed', '/bed/', '/bed/', 12),
(174,  8, 'mirror', '/ˈmɪrər/', '/ˈmɪrə/', 13),
(175,  8, 'closet', '/ˈklɑːzɪt/', '/ˈklɒzɪt/', 14),
(176,  8, 'air-conditioner', '/ˈer kənˌdɪʃənər/', '/ˈeə kənˌdɪʃənə/', 15),
(177,  8, 'trash bin', '/træʃ bɪn/', '/træʃ bɪn/', 16),
(178,  8, 'lamp', '/læmp/', '/læmp/', 17),
(179,  8, 'clock', '/klɑːk/', '/klɒk/', 18),
(180,  8, 'TV', '/ˌtiːˈviː/', '/ˌtiːˈviː/', 19),
(181,  8, 'washing machine', '/ˈwɑːʃɪŋ məˌʃiːn/', '/ˈwɒʃɪŋ məˌʃiːn/', 20);

INSERT INTO wp_word_sense (id, word_id, pos, meaning, sort) VALUES
(162, 162, 'n.', '客厅', 1),
(163, 163, 'n.', '卧室', 1),
(164, 164, 'n.', '浴室；卫生间', 1),
(165, 165, 'n.', '厨房', 1),
(166, 166, 'n.', '书房', 1),
(167, 167, 'n.', '沙发', 1),
(168, 168, 'n.', '架子；搁板', 1),
(169, 169, 'n.', '冰箱', 1),
(170, 170, 'n.', '桌子', 1),
(171, 171, 'n.', '电话', 1),
(172, 172, 'n.', '窗帘', 1),
(173, 173, 'n.', '床', 1),
(174, 174, 'n.', '镜子', 1),
(175, 175, 'n.', '壁橱；衣橱', 1),
(176, 176, 'n.', '空调', 1),
(177, 177, 'n.', '垃圾桶', 1),
(178, 178, 'n.', '台灯；灯', 1),
(179, 179, 'n.', '时钟', 1),
(180, 180, 'n.', '电视；电视机', 1),
(181, 181, 'n.', '洗衣机', 1);

INSERT INTO wp_word_example (id, word_id, sentence, translation, sort) VALUES
(162, 162, 'We watch TV in the living room.', '我们在客厅里看电视。', 1),
(163, 163, 'My bedroom is very tidy.', '我的卧室非常整洁。', 1),
(164, 164, 'Please wash your hands in the bathroom.', '请在浴室洗手。', 1),
(165, 165, 'My mum cooks in the kitchen.', '我妈妈在厨房做饭。', 1),
(166, 166, 'My dad reads books in the study.', '我爸爸在书房看书。', 1),
(167, 167, 'The cat is on the sofa.', '猫在沙发上。', 1),
(168, 168, 'My books are on the shelf.', '我的书在架子上。', 1),
(169, 169, 'There is some milk in the fridge.', '冰箱里有一些牛奶。', 1),
(170, 170, 'We eat dinner at the table.', '我们在桌子旁吃晚饭。', 1),
(171, 171, 'The phone is on the desk.', '电话在桌子上。', 1),
(172, 172, 'Please close the curtain at night.', '晚上请拉上窗帘。', 1),
(173, 173, 'I sleep in my bed every night.', '我每晚在床上睡觉。', 1),
(174, 174, 'I look at myself in the mirror.', '我在镜子里看自己。', 1),
(175, 175, 'My clothes are in the closet.', '我的衣服在衣橱里。', 1),
(176, 176, 'We turn on the air-conditioner in summer.', '夏天我们开空调。', 1),
(177, 177, 'Please put the rubbish in the trash bin.', '请把垃圾放进垃圾桶。', 1),
(178, 178, 'I read by the lamp at night.', '晚上我在台灯下读书。', 1),
(179, 179, 'The clock on the wall shows eight o''clock.', '墙上的时钟显示八点钟。', 1),
(180, 180, 'We watch TV after dinner.', '我们晚饭后看电视。', 1),
(181, 181, 'Mum puts the clothes in the washing machine.', '妈妈把衣服放进洗衣机。', 1);

-- ------------------------------------------------------------
-- 第9章：方位与位置
-- ------------------------------------------------------------
INSERT INTO wp_word (id, chapter_id, word, phonetic_us, phonetic_uk, sort) VALUES
(182,  9, 'in', '/ɪn/', '/ɪn/',  1),
(183,  9, 'on', '/ɑːn/', '/ɒn/',  2),
(184,  9, 'under', '/ˈʌndər/', '/ˈʌndə/',  3),
(185,  9, 'behind', '/bɪˈhaɪnd/', '/bɪˈhaɪnd/',  4),
(186,  9, 'in front of', '/ɪn frʌnt əv/', '/ɪn frʌnt əv/',  5),
(187,  9, 'next to', '/nekst tuː/', '/nekst tuː/',  6),
(188,  9, 'near', '/nɪr/', '/nɪə/',  7),
(189,  9, 'beside', '/bɪˈsaɪd/', '/bɪˈsaɪd/',  8),
(190,  9, 'left', '/left/', '/left/',  9),
(191,  9, 'right', '/raɪt/', '/raɪt/', 10),
(192,  9, 'up', '/ʌp/', '/ʌp/', 11),
(193,  9, 'down', '/daʊn/', '/daʊn/', 12),
(194,  9, 'inside', '/ˈɪnsaɪd/', '/ˈɪnsaɪd/', 13),
(195,  9, 'outside', '/ˈaʊtsaɪd/', '/ˈaʊtsaɪd/', 14),
(196,  9, 'between', '/bɪˈtwiːn/', '/bɪˈtwiːn/', 15),
(197,  9, 'above', '/əˈbʌv/', '/əˈbʌv/', 16),
(198,  9, 'below', '/bɪˈloʊ/', '/bɪˈləʊ/', 17),
(199,  9, 'here', '/hɪr/', '/hɪə/', 18),
(200,  9, 'there', '/ðer/', '/ðeə/', 19),
(201,  9, 'where', '/wer/', '/weə/', 20);

INSERT INTO wp_word_sense (id, word_id, pos, meaning, sort) VALUES
(182, 182, 'prep.', '在……里面', 1),
(183, 183, 'prep.', '在……上面', 1),
(184, 184, 'prep.', '在……下面', 1),
(185, 185, 'prep.', '在……后面', 1),
(186, 186, 'prep.', '在……前面', 1),
(187, 187, 'prep.', '紧挨着；在……旁边', 1),
(188, 188, 'prep.', '在……附近', 1),
(189, 189, 'prep.', '在……旁边', 1),
(190, 190, 'n.', '左边', 1),
(191, 191, 'n.', '右边', 1),
(192, 192, 'adv.', '向上', 1),
(193, 193, 'adv.', '向下', 1),
(194, 194, 'prep.', '在……里面', 1),
(195, 195, 'prep.', '在……外面', 1),
(196, 196, 'prep.', '在……之间', 1),
(197, 197, 'prep.', '在……上方', 1),
(198, 198, 'prep.', '在……下方', 1),
(199, 199, 'adv.', '这里；在这里', 1),
(200, 200, 'adv.', '那里；在那里', 1),
(201, 201, 'adv.', '在哪里', 1);

INSERT INTO wp_word_example (id, word_id, sentence, translation, sort) VALUES
(182, 182, 'The pencil is in the box.', '铅笔在盒子里。', 1),
(183, 183, 'The book is on the desk.', '书在桌子上。', 1),
(184, 184, 'The cat is under the chair.', '猫在椅子下面。', 1),
(185, 185, 'The ball is behind the door.', '球在门后面。', 1),
(186, 186, 'There is a garden in front of the school.', '学校前面有一个花园。', 1),
(187, 187, 'The post office is next to the bank.', '邮局紧挨着银行。', 1),
(188, 188, 'There is a park near my home.', '我家附近有一个公园。', 1),
(189, 189, 'She sits beside me in class.', '她在课堂上坐在我旁边。', 1),
(190, 190, 'Turn left at the traffic light.', '在红绿灯处向左转。', 1),
(191, 191, 'The shop is on the right.', '商店在右边。', 1),
(192, 192, 'Look up at the sky.', '向上看天空。', 1),
(193, 193, 'The bird flew down from the tree.', '鸟从树上飞了下来。', 1),
(194, 194, 'Stay inside when it rains.', '下雨时待在里面。', 1),
(195, 195, 'The children play outside in the afternoon.', '孩子们下午在外面玩。', 1),
(196, 196, 'The library is between the gym and the office.', '图书馆在体育馆和办公室之间。', 1),
(197, 197, 'The lamp is above the desk.', '台灯在桌子上方。', 1),
(198, 198, 'The shoes are below the bed.', '鞋子在床下方。', 1),
(199, 199, 'Come here, please.', '请过来这里。', 1),
(200, 200, 'My school is over there.', '我的学校在那边。', 1),
(201, 201, 'Where is my bag?', '我的书包在哪里？', 1);

-- ------------------------------------------------------------
-- 第10章：学校场所
-- ------------------------------------------------------------
INSERT INTO wp_word (id, chapter_id, word, phonetic_us, phonetic_uk, sort) VALUES
(202, 10, 'library', '/ˈlaɪbreri/', '/ˈlaɪbrəri/',  1),
(203, 10, 'gym', '/dʒɪm/', '/dʒɪm/',  2),
(204, 10, 'canteen', '/kænˈtiːn/', '/kænˈtiːn/',  3),
(205, 10, 'art room', '/ɑːrt ruːm/', '/ɑːt ruːm/',  4),
(206, 10, 'computer room', '/kəmˈpjuːtər ruːm/', '/kəmˈpjuːtə ruːm/',  5),
(207, 10, 'music room', '/ˈmjuːzɪk ruːm/', '/ˈmjuːzɪk ruːm/',  6),
(208, 10, 'playground', '/ˈpleɪɡraʊnd/', '/ˈpleɪɡraʊnd/',  7),
(209, 10, 'first floor', '/fɜːrst flɔːr/', '/fɜːst flɔː/',  8),
(210, 10, 'second floor', '/ˈsekənd flɔːr/', '/ˈsekənd flɔː/',  9),
(211, 10, 'third floor', '/θɜːrd flɔːr/', '/θɜːd flɔː/', 10),
(212, 10, 'office', '/ˈɑːfɪs/', '/ˈɒfɪs/', 11),
(213, 10, 'toilet', '/ˈtɔɪlɪt/', '/ˈtɔɪlɪt/', 12),
(214, 10, 'garden', '/ˈɡɑːrdn/', '/ˈɡɑːdn/', 13),
(215, 10, 'gate', '/ɡeɪt/', '/ɡeɪt/', 14),
(216, 10, 'hall', '/hɔːl/', '/hɔːl/', 15);

INSERT INTO wp_word_sense (id, word_id, pos, meaning, sort) VALUES
(202, 202, 'n.', '图书馆', 1),
(203, 203, 'n.', '体育馆', 1),
(204, 204, 'n.', '食堂；餐厅', 1),
(205, 205, 'n.', '美术教室', 1),
(206, 206, 'n.', '计算机教室', 1),
(207, 207, 'n.', '音乐教室', 1),
(208, 208, 'n.', '操场；游乐场', 1),
(209, 209, 'n.', '一楼', 1),
(210, 210, 'n.', '二楼', 1),
(211, 211, 'n.', '三楼', 1),
(212, 212, 'n.', '办公室', 1),
(213, 213, 'n.', '厕所；卫生间', 1),
(214, 214, 'n.', '花园', 1),
(215, 215, 'n.', '大门', 1),
(216, 216, 'n.', '大厅；礼堂', 1);

INSERT INTO wp_word_example (id, word_id, sentence, translation, sort) VALUES
(202, 202, 'I borrow books from the library.', '我从图书馆借书。', 1),
(203, 203, 'We play basketball in the gym.', '我们在体育馆打篮球。', 1),
(204, 204, 'We have lunch in the canteen.', '我们在食堂吃午饭。', 1),
(205, 205, 'We draw pictures in the art room.', '我们在美术教室里画画。', 1),
(206, 206, 'We learn how to use computers in the computer room.', '我们在计算机教室学习使用电脑。', 1),
(207, 207, 'We sing songs in the music room.', '我们在音乐教室唱歌。', 1),
(208, 208, 'We run on the playground after class.', '课后我们在操场上跑步。', 1),
(209, 209, 'The canteen is on the first floor.', '食堂在一楼。', 1),
(210, 210, 'Our classroom is on the second floor.', '我们的教室在二楼。', 1),
(211, 211, 'The library is on the third floor.', '图书馆在三楼。', 1),
(212, 212, 'The teacher is in the office.', '老师在办公室里。', 1),
(213, 213, 'Where is the toilet, please?', '请问厕所在哪里？', 1),
(214, 214, 'There are many flowers in the garden.', '花园里有很多花。', 1),
(215, 215, 'Meet me at the school gate.', '在学校大门口等我。', 1),
(216, 216, 'We have a meeting in the hall.', '我们在礼堂开会。', 1);

-- ------------------------------------------------------------
-- 第11章：）时间与作息
-- ------------------------------------------------------------
INSERT INTO wp_word (id, chapter_id, word, phonetic_us, phonetic_uk, sort) VALUES
(217, 11, 'get up', '/ɡet ʌp/', '/ɡet ʌp/',  1),
(218, 11, 'eat breakfast', '/iːt ˈbrekfəst/', '/iːt ˈbrekfəst/',  2),
(219, 11, 'go to school', '/ɡoʊ tə skuːl/', '/ɡəʊ tə skuːl/',  3),
(220, 11, 'have lunch', '/hæv lʌntʃ/', '/hæv lʌntʃ/',  4),
(221, 11, 'go home', '/ɡoʊ hoʊm/', '/ɡəʊ həʊm/',  5),
(222, 11, 'eat dinner', '/iːt ˈdɪnər/', '/iːt ˈdɪnə/',  6),
(223, 11, 'go to bed', '/ɡoʊ tə bed/', '/ɡəʊ tə bed/',  7),
(224, 11, 'morning', '/ˈmɔːrnɪŋ/', '/ˈmɔːnɪŋ/',  8),
(225, 11, 'afternoon', '/ˌæftərˈnuːn/', '/ˌɑːftəˈnuːn/',  9),
(226, 11, 'evening', '/ˈiːvnɪŋ/', '/ˈiːvnɪŋ/', 10),
(227, 11, 'night', '/naɪt/', '/naɪt/', 11),
(228, 11, 'o''clock', '/əˈklɑːk/', '/əˈklɒk/', 12),
(229, 11, 'time', '/taɪm/', '/taɪm/', 13),
(230, 11, 'early', '/ˈɜːrli/', '/ˈɜːli/', 14),
(231, 11, 'late', '/leɪt/', '/leɪt/', 15),
(232, 11, 'half', '/hæf/', '/hɑːf/', 16),
(233, 11, 'minute', '/ˈmɪnɪt/', '/ˈmɪnɪt/', 17),
(234, 11, 'hour', '/ˈaʊər/', '/ˈaʊə/', 18),
(235, 11, 'today', '/təˈdeɪ/', '/təˈdeɪ/', 19),
(236, 11, 'tomorrow', '/təˈmɑːroʊ/', '/təˈmɒrəʊ/', 20);

INSERT INTO wp_word_sense (id, word_id, pos, meaning, sort) VALUES
(217, 217, 'v.', '起床', 1),
(218, 218, 'v.', '吃早饭', 1),
(219, 219, 'v.', '去上学', 1),
(220, 220, 'v.', '吃午饭', 1),
(221, 221, 'v.', '回家', 1),
(222, 222, 'v.', '吃晚饭', 1),
(223, 223, 'v.', '上床睡觉', 1),
(224, 224, 'n.', '早晨；上午', 1),
(225, 225, 'n.', '下午', 1),
(226, 226, 'n.', '傍晚；晚上', 1),
(227, 227, 'n.', '夜晚', 1),
(228, 228, 'adv.', '……点钟', 1),
(229, 229, 'n.', '时间；时刻', 1),
(230, 230, 'adv.', '早；提前', 1),
(231, 231, 'adv.', '迟；晚', 1),
(232, 232, 'n.', '半；三十分钟', 1),
(233, 233, 'n.', '分钟', 1),
(234, 234, 'n.', '小时', 1),
(235, 235, 'n.', '今天', 1),
(236, 236, 'n.', '明天', 1);

INSERT INTO wp_word_example (id, word_id, sentence, translation, sort) VALUES
(217, 217, 'I get up at seven o''clock every day.', '我每天七点钟起床。', 1),
(218, 218, 'I eat breakfast before going to school.', '我上学前吃早饭。', 1),
(219, 219, 'I go to school at eight o''clock.', '我八点钟去上学。', 1),
(220, 220, 'We have lunch at twelve o''clock.', '我们十二点吃午饭。', 1),
(221, 221, 'I go home after school.', '放学后我回家。', 1),
(222, 222, 'My family eats dinner together at six.', '我们全家六点一起吃晚饭。', 1),
(223, 223, 'I go to bed at nine o''clock.', '我九点钟上床睡觉。', 1),
(224, 224, 'Good morning, everyone!', '大家早上好！', 1),
(225, 225, 'We have PE class in the afternoon.', '我们下午有体育课。', 1),
(226, 226, 'I do my homework in the evening.', '我在晚上做作业。', 1),
(227, 227, 'Good night, Mum.', '晚安，妈妈。', 1),
(228, 228, 'It is three o''clock now.', '现在是三点钟。', 1),
(229, 229, 'What time is it?', '现在几点了？', 1),
(230, 230, 'I get up early every morning.', '我每天早晨很早起床。', 1),
(231, 231, 'Don''t be late for school.', '上学不要迟到。', 1),
(232, 232, 'It is half past eight.', '现在是八点半。', 1),
(233, 233, 'Wait a minute, please.', '请等一分钟。', 1),
(234, 234, 'I study for one hour after dinner.', '晚饭后我学习一个小时。', 1),
(235, 235, 'Today is Monday.', '今天是星期一。', 1),
(236, 236, 'We have a test tomorrow.', '我们明天有一次考试。', 1);

-- ------------------------------------------------------------
-- 第12章：）天气与季节
-- ------------------------------------------------------------
INSERT INTO wp_word (id, chapter_id, word, phonetic_us, phonetic_uk, sort) VALUES
(237, 12, 'sunny', '/ˈsʌni/', '/ˈsʌni/',  1),
(238, 12, 'warm', '/wɔːrm/', '/wɔːm/',  2),
(239, 12, 'hot', '/hɑːt/', '/hɒt/',  3),
(240, 12, 'cool', '/kuːl/', '/kuːl/',  4),
(241, 12, 'cold', '/koʊld/', '/kəʊld/',  5),
(242, 12, 'snowy', '/ˈsnoʊi/', '/ˈsnəʊi/',  6),
(243, 12, 'windy', '/ˈwɪndi/', '/ˈwɪndi/',  7),
(244, 12, 'rainy', '/ˈreɪni/', '/ˈreɪni/',  8),
(245, 12, 'cloudy', '/ˈklaʊdi/', '/ˈklaʊdi/',  9),
(246, 12, 'weather', '/ˈweðər/', '/ˈweðə/', 10),
(247, 12, 'spring', '/sprɪŋ/', '/sprɪŋ/', 11),
(248, 12, 'summer', '/ˈsʌmər/', '/ˈsʌmə/', 12),
(249, 12, 'autumn', '/ˈɔːtəm/', '/ˈɔːtəm/', 13),
(250, 12, 'winter', '/ˈwɪntər/', '/ˈwɪntə/', 14),
(251, 12, 'season', '/ˈsiːzn/', '/ˈsiːzn/', 15),
(252, 12, 'temperature', '/ˈtemprətʃər/', '/ˈtemprətʃə/', 16),
(253, 12, 'storm', '/stɔːrm/', '/stɔːm/', 17),
(254, 12, 'fog', '/fɑːɡ/', '/fɒɡ/', 18),
(255, 12, 'rainbow', '/ˈreɪnboʊ/', '/ˈreɪnbəʊ/', 19),
(256, 12, 'thunder', '/ˈθʌndər/', '/ˈθʌndə/', 20);

INSERT INTO wp_word_sense (id, word_id, pos, meaning, sort) VALUES
(237, 237, 'adj.', '晴朗的', 1),
(238, 238, 'adj.', '温暖的', 1),
(239, 239, 'adj.', '热的；炎热的', 1),
(240, 240, 'adj.', '凉爽的', 1),
(241, 241, 'adj.', '寒冷的', 1),
(242, 242, 'adj.', '下雪的；多雪的', 1),
(243, 243, 'adj.', '有风的；刮风的', 1),
(244, 244, 'adj.', '下雨的；多雨的', 1),
(245, 245, 'adj.', '阴天的；多云的', 1),
(246, 246, 'n.', '天气', 1),
(247, 247, 'n.', '春天；春季', 1),
(248, 248, 'n.', '夏天；夏季', 1),
(249, 249, 'n.', '秋天；秋季', 1),
(250, 250, 'n.', '冬天；冬季', 1),
(251, 251, 'n.', '季节', 1),
(252, 252, 'n.', '温度；气温', 1),
(253, 253, 'n.', '暴风雨', 1),
(254, 254, 'n.', '雾', 1),
(255, 255, 'n.', '彩虹', 1),
(256, 256, 'n.', '雷；雷声', 1);

INSERT INTO wp_word_example (id, word_id, sentence, translation, sort) VALUES
(237, 237, 'It is a sunny day today.', '今天是个晴天。', 1),
(238, 238, 'Spring is warm and beautiful.', '春天温暖而美丽。', 1),
(239, 239, 'Summer is very hot.', '夏天非常炎热。', 1),
(240, 240, 'Autumn is cool and comfortable.', '秋天凉爽舒适。', 1),
(241, 241, 'It is very cold in winter.', '冬天非常寒冷。', 1),
(242, 242, 'It is snowy outside today.', '今天外面在下雪。', 1),
(243, 243, 'It is windy today, so wear a coat.', '今天有风，所以穿上外套。', 1),
(244, 244, 'Take an umbrella on rainy days.', '下雨天要带伞。', 1),
(245, 245, 'It is cloudy today.', '今天是阴天。', 1),
(246, 246, 'What is the weather like today?', '今天天气怎么样？', 1),
(247, 247, 'Flowers bloom in spring.', '春天花儿盛开。', 1),
(248, 248, 'I like to swim in summer.', '我喜欢在夏天游泳。', 1),
(249, 249, 'Leaves turn red in autumn.', '秋天树叶变红。', 1),
(250, 250, 'We can skate in winter.', '冬天我们可以溜冰。', 1),
(251, 251, 'There are four seasons in a year.', '一年有四个季节。', 1),
(252, 252, 'The temperature is low today.', '今天气温很低。', 1),
(253, 253, 'There is a big storm tonight.', '今晚有一场大暴风雨。', 1),
(254, 254, 'There is thick fog in the morning.', '早晨有大雾。', 1),
(255, 255, 'I can see a rainbow after the rain.', '雨后我能看到彩虹。', 1),
(256, 256, 'I am scared of thunder.', '我害怕打雷。', 1);

-- ------------------------------------------------------------
-- 第13章：）服装
-- ------------------------------------------------------------
INSERT INTO wp_word (id, chapter_id, word, phonetic_us, phonetic_uk, sort) VALUES
(257, 13, 'T-shirt', '/ˈtiː ʃɜːrt/', '/ˈtiː ʃɜːt/',  1),
(258, 13, 'shirt', '/ʃɜːrt/', '/ʃɜːt/',  2),
(259, 13, 'jacket', '/ˈdʒækɪt/', '/ˈdʒækɪt/',  3),
(260, 13, 'sweater', '/ˈswetər/', '/ˈswetə/',  4),
(261, 13, 'jeans', '/dʒiːnz/', '/dʒiːnz/',  5),
(262, 13, 'pants', '/pænts/', '/pænts/',  6),
(263, 13, 'socks', '/sɑːks/', '/sɒks/',  7),
(264, 13, 'shoes', '/ʃuːz/', '/ʃuːz/',  8),
(265, 13, 'dress', '/dres/', '/dres/',  9),
(266, 13, 'skirt', '/skɜːrt/', '/skɜːt/', 10),
(267, 13, 'coat', '/koʊt/', '/kəʊt/', 11),
(268, 13, 'hat', '/hæt/', '/hæt/', 12),
(269, 13, 'cap', '/kæp/', '/kæp/', 13),
(270, 13, 'scarf', '/skɑːrf/', '/skɑːf/', 14),
(271, 13, 'gloves', '/ɡlʌvz/', '/ɡlʌvz/', 15),
(272, 13, 'boots', '/buːts/', '/buːts/', 16),
(273, 13, 'uniform', '/ˈjuːnɪfɔːrm/', '/ˈjuːnɪfɔːm/', 17),
(274, 13, 'shorts', '/ʃɔːrts/', '/ʃɔːts/', 18),
(275, 13, 'belt', '/belt/', '/belt/', 19),
(276, 13, 'pocket', '/ˈpɑːkɪt/', '/ˈpɒkɪt/', 20);

INSERT INTO wp_word_sense (id, word_id, pos, meaning, sort) VALUES
(257, 257, 'n.', 'T恤衫', 1),
(258, 258, 'n.', '衬衫', 1),
(259, 259, 'n.', '夹克；短外套', 1),
(260, 260, 'n.', '毛衣；套头衫', 1),
(261, 261, 'n.', '牛仔裤', 1),
(262, 262, 'n.', '裤子', 1),
(263, 263, 'n.', '袜子', 1),
(264, 264, 'n.', '鞋子', 1),
(265, 265, 'n.', '连衣裙', 1),
(266, 266, 'n.', '裙子', 1),
(267, 267, 'n.', '外套；大衣', 1),
(268, 268, 'n.', '（有边沿的）帽子', 1),
(269, 269, 'n.', '（无边的）帽子；鸭舌帽', 1),
(270, 270, 'n.', '围巾', 1),
(271, 271, 'n.', '手套', 1),
(272, 272, 'n.', '靴子', 1),
(273, 273, 'n.', '校服；制服', 1),
(274, 274, 'n.', '短裤', 1),
(275, 275, 'n.', '腰带；皮带', 1),
(276, 276, 'n.', '口袋', 1);

INSERT INTO wp_word_example (id, word_id, sentence, translation, sort) VALUES
(257, 257, 'He is wearing a red T-shirt.', '他穿着一件红色T恤衫。', 1),
(258, 258, 'My dad wears a white shirt to work.', '我爸爸穿白衬衫去上班。', 1),
(259, 259, 'Put on your jacket, it is cold outside.', '穿上你的夹克，外面很冷。', 1),
(260, 260, 'I wear a sweater in autumn.', '秋天我穿毛衣。', 1),
(261, 261, 'She likes to wear jeans.', '她喜欢穿牛仔裤。', 1),
(262, 262, 'He is wearing blue pants.', '他穿着蓝色裤子。', 1),
(263, 263, 'I put on my socks before my shoes.', '穿鞋之前我先穿袜子。', 1),
(264, 264, 'These shoes are too big for me.', '这双鞋对我来说太大了。', 1),
(265, 265, 'She wears a pink dress today.', '她今天穿一条粉色连衣裙。', 1),
(266, 266, 'My sister has a new skirt.', '我妹妹有一条新裙子。', 1),
(267, 267, 'Wear your coat when it is cold.', '天冷时要穿外套。', 1),
(268, 268, 'She wears a big sun hat in summer.', '夏天她戴一顶大太阳帽。', 1),
(269, 269, 'He always wears a blue cap.', '他总是戴一顶蓝色鸭舌帽。', 1),
(270, 270, 'She wears a red scarf in winter.', '冬天她戴红色围巾。', 1),
(271, 271, 'My hands are warm with gloves on.', '戴上手套我的手很暖和。', 1),
(272, 272, 'I wear boots when it snows.', '下雪时我穿靴子。', 1),
(273, 273, 'We wear a uniform to school.', '我们穿校服上学。', 1),
(274, 274, 'He wears shorts in summer.', '他夏天穿短裤。', 1),
(275, 275, 'He wears a brown belt.', '他系着一条棕色腰带。', 1),
(276, 276, 'I put my keys in my pocket.', '我把钥匙放进口袋里。', 1);

-- ------------------------------------------------------------
-- 第14章：）购物与价格
-- ------------------------------------------------------------
INSERT INTO wp_word (id, chapter_id, word, phonetic_us, phonetic_uk, sort) VALUES
(277, 14, 'buy', '/baɪ/', '/baɪ/',  1),
(278, 14, 'sell', '/sel/', '/sel/',  2),
(279, 14, 'shop', '/ʃɑːp/', '/ʃɒp/',  3),
(280, 14, 'store', '/stɔːr/', '/stɔː/',  4),
(281, 14, 'price', '/praɪs/', '/praɪs/',  5),
(282, 14, 'cheap', '/tʃiːp/', '/tʃiːp/',  6),
(283, 14, 'expensive', '/ɪkˈspensɪv/', '/ɪkˈspensɪv/',  7),
(284, 14, 'yuan', '/juːˈɑːn/', '/juːˈæn/',  8),
(285, 14, 'how much', '/haʊ mʌtʃ/', '/haʊ mʌtʃ/',  9),
(286, 14, 'money', '/ˈmʌni/', '/ˈmʌni/', 10),
(287, 14, 'pay', '/peɪ/', '/peɪ/', 11),
(288, 14, 'change', '/tʃeɪndʒ/', '/tʃeɪndʒ/', 12),
(291, 14, 'box', '/bɑːks/', '/bɒks/', 15),
(292, 14, 'size', '/saɪz/', '/saɪz/', 16),
(293, 14, 'colour', '/ˈkʌlər/', '/ˈkʌlə/', 17),
(294, 14, 'new', '/njuː/', '/njuː/', 18),
(295, 14, 'old', '/oʊld/', '/əʊld/', 19),
(296, 14, 'sale', '/seɪl/', '/seɪl/', 20);

INSERT INTO wp_word_sense (id, word_id, pos, meaning, sort) VALUES
(277, 277, 'v.', '买；购买', 1),
(278, 278, 'v.', '卖；出售', 1),
(279, 279, 'n.', '商店', 1),
(280, 280, 'n.', '商店；百货店', 1),
(281, 281, 'n.', '价格；价钱', 1),
(282, 282, 'adj.', '便宜的', 1),
(283, 283, 'adj.', '贵的；昂贵的', 1),
(284, 284, 'n.', '元（人民币单位）', 1),
(285, 285, 'phr.', '多少钱；多少', 1),
(286, 286, 'n.', '钱；金钱', 1),
(287, 287, 'v.', '付款；支付', 1),
(288, 288, 'n.', '找零；零钱', 1),
(291, 291, 'n.', '盒子；箱子', 1),
(292, 292, 'n.', '尺寸；大小', 1),
(293, 293, 'n.', '颜色', 1),
(294, 294, 'adj.', '新的', 1),
(295, 295, 'adj.', '旧的；老的', 1),
(296, 296, 'n.', '促销；降价出售', 1);

INSERT INTO wp_word_example (id, word_id, sentence, translation, sort) VALUES
(277, 277, 'I want to buy a new book.', '我想买一本新书。', 1),
(278, 278, 'This shop sells fruit.', '这家商店卖水果。', 1),
(279, 279, 'There is a food shop near my home.', '我家附近有一家食品商店。', 1),
(280, 280, 'We go to the store every weekend.', '我们每个周末去商店。', 1),
(281, 281, 'What is the price of this bag?', '这个包多少钱？', 1),
(282, 282, 'This pen is very cheap.', '这支钢笔很便宜。', 1),
(283, 283, 'This coat is too expensive for me.', '这件外套对我来说太贵了。', 1),
(284, 284, 'This apple costs two yuan.', '这个苹果两元钱。', 1),
(285, 285, 'How much is this T-shirt?', '这件T恤衫多少钱？', 1),
(286, 286, 'I save my money to buy a bike.', '我存钱买一辆自行车。', 1),
(287, 287, 'Please pay at the counter.', '请在收银台付款。', 1),
(288, 288, 'Here is your change.', '这是找给你的零钱。', 1),
(291, 291, 'The shoes come in a big box.', '鞋子装在一个大盒子里。', 1),
(292, 292, 'What size do you wear?', '你穿多大号？', 1),
(293, 293, 'What colour do you like?', '你喜欢什么颜色？', 1),
(294, 294, 'I want to buy a new pencil case.', '我想买一个新文具盒。', 1),
(295, 295, 'My old shoes are broken.', '我的旧鞋子破了。', 1),
(296, 296, 'The shoes are on sale today.', '鞋子今天正在促销。', 1);

-- ------------------------------------------------------------
-- 第15章：）描述外貌与性格
-- ------------------------------------------------------------
INSERT INTO wp_word (id, chapter_id, word, phonetic_us, phonetic_uk, sort) VALUES
(302, 15, 'tall', '/tɔːl/', '/tɔːl/',  1),
(303, 15, 'short', '/ʃɔːrt/', '/ʃɔːt/',  2),
(304, 15, 'thin', '/θɪn/', '/θɪn/',  3),
(305, 15, 'fat', '/fæt/', '/fæt/',  4),
(306, 15, 'long', '/lɔːŋ/', '/lɒŋ/',  5),
(307, 15, 'strong', '/strɔːŋ/', '/strɒŋ/',  6),
(308, 15, 'young', '/jʌŋ/', '/jʌŋ/',  7),
(309, 15, 'old', '/oʊld/', '/əʊld/',  8),
(310, 15, 'funny', '/ˈfʌni/', '/ˈfʌni/',  9),
(311, 15, 'kind', '/kaɪnd/', '/kaɪnd/', 10),
(312, 15, 'quiet', '/ˈkwaɪət/', '/ˈkwaɪət/', 11),
(313, 15, 'friendly', '/ˈfrendli/', '/ˈfrendli/', 12),
(314, 15, 'smart', '/smɑːrt/', '/smɑːt/', 13),
(315, 15, 'cute', '/kjuːt/', '/kjuːt/', 14),
(316, 15, 'beautiful', '/ˈbjuːtɪfl/', '/ˈbjuːtɪfl/', 15),
(317, 15, 'handsome', '/ˈhænsəm/', '/ˈhænsəm/', 16),
(318, 15, 'glasses', '/ˈɡlæsɪz/', '/ˈɡlɑːsɪz/', 17),
(319, 15, 'curly', '/ˈkɜːrli/', '/ˈkɜːli/', 18),
(320, 15, 'straight', '/streɪt/', '/streɪt/', 19),
(321, 15, 'blonde', '/blɑːnd/', '/blɒnd/', 20);

INSERT INTO wp_word_sense (id, word_id, pos, meaning, sort) VALUES
(302, 302, 'adj.', '高的', 1),
(303, 303, 'adj.', '矮的', 1),
(304, 304, 'adj.', '瘦的', 1),
(305, 305, 'adj.', '胖的', 1),
(306, 306, 'adj.', '长的', 1),
(307, 307, 'adj.', '强壮的', 1),
(308, 308, 'adj.', '年轻的', 1),
(309, 309, 'adj.', '年老的', 1),
(310, 310, 'adj.', '有趣的', 1),
(311, 311, 'adj.', '友善的', 1),
(312, 312, 'adj.', '安静的', 1),
(313, 313, 'adj.', '友好的', 1),
(314, 314, 'adj.', '聪明的', 1),
(315, 315, 'adj.', '可爱的', 1),
(316, 316, 'adj.', '美丽的', 1),
(317, 317, 'adj.', '英俊的', 1),
(318, 318, 'n.', '眼镜', 1),
(319, 319, 'adj.', '卷曲的', 1),
(320, 320, 'adj.', '直的', 1),
(321, 321, 'adj.', '金色的（头发）', 1);

INSERT INTO wp_word_example (id, word_id, sentence, translation, sort) VALUES
(302, 302, 'My father is very tall.', '我爸爸非常高。', 1),
(303, 303, 'She is shorter than her sister.', '她比她姐姐矮。', 1),
(304, 304, 'The cat is very thin.', '这只猫非常瘦。', 1),
(305, 305, 'The panda is fat and cute.', '这只熊猫又胖又可爱。', 1),
(306, 306, 'She has long hair.', '她有一头长发。', 1),
(307, 307, 'He is strong and tall.', '他既强壮又高大。', 1),
(308, 308, 'My teacher is young and kind.', '我的老师年轻又友善。', 1),
(309, 309, 'My grandpa is old but healthy.', '我爷爷年老但健康。', 1),
(310, 310, 'He tells funny stories.', '他讲有趣的故事。', 1),
(311, 311, 'She is very kind to everyone.', '她对每个人都很友善。', 1),
(312, 312, 'Please be quiet in the library.', '在图书馆请保持安静。', 1),
(313, 313, 'Our new classmate is very friendly.', '我们的新同学非常友好。', 1),
(314, 314, 'Tom is a smart boy.', '汤姆是个聪明的男孩。', 1),
(315, 315, 'The puppy is so cute.', '这只小狗太可爱了。', 1),
(316, 316, 'The flowers are beautiful.', '这些花很美丽。', 1),
(317, 317, 'He is a handsome young man.', '他是一个英俊的年轻人。', 1),
(318, 318, 'He wears glasses every day.', '他每天都戴眼镜。', 1),
(319, 319, 'She has curly hair.', '她有卷发。', 1),
(320, 320, 'He has short straight hair.', '他有一头短直发。', 1),
(321, 321, 'She has long blonde hair.', '她有一头金色长发。', 1);

-- ------------------------------------------------------------
-- 第16章：）能力与家务
-- ------------------------------------------------------------
INSERT INTO wp_word (id, chapter_id, word, phonetic_us, phonetic_uk, sort) VALUES
(322, 16, 'cook', '/kʊk/', '/kʊk/',  1),
(323, 16, 'wash', '/wɑːʃ/', '/wɒʃ/',  2),
(324, 16, 'clean', '/kliːn/', '/kliːn/',  3),
(325, 16, 'sweep', '/swiːp/', '/swiːp/',  4),
(326, 16, 'water', '/ˈwɔːtər/', '/ˈwɔːtə/',  5),
(327, 16, 'make', '/meɪk/', '/meɪk/',  6),
(328, 16, 'help', '/help/', '/help/',  7),
(329, 16, 'carry', '/ˈkæri/', '/ˈkæri/',  8),
(330, 16, 'read', '/riːd/', '/riːd/',  9),
(331, 16, 'draw', '/drɔː/', '/drɔː/', 10),
(332, 16, 'sing', '/sɪŋ/', '/sɪŋ/', 11),
(333, 16, 'dance', '/dæns/', '/dɑːns/', 12),
(334, 16, 'swim', '/swɪm/', '/swɪm/', 13),
(335, 16, 'run', '/rʌn/', '/rʌn/', 14),
(336, 16, 'jump', '/dʒʌmp/', '/dʒʌmp/', 15),
(337, 16, 'ride', '/raɪd/', '/raɪd/', 16),
(338, 16, 'play', '/pleɪ/', '/pleɪ/', 17),
(339, 16, 'climb', '/klaɪm/', '/klaɪm/', 18),
(340, 16, 'fly', '/flaɪ/', '/flaɪ/', 19),
(341, 16, 'do homework', '/duː ˈhoʊmwɜːrk/', '/duː ˈhəʊmwɜːk/', 20);

INSERT INTO wp_word_sense (id, word_id, pos, meaning, sort) VALUES
(322, 322, 'v.', '做饭', 1),
(323, 323, 'v.', '洗', 1),
(324, 324, 'v.', '打扫', 1),
(325, 325, 'v.', '扫', 1),
(326, 326, 'v.', '浇水', 1),
(327, 327, 'v.', '做；制作', 1),
(328, 328, 'v.', '帮助', 1),
(329, 329, 'v.', '搬运；提', 1),
(330, 330, 'v.', '阅读', 1),
(331, 331, 'v.', '画画', 1),
(332, 332, 'v.', '唱歌', 1),
(333, 333, 'v.', '跳舞', 1),
(334, 334, 'v.', '游泳', 1),
(335, 335, 'v.', '跑', 1),
(336, 336, 'v.', '跳', 1),
(337, 337, 'v.', '骑', 1),
(338, 338, 'v.', '玩；演奏', 1),
(339, 339, 'v.', '爬；攀登', 1),
(340, 340, 'v.', '飞', 1),
(341, 341, 'v.', '做作业', 1);

INSERT INTO wp_word_example (id, word_id, sentence, translation, sort) VALUES
(322, 322, 'My mum can cook delicious food.', '我妈妈能做美味的食物。', 1),
(323, 323, 'I wash my hands before meals.', '我在饭前洗手。', 1),
(324, 324, 'We clean the classroom every day.', '我们每天打扫教室。', 1),
(325, 325, 'Can you sweep the floor?', '你能扫地吗？', 1),
(326, 326, 'I water the plants every morning.', '我每天早上给植物浇水。', 1),
(327, 327, 'Let''s make a birthday card.', '我们来做一张生日贺卡吧。', 1),
(328, 328, 'I often help my mum at home.', '我经常在家帮妈妈。', 1),
(329, 329, 'Can you carry this box for me?', '你能帮我搬这个箱子吗？', 1),
(330, 330, 'I like to read books after school.', '我喜欢放学后读书。', 1),
(331, 331, 'She can draw beautiful pictures.', '她能画出漂亮的画。', 1),
(332, 332, 'He can sing many English songs.', '他能唱很多英文歌曲。', 1),
(333, 333, 'She loves to dance.', '她喜欢跳舞。', 1),
(334, 334, 'Can you swim?', '你会游泳吗？', 1),
(335, 335, 'I run in the park every morning.', '我每天早上在公园跑步。', 1),
(336, 336, 'The frog can jump very high.', '这只青蛙能跳很高。', 1),
(337, 337, 'I can ride a bike.', '我会骑自行车。', 1),
(338, 338, 'We play football on Saturdays.', '我们每周六踢足球。', 1),
(339, 339, 'He can climb trees easily.', '他能轻松地爬树。', 1),
(340, 340, 'Birds can fly in the sky.', '鸟儿能在天空中飞翔。', 1),
(341, 341, 'I do my homework after dinner.', '我在晚饭后做作业。', 1);

-- ------------------------------------------------------------
-- 第17章：）自然景物
-- ------------------------------------------------------------
INSERT INTO wp_word (id, chapter_id, word, phonetic_us, phonetic_uk, sort) VALUES
(342, 17, 'forest', '/ˈfɔːrɪst/', '/ˈfɒrɪst/',  1),
(343, 17, 'river', '/ˈrɪvər/', '/ˈrɪvə/',  2),
(344, 17, 'lake', '/leɪk/', '/leɪk/',  3),
(345, 17, 'mountain', '/ˈmaʊntən/', '/ˈmaʊntɪn/',  4),
(346, 17, 'sky', '/skaɪ/', '/skaɪ/',  5),
(347, 17, 'grass', '/ɡræs/', '/ɡrɑːs/',  6),
(348, 17, 'flower', '/ˈflaʊər/', '/ˈflaʊə/',  7),
(349, 17, 'tree', '/triː/', '/triː/',  8),
(350, 17, 'cloud', '/klaʊd/', '/klaʊd/',  9),
(351, 17, 'sun', '/sʌn/', '/sʌn/', 10),
(352, 17, 'moon', '/muːn/', '/muːn/', 11),
(353, 17, 'star', '/stɑːr/', '/stɑː/', 12),
(354, 17, 'sea', '/siː/', '/siː/', 13),
(355, 17, 'beach', '/biːtʃ/', '/biːtʃ/', 14),
(356, 17, 'island', '/ˈaɪlənd/', '/ˈaɪlənd/', 15),
(357, 17, 'hill', '/hɪl/', '/hɪl/', 16),
(358, 17, 'field', '/fiːld/', '/fiːld/', 17),
(359, 17, 'rock', '/rɑːk/', '/rɒk/', 18),
(360, 17, 'path', '/pæθ/', '/pɑːθ/', 19),
(361, 17, 'bridge', '/brɪdʒ/', '/brɪdʒ/', 20);

INSERT INTO wp_word_sense (id, word_id, pos, meaning, sort) VALUES
(342, 342, 'n.', '森林', 1),
(343, 343, 'n.', '河流', 1),
(344, 344, 'n.', '湖', 1),
(345, 345, 'n.', '山；山脉', 1),
(346, 346, 'n.', '天空', 1),
(347, 347, 'n.', '草', 1),
(348, 348, 'n.', '花', 1),
(349, 349, 'n.', '树', 1),
(350, 350, 'n.', '云', 1),
(351, 351, 'n.', '太阳', 1),
(352, 352, 'n.', '月亮', 1),
(353, 353, 'n.', '星星', 1),
(354, 354, 'n.', '大海', 1),
(355, 355, 'n.', '海滩', 1),
(356, 356, 'n.', '岛屿', 1),
(357, 357, 'n.', '小山；丘陵', 1),
(358, 358, 'n.', '田野', 1),
(359, 359, 'n.', '岩石', 1),
(360, 360, 'n.', '小路', 1),
(361, 361, 'n.', '桥', 1);

INSERT INTO wp_word_example (id, word_id, sentence, translation, sort) VALUES
(342, 342, 'There are many animals in the forest.', '森林里有很多动物。', 1),
(343, 343, 'The river is long and wide.', '这条河又长又宽。', 1),
(344, 344, 'We can swim in the lake.', '我们可以在湖里游泳。', 1),
(345, 345, 'The mountain is very high.', '这座山非常高。', 1),
(346, 346, 'The sky is blue and clear today.', '今天天空蔚蓝清澈。', 1),
(347, 347, 'The cow eats grass every day.', '奶牛每天吃草。', 1),
(348, 348, 'I like to pick flowers in the park.', '我喜欢在公园里摘花。', 1),
(349, 349, 'There is a big tree in our yard.', '我们院子里有一棵大树。', 1),
(350, 350, 'I see white clouds in the sky.', '我看到天空中有白云。', 1),
(351, 351, 'The sun rises in the east.', '太阳从东方升起。', 1),
(352, 352, 'The moon is bright tonight.', '今晚月亮很亮。', 1),
(353, 353, 'I can see many stars at night.', '晚上我能看到很多星星。', 1),
(354, 354, 'The sea is very deep.', '大海非常深。', 1),
(355, 355, 'We played on the beach.', '我们在海滩上玩耍。', 1),
(356, 356, 'There is a small island in the lake.', '湖中有一个小岛。', 1),
(357, 357, 'We climbed up the hill.', '我们爬上了小山。', 1),
(358, 358, 'Cows are eating grass in the field.', '奶牛在田野里吃草。', 1),
(359, 359, 'He sat on a big rock.', '他坐在一块大岩石上。', 1),
(360, 360, 'There is a path through the forest.', '森林里有一条小路。', 1),
(361, 361, 'We crossed the bridge to the other side.', '我们过桥到了对岸。', 1);

-- ------------------------------------------------------------
-- 第18章：）月份、星期与节假日
-- ------------------------------------------------------------
INSERT INTO wp_word (id, chapter_id, word, phonetic_us, phonetic_uk, sort) VALUES
(362, 18, 'Monday', '/ˈmʌndeɪ/', '/ˈmʌndeɪ/',  1),
(363, 18, 'Tuesday', '/ˈtuːzdeɪ/', '/ˈtjuːzdeɪ/',  2),
(364, 18, 'Wednesday', '/ˈwenzdeɪ/', '/ˈwenzdeɪ/',  3),
(365, 18, 'Thursday', '/ˈθɜːrzdeɪ/', '/ˈθɜːzdeɪ/',  4),
(366, 18, 'Friday', '/ˈfraɪdeɪ/', '/ˈfraɪdeɪ/',  5),
(367, 18, 'Saturday', '/ˈsætərdeɪ/', '/ˈsætədeɪ/',  6),
(368, 18, 'Sunday', '/ˈsʌndeɪ/', '/ˈsʌndeɪ/',  7),
(369, 18, 'January', '/ˈdʒænjueri/', '/ˈdʒænjuəri/',  8),
(370, 18, 'February', '/ˈfebrueri/', '/ˈfebruəri/',  9),
(371, 18, 'March', '/mɑːrtʃ/', '/mɑːtʃ/', 10),
(372, 18, 'April', '/ˈeɪprəl/', '/ˈeɪprəl/', 11),
(373, 18, 'May', '/meɪ/', '/meɪ/', 12),
(374, 18, 'June', '/dʒuːn/', '/dʒuːn/', 13),
(375, 18, 'July', '/dʒuˈlaɪ/', '/dʒuˈlaɪ/', 14),
(376, 18, 'August', '/ˈɔːɡəst/', '/ˈɔːɡəst/', 15),
(377, 18, 'September', '/sepˈtembər/', '/sepˈtembə/', 16),
(378, 18, 'October', '/ɑːkˈtoʊbər/', '/ɒkˈtəʊbə/', 17),
(379, 18, 'November', '/noʊˈvembər/', '/nəˈvembə/', 18),
(380, 18, 'December', '/dɪˈsembər/', '/dɪˈsembə/', 19),
(381, 18, 'holiday', '/ˈhɑːlədeɪ/', '/ˈhɒlədeɪ/', 20),
(382, 18, 'birthday', '/ˈbɜːrθdeɪ/', '/ˈbɜːθdeɪ/', 21),
(383, 18, 'Christmas', '/ˈkrɪsməs/', '/ˈkrɪsməs/', 22),
(384, 18, 'New Year', '/nuː jɪr/', '/njuː jɪə/', 23);

INSERT INTO wp_word_sense (id, word_id, pos, meaning, sort) VALUES
(362, 362, 'n.', '星期一', 1),
(363, 363, 'n.', '星期二', 1),
(364, 364, 'n.', '星期三', 1),
(365, 365, 'n.', '星期四', 1),
(366, 366, 'n.', '星期五', 1),
(367, 367, 'n.', '星期六', 1),
(368, 368, 'n.', '星期日', 1),
(369, 369, 'n.', '一月', 1),
(370, 370, 'n.', '二月', 1),
(371, 371, 'n.', '三月', 1),
(372, 372, 'n.', '四月', 1),
(373, 373, 'n.', '五月', 1),
(374, 374, 'n.', '六月', 1),
(375, 375, 'n.', '七月', 1),
(376, 376, 'n.', '八月', 1),
(377, 377, 'n.', '九月', 1),
(378, 378, 'n.', '十月', 1),
(379, 379, 'n.', '十一月', 1),
(380, 380, 'n.', '十二月', 1),
(381, 381, 'n.', '假日；节日', 1),
(382, 382, 'n.', '生日', 1),
(383, 383, 'n.', '圣诞节', 1),
(384, 384, 'n.', '新年', 1);

INSERT INTO wp_word_example (id, word_id, sentence, translation, sort) VALUES
(362, 362, 'I have PE class on Monday.', '我周一有体育课。', 1),
(363, 363, 'We have math on Tuesday.', '我们周二有数学课。', 1),
(364, 364, 'Wednesday is the middle of the week.', '周三是一周的中间。', 1),
(365, 365, 'We have English class on Thursday.', '我们周四有英语课。', 1),
(366, 366, 'Friday is the last school day of the week.', '周五是一周最后一个上学日。', 1),
(367, 367, 'I play football on Saturday.', '我周六踢足球。', 1),
(368, 368, 'We have a family meal on Sunday.', '我们周日全家聚餐。', 1),
(369, 369, 'January is the first month of the year.', '一月是一年中的第一个月。', 1),
(370, 370, 'February has twenty-eight days.', '二月有二十八天。', 1),
(371, 371, 'Spring begins in March.', '春天在三月开始。', 1),
(372, 372, 'April is warm and sunny.', '四月温暖晴朗。', 1),
(373, 373, 'Children''s Day is in May.', '儿童节在五月。', 1),
(374, 374, 'School ends in June.', '学校在六月结束课程。', 1),
(375, 375, 'It is very hot in July.', '七月非常热。', 1),
(376, 376, 'We go to the beach in August.', '我们八月去海滩。', 1),
(377, 377, 'School starts again in September.', '九月学校重新开学。', 1),
(378, 378, 'National Day is in October.', '国庆节在十月。', 1),
(379, 379, 'November is cold in the north.', '北方十一月很冷。', 1),
(380, 380, 'Christmas is in December.', '圣诞节在十二月。', 1),
(381, 381, 'We have a long holiday in summer.', '我们夏天有一个长假。', 1),
(382, 382, 'Today is my birthday.', '今天是我的生日。', 1),
(383, 383, 'We sing songs at Christmas.', '我们在圣诞节唱歌。', 1),
(384, 384, 'Happy New Year to everyone!', '祝大家新年快乐！', 1);

-- ------------------------------------------------------------
-- 第19章：）问路、交通与方向
-- ------------------------------------------------------------
INSERT INTO wp_word (id, chapter_id, word, phonetic_us, phonetic_uk, sort) VALUES
(385, 19, 'bus', '/bʌs/', '/bʌs/',  1),
(386, 19, 'car', '/kɑːr/', '/kɑː/',  2),
(387, 19, 'bike', '/baɪk/', '/baɪk/',  3),
(388, 19, 'taxi', '/ˈtæksi/', '/ˈtæksi/',  4),
(390, 19, 'train', '/treɪn/', '/treɪn/',  6),
(391, 19, 'plane', '/pleɪn/', '/pleɪn/',  7),
(392, 19, 'ship', '/ʃɪp/', '/ʃɪp/',  8),
(393, 19, 'turn', '/tɜːrn/', '/tɜːn/',  9),
(396, 19, 'straight', '/streɪt/', '/streɪt/', 12),
(397, 19, 'stop', '/stɑːp/', '/stɒp/', 13),
(398, 19, 'go', '/ɡoʊ/', '/ɡəʊ/', 14),
(399, 19, 'cross', '/krɔːs/', '/krɒs/', 15),
(400, 19, 'street', '/striːt/', '/striːt/', 16),
(401, 19, 'road', '/roʊd/', '/rəʊd/', 17),
(402, 19, 'traffic', '/ˈtræfɪk/', '/ˈtræfɪk/', 18),
(404, 19, 'far', '/fɑːr/', '/fɑː/', 20),
(406, 19, 'walk', '/wɔːk/', '/wɔːk/', 22),
(407, 19, 'drive', '/draɪv/', '/draɪv/', 23),
(408, 19, 'map', '/mæp/', '/mæp/', 24),
(409, 19, 'station', '/ˈsteɪʃn/', '/ˈsteɪʃn/', 25);

INSERT INTO wp_word_sense (id, word_id, pos, meaning, sort) VALUES
(385, 385, 'n.', '公共汽车', 1),
(386, 386, 'n.', '小汽车', 1),
(387, 387, 'n.', '自行车', 1),
(388, 388, 'n.', '出租车', 1),
(390, 390, 'n.', '火车', 1),
(391, 391, 'n.', '飞机', 1),
(392, 392, 'n.', '船', 1),
(393, 393, 'v.', '转弯', 1),
(396, 396, 'adv.', '直走', 1),
(397, 397, 'v.', '停止', 1),
(398, 398, 'v.', '走；去', 1),
(399, 399, 'v.', '穿越；横过', 1),
(400, 400, 'n.', '街道', 1),
(401, 401, 'n.', '公路；道路', 1),
(402, 402, 'n.', '交通', 1),
(404, 404, 'adj.', '远的', 1),
(406, 406, 'v.', '步行', 1),
(407, 407, 'v.', '驾驶', 1),
(408, 408, 'n.', '地图', 1),
(409, 409, 'n.', '车站', 1);

INSERT INTO wp_word_example (id, word_id, sentence, translation, sort) VALUES
(385, 385, 'I go to school by bus.', '我乘公共汽车上学。', 1),
(386, 386, 'My dad drives a red car.', '我爸爸开一辆红色小汽车。', 1),
(387, 387, 'She rides her bike to school.', '她骑自行车上学。', 1),
(388, 388, 'We took a taxi to the airport.', '我们乘出租车去机场。', 1),
(390, 390, 'We traveled by train.', '我们乘火车旅行。', 1),
(391, 391, 'The plane flies very fast.', '飞机飞得非常快。', 1),
(392, 392, 'We took a ship to the island.', '我们乘船去那个岛屿。', 1),
(393, 393, 'Turn left at the next corner.', '在下一个路口向左转。', 1),
(396, 396, 'Go straight and turn left.', '直走然后左转。', 1),
(397, 397, 'Stop at the red light.', '在红灯处停车。', 1),
(398, 398, 'Go straight for two blocks.', '直走两个街区。', 1),
(399, 399, 'Cross the street at the zebra crossing.', '在斑马线处过马路。', 1),
(400, 400, 'There are many shops on this street.', '这条街上有很多商店。', 1),
(401, 401, 'This road leads to the school.', '这条路通向学校。', 1),
(402, 402, 'There is heavy traffic in the morning.', '早上交通很拥挤。', 1),
(404, 404, 'Is the park far from here?', '公园离这里远吗？', 1),
(406, 406, 'I walk to school every day.', '我每天步行上学。', 1),
(407, 407, 'My mum can drive a car.', '我妈妈会开车。', 1),
(408, 408, 'Can you read a map?', '你会看地图吗？', 1),
(409, 409, 'The train station is very big.', '火车站非常大。', 1);

-- ------------------------------------------------------------
-- 第20章：）兴趣爱好与职业
-- ------------------------------------------------------------
INSERT INTO wp_word (id, chapter_id, word, phonetic_us, phonetic_uk, sort) VALUES
(410, 20, 'hobby', '/ˈhɑːbi/', '/ˈhɒbi/',  1),
(411, 20, 'music', '/ˈmjuːzɪk/', '/ˈmjuːzɪk/',  2),
(412, 20, 'sport', '/spɔːrt/', '/spɔːt/',  3),
(413, 20, 'reading', '/ˈriːdɪŋ/', '/ˈriːdɪŋ/',  4),
(414, 20, 'painting', '/ˈpeɪntɪŋ/', '/ˈpeɪntɪŋ/',  5),
(415, 20, 'cooking', '/ˈkʊkɪŋ/', '/ˈkʊkɪŋ/',  6),
(416, 20, 'fishing', '/ˈfɪʃɪŋ/', '/ˈfɪʃɪŋ/',  7),
(417, 20, 'hiking', '/ˈhaɪkɪŋ/', '/ˈhaɪkɪŋ/',  8),
(418, 20, 'doctor', '/ˈdɑːktər/', '/ˈdɒktə/',  9),
(419, 20, 'teacher', '/ˈtiːtʃər/', '/ˈtiːtʃə/', 10),
(420, 20, 'nurse', '/nɜːrs/', '/nɜːs/', 11),
(421, 20, 'driver', '/ˈdraɪvər/', '/ˈdraɪvə/', 12),
(422, 20, 'farmer', '/ˈfɑːrmər/', '/ˈfɑːmə/', 13),
(423, 20, 'cook', '/kʊk/', '/kʊk/', 14),
(424, 20, 'singer', '/ˈsɪŋər/', '/ˈsɪŋə/', 15),
(425, 20, 'dancer', '/ˈdænsər/', '/ˈdɑːnsə/', 16),
(426, 20, 'artist', '/ˈɑːrtɪst/', '/ˈɑːtɪst/', 17),
(427, 20, 'pilot', '/ˈpaɪlət/', '/ˈpaɪlət/', 18),
(428, 20, 'police', '/pəˈliːs/', '/pəˈliːs/', 19),
(429, 20, 'engineer', '/ˌendʒɪˈnɪr/', '/ˌendʒɪˈnɪə/', 20);

INSERT INTO wp_word_sense (id, word_id, pos, meaning, sort) VALUES
(410, 410, 'n.', '爱好', 1),
(411, 411, 'n.', '音乐', 1),
(412, 412, 'n.', '运动', 1),
(413, 413, 'n.', '阅读', 1),
(414, 414, 'n.', '绘画', 1),
(415, 415, 'n.', '烹饪', 1),
(416, 416, 'n.', '钓鱼', 1),
(417, 417, 'n.', '远足', 1),
(418, 418, 'n.', '医生', 1),
(419, 419, 'n.', '老师', 1),
(420, 420, 'n.', '护士', 1),
(421, 421, 'n.', '司机', 1),
(422, 422, 'n.', '农民', 1),
(423, 423, 'n.', '厨师', 1),
(424, 424, 'n.', '歌手', 1),
(425, 425, 'n.', '舞蹈演员', 1),
(426, 426, 'n.', '艺术家', 1),
(427, 427, 'n.', '飞行员', 1),
(428, 428, 'n.', '警察', 1),
(429, 429, 'n.', '工程师', 1);

INSERT INTO wp_word_example (id, word_id, sentence, translation, sort) VALUES
(410, 410, 'My hobby is reading books.', '我的爱好是读书。', 1),
(411, 411, 'I love listening to music.', '我喜欢听音乐。', 1),
(412, 412, 'My favourite sport is football.', '我最喜欢的运动是足球。', 1),
(413, 413, 'Reading is a great hobby.', '阅读是一个很好的爱好。', 1),
(414, 414, 'She is good at painting.', '她擅长绘画。', 1),
(415, 415, 'He enjoys cooking with his mum.', '他喜欢和妈妈一起做饭。', 1),
(416, 416, 'We go fishing on weekends.', '我们周末去钓鱼。', 1),
(417, 417, 'Hiking in the mountains is fun.', '在山里远足很有趣。', 1),
(418, 418, 'The doctor helps sick people.', '医生帮助病人。', 1),
(419, 419, 'My teacher is very patient.', '我的老师非常耐心。', 1),
(420, 420, 'The nurse takes care of the patients.', '护士照顾病人。', 1),
(421, 421, 'The bus driver is very friendly.', '公共汽车司机非常友好。', 1),
(422, 422, 'The farmer grows rice and wheat.', '农民种植水稻和小麦。', 1),
(423, 423, 'The cook made a delicious meal.', '厨师做了一顿美味的饭。', 1),
(424, 424, 'She wants to be a singer.', '她想成为一名歌手。', 1),
(425, 425, 'The dancer moved beautifully.', '舞蹈演员舞姿优美。', 1),
(426, 426, 'The artist painted a lovely picture.', '艺术家画了一幅可爱的画。', 1),
(427, 427, 'The pilot flies the plane safely.', '飞行员安全地驾驶飞机。', 1),
(428, 428, 'The police help keep us safe.', '警察帮助我们保持安全。', 1),
(429, 429, 'He wants to be an engineer.', '他想成为一名工程师。', 1);

-- ------------------------------------------------------------
-- 第21章：一）学科与课程
-- ------------------------------------------------------------
INSERT INTO wp_word (id, chapter_id, word, phonetic_us, phonetic_uk, sort) VALUES
(430, 21, 'Chinese', '/ˈtʃaɪniːz/', '/ˈtʃaɪniːz/',  1),
(431, 21, 'math', '/mæθ/', '/mæθ/',  2),
(432, 21, 'English', '/ˈɪŋɡlɪʃ/', '/ˈɪŋɡlɪʃ/',  3),
(433, 21, 'science', '/ˈsaɪəns/', '/ˈsaɪəns/',  4),
(435, 21, 'art', '/ɑːrt/', '/ɑːt/',  6),
(436, 21, 'PE', '/ˌpiː ˈiː/', '/ˌpiː ˈiː/',  7),
(437, 21, 'subject', '/ˈsʌbdʒɪkt/', '/ˈsʌbdʒɪkt/',  8),
(438, 21, 'favourite', '/ˈfeɪvərɪt/', '/ˈfeɪvərɪt/',  9),
(439, 21, 'difficult', '/ˈdɪfɪkəlt/', '/ˈdɪfɪkəlt/', 10),
(440, 21, 'easy', '/ˈiːzi/', '/ˈiːzi/', 11),
(441, 21, 'interesting', '/ˈɪntrəstɪŋ/', '/ˈɪntrəstɪŋ/', 12),
(442, 21, 'lesson', '/ˈlesn/', '/ˈlesn/', 13),
(443, 21, 'homework', '/ˈhoʊmwɜːrk/', '/ˈhəʊmwɜːk/', 14),
(444, 21, 'test', '/test/', '/test/', 15);

INSERT INTO wp_word_sense (id, word_id, pos, meaning, sort) VALUES
(430, 430, 'n.', '语文；中文', 1),
(431, 431, 'n.', '数学', 1),
(432, 432, 'n.', '英语', 1),
(433, 433, 'n.', '科学', 1),
(435, 435, 'n.', '美术；艺术', 1),
(436, 436, 'n.', '体育（Physical Education的缩写）', 1),
(437, 437, 'n.', '科目；学科', 1),
(438, 438, 'adj.', '最喜欢的', 1),
(439, 439, 'adj.', '难的；困难的', 1),
(440, 440, 'adj.', '容易的；简单的', 1),
(441, 441, 'adj.', '有趣的；吸引人的', 1),
(442, 442, 'n.', '课；课时', 1),
(443, 443, 'n.', '家庭作业', 1),
(444, 444, 'n.', '测验；考试', 1);

INSERT INTO wp_word_example (id, word_id, sentence, translation, sort) VALUES
(430, 430, 'I love Chinese class.', '我喜欢语文课。', 1),
(431, 431, 'Math is my favourite subject.', '数学是我最喜欢的科目。', 1),
(432, 432, 'We have English every day.', '我们每天都有英语课。', 1),
(433, 433, 'We do experiments in science class.', '我们在科学课上做实验。', 1),
(435, 435, 'I draw pictures in art class.', '我在美术课上画画。', 1),
(436, 436, 'We run and jump in PE class.', '我们在体育课上跑步和跳跃。', 1),
(437, 437, 'English is my favourite subject.', '英语是我最喜欢的科目。', 1),
(438, 438, 'What is your favourite food?', '你最喜欢的食物是什么？', 1),
(439, 439, 'Maths can be difficult at first.', '数学一开始可能很难。', 1),
(440, 440, 'This question is easy.', '这道题很容易。', 1),
(441, 441, 'Science is very interesting.', '科学非常有趣。', 1),
(442, 442, 'We have four lessons in the morning.', '我们上午有四节课。', 1),
(443, 443, 'I do my homework after school.', '我放学后做家庭作业。', 1),
(444, 444, 'We have a maths test tomorrow.', '我们明天有数学测验。', 1);

-- ------------------------------------------------------------
-- 第22章：二）运动与体育
-- ------------------------------------------------------------
INSERT INTO wp_word (id, chapter_id, word, phonetic_us, phonetic_uk, sort) VALUES
(445, 22, 'football', '/ˈfʊtbɔːl/', '/ˈfʊtbɔːl/',  1),
(446, 22, 'basketball', '/ˈbæskɪtbɔːl/', '/ˈbɑːskɪtbɔːl/',  2),
(447, 22, 'volleyball', '/ˈvɑːlibɔːl/', '/ˈvɒlibɔːl/',  3),
(448, 22, 'ping-pong', '/ˈpɪŋ pɒŋ/', '/ˈpɪŋ pɒŋ/',  4),
(449, 22, 'tennis', '/ˈtenɪs/', '/ˈtenɪs/',  5),
(450, 22, 'badminton', '/ˈbædmɪntən/', '/ˈbædmɪntən/',  6),
(451, 22, 'exercise', '/ˈeksərsaɪz/', '/ˈeksəsaɪz/',  7),
(452, 22, 'team', '/tiːm/', '/tiːm/',  8),
(453, 22, 'match', '/mætʃ/', '/mætʃ/',  9),
(454, 22, 'player', '/ˈpleɪər/', '/ˈpleɪə/', 10),
(455, 22, 'score', '/skɔːr/', '/skɔː/', 11),
(456, 22, 'skate', '/skeɪt/', '/skeɪt/', 12),
(457, 22, 'ski', '/skiː/', '/skiː/', 13),
(458, 22, 'win', '/wɪn/', '/wɪn/', 14),
(459, 22, 'lose', '/luːz/', '/luːz/', 15),
(460, 22, 'throw', '/θroʊ/', '/θrəʊ/', 16),
(461, 22, 'catch', '/kætʃ/', '/kætʃ/', 17),
(462, 22, 'kick', '/kɪk/', '/kɪk/', 18),
(463, 22, 'race', '/reɪs/', '/reɪs/', 19);

INSERT INTO wp_word_sense (id, word_id, pos, meaning, sort) VALUES
(445, 445, 'n.', '足球', 1),
(446, 446, 'n.', '篮球', 1),
(447, 447, 'n.', '排球', 1),
(448, 448, 'n.', '乒乓球', 1),
(449, 449, 'n.', '网球', 1),
(450, 450, 'n.', '羽毛球', 1),
(451, 451, 'n.', '锻炼；运动', 1),
(452, 452, 'n.', '队；团队', 1),
(453, 453, 'n.', '比赛；竞赛', 1),
(454, 454, 'n.', '运动员；选手', 1),
(455, 455, 'n.', '得分；比分', 1),
(456, 456, 'v.', '溜冰；滑冰', 1),
(457, 457, 'v.', '滑雪', 1),
(458, 458, 'v.', '赢；获胜', 1),
(459, 459, 'v.', '输；失败', 1),
(460, 460, 'v.', '扔；投掷', 1),
(461, 461, 'v.', '接；抓住', 1),
(462, 462, 'v.', '踢', 1),
(463, 463, 'n.', '赛跑；竞赛', 1);

INSERT INTO wp_word_example (id, word_id, sentence, translation, sort) VALUES
(445, 445, 'He plays football every Saturday.', '他每个周六踢足球。', 1),
(446, 446, 'Our team won the basketball match.', '我们队赢得了篮球比赛。', 1),
(447, 447, 'She is good at volleyball.', '她擅长打排球。', 1),
(448, 448, 'Ping-pong is very popular in China.', '乒乓球在中国非常流行。', 1),
(449, 449, 'My sister likes to play tennis.', '我姐姐喜欢打网球。', 1),
(450, 450, 'Let us play badminton after school.', '放学后我们去打羽毛球吧。', 1),
(451, 451, 'Exercise is good for your health.', '锻炼对你的健康有好处。', 1),
(452, 452, 'We are in the same team.', '我们在同一个队。', 1),
(453, 453, 'The football match starts at three.', '足球比赛三点开始。', 1),
(454, 454, 'He is a great football player.', '他是一名出色的足球运动员。', 1),
(455, 455, 'The score is two to one.', '比分是二比一。', 1),
(456, 456, 'Can you skate on ice?', '你会在冰上溜冰吗？', 1),
(457, 457, 'I want to learn to ski.', '我想学习滑雪。', 1),
(458, 458, 'Our team will win!', '我们队会赢的！', 1),
(459, 459, 'Do not be sad if you lose.', '如果输了不要难过。', 1),
(460, 460, 'Throw the ball to me.', '把球扔给我。', 1),
(461, 461, 'Can you catch the ball?', '你能接住球吗？', 1),
(462, 462, 'He can kick the ball very far.', '他能把球踢得很远。', 1),
(463, 463, 'She won the running race.', '她赢得了赛跑。', 1);

-- ------------------------------------------------------------
-- 第23章：三）情绪与感受
-- ------------------------------------------------------------
INSERT INTO wp_word (id, chapter_id, word, phonetic_us, phonetic_uk, sort) VALUES
(465, 23, 'happy', '/ˈhæpi/', '/ˈhæpi/',  1),
(466, 23, 'sad', '/sæd/', '/sæd/',  2),
(467, 23, 'angry', '/ˈæŋɡri/', '/ˈæŋɡri/',  3),
(468, 23, 'tired', '/ˈtaɪərd/', '/ˈtaɪəd/',  4),
(469, 23, 'hungry', '/ˈhʌŋɡri/', '/ˈhʌŋɡri/',  5),
(470, 23, 'thirsty', '/ˈθɜːrsti/', '/ˈθɜːsti/',  6),
(471, 23, 'sick', '/sɪk/', '/sɪk/',  7),
(472, 23, 'excited', '/ɪkˈsaɪtɪd/', '/ɪkˈsaɪtɪd/',  8),
(473, 23, 'bored', '/bɔːrd/', '/bɔːd/',  9),
(474, 23, 'scared', '/skerd/', '/skeəd/', 10),
(475, 23, 'surprised', '/sərˈpraɪzd/', '/səˈpraɪzd/', 11),
(476, 23, 'worried', '/ˈwɜːrid/', '/ˈwʌrid/', 12),
(477, 23, 'nervous', '/ˈnɜːrvəs/', '/ˈnɜːvəs/', 13),
(478, 23, 'fine', '/faɪn/', '/faɪn/', 14),
(479, 23, 'great', '/ɡreɪt/', '/ɡreɪt/', 15);

INSERT INTO wp_word_sense (id, word_id, pos, meaning, sort) VALUES
(465, 465, 'adj.', '快乐的；高兴的', 1),
(466, 466, 'adj.', '难过的；悲伤的', 1),
(467, 467, 'adj.', '生气的；愤怒的', 1),
(468, 468, 'adj.', '疲倦的；累的', 1),
(469, 469, 'adj.', '饥饿的；饿的', 1),
(470, 470, 'adj.', '口渴的', 1),
(471, 471, 'adj.', '生病的；不舒服的', 1),
(472, 472, 'adj.', '兴奋的；激动的', 1),
(473, 473, 'adj.', '无聊的；厌倦的', 1),
(474, 474, 'adj.', '害怕的；恐惧的', 1),
(475, 475, 'adj.', '惊讶的；吃惊的', 1),
(476, 476, 'adj.', '担心的；忧虑的', 1),
(477, 477, 'adj.', '紧张的', 1),
(478, 478, 'adj.', '好的；健康的', 1),
(479, 479, 'adj.', '很好的；极好的', 1);

INSERT INTO wp_word_example (id, word_id, sentence, translation, sort) VALUES
(465, 465, 'I am happy today.', '我今天很开心。', 1),
(466, 466, 'She looks sad today.', '她今天看起来很难过。', 1),
(467, 467, 'Do not be angry with me.', '别对我生气。', 1),
(468, 468, 'I am tired after PE class.', '体育课后我很累。', 1),
(469, 469, 'I am hungry. Let us eat.', '我饿了，我们去吃饭吧。', 1),
(470, 470, 'I am thirsty. Can I have some water?', '我口渴了，我能喝点水吗？', 1),
(471, 471, 'She is sick and stays at home.', '她生病了，待在家里。', 1),
(472, 472, 'I am so excited about the trip.', '我对这次旅行感到非常兴奋。', 1),
(473, 473, 'He looks bored in class.', '他在课堂上看起来很无聊。', 1),
(474, 474, 'She is scared of the dark.', '她害怕黑暗。', 1),
(475, 475, 'I was surprised by the gift.', '我被这份礼物惊到了。', 1),
(476, 476, 'Mum is worried about me.', '妈妈担心我。', 1),
(477, 477, 'I am nervous before the test.', '考试前我很紧张。', 1),
(478, 478, 'How are you? I am fine.', '你好吗？我很好。', 1),
(479, 479, 'That sounds great!', '听起来很棒！', 1);

-- ------------------------------------------------------------
-- 第24章：四）国家与语言
-- ------------------------------------------------------------
INSERT INTO wp_word (id, chapter_id, word, phonetic_us, phonetic_uk, sort) VALUES
(480, 24, 'China', '/ˈtʃaɪnə/', '/ˈtʃaɪnə/',  1),
(481, 24, 'America', '/əˈmerɪkə/', '/əˈmerɪkə/',  2),
(482, 24, 'England', '/ˈɪŋɡlənd/', '/ˈɪŋɡlənd/',  3),
(483, 24, 'Canada', '/ˈkænədə/', '/ˈkænədə/',  4),
(484, 24, 'Australia', '/ɒˈstreɪliə/', '/ɒˈstreɪliə/',  5),
(485, 24, 'France', '/fræns/', '/frɑːns/',  6),
(486, 24, 'Japan', '/dʒəˈpæn/', '/dʒəˈpæn/',  7),
(487, 24, 'country', '/ˈkʌntri/', '/ˈkʌntri/',  8),
(488, 24, 'city', '/ˈsɪti/', '/ˈsɪti/',  9),
(489, 24, 'capital', '/ˈkæpɪtl/', '/ˈkæpɪtl/', 10),
(490, 24, 'language', '/ˈlæŋɡwɪdʒ/', '/ˈlæŋɡwɪdʒ/', 11),
(491, 24, 'world', '/wɜːrld/', '/wɜːld/', 12),
(492, 24, 'French', '/frentʃ/', '/frentʃ/', 13),
(493, 24, 'Japanese', '/ˌdʒæpəˈniːz/', '/ˌdʒæpəˈniːz/', 14);

INSERT INTO wp_word_sense (id, word_id, pos, meaning, sort) VALUES
(480, 480, 'n.', '中国', 1),
(481, 481, 'n.', '美国；美洲', 1),
(482, 482, 'n.', '英国；英格兰', 1),
(483, 483, 'n.', '加拿大', 1),
(484, 484, 'n.', '澳大利亚', 1),
(485, 485, 'n.', '法国', 1),
(486, 486, 'n.', '日本', 1),
(487, 487, 'n.', '国家', 1),
(488, 488, 'n.', '城市', 1),
(489, 489, 'n.', '首都', 1),
(490, 490, 'n.', '语言', 1),
(491, 491, 'n.', '世界', 1),
(492, 492, 'n.', '法语；法国人', 1),
(493, 493, 'n.', '日语；日本人', 1);

INSERT INTO wp_word_example (id, word_id, sentence, translation, sort) VALUES
(480, 480, 'China is a big country.', '中国是一个大国。', 1),
(481, 481, 'America is far from China.', '美国离中国很远。', 1),
(482, 482, 'English comes from England.', '英语来自英格兰。', 1),
(483, 483, 'Canada is next to America.', '加拿大紧邻美国。', 1),
(484, 484, 'Australia is a big island country.', '澳大利亚是一个大岛国。', 1),
(485, 485, 'The Eiffel Tower is in France.', '埃菲尔铁塔在法国。', 1),
(486, 486, 'Japan is near China.', '日本靠近中国。', 1),
(487, 487, 'China is my country.', '中国是我的国家。', 1),
(488, 488, 'Beijing is the capital city of China.', '北京是中国的首都城市。', 1),
(489, 489, 'London is the capital of England.', '伦敦是英格兰的首都。', 1),
(490, 490, 'English is a world language.', '英语是一门世界语言。', 1),
(491, 491, 'There are many countries in the world.', '世界上有很多国家。', 1),
(492, 492, 'She is learning French.', '她在学法语。', 1),
(493, 493, 'He can speak Japanese.', '他会说日语。', 1);

-- ------------------------------------------------------------
-- 第25章：五）健康与疾病
-- ------------------------------------------------------------
INSERT INTO wp_word (id, chapter_id, word, phonetic_us, phonetic_uk, sort) VALUES
(495, 25, 'headache', '/ˈhedeɪk/', '/ˈhedeɪk/',  1),
(496, 25, 'stomachache', '/ˈstʌməkeɪk/', '/ˈstʌməkeɪk/',  2),
(497, 25, 'toothache', '/ˈtuːθeɪk/', '/ˈtuːθeɪk/',  3),
(498, 25, 'cold', '/koʊld/', '/kəʊld/',  4),
(499, 25, 'fever', '/ˈfiːvər/', '/ˈfiːvə/',  5),
(500, 25, 'cough', '/kɒf/', '/kɒf/',  6),
(501, 25, 'medicine', '/ˈmedɪsn/', '/ˈmedɪsn/',  7),
(502, 25, 'hospital', '/ˈhɒspɪtl/', '/ˈhɒspɪtl/',  8),
(503, 25, 'healthy', '/ˈhelθi/', '/ˈhelθi/',  9),
(504, 25, 'health', '/helθ/', '/helθ/', 10),
(505, 25, 'rest', '/rest/', '/rest/', 11),
(506, 25, 'hurt', '/hɜːrt/', '/hɜːt/', 12),
(507, 25, 'better', '/ˈbetər/', '/ˈbetə/', 13),
(508, 25, 'ill', '/ɪl/', '/ɪl/', 14),
(509, 25, 'diet', '/ˈdaɪət/', '/ˈdaɪət/', 15);

INSERT INTO wp_word_sense (id, word_id, pos, meaning, sort) VALUES
(495, 495, 'n.', '头痛', 1),
(496, 496, 'n.', '胃痛；肚子痛', 1),
(497, 497, 'n.', '牙痛', 1),
(498, 498, 'n.', '感冒', 1),
(499, 499, 'n.', '发烧；高烧', 1),
(500, 500, 'n.', '咳嗽', 1),
(501, 501, 'n.', '药；药物', 1),
(502, 502, 'n.', '医院', 1),
(503, 503, 'adj.', '健康的', 1),
(504, 504, 'n.', '健康', 1),
(505, 505, 'v.', '休息', 1),
(506, 506, 'v.', '受伤；疼痛', 1),
(507, 507, 'adj.', '好转的；更好的', 1),
(508, 508, 'adj.', '生病的；不舒服的', 1),
(509, 509, 'n.', '饮食；日常食物', 1);

INSERT INTO wp_word_example (id, word_id, sentence, translation, sort) VALUES
(495, 495, 'I have a headache today.', '我今天头痛。', 1),
(496, 496, 'She has a stomachache after lunch.', '她午饭后胃痛。', 1),
(497, 497, 'He has a toothache and sees a dentist.', '他牙痛，去看牙医。', 1),
(498, 498, 'I have a cold and stay at home.', '我感冒了，待在家里。', 1),
(499, 499, 'She has a high fever.', '她发高烧了。', 1),
(500, 500, 'He has a bad cough.', '他咳嗽得很厉害。', 1),
(501, 501, 'Take the medicine three times a day.', '每天服药三次。', 1),
(502, 502, 'My grandma is in the hospital.', '我奶奶在医院。', 1),
(503, 503, 'Eating vegetables keeps you healthy.', '吃蔬菜让你保持健康。', 1),
(504, 504, 'Health is more important than money.', '健康比金钱更重要。', 1),
(505, 505, 'You should rest when you are sick.', '生病时你应该休息。', 1),
(506, 506, 'My leg hurts after running.', '跑步后我的腿疼。', 1),
(507, 507, 'I feel better after taking medicine.', '吃了药后我感觉好多了。', 1),
(508, 508, 'The boy is ill and cannot go to school.', '这个男孩生病了，不能上学。', 1),
(509, 509, 'A good diet keeps you healthy.', '良好的饮食让你保持健康。', 1);

-- ------------------------------------------------------------
-- 第26章：问候与日常用语
-- ------------------------------------------------------------
INSERT INTO wp_word (id, chapter_id, word, phonetic_us, phonetic_uk, sort) VALUES
(510, 26, 'hello', '/həˈloʊ/', '/həˈləʊ/',  1),
(511, 26, 'hi', '/haɪ/', '/haɪ/',  2),
(512, 26, 'goodbye', '/ˌɡʊdˈbaɪ/', '/ˌɡʊdˈbaɪ/',  3),
(513, 26, 'good morning', '/ˌɡʊd ˈmɔːrnɪŋ/', '/ˌɡʊd ˈmɔːnɪŋ/',  4),
(514, 26, 'good afternoon', '/ˌɡʊd ˌæftərˈnuːn/', '/ˌɡʊd ˌɑːftəˈnuːn/',  5),
(515, 26, 'good evening', '/ˌɡʊd ˈiːvnɪŋ/', '/ˌɡʊd ˈiːvnɪŋ/',  6),
(516, 26, 'good night', '/ˌɡʊd naɪt/', '/ˌɡʊd naɪt/',  7),
(517, 26, 'thank you', '/ˈθæŋk juː/', '/ˈθæŋk juː/',  8),
(518, 26, 'sorry', '/ˈsɒri/', '/ˈsɒri/',  9),
(519, 26, 'excuse me', '/ɪkˈskjuːz miː/', '/ɪkˈskjuːz miː/', 10),
(520, 26, 'please', '/pliːz/', '/pliːz/', 11),
(521, 26, 'welcome', '/ˈwelkəm/', '/ˈwelkəm/', 12),
(522, 26, 'yes', '/jes/', '/jes/', 13),
(523, 26, 'no', '/noʊ/', '/nəʊ/', 14),
(524, 26, 'OK', '/ˌoʊˈkeɪ/', '/ˌəʊˈkeɪ/', 15),
(525, 26, 'nice to meet you', '/naɪs tə miːt juː/', '/naɪs tə miːt juː/', 16);

INSERT INTO wp_word_sense (id, word_id, pos, meaning, sort) VALUES
(510, 510, 'int.', '你好；喂', 1),
(511, 511, 'int.', '嗨；你好', 1),
(512, 512, 'int.', '再见', 1),
(513, 513, 'phr.', '早上好', 1),
(514, 514, 'phr.', '下午好', 1),
(515, 515, 'phr.', '晚上好', 1),
(516, 516, 'phr.', '晚安', 1),
(517, 517, 'phr.', '谢谢你', 1),
(518, 518, 'int.', '对不起；抱歉', 1),
(519, 519, 'phr.', '打扰了；劳驾', 1),
(520, 520, 'adv.', '请', 1),
(521, 521, 'int.', '欢迎；不客气', 1),
(522, 522, 'adv.', '是的；对', 1),
(523, 523, 'adv.', '不；不是', 1),
(524, 524, 'int.', '好的；可以', 1),
(525, 525, 'phr.', '很高兴认识你', 1);

INSERT INTO wp_word_example (id, word_id, sentence, translation, sort) VALUES
(510, 510, 'Hello! My name is Tom.', '你好！我叫汤姆。', 1),
(511, 511, 'Hi! How are you?', '嗨！你好吗？', 1),
(512, 512, 'Goodbye! See you tomorrow.', '再见！明天见。', 1),
(513, 513, 'Good morning, Miss Li!', '李老师，早上好！', 1),
(514, 514, 'Good afternoon, everyone.', '大家下午好。', 1),
(515, 515, 'Good evening! Come in, please.', '晚上好！请进。', 1),
(516, 516, 'Good night! Sleep well.', '晚安！好好睡觉。', 1),
(517, 517, 'Thank you for your help.', '谢谢你的帮助。', 1),
(518, 518, 'Sorry, I am late.', '对不起，我迟到了。', 1),
(519, 519, 'Excuse me, where is the library?', '打扰了，图书馆在哪里？', 1),
(520, 520, 'Sit down, please.', '请坐下。', 1),
(521, 521, 'Welcome to our school!', '欢迎来到我们学校！', 1),
(522, 522, 'Yes, I like apples.', '是的，我喜欢苹果。', 1),
(523, 523, 'No, I do not have a pet.', '不，我没有宠物。', 1),
(524, 524, 'Is that OK with you?', '你觉得可以吗？', 1),
(525, 525, 'Nice to meet you, I am Amy.', '很高兴认识你，我叫艾米。', 1);


-- ------------------------------------------------------------
-- 第27章：数字扩展（十三至一百）
-- ------------------------------------------------------------
INSERT INTO wp_word (id, chapter_id, word, phonetic_us, phonetic_uk, sort) VALUES
(526, 27, 'thirteen', '/ˌθɜːrˈtiːn/', '/ˌθɜːˈtiːn/',  1),
(527, 27, 'fourteen', '/ˌfɔːrˈtiːn/', '/ˌfɔːˈtiːn/',  2),
(528, 27, 'fifteen', '/ˌfɪfˈtiːn/', '/ˌfɪfˈtiːn/',  3),
(529, 27, 'sixteen', '/ˌsɪkˈstiːn/', '/ˌsɪkˈstiːn/',  4),
(530, 27, 'seventeen', '/ˌsevnˈtiːn/', '/ˌsevnˈtiːn/',  5),
(531, 27, 'eighteen', '/ˌeɪˈtiːn/', '/ˌeɪˈtiːn/',  6),
(532, 27, 'nineteen', '/ˌnaɪnˈtiːn/', '/ˌnaɪnˈtiːn/',  7),
(533, 27, 'twenty', '/ˈtwenti/', '/ˈtwenti/',  8),
(534, 27, 'thirty', '/ˈθɜːrti/', '/ˈθɜːti/',  9),
(535, 27, 'forty', '/ˈfɔːrti/', '/ˈfɔːti/', 10),
(536, 27, 'fifty', '/ˈfɪfti/', '/ˈfɪfti/', 11),
(537, 27, 'sixty', '/ˈsɪksti/', '/ˈsɪksti/', 12),
(538, 27, 'seventy', '/ˈsevnti/', '/ˈsevnti/', 13),
(539, 27, 'eighty', '/ˈeɪti/', '/ˈeɪti/', 14),
(540, 27, 'ninety', '/ˈnaɪnti/', '/ˈnaɪnti/', 15),
(541, 27, 'hundred', '/ˈhʌndrəd/', '/ˈhʌndrəd/', 16),
(542, 27, 'thousand', '/ˈθaʊznd/', '/ˈθaʊznd/', 17),
(543, 27, 'number', '/ˈnʌmbər/', '/ˈnʌmbə/', 18);

INSERT INTO wp_word_sense (id, word_id, pos, meaning, sort) VALUES
(526, 526, 'num.', '十三', 1),
(527, 527, 'num.', '十四', 1),
(528, 528, 'num.', '十五', 1),
(529, 529, 'num.', '十六', 1),
(530, 530, 'num.', '十七', 1),
(531, 531, 'num.', '十八', 1),
(532, 532, 'num.', '十九', 1),
(533, 533, 'num.', '二十', 1),
(534, 534, 'num.', '三十', 1),
(535, 535, 'num.', '四十', 1),
(536, 536, 'num.', '五十', 1),
(537, 537, 'num.', '六十', 1),
(538, 538, 'num.', '七十', 1),
(539, 539, 'num.', '八十', 1),
(540, 540, 'num.', '九十', 1),
(541, 541, 'num.', '百；一百', 1),
(542, 542, 'num.', '千；一千', 1),
(543, 543, 'n.', '数字；号码', 1);

INSERT INTO wp_word_example (id, word_id, sentence, translation, sort) VALUES
(526, 526, 'She is thirteen years old.', '她十三岁了。', 1),
(527, 527, 'He has fourteen books.', '他有十四本书。', 1),
(528, 528, 'There are fifteen students in our group.', '我们小组有十五名学生。', 1),
(529, 529, 'I have sixteen pencils.', '我有十六支铅笔。', 1),
(530, 530, 'My cousin is seventeen.', '我的表弟十七岁。', 1),
(531, 531, 'There are eighteen birds on the tree.', '树上有十八只鸟。', 1),
(532, 532, 'She scored nineteen points.', '她得了十九分。', 1),
(533, 533, 'I have twenty yuan.', '我有二十元钱。', 1),
(534, 534, 'There are thirty days in a month.', '一个月有三十天。', 1),
(535, 535, 'My dad is forty years old.', '我爸爸四十岁了。', 1),
(536, 536, 'The book costs fifty yuan.', '这本书五十元。', 1),
(537, 537, 'Grandpa is sixty years old.', '爷爷六十岁了。', 1),
(538, 538, 'Grandma walks seventy steps each morning.', '奶奶每天早上走七十步。', 1),
(539, 539, 'My great-grandmother is eighty.', '我的曾祖母八十岁了。', 1),
(540, 540, 'She scored ninety on the test.', '她考试得了九十分。', 1),
(541, 541, 'There are one hundred students.', '有一百名学生。', 1),
(542, 542, 'This coat costs one thousand yuan.', '这件外套一千元。', 1),
(543, 543, 'What is your phone number?', '你的电话号码是多少？', 1);


-- ------------------------------------------------------------
-- 第28章：公共场所与社区
-- ------------------------------------------------------------
INSERT INTO wp_word (id, chapter_id, word, phonetic_us, phonetic_uk, sort) VALUES
(544, 28, 'park', '/pɑːrk/', '/pɑːk/',  1),
(545, 28, 'zoo', '/zuː/', '/zuː/',  2),
(546, 28, 'cinema', '/ˈsɪnəmə/', '/ˈsɪnəmə/',  3),
(547, 28, 'museum', '/mjuːˈziːəm/', '/mjuːˈziːəm/',  4),
(548, 28, 'bookstore', '/ˈbʊkstɔːr/', '/ˈbʊkstɔː/',  5),
(549, 28, 'supermarket', '/ˈsuːpərˌmɑːrkɪt/', '/ˈsuːpəˌmɑːkɪt/',  6),
(550, 28, 'restaurant', '/ˈrestrɒnt/', '/ˈrestrɒnt/',  7),
(551, 28, 'bank', '/bæŋk/', '/bæŋk/',  8),
(552, 28, 'post office', '/poʊst ˈɒfɪs/', '/pəʊst ˈɒfɪs/',  9),
(553, 28, 'hotel', '/hoʊˈtel/', '/həʊˈtel/', 10),
(554, 28, 'airport', '/ˈerpɔːrt/', '/ˈeəpɔːt/', 11),
(555, 28, 'market', '/ˈmɑːrkɪt/', '/ˈmɑːkɪt/', 12),
(556, 28, 'square', '/skwer/', '/skweə/', 13),
(557, 28, 'police station', '/pəˈliːs ˈsteɪʃn/', '/pəˈliːs ˈsteɪʃn/', 14),
(558, 28, 'shopping mall', '/ˈʃɒpɪŋ mɔːl/', '/ˈʃɒpɪŋ mɔːl/', 15);

INSERT INTO wp_word_sense (id, word_id, pos, meaning, sort) VALUES
(544, 544, 'n.', '公园', 1),
(545, 545, 'n.', '动物园', 1),
(546, 546, 'n.', '电影院', 1),
(547, 547, 'n.', '博物馆', 1),
(548, 548, 'n.', '书店', 1),
(549, 549, 'n.', '超市', 1),
(550, 550, 'n.', '餐厅；饭馆', 1),
(551, 551, 'n.', '银行', 1),
(552, 552, 'n.', '邮局', 1),
(553, 553, 'n.', '酒店；宾馆', 1),
(554, 554, 'n.', '机场', 1),
(555, 555, 'n.', '市场；集市', 1),
(556, 556, 'n.', '广场', 1),
(557, 557, 'n.', '警察局', 1),
(558, 558, 'n.', '购物中心', 1);

INSERT INTO wp_word_example (id, word_id, sentence, translation, sort) VALUES
(544, 544, 'We play in the park on weekends.', '我们周末在公园里玩。', 1),
(545, 545, 'I saw pandas at the zoo.', '我在动物园看到了大熊猫。', 1),
(546, 546, 'Let us go to the cinema tonight.', '今晚我们去电影院吧。', 1),
(547, 547, 'We visited the science museum.', '我们参观了科学博物馆。', 1),
(548, 548, 'I bought a new book at the bookstore.', '我在书店买了一本新书。', 1),
(549, 549, 'Mum buys food at the supermarket.', '妈妈在超市买食物。', 1),
(550, 550, 'We had dinner at a restaurant.', '我们在餐厅吃了晚饭。', 1),
(551, 551, 'Dad goes to the bank to save money.', '爸爸去银行存钱。', 1),
(552, 552, 'I sent a letter from the post office.', '我从邮局寄了一封信。', 1),
(553, 553, 'We stayed at a nice hotel.', '我们住在一家很好的酒店。', 1),
(554, 554, 'My uncle picked us up at the airport.', '我叔叔在机场接了我们。', 1),
(555, 555, 'We buy fresh vegetables at the market.', '我们在市场买新鲜蔬菜。', 1),
(556, 556, 'People dance in the square in the evening.', '晚上人们在广场跳舞。', 1),
(557, 557, 'The police station is near our school.', '警察局在我们学校附近。', 1),
(558, 558, 'There is a big shopping mall in our city.', '我们城市有一个大型购物中心。', 1);

-- ------------------------------------------------------------
-- 第29章：人物与称谓
-- ------------------------------------------------------------
INSERT INTO wp_word (id, chapter_id, word, phonetic_us, phonetic_uk, sort) VALUES
(559, 29, 'boy', '/bɔɪ/', '/bɔɪ/',  1),
(560, 29, 'girl', '/ɡɜːrl/', '/ɡɜːl/',  2),
(561, 29, 'friend', '/frend/', '/frend/',  3),
(562, 29, 'name', '/neɪm/', '/neɪm/',  4),
(563, 29, 'person', '/ˈpɜːrsn/', '/ˈpɜːsn/',  5),
(564, 29, 'man', '/mæn/', '/mæn/',  6),
(565, 29, 'woman', '/ˈwʊmən/', '/ˈwʊmən/',  7),
(566, 29, 'child', '/tʃaɪld/', '/tʃaɪld/',  8),
(567, 29, 'people', '/ˈpiːpl/', '/ˈpiːpl/',  9),
(568, 29, 'student', '/ˈstuːdnt/', '/ˈstjuːdnt/', 10),
(569, 29, 'age', '/eɪdʒ/', '/eɪdʒ/', 11),
(570, 29, 'year', '/jɪr/', '/jɪə/', 12),
(571, 29, 'home', '/hoʊm/', '/həʊm/', 13),
(572, 29, 'word', '/wɜːrd/', '/wɜːd/', 14),
(573, 29, 'story', '/ˈstɔːri/', '/ˈstɔːri/', 15),
(574, 29, 'everyone', '/ˈevriwʌn/', '/ˈevriwʌn/', 16);

INSERT INTO wp_word_sense (id, word_id, pos, meaning, sort) VALUES
(559, 559, 'n.', '男孩', 1),
(560, 560, 'n.', '女孩', 1),
(561, 561, 'n.', '朋友', 1),
(562, 562, 'n.', '名字；姓名', 1),
(563, 563, 'n.', '人；个人', 1),
(564, 564, 'n.', '男人；男性', 1),
(565, 565, 'n.', '女人；女性', 1),
(566, 566, 'n.', '孩子（复数 children）', 1),
(567, 567, 'n.', '人们；人', 1),
(568, 568, 'n.', '学生', 1),
(569, 569, 'n.', '年龄；岁数', 1),
(570, 570, 'n.', '年；岁', 1),
(571, 571, 'n.', '家；住所', 1),
(572, 572, 'n.', '单词；词语', 1),
(573, 573, 'n.', '故事', 1),
(574, 574, 'pron.', '每个人；人人', 1);

INSERT INTO wp_word_example (id, word_id, sentence, translation, sort) VALUES
(559, 559, 'The boy is playing football.', '这个男孩在踢足球。', 1),
(560, 560, 'The girl has long hair.', '这个女孩有一头长发。', 1),
(561, 561, 'She is my best friend.', '她是我最好的朋友。', 1),
(562, 562, 'My name is Li Ming.', '我的名字叫李明。', 1),
(563, 563, 'She is a nice person.', '她是个好人。', 1),
(564, 564, 'The man is a teacher.', '那个男人是老师。', 1),
(565, 565, 'The woman is a doctor.', '那个女人是医生。', 1),
(566, 566, 'Every child likes playing.', '每个孩子都喜欢玩耍。', 1),
(567, 567, 'Many people live in the city.', '很多人住在城市里。', 1),
(568, 568, 'I am a student in Grade 4.', '我是四年级的学生。', 1),
(569, 569, 'What is your age?', '你多少岁了？', 1),
(570, 570, 'She is nine years old.', '她九岁了。', 1),
(571, 571, 'I go home after school.', '放学后我回家。', 1),
(572, 572, 'I learn ten new words every day.', '我每天学十个新单词。', 1),
(573, 573, 'Mum tells me a story at night.', '妈妈晚上给我讲故事。', 1),
(574, 574, 'Everyone is happy today.', '今天每个人都很开心。', 1);


-- ------------------------------------------------------------
-- 第30章：常用形容词与副词
-- ------------------------------------------------------------
INSERT INTO wp_word (id, chapter_id, word, phonetic_us, phonetic_uk, sort) VALUES
(575, 30, 'good', '/ɡʊd/', '/ɡʊd/',  1),
(576, 30, 'nice', '/naɪs/', '/naɪs/',  2),
(577, 30, 'big', '/bɪɡ/', '/bɪɡ/',  3),
(578, 30, 'small', '/smɔːl/', '/smɔːl/',  4),
(579, 30, 'fast', '/fæst/', '/fɑːst/',  5),
(580, 30, 'slow', '/sloʊ/', '/sləʊ/',  6),
(581, 30, 'fun', '/fʌn/', '/fʌn/',  7),
(582, 30, 'same', '/seɪm/', '/seɪm/',  8),
(583, 30, 'true', '/truː/', '/truː/',  9),
(584, 30, 'always', '/ˈɔːlweɪz/', '/ˈɔːlweɪz/', 10),
(585, 30, 'often', '/ˈɔːfn/', '/ˈɒfn/', 11),
(586, 30, 'sometimes', '/ˈsʌmtaɪmz/', '/ˈsʌmtaɪmz/', 12),
(587, 30, 'never', '/ˈnevər/', '/ˈnevə/', 13),
(588, 30, 'usually', '/ˈjuːʒuəli/', '/ˈjuːʒuəli/', 14),
(589, 30, 'together', '/təˈɡeðər/', '/təˈɡeðə/', 15);

INSERT INTO wp_word_sense (id, word_id, pos, meaning, sort) VALUES
(575, 575, 'adj.', '好的；优秀的', 1),
(576, 576, 'adj.', '好的；令人愉快的', 1),
(577, 577, 'adj.', '大的；重要的', 1),
(578, 578, 'adj.', '小的；少的', 1),
(579, 579, 'adj.', '快的；迅速的', 1),
(580, 580, 'adj.', '慢的；迟缓的', 1),
(581, 581, 'adj.', '有趣的；好玩的', 1),
(582, 582, 'adj.', '相同的；一样的', 1),
(583, 583, 'adj.', '真实的；正确的', 1),
(584, 584, 'adv.', '总是；一直', 1),
(585, 585, 'adv.', '经常；常常', 1),
(586, 586, 'adv.', '有时；偶尔', 1),
(587, 587, 'adv.', '从不；绝不', 1),
(588, 588, 'adv.', '通常；一般', 1),
(589, 589, 'adv.', '一起；共同', 1);

INSERT INTO wp_word_example (id, word_id, sentence, translation, sort) VALUES
(575, 575, 'She is a good student.', '她是个好学生。', 1),
(576, 576, 'What a nice day!', '多好的天气啊！', 1),
(577, 577, 'We live in a big house.', '我们住在一栋大房子里。', 1),
(578, 578, 'My cat has small ears.', '我的猫有小耳朵。', 1),
(579, 579, 'He runs very fast.', '他跑得很快。', 1),
(580, 580, 'The turtle is very slow.', '乌龟非常慢。', 1),
(581, 581, 'The game is so fun!', '这个游戏太好玩了！', 1),
(582, 582, 'We are in the same class.', '我们在同一个班。', 1),
(583, 583, 'Is that true?', '那是真的吗？', 1),
(584, 584, 'She always comes to school early.', '她总是早到学校。', 1),
(585, 585, 'I often go to the park.', '我经常去公园。', 1),
(586, 586, 'Sometimes I feel tired.', '有时我感到很累。', 1),
(587, 587, 'I never eat junk food.', '我从不吃垃圾食品。', 1),
(588, 588, 'I usually get up at seven.', '我通常七点起床。', 1),
(589, 589, 'Let us play together.', '让我们一起玩吧。', 1);


-- ------------------------------------------------------------
-- 第31章：基础动词
-- ------------------------------------------------------------
INSERT INTO wp_word (id, chapter_id, word, phonetic_us, phonetic_uk, sort) VALUES
(590, 31, 'like', '/laɪk/', '/laɪk/',  1),
(591, 31, 'want', '/wɒnt/', '/wɒnt/',  2),
(592, 31, 'love', '/lʌv/', '/lʌv/',  3),
(593, 31, 'eat', '/iːt/', '/iːt/',  4),
(594, 31, 'drink', '/drɪŋk/', '/drɪŋk/',  5),
(595, 31, 'wear', '/wer/', '/weə/',  6),
(596, 31, 'come', '/kʌm/', '/kʌm/',  7),
(597, 31, 'see', '/siː/', '/siː/',  8),
(598, 31, 'look', '/lʊk/', '/lʊk/',  9),
(599, 31, 'say', '/seɪ/', '/seɪ/', 10),
(600, 31, 'tell', '/tel/', '/tel/', 11),
(601, 31, 'think', '/θɪŋk/', '/θɪŋk/', 12),
(602, 31, 'try', '/traɪ/', '/traɪ/', 13),
(603, 31, 'use', '/juːz/', '/juːz/', 14),
(604, 31, 'visit', '/ˈvɪzɪt/', '/ˈvɪzɪt/', 15),
(605, 31, 'live', '/lɪv/', '/lɪv/', 16),
(606, 31, 'open', '/ˈoʊpən/', '/ˈəʊpən/', 17),
(607, 31, 'close', '/kloʊz/', '/kləʊz/', 18),
(608, 31, 'sit', '/sɪt/', '/sɪt/', 19),
(609, 31, 'start', '/stɑːrt/', '/stɑːt/', 20);

INSERT INTO wp_word_sense (id, word_id, pos, meaning, sort) VALUES
(590, 590, 'v.', '喜欢；像', 1),
(591, 591, 'v.', '想要；希望', 1),
(592, 592, 'v.', '爱；热爱', 1),
(593, 593, 'v.', '吃；进食', 1),
(594, 594, 'v.', '喝；饮用', 1),
(595, 595, 'v.', '穿；戴', 1),
(596, 596, 'v.', '来；过来', 1),
(597, 597, 'v.', '看见；明白', 1),
(598, 598, 'v.', '看；瞧', 1),
(599, 599, 'v.', '说；讲', 1),
(600, 600, 'v.', '告诉；讲述', 1),
(601, 601, 'v.', '想；认为', 1),
(602, 602, 'v.', '尝试；努力', 1),
(603, 603, 'v.', '使用；利用', 1),
(604, 604, 'v.', '参观；拜访', 1),
(605, 605, 'v.', '居住；生活', 1),
(606, 606, 'v.', '打开；开放', 1),
(607, 607, 'v.', '关闭；关上', 1),
(608, 608, 'v.', '坐；坐下', 1),
(609, 609, 'v.', '开始；出发', 1);

INSERT INTO wp_word_example (id, word_id, sentence, translation, sort) VALUES
(590, 590, 'I like playing football.', '我喜欢踢足球。', 1),
(591, 591, 'I want a new bike.', '我想要一辆新自行车。', 1),
(592, 592, 'I love my family.', '我爱我的家人。', 1),
(593, 593, 'We eat three meals a day.', '我们每天吃三顿饭。', 1),
(594, 594, 'Drink more water every day.', '每天多喝水。', 1),
(595, 595, 'She wears a red dress today.', '她今天穿一条红色连衣裙。', 1),
(596, 596, 'Come here, please.', '请过来。', 1),
(597, 597, 'I can see a bird in the tree.', '我能看见树上有一只鸟。', 1),
(598, 598, 'Look at the blackboard.', '看黑板。', 1),
(599, 599, 'What did she say?', '她说了什么？', 1),
(600, 600, 'Tell me your name.', '告诉我你的名字。', 1),
(601, 601, 'I think she is right.', '我认为她是对的。', 1),
(602, 602, 'Try your best!', '尽力尝试！', 1),
(603, 603, 'Can I use your pen?', '我可以用你的钢笔吗？', 1),
(604, 604, 'We visit the museum every year.', '我们每年都参观博物馆。', 1),
(605, 605, 'I live near the school.', '我住在学校附近。', 1),
(606, 606, 'Open the window, please.', '请打开窗户。', 1),
(607, 607, 'Please close the door.', '请关上门。', 1),
(608, 608, 'Sit down and listen carefully.', '坐下来，认真听。', 1),
(609, 609, 'Class starts at eight.', '课八点开始。', 1);

-- ------------------------------------------------------------
-- 第32章：疑问词
-- ------------------------------------------------------------
INSERT INTO wp_word (id, chapter_id, word, phonetic_us, phonetic_uk, sort) VALUES
(610, 32, 'who', '/huː/', '/huː/',  1),
(611, 32, 'what', '/wɒt/', '/wɒt/',  2),
(612, 32, 'when', '/wen/', '/wen/',  3),
(613, 32, 'why', '/waɪ/', '/waɪ/',  4),
(614, 32, 'how', '/haʊ/', '/haʊ/',  5),
(615, 32, 'which', '/wɪtʃ/', '/wɪtʃ/',  6),
(616, 32, 'whose', '/huːz/', '/huːz/',  7);

INSERT INTO wp_word_sense (id, word_id, pos, meaning, sort) VALUES
(610, 610, 'pron.', '谁', 1),
(611, 611, 'pron.', '什么；多么', 1),
(612, 612, 'adv.', '什么时候', 1),
(613, 613, 'adv.', '为什么', 1),
(614, 614, 'adv.', '怎样；如何', 1),
(615, 615, 'pron.', '哪个；哪些', 1),
(616, 616, 'pron.', '谁的', 1);

INSERT INTO wp_word_example (id, word_id, sentence, translation, sort) VALUES
(610, 610, 'Who is your teacher?', '谁是你的老师？', 1),
(611, 611, 'What is your name?', '你叫什么名字？', 1),
(612, 612, 'When is your birthday?', '你的生日是什么时候？', 1),
(613, 613, 'Why are you late?', '你为什么迟到了？', 1),
(614, 614, 'How do you go to school?', '你怎么去学校？', 1),
(615, 615, 'Which bag is yours?', '哪个书包是你的？', 1),
(616, 616, 'Whose pen is this?', '这是谁的钢笔？', 1);


-- ------------------------------------------------------------
-- 第33章：核心动词（续）
-- ------------------------------------------------------------
INSERT INTO wp_word (id, chapter_id, word, phonetic_us, phonetic_uk, sort) VALUES
(617, 33, 'have', '/hæv/', '/hæv/',  1),
(618, 33, 'get', '/ɡet/', '/ɡet/',  2),
(619, 33, 'give', '/ɡɪv/', '/ɡɪv/',  3),
(620, 33, 'take', '/teɪk/', '/teɪk/',  4),
(621, 33, 'put', '/pʊt/', '/pʊt/',  5),
(622, 33, 'find', '/faɪnd/', '/faɪnd/',  6),
(623, 33, 'hear', '/hɪr/', '/hɪə/',  7),
(624, 33, 'learn', '/lɜːrn/', '/lɜːn/',  8),
(625, 33, 'sleep', '/sliːp/', '/sliːp/',  9),
(626, 33, 'speak', '/spiːk/', '/spiːk/', 10),
(627, 33, 'write', '/raɪt/', '/raɪt/', 11),
(628, 33, 'need', '/niːd/', '/niːd/', 12),
(629, 33, 'know', '/noʊ/', '/nəʊ/', 13),
(630, 33, 'feel', '/fiːl/', '/fiːl/', 14),
(631, 33, 'stand', '/stænd/', '/stænd/', 15),
(632, 33, 'meet', '/miːt/', '/miːt/', 16),
(633, 33, 'bring', '/brɪŋ/', '/brɪŋ/', 17),
(634, 33, 'talk', '/tɔːk/', '/tɔːk/', 18),
(635, 33, 'keep', '/kiːp/', '/kiːp/', 19);

INSERT INTO wp_word_sense (id, word_id, pos, meaning, sort) VALUES
(617, 617, 'v.', '有；拥有', 1),
(618, 618, 'v.', '得到；获得', 1),
(619, 619, 'v.', '给；赠送', 1),
(620, 620, 'v.', '拿；取；带', 1),
(621, 621, 'v.', '放；放置', 1),
(622, 622, 'v.', '找到；发现', 1),
(623, 623, 'v.', '听见；听到', 1),
(624, 624, 'v.', '学习；学会', 1),
(625, 625, 'v.', '睡觉；入睡', 1),
(626, 626, 'v.', '说话；讲', 1),
(627, 627, 'v.', '写；写作', 1),
(628, 628, 'v.', '需要', 1),
(629, 629, 'v.', '知道；了解', 1),
(630, 630, 'v.', '感觉；觉得', 1),
(631, 631, 'v.', '站；站立', 1),
(632, 632, 'v.', '见面；遇见', 1),
(633, 633, 'v.', '带来；取来', 1),
(634, 634, 'v.', '谈话；讲话', 1),
(635, 635, 'v.', '保持；保留', 1);

INSERT INTO wp_word_example (id, word_id, sentence, translation, sort) VALUES
(617, 617, 'I have two cats at home.', '我家有两只猫。', 1),
(618, 618, 'I get a gift from Mum.', '我从妈妈那里得到一份礼物。', 1),
(619, 619, 'Give me your hand, please.', '请把你的手给我。', 1),
(620, 620, 'Take an umbrella. It may rain.', '带把伞，可能会下雨。', 1),
(621, 621, 'Put your bag on the desk.', '把书包放在桌上。', 1),
(622, 622, 'I can not find my ruler.', '我找不到我的尺子了。', 1),
(623, 623, 'I can hear music outside.', '我能听到外面的音乐。', 1),
(624, 624, 'I learn English every day.', '我每天学英语。', 1),
(625, 625, 'Go to sleep early tonight.', '今晚早点睡觉。', 1),
(626, 626, 'Can you speak English?', '你会说英语吗？', 1),
(627, 627, 'Write your name on the paper.', '在纸上写下你的名字。', 1),
(628, 628, 'Do you need any help?', '你需要帮忙吗？', 1),
(629, 629, 'Do you know the answer?', '你知道答案吗？', 1),
(630, 630, 'How do you feel today?', '你今天感觉怎么样？', 1),
(631, 631, 'Please stand up and read.', '请起立朗读。', 1),
(632, 632, 'Nice to meet you.', '很高兴见到你。', 1),
(633, 633, 'Bring a notebook to class.', '上课时带一个笔记本。', 1),
(634, 634, 'Let us talk about your holiday.', '我们聊聊你的假期吧。', 1),
(635, 635, 'Keep your desk clean.', '保持桌面整洁。', 1);


-- ------------------------------------------------------------
-- 第34章：基础形容词（续）
-- ------------------------------------------------------------
INSERT INTO wp_word (id, chapter_id, word, phonetic_us, phonetic_uk, sort) VALUES
(636, 34, 'heavy', '/ˈhevi/', '/ˈhevi/',  1),
(637, 34, 'hard', '/hɑːrd/', '/hɑːd/',  2),
(638, 34, 'soft', '/sɔːft/', '/sɒft/',  3),
(639, 34, 'dirty', '/ˈdɜːrti/', '/ˈdɜːti/',  4),
(640, 34, 'wrong', '/rɒŋ/', '/rɒŋ/',  5),
(641, 34, 'full', '/fʊl/', '/fʊl/',  6),
(642, 34, 'different', '/ˈdɪfrənt/', '/ˈdɪfrənt/',  7),
(643, 34, 'other', '/ˈʌðər/', '/ˈʌðə/',  8),
(644, 34, 'special', '/ˈspeʃl/', '/ˈspeʃl/',  9),
(645, 34, 'free', '/friː/', '/friː/', 10),
(646, 34, 'ready', '/ˈredi/', '/ˈredi/', 11),
(647, 34, 'wet', '/wet/', '/wet/', 12),
(648, 34, 'dry', '/draɪ/', '/draɪ/', 13),
(649, 34, 'dark', '/dɑːrk/', '/dɑːk/', 14);

INSERT INTO wp_word_sense (id, word_id, pos, meaning, sort) VALUES
(636, 636, 'adj.', '重的；沉的', 1),
(637, 637, 'adj.', '硬的；困难的', 1),
(638, 638, 'adj.', '软的；轻柔的', 1),
(639, 639, 'adj.', '脏的；肮脏的', 1),
(640, 640, 'adj.', '错误的；不对的', 1),
(641, 641, 'adj.', '满的；饱的', 1),
(642, 642, 'adj.', '不同的；有区别的', 1),
(643, 643, 'adj.', '其他的；另外的', 1),
(644, 644, 'adj.', '特别的；特殊的', 1),
(645, 645, 'adj.', '自由的；空闲的', 1),
(646, 646, 'adj.', '准备好的；就绪的', 1),
(647, 647, 'adj.', '湿的；潮湿的', 1),
(648, 648, 'adj.', '干的；干燥的', 1),
(649, 649, 'adj.', '黑暗的；深色的', 1);

INSERT INTO wp_word_example (id, word_id, sentence, translation, sort) VALUES
(636, 636, 'This box is very heavy.', '这个箱子很重。', 1),
(637, 637, 'The rock is hard.', '这块石头很硬。', 1),
(638, 638, 'The pillow is very soft.', '这个枕头很软。', 1),
(639, 639, 'Wash your dirty hands.', '洗洗你那双脏手。', 1),
(640, 640, 'Sorry, I got the wrong answer.', '对不起，我答错了。', 1),
(641, 641, 'The glass is full of water.', '杯子里装满了水。', 1),
(642, 642, 'We have different hobbies.', '我们有不同的爱好。', 1),
(643, 643, 'Please help the other students.', '请帮助其他同学。', 1),
(644, 644, 'Today is a special day.', '今天是特别的一天。', 1),
(645, 645, 'Are you free this afternoon?', '你今天下午有空吗？', 1),
(646, 646, 'Are you ready? Let us go!', '准备好了吗？我们走吧！', 1),
(647, 647, 'My jacket is wet from the rain.', '我的夹克被雨淋湿了。', 1),
(648, 648, 'Use a towel to dry your hands.', '用毛巾擦干你的手。', 1),
(649, 649, 'It is dark outside at night.', '晚上外面很黑。', 1);


-- ------------------------------------------------------------
-- 第35章：时间词（续）
-- ------------------------------------------------------------
INSERT INTO wp_word (id, chapter_id, word, phonetic_us, phonetic_uk, sort) VALUES
(650, 35, 'yesterday', '/ˈjestərdeɪ/', '/ˈjestədeɪ/',  1),
(651, 35, 'day', '/deɪ/', '/deɪ/',  2),
(652, 35, 'week', '/wiːk/', '/wiːk/',  3),
(653, 35, 'month', '/mʌnθ/', '/mʌnθ/',  4),
(654, 35, 'now', '/naʊ/', '/naʊ/',  5),
(655, 35, 'then', '/ðen/', '/ðen/',  6),
(656, 35, 'before', '/bɪˈfɔːr/', '/bɪˈfɔː/',  7),
(657, 35, 'after', '/ˈæftər/', '/ˈɑːftə/',  8),
(658, 35, 'still', '/stɪl/', '/stɪl/',  9),
(659, 35, 'soon', '/suːn/', '/suːn/', 10),
(660, 35, 'again', '/əˈɡen/', '/əˈɡeɪn/', 11),
(661, 35, 'last', '/læst/', '/lɑːst/', 12),
(662, 35, 'next', '/nekst/', '/nekst/', 13);

INSERT INTO wp_word_sense (id, word_id, pos, meaning, sort) VALUES
(650, 650, 'n.', '昨天', 1),
(651, 651, 'n.', '一天；白天', 1),
(652, 652, 'n.', '星期；周', 1),
(653, 653, 'n.', '月；月份', 1),
(654, 654, 'adv.', '现在；此刻', 1),
(655, 655, 'adv.', '那时；然后', 1),
(656, 656, 'adv.', '在……之前', 1),
(657, 657, 'adv.', '在……之后', 1),
(658, 658, 'adv.', '仍然；还是', 1),
(659, 659, 'adv.', '不久；很快', 1),
(660, 660, 'adv.', '再次；又', 1),
(661, 661, 'adj.', '上一个的；最后的', 1),
(662, 662, 'adj.', '下一个的；紧接的', 1);

INSERT INTO wp_word_example (id, word_id, sentence, translation, sort) VALUES
(650, 650, 'I went to the park yesterday.', '我昨天去了公园。', 1),
(651, 651, 'It is a fine day today.', '今天天气很好。', 1),
(652, 652, 'There are seven days in a week.', '一周有七天。', 1),
(653, 653, 'My birthday is next month.', '我的生日在下个月。', 1),
(654, 654, 'What are you doing now?', '你现在在做什么？', 1),
(655, 655, 'Wash your hands and then eat.', '先洗手，然后再吃饭。', 1),
(656, 656, 'Brush your teeth before bed.', '睡觉前刷牙。', 1),
(657, 657, 'We play after school.', '放学后我们去玩。', 1),
(658, 658, 'It is still raining outside.', '外面还在下雨。', 1),
(659, 659, 'Dinner will be ready soon.', '晚饭很快就好了。', 1),
(660, 660, 'Please say that again.', '请再说一遍。', 1),
(661, 661, 'I saw him last week.', '我上周见过他。', 1),
(662, 662, 'See you next Monday!', '下周一见！', 1);


-- ------------------------------------------------------------
-- 第36章：日用品
-- ------------------------------------------------------------
INSERT INTO wp_word (id, chapter_id, word, phonetic_us, phonetic_uk, sort) VALUES
(663, 36, 'toothbrush', '/ˈtuːθbrʌʃ/', '/ˈtuːθbrʌʃ/',  1),
(664, 36, 'toothpaste', '/ˈtuːθpeɪst/', '/ˈtuːθpeɪst/',  2),
(665, 36, 'towel', '/ˈtaʊəl/', '/ˈtaʊəl/',  3),
(666, 36, 'soap', '/soʊp/', '/səʊp/',  4),
(667, 36, 'bowl', '/boʊl/', '/bəʊl/',  5),
(668, 36, 'chopsticks', '/ˈtʃɒpstɪks/', '/ˈtʃɒpstɪks/',  6),
(669, 36, 'spoon', '/spuːn/', '/spuːn/',  7),
(670, 36, 'fork', '/fɔːrk/', '/fɔːk/',  8),
(671, 36, 'cup', '/kʌp/', '/kʌp/',  9),
(672, 36, 'umbrella', '/ʌmˈbrelə/', '/ʌmˈbrelə/', 10),
(673, 36, 'key', '/kiː/', '/kiː/', 11),
(674, 36, 'comb', '/koʊm/', '/kəʊm/', 12),
(675, 36, 'bottle', '/ˈbɒtl/', '/ˈbɒtl/', 13),
(676, 36, 'plate', '/pleɪt/', '/pleɪt/', 14);

INSERT INTO wp_word_sense (id, word_id, pos, meaning, sort) VALUES
(663, 663, 'n.', '牙刷', 1),
(664, 664, 'n.', '牙膏', 1),
(665, 665, 'n.', '毛巾；浴巾', 1),
(666, 666, 'n.', '肥皂', 1),
(667, 667, 'n.', '碗', 1),
(668, 668, 'n.', '筷子', 1),
(669, 669, 'n.', '勺子；汤匙', 1),
(670, 670, 'n.', '叉子', 1),
(671, 671, 'n.', '杯子；茶杯', 1),
(672, 672, 'n.', '雨伞', 1),
(673, 673, 'n.', '钥匙', 1),
(674, 674, 'n.', '梳子', 1),
(675, 675, 'n.', '瓶子；瓶', 1),
(676, 676, 'n.', '盘子；碟子', 1);

INSERT INTO wp_word_example (id, word_id, sentence, translation, sort) VALUES
(663, 663, 'Brush your teeth with a toothbrush.', '用牙刷刷牙。', 1),
(664, 664, 'Use toothpaste to clean your teeth.', '用牙膏清洁牙齿。', 1),
(665, 665, 'Dry your face with a towel.', '用毛巾擦干脸。', 1),
(666, 666, 'Wash hands with soap and water.', '用肥皂和水洗手。', 1),
(667, 667, 'There is rice in the bowl.', '碗里有米饭。', 1),
(668, 668, 'Chinese people eat with chopsticks.', '中国人用筷子吃饭。', 1),
(669, 669, 'Use a spoon to eat the soup.', '用勺子喝汤。', 1),
(670, 670, 'She eats salad with a fork.', '她用叉子吃沙拉。', 1),
(671, 671, 'I drink tea from this cup.', '我用这个杯子喝茶。', 1),
(672, 672, 'Take your umbrella. It may rain.', '带上雨伞，可能会下雨。', 1),
(673, 673, 'I can not find the door key.', '我找不到门钥匙。', 1),
(674, 674, 'Use a comb to tidy your hair.', '用梳子整理头发。', 1),
(675, 675, 'Fill the bottle with water.', '把瓶子装满水。', 1),
(676, 676, 'Put the fruit on the plate.', '把水果放在盘子里。', 1);


-- ------------------------------------------------------------
-- 第37章：更多食物
-- ------------------------------------------------------------
INSERT INTO wp_word (id, chapter_id, word, phonetic_us, phonetic_uk, sort) VALUES
(677, 37, 'candy', '/ˈkændi/', '/ˈkændi/',  1),
(678, 37, 'ice cream', '/ˈaɪs kriːm/', '/ˈaɪs kriːm/',  2),
(679, 37, 'chocolate', '/ˈtʃɒklət/', '/ˈtʃɒklət/',  3),
(680, 37, 'cookie', '/ˈkʊki/', '/ˈkʊki/',  4),
(681, 37, 'pizza', '/ˈpiːtsə/', '/ˈpiːtsə/',  5),
(682, 37, 'sandwich', '/ˈsænwɪtʃ/', '/ˈsænwɪdʒ/',  6),
(683, 37, 'dumpling', '/ˈdʌmplɪŋ/', '/ˈdʌmplɪŋ/',  7),
(684, 37, 'salad', '/ˈsæləd/', '/ˈsæləd/',  8),
(686, 37, 'sugar', '/ˈʃʊɡər/', '/ˈʃʊɡə/', 10);

INSERT INTO wp_word_sense (id, word_id, pos, meaning, sort) VALUES
(677, 677, 'n.', '糖果', 1),
(678, 678, 'n.', '冰激凌', 1),
(679, 679, 'n.', '巧克力', 1),
(680, 680, 'n.', '饼干；曲奇', 1),
(681, 681, 'n.', '比萨饼', 1),
(682, 682, 'n.', '三明治', 1),
(683, 683, 'n.', '饺子；汤圆', 1),
(684, 684, 'n.', '沙拉', 1),
(686, 686, 'n.', '糖；糖分', 1);

INSERT INTO wp_word_example (id, word_id, sentence, translation, sort) VALUES
(677, 677, 'Do not eat too much candy.', '不要吃太多糖果。', 1),
(678, 678, 'I like chocolate ice cream.', '我喜欢巧克力冰激凌。', 1),
(679, 679, 'She gives me a chocolate bar.', '她给了我一块巧克力。', 1),
(680, 680, 'Mum bakes cookies for us.', '妈妈为我们烤饼干。', 1),
(681, 681, 'We eat pizza on Fridays.', '我们周五吃比萨。', 1),
(682, 682, 'I have a sandwich for lunch.', '我午餐吃三明治。', 1),
(683, 683, 'We make dumplings for the New Year.', '我们在新年包饺子。', 1),
(684, 684, 'Eat more salad to stay healthy.', '多吃沙拉保持健康。', 1),
(686, 686, 'Do not add too much sugar.', '不要加太多糖。', 1);


-- ------------------------------------------------------------
-- 第38章：更多动物
-- ------------------------------------------------------------
INSERT INTO wp_word (id, chapter_id, word, phonetic_us, phonetic_uk, sort) VALUES
(687, 38, 'bee', '/biː/', '/biː/',  1),
(688, 38, 'butterfly', '/ˈbʌtərflaɪ/', '/ˈbʌtəflaɪ/',  2),
(689, 38, 'ant', '/ænt/', '/ænt/',  3),
(690, 38, 'snake', '/sneɪk/', '/sneɪk/',  4),
(691, 38, 'penguin', '/ˈpeŋɡwɪn/', '/ˈpeŋɡwɪn/',  5),
(692, 38, 'goldfish', '/ˈɡoʊldfɪʃ/', '/ˈɡəʊldfɪʃ/',  6),
(693, 38, 'parrot', '/ˈpærət/', '/ˈpærət/',  7),
(696, 38, 'squirrel', '/ˈskwɪrəl/', '/ˈskwɪrəl/', 10),
(697, 38, 'turtle', '/ˈtɜːrtl/', '/ˈtɜːtl/', 11),
(698, 38, 'fox', '/fɒks/', '/fɒks/', 12),
(699, 38, 'deer', '/dɪr/', '/dɪə/', 13),
(700, 38, 'owl', '/aʊl/', '/aʊl/', 14);

INSERT INTO wp_word_sense (id, word_id, pos, meaning, sort) VALUES
(687, 687, 'n.', '蜜蜂', 1),
(688, 688, 'n.', '蝴蝶', 1),
(689, 689, 'n.', '蚂蚁', 1),
(690, 690, 'n.', '蛇', 1),
(691, 691, 'n.', '企鹅', 1),
(692, 692, 'n.', '金鱼', 1),
(693, 693, 'n.', '鹦鹉', 1),
(696, 696, 'n.', '松鼠', 1),
(697, 697, 'n.', '海龟；龟', 1),
(698, 698, 'n.', '狐狸', 1),
(699, 699, 'n.', '鹿', 1),
(700, 700, 'n.', '猫头鹰', 1);

INSERT INTO wp_word_example (id, word_id, sentence, translation, sort) VALUES
(687, 687, 'A bee is flying near the flower.', '一只蜜蜂在花朵附近飞翔。', 1),
(688, 688, 'A butterfly landed on the flower.', '一只蝴蝶落在花上。', 1),
(689, 689, 'Ants work very hard every day.', '蚂蚁每天都很努力工作。', 1),
(690, 690, 'The snake is in the grass.', '蛇在草丛里。', 1),
(691, 691, 'Penguins live in cold places.', '企鹅生活在寒冷的地方。', 1),
(692, 692, 'I have two goldfish at home.', '我家有两条金鱼。', 1),
(693, 693, 'The parrot can say hello.', '这只鹦鹉会说你好。', 1),
(696, 696, 'The squirrel eats nuts in autumn.', '松鼠在秋天吃坚果。', 1),
(697, 697, 'The turtle swims slowly in the sea.', '海龟在海中慢慢游动。', 1),
(698, 698, 'The fox is clever and quick.', '狐狸聪明而敏捷。', 1),
(699, 699, 'A deer is eating grass in the forest.', '一头鹿在森林里吃草。', 1),
(700, 700, 'The owl sleeps during the day.', '猫头鹰白天睡觉。', 1);
