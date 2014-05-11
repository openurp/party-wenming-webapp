[#ftl]
[@b.head/]
[@b.toolbar title="评测指标编辑"]bar.addBack();[/@]
[@b.tabs]
  [@b.tab label="评测指标信息"]
    [@b.form action="!save" title="基本信息" theme="list"]
      [@b.textarea label="指标内容"  name="assessItem.content" required="true"
        maxlength="300" value=assessItem.content! rows="3" cols="80"/]
      [@b.textfield name="orderNumber" label="排序" value="${assessItem.orderNumber!}"
        required="true" maxlength="10"/]
      [@b.textfield name="assessItem.score" label="指标分值" value="${assessItem.orderNumber!}" 
        required="true" maxlength="10" check="match('integer')"/]
      [@b.formfoot]
        <input type="hidden" name="assessItem.id" value="${assessItem.id!}" />
        [@b.reset/]&nbsp;&nbsp;[@b.submit value="action.submit"/]
      [/@]
    [/@]
  [/@]
[/@]
[@b.foot/]