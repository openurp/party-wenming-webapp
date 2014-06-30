[#ftl]
<h5>各部门详细进度</h5>
<table class="gridtable" style="width:900px;" >
  <thead class="gridhead">
    <th width="10%">序号</th>
    <th width="30%">姓名</th>
    <th width="20%">优秀项目</th>
    <th width="20%">好人好事</th>
    <th width="20%">文明示范岗</th>
  </thead>
  <tbody>
  [#list datas as d]
    <tr>
      <td align="center">${d_index + 1}</td>
      <td>${d[0]}</td>
      <td align="center">[#if d[1]??]<span class="toolbar-icon action-activate"></span>[/#if]</td>
      <td align="center">[#if d[2]??]<span class="toolbar-icon action-activate"></span>[/#if]</td>
      <td align="center">[#if d[3]??]<span class="toolbar-icon action-activate"></span>[/#if]</td>
    </tr>
  [/#list]
  </tbody>
</table>