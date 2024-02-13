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
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.poi.util.SystemOutLogger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kick.entity.Product;
import com.kick.entity.ProductRepository;

@SpringBootTest
class Cafe24JandCompany {
    
	@Autowired ProductRepository repository;
	
	public static final String filePath= "C:\\Users\\15kso\\OneDrive\\바탕 화면\\새 폴더\\옵션.xlsx";
	List<String> selectCountOption = new ArrayList<>();
	/////////////////////////CAFE24 쿠팡////////////////////////////
	@Test
	//test 코드에서 transactional 사용 하지 마라
	void asd() throws InterruptedException, IOException {
		WebDriver  driver=new EdgeDriver();
	 	EdgeOptions options = new EdgeOptions();
        driver = new EdgeDriver(options);
	    driver.get("https://jncompany.shop/member/login.html");
        WebElement member_id=driver.findElement(By.id("member_id"));
        WebElement member_passwd=driver.findElement(By.id("member_passwd"));
        WebElement login=driver.findElement(By.cssSelector("form > div > fieldset > a.btnSubmit.sizeL.df-lang-button-login"));
        member_id.sendKeys("qudtn6085");
        member_passwd.sendKeys("qudtn!4589");
        login.click();
        Thread.sleep(5000);
        repository.deleteAll();
        //19까지 함 APPLE
        driver.navigate().to("https://jncompany.shop/product/search.html?banner_action=&keyword=%EC%A7%B1%EA%B5%AC+%EC%BC%80%EC%9D%B4%EC%8A%A4");
        //변수
        List<String> urls = new ArrayList<>();

        //tcnt
        for(int i=1; i<=2;i++) {
        	List<WebElement> elements = driver.findElements(By.cssSelector("#contents > div.xans-element-.xans-search.xans-search-result.df-prl-wrap.df-prl-setup > ul > li > div > div.df-prl-thumb > a"));
        	for(WebElement ele : elements) {
        		System.out.println(ele.getAttribute("href"));
        		urls.add(ele.getAttribute("href"));
        	}
        	
    		driver.findElement(By.cssSelector("#contents > div.xans-element-.xans-search.xans-search-paging.ec-base-paginate > a:nth-child(4)")).click();
        }
        
        
        for(String url : urls) {
        	driver.navigate().to(url);
        	StringBuilder selectOption  = new StringBuilder();
            StringBuilder selectOption2 = new StringBuilder();
        	StringBuilder optionNo = new StringBuilder();
        	StringBuilder optionYn =new StringBuilder();
        	List<String> imgs = new ArrayList<>();
        	List<WebElement> thumbLists = driver.findElements(By.cssSelector("#df-product-detail > div > div.imgArea-wrap > div > div > div.thumbnail > span > img"));
        	
        	String fileName = null;
            for(WebElement thmb : thumbLists) {
        		String thbFormat = thmb.getAttribute("src").substring(thmb.getAttribute("src").length()-4,thmb.getAttribute("src").length());
        		if(thbFormat.equals(".jpg")||thbFormat.equals(".gif")||thbFormat.equals(".png")||thbFormat.equals(".jpeg")) {
        			String imgUrl = thmb.getAttribute("src");
        			fileName = thmb.getAttribute("src").substring(thmb.getAttribute("src").lastIndexOf("/") + 1);
        			fileName = URLDecoder.decode(fileName,"UTF-8").toString().substring(0, URLDecoder.decode(fileName,"UTF-8").length()-4);
        			String savePath ="C:\\Users\\15kso\\OneDrive\\바탕 화면\\새 폴더\\";
              		File afterFile = new File(savePath + URLDecoder.decode(fileName,"UTF-8")+".png");
              		saveThumbImage(imgUrl,savePath,afterFile);	
              		
        		}
            }
            List<WebElement> detailImageLists = driver.findElements(By.cssSelector("#prdDetail > div.cont > div > p > img"));
            List<WebElement> detailImageLists2 = driver.findElements(By.cssSelector("#prdDetail > div.cont > div > img"));
            List<WebElement> detailImageLists3 = driver.findElements(By.cssSelector("#prdDetail > div.cont > div > div > p > img"));
            List<WebElement> detailImageLists4 = driver.findElements(By.cssSelector("#prdDetail > div.cont > div > div > img"));
            
            StringBuilder sb =new StringBuilder();
            for(WebElement detailImage : detailImageLists) {
            	 ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView();", detailImage);
             	 String imgUrl = detailImage.getAttribute("src");
        		 imgs.add(imgUrl);
            }
            
            for(WebElement detailImage2 : detailImageLists2) {
		   		((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView();", detailImage2);
	   			 String imgUrl2 = detailImage2.getAttribute("src");
		   	 	 imgs.add(imgUrl2);
		   	}
		   	
		   	for(WebElement detailImage3 : detailImageLists3) {
		   		((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView();", detailImage3);
	   			 String imgUrl3 = detailImage3.getAttribute("src");
		   	 	 imgs.add(imgUrl3);
		   	}
            
		   	for(WebElement detailImage4 : detailImageLists4) {
		   		((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView();", detailImage4);
	   			 String imgUrl4 = detailImage4.getAttribute("src");
		   	 	 imgs.add(imgUrl4);
		   	}
		   	
		   	String detailImgs = null;
		   	for(String img : imgs) {
				 String imageUrl  = img;
				 String savePath ="C:\\Users\\15kso\\OneDrive\\바탕 화면\\detailimage\\";
				 detailImgs = img.substring(img.lastIndexOf("/") + 1);
				 String fileNames =  URLDecoder.decode(detailImgs,"UTF-8").toString().substring(0, URLDecoder.decode(detailImgs,"UTF-8").length()-4);
				 if(fileNames.length()<50) {
					 File afterFile = new File(savePath + fileNames +".jpeg");
					 sb.append("<p align=\"center\">"+"<img src='https://qudtn0295.cafe24.com/detailImage23/"+URLDecoder.decode(fileNames,"UTF-8").toString()+".jpeg"+"'/>"+"</p>");
					 saveDetailImage(imageUrl,savePath,afterFile);
				 }else {
					 String uuid2 = UUID.randomUUID().toString();
					 File afterFile = new File(savePath + uuid2 +".jpeg");
					 sb.append("<p align=\"center\">"+"<img src='https://qudtn0295.cafe24.com/detailImage23/"+uuid2.toString()+".jpeg"+"'/>"+"</p>");
					 saveDetailImage(imageUrl,savePath,afterFile);
				 }
			}
		   	
        	
        	//셀렉트박스는 두개값 만 가져옴
        	List<WebElement> selectLists = driver.findElements(By.cssSelector("#df-product-detail > div > div.infoArea-wrap > div > div > div.scroll-wrapper.df-detail-fixed-scroll.scrollbar-macosx > div.df-detail-fixed-scroll.scrollbar-macosx.scroll-content > ul > li > ul > li.content > select"));
    		for(WebElement sel : selectLists) {
        		
        		Select selectOptions = new Select(sel);
        		selectOptions.selectByIndex(2);
	        	List<WebElement> optionList = selectOptions.getOptions();
	        	optionList.remove(0);
	        	for(WebElement option : optionList) {
	        		if(sel.getAttribute("id").equals("product_option_id1")) {
		        		if(option.isEnabled()) {
		        			selectOption2.append(option.getText()+"|");
		        		}
	        		}else {
	        			if(option.isEnabled()) {
	        				selectOption.append(option.getText()+"|");
		        			if(option.getText().equals("Z플립4")) {
	    	        			optionNo.append("161|");
	    	        			optionYn.append("Y|");
	    	        		}else if(option.getText().equals("갤럭시Z폴드5(F946)")) {
	    	        			optionNo.append("226|");
	    	        			optionYn.append("Y|");
	    	        		}else if(option.getText().equals("갤럭시Z플립5(F731)")) {
	    	        			optionNo.append("225|");
	    	        			optionYn.append("Y|");
	    	        		}else if(option.getText().equals("갤럭시 Z플립 3")||option.getText().equals("Z플립3")) {
	    	        			optionNo.append("162|");
	    	        			optionYn.append("Y|");
	    	        		}else if(option.getText().equals("갤럭시 Z플립 1/2")) {
	    	        			optionNo.append("163|");
	    	        			optionYn.append("Y|");
	    	        		}else if(option.getText().equals("노트20 (SM-N981N) S시리즈아님_기종선택 유의해주세요")||option.getText().equals("갤럭시 노트20")) {
	    	        			optionNo.append("122|");
	    	        			optionYn.append("Y|");
	    	        		}else if(option.getText().equals("노트20 울트라 (SM-N986N) S시리즈아님_기종선택 유의해주세요")||option.getText().equals("갤럭시 노트20울트라")) {
	    	        			optionNo.append("123|");
	    	        			optionYn.append("Y|");
	    	        		}else if(option.getText().equals("노트 10")||option.getText().equals("갤럭시 노트10")) {
	    	        			optionNo.append("124|");
	    	        			optionYn.append("Y|");
	    	        		}else if(option.getText().equals("노트 10 플러스")||option.getText().equals("갤럭시 노트10플러스")) {
	    	        			optionNo.append("125|");
	    	        			optionYn.append("Y|");
	    	        		}else if(option.getText().equals("노트 9")||option.getText().equals("갤럭시 노트9")) {
	    	        			optionNo.append("126|");
	    	        			optionYn.append("Y|");
	    	        		}else if(option.getText().equals("갤럭시 S23")||option.getText().equals("갤럭시 S23")) {
	    	        			optionNo.append("129|");
	    	        			optionYn.append("Y|");
	    	        		}else if(option.getText().equals("갤럭시 S23플러스")||option.getText().equals("갤럭시 S23플러스")) {
	    	        			optionNo.append("130|");
	    	        			optionYn.append("Y|");
	    	        		}else if(option.getText().equals("갤럭시 S23울트라")||option.getText().equals("갤럭시 S23울트라")) {
	    	        			optionNo.append("131|");
	    	        			optionYn.append("Y|");
	    	        		}else if(option.getText().equals("갤럭시 S22")) {
	    	        			optionNo.append("132|");
	    	        			optionYn.append("Y|");
	    	        		}else if(option.getText().equals("갤럭시 S22플러스")) {
	    	        			optionNo.append("133|");
	    	        			optionYn.append("Y|");
	    	        		}else if(option.getText().equals("갤럭시 S22울트라")) {
	    	        			optionNo.append("134|");
	    	        			optionYn.append("Y|");
	    	        		}else if(option.getText().equals("갤럭시 S21")) {
	    	        			optionNo.append("135|");
	    	        			optionYn.append("Y|");
	    	        		}else if(option.getText().equals("갤럭시 S21플러스")) {
	    	        			optionNo.append("136|");
	    	        			optionYn.append("Y|");
	    	        		}else if(option.getText().equals("갤럭시 S21울트라")) {
	    	        			optionNo.append("137|");
	    	        			optionYn.append("Y|");
	    	        		}else if(option.getText().equals("갤럭시 S20 (SM-G981N) 노트시리즈아님_기종선택 유의해주세요")||option.getText().equals("갤럭시 S20")) {
	    	        			optionNo.append("138|");
	    	        			optionYn.append("Y|");
	    	        		}else if(option.getText().equals("갤럭시 S20플러스 (SM-G986N) 노트시리즈아님_기종선택 유의해주세요")||option.getText().equals("갤럭시 S20플러스")) {
	    	        			optionNo.append("139|");
	    	        			optionYn.append("Y|");
	    	        		}else if(option.getText().equals("갤럭시 S20울트라 (SM-G988N) 노트시리즈아님_기종선택 유의해주세요")||option.getText().equals("갤럭시 S20울트라")) {
	    	        			optionNo.append("140|");
	    	        			optionYn.append("Y|");
	    	        		}else if(option.getText().equals("갤럭시S10E")) {
	    	        			optionNo.append("144|");
	    	        			optionYn.append("Y|");
	    	        		}else if(option.getText().equals("갤럭시S10")) {
	    	        			optionNo.append("142|");
	    	        			optionYn.append("Y|");
	    	        		}else if(option.getText().equals("갤럭시S10플러스")) {
	    	        			optionNo.append("143|");
	    	        			optionYn.append("Y|");
	    	        		}else if(option.getText().equals("갤럭시 S10 5G")) {
	    	        			optionNo.append("145|");
	    	        			optionYn.append("Y|");
	    	        		}else if(option.getText().equals("아이폰15")) {
	    	        			optionNo.append("227|");
	    	        			optionYn.append("Y|");
	    	        		}else if(option.getText().equals("아이폰 15플러스")) {
	    	        			optionNo.append("228|");
	    	        			optionYn.append("Y|");
	    	        		}else if(option.getText().equals("아이폰 15프로")) {
	    	        			optionNo.append("229|");
	    	        			optionYn.append("Y|");
	    	        		}else if(option.getText().equals("아이폰 15프로맥스")) {
	    	        			optionNo.append("230|");
	    	        			optionYn.append("Y|");
	    	        		}else if(option.getText().equals("아이폰 14")) {
	    	        			optionNo.append("102|");
	    	        			optionYn.append("Y|");
	    	        		}else if(option.getText().equals("아이폰 14플러스")) {
	    	        			optionNo.append("101|");
	    	        			optionYn.append("Y|");
	    	        		}else if(option.getText().equals("아이폰 14프로")) {
	    	        			optionNo.append("100|");
	    	        			optionYn.append("Y|");
	    	        		}else if(option.getText().equals("아이폰 14프로맥스")) {
	    	        			optionNo.append("99|");
	    	        			optionYn.append("Y|");
	    	        		}else if(option.getText().equals("아이폰 13미니")) {
	    	        			optionNo.append("107|");
	    	        			optionYn.append("Y|");
	    	        		}else if(option.getText().equals("아이폰 13")) {
	    	        			optionNo.append("106|");
	    	        			optionYn.append("Y|");
	    	        		}else if(option.getText().equals("아이폰 13프로")) {
	    	        			optionNo.append("105|");
	    	        			optionYn.append("Y|");
	    	        		}else if(option.getText().equals("아이폰 13프로맥스")) {
	    	        			optionNo.append("103|");
	    	        			optionYn.append("Y|");
	    	        		}else if(option.getText().equals("아이폰 12미니(5.4)")) {
	    	        			optionNo.append("110|");
	    	        			optionYn.append("Y|");
	    	        		}else if(option.getText().equals("아이폰 12/아이폰 12프로(6.1)")) {
	    	        			optionNo.append("109|");
	    	        			optionYn.append("Y|");
	    	        		}else if(option.getText().equals("아이폰 12프로맥스(6.7)")) {
	    	        			optionNo.append("108|");
	    	        			optionYn.append("Y|");
	    	        		}else if(option.getText().equals("아이폰 11")) {
	    	        			optionNo.append("113|");
	    	        			optionYn.append("Y|");
	    	        		}else if(option.getText().equals("아이폰 11pro")) {
	    	        			optionNo.append("112|");
	    	        			optionYn.append("Y|");
	    	        		}else if(option.getText().equals("아이폰 11pro MAX")) {
	    	        			optionNo.append("111|");
	    	        			optionYn.append("Y|");
	    	        		}
		        		}
	        		}
	        	}
    		}
    		
            StringBuilder selectBoxEnabled = new StringBuilder();
            for(int k=1;k<=selectLists.size();k++) {
            	selectBoxEnabled.append("F|");
            }
            
            WebElement title=driver.findElement(By.cssSelector("#df-product-detail > div > div.infoArea-wrap > div > div > div.scroll-wrapper.df-detail-fixed-scroll.scrollbar-macosx > div.df-detail-fixed-scroll.scrollbar-macosx.scroll-content > div.headingArea > h2"));
        	WebElement price=driver.findElement(By.cssSelector("#df-product-detail > div > div.infoArea-wrap > div > div > div.scroll-wrapper.df-detail-fixed-scroll.scrollbar-macosx > div.df-detail-fixed-scroll.scrollbar-macosx.scroll-content > div.xans-element-.xans-product.xans-product-detaildesign > table > tbody > tr.product_price_css.xans-record- > td > span:nth-child(1) > strong"));
        	
        	String rePrice = price.getText();
        	rePrice = rePrice.replace(",","");
        	rePrice = rePrice.replace("원","");
        	int reInPrice= Integer.parseInt(rePrice);
        	if(reInPrice<10000) {
        		reInPrice = reInPrice+9000;
        		rePrice = Integer.toString(reInPrice); 
        	}else {
        		reInPrice = reInPrice+10000;
        		rePrice = Integer.toString(reInPrice); 
        	}
        	
        	
        	String reTitle = title.getText();
        	String reOption = selectOption.toString();
        	
        	//플립
        	reOption = reOption.replace("(6.1)","");
        	reOption = reOption.replace("(5.4)","");
        	reOption = reOption.replace("(6.7)","");
        	reOption = reOption.replace(" (SM-G988N) 노트시리즈아님_기종선택 유의해주세요","");
        	reOption = reOption.replace(" (SM-G986N) 노트시리즈아님_기종선택 유의해주세요","");
        	reOption = reOption.replace(" (SM-G981N) 노트시리즈아님_기종선택 유의해주세요","");
        	reOption = reOption.replace(" (SM-N981N) S시리즈아님_기종선택 유의해주세요","");
        	reOption = reOption.replace(" (SM-N986N) S시리즈아님_기종선택 유의해주세요","");
        	
        	Product products = new Product();
        	products.setTitle(reTitle);
        	if(!selectOption2.toString().equals("") && !selectOption.toString().equals("")) {
        		products.setOption("옵션1{"+selectOption2.substring(0, selectOption2.toString().length()-1)+"}"+"//옵션2{"+reOption.substring(0, reOption.length()-1)+"}");
        	}else if(!selectOption2.toString().equals("") && selectOption.toString().equals("")){
        		products.setOption("옵션1{"+selectOption2.substring(0, selectOption2.toString().length()-1)+"}");
        	}
        	products.setPrice(rePrice);
        	products.setCompany("제이앤컴퍼니");
        	products.setSelectBoxEnabled(selectBoxEnabled.toString().substring(0, selectBoxEnabled.toString().length()-1));
        	
        	if(!optionNo.toString().equals("") && !optionYn.toString().equals("")) {
	        	products.setOptionNo(optionNo.toString().substring(0, optionNo.length()-1));
	        	products.setOptionEnabled(optionYn.toString().substring(0, optionYn.toString().length()-1));
        	}
        	products.setDetailImg(sb.toString());
        	products.setThmb(fileName+".png");
        	
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
	    	cell.setCellValue(p.getDetailImg());
	    	cell = row.createCell(9);
	    	cell.setCellValue("/thmb23/"+p.getThmb());
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
			
			Image resizeImage = bi.getScaledInstance(bi.getWidth(), bi.getHeight(), Image.SCALE_SMOOTH);
			BufferedImage newImage = new BufferedImage(bi.getWidth(), bi.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics g = newImage.getGraphics();
        	g.drawImage(resizeImage, 0, 0, null);
            g.dispose();
			ImageIO.write(newImage, "jpeg", afterFile);
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