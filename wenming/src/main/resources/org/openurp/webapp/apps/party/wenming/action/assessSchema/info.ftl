[#ftl]
[@b.head/]
[@b.toolbar title='测评方案信息']
bar.addBack("${b.text("action.back")}");
[/@]
[#include "/org/openurp/webapp/apps/party/wenming/comm.ftl"/]
<table class="infoTable">
  <tr>
   <td class="title" width="10%">方案名称:</td>
   <td class="content" width="40%"> ${assessSchema.name}</td>   
  </tr>
  <tr>
  <td class="title" width="10%">包含部门:</td>
   <td class="content" width="40%">[#list assessSchema.departs?sort_by("code") as depart]${depart.name}&nbsp;[/#list]</td>
  </tr>
</table>

<p>指标内容</p>
[#macro displayGroup group]
  <tr><td colspan="3"><B>${group.indexno} ${group.name}</B></tr>
  [#list group.items as item]
  <tr>
   <td class="title" width="10%">${item_index+1}</td>
   <td class="content" width="70%">${item.content}</td>
   <td class="content" width="20%">[#list item.departs?sort_by(["department","code"]) as idepart]${idepart.department.name}(${idepart.score})&nbsp;[/#list]</td>
  </tr>>
  [/#list]
  [#list group.children as child] 
    [@displayGroup child/]
  [/#list]
[/#macro]

<table class="infoTable">
  [#list assessSchema.groups?sort_by("indexno") as group]
    [@displayGroup group/]
  [/#list]
</table>

[@b.foot/]
