SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ls_album
-- ----------------------------
DROP TABLE IF EXISTS `ls_album`;
CREATE TABLE `ls_album`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `cid` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '类目ID',
  `aid` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '管理员ID',
  `uid` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户ID',
  `type` tinyint(2) UNSIGNED NOT NULL DEFAULT 10 COMMENT '类型: 10=图片, 20=视频',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '文件名称',
  `uri` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件路径',
  `ext` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '文件扩展',
  `size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '文件大小',
  `is_delete` int(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除: 0=否, 1=是',
  `create_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '更新时间',
  `delete_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '相册表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ls_album_cate
-- ----------------------------
DROP TABLE IF EXISTS `ls_album_cate`;
CREATE TABLE `ls_album_cate`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `pid` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '父级ID',
  `type` tinyint(2) UNSIGNED NOT NULL DEFAULT 10 COMMENT '类型: 10=图片, 20=视频',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '分类名称',
  `is_delete` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除: 0=否, 1=是',
  `create_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '更新时间',
  `delete_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '相册分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ls_article
-- ----------------------------
DROP TABLE IF EXISTS `ls_article`;
CREATE TABLE `ls_article`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cid` int(10) UNSIGNED NOT NULL COMMENT '分类',
  `title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '标题',
  `intro` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '简介',
  `image` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '封面',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '内容',
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
-- Table structure for ls_article_category
-- ----------------------------
DROP TABLE IF EXISTS `ls_article_category`;
CREATE TABLE `ls_article_category`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '名称',
  `sort` smallint(5) UNSIGNED NOT NULL DEFAULT 50 COMMENT '排序',
  `is_show` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '是否显示: 0=否, 1=是',
  `is_delete` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除: 0=否, 1=是',
  `create_time` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '更新时间',
  `delete_time` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文章分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ls_system_admin
-- ----------------------------
DROP TABLE IF EXISTS `ls_system_admin`;
CREATE TABLE `ls_system_admin`  (
    `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
    `dept_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '部门ID',
    `post_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '岗位ID',
    `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户账号',
    `nickname` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户昵称',
    `password` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户密码',
    `avatar` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户头像',
    `role` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '角色主键',
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
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统管理成员表' ROW_FORMAT = Dynamic;
-- ----------------------------
-- Table structure for ls_system_config
-- ----------------------------
DROP TABLE IF EXISTS `ls_system_config`;
CREATE TABLE `ls_system_config`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '类型',
  `name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '键',
  `value` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '值',
  `create_time` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ls_system_dept
