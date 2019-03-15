package com.web.securedApp.controller.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;

public class SessionRequestContent {
    private HttpServletRequest request;
    private HashMap<String, Object> requestAttributes;
    private HashMap<String, String> requestParameters;
    private HashMap<String, Object> sessionAttributes;

    public SessionRequestContent(HttpServletRequest request) {
        this.request = request;
        extractValues();
    }

    private void extractValues() {
        requestParameters = new HashMap<>();
        if (request.getParameterNames() != null) {
            Enumeration<String> paramNames = request.getParameterNames();
            while (paramNames.hasMoreElements()) {
                String name = paramNames.nextElement();
                requestParameters.put(name, request.getParameter(name));
            }
        }
        requestAttributes = new HashMap<>();
        if (request.getAttributeNames() != null) {
            Enumeration<String> attrNames = request.getAttributeNames();
            while (attrNames.hasMoreElements()) {
                String name = attrNames.nextElement();
                requestAttributes.put(name, request.getAttribute(name));
            }
        }
        sessionAttributes = new HashMap<>();
        if (request.getSession(false) != null) {
            Enumeration<String> sessionAttrNames = request.getSession(false).getAttributeNames();
            while (sessionAttrNames.hasMoreElements()) {
                String name = sessionAttrNames.nextElement();
                sessionAttributes.put(name, request.getSession().getAttribute(name));
            }
        }
    }

    public Object getRequestAttribute(String name) {
        return requestAttributes.get(name);
    }

    public void setRequestAttribute(String attributeName, Object attr) {
        request.setAttribute(attributeName, attr);
    }

    public String getRequestParameter(String name) {
        return requestParameters.get(name);
    }

    public void setSessionAttribute(String name, Object attr) {
        if (request.getSession(false) != null) {
            request.getSession(false).setAttribute(name, attr);
        }
    }

    public Object getSessionAttribute(String name) {
        return sessionAttributes.get(name);
    }

    public HttpSession getSession(boolean flag) {
        return request.getSession(flag);
    }

    public String[] getRequestParameters(String name) {
        return request.getParameterValues(name);
    }
}
