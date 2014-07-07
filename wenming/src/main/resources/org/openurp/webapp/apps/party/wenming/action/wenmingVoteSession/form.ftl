[#ftl]
[@b.head/]
<style>
form.listform label.title{width:120px} 
</style>
[@b.toolbar title="投票批次编辑"]bar.addBack();[/@]
    [@b.form action="!save" theme="list"]
      [@b.field label="相关测评批次"]${wenmingVoteSession.session.name}[/@]
      [@b.textfield name="wenmingVoteSession.name" label="批次名称" value="${wenmingVoteSession.name!}" required="true" maxlength="50"/]
      [@b.startend label="投票开始结束时间" 
        name="wenmingVoteSession.beginOn,wenmingVoteSession.endOn" required="true,true" 
        start=wenmingVoteSession.beginOn end=wenmingVoteSession.endOn format="datetime"/]
      [@b.textfield name="wenmingVoteSession.limit" label="优秀项目投票上限" value="${wenmingVoteSession.limit!}" required="true" maxlength="100" check="match('integer').range(0,100)"
       comment="票"/]
      [@b.textfield name="wenmingVoteSession.limit2" label="好人好事投票上限" value="${wenmingVoteSession.limit2!}" required="true" maxlength="100" check="match('integer').range(0,100)"
       comment="票"/]
      [@b.textfield name="wenmingVoteSession.limit3" label="文明示范岗投票上限" value="${wenmingVoteSession.limit3!}" required="true" maxlength="100" check="match('integer').range(0,100)"
       comment="票"/]
      [@b.select2 label="投票人" name1st="assessVoterId" name2nd="voterId" 
        items1st=voters items2nd= wenmingVoteSession.voters?sort_by("fullname") 
        option="id,fullname"/]
      [@b.select2 label="优秀项目" name1st="projectId" name2nd="goodProjectId" 
        items1st=projects items2nd= wenmingVoteSession.projects 
        option="id,name"/]
      [@b.select2 label="好人好事" name1st="personId" name2nd="goodPersonId" 
        items1st=persons items2nd= wenmingVoteSession.persons 
        option="id,name"/]
      [@b.select2 label="文明示范岗" name1st="postId" name2nd="goodPostId" 
        items1st=posts items2nd= wenmingVoteSession.posts 
        option="id,name"/]
      [@b.formfoot]
        <input type="hidden" name="wenmingVoteSession.id" value="${wenmingVoteSession.id!}" />
        <input type="hidden" name="wenmingVoteSession.session.id" value="${wenmingVoteSession.session.id!}" />
        [@b.reset/]&nbsp;&nbsp;[@b.submit value="action.submit"/]
      [/@]
    [/@]
[@b.foot/]