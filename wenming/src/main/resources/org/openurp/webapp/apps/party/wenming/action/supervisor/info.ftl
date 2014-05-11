[#ftl]
[@b.head/]
[@b.toolbar title='考核批次信息']
bar.addBack("${b.text("action.back")}");
[/@]
[#include "/org/openurp/webapp/apps/party/wenming/comm.ftl"/]
<table class="infoTable">
  <tr>
   <td class="title" width="10%">系统登录名称:</td>
   <td class="content" width="40%"> ${supervisor.name}</td>
  </tr>
  <tr>
   <td class="title" width="10%">真实姓名:</td>
   <td class="content" width="40%"> ${supervisor.fullname}</td>
  </tr>
  <tr>
   <td class="title" width="10%">说明:</td>
   <td class="content" width="40%"> ${(supervisor.remark)!}</td>
  </tr>
  <tr>
   <td class="title" width="10%">过期日期:</td>
   <td class="content" width="40%"> ${supervisor.expiredOn}</td>
  </tr>
</table>
[@b.foot/]
