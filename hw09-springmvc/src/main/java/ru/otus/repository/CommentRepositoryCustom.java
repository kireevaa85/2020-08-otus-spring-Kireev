package ru.otus.repository;

public interface CommentRepositoryCustom {

    void updateById(String id, String authorName, String comment);

}
