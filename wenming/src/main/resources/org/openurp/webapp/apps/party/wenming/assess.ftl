[#ftl]

[#macro assessTable assessList]
[@b.toolbar title='评测内容'][/@]
  <style>
    .score{width:60px;}
    .footdiv input{margin-right:200px;}
    .error{background-color:#F9CCCC}
    .error .scoreSpan{color:#fff;}
    .scoreSpan{float:left;}
    .scoreDiv{margin-left:24px;}
    .assessTable th{background-color:#c7dbff; padding: 10px;}
    .assessTable td{padding:10px 5px}
    .assessTable tr:hover{background-color:#eee;}
    .ui-slider-handle { border-color: #729fcf; }
    .ui-slider-range { background: #729fcf; }
  </style>
  <table id="assessTable" class="gridtable assessTable">
    <thead>
      <tr>
        <th width="220">被评分单位/指标</th>
        [#list assessList[0].items?sort_by(["item", "id"]) as v]
        <th>${v.item.content}</th>
        [/#list]
      </tr>
    </thead>
    <tbody>
      [#list assessList as assess]
        <tr>
          [#assign name="assess${assess_index}"/]
          <input type="hidden" name="index" value="${name}"/>
          <input type="hidden" name="${name}.id" value="${assess.id!}"/>
          <input type="hidden" name="${name}.schema.id" value="${schema.id}"/>
          <input type="hidden" name="${name}.department.id" value="${assess.department.id}"/>
          <td>${assess.department.name}</td>
          [#list assess.items?sort_by(["item", "id"]) as item]
          <td>
            [#assign itemName="item${assess.department.id}${item.item.id}"/]
            <input type="hidden" name="item${assess.department.id}" value="${itemName}"/>
            <input type="hidden" name="${itemName}.id" value="${item.id!}"/>
            <input type="hidden" name="${itemName}.item.id" value="${item.item.id}"/>
            <input type="hidden" name="${itemName}.score" value="${item.score!}" class="score"/>
            <span class="scoreSpan">${item.score!0}</span>
            <div class="scoreDiv" max="${item.item.score}" score="${item.score!(item.item.score/2)}"></div>
          </td>
          [/#list]
        </tr>
      [/#list]
    </tbody>
  </table>
  <script>
    jQuery.struts2_jquery.require("/js/base/jquery-ui.js",null,bg.getContextPath() + "/static");
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
[/#macro]