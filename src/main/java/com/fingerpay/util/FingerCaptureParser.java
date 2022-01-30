package com.fingerpay.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


import com.google.gson.Gson;

public class FingerCaptureParser {
	
	private static final Logger log = Logger.getLogger(FingerCaptureParser.class);
	
	private static String getCharacterDataFromElement(Element element){
		String data = "";
		Node node = element.getFirstChild();
		if(node instanceof CharacterData){
			CharacterData cd = (CharacterData) node;
			return cd.getData();
			
		}
		
		return data;
	}
	
	private static Document getDocument(String xml) throws ParserConfigurationException, SAXException, IOException{
		Document doc = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		InputSource is = new InputSource(new StringReader(xml));
		doc = db.parse(is);
		return doc;
	}
	
	public static String  captureResponse(String xml) {
		String res="";
		Map<String, Object> returnData = new HashMap<String, Object>();	
		
		try {
		Document doc=getDocument(xml);
		doc.getDocumentElement().normalize();
		NodeList node=doc.getElementsByTagName("PidData");
		Element element=(Element)node.item(0);
		NodeList DeviceInfo=element.getElementsByTagName("DeviceInfo");
		Element device=(Element)DeviceInfo.item(0);
		
	
		
		System.out.println("DeviceInfo:::::::::::::"+device.getAttributes().getLength());
		NodeList additional_info=device.getElementsByTagName("additional_info");
		Element additional=(Element)additional_info.item(0);
		System.out.println("res::::::::::::::::::::"+additional);
		NodeList Param=additional.getElementsByTagName("Param");
		System.out.println("res::::::::::::::::::::"+Param);
		Element par=(Element)Param.item(0);
		System.out.println("res::::::::::::::::::::"+par.getAttributes().getLength());
		res=par.getAttributes().getNamedItem("value").getNodeValue();
		System.out.println("res::::::::::::::::::::"+res);
		
		}catch (Exception e) {
		log.error("captureResponse:::::::::::::::::::"+e);
		}
		
		
		return res;
		
	}

