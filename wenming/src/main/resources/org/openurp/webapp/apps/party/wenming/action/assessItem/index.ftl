[#ftl]
[@b.head/]
<table  class="indexpanel">
  <tr>
    <td class="index_view">
    [@b.form name="assessItemSearchForm"  action="!search" target="assessItemlist" title="ui.searchForm" theme="search"]
      [@b.textfields names="assessItem.name;指标名称"/]
      [@b.select name="schema.id" label="方案" items=schemas empty="..."/]
    [/@]
    </td>
    <td class="index_content">[@b.div id="assessItemlist" href="!search?orderBy=id desc" /]</td>
  </tr>
</table>
[@b.foot/]