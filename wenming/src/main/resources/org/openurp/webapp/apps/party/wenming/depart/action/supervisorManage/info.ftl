[#ftl]
[@b.head/]
  [@b.form action="!info"]
    <table id="supercisorManageTable" class="gridtable assessTable" >
      <thead>
        <tr>
          <th width="3%">序号</th>
          <th width="10%">督察组成员</th>
          <th width="5%">已评单位数量</th>
          <th width="67%">已评单位</th>
          <th width="5%">状态</th>
          <th width="10%">操作时间</th>
        </tr>
      </thead>
      <tbody>
        [#list datas as data]
          <tr>
            <td align="center">${data_index+1}</td>
            <td align="center">${data[0].fullname}</td>
            <td align="center">${data[1]?size}</td>
            <td>
              [#list data[1]?sort_by("code") as department]
                [#if department_index gt 0]、[/#if]${department.name}
              [/#list]
            </td>
            <td align="center">[#if data[2]?string=='Submit']已提交[#else]草稿[/#if]</td>
            <td align="center">${data[3]?string("YYYY-MM-dd HH:mm:ss")}</td>
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