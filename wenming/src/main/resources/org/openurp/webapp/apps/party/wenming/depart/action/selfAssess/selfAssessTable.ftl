[#ftl]
[#if !selfAssess??][#assign selfAssess=malist[0]/][/#if]
[@b.toolbar title='测评内容'][/@]
<table id="assessTable" class="gridtable assessTable">
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
        <tr style="font-size:14px; color:red;">
          <td>${group.indexno}</td>
          <td>${group.name}</td>
          <td>&nbsp;</td>
        </tr>
      [/#if]
      <tr>
        [#assign name="sai${v_index}"/]
        <input type="hidden" name="index" value="${name}"/>
        <input type="hidden" name="${name}.item.id" value="${v.item.id}"/>
        <td>${group.indexno}-${v.item.orderNumber}</td>
        <td>${v.item.content}</td>
        <td>
            <input name="${name}.score" type="hidden" class="score" value="${v.score!}"/>
            <span class="scoreSpan">${v.score!0}</span>
            <span class="scoreMaxSpan">${v.item.score}</span>
            <div class="scoreDiv" max="${v.item.score}" score="${v.score!(v.item.score/2)}"></div>
        </td>
      </tr>
    [/#list]
  </tbody>
</table>

<script>
  jQuery.struts2_jquery.require("/js/base/jquery-ui.js",null,bg.getContextPath() + "/static");
  jQuery.struts2_jquery.requireCss("/css/party/main.css",bg.getContextPath() + "/static");
  function isallselected(){
    var haserror = false;
    $("#assessTable .score").parent().removeClass("error").end().each(function (){
      if(this.value == ""){
        haserror = true;
        $(this).parent().addClass("error");
      }
    });
    if(haserror){
      alert("请选择一个的分值");
    }
    return !(haserror);
  }
  
  $(function (){
    $(".scoreDiv").each(function (){
      var div = $(this);
      div.slider({
        orientation: "horizontal",
        range: "min",
        step: 0.5,
        max: div.attr("max")*1,
        value: div.attr("score")*1,
        slide: function (){refreshSwatch(div)},
        change: function (){refreshSwatch(div)}
      });
    });
    
    function refreshSwatch(div){
      var score = div.slider("value");
      if(score % 1 == 0) score += ".0";
      div.parent().removeClass("error");
      div.parent().find(".score").val(score);
      div.parent().find(".scoreSpan").html(score);
    }
  });
</script>