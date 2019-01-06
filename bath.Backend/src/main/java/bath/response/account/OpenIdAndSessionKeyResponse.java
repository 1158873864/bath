package bath.response.account;


import bath.response.Response;

public class OpenIdAndSessionKeyResponse extends Response {
    private String openId;
    private String sessionKey;

    private String token;
    public OpenIdAndSessionKeyResponse() {
    }

    public OpenIdAndSessionKeyResponse(String openId, String sessionKey,String token) {
        this.openId = openId;
        this.sessionKey = sessionKey;
        this.token=token;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
