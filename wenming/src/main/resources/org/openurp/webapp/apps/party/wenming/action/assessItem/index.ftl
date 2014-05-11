[#ftl]
[@b.head/]
<table  class="indexpanel">
  <tr>
    <td class="index_view">
    [@b.form name="assessItemSearchForm"  action="!search" target="assessItemlist" title="ui.searchForm" theme="search"]
      [@b.textfields names="assessItem.name;指标名称"/]
      <input type="hidden" name="assessItem.group.id" value="${assessItemGroupId}"/>
      <input type="hidden" name="orderBy" value="orderNumber"/>
    [/@]
    </td>
    <td class="index_content">[@b.div id="assessItemlist" href="!search?orderBy=orderNumber&assessItem.group.id=${assessItemGroupId}" /]</td>
  </tr>
</table>
[@b.foot/]