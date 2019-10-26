package com.bobo.cms.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bobo.cms.dao.TermMapper;
import com.bobo.cms.domain.Term;
import com.bobo.cms.service.TermService;

@Service
public class TermServiceImpl implements TermService {
	@Resource
	private TermMapper termMapper;

	@Override
	public int insert(Term term) {
		// TODO Auto-generated method stub
		return termMapper.insert(term);
	}

	@Override
	public Term selectByName(String name) {
		// TODO Auto-generated method stub
		return termMapper.selectByName(name);
	}

}
