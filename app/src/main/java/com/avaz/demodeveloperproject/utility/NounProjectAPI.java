package com.avaz.demodeveloperproject.utility;

import com.github.scribejava.core.builder.api.DefaultApi10a;
import com.github.scribejava.core.model.OAuth1RequestToken;

public class NounProjectAPI extends DefaultApi10a {

    private static final String AUTHORIZE_URL = "http://api.thenounproject.com/icon/1";

    private static class InstancaHolder {
        private static final NounProjectAPI INSTANCE = new NounProjectAPI();
    }

    public static NounProjectAPI getInstance() {
        return InstancaHolder.INSTANCE;
    }

    @Override
    public String getRequestTokenEndpoint() {
        return "http://api.thenounproject.com/icon/1";
    }

    @Override
    public String getAccessTokenEndpoint() {
        return "http://api.thenounproject.com/icon/1";
    }

    @Override
    protected String getAuthorizationBaseUrl() {
        return "http://api.thenounproject.com/icon/1";
    }

    @Override
    public String getAuthorizationUrl(OAuth1RequestToken requestToken) {
        return String.format(AUTHORIZE_URL, requestToken.getToken());

    }
}