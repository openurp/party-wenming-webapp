[#ftl]
[@b.head/]
[@b.toolbar title="文明示范岗编辑"]bar.addBack();[/@]
[@b.form action="!save" theme="list" enctype="multipart/form-data"]
  [#include "editField.ftl"/] 
  [@b.formfoot]
    <input type="hidden" name="goodPost.id" value="${goodPost.id!}" />
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