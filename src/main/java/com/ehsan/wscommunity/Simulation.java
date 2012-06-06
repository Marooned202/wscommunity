package com.ehsan.wscommunity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import sun.rmi.runtime.Log;

import com.ehsan.wscommunity.model.Cluster;
import com.ehsan.wscommunity.model.WebService;
import com.ehsan.wscommunity.model.WebServiceFeature;
import com.ehsan.wscommunity.util.Constants;

public class Simulation {

	private static final Logger log = Logger.getLogger(Simulation.class.getName());

	List <WebService> webServiceList = new ArrayList<WebService>();
	List <Cluster> centroids = new ArrayList<Cluster>(); 

	private final int CLUSTER_NUMBER = 3; 
	private final int WEBSERVICE_NUMBER = 10; 
	private final int FEATURE_NUMBER = 2; 
	private final DecimalFormat df = new DecimalFormat("#.###");

	public void initialize()
	{		
		int featureNumber = 0;
		for (int i = 0;i < WEBSERVICE_NUMBER; i++) 
		{
			featureNumber = 0;
			WebService webService = new WebService();		
			//for (int j = 0;j < FEATURE_NUMBER; j++)
			//{
			//	WebServiceFeature feature = new WebServiceFeature();
			//	feature.setId(j);
			//	feature.setValue(Math.random());
			//	webService.addWebServiceFeature(feature);
			//}						
						
			WebServiceFeature feature = new WebServiceFeature();
			feature.setId(featureNumber++);
			feature.setValue(Math.random());
			feature.setName(Constants.CommunityFeatureNames[featureNumber-1]);
			webService.addWebServiceFeature(feature);
				
			feature = new WebServiceFeature();
			feature.setId(featureNumber++);
			feature.setValue(Math.random());
			feature.setName(Constants.CommunityFeatureNames[featureNumber-1]);
			webService.addWebServiceFeature(feature);
			
			feature = new WebServiceFeature();
			feature.setId(featureNumber++);
			feature.setValue(Math.random());
			feature.setName(Constants.CommunityFeatureNames[featureNumber-1]);
			webService.addWebServiceFeature(feature);
			
			feature = new WebServiceFeature();
			feature.setId(featureNumber++);
			feature.setValue(Math.random());
			feature.setName(Constants.CommunityFeatureNames[featureNumber-1]);
			webService.addWebServiceFeature(feature);
			
			feature = new WebServiceFeature();
			feature.setId(featureNumber++);
			feature.setValue(Math.random());
			feature.setName(Constants.CommunityFeatureNames[featureNumber-1]);
			webService.addWebServiceFeature(feature);
			
			feature = new WebServiceFeature();
			feature.setId(featureNumber++);
			feature.setValue(Math.random());
			feature.setName(Constants.CommunityFeatureNames[featureNumber-1]);
			webService.addWebServiceFeature(feature);
			
			feature = new WebServiceFeature();
			feature.setId(featureNumber++);
			feature.setValue(Math.random());
			feature.setName(Constants.CommunityFeatureNames[featureNumber-1]);
			webService.addWebServiceFeature(feature);
			
			feature = new WebServiceFeature();
			feature.setId(featureNumber++);
			feature.setValue(Math.random());
			feature.setName(Constants.CommunityFeatureNames[featureNumber-1]);
			webService.addWebServiceFeature(feature);
			
			feature = new WebServiceFeature();
			feature.setId(featureNumber++);
			feature.setValue(Math.random());
			feature.setName(Constants.CommunityFeatureNames[featureNumber-1]);
			webService.addWebServiceFeature(feature);
			
			feature = new WebServiceFeature();
			feature.setId(featureNumber++);
			feature.setValue(Math.random());
			feature.setName(Constants.CommunityFeatureNames[featureNumber-1]);
			webService.addWebServiceFeature(feature);
			
			feature = new WebServiceFeature();
			feature.setId(featureNumber++);
			feature.setValue(Math.random());
			feature.setName(Constants.CommunityFeatureNames[featureNumber-1]);
			webService.addWebServiceFeature(feature);
			
			webServiceList.add(webService);
		}

		for (int i = 0;i < CLUSTER_NUMBER; i++) 
		{
			Cluster cluster = new Cluster();	
			cluster.setCluster(i);
			for (int j = 0;j < featureNumber; j++)
			{
				WebServiceFeature feature = new WebServiceFeature();
				feature.setId(j);
				feature.setName(Constants.CommunityFeatureNames[j]);
				feature.setValue(Math.random());
				cluster.addWebServiceFeature(feature);
			}
			centroids.add(cluster);
		}
	}

	public void run ()
	{
		initialize();
		reportWebServices(2);
		reportCentroids(2);
		boolean centroidChanged = true;
		int iterNumber = 0;
		
		long start = new java.util.Date().getTime();
		
		while (centroidChanged) 
		{		
			iterNumber++;
			log.info("------------------------------------------");
			log.info("Starting Iteration: " + iterNumber);
			centroidChanged = false;
			// Update Closest Centroids
			for (WebService webService:webServiceList)
			{				
				double minDistance=Double.MAX_VALUE, dist;
				int minIndex=-1;
				for (Cluster centroid:centroids) {
					dist = webService.distance(centroid);
					if (dist < minDistance) {
						minDistance = dist;
						minIndex = centroid.getCluster(); 
					}
				}
				if (webService.getCluster() != minIndex) centroidChanged = true;
				webService.setCluster(minIndex);				
			}

			reportWebServices(1);
			reportCentroids(2);
			
			// Calculate New Centroids
			int i = 0;
			for (Cluster centroid:centroids) {
				centroid.setCount(0);
				for (WebServiceFeature feature: centroid.getFeatureList()) {
					feature.setValue(0);
				}
				for (WebService webService:webServiceList) {
					if (webService.getCluster() == centroid.getCluster()) {
						centroid.setCount(centroid.getCount() + 1);
						for (WebServiceFeature feature: webService.getFeatureList()) {
							WebServiceFeature centroidFeature = centroid.getFeatureByID(feature.getId());
							centroidFeature.setValue(centroidFeature.getValue() + feature.getValue());
						}
					}
				}
				//log.info("Centroid: " + centroid.getCluster() + " , Count: " + centroid.getCount());
				if (centroid.getCount() != 0) {
					for (WebServiceFeature feature: centroid.getFeatureList()) {
						feature.setValue(feature.getValue() / centroid.getCount());					
						//log.info("Feature: " + feature.getId() + ", Value: " + df.format(feature.getValue()));
					}
				}
			}			
		}
		
		long end = new java.util.Date().getTime();
		
		log.info("-----------------------------------------------");
		log.info("K-Mean took: " + (end-start) + "ms");
		reportWebServices(2);
		reportCentroids(2);
	}

	private void reportWebServices(int level) 
	{
		int i = 0;
		for (WebService webService:webServiceList) {
			log.info("WebService["+ (i++) +"]: " + webService.getCluster());
			for (WebServiceFeature feature: webService.getFeatureList()) {
				if (level > 1) log.info("Feature["+ (feature.getId()) +"]: " + feature.getName() + ", Value: " + df.format(feature.getValue()));
			}
		}	
	}


	private void reportCentroids(int level) 
	{
		for (Cluster cluster:centroids) {
			log.info("Centroid-: " + cluster.getCluster() + ", Count: " + cluster.getCount());	
			for (WebServiceFeature feature: cluster.getFeatureList()) {
				if (level > 1) log.info("Feature["+ (feature.getId()) +"]: " + feature.getName() + ", Value: " + df.format(feature.getValue()));
			}
		}
	}
}
