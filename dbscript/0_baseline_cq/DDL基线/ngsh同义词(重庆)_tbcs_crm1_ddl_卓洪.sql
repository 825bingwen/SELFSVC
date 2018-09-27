--drop原来的实体表
/*
drop table sh_info_operator;
drop table sh_organization;
drop table sh_organization_child;
drop table sh_info_regionlist;
*/

create or replace synonym sh_info_operator for tbcs.operator;
create or replace synonym sh_organization for tbcs.organization;
create or replace synonym sh_organization_child for icdpub.T_UCP_ORGACHILD;
create or replace synonym sh_info_regionlist for common_dict.SA_DB_REGION_LIST;