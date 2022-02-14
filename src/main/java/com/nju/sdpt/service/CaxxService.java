package com.nju.sdpt.service;

import com.nju.sdpt.entity.PubCaxxEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface CaxxService {

    List<PubCaxxEntity> getCaxxByYysdbh(Integer yysdbh);
}