-- ----------------------------
DROP TABLE IF EXISTS `ls_system_dept`;
CREATE TABLE `ls_system_dept`  (
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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统部门管理表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ls_system_log_login
-- ----------------------------
DROP TABLE IF EXISTS `ls_system_log_login`;
CREATE TABLE `ls_system_log_login`  (
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
-- Table structure for ls_system_log_operate
-- ----------------------------
DROP TABLE IF EXISTS `ls_system_log_operate`;
CREATE TABLE `ls_system_log_operate`  (
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
-- Table structure for ls_system_menu
-- ----------------------------
DROP TABLE IF EXISTS `ls_system_menu`;
CREATE TABLE `ls_system_menu`  (
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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ls_system_role
-- ----------------------------
DROP TABLE IF EXISTS `ls_system_role`;
CREATE TABLE `ls_system_role`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '角色名称',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '备注信息',
  `sort` smallint(5) UNSIGNED NOT NULL DEFAULT 0 COMMENT '角色排序',
  `is_disable` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否禁用: 0=否, 1=是',
  `create_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ls_system_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `ls_system_role_menu`;
CREATE TABLE `ls_system_role_menu`  (
  `id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '主键',
  `role_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '角色ID',
  `menu_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`, `id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统角色菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ls_system_dept
-- ----------------------------
DROP TABLE IF EXISTS `ls_system_dept`;
CREATE TABLE `ls_system_dept`  (
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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统部门管理表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ls_system_post
-- ----------------------------
DROP TABLE IF EXISTS `ls_system_post`;
CREATE TABLE `ls_system_post`  (
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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统岗位管理表' ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for ls_system_post
-- ----------------------------
DROP TABLE IF EXISTS `ls_system_post`;
CREATE TABLE `ls_system_post`  (
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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统岗位管理表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- INSERT
-- ----------------------------
BEGIN;
INSERT INTO `ls_system_menu` VALUES (1, 0, 'C', '工作台', 'tradingdata', 100, 'index:console', 'workbench', 'workbench/index', '', '', 0, 1, 0, 1650341765, 1651889557);
INSERT INTO `ls_system_menu` VALUES (2, 0, 'M', '权限管理', 'icon_set_quanxian', 100, '', 'permission', '', '', '', 0, 1, 0, 1650348157, 1650883759);
INSERT INTO `ls_system_menu` VALUES (3, 2, 'C', '管理员', '', 100, 'system:admin:lists', 'admin', 'permission/admin/index', '', '', 1, 1, 0, 1650348252, 1650422316);
INSERT INTO `ls_system_menu` VALUES (5, 3, 'A', '添加管理员', '', 10, 'system:admin:add', '', '', '', '', 0, 1, 0, 1650350200, 1650350200);
INSERT INTO `ls_system_menu` VALUES (6, 3, 'A', '编辑管理员', '', 10, 'system:admin:edit', '', '', '', '', 0, 1, 0, 1650350327, 1650350327);
INSERT INTO `ls_system_menu` VALUES (7, 3, 'A', '删除管理员', '', 10, 'system:admin:del', '', '', '', '', 0, 1, 0, 1650350490, 1650350512);
INSERT INTO `ls_system_menu` VALUES (8, 3, 'A', '设置管理员状态', '', 10, 'system:admin:disable', '', '', '', '', 0, 1, 0, 1650350597, 1650350597);
INSERT INTO `ls_system_menu` VALUES (9, 2, 'C', '角色', '', 100, 'system:role:lists', 'role', 'permission/role/index', '', '', 1, 1, 0, 1650350713, 1650350713);
INSERT INTO `ls_system_menu` VALUES (11, 9, 'A', '添加角色', '', 10, 'system:role:add', '', '', '', '', 0, 1, 0, 1650351087, 1650351087);
INSERT INTO `ls_system_menu` VALUES (12, 9, 'A', '编辑角色', '', 10, 'system:role:edit', '', '', '', '', 0, 1, 0, 1650351261, 1650351261);
INSERT INTO `ls_system_menu` VALUES (13, 9, 'A', '删除角色', '', 10, 'system:role:del', '', '', '', '', 0, 1, 0, 1650351333, 1650351333);
INSERT INTO `ls_system_menu` VALUES (14, 2, 'C', '菜单', '', 100, 'system:menu:add', 'menu', 'permission/menu/index', '', '', 1, 0, 0, 1650351462, 1650883606);
INSERT INTO `ls_system_menu` VALUES (16, 14, 'A', '新增菜单', '', 10, 'system:menu:add', '', '', '', '', 0, 1, 0, 1650351620, 1650351620);
INSERT INTO `ls_system_menu` VALUES (17, 14, 'A', '编辑菜单', '', 10, 'system:menu:edit', '', '', '', '', 0, 1, 0, 1650351675, 1650351675);
INSERT INTO `ls_system_menu` VALUES (18, 14, 'A', '删除菜单', '', 10, 'system:menu:del', '', '', '', '', 0, 1, 0, 1650351710, 1650422335);
INSERT INTO `ls_system_menu` VALUES (19, 0, 'M', '系统设置', 'icon_sort', 100, '', 'setting', '', '', '', 0, 1, 0, 1650422463, 1650883955);
INSERT INTO `ls_system_menu` VALUES (20, 19, 'M', '网站设置', '', 100, '', 'website', '', '', '', 0, 1, 0, 1650422514, 1650422514);
INSERT INTO `ls_system_menu` VALUES (21, 20, 'C', '网站信息', '', 100, 'setting:getWebsite', 'information', 'setting/website/information', '', '', 0, 1, 0, 1650422667, 1650423073);
INSERT INTO `ls_system_menu` VALUES (22, 20, 'C', '备案信息', '', 100, 'setting:getCopyright', 'filing', 'setting/website/filing', '', '', 0, 1, 0, 1650423044, 1650423077);
INSERT INTO `ls_system_menu` VALUES (23, 19, 'M', '系统维护', '', 100, '', 'system', '', '', '', 0, 1, 0, 1650599623, 1650599623);
INSERT INTO `ls_system_menu` VALUES (24, 23, 'C', '系统环境', '', 100, 'setting:server', 'environment', 'setting/system/environment', '', '', 0, 1, 0, 1650599791, 1650600124);
INSERT INTO `ls_system_menu` VALUES (25, 23, 'C', '系统日志', '', 100, 'setting:operate', 'journal', 'setting/system/journal', '', '', 0, 1, 0, 1650600381, 1650600796);
INSERT INTO `ls_system_menu` VALUES (26, 23, 'C', '系统缓存', '', 100, 'setting:cache', 'cache', 'setting/system/cache', '', '', 0, 1, 0, 1650600466, 1650600807);
INSERT INTO `ls_system_menu` VALUES (28, 19, 'C', '个人设置', '', 100, 'system:admin:self', 'personal_data', 'setting/personal/personal_data', '', '', 0, 0, 0, 1650859164, 1650859929);
INSERT INTO `ls_system_menu` VALUES (29, 0, 'M', '组织管理', 'icon_fenxiao_member', 90, '', 'organize', '', '', '', 0, 1, 0, 1653978281, 1656302989);
INSERT INTO `ls_system_menu` VALUES (30, 29, 'C', '部门管理', '', 100, '', 'department', 'organize/department/index', '', '', 1, 1, 0, 1653978472, 1653978472);
INSERT INTO `ls_system_menu` VALUES (31, 29, 'C', '岗位管理', '', 100, 'system:post:list', 'post', 'organize/post/index', '', '', 1, 1, 0, 1653978814, 1655716541);
INSERT INTO `ls_system_menu` VALUES (32, 31, 'A', '新增岗位', '', 10, 'system:post:add', '', '', '', '', 0, 1, 0, 1655716165, 1655716446);
INSERT INTO `ls_system_menu` VALUES (33, 19, 'M', '存储设置', '', 100, '', 'storage', '', '', '', 0, 1, 0, 1655891318, 1655891318);
INSERT INTO `ls_system_menu` VALUES (34, 33, 'C', '存储设置', '', 100, '', 'index', 'setting/storage/index', '', '', 0, 1, 0, 1655891420, 1655891420);
INSERT INTO `ls_system_menu` VALUES (36, 34, 'A', '设置储存', '', 10, 'setting:storage:edit', '', '', '', '', 0, 1, 0, 1655981352, 1655981352);
COMMIT;

BEGIN;
INSERT INTO `ls_system_dept` VALUES (1, 0, '总公司部门', 'boss', '13800138000', 3, 0, 0, 1648696695, 1656303298, 0);
INSERT INTO `ls_system_admin`(`id`, `username`, `nickname`, `password`, `avatar`, `role`, `salt`, `sort`, `is_multipoint`, `is_disable`, `is_delete`, `last_login_ip`, `last_login_time`, `create_time`, `update_time`, `delete_time`) VALUES (1, 'admin', 'admin', 'c507c81efc8c2fbd3b68bf66b1c5eaa3', '', '0', 'YisBi', 0, 0, 0, 0, '127.0.0.1', 1652336423, 1642321599, 1651819202, 0);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
