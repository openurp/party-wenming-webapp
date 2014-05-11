[#ftl]
[@b.head/]
[#include "/org/openurp/webapp/apps/party/wenming/comm.ftl"/]

[@b.grid  items=assessItems var="assessItem" sortable="false"]
  [@b.gridbar]
    bar.addItem("${b.text("action.new")}",action.add());
    bar.addItem("${b.text("action.modify")}",action.edit());
    bar.addItem("${b.text("action.delete")}",action.remove("确认删除?"));
  [/@]
  [@b.row]
    [@b.boxcol /]
    [@b.col width="10%" property="orderNumber" title="排序"][/@]
    [@b.col width="50%" property="content" title="指标内容"][/@]
    [@b.col width="10%" property="score" title="指标分值"][/@]
    [@b.col width="15%" property="mutual" title="是否为互评指标"][@c.enabled assessItem.mutual/][/@]
    [@b.col width="15%" property="forSupervisor" title="是否督察组测评指标"][@c.enabled assessItem.forSupervisor/][/@]
  [/@]
[/@]
[@b.foot/]