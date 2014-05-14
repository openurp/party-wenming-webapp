[#ftl]
[@b.head/]
[#include "/org/openurp/webapp/apps/party/wenming/assess.ftl"/]
[@assessTable malist/]
<script>
  jQuery.struts2_jquery.require("/js/base/jquery-ui.js",null,bg.getContextPath() + "/static");
  $(function (){
    $(".scoreDiv").each(function (){
      var div = $(this);
      div.slider({
        disabled:true,
        orientation: "horizontal",
        range: "min",
        step: 0.5,
        max: div.attr("max")*1,
        value: div.attr("score")*1
      });
    });
  });
</script>
[@b.foot/]