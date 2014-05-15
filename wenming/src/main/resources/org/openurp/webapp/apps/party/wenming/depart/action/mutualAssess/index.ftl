[#ftl]
[@b.head/]
<div id="mutualAssessIndex" class="ajax_container">
  <div style="padding:10px;">
    <div class="div_bar inline">
      <div class="inline_forms">
        [@b.form action="!index" target="mutualAssessIndex"]
          <label>批次：</label>
          [@b.select name="session.id" label="测评批次" items=assessSessions value=assessSession onchange="bg.form.submit(this.form)"][/@]
        [/@]
        [@b.form action="!edit" target="mutualAssessEdit"]
          <input type="hidden" name="session.id" value="${assessSession.id}"/>
          <label>方案：</label>
          <div class="radiodiv">
            [#list schemas as v]
              <input type="radio" id="schema${v.id}" name="schema.id" value="${v.id}"/><label for="schema${v.id}">${v.name}</label>
            [/#list]
          </div>
        [/@]
      </div>
    </div>
    <div id="mutualAssessEdit" class="ajax_container"></div>
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