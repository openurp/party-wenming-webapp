[#ftl]
[@b.head/]
[@b.toolbar title='督察组测评管理' /]
<div id="supercisorManageIndex" class="ajax_container">
  <div style="padding:10px;">
    <div class="div_bar inline">
      <div class="inline_forms">
        [@b.form action="!index" target="supercisorManageIndex"]
          <label>批次：</label>
          [@b.select name="session.id" label="测评批次" items=assessSessions value=assessSession onchange="bg.form.submit(this.form)"][/@]
        [/@]
        [@b.form action="!info" target="supercisorManageInfo"]
          <input type="hidden" name="session.id" value="${assessSession.id}"/>
        [/@]
      </div>
    </div>
    [@b.div id="supercisorManageInfo" href="!info?session.id=${assessSession.id}" /]
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