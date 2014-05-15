[#ftl]
[@b.head/]
[@b.form action="!edit"]
  [#if malist?? && malist?size gt 0]
    [#assign selfAssess = malist[0]/]
    [#include "../selfAssess/infoTable.ftl"/]
    [#if editable??]
      <div class="footdiv">
        <input type="hidden" name="schema.id" value="${malist[0].schema.id}"/>
        <input type="hidden" name="session.id" value="${malist[0].session.id}"/>
        <input type="hidden" name="assessBy.id" value="${malist[0].assessBy.id}"/>
        [@b.submit value="审核"/]
      </div>
    [/#if]
  [#else]
    没有自评记录
  [/#if]
[/@]
<script>
  jQuery.struts2_jquery.require("/js/base/jquery-ui.js",null,bg.getContextPath() + "/static");
  $(function (){
    $(".scoreDiv").each(function (){
      var div = $(this);
      div.slider({
        disabled:true,
      });
    });
  });
</script>
[@b.foot/]