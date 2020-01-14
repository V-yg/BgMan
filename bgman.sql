

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dept
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `dept_no` varchar(18) DEFAULT NULL COMMENT '部门编号(规则：父级关系编码+自己的编码)',
  `name` varchar(300) DEFAULT NULL COMMENT '部门名称',
  `pid` varchar(64) NOT NULL COMMENT '父级id',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态(1:正常；0:弃用)',
  `relation_code` varchar(3000) DEFAULT NULL COMMENT '为了维护更深层级关系',
  `dept_manager_id` varchar(64) DEFAULT NULL COMMENT '部门经理user_id',
  `manager_name` varchar(255) DEFAULT NULL COMMENT '部门经理名称',
  `phone` varchar(20) DEFAULT NULL COMMENT '部门经理联系电话',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(4) DEFAULT '1' COMMENT '是否删除(1未删除；0已删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dept
-- ----------------------------
INSERT INTO `dept` VALUES ('34978a55-6fd5-4716-bea5-478c7dd2f05a', 'YXD000006', '前端开发一组', 'eaa77595-e16d-4f80-9769-7720c3ea147b', '1', 'YXD000005YXD000004YXD000001YXD000005YXD000006', null, '王伟', '15523154782', '2020-01-04 16:52:26', null, '1');
INSERT INTO `dept` VALUES ('52ee944b-08e2-4fd1-bf11-254b89b31821', 'YXD000008', '微服务开发部', 'f383e39d-b4a3-4994-9c35-439d74e0ed5a', '1', 'YXD000005YXD000004YXD000002YXD000008', null, '张可', '18821485689', '2020-01-04 16:53:54', null, '1');
INSERT INTO `dept` VALUES ('72a4f388-50f8-4019-8c67-530cd7c74e7a', 'YXD000005', '上海总部', '0', '1', 'YXD000005YXD000004', null, '王一钢', '15503679153', '2019-11-07 22:43:33', '2020-01-05 16:48:06', '1');
INSERT INTO `dept` VALUES ('857c5c62-1544-4229-b903-31db62c39143', 'YXD000004', '人事部', '931bebd9-259e-43f0-a5a2-a3b7b3403dae', '1', 'YXD000005YXD000004YXD000001YXD000004', null, '李凯', '13642145685', '2020-01-04 16:50:58', null, '1');
INSERT INTO `dept` VALUES ('931bebd9-259e-43f0-a5a2-a3b7b3403dae', 'YXD000001', '北京分公司', '72a4f388-50f8-4019-8c67-530cd7c74e7a', '1', 'YXD000005YXD000004YXD000001', null, '王嫦嫔', '15503687580', '2020-01-04 16:47:57', null, '1');
INSERT INTO `dept` VALUES ('9def8c9e-0cf4-40cd-9921-5b7f86a7ae3f', 'YXD000003', '大数据部', '931bebd9-259e-43f0-a5a2-a3b7b3403dae', '0', 'YXD000005YXD000004YXD000001YXD000003', null, '李玉', '13561457823', '2020-01-04 16:50:02', '2020-01-05 16:50:30', '1');
INSERT INTO `dept` VALUES ('e8d8c149-6548-48a4-8a72-7a7f46f3c9b2', 'YXD000007', '前端开发一组', 'eaa77595-e16d-4f80-9769-7720c3ea147b', '1', 'YXD000005YXD000004YXD000001YXD000005YXD000007', null, '王伟', '15523154782', '2020-01-04 16:52:27', '2020-01-04 16:52:34', '0');
INSERT INTO `dept` VALUES ('eaa77595-e16d-4f80-9769-7720c3ea147b', 'YXD000005', '前端开发部', '931bebd9-259e-43f0-a5a2-a3b7b3403dae', '1', 'YXD000005YXD000004YXD000001YXD000005', null, '杨丽', '13524512563', '2020-01-04 16:51:57', null, '1');
INSERT INTO `dept` VALUES ('f383e39d-b4a3-4994-9c35-439d74e0ed5a', 'YXD000002', '山西分公司', '72a4f388-50f8-4019-8c67-530cd7c74e7a', '1', 'YXD000005YXD000004YXD000002', null, '王帕维', '13524597526', '2020-01-04 16:49:27', null, '1');

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `id` varchar(64) NOT NULL,
  `user_id` varchar(64) DEFAULT NULL COMMENT '用户id',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) DEFAULT NULL COMMENT '用户操作',
  `time` int(11) DEFAULT NULL COMMENT '响应时间',
  `method` varchar(200) DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) DEFAULT NULL COMMENT '请求参数',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `location` varchar(255) DEFAULT NULL COMMENT '操作地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统日志';

