[#ftl]
[#include "/org/openurp/webapp/apps/party/wenming/comm.ftl"/]
[@b.head/]
[@b.grid  items=assessList var="assess" sortable="false"]
  [@b.row]
    [@b.col width="15%" title="测评批次"]${assess[1]}[/@]
    [@b.col width="15%" title="测评方案"]${assess[3]}[/@]
    [@b.col width="15%" title="测评人"][@b.a href="!info?session.id=${assess[0]}&schema.id=${assess[2]}&assessBy.id=${assess[4]}"]${assess[5]}(${assess[6]})[/@][/@]
    [@b.col width="15%" title="测评时间"]${assess[7]?date}[/@]
    [@b.col width="15%" title="状态"]${assess[8].description}[/@]
    [#if forAudit??]
    [@b.col width="15%" title="操作"][@b.a href="!edit?session.id=${assess[0]}&schema.id=${assess[2]}&assessBy.id=${assess[4]}"]审核[/@][/@]
    [/#if]
  [/@]
[/@]
<script>
    jQuery.struts2_jquery.requireCss("/css/party/main.css",bg.getContextPath() + "/static");
</script>
[@b.foot/]