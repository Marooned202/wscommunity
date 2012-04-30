package com.ehsan.wscommunity;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ehsan.wscommunity.model.Cluster;
import com.ehsan.wscommunity.model.WebService;
import com.ehsan.wscommunity.model.WebServiceFeature;

public class Simulation {

	private static final Logger log = Logger.getLogger(Simulation.class.getName());

	List <WebService> webServiceList = new ArrayList<WebService>();
	List <Cluster> centroids = new ArrayList<Cluster>(); 

	private final int CLUSTER_NUMBER = 3; 

	public void initialize()
	{
		for (int i = 0;i < 10; i++) 
		{
			WebService webService = new WebService();		
			WebServiceFeature feature = new WebServiceFeature();
			for (int j = 0;j < 5; j++)
			{
				feature.setId(j);
				feature.setValue(Math.random());
				webService.addWebServiceFeature(feature);
			}						
			webServiceList.add(webService);
		}

		for (int i = 0;i < CLUSTER_NUMBER; i++) 
		{
			Cluster cluster = new Cluster();	
			cluster.setCluster(i);
			WebServiceFeature feature = new WebServiceFeature();
			for (int j = 0;j < 5; j++)
			{
				feature.setId(j);
				feature.setValue(Math.random());
				cluster.addWebServiceFeature(feature);
			}
			centroids.add(cluster);
		}

	}

	public void run ()
	{
		initialize();
		boolean centroidChanged = true;
		int iterNumber = 0;

		while (centroidChanged) 
		{		
			iterNumber++;
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

			// Calculate New Centroids
			int i = 0;
			for (Cluster centroid:centroids) {
				centroid.setCount(0);
				for (WebService webService:webServiceList) {
					if (webService.getCluster() == centroid.getCluster()) {
						centroid.setCount(centroid.getCount() + 1);
						for (WebServiceFeature feature: webService.getFeatureList()) {
							WebServiceFeature centroidFeature = centroid.getFeatureByID(feature.getId());
							centroidFeature.setValue(centroidFeature.getValue() + feature.getValue());
						}
					}
				}
				log.info("Centroid : " + centroid.getCount());
				if (centroid.getCount() != 0) {
					for (WebServiceFeature feature: centroid.getFeatureList()) {
						feature.setValue(feature.getValue() / centroid.getCount());					
						log.info("Feature: " + feature.getId() + ", Value: " + feature.getValue());
					}
				}
			}			
		}
	}

}
