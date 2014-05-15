[#ftl]
[@b.head/]
[#assign assess = malist[0]/]
<table class="infoTable">
  <tr>
   <td class="title">所属批次:</td>
   <td class="content"> ${assess.session.name}</td>
   <td class="title">测评单位名称:</td>
   <td class="content">${assess.assessDepartment.name}</td>
  </tr>
  <tr>
   <td class="title">测评时间:</td>
   <td class="content">${assess.assessAt?string("YYYY-MM-dd HH:mm")}</td>
   <td class="title">审核状态:</td>
   <td class="content" style="color:red; font-weight:bold;">${assess.state.description}</td>
  </tr>
</table>
[@b.toolbar title='测评内容'][/@]
[@b.form action="!edit"]
  <table id="assessTable" class="gridtable assessTable">
    <thead>
      <tr>
        <th width="220">被评分单位/指标</th>
        [#list malist[0].items?sort_by(["item", "id"]) as v]
        <th>${v.item.content!}</th>
        [/#list]
        <th width="40">总分</th>
      </tr>
    </thead>
    <tbody>
      [#list malist as ma]
        <tr>
          <td>${ma.department.name}</td>
          [#list ma.items?sort_by(["item", "id"]) as item]
          <td>
            <span class="scoreSpan">${item.score}</span>
            <span class="scoreMaxSpan">${item.item.score}</span>
            <div class="scoreDiv" max="${item.item.score}" score="${item.score}"></div>
          </td>
          [/#list]
          <td align="center">${ma.totalScore!0}</td>
        </tr>
      [/#list]
    </tbody>
  </table>
  [#if editable??]
    <div style="text-align:center; padding:30px;" class="footdiv">
      <input type="hidden" name="schema.id" value="${malist[0].schema.id}"/>
      [@b.submit value="修改"/]
    </div>
  [/#if]
[/@]
<script>
  jQuery.struts2_jquery.require("/js/base/jquery-ui.js",null,bg.getContextPath() + "/static");
  jQuery.struts2_jquery.requireCss("/css/party/main.css",bg.getContextPath() + "/static");
  $(function (){
    $(".scoreDiv").each(function (){
      var div = $(this);
      div.slider({
        disabled:true,
        orientation: "horizontal",
        range: "min",
        step: 0.5,
        max: div.attr("max")*1,
        value: div.attr("score")*1
      });
    });
  });
</script>
[@b.foot/]