package ee.ttu.catering.config.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@Component
public class CorsFilter implements Filter {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
    	putCorsHeaders(res);
        chain.doFilter(req, res);
    }

    private void putCorsHeaders(ServletResponse res) {
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "PUT, POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with, Content-Type,Accept");
    }

    public void init(FilterConfig filterConfig) {
        LOG.info("initialized");
    }

    public void destroy() {}
}