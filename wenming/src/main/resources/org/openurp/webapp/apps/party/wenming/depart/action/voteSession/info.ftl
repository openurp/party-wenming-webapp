[#ftl]
[@b.head/]
[@b.toolbar title='投票批次信息']
bar.addBack("${b.text("action.back")}");
[/@]
[#include "/org/openurp/webapp/apps/party/wenming/comm.ftl"/]
<table class="infoTable">
  <tr>
   <td class="title" width="15%">相关测评批次:</td>
   <td class="content" width="35%"> ${voteSession.session.name}</td>
   <td class="title" width="15%">投票批次名称:</td>
   <td class="content" width="35%"> ${voteSession.name}</td>
  </tr>
  <tr>
   <td class="title" width="15%">教学部门投票上限:</td>
   <td class="content" width="35%"> ${voteSession.limit}票</td>
   <td class="title" width="15%">职能部门投票上限:</td>
   <td class="content" width="35%"> ${voteSession.limit2}票</td>
  </tr>
  <tr>
   <td class="title" >投票开始时间:</td>
   <td class="content">${(voteSession.beginOn?datetime)!}</td>
   <td class="title" >投票结束时间:</td>
   <td class="content">${(voteSession.endOn?datetime)!}</td>
  </tr>
  <tr>
   <td class="title" width="15%">投票人:</td>
   <td class="content" colspan="3">[#list voteSession.voters as voters]${voters.fullname}&nbsp;[/#list]</td>
  </tr>
  <tr>
   <td class="title" width="15%">被投票部门:</td>
   <td class="content" colspan="3">[#list voteSession.departments as departments]${departments.name}&nbsp;[/#list]</td>
  </tr>
</table>
[@b.foot/]
