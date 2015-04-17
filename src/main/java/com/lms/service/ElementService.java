package com.lms.service;

import java.util.List;

import com.lms.model.entity.Element;

public interface ElementService {
    public Long save(Element element);

    public List<Element> getAll();

    public Element get(Long id);
}
