--==============================================================
-- 本脚本包含以下内容（用户ngsh）
-- 1.建立procedure

-- 本脚本以ngsh用户执行
-- 
--==============================================================

create or replace procedure proc_sh_updatealarmlevel is
v_startnum varchar2(32);
v_endnum varchar2(32);
v_alarmdate date;
v_degree number;
v_count number;
begin
  --查询未解除状态的告警，并循环进行处理
  for cl in (select * from sh_alarm_info where unchain = 0) loop
        --根据告警类型查询告警规则
        select count(*) into v_count from sh_alarm_rule where exceptiontype=cl.exceptiontype and status = 1;
        --如果存在，获取对应的时间线
        if v_count > 0 then
           select nvl(startnum, '') into v_startnum from sh_alarm_rule where exceptiontype=cl.exceptiontype and status = 1;
           select nvl(endnum, '') into v_endnum from sh_alarm_rule where exceptiontype=cl.exceptiontype and status = 1;
        --如果存在，根据默认规则获取对应的时间线
        else
            select startnum into v_startnum from sh_alarm_rule where exceptiontype='ALL' and status = 1;
            select endnum into v_endnum from sh_alarm_rule where exceptiontype='ALL' and status = 1;
        end if;
        --查询告警发生的时间
        select alarmdate into v_alarmdate from sh_alarm_info where alarminfoid=cl.alarminfoid;
        --如果告警持续时间小于时间下限，则级别为1 普通
        if sysdate - v_alarmdate < to_number(v_startnum)/(24 * 60) then
           v_degree := 1;
        else
             --如果告警持续时间大于等于时间上限，则级别为3 严重
             if sysdate - v_alarmdate >= to_number(v_endnum)/(24 * 60) then
                v_degree := 3;
             --其余情况，级别为2 紧急
             else
                 v_degree := 2;
             end if;
        end if;

        --更新对应的告警记录，修改级别
        UPDATE sh_alarm_info SET alarmlevel = v_degree WHERE alarminfoid=cl.alarminfoid;
        commit;
    end loop;
end proc_sh_updatealarmlevel;
/

create or replace procedure proc_sh_updatealarminfo(v_termid in varchar2, v_status in number, v_ipaddr in varchar2) is
v_startnum varchar2(32);
v_endnum varchar2(32);
v_alarmdate date;
v_degree number;
v_count number;
begin
  --终端机上传状态后，首先向sh_log_termstatus表中插入记录(程序中已处理)
  --select status from sh_log_termstatus where ipaddr=v_ipaddr and statusdate > sysdate - 5 / (24 * 60)，取终端机刚刚上传的状态
  --上面的语句写的是5分钟，其实可以更短，比如4分钟、3分钟，目的就是取刚刚插入的记录
  --select * from sh_alarm_info where EXCEPTIONTYPE NOT IN (select status from sh_log_termstatus where ipaddr=v_ipaddr and statusdate > sysdate - 5 / (24 * 60)) AND TERMID = v_termid AND UNCHAIN = v_status
  --取终端机尚未解除的告警记录，但该告警实际已不存在(比如之前有301告警，但最近一次上传了0000，说明301已经实际解除了)
  --对上述查询出来的记录进行循环处理
  for cl in (select * from sh_alarm_info where EXCEPTIONTYPE NOT IN (select status from sh_log_termstatus where ipaddr=v_ipaddr and statusdate > sysdate - 5 / (24 * 60)) AND TERMID = v_termid AND UNCHAIN = v_status) loop
        --在告警规则表中查询该告警类型对应的记录
        select count(*) into v_count from sh_alarm_rule where exceptiontype=cl.exceptiontype and status = 1;
        --如果有，取告警持续时间的分隔线
        if v_count > 0 then
           select nvl(startnum, '') into v_startnum from sh_alarm_rule where exceptiontype=cl.exceptiontype and status = 1;
           select nvl(endnum, '') into v_endnum from sh_alarm_rule where exceptiontype=cl.exceptiontype and status = 1;
        --如果没有，使用默认规则。取告警持续时间的分隔线
        else
            select startnum into v_startnum from sh_alarm_rule where exceptiontype='ALL' and status = 1;
            select endnum into v_endnum from sh_alarm_rule where exceptiontype='ALL' and status = 1;
        end if;
        --取该告警发生的时间
        select alarmdate into v_alarmdate from sh_alarm_info where alarminfoid=cl.alarminfoid;
        --用当前时间与告警发生时间进行比较，如果持续时间小于分割线1，则级别为1 普通
        if sysdate - v_alarmdate < to_number(v_startnum)/(24 * 60) then
           v_degree := 1;
        else
             --用当前时间与告警发生时间进行比较，如果持续时间大于等于分割线2，则级别为3 严重
             if sysdate - v_alarmdate >= to_number(v_endnum)/(24 * 60) then
                v_degree := 3;
             --其余情况，级别为2 紧急
             else
                 v_degree := 2;
             end if;
        end if;

        --更新该告警记录，设置级别为刚才计算出的级别、解除时间为当前时间、解除状态为1
        UPDATE sh_alarm_info SET alarmlevel = v_degree, UNCHAINDATE = SYSDATE, UNCHAIN = 1 WHERE alarminfoid=cl.alarminfoid;
        commit;
    end loop;
end proc_sh_updatealarminfo;
/