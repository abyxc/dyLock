package com.mnsn.project.uploadMain;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mnsn.common.BaseService;

@Service
@Transactional
public class UploadMainService extends BaseService<UploadMain> implements UploadMainServiceIntf{

}
