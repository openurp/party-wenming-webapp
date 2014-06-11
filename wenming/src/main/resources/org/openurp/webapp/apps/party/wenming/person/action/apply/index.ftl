[#ftl]
[@b.head/]
[@b.toolbar title=title!"好人好事申报"/]
<script>
  jQuery.struts2_jquery.requireCss("/css/party/main.css",bg.getContextPath() + "/static");
</script>
<table  class="indexpanel">
  <tr>
    <td class="index_view">
    [@b.form name="goodPersonSearchForm"  action="!search" target="goodPersonlist" title="ui.searchForm" theme="search"]
      [@b.select items=sessions name="goodPerson.session.id" label="测评批次"/]
      [@b.textfields names="goodPerson.name;项目名称"/]
      <input type="hidden" name="orderBy" value="updatedAt desc"/>
    [/@]
    </td>
    <td class="index_content">[@b.div id="goodPersonlist" href="!search?orderBy=updatedAt desc" /]</td>
  </tr>
</table>
[@b.foot/]