package com.lms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lms.model.dao.ModuleDao;
import com.lms.model.entity.Module;
import com.lms.service.ModuleService;

@Service
@Transactional
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    private ModuleDao moduleDao;

    @Override
    public void save(Module module) {
        moduleDao.save(module);
    }

    @Override
    public Module get(Long id) {
        return moduleDao.find(id);
    }

}
