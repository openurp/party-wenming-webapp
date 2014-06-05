[#ftl]
[@b.head/]
[@b.toolbar title="好人好事编辑"]bar.addBack();[/@]
[@b.form action="!save" theme="list" enctype="multipart/form-data"]
  [#if ifAdvise?? && ifAdvise]
    [@b.field label="文明办审核意见"]<span style="color:red; font-size:16px">${goodPerson.auditOpinion}</span>[/@]
  [/#if]
  [#include "editField.ftl"/] 
  [@b.formfoot]
    <input type="hidden" name="goodPerson.id" value="${goodPerson.id!}" />
    <input type="hidden" name="save" value="0" id="saveIpt"/>
    [@b.reset/]
    [@b.submit value="action.save" onsubmit="saveForm"/]
    [@b.submit value="action.submit" onsubmit="submitForm"/]
  [/@]
[/@]
<script>
  function saveForm(){
    $("#saveIpt").val("1");
    return true;
  }
  
  function submitForm(){
    return confirm("提交后不能修改，是否确定提交？")
  }
</script>
[@b.foot/]