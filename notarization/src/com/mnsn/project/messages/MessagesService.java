package com.mnsn.project.messages;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mnsn.common.BaseService;

@Service
@Transactional
public class MessagesService extends BaseService<Messages> implements MessagesServiceIntf{

}
