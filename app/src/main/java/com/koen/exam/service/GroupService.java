package com.koen.exam.service;

public interface GroupService {
    void findByName(String name, String authToken);
    void joinInGroup(String name, String authToken);
    void getMyGroup(String authToken);
}
