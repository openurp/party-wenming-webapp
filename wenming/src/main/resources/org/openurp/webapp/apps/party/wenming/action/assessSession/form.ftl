[#ftl]
[@b.head/]
[@b.toolbar title="考核批次编辑"]bar.addBack();[/@]
[@b.tabs]
  [@b.tab label="考核批次信息"]
    [@b.form action="!save" title="基本信息" theme="list"]
      [#if repeatDepartmentList??]
      [@b.field label="部门重复"]
        <div style="padding-left:110px; color:red;">
          [#list repeatDepartmentList as v]
        [#if v_index gt 0]、[/#if]${v}
        [/#list]
        </div>
      [/@]
      [/#if]
      [@b.textfield name="assessSession.name" label="批次名称" value="${assessSession.name!}" required="true" maxlength="50"/]
      [@b.startend label="测评开始结束时间" 
        name="assessSession.beginOn,assessSession.endOn" required="true,false" 
        start=assessSession.beginOn end=assessSession.endOn format="datetime"/]
      [@b.startend label="投票开始结束时间" 
        name="assessSession.voteBeginOn,assessSession.voteEndOn" required="false,false" 
        start=assessSession.voteBeginOn end=assessSession.voteEndOn format="datetime"/]
      [@b.select2 label="关联方案" name1st="assessSchemaId" name2nd="schemaId" 
        items1st=schemas items2nd= assessSession.schemas 
        option="id,name"/]
      [@b.radios label="是否有效"  name="assessSession.enabled" value=assessSession.enabled items="1:common.yes,0:common.no"/]
      [@b.formfoot]
        <input type="hidden" name="assessSession.id" value="${assessSession.id!}" />
        [@b.reset/]&nbsp;&nbsp;[@b.submit value="action.submit"/]
      [/@]
    [/@]
  [/@]
[/@]
[@b.foot/]