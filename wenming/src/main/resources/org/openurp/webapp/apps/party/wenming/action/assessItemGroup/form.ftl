[#ftl]
[@b.head/]
[@b.toolbar title="测评指标分类编辑"]bar.addBack();[/@]
[@b.form action="!save" title="基本信息" theme="list"]
  [@b.textfield name="assessItemGroup.name" label="名称" value="${assessItemGroup.name!}" required="true" maxlength="50"/]
  [@b.textfield name="indexno" label="排序" value="${assessItemGroup.index!}" required="true" maxlength="50"/]
  [@b.select label="相关方案" name="schema.id" value=(assessItemGroup.schema.id)! 
    items=schemas?sort_by("name") option=r"${item.name}" empty="..."/]
  [#--
  [@b.select label="上级分类" name="parent.id" value=(assessItemGroup.parent.id)! 
    items=groups?sort_by("indexno") option=r"${item.indexno} ${item.name}" empty="..."/]
  --]
  [@b.formfoot]
    <input type="hidden" name="assessItemGroup.id" value="${assessItemGroup.id!}" />
    [@b.reset/]&nbsp;&nbsp;[@b.submit value="action.submit"/]
  [/@]
[/@]
[@b.foot/]