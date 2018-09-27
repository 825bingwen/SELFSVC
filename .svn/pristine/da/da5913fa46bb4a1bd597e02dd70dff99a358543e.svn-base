declare
  V_OWNER      varchar(128);
  V_TABLE_NAME varchar(128);
  mysqlstr     varchar(1024);
  condstr      varchar(512);
begin
  V_OWNER      := 'srp';
  V_TABLE_NAME := 'sp_business_order';
  condstr      := 'where order_id=''Y001_ADD''';

  WITH DM_TBLS AS(
    SELECT DECODE(T.DATA_TYPE,
                  'CHAR',
                  '''''''''||' || T.COLUMN_NAME || '||''''''''',
                  'VARCHAR2',
                  '''''''''||' || T.COLUMN_NAME || '||''''''''',
                  'DATE',
                  '''TO_DATE(''''''||to_char(' || T.COLUMN_NAME ||
                  ',''YYYY-MM-DD HH24:MI:SS'')||'''''',''''YYYY-MM-DD HH24:MI:SS'''')''',
                  T.COLUMN_NAME) COLUMN_NAME,
           T.COLUMN_ID,
           T.TABLE_NAME,
           T.OWNER,
           T.COLUMN_NAME COL1,
           LAG(COLUMN_ID) OVER(PARTITION BY T.TABLE_NAME ORDER BY T.COLUMN_ID) RN
      FROM DBA_TAB_COLUMNS T
     WHERE T.TABLE_NAME = UPPER(V_TABLE_NAME)
       AND T.OWNER = UPPER(V_OWNER))
      SELECT 'SELECT ''INSERT INTO ' || V_OWNER || '.' ||
       'V_TABLE_NAME' || ' VALUES (''||' ||
       REPLACE(SUBSTRB(MAX(CHR(64 + LEVEL) ||
                           SYS_CONNECT_BY_PATH(T.COLUMN_NAME,
                                               '#')),
                       3),'#','||'',''||') || '||'');'' TEXT FROM ' || T.OWNER || '.' ||
       T.TABLE_NAME || ' ' || condstr || ';'
        into mysqlstr
        FROM DM_TBLS T
       START WITH RN IS NULL
      CONNECT BY RN = PRIOR COLUMN_ID
             AND TABLE_NAME = PRIOR TABLE_NAME
       GROUP BY T.OWNER, T.TABLE_NAME;

  dbms_output.put_line(substr(mysqlstr, 0, 254));
  dbms_output.put_line(substr(mysqlstr, 255, 254));
end;
