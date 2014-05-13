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

<h3>一、 指标内容</h3>
[#macro displayGroup group]
  <tr>
    <td align="center"><B>${group.indexno}</B></td>
    <td colspan="3"><B>${group.name}</B>
  </tr>
  [#list group.items as item]
  <tr>
   <td align="center">${item_index+1}</td>
   <td>${item.content}</td>
   <td align="center">${item.score}</td>
   <td align="center">[#if item.assessType.id!="FUNC_DEPART"]${item.assessType.description}[#else][#list item.departs?sort_by(["department","code"]) as idepart]${idepart.department.name}&nbsp;[/#list][/#if]</td>
  </tr>
  [/#list]
  [#list group.children as child] 
    [@displayGroup child/]
  [/#list]
[/#macro]

<table class="infoTable">
  <thead>
    <th width="7%">序号</th>
    <th width="70%">评分内容及标准</th>
    <th width="8%">分值</th>
    <th width="15%">备注</th>
  </thead>
  [#list assessSchema.groups?sort_by("indexno") as group]
    [@displayGroup group/]
  [/#list]
</table>

<h3>二、 加分项${assessSchema.bonusItems?size}</h3>
<table class="infoTable">
  <thead>
    <th width="10%">序号</th>
    <th width="70%">内容及标准</th>
    <th width="20%">加分方法</th>
    
  </thead>
  [#list assessSchema.bonusItems as item]
    <tr>
     <td align="center">${item_index+1}</td>
     <td>${item.name}</td>
     <td align="center">${item.method}</td>
  </tr>
  [/#list]
</table>
<br/>
[@b.foot/]
