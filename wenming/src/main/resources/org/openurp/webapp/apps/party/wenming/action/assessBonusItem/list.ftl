[#ftl]
[@b.head/]
[#include "/org/beangle/ems/security/web/action/status.ftl"/]
[#include "/org/openurp/webapp/apps/party/wenming/comm.ftl"/]

[@b.grid  items=assessBonusItems var="assessBonusItem" sortable="false"]
  [@b.gridbar]
    bar.addItem("${b.text("action.new")}",action.add());
    bar.addItem("${b.text("action.modify")}",action.edit());
    bar.addItem("${b.text("action.delete")}",action.remove("确认删除?"));
  [/@]
  [@b.row]
    [@b.boxcol /]
    [@b.col width="15%" property="name" title="加分项目名称"][@b.a href="!info?id=${assessBonusItem.id}"]${assessBonusItem.name}[/@][/@]
    [@b.col width="15%" property="bonusType.name" title="加分项目类型"][/@]
    [@b.col width="15%" property="method" title="加分方式"][/@]
    [@b.col width="15%" property="schema.name" title="所属方案"][/@]
  [/@]
[/@]
[@b.foot/]