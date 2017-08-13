package com.mori.domain;

public class PageFormBean {//不同字段名的类似bean需要在同一页面的表单获取数据 所以这是个中介BEAN  自创666
	private Object firstPut;
	private Object secondPut;
	private Object thirdPut;
	private Object fourthPut;
	private Object fifthPut;
	private Object sixthPut;

	public Object getFirstPut() {
		return firstPut;
	}
	@Override
	public String toString() {
		return "PageFormBean [firstPut=" + firstPut + ", secondPut="
				+ secondPut + ", thirdPut=" + thirdPut + ", fourthPut="
				+ fourthPut + ", fifthPut=" + fifthPut + ", sixthPut="
				+ sixthPut + "]";
	}
	public void setFirstPut(Object firstPut) {
		this.firstPut = firstPut;
	}
	public Object getSecondPut() {
		return secondPut;
	}
	public void setSecondPut(Object secondPut) {
		this.secondPut = secondPut;
	}
	public Object getThirdPut() {
		return thirdPut;
	}
	public void setThirdPut(Object thirdPut) {
		this.thirdPut = thirdPut;
	}
	public Object getFourthPut() {
		return fourthPut;
	}
	public void setFourthPut(Object fourthPut) {
		this.fourthPut = fourthPut;
	}
	public Object getFifthPut() {
		return fifthPut;
	}
	public void setFifthPut(Object fitthPut) {
		this.fifthPut = fitthPut;
	}
	public Object getSixthPut() {
		return sixthPut;
	}
	public void setSixthPut(Object sixthPut) {
		this.sixthPut = sixthPut;
	}
	
	
	
}
