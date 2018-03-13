package com.example.springbootmybatismapper.dao;

import com.example.springbootmybatismapper.domain.LearnResource;
import com.example.springbootmybatismapper.util.MyMapper;

import java.util.List;
import java.util.Map;

public interface LearnResourceMapper extends MyMapper<LearnResource> {
    List<LearnResource> queryLearnResouceList(Map<String,Object> map);
}