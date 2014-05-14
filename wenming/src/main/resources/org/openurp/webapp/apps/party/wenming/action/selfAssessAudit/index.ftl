[#ftl]
[@b.head/]
<div id="selfAssessIndex" class="ajax_container">
  <div style="padding:10px;">
    [@b.toolbar title='文明单位互评'][/@]
    <div class="div_bar">
      <div>
        [@b.form action="!index" target="selfAssessIndex"]
          <label class="title">批次：</label>
          <div class="radiodiv">
            [#list assessSessions as v]
              <input type="radio" id="session${v.id}" name="session.id" value="${v.id}" [#if v.id == assessSession.id]checked="checked"[/#if]/><label for="session${v.id}">${v.name}</label>
            [/#list]
          </div>
        [/@]
      </div>
      <div style="margin:10px 0;">
        [@b.form action="!edit" target="selfAssessEdit"]
          <input type="hidden" name="session.id" value="${assessSession.id}"/>
          <input type="hidden" name="schema.id" value="${schema.id}"/>
          <label class="title">自评人员：</label>
          <div class="radiodiv">
            [#list users as v]
              <input type="radio" id="assessBy${v.id}" name="assessBy.id" value="${v.id}"/><label for="assessBy${v.id}">${v.name}(${v.fullname})</label>
            [/#list]
          </div>
        [/@]
      </div>
    </div>
    <div id="selfAssessEdit" class="ajax_container"></div>
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