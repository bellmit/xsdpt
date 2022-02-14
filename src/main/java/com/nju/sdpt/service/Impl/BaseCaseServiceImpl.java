package com.nju.sdpt.service.Impl;


import com.nju.sdpt.mapper.AjJbMapper;
import com.nju.sdpt.entity.AjJbEntity;
import com.nju.sdpt.model.BaseCaseModel;
import com.nju.sdpt.model.CaseContextModel;
import com.nju.sdpt.service.BaseCaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by XXT on 2019/5/24.
 */
@Service
@Slf4j
public class BaseCaseServiceImpl implements BaseCaseService {

    @Autowired
    private AjJbMapper ajJbMapper;


    public AjJbEntity convertBaseCaseModelToEntity(BaseCaseModel baseCaseModel) {
        AjJbEntity ajJbEntity = new AjJbEntity();

        if (baseCaseModel != null) {
            ajJbEntity.setAjly(baseCaseModel.getAjly());
            ajJbEntity.setAjxz(baseCaseModel.getAjxz());
            ajJbEntity.setAjmc(baseCaseModel.getAjmc());
            ajJbEntity.setLydq(baseCaseModel.getLydq());
            ajJbEntity.setSpcx(baseCaseModel.getSpcx());
            ajJbEntity.setLarq(baseCaseModel.getLarq());
            ajJbEntity.setAjxh((int) baseCaseModel.getAjxh());
            ajJbEntity.setAh(baseCaseModel.getAh());
            ajJbEntity.setLar(baseCaseModel.getLar());
            // ajJbEntity.setFybh(baseCaseModel.getFybh());
            ajJbEntity.setSpcxdz(baseCaseModel.getSpcxdz());
            ajJbEntity.setBycxdz(baseCaseModel.getBycxdz());
            ajJbEntity.setSx(baseCaseModel.getSx());
            ajJbEntity.setLydq(baseCaseModel.getLydq());
            ajJbEntity.setSfys(baseCaseModel.getSfys());
            ajJbEntity.setSycx(baseCaseModel.getSycx());
            ajJbEntity.setSffhcs(baseCaseModel.getSffhcs());
            ajJbEntity.setSfwdyj(baseCaseModel.getSfwdyj());
            ajJbEntity.setGksjg(baseCaseModel.getGksjg());
            ajJbEntity.setSfgs(baseCaseModel.getSfgs());
            ajJbEntity.setSslx(baseCaseModel.getSslx());
            ajJbEntity.setSfzdaj(baseCaseModel.getSfzdaj());
            ajJbEntity.setSfjbaj(baseCaseModel.getSfjbaj());
            ajJbEntity.setSfxess(baseCaseModel.getSfxess());
//			ajJbEntity.setSfjgyla(baseCaseModel.getSfjgyla());
//			ajJbEntity.setSfsqsfqr(baseCaseModel.getSfsqsfqr());
            // ajJbEntity.setLabdje(baseCaseModel.getLabdje());
            // ajJbEntity.setLaslf(baseCaseModel.getLaslf());
            ajJbEntity.setAjwsqk(baseCaseModel.getAjsj());
            ajJbEntity.setSwlx(baseCaseModel.getSjgb());
            // ajJbEntity.setAjzay(baseCaseModel.getLaay());
            // ajJbEntity.setLaspt(baseCaseModel.getLaspt());
            ajJbEntity.setSfzscq(baseCaseModel.getSfzscq());

            ajJbEntity.setJarq(baseCaseModel.getJarq());
            ajJbEntity.setJafs(baseCaseModel.getJafs());
            // ajJbEntity.setJazay(baseCaseModel.getJazay());
            // ajJbEntity.setJazaymc(baseCaseModel.getJazaymc());
            // ajJbEntity.setZrcdr(baseCaseModel.getZrcdr());
            // ajJbEntity.setAzdcsclyy(baseCaseModel.getAzdcsclyy());
            // ajJbEntity.setByslyy(baseCaseModel.getByslyy());
            // ajJbEntity.setBhqsyy(baseCaseModel.getBhqsyy());
            // ajJbEntity.setZjssyy(baseCaseModel.getZjssyy());
            ajJbEntity.setFhcsyy(baseCaseModel.getFhcsyy());
            ajJbEntity.setGpyy(baseCaseModel.getGpyy());
            ajJbEntity.setCbrbprq(baseCaseModel.getCbrbprq());
            ajJbEntity.setAjmc(baseCaseModel.getAjmc());
            // ajJbEntity.setBasptmc(baseCaseModel.getBasptmc());
            ajJbEntity.setSxrq(baseCaseModel.getSxrq());
            // ajJbEntity.setJabdje(baseCaseModel.getJabd());
            ajJbEntity.setBaspt(baseCaseModel.getBasptbh());
            ajJbEntity.setBafy(baseCaseModel.getBafy());
            ajJbEntity.setShwdfxpg(baseCaseModel.getShwdfxpg());
            // add
            ajJbEntity.setJyaq(baseCaseModel.getJyaq());
            ajJbEntity.setJayydm(baseCaseModel.getJayydm());
            ajJbEntity.setBz(baseCaseModel.getBz());
            ajJbEntity.setGdbz(baseCaseModel.getGdbz());
            ajJbEntity.setAjzt(baseCaseModel.getAjzt());
            ajJbEntity.setSlqk(baseCaseModel.getSlqk());

            ajJbEntity.setCbryj(baseCaseModel.getCbryj());
            ajJbEntity.setHytyj(baseCaseModel.getHytyj());
            ajJbEntity.setTzhyj(baseCaseModel.getTzhyj());
            ajJbEntity.setYzhyj(baseCaseModel.getYzhyj());
            ajJbEntity.setSwhyj(baseCaseModel.getSwhyj());
            ajJbEntity.setJayy(baseCaseModel.getJayy());
            ajJbEntity.setSpzsqrq(baseCaseModel.getSpzsqrq());
            ajJbEntity.setHythyrq(baseCaseModel.getHythyrq());
            ajJbEntity.setTzsqrq(baseCaseModel.getTzsqrq());
            ajJbEntity.setYzsqrq(baseCaseModel.getYzsqrq());
            ajJbEntity.setSwhjdrq(baseCaseModel.getSwhjdrq());
            ajJbEntity.setYzqfrq(baseCaseModel.getYzqfrq());
            ajJbEntity.setPsycy(baseCaseModel.getPsycy());
            ajJbEntity.setSffcxx(baseCaseModel.getSffcxx());
            ajJbEntity.setSfcqyscs(baseCaseModel.getSfcqyscs());
            ajJbEntity.setAjsfys(baseCaseModel.getAjsfys());
            ajJbEntity.setSptg(baseCaseModel.getSptg());
        }

        return ajJbEntity;
    }
    public CaseContextModel getCaseContextByAjxh(long ajxh) {
        CaseContextModel context = new CaseContextModel();

        AjJbEntity ajDo = ajJbMapper.findById((int)ajxh);

        if (ajDo == null) {
            return null;
        }
        context = new CaseContextModel(ajDo);

//		if (ajxh == 6010) {
        //long procInsId=0;
        //try {
        //	procInsId = workFlowServiceClient.getProcInsByAjxh(ajxh);
        //	context.setProcInsId(procInsId);
        //} catch (ThingsNotFoundException e) {

        //}
//		}

        return context;
    }

}
