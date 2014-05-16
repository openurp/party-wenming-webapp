[#ftl]
[#if malist?? && malist?size gt 0]
[#assign assess = malist[0]/]
<table class="infoTable">
  <tr>
   <td class="title">所属批次:</td>
   <td class="content"> ${assess.session.name}</td>
   <td class="title">测评单位名称:</td>
   <td class="content">${assess.assessDepartment.name}</td>
  </tr>
  <tr>
   <td class="title">测评时间:</td>
   <td class="content">${assess.assessAt?string("YYYY-MM-dd HH:mm")}</td>
   <td class="title">审核状态:</td>
   <td class="content" style="color:red; font-weight:bold;">${assess.state.description}</td>
  </tr>
</table>
[/#if]