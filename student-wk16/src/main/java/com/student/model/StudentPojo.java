package com.student.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class StudentPojo {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("email")
    private String email;
    @JsonProperty("programme")
    private String programme;
    @JsonProperty("courses")
    private List<String> courses;

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("firstName")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("firstName")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty("lastName")
    public String getLastName() {
        return lastName;
    }

    @JsonProperty("lastName")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("programme")
    public String getProgramme() {
        return programme;
    }

    @JsonProperty("programme")
    public void setProgramme(String programme) {
        this.programme = programme;
    }

    @JsonProperty("courses")
    public List<String> getCourses() {
        return courses;
    }

    @JsonProperty("courses")
    public void setCourses(List<String> courses) {
        this.courses = courses;
    }
}
