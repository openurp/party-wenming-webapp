[#ftl]
[@b.head/]
  [@b.form action="!save"]
    <style>
      .score{width:60px;}
      .footdiv input{margin-right:200px;}
      .error{background-color:red}
      .error .scoreSpan{color:#fff;}
      .scoreSpan{float:left;}
      .scoreDiv{margin-left:24px;}
      .assessTable td{padding:5px 15px 3px 5px;}
    </style>
    <table id="assessTable" class="gridtable assessTable">
      <thead>
        <tr>
          <th>被评分单位/指标</th>
          [#list items?sort_by("id") as v]
          <th>${v.content}</th>
          [/#list]
        </tr>
      </thead>
      <tbody>
        [#list malist as ma]
          <tr>
            [#assign name="ma${ma_index}"/]
            <input type="hidden" name="index" value="${name}"/>
            <input type="hidden" name="${name}.id" value="${ma.id!}"/>
            <input type="hidden" name="${name}.schema.id" value="${schema.id}"/>
            <input type="hidden" name="${name}.department.id" value="${ma.department.id}"/>
            <td>${ma.department.name}</td>
            [#list ma.items?sort_by(["item", "id"]) as item]
            <td>
              [#assign maiName="mai${ma.department.id}${item.item.id}"/]
              <input type="hidden" name="mai${ma.department.id}" value="${maiName}"/>
              <input type="hidden" name="${maiName}.id" value="${item.id!}"/>
              <input type="hidden" name="${maiName}.item.id" value="${item.item.id}"/>
              <input type="hidden" name="${maiName}.score" value="${item.score!}" class="score"/>
              <span class="scoreSpan">${item.score!0}</span>
              <div class="scoreDiv" max="${item.item.score}" score="${item.score!(item.item.score/2)}"></div>
            </td>
            [/#list]
          </tr>
        [/#list]
      </tbody>
    </table>
    <div style="text-align:center; padding:30px;" class="footdiv">
      <input type="hidden" name="save" value="0" id="saveIpt"/>
      [@b.submit value="action.save" onsubmit="saveAssess"/]
      [@b.submit value="action.submit" onsubmit="submitAssess"/]
    </div>
  [/@]
  <script>
    jQuery.struts2_jquery.require("/js/base/jquery-ui.js",null,bg.getContextPath() + "/static");
    function saveAssess(){
      if(isallselected()){
        $("#saveIpt").val("1");
        return true;
      }
      return false;
    }
    
    function submitAssess(){
      if(isallselected() && confirm("提交后不能修改，是否确定提交？")){
        return true;
      }
      return false;
    }
  
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
[@b.foot/]