package com.ehsan.wscommunity;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.Logger;

import sun.misc.Cleaner;
import sun.rmi.runtime.Log;

import com.ehsan.wscommunity.model.Cluster;
import com.ehsan.wscommunity.model.WebService;
import com.ehsan.wscommunity.model.WebServiceFeature;
import com.ehsan.wscommunity.util.Constants;

public class Simulation {

	private static final Logger log = Logger.getLogger(Simulation.class.getName());

	List <WebService> webServiceList = new ArrayList<WebService>();
	List <Cluster> centroids = new ArrayList<Cluster>(); 


	private final int CLUSTER_NUMBER_WEBSERVICES = 5; 
	private final int WEBSERVICE_NUMBER = 100;


	private final int CLUSTER_NUMBER_COMMUNITY = 3;
	private final int COMMUNITY_NUMBER = 30;

	//private final int FEATURE_NUMBER = 2; 

	private final DecimalFormat df = new DecimalFormat("#.###");	
	Map<Integer,Double> threshold = new HashMap<Integer,Double>();
	Map<Integer,Double> weight = new HashMap<Integer,Double>();
	Map<Cluster,Double> goodness = new HashMap<Cluster,Double>();

	public void initializeCommunity()
	{		

		webServiceList.clear();
		int featureNumber = 0;
		for (int i = 0;i < COMMUNITY_NUMBER; i++) 
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

		centroids.clear();
		for (int i = 0;i < CLUSTER_NUMBER_COMMUNITY; i++) 
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


	public void initializeWebService()
	{		
		int featureNumber = 0;
		webServiceList.clear();
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



			WebServiceFeature feature = new WebServiceFeature(); // 0 Availability
			feature.setId(featureNumber++);
			feature.setValue(Math.random());
			feature.setName(Constants.WebServiceFeatureNames[featureNumber-1]);
			webService.addWebServiceFeature(feature);

			feature = new WebServiceFeature();  // 1 Response Time
			feature.setId(featureNumber++);
			feature.setValue(Math.random());
			feature.setName(Constants.WebServiceFeatureNames[featureNumber-1]);
			webService.addWebServiceFeature(feature);

			feature = new WebServiceFeature();	// 2 Throughput
			feature.setId(featureNumber++);
			feature.setValue(Math.random());
			feature.setName(Constants.WebServiceFeatureNames[featureNumber-1]);
			webService.addWebServiceFeature(feature);

			feature = new WebServiceFeature(); // 3 Excecution Time
			feature.setId(featureNumber++);
			feature.setValue(Math.random());
			feature.setName(Constants.WebServiceFeatureNames[featureNumber-1]);
			webService.addWebServiceFeature(feature);

			feature = new WebServiceFeature(); // 4 Latency
			feature.setId(featureNumber++);
			feature.setValue((Math.random() * webService.getFeatureByID(1).getValue()) / 2);
			feature.setName(Constants.WebServiceFeatureNames[featureNumber-1]);
			webService.addWebServiceFeature(feature);

			feature = new WebServiceFeature(); // 5 Stability
			feature.setId(featureNumber++);
			feature.setValue(Math.random());
			feature.setName(Constants.WebServiceFeatureNames[featureNumber-1]);
			webService.addWebServiceFeature(feature);

			feature = new WebServiceFeature(); 
			feature.setId(featureNumber++);
			feature.setValue(Math.random());
			feature.setName(Constants.WebServiceFeatureNames[featureNumber-1]);
			webService.addWebServiceFeature(feature);

			feature = new WebServiceFeature();
			feature.setId(featureNumber++);
			feature.setValue(Math.random());
			feature.setName(Constants.WebServiceFeatureNames[featureNumber-1]);
			webService.addWebServiceFeature(feature);

			feature = new WebServiceFeature(); // 8 Direct Out Degree
			feature.setId(featureNumber++);
			feature.setValue(Math.random());
			feature.setName(Constants.WebServiceFeatureNames[featureNumber-1]);
			webService.addWebServiceFeature(feature);

			feature = new WebServiceFeature(); // 9 Indirect Out Degree
			feature.setId(featureNumber++);
			feature.setValue((Math.random() * webService.getFeatureByID(7).getValue()));
			feature.setName(Constants.WebServiceFeatureNames[featureNumber-1]);
			webService.addWebServiceFeature(feature);

			feature = new WebServiceFeature(); // 10 Interoperability
			feature.setId(featureNumber++);
			feature.setValue(Math.random());
			feature.setName(Constants.WebServiceFeatureNames[featureNumber-1]);
			webService.addWebServiceFeature(feature);

			feature = new WebServiceFeature(); // 11 Accuracy
			feature.setId(featureNumber++);
			feature.setValue(Math.random());
			feature.setName(Constants.WebServiceFeatureNames[featureNumber-1]);
			webService.addWebServiceFeature(feature);

			feature = new WebServiceFeature(); // 12 Cooperative
			feature.setId(featureNumber++);
			feature.setValue(Math.random());
			feature.setName(Constants.WebServiceFeatureNames[featureNumber-1]);
			webService.addWebServiceFeature(feature);

			feature = new WebServiceFeature(); // 13 Replaceability
			feature.setId(featureNumber++);
			feature.setValue(Math.random());
			feature.setName(Constants.WebServiceFeatureNames[featureNumber-1]);
			webService.addWebServiceFeature(feature);

			feature = new WebServiceFeature(); // 14 Contributability
			feature.setId(featureNumber++);
			feature.setValue(Math.random());
			feature.setName(Constants.WebServiceFeatureNames[featureNumber-1]);
			webService.addWebServiceFeature(feature);

			webServiceList.add(webService);
		}

		centroids.clear();
		for (int i = 0;i < CLUSTER_NUMBER_WEBSERVICES; i++) 
		{
			Cluster cluster = new Cluster();	
			cluster.setCluster(i);
			for (int j = 0;j < featureNumber; j++)
			{
				WebServiceFeature feature = new WebServiceFeature();
				feature.setId(j);
				feature.setName(Constants.WebServiceFeatureNames[j]);
				feature.setValue(Math.random());
				cluster.addWebServiceFeature(feature);
			}
			centroids.add(cluster);
		}
	}


