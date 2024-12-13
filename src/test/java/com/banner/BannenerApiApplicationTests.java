package com.banner;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.banner.bean.Banner;
import com.banner.repository.BannerRepository;
import com.banner.service.BannerService;



@ExtendWith(MockitoExtension.class)
class BannenerApiApplicationTests {

    @InjectMocks
    private BannerService bannerService;

    @Mock
    private BannerRepository bannerRepository;

    private Banner banner;
    
    private Banner bannerDetails;
    
    
    private List<Banner> banners;
    


    @BeforeEach                                         
    void setUp() {
    	banners = new ArrayList<>();
        banner = new Banner();
        banner.setBannerId(1L);
        banner.setTitle("Christmas Sale");
        banner.setContent("Get Dress Collection");
        banner.setStartDateTime(ZonedDateTime.now().minusHours(1));
        banner.setEndDateTime(ZonedDateTime.now().plusHours(1));
        bannerDetails = new Banner();
        bannerDetails.setTitle("May Celebeartion");
        bannerDetails.setContent("Get 5% offer");
        bannerDetails.setStartDateTime(ZonedDateTime.now());
        bannerDetails.setEndDateTime(ZonedDateTime.now().plusDays(2));
    }
    
    @Test
    public void testGetAllBannersData() {
        Banner banner1 = new Banner();
        Banner banner2 = new Banner();
        List<Banner> expectedBanners = List.of(banner1, banner2);
        when(bannerRepository.findAll()).thenReturn(expectedBanners);
        List<Banner> actualBanners = bannerService.getAllBanners();
        assertNotNull(actualBanners, "Banners list should not be null");
        assertEquals(2, actualBanners.size(), "Size of banners should match");
        assertEquals(expectedBanners, actualBanners, "Returned banners should match the expected banners");
    }
    

    @Test
    void testGetAllBanners() {
        Banner banner1 = new Banner();
        banner1.setTitle("ActivBanner");
        Banner banner2 = new Banner();
        banner2.setTitle("InActiveBanner");
        when(bannerRepository.findAll()).thenReturn(Arrays.asList(banner1, banner2));
        List<Banner> banners = bannerService.getAllBanners();
        assertEquals(2, banners.size());
        assertEquals("ActivBanner", banners.get(0).getTitle());

    }
    
//    @Test
//    public void testGetBannersActiveAndInactive() {
//        Banner activeBanner = new Banner();
//        activeBanner.setStartDateTime(ZonedDateTime.now().minusDays(1));
//        activeBanner.setEndDateTime(ZonedDateTime.now().plusDays(1));
//        Banner inactiveBanner = new Banner();
//        inactiveBanner.setStartDateTime(ZonedDateTime.now().minusDays(2));
//        inactiveBanner.setEndDateTime(ZonedDateTime.now().minusDays(1));
//        banners.add(activeBanner);
//        banners.add(inactiveBanner);
//        when(bannerRepository.findAll()).thenReturn(banners);
//        BannerResponse response = bannerService.getBanners();
//        assertNotNull(response, "Response should not be null");
//        assertEquals(1, response.getActiveBanners().size(), "Should contain 1 active banner");
//        assertEquals(activeBanner, response.getActiveBanners().get(0), "Active banner should match");
//        assertEquals(1, response.getInActiveBanners().size(), "Should contain 1 inactive banner");
//        assertEquals(inactiveBanner, response.getInActiveBanners().get(0), "Inactive banner should match");
//    }

//    @Test
//    public void testGetBanners_NoBanners() {
//        when(bannerRepository.findAll()).thenReturn(Collections.emptyList());
//        BannerResponse response = bannerService.getBanners();
//       assertNotNull(response, "Response should not be null");
//        assertTrue(response.getActiveBanners().isEmpty(), "Should have no active banners");
//        assertTrue(response.getInActiveBanners().isEmpty(), "Should have no inactive banners");
//    }

//    @Test
//    public void testGetAllActiveBanner() {
//        Banner activeBanner1 = new Banner();
//        activeBanner1.setStartDateTime(ZonedDateTime.now().minusDays(1));
//        activeBanner1.setEndDateTime(ZonedDateTime.now().plusDays(1));
//        Banner activeBanner2 = new Banner();
//        activeBanner2.setStartDateTime(ZonedDateTime.now().minusDays(1));
//        activeBanner2.setEndDateTime(ZonedDateTime.now().plusDays(2));
//        banners.add(activeBanner1);
//        banners.add(activeBanner2);
//        when(bannerRepository.findAll()).thenReturn(banners);
//        BannerResponse response = bannerService.getBanners();
//        assertNotNull(response, "Response should not be null");
//        assertEquals(2, response.getActiveBanners().size(), "Should contain 2 active banners");
//        assertTrue(response.getInActiveBanners().isEmpty(), "Should have no inactive banners");
//    }

//    @Test
//    public void testGetAllInactiveBanner() {
//        Banner inactiveBanner1 = new Banner();
//        inactiveBanner1.setStartDateTime(ZonedDateTime.now().minusDays(2));
//        inactiveBanner1.setEndDateTime(ZonedDateTime.now().minusDays(1));
//        Banner inactiveBanner2 = new Banner();
//        inactiveBanner2.setStartDateTime(ZonedDateTime.now().minusDays(3));
//        inactiveBanner2.setEndDateTime(ZonedDateTime.now().minusDays(2));
//        banners.add(inactiveBanner1);
//        banners.add(inactiveBanner2);
//        when(bannerRepository.findAll()).thenReturn(banners);
//        BannerResponse response = bannerService.getBanners();
//        assertNotNull(response, "Response should not be null");
//        assertTrue(response.getActiveBanners().isEmpty(), "Should have no active banners");
//        assertEquals(2, response.getInActiveBanners().size(), "Should contain 2 inactive banners");
//    }
    
