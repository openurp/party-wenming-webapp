[#ftl]
[@b.head/]
[#include "/org/beangle/ems/security/web/action/status.ftl"/]
[#include "/org/openurp/webapp/apps/party/wenming/comm.ftl"/]
<script type="text/javascript">
  bg.ui.load("tabletree");
</script>
[@b.grid  items=assessItemGroups var="assessItemGroup" sortable="false"]
  [@b.gridbar]
    bar.addItem("${b.text("action.new")}",action.add());
    bar.addItem("${b.text("action.modify")}",action.edit());
    bar.addItem("${b.text("action.delete")}",action.remove("删除时，会级联删除对应的所有子分类和指标，确认删除?"));
  [/@]
  [@b.row]
    [@b.boxcol /]
    [@b.treecol title="common.title" width="70%"]
      [@b.a href="assess-item?assessItemGroupId=${assessItemGroup.id}" target="_blank"]${assessItemGroup.indexno} ${assessItemGroup.name}[/@]
    [/@]
    [@b.col width="30%" property="schema.name" title="相关方案"][/@]
  [/@]
[/@]
[@b.foot/]