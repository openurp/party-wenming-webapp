[#ftl]
[@b.head/]
[@b.toolbar title='加分项目信息']
bar.addBack("${b.text("action.back")}");
[/@]
[#include "/org/openurp/webapp/apps/party/wenming/comm.ftl"/]
<table class="infoTable">
  <tr>
   <td class="title" width="10%">类型代码:</td>
   <td class="content" width="40%"> ${assessBonusType.code}</td>
  </tr>
  <tr>
   <td class="title" width="10%">加分类型名称:</td>
   <td class="content" width="40%"> ${assessBonusType.name}</td>
  </tr>
</table>
[@b.foot/]
