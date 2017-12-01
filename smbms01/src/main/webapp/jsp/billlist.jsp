<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/jsp/common/head.jsp"%>

<div class="right">
       <div class="location">
           <strong>你现在所在的位置是:</strong>
           <span>订单管理页面</span>
       </div>
       <div class="search">
       <form method="get" action="searchbill.action">
			<input name="method" value="query" class="input-text" type="hidden">
			<span>商品名称：</span>
			<input name="queryProductName" type="text"id="queryProductName">
			 
			<span>供应商：</span>
			<select name="queryProviderId" id="queryProviderId">
				<c:if test="${listprovider != null }">
				   <option value="请选择">--请选择--</option>
				   <c:forEach var="pro" items="${listprovider}">
				   		<option value="${pro.proName}">${pro.proName}</option>
				   </c:forEach>
				</c:if> 
       		</select>
			 
			<span>是否付款：</span>
			<select name="queryIsPayment" id="queryIsPayment">
				<option value="0">--请选择--</option>
				<c:forEach items="${listpay }" var="ii">
					<option value="${ii}">
						<c:if test="${ii == 1}">
							未付款
						</c:if>
						<c:if test="${ii == 2}">
							已付款
						</c:if>
					</option>
				</c:forEach>
				<%-- <option value="1" ${queryIsPayment == 1 ? "selected=\"selected\"":"" }>未付款</option>
				<option value="2" ${queryIsPayment == 2 ? "selected=\"selected\"":"" }>已付款</option> --%>
       		</select>
			<!--  <input	value="查 询" type="submit" id="searchbutton"> -->
			 <input	value="查 询" type="button" id="searchbutton">
			 <a href="${pageContext.request.contextPath }/gotoaddbill.action">添加订单</a>
		</form>
       </div>
       <!--账单表格 样式和供应商公用-->
      <table class="providerTable" cellpadding="0" cellspacing="0">
          <tr class="firstTr">
              <th width="10%">订单编码</th>
              <th width="20%">商品名称</th>
              <th width="10%">供应商</th>
              <th width="10%">订单金额</th>
              <th width="10%">是否付款</th>
              <th width="10%">创建时间</th>
              <th width="30%">操作</th>
          </tr>
          <c:forEach var="sb" items="${listbill}" varStatus="status">
				<tr>
					<td>
					<span>${sb.billCode }</span>
					</td>
					<td>
					<span>${sb.productName }</span>
					</td>
					<td>
					<span>${sb.proName}</span>
					</td>
					<td>
					<span>${sb.totalPrice}</span>
					</td>
					<td>
					<span>
						<c:if test="${sb.isPayment == 1}">未付款</c:if>
						<c:if test="${sb.isPayment == 2}">已付款</c:if>
					</span>
					</td>
					<td>
					<span>
					<fmt:formatDate value="${sb.creationDate}" pattern="yyyy-MM-dd"/>
					</span>
					</td>
					<td>
					<span><a class="viewBill" href="javascript:;" billid=${sb.id } billcc=${sb.billCode }><img src="${pageContext.request.contextPath }/images/read.png" alt="查看" title="查看"/></a></span>
					<span><a class="modifyBill" href="jsp/billmodify.jsp?sid=${sb.id }"><img src="${pageContext.request.contextPath }/images/xiugai.png" alt="修改" title="修改"/></a></span>
					<span><a class="deleteBill" href="delBill?sid=${sb.id }"><img src="${pageContext.request.contextPath }/images/schu.png" alt="删除" title="删除"/></a></span>
					</td>
				</tr>
			</c:forEach>
      </table>
  </div>
</section>

<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeBi">
    <div class="removerChid">
        <h2>提示</h2>
        <div class="removeMain">
            <p>你确定要删除该订单吗？</p>
            <a href="#" id="yes">确定</a>
            <a href="#" id="no">取消</a>
        </div>
    </div>
</div>

<%@include file="/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/billlist.js"></script>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="js/json2.js"></script>
 <script>
	$(function(){
		$("#searchbutton").click(function(){
			var queryProductName=$("#queryProductName").val();
			var queryProviderId=$("#queryProviderId").val();
			var queryIsPayment=$("#queryIsPayment").val();
			alert("queryProductName:"+queryProductName+"queryProviderId:"+queryProviderId+"queryIsPayment:"+queryIsPayment);
			var bill_json= {
							"productName":queryProductName,
							"proName":queryProviderId,
							"isPayment":queryIsPayment
							}
			//alert(bill_json);
				$.ajax({
					url:"search.action",
					type:"POST",
					contentType:"application/json",
					data:JSON.stringify(bill_json),
					success:function(message){
						alert(message);
						var mess=eval("("+message+")");
						alert(mess);
						alert("1111");						
					},
					error:function(){
						alert("异步失败！");
					}					
			});		
		});
	})
</script>