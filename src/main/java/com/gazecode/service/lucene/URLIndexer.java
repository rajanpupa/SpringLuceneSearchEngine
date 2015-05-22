package com.gazecode.service.lucene;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.lucene.analysis.SimpleAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.util.Version;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gazecode.beans.WebDocument;
import com.gazecode.config.Configuration;
import com.gazecode.zzzexperiment.jsoup.Example;

public class URLIndexer {
	
	@Autowired
	Configuration configuration;
	
	String indexDirectory;

	public URLIndexer(){
		if(configuration != null)
			indexDirectory = configuration.getProperty("INDEX_DIRECTORY");
	}
	
	public String getIndexDirectory() {
		return indexDirectory;
	}

	public void setIndexDirectory(String indexDirectory) {
		this.indexDirectory = indexDirectory;
	}
	
	/**
	 * This function adds a new url to the index
	 * 
	 * @param url
	 * @return "Url successfully added to index" if successful, otherwise "Failed"
	 */
	public WebDocument addToIndex(String url){
		WebDocument response = new WebDocument("url", "The body");
		indexDirectory = configuration.getProperty("INDEX_DIRECTORY");
		try {
            org.jsoup.nodes.Document doc = Jsoup.connect(url).get();
            
            String title = doc.title();
            String text = doc.text();
            String body = doc.body().text();
            
            
            
            response.setBody(body);
            response.setUrl(url);
            
            indexContent(url, title, body, indexDirectory);
        } catch (IOException ex) {
            Logger.getLogger(Example.class.getName()).log(Level.SEVERE, null, ex);
        }
		return response;
	}
	
	private void indexContent(String url, String title, String body, String indexDirectory){
		
		File file = new File(indexDirectory);
		IndexWriter indexWriter;
		Boolean mode;
		if(file.isDirectory() && file.list().length > 0){
			mode = false;
		}else{
			mode = true;
		}
		try {
			//true will write, false will append
			indexWriter = new IndexWriter(FSDirectory.open(file),
					new SimpleAnalyzer(), mode, IndexWriter.MaxFieldLength.LIMITED);
			org.apache.lucene.document.Document doc = new org.apache.lucene.document.Document();
			doc.add(	new Field("url", url, Field.Store.YES, Field.Index.ANALYZED) 	);
			doc.add(	new Field("title", title, Field.Store.YES, Field.Index.ANALYZED)	);
			doc.add(	new Field("body", body, Field.Store.YES, Field.Index.ANALYZED)	);

			System.out.println("The number of documents in this index is " + indexWriter.numDocs());
			indexWriter.addDocument(doc);
			
			indexWriter.optimize();
			indexWriter.close();
			
		} catch (CorruptIndexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LockObtainFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}


	public List<WebDocument> searchIndex(String searchString){
		
		List<WebDocument> webDocuments = new ArrayList<WebDocument>();
		indexDirectory = configuration.getProperty("INDEX_DIRECTORY");
		int maxHits = 100;
		
		File indexDir = new File(indexDirectory);
		//search the index
		//create WebDocument object out of the hit documents, add to list
		Directory directory;
		try {
			System.out.println(indexDirectory);
			directory = FSDirectory.open(indexDir);
			IndexSearcher searcher = new IndexSearcher(directory);
	        QueryParser parser = new QueryParser(Version.LUCENE_30, 
	             "body", new SimpleAnalyzer());
	        
	        System.out.println("search String is : " + searchString);
	        
	        Query query = parser.parse(searchString);
	        
	        TopDocs topDocs = searcher.search(query, maxHits);
	        
	        ScoreDoc[] hits = topDocs.scoreDocs;
	        System.out.println("The size of hits is : " + hits.length);
	        
	        for (int i = 0; i < hits.length; i++) {
	            int docId = hits[i].doc;
	            Document d = searcher.doc(docId);
	            webDocuments.add(new WebDocument(d.get("url"), d.get("body")));
	        }
		} catch (IOException e) {
			System.out.println("IOException in the URLIndexer, searchindex method.");
			e.printStackTrace();
		} catch (ParseException e) {
			System.out.println("parseException in the URLIndexer, searchindex method.");
			e.printStackTrace();
		}

        
        
		
		return webDocuments;
	}

	public static void main(String[] args) throws Exception {
		String url1 = "http://en.wikipedia.org/wiki/Java_Servlet";
		String url2 = "http://en.wikipedia.org/wiki/Java_programming_language";
		
		String query1 = "java";
		String query2 = "servlet";
		
		//main_create_index(url2);
		main_search_index(query1);
	}
	public static void main_create_index(String url){
		
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("spring-beans.xml");
		
		URLIndexer indexer = (URLIndexer) context.getBean("urlIndexer");
		indexer.setIndexDirectory("url_index");
		
		WebDocument wdoc = indexer.addToIndex(url);

		System.out.println("content = :\n " + wdoc.getBody());
	}

	public static void main_search_index(String query){
		List<WebDocument> webList;
		
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("spring-beans.xml");
		
		URLIndexer indexer = (URLIndexer) context.getBean("urlIndexer");
		indexer.setIndexDirectory("url_index");
		
		webList = indexer.searchIndex(query);
		
		System.out.println(webList.size());
		for(WebDocument wdoc : webList){
			System.out.println(wdoc.getUrl());
		}
	}
}
