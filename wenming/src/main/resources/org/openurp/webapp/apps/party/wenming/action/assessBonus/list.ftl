[#ftl]
[@b.head/]
[#assign score=0/]
[#list assessBonuss as b]
[#assign score=b.score/]
[/#list]

[@b.toolbar title='加分材料---共计${score}分']
  bar.addBack();
[/@]

[@b.grid  items=assessBonuss var="assessBonus"]
  [@b.gridbar]
    bar.addItem("${b.text("action.new")}",action.add());
    bar.addItem("${b.text("action.modify")}",action.edit());
    bar.addItem("${b.text("action.delete")}",action.remove("确认删除?"));
  [/@]
  [@b.row]
    [@b.boxcol width="5%"/]
    [@b.col property="item.bonusType.name" width="20%" title="加分类型"/]
    [@b.col property="item.name" width="40%" title="加分内容"/]
    [@b.col property="score" width="10%" title="分值"/]
    [@b.col width="25%" title="附件"]
    [#list assessBonus.attachments as attach]
     [@b.a target="_blank" href="attachment?path=${attach.filePath}&name=${attach.name?url('utf-8')}"]${attach.name}[/@][#if attach_has_next]<br/>[/#if]
     [/#list]
    [/@]
  [/@]
[/@]
[@b.foot/]