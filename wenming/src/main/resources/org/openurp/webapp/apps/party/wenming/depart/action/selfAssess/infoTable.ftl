[#ftl]
<table class="infoTable">
  <tr>
   <td class="title">单位名称:</td>
   <td class="content">${selfAssess.department.name}</td>
   <td class="title">所属批次:</td>
   <td class="content"> ${selfAssess.session.name}</td>
   <td class="title">自评时间:</td>
   <td class="content">${selfAssess.assessAt?string("YYYY-MM-dd HH:mm")}</td>
  </tr>
  <tr>
   <td class="title">自评人员:</td>
   <td class="content">${selfAssess.assessBy.name}（${selfAssess.assessBy.fullname}）</td>
   <td class="title">自评总分:</td>
   <td class="content" style="color:red; font-weight:bold;"> ${selfAssess.totalScore}</td>
   <td class="title">审核状态:</td>
   <td class="content" style="color:red; font-weight:bold;">${selfAssess.state.description}</td>
  </tr>
</table>
[@b.toolbar title="测评内容"][/@]
<table id="selfAssessTable" class="gridtable assessTable">
  <thead class="gridhead">
    <tr style="height:30px; font-size:16px;">
      <th width="80">指标编号</th>
      <th>指标内容</th>
      <th width="200">自评分值</th>
    </tr>
  </thead>
  <tbody>
    [#list selfAssess.items as v]
      [#if !group?? || v.item.group.id != group.id]
        [#assign group = v.item.group/]
        <tr style="font-size:14px; color:red;" class="group" value="${group.id}">
          <td>${group.indexno}</td>
          <td>${group.name}</td>
          <td align="center">
              <span class="scoreSpan"></span>
              <span class="scoreMaxSpan"></span>
              <div class="scoreDiv"></div>
          </td>
        </tr>
      [/#if]
      <tr class="group${group.id}">
        [#assign name="sai${v_index}"/]
        <input type="hidden" name="index" value="${name}"/>
        <input type="hidden" name="${name}.item.id" value="${v.item.id}"/>
        <td>${group.indexno}-${v.item.orderNumber}</td>
        <td>${v.item.content}</td>
        <td align="center">
            <span class="scoreSpan">${v.score!0}</span>
            <span class="scoreMaxSpan">${v.item.score}</span>
            <div class="scoreDiv" max="${v.item.score}" score="${v.score!0}"></div>
        </td>
      </tr>
    [/#list]
  </tbody>
</table>
<div style="text-align:right; padding:10px;">
  <span style="font-size:16px;">自评总分：</span><span style="font-size:22px;">${selfAssess.totalScore}</span>
</div>
<script>
  jQuery.struts2_jquery.require("/js/base/jquery-ui.js",null,bg.getContextPath() + "/static");
  jQuery.struts2_jquery.requireCss("/css/party/main.css",bg.getContextPath() + "/static");
  $(".group").each(function (){
    var id = $(this).attr("value"), sum = 0, score = 0;
    $(".group" + id).each(function (){
      sum += $(this).find(".scoreMaxSpan").html() * 1;
      score += $(this).find(".scoreSpan").html() * 1;
    });
    $(this).find(".scoreMaxSpan").html(sum);
    $(this).find(".scoreSpan").html(score);
    $(this).find(".scoreDiv").attr("max", sum).attr("score", score);
  });
  $(function (){
    $(".scoreDiv").each(function (){
      var div = $(this);
      div.slider({
        disabled:true,
        orientation: "horizontal",
        range: "min",
        step: 0.5,
        max: div.attr("max")*1,
        value: div.attr("score")*1,
        slide: function (){refreshSwatch(div)},
        change: function (){refreshSwatch(div)}
      });
    });
  });
</script>