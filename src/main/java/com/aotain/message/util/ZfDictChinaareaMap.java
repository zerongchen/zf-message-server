package com.aotain.message.util;

import com.aotain.message.dao.ZfDictChinaareaDao;
import com.aotain.message.entity.ZfDictChinaareaModel;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Repository
public class ZfDictChinaareaMap {
    /**
     * 写日志
     */
    private static Logger LOG = LoggerFactory.getLogger(ZfDictChinaareaMap.class);

    @Autowired
    private ZfDictChinaareaDao zfDictChinaareaDao;

    private static Map<String,ZfDictChinaareaModel> areaMap = Maps.newHashMap();

    @PostConstruct
    public void initAreaMap(){
        List<ZfDictChinaareaModel> model = zfDictChinaareaDao.get_zf_dict_chinaarea();
        for(ZfDictChinaareaModel m:model){
            areaMap.put(m.getAreaShort(),m);
        }
    }

    public static String getAreaId(String DeploySiteName){
        try {
            if(DeploySiteName.length()>0){
                String b = DeploySiteName.substring(0,1);
                if("M".equalsIgnoreCase(b)||"P".equalsIgnoreCase(b)){
                    String key = DeploySiteName.substring(2);
                    if(areaMap.containsKey(key)){
                        return areaMap.get(key).getAreaCode();
                    }
                }else if("I".equalsIgnoreCase(b)){
                    String[] arr = DeploySiteName.split("-");
                    return arr[arr.length-1];
                }else{
                    return "";
                }
            }
        } catch (Exception e) {
            LOG.error("get areaCode error ",e);
        }
        return "";
    }

    public static String getProbeType(String DeploySiteName){
        try {
            if(DeploySiteName.length()>0){
                String b = DeploySiteName.substring(0,1);
                if("M".equalsIgnoreCase(b)||"P".equalsIgnoreCase(b)){
                    return "0";
                }else if("I".equalsIgnoreCase(b)){
                    return "1";
                }else{
                    return "0";
                }
            }
        } catch (Exception e) {
            LOG.error("get probe type error ",e);
        }
        return "";
    }

}
