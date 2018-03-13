package com.example.springbootmybatismapper.controller;


import com.example.springbootmybatismapper.domain.LearnResource;
import com.example.springbootmybatismapper.service.LearnService;
import com.example.springbootmybatismapper.util.AjaxObject;
import com.example.springbootmybatismapper.util.Page;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("/learn")
public class LearnController  extends AbstractController{

    private Logger logger = LoggerFactory.getLogger(LearnController.class);

    @Autowired
    private LearnService learnService;
    @Autowired
    private CacheManager cacheManager;



    /**
     * 查询教程列表
     * @param page
     * @return
     */
    @RequestMapping(value = "/queryLeanList",method = RequestMethod.POST)
    @ResponseBody
    public AjaxObject queryLearnList(@RequestBody Page<LearnResource> page){
        List<LearnResource> learnList=learnService.queryLearnResouceList(page);
        PageInfo<LearnResource> pageInfo =new PageInfo<LearnResource>(learnList);
        return AjaxObject.ok().put("page", pageInfo);
    }
    /**
     * 新添教程
     * @param learn
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    @CachePut(value="learn", key="#learn.id")
    public AjaxObject addLearn(@RequestBody LearnResource learn){
        learnService.save(learn);
        logger.info("为id、key为:" + learn.getId() + "数据新增做了缓存");
        return AjaxObject.ok();
    }

    /**
     * 修改教程
     * @param learn
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public AjaxObject updateLearn(@RequestBody LearnResource learn){
        learnService.updateNotNull(learn);
        return AjaxObject.ok();
    }

    /**
     * 删除教程
     * @param ids
     */
    @RequestMapping(value="/delete",method = RequestMethod.POST)
    @ResponseBody
    public AjaxObject deleteLearn(@RequestBody Long[] ids){
        learnService.deleteBatch(ids);
        return AjaxObject.ok();
    }

    /**
     * 根据id查询教程
     * @param
     */
    @RequestMapping(value="/query",method = RequestMethod.POST)
    @ResponseBody
    @CachePut(value="learn", key="#learn.id")
    public AjaxObject queryLearn(@RequestBody LearnResource learn){
        logger.info(cacheManager.toString());
        LearnResource learnResource = learnService.selectByKey(learn.getId());
        return AjaxObject.ok().put("learn", learnResource);
    }
}