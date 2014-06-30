[#ftl]
[@b.head/]
[#include "/org/beangle/ems/security/web/action/status.ftl"/]
[#include "/org/openurp/webapp/apps/party/wenming/comm.ftl"/]

[@b.grid  items=supervisors var="supervisor" sortable="false"]
  [@b.gridbar]
    bar.addItem("${b.text("action.new")}",action.add());
    bar.addItem("${b.text("action.modify")}",action.edit());
    bar.addItem("${b.text("action.delete")}",action.remove("确认删除?"));
  [/@]
  [@b.row]
    [@b.boxcol /]
    [@b.col width="15%" property="name" title="系统登录账户"][@b.a href="!info?id=${supervisor.id}"]${supervisor.name}[/@][/@]
    [@b.col width="15%" property="fullname" title="真实姓名"][/@]
    [@b.col width="60%" property="remark" title="说明"][/@]
    [@b.col width="10%" property="expiredOn" title="过期日期"]${(supervisor.expiredOn?date)!}[/@]
  [/@]
[/@]
[@b.foot/]