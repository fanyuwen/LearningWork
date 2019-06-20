drop database if exists shiro;
create database shiro; # 创建数据库
use shiro;

#users(用户表)
create table users
(
    id            bigint auto_increment,
    username      varchar(100),
    password      varchar(100),
    password_salt varchar(100),
    constraint pk_users primary key (id)
) charset = utf8
  ENGINE = InnoDB;
create unique index idx_users_username on users (username);

#user_roles(用户与角色关联表)
create table user_roles
(
    id        bigint auto_increment,
    username  varchar(100),
    role_name varchar(100),
    constraint pk_user_roles primary key (id)
) charset = utf8
  ENGINE = InnoDB;
create unique index idx_user_roles on user_roles (username, role_name);

#roles_permissions(角色与权限关联表)
create table roles_permissions
(
    id         bigint auto_increment,
    role_name  varchar(100),
    permission varchar(100),
    constraint pk_roles_permissions primary key (id)
) charset = utf8
  ENGINE = InnoDB;
create unique index idx_roles_permissions on roles_permissions (role_name, permission);

insert into users(username, password)
values ('zhang', '123');