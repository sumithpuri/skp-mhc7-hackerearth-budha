package com.hackerearth.mphasis.mhc7.brahmastra.biz.messaging;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import com.hackerearth.mphasis.mhc7.brahmastra.biz.vo.BudhaEvent;


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
 * BudhaMessagingBean is used to receive messages of various types  from  the  BudhaDataLoader and is then used to 
 * pass on to the Complex Event Processor runtime. We have modelled every Budha Service such as Rules Engine as also
 * the Complex Event Processing service. 
 * 
 * Since we wanted to demo Budha using a layered architecutre in Brainwaves 2015, we had created this as class:
 * 
 * Data Loader(s) > Message Oriented Middleware > Complex Event Processing > UI
 *
 * [Core Java]		[HornetQ]						[Wildfly]			[Tomcat]
 * 
 * 
 * This is a very much real-world enterprise scneario for high volume  and  real time processing architecture - we may 
 * plan to use  high throughput messaging like Apache Kafka or even Apache Spark.
 */
/* @MessageDriven(
		activationConfig = { @ActivationConfigProperty(
			propertyName = "destination", propertyValue = "budhaqueue"), 
			@ActivationConfigProperty(
			propertyName = "destinationType", propertyValue = "javax.jms.Queue")
		}, 
		mappedName = "budhaqueue")
*/
public class BudhaMessagingBean 
// implements MessageListener 
{
	// TODO FIXME Class is Template and is Unused / Un-Implemented
	// TODO FIXME Intention was to Pump Events to this Bean (Enterprise Grade Software)
	// TODO FIXME Go through the Budha! Code, Feel Free to Implement this Part 

    /**
     * Default constructor. 
     */
    public BudhaMessagingBean() {
        // TODO Auto-generated constructor stub
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
       
    	System.out.println("Budha Messaging Bean has Received an Event");
    	try {
	    	ObjectMessage objMessage = (ObjectMessage) message;
	    	BudhaEvent sEvent = (BudhaEvent) objMessage.getObject();
	    	
	    	System.out.println("Budha Messaging has Converted Object");
    	} catch (JMSException e) {
    	
    		System.out.println("Budha Message has Encountered JMS Exception Due To " + e.getMessage());
    	}
    }

}
