package com.kick;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.poi.util.SystemOutLogger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kick.entity.Product;
import com.kick.entity.ProductRepository;

@SpringBootTest
class Cafe24Booming {
    
	@Autowired ProductRepository repository;
	
	public static final String filePath= "C:\\Users\\15kso\\OneDrive\\바탕 화면\\새 폴더\\옵션.xlsx";
	List<String> selectCountOption = new ArrayList<>();
	/////////////////////////CAFE24 쿠팡////////////////////////////
	@Test
	//test 코드에서 transactional 사용 하지 마라
	void asd() throws InterruptedException, IOException {
		EdgeDriver driver;
	 	EdgeOptions options = new EdgeOptions();
	 	options.addArguments("--start-maximized");
	 	driver = new EdgeDriver(options);
        
	    driver.get("https://booming-b2b.co.kr/member/login.html");
        WebElement member_id=driver.findElement(By.id("member_id"));
        WebElement member_passwd=driver.findElement(By.id("member_passwd"));
        WebElement login=driver.findElement(By.cssSelector("form > div > fieldset > a.btnSubmit.sizeL.df-lang-button-login"));
        member_id.sendKeys("qudtn0295");
        //qudtn0295 qudtn4589
        member_passwd.sendKeys("qudtn!4589");
        login.click();
        Thread.sleep(5000);
        
        repository.deleteAll();
        //19까지 함 APPLE
        driver.navigate().to("https://booming-b2b.co.kr/product/list.html?cate_no=175");
        
        //변수
        List<String> urls = new ArrayList<>();

        //tcnt
        for(int i=1; i<=2;i++) {
//        	WebElement nextPage=driver.findElement(By.cssSelector("#contents > div.xans-element-.xans-product.xans-product-normalpaging.ec-base-paginate > a:nth-child(4)"));
        	
        	List<WebElement> elements = driver.findElements(By.cssSelector("#contents > div.xans-element-.xans-product.xans-product-normalpackage > div.xans-element-.xans-product.xans-product-listnormal.df-prl-wrap.df-prl-setup > ul >li  > div >div.df-prl-thumb >a "));
        	for(WebElement ele : elements) {
        		System.out.println(ele.getAttribute("href"));
        		urls.add(ele.getAttribute("href"));
        	}
        	driver.findElement(By.cssSelector("#contents > div.xans-element-.xans-product.xans-product-normalpaging.ec-base-paginate > a:nth-child(4)")).click();
        }
        
        for(String url : urls) {
        	System.out.println(url);
        	driver.navigate().to(url);
        	WebElement cl = driver.findElement(By.cssSelector("#prdDetail > div.df-prd-tab > ul > li.df-prd-tab-item.selected > a"));
        	cl.click();
        	Thread.sleep(3000);
        	JavascriptExecutor js = (JavascriptExecutor) driver;
        	//셀렉트박스는 두개값 만 가져옴
        	List<WebElement> selectLists = driver.findElements(By.cssSelector("#df-product-detail > div > div.infoArea-wrap > div > div > div.scroll-wrapper.df-detail-fixed-scroll.scrollbar-macosx > div.df-detail-fixed-scroll.scrollbar-macosx.scroll-content > ul > li >ul > li >select"));
        	StringBuilder selectOption = new StringBuilder();
        	StringBuilder selectOption2 = new StringBuilder();
        	StringBuilder optionNo = new StringBuilder();
        	StringBuilder optionYn =new StringBuilder();
        	List<String> imgs = new ArrayList<>();
        	List<WebElement> thumbLists = driver.findElements(By.cssSelector("#df-product-detail > div > div.imgArea-wrap > div > div > div.thumbnail > div.df-slider > div.swiper-container.swiper-container-slide.swiper-container-initialized.swiper-container-horizontal.swiper-container-autoheight > ul > li.xans-record-.swiper-slide.swiper-slide-active > div > img"));
        	List<WebElement> detailImageLists = driver.findElements(By.cssSelector("#prdDetail > div.cont > div > div > p > img"));
            List<WebElement> detailImageLists2 = driver.findElements(By.cssSelector("#prdDetail > div.cont > div > p > img"));

            String uuid = UUID.randomUUID().toString();
            String fileName = null;
            for(WebElement thmb : thumbLists) {
            	String thbFormat = thmb.getAttribute("src").substring(thmb.getAttribute("src").length()-4,thmb.getAttribute("src").length());
        		if(thbFormat.equals(".jpg")||thbFormat.equals(".gif")||thbFormat.equals(".png")||thbFormat.equals(".jpeg")) {
        			String imgUrl = thmb.getAttribute("src");
        			fileName = thmb.getAttribute("src").substring(thmb.getAttribute("src").lastIndexOf("/") + 1);
        			fileName = URLDecoder.decode(fileName,"UTF-8").toString().substring(0, URLDecoder.decode(fileName,"UTF-8").length()-4);
        			String savePath ="C:\\Users\\15kso\\OneDrive\\바탕 화면\\새 폴더\\";
        			System.out.println(fileName);
              		File afterFile = new File(savePath + URLDecoder.decode(fileName,"UTF-8")+".png");
              		saveThumbImage(imgUrl,savePath,afterFile);	
              		
        		}
            }
            
            Thread.sleep(3000);
            for(WebElement detailImage : detailImageLists) {
            	 
            	 String imgUrl = detailImage.getAttribute("src");
            	 Thread.sleep(5000);
            	 ((JavascriptExecutor) driver).executeScript(
                         "arguments[0].scrollIntoView();", detailImage);
            	 ((JavascriptExecutor)js).executeScript("window.scrollBy(0,500)", "");
        		 imgs.add(imgUrl);
            }
            Thread.sleep(3000);
		   	for(WebElement detailImage2 : detailImageLists2) {
		   		 String imgUrl2 = detailImage2.getAttribute("src"); 
		   		 Thread.sleep(5000);
		   		 ((JavascriptExecutor) driver).executeScript(
                         "arguments[0].scrollIntoView();", detailImage2);
		   		 ((JavascriptExecutor)js).executeScript("window.scrollBy(0,500)", "");
	   		 	 imgs.add(imgUrl2);
		   	}
		   	
		   	String detailImgs = null;
		   	StringBuilder sb =new StringBuilder();
		   	for(String img : imgs) {
				 String imageUrl  = img;
				 String savePath ="C:\\Users\\15kso\\OneDrive\\바탕 화면\\detailimage\\";
				 detailImgs = uuid.toString() + img.substring(img.lastIndexOf("/") + 1);
				 String fileNames =  URLDecoder.decode(detailImgs,"UTF-8").toString().substring(0, URLDecoder.decode(detailImgs,"UTF-8").length()-4);
				 if(fileNames.length()<50) {
					 File afterFile = new File(savePath + fileNames +".jpeg");
					 sb.append("<p align=\"center\">"+"<img src='https://qudtn0295.cafe24.com/detailImage21/"+URLDecoder.decode(fileNames,"UTF-8").toString()+".jpeg"+"'/>"+"</p>");
					 saveDetailImage(imageUrl,savePath,afterFile);
				 }else {
					 String uuid2 = UUID.randomUUID().toString();
					 File afterFile = new File(savePath + uuid2 +".jpeg");
					 sb.append("<p align=\"center\">"+"<img src='https://qudtn0295.cafe24.com/detailImage21/"+uuid2.toString()+".jpeg"+"'/>"+"</p>");
					 saveDetailImage(imageUrl,savePath,afterFile);
				 }
			}

		   	StringBuilder selectBoxEnabled = new StringBuilder();
            for(int k=1;k<=selectLists.size();k++) {
            	selectBoxEnabled.append("F|");
            }
            
        	//상세페이지에있는 제목 가격 온라인판매가 제조사
        	WebElement title=driver.findElement(By.cssSelector("#df-product-detail > div > div.infoArea-wrap > div > div > div.scroll-wrapper.df-detail-fixed-scroll.scrollbar-macosx > div.df-detail-fixed-scroll.scrollbar-macosx.scroll-content > div.headingArea > h2"));
//        	WebElement realPrice=driver.findElement(By.cssSelector("#contents > div.detaarea > div.bg_gray > div > div.xans-element-.xans-product.xans-product-detail > div.detailArea > div.infoArea > div.disno > div > ul > li:nth-child(4) > div.disnoul_right > span"));
        	WebElement price=driver.findElement(By.cssSelector("#span_product_price_text"));
        	
        	for(WebElement sel : selectLists) {
        		Select selectOptions = new Select(sel);
        		selectOptions.selectByIndex(2);
	        	List<WebElement> optionList = selectOptions.getOptions();
	        	optionList.remove(0);
	        	for(WebElement option : optionList) {
	        		if(sel.getAttribute("id").equals("product_option_id1")) {
	        			//optionList에서 개수를 제한하는것도 방법일듯함..
	        			if(option.isEnabled()) {
	        				selectOption.append(option.getText()+"|");
	        			}
	        		}else {
	        			if(option.isEnabled()) {
		        			selectOption2.append(option.getText()+"|");
		        			
	        			}
	        		}
	        	}
        	}
//        		//셀렉트
//
        	String rePrice = price.getText();
        	rePrice = rePrice.replaceAll("[^ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9]", "");
        	rePrice = rePrice.replace("원","");
        	int reInPrice= Integer.parseInt(rePrice);
        	System.out.println(reInPrice);
        	if(reInPrice<10000) {
        		reInPrice = reInPrice+10000;
        	}else {
        		reInPrice = reInPrice+11000;
        	}
        	String price2 = Integer.toString(reInPrice); 
        	String reTitle = title.getText();
        	reTitle = reTitle.replace("B2B_","[커버핏]");
        	Product products = new Product();
        	products.setTitle(reTitle);
        	if(selectOption.toString().length()>0 &&selectOption2.toString().length()>0 ) {
        		products.setOption("옵션1{"+selectOption.toString().substring(0, selectOption.toString().length()-1)+"}"+"//옵션2{"+selectOption2.substring(0, selectOption2.length()-1)+"}");
        	}else {
        		products.setOption("옵션1{"+selectOption.toString().substring(0, selectOption.toString().length()-1)+"}");
        	}
        	products.setPrice(price2);
        	products.setCompany("디자인바니");
        	products.setUuid(uuid.toString());
        	products.setSelectBoxEnabled(selectBoxEnabled.toString().substring(0, selectBoxEnabled.toString().length()-1));
        	if(optionNo.toString().length()>0 && optionYn.toString().length()>0) {
	        	products.setOptionNo(optionNo.toString().substring(0, optionNo.toString().length()-1));
	        	products.setOptionEnabled(optionYn.toString().substring(0, optionYn.toString().length()-1));
        	}
        	products.setDetailImg(sb.toString());
        	products.setThmb(fileName+".png");
        	
        	System.out.println(sb.toString());
        	repository.save(products);
        	
        	
        }
        
	}
	//이미지 읽을대 뒤에 확장자명 곡 붙여줘야함..
	@Test
	public void excel() throws InterruptedException, IOException {
		int i=0;
		 //워크북 생성
	    XSSFWorkbook workbook = new XSSFWorkbook();
	    //워크시트 생성
	    XSSFSheet sheet = workbook.createSheet();
	    //행 생성
	    XSSFRow row = sheet.createRow(0);
	    //셀 생성
	    XSSFCell cell;
	    cell = row.createCell(0);
	    cell.setCellValue("제목");
	    cell = row.createCell(1);
	    cell.setCellValue("제조사");
	    cell = row.createCell(2);
	    cell.setCellValue("도매가");
	    cell = row.createCell(3);
	    cell.setCellValue("온라인 판매가");
	    cell = row.createCell(4);
	    cell.setCellValue("상품분류 번호");
	    cell = row.createCell(5);
	    cell.setCellValue("상품신상품 영역");
	    cell = row.createCell(6);
	    cell.setCellValue("옵션");
	    cell = row.createCell(7);
	    cell.setCellValue("옵션사용여부");
	    cell = row.createCell(8);
	    cell.setCellValue("상세페이지 이미지");
	    cell = row.createCell(9);
	    cell.setCellValue("THMB");
	    List<Product> products = repository.findAll();
	    
//	    long count = repository.count();
	    for(Product p : products) {
	    	row = sheet.createRow(i+1);
	    	cell = row.createCell(0);
	    	cell.setCellValue(p.getTitle());
	    	cell = row.createCell(1);
	    	cell.setCellValue("커버핏");
	    	cell = row.createCell(2);
	    	cell.setCellValue(p.getPrice());
	    	cell = row.createCell(3);
	    	cell.setCellValue(p.getRealPrice());
	    	cell = row.createCell(4);
	    	cell.setCellValue(p.getOptionEnabled());
	    	cell = row.createCell(5);
	    	cell.setCellValue(p.getOptionNo());
	    	cell = row.createCell(6);
	    	cell.setCellValue(p.getOption());
	    	cell = row.createCell(7);
	    	cell.setCellValue(p.getSelectBoxEnabled());
	    	cell = row.createCell(8);
	    	cell.setCellValue(p.getDetailImg()+p.getDetailImg2()+p.getDetailImg3());
	    	cell = row.createCell(9);
	    	cell.setCellValue("/thmb21/"+p.getThmb());
	    	i++;
	    }
	   
		File file = new File("C:\\Users\\15kso\\OneDrive\\바탕 화면\\새 폴더\\옵션.xls");
        FileOutputStream fos = null;
        fos = new FileOutputStream(file);
        workbook.write(fos);
	}
	
