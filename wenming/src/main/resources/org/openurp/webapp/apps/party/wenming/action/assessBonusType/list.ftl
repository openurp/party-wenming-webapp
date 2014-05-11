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
    [@b.col width="20%" property="name" title="加分项目类别"][@b.a href="!info?id=${assessBonusType.id}"]${assessBonusType.name}[/@][/@]
    [@b.col width="15%" property="beginOn" title="测评开始时间"]${(assessBonusType.beginOn?datetime)!}[/@]
    [@b.col width="15%" property="endOn" title="测评结束时间"]${(assessBonusType.endOn?datetime)!}[/@]
    [@b.col width="15%" property="voteBeginOn" title="投票开始时间"]${(assessBonusType.voteBeginOn?datetime)!}[/@]
    [@b.col width="15%" property="voteEndOn" title="投票结束时间"]${(assessBonusType.voteEndOn?datetime)!}[/@]
    [@b.col width="10%" property="createdAt" title="common.createdAt"]${(assessBonusType.createdAt?date)!}[/@]
    [@b.col width="10%" property="enabled" title="是否有效"][@c.enabled assessBonusType.enabled/][/@]
  [/@]
[/@]
[@b.foot/]