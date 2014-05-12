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
      [@b.select items=departments name="assessApply.department.id"  label="申报部门" empty="..."/]
      [#--[@b.select items=states name="assessApply.state" label="状态"/]--]
    [/@]
    </td>
    <td class="index_content">[@b.div id="assessApplyList" href="!search" /]</td>
  </tr>
</table>
[@b.foot/]