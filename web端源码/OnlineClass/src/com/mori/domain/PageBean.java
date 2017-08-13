package com.mori.domain;

import java.util.List;

public class PageBean <T>{
	private int pc;//当前页面page code
	//private int tp;//total page
	private int tr;//total record
	private int ps;//page size
	private List<T>beanList;//当前页记录
	private String url;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getTp()
	{
		int tp=tr/ps;
		return tr%ps==0?tp:tp+1;
	}
	public int getPc() {
		return pc;
	}
	public void setPc(int pc) {
		this.pc = pc;
	}
	public int getTr() {
		return tr;
	}
	public void setTr(int tr) {
		this.tr = tr;
	}
	public int getPs() {
		return ps;
	}
	public void setPs(int ps) {
		this.ps = ps;
	}
	public List<T> getBeanList() {
		return beanList;
	}
	public void setBeanList(List<T> beanList) {
		this.beanList = beanList;
	}
	@Override
	public String toString() {
		return "PageBean [pc=" + pc + ", tr=" + tr + ", ps=" + ps
				+ ", beanList=" + beanList + ", url=" + url + "]";
	}
	

}
