package com.nology.SpringAPIHalfwayChallenge.entity;

import java.util.ArrayList;
import java.util.List;

//resource means type of data - in this case, it's the "course module"
public class CourseModule {
    private int id;
    private String title;
    private List<String> contentList = new ArrayList<>();
    private String projectDeliverable;

    public CourseModule(int id, String title, String[] contentArr, String projectDeliverable) {
        this.id = id;
        this.title = title;
        for(String content : contentArr) this.contentList.add(content);
        this.projectDeliverable = projectDeliverable;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getProjectDeliverable() {
        return projectDeliverable;
    }

    public List<String> getContentList() {
        return contentList;
    }
}
