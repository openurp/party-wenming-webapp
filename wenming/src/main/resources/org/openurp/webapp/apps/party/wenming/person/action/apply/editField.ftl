[#ftl]
[@b.textfield name="goodPerson.name" label="项目名称" value="${goodPerson.name!}" required="true" maxlength="200"/]
[@b.startend label="开始结束时间" 
  name="goodPerson.beginOn,goodPerson.endOn" required="true,true"
  start=goodPerson.beginOn end=goodPerson.endOn format="date"/]
[@b.textarea label="申报理由" cols="50" rows="4" name="goodPerson.statements" value=(goodPerson.statements)! maxlength="1000" required="true"/]
[@b.textarea label="特色与创新点" cols="50" rows="4" name="goodPerson.features" value=(goodPerson.features)! maxlength="1000" required="true"/]
[@b.textfield name="goodPerson.contactPerson" label="联系人" value="${goodPerson.contactPerson!}" required="true" maxlength="100"/]
[@b.textfield name="goodPerson.contactPhone" label="联系电话" value="${goodPerson.contactPhone!}" required="true" maxlength="30"/]
[@b.field label="相关支撑材料"]<input name="attachment" type="file"/>（上传文件请控制在10M以内，如果有多份附件请打成压缩包上传）[/@]
 