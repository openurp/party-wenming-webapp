[#ftl]
[@b.head/]
[@b.form action="!save" title="申报基本信息" theme="list"]
  [@b.textarea name="goodOffice.offices" label="文明科室" value="${goodOffice.offices!}" required="true"  cols="100" rows="5" maxlength="500" comment="300字以内"/]
  [@b.field label="评比办法及支撑材料"]<input name="attachment" type="file"/>（大小控制在20M以内）[/@]
  [@b.formfoot]
    <input type="hidden" name="goodOffice.id" value="${goodOffice.id!}" />
    [#if !(goodOffice.id??)]
    <input type="hidden" name="goodOffice.session.id" value="${Parameters['goodOffice.session.id']}" />
    [/#if]
    [@b.reset/]&nbsp;&nbsp;[@b.submit value="action.save"/]
  [/@]
[/@]
[@b.foot/]