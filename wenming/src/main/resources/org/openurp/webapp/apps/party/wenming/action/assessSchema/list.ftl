[#ftl]
[@b.head/]
[#include "/org/beangle/ems/security/web/action/status.ftl"/]
[#include "/org/openurp/webapp/apps/party/wenming/comm.ftl"/]

[@b.grid  items=assessSchemas var="assessSchema" sortable="false"]
  [@b.gridbar]
    bar.addItem("${b.text("action.add")}",action.add());
    bar.addItem("${b.text("action.modify")}",action.edit());
    bar.addItem("维护指标",action.single("item"));
    bar.addItem("${b.text("action.delete")}",action.remove("删除时，会级联删除对应的所有子角色，确认删除?"));
  [/@]
  [@b.row]
    [@b.boxcol width="5%" /]
    [@b.col width="20%" property="name" title="方案名称"][@b.a href="!info?id=${assessSchema.id}"]${assessSchema.name}[/@][/@]
    [@b.col width="60%" property="departs" title="包含部门"]
    [#list assessSchema.departs?sort_by("code") as depart]${depart.name}&nbsp;[/#list]
    [/@]
    [@b.col width="8%" property="forTeaching" title="适用对象"]${assessSchema.forTeaching?string("教学部门","职能部门")}[/@]
    [@b.col width="7%" property="createdAt" title="common.updatedAt"]${(assessSchema.updatedAt?date)!}[/@]
  [/@]
[/@]
[@b.foot/]