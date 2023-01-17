<%@ taglib prefix="aui" uri="http://liferay.com/tld/aui" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet_2_0" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://liferay.com/tld/commerce-ui" prefix="commerce-ui" %>
<%@ taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.commerce.model.CommerceOrder" %>
<%@ page import="com.liferay.commerce.model.CommerceOrderItem" %>
<%@ page import="com.liferay.commerce.product.model.CPDefinition" %>
<%@ page import="java.util.List" %>
<%@ page import="com.liferay.portal.kernel.util.*" %>
<%@ page import="com.liferay.commerce.context.CommerceContext" %>

<liferay-frontend:defineObjects />

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
    CommerceContext commerceContext = (CommerceContext) request.getAttribute("commerceContext");
    CommerceOrder commerceOrder = (CommerceOrder) request.getAttribute("commerceOrder");
    String detailURL = (String) request.getAttribute("detailURL");
%>

Order #<%= commerceOrder.getCommerceOrderId()%> completed for <%=commerceContext.getCommerceAccount().getName()%>

<aui:button-row>
    <aui:button href="<%= detailURL %>" primary="<%= true %>" type="submit" value="go-to-order-details" />
</aui:button-row>