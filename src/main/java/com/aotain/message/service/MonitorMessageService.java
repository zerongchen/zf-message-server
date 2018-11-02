package com.aotain.message.service;

import com.alibaba.fastjson.JSONObject;
import com.aotain.message.dao.MonitorMessageDao;
import com.aotain.message.util.DpiAttributeUtil;
import com.aotain.message.util.FileTypeContants;
import com.aotain.message.util.ZfDictChinaareaMap;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * type=4 监控信息
 */

@Service
public class MonitorMessageService extends IMsgService{

    private static Logger logger = LoggerFactory.getLogger(MonitorMessageService.class.getName());

    @Autowired
    private MonitorMessageDao monitorMessageDao;

    @Autowired
    private ZfDictChinaareaMap zfDictChinaareaMap;

    @Override
    public void execute(String data, long createTime,String createip) {


        try {
            JSONObject object = JSONObject.parseObject(data);
            Long datatype = object.getLong("datatype");
            Long datasubtype = object.getLong("datasubtype");
            String datamessage = object.getString("datamessage");

            if (datatype == null || datasubtype == null || datamessage == null) {
                logger.error("error line={" + data + "}");
                return;
            }

            // 1=AAA监控信息
            if (datatype.intValue() == 1) {
                // 101=采集端监控数据
                if (datasubtype.intValue() == 101) {
                    JSONObject json_101 = JSONObject.parseObject(datamessage);
                    Long capturepacketnum = json_101.getLong("capturepacketnum");
                    Long invalidpacketnum = json_101.getLong("invalidpacketnum");
                    Long sendsuccessnum = json_101.getLong("sendsuccessnum");
                    Long sendfailednum = json_101.getLong("sendfailednum");
                    Map<String,Object> map = Maps.newHashMap();
                    map.put("stat_time",createTime);
                    map.put("server_ip",createip);
                    map.put("capturepacketnum",capturepacketnum);
                    map.put("validpacketnum",capturepacketnum-invalidpacketnum);
                    map.put("invalidpacketnum",invalidpacketnum);
                    map.put("sendnum",sendsuccessnum+sendfailednum);
                    map.put("sendsuccessnum",sendsuccessnum);
                    map.put("sendfailednum",sendfailednum);
                    map.put("create_time",new Date());
                   monitorMessageDao.add_zf_v2_monitor_radius_pcap_detail(map);
                    // 102=中转程序监控数据
                } else if (datasubtype.intValue() == 102) {
                    JSONObject json_102 = JSONObject.parseObject(datamessage);
                    String policyip = json_102.getString("policyip");
                    Long sendsuccessnum = json_102.getLong("sendsuccessnum");
                    Long sendfailednum = json_102.getLong("sendfailednum");
                    Long receivednum = json_102.getLong("receivednum");
                    Long parsesuccessnum = json_102.getLong("parsesuccessnum");
                    Long parsefailednum = json_102.getLong("parsefailednum");

                    Map<String,Object> map = Maps.newHashMap();
                    map.put("stat_time",createTime);
                    map.put("src_ip",createip);
                    map.put("dst_ip",policyip);
                    map.put("receivednum",receivednum);
                    map.put("parsesuccessnum",receivednum-parsefailednum);
                    map.put("parsefailednum",parsefailednum);
                    map.put("sendnum",sendsuccessnum+sendfailednum);
                    map.put("sendsuccessnum",sendsuccessnum);
                    map.put("sendfailednum",sendfailednum);
                    map.put("create_time",new Date());
                    monitorMessageDao.add_zf_v2_monitor_radius_relay_detail(map);
                    // 103=policy转发数据
                } else if (datasubtype.intValue() == 103) {
                    JSONObject json_103 = JSONObject.parseObject(datamessage);
                    String dpiip = json_103.getString("dpiip");
                    Long sendsuccessnum = json_103.getLong("sendsuccessnum");
                    Long sendfailednum = json_103.getLong("sendfailednum");

                    Map<String,Object> map = Maps.newHashMap();
                    map.put("stat_time",createTime);
                    map.put("src_ip",createip);
                    map.put("dst_ip",dpiip);
                    map.put("sendnum",sendsuccessnum+sendfailednum);
                    map.put("sendsuccessnum",sendsuccessnum);
                    map.put("sendfailednum",sendfailednum);
                    map.put("create_time",new Date());
                    monitorMessageDao.add_zf_v2_monitor_radius_policy_detail(map);
                    // 104=在线用户数数据
                }else if (datasubtype.intValue() == 104) {
                    JSONObject json_104 = JSONObject.parseObject(datamessage);
                    String stattype = json_104.getString("stattype");
                    Long onlineusernum = json_104.getLong("onlineusernum");
                    Map<String,Object> map = Maps.newHashMap();
                    map.put("stat_time",createTime);
                    map.put("server_ip",createip);
                    map.put("stat_type",stattype);
                    map.put("onlineusernum",onlineusernum);
                    map.put("create_time",new Date());
                    monitorMessageDao.add_zf_v2_monitor_onlineuser_detail(map);
                }

                // 2=Socket监控信息
            } else if (datatype.intValue() == 2) {
                // 201=policy接收记录信息
                if (datasubtype.intValue() == 201) {
                    JSONObject json_201 = JSONObject.parseObject(datamessage);
                    String dpiip = json_201.getString("dpiip");
                    Long packetsubtype = json_201.getLong("packetsubtype");

                    Long validrecordernum = json_201.getLong("validrecordernum");
                    Long invalidrecordernum = json_201.getLong("invalidrecordernum");
                    Long writerkafkanum = json_201.getLong("writerkafkanum");

                    Map<String,Object> map = Maps.newHashMap();
                    map.put("stat_time",createTime);
                    map.put("policy_ip",createip);
                    map.put("dpi_ip",dpiip);
                    map.put("packetsubtype",packetsubtype);
                    map.put("receivednum",validrecordernum+invalidrecordernum);
                    map.put("validrecordernum",validrecordernum);
                    map.put("invalidrecordernum",invalidrecordernum);
                    map.put("writerkafkanum",writerkafkanum);
                    map.put("probe_type", DpiAttributeUtil.getProbeTypeByIp(dpiip));
                    map.put("area_id",DpiAttributeUtil.getEuAreaIdByIp(dpiip));
                    map.put("software_provider",DpiAttributeUtil.getEuSoftwareProviderByIp(dpiip));
                    map.put("create_time",new Date());
                    monitorMessageDao.add_zf_v2_monitor_socket_received_detail(map);
                }

                // 3=SFTP文件信息
            } else if (datatype.intValue() == 3) {
                // 301=生成文件信息
                if (datasubtype.intValue() == 301) {

                    JSONObject json_301 = JSONObject.parseObject(datamessage);
                    Long filetype = json_301.getLong("filetype");
                    String filename = json_301.getString("filename");
                    Long filetime = json_301.getLong("filetime");
                  //  Long filesize = json_301.getLong("filesize");
                    Double filesize = json_301.getDouble("filesize");
                    Long filerecord = json_301.getLong("filerecord");
                    Map<String,Object> map = Maps.newHashMap();
                    map.put("file_name",filename);
                    map.put("server_ip",createip);
                    map.put("file_type",filetype);
                    map.put("file_time",filetime);
                    map.put("file_size",filesize.toString());
                    map.put("file_record",filerecord);
                    map.put("create_time",new Date());
                    monitorMessageDao.add_zf_v2_monitor_createfile_detail(map);
                    // 302=接收文件信息
                } else if (datasubtype.intValue() == 302) {
                    JSONObject json_302 = JSONObject.parseObject(datamessage);
                    String filename = json_302.getString("filename");
                    Long filereceivedtime = json_302.getLong("filereceivedtime");
                    Long filecreatetime = json_302.getLong("filecreatetime");
                    Long filesize = json_302.getLong("filesize");
                    String dpi_ip = json_302.getString("dpi_ip");

                    String[] f = filename.split("\\+");
                    Integer fileType = FileTypeContants.getType(f[1],f[2]);
                    String areaId = ZfDictChinaareaMap.getAreaId(f[3]);
                    String probeType=ZfDictChinaareaMap.getProbeType(f[3]);
                    String software_provider = f[4];
                    Map<String,Object> map = Maps.newHashMap();
                    map.put("file_name",filename);
                    map.put("server_ip",createip);
                    map.put("dpi_ip",dpi_ip);
                    map.put("file_type",fileType);
                    map.put("filecreate_time",filecreatetime);
                    map.put("filereceived_time",filereceivedtime);
                    map.put("file_size",filesize);
                    map.put("probe_type", probeType);
                    map.put("area_id",areaId);
                    map.put("software_provider",software_provider);
                    map.put("create_time",new Date());
                    /*int countByFileName= monitorMessageDao.select_zf_v2_monitor_receivedfile_detail(map);
                    if(countByFileName>0){
                        logger.warn("received file exists{"+filename+"}");
                        return;
                    }*/
                    monitorMessageDao.add_zf_v2_monitor_receivedfile_detail(map);
                    // 303=上报文件信息
                } else if (datasubtype.intValue() == 303) {
                    JSONObject json_303 = JSONObject.parseObject(datamessage);
                    String filename = json_303.getString("filename");
                    Long fileuploadtime = json_303.getLong("fileuploadtime");
                    Long filesize = json_303.getLong("filesize");
                    String received_ip = json_303.getString("received_ip");

                    String[] f = filename.split("\\+");
                    Integer fileType = FileTypeContants.getType(f[1],f[2]);
                    String areaId = ZfDictChinaareaMap.getAreaId(f[3]);
                    String probeType=ZfDictChinaareaMap.getProbeType(f[3]);
                    String software_provider = f[4];
                    Map<String,Object> map = Maps.newHashMap();
                    map.put("file_name",filename);
                    map.put("server_ip",createip);
                    map.put("received_ip",received_ip);
                    map.put("file_type",fileType);
                    map.put("fileupload_time",fileuploadtime);
                    map.put("file_size",filesize);
                    map.put("probe_type", probeType);
                    map.put("area_id",areaId);
                    map.put("software_provider",software_provider);
                    map.put("create_time",new Date());

                    int countByFileName= monitorMessageDao.select_zf_v2_monitor_uploaddfile_detail(map);
                    if(countByFileName>0){
                        logger.warn("upload file exists{"+filename+"}");
                        return;
                    }
                    monitorMessageDao.add_zf_v2_monitor_uploaddfile_detail(map);
                }
            }

        }catch(Exception e){
            logger.error(" execute error,",e);
        }finally{

        }
    }

    private int getReceivedfile_type(String filename) {
        try {
            String[] f = filename.split("\\+");
            String moduleType = f[1];
            if(moduleType.equals("0x0300")){
                return 1;
            }else if(moduleType.equals("0x0301")){
                return 2;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


}
