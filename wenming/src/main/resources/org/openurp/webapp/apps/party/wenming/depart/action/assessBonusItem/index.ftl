[#ftl]
[@b.head/]
<style>
.search-item input{width:80px;}
</style>
<table  class="indexpanel">
  <tr>
    <td class="index_view">
    [@b.form name="assessBonusItemSearchForm"  action="!search" target="assessBonusItemlist" title="ui.searchForm" theme="search"]
      [@b.textfields names="assessBonusItem.name;加分项目名称"/]
      [@b.textfields names="assessBonusItem.bonusType;加分项目类型"/]
      [@b.select name="assessBonusItem.schema" items=schemas label="所属方案" empty="..."/]
    [/@]
    </td>
    <td class="index_content">[@b.div id="assessBonusItemlist" href="!search?orderBy=id desc" /]</td>
  </tr>
</table>
[@b.foot/]