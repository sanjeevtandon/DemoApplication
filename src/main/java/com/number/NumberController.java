package com.number;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;
import java.util.TreeSet;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NumberController {
	ArrayList<Long> arrList = null;
	Properties properties = null;
	File file = null;
	String errorMsg = "";
	String successMsg = "Added Successfully...";
	int iNumberofPosChange = 0;
	Long timeTakentoSort = 0l;
	
	//loading data property file to load the last saved sorted list, time taken to sort and number of position re-arranged data if any
	NumberController() {
		FileInputStream is = null;
		ArrayList<String> arrListStr;
		try {
			file = new File("data.properties");
			if(!file.exists())
			{
				file.createNewFile();
			}
			properties = new Properties();
			is = new FileInputStream(file);
			properties.load(is);
			if (properties.getProperty("numbers") != null) {
				arrListStr = new ArrayList<String>(Arrays.asList(properties
						.getProperty("numbers").split(";")));
				arrList = new ArrayList<Long>();
				for (String string : arrListStr) {
					arrList.add(Long.parseLong(string));
				}
			} else {
				arrListStr = new ArrayList<String>();
				arrList = new ArrayList<Long>();
			}
			iNumberofPosChange = properties.getProperty("numberofposchange") == null ? 0
					: Integer.parseInt(properties
							.getProperty("numberofposchange"));
			timeTakentoSort = properties.getProperty("timetakentosort") == null ? 0l
					: Long.parseLong(properties.getProperty("timetakentosort"));
		} catch (IOException e) {
			errorMsg = e.getMessage();
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (IOException io) {
				errorMsg = io.getMessage();
			}
		}
	}

	@RequestMapping("/")
	public String begin(Map<String, Object> model) {
		if (errorMsg.equals("")) {
			//setting model objects for view layer use
			model.put("data", arrList);
			model.put("timeTaken", timeTakentoSort);
			model.put("changedPositions", iNumberofPosChange);
		} else {
			//setting error message for view layer use
			model.put("errorMsg", errorMsg);
		}
		return "welcome";
	}
	
	//re-sort, calculate time taken to sort, number of re-arrangements and storing the modified values
	@RequestMapping(value = "/submitData", method = RequestMethod.POST)
	public ModelAndView setData(@RequestParam(value = "enterNumber") Long number) {
		ModelAndView mv = new ModelAndView("welcome");
		if (errorMsg.equals("")) {
			FileOutputStream out = null;
			timeTakentoSort = 0l;
			iNumberofPosChange = 0;
			try {
				ArrayList<Long> oldNumbers = new ArrayList<>(arrList);
				if (!arrList.contains(number)) {
					arrList.add(number);
				}
				
				//Sorting and calculating time taken for sorting
				Long startTime = System.nanoTime();
				TreeSet<Long> ts = new TreeSet<Long>(arrList);
				Long endTime = System.nanoTime();
				timeTakentoSort = endTime - startTime;
				
				
				arrList = new ArrayList<>(ts);
				int iSize = arrList.size();
				
				//Calculating number of re-arranged positions as compared to previous list
				for (int iCount = 0; iCount < iSize - 1; iCount++) {
					if (arrList.get(iCount) != oldNumbers.get(iCount)) {
						iNumberofPosChange++;
					}
				}
				out = new FileOutputStream(file);
				
				//storing the latest sorted list, number of position re-arranged and time taken to sort
				properties.setProperty("numbers",
						StringUtils.arrayToDelimitedString(ts.toArray(), ";"));
				properties.setProperty("numberofposchange", iNumberofPosChange
						+ "");
				properties.setProperty("timetakentosort", timeTakentoSort + "");
				properties.store(out, null);
				
				//setting model objects for view layer use
				mv.addObject("data", ts);
				mv.addObject("timeTaken", timeTakentoSort);
				mv.addObject("changedPositions", iNumberofPosChange);
				mv.addObject("successMsg", successMsg);
			} catch (IOException fl) {
				errorMsg = fl.getMessage();
			} finally {
				timeTakentoSort = 0l;
				iNumberofPosChange = 0;
				try {
					if (out != null)
						out.close();
				} catch (IOException e) {
					errorMsg = e.getMessage();
				}
			}
		} else {
			mv.addObject("errorMsg", errorMsg);
		}

		return mv;
	}
}