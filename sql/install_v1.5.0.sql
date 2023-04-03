SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for la_album
-- ----------------------------
DROP TABLE IF EXISTS `la_album`;
CREATE TABLE `la_album`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `cid` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '类目ID',
  `aid` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '管理员ID',
  `uid` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户ID',
  `type` tinyint(2) UNSIGNED NOT NULL DEFAULT 10 COMMENT '文件类型: [10=图片, 20=视频]',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '文件名称',
  `uri` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件路径',
  `ext` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '文件扩展',
  `size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '文件大小',
  `is_delete` int(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除: 0=否, 1=是',
  `create_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '更新时间',
  `delete_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_cid`(`cid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '相册管理表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for la_album_cate
-- ----------------------------
DROP TABLE IF EXISTS `la_album_cate`;
CREATE TABLE `la_album_cate`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `pid` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '父级ID',
  `type` tinyint(2) UNSIGNED NOT NULL DEFAULT 10 COMMENT '类型: [10=图片, 20=视频]',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '分类名称',
  `is_delete` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除: [0=否, 1=是]',
  `create_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '更新时间',
  `delete_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '相册分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for la_article
-- ----------------------------
DROP TABLE IF EXISTS `la_article`;
CREATE TABLE `la_article`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cid` int(10) UNSIGNED NOT NULL COMMENT '分类',
  `title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '标题',
  `intro` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '简介',
  `summary` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '摘要',
  `image` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '封面',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '内容',
  `author` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '作者',
  `visit` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '浏览',
  `sort` int(10) UNSIGNED NOT NULL DEFAULT 50 COMMENT '排序',
  `is_show` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否显示: 0=否, 1=是',
  `is_delete` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除: 0=否, 1=是',
  `create_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '更新时间',
  `delete_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `cid_idx`(`cid`) USING BTREE COMMENT '分类索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文章资讯表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for la_article_category
-- ----------------------------
DROP TABLE IF EXISTS `la_article_category`;
CREATE TABLE `la_article_category`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '名称',
  `sort` smallint(5) UNSIGNED NOT NULL DEFAULT 50 COMMENT '排序',
  `is_show` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '是否显示: 0=否, 1=是',
  `is_delete` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除: 0=否, 1=是',
  `create_time` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '更新时间',
  `delete_time` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文章分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for la_article_collect
-- ----------------------------
DROP TABLE IF EXISTS `la_article_collect`;
CREATE TABLE `la_article_collect`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户ID',
  `article_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '文章ID',
  `is_delete` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除',
  `create_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '更新时间',
  `delete_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文章收藏表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for la_crontab
-- ----------------------------
DROP TABLE IF EXISTS `la_crontab`;
CREATE TABLE `la_crontab`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '任务名称',
  `types` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '任务分组',
  `command` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '任务命令',
  `rules` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '任务贵州',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '备注信息',
  `error` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '错误信息',
  `status` tinyint(1) UNSIGNED NOT NULL DEFAULT 2 COMMENT '执行状态: 1=正在运行, 2=任务停止, 3=发生错误',
  `strategy` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '执行策略: 1=立即执行, 2=执行一次, 3=放弃执行',
  `concurrent` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '并发执行: 0=否, 1=是',
  `is_delete` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除: 0=否, 1=是',
  `start_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '开始时间',
  `end_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '结束时间',
  `task_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '任务耗时',
  `create_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '更新时间',
  `delete_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '计划任务表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for la_decorate_page
-- ----------------------------
DROP TABLE IF EXISTS `la_decorate_page`;
CREATE TABLE `la_decorate_page`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `page_type` tinyint(2) UNSIGNED NOT NULL DEFAULT 10 COMMENT '页面类型',
  `page_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '页面名称',
  `page_data` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '页面数据',
  `create_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` int(10) UNSIGNED NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '页面装修表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for la_decorate_tabbar
-- ----------------------------
DROP TABLE IF EXISTS `la_decorate_tabbar`;
CREATE TABLE `la_decorate_tabbar`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '导航名称',
  `selected` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '未选图标',
  `unselected` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '已选图标',
  `link` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '链接地址',
  `create_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '底部装修表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for la_dev_pay_config
-- ----------------------------
DROP TABLE IF EXISTS `la_dev_pay_config`;
CREATE TABLE `la_dev_pay_config`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '模版名称',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '渠道图标',
  `way` tinyint(1) NOT NULL COMMENT '支付方式: [1=余额支付, 2=微信支付, 3=支付宝支付]',
  `sort` int(5) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序编号',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '备注信息',
  `params` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '配置参数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '支付配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for la_dev_pay_way
-- ----------------------------
DROP TABLE IF EXISTS `la_dev_pay_way`;
CREATE TABLE `la_dev_pay_way`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `pay_config_id` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '支付配置ID',
  `scene` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '场景编码: [1=微信小程序, 2=微信公众号, 3=H5, 4=PC, 5=APP]',
  `is_default` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '默认支付: [0=否的, 1=是的]',
  `status` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '方式状态: [0=关闭, 1=开启]',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '支付方式表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for la_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `la_dict_data`;
CREATE TABLE `la_dict_data`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '类型',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '键名',
  `value` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '数值',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '备注',
  `sort` smallint(5) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序',
  `status` tinyint(1) NOT NULL COMMENT '状态: 0=停用, 1=正常',
  `is_delete` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除: 0=否, 1=是',
  `create_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '更新时间',
  `delete_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for la_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `la_dict_type`;
CREATE TABLE `la_dict_type`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dict_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '字典类型',
  `dict_remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '字典备注',
  `dict_status` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '字典状态: 0=停用, 1=正常',
  `is_delete` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除: 0=否, 1=是',
  `create_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '更新时间',
  `delete_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for la_gen_table
-- ----------------------------
DROP TABLE IF EXISTS `la_gen_table`;
CREATE TABLE `la_gen_table`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `table_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '表名称',
  `table_comment` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '表描述',
  `sub_table_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '关联表名称',
  `sub_table_fk` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '关联表外键',
  `sub_table_fr` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '关联表主键',
  `author_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '作者的名称',
  `entity_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '实体的名称',
  `module_name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '生成模块名',
  `function_name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '生成功能名',
  `tree_primary` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '树主键字段',
  `tree_parent` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '树父级字段',
  `tree_name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '树显示字段',
  `gen_tpl` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'crud' COMMENT '生成模板方式: [crud=单表, tree=树表]',
  `gen_type` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '生成代码方式: [0=zip压缩包, 1=自定义路径]',
  `gen_path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '/' COMMENT '生成代码路径: [不填默认项目路径]',
  `menu_status` tinyint(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '菜单状态: [1=自动构建, 2=手动添加]',
  `menu_pid` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '菜单父级',
  `menu_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '菜单名称',
  `remarks` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '备注信息',
  `create_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '代码生成业务表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for la_gen_table_column
-- ----------------------------
DROP TABLE IF EXISTS `la_gen_table_column`;
CREATE TABLE `la_gen_table_column`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '列主键',
  `table_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '表外键',
  `column_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '列名称',
  `column_comment` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '列描述',
  `column_length` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '列长度',
  `column_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '列类型 ',
  `java_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'JAVA类型',
  `java_field` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'JAVA字段',
  `is_pk` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否主键: [1=是, 0=否]',
  `is_increment` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否自增: [1=是, 0=否]',
  `is_required` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否必填: [1=是, 0=否]',
  `is_insert` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否插入字段: [1=是, 0=否]',
  `is_edit` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否编辑字段: [1=是, 0=否]',
  `is_list` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否列表字段: [1=是, 0=否]',
  `is_query` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否查询字段: [1=是, 0=否]',
  `query_type` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'EQ' COMMENT '查询方式: [等于、不等于、大于、小于、范围]',
  `html_type` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '显示类型: [文本框、文本域、下拉框、复选框、单选框、日期控件]',
  `dict_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '字典类型',
  `sort` smallint(5) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序编号',
  `create_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '代码生成字段表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for la_hot_search
-- ----------------------------
DROP TABLE IF EXISTS `la_hot_search`;
CREATE TABLE `la_hot_search`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '关键词',
  `sort` smallint(5) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '热门搜索配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for la_log_money
-- ----------------------------
DROP TABLE IF EXISTS `la_log_money`;
CREATE TABLE `la_log_money`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sn` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '流水号',
  `user_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户ID',
  `source_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '关联ID',
  `source_sn` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '关联单号',
  `change_type` smallint(5) UNSIGNED NOT NULL COMMENT '变动类型',
  `change_amount` decimal(10, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '变动的数量',
  `left_amount` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '变动后数量',
  `action` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '变动的动作: 1=增加, 2=减少',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '备注信息',
  `extra` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '预留字段',
  `create_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '更新时间',
  `delete_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '账户变动日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for la_notice_record
-- ----------------------------
DROP TABLE IF EXISTS `la_notice_record`;
CREATE TABLE `la_notice_record`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `scene` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '通知场景',
  `user_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '接收用户',
  `account` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '接收账号',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '通知标题',
  `code` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '验证编码',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '通知内容',
  `error` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '失败原因',
  `sender` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '发送类型: [1=系统, 2=短信, 3=公众号, 4=小程序]',
  `receiver` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '接收对象: [1=用户, 2=平台]',
  `status` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '通知状态: [0=等待, 1=成功, 2=失败]',
  `is_read` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '已读状态: [0=未读, 1=已读]',
  `is_captcha` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是验证码: [0=否的, 1=是的]',
  `is_delete` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除: [0=否的, 1=是的]',
  `expire_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '失效时间',
  `create_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '更新时间',
  `delete_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '消息通知记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for la_notice_setting
-- ----------------------------
DROP TABLE IF EXISTS `la_notice_setting`;
CREATE TABLE `la_notice_setting`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `scene` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '场景编号',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '场景名称',
  `remarks` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '场景描述',
  `recipient` tinyint(1) NOT NULL DEFAULT 1 COMMENT '接收人员: [1=用户, 2=平台]',
  `type` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '通知类型: [1=业务, 2=验证]',
  `system_notice` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '系统的通知设置',
  `sms_notice` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '短信的通知设置',
  `oa_notice` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '公众号通知设置',
  `mnp_notice` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '小程序通知设置',
  `is_delete` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除',
  `create_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '更新时间',
  `delete_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '消息通知设置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for la_official_reply
-- ----------------------------
DROP TABLE IF EXISTS `la_official_reply`;
CREATE TABLE `la_official_reply`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '规则名',
  `keyword` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '关键词',
  `reply_type` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '回复类型: [1=关注回复 2=关键字回复, 3=默认回复]',
  `matching_type` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '匹配方式: [1=全匹配, 2=模糊匹配]',
  `content_type` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '内容类型: [1=文本]',
  `status` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '启动状态: [1=启动, 0=关闭]',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '回复内容',
  `sort` int(11) UNSIGNED NOT NULL DEFAULT 50 COMMENT '排序编号',
  `is_delete` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除',
  `create_time` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '更新时间',
  `delete_time` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '公众号的回复表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for la_recharge_order
-- ----------------------------
DROP TABLE IF EXISTS `la_recharge_order`;
CREATE TABLE `la_recharge_order`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户ID',
  `order_sn` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '订单编号',
  `pay_sn` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '支付编号',
  `pay_way` tinyint(2) UNSIGNED NOT NULL DEFAULT 2 COMMENT '支付方式: [2=微信支付, 3=支付宝支付]',
  `pay_status` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '支付状态: [0=待支付, 1=已支付]',
  `pay_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '支付时间',
  `order_amount` decimal(10, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '充值金额',
  `order_terminal` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '下单终端',
  `transaction_id` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '交易流水',
  `refund_status` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '退款状态: [0=未退款 , 1=已退款]',
  `create_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '更新时间',
  `delete_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '充值余额订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for la_refund_log
-- ----------------------------
DROP TABLE IF EXISTS `la_refund_log`;
CREATE TABLE `la_refund_log`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `sn` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编号',
  `record_id` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '退款记录ID',
  `user_id` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '关联用户ID',
  `handle_id` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '处理管理ID',
  `order_amount` decimal(10, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '总应付的金额',
  `refund_amount` decimal(10, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '本次退款金额',
  `refund_status` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '退款状态: [0=退款中, 1=退款成功, 2=退款失败]',
  `refund_msg` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '退款信息',
  `create_time` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '退款日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for la_refund_record
-- ----------------------------
DROP TABLE IF EXISTS `la_refund_record`;
CREATE TABLE `la_refund_record`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `sn` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '退款编号',
  `user_id` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '关联用户ID',
  `order_id` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '来源订单ID',
  `order_sn` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '来源单号SN',
  `order_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'order' COMMENT '订单类型: [order=商品订单, recharge=充值订单]',
  `order_amount` decimal(10, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '总应付款金额',
  `refund_amount` decimal(10, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '本次退款金额',
  `transaction_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '平台交易流水号',
  `refund_way` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '退款方式: 1=线上退款, 2=线下退款',
  `refund_type` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '退款类型: 1=后台退款',
  `refund_status` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '退款状态: 0=退款中, 1=退款成功, 2=退款失败',
  `create_time` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '退款记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for la_system_auth_admin
-- ----------------------------
DROP TABLE IF EXISTS `la_system_auth_admin`;
CREATE TABLE `la_system_auth_admin`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_ids` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '角色主键',
  `dept_ids` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '部门ID',
  `post_ids` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '岗位ID',
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户账号',
  `nickname` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户昵称',
  `password` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户密码',
  `avatar` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户头像',
  `salt` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '加密盐巴',
  `sort` smallint(5) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序编号',
  `is_multipoint` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '多端登录: 0=否, 1=是',
  `is_disable` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否禁用: 0=否, 1=是',
  `is_delete` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除: 0=否, 1=是',
  `last_login_ip` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '最后登录IP',
  `last_login_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后登录',
  `create_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '更新时间',
  `delete_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统管理成员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for la_system_auth_dept
-- ----------------------------
DROP TABLE IF EXISTS `la_system_auth_dept`;
CREATE TABLE `la_system_auth_dept`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pid` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '上级主键',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '部门名称',
  `duty` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '负责人名',
  `mobile` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '联系电话',
  `sort` smallint(5) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序编号',
  `is_stop` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否禁用: 0=否, 1=是',
  `is_delete` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除: 0=否, 1=是',
  `create_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '更新时间',
  `delete_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统部门管理表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for la_system_auth_menu
-- ----------------------------
DROP TABLE IF EXISTS `la_system_auth_menu`;
CREATE TABLE `la_system_auth_menu`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pid` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '上级菜单',
  `menu_type` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '权限类型: M=目录，C=菜单，A=按钮',
  `menu_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '菜单名称',
  `menu_icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '菜单图标',
  `menu_sort` smallint(5) UNSIGNED NOT NULL DEFAULT 0 COMMENT '菜单排序',
  `perms` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '权限标识',
  `paths` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '路由地址',
  `component` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '前端组件',
  `selected` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '选中路径',
  `params` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '路由参数',
  `is_cache` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否缓存: 0=否, 1=是',
  `is_show` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '是否显示: 0=否, 1=是',
  `is_disable` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否禁用: 0=否, 1=是',
  `create_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统菜单管理表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for la_system_auth_perm
-- ----------------------------
DROP TABLE IF EXISTS `la_system_auth_perm`;
CREATE TABLE `la_system_auth_perm`  (
  `id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '主键',
  `role_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '角色ID',
  `menu_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '菜单ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统角色菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for la_system_auth_post
-- ----------------------------
DROP TABLE IF EXISTS `la_system_auth_post`;
CREATE TABLE `la_system_auth_post`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '岗位编码',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '岗位名称',
  `remarks` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '岗位备注',
  `sort` smallint(5) UNSIGNED NOT NULL DEFAULT 0 COMMENT '岗位排序',
  `is_stop` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否停用: 0=否, 1=是',
  `is_delete` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除: 0=否, 1=是',
  `create_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '更新时间',
  `delete_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统岗位管理表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for la_system_auth_role
-- ----------------------------
DROP TABLE IF EXISTS `la_system_auth_role`;
CREATE TABLE `la_system_auth_role`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '角色名称',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '备注信息',
  `sort` smallint(5) UNSIGNED NOT NULL DEFAULT 0 COMMENT '角色排序',
  `is_disable` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否禁用: 0=否, 1=是',
  `create_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统角色管理表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for la_system_config
-- ----------------------------
DROP TABLE IF EXISTS `la_system_config`;
CREATE TABLE `la_system_config`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '类型',
  `name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '键',
  `value` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '值',
  `create_time` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统全局配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for la_system_log_login
-- ----------------------------
DROP TABLE IF EXISTS `la_system_log_login`;
CREATE TABLE `la_system_log_login`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '注解',
  `admin_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '管理员ID',
  `username` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '登录账号',
  `ip` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录地址',
  `os` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '操作系统',
  `browser` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '浏览器',
  `status` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '操作状态: 1=成功, 2=失败',
  `create_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统登录日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for la_system_log_operate
-- ----------------------------
DROP TABLE IF EXISTS `la_system_log_operate`;
CREATE TABLE `la_system_log_operate`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `admin_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '操作人ID',
  `type` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '请求类型: GET/POST/PUT',
  `title` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '操作标题',
  `ip` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '请求IP',
  `url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '请求接口',
  `method` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '请求方法',
  `args` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '请求参数',
  `error` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '错误信息',
  `status` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '执行状态: 1=成功, 2=失败',
  `start_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '开始时间',
  `end_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '结束时间',
  `task_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '执行耗时',
  `create_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统操作日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for la_user
-- ----------------------------
DROP TABLE IF EXISTS `la_user`;
CREATE TABLE `la_user`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sn` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '编号',
  `avatar` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '头像',
  `real_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '真实姓名',
  `nickname` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户昵称',
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户账号',
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户密码',
  `mobile` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户电话',
  `money` decimal(8, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '用户钱包',
  `salt` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '加密盐巴',
  `sex` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户性别: [1=男, 2=女]',
  `channel` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '注册渠道: [1=微信小程序, 2=微信公众号, 3=手机H5, 4=电脑PC, 5=苹果APP, 6=安卓APP]',
  `is_disable` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否禁用: [0=否, 1=是]',
  `is_delete` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除: [0=否, 1=是]',
  `is_new` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否是新注册用户: [1-是, 0-否]',
  `last_login_ip` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '最后登录IP',
  `last_login_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后登录时间',
  `create_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '更新时间',
  `delete_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for la_user_auth
-- ----------------------------
DROP TABLE IF EXISTS `la_user_auth`;
CREATE TABLE `la_user_auth`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户ID',
  `openid` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'Openid',
  `unionid` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'Unionid',
  `terminal` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '客户端类型: [1=微信小程序, 2=微信公众号, 3=手机H5, 4=电脑PC, 5=苹果APP, 6=安卓APP]',
  `create_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `openid`(`openid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户授权表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- INSERT
-- ----------------------------
BEGIN;
INSERT INTO `la_system_auth_dept` VALUES (1, 0, '默认部门', 'LikeAdmin', '18327647788', 10, 0, 0, 1649841995, 1660190949, 0);
INSERT INTO `la_system_auth_role` VALUES (1, '审核员', '审核数据', 0, 0, 1668679451, 1668679468);
INSERT INTO `la_system_auth_admin` VALUES (1, '0', '1', '', 'admin', 'admin', '4919832b10f1d2133c0f24a7dbe8330e', '/api/static/backend_avatar.png', 'Huku0', 0, 1, 0, 0, '127.0.0.1', 1670314940, 1642321599, 1670376604, 0);
INSERT INTO `la_crontab` VALUES (1, '有参数任务', 'default', 'myJob.handle(\"参数\")', '* * * * * ?', '', '', 2, 1, 0, 0, 1670377612, 1670377612, 0, 1669970830, 1670289651, 0);
COMMIT;

BEGIN;
INSERT INTO `la_system_config` VALUES (1, 'storage', 'default', 'local', 1660620367, 1662620927);
INSERT INTO `la_system_config` VALUES (2, 'storage', 'local', '{\"name\":\"本地存储\"}', 1660620367, 1662620927);
INSERT INTO `la_system_config` VALUES (3, 'storage', 'qiniu', '{\"name\":\"七牛云存储\",\"bucket\":\"\",\"secretKey\":\"\",\"accessKey\":\"\",\"domain\":\"\"}', 1660620367, 1660620367);
INSERT INTO `la_system_config` VALUES (4, 'storage', 'aliyun', '{\"name\":\"阿里云OSS\",\"bucket\":\"\",\"secretKey\":\"\",\"accessKey\":\"\",\"domain\":\"\"}', 1660620367, 1662620071);
INSERT INTO `la_system_config` VALUES (5, 'storage', 'qcloud', '{\"name\":\"腾讯云COS\",\"bucket\":\"\",\"secretKey\":\"\",\"accessKey\":\"\",\"domain\":\"\",\"region\":\"\"}', 1660620367, 1660620367);
INSERT INTO `la_system_config` VALUES (6, 'sms', 'default', 'aliyun', 1660620367, 1660620367);
INSERT INTO `la_system_config` VALUES (7, 'sms', 'aliyun', '{\"name\":\"阿里云短信\",\"alias\":\"aliyun\",\"sign\":\"\",\"appKey\":\"\",\"secretKey\":\"\"}', 1660620367, 1660620367);
INSERT INTO `la_system_config` VALUES (8, 'sms', 'tencent', '{\"name\":\"腾讯云短信\",\"alias\":\"tencent\",\"sign\":\"\",\"appId\":\"\",\"secretId\":\"\",\"secretKey\":\"\"}', 1660620367, 1660620367);
INSERT INTO `la_system_config` VALUES (9, 'sms', 'huawei', '{\"name\":\"华为云短信\",\"alias\":\"huawei\"}', 1660620367, 1660620367);
INSERT INTO `la_system_config` VALUES (10, 'website', 'name', 'LikeAdmin开源后台', 1660620367, 1660620367);
INSERT INTO `la_system_config` VALUES (11, 'website', 'logo', '/api/static/backend_logo.png', 1660620367, 1660620367);
INSERT INTO `la_system_config` VALUES (12, 'website', 'favicon', '/api/static/backend_favicon.ico', 1660620367, 1660620367);
INSERT INTO `la_system_config` VALUES (13, 'website', 'backdrop', '/api/static/backend_backdrop.png', 1660620367, 1660620367);
INSERT INTO `la_system_config` VALUES (14, 'website', 'copyright', '[{\"name\":\"LikeAdmin开源系统\",\"link\":\"http://www.beian.gov.cn\"}]', 1660620367, 1660620367);
INSERT INTO `la_system_config` VALUES (15, 'website', 'shopName', 'LikeAdmin开源系统', 1631255140, 1631255140);
INSERT INTO `la_system_config` VALUES (16, 'website', 'shopLogo', '/api/static/shop_logo.png', 1631255140, 1631255140);
INSERT INTO `la_system_config` VALUES (20, 'website', 'pcLogo', '/api/static/pc_logo.png', 1678963763, 1678963817);
INSERT INTO `la_system_config` VALUES (21, 'website', 'pcIco', '/api/static/pc_favicon.ico', 1678963763, 1678963817);
INSERT INTO `la_system_config` VALUES (22, 'website', 'pcTitle', 'LikeAdmin开源系统', 1678963763, 1678963817);
INSERT INTO `la_system_config` VALUES (23, 'website', 'pcDesc', '', 1678963763, 1678963817);
INSERT INTO `la_system_config` VALUES (24, 'website', 'pcKeywords', '', 1678963763, 1678963817);
INSERT INTO `la_system_config` VALUES (25, 'protocol', 'service', '{\"name\":\"服务协议\",\"content\":\"\"}', 1660620367, 1660620367);
INSERT INTO `la_system_config` VALUES (26, 'protocol', 'privacy', '{\"name\":\"隐私协议\",\"content\":\"\"}', 1660620367, 1660620367);
INSERT INTO `la_system_config` VALUES (27, 'tabbar', 'style', '{\"defaultColor\":\"#999999\",\"selectedColor\":\"#4173ff\"}', 1660620367, 1662544900);
INSERT INTO `la_system_config` VALUES (28, 'search', 'isHotSearch', '0', 1660620367, 1662546997);
INSERT INTO `la_system_config` VALUES (30, 'h5_channel', 'status', '1', 1660620367, 1660620367);
INSERT INTO `la_system_config` VALUES (31, 'h5_channel', 'close', '0', 1660620367, 1660620367);
INSERT INTO `la_system_config` VALUES (32, 'h5_channel', 'url', '', 1660620367, 1660620367);
INSERT INTO `la_system_config` VALUES (40, 'mp_channel', 'name', '', 1660620367, 1662551403);
INSERT INTO `la_system_config` VALUES (41, 'mp_channel', 'primaryId', '', 1660620367, 1662551403);
INSERT INTO `la_system_config` VALUES (42, 'mp_channel', 'appId', '', 1660620367, 1662551403);
INSERT INTO `la_system_config` VALUES (43, 'mp_channel', 'appSecret', '', 1660620367, 1662551403);
INSERT INTO `la_system_config` VALUES (44, 'mp_channel', 'qrCode', '', 1660620367, 1662551403);
INSERT INTO `la_system_config` VALUES (55, 'oa_channel', 'name', '', 1660620367, 1662551337);
INSERT INTO `la_system_config` VALUES (56, 'oa_channel', 'primaryId', ' ', 1660620367, 1662551337);
INSERT INTO `la_system_config` VALUES (57, 'oa_channel', 'qrCode', '', 1662551337, 1662551337);
INSERT INTO `la_system_config` VALUES (58, 'oa_channel', 'appId', '', 1660620367, 1662551337);
INSERT INTO `la_system_config` VALUES (59, 'oa_channel', 'appSecret', '', 1660620367, 1662551337);
INSERT INTO `la_system_config` VALUES (60, 'oa_channel', 'url', '', 1660620367, 1662551337);
INSERT INTO `la_system_config` VALUES (61, 'oa_channel', 'token', '', 1660620367, 1662551337);
INSERT INTO `la_system_config` VALUES (62, 'oa_channel', 'encodingAesKey', '', 1660620367, 1662551337);
INSERT INTO `la_system_config` VALUES (63, 'oa_channel', 'encryptionType', '1', 1660620367, 1662551337);
INSERT INTO `la_system_config` VALUES (64, 'oa_channel', 'menus', '[]', 1631255140, 1663118712);
INSERT INTO `la_system_config` VALUES (68, 'op_channel', 'appId', '', 0, 0);
INSERT INTO `la_system_config` VALUES (69, 'op_channel', 'appSecret', '', 0, 0);
INSERT INTO `la_system_config` VALUES (70, 'login', 'loginWay', '1,2', 1660620367, 1662538771);
INSERT INTO `la_system_config` VALUES (71, 'login', 'forceBindMobile', '0', 1660620367, 1662538771);
INSERT INTO `la_system_config` VALUES (72, 'login', 'openAgreement', '1', 1660620367, 1662538771);
INSERT INTO `la_system_config` VALUES (73, 'login', 'openOtherAuth', '1', 1660620367, 1662538771);
INSERT INTO `la_system_config` VALUES (74, 'login', 'autoLoginAuth', '1,2', 1660620367, 1662538771);
INSERT INTO `la_system_config` VALUES (80, 'user', 'defaultAvatar', '/api/static/default_avatar.png', 1660620367, 1662535156);
INSERT INTO `la_system_config` VALUES (200, 'recharge', 'openRecharge', '1', 1680160044, 1680166680);
INSERT INTO `la_system_config` VALUES (201, 'recharge', 'minRechargeMoney', '0.01', 1680160044, 1680166680);
COMMIT;

BEGIN;
INSERT INTO `la_dev_pay_config` VALUES (1, '余额支付', '/api/static/balance_pay.png', 1, 0, '余额支付', '{}');
INSERT INTO `la_dev_pay_config` VALUES (2, '微信支付', '/api/static/wechat_pay.png', 2, 0, '微信支付', '{}');
COMMIT;

BEGIN;
INSERT INTO `la_dev_pay_way` VALUES (1, 1, 1, 0, 1);
INSERT INTO `la_dev_pay_way` VALUES (2, 2, 1, 1, 1);
INSERT INTO `la_dev_pay_way` VALUES (3, 1, 2, 0, 1);
INSERT INTO `la_dev_pay_way` VALUES (4, 2, 2, 1, 1);
INSERT INTO `la_dev_pay_way` VALUES (5, 1, 3, 0, 1);
INSERT INTO `la_dev_pay_way` VALUES (6, 2, 3, 1, 1);
COMMIT;

BEGIN;
INSERT INTO `la_notice_setting` VALUES (1, 101, '登录验证码', '用户手机号码登录时发送', 1, 2, '{}', '{\"type\":\"sms\",\"templateId\":\"SMS_222458159\",\"content\":\"您正在登录，验证码${code}，切勿将验证码泄露于他人，本条验证码有效期5分钟。\",\"tips\":[\"可选变量 验证码:code\",\"示例：您正在登录，验证码${code}，切勿将验证码泄露于他人，本条验证码有效期5分钟。\",\"生效条件：1、管理后台完成短信设置。2、第三方短信平台申请模板。\"],\"status\":\"1\"}', '{}', '{}', 0, 1648696695, 1648696695, 0);
INSERT INTO `la_notice_setting` VALUES (2, 102, '绑定手机验证码', '用户绑定手机号码时发送', 1, 2, '{}', '{\"type\":\"sms\",\"templateId\":\"SMS_175615069\",\"content\":\"您正在绑定手机号，验证码${code}，切勿将验证码泄露于他人，本条验证码有效期5分钟。\",\"tips\":[\"可选变量 验证码:code\",\"示例：您正在绑定手机号，验证码${code}，切勿将验证码泄露于他人，本条验证码有效期5分钟。\",\"生效条件：1、管理后台完成短信设置。2、第三方短信平台申请模板。\"],\"status\":\"1\"}', '{}', '{}', 0, 1648696695, 1648696695, 0);
INSERT INTO `la_notice_setting` VALUES (3, 103, '变更手机验证码', '用户变更手机号码时发送', 1, 2, '{}', '{\"type\":\"sms\",\"templateId\":\"SMS_207952628\",\"content\":\"您正在变更手机号，验证码${code}，切勿将验证码泄露于他人，本条验证码有效期5分钟。\",\"tips\":[\"可选变量 验证码:code\",\"示例：您正在变更手机号，验证码${code}，切勿将验证码泄露于他人，本条验证码有效期5分钟。\",\"生效条件：1、管理后台完成短信设置。2、第三方短信平台申请模板。\"],\"status\":\"1\"}', '{}', '{}', 0, 1648696695, 1648696695, 0);
INSERT INTO `la_notice_setting` VALUES (4, 104, '找回登录密码验证码', '用户找回登录密码号码时发送', 1, 2, '{}', '{\"type\":\"sms\",\"templateId\":\"SMS_175615069\",\"content\":\"您正在找回登录密码，验证码${code}，切勿将验证码泄露于他人，本条验证码有效期5分钟。\",\"tips\":[\"可选变量 验证码:code\",\"示例：您正在找回登录密码，验证码${code}，切勿将验证码泄露于他人，本条验证码有效期5分钟。\",\"条验证码有效期5分钟。\"],\"status\":\"1\"}', '{}', '{}', 0, 1648696695, 1648696695, 0);
COMMIT;

BEGIN;
INSERT INTO `la_decorate_tabbar` VALUES (13, '首页', '/api/static/tabbar_home_sel.png', '/api/static/tabbar_home.png', '{\"path\":\"/pages/index/index\",\"name\":\"商城首页\",\"type\":\"shop\"}', 1662688157, 1662688157);
INSERT INTO `la_decorate_tabbar` VALUES (14, '资讯', '/api/static/tabbar_text_sel.png', '/api/static/tabbar_text.png', '{\"path\":\"/pages/news/news\",\"name\":\"文章资讯\",\"type\":\"shop\"}', 1662688157, 1662688157);
INSERT INTO `la_decorate_tabbar` VALUES (15, '我的', '/api/static/tabbar_me_sel.png', '/api/static/tabbar_me.png', '{\"path\":\"/pages/user/user\",\"name\":\"个人中心\",\"type\":\"shop\"}', 1662688157, 1662688157);
COMMIT;

BEGIN;
INSERT INTO `la_decorate_page` VALUES (1, 1, '商城首页', '[{\"title\":\"搜索\",\"name\":\"search\",\"disabled\":1,\"content\":{},\"styles\":{}},{\"title\":\"首页轮播图\",\"name\":\"banner\",\"content\":{\"enabled\":1,\"data\":[{\"image\":\"/api/static/banner01.png\",\"name\":\"\",\"link\":{\"path\":\"/pages/index/index\",\"name\":\"商城首页\",\"type\":\"shop\"}},{\"image\":\"/api/static/banner02.png\",\"name\":\"\",\"link\":{}}]},\"styles\":{}},{\"title\":\"导航菜单\",\"name\":\"nav\",\"content\":{\"enabled\":1,\"data\":[{\"image\":\"/api/static/nav01.png\",\"name\":\"资讯中心\",\"link\":{\"path\":\"/pages/news/news\",\"name\":\"文章资讯\",\"type\":\"shop\"}},{\"image\":\"/api/static/nav02.png\",\"name\":\"我的收藏\",\"link\":{\"path\":\"/pages/collection/collection\",\"name\":\"我的收藏\",\"type\":\"shop\"}},{\"image\":\"/api/static/nav03.png\",\"name\":\"个人设置\",\"link\":{\"path\":\"/pages/user_set/user_set\",\"name\":\"个人设置\",\"type\":\"shop\"}},{\"image\":\"/api/static/nav04.png\",\"name\":\"联系客服\",\"link\":{\"path\":\"/pages/customer_service/customer_service\",\"name\":\"联系客服\",\"type\":\"shop\"}},{\"image\":\"/api/static/nav05.png\",\"name\":\"关于我们\",\"link\":{\"path\":\"/pages/as_us/as_us\",\"name\":\"关于我们\",\"type\":\"shop\"}}]},\"styles\":{}},{\"id\":\"l84almsk2uhyf\",\"title\":\"资讯\",\"name\":\"news\",\"disabled\":1,\"content\":{},\"styles\":{}}]', 1661757188, 1663321380);
INSERT INTO `la_decorate_page` VALUES (2, 2, '个人中心', '[{\"title\":\"用户信息\",\"name\":\"user-info\",\"disabled\":1,\"content\":{},\"styles\":{}},{\"title\":\"我的服务\",\"name\":\"my-service\",\"content\":{\"style\":2,\"title\":\"服务中心\",\"data\":[{\"image\":\"/api/static/user_collect.png\",\"name\":\"我的收藏\",\"link\":{\"path\":\"/pages/collection/collection\",\"name\":\"我的收藏\",\"type\":\"shop\"}},{\"image\":\"/api/static/user_setting.png\",\"name\":\"个人设置\",\"link\":{\"path\":\"/pages/user_set/user_set\",\"name\":\"个人设置\",\"type\":\"shop\"}},{\"image\":\"/api/static/user_kefu.png\",\"name\":\"联系客服\",\"link\":{\"path\":\"/pages/customer_service/customer_service\",\"name\":\"联系客服\",\"type\":\"shop\"}},{\"image\":\"/api/static/user_money.png\",\"name\":\"我的钱包\",\"link\":{\"path\":\"/packages/pages/user_wallet/user_wallet\",\"name\":\"我的钱包\",\"type\":\"shop\"}}]},\"styles\":{}},{\"title\":\"个人中心广告图\",\"name\":\"user-banner\",\"content\":{\"enabled\":1,\"data\":[{\"image\":\"/api/static/ad01.jpg\",\"name\":\"\",\"link\":{}}]},\"styles\":{}}]', 1661757188, 1680513327);
INSERT INTO `la_decorate_page` VALUES (3, 3, '客服设置', '[{\"title\":\"客服设置\",\"name\":\"customer-service\",\"content\":{\"title\":\"添加客服二维码\",\"time\":\"早上 9:00 - 22:00\",\"mobile\":\"13800138000\",\"qrcode\":\"\"},\"styles\":{}}]', 1661757188, 1662689155);
INSERT INTO `la_decorate_page` VALUES (4, 4, 'PC首页装修', '[{\"id\":\"lajcn8d0hzhed\",\"title\":\"首页轮播图\",\"name\":\"banner\",\"content\":{\"enabled\":1,\"data\":[{\"image\":\"/api/static/banner01.png\",\"name\":\"\",\"link\":{}}]},\"styles\":{\"position\":\"absolute\",\"left\":\"42px\",\"top\":\"27px\",\"width\":\"234px\",\"height\":\"114px\"}}]', 1662689155, 1673251983);
COMMIT;

BEGIN;
INSERT INTO `la_article_category` VALUES (1, '文章资讯', 0, 1, 0, 1663317280, 1663317282, 0);
INSERT INTO `la_article_category` VALUES (2, '社会热点', 0, 1, 0, 1663321464, 1663321494, 0);
COMMIT;

BEGIN;
INSERT INTO `la_article` VALUES (1, 1, '让生活更精致！五款居家好物推荐，实用性超高', '##好物推荐🔥', '随着当代生活节奏的忙碌，很多人在闲暇之余都想好好的享受生活。随着科技的发展，也出现了越来越多可以帮助我们提升幸福感，让生活变得更精致的产品，下面周周就给大家盘点五款居家必备的好物，都是实用性很高的产品，周周可以保证大家买了肯定会喜欢。', '/api/static/article01.png', '<p><img src=\"https://likeadmin-java.yixiangonline.com/api/uploads/image/20220916/46d29489-4260-4917-8eca-d0f6cba6af23.png\" alt=\"\" data-href=\"\" style=\"\"/></p><p>拥有一台投影仪，闲暇时可以在家里直接看影院级别的大片，光是想想都觉得超级爽。市面上很多投影仪大几千，其实周周觉得没必要，选泰捷这款一千多的足够了，性价比非常高。</p><p>泰捷的专业度很高，在电视TV领域研发已经十年，有诸多专利和技术创新，荣获国内外多项技术奖项，拿下了腾讯创新工场投资，打造的泰捷视频TV端和泰捷电视盒子都获得了极高评价。</p><p>这款投影仪的分辨率在3000元内无敌，做到了真1080P高分辨率，也就是跟市场售价三千DLP投影仪一样的分辨率，真正做到了分毫毕现，像桌布的花纹、天空的云彩等，这些细节都清晰可见。</p><p>亮度方面，泰捷达到了850ANSI流明，同价位一般是200ANSI。这是因为泰捷为了提升亮度和LCD技术透射率低的问题，首创高功率LED灯源，让其亮度做到同价位最好。专业媒体也进行了多次对比，效果与3000元价位投影仪相当。</p><p>操作系统周周也很喜欢，完全不卡。泰捷作为资深音视频品牌，在系统优化方面有十年的研发经验，打造出的“零极”系统是业内公认效率最高、速度最快的系统，用户也评价它流畅度能一台顶三台，而且为了解决行业广告多这一痛点，系统内不植入任何广告。</p>', '红花', 9, 0, 1, 0, 1663317759, 1663322726, 0);
INSERT INTO `la_article` VALUES (2, 1, '埋葬UI设计师的坟墓不是内卷，而是免费模式', '', '本文从另外一个角度，聊聊作者对UI设计师职业发展前景的担忧，欢迎从事UI设计的同学来参与讨论，会有赠书哦', '/api/static/article02.jpeg', '<p><br></p><p style=\"text-align: justify;\">一个职业，卷，根本就没什么大不了的，尤其是成熟且收入高的职业，不卷才不符合事物发展的规律。何况 UI 设计师的人力市场到今天也和 5 年前一样，还是停留在大型菜鸡互啄的场面。远不能和医疗、证券、教师或者演艺练习生相提并论。</p><p style=\"text-align: justify;\">真正会让我对 <a href=\"https://www.uisdc.com/tag/ui\" target=\"_blank\">UI</a> 设计师发展前景觉得悲观的事情就只有一件 —— 国内的互联网产品免费机制。这也是一个我一直以来想讨论的话题，就在这次写一写。</p><p style=\"text-align: justify;\">国内互联网市场的发展，是一部浩瀚的 “免费经济” 发展史。虽然今天免费已经是深入国内民众骨髓的认知，但最早的中文互联网也是需要付费的，网游也都是要花钱的。</p><p style=\"text-align: justify;\">只是自有国情在此，付费确实阻碍了互联网行业的扩张和普及，一批创业家就开始通过免费的模式为用户提供服务，从而扩大了自己的产品覆盖面和普及程度。</p><p style=\"text-align: justify;\">印象最深的就是免费急先锋周鸿祎，和现在鲜少出现在公众视野不同，一零年前他是当之无愧的互联网教主，因为他开发出了符合中国国情的互联网产品 “打法”，让 360 的发展如日中天。</p><p style=\"text-align: justify;\">就是他在自传中提到：</p><p style=\"text-align: justify;\">只要是在互联网上每个人都需要的服务，我们就认为它是基础服务，基础服务一定是免费的，这样的话不会形成价值歧视。就是说，只要这种服务是每个人都一定要用的，我一定免费提供，而且是无条件免费。增值服务不是所有人都需要的，这个比例可能会相当低，它只是百分之几甚至更少比例的人需要，所以这种服务一定要收费……</p><p style=\"text-align: justify;\">这就是互联网的游戏规则，它决定了要想建立一个有效的商业模式，就一定要有海量的用户基数……</p>', '一一', 23, 0, 1, 0, 1663320938, 1663322854, 0);
INSERT INTO `la_article` VALUES (3, 2, '金山电池公布“沪广深市民绿色生活方式”调查结果', '', '60%以上受访者认为高质量的10分钟足以完成“自我充电”', '/api/static/article03.png', '<p style=\"text-align: left;\"><strong>深圳，2021年10月22日）</strong>生活在一线城市的沪广深市民一向以效率见称，工作繁忙和快节奏的生活容易缺乏充足的休息。近日，一项针对沪广深市民绿色生活方式而展开的网络问卷调查引起了大家的注意。问卷的问题设定集中于市民对休息时间的看法，以及从对循环充电电池的使用方面了解其对绿色生活方式的态度。该调查采用随机抽样的模式，并对最终收集的1,500份有效问卷进行专业分析后发现，超过60%的受访者表示，在每天的工作时段能拥有10分钟高质量的休息时间，就可以高效“自我充电”。该调查结果反映出，在快节奏时代下，人们需要高质量的休息时间，也要学会利用高效率的休息方式和工具来应对快节奏的生活，以时刻保持“满电”状态。</p><p style=\"text-align: left;\">　　<strong>60%以上受访者认为高质量的10分钟足以完成“自我充电”</strong></p><p style=\"text-align: left;\">　　这次调查超过1,500人，主要聚焦18至85岁的沪广深市民，了解他们对于休息时间的观念及使用充电电池的习惯，结果发现：</p><p style=\"text-align: left;\">　　· 90%以上有工作受访者每天工作时间在7小时以上，平均工作时间为8小时，其中43%以上的受访者工作时间超过9小时</p><p style=\"text-align: left;\">　　· 70%受访者认为在工作期间拥有10分钟“自我充电”时间不是一件困难的事情</p><p style=\"text-align: left;\">　　· 60%受访者认为在工作期间有10分钟休息时间足以为自己快速充电</p><p style=\"text-align: left;\">　　临床心理学家黄咏诗女士在发布会上分享为自己快速充电的实用技巧，她表示：“事实上，只要选择正确的休息方法，10分钟也足以为自己充电。以喝咖啡为例，我们可以使用心灵休息法 ── 静观呼吸，慢慢感受咖啡的温度和气味，如果能配合着聆听流水或海洋的声音，能够有效放松大脑及心灵。”</p><p style=\"text-align: left;\">　　这次调查结果反映出沪广深市民的希望在繁忙的工作中适时停下来，抽出10分钟喝杯咖啡、聆听音乐或小睡片刻，为自己充电。金山电池全新推出的“绿再十分充”超快速充电器仅需10分钟就能充好电，喝一杯咖啡的时间既能完成“自我充电”，也满足设备使用的用电需求，为提升工作效率和放松身心注入新能量。</p><p style=\"text-align: left;\">　　<strong>金山电池推出10分钟超快电池充电器*绿再十分充，以创新科技为市场带来革新体验</strong></p><p style=\"text-align: left;\">　　该问卷同时从沪广深市民对循环充电电池的使用方面进行了调查，以了解其对绿色生活方式的态度：</p><p style=\"text-align: left;\">　　· 87%受访者目前没有使用充电电池，其中61%表示会考虑使用充电电池</p><p style=\"text-align: left;\">　　· 58%受访者过往曾使用过充电电池，却只有20%左右市民仍在使用</p><p style=\"text-align: left;\">　　· 60%左右受访者认为充电电池尚未被广泛使用，主要障碍来自于充电时间过长、缺乏相关教育</p><p style=\"text-align: left;\">　　· 90%以上受访者认为充电电池充满电需要1小时或更长的时间</p><p style=\"text-align: left;\">　　金山电池一直致力于为大众提供安全可靠的充电电池，并与消费者的需求和生活方式一起演变及进步。今天，金山电池宣布推出10分钟超快电池充电器*绿再十分充，只需10分钟*即可将4粒绿再十分充充电电池充好电，充电速度比其他品牌提升3倍**。充电器的LED灯可以显示每粒电池的充电状态和模式，并提示用户是否错误插入已损坏电池或一次性电池。尽管其体型小巧，却具备多项创新科技 ，如拥有独特的充电算法以优化充电电流，并能根据各个电池类型、状况和温度用最短的时间为充电电池充好电;绿再十分充内置横流扇，有效防止电池温度过热和提供低噪音的充电环境等。<br></p>', '中网资讯科技', 3, 0, 1, 0, 1663322665, 1663322665, 0);
COMMIT;

BEGIN;
INSERT INTO `la_system_auth_menu` VALUES (1, 0, 'C', '工作台', 'el-icon-Monitor', 50, 'index:console', 'workbench', 'workbench/index', '', '', 1, 1, 0, 1650341765, 1668672757);
INSERT INTO `la_system_auth_menu` VALUES (100, 0, 'M', '权限管理', 'el-icon-Lock', 44, '', 'permission', '', '', '', 0, 1, 0, 1650341765, 1662626201);
INSERT INTO `la_system_auth_menu` VALUES (101, 100, 'C', '管理员', 'local-icon-wode', 0, 'system:admin:list', 'admin', 'permission/admin/index', '', '', 1, 1, 0, 1650341765, 1663301404);
INSERT INTO `la_system_auth_menu` VALUES (102, 101, 'A', '管理员详情', '', 0, 'system:admin:detail', '', '', '', '', 0, 1, 0, 1650341765, 1660201785);
INSERT INTO `la_system_auth_menu` VALUES (103, 101, 'A', '管理员新增', '', 0, 'system:admin:add', '', '', '', '', 0, 1, 0, 1650341765, 1650341765);
INSERT INTO `la_system_auth_menu` VALUES (104, 101, 'A', '管理员编辑', '', 0, 'system:admin:edit', '', '', '', '', 0, 1, 0, 1650341765, 1650341765);
INSERT INTO `la_system_auth_menu` VALUES (105, 101, 'A', '管理员删除', '', 0, 'system:admin:del', '', '', '', '', 0, 1, 0, 1650341765, 1650341765);
INSERT INTO `la_system_auth_menu` VALUES (106, 101, 'A', '管理员状态', '', 0, 'system:admin:disable', '', '', '', '', 0, 1, 0, 1650341765, 1650341765);
INSERT INTO `la_system_auth_menu` VALUES (110, 100, 'C', '角色管理', 'el-icon-Female', 0, 'system:role:list', 'role', 'permission/role/index', '', '', 1, 1, 0, 1650341765, 1663301451);
INSERT INTO `la_system_auth_menu` VALUES (111, 110, 'A', '角色详情', '', 0, 'system:role:detail', '', '', '', '', 0, 1, 0, 1650341765, 1650341765);
INSERT INTO `la_system_auth_menu` VALUES (112, 110, 'A', '角色新增', '', 0, 'system:role:add', '', '', '', '', 0, 1, 0, 1650341765, 1650341765);
INSERT INTO `la_system_auth_menu` VALUES (113, 110, 'A', '角色编辑', '', 0, 'system:role:edit', '', '', '', '', 0, 1, 0, 1650341765, 1650341765);
INSERT INTO `la_system_auth_menu` VALUES (114, 110, 'A', '角色删除', '', 0, 'system:role:del', '', '', '', '', 0, 1, 0, 1650341765, 1650341765);
INSERT INTO `la_system_auth_menu` VALUES (120, 100, 'C', '菜单管理', 'el-icon-Operation', 0, 'system:menu:list', 'menu', 'permission/menu/index', '', '', 1, 1, 0, 1650341765, 1680340798);
INSERT INTO `la_system_auth_menu` VALUES (121, 120, 'A', '菜单详情', '', 0, 'system:menu:detail', '', '', '', '', 0, 1, 0, 1650341765, 1650341765);
INSERT INTO `la_system_auth_menu` VALUES (122, 120, 'A', '菜单新增', '', 0, 'system:menu:add', '', '', '', '', 0, 1, 0, 1650341765, 1650341765);
INSERT INTO `la_system_auth_menu` VALUES (123, 120, 'A', '菜单编辑', '', 0, 'system:menu:edit', '', '', '', '', 0, 1, 0, 1650341765, 1650341765);
INSERT INTO `la_system_auth_menu` VALUES (124, 120, 'A', '菜单删除', '', 0, 'system:menu:del', '', '', '', '', 0, 1, 0, 1650341765, 1650341765);
INSERT INTO `la_system_auth_menu` VALUES (130, 0, 'M', '组织管理', 'el-icon-OfficeBuilding', 45, '', 'organization', '', '', '', 0, 1, 0, 1650341765, 1664416715);
INSERT INTO `la_system_auth_menu` VALUES (131, 130, 'C', '部门管理', 'el-icon-Coordinate', 0, 'system:dept:list', 'department', 'organization/department/index', '', '', 1, 1, 0, 1650341765, 1660201994);
INSERT INTO `la_system_auth_menu` VALUES (132, 131, 'A', '部门详情', '', 0, 'system:dept:detail', '', '', '', '', 0, 1, 0, 1650341765, 1650341765);
INSERT INTO `la_system_auth_menu` VALUES (133, 131, 'A', '部门新增', '', 0, 'system:dept:add', '', '', '', '', 0, 1, 0, 1650341765, 1650341765);
INSERT INTO `la_system_auth_menu` VALUES (134, 131, 'A', '部门编辑', '', 0, 'system:dept:edit', '', '', '', '', 0, 1, 0, 1650341765, 1650341765);
INSERT INTO `la_system_auth_menu` VALUES (135, 131, 'A', '部门删除', '', 0, 'system:dept:del', '', '', '', '', 0, 1, 0, 1650341765, 1650341765);
INSERT INTO `la_system_auth_menu` VALUES (140, 130, 'C', '岗位管理', 'el-icon-PriceTag', 0, 'system:post:list', 'post', 'organization/post/index', '', '', 1, 1, 0, 1650341765, 1660202057);
INSERT INTO `la_system_auth_menu` VALUES (141, 140, 'A', '岗位详情', '', 0, 'system:post:detail', '', '', '', '', 0, 1, 0, 1650341765, 1650341765);
INSERT INTO `la_system_auth_menu` VALUES (142, 140, 'A', '岗位新增', '', 0, 'system:post:add', '', '', '', '', 0, 1, 0, 1650341765, 1650341765);
INSERT INTO `la_system_auth_menu` VALUES (143, 140, 'A', '岗位编辑', '', 0, 'system:post:edit', '', '', '', '', 0, 1, 0, 1650341765, 1650341765);
INSERT INTO `la_system_auth_menu` VALUES (144, 140, 'A', '岗位删除', '', 0, 'system:post:del', '', '', '', '', 0, 1, 0, 1650341765, 1650341765);
INSERT INTO `la_system_auth_menu` VALUES (200, 0, 'M', '其它管理', '', 0, '', '', '', '', '', 0, 0, 0, 1650341765, 1660636870);
INSERT INTO `la_system_auth_menu` VALUES (201, 200, 'M', '图库管理', '', 0, '', '', '', '', '', 0, 0, 0, 1650341765, 1650341765);
INSERT INTO `la_system_auth_menu` VALUES (202, 201, 'A', '文件列表', '', 0, 'albums:albumList', '', '', '', '', 0, 0, 0, 1650341765, 1650341765);
INSERT INTO `la_system_auth_menu` VALUES (203, 201, 'A', '文件命名', '', 0, 'albums:albumRename', '', '', '', '', 0, 0, 0, 1650341765, 1650341765);
INSERT INTO `la_system_auth_menu` VALUES (204, 201, 'A', '文件移动', '', 0, 'albums:albumMove', '', '', '', '', 0, 0, 0, 1650341765, 1650341765);
INSERT INTO `la_system_auth_menu` VALUES (205, 201, 'A', '文件删除', '', 0, 'albums:albumDel', '', '', '', '', 0, 0, 0, 1650341765, 1650341765);
INSERT INTO `la_system_auth_menu` VALUES (206, 201, 'A', '分类列表', '', 0, 'albums:cateList', '', '', '', '', 0, 0, 0, 1650341765, 1650341765);
INSERT INTO `la_system_auth_menu` VALUES (207, 201, 'A', '分类新增', '', 0, 'albums:cateAdd', '', '', '', '', 0, 0, 0, 1650341765, 1650341765);
INSERT INTO `la_system_auth_menu` VALUES (208, 201, 'A', '分类命名', '', 0, 'albums:cateRename', '', '', '', '', 0, 0, 0, 1650341765, 1650341765);
INSERT INTO `la_system_auth_menu` VALUES (209, 201, 'A', '分类删除', '', 0, 'albums:cateDel', '', '', '', '', 0, 0, 0, 1650341765, 1650341765);
INSERT INTO `la_system_auth_menu` VALUES (215, 200, 'M', '上传管理', '', 0, '', '', '', '', '', 0, 0, 0, 1650341765, 1650341765);
INSERT INTO `la_system_auth_menu` VALUES (216, 215, 'A', '上传图片', '', 0, 'upload:image', '', '', '', '', 0, 0, 0, 1650341765, 1650341765);
INSERT INTO `la_system_auth_menu` VALUES (217, 215, 'A', '上传视频', '', 0, 'upload:video', '', '', '', '', 0, 0, 0, 1650341765, 1650341765);
INSERT INTO `la_system_auth_menu` VALUES (500, 0, 'M', '系统设置', 'el-icon-Setting', 0, '', 'setting', '', '', '', 0, 1, 0, 1650341765, 1662626322);
INSERT INTO `la_system_auth_menu` VALUES (501, 500, 'M', '网站设置', 'el-icon-Basketball', 10, '', 'website', '', '', '', 0, 1, 0, 1650341765, 1663233572);
INSERT INTO `la_system_auth_menu` VALUES (502, 501, 'C', '网站信息', '', 0, 'setting:website:detail', 'information', 'setting/website/information', '', '', 0, 1, 0, 1650341765, 1660202218);
INSERT INTO `la_system_auth_menu` VALUES (503, 502, 'A', '保存配置', '', 0, 'setting:website:save', '', '', '', '', 0, 0, 0, 1650341765, 1650341765);
INSERT INTO `la_system_auth_menu` VALUES (505, 501, 'C', '网站备案', '', 0, 'setting:copyright:detail', 'filing', 'setting/website/filing', '', '', 0, 1, 0, 1650341765, 1660202294);
INSERT INTO `la_system_auth_menu` VALUES (506, 505, 'A', '备案保存', '', 0, 'setting:copyright:save', '', 'setting/website/protocol', '', '', 0, 0, 0, 1650341765, 1650341765);
INSERT INTO `la_system_auth_menu` VALUES (510, 501, 'C', '政策协议', '', 0, 'setting:protocol:detail', 'protocol', 'setting/website/protocol', '', '', 0, 1, 0, 1660027606, 1660202312);
INSERT INTO `la_system_auth_menu` VALUES (511, 510, 'A', '协议保存', '', 0, 'setting:protocol:save', '', '', '', '', 0, 0, 0, 1660027606, 1663670865);
INSERT INTO `la_system_auth_menu` VALUES (515, 600, 'C', '字典管理', 'el-icon-Box', 0, 'setting:dict:type:list', 'dict', 'setting/dict/type/index', '', '', 0, 1, 0, 1660035436, 1663226087);
INSERT INTO `la_system_auth_menu` VALUES (516, 515, 'A', '字典类型新增', '', 0, 'setting:dict:type:add', '', '', '', '', 0, 1, 0, 1660202761, 1660202761);
INSERT INTO `la_system_auth_menu` VALUES (517, 515, 'A', '字典类型编辑', '', 0, 'setting:dict:type:edit', '', '', '', '', 0, 1, 0, 1660202842, 1660202842);
INSERT INTO `la_system_auth_menu` VALUES (518, 515, 'A', '字典类型删除', '', 0, 'setting:dict:type:del', '', '', '', '', 0, 1, 0, 1660202903, 1660202903);
INSERT INTO `la_system_auth_menu` VALUES (519, 600, 'C', '字典数据管理', '', 0, 'setting:dict:data:list', 'dict/data', 'setting/dict/data/index', '/dev_tools/dict', '', 0, 0, 0, 1660202948, 1663309252);
INSERT INTO `la_system_auth_menu` VALUES (520, 515, 'A', '字典数据新增', '', 0, 'setting:dict:data:add', '', '', '', '', 0, 1, 0, 1660203117, 1660203117);
INSERT INTO `la_system_auth_menu` VALUES (521, 515, 'A', '字典数据编辑', '', 0, 'setting:dict:data:edit', '', '', '', '', 0, 1, 0, 1660203142, 1660203142);
INSERT INTO `la_system_auth_menu` VALUES (522, 515, 'A', '字典数据删除', '', 0, 'setting:dict:data:del', '', '', '', '', 0, 1, 0, 1660203159, 1660203159);
INSERT INTO `la_system_auth_menu` VALUES (550, 500, 'M', '系统维护', 'el-icon-SetUp', 0, '', 'system', '', '', '', 0, 1, 0, 1650341765, 1660202466);
INSERT INTO `la_system_auth_menu` VALUES (551, 550, 'C', '系统环境', '', 0, 'monitor:server', 'environment', 'setting/system/environment', '', '', 0, 1, 0, 1650341765, 1650341765);
INSERT INTO `la_system_auth_menu` VALUES (552, 550, 'C', '系统缓存', '', 0, 'monitor:cache', 'cache', 'setting/system/cache', '', '', 0, 1, 0, 1650341765, 1650341765);
INSERT INTO `la_system_auth_menu` VALUES (553, 550, 'C', '系统日志', '', 0, 'system:log:operate', 'journal', 'setting/system/journal', '', '', 0, 1, 0, 1650341765, 1650341765);
INSERT INTO `la_system_auth_menu` VALUES (554, 550, 'C', '登录日志', '', 0, 'system:log:login', 'login_log', 'setting/system/login_log', '', '', 0, 1, 0, 1673942795, 1673942795);
INSERT INTO `la_system_auth_menu` VALUES (555, 500, 'C', '存储设置', 'el-icon-FolderOpened', 6, 'setting:storage:list', 'storage', 'setting/storage/index', '', '', 0, 1, 0, 1650341765, 1663312996);
INSERT INTO `la_system_auth_menu` VALUES (556, 555, 'A', '保存配置', '', 0, 'setting:storage:edit', '', '', '', '', 0, 1, 0, 1650341765, 1650341765);
INSERT INTO `la_system_auth_menu` VALUES (600, 0, 'M', '开发工具', 'el-icon-EditPen', 0, '', 'dev_tools', '', '', '', 0, 1, 0, 1660027606, 1664335701);
INSERT INTO `la_system_auth_menu` VALUES (610, 600, 'C', '代码生成器', 'el-icon-DocumentAdd', 0, 'gen:list', 'code', 'dev_tools/code/index', '', '', 0, 1, 0, 1660028954, 1660532510);
INSERT INTO `la_system_auth_menu` VALUES (611, 610, 'A', '导入数据表', '', 0, 'gen:importTable', '', '', '', '', 0, 1, 0, 1660532389, 1660532389);
INSERT INTO `la_system_auth_menu` VALUES (612, 610, 'A', '生成代码', '', 0, 'gen:genCode', '', '', '', '', 0, 1, 0, 1660532421, 1660532421);
INSERT INTO `la_system_auth_menu` VALUES (613, 610, 'A', '下载代码', '', 0, 'gen:downloadCode', '', '', '', '', 0, 1, 0, 1660532437, 1660532437);
INSERT INTO `la_system_auth_menu` VALUES (614, 610, 'A', '预览代码', '', 0, 'gen:previewCode', '', '', '', '', 0, 1, 0, 1660532549, 1660532549);
INSERT INTO `la_system_auth_menu` VALUES (616, 610, 'A', '同步表结构', '', 0, 'gen:syncTable', '', '', '', '', 0, 1, 0, 1660532781, 1660532781);
INSERT INTO `la_system_auth_menu` VALUES (617, 610, 'A', '删除数据表', '', 0, 'gen:delTable', '', '', '', '', 0, 1, 0, 1660532800, 1660532800);
INSERT INTO `la_system_auth_menu` VALUES (618, 610, 'A', '数据表详情', '', 0, 'gen:detail', '', '', '', '', 0, 1, 0, 1660532964, 1660532977);
INSERT INTO `la_system_auth_menu` VALUES (700, 0, 'M', '素材管理', 'el-icon-Picture', 43, '', 'material', '', '', '', 0, 1, 0, 1660203293, 1663300847);
INSERT INTO `la_system_auth_menu` VALUES (701, 700, 'C', '素材中心', 'el-icon-PictureRounded', 0, '', 'index', 'material/index', '', '', 0, 1, 0, 1660203402, 1663301493);
INSERT INTO `la_system_auth_menu` VALUES (703, 0, 'M', '文章资讯', 'el-icon-ChatLineSquare', 49, '', 'article', '', '', '', 0, 1, 0, 1661757636, 1664416659);
INSERT INTO `la_system_auth_menu` VALUES (704, 703, 'C', '文章管理', 'el-icon-ChatDotSquare', 3, 'article:list', 'lists', 'article/lists/index', '', '', 1, 1, 0, 1661757743, 1663658220);
INSERT INTO `la_system_auth_menu` VALUES (705, 703, 'C', '文章栏目', 'el-icon-CollectionTag', 0, 'article:cate:list', 'column', 'article/column/index', '', '', 1, 1, 0, 1661759218, 1663578137);
INSERT INTO `la_system_auth_menu` VALUES (706, 0, 'M', '渠道设置', 'el-icon-Message', 46, '', 'channel', '', '', '', 0, 1, 0, 1661767630, 1664416682);
INSERT INTO `la_system_auth_menu` VALUES (707, 706, 'C', 'H5设置', 'el-icon-Cellphone', 0, 'channel:h5:detail', 'h5', 'channel/h5', '', '', 0, 1, 0, 1661768566, 1662626123);
INSERT INTO `la_system_auth_menu` VALUES (708, 706, 'M', '微信公众号', 'local-icon-dingdan', 0, '', 'wx_oa', '', '', '', 0, 1, 0, 1661769386, 1663301237);
INSERT INTO `la_system_auth_menu` VALUES (709, 708, 'C', '公众号配置', '', 0, 'channel:oa:detail', 'config', 'channel/wx_oa/config', '', '', 0, 1, 0, 1661769457, 1662638440);
INSERT INTO `la_system_auth_menu` VALUES (710, 706, 'C', '微信小程序', 'local-icon-qudao_xiaochengxu', 0, 'channel:mp:detail', 'weapp', 'channel/weapp', '', '', 0, 1, 0, 1661823746, 1673248286);
INSERT INTO `la_system_auth_menu` VALUES (711, 706, 'C', '微信开放平台', 'local-icon-weixin', 0, 'channel:op:detail', 'wx_op', 'channel/wx_op', '', '', 0, 1, 0, 1661824989, 1673258233);
INSERT INTO `la_system_auth_menu` VALUES (712, 0, 'M', '用户管理', 'el-icon-User', 48, '', 'consumer', '', '', '', 0, 1, 0, 1661832966, 1663294141);
INSERT INTO `la_system_auth_menu` VALUES (713, 712, 'C', '用户列表', 'el-icon-User', 0, 'user:list', 'lists', 'consumer/lists/index', '', '', 0, 1, 0, 1661839365, 1663301092);
INSERT INTO `la_system_auth_menu` VALUES (714, 714, 'A', '用户编辑', '', 0, 'user:edit', 'detail', 'consumer/lists/detail', '/consumer/lists', '', 0, 0, 0, 1661840502, 1662627718);
INSERT INTO `la_system_auth_menu` VALUES (715, 600, 'C', '编辑数据表', '', 0, 'gen:editTable', 'code/edit', 'dev_tools/code/edit', '/dev_tools/code', '', 0, 0, 0, 1661843525, 1661843615);
INSERT INTO `la_system_auth_menu` VALUES (716, 705, 'A', '栏目详情', '', 0, 'article:cate:detail', 'lists/edit', 'article/lists/edit', '/article/lists', '', 0, 0, 0, 1661844126, 1662626009);
INSERT INTO `la_system_auth_menu` VALUES (717, 0, 'M', '装修管理', 'el-icon-Brush', 47, '', 'decoration', '', '', '', 0, 1, 0, 1661845634, 1664416675);
INSERT INTO `la_system_auth_menu` VALUES (718, 717, 'C', '页面装修', 'el-icon-CopyDocument', 0, 'decorate:pages:detail', 'pages', 'decoration/pages/index', '', '', 0, 1, 0, 1661845678, 1663294313);
INSERT INTO `la_system_auth_menu` VALUES (719, 717, 'C', '底部导航', 'el-icon-Position', 0, 'decorate:tabbar:detail', 'tabbar', 'decoration/tabbar', '', '', 0, 1, 0, 1661845811, 1663294354);
INSERT INTO `la_system_auth_menu` VALUES (720, 794, 'M', '消息通知', 'el-icon-Message', 9, '', 'message', '', '', '', 0, 1, 0, 1661848742, 1680157682);
INSERT INTO `la_system_auth_menu` VALUES (721, 720, 'C', '通知设置', '', 0, 'setting:notice:list', 'notice', 'message/notice/index', '', '', 0, 1, 0, 1661848772, 1662638112);
INSERT INTO `la_system_auth_menu` VALUES (722, 720, 'C', '通知详情', '', 0, 'setting:notice:detail', 'notice/edit', 'message/notice/edit', '/setting/message/notice', '', 0, 0, 0, 1661848944, 1663142853);
INSERT INTO `la_system_auth_menu` VALUES (723, 720, 'C', '短信设置', '', 0, 'setting:sms:list', 'short_letter', 'message/short_letter/index', '', '', 0, 1, 0, 1661848995, 1662638165);
INSERT INTO `la_system_auth_menu` VALUES (724, 500, 'M', '用户设置', 'local-icon-keziyuyue', 8, '', 'user', '', '', '', 0, 1, 0, 1662455407, 1663301570);
INSERT INTO `la_system_auth_menu` VALUES (725, 724, 'C', '用户设置', '', 0, 'setting:user:detail', 'setup', 'setting/user/setup', '', '', 0, 1, 0, 1662455555, 1663312225);
INSERT INTO `la_system_auth_menu` VALUES (726, 724, 'C', '登录注册', '', 0, 'setting:login:detail', 'login_register', 'setting/user/login_register', '', '', 0, 1, 0, 1662456475, 1663312263);
INSERT INTO `la_system_auth_menu` VALUES (728, 500, 'C', '热门搜索', 'el-icon-Search', 7, 'setting:search:detail', 'search', 'setting/search/index', '', '', 0, 1, 0, 1662540429, 1663312392);
INSERT INTO `la_system_auth_menu` VALUES (730, 704, 'A', '文章新增', '', 0, 'article:add', '', '', '', '', 0, 1, 0, 1662625870, 1662625870);
INSERT INTO `la_system_auth_menu` VALUES (732, 704, 'A', '文章删除', '', 0, 'article:del', '', '', '', '', 0, 1, 0, 1662625894, 1662625894);
INSERT INTO `la_system_auth_menu` VALUES (733, 704, 'A', '文章状态', '', 0, 'article:change', '', '', '', '', 0, 1, 0, 1662625909, 1662625909);
INSERT INTO `la_system_auth_menu` VALUES (734, 705, 'A', '栏目新增', '', 0, 'article:cate:add', '', '', '', '', 0, 1, 0, 1662626024, 1662626024);
INSERT INTO `la_system_auth_menu` VALUES (735, 705, 'A', '栏目编辑', '', 0, 'article:cate:edit', '', '', '', '', 0, 1, 0, 1662626044, 1662626044);
INSERT INTO `la_system_auth_menu` VALUES (736, 705, 'A', '栏目删除', '', 0, 'article:cate:del', '', '', '', '', 0, 1, 0, 1662626060, 1662626060);
INSERT INTO `la_system_auth_menu` VALUES (737, 705, 'A', '栏目状态', '', 0, 'article:cate:change', '', '', '', '', 0, 1, 0, 1662626077, 1662626077);
INSERT INTO `la_system_auth_menu` VALUES (738, 704, 'A', '文章编辑', '', 0, 'article:edit', 'lists/edit', 'article/lists/edit', '', '', 0, 0, 0, 1662626554, 1663309550);
INSERT INTO `la_system_auth_menu` VALUES (739, 712, 'C', '用户详情', '', 0, 'user:detail', 'detail', 'consumer/lists/detail', '/consumer/lists', '', 0, 0, 0, 1662628049, 1662628049);
INSERT INTO `la_system_auth_menu` VALUES (740, 739, 'A', '用户编辑', '', 0, 'user:edit', '', '', '', '', 0, 1, 0, 1662628085, 1662628085);
INSERT INTO `la_system_auth_menu` VALUES (741, 721, 'A', '设置保存', '', 0, 'setting:notice:save', '', '', '', '', 0, 1, 0, 1662638049, 1662638049);
INSERT INTO `la_system_auth_menu` VALUES (742, 723, 'A', '短信详情', '', 0, 'setting:sms:detail', '', '', '', '', 0, 1, 0, 1662638180, 1662638180);
INSERT INTO `la_system_auth_menu` VALUES (743, 723, 'A', '保存设置', '', 0, 'setting:sms:save', '', '', '', '', 0, 1, 0, 1662638196, 1662638196);
INSERT INTO `la_system_auth_menu` VALUES (744, 707, 'A', '设置保存', '', 0, 'channel:h5:save', '', '', '', '', 0, 1, 0, 1662638326, 1662638326);
INSERT INTO `la_system_auth_menu` VALUES (745, 710, 'A', '设置保存', '', 0, 'channel:mp:detail', '', '', '', '', 0, 1, 0, 1662638359, 1662638359);
INSERT INTO `la_system_auth_menu` VALUES (746, 711, 'A', '保存设置', '', 0, 'channel:op:save', '', '', '', '', 0, 1, 0, 1662638410, 1673248274);
INSERT INTO `la_system_auth_menu` VALUES (747, 709, 'A', '保存', '', 0, 'channel:oa:save', '', '', '', '', 0, 1, 0, 1662638459, 1663310514);
INSERT INTO `la_system_auth_menu` VALUES (748, 708, 'C', '菜单管理', '', 0, 'channel:oaMenu:detail', 'menu', 'channel/wx_oa/menu', '', '', 0, 1, 0, 1663050714, 1669963980);
INSERT INTO `la_system_auth_menu` VALUES (750, 708, 'C', '关注回复', '', 0, 'channel:oaReplyFollow:list', 'follow', 'channel/wx_oa/reply/follow_reply', '', '', 0, 1, 0, 1663149592, 1664511108);
INSERT INTO `la_system_auth_menu` VALUES (751, 708, 'C', '关键字回复', '', 0, 'channel:oaReplyKeyword:list', 'keyword', 'channel/wx_oa/reply/keyword_reply', '', '', 0, 1, 0, 1663149622, 1664511241);
INSERT INTO `la_system_auth_menu` VALUES (752, 708, 'C', '默认回复', '', 0, 'channel:oaReplyDefault:list', 'default', 'channel/wx_oa/reply/default_reply', '', '', 0, 1, 0, 1663149650, 1664517685);
INSERT INTO `la_system_auth_menu` VALUES (753, 718, 'A', '保存', '', 0, 'decorate:pages:save', '', '', '', '', 0, 1, 0, 1663236648, 1663236648);
INSERT INTO `la_system_auth_menu` VALUES (754, 719, 'A', '保存', '', 0, 'decorate:tabbar:save', '', '', '', '', 0, 1, 0, 1663236675, 1663236675);
INSERT INTO `la_system_auth_menu` VALUES (755, 704, 'A', '文章详情', '', 0, 'article:detail', '', '', '', '', 0, 1, 0, 1663310241, 1663310252);
INSERT INTO `la_system_auth_menu` VALUES (756, 748, 'A', '发布', '', 0, 'channel:oaMenu:publish', '', '', '', '', 0, 1, 0, 1663310379, 1663310525);
INSERT INTO `la_system_auth_menu` VALUES (757, 748, 'A', '保存', '', 0, 'channel:oaMenu:save', '', '', '', '', 0, 1, 0, 1663310556, 1663310556);
INSERT INTO `la_system_auth_menu` VALUES (758, 725, 'A', '保存', '', 0, 'setting:user:save', '', '', '', '', 0, 1, 0, 1663312193, 1663312193);
INSERT INTO `la_system_auth_menu` VALUES (759, 726, 'A', '保存', '', 0, 'setting:login:save', '', '', '', '', 0, 1, 0, 1663312289, 1663312289);
INSERT INTO `la_system_auth_menu` VALUES (760, 728, 'A', '保存', '', 0, 'setting:search:save', '', '', '', '', 0, 1, 0, 1663312423, 1663312423);
INSERT INTO `la_system_auth_menu` VALUES (762, 750, 'A', '新增', '', 0, 'channel:oaReplyFollow:add', '', '', '', '', 1, 1, 0, 1664511131, 1664511131);
INSERT INTO `la_system_auth_menu` VALUES (763, 750, 'A', '状态', '', 0, 'channel:oaReplyFollow:status', '', '', '', '', 1, 1, 0, 1664511160, 1664511160);
INSERT INTO `la_system_auth_menu` VALUES (764, 750, 'A', '编辑', '', 0, 'channel:oaReplyFollow:edit', '', '', '', '', 1, 1, 0, 1664511177, 1664511190);
INSERT INTO `la_system_auth_menu` VALUES (765, 750, 'A', '删除', '', 0, 'channel:oaReplyFollow:del', '', '', '', '', 1, 1, 0, 1664511208, 1664511208);
INSERT INTO `la_system_auth_menu` VALUES (766, 751, 'A', '新增', '', 0, 'channel:oaReplyKeyword:add', '', '', '', '', 1, 1, 0, 1664511264, 1664511264);
INSERT INTO `la_system_auth_menu` VALUES (767, 751, 'A', '状态', '', 0, 'channel:oaReplyKeyword:status', '', '', '', '', 1, 1, 0, 1664511295, 1664511295);
INSERT INTO `la_system_auth_menu` VALUES (768, 751, 'A', '编辑', '', 0, 'channel:oaReplyKeyword:edit', '', '', '', '', 1, 1, 0, 1664511312, 1664511312);
INSERT INTO `la_system_auth_menu` VALUES (769, 751, 'A', '删除', '', 0, 'channel:oaReplyKeyword:del', '', '', '', '', 1, 1, 0, 1664511327, 1664511327);
INSERT INTO `la_system_auth_menu` VALUES (770, 752, 'A', '新增', '', 0, 'channel:oaReplyDefault:add', '', '', '', '', 1, 1, 0, 1664517709, 1664517709);
INSERT INTO `la_system_auth_menu` VALUES (771, 752, 'A', '编辑', '', 0, 'channel:oaReplyDefault:edit', '', '', '', '', 1, 1, 0, 1664517725, 1664517725);
INSERT INTO `la_system_auth_menu` VALUES (772, 752, 'A', '状态', '', 0, 'channel:oaReplyDefault:status', '', '', '', '', 1, 1, 0, 1664517757, 1664517757);
INSERT INTO `la_system_auth_menu` VALUES (773, 752, 'A', '删除', '', 0, 'channel:oaReplyDefault:del', '', '', '', '', 1, 1, 0, 1664517778, 1664517778);
INSERT INTO `la_system_auth_menu` VALUES (774, 610, 'A', '导入数据表列表', '', 0, 'gen:db', '', '', '', '', 1, 1, 0, 1665646316, 1665646316);
INSERT INTO `la_system_auth_menu` VALUES (775, 703, 'C', '文章添加/编辑', '', 0, 'article:add/edit', 'lists/edit', 'article/lists/edit', '/article/lists', '', 0, 0, 0, 1668677477, 1668677477);
INSERT INTO `la_system_auth_menu` VALUES (776, 550, 'C', '定时任务', '', 0, 'crontab:list', 'scheduled_task', 'setting/system/scheduled_task/index', '', '', 0, 1, 0, 1669964588, 1669964598);
INSERT INTO `la_system_auth_menu` VALUES (777, 550, 'C', '定时任务添加/编辑', '', 0, 'crontab/add:edit', 'scheduled_task/edit', 'setting/system/scheduled_task/edit', '/setting/system/scheduled_task', '', 0, 0, 0, 1669965065, 1669965660);
INSERT INTO `la_system_auth_menu` VALUES (779, 717, 'C', 'PC端', 'el-icon-Monitor', 0, '', 'pc', 'decoration/pc', '', '', 0, 1, 0, 1673248971, 1673249036);
INSERT INTO `la_system_auth_menu` VALUES (780, 0, 'M', '模板', 'el-icon-SetUp', 0, '', 'template', '', '', '', 0, 1, 0, 1673319812, 1673319812);
INSERT INTO `la_system_auth_menu` VALUES (781, 780, 'M', '组件示例', 'el-icon-Coin', 0, '', 'component', '', '', '', 0, 1, 0, 1673319842, 1673319842);
INSERT INTO `la_system_auth_menu` VALUES (782, 781, 'C', '富文本', '', 0, '', 'rich_text', 'template/component/rich_text', '', '', 0, 1, 0, 1673319879, 1673319879);
INSERT INTO `la_system_auth_menu` VALUES (783, 781, 'C', '上传文件', '', 0, '', 'upload', 'template/component/upload', '', '', 0, 1, 0, 1673319905, 1673319905);
INSERT INTO `la_system_auth_menu` VALUES (784, 781, 'C', '图标', '', 0, '', 'icon', 'template/component/icon', '', '', 0, 1, 0, 1673319936, 1673319936);
INSERT INTO `la_system_auth_menu` VALUES (785, 781, 'C', '文件选择器', '', 0, '', 'file', 'template/component/file', '', '', 0, 1, 0, 1673319972, 1673319972);
INSERT INTO `la_system_auth_menu` VALUES (786, 781, 'C', '链接选择器', '', 0, '', 'link', 'template/component/link', '', '', 0, 1, 0, 1673319997, 1673319997);
INSERT INTO `la_system_auth_menu` VALUES (787, 781, 'C', '超出自动打点', '', 0, '', 'overflow', 'template/component/overflow', '', '', 0, 1, 0, 1673320021, 1673320021);
INSERT INTO `la_system_auth_menu` VALUES (788, 781, 'C', '悬浮input', '', 0, '', 'popover_input', 'template/component/popover_input', '', '', 0, 1, 0, 1673320046, 1673320046);
INSERT INTO `la_system_auth_menu` VALUES (789, 500, 'M', '支付设置', 'local-icon-fukuan', 0, '', 'pay', '', '', '', 0, 1, 0, 1680082358, 1680082358);
INSERT INTO `la_system_auth_menu` VALUES (790, 789, 'C', '支付方式', '', 0, 'setting:payment:method', 'method', 'setting/pay/method/index', '', '', 0, 1, 0, 1680082434, 1680082434);
INSERT INTO `la_system_auth_menu` VALUES (791, 790, 'A', '保存', '', 0, 'setting:payment:editMethod', '', '', '', '', 0, 1, 0, 1680082473, 1680082473);
INSERT INTO `la_system_auth_menu` VALUES (792, 789, 'C', '支付配置', '', 0, 'setting:payment:list', 'config', 'setting/pay/config/index', '', '', 0, 1, 0, 1680083681, 1680083681);
INSERT INTO `la_system_auth_menu` VALUES (793, 792, 'A', '配置', '', 0, 'setting:payment:editConfig', '', '', '', '', 0, 1, 0, 1680083734, 1680083734);
INSERT INTO `la_system_auth_menu` VALUES (794, 0, 'M', '应用管理', 'el-icon-Postcard', 0, '', 'app', '', '', '', 0, 1, 0, 1680157467, 1680157467);
INSERT INTO `la_system_auth_menu` VALUES (795, 794, 'C', '用户充值', 'local-icon-caiwu', 0, 'marketing:recharge:detail', 'recharge', 'app/recharge/index', '', '', 0, 1, 0, 1680158031, 1680158031);
INSERT INTO `la_system_auth_menu` VALUES (796, 795, 'A', '保存', '', 0, 'marketing:recharge:save', '', '', '', '', 0, 1, 0, 1680158056, 1680158056);
INSERT INTO `la_system_auth_menu` VALUES (797, 0, 'M', '财务管理', 'local-icon-user_gaikuang', 0, '', 'finance', '', '', '', 0, 1, 0, 1680169445, 1680169445);
INSERT INTO `la_system_auth_menu` VALUES (798, 797, 'C', '充值记录', 'el-icon-Wallet', 0, 'finance:recharger:list', 'recharge_record', 'finance/recharge_record', '', '', 0, 1, 0, 1680169574, 1680169574);
INSERT INTO `la_system_auth_menu` VALUES (799, 797, 'C', '余额明细', 'local-icon-qianbao', 0, 'finance:wallet:list', 'balance_details', 'finance/balance_details', '', '', 0, 1, 0, 1680169658, 1680169658);
INSERT INTO `la_system_auth_menu` VALUES (800, 797, 'C', '退款记录', 'local-icon-heshoujilu', 0, 'finance:refund:list', 'refund_record', 'refund_record', '', '', 0, 1, 0, 1680171454, 1680171454);
INSERT INTO `la_system_auth_menu` VALUES (801, 798, 'A', '退款', '', 0, 'finance:recharger:refund', '', '', '', '', 0, 1, 0, 1680486780, 1680486780);
INSERT INTO `la_system_auth_menu` VALUES (802, 800, 'A', '退款日志', '', 0, 'finance:refund:log', '', '', '', '', 0, 1, 0, 1680486899, 1680486899);
INSERT INTO `la_system_auth_menu` VALUES (803, 800, 'A', '重新退款', '', 0, 'finance:recharger:refundAgain', '', '', '', '', 0, 1, 0, 1680486916, 1680486916);
COMMIT;
