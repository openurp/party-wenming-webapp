[#ftl]
[@b.head/]
[#include "/org/openurp/webapp/apps/party/wenming/comm.ftl"/]

[@b.grid  items=selfAssesss var="selfAssess" sortable="false"]
  [@b.gridbar]
    [#if addable??]
    bar.addItem("${b.text("action.new")}",action.add());
    [/#if]
    [#if modifyable??]
    bar.addItem("${b.text("action.modify")}",action.method("edit"));
    [/#if]
    [#--
    bar.addItem("${b.text("action.delete")}",action.remove("确认删除?"));
    --]
  [/@]
  [@b.row]
    [#--
    [@b.boxcol /]
    --]
    [@b.col width="20%" property="department.name" title="单位名称"][/@]
    [@b.col width="20%" property="session.name" title="所属批次"][@b.a href="!info?selfAssess.id=${selfAssess.id}"]${selfAssess.session.name}[/@][/@]
    [@b.col width="20%" property="assessAt" title="自评时间"]${selfAssess.assessAt?date}[/@]
    [@b.col width="20%" property="assessBy.fullname" title="自评人员"][/@]
    [@b.col width="20%" property="totalScore" title="自评总分"][/@]
    [@b.col width="20%" property="state.description" title="审核状态"][/@]
  [/@]
[/@]
[@b.foot/]