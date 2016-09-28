package com.mnsn.project.uploadPicture;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mnsn.common.BaseService;

@Service
@Transactional
public class UploadPictureService extends BaseService<UploadPicture> implements UploadPictureServiceIntf{

}
