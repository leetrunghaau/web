package com.webmvc.chiken.model.entity;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "feedback", schema = "public", catalog = "darschqvp4f5i8")
public class FeedbackEntity {
    private int feedbackId;
    private String email;
    private String name;
    private String subject;
    private String message;

    @Id
    @Column(name = "feedback_id")
    @GeneratedValue(strategy= AUTO)
    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "subject")
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Basic
    @Column(name = "message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FeedbackEntity feedback = (FeedbackEntity) o;

        if (feedbackId != feedback.feedbackId) return false;
        if (email != null ? !email.equals(feedback.email) : feedback.email != null) return false;
        if (name != null ? !name.equals(feedback.name) : feedback.name != null) return false;
        if (subject != null ? !subject.equals(feedback.subject) : feedback.subject != null) return false;
        if (message != null ? !message.equals(feedback.message) : feedback.message != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = feedbackId;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }
}
