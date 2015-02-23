package com.lms.service.impl;

import java.util.ArrayList;
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
import com.lms.model.dao.UserDao;
import com.lms.model.entity.Category;
import com.lms.model.entity.Course;
import com.lms.model.entity.User;
import com.lms.service.CourseService;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private UserDao userDao;

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

    @Override
    public void subscribeUser(Long id, String login) {
        Course course = this.get(id);
        User user = userDao.getByLogin(login);
        user.getSubscribedCourses().add(course);
        userDao.save(user);
    }

    @Override
    public List<Course> getByCategoryId(Long id) {
        if (id == 0) {
            return this.getAll();
        }
        Category category = categoryDao.find(id);
        return new ArrayList<>(category.getCourses());
    }

    @Override
    public List<Course> getByUserlogin(String login) {
        User user = userDao.getByLogin(login);
        return new ArrayList<>(user.getSubscribedCourses());
    }
}
