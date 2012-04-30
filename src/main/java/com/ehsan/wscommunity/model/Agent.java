package com.ehsan.wscommunity.model;

import java.util.List;

public interface Agent {
	public List<WebServiceFeature> getFeatureList();
	public WebServiceFeature getFeatureByID (int id);
}
