package com.louis.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.louis.springbootinit.common.ErrorCode;
import com.louis.springbootinit.exception.BusinessException;
import com.louis.springbootinit.exception.ThrowUtils;
import com.louis.springbootinit.mapper.HctypeMapper;
import com.louis.springbootinit.model.dto.HcTypeAddRequest;
import com.louis.springbootinit.model.dto.HcIb.IbSearchByHcNameRequest;
import com.louis.springbootinit.model.entity.Hctype;
import com.louis.springbootinit.model.vo.HcTypeInfoVO;
import com.louis.springbootinit.model.vo.HcTypeListVO;
import com.louis.springbootinit.model.vo.HctypeResListVO;
import com.louis.springbootinit.service.HctypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
* @author 35064
* @description 针对表【hctype】的数据库操作Service实现
* @createDate 2024-01-23 11:00:38
*/
@Service
public class HctypeServiceImpl extends ServiceImpl<HctypeMapper, Hctype>
    implements HctypeService {

    @Resource
    private HctypeMapper hctypeMapper;

    /**
     * 获取危化品类型信息
     * @param hc_name
     * @return
     */
    @Override
    public List<HcTypeInfoVO> getHcTypeInfo(String hc_name) {
        if(hc_name == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"参数不能空");
        }
        QueryWrapper<Hctype> hctypeQueryWrapper = new QueryWrapper<>();
        hctypeQueryWrapper.like("hc_name",hc_name);
        List<Hctype> hctypes = this.baseMapper.selectList(hctypeQueryWrapper);
        List<HcTypeInfoVO> hcTypeInfoVOS = new ArrayList<>(hctypes.size());
        hctypes.forEach(hctype -> {
            HcTypeInfoVO hcTypeInfoVO = new HcTypeInfoVO();
            hcTypeInfoVO.setHctype_id(hctype.getHctype_id());
            hcTypeInfoVO.setHc_spec(Integer.parseInt(hctype.getHc_spec()));
            hcTypeInfoVO.setHc_name(hctype.getHc_name());
            hcTypeInfoVO.setHc_unit(hctype.getHc_unit());
            hcTypeInfoVOS.add(hcTypeInfoVO);
        });
        return hcTypeInfoVOS;
    }

    /**
     * 添加危化品类型
     * @param hcTypeAddRequest
     * @return
     */
    @Override
    @Transactional
    public boolean addHcType(HcTypeAddRequest hcTypeAddRequest) {
        Hctype hctype = new Hctype();
        hctype.setHc_name(hcTypeAddRequest.getHc_name());
        hctype.setHc_enname(hcTypeAddRequest.getHc_enname());
        hctype.setHc_spec(hcTypeAddRequest.getHc_spec().toString());
        hctype.setHctype_limit(hcTypeAddRequest.getHctype_limit());
        hctype.setCas(hcTypeAddRequest.getCas());
        hctype.setProfile(hcTypeAddRequest.getProfile());
        hctype.setHc_unit(hcTypeAddRequest.getHc_unit());
        hctype.setHc_formula(hcTypeAddRequest.getHc_formula());
        boolean save = this.save(hctype);
        ThrowUtils.throwIf(!save,ErrorCode.PARAMS_ERROR,"添加失败");
        return true;
    }

    /**
     * 添加危化品类型列表
     * @param hctypeSearchRequest
     * @return
     */
    @Override
    public HctypeResListVO getHcTypeListInfo(IbSearchByHcNameRequest hctypeSearchRequest) {
        String hc_name = hctypeSearchRequest.getHc_name();
        if(hc_name == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"参数不能空");
        }
        // 分页参数获取 进行分页操作
        long limit = hctypeSearchRequest.getLimit();
        long page = hctypeSearchRequest.getPage();
        QueryWrapper<Hctype> hctypeQueryWrapper = new QueryWrapper<>();
        QueryWrapper<Hctype> hc_nameQueryWrapper = hctypeQueryWrapper.like("hc_name", hc_name);
        // 未分页的数据结果
        List<Hctype> hctypes = hctypeMapper.selectList(hc_nameQueryWrapper);
        Page<Hctype> hctypePage = new Page<>();
        hctypePage.setSize(limit);
        hctypePage.setCurrent(page);
        // 搜索分页——模糊匹配like
        Page<Hctype> hctypeResPage = hctypeMapper.selectPage(hctypePage, hc_nameQueryWrapper);
        // 获取分页结果
        List<Hctype> records = hctypeResPage.getRecords();
        List<HcTypeListVO> hcTypeInfoVOS = new ArrayList<>(records.size());
        for(Hctype hctype : records){
            HcTypeListVO hcTypeInfoVO = new HcTypeListVO();
            hcTypeInfoVO.setHctype_id(hctype.getHctype_id());
            hcTypeInfoVO.setHc_spec(Integer.parseInt(hctype.getHc_spec()));
            hcTypeInfoVO.setHc_name(hctype.getHc_name());
            hcTypeInfoVO.setHc_unit(hctype.getHc_unit());
            hcTypeInfoVO.setHc_enname(hctype.getHc_enname());
            hcTypeInfoVO.setHc_formula(hctype.getHc_formula());
            hcTypeInfoVO.setCas(hctype.getCas());
            hcTypeInfoVO.setProfile(hctype.getProfile());
            hcTypeInfoVO.setHctype_limit(hctype.getHctype_limit());
            hcTypeInfoVOS.add(hcTypeInfoVO);
        };
        HctypeResListVO hctypeResListVO = new HctypeResListVO();
        hctypeResListVO.setList(hcTypeInfoVOS);
        hctypeResListVO.setCount(hctypes.size());
        return hctypeResListVO;
    }

}