	public static void main(String[] args) {
		String xml="<?xml version=\"1.0\"?><PidData><Resp errCode=\"0\" errInfo=\"Success\" fCount=\"1\" fType=\"0\" nmPoints=\"44\" qScore=\"65\" /><DeviceInfo dpId=\"MANTRA.MSIPL\" rdsId=\"MANTRA.WIN.001\" rdsVer=\"1.0.2\" mi=\"MFS100\" mc=\"MIIEFzCCAv+gAwIBAgIEAp9jADANBgkqhkiG9w0BAQsFADCB6TEqMCgGA1UEAxMhRFMgTWFudHJhIFNvZnRlY2ggSW5kaWEgUHZ0IEx0ZCA1MU0wSwYDVQQzE0RCIDIwMyBTaGFwYXRoIEhleGEgb3Bwb3NpdGUgR3VqYXJhdCBIaWdoIENvdXJ0IFMgRyBIaWdod2F5IEFobWVkYWJhZDESMBAGA1UECRMJQWhtZWRhYmFkMRAwDgYDVQQIEwdHdWphcmF0MRIwEAYDVQQLEwlUZWNobmljYWwxJTAjBgNVBAoTHE1hbnRyYSBTb2Z0ZWNoIEluZGlhIFB2dCBMdGQxCzAJBgNVBAYTAklOMB4XDTIwMDcwOTEyMzM0NloXDTIwMDgwODEyNDg0NFowgbAxJTAjBgNVBAMTHE1hbnRyYSBTb2Z0ZWNoIEluZGlhIFB2dCBMdGQxHjAcBgNVBAsTFUJpb21ldHJpYyBNYW51ZmFjdHVyZTEOMAwGA1UEChMFTVNJUEwxEjAQBgNVBAcTCUFITUVEQUJBRDEQMA4GA1UECBMHR1VKQVJBVDELMAkGA1UEBhMCSU4xJDAiBgkqhkiG9w0BCQEWFXN1cHBvcnRAbWFudHJhdGVjLmNvbTCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBAKbm5cGQGr7+gqISGUMn7TKy7+6RW4jNyZNX0RXLTpXP/JrMoS99d5LBtjmdg3ew6WeMUpRqg6BZnVF48rMhFkoZ/NU8KW5UDmV+qLuy7JcmjfLxi9KqPj1Prkviwo6MDwgy64GX8PKYL9hJZlUM8R8snY53MgfoJnJRgmpm1N4WZYGkS0UNdqrVNXzLsmYoYjkjPeKjUOjy//LcE58bpwRkZevG35RZ8BgwMZ5CwRk4s7eRR+woHSHwsO+kurcIr+vuTq/bLBhiOxtc9IVSL4QR/P7EKdl96pCjAfRcVsLgOmM/huY1qDSCaVNSWMe6y9MetrxgR8oT+ZEZH0njVwUCAwEAATANBgkqhkiG9w0BAQsFAAOCAQEAM3oNYCHJVf1VDozGeV6qUOS2x21cPkEvJimUKtbq/icdJsIltUYLZX6Sr30DIsx6BfK3COXs+tN31r6t4rzVQ/1sNwcXoHzYa+Khe7eddd0QgJN4X9PsjA/G+D7ZnXdemH22BmzGDLGNlFBWYJnUX9AQuGqBZ+bXYS9E83y+WwqLDxLhe6nxg5tYcheb/zybUTfYtIj09LP/X98yyPzqF64cCkSEm5sPG+vcBLLqokLJuyVm6MYznOIK89W7W1X1CvgHIeglclAZIfq+NYKAWLDx5pebK8rbFhuyeQl4xFBos0lXlcHO/c+kX7bp6WG/8d+cYXZVgiCpvT6OE3PaeQ==\" dc=\"39b60c86-c709-4f70-b8fa-d1a32443035d\"><additional_info><Param name=\"srno\" value=\"3231104\" /><Param name=\"sysid\" value=\"6EBFEBF08113CEFFBFF0\" /><Param name=\"ts\" value=\"2020-07-11T15:49:46+05:30\" /></additional_info></DeviceInfo><Skey ci=\"20221021\">Gts8WKf2W9YHxKyBUWZHbwksqFszsWdbZzICpNWWdcscAXQToot5PQ7fXoSETv1kAQJQGeqKGQsqGtNaiss+KbNGlhGjCjJM/ylv3OTmStYRnTcUNnp0UM1mwUBZX6y3yXYSOz5+zKlLBTlPVi3DF5StuWqWo366F7V8+NlPJZyeKLhEFnyDd4eCBasHUTLHu1xb4XpXqSf7DL4+31MzNSaZ/raEkoM62O9T+h3ojfa/pSSBofNswOhkZFkH/Hmqe7tHCRufmZLovI367i7fpDJpbxG4IaXGL8+u7+3NhpQZ3+OVraDcpRlJX5HqEucayOOUVLnM+Vc7+hvntx4c4A==</Skey><Hmac>9iJsbyi8nmad4puqPbg+MAAIqWISUNsmu+uQ9/YdzUd9zskyjHfC9DqjxQ28alvE</Hmac><Data type=\"P\">MjAyMC0wNy0xMVQxNTo0OTo0NvFnffD+dzA14+AdSYk5/YCp8qemMMYpNOUIcrFIhJkHyga+Jm7mUyJo1GjpwUoiF/xJNh0eX+mbCbgP9fyl5ZSIQPG7tAjaOamkflZbrL2FhUHQqEqeCW8wbsp71j4wsTrddeu6NIvBHCbk5sfnPaF49wex6oIrmlZJvjldJMxCzN9gOcyMe3WydSaDyuNodbgfjlf+LzUYicgbFiHPhSk7rj5sI3bwaYhxw/nQodyib2dokc2ONrTv5t5Lo9+7jb1LgO44IX4VWKfkaEeeVeugzg6FMratLvIWPGIoO+4l4KYSL13BFJcJggB4hdPh+5XhvTbhOzFcj5bxhJ9jSmmFQ1eUrOagED0PQQCnfwLbsuwOgTwm6DjXtXxIOOG9DnVOib2kVLwXflQl/bV/2E/EXbL6Gb3BNg5GSZrF/9LzyTQ0wxo4gV2VwXMq4qgMOAv55Lv+PJmXmW8lIaXOKwKNYtVLivGEUZRbTJm1zA8rA4T2trDEq5oITZakgi5qQGpZn6lNzfHJUBkmSCmYKRAW9T3OeHWAGhPmsTVmBx8bn67pE6zibnd3+xz2x6L7FmTsOAREvsYBkAw0Odx1aBhc2+0u/EZPkMW2RNK4fhY35fDOhq0SPpopdNLU1zJHqxzZIQaICSdQ5VqbLw6rhLN+33QLu1dLXiliQxPtrd1YOjpkA2eGuLeSoARGLgvZQUVBq1ThjmikcSPygcqtYkqd+uLck6pj221Z0/vSXcnyUPqselVEf9GHep6yC2yfY1uMd1wazx9yvfXqAKEJrWn9jZgHdivJfMI5DJXCJTPKpe7bPF49cYHo2kra0s460SWR1gvCWNKd2uPdM3K0sck17LhztkKFJaixajIUP7mS64FZCmx75X11+5CwT2uwNLPFSHoOWttCdzErVGQ4CR7tqn31RjGs25giKYZvTtiiQTWqou765M8bqU7xm0plDxFHv61LOFaitGXhUbw2LE2b/4q4ib56hg==</Data></PidData>";
		captureResponse(xml);
	}

}