       @Test  
       void testBannerIsActive() {
        ZonedDateTime now = ZonedDateTime.now();
        // Active 
        Banner activeBanner = new Banner();
        activeBanner.setStartDateTime(now.minusDays(1)); // Started yesterday
        activeBanner.setEndDateTime(now.plusDays(1));    // Ends tomorrow
        assertTrue(activeBanner.getStartDateTime().isBefore(now) && activeBanner.getEndDateTime().isAfter(now), "Banner should be active");
        // Inactive 
        Banner futureBanner = new Banner();
        futureBanner.setStartDateTime(now.plusDays(1)); // Starts tomorrow
        futureBanner.setEndDateTime(now.plusDays(2));   // Ends the day after tomorrow
        assertFalse(futureBanner.getStartDateTime().isBefore(now), "Banner should not be active yet");
        // Inactive case - Already ended
        Banner pastBanner = new Banner();
        pastBanner.setStartDateTime(now.minusDays(2)); // Started two days ago
        pastBanner.setEndDateTime(now.minusDays(1));   // Ends yesterday
        assertFalse(pastBanner.getEndDateTime().isAfter(now), "Banner should no longer be active");
    }

//       @Test
//        void testGetBanners() throws Exception{
//        when(bannerRepository.findAll()).thenReturn(Arrays.asList(banner));        
//        BannerResponse response = bannerService.getBanners();
//        assertTrue(response.getInActiveBanners().isEmpty());
//    }
    
    //// GetIdOrTitle
       
    @Test
     void testFindByIdOrTitle() {
        List<Banner> bannerList = new ArrayList<>();
        bannerList.add(banner);
        when(bannerRepository.findByBannerIdOrTitle(1L, "Christmas Sale")).thenReturn(bannerList);
        List<Banner> result = bannerService.findByIdOrTitle(1L, "Christmas Sale");
        assertFalse(result.isEmpty(), "Result should not be empty");
        assertEquals(1, result.size(), "Result size should be 1");
        assertEquals(banner, result.get(0), "The returned banner should match the existing banner");
        verify(bannerRepository).findByBannerIdOrTitle(1L, "Christmas Sale"); // Verify the method was called
    }



//// Insert Banner:

    @Test
    void testAddBanner(){
        bannerService.addBanner(banner);
        verify(bannerRepository, times(1)).save(banner);  // verify it is behavior happened check 
        //  verify   ---- times(1) It is default have times(1) . How many time method call give time(2).
        //  verify  atLeast(5) , method display can used for purpose .
        //  verify  verify(mock , times(1));
        //  verify verify(mock , atLeast(5));
    }
    
   // GeByTitleMethod
    
    // It is here Check One Method Exception Handled.
    //  1. Null Value check throws exception 
    //  2. Empty value check throws exception
    //  3. FoundContent check throws exception
    //  4. ReturnFoundConetnetListBanner 

    @Test
    void testFindByNulltitle() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            bannerService.findByTitle(null);
        });

        Assertions.assertEquals("Title must not be empty", exception.getMessage());
    }

    @Test
    void testFindByEmptytitle() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            bannerService.findByTitle("");
        });

        Assertions.assertEquals("Title must not be empty", exception.getMessage());
    }
  
    @Test
    void testFindByTitleFound() {
        String title = "Christmas Sale";
        when(bannerRepository.findByTitle(title)).thenReturn(Collections.emptyList());
        Exception exception = Assertions.assertThrows(RuntimeException.class, () -> {
            bannerService.findByTitle(title);
        });
        Assertions.assertEquals("No Title found with the nameChristmas Sale", exception.getMessage());
    }

    @Test
    void testFindByTitleBannerList() {
        String title = "Christmas Sale";
        Banner Banner = new Banner(); 
        when(bannerRepository.findByTitle(title)).thenReturn(List.of(Banner));
        List<Banner> result = bannerService.findByTitle(title);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(Banner, result.get(0)); 
//      Assertions.assertEquals(expectedBanner, result.get(1)); failure message IndexOutOfException  

    }

