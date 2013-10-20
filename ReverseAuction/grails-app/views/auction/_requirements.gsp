<script type="text/javascript">
    var childCount = ${auctionInstance?.requirements.size()} + 0;
 
    function addRequirement(){
      var clone = $("#requirement_clone").clone()
      var htmlId = 'requirementsList['+childCount+'].';
      var requirementInput = clone.find("input[id$=description]");
 
      clone.find("input[id$=id]")
             .attr('id',htmlId + 'id')
             .attr('name',htmlId + 'id');
      clone.find("input[id$=deleted]")
              .attr('id',htmlId + 'deleted')
              .attr('name',htmlId + 'deleted');
      clone.find("input[id$=new]")
              .attr('id',htmlId + 'new')
              .attr('name',htmlId + 'new')
              .attr('value', 'true');
      requirementInput.attr('id',htmlId + 'description')
              .attr('name',htmlId + 'description');
      clone.find("select[id$=type]")
              .attr('id',htmlId + 'type')
              .attr('name',htmlId + 'type');
 
      clone.attr('id', 'requirement'+childCount);
      $("#childList").append(clone);
      clone.show();
      //requirementInput.focus();
      childCount++;
    }
 
    //bind click event on delete buttons using jquery live
    $('.del-requirement').live('click', function() {
        //find the parent div
        var prnt = $(this).parents(".requirement-div");
        //find the deleted hidden input
        var delInput = prnt.find("input[id$=deleted]");
        //check if this is still not persisted
        var newValue = prnt.find("input[id$=new]").attr('value');
        //if it is new then i can safely remove from dom
        if(newValue == 'true'){
            prnt.remove();
        }else{
            //set the deletedFlag to true
            delInput.attr('value','true');
            //hide the div
            prnt.hide();
        }        
    });
 
</script>

<input type="button" value="Add Requirement" onclick="addRequirement();" />
<div id="childList">
    <g:each var="requirement" in="${auctionInstance.requirements}" status="i">
  	
  	<g:if test="${requirement?.description != null && !requirement?.deleted}">
        <g:render template='requirement' model="['requirement':requirement,'i':i,'hidden':false]"/>
    </g:if>
      	
    </g:each>
</div>
<br/>
