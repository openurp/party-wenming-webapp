[#ftl]
[@b.head/]
[@b.toolbar title='文明单位互评'][/@]
<div style="padding:10px;">
  [#if assessSession??]
  [#assign schemas = assessSession.schemas/]
  <div>
    [@b.form action="!edit" target="mutualAssessEdit"]
    请选择方案：
    <select name="schema.id" onchange="if(this.value != '')bg.form.submit(this.form);">
    <option value="">请选择...</option>
    [#list schemas as v]
    <option value="${v.id}">${v.name}</option>
    [/#list]
    </select>
    [/@]
  </div>
  [#else]
  <p>当前没有可评价的批次</p>
  [/#if]
  <div id="mutualAssessEdit" class="ajax_container"></div>
</div>
[@b.foot/]