[#ftl]
[@b.head/]
[@b.messages slash="3"/]
[#if assessApply??]
[@b.toolbar title='文明单位申报']
    
    bar.addBack("${b.text("action.back")}");
    [#if editable]
    bar.addItem("修改","!edit?assessApply.id=${assessApply.id}" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text"]);
    [/#if]
    [#if submitable]
    bar.addItem("提交",[@b.a href="!submit?assessApply.id=${assessApply.id}" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text"]&nbsp&nbsp&nbsp提交&nbsp&nbsp&nbsp[/@]
    [/#if]
    
[/@]
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
  <tr>
</table>

[@b.a href="!editBonus?assessApply.id=${assessApply.id}" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text"]&nbsp&nbsp&nbsp修改加分材料&nbsp&nbsp&nbsp[/@]
[#else]
<p>
    [@b.a href="!edit?assessApply.session.id=${Parameters['session.id']}" ]还没有进行申报，现在申报[/@]
</p>
[/#if]
[@b.foot/]
