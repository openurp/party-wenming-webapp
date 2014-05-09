[#ftl]
[@b.head/]
[#include "/org/beangle/ems/security/web/action/status.ftl"/]
[#include "/org/openurp/webapp/apps/party/wenming/comm.ftl"/]

[@b.grid  items=assessSessions var="assessSession" sortable="false"]
  [@b.gridbar]
    bar.addItem("${b.text("action.new")}",action.add());
    bar.addItem("${b.text("action.modify")}",action.edit());
    bar.addItem("${b.text("action.delete")}",action.remove("删除时，会级联删除对应的所有子角色，确认删除?"));
  [/@]
  [@b.row]
    [@b.boxcol /]
    [@b.col width="10%" property="name" title="批次名称"][@b.a href="!info?id=${assessSession.id}"]${assessSession.name}[/@][/@]
    [@b.col width="15%" property="beginOn" title="测评开始时间"]${(assessSession.beginOn?datetime)!}[/@]
    [@b.col width="15%" property="endOn" title="测评结束时间"]${(assessSession.endOn?datetime)!}[/@]
    [@b.col width="15%" property="voteBeginOn" title="投票开始时间"]${(assessSession.voteBeginOn?datetime)!}[/@]
    [@b.col width="15%" property="voteEndOn" title="投票结束时间"]${(assessSession.voteEndOn?datetime)!}[/@]
    [@b.col width="10%" property="createdAt" title="common.createdAt"]${(assessSession.createdAt?date)!}[/@]
    [@b.col width="10%" property="enabled" title="是否有效"][@c.enabled assessSession.enabled/][/@]
    [@b.col width="10%" title="操作"][@b.a href="!edit?assessSession.id=${assessSession.id}"]编辑[/@][/@]
  [/@]
[/@]
[@b.foot/]