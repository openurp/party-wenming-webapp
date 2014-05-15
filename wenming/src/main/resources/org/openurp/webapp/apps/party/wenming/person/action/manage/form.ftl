[#ftl]
[@b.head/]
[@b.toolbar title="好人好事审核"]bar.addBack();[/@]
[@b.form action="!save" theme="list"]
  [#include "../apply/info.ftl"/] 
  [@b.formfoot]
    <input type="hidden" name="goodPerson.id" value="${goodPerson.id!}" />
    <input type="hidden" name="save" value="0" id="saveIpt"/>
    <input type="hidden" name="passed" value="0" id="passedIpt"/>
    [@b.submit value="通过" onsubmit="passForm"/]
    [@b.submit value="不通过" onsubmit="unpassForm"/]
  [/@]
[/@]
<script>
  function passForm(){
    if(confirm("是否确定审核通过？")){
      $("#passedIpt").val("1");
      return true;
    }
    return false;
  }
  
  function unpassForm(){
    if(confirm("是否确定审核不通过？")){
      return true;
    }
    return false;
  }
</script>
[@b.foot/]