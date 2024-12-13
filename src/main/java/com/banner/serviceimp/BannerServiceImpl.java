package com.banner.serviceimp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.banner.bean.Banner;
import com.banner.bean.Banner.BannerType;
import com.banner.dto.BannerResponse;
import com.banner.exception.BannerNotFoundException;
import com.banner.repository.BannerRepository;
import com.banner.service.BannerService;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerRepository bannerRepository;

    @Override
    public List<Banner> getAllBanners() {
        return bannerRepository.findAll();
    }
    
   @Override  // Sort used Function First and Priority - Ascending Order
   public List<Banner> findBannnersWithSort(String sort) {
	   return bannerRepository.findAll(Sort.by(Sort.Direction.ASC,sort));
   }
 
   @Override  // Pagination
   public Page<Banner> findBannersWithPaging(int offset, int records) {
	   Page<Banner>  bannerPage = bannerRepository.findAll(PageRequest.of(offset, records));
	   return bannerPage;
   }     // offset - 0 , 20 ; -- 10 List return
         // offset - 2 , 50 ; -- 11 to 20 List return 
   
   @Override  // PaginationAndSort 
   public Page<Banner> findBannersWithPagingAndSort(int offset, int records, String sort) {
	   Page<Banner>  bannerPage=bannerRepository.findAll(PageRequest.of(offset, records).withSort(Sort.by(Sort.Direction.DESC,sort)));
	   return bannerPage;
   }
   
   @Override 
   public Page<Banner> findBannersWithPagingAndSorttitle(int offset, int records, String sort, String filter) {
       Pageable pageable = PageRequest.of(offset, records, Sort.by(Sort.Direction.DESC, sort));
       if (filter != null && !filter.isEmpty()) {
           return bannerRepository.findByTitleContainingIgnoreCase(filter, pageable);
       } else {
           return bannerRepository.findAll(pageable);
       }
   }
         
   @Override
   public Page<Banner> findBannersWithPagingAndSortAll(int offset, int records, String sort, String filterTitle, String filterContent, BannerType filterType,String order) {
       Sort.Direction direction = order.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
       Pageable pageable = PageRequest.of(offset, records, direction, sort);
       if (filterTitle != null && !filterTitle.isEmpty() || filterContent !=null && !filterContent.isEmpty() || filterType !=null && !filterType.equals(filterType) ) {
           return bannerRepository.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCaseOrType(filterTitle, 
               filterContent, filterType, pageable );
       } else {
           return bannerRepository.findAll(pageable);
       }
   }

   @Override
   public BannerResponse getBanners(String title, String content, Banner.BannerType type, Pageable pageable) {
       BannerResponse response = new BannerResponse();
       Page<Banner> bannerPage = bannerRepository.findByTitleContainingOrContentContainingOrType(title, content, type, pageable);
       List<Banner> activeBanners = new ArrayList<>();
       List<Banner> inactiveBanners = new ArrayList<>();
       ZonedDateTime now = ZonedDateTime.now();
        
       for (Banner banner : bannerPage) {
           boolean isActive = banner.getStartDateTime().isBefore(now) && banner.getEndDateTime().isAfter(now);
           if (isActive) {
               activeBanners.add(banner);
           } else {
                inactiveBanners.add(banner);
           }
       }
        response.setActiveBanners(activeBanners);
        response.setInActiveBanners(inactiveBanners);
        return response;
    }
    
    
    @Override
    public void addBanner(Banner banner) {
        bannerRepository.save(banner);
    }

    @Override
    public List<Banner> findByIdOrTitle(Long bannerId, String title) {
        return bannerRepository.findByBannerIdOrTitle(bannerId, title);
    }

    @Override
    public List<Banner> findByTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title must not be empty");  
        }
        List<Banner> banners = bannerRepository.findByTitle(title);
        if (banners.isEmpty()) {
            throw new BannerNotFoundException("No Title found with the name: " + title);  
        }
        return banners;
    }

    @Override
    public Optional<Banner> findById(Long bannerId) {
        Banner banner = bannerRepository.findById(bannerId)
            .orElseThrow(() -> new BannerNotFoundException("Id " + bannerId + " is not found")); 
        return Optional.of(banner);   
    }

    @Override
    public Banner updateBanner(Long bannerId, Banner bannerDetails) {
        Banner banner = bannerRepository.findById(bannerId)
            .orElseThrow(() -> new BannerNotFoundException("Id " + bannerId + " is not found"));
        banner.setTitle(bannerDetails.getTitle());
        banner.setContent(bannerDetails.getContent());
        banner.setStartDateTime(bannerDetails.getStartDateTime());
        banner.setEndDateTime(bannerDetails.getEndDateTime());
        return bannerRepository.save(banner);
    }

//    @Override
//    public List<Banner> findByContent(String content) {
//    	
//     if (content == null || content.trim().isEmpty()) {
//        	throw new IllegalArgumentException("Content must not be empty");  
//        }
//        List<Banner> banners = bannerRepository.findByContent(content);
//        if (banners.isEmpty()) {
//            throw new BannerNotFoundException("No Content found with the name: " + content);  
//        }
//        return banners;
//    }
    @Override
    public List<Banner> findByContent(String content){
    	if(content == null || content.trim().isEmpty()) {
    		throw new IllegalArgumentException("Content must not be empty");  
    	}
    	List<Banner> banner=bannerRepository.findByContent(content);
    	if(banner.isEmpty()) {
    		throw new BannerNotFoundException("No Content found with the name: " + content); 
    	}
    	return banner;
    }

    @Override
    public List<Banner> findByTitleOrContent(String title, String content) {
        List<Banner> banners = bannerRepository.findByTitleOrContent(title, content);
        if (banners.isEmpty()) {
            throw new BannerNotFoundException("No banners found for title: " + title + " or content: " + content);
        }
        return banners;
    }
}