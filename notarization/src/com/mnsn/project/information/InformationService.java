package com.mnsn.project.information;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mnsn.common.BaseService;

@Service
@Transactional
public class InformationService extends BaseService<Information> implements InformationServiceIntf{

}
