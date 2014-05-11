[#ftl]
[@b.head/]
[@b.messages slash="3"/]
[#if assessApply??]
[@b.toolbar title='基本信息']
    function edit(){document.getElementById("apply_edit").click();}
    function submit_apply(){ if(confirm("提交后不能修改，确认提交？")) document.getElementById("apply_submit").click(); }
    [#if editable]
    bar.addItem("${b.text("action.edit")}",edit);
    [/#if]
    [#if submitable]
    bar.addItem("${b.text("action.submit")}",submit_apply);
    [/#if]
[/@]
<div>
  [@b.a id="apply_edit" style="display:none" href="!edit?assessApply.id=${assessApply.id}"]修改[/@]
  [@b.a id="apply_submit" style="display:none" href="!submit?assessApply.id=${assessApply.id}"]提交[/@]
</div>
<table class="infoTable">
  <tr>
   <td class="title">状态:</td>
   <td class="content">${assessApply.state.description}</td>
   <td class="title">提交人:</td>
   <td class="content"> ${assessApply.submitBy.name}(${assessApply.submitBy.fullname})</td>
   <td class="title">更新时间:</td>
   <td class="content">${assessApply.updatedAt?string("YYYY-MM-dd HH:mm")}</td>
  </tr>
  <tr>
   <td class="title">创建活动及其效果概要:</td>
   <td class="content" colspan="5" width="80%"> ${assessApply.activitySummary}</td>
  </tr>
  <tr>
   <td class="title">文明创建特色工作概要:</td>
   <td class="content" colspan="5" >${assessApply.wenmingSummary}</td>
  </tr>
  <tr>
   <td class="title" >详实材料:</td>
   <td class="content" colspan="5" >${assessApply.detail}</td>
  </tr>
  <tr>
   <td class="title" >支撑材料:</td>
   <td class="content" colspan="5">[#if assessApply.attachment??][@b.a target="_blank" href="attachment?path=${assessApply.attachment.filePath}&name=${assessApply.attachment.name?url('utf-8')}"]${assessApply.attachment.name}[/@][/#if]</td>
  </tr>
 </table>
[@b.toolbar title='加分材料'/]
[@b.grid  items=assessApply.bonuses var="bonus"]
  [@b.gridbar]
   [#if editable]
    bar.addItem("${b.text("action.new")}",action.add());
    bar.addItem("${b.text("action.modify")}",action.edit());
    bar.addItem("${b.text("action.delete")}",action.remove("确认删除?"));
    [/#if]
  [/@]
  [@b.row]
    [#if editable][@b.boxcol width="5%"/]  [/#if]
    [@b.col property="item.bonusType.name" width="20%" title="加分类型"/]
    [@b.col property="item.name" width="40%" title="加分内容"/]
    [@b.col property="score" width="10%" title="分值"/]
    [@b.col width="25%" title="附件"/]
  [/@]
[/@]

[#else]
<p>
    [@b.a href="!edit?assessApply.session.id=${Parameters['session.id']}" ]还没有进行申报，现在申报[/@]
</p>
[/#if]
[@b.foot/]
