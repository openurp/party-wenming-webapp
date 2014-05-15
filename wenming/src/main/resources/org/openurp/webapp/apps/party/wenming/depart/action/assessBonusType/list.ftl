[#ftl]
[@b.head/]
[#include "/org/beangle/ems/security/web/action/status.ftl"/]
[#include "/org/openurp/webapp/apps/party/wenming/comm.ftl"/]

[@b.grid  items=assessBonusTypes var="assessBonusType" sortable="false"]
  [@b.gridbar]
    bar.addItem("${b.text("action.new")}",action.add());
    bar.addItem("${b.text("action.modify")}",action.edit());
    bar.addItem("${b.text("action.delete")}",action.remove("删除时，会级联删除对应的所有子角色，确认删除?"));
  [/@]
  [@b.row]
    [@b.boxcol /]
    [@b.col width="15%" property="code" title="加分类型代码"][@b.a href="!info?id=${assessBonusType.id}"]${(assessBonusType.code)!}[/@]
    [@b.col width="20%" property="name" title="加分类型名称"]${assessBonusType.name}[/@][/@]
  [/@]
[/@]
[@b.foot/]