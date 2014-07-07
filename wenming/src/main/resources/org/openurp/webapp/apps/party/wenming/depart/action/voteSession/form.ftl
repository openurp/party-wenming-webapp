[#ftl]
[@b.head/]
<style>
form.listform label.title{width:120px} 
</style>
[@b.toolbar title="投票批次编辑"]bar.addBack();[/@]
    [@b.form action="!save" theme="list"]
      [@b.field label="相关测评批次"]${voteSession.session.name}[/@]
      [@b.textfield name="voteSession.name" label="投票批次名称" value="${voteSession.name!}" required="true" maxlength="50"/]
      [@b.startend label="投票开始结束时间" 
        name="voteSession.beginOn,voteSession.endOn" required="true,true" 
        start=voteSession.beginOn end=voteSession.endOn format="datetime"/]
      [@b.textfield name="voteSession.limit" label="教学部门投票上限" value="${voteSession.limit!}" required="true" maxlength="100" check="match('integer').range(0,100)"
       comment="票"/]
      [@b.textfield name="voteSession.limit2" label="职能部门投票上限" value="${voteSession.limit2!}" required="true" maxlength="100" check="match('integer').range(0,100)"
       comment="票"/]
      [@b.select2 label="投票人" name1st="assessVoterId" name2nd="voterId" 
        items1st=voters items2nd= voteSession.voters?sort_by("fullname") 
        option="id,fullname"/]
      [@b.select2 label="被投票部门" name1st="departmentId" name2nd="departId" 
        items1st=departments items2nd= voteSession.departments?sort_by("code") 
        option="id,name"/]
      [@b.formfoot]
        <input type="hidden" name="voteSession.id" value="${voteSession.id!}" />
        <input type="hidden" name="voteSession.session.id" value="${voteSession.session.id!}" />
        [@b.reset/]&nbsp;&nbsp;[@b.submit value="action.submit"/]
      [/@]
    [/@]
[@b.foot/]