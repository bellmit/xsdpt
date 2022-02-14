package com.nju.sdpt.viewobject;

public class PayCode {
    private String fybh;//法院编号
    private String paycode;//缴款码

    public String getFybh() {
        return fybh;
    }

    public void setFybh(String fybh) {
        this.fybh = fybh;
    }

    public String getPaycode() {
        return paycode;
    }

    public void setPaycode(String paycode) {
        this.paycode = paycode;
    }

    public PayCode(String fybh, String paycode) {
        this.fybh = fybh;
        this.paycode = paycode;
    }
}
