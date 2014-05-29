[#ftl]
[@b.head/]
[@b.messages slash="3"/]
[#if assessApply??]
[@b.toolbar title='基本信息']
    function edit(){document.getElementById("apply_edit").click();}
    function submit_apply(){ if(confirm("提交后不能修改，确认提交？")) document.getElementById("apply_submit").click(); }
    [#if editable && assessSession.opened]
    bar.addItem("${b.text("action.edit")}",edit);
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
   <td class="title">创建活动及其效果:</td>
   <td class="content" colspan="5" width="80%"> ${assessApply.activitySummary}</td>
  </tr>
  <tr>
   <td class="title">文明创建特色:</td>
   <td class="content" colspan="5" >${assessApply.wenmingSummary}</td>
  </tr>
  <tr>
   <td class="title" >详实材料:</td>
   <td class="content" colspan="5" >${assessApply.detail}</td>
  </tr>
  <tr>
   <td class="title" >支撑材料:</td>
   <td class="content" colspan="5">[#if assessApply.attachment??][@b.a target="_blank" href="../attachment?path=${assessApply.attachment.filePath}&name=${assessApply.attachment.name?url('utf-8')}"]${assessApply.attachment.name}[/@][/#if]</td>
  </tr>
  <tr>
   <td class="title" >自评总分:</td>
   <td class="content">${(selfAssess.totalScore)!}</td>
  </tr>
 </table>
 [@b.div id="bonus_panel"]
    [#assign score=0/]
    [#list assessApply.bonuses as b]
    [#assign score=score + b.score/]
    [/#list]
   [@b.toolbar title='加分材料---共计${score}分']
     function edit_attachment(){document.getElementById("apply_edit_bonus").click();}
     [#if editable && assessSession.opened]bar.addItem("修改",edit_attachment);[/#if]
   [/@]
  [@b.a id="apply_edit_bonus" style="display:none" href="assess-bonus!search?assessBonus.apply.id=${assessApply.id}"]修改[/@]
  [@b.div href="assess-bonus!info?assessBonus.apply.id=${assessApply.id}"/]
 [/@]
[#else]
<p>
    [#if assessSession.opened][@b.a href="!edit?assessApply.session.id=${Parameters['session.id']}" ]还没有进行申报，现在申报[/@]
    [#else]现在不在申报时间内[/#if]
</p>
[/#if]
[@b.foot/]
