[#ftl]
[@b.head/]
[@b.toolbar title='文明科室申报管理'/]
<script>
  jQuery.struts2_jquery.requireCss("/css/party/main.css",bg.getContextPath() + "/static");
</script>
<table  class="indexpanel">
  <tr>
    <td class="index_view">
    [@b.form name="goodProjectSearchForm"  action="!search" target="goodProjectlist" title="ui.searchForm" theme="search"]
      [@b.textfields names="goodProject.name;项目名称"/]
      <input type="hidden" name="orderBy" value="updatedAt desc"/>
    [/@]
    </td>
    <td class="index_content">[@b.div id="goodProjectlist" href="!search?orderBy=updatedAt desc" /]</td>
  </tr>
</table>
[@b.foot/]