[#ftl]
[@b.head/]
[#assign wenmingTypeNames={'Project':'优秀项目','Person':'好人好事','Post':'文明示范岗'}/]
[@b.toolbar title="文明项目批次编辑"]bar.addBack();[/@]
    [@b.form action="!save" theme="list"]
      [@b.textfield name="wenmingSession.name" label="批次名称" value="${wenmingSession.name!}" required="true" maxlength="50"/]
      [@b.startend label="申报时间段"  require="true"
        name="wenmingSession.beginOn,wenmingSession.endOn" required="true,false" 
        start=wenmingSession.beginOn end=wenmingSession.endOn format="datetime"/]
      [@b.startend label="投票时间段"  require="true"
        name="wenmingSession.voteBeginOn,wenmingSession.voteEndOn" required="false,false" 
        start=wenmingSession.voteBeginOn end=wenmingSession.voteEndOn format="datetime"/]
      [@b.checkboxes label="文明项目类型" name="wenmingTypeId" items=wenmingTypeNames value=wenmingSession.types min=1/]
      [@b.formfoot]
        <input type="hidden" name="wenmingSession.id" value="${wenmingSession.id!}" />
        [@b.reset/]&nbsp;&nbsp;[@b.submit value="action.submit"/]
      [/@]
    [/@]
[@b.foot/]