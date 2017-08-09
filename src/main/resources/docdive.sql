
CREATE DATABASE /*!32312 IF NOT EXISTS*/`docdive` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `docdive`;

drop table if exists Role;
/*==============================================================*/
/* Table: Role                                                  */
/*==============================================================*/
create table Role
(
   roleId               varchar(36) not null,
   roleName             varchar(20),
   roleDesc             varchar(100),
   primary key (roleId)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;
alter table Role comment '角色表';
 
drop table if exists User_Role;
/*==============================================================*/
/* Table: User_Role                                             */
/*==============================================================*/
create table User_Role
(
   userId               varchar(36) not null,
   roleId               varchar(36) not null,
   tenantId             varchar(36) not null,
   primary key (userId, roleId, tenantId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
alter table User_Role comment '用户角色关联表';
alter table User_Role add constraint FK_user_role_reference foreign key (roleId)
      references Role (roleId) on delete restrict on update restrict;
 
 
 
drop table if exists Role_Privilege;
/*==============================================================*/
/* Table: Role_Privilege                                        */
/*==============================================================*/
create table Role_Privilege
(
   roleId               varchar(36) not null,
   privilegeName        varchar(36) not null,
   primary key (roleId, privilegeName)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
alter table Role_Privilege comment '角色和权限的关系表';
alter table Role_Privilege add constraint FK_Role_Privilege_reference foreign key (roleId)
      references Role (roleId) on delete restrict on update restrict;
