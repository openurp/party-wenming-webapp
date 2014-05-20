[#ftl]
[@b.head/]
[@b.form action="!save" title="申报基本信息" theme="list" enctype="multipart/form-data"]
  [@b.textarea id="assessApply_activitySummary"  name="assessApply.activitySummary" label="创建活动及其效果概要" value="${assessApply.activitySummary!}" required="true"  cols="100" rows="5" maxlength="2000" comment="300字以内" editor="kind"/]
  [@b.textarea id="assessApply_wenmingSummary"  name="assessApply.wenmingSummary" label="文明创建特色工作概要" value="${assessApply.wenmingSummary!}" required="true"  cols="100" rows="5" maxlength="2000" comment="300字以内" editor="kind"/]
  [@b.textarea name="assessApply.detail" label="详实材料" value="${assessApply.detail!}" required="true"  cols="100" rows="15" maxlength="8000" comment="1800字以内" editor="kind"/]
  [@b.field label="相关支撑材料"]<input name="attachment" type="file"/>（可提供相关扫描件、文档、图片、表格等，如有多个文件，请放在一个文件夹里，打包成压缩包进行上传）[/@]
  [@b.formfoot]
    <input type="hidden" name="assessApply.id" value="${assessApply.id!}" />
    [#if !(assessApply.id??)]
    <input type="hidden" name="assessApply.session.id" value="${Parameters['assessApply.session.id']}" />
    [/#if]
    [@b.reset/]&nbsp;&nbsp;[@b.submit value="action.save"/]
  [/@]
[/@]

[@b.foot/]