package com.aotain.message.dao;


import com.aotain.common.config.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

@MyBatisDao
public interface MonitorMessageDao {

    @Insert({"insert into zf_v2_monitor_radius_pcap_detail(stat_time,server_ip,capturepacketnum,validpacketnum,invalidpacketnum,sendnum,sendsuccessnum,sendfailednum,create_time)" +
        "values(#{stat_time},#{server_ip},#{capturepacketnum},#{validpacketnum},#{invalidpacketnum},#{sendnum},#{sendsuccessnum},#{sendfailednum}," +
        "#{create_time,jdbcType=TIMESTAMP})"})
   int add_zf_v2_monitor_radius_pcap_detail(Map<String,Object> map);


    @Insert({"insert into zf_v2_monitor_radius_relay_detail(stat_time,src_ip,dst_ip,receivednum,parsesuccessnum,parsefailednum,sendnum,sendsuccessnum,sendfailednum,create_time)" +
            "values(#{stat_time},#{src_ip},#{dst_ip},#{receivednum},#{parsesuccessnum},#{parsefailednum},#{sendnum},#{sendsuccessnum},#{sendfailednum}," +
            "#{create_time,jdbcType=TIMESTAMP})"})
   int add_zf_v2_monitor_radius_relay_detail(Map<String,Object> map);


    @Insert({"insert into zf_v2_monitor_radius_policy_detail(stat_time,src_ip,dst_ip,sendnum,sendsuccessnum,sendfailednum,create_time)" +
            "values(#{stat_time},#{src_ip},#{dst_ip},#{sendnum},#{sendsuccessnum},#{sendfailednum}," +
            "#{create_time,jdbcType=TIMESTAMP})"})
   int add_zf_v2_monitor_radius_policy_detail(Map<String,Object> map);


    @Insert({"insert into zf_v2_monitor_socket_received_detail(stat_time,policy_ip,dpi_ip,packetsubtype,receivednum" +
            ",validrecordernum,invalidrecordernum,writerkafkanum,probe_type,area_id,software_provider,create_time) " +
            "values(#{stat_time},#{policy_ip},#{dpi_ip},#{packetsubtype},#{receivednum}," +
            "#{validrecordernum},#{invalidrecordernum},#{writerkafkanum},#{probe_type},#{area_id},#{software_provider}," +
            "#{create_time,jdbcType=TIMESTAMP})"})
    int add_zf_v2_monitor_socket_received_detail(Map<String,Object> map);


    @Insert({"insert into zf_v2_monitor_createfile_detail(file_name,server_ip,file_type,file_time,file_size" +
            ",file_record,create_time) " +
            "values(#{file_name},#{server_ip},#{file_type},#{file_time},#{file_size}," +
            "#{file_record}," +
            "#{create_time,jdbcType=TIMESTAMP})"})
    int add_zf_v2_monitor_createfile_detail(Map<String,Object> map);

    @Insert({"replace into zf_v2_monitor_receivedfile_detail(file_name,server_ip,dpi_ip,file_type,filecreate_time" +
            ",filereceived_time,file_size,probe_type,area_id,software_provider,create_time) " +
            "values(#{file_name},#{server_ip},#{dpi_ip},#{file_type},#{filecreate_time}," +
            "#{filereceived_time},#{file_size},#{probe_type},#{area_id},#{software_provider}," +
            "#{create_time,jdbcType=TIMESTAMP})"})
    int add_zf_v2_monitor_receivedfile_detail(Map<String,Object> map);

    @Select({"select count(1) from zf_v2_monitor_receivedfile_detail where file_name=#{file_name}"})
    int select_zf_v2_monitor_receivedfile_detail(Map<String,Object> map);

    @Insert({"insert into zf_v2_monitor_uploaddfile_detail(file_name,server_ip,received_ip,file_type,fileupload_time" +
            ",file_size,probe_type,area_id,software_provider,create_time) " +
            "values(#{file_name},#{server_ip},#{received_ip},#{file_type},#{fileupload_time}," +
            "#{file_size},#{probe_type,jdbcType=TINYINT},#{area_id},#{software_provider}," +
            "#{create_time,jdbcType=TIMESTAMP})"})
    int add_zf_v2_monitor_uploaddfile_detail(Map<String,Object> map);

    @Select({"select count(1) from zf_v2_monitor_uploaddfile_detail where file_name=#{file_name}"})
    int select_zf_v2_monitor_uploaddfile_detail(Map<String,Object> map);

    @Insert({"insert into zf_v2_monitor_onlineuser_detail(stat_time,server_ip,stat_type,onlineusernum,create_time)"+
            "values(#{stat_time},#{server_ip},#{stat_type},#{onlineusernum}," +
            "#{create_time,jdbcType=TIMESTAMP})"})
    int add_zf_v2_monitor_onlineuser_detail(Map<String,Object> map);

}
