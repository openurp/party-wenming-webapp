[#ftl]
[@b.head/]
  [@b.form action="!info"]
    <table class="infoTable">
      <tr>
        <span style="font-size:15px;font-weight:bold;">说明</span>
        ：第一行黑色粗体表示测评总分，第二行黑色表示自评分数，最后一行
        <span style="color:red">红色</span>表示互评分数，
        <span style="color:green">绿色</span>表示职能部门测评分数，
        <span style="color:blue">蓝色</span>表示督察组测评分数
      </tr>
    </table>
    <table id="summaryTable" class="gridtable assessTable" style="width:${departments?size*80+200}px">
      <thead>
        <tr>
          <th width="200">指标/单位</th>
          [#list departments?sort_by("code") as department]
            <th>${department.name}</th>
          [/#list]
        </tr>
      </thead>
      <tbody>
        <tr>
          <td align="center">总分</td>
          [#list departments?sort_by("code") as department]
            <td align="center" style="font-size:16px;font-weight:bold;">${totalScoreMap[department.id+""]!}</td>
          [/#list]
        </tr>
        [#list assessItems as item]
          <tr align="center">
            <td>${item.content!}</td>
            [#list departments?sort_by("code") as department]
              <td align="center">
                <span style="font-size:16px;font-weight:bold;">${(itemTotalScoreMap[department.id+""][item.id+""])!0}</span>
                <br/>${(selfAssessScore[department.id+""][item.id+""])!0}[#t/]
                [#if (mutualAssessScore[department.id+""][item.id+""]) ??]
                  [#t/]<br/><span style="color:red">${(mutualAssessScore[department.id+""][item.id+""])!}</span>
                [/#if][#t/]
                [#if (funcDepartAssessScore[department.id+""][item.id+""]) ??][#t/]
                  [#t/]<br/><span style="color:green">${(funcDepartAssessScore[department.id+""][item.id+""])!}</span>
                [/#if][#t/]
                [#if (supervisorAssessScore[department.id+""][item.id+""]) ??][#t/]
                  [#t/]<br/><span style="color:blue">${(supervisorAssessScore[department.id+""][item.id+""])!}</span>
                [/#if]
              </td>
            [/#list]
          </tr>
        [/#list]
      </tbody>
    </table>
  [/@]
[@b.foot/]

<script>
  jQuery.struts2_jquery.require("/js/base/jquery-ui.js",null,bg.getContextPath() + "/static");
  jQuery.struts2_jquery.requireCss("/css/party/main.css",bg.getContextPath() + "/static");
</script>