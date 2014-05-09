[#ftl]
[@b.head/]
<table  class="indexpanel">
  <tr>
    <td class="index_view">
    [@b.form name="assessSessionSearchForm"  action="!search" target="assessSessionlist" title="ui.searchForm" theme="search"]
      [@b.textfields names="assessSession.name;批次名称"/]
      [@b.select name="assessSession.enabled" label="是否有效" items={'true':'是','false':'否'} empty="..."/]
    [/@]
    </td>
    <td class="index_content">[@b.div id="assessSessionlist" href="!search?orderBy=id desc" /]</td>
  </tr>
</table>
[@b.foot/]