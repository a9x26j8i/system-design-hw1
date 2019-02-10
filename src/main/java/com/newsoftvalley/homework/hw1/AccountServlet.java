package com.newsoftvalley.homework.hw1;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.security.auth.login.AccountNotFoundException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccountServlet extends HttpServlet {

    private final AccountResource _accountResource = new AccountResource();
    private final ObjectMapper _objectMapper = new ObjectMapper()
            .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String method = req.getMethod();
        try {
            switch(method) {
                case "POST":
                    createAccount(req, resp);
                    break;
                case "GET":
                    getAccount(req, resp);
                    break;
                case "DELETE":
                    deleteAccount(req, resp);
                    break;
                case "PUT":
                    updateAccount(req, resp);
                    break;
            }
        } catch (Exception e) {
            resp.getWriter().append(e.toString());
        }

    }

    private void createAccount(HttpServletRequest req, HttpServletResponse resp) throws IOException, DataNotFoundException {
        Account account = getAccountFromReq(req);
        Long newAccountId = _accountResource.add(account);
        resp.getWriter().append("create account successfully, id = " + newAccountId);
    }

    private void getAccount(HttpServletRequest req, HttpServletResponse resp) throws IOException, AccountNotFoundException {
        Long accountId = getId(req.getPathInfo());
        Account account = _accountResource.get(accountId);
        String output = _objectMapper.writeValueAsString(account);
        resp.getWriter().append(output);
    }

    private void updateAccount(HttpServletRequest req, HttpServletResponse resp) throws IOException, DataNotFoundException, AccountNotFoundException {
        Long accountId = getId(req.getPathInfo());
        Account account = _accountResource.update(accountId, getAccountFromReq(req));
        resp.getWriter().append("update successfully:\n" + account);
    }

    private void deleteAccount(HttpServletRequest req, HttpServletResponse resp) throws AccountNotFoundException, IOException {
        Long accountId = getId(req.getPathInfo());
        _accountResource.delete(accountId);
        resp.getWriter().append("deleted successfully:\n");
    }

    private Account getAccountFromReq (HttpServletRequest req) throws DataNotFoundException, IOException {
        int length = req.getContentLength();
        if (length != 0) {
            ServletInputStream servletInputStream = req.getInputStream();
            byte[] buffer = new byte[length];
            servletInputStream.read(buffer);
            String input = new String(buffer, "UTF-8");
            Account account = _objectMapper.readValue(input, Account.class);
            return account;
        } else {
            throw new DataNotFoundException();
        }
    }

    public Long getId(String pathInfo) {
        String regEx = "/([\\d]+)";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(pathInfo);
        Long result = null;
        if (m.find()) {
            result = Long.parseLong(m.group(1));
        }
        return result;
    }

}