	public List <WebService> runCommunitiesSimulation ()
	{
		initializeCommunity();
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

		// Threshold

		assignThresholdsAndWeights();

		Cluster bestCluster = null;
		double bestClusterGoodness = 0;
		
		goodness.clear();
		for (Cluster cluster:centroids) {
			if (cluster.getCount() == 0) continue;
			double goodnessValue = 0; 	
			String logLine = "";
			for (WebServiceFeature feature: cluster.getFeatureList()) {
				goodnessValue += feature.getValue() * weight.get(feature.getId());
				logLine += " + " + feature.getName() + ":" + feature.getValue() + "*" + weight.get(feature.getId());
			}
			goodness.put(cluster, goodnessValue);
			log.info("Goodness["+cluster.getCluster()+"]: " + df.format(goodnessValue) + ", Count: " + cluster.getCount());
			log.info("Goodness["+cluster.getCluster()+"]: " + logLine);
			
			if (goodnessValue > bestClusterGoodness) {
				bestClusterGoodness = goodnessValue;
				bestCluster = cluster;
			}
		}	
		
		List <WebService> selectedList = new ArrayList<WebService>();
		// Reporting Best Cluster and its members
		log.info("Best Cluster: " + bestCluster.getCluster() + " ,Goodness Value: " + bestClusterGoodness);
		int i = 0;
		for (WebService webService:webServiceList) {
			if (webService.getCluster() != bestCluster.getCluster()) continue;
			selectedList.add(webService);
			log.info("WebService["+ (i++) +"]: " + webService.getCluster());			 
			for (WebServiceFeature feature: webService.getFeatureList()) {
				log.info("Feature["+ (feature.getId()) +"]: " + feature.getName() + ", Value: " + df.format(feature.getValue()));
			}
		}

		return selectedList;
	}

