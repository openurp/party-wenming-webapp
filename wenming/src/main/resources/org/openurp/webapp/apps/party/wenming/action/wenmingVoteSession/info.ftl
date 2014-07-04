[#ftl]
[@b.head/]
[@b.toolbar title='投票批次信息']
bar.addBack("${b.text("action.back")}");
[/@]
[#include "/org/openurp/webapp/apps/party/wenming/comm.ftl"/]
<table class="infoTable">
  <tr>
   <td class="title" width="10%">相关测评批次:</td>
   <td class="content" width="40%"> ${voteSession.session.name}</td>
  </tr>
  <tr>
   <td class="title" width="10%">批次名称:</td>
   <td class="content" width="40%"> ${voteSession.name}</td>
   <td class="title" width="10%">投票上限:</td>
   <td class="content" width="40%"> ${voteSession.limit}票</td>
  </tr>
  <tr>
   <td class="title" >投票开始时间:</td>
   <td class="content">${(voteSession.beginOn?datetime)!}</td>
   <td class="title" >投票结束时间:</td>
   <td class="content">${(voteSession.endOn?datetime)!}</td>
  </tr>
  <tr>
   <td class="title" width="10%">投票人:</td>
   <td class="content" colspan="3">[#list voteSession.voters as voters]${voters.fullname}&nbsp;[/#list]</td>
  </tr>
  <tr>
   <td class="title" width="10%">优秀项目:</td>
   <td class="content" colspan="3">[#list voteSession.projects as projects]${projects.name}&nbsp;[/#list]</td>
  </tr>
  <tr>
   <td class="title" width="10%">好人好事:</td>
   <td class="content" colspan="3">[#list voteSession.persons as persons]${persons.name}&nbsp;[/#list]</td>
  </tr>
  <tr>
   <td class="title" width="10%">文明示范岗:</td>
   <td class="content" colspan="3">[#list voteSession.posts as posts]${posts.name}&nbsp;[/#list]</td>
  </tr>
</table>
[@b.foot/]
