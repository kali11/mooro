package com.lms.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("audio")
public class ElementAudio extends Element implements Serializable {

    public ElementAudio() {
    }

    public ElementAudio(Element element) {
        super(element);
    }

    @Column(name = "description")
    private String description;

    @OneToOne
    @JoinColumn(name = "file")
    private File file;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

}
