[#ftl]

[#macro templateTr i departmentId=0 score=""]
  <tr>
    <td>
      [#assign name="aid${i}"/]
      <input type="hidden" name="index" value="${name}"/>
      <select name="${name}.department.id">
      <option value="">请选择...</option>
      [#list departments as v]
      <option value="${v.id}" [#if departmentId==v.id]selected="selected"[/#if]>${v.name}</option>
      [/#list]
      </select>
    </td>
    <td align="center"><input name="${name}.score" style="width:60px;" value="${score}" class="score"/></td>
    <td align="center"><input type="button" value="删除" onclick="deltr(this)"/></td>
  </tr>
[/#macro]

[@b.head/]
[@b.toolbar title="评测指标编辑"]bar.addBack();[/@]
<style>
form.listform label.title{width:150px;}
form.listform li.foot {padding-left: 160px;}
</style>
[@b.tabs]
  [@b.tab label="评测指标信息"]
    [@b.form action="!save" title="基本信息" theme="list"]
      [@b.field label="指标分类"]${group.name}[/@]
      [@b.textarea label="指标内容"  name="assessItem.content" required="true"
        maxlength="300" value=assessItem.content! rows="3" cols="80"/]
      [@b.textfield name="assessItem.score" label="指标分值" value="${assessItem.orderNumber!}" readonly="readonly" id="scoreIpt"
        required="true" maxlength="10" check="match('integer')"/]
      [@b.textfield name="orderNumber" label="排序" value="${assessItem.orderNumber!}"
        required="true" maxlength="10" check="match('integer')"/]
      [@b.radios label="是否为互评指标"  name="assessItem.mutual" 
        value=assessItem.mutual items="1:common.yes,0:common.no"/]
      [@b.radios label="是否督察组测评指标"  name="assessItem.forSupervisor" 
        value=assessItem.forSupervisor items="1:common.yes,0:common.no"/]
      [@b.field label="评分单位列表"]
        <div style="padding-left:150px;">
          <table class="gridtable" style="width:500px">
            <thead>
              <tr>
                <th align="center">评分单位</th>
                <th width="80" align="center">评分分值</th>
                <th width="80" align="center">操作</th>
              </tr>
            </thead>
            <tbody id="itbody">
              [#list assessItem.departs as v]
              [@templateTr i=v_index departmentId=v.department.id score=v.score/]
              [/#list]
            </tbody>
          </table>
          <input id="addTrBtn" type="button" value="添加"/>
        </div>
      [/@]
      [@b.formfoot]
        <input type="hidden" name="assessItem.id" value="${assessItem.id!}" />
        <input type="hidden" name="assessItem.group.id" value="${group.id!}" />
        [@b.reset/]&nbsp;&nbsp;[@b.submit value="action.submit" onsubmit="ionsubmit"/]
      [/@]
    [/@]
    <table id="templateTable" style="display:none">
        [@templateTr i="{i}"/]
    </table>
  [/@]
[/@]
<script>
  function ionsubmit(){
    var hasEmtpy = false;
    $("#itbody select").each(function (){
      if(this.value == ""){
        hasEmtpy = true;
        this.focus();
      }
    });
    if(hasEmtpy){
      alert("请选择评分单位！");
      return false;
    }
    var formatInvalid = false;
    var sum = 0;
    $("#itbody .score").each(function (){
      if(!this.value.match(/^\d+(\.\d+)?$/)){
        formatInvalid = true;
        this.focus();
      }
      sum +=  this.value * 1;
    });
    if(formatInvalid){
      alert("评分分值格式错误！");
      return false;
    }
    $("#scoreIpt").val(sum);
    return true;
  }
  $(function (){
    var i = 1000;
    $("#addTrBtn").click(function (){
      var tr = $("#templateTable tr").prop("outerHTML").replace(/\{i\}/g, ++i);
      $("#itbody").append(tr);
    });
  });
  function deltr(btn){
    $(btn).closest("tr").remove();
  }
</script>
[@b.foot/]