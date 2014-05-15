[#ftl]
[@b.textfield name="goodProject.name" label="项目名称" value="${goodProject.name!}" required="true" maxlength="200"/]
[@b.startend label="开始结束时间" 
  name="goodProject.beginOn,goodProject.endOn" required="true,true"
  start=goodProject.beginOn end=goodProject.endOn format="date"/]
[@b.textarea label="申报理由" cols="50" rows="4" name="goodProject.statements" value=(goodProject.statements)! maxlength="1000" required="true"/]
[@b.textarea label="项目方案" cols="50" rows="8" name="goodProject.plan" value=(goodProject.plan)! maxlength="3000" required="true"/]
[@b.textarea label="特色与创新点" cols="50" rows="4" name="goodProject.features" value=(goodProject.features)! maxlength="1000" required="true"/]
[@b.textfield name="goodProject.contactPerson" label="联系人" value="${goodProject.contactPerson!}" required="true" maxlength="100"/]
[@b.textfield name="goodProject.contactPhone" label="联系电话" value="${goodProject.contactPhone!}" required="true" maxlength="30"/]
[@b.field label="相关支撑材料"]<input name="attachment" type="file"/>（上传文件请控制在10M以内，如果有多份附件请打成压缩包上传）[/@]
 