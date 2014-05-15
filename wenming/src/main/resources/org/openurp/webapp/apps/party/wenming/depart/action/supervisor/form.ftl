[#ftl]
[@b.head/]
[@b.toolbar title="督察组成员编辑"]bar.addBack();[/@]
[@b.tabs]
    [@b.form action="!save" theme="list"]
      [@b.textfield name="supervisor.name" label="系统登录名称" value="${supervisor.name!}" 
        required="true" maxlength="50"/]
      [@b.textfield name="supervisor.fullname" label="真实姓名" value="${supervisor.fullname!}" 
        required="true" maxlength="50"/]
      [@b.password name="supervisor.password" label="密码" value="${supervisor.password!}" 
        required="true" maxlength="50"/]
      [@b.textarea name="supervisor.remark" label="说明" value="${supervisor.remark!}" 
        required="false" maxlength="50"/]
      [@b.datepicker name="supervisor.expiredOn" label="过期日期" value="${(supervisor.expiredOn?string('yyyy-MM-dd'))!}"
        required="true" datepicker=supervisor.expiredOn format="yyyy-MM-dd"/]
      [@b.formfoot]
        <input type="hidden" name="supervisor.id" value="${supervisor.id!}" />
        [@b.reset/]&nbsp;&nbsp;[@b.submit value="action.submit"/]
      [/@]
    [/@]
  [/@]
[@b.foot/]