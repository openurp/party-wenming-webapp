[#ftl]
[@b.head/]
[@b.toolbar title="投票批次编辑"]bar.addBack();[/@]
[@b.tabs]
    [@b.form action="!save" theme="list"]
      [@b.field label="相关测评批次"]${voteSession.session.name}[/@]
      [@b.textfield name="voteSession.name" label="批次名称" value="${voteSession.name!}" required="true" maxlength="50"/]
      [@b.startend label="投票开始结束时间" 
        name="voteSession.beginOn,voteSession.endOn" required="true,true" 
        start=voteSession.beginOn end=voteSession.endOn format="datetime"/]
      [@b.textfield name="voteSession.limit" label="投票限制" value="${voteSession.limit!}" required="true" maxlength="100" check="match('integer').range(1,100)"
       comment="请输入投票上限"/]
      [@b.select2 label="投票人" name1st="assessVoterId" name2nd="voterId" 
        items1st=voters items2nd= voteSession.voters?sort_by("fullname") 
        option="id,fullname"/]
      [@b.select2 label="优秀项目" name1st="projectId" name2nd="goodProjectId" 
        items1st=goodProjects items2nd= voteSession.projects 
        option="id,name"/]
      [@b.select2 label="好人好事" name1st="personId" name2nd="goodPersonId" 
        items1st=goodPeople items2nd= voteSession.persons 
        option="id,name"/]
      [@b.select2 label="文明示范岗" name1st="postId" name2nd="goodPostId" 
        items1st=goodPosts items2nd= voteSession.posts 
        option="id,name"/]
      [@b.formfoot]
        <input type="hidden" name="voteSession.id" value="${voteSession.id!}" />
        <input type="hidden" name="voteSession.session.id" value="${voteSession.session.id!}" />
        [@b.reset/]&nbsp;&nbsp;[@b.submit value="action.submit"/]
      [/@]
    [/@]
  [/@]
[@b.foot/]