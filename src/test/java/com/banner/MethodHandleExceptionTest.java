package com.banner;

import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.banner.bean.Banner;
import com.banner.repository.BannerRepository;
import com.banner.serviceimp.BannerServiceImpl;



public class MethodHandleExceptionTest {

	
	
    @InjectMocks
    private BannerServiceImpl bannerService;
    // It is @InjectMocks Indicated Which class write TestCase:

    @Mock
    private BannerRepository bannerRepository;
    // It @Mock Indicated Mock dummy Input and Output:

    // It is here Check One Method Exception Handled.
    //  1. Null Value check throws exception 
    //  2. Empty value check throws exception
    //  3. WhiteSpace check throws exception
    //  4. FoundContent check throws exception
    //  5. FoundListBanner check throws exception Here . Index 0 , 1 get(0) check listofBanners 

  

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); 
//        bannerService = new BannerService(bannerRepository); 
    }

    @Test
    void testGetByContentNullContentThrowsIllegalArgumentException() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            bannerService.findByContent(null);
        });

        Assertions.assertEquals("Content must not be empty", exception.getMessage());
    }

    @Test
    void testGetByContentEmptyContentThrowsIllegalArgumentException() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            bannerService.findByContent("");
        });

        Assertions.assertEquals("Content must not be empty", exception.getMessage());
    }

    @Test
    void testGetByContentWhitespaceContentThrowsIllegalArgumentException() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            bannerService.findByContent("   ");
        });

        Assertions.assertEquals("Content must not be empty", exception.getMessage());
    }

    @Test
    void testGetByContentNoBannerFoundThrowsRuntimeException() {
        String content = "Get Dress Collection";
        when(bannerRepository.findByContent(content)).thenReturn(Collections.emptyList());
        Exception exception = Assertions.assertThrows(RuntimeException.class, () -> {
            bannerService.findByContent(content);
        });
        Assertions.assertEquals("No Content found with the nameGet Dress Collection", exception.getMessage());
    }

    @Test
    void testGetByContentBannerFoundReturnsBannerList() {
        String content = "Get Dress Collection";
        Banner Banner = new Banner(); 
        when(bannerRepository.findByContent(content)).thenReturn(List.of(Banner));
        List<Banner> result = bannerService.findByContent(content);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(Banner, result.get(0)); 
//      Assertions.assertEquals(expectedBanner, result.get(1)); failure message IndexOutOfException  

    }
}
