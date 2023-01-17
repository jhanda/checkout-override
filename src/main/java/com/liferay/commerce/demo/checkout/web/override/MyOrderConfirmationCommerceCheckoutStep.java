package com.liferay.commerce.demo.checkout.web.override;


import com.liferay.commerce.constants.CommerceCheckoutWebKeys;
import com.liferay.commerce.constants.CommerceWebKeys;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.order.CommerceOrderHttpHelper;
import com.liferay.commerce.service.CommerceOrderPaymentLocalService;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.commerce.util.BaseCommerceCheckoutStep;
import com.liferay.commerce.util.CommerceCheckoutStep;
import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletURL;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liferay.petra.string.StringPool;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jeff Handa
 */
@Component(
        property = {
                "commerce.checkout.step.name=" + MyOrderConfirmationCommerceCheckoutStep.NAME,
                "commerce.checkout.step.order:Integer=" + Integer.MAX_VALUE,
                "service.ranking:Integer=" + Integer.MAX_VALUE

        },
        service = CommerceCheckoutStep.class
)
public class MyOrderConfirmationCommerceCheckoutStep
        extends BaseCommerceCheckoutStep {

    public static final String NAME = "order-confirmation";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public boolean isOrder() {
        return true;
    }

    @Override
    public boolean isSennaDisabled() {
        return true;
    }

    @Override
    public void processAction(
            ActionRequest actionRequest, ActionResponse actionResponse)
            throws Exception {
    }

    @Override
    public void render(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse)
            throws Exception {

        System.out.println("Rendering!");

        CommerceContext commerceContext = (CommerceContext)httpServletRequest.getAttribute(CommerceWebKeys.COMMERCE_CONTEXT);
        CommerceOrder commerceOrder = (CommerceOrder)httpServletRequest.getAttribute(CommerceCheckoutWebKeys.COMMERCE_ORDER);

        PortletURL portletURL = _commerceOrderHttpHelper.getCommerceCartPortletURL(httpServletRequest, commerceOrder);
        String detailURL = StringPool.BLANK;
        if (portletURL != null) {
            detailURL = portletURL.toString();
        }

        httpServletRequest.setAttribute("commerceContext", commerceContext);
        httpServletRequest.setAttribute("commerceOrder", commerceOrder);
        httpServletRequest.setAttribute("detailURL", detailURL);

        _jspRenderer.renderJSP(
                _servletContext, httpServletRequest, httpServletResponse,
                "/order_confirmation.jsp");
    }

    @Override
    public boolean showControls(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        return false;
    }

    @Reference
    private CommerceOrderHttpHelper _commerceOrderHttpHelper;

    @Reference(
            target = "(osgi.web.symbolicname=com.liferay.commerce.demo.checkout.web.override)"
    )
    private ServletContext _servletContext;

    @Reference
    private JSPRenderer _jspRenderer;
}