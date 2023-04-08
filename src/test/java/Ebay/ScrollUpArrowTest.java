package Ebay;

import base.CommonAPI;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class ScrollUpArrowTest extends CommonAPI {
        static ScrollUpArrow scrollUpArrow;
        public static void  getUpArrow(){scrollUpArrow = PageFactory.initElements(driver, ScrollUpArrow.class);}
        @Test
        public void SCROLL_UP_THE_ARROW() throws InterruptedException {
            getUpArrow();
            scrollUpArrow.UPARROW();
            String actual = scrollUpArrow.verification();
            String expeected = "ebay";
        }
    }