	public static void saveDetailImage(String imageUrl, String savePath, File afterFile) throws MalformedURLException {
		URL url;
		//jpeg,png,bmp,wbmp,gif 만 지원을함. webp 지원안함 ㅡㅡ 다른 라이브러리 찾아라  개같음
		BufferedImage bi = null;
		try {
			url = new URL(imageUrl); // 다운로드 할 이미지 URL
			bi = ImageIO.read(url);
			System.out.println("bi.getWidth()"+bi.getWidth());
			System.out.println("bi.getHeight()"+bi.getHeight());
//			Image resizeImage = bi.getScaledInstance(bi.getWidth(), bi.getHeight(), Image.SCALE_SMOOTH);
//			BufferedImage newImage = new BufferedImage(bi.getWidth(), bi.getHeight(), BufferedImage.TYPE_INT_RGB);
			if(bi.getHeight()>5000) {
				Image resizeImage = bi.getScaledInstance(1000, 5000, Image.SCALE_SMOOTH);
				BufferedImage newImage = new BufferedImage(1000, 5000, BufferedImage.TYPE_INT_RGB);
				Graphics g = newImage.getGraphics();
	        	g.drawImage(resizeImage, 0, 0, null);
	            g.dispose();
				ImageIO.write(newImage, "jpeg", afterFile);	
			}else {
				Image resizeImage = bi.getScaledInstance(1000, bi.getHeight(), Image.SCALE_SMOOTH);
				BufferedImage newImage = new BufferedImage(1000, bi.getHeight(), BufferedImage.TYPE_INT_RGB);
				Graphics g = newImage.getGraphics();
	        	g.drawImage(resizeImage, 0, 0, null);
	            g.dispose();
				ImageIO.write(newImage, "jpeg", afterFile);
			}
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static void saveThumbImage(String imageUrl, String savePath, File afterFile) throws MalformedURLException {
		URL url;
		//jpeg,png,bmp,wbmp,gif 만 지원을함. webp 지원안함 ㅡㅡ 다른 라이브러리 찾아라  개같음
		BufferedImage bi = null;
		try {
			
			url = new URL(imageUrl); // 다운로드 할 이미지 URL
			bi = ImageIO.read(url);
			ImageIO.write(bi, "png", afterFile);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
