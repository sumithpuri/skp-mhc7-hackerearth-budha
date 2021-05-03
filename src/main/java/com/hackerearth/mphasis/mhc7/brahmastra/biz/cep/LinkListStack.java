package com.hackerearth.mphasis.mhc7.brahmastra.biz.cep;

import java.util.LinkedList;
import java.util.List;

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
public class LinkListStack {
	
	// TODO FIXME LinkListStack Remains Unused / Un-Implemented
	// TODO FIXME Intention was to Use it in the User Interface
	// TODO FIXME Go through the Budha! Code, Feel Free to Implement this Part 
	
	LinkedList<BudhaEvent> li = new LinkedList<BudhaEvent>();

	public void push(BudhaEvent data) {
	    li.add(data);
	}
	

	public void pushAll(List<BudhaEvent> data) {
	    li.addAll(data);
	}

	public BudhaEvent poll() {
	    while(!li.isEmpty()){
	    	return li.poll();
	 	    }
	    return null;
	}

	public void displayStack() {
	    System.out.println("test");
	    li.peek();
	    li.remove();
	 }	
}
