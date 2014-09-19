package com.example.listgridview;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class Tag implements Parcelable {
	
	private String name;
	private List<UserTag> userTags = new ArrayList<UserTag>();

	public static final Parcelable.Creator<Tag> CREATOR = new Parcelable.Creator<Tag>() {  
		  
        @Override  
        public Tag createFromParcel(Parcel source) {  
            return new Tag(source);  
        }  
  
        @Override  
        public Tag[] newArray(int size) {  
            return new Tag[size];  
        }  
    };  	
	
    public Tag() {
	}
    
	public Tag(Parcel source) {
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
        dest.writeString(name);
        dest.writeList(userTags);
	}	
	
	public void readFromParcel(Parcel source) { 
		name = source.readString();
		userTags = new ArrayList<UserTag>();
		source.readList(userTags, getClass().getClassLoader());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<UserTag> getUserTags() {
		return userTags;
	}

	public void setUserTags(List<UserTag> userTags) {
		this.userTags = userTags;
	}

}
