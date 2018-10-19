<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>分配结果管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			 $("#btnsend").click(function(){
				top.$.jBox.confirm("确定要一键给所有专家发送邮件吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/jwcmis/email/send");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			}); 
			 $("#btnselectsend").click(function(){
					top.$.jBox.confirm("确定要给所选中的专家发送邮件吗？","系统提示",function(v,h,f){
						if(v=="ok"){
							var distributeResultIds = "";
							$("input[name='distributeResultId']:checked").each(  
									function(){
										if($(this).val() != "undefined"){
											distributeResultIds += $(this).val() + ",";
										}
									}
								)
							if(distributeResultIds==""){
								top.$.jBox.confirm("请选择需要发送邮件的专家！","系统提示");
								return;
							}
							$("#searchForm").attr("action","${ctx}/jwcmis/email/sendSelectEmail?distributeResultIds="+distributeResultIds);
							$("#searchForm").submit();
						}
					},{buttonsFocus:1});
					top.$('.jbox-body .jbox-icon').css('top','55px');
				}); 
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		function selectall(){
			if($('input[name="distributeResultId"]').get(0).checked){
				$('input[name="distributeResultId"]').attr("checked",false); 
			}else{
				$('input[name="distributeResultId"]').attr("checked",true); 
			}
		}
	</script>
</head>
<body>

	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/jwcmis/distributeResult/">分配结果列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="distributeResult" action="${ctx}/jwcmis/distributeResult/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>论文名：</label>
				<form:input path="topic" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>学号：</label>
				<form:input path="studentID" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li>
			<label>邮件状态</label>
				<%-- <form:radiobuttons path="1" items="${fns:getDictList('resultemailStatus')}" itemLabel="label" itemValue="value" htmlEscape="false"/> --%>
				<form:select path="emailstatus" class="input-xlarge required">
					<form:option value="">请选择邮件发送状态</form:option>
					<form:option value="0">未发送</form:option>
					<form:option value="1">已经发送</form:option>
				</form:select>
			</li>
			<li class="btns">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
			</li>
			<li class="btns">
				<input id="btnselectsend" class="btn btn-primary" type="button"  value="批量发送邮件"/>
			</li>
			<li class="btns">
				<input id="btnsend" class="btn btn-primary" type="button"  value="全部发送邮件"/>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align:center;"><a style="cursor:pointer;" onclick="selectall()">全选</a></th>
				<th>论文名</th>
				<th>硕士名</th>
				<th>学号</th>
				<th>关键词</th>
				<th>校内专家</th>
				<th>校外专家</th>
				<th>分配状态</th>
				<th>是否发送邮件</th>
				<th>更新时间</th>
				
				<shiro:hasPermission name="jwcmis:distributeResult:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="distributeResult">
			<tr>
				<td style="text-align:center;">
					<input type="checkbox" name="distributeResultId" value="${distributeResult.id}"/>
				</td>
				<td>
					${distributeResult.topic}
				</td>
				<td>
					${distributeResult.masterName}
				</td>
				<td>
					${distributeResult.studentID}
				</td>
				<td>
					${distributeResult.keywords}
				</td>
				<td>
					${distributeResult.inexpertName}
				</td>
				<td>
					${distributeResult.outexpertName}
				</td>
				<td>
					${fns:getDictLabel(distributeResult.status, 'thesisStatus', '')}
				</td>
				<td>
					${fns:getDictLabel(distributeResult.emailstatus, 'resultemailStatus', '')}
				</td>
				<td>
					<fmt:formatDate value="${distributeResult.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				
				<shiro:hasPermission name="jwcmis:distributeResult:edit">
					<td>
					<a href="${ctx}/jwcmis/distributeResult/delete?id=${distributeResult.id}" onclick="return confirmx('确认要删除该分配结果吗？', this.href)">删除</a>
					</td>
				</shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>