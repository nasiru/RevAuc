<div id="requirement${i}" class="requirement-div fieldcontain" <g:if test="${hidden}">style="display:none;"</g:if>>
 <label></label>
 
    <g:hiddenField name='requirementsList[${i}].deleted' value='false'/>
    <g:hiddenField name='requirementsList[${i}].new' value='false'/>
 
    <g:textField name='requirementsList[${i}].description' value='${requirement?.description}' placeholder="Description" />
 
    <span class="del-requirement">
        <img src="${resource(dir:'images/skin', file:'icon_delete.png')}"
            style="vertical-align:middle;"/>
    </span>
</div>