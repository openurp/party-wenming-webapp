[#ftl]
[@b.head/]
[@b.toolbar title="优秀项目编辑"]bar.addBack();[/@]
[@b.form action="!save" theme="list" enctype="multipart/form-data"]
  [#if ifAdvise?? && ifAdvise]
    [@b.field label="文明办审核意见"]<span style="color:red; font-size:16px">${goodProject.auditOpinion!}</span>[/@]
  [/#if]
  [#include "editField.ftl"/] 
  [@b.field label="相关支撑材料"]<input name="attachment" type="file"/>（上传文件请控制在10M以内，如果有多份附件请打成压缩包上传）[/@]
  &nbsp;
  [@b.formfoot]
    <input type="hidden" name="goodProject.id" value="${goodProject.id!}" />
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