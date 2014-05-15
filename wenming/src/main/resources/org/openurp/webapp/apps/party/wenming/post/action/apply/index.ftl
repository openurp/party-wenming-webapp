[#ftl]
[@b.head/]
<script>
  jQuery.struts2_jquery.requireCss("/css/party/main.css",bg.getContextPath() + "/static");
</script>
<table  class="indexpanel">
  <tr>
    <td class="index_view">
    [@b.form name="goodPostSearchForm"  action="!search" target="goodPostlist" title="ui.searchForm" theme="search"]
      [@b.textfields names="goodPost.name;项目名称"/]
      <input type="hidden" name="orderBy" value="updatedAt desc"/>
    [/@]
    </td>
    <td class="index_content">[@b.div id="goodPostlist" href="!search?orderBy=updatedAt desc" /]</td>
  </tr>
</table>
[@b.foot/]