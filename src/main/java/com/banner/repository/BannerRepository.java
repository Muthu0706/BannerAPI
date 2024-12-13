package com.banner.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.banner.bean.Banner;

@Repository
public interface BannerRepository extends JpaRepository<Banner ,Long> {
	
	List<Banner> findByTitle(String title);
	
	// select * from tbl_banner where emp_Id=?;
	Optional<Banner>  findById(Long bannerId);

	// Title 
	Page<Banner> findByTitleContainingIgnoreCase(String title, Pageable pageable);
		 
	Page<Banner> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCaseOrType(
	            String title, String content, Banner.BannerType type, Pageable pageable);
	
	//select * from tbl_banner where bannerId=? Or title=?;
	List<Banner> findByBannerIdOrTitle(Long bannerId, String title);
	    
	//select * form tbl_banner where content=?;
	List<Banner>  findByContent(String content);
	// select * from tbl_banners where title=? Or content=?;
    List<Banner> findByTitleOrContent(String title, String content);

    Page<Banner> findByTitleContainingOrContentContainingOrType(String title, String content, Banner.BannerType type, Pageable pageable);


}
