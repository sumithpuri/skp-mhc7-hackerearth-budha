package com.hackerearth.mphasis.mhc7.brahmastra.biz.cep;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.conf.ClockTypeOption;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;

import com.hackerearth.mphasis.mhc7.brahmastra.biz.vo.BudhaEvent;
import com.hackerearth.mphasis.mhc7.brahmastra.biz.vo.BudhaEventCorrelation;

/**
 * MIT License
 *
 * Copyright (c) 2018-19,	Sumith Kumar Puri

 * GitHub URL 			https://github.com/sumithpuri
 * Contest Name         Mphasis Hackathon Challenge Season 07 - Hosted by HackerEarth 
 * Techical Areas       Microservices, Predictive Analytics, Machine Learning
 * Package Prefix 		com.hackerearth.mphasis.mhc7.brahmastra
 * Project Codename     Budha (Mystery That is Data!)
 * Contact E-Mail		code@sumithpuri.me
 * Contact WhatsApp		+91 9591497974
 *
 *
 * Permission is hereby  granted,  free  of  charge, to  any person  obtaining a  copy of  this  software and associated 
 * documentation files (the "Software"), to deal in the  Software without  restriction, including without limitation the 
 * rights to use, copy, modify, merge, publish, distribute, sub-license and/or sell copies of the Software and to permit 
 * persons to whom the Software is furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in  all copies or substantial portions of the 
 * Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS  OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE 
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR 
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES  OR  OTHER  LIABILITY, WHETHER IN AN ACTION  OF  CONTRACT, TORT OR 
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

/*
 * Budha Complex Event Processing is the Core Service that provides  the  most essential part  of feeding data to the 
 * Knowledge Is Everything API of  Drools. It will process data that is in  order or even  out-of-order. In  essence, it
 * provides the core of the Budha Intellect.
 */
public class BudhaComplexEventProcessing {

	private static BudhaComplexEventProcessing cepService = null;

	// Drools Fusion Runtime Configuration
	private KieBaseConfiguration kieConfiguration;
	private KnowledgeBase kieBase;
	private KieServices ks;
	private KieContainer kContainer;
	private KieSession kSession;
	private KnowledgeBuilder kbuilder;

	// memory sizing and reporting activities
	static long totalFactCount = 0;

	public static LinkedList<BudhaEventCorrelation> threats = new LinkedList();

	public static BudhaComplexEventProcessing getInstance() {

		if (cepService == null) {
			cepService = new BudhaComplexEventProcessing();
			cepService.init();
		}
		return cepService;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

		// XXX It is Recommended that you Run the Demo Using Budha.java
		//
		
		// Standalone Testing of the Complex Event Processing
		// Application of Artificial Intelligence in Banking
		// Many Queries, One Injection Point

		// budha01.drl - port and ip scan
		// budha02.drl - repeated System.out.printlnin
		// budha03.drl - port

		System.out.println("Budha Complex Event Processing is Testing Standalone...");

		System.setProperty("drools.dialect.mvel.strict", "false");
		System.setProperty("org.kie.demo", "false");

		System.out.println("Budha Complex Event Processing - Test Standalone Events");
		System.out.println("==========================================================");

		BudhaComplexEventProcessing droolsCEPServiceL = BudhaComplexEventProcessing.getInstance();
		BudhaEvent BudhaEvent = new BudhaEvent();

		String[] actions = new String[] { "", "failed login attempt", "port and ip scan", "download log file",
				"attempt to access", "attempt to attack", "port access", "download log file2", "attempt to access2",
				"attempt to attack2" };
		int k = 0;

		while (true) {
			int v = 100, s = 0;
			if (k % 2 == 0) {
				v = 255;
			}

			// 100 / 10s
			for (int i = 0; i <= 100; i++) {
				if (s > 25)
					s = 0;
				int a = (int) (Math.random() * 10);
				if (k % 2 == 0)
					a = 1;
				BudhaEvent = new BudhaEvent();
				BudhaEvent.setEventSourceIp("10.56.112.122");
				BudhaEvent.setEventDestinationIp("192.22.23.121");
				BudhaEvent.setEventSourcePort("201");
				BudhaEvent.setEventSourceTime(new java.util.Date().getTime());
				BudhaEvent.setEventDestinationPort("" + (++s));
				BudhaEvent.setEventType(actions[2]);
				BudhaEvent.setEventDestinationTimestamp(new Date().getTime());
				droolsCEPServiceL.execute(BudhaEvent);
			}
			k++;

			// 1s - #poc_demo_scope_only
			// Thread.sleep(1000);
		}
	}

