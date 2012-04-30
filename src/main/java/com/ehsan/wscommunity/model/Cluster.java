package com.ehsan.wscommunity.model;

import java.util.*;

import org.apache.log4j.Logger;

import com.ehsan.wscommunity.model.Cluster;

public class Cluster implements Agent{
		
	private static final Logger log = Logger.getLogger(Cluster.class.getName());
	
	List<WebServiceFeature> featureList = new ArrayList <WebServiceFeature>();	
	int cluster;
	int count;

	public int getCluster() {
		return cluster;
	}

	public void setCluster(int cluster) {
		this.cluster = cluster;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double distance (Cluster otherWebService)
	{
		if (featureList.size() != otherWebService.getFeatureList().size())
		{
			log.error("Webservices do not have same feature size");
			return -1; // Later throw an exception maybe
		}
		
		double sum = 0, dist;
		for (WebServiceFeature feature: featureList)
		{
			dist = feature.getValue() - otherWebService.getFeatureByID(feature.id).getValue();
			sum += (dist * dist);			
		}		
		return Math.sqrt(sum);
	}
	
	public WebServiceFeature getFeatureByID (int id)
	{
		for (WebServiceFeature feature: featureList) {
			if (feature.getId() == id)
				return feature;
		}
		return null;
	}
	
	public List<WebServiceFeature> getFeatureList() {
		if (featureList == null)
			featureList = new ArrayList<WebServiceFeature>();
		return featureList;
	}

	public Iterator<WebServiceFeature> getIteratorFeatureList() {
		if (featureList == null)
			featureList = new ArrayList<WebServiceFeature>();
		return featureList.iterator();
	}

	public void setWebServiceFeatureList(List<WebServiceFeature> newWebService) {
		removeAllFeatures();
		for (Iterator<WebServiceFeature> iter = newWebService.iterator(); iter.hasNext();)
			addWebServiceFeature((WebServiceFeature)iter.next());
	}

	public void addWebServiceFeature (WebServiceFeature newWebService) {
		if (newWebService == null)
			return;
		if (this.featureList == null)
			this.featureList = new ArrayList<WebServiceFeature>();
		if (!this.featureList.contains(newWebService))
			this.featureList.add(newWebService);
	}

	public void removeFeature(Cluster oldWebService) {
		if (oldWebService == null)
			return;
		if (this.featureList != null)
			if (this.featureList.contains(oldWebService))
				this.featureList.remove(oldWebService);
	}

	public void removeAllFeatures() {
		if (featureList != null)
			featureList.clear();
	}
	
}
