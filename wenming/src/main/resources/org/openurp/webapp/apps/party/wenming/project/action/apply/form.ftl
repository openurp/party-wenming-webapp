[#ftl]
[@b.head/]
[@b.toolbar title="考核批次编辑"]bar.addBack();[/@]
[@b.tabs]
  [@b.tab label="考核批次信息"]
    [@b.form action="!save" title="基本信息" theme="list"]
      [@b.textfield name="goodProject.name" label="项目名称" value="${goodProject.name!}" required="true" maxlength="200"/]
      [@b.startend label="投票开始结束时间" 
        name="goodProject.beginOn,goodProject.endOn" required="false,false"
        start=goodProject.beginOn end=goodProject.endOn format="date"/]
      [@b.textarea label="申报理由" cols="50" rows="4" name="goodProject.statements" value=(goodProject.statements)! maxlength="1000" required="true"/]
      [@b.textarea label="项目方案" cols="50" rows="8" name="goodProject.plan" value=(goodProject.plan)! maxlength="3000" required="true"/]
      [@b.textarea label="特色与创新点" cols="50" rows="4" name="goodProject.features" value=(goodProject.features)! maxlength="1000" required="true"/]
      [@b.textfield name="goodProject.contactPerson" label="联系人" value="${goodProject.contactPerson!}" required="true" maxlength="100"/]
      [@b.textfield name="goodProject.contactPhone" label="联系电话" value="${goodProject.contactPhone!}" required="true" maxlength="30"/]
      [@b.field label="相关支撑材料"]<input name="attachment" type="file"/>（上传文件请控制在10M以内，如果有多份附件请打成压缩包上传）[/@]
      [@b.formfoot]
        <input type="hidden" name="goodProject.id" value="${goodProject.id!}" />
        <input type="hidden" name="save" value="0" id="saveIpt"/>
        [@b.reset/]
        [@b.submit value="action.save" onsubmit="saveForm"/]
        [@b.submit value="action.submit" onsubmit="submitForm"/]
      [/@]
    [/@]
  [/@]
[/@]
<script>
  function saveForm(){
    $("#saveIpt").val("1");
    return true;
  }
  
  function submitForm(){
    return confirm("提交后不能修改，是否确定提交？")
  }
</script>
[@b.foot/]