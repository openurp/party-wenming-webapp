[#ftl]
[#include "/org/openurp/webapp/apps/party/wenming/comm.ftl"/]
[@b.head/]
[@b.grid  items=goodProjects var="goodProject"]
  [@b.gridbar]
    [#if editable]
    bar.addItem("${b.text("action.new")}",action.add());
    bar.addItem("${b.text("action.modify")}",action.edit());
    bar.addItem("中期总结",action.single("middleSummary"));
    bar.addItem("终期总结",action.single("finalSummary"));
    bar.addItem("${b.text("action.delete")}",action.remove("确认删除?"));
    [/#if]
  [/@]
  [@b.row]
    [@b.boxcol /]
    [@b.col width="20%" property="name" title="项目名称" align="left"][@b.a href="!info?id=${goodProject.id}"]${goodProject.name}[/@][/@]
    [@b.col width="15%" property="department.name" title="选送单位"][/@]
    [@b.col width="20%" title="中期总结（审核状态）"]
      [#if (goodProject.middleSummary.attachment)??]
        [@b.a target="_blank" href="../attachment?path=${goodProject.middleSummary.attachment.filePath}&name=${goodProject.middleSummary.attachment.name?url('utf-8')}"]下载（${goodProject.middleSummary.state.description}）[/@]
      [#else]未上传
      [/#if]
    [/@]
    [@b.col width="20%" title="终期总结(审核状态)"]
      [#if (goodProject.finalSummary.attachment)??]
        [@b.a target="_blank" href="../attachment?path=${goodProject.finalSummary.attachment.filePath}&name=${goodProject.finalSummary.attachment.name?url('utf-8')}"]下载（${goodProject.finalSummary.state.description}）[/@]
      [#else]未上传
      [/#if]
    [/@]
    [@b.col width="10%" property="attachment.name" title="详细材料"]
      [#if goodProject.attachment??]
        [@b.a target="_blank" href="../attachment?path=${goodProject.attachment.filePath}&name=${goodProject.attachment.name?url('utf-8')}"]下载[/@]
      [#else]未上传
      [/#if]
    [/@]
    [@b.col width="15%" property="state" title="审核情况"]${goodProject.state.description}[/@]
  [/@]
[/@]
[@b.foot/]