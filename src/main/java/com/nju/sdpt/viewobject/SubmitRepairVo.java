package com.nju.sdpt.viewobject;

import com.nju.sdpt.entity.PubYysdSsdrEntityKey;
import lombok.Data;

import java.util.List;

/**
 * @Author: Diao Lin
 * @Date: 2019/12/3 15:58
 * @Description:
 */
@Data
public class SubmitRepairVo {

    /**
     * 当事人联合主键
     */
    private List<PubYysdSsdrEntityKey> pubYysdSsdrKeyList;

    /**
     * 登陆用户名
     */
    private String yhm;

    public List<PubYysdSsdrEntityKey> getPubYysdSsdrEntityKeyList() {
        return pubYysdSsdrKeyList;
    }

    public void setPubYysdSsdrEntityKeyList(List<PubYysdSsdrEntityKey> pubYysdSsdrKeyList) {
        this.pubYysdSsdrKeyList = pubYysdSsdrKeyList;
    }

    public String getYhm() {
        return yhm;
    }

    public void setYhm(String yhm) {
        this.yhm = yhm;
    }
}
