package com.hexudong.cms.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hexudong.cms.dao.ArticleMapper;
import com.hexudong.cms.dao.CollectMapper;
import com.hexudong.cms.exception.CMSRuntimeException;
import com.hexudong.cms.pojo.Article;
import com.hexudong.cms.pojo.Collect;
import com.hexudong.cms.service.CollectService;
import com.hexudong.utils.StringUtil;

@Service
public class CollectServiceImpl implements CollectService {

	@Resource
	private CollectMapper collectMapper;
	@Autowired
	private ArticleMapper articleMapper;
	@Override
	public int add(Collect collect) throws CMSRuntimeException {
		//判断开头地址http是否符合
		if(StringUtil.isHttpUrl(collect.getUrl())) {
			return collectMapper.add(collect);
		}else {
			throw new CMSRuntimeException("URL不合法重新进行添加");
		}
		
	}
	@Override
	public PageInfo<Collect> selectCellects(Integer id, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		//根据用户id查所有收藏
		List<Collect> list = collectMapper.selectCellects(id);
		//根据文章查找查找文章ID
		list.forEach(a->{
			Article cid =	articleMapper.selectctId(a.getText());
			a.setCid(cid.getId());
		});
		return new PageInfo<Collect>(list);
	}
	@Override
	public void delete(Integer id) {
		collectMapper.delete(id);
		
	}
	@Override
	public List<Collect> select() {
		// TODO Auto-generated method stub
		return collectMapper.select();
	}
	@Override
	public Collect selectText(String text,Integer integer) {
		// TODO Auto-generated method stub
		return collectMapper.selectText(text,integer);
	}

	
}
