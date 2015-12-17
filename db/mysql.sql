
-- MySQL zhuanlemei

-- START

-- 产品集
CREATE TABLE t_next_collect (
  collectid int(5) NOT NULL AUTO_INCREMENT COMMENT '产品集主键',
  collectname varchar(100) NOT NULL DEFAULT '' COMMENT '产品集名称',
  coverimgpath varchar(100) DEFAULT '' COMMENT '封面路径',
  bgcolor char(6) DEFAULT NULL COMMENT '背景颜色',
  userid int(5) NOT NULL DEFAULT '0' COMMENT '创建用户',
  productsum int(5) NOT NULL DEFAULT '0' COMMENT '产品总数',
  collectsum int(5) NOT NULL DEFAULT '0' COMMENT '被收藏次数',
  memo varchar(150) DEFAULT '' COMMENT '说明',
  updatestate char(1) NOT NULL DEFAULT '' COMMENT '更新标志',
  updatetime varchar(20) NOT NULL DEFAULT '' COMMENT '更新时间',
  createtime varchar(20) NOT NULL DEFAULT '' COMMENT '创建时间',
  collectstate char(1) NOT NULL DEFAULT '' COMMENT '产品集状态',
  recommend varchar(200) NOT NULL COMMENT '产品集介绍',
  likesum int(5) DEFAULT '0' COMMENT '赞同总数',
  likescore int(5) DEFAULT '0' COMMENT '赞同分数',
  unlikesum int(5) DEFAULT '0' COMMENT '反对总数',
  unlikescore int(5) DEFAULT '0' COMMENT '反对分数',
  commentsum int(5) DEFAULT '0' COMMENT '评论总数',
  detailcontent mediumtext COMMENT '详细内容',
  PRIMARY KEY (collectid)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COMMENT='产品集表';

-- 用户收藏产品集表
CREATE TABLE t_next_collectuser (
  collectid int(5) DEFAULT NULL COMMENT '产品集ID',
  userid int(5) NOT NULL DEFAULT '0' COMMENT '用户ID',
  createtime varchar(20) NOT NULL DEFAULT '' COMMENT '收藏时间',
  collectstate char(1) NOT NULL DEFAULT '' COMMENT '收藏状态',
  fundcode char(6) DEFAULT NULL COMMENT '基金代码'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户收藏产品集表';

-- 评论表
CREATE TABLE t_next_comment (
  commentid int(5) NOT NULL AUTO_INCREMENT COMMENT '评论主键',
  commentrefid int(5) DEFAULT '0' COMMENT '评论引用主键',
  productid int(5) DEFAULT NULL COMMENT '产品主键',
  collectid int(5) DEFAULT NULL COMMENT '产品集主键',
  fundcode char(6) DEFAULT NULL COMMENT '基金代码',
  userid int(1) NOT NULL DEFAULT '0' COMMENT '用户主键',
  content varchar(500) NOT NULL DEFAULT '' COMMENT '评论内容',
  createtime datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '评论时间',
  commentstate char(1) NOT NULL DEFAULT '' COMMENT '评论状态',
  replysum int(5) DEFAULT '0' COMMENT '回复数',
  likesum int(5) DEFAULT '0' COMMENT '赞数',
  commenttype varchar(10) DEFAULT NULL COMMENT '评论类型',
  PRIMARY KEY (commentid)
) ENGINE=InnoDB AUTO_INCREMENT=118 DEFAULT CHARSET=utf8 COMMENT='评论表';

-- 用户点赞表
CREATE TABLE t_next_prodlike (
  userid int(5) DEFAULT NULL COMMENT '用户主键',
  prodid int(5) DEFAULT NULL COMMENT '产品主键',
  collectid int(5) DEFAULT NULL COMMENT '产品集主键',
  fundcode char(6) DEFAULT NULL COMMENT '基金代码',
  liketime varchar(20) NOT NULL DEFAULT '' COMMENT '点赞时间',
  likestate char(1) NOT NULL DEFAULT '0' COMMENT '是否有效',
  liketype varchar(10) DEFAULT '' COMMENT '赞类型'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户点赞表';

-- 产品表
CREATE TABLE t_next_product (
  prodid int(5) NOT NULL AUTO_INCREMENT COMMENT '产品主键',
  prodname varchar(100) DEFAULT '' COMMENT '产品名称',
  produrl varchar(255) DEFAULT '' COMMENT '产品官网',
  fundname varchar(100) DEFAULT NULL COMMENT '基金名称',
  fundcode char(6) DEFAULT NULL COMMENT '基金代码',
  memo varchar(100) NOT NULL DEFAULT '' COMMENT '产品描述',
  likesum int(5) NOT NULL DEFAULT '0' COMMENT '产品赞数',
  commentsum int(5) NOT NULL DEFAULT '0' COMMENT '评论总数',
  prodstate char(1) NOT NULL DEFAULT '' COMMENT '产品状态',
  postdate varchar(8) NOT NULL DEFAULT '' COMMENT '发布时间',
  postdatetime varchar(20) NOT NULL DEFAULT '' COMMENT '更新时间',
  userid int(5) NOT NULL DEFAULT '0' COMMENT '用户主键',
  collectid int(5) DEFAULT NULL COMMENT '产品集主键',
  score int(5) NOT NULL DEFAULT '0' COMMENT '产品关注度',
  PRIMARY KEY (prodid)
) ENGINE=InnoDB AUTO_INCREMENT=217 DEFAULT CHARSET=utf8 COMMENT='产品表';

-- 用户表
CREATE TABLE t_next_user (
  userid int(5) NOT NULL AUTO_INCREMENT,
  nickname varchar(20) DEFAULT '' COMMENT '用户昵称',
  password varchar(50) DEFAULT '' COMMENT '登录密码',
  loginemail varchar(100) DEFAULT '' COMMENT '登录邮箱',
  picpath varchar(100) DEFAULT '' COMMENT '我的头像',
  socialpicpath varchar(200) DEFAULT '' COMMENT '社交头像',
  memo varchar(255) DEFAULT '' COMMENT '个人简介',
  usergroup varchar(50) DEFAULT '' COMMENT '用户组织',
  position varchar(50) DEFAULT '' COMMENT '用户职位',
  userstate char(1) NOT NULL DEFAULT '0' COMMENT '用户状态',
  collectpermission char(1) NOT NULL DEFAULT '' COMMENT '产品集权限',
  accesstoken varchar(200) DEFAULT NULL COMMENT '第三方授权Token',
  tokenexpirein varchar(200) DEFAULT NULL COMMENT '第三方授权expireIn',
  openid varchar(100) DEFAULT NULL COMMENT '第三方授权Openid',
  logintype varchar(10) DEFAULT NULL COMMENT '第三方平台类型',
  sex char(1) DEFAULT NULL COMMENT '性别',
  PRIMARY KEY (userid)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- 当天基金行情信息表
CREATE TABLE tfundmarket (
  marketdate varchar(10) NOT NULL DEFAULT '' COMMENT '行情日期',
  fundname varchar(50) DEFAULT '' COMMENT '基金名称',
  fundcode char(6) NOT NULL DEFAULT '' COMMENT '基金代码',
  fundtacode char(2) DEFAULT NULL COMMENT '基金TA代码',
  fundcompany varchar(50) DEFAULT NULL COMMENT '基金公司',
  fundtype char(1) NOT NULL DEFAULT '' COMMENT '基金类型',
  foundeddate varchar(10) DEFAULT NULL COMMENT '基金成立日期',
  fundrisklevel char(1) DEFAULT NULL COMMENT '基金风险级别',
  fundmanager varchar(20) DEFAULT NULL COMMENT '基金经理',
  currentnav varchar(10) DEFAULT NULL COMMENT '基金净值/7日年化收益率',
  currentstate char(1) NOT NULL DEFAULT '' COMMENT '基金当日状态',
  fundincome varchar(10) DEFAULT NULL COMMENT '基金收益',
  fundincomerate varchar(10) DEFAULT NULL COMMENT '基金收益率',
  navdate varchar(10) DEFAULT '' COMMENT '基金净值日期',
  totalnav varchar(20) DEFAULT NULL COMMENT '累计单位净值',
  fundriseweek varchar(10) DEFAULT NULL COMMENT '近一周涨幅',
  fundrisemonth varchar(10) DEFAULT NULL COMMENT '近一个月涨幅',
  fundrisethreemonth varchar(10) DEFAULT NULL COMMENT '近三个月涨幅',
  fundrisehalfyear varchar(10) DEFAULT NULL COMMENT '近半年涨幅',
  fundriseyear varchar(10) DEFAULT NULL COMMENT '近一年涨幅',
  fundrisethisyear varchar(10) DEFAULT NULL COMMENT '今年涨幅',
  newscale varchar(20) DEFAULT NULL COMMENT '基金最新规模',
  ratechange varchar(10) DEFAULT NULL COMMENT '基金涨跌幅',
  fundsortthreemonth varchar(10) DEFAULT NULL COMMENT '基金近3月排名',
  fundtotalthreemonth varchar(10) DEFAULT NULL COMMENT '基金近3月排名数量',
  createtime varchar(30) DEFAULT NULL COMMENT '创建时间',
  updatetime varchar(30) DEFAULT NULL COMMENT '更新时间',
  likesum int(5) DEFAULT '0' COMMENT '产品赞数',
  likescore int(5) DEFAULT '0' COMMENT '产品赞度',
  unlikesum int(5) DEFAULT '0' COMMENT '产品反对数',
  unlikescore int(5) DEFAULT '0' COMMENT '产品反对度',
  commentsum int(6) DEFAULT '0' COMMENT '产品评论数',
  focussum int(5) DEFAULT '0' COMMENT '产品关注人数',
  focusscore int(5) DEFAULT '0' COMMENT '产品关注度'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='当天基金行情信息表';

-- 跑批任务表
CREATE TABLE ttask (
  taskname varchar(20) NOT NULL DEFAULT '' COMMENT '任务名称',
  taskdate varchar(10) NOT NULL DEFAULT '' COMMENT '任务日期',
  taskflag char(1) NOT NULL DEFAULT '' COMMENT '任务标志: 0-未执行, 1-已执行, 2-执行失败',
  taskstate char(1) NOT NULL DEFAULT '' COMMENT '任务状态: 0-正常任务, 1-不使用任务',
  updatetime varchar(20) NOT NULL DEFAULT '' COMMENT '任务执行时间',
  taskmemo varchar(100) NOT NULL DEFAULT '' COMMENT '任务说明'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='跑批任务表';

-- END
