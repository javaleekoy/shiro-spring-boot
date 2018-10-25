package com.pd.security.shiro.security;

import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;


/**
 * @author peramdy on 2018/10/23.
 */
public class PdSecurityManager extends DefaultSecurityManager {
    @Override
    public void setRealm(Realm realm) {
        super.setRealm(realm);
    }
}
