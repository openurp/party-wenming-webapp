[#ftl]
[@b.head/]
[@b.toolbar title='投票进度' /]
<div>
  [@b.form name="voteStatForm"  action="!progress" target="voteStatResult"]
   [@b.select name="session.id" label="测评批次" items=sessions value=assessSession onchange="bg.form.submit(this.form)"][/@]
  [/@]
</div>
[#if sessions?size>0]
[@b.div id="voteStatResult" href="!progress?session.id=${sessions?first.id}" /]
[/#if]
[@b.foot/]