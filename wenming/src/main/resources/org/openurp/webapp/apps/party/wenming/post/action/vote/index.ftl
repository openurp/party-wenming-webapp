[#ftl]
[@b.head/]
<div id="voteIndex" class="ajax_container">
  [@b.toolbar title=title!"文明示范岗投票"/]
  <div style="padding:10px;">
    <div class="div_bar inline">
      <div class="inline_forms">
        [@b.form action="!index" target="voteIndex"]
          <label>批次：</label>
          [@b.select name="session.id" label="测评批次" items=wenmingSessions value=wenmingSession onchange="bg.form.submit(this.form)"][/@]
        [/@]
      </div>
    </div>
    [#if wenmingSession??]
      [@b.div id="voteEdit" href = "!info?session.id=${wenmingSession.id}"][/@]
    [/#if]
  </div>
  <script>
    jQuery.struts2_jquery.require("/js/base/jquery-ui.js",null,bg.getContextPath() + "/static");
    jQuery.struts2_jquery.requireCss("/css/party/main.css",bg.getContextPath() + "/static");
  </script>
</div>
[@b.foot/]