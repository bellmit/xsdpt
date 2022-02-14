package com.nju.sdpt.service;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPCell;
import com.nju.sdpt.model.GddcModel;
import com.nju.sdpt.model.sdfk.SdfkDataModel;
import com.nju.sdpt.viewobject.ConfirmSdfkVo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 送达反馈信息相关业务
 */
public interface SdfkService {
    /**
     * 根据预约送达编号获取送达反馈信息
     * @param yysdbh
     * @return
     */
    List<SdfkDataModel> getSdfkData(Integer yysdbh,List<Integer> ssdrbh);

    void confirmSdfk(ConfirmSdfkVo confirmSdfkVo);


    void addEmsmd(List<String> paths, List<String> fileNames,Integer yysdbh,Integer ssdrbh);

    void addLyhz(List<String> paths, List<String> fileNames, Integer yysdbh, Integer ssdrbh);

    void addSdfkb(List<String> paths, List<String> fileNames, Integer yysdbh, List<Integer> ssdrs, Integer ssdrbh) throws IOException, DocumentException;

    PdfPCell mergeCol(String s, Font font, int i, String align);

    PdfPCell mergeColRow(String s, Font font, int i, String align, int row);

    String gdxqxxdc(Integer yysdbh) throws DocumentException, IOException;
}
