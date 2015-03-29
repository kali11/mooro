package com.lms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Field;
import com.googlecode.genericdao.search.Search;
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

        Search search = new Search(Module.class);
        search.addField("orderSeq", Field.OP_MAX);
        Long x = (Long) moduleDao.searchUnique(search);

        // search.addFilter(Filter.custom(""))
        // Long higherOrder = moduleDao.
        System.out.println(x);
        // moduleDao.save(module);
    }

    @Override
    public Module get(Long id) {
        return moduleDao.find(id);
    }

}
