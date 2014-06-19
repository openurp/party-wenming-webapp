[#ftl]
[@b.head/]
  [@b.form action="!info"]
    <table id="supercisorManageTable" class="gridtable assessTable" >
      <thead>
        <tr>
          <th width="50">督察组成员</th>
          <th width="50">已评单位数量</th>
          <th>已评单位</th>
        </tr>
      </thead>
      <tbody>
        [#list datas as data]
          <tr>
            <td align="center">${data[0].fullname}</td>
            <td align="center">${data[1]?size}</td>
            <td>
              [#list data[1]?sort_by("code") as department]
                [#if department_index gt 0]、[/#if]${department.name}
              [/#list]
            </td>
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