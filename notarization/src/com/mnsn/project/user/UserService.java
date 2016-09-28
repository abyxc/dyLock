package com.mnsn.project.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mnsn.common.BaseService;

@Service
@Transactional
public class UserService extends BaseService<User> implements UserServiceIntf{

}
