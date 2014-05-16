[#ftl]
<div>
  <b>申报时间:</b>${(selfAssess.session.beginOn?string("yyyy-MM-dd HH:mm"))!}~${(selfAssess.session.endOn?string("yyyy-MM-dd HH:mm"))!}
</div>
<table class="infoTable">
  <tr>
   <td class="title">单位名称:</td>
   <td class="content">${(selfAssess.assessDepartment.name)!}</td>
   <td class="title">所属批次:</td>
   <td class="content"> ${selfAssess.session.name}</td>
   <td class="title">自评时间:</td>
   <td class="content">${selfAssess.assessAt?string("YYYY-MM-dd HH:mm")}</td>
  </tr>
  <tr>
   <td class="title">自评人员:</td>
   <td class="content">${selfAssess.assessBy.name}（${selfAssess.assessBy.fullname}）</td>
   <td class="title">自评总分:</td>
   <td class="content" style="color:red; font-weight:bold;"> ${selfAssess.totalScore}</td>
   <td class="title">审核状态:</td>
   <td class="content" style="color:red; font-weight:bold;">${selfAssess.state.description}</td>
  </tr>
</table>