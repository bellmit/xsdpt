package com.nju.sdpt.service.Impl;

import java.net.MalformedURLException;
import java.net.URL;

import javax.annotation.Resource;

import com.nju.sdpt.model.DmbModel;
import com.nju.sdpt.service.DmService;
import com.nju.sdpt.service.QzHolderService;
import com.nju.sdpt.service.SNPServiceV2;
import com.nju.sdpt.util.PropertiesUtil;
import com.nju.sdpt.util.StringUtil;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;



@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Service
public class QzHolderServiceImpl implements QzHolderService {


	@Resource
	private DmService dmService;

	private SNPServiceV2 loadedService;

	public SNPServiceV2 getQzServiceIfOpen() {

		DmbModel dmbModel = dmService.getModuleByLbbhAndDmbh2("系统缺省", "签章地址");
		if(dmbModel == null || StringUtil.isBlank(dmbModel.getDmms())){
			return defaultService();
		}
		String qzAddress = dmbModel.getDmms();
		try {
			loadedService = new SNPServiceV2(new URL(qzAddress));
			return loadedService;
		} catch (MalformedURLException e) {
		}
		return defaultService();
	}

	public void reset() {
		return;
	}

	private SNPServiceV2 getLoadedService() {
		return this.loadedService;
	}

	private SNPServiceV2 defaultService(){
		String address = PropertiesUtil.getQzAddress();
		if(address == null || StringUtil.isBlank(address)){
			return null;
		}
		try {
			return new SNPServiceV2(new URL(address));
		} catch (MalformedURLException e) {
			e.printStackTrace();
			System.out.println("不正确的签章地址");
		}
		return null;
	}

}
