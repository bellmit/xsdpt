package com.nju.sdpt.service;


import com.nju.sdpt.util.PropertiesUtil;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import java.net.MalformedURLException;
import java.net.URL;

@WebServiceClient(name = "SNPServiceV2", targetNamespace = "SnPrintAPIEntity", wsdlLocation = "http://130.1.198.16/WebService/SNPServiceV2.asmx?wsdl")
public class SNPServiceV2
        extends Service
{

    private final static URL SNPSERVICEV2_WSDL_LOCATION;

    //    private static DmService dmService;
    private static String uaddress = PropertiesUtil.getQzAddress();
    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = com.nju.sdpt.service.SNPServiceV2 .class.getResource(".");
//          url = new URL(baseUrl, "http://130.10.0.44:8001/WebService/SNPServiceV2.asmx?wsdl");
//            url = new URL(baseUrl, "http://130.1.198.16:8001/WebService/SNPServiceV2.asmx?wsdl");
            url = new URL(baseUrl,uaddress);
        } catch (MalformedURLException e) {

        }
        SNPSERVICEV2_WSDL_LOCATION = url;
    }

    public SNPServiceV2(URL wsdlLocation) {
        super(wsdlLocation, new QName("SnPrintAPIEntity", "SNPServiceV2"));
    }


    @WebEndpoint(name = "SNPServiceV2Soap")
    public SNPServiceV2Soap getSNPServiceV2Soap() {
        return super.getPort(new QName("SnPrintAPIEntity", "SNPServiceV2Soap"), SNPServiceV2Soap.class);
    }

}
