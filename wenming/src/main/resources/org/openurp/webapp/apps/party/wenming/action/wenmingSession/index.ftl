[#ftl]
[@b.head/]
[@b.toolbar title="文明项目申请批次"/]
<table  class="indexpanel">
  <tr>
    <td class="index_view">
    [@b.form name="wenmingSessionSearchForm"  action="!search" target="wenmingSessionlist" title="ui.searchForm" theme="search"]
      [@b.textfields names="wenmingSession.name;批次名称"/]
      <input type="hidden" name="orderBy" value="beginOn desc"/>
    [/@]
    </td>
    <td class="index_content">[@b.div id="wenmingSessionlist" href="!search?orderBy=beginOn desc" /]</td>
  </tr>
</table>
[@b.foot/]