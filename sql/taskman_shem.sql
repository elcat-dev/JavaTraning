/* sql by Oracle 12 */
create sequence s_task_id start with 1;
  
create table tasks (
    task_id number default s_task_id.nextval not null
    ,t_name varchar2(200)
    ,t_owner varchar2(200)
    ,t_executor varchar2(200)
    ,t_description varchar2(200)
    ,t_status varchar2(200)
    ,primary key (task_id)
);