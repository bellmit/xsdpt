package com.nju.sdpt.service;

import com.nju.sdpt.model.NpForDzfyServicePortType;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;

/**
 * This class was generated by the JAX-WS RI. JAX-WS RI 2.1.3-hudson-390-
 * Generated source version: 2.0
 * <p>
 * An example of how this class may be used:
 * 
 * <pre>
 * npForDzfyService service = new npForDzfyService();
 * NpForDzfyServicePortType portType = service.getNpForDzfyServiceHttpPort();
 * portType.getWslaList(...);
 * </pre>
 * 
 * </p>
 * 
 */
@WebServiceClient(name = "npForDzfyService", targetNamespace = "http://impl.sfgk.dwjk.fy.np.thunisoft.com/", wsdlLocation = "http://130.1.1.120:8080/sjjhpt/service/npForDzfyService?wsdl")
public class NpForDzfyService extends Service {

	private final static URL NPFORDZFYSERVICE_WSDL_LOCATION;


	static {
		URL url = null;
		try {
			URL baseUrl;
			baseUrl = com.nju.sdpt.service.NpForDzfyService.class
					.getResource(".");
			url = new URL(baseUrl,
					"http://130.1.1.120:8080/sjjhpt/service/npForDzfyService?wsdl");
		} catch (MalformedURLException e) {

		}
		NPFORDZFYSERVICE_WSDL_LOCATION = url;
	}

	public NpForDzfyService(URL wsdlLocation, QName serviceName) {
		super(wsdlLocation, serviceName);
	}

	public NpForDzfyService() {
		super(NPFORDZFYSERVICE_WSDL_LOCATION, new QName(
				"http://impl.sfgk.dwjk.fy.np.thunisoft.com/",
				"npForDzfyService"));
	}

	/**
	 * 
	 * @return returns NpForDzfyServicePortType
	 */
	@WebEndpoint(name = "npForDzfyServiceHttpPort")
	public NpForDzfyServicePortType getNpForDzfyServiceHttpPort() {
		return super.getPort(new QName(
				"http://impl.sfgk.dwjk.fy.np.thunisoft.com/",
				"npForDzfyServiceHttpPort"), NpForDzfyServicePortType.class);
	}

}
