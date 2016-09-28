package com.mnsn.project.privilege;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mnsn.common.BaseService;

@Service
@Transactional
public class PrivilegeService extends BaseService<Privilege> implements PrivilegeServiceIntf{

}
