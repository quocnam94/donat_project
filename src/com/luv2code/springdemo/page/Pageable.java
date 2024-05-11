package com.luv2code.springdemo.page;

	public class Pageable {
	    private int page; 
	    private int size; 
	    
		public Pageable() {
		}
		public Pageable(int page, int size) {
			super();
			this.page = page;
			this.size = size;
		}
		
		public int getPage() {
			return page;
		}
		public void setPage(int page) {
			this.page = page;
		}
		public int getSize() {
			return size;
		}
		public void setSize(int size) {
			this.size = size;
		}
	}
