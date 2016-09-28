package com.mnsn.project.pictureRelevance;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mnsn.common.BaseService;

@Service
@Transactional
public class PictureRelevanceService extends BaseService<PictureRelevance> implements PictureRelevanceServiceIntf{

}
