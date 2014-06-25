[#ftl]
[@b.head/]
[@b.toolbar title="优秀项目年终总结"]bar.addBack();[/@]
<style>
form.listform label.title {
width: 130px;
}
</style>
[@b.form action="!saveFinalSummary" theme="list" enctype="multipart/form-data"]
  [#if ifAdvise?? && ifAdvise]
    [@b.field label="文明办审核意见"]<span style="color:red; font-size:16px">${goodProjectFinalSummary.auditOpinion!}</span>[/@]
  [/#if]
  [@b.textarea label="项目内容及成效" cols="50" rows="4" name="goodProjectFinalSummary.content" value=(goodProjectFinalSummary.content)! maxlength="1000" required="true"/]
  [@b.radios label="是否参加学年末评选"  name="goodProjectFinalSummary.forAssess" value=goodProjectFinalSummary.forAssess items="1:common.yes,0:common.no"/]
  [@b.field label="年终总结上传"]<input name="attachment" id="attachment" type="file"/>（上传文件请控制在10M以内，如果有多份附件请打成压缩包上传）[/@]
  [@b.formfoot]
    <input type="hidden" name="goodProjectFinalSummary.id" value="${goodProjectFinalSummary.id!}" />
    <input type="hidden" name="goodProjectFinalSummary.good.id" value="${goodProjectFinalSummary.good.id!}" />
    <input type="hidden" name="save" value="0" id="saveIpt"/>
    [@b.reset/]
    [@b.submit value="action.submit" onsubmit="beforesubmit"/]
  [/@]
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
[@b.foot/]