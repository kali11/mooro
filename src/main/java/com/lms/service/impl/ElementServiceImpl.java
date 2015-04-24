package com.lms.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Field;
import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import com.lms.model.dao.ElementDao;
import com.lms.model.dao.FileDao;
import com.lms.model.dao.LessonDao;
import com.lms.model.entity.Element;
import com.lms.model.entity.ElementAudio;
import com.lms.model.entity.ElementText;
import com.lms.model.entity.ElementVideo;
import com.lms.model.entity.File;
import com.lms.service.ElementService;

@Service
@Transactional
public class ElementServiceImpl implements ElementService {

    @Autowired
    private ElementDao elementDao;

    @Autowired
    private LessonDao lessonDao;

    @Autowired
    private FileDao fileDao;

    @Autowired
    private Environment env;

    @Override
    public Element get(Long id) {
        return elementDao.find(id);
    }

    @Override
    public void save(Element element, String elementType, HttpServletRequest request) {
        switch (elementType) {
        case "text":
            saveText(element, request);
            break;
        case "video":
            saveVideo(element, request);
            break;
        case "audio":
            saveAudio(element, request);
            break;
        }
    }

    @Override
    public void delete(Element element) {
        element.getLesson().getElements().remove(element);
        lessonDao.save(element.getLesson());
        elementDao.remove(element);
    }

    private void saveText(Element element, HttpServletRequest request) {
        ElementText elementText = new ElementText(element);
        elementText.setText(request.getParameter("text"));
        saveElement(elementText);
    }

    private void saveVideo(Element element, HttpServletRequest request) {
        ElementVideo elementVideo = new ElementVideo(element);
        elementVideo.setDescription(request.getParameter("description"));
        String src = request.getParameter("src");
        src = src.replace("watch?v=", "embed/");
        elementVideo.setSrc(src);
        saveElement(elementVideo);
    }

    private void saveAudio(Element element, HttpServletRequest request) {
        ElementAudio elementAudio = new ElementAudio(element);
        elementAudio.setDescription(request.getParameter("description"));
        File file = fileDao.find(Long.valueOf(request.getParameter("fileId")));
        elementAudio.setFile(file);
        saveElement(elementAudio);
    }

    private Long saveElement(Element element) {
        Search search = new Search(Element.class);
        search.addFilter(Filter.equal("lesson", element.getLesson()));
        search.addField("orderSeq", Field.OP_MAX);
        Long higherOrder = (Long) elementDao.searchUnique(search);
        element.setOrderSeq(higherOrder != null ? ++higherOrder : 1);
        elementDao.save(element);
        return element.getId();
    }

}
