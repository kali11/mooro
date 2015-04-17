package com.lms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Field;
import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import com.lms.model.dao.ElementDao;
import com.lms.model.entity.Element;
import com.lms.service.ElementService;

@Service
@Transactional
public class ElementServiceImpl implements ElementService {

    @Autowired
    private ElementDao elementDao;

    @Override
    public Long save(Element element) {
        Search search = new Search(Element.class);
        search.addFilter(Filter.equal("lesson", element.getLesson()));
        search.addField("orderSeq", Field.OP_MAX);
        Long higherOrder = (Long) elementDao.searchUnique(search);
        element.setOrderSeq(higherOrder != null ? ++higherOrder : 1);
        elementDao.save(element);
        return element.getId();
    }

    @Override
    public List<Element> getAll() {
        return elementDao.findAll();
    }

    @Override
    public Element get(Long id) {
        return elementDao.find(id);
    }

}
