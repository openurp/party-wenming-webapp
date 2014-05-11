[#ftl]
[@b.head/]
[#--
<table  class="indexpanel">
  <tr>
    <td class="index_view">
    [@b.form name="selfAssessSearchForm"  action="!search" target="selfAssesslist" title="ui.searchForm" theme="search"]
      [@b.textfields names="selfAssess.name;批次名称"/]
      [@b.select name="selfAssess.enabled" label="是否有效" items={'true':'是','false':'否'} empty="..."/]
    [/@]
    </td>
    <td class="index_content">[@b.div id="selfAssesslist" href="!search?orderBy=id desc" /]</td>
  </tr>
</table>
--]
[#assign assessSession = sessions?first/]
<table>
 <tr>
   <td><b>所在部门:</b>${user.department.name}</td>
   <td>
    [@b.form action="!info" target="selfSessionInfo" title="ui.searchForm" ]
    <b>批次:</b> [@b.select name="session.id" label="测评批次" items=sessions value=assessSession onchange="bg.form.submit(this.form)"][/@]
    [/@]
   </td>
  <tr>
</table>
[@b.div id="selfSessionInfo" href="!info?session.id=${assessSession.id}" /]
[@b.foot/]