package com.qf.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.qf.entity.DiscussxinnengyuanqicheEntity;
import com.qf.entity.XinnengyuanqicheEntity;
import com.qf.service.DiscussxinnengyuanqicheService;
import com.qf.utils.MPUtil;
import com.qf.utils.PageUtils;
import com.qf.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/discussxinnengyuanqiche")
public class DiscussxinnengyuanqicheController {

    @Autowired
    private DiscussxinnengyuanqicheService discussxinnengyuanqicheService;


    /**
     * 后端列表
     *
     * @param params
     * @param discussxinnengyuanqiche
     * @return
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, DiscussxinnengyuanqicheEntity discussxinnengyuanqiche) {
        EntityWrapper<DiscussxinnengyuanqicheEntity> ew = new EntityWrapper<>();
        PageUtils pageUtils = discussxinnengyuanqicheService.selectListView(params, MPUtil.likeOrEq(ew, discussxinnengyuanqiche));
        return R.ok().put("data", pageUtils);
    }

    /**
     * 信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request) {
        DiscussxinnengyuanqicheEntity discussxinnengyuanqiche = discussxinnengyuanqicheService.selectById(id);
        return R.ok().put("data", discussxinnengyuanqiche);
    }

    /**
     * 保存
     *
     * @param discussxinnengyuanqiche
     * @return
     */
    @RequestMapping("/save")
    public R save(@RequestBody DiscussxinnengyuanqicheEntity discussxinnengyuanqiche) {


        discussxinnengyuanqicheService.insert(discussxinnengyuanqiche);
        return R.ok();
    }

    /**
     * 修改
     *
     * @param discussxinnengyuanqiche
     * @return
     */
    @RequestMapping("/update")
    public R update(@RequestBody DiscussxinnengyuanqicheEntity discussxinnengyuanqiche) {
        //正常更新
        discussxinnengyuanqicheService.updateById(discussxinnengyuanqiche);
        return R.ok();
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        discussxinnengyuanqicheService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    /**
     * 前端列表
     * @param params
     * @param discussxinnengyuanqiche
     * @param request
     * @return
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, DiscussxinnengyuanqicheEntity discussxinnengyuanqiche, HttpServletRequest request){
        //设置查询条件
        EntityWrapper<DiscussxinnengyuanqicheEntity> ew = new EntityWrapper<>();
        //调用service完成业务查询
        PageUtils pageUtils = discussxinnengyuanqicheService.selectListView(params, MPUtil.likeOrEq(ew, discussxinnengyuanqiche));
        return R.ok().put("data", pageUtils);
    }
    /**
     * 前端列表
     * @param params
     * @param discussxinnengyuanqiche
     * @param request
     * @return
     */
    @RequestMapping("/lists")
    public R lists(@RequestParam Map<String, Object> params, DiscussxinnengyuanqicheEntity discussxinnengyuanqiche, HttpServletRequest request){
        //设置查询条件
        EntityWrapper<DiscussxinnengyuanqicheEntity> ew = new EntityWrapper<>();
        //调用service完成业务查询
        ew.allEq(MPUtil.allEQMapPre(discussxinnengyuanqiche,"discussxinnengyuanqiche"));
        //查询列表数据
        List<DiscussxinnengyuanqicheEntity> discussxinnengyuanqicheEntities = discussxinnengyuanqicheService.selectListView(ew);
        return R.ok().put("data", discussxinnengyuanqicheEntities);
    }

    /**
     * 查询
     * @param discussxinnengyuanqiche
     * @param request
     * @return
     */
    @RequestMapping("/query")
    public R query(DiscussxinnengyuanqicheEntity discussxinnengyuanqiche, HttpServletRequest request){
        //设置查询条件
        EntityWrapper<DiscussxinnengyuanqicheEntity> ew = new EntityWrapper<>();
        //调用service完成业务查询
        ew.allEq(MPUtil.allEQMapPre(discussxinnengyuanqiche,"discussxinnengyuanqiche"));
        DiscussxinnengyuanqicheEntity discussxinnengyuanqiche1 = discussxinnengyuanqicheService.selectOne(ew);
        return R.ok().put("data", discussxinnengyuanqiche1);

    }

    /**
     * 前端详情
     * @param id
     * @return
     */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        DiscussxinnengyuanqicheEntity discussxinnengyuanqiche1 = discussxinnengyuanqicheService.selectById(id);
        return R.ok().put("data", discussxinnengyuanqiche1);
    }
    /**
     * 前端保存
     * @param discussxinnengyuanqiche
     * @return
     */
    @RequestMapping("/add")
    public R add(@RequestBody DiscussxinnengyuanqicheEntity discussxinnengyuanqiche, HttpServletRequest request){
        discussxinnengyuanqiche.setId(new Date().getTime());
        discussxinnengyuanqiche.setUserid((Long) request.getSession().getAttribute("userId"));
        discussxinnengyuanqicheService.insert(discussxinnengyuanqiche);
        return R.ok();
    }

}
