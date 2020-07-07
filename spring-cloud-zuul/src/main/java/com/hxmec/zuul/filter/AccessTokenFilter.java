package com.hxmec.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 功能描述: 验证请求是否有accessToken参数
 * @author  Trazen
 * @date  2020/7/7 11:04
 */
@Component
@Slf4j
public class AccessTokenFilter extends ZuulFilter {

    /**
     * 过滤器类型 pre 表示在 请求之前进行拦截
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤器的执行顺序。当请求在一个阶段的时候存在多个多个过滤器时，需要根据该方法的返回值依次执行
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 判断过滤器是否生效
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        // 获取上下文
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String accessToken = request.getParameter("accessToken");
        if (StringUtils.isEmpty(accessToken)) {
            //setSendZuulResponse(false)令zuul过滤该请求，不进行路由
            currentContext.setSendZuulResponse(false);
            //设置返回的错误码
            currentContext.setResponseStatusCode(401);
            currentContext.setResponseBody("AccessToken is null");
            return null;
        }
        log.info("获取到AccessToken为{}",accessToken);
        // 否则正常执行业务逻辑.....
        return null;
    }

}
