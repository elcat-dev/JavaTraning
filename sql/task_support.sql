CREATE OR REPLACE package task_support is

procedure get_tasks(tasks_cur$cr out sys_refcursor
                  ,task_id$i in integer := null
                  ,t_executor$c in varchar2 := null
                  ,t_status$c in  varchar2 := null
                  );

end task_support;
/

CREATE OR REPLACE package body task_support is

procedure get_tasks(tasks_cur$cr out sys_refcursor
                  ,task_id$i in integer := null
                  ,t_executor$c in varchar2 := null
                  ,t_status$c in  varchar2 := null
                  ) is
procedure_name$c varchar2(50) := 'get_tasks';
param$c varchar2(500);
begin
    param$c := nvl(to_char(task_id$i),'null')||','||nvl(t_executor$c, 'null')||','||nvl(t_status$c, 'null');
    open tasks_cur$cr for
        select ts.task_id
            ,ts.t_name
            ,ts.t_owner
            ,ts.t_executor
            ,ts.t_description
            ,ts.t_status
        from tasks ts
        where (ts.task_id = task_id$i or task_id$i is null)  
            and (ts.t_executor = t_executor$c or t_executor$c is null) 
            and (ts.t_status = t_status$c or t_status$c is null)
        order by ts.task_id;
exception
  when others then
    raise_application_error(-20100, dbms_utility.format_error_stack||dbms_utility.format_error_backtrace
                            ||' in '||procedure_name$c||'('||param$c||')');
end get_tasks;

end task_support;
/
