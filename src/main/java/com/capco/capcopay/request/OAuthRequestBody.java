package com.capco.capcopay.request;

public class OAuthRequestBody {

    private String grant_type;
    private String scope;

    public OAuthRequestBody(String grant_type, String scope) {
        this.grant_type = grant_type;
        this.scope = scope;
    }

	public String getGrant_type() {
		return grant_type;
	}

	public void setGrant_type(String grant_type) {
		this.grant_type = grant_type;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}
    
}
