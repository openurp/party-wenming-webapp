[#ftl]
[@b.head/]
<div id="selfAssessIndex" class="ajax_container">
  <div style="padding:0 10px;">
    <div class="div_bar">
      <div class="inline_forms">
        [@b.form action="!index" target="selfAssessIndex"]
          <b>批次:</b>
          [@b.select name="session.id" label="测评批次" items=assessSessions value=assessSession onchange="bg.form.submit(this.form)"][/@]
        [/@]
        [#--
        [@b.form action="!edit" target="selfAssessEdit"]
          <input type="hidden" name="session.id" value="${assessSession.id}"/>
          <input type="hidden" name="schema.id" value="${schema.id}"/>
          <label>自评人员：</label>
          <div class="radiodiv">
            [#list users as v]
              <input type="radio" id="assessBy${v.id}" name="assessBy.id" value="${v.id}"/><label for="assessBy${v.id}">${v.fullname}(${v.name})</label>
            [/#list]
          </div>
        [/@]
        --]
      </div>
    </div>
    [@b.div id="selfAssessEdit" href="!info?schema.id=${schema.id}&session.id=${assessSession.id}"/]
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