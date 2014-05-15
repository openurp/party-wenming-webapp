[#ftl]
[@b.head/]
[@b.toolbar title='考核批次信息']
bar.addBack("${b.text("action.back")}");
[/@]
[#include "/org/openurp/webapp/apps/party/wenming/comm.ftl"/]
<table class="infoTable">
  <tr>
   <td class="title" width="10%">批次名称:</td>
   <td class="content" width="40%"> ${goodProject.name}</td>
   <td class="title" width="10%">是否有效:</td>
   <td class="content" width="40%">[@c.enabled goodProject.enabled/]</td>
  </tr>
  <tr>
   <td class="title" >测评开始时间:</td>
   <td class="content">${(goodProject.beginOn?datetime)!}</td>
   <td class="title" >测评结束时间:</td>
   <td class="content">${(goodProject.endOn?datetime)!}</td>
  </tr>
  <tr>
   <td class="title" >投票开始时间:</td>
   <td class="content">${(goodProject.voteBeginOn?datetime)!}</td>
   <td class="title" >投票结束时间:</td>
   <td class="content">${(goodProject.voteEndOn?datetime)!}</td>
  </tr>
  <td class="title" width="10%">关联方案:</td>
   <td class="content" width="40%">[#list goodProject.schemas as schemas]${schemas.name}&nbsp;[/#list]</td>
  </tr>
</table>
[@b.foot/]
