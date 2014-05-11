[#ftl]
[@b.head/]
[#include "/org/beangle/ems/security/web/action/status.ftl"/]
[#include "/org/openurp/webapp/apps/party/wenming/comm.ftl"/]

[@b.grid  items=assessItemGroups var="assessItemGroup" sortable="false"]
  [@b.gridbar]
    bar.addItem("${b.text("action.new")}",action.add());
    bar.addItem("${b.text("action.modify")}",action.edit());
    bar.addItem("${b.text("action.delete")}",action.remove("删除时，会级联删除对应的所有子分类和指标，确认删除?"));
  [/@]
  [@b.row]
    [@b.boxcol /]
    [@b.col width="20%" property="indexno" title="排序"][/@]
    [@b.col width="40%" property="name" title="名称"][@b.a href="!info?id=${assessItemGroup.id}"]${assessItemGroup.name}[/@][/@]
    [@b.col width="20%" property="parent.name" title="上级分类"][/@]
    [@b.col width="20%" property="schema.name" title="相关方案"][/@]
  [/@]
[/@]
[@b.foot/]