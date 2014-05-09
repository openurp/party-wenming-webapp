[#ftl]
[@b.head/]
[@b.toolbar title="评价方案编辑"]bar.addBack();[/@]
[@b.tabs]
  [@b.tab label="评价方案信息"]
    [@b.form action="!save" title="基本信息" theme="list"]
      [@b.textfield name="assessSchema.name" label="方案名称" value="${assessSchema.name!}" required="true" maxlength="50"/]
      [@b.select2 label="包含部门" name1st="departmentId" name2nd="departId" 
        items1st=departments items2nd= assessSchema.departs?sort_by("code") 
        option="id,name"/]
       [@b.formfoot]
        <input type="hidden" name="assessSchema.id" value="${assessSchema.id!}" />
        [@b.reset/]&nbsp;&nbsp;[@b.submit value="action.submit"/]
      [/@]
    [/@]
  [/@]
[/@]
[@b.foot/]