[#ftl]
[@b.head/]
[@b.toolbar title='投票批次信息']
bar.addBack("${b.text("action.back")}");
[/@]
[#include "/org/openurp/webapp/apps/party/wenming/comm.ftl"/]
<table class="infoTable">
  <tr>
   <td class="title" width="10%">相关测评批次:</td>
   <td class="content" width="40%"> ${wenmingVoteSession.session.name}</td>
   <td class="title" width="10%">投票批次名称:</td>
   <td class="content" width="40%"> ${wenmingVoteSession.name}</td>
  </tr>
  <tr>
   <td class="title" width="10%">优秀项目投票限制:</td>
   <td class="content" width="40%"> ${wenmingVoteSession.limit}票</td>
  </tr>
  <tr>
   <td class="title" width="10%">好人好事投票上限:</td>
   <td class="content" width="40%"> ${wenmingVoteSession.limit2}票</td>
  </tr>
  <tr>
   <td class="title" width="10%">文明示范岗投票上限:</td>
   <td class="content" width="40%"> ${wenmingVoteSession.limit3}票</td>
  </tr>
  <tr>
   <td class="title" >投票开始时间:</td>
   <td class="content">${(wenmingVoteSession.beginOn?datetime)!}</td>
   <td class="title" >投票结束时间:</td>
   <td class="content">${(wenmingVoteSession.endOn?datetime)!}</td>
  </tr>
  <tr>
   <td class="title" width="10%">投票人:</td>
   <td class="content" colspan="3">[#list wenmingVoteSession.voters as voters]${voters.fullname}&nbsp;[/#list]</td>
  </tr>
  <tr>
   <td class="title" width="10%">优秀项目:</td>
   <td class="content" colspan="3">[#list wenmingVoteSession.projects as projects]${projects.name}&nbsp;[/#list]</td>
  </tr>
  <tr>
   <td class="title" width="10%">好人好事:</td>
   <td class="content" colspan="3">[#list wenmingVoteSession.persons as persons]${persons.name}&nbsp;[/#list]</td>
  </tr>
  <tr>
   <td class="title" width="10%">文明示范岗:</td>
   <td class="content" colspan="3">[#list wenmingVoteSession.posts as posts]${posts.name}&nbsp;[/#list]</td>
  </tr>
</table>
[@b.foot/]
