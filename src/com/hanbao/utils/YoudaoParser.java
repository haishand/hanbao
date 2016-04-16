package com.hanbao.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;


public class YoudaoParser {

	private static YoudaoParser instance; 
	public static YoudaoParser getInstance() {
		if (instance != null) {
			instance = new YoudaoParser();
		}
		return instance;
	}
	
	public TranslatorResult search(String word) {
		TranslatorResult result = null;
		
		try {
			result = new TranslatorResult();
			
			URL url = new URL("http://dict.youdao.com/search?q=" + URLEncoder.encode(word, "utf-8") + "&keyfrom=dict.index");
			HttpURLConnection con = (HttpURLConnection)url.openConnection();

			Parser parser = new Parser(con);
			
			NodeFilter filter1 = new TagNameFilter("div");
			NodeFilter filter2 = new HasAttributeFilter("id", "hhDictTrans");
			NodeFilter filter = new AndFilter(filter1, filter2);
			NodeList divNodes = parser.extractAllNodesThatMatch(filter);
			Node divNode = divNodes.elementAt(0);
			
			String html = divNode.getChildren().toHtml();
			parser = Parser.createParser(html, "utf-8");
			
			NodeFilter phoneticFilter = new AndFilter(new TagNameFilter("span"), 
					new HasAttributeFilter("class", "phonetic"));
			NodeList spanNodes = parser.extractAllNodesThatMatch(phoneticFilter);
System.out.println(spanNodes.elementAt(0).getChildren().asString());
			result.setPhonetic(spanNodes.elementAt(0).getChildren().asString());
			
			parser = Parser.createParser(html, "utf-8");
			NodeFilter senseFilter = new AndFilter(new TagNameFilter("p"), 
					new HasAttributeFilter("class", "sense-title"));
//		NodeFilter senseFilter = new TagNameFilter("li");
//		NodeFilter senseFilter = new TagNameFilter("p");
			NodeList senseNodes = parser.extractAllNodesThatMatch(senseFilter);
			System.out.println(senseNodes.elementAt(0).getChildren().asString());
			result.setSense(senseNodes.elementAt(0).getChildren().asString());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
}

