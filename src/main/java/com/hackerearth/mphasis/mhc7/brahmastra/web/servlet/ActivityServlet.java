package com.hackerearth.mphasis.mhc7.brahmastra.web.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.hackerearth.mphasis.mhc7.brahmastra.biz.vo.BudhaEvent;
import com.hackerearth.mphasis.mhc7.brahmastra.data.BudhaDataforDataLoader;
import com.hackerearth.mphasis.mhc7.brahmastra.data.loader.BudhaDataLoaderThread;

/**
 * MIT License
 *
 * Copyright (c) 2018-19, Sumith Kumar Puri
 * 
 * GitHub URL https://github.com/sumithpuri Contest Name Mphasis Hackathon
 * Challenge Season 07 - Hosted by HackerEarth Techical Areas Microservices,
 * Predictive Analytics, Machine Learning Package Prefix
 * com.hackerearth.mphasis.mhc7.brahmastra Project Codename Budha (Mystery That
 * is Data!) Contact E-Mail code@sumithpuri.me Contact WhatsApp +91 9591497974
 *
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sub-license and/or sell
 * copies of the Software and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
public class ActivityServlet extends AbstractServlet {

	// TODO FIXME Class is Template and is Unused / Un-Implemented
	// TODO FIXME Intention was to Create/Render User Interface (Enterprise Grade
	// Software)
	// TODO FIXME Go through the Budha! Code, Feel Free to Implement this Part

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		Integer budhaEngineSignal = (Integer) req.getAttribute("budhaEngine");
		if(budhaEngineSignal != null && budhaEngineSignal==0) {
			
			// Send Shutdown Signal
			BudhaDataLoaderThread.IS_BUDHA_ENGINE_STARTED=false;
		}
		
		
		// TODO Respond Activity Event List Present in StoredList as JSON
		// Creating ArrayList
		initThread();
		ArrayList<BudhaEvent> al = new ArrayList<BudhaEvent>();
		for (int i = 0; i < 100; i++) {
			BudhaEvent ev = BudhaDataLoaderThread.activities.poll();
			if (ev == null) {
				break;
			}
			al.add(ev);
		}
		
		
		ObjectMapper mapper = new ObjectMapper();
		resp.setContentType("application/json");
		resp.addHeader("Access-Control-Allow-Origin", "*");
		try {
			mapper.writeValue(resp.getOutputStream(), al);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
