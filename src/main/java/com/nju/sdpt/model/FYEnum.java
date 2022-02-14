package com.nju.sdpt.model;

/**
 * Created by XXT on 2019/5/10.
 */

import com.nju.sdpt.util.StringUtil;

/**
 * 法院的Enum
 *
 * @author byron
 */
public enum FYEnum {

    TJGY("天津市高级人民法院", "市高级法院", "120000 200", "130.1.1.111/tjspxt", "51", "51", "120000 200", "高院"),
    TJYZY("天津市第一中级人民法院", "第一中级法院", "120100 210", "130.2.0.110/tjspxt", "52", "52", "120000 200", "一中院"),//,有问题
    TJEZY("天津市第二中级人民法院", "第二中级法院", "120200 220", "130.3.100.36/tjspxt", "62", "62", "120000 200", "二中院"),
    TJSZY("天津市第三中级人民法院", "第三中级法院", "120300 230", "130.50.2.13/tjspxt", "5015", "5015", "120000 200", "三中院"),
    TJHS("天津海事法院", "海事法院", "960200 230", "130.4.1.196/tjspxt", "72", "72", "", "海事"),
    TJHP("天津市和平区人民法院", "和平区法院", "120101 211", "130.6.0.200/tjspxt", "53", "53", "120100 210", "和平"),
    TJNK("天津市南开区人民法院", "南开区法院", "120104 212", "130.5.0.14/tjspxt", "54", "54", "120100 210", "南开"),
    TJHX("天津市河西区人民法院", "河西区法院", "120203 222", "130.10.0.167/tjspxt", "64", "64", "120200 220", "河西"),
    TJHD("天津市河东区人民法院", "河东区法院", "120202 221", "130.9.40.13/tjspxt", "63", "63", "120200 220", "河东"),
    TJHB("天津市河北区人民法院", "河北区法院", "120105 213", "130.7.0.7/tjspxt", "55", "55", "120200 220", "河北"),
    TJHQ("天津市红桥区人民法院", "红桥区法院", "120106 214", "130.8.0.73/tjspxt", "56", "56", "120100 210", "红桥"),//有问题
    TJBH("天津市滨海新区人民法院", "滨海新区法院", "120242 22A", "130.23.0.101/tjspxt_bh", "74", "74", "120300 230", "滨海"),
    TJZM("天津自贸试验区人民法院","自贸法院","120304 234","130.16.0.18/tjspxt","5047","5047","120300 230","自贸"),
//    TJTG("天津市塘沽区人民法院", "塘沽审判区", "120204 223", "130.15.0.21", "65", "741", "120300 230"),
//    TJHG("天津市汉沽区人民法院", "汉沽审判区", "120205 224", "130.16.0.18", "66", "742", "120300 230"),
//    TJDG("天津市大港区人民法院", "大港审判区", "120206 225", "130.17.0.12", "67", "743", "120300 230"),
    TJDL("天津市东丽区人民法院", "东丽区法院", "120207 226", "130.13.0.13/tjspxt", "68", "68", "120300 230", "东丽"),
    TJJN("天津市津南区人民法院", "津南区法院", "120208 227", "130.14.0.22/tjspxt", "69", "69", "120200 220", "津南"),
    TJXQ("天津市西青区人民法院", "西青区法院", "120107 215", "130.11.1.5/tjspxt", "57", "57", "120100 210", "西青"),
    TJBC("天津市北辰区人民法院", "北辰区法院", "120108 216", "130.12.0.2/tjspxt", "58", "58", "120200 220", "北辰"),
    TJWQ("天津市武清区人民法院", "武清区法院", "120222 217", "130.19.0.12/tjspxt", "59", "59", "120100 210", "武清"),
    TJBD("天津市宝坻区人民法院", "宝坻区法院", "120224 219", "130.21.0.5/tjspxt", "61", "61", "120100 210", "宝坻"),
    TJJH("天津市静海区人民法院", "静海县法院", "120223 218", "130.20.1.8/tjspxt", "60", "60", "120200 220", "静海"),
    TJNH("天津市宁河区人民法院", "宁河县法院", "120221 228", "130.18.0.11/tjspxt", "70", "70", "120300 230", "宁河"),
    TJJX("蓟州区人民法院", "蓟州区法院", "120225 21A", "130.22.0.8/tjspxt", "73", "73", "120100 210", "蓟州"),
    TJTL("天津铁路运输法院", "铁路法院", "920103 132", "130.26.0.7/tjspxt", "24", "24", "120100 210", "铁路");
//    TEST("测试库","测试库","120000 2000","130.39.105.148","-1","-1","120000 200"),
//    FZTEST("法综测试库","法综测试库","120000 2","127.0.0.1:8080","666","666","120000 2");

    private String name;

    private String jc;

    private String fydm;

    private String fydz;

    private String fybh;//最高院法院编号

    private String bsbh;//报送编号

    private String sjfy;//上级法s院

    private String jjc;

    private FYEnum(String name, String jc, String fydm) {
        this.name = name;
        this.jc = jc;
        this.fydm = fydm;
    }

    private FYEnum(String name, String jc, String fydm, String fydz, String fybh, String bsbh) {
        this.name = name;
        this.jc = jc;
        this.fydm = fydm;
        this.fydz = fydz;
        this.fybh = fybh;
        this.bsbh = bsbh;
    }

