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
commit;
end;

create sequence s_user_id start with 1;
  
create table tm_users (
    user_id number default s_user_id.nextval not null
    ,user_name varchar2(200) not null
    ,role_id number not null
    ,primary key (user_id)
);

create table tm_users_roles (role_id integer not null
    ,role_name varchar(50) not null
    ,primary key (role_id)
);

begin
insert into tm_users_roles values (1, 'Manager');
insert into tm_users_roles values (2, 'Executor');
commit;
end;

begin
insert into tm_users (user_name, role_id) values ('Albert O’Connor', 1);
insert into tm_users (user_name, role_id) values ('Catherine Edwards', 2);
insert into tm_users (user_name, role_id) values ('Gerald Robin', 1);
insert into tm_users (user_name, role_id) values ('Scott Bush', 2);
insert into tm_users (user_name, role_id) values ('Vivien Archibald', 1);
insert into tm_users (user_name, role_id) values ('Wilma Leapman', 2);
commit;
end;