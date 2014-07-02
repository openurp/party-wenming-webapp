[#ftl]
[@b.head/]
[#include "/org/openurp/webapp/apps/party/wenming/action/goodObjectVoteStat/voteStatNav.ftl"/]
[@b.toolbar title="优秀项目投票汇总"/]
<div id="voteManageIndex" class="ajax_container">
  <div class="inline_forms">
    [@b.form action="!index" target="voteManageIndex"]
      [@b.select name="session.id" label="测评批次" items=wenmingSessions value=wenmingSession onchange="bg.form.submit(this.form)"][/@]
    [/@]
  </div>
  [#if wenmingSessions?size gt 0]
    [@b.div id="voteManageEdit" href = "!info?session.id=${wenmingSessions[0].id}"][/@]
  [/#if]
</div>
[@b.foot/]