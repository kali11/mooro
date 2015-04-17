package com.lms.model.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "elements")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "discriminator", discriminatorType = DiscriminatorType.STRING)
public class Element implements Serializable {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "main_seq")
    @SequenceGenerator(name = "main_seq", sequenceName = "hibernate_sequence", allocationSize = 1)
    @Column(name = "id", nullable = false, unique = true)
    private Long Id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "order_seq", nullable = false)
    private Long orderSeq;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "lesson", nullable = false)
    private Lesson lesson;

    public Element() {
    }

    public Element(Element element) {
        Id = element.getId();
        title = element.getTitle();
        orderSeq = element.getOrderSeq();
        active = element.getActive();
        lesson = element.getLesson();
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getOrderSeq() {
        return orderSeq;
    }

    public void setOrderSeq(Long orderSeq) {
        this.orderSeq = orderSeq;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

}
