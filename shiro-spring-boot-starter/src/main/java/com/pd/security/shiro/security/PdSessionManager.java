package com.pd.security.shiro.security;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * @author peramdy on 2018/10/25.
 */
public class PdSessionManager extends DefaultWebSessionManager {


    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        return super.getSessionId(request, response);
    }

    @Override
    protected Session doCreateSession(SessionContext context) {
        return super.doCreateSession(context);
    }

    @Override
    protected Session newSessionInstance(SessionContext context) {
        return super.newSessionInstance(context);
    }

    @Override
    public Session start(SessionContext context) {
        return super.start(context);
    }


}
