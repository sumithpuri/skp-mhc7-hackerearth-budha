package com.hackerearth.mphasis.mhc7.brahmastra.data.loader;

import static com.hackerearth.mphasis.mhc7.brahmastra.data.BudhaDataforDataLoader.*;

import java.util.Date;

import com.hackerearth.mphasis.mhc7.brahmastra.biz.cep.LinkListStack;
import com.hackerearth.mphasis.mhc7.brahmastra.biz.cep.BudhaComplexEventProcessing;
import com.hackerearth.mphasis.mhc7.brahmastra.biz.vo.BudhaEvent;
import com.hackerearth.mphasis.mhc7.brahmastra.data.BudhaDataforDataLoader;

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
public class BudhaDataLoaderThread extends Thread {
	
	public static boolean IS_BUDHA_ENGINE_STARTED=false;
	
	private int usecase;
	

	public BudhaDataLoaderThread() {

	}
	
	public BudhaDataLoaderThread(int usecase) {
		this.usecase=usecase;
	}

	
	static synchronized BudhaEvent generateDataforUseCases(BudhaEvent DataM){
		int rand = 0;

		// 1.Setting event_ID -> unique,integer
		DataM.setEventId(++BudhaDataforDataLoader.event_id);
		DataM.setEventSourceTime(new java.util.Date().getTime());
		DataM.setEventDestinationTimestamp(new Date().getTime());

		// 2.setting eventDesc
		rand = 0 + (int) (Math.random() * (eventDesc.length - 1));
		DataM.setEventDescription(eventDesc[rand]);

		if (BudhaDataforDataLoader.event_id % 10 == 0) {

			injectPositiveForUseCase01(DataM);

		} else if (BudhaDataforDataLoader.event_id % 25 == 0) {

			injectPositiveForUseCase02(DataM);

		} else if (BudhaDataforDataLoader.event_id % rand_x == 0) {

			injectPositiveForUseCase03(DataM);
		}

		else {

			// 3.Setting eventType ->from a list random events
			rand = 0 + (int) (Math.random() * (eventTypes.length - 1));
			DataM.setEventType(eventTypes[rand]);

			// 4.Setting eventSrcIP
			rand = 0 + (int) (Math.random() * (eventSrcIP.length - 1));
			DataM.setEventSourceIp(eventSrcIP[rand]);

			// 5.Setting eventDestIP
			rand = 0 + (int) (Math.random() * (eventDestIP.length - 1));
			DataM.setEventDestinationIp(eventDestIP[rand]);

			// 6.setting eventSrcPort
			rand = 0 + (int) (Math.random() * (eventSrcPort.length - 1));
			DataM.setEventSourcePort(eventSrcPort[rand]);

			// 7.setting eventDstnPort
			rand = 0 + (int) (Math.random() * (eventDstnPort.length - 1));
			DataM.setEventDestinationPort(eventDstnPort[rand]);
		}

		addCommonDataToRecord(DataM);
		return DataM;
	}

	public static void addCommonDataToRecord(BudhaEvent DataM) {
		int rand;
		//8.setting countries
		rand= 0 + (int)(Math.random()*(countries.length-1)); 
		DataM.setEventSourceCountry(countries[rand]);
		
		//9.setting countries
		rand= 0 + (int)(Math.random()*(countries.length-1)); 
		DataM.setEventDestinationCountry(countries[rand]);
		
		//10.SettingEventSourceuserName 
		rand= 0 + (int)(Math.random()*(userName.length-1)); 
		DataM.setEventSourceUsername(userName[rand]);
		
		//11.Setting EventDestinationUsername 
		rand= 0 + (int)(Math.random()*(userName.length-1)); 
		DataM.setEventDestinationUsername(userName[rand]);
		
		//12.Setting Source and Destn timeStamp	
		java.util.Date date= new java.util.Date();
		DataM.setEventSourceTime(date.getTime());
		DataM.setEventDestinationTimestamp(date.getTime());
		
		//13.Setting remarks
		DataM.setEventRemarks("Event Remarks");
	}

	public static void injectPositiveForUseCase03(BudhaEvent DataM) {
		// use-case 02
		System.out.println("Injected a Positive Record in the Stream...");
		DataM.setEventSourceIp("192.22.11.121");
		DataM.setEventDestinationIp("192.22.23.121");
		DataM.setEventSourcePort("201");			
		DataM.setEventDestinationPort("8085");
		DataM.setEventDestinationUsername("admin");
		DataM.setEventType(eventTypes[6]);
		
		if(rand_x % 5 !=0) rand_x++;
	}

