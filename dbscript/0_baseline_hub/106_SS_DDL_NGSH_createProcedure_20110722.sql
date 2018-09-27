--==============================================================
-- ���ű������������ݣ��û�ngsh��
-- 1.����procedure

-- ���ű���ngsh�û�ִ��
-- 
--==============================================================

create or replace procedure proc_sh_updatealarmlevel is
v_startnum varchar2(32);
v_endnum varchar2(32);
v_alarmdate date;
v_degree number;
v_count number;
begin
  --��ѯδ���״̬�ĸ澯����ѭ�����д���
  for cl in (select * from sh_alarm_info where unchain = 0) loop
        --���ݸ澯���Ͳ�ѯ�澯����
        select count(*) into v_count from sh_alarm_rule where exceptiontype=cl.exceptiontype and status = 1;
        --������ڣ���ȡ��Ӧ��ʱ����
        if v_count > 0 then
           select nvl(startnum, '') into v_startnum from sh_alarm_rule where exceptiontype=cl.exceptiontype and status = 1;
           select nvl(endnum, '') into v_endnum from sh_alarm_rule where exceptiontype=cl.exceptiontype and status = 1;
        --������ڣ�����Ĭ�Ϲ����ȡ��Ӧ��ʱ����
        else
            select startnum into v_startnum from sh_alarm_rule where exceptiontype='ALL' and status = 1;
            select endnum into v_endnum from sh_alarm_rule where exceptiontype='ALL' and status = 1;
        end if;
        --��ѯ�澯������ʱ��
        select alarmdate into v_alarmdate from sh_alarm_info where alarminfoid=cl.alarminfoid;
        --����澯����ʱ��С��ʱ�����ޣ��򼶱�Ϊ1 ��ͨ
        if sysdate - v_alarmdate < to_number(v_startnum)/(24 * 60) then
           v_degree := 1;
        else
             --����澯����ʱ����ڵ���ʱ�����ޣ��򼶱�Ϊ3 ����
             if sysdate - v_alarmdate >= to_number(v_endnum)/(24 * 60) then
                v_degree := 3;
             --�������������Ϊ2 ����
             else
                 v_degree := 2;
             end if;
        end if;

        --���¶�Ӧ�ĸ澯��¼���޸ļ���
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
  --�ն˻��ϴ�״̬��������sh_log_termstatus���в����¼(�������Ѵ���)
  --select status from sh_log_termstatus where ipaddr=v_ipaddr and statusdate > sysdate - 5 / (24 * 60)��ȡ�ն˻��ո��ϴ���״̬
  --��������д����5���ӣ���ʵ���Ը��̣�����4���ӡ�3���ӣ�Ŀ�ľ���ȡ�ող���ļ�¼
  --select * from sh_alarm_info where EXCEPTIONTYPE NOT IN (select status from sh_log_termstatus where ipaddr=v_ipaddr and statusdate > sysdate - 5 / (24 * 60)) AND TERMID = v_termid AND UNCHAIN = v_status
  --ȡ�ն˻���δ����ĸ澯��¼�����ø澯ʵ���Ѳ�����(����֮ǰ��301�澯�������һ���ϴ���0000��˵��301�Ѿ�ʵ�ʽ����)
  --��������ѯ�����ļ�¼����ѭ������
  for cl in (select * from sh_alarm_info where EXCEPTIONTYPE NOT IN (select status from sh_log_termstatus where ipaddr=v_ipaddr and statusdate > sysdate - 5 / (24 * 60)) AND TERMID = v_termid AND UNCHAIN = v_status) loop
        --�ڸ澯������в�ѯ�ø澯���Ͷ�Ӧ�ļ�¼
        select count(*) into v_count from sh_alarm_rule where exceptiontype=cl.exceptiontype and status = 1;
        --����У�ȡ�澯����ʱ��ķָ���
        if v_count > 0 then
           select nvl(startnum, '') into v_startnum from sh_alarm_rule where exceptiontype=cl.exceptiontype and status = 1;
           select nvl(endnum, '') into v_endnum from sh_alarm_rule where exceptiontype=cl.exceptiontype and status = 1;
        --���û�У�ʹ��Ĭ�Ϲ���ȡ�澯����ʱ��ķָ���
        else
            select startnum into v_startnum from sh_alarm_rule where exceptiontype='ALL' and status = 1;
            select endnum into v_endnum from sh_alarm_rule where exceptiontype='ALL' and status = 1;
        end if;
        --ȡ�ø澯������ʱ��
        select alarmdate into v_alarmdate from sh_alarm_info where alarminfoid=cl.alarminfoid;
        --�õ�ǰʱ����澯����ʱ����бȽϣ��������ʱ��С�ڷָ���1���򼶱�Ϊ1 ��ͨ
        if sysdate - v_alarmdate < to_number(v_startnum)/(24 * 60) then
           v_degree := 1;
        else
             --�õ�ǰʱ����澯����ʱ����бȽϣ��������ʱ����ڵ��ڷָ���2���򼶱�Ϊ3 ����
             if sysdate - v_alarmdate >= to_number(v_endnum)/(24 * 60) then
                v_degree := 3;
             --�������������Ϊ2 ����
             else
                 v_degree := 2;
             end if;
        end if;

        --���¸ø澯��¼�����ü���Ϊ�ղż�����ļ��𡢽��ʱ��Ϊ��ǰʱ�䡢���״̬Ϊ1
        UPDATE sh_alarm_info SET alarmlevel = v_degree, UNCHAINDATE = SYSDATE, UNCHAIN = 1 WHERE alarminfoid=cl.alarminfoid;
        commit;
    end loop;
end proc_sh_updatealarminfo;
/