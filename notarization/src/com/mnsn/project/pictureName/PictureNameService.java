package com.mnsn.project.pictureName;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mnsn.common.BaseService;

@Service
@Transactional
public class PictureNameService extends BaseService<PictureName> implements PictureNameServiceIntf{

}
