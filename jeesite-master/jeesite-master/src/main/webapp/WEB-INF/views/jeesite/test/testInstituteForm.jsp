<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>学院管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
		function addRow(list, idx, tpl, row){
			$(list).append(Mustache.render(tpl, {
				idx: idx, delBtn: true, row: row
			}));
			$(list+idx).find("select").each(function(){
				$(this).val($(this).attr("data-value"));
			});
			$(list+idx).find("input[type='checkbox'], input[type='radio']").each(function(){
				var ss = $(this).attr("data-value").split(',');
				for (var i=0; i<ss.length; i++){
					if($(this).val() == ss[i]){
						$(this).attr("checked","checked");
					}
				}
			});
		}
		function delRow(obj, prefix){
			var id = $(prefix+"_id");
			var delFlag = $(prefix+"_delFlag");
			if (id.val() == ""){
				$(obj).parent().parent().remove();
			}else if(delFlag.val() == "0"){
				delFlag.val("1");
				$(obj).html("&divide;").attr("title", "撤销删除");
				$(obj).parent().parent().addClass("error");
			}else if(delFlag.val() == "1"){
				delFlag.val("0");
				$(obj).html("&times;").attr("title", "删除");
				$(obj).parent().parent().removeClass("error");
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/test/testInstitute/">学院管理列表</a></li>
		<li class="active"><a href="${ctx}/test/testInstitute/form?id=${testInstitute.id}">学院管理<shiro:hasPermission name="test:testInstitute:edit">${not empty testInstitute.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="test:testInstitute:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="testInstitute" action="${ctx}/test/testInstitute/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">学院名：</label>
			<div class="controls"  id="schoolname">
				<form:input path="name" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">学院编号：</label>
			<div class="controls">
				<form:input path="xydm" htmlEscape="false" maxlength="20" class="input-xlarge " id="xydm"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:input path="memo" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">标记：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
			
		<!-- 一对多从表添加 ---------开始-------------------->
		<div class="control-group">
				<label class="control-label">专业子表</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>专业名</th>
								<th>专业编号</th>
								<th>专业性质</th>
								<th>是否师范</th>
								<shiro:hasPermission name="test:testInstitute:edit">
								<th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="testMajorList">
						</tbody>
						<shiro:hasPermission name="test:testInstitute:edit"><tfoot>
							<tr><td colspan="4"><a href="javascript:" onclick="addRow('#testMajorList', testMajorRowIdx, testMajorTpl);testMajorRowIdx = testMajorRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="testMajorTpl">//<!--
						<tr id="testMajorList{{idx}}">
							<td class="hide">
								<input id="majorList{{idx}}_id" name="majorList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="majorList{{idx}}_delFlag" name="majorList[{{idx}}].delFlag" type="hidden" value="0"/>
								<input id="majorList{{idx}}_schoolXydm" name="majorList[{{idx}}].schoolXydm" type="hidden" value="$('#xydm').innerHTML" />
							</td>
							<td>
								<input id="majorList{{idx}}_name" name="majorList[{{idx}}].name" type="text" value="{{row.name}}" maxlength="64" class="input-small "/>
							</td>
							<td>
								<input id="majorList{{idx}}_zydm" name="majorList[{{idx}}].zydm" type="text" value="{{row.zydm}}" maxlength="64" class="input-small "/>
							</td>
							<td>
								<input id="majorList{{idx}}_zyxz" name="majorList[{{idx}}].zyxz" type="text" value="{{row.zyxz}}" maxlength="255" class="input-small "/>
							</td>
                            <td>
								<input id="majorList{{idx}}_sfxz" name="majorList[{{idx}}].sfxz" type="text" value="{{row.sfxz}}" maxlength="255" class="input-small "/>
							</td>
							<shiro:hasPermission name="test:testInstitute:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#majorList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var testMajorRowIdx = 0, testMajorTpl = $("#testMajorTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(testInstitute.majorList)};
							for (var i=0; i<data.length; i++){
								addRow('#testMajorList', testMajorRowIdx, testMajorTpl, data[i]);
								testMajorRowIdx = testMajorRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>		
		<div class="form-actions">
			<shiro:hasPermission name="test:testInstitute:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
		
		
		
	</form:form>
</body>
</html>