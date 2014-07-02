[#ftl]
[@b.head/]
[#include "../voteStat/voteStatNav.ftl"/]
[@b.toolbar title='文明委投票汇总' /]
<div id="voteManageIndex" class="ajax_container">
  <div class="inline_forms">
    [@b.form action="!index" target="voteManageIndex"]
      [@b.select name="session.id" label="测评批次" items=assessSessions value=assessSession onchange="bg.form.submit(this.form)"][/@]
    [/@]
    [@b.form action="!info" target="voteManageInfo"]
      <input type="hidden" name="session.id" value="${assessSession.id}"/>
    [/@]
  </div>
  [@b.div id="voteManageInfo" href="!info?session.id=${assessSession.id}" /]
</div>
[@b.foot/]