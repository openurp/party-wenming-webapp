[#ftl]
[@b.head/]
  [@b.form action="!save"]
    [#assign partMode=true/]
    [#include "/org/openurp/webapp/apps/party/wenming/assess.ftl"/]
    [@assessTable malist/]
    <div style="text-align:center; padding:30px;" class="footdiv">
      <input type="hidden" name="save" value="0" id="saveIpt"/>
      [@b.submit value="action.save" onsubmit="saveAssess"/]
      [@b.submit value="action.submit" onsubmit="submitAssess"/]
    </div>
  [/@]
  <script>
    function saveAssess(){
      if(isallselected()){
        $("#saveIpt").val("1");
        return true;
      }
      return false;
    }
    
    function submitAssess(){
      if(isallselected() && confirm("提交后不能修改，是否确定提交？")){
        return true;
      }
      return false;
    }
  </script>
[@b.foot/]