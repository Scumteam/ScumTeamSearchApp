package com.scumteam.searchapp;

public class Note {
 
    private String id;
    private String name;
    private String content;
    private String fileUrl;
 
    Note(String noteId, String noteName, String noteContent, String noteFileUrl) {
        id = noteId;
        name = noteName;
        content = noteContent;
        fileUrl = noteFileUrl;
 
    }
 
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    
    public void setFileUrl(String fileUrl) {
    	this.fileUrl = fileUrl;
    }
    
    public String getFileUrl() {
    	return fileUrl;
    }
    
    @Override
    public String toString() {
        return this.getName();
    }
 
}