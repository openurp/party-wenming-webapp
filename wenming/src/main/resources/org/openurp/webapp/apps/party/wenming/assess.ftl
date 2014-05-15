[#ftl]
[#macro assessTable assessList]
[@b.toolbar title='测评内容'][/@]
  <table id="assessTable" class="gridtable assessTable">
    <thead>
      <tr>
        [#if partMode??]
          <th width="20"></th>
        [/#if]
        <th width="220">被评分单位/指标</th>
        [#list assessList[0].items?sort_by(["item", "id"]) as v]
        <th>${v.item.content}</th>
        [/#list]
      </tr>
    </thead>
    <tbody>
      [#list assessList?sort_by(["department","code"]) as assess]
        <tr>
          [#assign name="assess${assess_index}"/]
          <input type="hidden" name="index" value="${name}"/>
          <input type="hidden" name="${name}.id" value="${assess.id!}"/>
          <input type="hidden" name="${name}.schema.id" value="${schema.id}"/>
          <input type="hidden" name="${name}.department.id" value="${assess.department.id}"/>
          [#if partMode??]
            <td align="center"><input type="checkbox" class="partIpt"/></td>
          [/#if]
          <td>${assess.department.name}</td>
          [#list assess.items?sort_by(["item", "id"]) as item]
          <td>
            [#assign itemName="item${assess.department.id}${item.item.id}"/]
            <input type="hidden" name="item${assess.department.id}" value="${itemName}"/>
            <input type="hidden" name="${itemName}.id" value="${item.id!}"/>
            <input type="hidden" name="${itemName}.item.id" value="${item.item.id}"/>
            <input type="hidden" name="${itemName}.score" value="${item.score!}" class="score"/>
            <span class="scoreSpan">${item.score!0}</span>
            <span class="scoreMaxSpan">${item.item.score!0}</span>
            <div class="scoreDiv" max="${item.item.score}" score="${item.score!0}"></div>
          </td>
          [/#list]
        </tr>
      [/#list]
    </tbody>
  </table>
  <script>
    jQuery.struts2_jquery.require("/js/base/jquery-ui.js",null,bg.getContextPath() + "/static");
    jQuery.struts2_jquery.requireCss("/css/party/main.css",bg.getContextPath() + "/static");
    [#if partMode??]
      $("#assessTable tr").not(function (){
        return $(this).find(".score").val() != ""
      }).addClass("ignore");
    [/#if]
    $(".partIpt").click(function (){
      var tr = $(this).closest("tr");
      if(this.checked){
        tr.removeClass("ignore");
        tr.find("input").removeAttr("disabled");
      }else{
        tr.addClass("ignore").find(".error").removeClass("error");
        tr.find("input").not(".partIpt").attr("disabled", "disabled");
      }
    });
    function isallselected(){
      var haserror = false;
      var trs = $("#assessTable tr").not(".ignore");
      trs.find(".score").parent().removeClass("error").end().each(function (){
        if(this.value == ""){
          haserror = true;
          $(this).parent().addClass("error");
        }
      });
      if(haserror || trs.length <= 1){
        alert("请选择一个的分值");
        haserror = true;
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
[/#macro]