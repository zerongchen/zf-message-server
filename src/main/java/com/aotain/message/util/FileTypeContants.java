package com.aotain.message.util;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class FileTypeContants {

    private static Logger LOG = LoggerFactory.getLogger(FileTypeContants.class);

    private static Map<String, Integer> fileTypeMAP = Maps.newHashMap();

    static {

        fileTypeMAP.put("0x0100", 256); // Web类流量统计（资源在网外）
        fileTypeMAP.put("0x0101", 257); // VoIP类流量统计
        fileTypeMAP.put("0x0102", 258); // 通用类流量统计
        fileTypeMAP.put("0x0103", 259); // 访问指定应用的用户统计
        fileTypeMAP.put("0x0104", 260); // Download类分析功能模块
        fileTypeMAP.put("0x0105", 261); // WEB类流量统计（资源在网内）
        fileTypeMAP.put("0x0180", 384); // 用户偏好分析
        fileTypeMAP.put("0x0181", 385); // 非法路由检测分析
        fileTypeMAP.put("0x0182", 386); // 一拖N用户行为分析（检测结果）
        fileTypeMAP.put("0x0183", 387); // 一拖N用户行为分析（关键字段）
        fileTypeMAP.put("0x0184", 388); // Web信息推送结果上报
        fileTypeMAP.put("0x01c0", 448); // 应用层DDoS异常流量分析功能模块
        fileTypeMAP.put("0x01c1", 449); // CP/SP 资源服务器分析上报模块
        fileTypeMAP.put("0x01c2", 450); // P2P应用流量流向分析模块
        fileTypeMAP.put("0x01c3", 451); // 预留
        fileTypeMAP.put("0x01c4", 452); // 流量流向分析结果上报
        fileTypeMAP.put("0x01c6", 453); // IP地址流量TOP N流量分析

        fileTypeMAP.put("0x0200", 512); // HTTP
        fileTypeMAP.put("0x0201", 513); // RTSP
        fileTypeMAP.put("0x0202", 514); // VoIP
        fileTypeMAP.put("0x0203", 515); // FTP
        fileTypeMAP.put("0x0204", 516); // SMTP
        fileTypeMAP.put("0x0205", 517); // POP3
        fileTypeMAP.put("0x0206", 518); // IMAP
        fileTypeMAP.put("0x0207", 519); // DNS
        fileTypeMAP.put("0x0208", 520); // P2P
        fileTypeMAP.put("0x0209", 521); // Game
        fileTypeMAP.put("0x020a", 522); // IM

        fileTypeMAP.put("0x0300", 768); // HTTP GET报文关键字段上报  001:HTTP GET消息
        fileTypeMAP.put("0x03ff", 1023); // AAA记录消息
        fileTypeMAP.put("0x0301", 769); // WLAN终端类型上报
    }


    public static Integer getType(String moduleType,String fileType){
        if("0x0300".equals(moduleType)){
            if("001".equals(fileType)||"000".equals(fileType)){
                return 768;
            }else if("002".equals(fileType)){
                return 1023;
            }
        }else{
            return getType(moduleType);
        }
        return 0;
    }

    public static Integer getType(String moduleType){
        if(fileTypeMAP.containsKey(moduleType)){
            return fileTypeMAP.get(moduleType);
        }
        return 0;
    }


}