	public List <WebService> runWebServiceSimulation ()
	{
		initializeWebService();
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

		// Threshold

		assignThresholdsAndWeights();

		Cluster bestCluster = null;
		double bestClusterGoodness = 0;

		goodness.clear();
		for (Cluster cluster:centroids) {
			if (cluster.getCount() == 0) continue;
			double goodnessValue = 0; 	
			String logLine = "";
			for (WebServiceFeature feature: cluster.getFeatureList()) {
				goodnessValue += feature.getValue() * weight.get(feature.getId());				
				logLine += feature.getName() + ":" + df.format(feature.getValue()) + " * " + df.format(weight.get(feature.getId())) + " + ";				
			}
			goodness.put(cluster, goodnessValue);
			log.info("Goodness["+cluster.getCluster()+"]: " + df.format(goodnessValue) + ", Count: " + cluster.getCount());
			log.info("Goodness["+cluster.getCluster()+"]: " + logLine);

			if (goodnessValue > bestClusterGoodness) {
				bestClusterGoodness = goodnessValue;
				bestCluster = cluster;
			}
		}		

		List <WebService> selectedList = new ArrayList<WebService>();
		// Reporting Best Cluster and its members
		log.info("Best Cluster: " + bestCluster.getCluster() + " ,Goodness Value: " + bestClusterGoodness);
		int i = 0;
		for (WebService webService:webServiceList) {
			if (webService.getCluster() != bestCluster.getCluster()) continue;
			selectedList.add(webService);
			log.info("WebService["+ (i++) +"]: " + webService.getCluster());			 
			for (WebServiceFeature feature: webService.getFeatureList()) {
				log.info("Feature["+ (feature.getId()) +"]: " + feature.getName() + ", Value: " + df.format(feature.getValue()));
			}
		}

		return selectedList;
	}


