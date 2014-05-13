[#ftl]
[@b.head/]
<table  class="indexpanel">
  <tr>
    <td class="index_view">
    [@b.form name="assessItemGroupSearchForm"  action="!search" target="assessItemGrouplist" title="ui.searchForm" theme="search"]
      [@b.textfields names="assessItemGroup.name;名称"/]
      [@b.select name="assessItemGroup.schema.id" label="相关方案" items=schemas options="id,name" empty="..."/]
      <input name="orderBy" value="schema.id,indexno" type="hidden"/>
    [/@]
    </td>
    <td class="index_content">[@b.div id="assessItemGrouplist" href="!search?orderBy=schema.id,indexno" /]</td>
  </tr>
</table>
[@b.foot/]