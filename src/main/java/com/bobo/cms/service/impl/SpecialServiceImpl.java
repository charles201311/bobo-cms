package com.bobo.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bobo.cms.dao.SpecialMapper;
import com.bobo.cms.domain.Special;
import com.bobo.cms.service.SpecialService;

@Service
public class SpecialServiceImpl implements SpecialService {
	@Resource
	private SpecialMapper specialMapper;

	@Override
	public int insert(Special special) {
		// TODO Auto-generated method stub
		return specialMapper.insert(special);
	}

	@Override
	public int update(Special special) {
		// TODO Auto-generated method stub
		return specialMapper.update(special);
	}

	@Override
	public List<Special> selects() {
		// TODO Auto-generated method stub
		return specialMapper.selects();
	}

	@Override
	public int insertSpecialArticle(Integer sid, Integer aid) {
		// TODO Auto-generated method stub
		return specialMapper.insertSpecialArticle(sid, aid);
	}

	@Override
	public Special select(Integer sid) {
		// TODO Auto-generated method stub
		return specialMapper.select(sid);
	}

}
