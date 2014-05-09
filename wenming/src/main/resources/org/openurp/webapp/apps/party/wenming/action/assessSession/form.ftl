[#ftl]
[@b.head/]
[@b.toolbar title="考核批次编辑"]bar.addBack();[/@]
[@b.tabs]
  [@b.tab label="考核批次信息"]
    [@b.form action="!save" title="基本信息" theme="list"]
      [@b.textfield name="assessSession.name" label="批次名称" value="${assessSession.name!}" required="true" maxlength="50"/]
      [@b.startend label="测评开始结束时间" 
        name="assessSession.beginOn,assessSession.endOn" required="false,false" 
        start=assessSession.beginOn end=assessSession.endOn format="datetime"/]
      [@b.startend label="投票开始结束时间" 
        name="assessSession.voteBeginOn,assessSession.voteEndOn" required="false,false" 
        start=assessSession.voteBeginOn end=assessSession.voteEndOn format="datetime"/]
      [#--
      [@b.radios label="测评开关状态"  name="assessSession.opened" value=assessSession.opened items="1:开,0:关"/]
      --]
      [@b.radios label="是否有效"  name="assessSession.enabled" value=assessSession.enabled items="1:common.yes,0:common.no"/]
      [@b.formfoot]
        <input type="hidden" name="assessSession.id" value="${assessSession.id!}" />
        [@b.reset/]&nbsp;&nbsp;[@b.submit value="action.submit"/]
      [/@]
    [/@]
  [/@]
[/@]
[@b.foot/]