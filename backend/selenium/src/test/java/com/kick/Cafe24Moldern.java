package com.kick;

import static org.mockito.ArgumentMatchers.intThat;

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
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.poi.util.SystemOutLogger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kick.entity.Product;
import com.kick.entity.ProductRepository;

@SpringBootTest
class Cafe24Moldern {
    
	@Autowired ProductRepository repository;
	
	public static final String filePath= "C:\\Users\\15kso\\OneDrive\\바탕 화면\\새 폴더\\옵션.xlsx";
	List<String> selectCountOption = new ArrayList<>();
	/////////////////////////CAFE24 모던올////////////////////////////
	@Test
	//test 코드에서 transactional 사용 하지 마라
	void asd() throws InterruptedException, IOException {
		EdgeDriver driver;
	 	EdgeOptions options = new EdgeOptions();
	 	options.addArguments("--window-size=1400,600");
        driver = new EdgeDriver(options);
        
	    driver.get("https://www.moldern.co.kr/login");
        WebElement member_id=driver.findElement(By.name("uid"));
        WebElement member_passwd=driver.findElement(By.name("passwd"));
        member_id.sendKeys("qudtn6085@naver.com");
        member_passwd.sendKeys("qudtn!4589");
        //윈도우 사이즈 신경좀 쓰자 윈도우 사이즈 신경안쓰면 버튼 안눌림.
        driver.findElement(By.cssSelector("div > div > div > form > p > button.btn.btn-primary.btn-block")).click();
        Thread.sleep(5000);
        repository.deleteAll();
        //19까지 함 APPLE
        driver.navigate().to("https://www.moldern.co.kr/30");
        
        //변수
        List<String> urls = new ArrayList<>();
        //tcnt
        for(int i=1; i<=1;i++) {
        	
        	List<WebElement> elements = driver.findElements(By.cssSelector("div > div > div.item-wrap > a"));
        	
        	for(WebElement ele : elements) {
        		System.out.println(ele.getAttribute("href"));
        		urls.add(ele.getAttribute("href"));
        	}
//        	driver.findElement(By.cssSelector("div > div > div > nav > ul > li:nth-child(7) > a.disabled")).click();
        }
        
        for(String url : urls) {
        	driver.navigate().to(url);
        	driver.findElement(By.cssSelector("#prod_goods_form > div.buy_btns.pc.clearfix.talkpay_pc > div > div > div > div.float_l.holder > a.btn.defualt.buy.bg-brand")).click();
        	Thread.sleep(5000);
        	List<WebElement> op =driver.findElements(By.cssSelector("#prod_options > div > div > div > div > div > span.blocked margin-bottom-lg"));
        	
        	
        	for(WebElement o : op) {
        		System.out.println("option:::"+o.getText());
        	}
        	List<WebElement> in =driver.findElements(By.cssSelector("#prod_options > div > div > div.txt_l > label"));
        	for(WebElement i : in) {
        		String color = i.getAttribute("data-title");
        		System.out.println("color:::"+color);
        	}
        	//        	#prod_options > div > div > div.txt_l > label
//        	if(!driver.findElements(By.id("#prod_options > div > div > div.txt_l > label")).isEmpty()) {
//	        	System.out.println("!!!!!!!!!!!!!!");
//        		WebElement option = driver.findElement(By.cssSelector("#prod_options > div > div > div.txt_l > label"));
//	        	String optionTitle = option.getAttribute("title");
//	        	System.out.println(optionTitle);
//        	}else if(!driver.findElements(By.cssSelector("#prod_options > div > div > div > div.dropdown-menu")).isEmpty()) {
////	        	List<WebElement> optionList = driver.findElements(By.cssSelector("#prod_options > div > div > div > div.dropdown-menu"));
////	        	for(WebElement option : optionList) {
////	        		System.out.println(option.getText());
////	        	}
//        	}else {
//        		System.out.println("!!!!!!!없어!!!!!!!!!!!!");
//        	}
        	//셀렉트박스는 두개값 만 가져옴
//        	List<WebElement> selectLists = driver.findElements(By.cssSelector("#contents > div.xans-element-.xans-product.xans-product-detail > div.detailArea > div.infoArea > table:nth-child(10) > tbody > tr > td > select"));
//        	StringBuilder selectOption = new StringBuilder();
//        	StringBuilder selectOption2 = new StringBuilder();
//        	StringBuilder optionNo = new StringBuilder();
//        	StringBuilder optionYn =new StringBuilder();
//        	List<String> imgs = new ArrayList<>();
//        	List<WebElement> thumbLists = driver.findElements(By.cssSelector("#contents > div.xans-element-.xans-product.xans-product-detail > div.detailArea > div.xans-element-.xans-product.xans-product-image.imgArea > div.keyImg > a > img.BigImage"));
//        	
//        	List<WebElement> detailImageLists = driver.findElements(By.cssSelector("#prdDetail > div > p > img"));
//            List<WebElement> detailImageLists2 = driver.findElements(By.cssSelector("#prdDetail > div > p > a > img"));
//            String fileName = null;
//            for(WebElement thmb : thumbLists) {
//        		String thbFormat = thmb.getAttribute("src").substring(thmb.getAttribute("src").length()-4,thmb.getAttribute("src").length());
//        		if(thbFormat.equals(".jpg")||thbFormat.equals(".gif")||thbFormat.equals(".png")||thbFormat.equals(".jpeg")) {
//        			String imgUrl = thmb.getAttribute("src");
//        			fileName = thmb.getAttribute("src").substring(thmb.getAttribute("src").lastIndexOf("/") + 1);
//        			fileName = URLDecoder.decode(fileName,"UTF-8").toString().substring(0, URLDecoder.decode(fileName,"UTF-8").length()-4);
//        			String savePath ="C:\\Users\\15kso\\OneDrive\\바탕 화면\\새 폴더\\";
//              		File afterFile = new File(savePath + URLDecoder.decode(fileName,"UTF-8")+".png");
//              		saveThumbImage(imgUrl,savePath,afterFile);	
//              		
//        		}
//            }
//
//            String detailName = null;
//            StringBuilder sb =new StringBuilder();
//            for(WebElement detailImage : detailImageLists) {
//            	 String imgUrl = detailImage.getAttribute("src");
//       		     detailName = detailImage.getAttribute("src").substring(detailImage.getAttribute("src").lastIndexOf("/") + 1);
//        		 String detailImg = "<p align=\"center\">"+"<img src='https://qudtn0295.cafe24.com/detailImage7/"+URLDecoder.decode(detailName,"UTF-8").toString().substring(0, URLDecoder.decode(detailName,"UTF-8").length()-4)+".jpeg"+"'/>"+"</p>";
//        		 sb.append(detailImg);
//        		 imgs.add(imgUrl);
//            }
//            
//            String detailName2 = null;
//            StringBuilder sb2 =new StringBuilder();
//		   	for(WebElement detailImage2 : detailImageLists2) {
//		   		 String imgUrl2 = detailImage2.getAttribute("src");
//		   	 	 detailName2 = detailImage2.getAttribute("src").substring(detailImage2.getAttribute("src").lastIndexOf("/") + 1);
//		   	 	 String detailImg2 = "<p align=\"center\">"+"<img src='https://qudtn0295.cafe24.com/detailImage7/"+URLDecoder.decode(detailName2,"UTF-8").toString().substring(0, URLDecoder.decode(detailName2,"UTF-8").length()-4)+".jpeg"+"'/>"+"</p>";
//		   	 	 sb2.append(detailImg2);
//		   	 	 imgs.add(imgUrl2);
//		   	}
//		   	
//		   	String detailImgs = null;
//		   	for(String img : imgs) {
//				 String imageUrl  = img;
//				 String savePath ="C:\\Users\\15kso\\OneDrive\\바탕 화면\\detailimage\\";
//				 detailImgs = img.substring(img.lastIndexOf("/") + 1);
//				 File afterFile = new File(savePath + URLDecoder.decode(detailImgs,"UTF-8").toString().substring(0, URLDecoder.decode(detailImgs,"UTF-8").length()-4)+".jpeg");
//				 saveDetailImage(imageUrl,savePath,afterFile);
//			}
//		   	
//            StringBuilder selectBoxEnabled = new StringBuilder();
//            for(int k=1;k<=selectLists.size();k++) {
//            	selectBoxEnabled.append("F|");
//            }
//        	//상세페이지에있는 제목 가격 온라인판매가 제조사
//        	WebElement title=driver.findElement(By.cssSelector("#contents > div.xans-element-.xans-product.xans-product-detail > div.detailArea > div.infoArea > h2"));
//        	WebElement realPrice=driver.findElement(By.xpath("//*[@id=\"contents\"]/div[2]/div[2]/div[2]/div[3]/table/tbody/tr[4]/td/span"));
//        	WebElement price=driver.findElement(By.cssSelector("#contents > div.xans-element-.xans-product.xans-product-detail > div.detailArea > div.infoArea > div.xans-element-.xans-product.xans-product-detaildesign > table > tbody > tr:nth-child(2) > td > strong > span > strong"));
//        	
//        	//셀렉트
//        	for(WebElement sel : selectLists) {
//        		
//        		Select selectOptions = new Select(sel);
//        		selectOptions.selectByIndex(2);
//	        	List<WebElement> optionList = selectOptions.getOptions();
//	        	optionList.remove(0);
//	        	
//	        	for(WebElement option : optionList) {
//	        		if(sel.getAttribute("id").equals("product_option_id1")) {
//	        			//optionList에서 개수를 제한하는것도 방법일듯함..
//	        			if(option.isEnabled()) {
//		        			selectOption.append(option.getText()+"|");
//		        			if(option.getText().equals("갤럭시Z플립4(F721)")||option.getText().equals("갤럭시Z플립3/4(F711/F721)")) {
//	    	        			optionNo.append("161|");
//	    	        			optionYn.append("Y|");
//	    	        		}else if(option.getText().equals("갤럭시Z폴드5(F946)")) {
//	    	        			optionNo.append("226|");
//	    	        			optionYn.append("Y|");
//	    	        		}else if(option.getText().equals("갤럭시Z플립5(F731)")) {
//	    	        			optionNo.append("225|");
//	    	        			optionYn.append("Y|");
//	    	        		}else if(option.getText().equals("갤럭시Z플립3(F711)")||option.getText().equals("갤럭시Z플립3/4(F711/F721)")) {
//	    	        			optionNo.append("162|");
//	    	        			optionYn.append("Y|");
//	    	        		}else if(option.getText().equals("갤럭시Z플립(F700)")) {
//	    	        			optionNo.append("163|");
//	    	        			optionYn.append("Y|");
//	    	        		}else if(option.getText().equals("갤럭시노트20(N981)")) {
//	    	        			optionNo.append("122|");
//	    	        			optionYn.append("Y|");
//	    	        		}else if(option.getText().equals("갤럭시노트20울트라(N986)")) {
//	    	        			optionNo.append("123|");
//	    	        			optionYn.append("Y|");
//	    	        		}else if(option.getText().equals("갤럭시노트10(N971)")) {
//	    	        			optionNo.append("124|");
//	    	        			optionYn.append("Y|");
//	    	        		}else if(option.getText().equals("갤럭시노트10플러스(N976)")) {
//	    	        			optionNo.append("125|");
//	    	        			optionYn.append("Y|");
//	    	        		}else if(option.getText().equals("갤럭시노트9(N960)")) {
//	    	        			optionNo.append("126|");
//	    	        			optionYn.append("Y|");
//	    	        		}else if(option.getText().equals("갤럭시S23(S911)")) {
//	    	        			optionNo.append("129|");
//	    	        			optionYn.append("Y|");
//	    	        		}else if(option.getText().equals("갤럭시S23플러스(S916)")) {
//	    	        			optionNo.append("130|");
//	    	        			optionYn.append("Y|");
//	    	        		}else if(option.getText().equals("갤럭시S23울트라(S918)")) {
//	    	        			optionNo.append("131|");
//	    	        			optionYn.append("Y|");
//	    	        		}else if(option.getText().equals("갤럭시S22(S901)")) {
//	    	        			optionNo.append("132|");
//	    	        			optionYn.append("Y|");
//	    	        		}else if(option.getText().equals("갤럭시S22플러스(S906)")) {
//	    	        			optionNo.append("133|");
//	    	        			optionYn.append("Y|");
//	    	        		}else if(option.getText().equals("갤럭시S22울트라(S908)")) {
//	    	        			optionNo.append("134|");
//	    	        			optionYn.append("Y|");
//	    	        		}else if(option.getText().equals("갤럭시S21(G991)")) {
//	    	        			optionNo.append("135|");
//	    	        			optionYn.append("Y|");
//	    	        		}else if(option.getText().equals("갤럭시S21플러스(G996)")) {
//	    	        			optionNo.append("136|");
//	    	        			optionYn.append("Y|");
//	    	        		}else if(option.getText().equals("갤럭시S21울트라(G998)")) {
//	    	        			optionNo.append("137|");
//	    	        			optionYn.append("Y|");
//	    	        		}else if(option.getText().equals("갤럭시S20(G981)")) {
//	    	        			optionNo.append("138|");
//	    	        			optionYn.append("Y|");
//	    	        		}else if(option.getText().equals("갤럭시S20플러스(G986)")) {
//	    	        			optionNo.append("139|");
//	    	        			optionYn.append("Y|");
//	    	        		}else if(option.getText().equals("갤럭시S20울트라(G988)")) {
//	    	        			optionNo.append("140|");
//	    	        			optionYn.append("Y|");
//	    	        		}else if(option.getText().equals("갤럭시S10E(G970)")) {
//	    	        			optionNo.append("144|");
//	    	        			optionYn.append("Y|");
//	    	        		}else if(option.getText().equals("갤럭시S10(G973)")) {
//	    	        			optionNo.append("142|");
//	    	        			optionYn.append("Y|");
//	    	        		}else if(option.getText().equals("갤럭시S10플러스(G975)")) {
//	    	        			optionNo.append("143|");
//	    	        			optionYn.append("Y|");
//	    	        		}else if(option.getText().equals("갤럭시S10 5G(G977)")) {
//	    	        			optionNo.append("145|");
//	    	        			optionYn.append("Y|");
//	    	        		}else if(option.getText().equals("아이폰15")) {
//	    	        			optionNo.append("227|");
//	    	        			optionYn.append("Y|");
//	    	        		}else if(option.getText().equals("아이폰15플러스")) {
//	    	        			optionNo.append("228|");
//	    	        			optionYn.append("Y|");
//	    	        		}else if(option.getText().equals("아이폰15프로")) {
//	    	        			optionNo.append("229|");
//	    	        			optionYn.append("Y|");
//	    	        		}else if(option.getText().equals("아이폰15프로맥스")) {
//	    	        			optionNo.append("230|");
//	    	        			optionYn.append("Y|");
//	    	        		}else if(option.getText().equals("아이폰14")) {
//	    	        			optionNo.append("102|");
//	    	        			optionYn.append("Y|");
//	    	        		}else if(option.getText().equals("아이폰14플러스")) {
//	    	        			optionNo.append("101|");
//	    	        			optionYn.append("Y|");
//	    	        		}else if(option.getText().equals("아이폰14프로")) {
//	    	        			optionNo.append("100|");
//	    	        			optionYn.append("Y|");
//	    	        		}else if(option.getText().equals("아이폰14프로맥스")) {
//	    	        			optionNo.append("99|");
//	    	        			optionYn.append("Y|");
//	    	        		}else if(option.getText().equals("아이폰13미니")) {
//	    	        			optionNo.append("107|");
//	    	        			optionYn.append("Y|");
//	    	        		}else if(option.getText().equals("아이폰13")) {
//	    	        			optionNo.append("106|");
//	    	        			optionYn.append("Y|");
//	    	        		}else if(option.getText().equals("아이폰13프로")) {
//	    	        			optionNo.append("105|");
//	    	        			optionYn.append("Y|");
//	    	        		}else if(option.getText().equals("아이폰13프로맥스")) {
//	    	        			optionNo.append("103|");
//	    	        			optionYn.append("Y|");
//	    	        		}else if(option.getText().equals("아이폰12미니")) {
//	    	        			optionNo.append("110|");
//	    	        			optionYn.append("Y|");
//	    	        		}else if(option.getText().equals("아이폰12/12프로")) {
//	    	        			optionNo.append("109|");
//	    	        			optionYn.append("Y|");
//	    	        		}else if(option.getText().equals("아이폰12프로맥스")) {
//	    	        			optionNo.append("108|");
//	    	        			optionYn.append("Y|");
//	    	        		}else if(option.getText().equals("아이폰11(6.1)")) {
//	    	        			optionNo.append("113|");
//	    	        			optionYn.append("Y|");
//	    	        		}else if(option.getText().equals("아이폰11 Pro(5.8)")) {
//	    	        			optionNo.append("112|");
//	    	        			optionYn.append("Y|");
//	    	        		}else if(option.getText().equals("아이폰11 Pro Max(6.5)")) {
//	    	        			optionNo.append("111|");
//	    	        			optionYn.append("Y|");
//	    	        		}
//	        			}
//	        		}else {
//	        			if(option.isEnabled()) {
//		        			selectOption2.append(option.getText()+"|");
//		        			
//	        			}
//	        		}
//	        	}
//        	}
//        	
//        	String uuid = UUID.randomUUID().toString();
//        	String rePrice = price.getText();
//        	rePrice = rePrice.replace(",","");
//        	rePrice = rePrice.replace("원","");
//        	int reInPrice= Integer.parseInt(rePrice);
//        	if(reInPrice<10000) {
//        		reInPrice = reInPrice+7500;
//        	}else {
//        		reInPrice = reInPrice+8500;
//        	}
//        	String reRealPrice = realPrice.getText();
//        	reRealPrice = reRealPrice.replace(",", "");
//        	reRealPrice = reRealPrice.replace("원 이상", "");
//        	reRealPrice = reRealPrice.replace("㈜트라이코지", rePrice);
//        	reRealPrice = Integer.toString(reInPrice); 
//        	String reTitle = title.getText();
//        	
//        	Product products = new Product();
//        	products.setTitle(reTitle);
//        	products.setOption("옵션1{"+selectOption.toString().substring(0, selectOption.toString().length()-1)+"}"+"//옵션2{"+selectOption2.substring(0, selectOption2.length()-1)+"}");
//        	products.setPrice(rePrice);
//        	products.setRealPrice(reRealPrice);
//        	products.setCompany("트라이코지");
//        	products.setUuid(uuid);
//        	products.setSelectBoxEnabled(selectBoxEnabled.toString().substring(0, selectBoxEnabled.toString().length()-1));
//        	products.setOptionNo(optionNo.toString().substring(0, optionNo.toString().length()-1));
//        	products.setOptionEnabled(optionYn.toString().substring(0, optionYn.toString().length()-1));
//        	products.setDetailImg(sb.toString());
//        	products.setDetailImg2(sb2.toString());
//        	products.setThmb(fileName+".png");
//        	
//        	repository.save(products);
        	
        	
        }
        
	}
	//이미지 읽을대 뒤에 확장자명 곡 붙여줘야함..
//	@Test
//	public void excel() throws InterruptedException, IOException {
//		int i=0;
//		 //워크북 생성
//	    XSSFWorkbook workbook = new XSSFWorkbook();
//	    //워크시트 생성
//	    XSSFSheet sheet = workbook.createSheet();
//	    //행 생성
//	    XSSFRow row = sheet.createRow(0);
//	    //셀 생성
//	    XSSFCell cell;
//	    cell = row.createCell(0);
//	    cell.setCellValue("제목");
//	    cell = row.createCell(1);
//	    cell.setCellValue("제조사");
//	    cell = row.createCell(2);
//	    cell.setCellValue("도매가");
//	    cell = row.createCell(3);
//	    cell.setCellValue("온라인 판매가");
//	    cell = row.createCell(4);
//	    cell.setCellValue("상품분류 번호");
//	    cell = row.createCell(5);
//	    cell.setCellValue("상품신상품 영역");
//	    cell = row.createCell(6);
//	    cell.setCellValue("옵션");
//	    cell = row.createCell(7);
//	    cell.setCellValue("옵션사용여부");
//	    cell = row.createCell(8);
//	    cell.setCellValue("상세페이지 이미지");
//	    cell = row.createCell(9);
//	    cell.setCellValue("THMB");
//	    List<Product> products = repository.findAll();
//	    
////	    long count = repository.count();
//	    for(Product p : products) {
//	    	row = sheet.createRow(i+1);
//	    	cell = row.createCell(0);
//	    	cell.setCellValue(p.getTitle());
//	    	cell = row.createCell(1);
//	    	cell.setCellValue("커버핏");
//	    	cell = row.createCell(2);
//	    	cell.setCellValue(p.getPrice());
//	    	cell = row.createCell(3);
//	    	cell.setCellValue(p.getRealPrice());
//	    	cell = row.createCell(4);
//	    	cell.setCellValue(p.getOptionEnabled());
//	    	cell = row.createCell(5);
//	    	cell.setCellValue(p.getOptionNo());
//	    	cell = row.createCell(6);
//	    	cell.setCellValue(p.getOption());
//	    	cell = row.createCell(7);
//	    	cell.setCellValue(p.getSelectBoxEnabled());
//	    	cell = row.createCell(8);
//	    	cell.setCellValue(p.getDetailImg()+p.getDetailImg2());
//	    	cell = row.createCell(9);
//	    	cell.setCellValue("/thmb3/"+p.getThmb());
//	    	i++;
//	    }
//	   
//		File file = new File("C:\\Users\\15kso\\OneDrive\\바탕 화면\\새 폴더\\옵션.xls");
//        FileOutputStream fos = null;
//        fos = new FileOutputStream(file);
//        workbook.write(fos);
//	}
//	
//	public static void saveDetailImage(String imageUrl, String savePath, File afterFile) throws MalformedURLException {
//		URL url;
//		//jpeg,png,bmp,wbmp,gif 만 지원을함. webp 지원안함 ㅡㅡ 다른 라이브러리 찾아라  개같음
//		BufferedImage bi = null;
//		try {
//			url = new URL(imageUrl); // 다운로드 할 이미지 URL
//			bi = ImageIO.read(url);
//			
//			Image resizeImage = bi.getScaledInstance(bi.getWidth(), bi.getHeight(), Image.SCALE_SMOOTH);
//			BufferedImage newImage = new BufferedImage(bi.getWidth(), bi.getHeight(), BufferedImage.TYPE_INT_RGB);
//            Graphics g = newImage.getGraphics();
//        	g.drawImage(resizeImage, 0, 0, null);
//            g.dispose();
//			ImageIO.write(newImage, "jpeg", afterFile);
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//	}
//
//	public static void saveThumbImage(String imageUrl, String savePath, File afterFile) throws MalformedURLException {
//		URL url;
//		//jpeg,png,bmp,wbmp,gif 만 지원을함. webp 지원안함 ㅡㅡ 다른 라이브러리 찾아라  개같음
//		BufferedImage bi = null;
//		try {
//			url = new URL(imageUrl); // 다운로드 할 이미지 URL
//			bi = ImageIO.read(url);
//			ImageIO.write(bi, "png", afterFile);
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//	}
}
