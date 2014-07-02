[#ftl]
[@b.head/]
  [@b.form action="!info"]
    <table id="voteManageTable" class="gridtable assessTable" style="width:600px;">
      <thead>
        <tr>
          <th width="20%">序号</th>
          <th width="60%">优秀项目</th>
          <th width="20%">所得票数</th>
        </tr>
      </thead>
      <tbody>
        [#list datas as d]
          <tr>
            <td align="center">${d_index + 1}</td>
            <td>[@b.a href="/party/wenming/project/manage!info?id=${d[0]}"]${d[1]}[/@]</td>
            <td align="center">${d[2]}</td>
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
