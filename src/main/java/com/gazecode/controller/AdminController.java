package com.gazecode.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.gazecode.beans.SimpleFormString;
import com.gazecode.beans.WebDocument;
import com.gazecode.service.lucene.URLIndexer;

@Controller
@SessionAttributes
public class AdminController {

	@Autowired
	SimpleFormString toIndexURL;

	@Autowired
	URLIndexer urlIndexer;
	
	
	@RequestMapping("/admin")
	public ModelAndView index(Model model) {
		System.out.println("---------------/admin request handeling...........");
		ModelAndView model1 = new ModelAndView();
		model1.setViewName("search/admin");
		// model1.addObject(toIndexURL);
		model1.addObject("command", toIndexURL);

		return model1;
	}

	@RequestMapping(value = "/admin", method = RequestMethod.POST)
	public ModelAndView addToIndexURL(
			@ModelAttribute("toIndexURL") SimpleFormString toIndexURL,
			BindingResult result) {
		
		System.out.println("............./admin post method handeling........");
		String url = toIndexURL.getQuery();

		ModelAndView model1 = new ModelAndView("search/admin", "command",
				toIndexURL);

		System.out.println(url);
		// pass the url to service layer, to retrive the webpage, index etc
		// receive some data, about the task handed over to the service layer
		WebDocument wdoc = urlIndexer.addToIndex(url);
		// pass the data to the view, via model
		model1.addObject("url", wdoc.getUrl());
		model1.addObject("body", wdoc.getBody());
		return model1;
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView searchIndex(
			@ModelAttribute("searchQuery") SimpleFormString searchQuery,
			BindingResult result) {

		ModelAndView model1 = new ModelAndView("search/searchPage", "command",
				searchQuery);
		String Query = searchQuery.getQuery();

		System.out.println(Query);

		//pass the search query to the service->searchIndex
		//retrieve the list of WebDocuments
		List<WebDocument> webDocumentList = urlIndexer.searchIndex(Query);
		//pass it to the model
		model1.addObject("webDocumentList", webDocumentList);
		return model1;
	}

	@RequestMapping("/test")
	public ModelAndView test(Model model) {
		ModelAndView model1 = new ModelAndView();

		model1.addObject("message", "Test Successful");
		model1.setViewName("search/test");
		return model1;
	}

	@RequestMapping(value = { "/welcome**" }, method = RequestMethod.GET)
	public ModelAndView defaultPage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title",
				"Spring Security Login Form - Database Authentication");
		model.addObject("message", "This is default page!");
		model.setViewName("hello");
		return model;

	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");

		return model;

	}
}
