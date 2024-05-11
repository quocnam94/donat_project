package com.luv2code.springdemo.page;

import java.util.List;

public class Page<T> {
    private List<T> content; 
    private long totalElements; 
    private int totalPages; 
    private int number; 
    private int size; 
    
	public Page() {
	}
	
	public Page(List<T> content, long totalElements, int totalPages, int number, int size) {
		this.content = content;
		this.totalElements = totalElements;
		this.totalPages = totalPages;
		this.number = number;
		this.size = size;
	}

	public List<T> getContent() {
		return content;
	}
	public void setContent(List<T> content) {
		this.content = content;
	}
	public long getTotalElements() {
		return totalElements;
	}
	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}       
}