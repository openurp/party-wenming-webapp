[#ftl]
[@b.head/]
[@b.toolbar title="优秀项目中期总结"]bar.addBack();[/@]
[@b.form action="!saveMiddleSummary" theme="list" enctype="multipart/form-data"]
  [@b.field label="中期总结上传"]<input name="attachment" id="attachment" type="file"/>（上传文件请控制在10M以内，如果有多份附件请打成压缩包上传）[/@]
  [@b.formfoot]
    <input type="hidden" name="goodProjectMiddleSummary.id" value="${goodProjectMiddleSummary.id!}" />
    <input type="hidden" name="goodProjectMiddleSummary.good.id" value="${goodProjectMiddleSummary.good.id!}" />
    <input type="hidden" name="save" value="0" id="saveIpt"/>
    [@b.reset/]
    [@b.submit value="action.submit" onsubmit="beforesubmit"/]
  [/@]
<script>
  function beforesubmit(){
    if($("#attachment").val() == ""){
      alert("请上传附件");
      return false;
    }
    return true;
  }
</script>
[/@]
[@b.foot/]