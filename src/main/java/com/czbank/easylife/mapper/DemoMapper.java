package com.czbank.easylife.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import com.czbank.easylife.model.Demo;

import java.util.List;

@Repository
@Mapper
public interface DemoMapper {
    @Select("SELECT * FROM demo WHERE demo_id = #{demoId};")
    public Demo findDemoById(@Param("demoId") String demoId);

    @Select("SELECT * FROM demo;")
    public List<Demo> findAll();

}
