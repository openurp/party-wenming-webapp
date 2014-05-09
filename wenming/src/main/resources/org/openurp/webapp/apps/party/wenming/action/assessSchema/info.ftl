[#ftl]
[@b.head/]
[@b.toolbar title='评价方案信息']
bar.addBack("${b.text("action.back")}");
[/@]
[#include "/org/openurp/webapp/apps/party/wenming/comm.ftl"/]
<table class="infoTable">
  <tr>
   <td class="title" width="10%">方案名称:</td>
   <td class="content" width="40%"> ${assessSchema.name}</td>
  </tr>
  <tr>
    [@b.select label="方案部門" name="assessSchema.departs" value=(assessSchema.name)! style="width:200px;"
      items=name option="id,name"/]
  </tr>
  <tr>
</table>
[@b.foot/]
