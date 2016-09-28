package com.mnsn.project.templet;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mnsn.common.BaseService;

@Service
@Transactional
public class TempletService extends BaseService<Templet> implements TempletServiceIntf{

}
