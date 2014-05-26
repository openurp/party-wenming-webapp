[#ftl]
[@b.head/]
[@b.toolbar title="测评方案编辑"]bar.addBack();[/@]
    [@b.form action="!save" theme="list" name="schema_form"]
      [@b.textfield name="assessSchema.name" label="方案名称" value="${assessSchema.name!}" required="true" maxlength="50"/]
      [@b.radios name="assessSchema.forTeaching" value=assessSchema.forTeaching items={'1':'是','0':'否'} label="针对教学部门" required="true"/]
      [@b.select2 label="包含部门" name1st="departmentId" name2nd="departId" 
        items1st=departments items2nd= assessSchema.departs?sort_by("code") 
        option="id,name"/]
      [@b.formfoot]
        <input type="hidden" name="assessSchema.id" value="${assessSchema.id!}" />
        [@b.reset/]&nbsp;&nbsp;[@b.submit value="action.submit"/]
      [/@]
    [/@]
<script>
    departs = {}
    [#list allDepartments as d]
    departs['${d.id}']={name:"${d.name}",teaching:${d.teaching?string("true","false")}}
    [/#list]
    function removeDepart(isTeaching){
       jQuery("#schema_form select option").each(function(idx,obj){
          if(isTeaching == departs[obj.value].teaching) obj.remove();
       })
       jQuery("#schema_form select[name='departmentId'] option").remove();
       for( k in departs){
          if(departs[k].teaching!=isTeaching){
             var finded=false;
             jQuery("#schema_form select[name='departId'] option").each(function (idx,e){
                if(!finded){
                  if(e.value==k) finded=true;
                }
             });
             if(!finded)jQuery("#schema_form select[name='departmentId']").append("<option value='" + k + "'>" +departs[k].name + "</option>");
          } 
       }
    }
    [#if assessSchema.forTeaching]removeDepart(false);[#else]removeDepart(true)[/#if]
     jQuery("#schema_form :radio").bind("click",function(e){
        if(e.target.value=="1"){
         removeDepart(false);
        }else{
         removeDepart(true);
        }
     });
</script>
[@b.foot/]