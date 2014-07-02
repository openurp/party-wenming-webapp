[#ftl]
[@b.head/]
[@b.toolbar title="优秀项目投票"/]
<div id="voteIndex" class="ajax_container">
  <div style="padding:10px;">
    <div class="div_bar inline">
      <div class="inline_forms">
        [@b.form action="!index" target="voteIndex"]
          <label>批次：</label>
          [@b.select name="session.id" label="测评批次" items=wenmingSessions value=wenmingSession onchange="bg.form.submit(this.form)"][/@]
        [/@]
      </div>
    </div>
    [#if wenmingSessions?size gt 0]
      [@b.div id="voteEdit" href = "!info?session.id=${wenmingSessions[0].id}"][/@]
    [/#if]
  </div>
  <script>
    jQuery.struts2_jquery.require("/js/base/jquery-ui.js",null,bg.getContextPath() + "/static");
    jQuery.struts2_jquery.requireCss("/css/party/main.css",bg.getContextPath() + "/static");
  </script>
</div>
[@b.foot/]