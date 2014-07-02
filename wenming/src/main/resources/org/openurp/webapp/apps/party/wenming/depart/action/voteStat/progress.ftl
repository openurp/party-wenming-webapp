[#ftl]
<table class="gridtable" style="width:900px;" >
  <thead class="gridhead">
    <th width="30%">序号</th>
    <th width="40%">姓名</th>
    <th width="30%">是否已投票</th>
  </thead>
  <tbody>
  [#list datas as d]
    <tr>
      <td align="center">${d_index + 1}</td>
      <td>${d[0]}</td>
      <td align="center">[#if d[1]??]<span class="toolbar-icon action-activate"></span>[/#if]</td>
    </tr>
  [/#list]
  </tbody>
</table>