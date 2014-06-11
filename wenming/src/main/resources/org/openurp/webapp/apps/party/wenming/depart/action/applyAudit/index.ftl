[#ftl]
[@b.head/]
[@b.toolbar title='申报部门审批' /]
[#if sessions?size==0]
  <p>还没有制定响应的测评批次.[@ems.guard res="assess-session"][@b.a href="assess-session"]制定一个新批次[/@][/@]</p>
[#else]
[#if !(assessSession??) ]
    [#assign assessSession = sessions?first/]
[/#if]
<table>
 <tr>
   <td><b>所在部门:</b>${user.department.name}</td>
   <td>
    [@b.form name="applyForm"  action="!index" target="assessItemlist" title="ui.searchForm" ]
    <b>批次:</b> [@b.select name="session.id" label="测评批次" items=sessions value=assessSession onchange="bg.form.submit(this.form)"][/@]
    [/@]
   </td>
   <td><b>申报时间:</b><span [#if !assessSession.opened] color="red"[/#if]>${assessSession.beginOn?string("yyyy-MM-dd HH:mm")}~${assessSession.endOn?string("yyyy-MM-dd HH:mm")}</span></td>
  <tr>
</table>
<table>
<tr><td class="index_content">[@b.div id="apply_panel" href="!info?session.id=${assessSession.id}" /]</td></tr>
</table>
[/#if]
[@b.foot/]