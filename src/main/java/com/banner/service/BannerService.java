package com.banner.service;

import com.banner.bean.Banner;
import com.banner.bean.Banner.BannerType;
import com.banner.dto.BannerResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BannerService {
	
    List<Banner> getAllBanners();
    
    List<Banner> findBannnersWithSort(String sort);
    
    Page<Banner>  findBannersWithPaging(int offset, int records);
    
    Page<Banner> findBannersWithPagingAndSort(int offet, int records, String sort);
    
    Page<Banner> findBannersWithPagingAndSorttitle(int offset, int records, String sort, String filter);
        
    Page<Banner> findBannersWithPagingAndSortAll(int offset, int records, String sort, String filterTitle, String filterContent, BannerType filterType,String order);
    
    BannerResponse getBanners(String title, String content, Banner.BannerType type, Pageable pageable);

    void addBanner(Banner banner);

    List<Banner> findByIdOrTitle(Long bannerId, String title);

    List<Banner> findByTitle(String title);

    Optional<Banner> findById(Long bannerId);

    Banner updateBanner(Long bannerId, Banner bannerDetails);

    List<Banner> findByContent(String content);

    List<Banner> findByTitleOrContent(String title, String content);
}