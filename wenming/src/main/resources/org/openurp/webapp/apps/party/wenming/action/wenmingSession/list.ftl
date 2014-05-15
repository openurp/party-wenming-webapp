[#ftl]
[@b.head/]
[#include "/org/beangle/ems/security/web/action/status.ftl"/]
[#include "/org/openurp/webapp/apps/party/wenming/comm.ftl"/]

[@b.grid  items=wenmingSessions var="wenmingSession" sortable="false"]
  [@b.gridbar]
    bar.addItem("${b.text("action.new")}",action.add());
    bar.addItem("${b.text("action.modify")}",action.edit());
    bar.addItem("${b.text("action.delete")}",action.remove("确认删除?"));
  [/@]
  [@b.row]
    [@b.boxcol /]
    [@b.col width="15%" property="name" title="批次名称"][@b.a href="!info?id=${wenmingSession.id}"]${wenmingSession.name}[/@][/@]
    [@b.col width="20%" property="beginOn" title="开始时间"]${(wenmingSession.beginOn?datetime)!}[/@]
    [@b.col width="20%" property="endOn" title="结束时间"]${(wenmingSession.endOn?datetime)!}[/@]
    [@b.col width="20%" property="voteBeginOn" title="投票开始时间"]${(wenmingSession.voteBeginOn?datetime)!}[/@]
    [@b.col width="20%" property="voteEndOn" title="投票结束时间"]${(wenmingSession.voteEndOn?datetime)!}[/@]
  [/@]
[/@]
[@b.foot/]