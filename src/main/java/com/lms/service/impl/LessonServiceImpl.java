package com.lms.service.impl;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Field;
import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import com.lms.model.dao.LessonDao;
import com.lms.model.dict.FileType;
import com.lms.model.entity.Lesson;
import com.lms.service.LessonService;

@Service
@Transactional
public class LessonServiceImpl implements LessonService {

    @Autowired
    private LessonDao lessonDao;

    @Autowired
    private Environment env;

    @Override
    public Long save(Lesson lesson) {
        if (lesson.getOrderSeq() == null) {
            Search search = new Search(Lesson.class);
            search.addFilter(Filter.equal("module", lesson.getModule()));
            search.addField("orderSeq", Field.OP_MAX);
            Long higherOrder = (Long) lessonDao.searchUnique(search);
            lesson.setOrderSeq(higherOrder != null ? ++higherOrder : 1);
        }
        lessonDao.save(lesson);
        Long lessonId = lesson.getId();
        createLessonDirectories(lessonId);
        return lessonId;
    }

    @Override
    public Lesson get(Long id) {
        return lessonDao.find(id);
    }

    private void createLessonDirectories(Long id) {
        File dir = new File(env.getProperty("filesPath") + id);
        if (!dir.exists()) {
            dir.mkdir();
            for (FileType type : FileType.values()) {
                new File(env.getProperty("filesPath") + id + "/" + type).mkdir();
            }
        }
    }

}
