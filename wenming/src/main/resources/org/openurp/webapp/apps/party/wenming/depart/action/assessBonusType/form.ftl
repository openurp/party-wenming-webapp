[#ftl]
[@b.head/]
[@b.toolbar title="加分项目编辑"]bar.addBack();[/@]
[@b.tabs]
    [@b.form action="!save" theme="list"]
      [@b.textfield name="assessBonusType.code" label="加分类型代码" value="${assessBonusType.code!}" 
        style="width:200px;" required="true" maxlength="50"/]
      [@b.textfield name="assessBonusType.name" label="加分类型名称" value="${assessBonusType.name!}" 
        style="width:200px;" required="true" maxlength="50"/]
      [@b.formfoot]
        <input type="hidden" name="assessBonusType.id" value="${assessBonusType.id!}" />
        [@b.reset/]&nbsp;&nbsp;[@b.submit value="action.submit"/]
      [/@]
    [/@]
  [/@]
[@b.foot/]