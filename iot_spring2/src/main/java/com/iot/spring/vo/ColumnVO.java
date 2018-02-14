package com.iot.spring.vo;

public class ColumnVO {
	private String cName;
	private String cDefault;
	private String nullable;
	private String dType;
	private int mLength;
	private String cType;
	private String cKey;
	private String cComment;
	
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public String getcDefault() {
		return cDefault;
	}
	public void setcDefault(String cDefault) {
		this.cDefault = cDefault;
	}
	public String getNullable() {
		return nullable;
	}
	public void setNullable(String nullable) {
		this.nullable = nullable;
	}
	public String getdType() {
		return dType;
	}
	public void setdType(String dType) {
		this.dType = dType;
	}
	public int getmLength() {
		return mLength;
	}
	public void setmLength(int mLength) {
		this.mLength = mLength;
	}
	public String getcType() {
		return cType;
	}
	public void setcType(String cType) {
		this.cType = cType;
	}
	public String getcKey() {
		return cKey;
	}
	public void setcKey(String cKey) {
		this.cKey = cKey;
	}
	public String getcComment() {
		return cComment;
	}
	public void setcComment(String cComment) {
		this.cComment = cComment;
	}
	
	@Override
	public String toString() {
		return "ColumnVO [cName=" + cName + ", cDefault=" + cDefault + ", nullable=" + nullable + ", dType=" + dType
				+ ", mLength=" + mLength + ", cType=" + cType + ", cKey=" + cKey + ", cComment=" + cComment + "]";
	}
}
