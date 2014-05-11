[#ftl]
[@b.head/]
[@b.toolbar title='测评指标分类信息']
bar.addBack("${b.text("action.back")}");
[/@]
[#include "/org/openurp/webapp/apps/party/wenming/comm.ftl"/]
<table class="infoTable">
  <tr>
   <td class="title" width="30%">名称:</td>
   <td class="content" width="70%"> ${assessItemGroup.name}</td>
  </tr>
  <tr>
   <td class="title" >排序:</td>
   <td class="content">${assessItemGroup.indexno}</td>
  </tr>
  <tr>
   <td class="title" >相关方案:</td>
   <td class="content">${(assessItemGroup.schema.name)!}</td>
  </tr>
  <tr>
   <td class="title" >上级分类:</td>
   <td class="content">${(assessItemGroup.parent.name)!}</td>
  </tr>
  [#if assessItemGroup.children?size gt 0]
  <tr>
   <td class="title" >子分类:</td>
   <td class="content">[#list assessItemGroup.children as v][#if v_index gt 0]，[/#if]${v.name}[/#list]</td>
  </tr>
  [/#if]
  [#if assessItemGroup.items?size gt 0]
  <tr>
   <td class="title" >评测指标:</td>
   <td class="content">
     <ol>
       [#list assessItemGroup.items as v]
       <li>${v.name}</li>
       [/#list]
     </ol>
   </td>
  </tr>
  [/#if]
</table>
[@b.foot/]
