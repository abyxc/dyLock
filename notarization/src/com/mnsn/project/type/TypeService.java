package com.mnsn.project.type;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mnsn.common.BaseService;

@Service
@Transactional
public class TypeService extends BaseService<Type> implements TypeServiceIntf{

}
