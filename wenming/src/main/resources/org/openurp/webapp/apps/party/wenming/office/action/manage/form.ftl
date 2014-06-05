[#ftl]
[@b.head/]
[@b.toolbar title="文明科室审核"]bar.addBack();[/@]
[@b.form id="goodOfficeFormid" name="goodOfficeFormid" action="!save" theme="list" enctype="multipart/form-data"]
  [@b.field label="申报部门"]${goodOffice.department.name}[/@]
  [@b.field label="文明科室"]${goodOffice.offices}[/@]
  [@b.field label="相关支撑材料"]
    &nbsp;
    [#if goodOffice.attachment??]
    [@b.a target="_blank" href="../attachment?path=${goodOffice.attachment.filePath}&name=${goodOffice.attachment.name?url('utf-8')}"]${(goodOffice.attachment.name)!}[/@]</p>
    [/#if]
  [/@]
  [@b.formfoot]
    <input type="hidden" name="goodOffice.id" value="${goodOffice.id!}" />
    <input type="hidden" name="save" value="0" id="saveIpt"/>
    <input type="hidden" name="passed" value="0" id="passedIpt"/>
    <input type="hidden" name="auditOpinion" id="auditOpinion"/>
    [@b.submit value="通过" onsubmit="passForm"/]
    [@b.submit value="不通过" onsubmit="unpassForm"/]
  [/@]
[/@]
<script>
  function passForm(){
    if(confirm("是否确定审核通过？")){
      $("#passedIpt").val("1");
      return true;
    }
    return false;
  }
  
  function unpassForm(){ 
    var text=prompt("请填写审批意见");
    if(text != null && text != ""){
      $("#auditOpinion").val(text);
      return true;
    }else{
    alert("请填写审批意见");
    }
  }
</script>
[@b.foot/]
