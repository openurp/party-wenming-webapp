[#ftl]
[@b.head/]
[@b.toolbar title='评测指标信息']
bar.addBack("${b.text("action.back")}");
[/@]
[#include "/org/openurp/webapp/apps/party/wenming/comm.ftl"/]
<table class="infoTable">
  <tr>
   <td class="title" width="10%">批次名称:</td>
   <td class="content" width="40%"> ${assessItem.name}</td>
   <td class="title" width="10%">是否有效:</td>
   <td class="content" width="40%">[@c.enabled assessItem.enabled/]</td>
  </tr>
  <tr>
   <td class="title" >测评开始时间:</td>
   <td class="content">${assessItem.beginOn?datetime}</td>
   <td class="title" >测评结束时间:</td>
   <td class="content">${assessItem.endOn?datetime}</td>
  </tr>
  <tr>
   <td class="title" >投票开始时间:</td>
   <td class="content">${assessItem.voteBeginOn?datetime}</td>
   <td class="title" >投票结束时间:</td>
   <td class="content">${assessItem.voteEndOn?datetime}</td>
  </tr>
  <tr>
</table>
[@b.foot/]
