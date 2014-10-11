package com.lms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lms.model.dao.CategoryDao;
import com.lms.model.dao.CourseDao;
import com.lms.model.entity.Category;
import com.lms.model.entity.Course;
import com.lms.service.CourseService;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public List<Course> getAll() {
        return courseDao.findAll();
    }

    @Override
    public void save(Course course) {
        courseDao.save(course);
    }

    @Override
    public void save(Course course, List<String> categoryIds) {
        /*
         * List<Long> categs = Lists.transform(categoryIds, new Function<String, Long>() {
         * 
         * @Override
         * public Long apply(String id) {
         * return Long.parseLong(id);
         * }
         * });
         * Category[] categories = categoryDao.find(categs.toArray(new Long[] {}));
         * course.setCategories(Sets.newHashSet(categories));
         */
        for (String categoryId : categoryIds) {
            Category category = categoryDao.find(Long.parseLong(categoryId));
            course.getCategories().add(category);
        }
        this.save(course);
    }
}
