[#ftl]
[@b.head/]
[@b.toolbar title="优秀项目审核"]bar.addBack();[/@]
<style>
.audit_message{color:red; font-size:16px}
form.listform label.title {
width: 130px;
}
</style>
[@b.form id="goodProjectFormid" name="goodProjectFormid" action="!save" theme="list" enctype="multipart/form-data"]
  [#include "../apply/editField.ftl"/] 
  [#if ifApplyAudit?? && ifApplyAudit]
    [@b.field label="相关支撑材料"]
    <div style="padding-left:110px;">
    [#if goodProject.attachment??]
    [@b.a target="_blank" href="../attachment?path=${goodProject.attachment.filePath}&name=${goodProject.attachment.name?url('utf-8')}"]${(goodProject.attachment.name)!}下载[/@]<font class="audit_message">（请进行申报审核）</font>
    [/#if]
    </div>
    &nbsp;
    [/@]
  [/#if]
  [#if ifFinalSummary?? && ifFinalSummary]
    [#if goodProject.attachment??]
    [@b.field label="相关支撑材料"]
    [@b.a target="_blank" href="../attachment?path=${goodProject.attachment.filePath}&name=${goodProject.attachment.name?url('utf-8')}"]${(goodProject.attachment.name)!}下载[/@]
    &nbsp;
    [/@]
    [/#if]
    [@b.field label="中期审核材料"]
    [@b.a target="_blank" href="../attachment?path=${goodProject.middleSummary.attachment.filePath}&name=${goodProject.middleSummary.attachment.name?url('utf-8')}"]${(goodProject.middleSummary.attachment.name)!}下载[/@]
    [/@]
    [@b.textarea label="项目内容及成效" cols="50" rows="4" name="goodProject.finalSummary.content" value=(goodProject.finalSummary.content)! maxlength="1000" required="true"/]
    [@b.radios label="是否参加学年末评选"  name="goodProject.finalSummary.forAssess" value=goodProject.finalSummary.forAssess items="1:common.yes,0:common.no"/]
    [@b.field label="终期审核材料"]
    [@b.a target="_blank" href="../attachment?path=${goodProject.finalSummary.attachment.filePath}&name=${goodProject.finalSummary.attachment.name?url('utf-8')}"]${(goodProject.finalSummary.attachment.name)!}下载[/@]<font class="audit_message">（请下载附件进行终期审核）</font>
    [/@]
  [/#if]
  [@b.formfoot]
    <input type="hidden" name="goodProject.id" value="${goodProject.id!}" />
    <input type="hidden" name="save" value="0" id="saveIpt"/>
    <input type="hidden" name="passed" value="0" id="passedIpt"/>
    [#if alterable?? && alterable]
      [@b.reset/]
      [@b.submit value="action.save" onsubmit="saveForm"/]
    [/#if]
    <input type="hidden" name="auditOpinion" id="auditOpinion"/>
    [@b.submit value="通过" onsubmit="passForm"/]
    [@b.submit value="不通过" onsubmit="unpassForm"/]
  [/@]
[/@]
<script>
  function saveForm(){
    $("#saveIpt").val("1");
    return true;
  }
  
  function passForm(){
    if(confirm("是否确定审核通过？")){
      $("#passedIpt").val("1");
      return true;
    }
    return false;
  }
  
  function unpassForm(){ 
    var text=prompt("请填写审批意见");
    if(text != null && text != ""){
      $("#auditOpinion").val(text);
      return true;
    }else{
    alert("请填写审批意见");
    }
  }
  
  [#if alterable?? && !alterable]
  $("#goodProjectFormid").find("input,textarea").attr("readonly","readonly").removeAttr("onfocus").click(function(){return false;});
  $("#goodProjectFormid").find("input:file").remove();
  [/#if]
</script>
[@b.foot/]