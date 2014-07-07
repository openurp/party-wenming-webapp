[#ftl]
[@b.head/]
[#include "../wenmingVoteSession/nav.ftl"/]
[@b.toolbar title='文明项目投票成员维护' /]
<style>
.search-item input{width:80px;}
</style>
<table  class="indexpanel">
  <tr>
    <td class="index_view">
    [@b.form name="wenmingProjectVoterSearchForm"  action="!search" target="wenmingProjectVoterlist" title="ui.searchForm" theme="search"]
      [@b.textfields names="wenmingProjectVoter.name;登录账户"/]
      [@b.textfields names="wenmingProjectVoter.fullname;真实姓名"/]
    [/@]
    </td>
    <td class="index_content">[@b.div id="wenmingProjectVoterlist" href="!search?orderBy=id desc" /]</td>
  </tr>
</table>
[@b.foot/]