[#ftl]
[@b.head/]
  [@b.form action="!save"]
    [#if malist?size gt 0]
      [#include "../selfAssess/selfAssessTable.ftl"/]
      <div style="text-align:center; padding:30px;" class="footdiv">
        <input type="hidden" name="selfAssess.id" value="${selfAssess.id!}" />
        <input type="hidden" name="save" value="0" id="saveIpt"/>
        <input type="hidden" name="passed" value="0" id="passedIpt"/>
        [@b.submit value="action.save" onsubmit="saveAssess"/]
        [@b.submit value="通过" onsubmit="passAssess"/]
        [@b.submit value="不通过" onsubmit="unpassAssess"/]
      </div>
    [#else]
      <p>还未提交自评</p>
    [/#if]
  [/@]
  <script>
    function saveAssess(){
      $("#saveIpt").val("1");
      return true;
    }
    
    function passAssess(){
      if(confirm("是否确定审核通过？")){
        $("#passedIpt").val("1");
        return true;
      }
      return false;
    }
    
    function unpassAssess(){
      if(confirm("是否确定审核不通过？")){
        return true;
      }
      return false;
    }
  </script>
[@b.foot/]