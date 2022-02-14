package com.nju.sdpt.service;

import org.springframework.web.multipart.MultipartFile;

public interface ZjsdService {
    void uploadZjsd(MultipartFile file, Integer zjsdbh);

    String downloadZjsdWj(Integer yysdbh,Integer zjsdbh,String fybh,String wjid,String wjmc);

    Boolean uploadWj(Integer ajxh, MultipartFile file,String yhbh,Integer yysdbh,Integer zjsdbh,String fybh) ;

    void handleZjsdResponseXML();
}
