package com.nju.sdpt.service;

import com.nju.sdpt.model.TjxxModel;
import com.nju.sdpt.viewobject.ReportQueryVo;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;


public interface StatisticsService {

    int findLjsdajs();

    int findLjwtrc();

    int findLjsdrc();

    double findSdcgl();

    LinkedList<Integer> findYysdrws(Date date);

    LinkedList<Integer> findSdzrws(Date date);

    LinkedList<Integer> findWcsdrws(Date date);

    List<TjxxModel> findSdfsfb();

    void updateSdfsfb();

    void updateSENDSTATE();


    List<TjxxModel> getSjfypm(Date date);

    List<Integer> findSlrs();

    List<Integer> findCdrs();

    List<String> findSdcglList();


    List<TjxxModel> getSjfypmSd(Date date);

    int findLjsdAjcgs();

    void scheduledUpdateEmsCount(ReportQueryVo queryVo);

    List<TjxxModel> getSjfypmCgl(Date date);

}
