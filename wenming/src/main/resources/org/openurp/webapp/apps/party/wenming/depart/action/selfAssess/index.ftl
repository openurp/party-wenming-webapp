[#ftl]
[@b.head/]
[#--
<table  class="indexpanel">
  <tr>
    <td class="index_view">
    [@b.form name="selfAssessSearchForm"  action="!search" target="selfAssesslist" title="ui.searchForm" theme="search"]
      [@b.textfields names="selfAssess.name;批次名称"/]
      [@b.select name="selfAssess.enabled" label="是否有效" items={'true':'是','false':'否'} empty="..."/]
    [/@]
    </td>
    <td class="index_content">[@b.div id="selfAssesslist" href="!search?orderBy=id desc" /]</td>
  </tr>
</table>
--]
[#assign assessSession = sessions?first/]
<div id="selfAssessIndex" class="ajax_container">
  <div style="padding:0 10px;">
    <div class="div_bar">
      <div class="inline_forms">
        [@b.form action="!info" target="selfSessionInfo" title="ui.searchForm" ]
        <b>批次:</b> [@b.select name="session.id" label="测评批次" items=sessions value=assessSession onchange="bg.form.submit(this.form)"][/@]
        [/@]
      </div>
    </div>
    [@b.div id="selfSessionInfo" href="!info?session.id=${assessSession.id}" /]
  </div>
  <script>
    jQuery.struts2_jquery.require("/js/base/jquery-ui.js",null,bg.getContextPath() + "/static");
    jQuery.struts2_jquery.requireCss("/css/party/main.css",bg.getContextPath() + "/static");
    $(function (){
      $(".radiodiv").buttonset();
      $(".radiodiv input").click(function (event){
        bg.form.submit($(this).closest("form").get(0));
      });
    });
  </script>
</div>
[@b.foot/]