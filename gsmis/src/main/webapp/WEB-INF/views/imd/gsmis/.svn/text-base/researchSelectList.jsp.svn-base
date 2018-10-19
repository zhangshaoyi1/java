<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta name="decorator" content="default"/>
	<title>研究方向选择</title>
	<script type="text/javascript">
		$(document).ready(function() {
			$("input[name=id]").each(function(){
				//定义变量（其实就是主面板的数组）用于接收主面板的文本框的传值
				var researchSelect = null;
				//判断复面板（即弹出的面板）传值
				if (top.mainFrame.cmsMainFrame){
					//如果复面板有值存在，就把它的值重新赋给researchSelect，即researchSelect自我更新
					researchSelect = top.mainFrame.cmsMainFrame.researchSelect;
				}else{ 
					//否则就把主面板的值赋给researchSelect
					researchSelect = top.mainFrame.researchSelect;
				}
				//遍历researchSelect，如果文本框中存在复选框的值，就勾选它
				for (var i=0; i<researchSelect.length; i++){
					if (researchSelect[i][1]==$(this).attr("title")){
						this.checked = true;
					}
				}
				//点击确定按钮返回复面板的值
				$(this).click(function(){
					var id = $(this).val(), name = $(this).attr("title");
					if (top.mainFrame.cmsMainFrame){
						top.mainFrame.cmsMainFrame.researchSelectAddOrDel(id, name);
					}else{
						top.mainFrame.researchSelectAddOrDel(id, name);
					}
				});
			});
		});
		
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
	    	return false;
	    }
		function addNewResearch() {
			top.$.jBox.open("iframe:${ctx}/gsmis/research/form", "新增研究方向",$(top.document).width()-700,$(top.document).height()-165,{							
				buttons:{"确定":true}, 
				loaded:function(h){
					$(".jbox-content", top.document).css("overflow-y","hidden");
				}
			});
		}
	</script>
</head>
<body>
	<div style="margin:10px;">
	<form:form id="searchForm" modelAttribute="research" action="${ctx}/gsmis/research/selectList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>研究方向：</label>
				<form:input path="name" htmlEscape="false" maxlength="50" class="input-small"/>
			</li>
			<li class="btns">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
				<input id="btnSubmit" class="btn btn-primary" type="submit" onclick="addNewResearch()" value="添加" />
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr>
			<th style="text-align:center;">选择</th>
			<th>研究方向</th>		
 		</tr></thead>
		<tbody>
			<c:forEach items="${page.list}" var="research"><tr>
				<td style="text-align:center;">
					<input type="checkbox" name="id" value="${research.id}" title="${fns:abbr(research.name,40)}" />
				</td>
				<td id="researchlabel">${research.name}</td>
			</tr></c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
</body>
</html>