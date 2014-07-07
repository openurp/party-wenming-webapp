[#ftl]
[@b.head/]
[#include "nav.ftl"/]
[@b.toolbar title='投票批次维护' /]
<table  class="indexpanel">
  <tr>
    <td class="index_view">
    [@b.form name="wenmingVoteSessionSearchForm"  action="!search" target="wenmingVoteSessionlist" title="ui.searchForm" theme="search"]
      [@b.textfields names="wenmingVoteSession.name;批次名称"/]
      <input type="hidden" name="orderBy" value="beginOn desc"/>
    [/@]
    </td>
    <td class="index_content">[@b.div id="wenmingVoteSessionlist" href="!search?orderBy=beginOn desc" /]</td>
  </tr>
</table>
[@b.foot/]