package com.example.springbootmybatismapper.service;


import com.example.springbootmybatismapper.domain.LearnResource;
import com.example.springbootmybatismapper.util.Page;

import java.util.List;

/**
 * Created by
 */

public interface LearnService  extends IService<LearnResource>{
    public List<LearnResource> queryLearnResouceList(Page<LearnResource> page);
    public void deleteBatch(Long[] ids);
}
