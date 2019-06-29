
USE supporttibet;

DROP TABLE IF EXISTS school;

CREATE TABLE school(
  id VARCHAR(32) COMMENT 'ID',
  schoolName  VARCHAR(64) COMMENT '学校名称',
  city VARCHAR(64) COMMENT '城市',
  info TEXT COMMENT '学校简介',
  isGo VARCHAR(4) COMMENT '是否愿意去',
  isNeed VARCHAR(4) COMMENT '是否需要',
  history TEXT COMMENT '历史',
  createTime VARCHAR(64) COMMENT '创建时间',
  other VARCHAR(255) COMMENT '其他，预留字段',
  PRIMARY KEY (id)
);


DROP TABLE IF EXISTS User;

CREATE TABLE User(
  id VARCHAR(32) COMMENT '用户id',
  username  VARCHAR(32) COMMENT '用户名',
  password VARCHAR(32) COMMENT '密码',
  level INT COMMENT '级别',
  telephone VARCHAR(16) COMMENT '联系电话',
  qq  VARCHAR(16) COMMENT 'qq',
  email VARCHAR(16) COMMENT '邮箱',
  schoolId VARCHAR(32) COMMENT '学校ID',
  schoolName VARCHAR(64) COMMENT '学校名称',
  createTime VARCHAR(64) COMMENT '创建时间',
  address VARCHAR(255) COMMENT '地址',
  gender VARCHAR(8) COMMENT '性别',
  other VARCHAR(255) COMMENT '其他，预留',
  PRIMARY KEY (id),
  CONSTRAINT fk_schoolId FOREIGN KEY (schoolId) REFERENCES school(id)
);



USE supporttibet;

DROP TABLE IF EXISTS workstate;

CREATE TABLE workState(
  id  VARCHAR(32) COMMENT '工作动态Id',
  name VARCHAR(64) COMMENT '工作动态名称',
  userId  VARCHAR(32) COMMENT '发布工作动态的人员id',
  content TEXT COMMENT '工作动态内容',
  publicTime VARCHAR(64) COMMENT '工作动态发布时间',
  PRIMARY KEY (id),
  CONSTRAINT fk_userId FOREIGN KEY (userId) REFERENCES user(id)
);


DROP TABLE IF EXISTS needInfo;

CREATE TABLE needInfo(
  id  VARCHAR(32) COMMENT '需求Id',
  name  VARCHAR(64) COMMENT '援藏需求名称',
  userId VARCHAR(32) COMMENT '需求发布的人员id',
  schoolId VARCHAR(32) COMMENT '需求发布的学校id',
  level VARCHAR(16) COMMENT '需求紧急级别',
  info TEXT COMMENT '需求信息',
  publishTime VARCHAR(32) COMMENT '发布时间',
  editTime VARCHAR(32) COMMENT '最后一次编辑时间',
  other VARCHAR(255) COMMENT '备用',
  PRIMARY KEY (id),
  CONSTRAINT fk_userId_need_info FOREIGN KEY (userId) REFERENCES user(id)
);


DROP TABLE IF EXISTS needJoin;

CREATE TABLE needJoin(
  id    VARCHAR(32) COMMENT '报名id',
  inNeedId VARCHAR(32) COMMENT '需求信息id',
  username VARCHAR(32) COMMENT '用户名',
  telephone VARCHAR(32) COMMENT '电话',
  schoolName VARCHAR(32) COMMENT '学校名称',
  qq  VARCHAR(16) COMMENT 'qq',
  course VARCHAR(32) COMMENT '课程名称',
  other VARCHAR(255) COMMENT '备用',
  PRIMARY KEY (id),
  CONSTRAINT fk_inNeedId FOREIGN KEY (inNeedId) REFERENCES needInfo(id)
);


DROP TABLE IF EXISTS Policy;

CREATE TABLE policy(
  id    VARCHAR(32) COMMENT '政策ID',
  name  VARCHAR(64) COMMENT '政策名称',
  path  VARCHAR(64) COMMENT '政策文件保存位置',
  publishTime VARCHAR(64) COMMENT '发布时间',
  other VARCHAR(255) COMMENT '备用',
  PRIMARY KEY (id)
);


DROP TABLE IF EXISTS road;

CREATE TABLE road(
  id  VARCHAR(32) COMMENT '路线Id',
  name  VARCHAR(64)  COMMENT '路线名称',
  content TEXT COMMENT '路线内容',
  publishTime VARCHAR(32) COMMENT '发布时间',
  other VARCHAR(255) COMMENT '备用',
  PRIMARY KEY (id)
);


DROP TABLE IF EXISTS recruitInfo;

CREATE TABLE recruitInfo(
  id  VARCHAR(32) COMMENT '招聘Id',
  name VARCHAR(32) COMMENT '招聘信息名称',
  content TEXT COMMENT '招聘内容',
  publishTime VARCHAR(32) COMMENT '发布时间',
  other VARCHAR(255) COMMENT '备用',
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS permission;

CREATE TABLE permission(
  id  INT(11) AUTO_INCREMENT,
  name  VARCHAR(32) NOT NULL ,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS role;

CREATE TABLE role(
  id  INT(11) AUTO_INCREMENT,
  name  VARCHAR(32) NOT NULL ,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS role_permission;

CREATE TABLE role_permission(
  id  INT(11) AUTO_INCREMENT,
  role_id INT(11) NOT NULL ,
  permission_id INT(11) NOT NULL ,
  PRIMARY KEY (id),
  CONSTRAINT fk_role_id FOREIGN KEY (role_id) REFERENCES role(id),
  CONSTRAINT fk_permission_id FOREIGN KEY (permission_id) REFERENCES permission(id)
);







