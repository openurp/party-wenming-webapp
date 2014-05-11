[#ftl]
[@b.head/]
[@b.toolbar title="单位自评"]bar.addBack();[/@]
[@b.form action="!save" title="自评内容" theme="list"]
  <style>
    .footdiv input{margin-right:200px;}
  </style>
  <table id="selfAssessTable" class="gridtable">
    <thead class="gridhead">
      <tr style="height:30px; font-size:16px;">
        <th width="80">指标编号</th>
        <th>指标内容</th>
        <th width="80">指标分值</th>
        <th width="100">自评分值</th>
      </tr>
    </thead>
    <tbody>
      [#list selfAssess.items as v]
        [#if !group?? || v.item.group.id != group.id]
          [#assign group = v.item.group/]
          <tr style="font-size:14px; color:red;">
            <td>${group.indexno}</td>
            <td>${group.name}</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
        [/#if]
        <tr>
          [#assign name="sai${v_index}"/]
          <input type="hidden" name="index" value="${name}"/>
          <input type="hidden" name="${name}.item.id" value="${v.item.id}"/>
          <td>${group.indexno}-${v.item.orderNumber}</td>
          <td>${v.item.content}</td>
          <td>${v.item.score}</td>
          <td align="center"><input name="${name}.score" style="width:80px;" class="score" maxlength="6" value="${v.score!}"/></td>
        </tr>
      [/#list]
    </tbody>
  </table>
  <div style="text-align:center; padding:30px;" class="footdiv">
    <input type="hidden" name="selfAssess.id" value="${selfAssess.id!}" />
    <input type="hidden" name="save" value="0" id="saveIpt"/>
    [@b.reset/]
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