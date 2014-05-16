[#ftl]
[@b.head/]
[@b.form action="!save" theme="list"]
  [#if !selfAssess??][#assign selfAssess=malist[0]/][/#if]
  [#include "selfAssessTable.ftl"/]
  <div style="text-align:center; padding:30px;" class="footdiv">
    <input type="hidden" name="selfAssess.id" value="${selfAssess.id!}" />
    <input type="hidden" name="save" value="0" id="saveIpt"/>
    [@b.submit value="action.save" onsubmit="saveSelfAssess"/]
    [@b.submit value="action.submit" onsubmit="submitSelfAssess"/]
  </div>
[/@]
<script>
  function saveSelfAssess(){
    if(isallselected()){
      $("#saveIpt").val("1");
      return true;
    }
    return false;
  }
  
  function submitSelfAssess(){
    if(isallselected()){
      return confirm("提交后不能修改，是否确定提交？")
    }
    return false;
  }
</script>
[@b.foot/]