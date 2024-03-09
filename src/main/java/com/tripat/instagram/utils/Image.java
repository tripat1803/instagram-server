package com.tripat.instagram.utils;

import jakarta.persistence.Embeddable;

@Embeddable
public class Image {
    private String imageId;
    private String imageUrl;

    public Image(){
    }
    public Image(String imageId, String imageUrl){
        this.imageId = imageId;
        this.imageUrl = imageUrl;
    }

    public void setImageId(String imageId){
        this.imageId = imageId;
    }
    public void setImageUrl(String imageUrl){
        this.imageUrl = imageUrl;
    }
    public String getImageId(){
        return this.imageId;
    }
    public String getImageUrl(){
        return this.imageUrl;
    }
}
