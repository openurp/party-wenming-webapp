[#ftl]
[@b.head/]
<div id="mutualAssessIndex" class="ajax_container">
  <div style="padding:10px;">
    [@b.toolbar title='文明单位互评'][/@]
    <div class="div_bar">
      <div class="inline_forms">
        [@b.form action="!search" target="mutualAssessList"]
          <input type="hidden" name="session.id" value="${assessSession.id}"/>
          <label>状态：</label>
          <div class="radiodiv">
              <input type="radio" id="state1" name="forAudit" value="1" checked="checked"/><label for="state1">待审核</label>
              <input type="radio" id="state2" name="forAudit" value="0"/><label for="state2">其它</label>
          </div>
        [/@]
      </div>
    </div>
    [@b.div id="mutualAssessList" href="!search?forAudit=1"/]
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