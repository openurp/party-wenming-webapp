[#ftl]
[@b.head/]
<div id="supervisorAssessIndex" class="ajax_container">
  <div style="padding:10px;">
    <style>.div_bar a{margin-right:10px;}.div_bar a.active{back}.radiodiv{display:inline;}</style>
    [@b.toolbar title='督察组测评'][/@]
    <div class="div_bar">
      <div>
        [@b.form action="!index" target="supervisorAssessIndex"]
          <label>批次：</label>
          <div class="radiodiv">
            [#list assessSessions as v]
              <input type="radio" id="session${v.id}" name="session.id" value="${v.id}" [#if v.id == assessSession.id]checked="checked"[/#if]/><label for="session${v.id}">${v.name}</label>
            [/#list]
          </div>
        [/@]
      </div>
      <div style="margin:10px 0;">
        [@b.form action="!edit" target="supervisorAssessEdit"]
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
    <div id="supervisorAssessEdit" class="ajax_container"></div>
  </div>
  <script>
    jQuery.struts2_jquery.require("/js/base/jquery-ui.js",null,bg.getContextPath() + "/static");
    $(function (){
      $(".radiodiv").buttonset();
      $(".radiodiv input").click(function (event){
        bg.form.submit($(this).closest("form").get(0));
      });
    });
  </script>
</div>
[@b.foot/]