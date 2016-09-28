package com.mnsn.project.group;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mnsn.common.BaseService;

@Service
@Transactional
public class GroupService extends BaseService<Group> implements GroupServiceIntf{
	
	
}
