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
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NumberController {
	ArrayList<Long> arrList = null;
	Properties properties = null;
	String path = null;
	String errorMsg = "";
	String successMsg = "Added Successfully...";
	int iNumberofPosChange = 1;
	Long timeTakentoSort = 0l;
	NumberController() {
		FileInputStream is = null;
		ArrayList<String> arrListStr;
		try {
			path = new File(".").getCanonicalPath() + "/data.properties";

			properties = new Properties();
			is = new FileInputStream(path);
			properties.load(is);
			if(properties.getProperty("numbers") != null)
			{
				arrListStr = new ArrayList<String>(
						Arrays.asList(properties.getProperty("numbers").split(";")));
				arrList = new ArrayList<Long>();
				for (String string : arrListStr) {
					arrList.add(Long.parseLong(string));
				}
			}
			else
			{
				arrListStr = new ArrayList<String>();
				arrList = new ArrayList<Long>();
			}
			iNumberofPosChange = properties.getProperty("numberofposchange")==null?0:Integer.parseInt(properties.getProperty("numberofposchange"));
			timeTakentoSort = properties.getProperty("timetakentosort") == null?0l:Long.parseLong(properties.getProperty("timetakentosort"));
		} catch (IOException e) {
			errorMsg = e.getMessage();
		} finally {
			try {
				if(is != null)
				is.close();
			} catch (IOException io) {
				errorMsg = io.getMessage();
			}
		}
	}

	@RequestMapping("/")
	public String begin(Map<String, Object> model) {
		if (errorMsg.equals("")) {
			model.put("data", arrList);
			model.put("timeTaken", timeTakentoSort);
			model.put("changedPositions", iNumberofPosChange);
		} else {
			model.put("errorMsg", errorMsg);
		}
		return "welcome";
	}

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
				Long startTime = System.currentTimeMillis();
				TreeSet<Long> ts = new TreeSet<Long>(arrList);
				Long endTime = System.currentTimeMillis();
				timeTakentoSort = endTime - startTime;
				arrList = new ArrayList<>(ts);
				int iSize = arrList.size();
				for (int i = 0; i < iSize - 1; i++) {
					if (arrList.get(i) != oldNumbers.get(i)) {
						iNumberofPosChange++;
					}
				}
				out = new FileOutputStream(path);
				properties.setProperty("numbers",
						StringUtils.arrayToDelimitedString(ts.toArray(), ";"));
				properties.setProperty("numberofposchange",
						iNumberofPosChange+"");
				properties.setProperty("timetakentosort",
						timeTakentoSort+"");
				properties.store(out, null);

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
					if(out != null)
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