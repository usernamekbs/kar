package com.kick;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
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
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kick.entity.Product;
import com.kick.entity.ProductRepository;

import jakarta.xml.bind.DatatypeConverter;

@SpringBootTest
class Cafe24Soso {
    
	@Autowired ProductRepository repository;
	
	public static final String filePath= "C:\\Users\\15kso\\OneDrive\\바탕 화면\\새 폴더\\옵션.xlsx";
	List<String> selectCountOption = new ArrayList<>();
	/////////////////////////CAFE24 쿠팡////////////////////////////
	@Test
	//test 코드에서 transactional 사용 하지 마라
	void asd() throws InterruptedException, IOException {
		EdgeDriver driver;
	 	EdgeOptions options = new EdgeOptions();
	 	options.addArguments("--window-size=1400,1300");
        driver = new EdgeDriver(options);
        
	    driver.get("https://sosohome.co.kr/member/login.html");
        WebElement member_id=driver.findElement(By.id("member_id"));
        WebElement member_passwd=driver.findElement(By.id("member_passwd"));
        WebElement login=driver.findElement(By.cssSelector("form > div > div > fieldset > a"));
        member_id.sendKeys("qudtn0295");
        member_passwd.sendKeys("qudtn!4589");
        login.click();
        Thread.sleep(5000);
        repository.deleteAll();
        //19까지 함 APPLE
        driver.navigate().to("https://sosohome.co.kr/product/list.html?cate_no=42");
        
        //변수
        List<String> urls = new ArrayList<>();
        //tcnt
        for(int i=2; i<=9;i++) {
        	WebElement nextPage=driver.findElement(By.cssSelector("#contents > div.xans-element-.xans-product.xans-product-normalpaging.ec-base-paginate > a:nth-child(4)"));
        	
        	List<WebElement> elements = driver.findElements(By.cssSelector("ul > li > div.description > p.name > a"));
        	
        	for(WebElement ele : elements) {
        		urls.add(ele.getAttribute("href"));
        	}
        	nextPage.click();
        }
        
        for(String url : urls) {
        	driver.navigate().to(url);
        	JavascriptExecutor js = (JavascriptExecutor) driver;
        	js.executeScript("window.scrollBy(0,600)");
        	//셀렉트박스는 두개값 만 가져옴
        	List<WebElement> selectLists = driver.findElements(By.cssSelector("#contents > div.xans-element-.xans-product.xans-product-detail > div.detailArea > div.infoArea > div.buy_wrap.top > div.buy_box > div > div > div > div.scrollbar_box.op_list_h > table > tbody.xans-element-.xans-product.xans-product-option.xans-record- > tr >td > select"));
        	StringBuilder selectOption = new StringBuilder();
        	StringBuilder selectOption2 = new StringBuilder();
        	StringBuilder optionNo = new StringBuilder();
        	StringBuilder optionYn =new StringBuilder();
        	List<String> imgs = new ArrayList<>();
        	List<WebElement> thumbLists = driver.findElements(By.cssSelector("#contents > div.xans-element-.xans-product.xans-product-detail > div.detailArea > div.xans-element-.xans-product.xans-product-image.imgArea > div.keyImg.item > div.thumbnail > a > img"));
        	
        	List<WebElement> detailImageLists = driver.findElements(By.cssSelector("#prdDetail > div.cont > p > img"));
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

            String detailName = null;
            StringBuilder sb =new StringBuilder();
            for(WebElement detailImage : detailImageLists) {
            	System.out.println("==============>"+detailImage.getAttribute("src"));
            	 String imgUrl = detailImage.getAttribute("src");
            	 
            	 detailName = detailImage.getAttribute("src").substring(detailImage.getAttribute("src").lastIndexOf("/") + 1);
        		 String detailImg = "<p align='center'>"+"<img src='https://qudtn0295.cafe24.com/detailImage9/"+URLDecoder.decode(detailName,"UTF-8").toString().substring(0, URLDecoder.decode(detailName,"UTF-8").length()-4)+".jpeg"+"'/>"+"</p>";
        		 sb.append(detailImg);
        		 imgs.add(imgUrl);
        		 
            }
            
            
		   	String detailImgs = null;
		   	for(String img : imgs) {
				 String imageUrl  = img;
				 String savePath ="C:\\Users\\15kso\\OneDrive\\바탕 화면\\detailimage\\";
				 detailImgs = img.substring(img.lastIndexOf("/") + 1);
				 File afterFile = new File(savePath + URLDecoder.decode(detailImgs,"UTF-8").toString().substring(0, URLDecoder.decode(detailImgs,"UTF-8").length()-4)+".jpeg");
				 
				 saveDetailImage(imageUrl,savePath,afterFile);
			}
		   	
            StringBuilder selectBoxEnabled = new StringBuilder();
            for(int k=1;k<=selectLists.size();k++) {
            	selectBoxEnabled.append("F|");
            }
            
//        	//상세페이지에있는 제목 가격 온라인판매가 제조사
        	WebElement title=driver.findElement(By.cssSelector("#contents > div.xans-element-.xans-product.xans-product-detail > div.detailArea > div.infoArea > h2"));
        	WebElement realPrice=driver.findElement(By.cssSelector("#contents > div.xans-element-.xans-product.xans-product-detail > div.detailArea > div.infoArea > div.xans-element-.xans-product.xans-product-detaildesign > table > tbody > tr:nth-child(5) > td > span > span"));
        	WebElement price=driver.findElement(By.cssSelector("#contents > div.xans-element-.xans-product.xans-product-detail > div.detailArea > div.infoArea > div.xans-element-.xans-product.xans-product-detaildesign > table > tbody > tr.price.xans-record- > td >span > strong"));
        	//        	//셀렉트
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
	        				System.out.println("option2::::::::::::::::::"+option.getText());
		        			selectOption2.append(option.getText()+"|");
		        			
	        			}
	        		}
	        	}
        	}
        	String rePrice = price.getText();
        	rePrice = rePrice.replace(",","");
        	rePrice = rePrice.replace("원","");
        	int reInPrice= Integer.parseInt(rePrice);
        	if(reInPrice<10000) {
        		reInPrice = reInPrice+7500;
        	}else {
        		reInPrice = reInPrice+8500;
        	}
        	String reRealPrice = realPrice.getText();
        	reRealPrice = reRealPrice.replace(",", "");
        	reRealPrice = reRealPrice.replace("원", "");
        	String reTitle = title.getText();
        	
        	
        	Product products = new Product();
        	products.setTitle(reTitle);
        	
        	if(!selectOption2.toString().equals("") && !selectOption.toString().equals("")) {
        		products.setOption("옵션1{"+selectOption.substring(0, selectOption.toString().length()-1)+"}"+"//옵션2{"+selectOption2.substring(0, selectOption2.length()-1)+"}");
        	}else if(selectOption2.toString().equals("") && !selectOption.toString().equals("")){
        		products.setOption("옵션1{"+selectOption.substring(0, selectOption.toString().length()-1)+"}");
        	}
        	System.out.println(products.getOption());
        	products.setPrice(rePrice);
        	products.setRealPrice(reRealPrice);
        	products.setCompany("트라이코지");
        	products.setSelectBoxEnabled("Y");
//        	products.setOptionNo("Y");
        	products.setOptionEnabled("Y");
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
	    	cell.setCellValue("디자인테리어");
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
	    	cell.setCellValue(p.getDetailImg()+p.getDetailImg2());
	    	cell = row.createCell(9);
	    	cell.setCellValue("/thmb7/"+p.getThmb());
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
