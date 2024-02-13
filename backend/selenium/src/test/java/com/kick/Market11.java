package com.kick;

import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kick.entity.Product;
import com.kick.entity.Product11;
import com.kick.entity.Product11Repository;
import com.kick.entity.ProductRepository;

@SpringBootTest
class Market11 {
    
	@Autowired Product11Repository repository;
	
	public static final String filePath= "C:\\Users\\15kso\\OneDrive\\바탕 화면\\새 폴더\\옵션.xlsx";
	List<String> selectCountOption = new ArrayList<>();
	/////////////////////////////////////////11번가///////////////////////////////////////
	@Test
	//test 코드에서 transactional 사용 하지 마라
	void asd() throws InterruptedException, IOException {
		EdgeDriver driver;
	 	EdgeOptions options = new EdgeOptions();
        driver = new EdgeDriver(options);
        
	    driver.get("https://trycozy.com/member/login.html");
        WebElement member_id=driver.findElement(By.id("member_id"));
        WebElement member_passwd=driver.findElement(By.id("member_passwd"));
        WebElement login=driver.findElement(By.cssSelector("form > div > div > fieldset > a > img"));
        member_id.sendKeys("qudtn6085");
        member_passwd.sendKeys("qudtn4589");
        login.click();
        Thread.sleep(5000);
        
        repository.deleteAll();
        //19까지 함 APPLE
        driver.navigate().to("https://trycozy.com/product/list.html?cate_no=179&page=2");
        
        //변수
        List<String> urls = new ArrayList<>();
        WebElement totalCount=driver.findElement(By.cssSelector("#contents > div.xans-element-.xans-product.xans-product-normalpackage > div.xans-element-.xans-product.xans-product-normalmenu > div > p > strong"));
        //tcnt
        for(int i=2; i<=22;i++) {
        	WebElement nextPage=driver.findElement(By.cssSelector("#contents > div.xans-element-.xans-product.xans-product-normalpaging > p:nth-child(4) > a > img"));
        	List<WebElement> elements = driver.findElements(By.cssSelector("#contents > div.xans-element-.xans-product.xans-product-normalpackage > div.xans-element-.xans-product.xans-product-listnormal > ul > li > div > a"));
        	
        	for(WebElement ele : elements) {
        		urls.add(ele.getAttribute("href"));
        	}
        	nextPage.click();
        }
        
        for(String url : urls) {
        	driver.navigate().to(url);
        	//상세페이지에있는 제목 가격 온라인판매가 제조사
        	WebElement title=driver.findElement(By.cssSelector("#contents > div.xans-element-.xans-product.xans-product-detail > div.detailArea > div.infoArea > h2"));
        	WebElement realPrice=driver.findElement(By.xpath("//*[@id=\"contents\"]/div[2]/div[2]/div[2]/div[3]/table/tbody/tr[4]/td/span"));
        	WebElement price=driver.findElement(By.cssSelector("#contents > div.xans-element-.xans-product.xans-product-detail > div.detailArea > div.infoArea > div.xans-element-.xans-product.xans-product-detaildesign > table > tbody > tr:nth-child(2) > td > strong > span > strong"));
        	
        	//셀렉트박스는 두개값 만 가져옴
        	List<WebElement> selectLists = driver.findElements(By.cssSelector("#contents > div.xans-element-.xans-product.xans-product-detail > div.detailArea > div.infoArea > table:nth-child(10) > tbody > tr > td > select"));
        	StringBuilder selectOption = new StringBuilder();
        	StringBuilder optionQty =new StringBuilder();
        	
        	String uuid = UUID.randomUUID().toString();
        	String uuid2 = UUID.randomUUID().toString();
        	
        	List<String> imgs = new ArrayList<>();
        	List<WebElement> thumbLists = driver.findElements(By.cssSelector("#contents > div.xans-element-.xans-product.xans-product-detail > div.detailArea > div.xans-element-.xans-product.xans-product-image.imgArea > div.keyImg > a > img.BigImage"));
            List<WebElement> detailImageLists = driver.findElements(By.cssSelector("#prdDetail > div > p > img"));
            List<WebElement> detailImageLists2 = driver.findElements(By.cssSelector("#prdDetail > div > p > a > img"));
            String fileName = null;
            String thbFormat = null;
            for(WebElement thmb : thumbLists) {
        		thbFormat = thmb.getAttribute("src").substring(thmb.getAttribute("src").length()-4,thmb.getAttribute("src").length());
        		if(thbFormat.equals(".jpg")||thbFormat.equals(".gif")||thbFormat.equals(".png")||thbFormat.equals(".jpeg")) {
        			String imgUrl = thmb.getAttribute("src");
        			fileName = thmb.getAttribute("src").substring(thmb.getAttribute("src").lastIndexOf("/") + 1);
        			fileName = URLDecoder.decode(fileName,"UTF-8").toString().substring(0, URLDecoder.decode(fileName,"UTF-8").length()-4);
        			String savePath ="C:\\Users\\15kso\\OneDrive\\바탕 화면\\새 폴더\\";
              		File afterFile = new File(savePath + uuid.toString()+".png");
              		saveThumbImage(imgUrl,savePath,afterFile);	
        		}
            }

            String detailName = null;
            StringBuilder sb =new StringBuilder();
            for(WebElement detailImage : detailImageLists) {
            	String imgUrl = detailImage.getAttribute("src");
       		    detailName = detailImage.getAttribute("src").substring(detailImage.getAttribute("src").lastIndexOf("/") + 1);
       			if(URLDecoder.decode(detailName,"UTF-8").toString().substring(0, URLDecoder.decode(detailName,"UTF-8").length()-4).length()<50) {
       				String detailImg = "<p align='center'>"+"<img src='https://gi.esmplus.com/qudtn0295/11detail/"+URLDecoder.decode(detailName,"UTF-8").toString().substring(0, URLDecoder.decode(detailName,"UTF-8").length()-4)+".jpeg"+"'/>"+"</p>";
           			sb.append(detailImg);
            		imgs.add(imgUrl);
       			}else {
       				String detailImg = "<p align='center'>"+"<img src='https://gi.esmplus.com/qudtn0295/11detail/"+uuid2.toString()+".jpeg"+"'/>"+"</p>";
           			sb.append(detailImg);
            		imgs.add(imgUrl);
       			}
       		    	 	
	       		 
            }
            
            String detailName2 = null;
            StringBuilder sb2 =new StringBuilder();
		   	for(WebElement detailImage2 : detailImageLists2) {
		   		 String imgUrl2 = detailImage2.getAttribute("src");
		   	 	 detailName2 = detailImage2.getAttribute("src").substring(detailImage2.getAttribute("src").lastIndexOf("/") + 1);
		   	 	 if(URLDecoder.decode(detailName2,"UTF-8").toString().substring(0, URLDecoder.decode(detailName2,"UTF-8").length()-4).length()<50) {
			   	 	 String detailImg2 = "<p align='center'>"+"<img src='https://gi.esmplus.com/qudtn0295/11detail/"+URLDecoder.decode(detailName2,"UTF-8").toString().substring(0, URLDecoder.decode(detailName2,"UTF-8").length()-4)+".jpeg"+"'/>"+"</p>";
			   	 	 sb2.append(detailImg2);
			   	 	 imgs.add(imgUrl2);
		   	 	 }else {
		   	 		 String detailImg2 = "<p align='center'>"+"<img src='https://gi.esmplus.com/qudtn0295/11detail/"+uuid2.toString()+".jpeg"+"'/>"+"</p>";
			   	 	 sb2.append(detailImg2);
			   	 	 imgs.add(imgUrl2);
		   	 	 }
		   	}
		   	
		   	String detailImgs = null;
		   	for(String img : imgs) {
				 String imageUrl  = img;
				 String savePath ="C:\\Users\\15kso\\OneDrive\\바탕 화면\\detailimage\\";
				 detailImgs = img.substring(img.lastIndexOf("/") + 1);
				 
				 if(URLDecoder.decode(detailImgs,"UTF-8").toString().substring(0, URLDecoder.decode(detailImgs,"UTF-8").length()-4).length() <= 50) {
					 File afterFile = new File(savePath +URLDecoder.decode(detailImgs,"UTF-8").toString().substring(0, URLDecoder.decode(detailImgs,"UTF-8").length()-4)+".jpeg");
					 saveDetailImage(imageUrl,savePath,afterFile);
				 }else {
					 
					 File afterFile = new File(savePath + uuid2.toString()+".jpeg");
					 saveDetailImage(imageUrl,savePath,afterFile);
				 }
				 
			}
		   	
        	int size=0;
        	
        	//셀렉트
        	for(WebElement sel : selectLists) {
        		
        		Select selectOptions = new Select(sel);
        		selectOptions.selectByIndex(2);
	        	List<WebElement> optionList = selectOptions.getOptions();
	        	optionList.remove(0);
	        	
	        	for(WebElement option : optionList) {
	        		if(sel.getAttribute("id").equals("product_option_id1")) {
	        			//optionList에서 개수를 제한하는것도 방법일듯함..
	        			if(option.isEnabled()) {
	        				if(option.getText().equals("갤럭시노트20(N981)")||option.getText().equals("갤럭시노트20울트라(N986)")||option.getText().equals("갤럭시노트10(N971)")||option.getText().equals("갤럭시노트10플러스(N976)")
	        				||option.getText().equals("갤럭시노트9(N960)")||option.getText().equals("갤럭시노트8(N950)")||option.getText().equals("갤럭시노트7/FE(N930)")||option.getText().equals("갤럭시노트5(N920)")				
	        				||option.getText().equals("갤럭시S23(S911)")||option.getText().equals("갤럭시S23플러스(S916)")||option.getText().equals("갤럭시S23울트라(S918)")||option.getText().equals("갤럭시S22(S901)")
	        				||option.getText().equals("갤럭시S22플러스(S906)")||option.getText().equals("갤럭시S22울트라(S908)")||option.getText().equals("갤럭시S21FE(G990)")||option.getText().equals("갤럭시S21(G991)")
	        				||option.getText().equals("갤럭시S21플러스(G996)")||option.getText().equals("갤럭시S21울트라(G998)") ||option.getText().equals("갤럭시S20FE(G781)")
	        				||option.getText().equals("갤럭시S20(G981)")||option.getText().equals("갤럭시S20플러스(G986)") ||option.getText().equals("갤럭시S20울트라(G988)")||option.getText().equals("갤럭시S10E(G970)")
	        				||option.getText().equals("갤럭시S10(G973)")||option.getText().equals("갤럭시S10플러스(G975)") ||option.getText().equals("갤럭시S10 5G(G977)")||option.getText().equals("갤럭시S9(G960)")||option.getText().equals("갤럭시S9플러스(G965)")
	        				) {
	        					
			        			selectOption.append(option.getText()+"|");
			        			optionQty.append("999|");
			        			size++;
	        				}
	        			}
	        		}else {
	        			if(option.isEnabled()) {
	        				//인풋박스로 받기로함
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
        	reRealPrice = reRealPrice.replace("원 이상", "");
        	reRealPrice = reRealPrice.replace("㈜트라이코지", rePrice);
        	reRealPrice = Integer.toString(reInPrice); 
        	String reTitle = title.getText();
        	reTitle = reTitle.replace("[TryCozy]","");
        	reTitle = reTitle.replace("[[ZANMANG LOOPY]","");
        	reTitle = reTitle.replace("트라이코지","[커버핏]");
        	
        	String reOption = selectOption.toString();
        	//플립
        	reOption = reOption.replace("(F731)","");
        	reOption = reOption.replace("(F721)","");
        	reOption = reOption.replace("(F711)","");
        	reOption = reOption.replace("(F711/F721)","");
        	//폴드
        	reOption = reOption.replace("(F916)","");
        	reOption = reOption.replace("(F926)","");
        	reOption = reOption.replace("(F936)","");
        	reOption = reOption.replace("(F946)","");
        	//아이폰
        	reOption = reOption.replace("(6.5)","");
        	reOption = reOption.replace("(5.8)","");
        	reOption = reOption.replace("(6.1)","");
        	//s시리즈
        	reOption = reOption.replace("(N981)","");
        	reOption = reOption.replace("(N986)","");
        	reOption = reOption.replace("(N971)","");
        	reOption = reOption.replace("(N976)","");
        	reOption = reOption.replace("(N960)","");
        	reOption = reOption.replace("(N950)","");
        	reOption = reOption.replace("(N930)","");
        	reOption = reOption.replace("(N920)","");
        	reOption = reOption.replace("(S911)","");
        	reOption = reOption.replace("(S916)","");
        	reOption = reOption.replace("(S918)","");
        	reOption = reOption.replace("(S901)","");
        	reOption = reOption.replace("(S906)","");
        	reOption = reOption.replace("(S908)","");
        	reOption = reOption.replace("(G990)","");
        	reOption = reOption.replace("(G991)","");
        	reOption = reOption.replace("(G996)","");
        	reOption = reOption.replace("(G998)","");
        	reOption = reOption.replace("(G781)","");
        	reOption = reOption.replace("(G981)","");
        	reOption = reOption.replace("(G986)","");
        	reOption = reOption.replace("(G988)","");
        	reOption = reOption.replace("(G970)","");
        	reOption = reOption.replace("(G973)","");
        	reOption = reOption.replace("(G975)","");
        	reOption = reOption.replace("(G977)","");
        	reOption = reOption.replace("(G960)","");
        	reOption = reOption.replace("(G965)","");
        	
        	if(!thbFormat.equals("webp")) {
	        	Product11 products = new Product11();
	        	products.setTitle(reTitle);
	        	products.setOption(reOption.substring(0, reOption.length()-1));
	        	products.setPrice(rePrice);
	        	products.setRealPrice(reRealPrice);
	        	products.setCompany("트라이코지");
	        	products.setDetailImg(sb.toString());
	        	products.setDetailImg2(sb2.toString());
	        	products.setThmb("https://gi.esmplus.com/qudtn0295/11thumb5/"+uuid.toString()+".png");
	        	products.setStockQty(999*size);
	        	products.setOptionQty(optionQty.toString().substring(0, optionQty.toString().length()-1));
	        	repository.save(products);
        	}
        	
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
	    cell.setCellValue("옵션");
	    cell = row.createCell(5);
	    cell.setCellValue("재고 수량");
	    cell = row.createCell(6);
	    cell.setCellValue("재고 값");
	    cell = row.createCell(7);
	    cell.setCellValue("상세페이지 이미지");
	    cell = row.createCell(8);
	    cell.setCellValue("THMB");
	    List<Product11> products = repository.findAll();
	    
//	    long count = repository.count();
	    for(Product11 p : products) {
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
	    	cell.setCellValue(p.getOption());
	    	cell = row.createCell(5);
	    	cell.setCellValue(p.getStockQty());
	    	cell = row.createCell(6);
	    	cell.setCellValue(p.getOptionQty());
	    	cell = row.createCell(7);
	    	cell.setCellValue(p.getDetailImg()+p.getDetailImg2());
	    	cell = row.createCell(8);
	    	cell.setCellValue(p.getThmb());
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
		//jpeg,png,bmp,wbmp,gif 만 지원을함. webp 지원안함 ㅡㅡ 다른 라이브러리 찾아라  개같음
		try {
			URL url = new URL(imageUrl); // 다운로드 할 이미지 URL
			BufferedImage bi = ImageIO.read(url);
			Image resizeImage = bi.getScaledInstance( 600, 600, Image.SCALE_SMOOTH);
			BufferedImage newImage = new BufferedImage( 600, 600, BufferedImage.TYPE_INT_RGB );
			Graphics g = newImage.getGraphics();
			g.drawImage(resizeImage, 0, 0, null);
			g.dispose();
			
			ImageIO.write(newImage, "png", afterFile);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
