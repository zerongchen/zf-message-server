package com.aotain.message.dao;

import com.aotain.common.config.annotation.MyBatisDao;
import com.aotain.message.entity.ZfDictChinaareaModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@MyBatisDao
public interface ZfDictChinaareaDao {

    @Select({"select area_code,area_short from zf_dict_chinaarea"})
    List<ZfDictChinaareaModel> get_zf_dict_chinaarea();

}
