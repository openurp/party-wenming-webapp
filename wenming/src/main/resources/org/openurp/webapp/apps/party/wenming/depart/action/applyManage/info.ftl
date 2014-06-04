[#ftl]
[@b.head/]
[@b.messages slash="3"/]
[@b.toolbar title='基本信息']
    function approve(){ if(confirm("提交后不能修改，确认提交？")) document.getElementById("apply_approved").click(); }
    function disApprove(){ 
      var text=prompt("请填写审批意见");
      if(text != null && text != ""){
        $("#apply_advise").val(text);
        document.getElementById("apply_disapproved").click(); 
      }else{
      alert("请填写审批意见");
      }
    }
    [#if editable]
    bar.addItem("审批通过",approve);
    bar.addItem("退回修改",disApprove);
    [/#if]
[/@]
<div>
  [@b.form name="apply_manage_form"]
  <input type="hidden" name="advise" id="apply_advise"/>
  [@b.submit id="apply_approved" style="display:none" value="审批通过" action="!approve?assessApply.id=${assessApply.id}"/]
  [@b.submit id="apply_disapproved" style="display:none" value="审批不通过" action="!disApprove?assessApply.id=${assessApply.id}"/]
  [/@]
</div>
<table class="infoTable">
  <tr>
   <td class="title">状态:</td>
   <td class="content">${assessApply.state.description}</td>
   <td class="title">提交人:</td>
   <td class="content"> ${assessApply.submitBy.name}(${assessApply.submitBy.fullname})</td>
   <td class="title">部门审核人:</td>
   <td class="content">[#if assessApply.auditBy??]${assessApply.auditBy.name}(${assessApply.auditBy.fullname})[/#if]</td>
   <td class="title">更新时间:</td>
   <td class="content">${assessApply.updatedAt?string("YYYY-MM-dd HH:mm")}</td>
  </tr>
  <tr>
   <td class="title">创建活动及其效果:</td>
   <td class="content" colspan="7" width="80%"> ${assessApply.activitySummary}</td>
  </tr>
  <tr>
   <td class="title">文明创建特色:</td>
   <td class="content" colspan="7" >${assessApply.wenmingSummary}</td>
  </tr>
  <tr>
   <td class="title" >详实材料:</td>
   <td class="content" colspan="7" >${assessApply.detail}</td>
  </tr>
  <tr>
   <td class="title" >支撑材料:</td>
   <td class="content" colspan="7">[#if assessApply.attachment??][@b.a target="_blank" href="../attachment?path=${assessApply.attachment.filePath}&name=${assessApply.attachment.name?url('utf-8')}"]${assessApply.attachment.name}[/@][/#if]</td>
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
   [@b.toolbar title='加分材料---共计${score}分'/]
   [@b.div href="assess-bonus!info?assessBonus.apply.id=${assessApply.id}"/]
 [/@]
[@b.foot/]
