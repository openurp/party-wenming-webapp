[#ftl]
[@b.head/]
[@b.messages slash="3"/]
[#if goodOffice??]
[@b.toolbar title='基本信息']
    function edit(){document.getElementById("apply_edit").click();}
    function submit_apply(){ if(confirm("提交后不能修改，确认提交？")) document.getElementById("apply_submit").click(); }
    [#if editable && wenmingSession.opened]
    bar.addItem("${b.text("action.edit")}",edit);
    bar.addItem("${b.text("action.submit")}",submit_apply);
    [/#if]
[/@]
<div>
  [@b.a id="apply_edit" style="display:none" href="!edit?goodOffice.id=${goodOffice.id}"]修改[/@]
  [@b.a id="apply_submit" style="display:none" href="!submit?goodOffice.id=${goodOffice.id}"]提交[/@]
</div>
<table class="infoTable">
  <tr>
   <td class="title">状态:</td>
   <td class="content">${goodOffice.state.description}</td>
   <td class="title">提交人:</td>
   <td class="content"> ${goodOffice.submitBy.name}(${goodOffice.submitBy.fullname})</td>
   <td class="title">更新时间:</td>
   <td class="content">${goodOffice.updatedAt?string("YYYY-MM-dd HH:mm")}</td>
  </tr>
  <tr>
   <td class="title">文明科室:</td>
   <td class="content" colspan="5" width="80%"> ${goodOffice.offices}</td>
  </tr>
  <tr>
   <td class="title" >支撑材料:</td>
   <td class="content" colspan="5">[#if goodOffice.attachment??][@b.a target="_blank" href="../attachment?path=${goodOffice.attachment.filePath}&name=${goodOffice.attachment.name?url('utf-8')}"]${goodOffice.attachment.name}[/@][/#if]</td>
  </tr>
 </table>
[#else]
<p>
    [#if wenmingSession.opened][@b.a href="!edit?goodOffice.session.id=${Parameters['session.id']}" ]还没有进行申报，现在申报[/@]
    [#else]现在不在申报时间内[/#if]
</p>
[/#if]
[@b.foot/]
