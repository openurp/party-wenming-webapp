[#ftl]
[@b.head/]
<style>
.search-item input{width:80px;}
</style>
<table  class="indexpanel">
  <tr>
    <td class="index_view">
    [@b.form name="assessApplyForm"  action="!search" target="assessApplyList" title="ui.searchForm" theme="search"]
      [@b.select items=sessions name="assessApply.session.id" label="测评批次"/]
      [@b.select items=departments name="assessApply.department.id"  label="申报部门" empty="..." option="id,name"/]
      <input type="hidden" name="orderBy" value="assessApply.department.code asc" >
      [@b.select items=states name="assessApply.state" label="状态" option="id,description" empty="..." /]
    [/@]
    </td>
    [#if sessions?size>0]
    <td class="index_content">[@b.div id="assessApplyList" href="!search?assessApply.session.id=${sessions?first.id}&orderBy=assessApply.department.code asc" /]</td>
    [/#if]
  </tr>
</table>
[@b.foot/]