    private FYEnum(String name, String jc, String fydm, String fydz, String fybh, String bsbh, String sjfy) {
        this.name = name;
        this.jc = jc;
        this.fydm = fydm;
        this.fydz = fydz;
        this.fybh = fybh;
        this.bsbh = bsbh;
        this.sjfy = sjfy;
    }

    FYEnum(String name, String jc, String fydm, String fydz, String fybh, String bsbh, String sjfy, String jjc) {
        this.name = name;
        this.jc = jc;
        this.fydm = fydm;
        this.fydz = fydz;
        this.fybh = fybh;
        this.bsbh = bsbh;
        this.sjfy = sjfy;
        this.jjc = jjc;
    }

    public String getJjc() {
        return jjc;
    }

    public void setJjc(String jjc) {
        this.jjc = jjc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJc() {
        return jc;
    }

    public void setJc(String jc) {
        this.jc = jc;
    }

    public String getFydm() {
        return fydm;
    }

    public void setFydm(String fydm) {
        this.fydm = fydm;
    }

    public String getFydz() {
        return fydz;
    }

    public void setFydz(String fydz) {
        this.fydz = fydz;
    }

    public String getFybh() {
        return fybh;
    }

    public void setFybh(String fybh) {
        this.fybh = fybh;
    }

    public void setBsbh(String bsbh) {
        this.bsbh = bsbh;
    }

    public String getBsbh() {
        return bsbh;
    }

    public String getSjfy() {
        return sjfy;
    }

    public void setSjfy(String sjfy) {
        this.sjfy = sjfy;
    }

    public static String getFYDMByName(String name) {
        for (FYEnum fy : FYEnum.values()) {
            if (fy.getName().equals(name))
                return fy.getFydm();
        }
        return null;
    }

    public static String getFYDMByFybh(String fybh) {
        for (FYEnum fy : FYEnum.values()) {
            if (fy.getFybh().equals(fybh))
                return fy.getFydm();
        }
        return null;
    }

    public static String getNameByFYDM(String fydm) {
        for (FYEnum fy : FYEnum.values()) {
            if (fy.getFydm().equals(fydm))
                return fy.getName();
        }
        return null;
    }

    public static FYEnum getFYByName(String fymc) {
        for (FYEnum fy : FYEnum.values()) {
            if (fymc.contains(fy.getName()))
                return fy;
        }
        return null;
    }

    public static String getBsbhByAjbs(String ajbs) {
        for (FYEnum fy : FYEnum.values()) {
            ajbs = ajbs.trim();
            if (ajbs.startsWith(fy.getBsbh()))
                return fy.getBsbh();
        }
        return "";
    }

    public static FYEnum getFyByBsbh(String bsbh) {
        for (FYEnum fy : FYEnum.values()) {
            if (fy.getBsbh().equals(bsbh))
                return fy;
            if (bsbh.equals(fy.getBsbh() + "0"))
                return fy;
        }
        return null;
    }

    /**
     * 根据案件标识获取所属法院
     *
     * @param ajbs
     * @return
     */
    public static FYEnum getFyByAJbs(String ajbs) {
        if (!StringUtil.isBlank(ajbs)) {
            ajbs = "" + Long.parseLong(ajbs);
            String prefix = ajbs.substring(0, 3);
            for (FYEnum fy : FYEnum.values()) {
                if (prefix.equals(fy.getFybh() + "0"))
                    return fy;
                else if (prefix.startsWith(fy.getBsbh()))
                    return fy;
            }
        }
        return null;
    }

    public static String getFybhByFydm(String fydm) {
        for (FYEnum fy : FYEnum.values()) {
            if (fy.getFydm().equals(fydm)) {
                return fy.getFybh();
            }
        }
        return "";
    }

    /**
     * 根据案件标识获取所属法院
     *
     * @param
     * @return
     */
    public static FYEnum getFyByFydm(String fydm) {
        if (!StringUtil.isBlank(fydm)) {
            for (FYEnum fy : FYEnum.values()) {
                if (fy.getFydm().equals(fydm.trim()))
                    return fy;
            }
        }
        return null;
    }

    /**
     * 根据法院编号获取法院代码
     *
     * @param fybh
     * @return
     */
    public static FYEnum getFyByFybh(String fybh) {
        if (!StringUtil.isBlank(fybh)) {
            for (FYEnum fy : FYEnum.values()) {
                if (fy.getFybh().equals(fybh.trim()))
                    return fy;
            }
        }
        return null;
    }

    public static String getDzByFYDM(String fydm) {
        for (FYEnum fy : FYEnum.values()) {
            if (fy.getFydm().equals(fydm))
                return fy.getFydz();
        }
        return "";
    }

    public static String getSjfyByFYDM(String fydm) {
        for (FYEnum fy : FYEnum.values()) {
            if (fy.getFydm().equals(fydm))
                return fy.getSjfy();
        }
        return "";
    }


    public static boolean isBhFybh(String fybh) {
        if (StringUtil.equals(fybh, "74")
//                || StringUtil.equals(fybh, "65")
//                || StringUtil.equals(fybh, "66")
//                || StringUtil.equals(fybh, "67")
        ) {
            return true;
        }
        return false;
    }
}