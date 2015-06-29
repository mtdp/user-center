--用户
CREATE TABLE t_user(
	userId int auto_increment,
	userName varchar(50),
	account varchar(50),
	password varchar(100),
	email varchar(50),
	mobile varchar(11),
	status varchar(2),
	createTime varchar(14),
	updateTime varchar(14),
	PRIMARY KEY(userId)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

--用户角色
CREATE TABLE t_user_role(
	userRoleId int auto_increment,
	userId int,
	roleId int,
	createTime varchar(14),
	updateTime varchar(14),
	PRIMARY KEY(userRoleId)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

--角色
CREATE TABLE t_role(
	roleId int auto_increment,
	roleName varchar(50),
	roleCode varchar(50),
	status varchar(2),
	createByUserId int,
	lastUpdateUserId int,
	createTime varchar(14),
	updateTime varchar(14),
	PRIMARY KEY(roleId)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

--资源
CREATE TABLE t_resource(
	resId int auto_increment,
	resName varchar(50),
	resURI varchar(200),
	resCode varchar(50),
	isMenu varchar(2),
	status varchar(2),
	rank int,
	createTime varchar(14),
	updateTime varchar(14),
	PRIMARY KEY(resId)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

--角色资源
CREATE TABLE t_role_res(
	roleResId int auto_increment,
	roleId int,
	resId int,
	createTime varchar(14),
	updateTime varchar(14),
	PRIMARY KEY(roleResId)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

