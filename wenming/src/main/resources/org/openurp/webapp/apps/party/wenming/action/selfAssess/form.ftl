[#ftl]
[@b.head/]
[@b.form action="!save" theme="list"]
  [#if !selfAssess??][#assign selfAssess=malist[0]/][/#if]
  [#include "/org/openurp/webapp/apps/party/wenming/action/selfAssess/selfAssessTable.ftl"/]
  <div style="text-align:center; padding:30px;" class="footdiv">
    <input type="hidden" name="selfAssess.id" value="${selfAssess.id!}" />
    <input type="hidden" name="save" value="0" id="saveIpt"/>
    [@b.submit value="action.save" onsubmit="saveSelfAssess"/]
    [@b.submit value="action.submit" onsubmit="submitSelfAssess"/]
  </div>
[/@]
<script>
  function saveSelfAssess(){
    if(isallnumebr()){
      $("#saveIpt").val("1");
      return true;
    }
    return false;
  }
  
  function submitSelfAssess(){
    if(isallnumebr()){
      return confirm("提交后不能修改，是否确定提交？")
    }
    return false;
  }

  function isallnumebr(){
    var haserror = false;
    var toobig = false;
    $("#selfAssessTable .score").each(function (){
      if(!this.value.match(/^\d+(\.\d+)?$/)){
        this.focus();
        haserror = true;
      }else{
        var max = $(this).parent().prev().html() * 1;
        if(this.value * 1 > max){
          this.focus();
          toobig = true;
        }
      }
    });
    if(haserror || toobig){
      alert("请输入正确的分值");
    }
    return !(haserror || toobig);
  }
</script>
[@b.foot/]