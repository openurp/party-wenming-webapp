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
    [@b.col width="5%" property="orderNumber" title="排序"][/@]
    [@b.col width="68%" property="content" title="指标内容"][/@]
    [@b.col width="8%" property="score" title="指标分值"][/@]
    [@b.col width="15%" property="mutual" title="备注"]
       [#if assessItem.assessType.id!='FUNC_DEPART']${assessItem.assessType.description}[#else][#list assessItem.departs?sort_by(["department","code"]) as idepart]${idepart.department.name}&nbsp;[/#list][/#if]
    [/@]
  [/@]
[/@]
[@b.foot/]