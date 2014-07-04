[#ftl]
[@b.head/]
[#include "/org/beangle/ems/security/web/action/status.ftl"/]
[#include "/org/openurp/webapp/apps/party/wenming/comm.ftl"/]

[@b.grid  items=voteSessions var="voteSession" sortable="false"]
  [@b.gridbar]
    bar.addItem("${b.text("action.new")}",action.add());
    bar.addItem("${b.text("action.modify")}",action.edit());
    bar.addItem("${b.text("action.delete")}",action.remove("确认删除?"));
  [/@]
  [@b.row]
    [@b.boxcol /]
    [@b.col width="25%" title="相关测评批次"]${voteSession.session.name}[/@]
    [@b.col width="25%" property="name" title="批次名称"][@b.a href="!info?id=${voteSession.id}"]${voteSession.name}[/@][/@]
    [@b.col width="25%" property="beginOn" title="投票开始时间"]${(voteSession.beginOn?datetime)!}[/@]
    [@b.col width="25%" property="endOn" title="投票结束时间"]${(voteSession.endOn?datetime)!}[/@]
  [/@]
[/@]
[@b.foot/]