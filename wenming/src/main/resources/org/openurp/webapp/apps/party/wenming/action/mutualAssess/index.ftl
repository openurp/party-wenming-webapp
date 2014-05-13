[#ftl]
[@b.head/]
<table  class="indexpanel">
  <tr>
    <td class="index_view">
    [@b.form name="mutualAssessSearchForm"  action="!search" target="mutualAssesslist" title="ui.searchForm" theme="search"]
      [@b.textfields names="mutualAssess.name;批次名称"/]
      [@b.select name="mutualAssess.enabled" label="是否有效" items={'true':'是','false':'否'} empty="..."/]
    [/@]
    </td>
    <td class="index_content">[@b.div id="mutualAssesslist" href="!search?orderBy=id desc" /]</td>
  </tr>
</table>
[@b.foot/]