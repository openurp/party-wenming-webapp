[#ftl]
[@b.head/]
<table  class="indexpanel">
  <tr>
    <td class="index_view">
    [@b.form name="assessBonusTypeSearchForm"  action="!search" target="assessBonusTypelist" title="ui.searchForm" theme="search"]
      [@b.textfields names="assessBonusType.name;加分项目类别"/]
    [/@]
    </td>
    <td class="index_content">[@b.div id="assessBonusTypelist" href="!search?orderBy=id desc" /]</td>
  </tr>
</table>
[@b.foot/]