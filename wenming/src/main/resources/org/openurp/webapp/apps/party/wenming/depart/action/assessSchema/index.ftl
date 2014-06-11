[#ftl]
[@b.head/]
[@b.toolbar title='评价指标维护' /]
<table  class="indexpanel">
  <tr>
    <td class="index_view">
    [@b.form name="assessSchemaSearchForm"  action="!search" 
      target="assessSchemalist" title="ui.searchForm" theme="search"]
      [@b.textfields names="assessSchema.name;方案名称"/]
    [/@]
    </td>
    <td class="index_content">[@b.div id="assessSchemalist" href="!search" /]</td>
  </tr>
</table>
[@b.foot/]