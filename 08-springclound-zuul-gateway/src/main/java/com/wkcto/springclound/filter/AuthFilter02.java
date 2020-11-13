package com.wkcto.springclound.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class AuthFilter02 extends ZuulFilter {

    //过滤器类型 决定式在请求之前还是请求之后指定当前过滤器
    @Override
    public String filterType() {
        //返回pre表示要在请求之前执行过滤器
        return "pre";
    }

    //过滤器的排序，如果有多个过滤器那么这些过滤器将按照返回值大小直接排序执行
    @Override
    public int filterOrder() {
        return 2;
    }
    //是否启动当前过滤器如果返回true表示将要执行这个过滤器，如果返回false表示不执行这个过滤器
    @Override
    public boolean shouldFilter() {
        return true;
    }
    //如果进入当前过滤后，这个方法就是过滤器中具体的业务逻辑
    //注意：这个返回值目前版本没有什么特殊作用 因此返回 null即可
    @Override
    public Object run() throws ZuulException {
        System.out.println("0002222222222");


        //获取当前请求的上下文对象
        RequestContext requestContext=RequestContext.getCurrentContext();
        //获取当前请求对象
        HttpServletRequest request=requestContext.getRequest();
        //获取请求参数中的token数据（用户的身份令牌）
        String token=request.getParameter("token");
        if(token==null||!"123456".equals(token)){//进入if表示当前请求中没有携带token我们任务当前请求是非法请求
            //通知ZuulAPI网关当前请求非法
            requestContext.setSendZuulResponse(false);
            //设置响应编码为401 表示权限不足
            requestContext.setResponseStatusCode(401);
            //设置响应的头文件信息以html或文本响应编码格式为utf-8
            requestContext.addZuulResponseHeader("content-type","text/html;charset=utf-8");

            //设置响应的内容
            requestContext.setResponseBody("非法访问");
        }else{
            System.out.println("用户携带了身份令牌需要验证这个身份是否真的合法");

        }
        return null;
    }
}


