package com.example.listgridview;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
	private String usertag;
	private String id;
	private String nickName;
	private String image;
	private String sex;
	private String birthday;
	private String city;
	private List<UserTag> userTags=new ArrayList<UserTag>();
	
	public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {  
		  
        @Override  
        public User createFromParcel(Parcel source) {  
            return new User(source);  
        }  
  
        @Override  
        public User[] newArray(int size) {  
            return new User[size];  
        }  
    };  	
	
    public User() {
	}
    
	public User(Parcel source) {
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
        dest.writeString(nickName);
        dest.writeString(image);
        dest.writeString(sex);
        dest.writeString(birthday);
        dest.writeString(city);
        dest.writeString(usertag);
        dest.writeList(userTags);
	}	
	
	public void readFromParcel(Parcel source) { 
		id = source.readString();
		usertag=source.readString();
		nickName = source.readString();
		image = source.readString();
		sex = source.readString();
		birthday = source.readString();
		city = source.readString();
		userTags = new ArrayList<UserTag>();
		source.readList(userTags, getClass().getClassLoader());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	public List<UserTag> getUserTags() {
		return userTags;
	}

	public void setUserTags(List<UserTag> userTags) {
		this.userTags = userTags;
	}
	public String getUsertag() {
		return usertag;
	}

	public void setUsertag(String usertag) {
		this.usertag = usertag;
	}


}
