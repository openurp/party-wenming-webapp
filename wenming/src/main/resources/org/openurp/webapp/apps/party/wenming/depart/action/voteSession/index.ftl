[#ftl]
[@b.head/]
[#include "nav.ftl"/]
[@b.toolbar title='投票批次维护' /]
<table  class="indexpanel">
  <tr>
    <td class="index_view">
    [@b.form name="voteSessionSearchForm"  action="!search" target="voteSessionlist" title="ui.searchForm" theme="search"]
      [@b.textfields names="voteSession.name;批次名称"/]
      <input type="hidden" name="orderBy" value="beginOn desc"/>
    [/@]
    </td>
    <td class="index_content">[@b.div id="voteSessionlist" href="!search?orderBy=beginOn desc" /]</td>
  </tr>
</table>
[@b.foot/]