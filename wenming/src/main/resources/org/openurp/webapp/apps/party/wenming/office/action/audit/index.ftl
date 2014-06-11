[#ftl]
[@b.head/]
[@b.toolbar title='文明科室申报'/]
[#if sessions?size==0]
  <p>还没有制定相应的批次.</p>
[#else]
[#if !(wenmingSession??) ]
    [#assign wenmingSession = sessions?first/]
[/#if]
<table>
 <tr>
   <td><b>所在部门:</b>${user.department.name}</td>
   <td>
    [@b.form name="applyForm"  action="!index" title="ui.searchForm" ]
    <b>批次:</b> [@b.select name="session.id" label="测评批次" items=sessions value=wenmingSession onchange="bg.form.submit(this.form)"][/@]
    [/@]
   </td>
   <td><b>申报时间:</b><span [#if !wenmingSession.opened] style="color:red" title="不在申报时间内"[/#if]>${wenmingSession.beginOn?string("yyyy-MM-dd HH:mm")}~${wenmingSession.endOn?string("yyyy-MM-dd HH:mm")}</span></td>
  <tr>
</table>
<table>
<tr><td class="index_content">[@b.div id="apply_panel" href="!info?session.id=${wenmingSession.id}" /]</td></tr>
</table>
[/#if]
[@b.foot/]