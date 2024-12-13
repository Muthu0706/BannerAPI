package com.banner.controller;

import com.banner.bean.Banner;

import com.banner.dto.BannerResponse;
import com.banner.serviceimp.BannerServiceImpl;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/banners")
public class BannerController {

	@Autowired
	private BannerServiceImpl bannerService;
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Banner>> getAllBanners() {
		List<Banner> bannerList = bannerService.getAllBanners();
    	return ResponseEntity.ok(bannerList);
	}
	
	@GetMapping("/sorting/{sort}") // Sorting First and Priority  http://localhost:8080/api/banners/sorting/title
	public ResponseEntity<List<Banner>> getBannersWithSort(@PathVariable String sort) {
		List<Banner> bannerList = bannerService.findBannnersWithSort(sort);
		return ResponseEntity.ok(bannerList);
	}
	
	@GetMapping("/paging/{offset}/{records}") // http://localhost:8080/api/banners/paging/0/10
	public ResponseEntity<Page<Banner>>  getBannersWithPage(@PathVariable int offset, @PathVariable int records ) {
		Page<Banner>  bannerPage=bannerService.findBannersWithPaging(offset, records);
		return ResponseEntity.ok(bannerPage);
	}
	
	@GetMapping("/pagingAndSort/{offset}/{records}/{sort}") // http://localhost:8080/api/banners/pagingAndSort/0/10/title
	public ResponseEntity<Page<Banner>>  getBannersWithPagingAndSort(@PathVariable int offset, @PathVariable int records, @PathVariable String sort ) {
		Page<Banner> bannerPage=bannerService.findBannersWithPagingAndSort(offset, records, sort);
		return ResponseEntity.ok(bannerPage);
	}
	
	@GetMapping("/pagingAndSorttitle/{offset}/{records}/{sort}") // http://localhost:8080/api/banners/pagingAndSorttitle/0/10/title?filter=Christmas Sale
	public ResponseEntity<Page<Banner>> getBannersWithPagingAndSort(
	        @PathVariable int offset, 
	        @PathVariable int records, 
	        @PathVariable String sort,
	        @RequestParam(required = false) String filter) { 
	    Page<Banner> bannerPage = bannerService.findBannersWithPagingAndSorttitle(offset, records, sort, filter);
	    return ResponseEntity.ok(bannerPage);
	}
		
    @GetMapping("/pagingAndSortALL/{offset}/{records}/{sort}") // http://localhost:8080/api/banners/pagingAndSortALL/0/10/title?filterTitle=Christmas&filterContent=Sale&filterType=ALERT
    public ResponseEntity<Page<Banner>> getBannersWithPagingAndSort(
            @PathVariable int offset, 
            @PathVariable int records, 
            @PathVariable String sort,
            @RequestParam(required = false) String filterTitle,
            @RequestParam(required = false) String filterContent,
            @RequestParam(required = false) Banner.BannerType filterType,
            @RequestParam(defaultValue = "asc") String order) {
        Page<Banner> bannerPage = bannerService.findBannersWithPagingAndSortAll(offset, records, sort, filterTitle, filterContent, filterType, order);
        return ResponseEntity.ok(bannerPage);
    }
    

		
	@GetMapping("/banners") // http://localhost:8080/api/banners/banners?offset=0&records=10&type=INFORMATION
    public BannerResponse getBanners(@RequestParam(required = false) String title, @RequestParam(required = false) String content,
	   @RequestParam(required = false) Banner.BannerType type, @RequestParam int offset, @RequestParam int records) {
	   Pageable pageable = PageRequest.of(offset, records);
	   return bannerService.getBanners(title, content, type, pageable);
	 }
    
	 
    @PostMapping("/add")
    public ResponseEntity<Banner> addBanner(@RequestBody Banner banner) {
        bannerService.addBanner(banner);
        return new ResponseEntity<>(HttpStatus.CREATED); 
    }
    
    
    @GetMapping("/findByTitle")
    public ResponseEntity<List<Banner>>  findByTitle(@RequestParam (required = true) String title) throws RuntimeException {
    	List<Banner>  bannerList=bannerService.findByTitle(title);
		return ResponseEntity.ok(bannerList); 	
    }
    
    
    @GetMapping("/{bannerId}")
    public ResponseEntity<Optional<Banner>> findById(@PathVariable Long bannerId) throws IllegalArgumentException {
    	Optional<Banner>  banner =bannerService.findById(bannerId);
		return ResponseEntity.ok(banner); 	
		}
    
    
    @GetMapping("/findByIdOrTitle")
    public ResponseEntity<List<Banner>> findByIdAndTitle(@RequestParam (required=false) Long bannerId , @RequestParam(required=false) String title) {
    	List<Banner> bannerList =bannerService.findByIdOrTitle(bannerId, title);
    	return ResponseEntity.ok(bannerList);
    }
    
    
    @PutMapping("{bannerId}")
    public ResponseEntity<Banner> updateBanner( @PathVariable Long bannerId, @RequestBody Banner bannerDetails ) {
    	Banner banner =bannerService.updateBanner(bannerId, bannerDetails);
    	return ResponseEntity.ok(banner);
    }
    
    
    @GetMapping("/findByContent")
    public ResponseEntity<List<Banner>>  findByContent(@RequestParam String content) throws RuntimeException {
    	List<Banner>  bannerList =bannerService.findByContent(content);
    	return ResponseEntity.ok(bannerList);
    }
    
    
    @GetMapping("/findByTitleOrContent")
    public ResponseEntity<List<Banner>> findByTitleOrContent(@RequestParam  String title, @RequestParam  String content) throws RuntimeException {
    	List<Banner> bannerList =bannerService.findByTitleOrContent(title, content);
    	return ResponseEntity.ok(bannerList);
    }
}

