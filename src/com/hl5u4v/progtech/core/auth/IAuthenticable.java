package com.hl5u4v.progtech.core.auth;

import com.hl5u4v.progtech.core.ErrorHandling.ModelNotPresentException;

public interface IAuthenticable {
    public void login(String identification, String password) throws ModelNotPresentException;
    public void logout();
    public void register();
    public boolean isLoggedIn();
    public boolean isRegisteredUser();
    public Authority getAuthority();
    public void setAuthority(Authority authority);
}
