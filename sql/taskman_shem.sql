/* sql by Oracle 12 */
create sequence s_task_id start with 1;
  
create table tasks (
    task_id number default s_task_id.nextval not null
    ,t_name varchar2(200) not null
    ,owner_id number not null
    ,executor_id number not null
    ,t_description varchar2(200) not null
    ,status_id number not null
    ,primary key (task_id)
);

create table task_status (status_id integer not null
    ,status_name varchar(50) not null
    ,primary key (status_id)
);

begin
insert into task_status values (1, 'Created');
insert into task_status values (2, 'Close');
insert into task_status values (3, 'Rejected');
end;

create sequence s_user_id start with 1;
  
create table tm_users (
    user_id number default s_user_id.nextval not null
    ,user_name varchar2(200) not null
    ,email varchar2(100) not null
    ,upassword varchar2(100) not null
    ,primary key (user_id)
);

create table tm_users_roles (
    role_id integer not null
    ,role_name varchar(50) not null
    ,primary key (role_id)
);

create sequence s_user_role_link_id start with 1;

create table tm_user_role_links(
    link_id number default s_user_role_link_id.nextval not null
    ,user_id number not null
    ,role_id number not null
    ,primary key (link_id)
);

begin
insert into tm_users_roles values (1, 'ROLE_MANAGER');
insert into tm_users_roles values (2, 'ROLE_EXECUTOR');
insert into tm_users_roles values (3, 'ROLE_ADMIN');
end;

begin
insert into tm_users (user_name, email, upassword) values ('Albert O’Connor', 'albert@mail.ru', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i');
insert into tm_users (user_name, email, upassword) values ('Catherine Edwards', 'catherine@mail.ru', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i');
insert into tm_users (user_name, email, upassword) values ('Gerald Robin', 'gerald@mail.ru', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i');
insert into tm_users (user_name, email, upassword) values ('Scott Bush', 'scott@mail.ru', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i');
insert into tm_users (user_name, email, upassword) values ('Vivien Archibald', 'vivien@mail.ru', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i');
insert into tm_users (user_name, email, upassword) values ('Wilma Leapman', 'wilma@mail.ru', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i');
insert into tm_users (user_name, email, upassword) values ('Admin', 'admin@mail.ru', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i');
end;

declare
tmp_rol_id$i integer := 1;
begin
    for rec in (
        select tu.user_id
        from tm_users tu
        where not exists(select 1
                        from tm_user_role_links url
                        where url.user_id = tu.user_id)
    )
    loop
        insert into tm_user_role_links (user_id, role_id) values (rec.user_id, tmp_rol_id$i);
        if tmp_rol_id$i = 1 then
            tmp_rol_id$i := 2;
        else
            tmp_rol_id$i := 1;
        end if;
    end loop;
end;
