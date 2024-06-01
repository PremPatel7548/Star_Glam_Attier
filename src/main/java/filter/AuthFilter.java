/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */
package filter;

import Beans.UserBeanLocal;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import static javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters.withParams;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static jwtrest.Constants.AUTHORIZATION_HEADER;
import static jwtrest.Constants.BEARER;
import loginBean.LoginBean;
import record.KeepRecord;

/**
 *
 * @author Admin
 */
public class AuthFilter implements Filter {
    
    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    @EJB UserBeanLocal ub;
    @Inject LoginBean lb;
    @Inject SecurityContext sctx;
    CredentialValidationResult cres;
    AuthenticationStatus status;
    public AuthFilter() {
    }    
    
    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AuthFilter:DoBeforeProcessing");
        }
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if(req.getRequestURI().contains("Login.jsf")){
        if(KeepRecord.isLogout()){
        KeepRecord.reset();
        Cookie[] cookies = req.getCookies();
        for(Cookie c:cookies){
            if(c.getName().equals(jwtrest.Constants.AUTHORIZATION_HEADER)){
                c.setMaxAge(0);
                c.setPath("/");
                System.out.println("Cookie deleted...");
                resp.addCookie(c);
            }
        }
        //req.getRequestDispatcher("index.xhtml").forward(request, response);
         req.getRequestDispatcher("/Login.jsf").forward(request, response);
//        return;
        }
        else if(lb.getUsername()!=null && lb.getPassword()!=null){
            Credential cred = new UsernamePasswordCredential(lb.getUsername(),new Password(lb.getPassword()));
            status = sctx.authenticate(req, resp, withParams().credential(cred));
            if(status == AuthenticationStatus.SUCCESS && KeepRecord.getPrincipal()!=null){
                if(KeepRecord.getRoles().contains("Admin")){
                    if(lb.isRememberme()){
                    Cookie cookie = new Cookie(AUTHORIZATION_HEADER,BEARER + KeepRecord.getToken());
                    cookie.setPath("/");
                    System.out.println("Cookie added....");
                    cookie.setMaxAge(60*24*24*60);
                    resp.addCookie(cookie);
                    }
                    req.getRequestDispatcher("/Admin/Dashboard.jsf").forward(request, response);
                }
                else if(KeepRecord.getRoles().contains("User")){
                    if(lb.isRememberme()){
                    Cookie cookie = new Cookie(AUTHORIZATION_HEADER,BEARER + KeepRecord.getToken());
                    cookie.setPath("/");
                    cookie.setMaxAge(60*24*24*60);
                    resp.addCookie(cookie);
                    }
                    lb.setuId(ub.getUserIdByUsername(lb.getUsername()));
                    req.getRequestDispatcher("/User/Dashboard.jsf").forward(request, response);
                }
                else{
                    lb.setErrorstatus("Username or Password is Incorrect...");
                    req.getRequestDispatcher("Login.jsf").forward(request, response);
                }
            }
            else{
                lb.setErrorstatus("Username or Password is Incorrect...");
//                lb.getFc().getExternalContext().redirect("index.xhtml");
            }
        }
        }
        else{
            if(req.getRequestURI().contains("Admin") && KeepRecord.getRoles()!=null && !KeepRecord.getRoles().contains("Admin")){
                lb.setErrorstatus("User doesn't has the authorization...");
                req.getRequestDispatcher("Login.jsf").forward(request, response);
            }
            if(req.getRequestURI().contains("user") && KeepRecord.getRoles()!=null && !KeepRecord.getRoles().contains("User")){
                lb.setErrorstatus("User doesn't has the authorization...");
                req.getRequestDispatcher("Login.jsf").forward(request, response);
            }
            String str = req.getRequestURI();
            if(req.getRequestURI().equals("/SGA/")){
                sctx.authenticate(req, resp, null);
                if(KeepRecord.getRoles()!=null && KeepRecord.getRoles().contains("Admin")){
                    req.getRequestDispatcher("/Admin/Dashboard.jsf").forward(request, response);
                }
                else if(KeepRecord.getRoles()!=null && KeepRecord.getRoles().contains("User")){
                    lb.setuId(ub.getUserIdByUsername(lb.getUsername()));
                    req.getRequestDispatcher("/User/Dashboard.jsf").forward(request, response);
                }
            }
        }

        // Write code here to process the request and/or response before
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log items on the request object,
        // such as the parameters.
        /*
	for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    String values[] = request.getParameterValues(name);
	    int n = values.length;
	    StringBuffer buf = new StringBuffer();
	    buf.append(name);
	    buf.append("=");
	    for(int i=0; i < n; i++) {
	        buf.append(values[i]);
	        if (i < n-1)
	            buf.append(",");
	    }
	    log(buf.toString());
	}
         */
    }    
    
    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AuthFilter:DoAfterProcessing");
        }

        // Write code here to process the request and/or response after
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log the attributes on the
        // request object after the request has been processed. 
        /*
	for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    Object value = request.getAttribute(name);
	    log("attribute: " + name + "=" + value.toString());

	}
         */
        // For example, a filter might append something to the response.
        /*
	PrintWriter respOut = new PrintWriter(response.getWriter());
	respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
         */
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        if (debug) {
            log("AuthFilter:doFilter()");
        }
        
        doBeforeProcessing(request, response);
        
        Throwable problem = null;
        try {
            chain.doFilter(request, response);
        } catch (Throwable t) {
            // If an exception is thrown somewhere down the filter chain,
            // we still want to execute our after processing, and then
            // rethrow the problem after that.
            problem = t;
            t.printStackTrace();
        }
        
        doAfterProcessing(request, response);

        // If there was a problem, we want to rethrow it if it is
        // a known type, otherwise log it.
        if (problem != null) {
            if (problem instanceof ServletException) {
                throw (ServletException) problem;
            }
            if (problem instanceof IOException) {
                throw (IOException) problem;
            }
            sendProcessingError(problem, response);
        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {        
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {        
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {                
                log("AuthFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("AuthFilter()");
        }
        StringBuffer sb = new StringBuffer("AuthFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }
    
    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);        
        
        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);                
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");                
                pw.print(stackTrace);                
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }
    
    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }
    
    public void log(String msg) {
        filterConfig.getServletContext().log(msg);        
    }
    
}
