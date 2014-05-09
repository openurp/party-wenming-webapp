[#ftl]
[@b.head/]
[@b.toolbar title='评价指标分类信息']
bar.addBack("${b.text("action.back")}");
[/@]
[#include "/org/openurp/webapp/apps/party/wenming/comm.ftl"/]
<table class="infoTable">
  <tr>
   <td class="title" width="10%">批次名称:</td>
   <td class="content" width="40%"> ${assessItemGroup.name}</td>
   <td class="title" width="10%">是否有效:</td>
   <td class="content" width="40%">[@c.enabled assessItemGroup.enabled/]</td>
  </tr>
  <tr>
   <td class="title" >测评开始时间:</td>
   <td class="content">${assessItemGroup.beginOn?datetime}</td>
   <td class="title" >测评结束时间:</td>
   <td class="content">${assessItemGroup.endOn?datetime}</td>
  </tr>
  <tr>
   <td class="title" >投票开始时间:</td>
   <td class="content">${assessItemGroup.voteBeginOn?datetime}</td>
   <td class="title" >投票结束时间:</td>
   <td class="content">${assessItemGroup.voteEndOn?datetime}</td>
  </tr>
  <tr>
</table>
[@b.foot/]
