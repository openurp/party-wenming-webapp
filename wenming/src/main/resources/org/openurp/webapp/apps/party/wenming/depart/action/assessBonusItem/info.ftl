[#ftl]
[@b.head/]
[@b.toolbar title='考核批次信息']
bar.addBack("${b.text("action.back")}");
[/@]
[#include "/org/openurp/webapp/apps/party/wenming/comm.ftl"/]
<table class="infoTable">
  <tr>
   <td class="title" width="10%">加分项目名称:</td>
   <td class="content" width="40%"> ${assessBonusItem.name}</td>
  </tr>
  <tr>
   <td class="title" width="10%">加分项目类型:</td>
   <td class="content" width="40%"> ${assessBonusItem.bonusType.name}</td>
  </tr>
  <tr>
   <td class="title" width="10%">加分方式:</td>
   <td class="content" width="40%"> ${assessBonusItem.method}</td>
  </tr>
  <tr>
   <td class="title" width="10%">具体加分内容:</td>
   <td class="content" width="40%"> ${assessBonusItem.content}</td>
  </tr>
  <tr>
   <td class="title" width="10%">所属方案:</td>
   <td class="content" width="40%"> ${assessBonusItem.schema.name}</td>
  </tr>
</table>
[@b.foot/]