	public static void injectPositiveForUseCase02(BudhaEvent DataM) {
		// use-case 02
		System.out.println("Injected a Positive Record in the Stream...");
		DataM.setEventSourceIp("192.22.23.121");
		DataM.setEventDestinationIp("192.22.23.121");
		DataM.setEventSourcePort("201");			
		DataM.setEventDestinationPort("8081");
		DataM.setEventDestinationUsername("admin");
		DataM.setEventType(eventTypes[1]);
	}

	public static void injectPositiveForUseCase01(BudhaEvent DataM) {
		// use-case 01
		// System.out.println("injected a positive record in the stream...");
		DataM.setEventSourceIp("10.56.112.122");
		DataM.setEventDestinationIp("192.22.23.121");
		DataM.setEventSourcePort("201");			
		DataM.setEventDestinationPort(""+(++rand_pt));
		DataM.setEventType(eventTypes[2]);
	}


	public static synchronized BudhaEvent randomDataGeneratorCase1(BudhaEvent DataM) {
		int rand = 0;
		// 1.Setting event_ID -> unique,integer
		DataM.setEventId(++BudhaDataforDataLoader.event_id);
		// 2.Setting eventType ->from a list random events
		rand = 0 + (int) (Math.random() * (eventTypes.length - 1));
		DataM.setEventType(eventTypes[rand]);
		// 3.setting eventDesc
		rand = 0 + (int) (Math.random() * (eventDesc.length - 1));
		DataM.setEventDescription(eventDesc[rand]);
		// 4.Setting eventSrcIP
		DataM.setEventSourceIp(eventSrcIP[5]);
		// 5.Setting eventDestIP for Icici bank
		DataM.setEventDestinationIp("115.112.1.14");
		// 6.setting eventSrcPort
		rand = 0 + (int) (Math.random() * 5);
		DataM.setEventSourcePort(eventSrcPort[rand]);
		// 7.setting eventDstnPort
		rand = 0 + (int) (Math.random() * 5);
		DataM.setEventDestinationPort(eventDstnPort[rand]);
		addCommonDataToRecord(DataM);
		return DataM;
	}
	
	public static LinkListStack activities=new LinkListStack();
	

	
	public void run() {

		// TODO FIXME Commented Code Provided Here is to Facilitate JSON/Servlet (User Interface)
		while(IS_BUDHA_ENGINE_STARTED) {
			
			try {
				// 1000 Records Per Minute
				for (int i = 0; i <= 100; i++) {

					BudhaEvent DataM1 = new BudhaEvent();
					
					// if(this.usecase==1) {
					// RandomGenerator will be called					
					DataM1 = generateDataforUseCases(DataM1);
					activities.push(DataM1);
					BudhaComplexEventProcessing budhaCEP = BudhaComplexEventProcessing.getInstance();
					budhaCEP.execute(DataM1);
					
					// }
					// if(usecase ==1 && usecase!=0) {
					// 		for(int j=0;j<20;j++) {
					// 				BudhaEvent DataUsecase1=new BudhaEvent();
					// 				DataM1=randomDataGeneratorCase1(DataUsecase1);	
					//			}
					//		}
					//
					// Providing Json Objects
					// ObjectMapper mapper = new ObjectMapper();
					//
					// try {
					//		mapper.writeValue(new File("C:\\Users\\gsahu\\Desktop\\UI\\DataModel.json"), DataM1);
					//		System.out.println(mapper.writeValueAsString(DataM1));
					// } catch (JsonGenerationException e) {
					//		// TODO Auto-Generated Catch Block
					// 		e.printStackTrace();
					// } catch (JsonMappingException e) {
					//		// TODO Auto-Generated Catch Block
					//		e.printStackTrace();
					// } catch (IOException e) {
					//		// TODO Auto-Generated Catch Block
					//		e.printStackTrace();
					// }
					//
					// if(usecase ==2 && usecase!=0){
					//		for(int j=0;j<2;j++){
					//			BudhaEvent DataUsecase1=new BudhaEvent();
					//			DataUsecase1=randomDataGeneratorCase1(DataUsecase1);
					//			try {
					//				mapper.writeValue(new File("C:\\Users\\gsahu\\Desktop\\UI\\DataModel.json"), DataUsecase1);
					//				System.out.println(mapper.writeValueAsString(DataUsecase1));
					//			} catch (JsonGenerationException e) {
					//				// TODO Auto-generated catch block
					//				e.printStackTrace();
					//			} catch (JsonMappingException e) {
					//				// TODO Auto-generated catch block
					//				e.printStackTrace();
					//			} catch (IOException e) {
					//				// TODO Auto-generated catch block
					//				e.printStackTrace();
					//			}
					//		}
					//	}
					
				}
				
				//Thread.sleep(1000);
			} catch (Exception e) {}
		}
	}
}
