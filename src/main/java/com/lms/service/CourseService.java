package com.lms.service;

import java.util.List;

import com.lms.model.entity.Course;

public interface CourseService {

    public List<Course> getAll();

    public void save(Course course);

    public void save(Course course, List<String> categoryId);

    public Course get(Long id);

    public void remove(Long id);

    public List<String> getCourseCategoriesList(Course course);
}
