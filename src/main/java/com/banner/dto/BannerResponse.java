package com.banner.dto;


import java.util.List;
import com.banner.bean.Banner;



public class BannerResponse {
    private List<Banner> ActiveBanners;
    private List<Banner> inActiveBanners;
    
	public List<Banner> getActiveBanners() {
		return ActiveBanners;
	}
	public void setActiveBanners(List<Banner> activeBanners) {
		this.ActiveBanners = activeBanners;
	}
	public List<Banner> getInActiveBanners() {
		return inActiveBanners;
	}
	public void setInActiveBanners(List<Banner> inActiveBanners) {
		this.inActiveBanners = inActiveBanners;
	}

   }
