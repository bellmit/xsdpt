package com.nju.sdpt.viewobject;

/**
 * 来院领取 - 上传送达回执 - VO
 */
public class LylqUploadSdhzVo {

    /**
     * 来院记录 - 唯一标识
     */
    private Integer lylqId;

    /**
     * 送达回执 - base64加密串
     */
    private String sdhzBase64Str;

    /**
     * 送达结果 0: 待送达  1：送达成功  2：送达失败  默认0)
     */
    private Integer sdjg;

    /**
     *  图片文件名
     */
    private String imageName;

    public Integer getSdjg() {
        return sdjg;
    }

    public void setSdjg(Integer sdjg) {
        this.sdjg = sdjg;
    }

    public Integer getLylqId() {
        return lylqId;
    }

    public void setLylqId(Integer lylqId) {
        this.lylqId = lylqId;
    }

    public String getSdhzBase64Str() {
        return sdhzBase64Str;
    }

    public void setSdhzBase64Str(String sdhzBase64Str) {
        this.sdhzBase64Str = sdhzBase64Str;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