	public void init() {

		try {

			System.out.println("Initializing KIE Runtime for Drools Fusion...");

			kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
			// kbuilder.add(ResourceFactory.newClassPathResource("event.drl"),
			// ResourceType.DRL);

			if (kbuilder.hasErrors()) {
				System.out.println(kbuilder.getErrors().toString());
			}

			kieConfiguration = KieServices.Factory.get().newKieBaseConfiguration();

			kieConfiguration.setProperty("drools.dialect.mvel.strict", "false");
			kieConfiguration.setProperty("org.kie.demo", "false");
			kieConfiguration.setOption(EventProcessingOption.STREAM);
			ks = KieServices.Factory.get();
			kContainer = ks.getKieClasspathContainer();
			kieBase = KnowledgeBaseFactory.newKnowledgeBase(kieConfiguration);

			kieBase.addKnowledgePackages(kbuilder.getKnowledgePackages());

			// clock type for the session
			KieSessionConfiguration sessionConfiguration = KnowledgeBaseFactory.newKnowledgeSessionConfiguration();
			sessionConfiguration.setOption(ClockTypeOption.get("realtime"));

			kSession = kContainer.newKieSession("budha-event", sessionConfiguration);
			kSession.setGlobal("threatMap", new HashMap<Long, BudhaEventCorrelation>());
			kSession.setGlobal("startTime", new Date().getTime());
			kSession.setGlobal("startMemory", Runtime.getRuntime().freeMemory());
			kSession.setGlobal("totalFactCount", totalFactCount);
			System.out.println("Initialized the KIE Runtime for Drools Fusion...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static long prevTime = 0, currTime = 0;

	public void execute(BudhaEvent event) {

		// try {
		System.out.println("Received a Budha Event in the Complex Event Processing Service...");

		// anything to with event object
		kSession.setGlobal("totalFactCount", totalFactCount++);
		System.out.println("Running through the Complex Event Processing Rules... #" + totalFactCount);
		kSession.insert(event);
		
		// creating efficient throughput by firing rules per 100 facts
		if(totalFactCount%100==0) kSession.fireAllRules();
		
		// TODO FIXME XXX
		// remember that this entire codebase demonstrates all aspects of drools
		// rules (drools expert) and drools cep (drools fusion) - also there is
		// a starting point to create the multi-threaded data loader. all is fine
		// except that the correct way to use drools is to continuously fire the
		// drools runtime using the following code, but make sure that the data
		// loader is running as a separate thread
		//
		// uncomment the below, once you have separate multi-threaded data loader 
		/*
		 * new Thread() {
		        @Override
		        public void run() {
		            kieSession.fireUntilHalt();
		        }
		    }.start();
		 */

		// Below Code was Actually Intended to Help Web User Interface Rendering
		HashMap<Long,BudhaEventCorrelation> threatM = (HashMap) kSession.getGlobal("threatMap");
		LinkedList<BudhaEventCorrelation> list = new LinkedList();
		list.addAll(threatM.values());
		threats.addAll(list);
		System.out.println("Finished Running through the Complex Event Processing Rules...");

		if (prevTime == 0)
			prevTime = Long.parseLong(kSession.getGlobal("startTime").toString());
		currTime = new Date().getTime();
	}
}
