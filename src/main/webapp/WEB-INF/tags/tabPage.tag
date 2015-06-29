<%@ tag pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ attribute name="pageInfo" required="true" type="me.wanx.common.core.persistence.BasePagination" description="分页对象类型"%>
<%@ attribute name="startUrl" required="true" type="java.lang.String" description="分页开始"%>
<%@ attribute name="endUrl" required="false" type="java.lang.String" description="分页结束"%>
<c:if test="${not empty pageInfo}">
	<ul>
		<li><a href="${startUrl}1">首页</a></li>
		<li><a href="${startUrl}${pageInfo.beforePage}">上一页</a></li>
		<li><a href="${startUrl}${pageInfo.nextPage}">下一页</a></li>
		<li><a href="${startUrl}${pageInfo.totalPage}">尾页</a></li>
		<li class="active"><a href="javascript:;">${pageInfo.currentPage} / ${pageInfo.totalPage}</a></li>
	</ul>
</c:if>