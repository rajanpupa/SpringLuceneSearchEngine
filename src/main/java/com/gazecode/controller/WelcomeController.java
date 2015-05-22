package com.gazecode.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.gazecode.beans.SimpleFormString;
import com.gazecode.beans.WebDocument;
import com.gazecode.service.lucene.URLIndexer;
 
 
@Controller
@SessionAttributes
public class WelcomeController {
	@Autowired
	SimpleFormString searchQuery;
	
	@Autowired
	URLIndexer urlIndexer;
	
	@RequestMapping("/")
    public ModelAndView index(Model model) {
		ModelAndView model1 = new ModelAndView("search/searchPage", "command", searchQuery);
//		ModelAndView model1 = new ModelAndView();
//		model1.setViewName("search/searchPage");
//		
//		model1.addObject(searchQuery);
		return model1;
    }
	
	@RequestMapping( value = "/", method = RequestMethod.POST)
    public ModelAndView index2(@ModelAttribute("searchQuery") SimpleFormString searchQuery, BindingResult result) {
		
		ModelAndView model1 = new ModelAndView("search/searchPage", "command",
				searchQuery);
		String Query = searchQuery.getQuery();

		System.out.println(Query);

		//pass the search query to the service->searchIndex
		//retrieve the list of WebDocuments
		List<WebDocument> webDocumentList = urlIndexer.searchIndex(Query);
		//pass it to the model
		model1.addObject("webDocumentList", webDocumentList);
		
		System.out.println("WelcomeController : Size of documentList is " + webDocumentList.size());
		return model1;
    }
	
	@RequestMapping("/403")
    public String error403(Model model) {
		return "403";
    }
}