-
-- ----------------------------
-- Table structure for loginlog
-- ----------------------------
DROP TABLE IF EXISTS `loginlog`;
CREATE TABLE `loginlog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `login_time` datetime DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `system` varchar(255) DEFAULT NULL,
  `browser` varchar(255) DEFAULT NULL,
  `status` int(255) DEFAULT NULL,
  `msg` varchar(255) DEFAULT NULL,
  `code` int(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=134 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of loginlog
-- ----------------------------------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `code` varchar(64) DEFAULT NULL COMMENT '菜单权限编码',
  `name` varchar(300) DEFAULT NULL COMMENT '菜单权限名称',
  `icon` varchar(50) DEFAULT NULL,
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：sys:user:add,sys:user:edit)',
  `url` varchar(100) DEFAULT NULL COMMENT '访问地址URL',
  `method` varchar(10) DEFAULT NULL COMMENT '资源请求类型',
  `pid` varchar(64) DEFAULT NULL COMMENT '父级菜单权限名称',
  `order_num` int(11) DEFAULT '0' COMMENT '排序',
  `type` tinyint(4) DEFAULT NULL COMMENT '菜单权限类型(1:目录;2:菜单;3:按钮)',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态1:正常 0：禁用',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(4) DEFAULT '1' COMMENT '是否删除(1未删除；0已删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('0d99b687-3f46-4632-9d56-8dd5e476dae7', '', 'SQL 监控', 'layui-icon-table', '', '/druid/sql.html', 'GET', 'e549c4b8-72ca-4ba3-91a8-9ffa1daf77cf', '98', '2', '1', '2019-11-09 20:58:23', '2020-01-07 00:35:39', '1');
INSERT INTO `permission` VALUES ('1a2ec857-e775-4377-9fb7-e3c77738b3e5', 'btn-role-add', '新增', null, 'sys:role:add', '/sys/role', 'POST', 'e0b16b95-09de-4d60-a283-1eebd424ed47', '0', '3', '1', '2019-09-22 16:00:59', null, '1');
INSERT INTO `permission` VALUES ('2073345f-7344-43fe-9084-b7add56da652', 'btn-dept-deleted', '删除', null, 'sys:dept:deleted', '/sys/dept/*', 'DELETED', 'c038dc93-f30d-4802-a090-be352eab341a', '100', '3', '1', '2019-11-09 20:49:59', null, '1');
INSERT INTO `permission` VALUES ('26764d88-1d90-402d-b355-a75deef116f2', '', '接口管理', 'layui-icon-engine', '', '/swagger-ui.html', 'GET', 'e549c4b8-72ca-4ba3-91a8-9ffa1daf77cf', '100', '2', '1', '2019-11-09 20:56:37', '2020-01-07 00:35:51', '1');
INSERT INTO `permission` VALUES ('26e66825-5ca9-4470-b7dc-9e710b2563ef', 'btn-user-list', '列表', null, 'sys:user:list', '/sys/users', 'POST', '78f8e29a-cccd-49e5-ada7-5af40dd95312', '89', '3', '1', '2020-01-01 19:31:56', '2020-01-01 19:43:33', '0');
INSERT INTO `permission` VALUES ('355f387f-a22b-4f8c-9cd6-ae10e930cd70', 'btn-logs-list', '列表', null, 'sys:log:list', '/sys/logs', 'POST', '37101ed5-e840-4082-ae33-682ca6e41ad8', '100', '3', '1', '2019-11-09 21:00:49', '2019-11-09 21:02:08', '1');
INSERT INTO `permission` VALUES ('37101ed5-e840-4082-ae33-682ca6e41ad8', '', '操作日志', 'layui-icon-about', '', '/index/logs', 'GET', 'e549c4b8-72ca-4ba3-91a8-9ffa1daf77cf', '100', '2', '1', '2019-11-09 20:59:09', '2020-01-07 00:36:06', '1');
INSERT INTO `permission` VALUES ('3a93a7e3-956a-408e-b2e4-108e9ece8f04', 'btn-dept-add', '新增', null, 'sys:dept:add', '/sys/dept', 'POST', 'c038dc93-f30d-4802-a090-be352eab341a', '100', '3', '1', '2019-11-07 22:42:49', '2019-11-09 20:51:08', '1');
INSERT INTO `permission` VALUES ('3c390dfd-0d9a-46de-9a5b-1ed884febcb2', 'btn-user-role-update', '更改权限', null, 'sys:user:role:update', '/sys/user/roles/*', 'POST', '78f8e29a-cccd-49e5-ada7-5af40dd95312', '100', '3', '1', '2019-11-09 20:39:09', null, '1');
INSERT INTO `permission` VALUES ('3dac936c-c4e1-4560-ac93-905502f61ae0', null, '菜单权限管理', 'layui-icon-note', '', '/index/menus', 'GET', 'd6214dcb-8b6d-494b-88fa-f519fc08ff8f', '98', '2', '1', '2019-09-22 15:18:12', '2020-01-07 00:29:35', '1');
INSERT INTO `permission` VALUES ('3ed79f23-90bf-4669-bc02-42ae392e75c1', 'btn-dept-list', '列表', null, 'sys:dept:list', '/sys/depts', 'POST', 'c038dc93-f30d-4802-a090-be352eab341a', '100', '3', '1', '2019-11-07 22:38:34', '2019-11-09 20:51:18', '1');
INSERT INTO `permission` VALUES ('4018e179-e599-41d0-bac5-c5408e1d4bc6', 'btn-role-deleted', '删除', null, 'sys:role:deleted', '/sys/role/*', 'DELETED', 'e0b16b95-09de-4d60-a283-1eebd424ed47', '100', '3', '1', '2019-11-09 20:54:28', null, '1');
INSERT INTO `permission` VALUES ('4405d359-43b5-405d-ad1a-148001766359', '', 'waw', '', '', 'dawdawda', '', '0', '22', '1', '1', '2020-01-06 21:15:27', '2020-01-06 21:23:27', '0');
INSERT INTO `permission` VALUES ('475b4c24-40fa-4823-863a-ba6d793b7610', 'btn-permission-detail', '详情', null, 'sys:permission:detail', '/sys/permission/*', 'GET', '3dac936c-c4e1-4560-ac93-905502f61ae0', '100', '3', '1', '2019-11-09 20:43:05', null, '1');
INSERT INTO `permission` VALUES ('58612968-d93c-4c21-8fdc-a825c0ab0275', 'btn-role-list', '列表', null, 'sys:role:list', '/sys/roles', 'POST', 'e0b16b95-09de-4d60-a283-1eebd424ed47', '0', '3', '1', '2019-09-22 16:04:33', null, '1');
INSERT INTO `permission` VALUES ('761db494-833d-4a6c-94b4-3a7409fd9a78', 'btn-dept-detail', '详情', null, 'sys:dept:detail', '/sys/dept/*', 'GET', 'c038dc93-f30d-4802-a090-be352eab341a', '100', '3', '1', '2019-11-09 20:50:53', null, '1');
INSERT INTO `permission` VALUES ('783aedd8-5d93-46b6-8c6d-e4d3f0f3f466', 'btn-user-list', '列表', null, 'sys:user:list', '/sys/users', 'POST', '78f8e29a-cccd-49e5-ada7-5af40dd95312', '100', '3', '1', '2020-01-01 19:44:37', null, '1');
INSERT INTO `permission` VALUES ('78f8e29a-cccd-49e5-ada7-5af40dd95312', '', '用户管理', 'layui-icon-vercode', '', '/index/users', 'GET', 'd6214dcb-8b6d-494b-88fa-f519fc08ff8f', '100', '2', '1', '2020-01-01 19:30:30', '2019-11-09 20:48:29', '1');
INSERT INTO `permission` VALUES ('817a58d1-ec82-4106-870a-bcc0bfaee0e7', 'btn-user-detail', '详情', null, 'sys:user:detail', '/sys/user/*', 'GET', '78f8e29a-cccd-49e5-ada7-5af40dd95312', '100', '3', '1', '2019-11-09 20:24:24', '2019-11-09 20:48:05', '1');
INSERT INTO `permission` VALUES ('8623c941-5746-4667-9fb8-76f6f5059788', 'btn-permission-deleted', '删除', null, 'sys:permission:deleted', '/sys/permission/*', 'DELETED', '3dac936c-c4e1-4560-ac93-905502f61ae0', '100', '3', '1', '2019-11-07 22:35:50', '2019-11-09 20:44:44', '1');
INSERT INTO `permission` VALUES ('992d1a8d-b5f8-44fc-9a48-4b3e60a7b15e', 'btn-role-update', '更新', null, 'sys:role:update', '/sys/role', 'PUT', 'e0b16b95-09de-4d60-a283-1eebd424ed47', '0', '3', '1', '2019-09-22 16:03:46', null, '1');
INSERT INTO `permission` VALUES ('99c12035-32de-4aa1-aa0c-de4d846c9edc', '', 'aaa', '', '', 'daw', '', '0', '100', '1', '1', '2020-01-06 21:14:08', '2020-01-06 21:23:33', '0');
INSERT INTO `permission` VALUES ('a137e93a-32df-4796-8a88-abfc52fbf4d1', '', 'dad', 'layui-icon-rate-solid', '', 'dad', '', '0', '100', '1', '1', '2020-01-07 00:33:39', '2020-01-07 00:33:59', '0');
INSERT INTO `permission` VALUES ('a31acb33-9412-4ada-bbdf-0138c007e6db', '', '删除', null, 'sys:loginlog:deleted', 'sys/loginlogs', 'DELETED', 'b545d509-9a27-47b7-9c24-7bd2bd540229', '100', '3', '1', '2020-01-05 15:11:13', null, '1');
INSERT INTO `permission` VALUES ('a390845b-a53d-4bc9-af5d-331c37f34e6f', 'btn-dept-update', '更新', null, 'sys:dept:update', '/sys/dept', 'PUT', 'c038dc93-f30d-4802-a090-be352eab341a', '100', '3', '1', '2019-11-09 20:53:16', null, '1');
INSERT INTO `permission` VALUES ('b01614ab-0538-4cca-bb61-b46f18c60aa4', 'btn-role-detail', '详情', null, 'sys:role:detail', '/sys/role/*', 'GET', 'e0b16b95-09de-4d60-a283-1eebd424ed47', '100', '3', '1', '2019-09-22 16:06:13', '2019-11-09 20:55:08', '1');
INSERT INTO `permission` VALUES ('b180aafa-0d1a-4898-b838-bc20cd44356d', null, '编辑', null, 'sys:permission:update', '/sys/permission', 'PUT', '3dac936c-c4e1-4560-ac93-905502f61ae0', '100', '3', '1', '2019-11-07 22:27:22', '2019-11-09 20:48:44', '1');
INSERT INTO `permission` VALUES ('b545d509-9a27-47b7-9c24-7bd2bd540229', '', '登录日志', 'layui-icon-group', '', '/index/loginlogs', 'GET', 'e549c4b8-72ca-4ba3-91a8-9ffa1daf77cf', '100', '2', '1', '2020-01-05 15:05:03', '2020-01-07 00:36:21', '1');
INSERT INTO `permission` VALUES ('c038dc93-f30d-4802-a090-be352eab341a', '', '部门管理', 'layui-icon-theme', '', '/index/depts', 'GET', 'd6214dcb-8b6d-494b-88fa-f519fc08ff8f', '100', '2', '1', '2019-11-07 22:37:20', '2020-01-07 00:34:58', '1');
INSERT INTO `permission` VALUES ('c09221bd-35ef-4a63-bd92-018c7fa218ad', '', '列表', null, 'sys:loginlog:list', '/sys/loginlogs', 'POST', 'b545d509-9a27-47b7-9c24-7bd2bd540229', '100', '3', '1', '2020-01-05 15:07:42', null, '1');
INSERT INTO `permission` VALUES ('c0a84726-47d8-4d7a-8d53-0736a4586647', 'btn-user-add', '新增', null, 'sys:user:add', '/sys/user', 'POST', '78f8e29a-cccd-49e5-ada7-5af40dd95312', '100', '3', '1', '2019-11-09 20:25:18', null, '1');
INSERT INTO `permission` VALUES ('c30389e8-eb3e-4a0d-99c4-639e1893a05f', 'btn-permission-list', '列表', null, 'sys:permission:list', '/sys/permissions', 'POST', '3dac936c-c4e1-4560-ac93-905502f61ae0', '100', '3', '1', '2019-09-22 15:26:45', '2019-11-09 20:45:19', '1');
INSERT INTO `permission` VALUES ('c30389e8-eb3e-4a0d-99c4-639e1893f50a', 'btn-permission-list', '新增', null, 'sys:permission:add', '/sys/permission', 'POST', '3dac936c-c4e1-4560-ac93-905502f61ae0', '100', '3', '1', '2019-09-22 15:26:45', '2019-11-09 20:45:25', '1');
INSERT INTO `permission` VALUES ('d6214dcb-8b6d-494b-88fa-f519fc08ff8f', null, '组织管理', '', '', '', '', '0', '100', '1', '1', '2019-09-28 15:16:14', '2020-01-08 14:19:04', '1');
INSERT INTO `permission` VALUES ('db2d31b7-fdcb-478e-bfde-a55eb8b0aa43', 'btn-user-role-detail', '授权', null, 'sys:user:role:detail', '/sys/user/roles/*', 'GET', '78f8e29a-cccd-49e5-ada7-5af40dd95312', '100', '3', '1', '2019-11-09 20:29:47', null, '1');
INSERT INTO `permission` VALUES ('e0b16b95-09de-4d60-a283-1eebd424ed47', '', '角色管理', 'layui-icon-tree', '', '/index/roles', 'GET', 'd6214dcb-8b6d-494b-88fa-f519fc08ff8f', '99', '2', '1', '2019-09-22 15:45:45', '2020-01-07 00:35:21', '1');
INSERT INTO `permission` VALUES ('e549c4b8-72ca-4ba3-91a8-9ffa1daf77cf', '', '系统管理', '', '', '', '', '0', '98', '1', '1', '2019-11-09 20:56:01', '2020-01-08 14:19:17', '1');
INSERT INTO `permission` VALUES ('f21ed5e8-0756-45dc-91c5-f58a9463caaa', 'btn-user-update', '更新', null, 'sys:user:update', '/sys/user', 'PUT', '78f8e29a-cccd-49e5-ada7-5af40dd95312', '100', '3', '1', '2019-11-09 20:23:20', null, '1');
INSERT INTO `permission` VALUES ('f28b9215-3119-482d-bdc1-1f4c3f7c0869', 'btn-user-deleted', '删除', null, 'sys:user:deleted', '/sys/user', 'DELETED', '78f8e29a-cccd-49e5-ada7-5af40dd95312', '100', '3', '1', '2019-11-09 20:26:45', null, '1');
INSERT INTO `permission` VALUES ('f2ff9320-c643-4c85-8b68-15f86d47b88b', 'btn-log-deleted', '删除', null, 'sys:log:deleted', '/sys/logs', 'DELETED', '37101ed5-e840-4082-ae33-682ca6e41ad8', '100', '3', '1', '2019-11-09 21:01:49', null, '1');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `description` varchar(300) DEFAULT NULL,
  `status` tinyint(4) DEFAULT '1' COMMENT '状态(1:正常0:弃用)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(4) DEFAULT '1' COMMENT '是否删除(1未删除；0已删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('11b3b80c-4a0b-4a92-96ea-fdd4f7a4a7e9', '超级管理员', '拥有所有权限-不能删除', '1', '2019-11-01 19:26:29', '2020-01-05 16:06:38', '1');
INSERT INTO `role` VALUES ('12856224-701c-40e7-995f-4699305ea831', '人事部管理员', '拥有用户权限的所有权限', '1', '2020-01-04 16:58:48', '2020-01-04 21:19:39', '1');
INSERT INTO `role` VALUES ('b95c69b7-84be-430f-ae57-27a703ae3998', '后台管理员', '拥有全部权限', '1', '2019-11-09 21:25:31', '2020-01-04 16:56:37', '1');
INSERT INTO `role` VALUES ('ba6e1f7e-20d8-453c-a63b-841093bd402c', '大数据部主管', '拥有部分权限', '1', '2020-01-04 17:00:19', '2020-01-06 11:57:34', '1');
INSERT INTO `role` VALUES ('cdd7105c-694c-43e8-afd1-32c29250bb9e', '访客特殊权限', '访客特殊权限', '1', '2020-01-07 17:55:28', '2020-01-07 21:05:24', '1');
INSERT INTO `role` VALUES ('d689cf27-137f-41a1-bc68-73045f7ff29b', '总部管理员', '拥有部分权限，没有删除权限', '1', '2020-01-04 16:57:32', '2020-01-06 00:04:41', '1');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `role_id` varchar(64) DEFAULT NULL COMMENT '角色id',
  `permission_id` varchar(64) DEFAULT NULL COMMENT '菜单权限id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('00b4f237-2288-4b4c-bfa7-325c1d496ec9', 'b95c69b7-84be-430f-ae57-27a703ae3998', 'c038dc93-f30d-4802-a090-be352eab341a', '2020-01-04 16:56:37');
INSERT INTO `role_permission` VALUES ('00ef1de5-6973-4db5-b794-72a8ec54ca52', 'ba6e1f7e-20d8-453c-a63b-841093bd402c', '1a2ec857-e775-4377-9fb7-e3c77738b3e5', '2020-01-06 11:57:34');
INSERT INTO `role_permission` VALUES ('04353373-3bb9-4f6e-b2b7-7484a2a1c13f', 'cdd7105c-694c-43e8-afd1-32c29250bb9e', 'e549c4b8-72ca-4ba3-91a8-9ffa1daf77cf', '2020-01-07 21:05:24');
INSERT INTO `role_permission` VALUES ('05c17fc2-d23e-4392-89df-aeec3b0f6038', 'd689cf27-137f-41a1-bc68-73045f7ff29b', '761db494-833d-4a6c-94b4-3a7409fd9a78', '2020-01-06 00:04:42');
INSERT INTO `role_permission` VALUES ('09d33bf5-0c42-402c-acd5-a5d2ef79260c', '11b3b80c-4a0b-4a92-96ea-fdd4f7a4a7e9', '0d99b687-3f46-4632-9d56-8dd5e476dae7', '2020-01-05 16:06:38');
INSERT INTO `role_permission` VALUES ('0a01a47b-9431-4095-9998-e6385e7f2c2d', '11b3b80c-4a0b-4a92-96ea-fdd4f7a4a7e9', 'f2ff9320-c643-4c85-8b68-15f86d47b88b', '2020-01-05 16:06:38');
INSERT INTO `role_permission` VALUES ('0e688274-345b-4dfe-a686-d307f8f6860b', '11b3b80c-4a0b-4a92-96ea-fdd4f7a4a7e9', 'a390845b-a53d-4bc9-af5d-331c37f34e6f', '2020-01-05 16:06:38');
INSERT INTO `role_permission` VALUES ('0eee48dd-d492-47f2-9172-7c35da5825ce', 'ba6e1f7e-20d8-453c-a63b-841093bd402c', '992d1a8d-b5f8-44fc-9a48-4b3e60a7b15e', '2020-01-06 11:57:34');
INSERT INTO `role_permission` VALUES ('0f55a91e-1229-4e63-9b39-54b0095f0927', '11b3b80c-4a0b-4a92-96ea-fdd4f7a4a7e9', 'db2d31b7-fdcb-478e-bfde-a55eb8b0aa43', '2020-01-05 16:06:38');
INSERT INTO `role_permission` VALUES ('0f631a26-0f0b-4247-ab0b-37ec82f6e183', 'b95c69b7-84be-430f-ae57-27a703ae3998', '783aedd8-5d93-46b6-8c6d-e4d3f0f3f466', '2020-01-04 16:56:37');
INSERT INTO `role_permission` VALUES ('1015508d-a568-4d7a-ab44-253e302d4be7', 'd689cf27-137f-41a1-bc68-73045f7ff29b', '78f8e29a-cccd-49e5-ada7-5af40dd95312', '2020-01-06 00:04:42');
INSERT INTO `role_permission` VALUES ('1028f650-9b7e-43b5-b0c8-e008c97edd68', 'd689cf27-137f-41a1-bc68-73045f7ff29b', 'a390845b-a53d-4bc9-af5d-331c37f34e6f', '2020-01-06 00:04:42');
INSERT INTO `role_permission` VALUES ('107af9b9-b260-417b-b6fd-c273c5dd58a7', 'b95c69b7-84be-430f-ae57-27a703ae3998', '761db494-833d-4a6c-94b4-3a7409fd9a78', '2020-01-04 16:56:37');
INSERT INTO `role_permission` VALUES ('1531412c-5aaf-4f7e-9716-d2b17200971b', '12856224-701c-40e7-995f-4699305ea831', 'c0a84726-47d8-4d7a-8d53-0736a4586647', '2020-01-04 21:19:39');
INSERT INTO `role_permission` VALUES ('15de3aaf-8989-412e-965c-a3d6ac0c53d9', 'b95c69b7-84be-430f-ae57-27a703ae3998', '3a93a7e3-956a-408e-b2e4-108e9ece8f04', '2020-01-04 16:56:37');
INSERT INTO `role_permission` VALUES ('1874fc8a-30bc-4ca5-aefe-09e828c1301d', 'd689cf27-137f-41a1-bc68-73045f7ff29b', 'f28b9215-3119-482d-bdc1-1f4c3f7c0869', '2020-01-06 00:04:42');
INSERT INTO `role_permission` VALUES ('1a6bbbc5-d1cc-4d57-b948-4166d6c0575c', 'b95c69b7-84be-430f-ae57-27a703ae3998', '26764d88-1d90-402d-b355-a75deef116f2', '2020-01-04 16:56:37');
INSERT INTO `role_permission` VALUES ('1a93b4d1-c894-4c65-be2c-bfa3213614c0', 'b95c69b7-84be-430f-ae57-27a703ae3998', '817a58d1-ec82-4106-870a-bcc0bfaee0e7', '2020-01-04 16:56:37');
INSERT INTO `role_permission` VALUES ('1abaa246-a46d-4335-8e6f-f4288f080f4c', 'd689cf27-137f-41a1-bc68-73045f7ff29b', 'c038dc93-f30d-4802-a090-be352eab341a', '2020-01-06 00:04:42');
INSERT INTO `role_permission` VALUES ('23d4500d-4c70-46d2-83b2-773090d2ecf1', '12856224-701c-40e7-995f-4699305ea831', 'f21ed5e8-0756-45dc-91c5-f58a9463caaa', '2020-01-04 21:19:39');
INSERT INTO `role_permission` VALUES ('23e86e51-958d-4bf0-9c81-6ba99dd537f4', 'cdd7105c-694c-43e8-afd1-32c29250bb9e', '3ed79f23-90bf-4669-bc02-42ae392e75c1', '2020-01-07 21:05:24');
INSERT INTO `role_permission` VALUES ('25d1e005-37aa-43f1-9f93-c63c761f809d', 'd689cf27-137f-41a1-bc68-73045f7ff29b', 'c0a84726-47d8-4d7a-8d53-0736a4586647', '2020-01-06 00:04:42');
INSERT INTO `role_permission` VALUES ('28e3065c-d61e-451c-b4b6-670b14e88f80', 'cdd7105c-694c-43e8-afd1-32c29250bb9e', 'c09221bd-35ef-4a63-bd92-018c7fa218ad', '2020-01-07 21:05:24');
INSERT INTO `role_permission` VALUES ('2a7e5f8c-9721-4d2c-84fa-2378c87b3edb', 'ba6e1f7e-20d8-453c-a63b-841093bd402c', '26764d88-1d90-402d-b355-a75deef116f2', '2020-01-06 11:57:34');
INSERT INTO `role_permission` VALUES ('2bc4233c-3dde-4199-945e-b4f04eb80653', '11b3b80c-4a0b-4a92-96ea-fdd4f7a4a7e9', '817a58d1-ec82-4106-870a-bcc0bfaee0e7', '2020-01-05 16:06:38');
INSERT INTO `role_permission` VALUES ('2c4545a1-aa22-42cc-9eec-e9676ee6854f', 'cdd7105c-694c-43e8-afd1-32c29250bb9e', '355f387f-a22b-4f8c-9cd6-ae10e930cd70', '2020-01-07 21:05:24');
INSERT INTO `role_permission` VALUES ('2f031461-ddea-4c90-897e-a9b3b7b2f360', '11b3b80c-4a0b-4a92-96ea-fdd4f7a4a7e9', '475b4c24-40fa-4823-863a-ba6d793b7610', '2020-01-05 16:06:38');
INSERT INTO `role_permission` VALUES ('2fa5a60f-6224-45ca-8fcc-2eae963b4ec3', '11b3b80c-4a0b-4a92-96ea-fdd4f7a4a7e9', '2073345f-7344-43fe-9084-b7add56da652', '2020-01-05 16:06:38');
INSERT INTO `role_permission` VALUES ('3097fab7-f8d6-4c2b-965e-018f862e972a', 'ba6e1f7e-20d8-453c-a63b-841093bd402c', '37101ed5-e840-4082-ae33-682ca6e41ad8', '2020-01-06 11:57:34');
INSERT INTO `role_permission` VALUES ('30f22014-1a36-44a7-b21e-9097acbb70a6', 'ba6e1f7e-20d8-453c-a63b-841093bd402c', '355f387f-a22b-4f8c-9cd6-ae10e930cd70', '2020-01-06 11:57:34');
INSERT INTO `role_permission` VALUES ('31771337-1bc3-468d-8529-a8a27d7e5a90', '11b3b80c-4a0b-4a92-96ea-fdd4f7a4a7e9', '783aedd8-5d93-46b6-8c6d-e4d3f0f3f466', '2020-01-05 16:06:38');
INSERT INTO `role_permission` VALUES ('31afb11b-50bd-4032-a025-40dcecc1e803', 'd689cf27-137f-41a1-bc68-73045f7ff29b', 'e0b16b95-09de-4d60-a283-1eebd424ed47', '2020-01-06 00:04:42');
INSERT INTO `role_permission` VALUES ('3213175c-cc96-4a3f-a1fa-653c30e536cf', 'b95c69b7-84be-430f-ae57-27a703ae3998', '58612968-d93c-4c21-8fdc-a825c0ab0275', '2020-01-04 16:56:37');
INSERT INTO `role_permission` VALUES ('342c51a2-4540-4166-813c-22df7b2c0584', '11b3b80c-4a0b-4a92-96ea-fdd4f7a4a7e9', 'c30389e8-eb3e-4a0d-99c4-639e1893a05f', '2020-01-05 16:06:38');
INSERT INTO `role_permission` VALUES ('346f1ad7-6e48-45bc-890c-0a0aec5cb0f0', '11b3b80c-4a0b-4a92-96ea-fdd4f7a4a7e9', '37101ed5-e840-4082-ae33-682ca6e41ad8', '2020-01-05 16:06:38');
INSERT INTO `role_permission` VALUES ('3590d1ff-5d44-400b-bd48-44cfc64b7b65', 'b95c69b7-84be-430f-ae57-27a703ae3998', '475b4c24-40fa-4823-863a-ba6d793b7610', '2020-01-04 16:56:37');
INSERT INTO `role_permission` VALUES ('368082ee-c75e-4fe9-b8b1-c4d5a5fe66a4', 'ba6e1f7e-20d8-453c-a63b-841093bd402c', 'a390845b-a53d-4bc9-af5d-331c37f34e6f', '2020-01-06 11:57:34');
INSERT INTO `role_permission` VALUES ('3d983836-4256-410c-82f9-818601c2c658', '12856224-701c-40e7-995f-4699305ea831', '817a58d1-ec82-4106-870a-bcc0bfaee0e7', '2020-01-04 21:19:39');
INSERT INTO `role_permission` VALUES ('3dc19344-e4e9-44d2-b7c1-bd051bd268ff', '11b3b80c-4a0b-4a92-96ea-fdd4f7a4a7e9', '78f8e29a-cccd-49e5-ada7-5af40dd95312', '2020-01-05 16:06:38');
INSERT INTO `role_permission` VALUES ('4048b1c2-c588-4e3d-8dc9-601d9457a7fd', '11b3b80c-4a0b-4a92-96ea-fdd4f7a4a7e9', 'b01614ab-0538-4cca-bb61-b46f18c60aa4', '2020-01-05 16:06:38');
INSERT INTO `role_permission` VALUES ('410239e7-0e9d-4c74-a077-f08932d50301', '11b3b80c-4a0b-4a92-96ea-fdd4f7a4a7e9', '26764d88-1d90-402d-b355-a75deef116f2', '2020-01-05 16:06:38');
INSERT INTO `role_permission` VALUES ('42c5ba49-a30c-402d-821d-195a297e8dfc', 'ba6e1f7e-20d8-453c-a63b-841093bd402c', '817a58d1-ec82-4106-870a-bcc0bfaee0e7', '2020-01-06 11:57:34');
INSERT INTO `role_permission` VALUES ('46a4e6f4-18ca-460c-a0c0-53d027e46fec', 'd689cf27-137f-41a1-bc68-73045f7ff29b', '3a93a7e3-956a-408e-b2e4-108e9ece8f04', '2020-01-06 00:04:42');
INSERT INTO `role_permission` VALUES ('49608674-c930-4849-8d4c-17df5e5dbfc6', 'ba6e1f7e-20d8-453c-a63b-841093bd402c', '8623c941-5746-4667-9fb8-76f6f5059788', '2020-01-06 11:57:34');
INSERT INTO `role_permission` VALUES ('4a26deec-b460-4f84-a3f0-8add81be585d', 'ba6e1f7e-20d8-453c-a63b-841093bd402c', '58612968-d93c-4c21-8fdc-a825c0ab0275', '2020-01-06 11:57:34');
INSERT INTO `role_permission` VALUES ('4bfd0f45-1cd0-457a-b708-79509b92c748', '11b3b80c-4a0b-4a92-96ea-fdd4f7a4a7e9', '355f387f-a22b-4f8c-9cd6-ae10e930cd70', '2020-01-05 16:06:38');
INSERT INTO `role_permission` VALUES ('4d7a7510-1f47-44dc-a731-2212f94b0120', '11b3b80c-4a0b-4a92-96ea-fdd4f7a4a7e9', 'b180aafa-0d1a-4898-b838-bc20cd44356d', '2020-01-05 16:06:38');
INSERT INTO `role_permission` VALUES ('505f6a73-e3d0-43ae-aa5e-1e24c095ab1e', 'd689cf27-137f-41a1-bc68-73045f7ff29b', 'c30389e8-eb3e-4a0d-99c4-639e1893a05f', '2020-01-06 00:04:42');
INSERT INTO `role_permission` VALUES ('54b87ce0-f021-4e59-8718-e62313c7fc2a', 'b95c69b7-84be-430f-ae57-27a703ae3998', 'c30389e8-eb3e-4a0d-99c4-639e1893a05f', '2020-01-04 16:56:37');
INSERT INTO `role_permission` VALUES ('562645f4-ea22-4d0b-af7f-2f122398c525', 'ba6e1f7e-20d8-453c-a63b-841093bd402c', 'a31acb33-9412-4ada-bbdf-0138c007e6db', '2020-01-06 11:57:34');
INSERT INTO `role_permission` VALUES ('58081edb-8d49-4ad1-91ff-a201d25e4afd', 'cdd7105c-694c-43e8-afd1-32c29250bb9e', '475b4c24-40fa-4823-863a-ba6d793b7610', '2020-01-07 21:05:24');
INSERT INTO `role_permission` VALUES ('590a201f-fca6-4372-8a85-bfc94da1f245', 'b95c69b7-84be-430f-ae57-27a703ae3998', 'c30389e8-eb3e-4a0d-99c4-639e1893f50a', '2020-01-04 16:56:37');
INSERT INTO `role_permission` VALUES ('595fe668-f118-410e-832d-1ddabc399615', 'd689cf27-137f-41a1-bc68-73045f7ff29b', '3c390dfd-0d9a-46de-9a5b-1ed884febcb2', '2020-01-06 00:04:42');
INSERT INTO `role_permission` VALUES ('597f9a97-7d91-4783-a559-823bb7bae391', 'b95c69b7-84be-430f-ae57-27a703ae3998', '992d1a8d-b5f8-44fc-9a48-4b3e60a7b15e', '2020-01-04 16:56:37');
INSERT INTO `role_permission` VALUES ('5e78b460-3bff-4e22-915e-db62a7e41d3b', 'ba6e1f7e-20d8-453c-a63b-841093bd402c', 'e0b16b95-09de-4d60-a283-1eebd424ed47', '2020-01-06 11:57:34');
INSERT INTO `role_permission` VALUES ('5f06b1e1-ba07-482b-839c-250779b9ad42', '12856224-701c-40e7-995f-4699305ea831', '783aedd8-5d93-46b6-8c6d-e4d3f0f3f466', '2020-01-04 21:19:39');
INSERT INTO `role_permission` VALUES ('60423f46-61b9-426c-8616-ac52f8c858a7', 'cdd7105c-694c-43e8-afd1-32c29250bb9e', 'c038dc93-f30d-4802-a090-be352eab341a', '2020-01-07 21:05:24');
INSERT INTO `role_permission` VALUES ('606182f0-8a1a-4c2e-a0e4-bc4fd5f6adcd', 'ba6e1f7e-20d8-453c-a63b-841093bd402c', 'c09221bd-35ef-4a63-bd92-018c7fa218ad', '2020-01-06 11:57:34');
INSERT INTO `role_permission` VALUES ('6256a673-bf46-4007-bc14-b96fbce32604', 'd689cf27-137f-41a1-bc68-73045f7ff29b', '475b4c24-40fa-4823-863a-ba6d793b7610', '2020-01-06 00:04:42');
INSERT INTO `role_permission` VALUES ('647f3abf-1c65-4f5f-b662-61c557f2edf0', 'd689cf27-137f-41a1-bc68-73045f7ff29b', '58612968-d93c-4c21-8fdc-a825c0ab0275', '2020-01-06 00:04:42');
INSERT INTO `role_permission` VALUES ('64fc3235-d62b-4958-91ae-db807ec18141', 'ba6e1f7e-20d8-453c-a63b-841093bd402c', 'b01614ab-0538-4cca-bb61-b46f18c60aa4', '2020-01-06 11:57:34');
INSERT INTO `role_permission` VALUES ('65020d37-f137-43b4-8931-d0a7213a32a4', 'd689cf27-137f-41a1-bc68-73045f7ff29b', '3ed79f23-90bf-4669-bc02-42ae392e75c1', '2020-01-06 00:04:42');
INSERT INTO `role_permission` VALUES ('6769f955-073f-4b37-beeb-eaf881f7fbcd', 'b95c69b7-84be-430f-ae57-27a703ae3998', '3c390dfd-0d9a-46de-9a5b-1ed884febcb2', '2020-01-04 16:56:37');
INSERT INTO `role_permission` VALUES ('685ad030-8a74-4751-b47a-2be1217b9ec9', 'b95c69b7-84be-430f-ae57-27a703ae3998', 'b180aafa-0d1a-4898-b838-bc20cd44356d', '2020-01-04 16:56:37');
INSERT INTO `role_permission` VALUES ('6af8a5d0-b246-43f7-b986-44f738153127', 'ba6e1f7e-20d8-453c-a63b-841093bd402c', '3ed79f23-90bf-4669-bc02-42ae392e75c1', '2020-01-06 11:57:34');
INSERT INTO `role_permission` VALUES ('6b5d894f-2a03-43c8-afe6-d3eca3ed70ee', 'ba6e1f7e-20d8-453c-a63b-841093bd402c', 'f2ff9320-c643-4c85-8b68-15f86d47b88b', '2020-01-06 11:57:34');
INSERT INTO `role_permission` VALUES ('70f1726d-d550-4e6c-9ff3-c3b4d3ddc07c', 'b95c69b7-84be-430f-ae57-27a703ae3998', 'f21ed5e8-0756-45dc-91c5-f58a9463caaa', '2020-01-04 16:56:37');
INSERT INTO `role_permission` VALUES ('71d33899-6f9b-44e0-8b42-aa9fded0511d', 'ba6e1f7e-20d8-453c-a63b-841093bd402c', '3a93a7e3-956a-408e-b2e4-108e9ece8f04', '2020-01-06 11:57:34');
INSERT INTO `role_permission` VALUES ('725d635c-482f-4c18-b7b6-52c3488087bd', 'b95c69b7-84be-430f-ae57-27a703ae3998', 'b01614ab-0538-4cca-bb61-b46f18c60aa4', '2020-01-04 16:56:37');
INSERT INTO `role_permission` VALUES ('72a13d38-2bec-4e0c-acbc-18dc87fec401', 'd689cf27-137f-41a1-bc68-73045f7ff29b', '1a2ec857-e775-4377-9fb7-e3c77738b3e5', '2020-01-06 00:04:42');
INSERT INTO `role_permission` VALUES ('7604dd1c-666e-4de2-90df-21a109152a3d', 'b95c69b7-84be-430f-ae57-27a703ae3998', '37101ed5-e840-4082-ae33-682ca6e41ad8', '2020-01-04 16:56:37');
INSERT INTO `role_permission` VALUES ('7797b728-5a05-4179-af60-d2a00756dc94', 'cdd7105c-694c-43e8-afd1-32c29250bb9e', '78f8e29a-cccd-49e5-ada7-5af40dd95312', '2020-01-07 21:05:24');
INSERT INTO `role_permission` VALUES ('78636983-7583-4d1e-b534-fba64ff05e93', 'b95c69b7-84be-430f-ae57-27a703ae3998', '8623c941-5746-4667-9fb8-76f6f5059788', '2020-01-04 16:56:37');
INSERT INTO `role_permission` VALUES ('78d92c49-9189-42c6-b71c-a5cd7270a557', '11b3b80c-4a0b-4a92-96ea-fdd4f7a4a7e9', '3a93a7e3-956a-408e-b2e4-108e9ece8f04', '2020-01-05 16:06:38');
INSERT INTO `role_permission` VALUES ('7b46e85d-c077-4d4e-8044-fc82cf9b6d6f', '11b3b80c-4a0b-4a92-96ea-fdd4f7a4a7e9', '8623c941-5746-4667-9fb8-76f6f5059788', '2020-01-05 16:06:38');
INSERT INTO `role_permission` VALUES ('7c6a985d-8dbc-43fd-83cb-233d43c3262a', 'd689cf27-137f-41a1-bc68-73045f7ff29b', 'b01614ab-0538-4cca-bb61-b46f18c60aa4', '2020-01-06 00:04:42');
INSERT INTO `role_permission` VALUES ('7ca2c125-0edd-4cb2-b16b-5029d6acce5e', 'b95c69b7-84be-430f-ae57-27a703ae3998', 'e0b16b95-09de-4d60-a283-1eebd424ed47', '2020-01-04 16:56:37');
INSERT INTO `role_permission` VALUES ('7fdfd508-97e3-4c43-9587-e77dbe7b2f91', '11b3b80c-4a0b-4a92-96ea-fdd4f7a4a7e9', '1a2ec857-e775-4377-9fb7-e3c77738b3e5', '2020-01-05 16:06:38');
INSERT INTO `role_permission` VALUES ('800f0c71-bbd1-401f-b56f-7f9bcb9a9c0b', 'ba6e1f7e-20d8-453c-a63b-841093bd402c', 'c30389e8-eb3e-4a0d-99c4-639e1893f50a', '2020-01-06 11:57:34');
INSERT INTO `role_permission` VALUES ('8048ea9f-4c2f-4338-9a85-286a877b616a', '11b3b80c-4a0b-4a92-96ea-fdd4f7a4a7e9', 'f21ed5e8-0756-45dc-91c5-f58a9463caaa', '2020-01-05 16:06:38');
INSERT INTO `role_permission` VALUES ('80a3fc62-5328-4f58-b69d-730be05b9309', 'ba6e1f7e-20d8-453c-a63b-841093bd402c', '4018e179-e599-41d0-bac5-c5408e1d4bc6', '2020-01-06 11:57:34');
INSERT INTO `role_permission` VALUES ('80fb3267-a65f-4047-9d12-f94e1b9e32ee', 'b95c69b7-84be-430f-ae57-27a703ae3998', '3ed79f23-90bf-4669-bc02-42ae392e75c1', '2020-01-04 16:56:37');
INSERT INTO `role_permission` VALUES ('83dc49d7-3399-4c75-8a15-c27f4e8ee6e8', 'd689cf27-137f-41a1-bc68-73045f7ff29b', 'f21ed5e8-0756-45dc-91c5-f58a9463caaa', '2020-01-06 00:04:42');
INSERT INTO `role_permission` VALUES ('8586a035-ea9f-439d-83d5-016a05eab964', 'b95c69b7-84be-430f-ae57-27a703ae3998', 'f2ff9320-c643-4c85-8b68-15f86d47b88b', '2020-01-04 16:56:37');
INSERT INTO `role_permission` VALUES ('891ca3b9-c975-4af4-a6c0-c502edcf8ee5', 'cdd7105c-694c-43e8-afd1-32c29250bb9e', 'db2d31b7-fdcb-478e-bfde-a55eb8b0aa43', '2020-01-07 21:05:24');
INSERT INTO `role_permission` VALUES ('8966f6bb-8624-43ea-94cb-ea0fa8dbaf14', 'ba6e1f7e-20d8-453c-a63b-841093bd402c', 'f28b9215-3119-482d-bdc1-1f4c3f7c0869', '2020-01-06 11:57:34');
INSERT INTO `role_permission` VALUES ('8a2fe5fb-475d-4a72-99ce-2cadb3f6af50', '11b3b80c-4a0b-4a92-96ea-fdd4f7a4a7e9', 'f28b9215-3119-482d-bdc1-1f4c3f7c0869', '2020-01-05 16:06:38');
INSERT INTO `role_permission` VALUES ('8ad523ae-663c-4d0d-b797-a432b6fba5ba', 'cdd7105c-694c-43e8-afd1-32c29250bb9e', '26764d88-1d90-402d-b355-a75deef116f2', '2020-01-07 21:05:24');
INSERT INTO `role_permission` VALUES ('8b045b97-95e5-4b23-9b2f-e63ebc203b00', 'ba6e1f7e-20d8-453c-a63b-841093bd402c', 'c038dc93-f30d-4802-a090-be352eab341a', '2020-01-06 11:57:34');
INSERT INTO `role_permission` VALUES ('8b61dc00-187e-4adf-9216-32d694627b27', '11b3b80c-4a0b-4a92-96ea-fdd4f7a4a7e9', 'e549c4b8-72ca-4ba3-91a8-9ffa1daf77cf', '2020-01-05 16:06:38');
INSERT INTO `role_permission` VALUES ('8be180a0-a860-4b2c-ba2c-8c999b48b920', 'b95c69b7-84be-430f-ae57-27a703ae3998', '355f387f-a22b-4f8c-9cd6-ae10e930cd70', '2020-01-04 16:56:37');
INSERT INTO `role_permission` VALUES ('8cd5c303-a745-4f17-a178-fd04655cd151', 'b95c69b7-84be-430f-ae57-27a703ae3998', 'db2d31b7-fdcb-478e-bfde-a55eb8b0aa43', '2020-01-04 16:56:37');
INSERT INTO `role_permission` VALUES ('8fa9fa58-4e31-4e59-8b00-666b853cf24f', '11b3b80c-4a0b-4a92-96ea-fdd4f7a4a7e9', 'd6214dcb-8b6d-494b-88fa-f519fc08ff8f', '2020-01-05 16:06:38');
INSERT INTO `role_permission` VALUES ('915ec767-c400-46ed-bc70-5980df56d397', '11b3b80c-4a0b-4a92-96ea-fdd4f7a4a7e9', '761db494-833d-4a6c-94b4-3a7409fd9a78', '2020-01-05 16:06:38');
INSERT INTO `role_permission` VALUES ('962eaade-cc7f-4ac7-9278-4ea885643deb', 'b95c69b7-84be-430f-ae57-27a703ae3998', 'e549c4b8-72ca-4ba3-91a8-9ffa1daf77cf', '2020-01-04 16:56:37');
INSERT INTO `role_permission` VALUES ('9f98b606-9aaa-495e-876d-cede77039cfb', 'ba6e1f7e-20d8-453c-a63b-841093bd402c', 'c30389e8-eb3e-4a0d-99c4-639e1893a05f', '2020-01-06 11:57:34');
INSERT INTO `role_permission` VALUES ('a2bcc083-cad7-43e4-a922-ba6d2b586dc3', '11b3b80c-4a0b-4a92-96ea-fdd4f7a4a7e9', '58612968-d93c-4c21-8fdc-a825c0ab0275', '2020-01-05 16:06:38');
INSERT INTO `role_permission` VALUES ('a6fb0b5e-1b5e-40eb-a728-468703513b57', '11b3b80c-4a0b-4a92-96ea-fdd4f7a4a7e9', 'b545d509-9a27-47b7-9c24-7bd2bd540229', '2020-01-05 16:06:38');
INSERT INTO `role_permission` VALUES ('a702e862-d5fb-4ae6-9094-d42359aae365', 'cdd7105c-694c-43e8-afd1-32c29250bb9e', 'b545d509-9a27-47b7-9c24-7bd2bd540229', '2020-01-07 21:05:24');
INSERT INTO `role_permission` VALUES ('a75253ed-1ae8-4397-8b52-01d4ca1215d4', 'd689cf27-137f-41a1-bc68-73045f7ff29b', '783aedd8-5d93-46b6-8c6d-e4d3f0f3f466', '2020-01-06 00:04:42');
INSERT INTO `role_permission` VALUES ('a85cf583-427a-4239-a984-cb0db34a2cd0', 'd689cf27-137f-41a1-bc68-73045f7ff29b', '3dac936c-c4e1-4560-ac93-905502f61ae0', '2020-01-06 00:04:42');
INSERT INTO `role_permission` VALUES ('a8f11e50-3868-4b21-bcdd-1d1df31942ec', 'ba6e1f7e-20d8-453c-a63b-841093bd402c', '3dac936c-c4e1-4560-ac93-905502f61ae0', '2020-01-06 11:57:34');
INSERT INTO `role_permission` VALUES ('a979a51e-fdfc-407a-ac1a-bb16e815318c', 'ba6e1f7e-20d8-453c-a63b-841093bd402c', 'b545d509-9a27-47b7-9c24-7bd2bd540229', '2020-01-06 11:57:34');
INSERT INTO `role_permission` VALUES ('a9980a33-50f8-49e4-8e51-b05be1864ce7', 'ba6e1f7e-20d8-453c-a63b-841093bd402c', '78f8e29a-cccd-49e5-ada7-5af40dd95312', '2020-01-06 11:57:34');
INSERT INTO `role_permission` VALUES ('a9b7bd0e-21ba-4e04-b2b7-7efbdb2e8c92', 'cdd7105c-694c-43e8-afd1-32c29250bb9e', 'b01614ab-0538-4cca-bb61-b46f18c60aa4', '2020-01-07 21:05:24');
INSERT INTO `role_permission` VALUES ('ac4a1599-f399-4779-834e-d3671a1ef5a1', 'd689cf27-137f-41a1-bc68-73045f7ff29b', 'c30389e8-eb3e-4a0d-99c4-639e1893f50a', '2020-01-06 00:04:42');
INSERT INTO `role_permission` VALUES ('ad62586b-8a0f-4991-9e6f-cd5dc41ae77b', 'cdd7105c-694c-43e8-afd1-32c29250bb9e', '783aedd8-5d93-46b6-8c6d-e4d3f0f3f466', '2020-01-07 21:05:24');
INSERT INTO `role_permission` VALUES ('ae7de1b8-8503-4568-b4b1-28f68b62935f', 'cdd7105c-694c-43e8-afd1-32c29250bb9e', 'e0b16b95-09de-4d60-a283-1eebd424ed47', '2020-01-07 21:05:24');
INSERT INTO `role_permission` VALUES ('aec884b9-43d4-4644-8719-07d9c0037a6d', '11b3b80c-4a0b-4a92-96ea-fdd4f7a4a7e9', '3c390dfd-0d9a-46de-9a5b-1ed884febcb2', '2020-01-05 16:06:38');
INSERT INTO `role_permission` VALUES ('af699091-89f9-414e-8673-3a26b2e65d77', 'cdd7105c-694c-43e8-afd1-32c29250bb9e', 'c30389e8-eb3e-4a0d-99c4-639e1893a05f', '2020-01-07 21:05:24');
INSERT INTO `role_permission` VALUES ('b3e94c02-43b3-46d8-a70f-89d4071cac30', 'b95c69b7-84be-430f-ae57-27a703ae3998', 'a390845b-a53d-4bc9-af5d-331c37f34e6f', '2020-01-04 16:56:37');
INSERT INTO `role_permission` VALUES ('b51a5687-347f-4b3f-a6cb-0b3104fbfd41', '11b3b80c-4a0b-4a92-96ea-fdd4f7a4a7e9', 'c30389e8-eb3e-4a0d-99c4-639e1893f50a', '2020-01-05 16:06:38');
INSERT INTO `role_permission` VALUES ('b66f1bba-6cf4-4246-ba3b-a2a9f5bc859e', 'd689cf27-137f-41a1-bc68-73045f7ff29b', 'd6214dcb-8b6d-494b-88fa-f519fc08ff8f', '2020-01-06 00:04:42');
INSERT INTO `role_permission` VALUES ('b6dc2fbf-8627-40c0-b317-ab219b35e508', 'b95c69b7-84be-430f-ae57-27a703ae3998', '1a2ec857-e775-4377-9fb7-e3c77738b3e5', '2020-01-04 16:56:37');
INSERT INTO `role_permission` VALUES ('b7d34329-13e6-4744-9b7c-618e59034d17', 'ba6e1f7e-20d8-453c-a63b-841093bd402c', '783aedd8-5d93-46b6-8c6d-e4d3f0f3f466', '2020-01-06 11:57:34');
INSERT INTO `role_permission` VALUES ('bab83e55-0589-4bbd-b054-16be5a96201b', 'ba6e1f7e-20d8-453c-a63b-841093bd402c', '761db494-833d-4a6c-94b4-3a7409fd9a78', '2020-01-06 11:57:34');
INSERT INTO `role_permission` VALUES ('bad233b4-39ad-42ee-80a5-ff3ca38647f9', 'd689cf27-137f-41a1-bc68-73045f7ff29b', '817a58d1-ec82-4106-870a-bcc0bfaee0e7', '2020-01-06 00:04:42');
INSERT INTO `role_permission` VALUES ('bccc0bfe-6d6e-48e9-b756-2712fd07c0fb', 'b95c69b7-84be-430f-ae57-27a703ae3998', '4018e179-e599-41d0-bac5-c5408e1d4bc6', '2020-01-04 16:56:37');
INSERT INTO `role_permission` VALUES ('be788b9f-c11d-40ac-a728-e90ec721ad32', 'b95c69b7-84be-430f-ae57-27a703ae3998', '78f8e29a-cccd-49e5-ada7-5af40dd95312', '2020-01-04 16:56:37');
INSERT INTO `role_permission` VALUES ('beb243f9-e92d-411e-85df-0e9f135dee68', 'b95c69b7-84be-430f-ae57-27a703ae3998', 'f28b9215-3119-482d-bdc1-1f4c3f7c0869', '2020-01-04 16:56:37');
INSERT INTO `role_permission` VALUES ('c172e776-6afd-48b8-b8dd-d1dd3b1bb296', 'd689cf27-137f-41a1-bc68-73045f7ff29b', 'db2d31b7-fdcb-478e-bfde-a55eb8b0aa43', '2020-01-06 00:04:42');
INSERT INTO `role_permission` VALUES ('c24956e7-dde2-4067-b3b7-7373ca729fc7', 'b95c69b7-84be-430f-ae57-27a703ae3998', 'c0a84726-47d8-4d7a-8d53-0736a4586647', '2020-01-04 16:56:37');
INSERT INTO `role_permission` VALUES ('c5d1ac4e-8f4f-4820-a4e0-a18826deea9a', 'cdd7105c-694c-43e8-afd1-32c29250bb9e', '761db494-833d-4a6c-94b4-3a7409fd9a78', '2020-01-07 21:05:24');
INSERT INTO `role_permission` VALUES ('c62e5e79-91c8-40dd-ad56-4c6ee787c3dd', '11b3b80c-4a0b-4a92-96ea-fdd4f7a4a7e9', '992d1a8d-b5f8-44fc-9a48-4b3e60a7b15e', '2020-01-05 16:06:38');
INSERT INTO `role_permission` VALUES ('c73fe9b7-2695-4358-950d-e10513250310', '11b3b80c-4a0b-4a92-96ea-fdd4f7a4a7e9', 'c0a84726-47d8-4d7a-8d53-0736a4586647', '2020-01-05 16:06:38');
INSERT INTO `role_permission` VALUES ('ce05f81b-8c98-42c0-bba8-b50189c90c2e', 'cdd7105c-694c-43e8-afd1-32c29250bb9e', '817a58d1-ec82-4106-870a-bcc0bfaee0e7', '2020-01-07 21:05:24');
INSERT INTO `role_permission` VALUES ('cea16ecd-cfbf-4b2b-828a-b542ba55d4f6', 'b95c69b7-84be-430f-ae57-27a703ae3998', 'd6214dcb-8b6d-494b-88fa-f519fc08ff8f', '2020-01-04 16:56:37');
INSERT INTO `role_permission` VALUES ('cec9941c-9e51-42f0-ac34-5f2fd2379e68', '12856224-701c-40e7-995f-4699305ea831', 'db2d31b7-fdcb-478e-bfde-a55eb8b0aa43', '2020-01-04 21:19:39');
INSERT INTO `role_permission` VALUES ('cf77a679-d95f-405e-abba-4f29768cf5e4', '11b3b80c-4a0b-4a92-96ea-fdd4f7a4a7e9', 'e0b16b95-09de-4d60-a283-1eebd424ed47', '2020-01-05 16:06:38');
INSERT INTO `role_permission` VALUES ('d0014a22-412c-4c43-ab5f-3587125d323a', 'b95c69b7-84be-430f-ae57-27a703ae3998', '3dac936c-c4e1-4560-ac93-905502f61ae0', '2020-01-04 16:56:37');
INSERT INTO `role_permission` VALUES ('d27b230c-c8d0-4528-b085-a494cefec5ef', 'cdd7105c-694c-43e8-afd1-32c29250bb9e', '3dac936c-c4e1-4560-ac93-905502f61ae0', '2020-01-07 21:05:24');
INSERT INTO `role_permission` VALUES ('d3242800-9420-449e-8149-872196d374b9', 'ba6e1f7e-20d8-453c-a63b-841093bd402c', 'c0a84726-47d8-4d7a-8d53-0736a4586647', '2020-01-06 11:57:34');
INSERT INTO `role_permission` VALUES ('d3aa106b-bc17-411b-9a91-1c5982fe72c7', '11b3b80c-4a0b-4a92-96ea-fdd4f7a4a7e9', 'c038dc93-f30d-4802-a090-be352eab341a', '2020-01-05 16:06:38');
INSERT INTO `role_permission` VALUES ('d738e825-a097-4e12-b1c8-875b6cf90ffb', 'd689cf27-137f-41a1-bc68-73045f7ff29b', 'b180aafa-0d1a-4898-b838-bc20cd44356d', '2020-01-06 00:04:42');
INSERT INTO `role_permission` VALUES ('d76f4ee0-8837-463f-a526-ff7fe71a45df', 'cdd7105c-694c-43e8-afd1-32c29250bb9e', 'd6214dcb-8b6d-494b-88fa-f519fc08ff8f', '2020-01-07 21:05:24');
INSERT INTO `role_permission` VALUES ('d7a58c34-1667-424b-9856-f11daaf1b6e8', 'ba6e1f7e-20d8-453c-a63b-841093bd402c', 'db2d31b7-fdcb-478e-bfde-a55eb8b0aa43', '2020-01-06 11:57:34');
INSERT INTO `role_permission` VALUES ('d7c1ee86-f5c6-46cc-86ab-c6201b292a62', '11b3b80c-4a0b-4a92-96ea-fdd4f7a4a7e9', '3ed79f23-90bf-4669-bc02-42ae392e75c1', '2020-01-05 16:06:38');
INSERT INTO `role_permission` VALUES ('d8db1a7a-61b0-4196-9219-517ed08f1de8', 'cdd7105c-694c-43e8-afd1-32c29250bb9e', '58612968-d93c-4c21-8fdc-a825c0ab0275', '2020-01-07 21:05:24');
INSERT INTO `role_permission` VALUES ('db418bf4-cdba-41f0-b0c7-76aab000d93a', 'd689cf27-137f-41a1-bc68-73045f7ff29b', '37101ed5-e840-4082-ae33-682ca6e41ad8', '2020-01-06 00:04:42');
INSERT INTO `role_permission` VALUES ('df38ec98-b385-4993-a819-141171019014', 'b95c69b7-84be-430f-ae57-27a703ae3998', '0d99b687-3f46-4632-9d56-8dd5e476dae7', '2020-01-04 16:56:37');
INSERT INTO `role_permission` VALUES ('df3eb261-45c0-4c05-9bf2-8e60aa87ac95', 'd689cf27-137f-41a1-bc68-73045f7ff29b', '992d1a8d-b5f8-44fc-9a48-4b3e60a7b15e', '2020-01-06 00:04:42');
INSERT INTO `role_permission` VALUES ('e4402fed-c56d-4ee4-b853-eb49517c4349', 'ba6e1f7e-20d8-453c-a63b-841093bd402c', '475b4c24-40fa-4823-863a-ba6d793b7610', '2020-01-06 11:57:34');
INSERT INTO `role_permission` VALUES ('e5fff565-dbd4-414e-8239-65385fcae28f', '11b3b80c-4a0b-4a92-96ea-fdd4f7a4a7e9', '3dac936c-c4e1-4560-ac93-905502f61ae0', '2020-01-05 16:06:38');
INSERT INTO `role_permission` VALUES ('e744c3f9-dc1e-429d-8752-4d935fa9dedd', 'ba6e1f7e-20d8-453c-a63b-841093bd402c', '2073345f-7344-43fe-9084-b7add56da652', '2020-01-06 11:57:34');
INSERT INTO `role_permission` VALUES ('e8e0a0e4-2953-40f1-8c07-ed9a3e7dd590', 'ba6e1f7e-20d8-453c-a63b-841093bd402c', 'b180aafa-0d1a-4898-b838-bc20cd44356d', '2020-01-06 11:57:34');
INSERT INTO `role_permission` VALUES ('e9a949a3-79ca-44d5-a812-dea91f1dfbb1', '11b3b80c-4a0b-4a92-96ea-fdd4f7a4a7e9', 'a31acb33-9412-4ada-bbdf-0138c007e6db', '2020-01-05 16:06:38');
INSERT INTO `role_permission` VALUES ('eaa6a475-dfc8-46db-b7d3-c9484646fac6', '12856224-701c-40e7-995f-4699305ea831', 'f28b9215-3119-482d-bdc1-1f4c3f7c0869', '2020-01-04 21:19:39');
INSERT INTO `role_permission` VALUES ('eaba3637-c577-4b20-9357-ff6688f3d762', '12856224-701c-40e7-995f-4699305ea831', 'd6214dcb-8b6d-494b-88fa-f519fc08ff8f', '2020-01-04 21:19:39');
INSERT INTO `role_permission` VALUES ('ed29c385-be2b-4074-93b0-42b93121cfbb', 'ba6e1f7e-20d8-453c-a63b-841093bd402c', 'e549c4b8-72ca-4ba3-91a8-9ffa1daf77cf', '2020-01-06 11:57:34');
INSERT INTO `role_permission` VALUES ('edcb1267-6cb3-431b-9309-2f4fe3f378bb', 'ba6e1f7e-20d8-453c-a63b-841093bd402c', '3c390dfd-0d9a-46de-9a5b-1ed884febcb2', '2020-01-06 11:57:34');
INSERT INTO `role_permission` VALUES ('ee1b6b6d-c4e9-4e32-b033-5eb45f047dc4', 'cdd7105c-694c-43e8-afd1-32c29250bb9e', '37101ed5-e840-4082-ae33-682ca6e41ad8', '2020-01-07 21:05:24');
INSERT INTO `role_permission` VALUES ('ee4d5176-52c5-435b-a466-822042a07e91', 'b95c69b7-84be-430f-ae57-27a703ae3998', '2073345f-7344-43fe-9084-b7add56da652', '2020-01-04 16:56:37');
INSERT INTO `role_permission` VALUES ('efcb8563-b30a-4692-a462-c3f71f357123', 'd689cf27-137f-41a1-bc68-73045f7ff29b', 'e549c4b8-72ca-4ba3-91a8-9ffa1daf77cf', '2020-01-06 00:04:42');
INSERT INTO `role_permission` VALUES ('f97778d9-7807-4374-8d13-c522550bba18', 'ba6e1f7e-20d8-453c-a63b-841093bd402c', 'f21ed5e8-0756-45dc-91c5-f58a9463caaa', '2020-01-06 11:57:34');
INSERT INTO `role_permission` VALUES ('f9e40fb3-29c7-468b-bc10-e70385289b98', 'ba6e1f7e-20d8-453c-a63b-841093bd402c', 'd6214dcb-8b6d-494b-88fa-f519fc08ff8f', '2020-01-06 11:57:34');
INSERT INTO `role_permission` VALUES ('fa38212c-6341-4cff-8e93-4272faf7d54e', '12856224-701c-40e7-995f-4699305ea831', '78f8e29a-cccd-49e5-ada7-5af40dd95312', '2020-01-04 21:19:39');
INSERT INTO `role_permission` VALUES ('fa483290-3501-40a9-8a8f-7a5ba81b7000', '11b3b80c-4a0b-4a92-96ea-fdd4f7a4a7e9', 'c09221bd-35ef-4a63-bd92-018c7fa218ad', '2020-01-05 16:06:38');
INSERT INTO `role_permission` VALUES ('fad283e7-0920-4fec-bea0-eab2f5ede7b1', 'd689cf27-137f-41a1-bc68-73045f7ff29b', '355f387f-a22b-4f8c-9cd6-ae10e930cd70', '2020-01-06 00:04:42');
INSERT INTO `role_permission` VALUES ('fbc1c88e-40ae-43cc-95b7-3a99f4e30b7d', '11b3b80c-4a0b-4a92-96ea-fdd4f7a4a7e9', '4018e179-e599-41d0-bac5-c5408e1d4bc6', '2020-01-05 16:06:38');
INSERT INTO `role_permission` VALUES ('fe3bb87f-82bf-4b6a-b805-97971ab4e4f1', 'ba6e1f7e-20d8-453c-a63b-841093bd402c', '0d99b687-3f46-4632-9d56-8dd5e476dae7', '2020-01-06 11:57:34');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(64) NOT NULL COMMENT '用户id',
  `username` varchar(50) NOT NULL COMMENT '账户名称',
  `salt` varchar(20) DEFAULT NULL COMMENT '加密盐值',
  `password` varchar(200) NOT NULL COMMENT '用户密码密文',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `dept_id` varchar(64) DEFAULT NULL COMMENT '部门id',
  `real_name` varchar(60) DEFAULT NULL COMMENT '真实名称',
  `nick_name` varchar(60) DEFAULT NULL COMMENT '昵称',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱(唯一)',
  `status` tinyint(4) DEFAULT '1' COMMENT '账户状态(1.正常 2.锁定 )',
  `sex` tinyint(4) DEFAULT '1' COMMENT '性别(1.男 2.女)',
  `deleted` tinyint(4) DEFAULT '1' COMMENT '是否删除(1未删除；0已删除)',
  `create_id` varchar(64) DEFAULT NULL COMMENT '创建人',
  `update_id` varchar(64) DEFAULT NULL COMMENT '更新人',
  `create_where` tinyint(4) DEFAULT '1' COMMENT '创建来源(1.web 2.android 3.ios )',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('17860e04-1132-4458-9347-5e9130b73d52', 'aw', 'f6573b6b08d243c0a308', '8acb98820463f3997badfc3cad173088', '15520325478', 'f383e39d-b4a3-4994-9c35-439d74e0ed5a', 'awdad', null, '1@qq.com', '1', '2', '1', null, '47420f71-6c13-4fef-ac88-c42c5d55a28c', '2', '2020-01-05 18:56:47', '2020-01-06 00:04:57');
INSERT INTO `user` VALUES ('2b674ef2-a7e1-42da-8868-ca062686370b', 'adsdad', 'c773a2e4523c4802b565', 'b589f4e548af7679ff97f977c06131a4', '15502145687', 'f383e39d-b4a3-4994-9c35-439d74e0ed5a', 'wda', null, 'dad@qq.com', '1', '1', '1', null, 'fcf34b56-a7a2-4719-9236-867495e74c31', '3', '2020-01-05 17:47:33', '2020-01-05 22:05:57');
INSERT INTO `user` VALUES ('2faca892-4756-4e32-820d-ca9386f3b2ce', 'guest', '6342f4ac46de4d269be8', '0b4e116c0bdfe985411d4c6321312bae', '15501547895', '52ee944b-08e2-4fd1-bf11-254b89b31821', '访客', null, 'guest@gmail.com', '1', '1', '1', null, null, '1', '2020-01-07 17:54:12', null);
INSERT INTO `user` VALUES ('47420f71-6c13-4fef-ac88-c42c5d55a28c', 'ww', 'a0d844e3832d41adbe0f', 'dfebba747156a212ea03f5fdb235cfc4', '15521254789', '52ee944b-08e2-4fd1-bf11-254b89b31821', 'wad', null, '2@qq.com', '1', '1', '1', null, null, '1', '2020-01-06 00:02:31', null);
INSERT INTO `user` VALUES ('7f8c0e32-058e-409d-8e7c-22a9afe6a0a0', 'zhangyang', '062f33e33afe4509b24b', '920f5d75077b25912e5054c4d58e0a4c', '13899999999', '931bebd9-259e-43f0-a5a2-a3b7b3403dae', '张杨', null, '16399@163.com', '1', '1', '1', null, 'fcf34b56-a7a2-4719-9236-867495e74c31', '1', '2019-11-09 21:23:36', '2020-01-06 00:54:13');
INSERT INTO `user` VALUES ('84c7d51e-62be-4008-a5f8-bd824d850568', 'admin4', 'f569fc22ed3846639c41', '3775dfd7168064662b57f82f721f07be', '15502145698', 'f383e39d-b4a3-4994-9c35-439d74e0ed5a', 'wada', null, 'dad@qq.com', '1', '2', '1', null, 'fcf34b56-a7a2-4719-9236-867495e74c31', '1', '2020-01-05 17:50:37', '2020-01-05 19:33:25');
INSERT INTO `user` VALUES ('974b43dd-9204-486c-9e92-291a10ab2376', 'admin_01', '76a661b53af046cba554', '5fbeebb525f243a996b4491dd67b364c', '15214568955', '931bebd9-259e-43f0-a5a2-a3b7b3403dae', '杨颖', null, '1224515@qq.com', '2', '3', '1', null, 'fcf34b56-a7a2-4719-9236-867495e74c31', '2', '2020-01-04 17:02:07', '2020-01-06 11:59:16');
INSERT INTO `user` VALUES ('9d3e2984-4d6f-4f6a-9d28-7564f292ab80', 'adad', 'dc3db079b02944ccac82', '671c45fc8a8eecd7eec1a9081faa3f19', '15523457856', 'f383e39d-b4a3-4994-9c35-439d74e0ed5a', 'dasda', null, 'dad@qq.com', '2', '3', '1', null, 'fcf34b56-a7a2-4719-9236-867495e74c31', '3', '2020-01-05 17:44:01', '2020-01-06 00:50:22');
INSERT INTO `user` VALUES ('ac1566aa-99f2-47b3-9d17-b37072501179', 'yangqicheng', '9f127fd023b742e8bd2a', '0d6cc23ab46b092a24ea0bff0f42c26c', '15503215422', '52ee944b-08e2-4fd1-bf11-254b89b31821', '杨启程', null, '1425466@gmail.com', '1', '1', '1', null, 'ac1566aa-99f2-47b3-9d17-b37072501179', '1', '2020-01-05 17:13:24', '2020-01-05 18:29:30');
INSERT INTO `user` VALUES ('fcf34b56-a7a2-4719-9236-867495e74c31', 'admin', '324ce32d86224b00a02b', '7367d0d2d17b49067b83fb60bf5acf3b', '15503679159', '72a4f388-50f8-4019-8c67-530cd7c74e7a', '王一钢', 'yiie', 'yiie315@163.com', '1', '3', '1', '', 'fcf34b56-a7a2-4719-9236-867495e74c31', '1', '2019-09-22 19:38:05', '2020-01-10 16:53:36');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `user_id` varchar(64) DEFAULT NULL COMMENT '用户id',
  `role_id` varchar(64) DEFAULT NULL COMMENT '角色id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('33f8fa93-78ef-4857-8d99-231459218731', '7f8c0e32-058e-409d-8e7c-22a9afe6a0a0', '11b3b80c-4a0b-4a92-96ea-fdd4f7a4a7e9', '2020-01-06 00:54:14');
INSERT INTO `user_role` VALUES ('435c255e-07da-4347-a6e4-5e4b546bee4a', '2faca892-4756-4e32-820d-ca9386f3b2ce', 'cdd7105c-694c-43e8-afd1-32c29250bb9e', '2020-01-07 17:55:40');
INSERT INTO `user_role` VALUES ('4e6f0e78-5d49-486c-bdc4-f66caf85fd9f', '974b43dd-9204-486c-9e92-291a10ab2376', 'ba6e1f7e-20d8-453c-a63b-841093bd402c', '2020-01-06 11:59:16');
INSERT INTO `user_role` VALUES ('885e3ab2-4e8b-4124-b2fa-4a4a7f7e5673', '7f8c0e32-058e-409d-8e7c-22a9afe6a0a0', 'b95c69b7-84be-430f-ae57-27a703ae3998', '2020-01-06 00:54:14');
INSERT INTO `user_role` VALUES ('8ac5213f-e80c-4deb-b0dd-3d2c476bd96d', '1dfaafa7-fddf-46f2-b3d8-11bfe9ac7230', '2d56198c-d14b-4d02-a625-7559815b62fb', '2019-11-09 23:28:23');
INSERT INTO `user_role` VALUES ('8e411165-ae3f-4e82-8734-4ed79a354e48', '9d3e2984-4d6f-4f6a-9d28-7564f292ab80', 'ba6e1f7e-20d8-453c-a63b-841093bd402c', '2020-01-06 00:50:22');
INSERT INTO `user_role` VALUES ('acd795be-f10d-4e62-a3e1-849913edbc09', 'ac1566aa-99f2-47b3-9d17-b37072501179', '12856224-701c-40e7-995f-4699305ea831', '2020-01-06 11:56:28');
INSERT INTO `user_role` VALUES ('cc42d243-5a25-4601-9f79-1cca844339d2', 'ac1566aa-99f2-47b3-9d17-b37072501179', '11b3b80c-4a0b-4a92-96ea-fdd4f7a4a7e9', '2020-01-06 11:56:28');
INSERT INTO `user_role` VALUES ('db775265-c821-49c1-8f5d-1b8c5bb34e50', 'd860412c-9a4b-404b-8b71-ae8e3f4c27b7', 'de54c167-e733-4b5b-83dd-ce10edd078f5', '2020-01-04 09:23:29');
INSERT INTO `user_role` VALUES ('e3014e3a-0f4e-4804-8b27-80c644723b0d', '17860e04-1132-4458-9347-5e9130b73d52', '11b3b80c-4a0b-4a92-96ea-fdd4f7a4a7e9', '2020-01-05 23:01:03');
INSERT INTO `user_role` VALUES ('e6573cda-2f32-45ad-ae9c-fdc7543b5c35', '47420f71-6c13-4fef-ac88-c42c5d55a28c', 'd689cf27-137f-41a1-bc68-73045f7ff29b', '2020-01-06 00:03:30');
INSERT INTO `user_role` VALUES ('e96c961a-719c-4e55-87bb-3c7b5080ad24', 'fcf34b56-a7a2-4719-9236-867495e74c31', '11b3b80c-4a0b-4a92-96ea-fdd4f7a4a7e9', '2020-01-05 23:54:33');
