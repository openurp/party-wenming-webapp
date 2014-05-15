[#ftl]
[@b.head/]
[@b.toolbar title='文明项目批次信息']
bar.addBack("${b.text("action.back")}");
[/@]
[#include "/org/openurp/webapp/apps/party/wenming/comm.ftl"/]
<table class="infoTable">
  <tr>
   <td class="title" width="10%">批次名称:</td>
   <td class="content" width="40%"> ${wenmingSession.name}</td>
  </tr>
  <tr>
   <td class="title" >开始时间:</td>
   <td class="content">${(wenmingSession.beginOn?datetime)!}</td>
   <td class="title" >结束时间:</td>
   <td class="content">${(wenmingSession.endOn?datetime)!}</td>
  </tr>
  <tr>
   <td class="title" >投票开始时间:</td>
   <td class="content">${(wenmingSession.voteBeginOn?datetime)!}</td>
   <td class="title" >投票结束时间:</td>
   <td class="content">${(wenmingSession.voteEndOn?datetime)!}</td>
  </tr>
  <tr>
   <td class="title" >文明项目:</td>
   <td class="content" colspan="3">[#list  wenmingSession.types as t]${t.description}&nbsp;[/#list]</td>
  </tr>
</table>
[@b.foot/]
