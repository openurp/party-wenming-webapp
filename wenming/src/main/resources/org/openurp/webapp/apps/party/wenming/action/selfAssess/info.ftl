[#ftl]
[@b.head/]
<div style="padding:5px 10px;">
  <div style="margin-left:10px;">
    <b>申报时间:</b>${(assessSession.beginOn?string("yyyy-MM-dd HH:mm"))!}~${(assessSession.endOn?string("yyyy-MM-dd HH:mm"))!}
    [#if modifyable??]
      [@b.a href="!edit"]修改[/@]
    [/#if]
  </div>
  [#if !selfAssess??]
  <div style="margin-left:10px;">
  没有自评内容
    [#if addable??]
      ，[@b.a href="!edit"]开始自评[/@]
    [/#if]
  </div> 
  [#else]
    [@b.form action="!save" theme="list"]
      <style>
        .footdiv input{margin-right:200px;}
      </style>
      <table class="infoTable">
        <tr>
         <td class="title">单位名称:</td>
         <td class="content">${selfAssess.department.name}</td>
         <td class="title">所属批次:</td>
         <td class="content"> ${selfAssess.session.name}</td>
         <td class="title">自评时间:</td>
         <td class="content">${selfAssess.assessAt?string("YYYY-MM-dd HH:mm")}</td>
        </tr>
        <tr>
         <td class="title">自评人员:</td>
         <td class="content">${selfAssess.assessBy.name}（${selfAssess.assessBy.fullname}）</td>
         <td class="title">自评总分:</td>
         <td class="content"> ${selfAssess.totalScore}</td>
         <td class="title">审核状态:</td>
         <td class="content">${selfAssess.state.description}</td>
        </tr>
       </table>
      <table id="selfAssessTable" class="gridtable">
        <thead class="gridhead">
          <tr style="height:30px; font-size:16px;">
            <th width="80">指标编号</th>
            <th>指标内容</th>
            <th width="80">指标分值</th>
            <th width="100">自评分值</th>
          </tr>
        </thead>
        <tbody>
          [#list selfAssess.items as v]
            [#if !group?? || v.item.group.id != group.id]
              [#assign group = v.item.group/]
              <tr style="font-size:14px;">
                <td>${group.indexno}</td>
                <td>${group.name}</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
            [/#if]
            <tr>
              [#assign name="sai${v_index}"/]
              <input type="hidden" name="index" value="${name}"/>
              <input type="hidden" name="${name}.item.id" value="${v.item.id}"/>
              <td>${group.indexno}-${v.item.orderNumber}</td>
              <td>${v.item.content}</td>
              <td>${v.item.score}</td>
              <td align="center">${v.score!}</td>
            </tr>
          [/#list]
        </tbody>
      </table>
    [/@]
  [/#if]
</div>
[@b.foot/]