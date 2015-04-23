package com.lms.service;

import javax.servlet.http.HttpServletRequest;

import com.lms.model.entity.Element;

public interface ElementService {

    public Element get(Long id);

    public void save(Element element, String elementType, HttpServletRequest request);

    public void delete(Element element);
}
