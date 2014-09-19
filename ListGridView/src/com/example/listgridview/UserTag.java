package com.example.listgridview;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class UserTag implements Parcelable {

	private String id;
	private String name;
	private String userId;
	private String nickName;
	private String image;
	private String tagname;
	private String status;
	private String followed;
	private String peopleNumbers;
	private List<String> subjects=new ArrayList<String>();
	
	public static final Parcelable.Creator<UserTag> CREATOR = new Parcelable.Creator<UserTag>() {  
		  
        @Override  
        public UserTag createFromParcel(Parcel source) {  
            return new UserTag(source);  
        }  
  
        @Override  
        public UserTag[] newArray(int size) {  
            return new UserTag[size];  
        }  
    };  
	
    public UserTag() {
	}
    
	public UserTag(Parcel source) {
		// TODO Auto-generated constructor stub
		readFromParcel(source);
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(id);  
        dest.writeString(name);
        dest.writeString(userId);
        dest.writeString(nickName);
        dest.writeString(image);
        dest.writeString(status);
        dest.writeString(tagname);
        dest.writeString(followed);
        dest.writeString(peopleNumbers);
        dest.writeList(subjects);
	}
	
	public void readFromParcel(Parcel source) {
		id = source.readString();  
		name = source.readString();
		userId = source.readString();
		nickName = source.readString();
		image = source.readString();
		status = source.readString();
		tagname=source .readString();
		followed=source .readString();
		peopleNumbers=source .readString();
		
		subjects=new ArrayList<String>();
		source.readList(subjects, getClass().getClassLoader());
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public String getTagname() {
		return tagname;
	}

	public void setTagname(String tagname) {
		this.tagname = tagname;
	}
	public String getFollowed() {
		return followed;
	}

	public void setFollowed(String followed) {
		this.followed = followed;
	}

	public String getPeopleNumbers() {
		return peopleNumbers;
	}

	public void setPeopleNumbers(String peopleNumbers) {
		this.peopleNumbers = peopleNumbers;
	}

	public List<String> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<String> subjects) {
		this.subjects = subjects;
	}
}
