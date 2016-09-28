package com.mnsn.project.log;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mnsn.common.BaseService;

@Service
@Transactional
public class LogService  extends BaseService<Log> implements LogServiceIntf{

}
