[#ftl]
[@b.head/]
[#include "/org/beangle/ems/security/web/action/status.ftl"/]
[#include "/org/openurp/webapp/apps/party/wenming/comm.ftl"/]

[@b.grid  items=assessSessions var="assessSession" sortable="false"]
  [@b.gridbar]
    bar.addItem("${b.text("action.new")}",action.add());
    bar.addItem("${b.text("action.modify")}",action.edit());
    bar.addItem("${b.text("action.delete")}",action.remove("确认删除?"));
  [/@]
  [@b.row]
    [@b.boxcol /]
    [@b.col width="15%" property="name" title="批次名称"][@b.a href="!info?id=${assessSession.id}"]${assessSession.name}[/@][/@]
    [@b.col width="14%" property="beginOn" title="测评开始时间"]${(assessSession.beginOn?datetime)!}[/@]
    [@b.col width="14%" property="endOn" title="测评结束时间"]${(assessSession.endOn?datetime)!}[/@]
    [@b.col width="45%" property="schemas" title="关联方案"]
    [#list assessSession.schemas as schema]${schema.name}[#if schema_has_next],[/#if][/#list]
    [/@]
    [@b.col width="7%" property="enabled" title="是否有效"][@c.enabled assessSession.enabled/][/@]
  [/@]
[/@]
[@b.foot/]