[#ftl]
[@b.head/]
[@b.toolbar title='文明委投票' /]
<div id="voteIndex" class="ajax_container">
  <div style="padding:10px;">
    [#if assessSessions?size gt 0]
    <div class="div_bar inline">
      <div class="inline_forms">
        [@b.form action="!index" target="voteIndex"]
          <label>批次：</label>
          [@b.select name="session.id" label="测评批次" items=assessSessions value=assessSession onchange="bg.form.submit(this.form)"][/@]
        [/@]
        [#if assessSession??]
          [@b.form action="!edit" target="voteEdit"]
            <input type="hidden" name="session.id" value="${(assessSession.id)!}"/>
            <label>部门类型：</label>
            <div class="radiodiv">
              <input type="radio" id="departForTeach" name="departmentType" value="1"/><label for="departForTeach">教学部门</label>
              <input type="radio" id="departForFunc" name="departmentType" value="0"/><label for="departForFunc">职能部门</label>
            </div>
          [/@]
        [/#if]
      </div>
    </div>
    <div id="voteEdit"class="ajax_container"></div>
    [#else]
    没有投票任务。
    [/#if]
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