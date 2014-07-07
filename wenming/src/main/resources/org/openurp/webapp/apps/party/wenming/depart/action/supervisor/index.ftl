[#ftl]
[@b.head/]
[#include "../voteSession/nav.ftl"/]
[@b.toolbar title='文明委成员维护' /]
<style>
.search-item input{width:80px;}
</style>
<table  class="indexpanel">
  <tr>
    <td class="index_view">
    [@b.form name="supervisorSearchForm"  action="!search" target="supervisorlist" title="ui.searchForm" theme="search"]
      [@b.textfields names="supervisor.name;登录账户"/]
      [@b.textfields names="supervisor.fullname;真实姓名"/]
    [/@]
    </td>
    <td class="index_content">[@b.div id="supervisorlist" href="!search?orderBy=id desc" /]</td>
  </tr>
</table>
[@b.foot/]