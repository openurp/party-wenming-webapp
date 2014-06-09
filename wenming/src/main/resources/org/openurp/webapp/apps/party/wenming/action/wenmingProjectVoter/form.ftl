[#ftl]
[@b.head/]
[@b.toolbar title="文明项目投票成员编辑"]bar.addBack();[/@]
[@b.tabs]
    [@b.form action="!save" theme="list"]
      [@b.textfield name="wenmingProjectVoter.name" label="系统登录名称" value="${wenmingProjectVoter.name!}" 
        required="true" maxlength="50"/]
      [@b.textfield name="wenmingProjectVoter.fullname" label="真实姓名" value="${wenmingProjectVoter.fullname!}" 
        required="true" maxlength="50"/]
      [@b.password name="wenmingProjectVoter.password" label="密码" value="${wenmingProjectVoter.password!}" 
        required="false" maxlength="50"/]
      [@b.textarea name="wenmingProjectVoter.remark" label="说明" value="${wenmingProjectVoter.remark!}" 
        required="false" maxlength="50"/]
      [@b.datepicker name="wenmingProjectVoter.expiredOn" label="过期日期" value="${(wenmingProjectVoter.expiredOn?string('yyyy-MM-dd'))!}"
        required="false" datepicker=wenmingProjectVoter.expiredOn format="yyyy-MM-dd"/]
      [@b.formfoot]
        <input type="hidden" name="wenmingProjectVoter.id" value="${wenmingProjectVoter.id!}" />
        [@b.reset/]&nbsp;&nbsp;[@b.submit value="action.submit"/]
      [/@]
    [/@]
  [/@]
[@b.foot/]