package com.gazecode.zzzexperiment.jsoup;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Example {
	public static void main(String[] args) {
		String url = "http://rajanpupa.blogspot.com";
		
		try {
            Document doc = Jsoup.connect(url).get();
            
            String title = doc.title();
            String text = doc.text();
            String body = doc.body().text();
            
            System.out.println("The title is " + title);
            System.out.println("The text \n" + text);
            System.out.println("The body \n" + body);
            
        } catch (IOException ex) {
            Logger.getLogger(Example.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
}
