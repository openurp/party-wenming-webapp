[#ftl]
[@b.head/]
<style>
.search-item input{width:80px;}
</style>
<table  class="indexpanel">
  <tr>
    <td class="index_view">
    [@b.form name="assessStatForm"  action="!progress" target="assessStatResult"]
     [@b.select name="session.id" label="测评批次" items=sessions value=assessSession onchange="bg.form.submit(this.form)"][/@]
     [@b.submit value="查询"/]
    [/@]
  </tr>
  <tr>
    </td>
    [#if sessions?size>0]
    <td class="index_content">[@b.div id="assessStatResult" href="!progress?session.id=${sessions?first.id}" /]</td>
    [/#if]
  </tr>
</table>
[@b.foot/]