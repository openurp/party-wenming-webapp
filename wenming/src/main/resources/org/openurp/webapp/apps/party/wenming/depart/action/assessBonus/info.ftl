[#ftl]
<table class="infoTable">
  <thead>
    <th>加分类型</th>
    <th>加分项目</th>
    <th>加分值</th>
    <th>材料</th>
  </thead>
[#list assessBonuses as bonus]
  <tr align="center">
  <td>${bonus.item.bonusType.name}</td>
  <td>${bonus.item.name}</td>
  <td>${bonus.score}</td>
  <td>[#list bonus.attachments as attach]
     [@b.a target="_blank" href="../attachment?path=${attach.filePath}&name=${attach.name?url('utf-8')}"]${attach.name}[/@][#if attach_has_next]<br/>[/#if]
     [/#list]
  </td>
  </tr>
[/#list]
</table>
