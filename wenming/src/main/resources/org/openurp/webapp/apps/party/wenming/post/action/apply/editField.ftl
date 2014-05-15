[#ftl]
[@b.textfield name="goodPost.name" label="项目名称" value="${goodPost.name!}" required="true" maxlength="200"/]
[@b.startend label="开始结束时间" 
  name="goodPost.beginOn,goodPost.endOn" required="true,true"
  start=goodPost.beginOn end=goodPost.endOn format="date"/]
[@b.textarea label="申报理由" cols="50" rows="4" name="goodPost.statements" value=(goodPost.statements)! maxlength="1000" required="true"/]
[@b.textarea label="项目方案" cols="50" rows="8" name="goodPost.plan" value=(goodPost.plan)! maxlength="3000" required="true"/]
[@b.textarea label="特色与创新点" cols="50" rows="4" name="goodPost.features" value=(goodPost.features)! maxlength="1000" required="true"/]
[@b.textfield name="goodPost.contactPerson" label="联系人" value="${goodPost.contactPerson!}" required="true" maxlength="100"/]
[@b.textfield name="goodPost.contactPhone" label="联系电话" value="${goodPost.contactPhone!}" required="true" maxlength="30"/]
[@b.field label="相关支撑材料"]<input name="attachment" type="file"/>（上传文件请控制在10M以内，如果有多份附件请打成压缩包上传）[/@]
 