[#ftl]
[@b.head/]
[@b.toolbar title="加分项目编辑"]bar.addBack();[/@]
[@b.tabs]
  [@b.tab label="加分项目信息"]
    [@b.form action="!save" title="基本信息" theme="list"]
      [@b.textfield name="assessBonusItem.name" label="加分项目名称" value="${assessBonusItem.name!}" 
        style="width:400px;" required="true" maxlength="50"/]
      [@b.select name="assessBonusItem.bonusType.id" label="加分项目类型" value="${assessBonusItem.bonusType!}"
        style="width:400px;" required="true" items=bonusTypes option="id,name" empty="..."/]
      [@b.textfield name="assessBonusItem.method" label="加分方式" value="${assessBonusItem.method!}"
        style="width:400px;"required="true" maxlength="50"/]
      [@b.textarea rows="5" name="assessBonusItem.content" label="具体加分内容" value="${assessBonusItem.content!}"
        style="width:400px;"required="true" maxlength="50"/]
      [@b.select name="assessBonusItem.schema.id" label="所属方案" value="${assessBonusItem.schema!}"
        style="width:400px;" required="true" items=schemas option="id,name" empty="..."/]
      [@b.formfoot]
        <input type="hidden" name="assessBonusItem.id" value="${assessBonusItem.id!}" />
        [@b.reset/]&nbsp;&nbsp;[@b.submit value="action.submit"/]
      [/@]
    [/@]
  [/@]
[/@]
[@b.foot/]