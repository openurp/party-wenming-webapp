[#ftl]
[@b.head/]
[@b.toolbar title='加分材料']
  bar.addBack();
[/@]
[@b.form action="!save" title="加分项目及其材料" theme="list"]
  [@b.select name="assessBonus.item.id" label="加分项目" items=bonusItems value= assessBonus.item required="true" style="width:500px" /]
  [@b.textfield name="assessBonus.score" label="分数" value=assessBonus.score! required="true" check="match('integer').range(1,100)"/]
  [@b.field label="相关支撑材料"]
  <div style="margin-left:120px">
    <input name="attachment" type="file"/><br>
    <input name="attachment" type="file"/><br>
    <input name="attachment" type="file"/><br>
    <input name="attachment" type="file"/><br>
    <input name="attachment" type="file"/>
  </div>
  [/@]
  [@b.formfoot]
    <input type="hidden" name="assessBonus.id" value="${assessBonus.id!}" />
    [#if !(assessBonus.id??)]
    <input type="hidden" name="assessBonus.apply.id" value="${Parameters['assessBonus.apply.id']}" />
    [/#if]
    [@b.reset/]&nbsp;&nbsp;[@b.submit value="action.save"/]
  [/@]
[/@]
[@b.foot/]