	private void assignThresholdsAndWeights() 
	{	
		int i = 0;

		weight.clear();
		weight.put(i++, 0.5);	
		weight.put(i++, 0.5);	
		weight.put(i++, 0.5);	
		weight.put(i++, 0.5);	
		weight.put(i++, 0.5);	
		weight.put(i++, 0.5);	
		weight.put(i++, 0.5);	
		weight.put(i++, 0.5);	
		weight.put(i++, 0.5);	
		weight.put(i++, 0.5);	
		weight.put(i++, 0.5);	
		weight.put(i++, 0.5);	
		weight.put(i++, 0.5);	
		weight.put(i++, 0.5);	
		weight.put(i++, 0.5);	

		i = 0;
		threshold.put(i++, 0.5);
		threshold.put(i++, 0.5);
		threshold.put(i++, 0.5);
		threshold.put(i++, 0.5);
		threshold.put(i++, 0.5);
		threshold.put(i++, 0.5);
		threshold.put(i++, 0.5);
		threshold.put(i++, 0.5);
		threshold.put(i++, 0.5);
		threshold.put(i++, 0.5);
		threshold.put(i++, 0.5);
		threshold.put(i++, 0.5);
		threshold.put(i++, 0.5);
		threshold.put(i++, 0.5);
		threshold.put(i++, 0.5);	
		threshold.put(i++, 0.5);

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

	public void run ()
	{
		List <WebService> selectedList = null;
		
		selectedList = runWebServiceSimulation();
		simulateModelForWebServices (selectedList);
		
		selectedList = runCommunitiesSimulation();				
		simulateModelForCommunities (selectedList);
	}

	public void simulateModelForCommunities(List<WebService> selectedList) 
	{

		int HOURS = 100;

		double[] numberOfRequests = new double[HOURS];
		double[] satisfaction = new double[HOURS];
		double[] executionTime = new double[HOURS];
		double[] selection = new double[HOURS];
		double[] inDemand = new double[HOURS];

		double[] totalNumberOfRequests = new double[HOURS];
		double[] totalSatisfaction = new double[HOURS];
		double[] totalExecutionTime = new double[HOURS];
		double[] totalSelection = new double[HOURS];
		double[] totalInDemand = new double[HOURS];

		Random random = new Random();

		for (WebService webService:selectedList) {
			//evaluateWebService(webService);
			evaluateCommunity(webService);
			//System.out.println(webService.getRate());
		}
		for (int hour = 0; hour < HOURS; hour++)
		{
			numberOfRequests[hour] = 0;
			satisfaction[hour] = 0;
			executionTime[hour] = 0;
			selection[hour] = 0;
			inDemand[hour] = 0;

			totalNumberOfRequests[hour] = 0;
			totalSatisfaction[hour] = 0;
			totalExecutionTime[hour] = 0;
			totalSelection[hour] = 0;
			totalInDemand[hour] = 0;

			for (WebService webService:selectedList) {
				if (webService.getRate() == 3) 
				{
					numberOfRequests[hour] += (random.nextInt(4)) + 7;  // 7-10
					satisfaction[hour] += (random.nextInt(4)) + 7; // 7-10
					executionTime[hour] += (random.nextInt(15)) + 6;  // 6-20
					selection[hour] += (random.nextInt(4)/10.0) + 0.7;  // 0.7-1
					inDemand[hour] += (random.nextInt(4)) + 7;  // 7-10

				} else if (webService.getRate() == 2) 
				{
					numberOfRequests[hour] += (random.nextInt(3)) + 4;  // 7-10
					satisfaction[hour] += (random.nextInt(3)) + 4; // 7-10
					executionTime[hour] += (random.nextInt(11)) + 25;  // 6-20
					selection[hour] += (random.nextInt(3)/10.0) + 0.4;  // 0.7-1
					inDemand[hour] += (random.nextInt(3)) + 4;  // 7-10
				} else if (webService.getRate() == 1) 
				{
					numberOfRequests[hour] += (random.nextInt(3)) + 1;  // 7-10
					satisfaction[hour] += (random.nextInt(3)) + 1; // 7-10
					executionTime[hour] += (random.nextInt(19)) + 42;  // 6-20
					selection[hour] += (random.nextInt(3)/10.0) + 0.1;  // 0.7-1
					inDemand[hour] += (random.nextInt(3)) + 1;  // 7-10
				} 
			}

			numberOfRequests[hour] = numberOfRequests[hour]/selectedList.size();
			satisfaction[hour] = satisfaction[hour]/selectedList.size();
			executionTime[hour] = executionTime[hour]/selectedList.size();
			selection[hour] = selection[hour]/selectedList.size();

			if (hour > 1) 
			{
				totalNumberOfRequests[hour] = numberOfRequests[hour] + totalNumberOfRequests[hour-1];
				totalSatisfaction[hour] = satisfaction[hour] + totalSatisfaction[hour-1];
				totalExecutionTime[hour] = executionTime[hour] + totalExecutionTime[hour-1];
				totalSelection[hour] = selection[hour] + totalSelection[hour-1];
				totalInDemand[hour] = inDemand[hour] + totalInDemand[hour-1];
			}								
		}		

		try {
			PrintWriter out = new PrintWriter(new FileWriter("cm_number_of_requests_" + new java.util.Date().getTime() + ".txt")); 
			System.out.println();
			for (int i = 0; i < HOURS;i++)
			{
				System.out.print(totalNumberOfRequests[i] + ", ");
				out.println(totalNumberOfRequests[i]);
			}
			out.close();

			out = new PrintWriter(new FileWriter("cm_total_reponse_time_" + new java.util.Date().getTime() + ".txt"));
			System.out.println();
			for (int i = 0; i < HOURS;i++)
			{
				System.out.print(totalSatisfaction[i] + ", ");
				out.println(totalSatisfaction[i]);
			}
			out.close();

			out = new PrintWriter(new FileWriter("cm_total_executionTime_" + new java.util.Date().getTime() + ".txt"));
			System.out.println();
			for (int i = 0; i < HOURS;i++)
			{
				System.out.print(totalExecutionTime[i] + ", ");
				out.println(totalExecutionTime[i]);
			}
			out.close();

			out = new PrintWriter(new FileWriter("cm_total_selection_" + new java.util.Date().getTime() + ".txt"));
			System.out.println();
			for (int i = 0; i < HOURS;i++)
			{
				System.out.print(totalSelection[i] + ", ");
				out.println(totalSelection[i]);
			}
			out.close();
			
			out = new PrintWriter(new FileWriter("cm_total_indemand_" + new java.util.Date().getTime() + ".txt"));
			System.out.println();
			for (int i = 0; i < HOURS;i++)
			{
				System.out.print(totalInDemand[i] + ", ");
				out.println(totalInDemand[i]);
			}
			out.close();

		} catch (Exception e)
		{
			e.printStackTrace();
		}

	}


	public void simulateModelForWebServices(List<WebService> selectedList) 
	{

		int HOURS = 100;

		double[] numberOfRequests = new double[HOURS];
		double[] responseTime = new double[HOURS];
		double[] contribution = new double[HOURS];
		double[] replaceability = new double[HOURS];

		double[] totalNumberOfRequests = new double[HOURS];
		double[] totalResponseTime = new double[HOURS];
		double[] totalContribution = new double[HOURS];
		double[] totalReplaceability = new double[HOURS];

		Random random = new Random();

		for (WebService webService:selectedList) {
			evaluateWebService(webService);
			//System.out.println(webService.getRate());
		}
		for (int hour = 0; hour < HOURS; hour++)
		{
			numberOfRequests[hour] = 0;
			responseTime[hour] = 0;
			contribution[hour] = 0;
			replaceability[hour] = 0;

			totalNumberOfRequests[hour] = 0;
			totalResponseTime[hour] = 0;
			totalContribution[hour] = 0;
			totalReplaceability[hour] = 0;

			for (WebService webService:selectedList) {
				if (webService.getRate() == 3) 
				{
					numberOfRequests[hour] += (random.nextInt(4)) + 7;  // 7-10
					responseTime[hour] += (random.nextInt(15)/10.0) + 0.6;  // 0.6-2
					contribution[hour] += (random.nextInt(4)) + 7;  // 7-10
					replaceability[hour] += (random.nextInt(4)) + 7;  // 7-10

				} else if (webService.getRate() == 2) 
				{
					numberOfRequests[hour] += (random.nextInt(3)) + 4;  // 7-10
					responseTime[hour] += (random.nextInt(11)/10.0) + 2.5;  // 0.6-2
					contribution[hour] += (random.nextInt(3)) + 4;  // 7-10
					replaceability[hour] += (random.nextInt(3)) + 4;  // 7-10
				} else if (webService.getRate() == 1) 
				{
					numberOfRequests[hour] += (random.nextInt(3)) + 1;  // 7-10
					responseTime[hour] += (random.nextInt(19)/10.0) + 4.2;  // 0.6-2
					contribution[hour] += (random.nextInt(3)) + 1;  // 7-10
					replaceability[hour] += (random.nextInt(3)) + 1;  // 7-10
				} 
			}

			numberOfRequests[hour] = numberOfRequests[hour]/selectedList.size();
			responseTime[hour] = responseTime[hour]/selectedList.size();
			contribution[hour] = contribution[hour]/selectedList.size();
			replaceability[hour] = replaceability[hour]/selectedList.size();

			if (hour > 1) 
			{
				totalNumberOfRequests[hour] = numberOfRequests[hour] + totalNumberOfRequests[hour-1];
				totalResponseTime[hour] = responseTime[hour] + totalResponseTime[hour-1];
				totalContribution[hour] = contribution[hour] + totalContribution[hour-1];
				totalReplaceability[hour] = replaceability[hour] + totalReplaceability[hour-1];
			}								
		}		

		try {
			PrintWriter out = new PrintWriter(new FileWriter("ws_number_of_requests_" + new java.util.Date().getTime() + ".txt")); 
			System.out.println();
			for (int i = 0; i < HOURS;i++)
			{
				System.out.print(totalNumberOfRequests[i] + ", ");
				out.println(totalNumberOfRequests[i]);
			}
			out.close();

			out = new PrintWriter(new FileWriter("ws_total_reponse_time_" + new java.util.Date().getTime() + ".txt"));
			System.out.println();
			for (int i = 0; i < HOURS;i++)
			{
				System.out.print(totalResponseTime[i] + ", ");
				out.println(totalResponseTime[i]);
			}
			out.close();

			out = new PrintWriter(new FileWriter("ws_total_contribution_" + new java.util.Date().getTime() + ".txt"));
			System.out.println();
			for (int i = 0; i < HOURS;i++)
			{
				System.out.print(totalContribution[i] + ", ");
				out.println(totalContribution[i]);
			}
			out.close();

			out = new PrintWriter(new FileWriter("ws_total_replaceability_" + new java.util.Date().getTime() + ".txt"));
			System.out.println();
			for (int i = 0; i < HOURS;i++)
			{
				System.out.print(totalReplaceability[i] + ", ");
				out.println(totalReplaceability[i]);
			}
			out.close();

		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void evaluateCommunity(WebService webService) 
	{
		int high = 0;
		int medium = 0;
		int low = 0;

		int i = 0;

		// Internal Connection
		if (webService.getFeatureByID(i).getValue() > 0.7) 
			high++;
		else if (webService.getFeatureByID(i).getValue() > 0.4)
			medium++;
		else 
			low++;

		i++; // External Connection	
		if (webService.getFeatureByID(i).getValue() > 0.7) 
			high++;
		else if (webService.getFeatureByID(i).getValue() > 0.4)
			medium++;
		else 
			low++;

		i++; // Productivity	
		if (webService.getFeatureByID(i).getValue() > 0.7) 
			high++;
		else if (webService.getFeatureByID(i).getValue() > 0.4)
			medium++;
		else 
			low++;

		i++; // Resposiveness
		if (webService.getFeatureByID(i).getValue() > 0.7) 
			low++;
		else if (webService.getFeatureByID(i).getValue() > 0.4)
			medium++;
		else 
			high++;

		i++; // In Demand
		if (webService.getFeatureByID(i).getValue() > 0.7) 
			high++;
		else if (webService.getFeatureByID(i).getValue() > 0.4)
			medium++;
		else 
			low++;

		i++; // Satisfaction
		if (webService.getFeatureByID(i).getValue() > 0.7) 
			high++;
		else if (webService.getFeatureByID(i).getValue() > 0.4)
			medium++;
		else 
			low++;

		i++; // Availability
		if (webService.getFeatureByID(i).getValue() > 0.7) 
			high++;
		else if (webService.getFeatureByID(i).getValue() > 0.4)
			medium++;
		else 
			low++;

		i++; // Popularity	
		if (webService.getFeatureByID(i).getValue() > 0.7) 
			high++;
		else if (webService.getFeatureByID(i).getValue() > 0.4)
			medium++;
		else 
			low++;

		i++; // Selectivity	
		if (webService.getFeatureByID(i).getValue() > 0.7) 
			high++;
		else if (webService.getFeatureByID(i).getValue() > 0.4)
			medium++;
		else 
			low++;

		i++; // Market Share
		if (webService.getFeatureByID(i).getValue() > 0.7) 
			high++;
		else if (webService.getFeatureByID(i).getValue() > 0.4)
			medium++;
		else 
			low++;

		i++; // Selection
		if (webService.getFeatureByID(i).getValue() > 0.7) 
			high++;
		else if (webService.getFeatureByID(i).getValue() > 0.4)
			medium++;
		else 
			low++;		


		if ((high >= medium) && (high >= low)) {
			webService.setRate(3);			
		} else if ((medium >= high) && (medium >= low)) {
			webService.setRate(2);			
		} else {
			webService.setRate(1);			
		}

	}

	private void evaluateWebService(WebService webService) 
	{
		int high = 0;
		int medium = 0;
		int low = 0;

		int i = 0;

		// Availability
		if (webService.getFeatureByID(i).getValue() > 0.7) 
			high++;
		else if (webService.getFeatureByID(i).getValue() > 0.4)
			medium++;
		else 
			low++;

		i++; // Response Time		
		if (webService.getFeatureByID(i).getValue() > 0.7) 
			low++;
		else if (webService.getFeatureByID(i).getValue() > 0.4)
			medium++;
		else 
			high++;

		i++; // Throughput	
		if (webService.getFeatureByID(i).getValue() > 0.7) 
			high++;
		else if (webService.getFeatureByID(i).getValue() > 0.4)
			medium++;
		else 
			low++;

		i++; // Execution Time
		if (webService.getFeatureByID(i).getValue() > 0.7) 
			low++;
		else if (webService.getFeatureByID(i).getValue() > 0.4)
			medium++;
		else 
			high++;

		i++; // Latency
		if (webService.getFeatureByID(i).getValue() > 0.7) 
			low++;
		else if (webService.getFeatureByID(i).getValue() > 0.4)
			medium++;
		else 
			high++;

		i++; // Stability
		if (webService.getFeatureByID(i).getValue() > 0.7) 
			low++;
		else if (webService.getFeatureByID(i).getValue() > 0.4)
			medium++;
		else 
			high++;

		i++; // Reliability
		if (webService.getFeatureByID(i).getValue() > 0.7) 
			low++;
		else if (webService.getFeatureByID(i).getValue() > 0.4)
			medium++;
		else 
			high++;

		i++; // Accessibility	
		if (webService.getFeatureByID(i).getValue() > 0.7) 
			high++;
		else if (webService.getFeatureByID(i).getValue() > 0.4)
			medium++;
		else 
			low++;

		i++; // Direct Out Degree	
		if (webService.getFeatureByID(i).getValue() > 0.7) 
			high++;
		else if (webService.getFeatureByID(i).getValue() > 0.4)
			medium++;
		else 
			low++;

		i++; // Indirect Out Degree	
		if (webService.getFeatureByID(i).getValue() > 0.7) 
			high++;
		else if (webService.getFeatureByID(i).getValue() > 0.4)
			medium++;
		else 
			low++;

		i++; // Interoperability	
		if (webService.getFeatureByID(i).getValue() > 0.7) 
			high++;
		else if (webService.getFeatureByID(i).getValue() > 0.4)
			medium++;
		else 
			low++;

		i++; // Accuracy
		if (webService.getFeatureByID(i).getValue() > 0.7) 
			low++;
		else if (webService.getFeatureByID(i).getValue() > 0.4)
			medium++;
		else 
			high++;

		i++; // Cooperative	
		if (webService.getFeatureByID(i).getValue() > 0.7) 
			high++;
		else if (webService.getFeatureByID(i).getValue() > 0.4)
			medium++;
		else 
			low++;

		i++; // Replaceability	
		if (webService.getFeatureByID(i).getValue() > 0.7) 
			high++;
		else if (webService.getFeatureByID(i).getValue() > 0.4)
			medium++;
		else 
			low++;

		i++; // Contributability	
		if (webService.getFeatureByID(i).getValue() > 0.7) 
			high++;
		else if (webService.getFeatureByID(i).getValue() > 0.4)
			medium++;
		else 
			low++;


		if ((high >= medium) && (high >= low)) {
			webService.setRate(3);			
		} else if ((medium >= high) && (medium >= low)) {
			webService.setRate(2);			
		} else {
			webService.setRate(1);			
		}

	}
}