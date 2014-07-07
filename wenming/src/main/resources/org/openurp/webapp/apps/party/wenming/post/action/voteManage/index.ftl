[#ftl]
[@b.head/]
<div id="voteManageIndex" class="ajax_container">
  [#include "/org/openurp/webapp/apps/party/wenming/action/goodObjectVoteStat/voteStatNav.ftl"/]
  [@b.toolbar title="文明示范岗投票汇总"/]
  <div class="inline_forms">
    [@b.form action="!index" target="voteManageIndex"]
      [@b.select name="session.id" label="测评批次" items=wenmingSessions value=wenmingSession onchange="bg.form.submit(this.form)"][/@]
    [/@]
  </div>
  [#if wenmingSession??]
    [@b.div id="voteManageEdit" href = "!info?session.id=${wenmingSession.id}"][/@]
  [/#if]
</div>
[@b.foot/]