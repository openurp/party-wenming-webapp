[#ftl]
[@b.head/]
<style>
.search-item input{width:80px;}
</style>
<table  class="indexpanel">
  <tr>
    <td class="index_view">
    [@b.form name="assessBonusTypeSearchForm"  action="!search" target="assessBonusTypelist" title="ui.searchForm" theme="search"]
      [@b.textfields names="assessBonusType.name;加分类型名称"/]
      [@b.textfields names="assessBonusType.code;类型代码"/]
    [/@]
    </td>
    <td class="index_content">[@b.div id="assessBonusTypelist" href="!search?orderBy=id desc" /]</td>
  </tr>
</table>
[@b.foot/]