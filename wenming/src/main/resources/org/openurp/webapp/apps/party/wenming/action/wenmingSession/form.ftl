[#ftl]
[@b.head/]
[@b.toolbar title="文明项目批次编辑"]bar.addBack();[/@]
[@b.tabs]
    [@b.form action="!save" theme="list"]
      [@b.textfield name="wenmingSession.name" label="批次名称" value="${wenmingSession.name!}" required="true" maxlength="50"/]
      [@b.startend label="开始结束时间" 
        name="wenmingSession.beginOn,wenmingSession.endOn" required="true,false" 
        start=wenmingSession.beginOn end=wenmingSession.endOn format="datetime"/]
      [@b.startend label="投票开始结束时间" 
        name="wenmingSession.voteBeginOn,wenmingSession.voteEndOn" required="false,false" 
        start=wenmingSession.voteBeginOn end=wenmingSession.voteEndOn format="datetime"/]
      [@b.formfoot]
        <input type="hidden" name="wenmingSession.id" value="${wenmingSession.id!}" />
        [@b.reset/]&nbsp;&nbsp;[@b.submit value="action.submit"/]
      [/@]
    [/@]
  [/@]
[@b.foot/]