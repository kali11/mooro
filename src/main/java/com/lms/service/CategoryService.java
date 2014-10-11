package com.lms.service;

import java.util.List;

import com.lms.model.entity.Category;

public interface CategoryService {
    public List<Category> getAll();

    public void save(Category category);

    public Category get(Long id);

    public void remove(Category category);
}