//// --------------------------------------------------------------------------------------------------------
 
   
    /// GetById Check Method :
    
    @Test
    void testGetById(){
    	when(bannerRepository.findById(1L)).thenReturn(Optional.of(banner));
     	  Optional<Banner> banner = bannerService.findById(1L);
         assertTrue(banner.isPresent());
    	 assertEquals("Christmas Sale", banner.get().getTitle());
    }

    @Test
     void testFindByIdNotFound() {
        when(bannerRepository.findById(anyLong())).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            bannerService.findById(1L);
        });
        assertEquals("Id is not found", exception.getMessage());
        verify(bannerRepository).findById(1L); // Ensures findById was called
    }
    
//// -----------------------------------------------------------------------------------------------

    // UPDATE METHOD
    //  1. Here Id is checking it id have and not have through the exception handle
    //  2. Update Banner Details 
    
    @Test
    void testBannerIdNotFound() {
       when(bannerRepository.findById(anyLong())).thenReturn(Optional.empty());
       RuntimeException exception = assertThrows(RuntimeException.class, () -> {
           bannerService.updateBanner(1L, bannerDetails);
       });
       assertEquals("Id is not found", exception.getMessage());
       verify(bannerRepository).findById(1L);
       verify(bannerRepository, never()).save(any(Banner.class));
   }

    @Test
     void testUpdateBanner() {
        when(bannerRepository.findById(1L)).thenReturn(Optional.of(banner));
        when(bannerRepository.save(any(Banner.class))).thenReturn(banner);
        Banner updatedBanner = bannerService.updateBanner(1L, bannerDetails);
        assertEquals("May Celebeartion", updatedBanner.getTitle());
        assertEquals("Get 5% offer", updatedBanner.getContent());
        verify(bannerRepository).findById(1L);
        verify(bannerRepository).save(banner);
     }



    
    
   // -----------------------------------------------------------------------------------------
    
   // GetByContent 
    
    // It is here Check One Method Exception Handled.
    //  1. Null Value check throws exception 
    //  2. Empty value check throws exception
    //  3. FoundContent check throws exception
    //  4. ReturnFoundConetnetListBanner 

    @Test
    void testGetByContentNullContent() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            bannerService.findByContent(null);
        });
        Assertions.assertEquals("Content must not be empty", exception.getMessage());
    }

    @Test
    void testGetByContentEmptyContent() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            bannerService.findByContent("");
        });
        Assertions.assertEquals("Content must not be empty", exception.getMessage());
    }
  
    @Test
    void testGetByContentFound() {
        String content = "Get Dress Collection";
        when(bannerRepository.findByContent(content)).thenReturn(Collections.emptyList());
        Exception exception = Assertions.assertThrows(RuntimeException.class, () -> {
            bannerService.findByContent(content);
        });
        Assertions.assertEquals("No Content found with the nameGet Dress Collection", exception.getMessage());
    }

    @Test
    void testGetByContentBannerList() {
        String content = "Get Dress Collection";
        Banner Banner = new Banner(); 
        when(bannerRepository.findByContent(content)).thenReturn(List.of(Banner));
        List<Banner> result = bannerService.findByContent(content);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(Banner, result.get(0)); 
//      Assertions.assertEquals(expectedBanner, result.get(1)); failure message IndexOutOfException  

    }
 
 //----------------------------------------------------------------------------------
    //GetByTitleandContent
     
    
    @Test
    public void testGetByTitleOrContent() {
        String title = "Christmas Sale";
        String content = "Get Dress Collection";
        Banner banner1 = new Banner();
        banner1.setTitle(title);
        banner1.setContent("May offer 5%");
        Banner banner2 = new Banner();
        banner2.setTitle("May Celebration");
        banner2.setContent(content);
        List<Banner> banners = Arrays.asList(banner1, banner2);
        when(bannerRepository.findByTitleOrContent(title, content)).thenReturn(banners);
        List<Banner> result = bannerService.findByTitleOrContent(title, content);
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void testGetByTitleOrContentFound() {
        String title = "Diwail Offer";
        String content = "Get 10% offer";
        when(bannerRepository.findByTitleOrContent(title, content)).thenReturn(Collections.emptyList());
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            bannerService.findByTitleOrContent(title, content);
        });
        assertEquals("No banners found for title: Diwail Offer or content: Get 10% offer", exception.getMessage());
    }
}
