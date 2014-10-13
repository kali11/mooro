package com.lms.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
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
        Set<Category> categories = new HashSet<>();
        for (String categoryId : categoryIds) {
            Category category = categoryDao.find(Long.parseLong(categoryId));
            categories.add(category);
        }
        course.setCategories(categories);
        this.save(course);
    }

    @Override
    public Course get(Long id) {
        return courseDao.find(id);
    }

    @Override
    public void remove(Long id) {
        courseDao.remove(courseDao.find(id));
    }

    @Override
    public List<String> getCourseCategoriesList(Course course) {
        return Lists.transform(ImmutableList.copyOf(course.getCategories()),
                new Function<Category, String>() {
                    @Override
                    public String apply(Category arg0) {
                        return arg0.getId().toString();
                    }
                });
    }
}
