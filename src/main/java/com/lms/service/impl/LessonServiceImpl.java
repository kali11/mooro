package com.lms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Field;
import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import com.lms.model.dao.LessonDao;
import com.lms.model.entity.Lesson;
import com.lms.service.LessonService;

@Service
@Transactional
public class LessonServiceImpl implements LessonService {

    @Autowired
    private LessonDao lessonDao;

    @Override
    public void save(Lesson lesson) {
        Search search = new Search(Lesson.class);
        search.addFilter(Filter.equal("module", lesson.getModule()));
        search.addField("orderSeq", Field.OP_MAX);
        Long higherOrder = (Long) lessonDao.searchUnique(search);
        lesson.setOrderSeq(higherOrder != null ? ++higherOrder : 1);
        lessonDao.save(lesson);
    }

}
