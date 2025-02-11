package resources;

import java.util.ArrayList;
import java.util.List;

import Pojo.CreateCategories;
import Pojo.CreateProduct;


public class TestDataBuild {
	
	public CreateCategories createCategoryPayload() {
		
		CreateCategories catreq = new CreateCategories();
		 catreq.setName("Laptop");
		 catreq.setImage("https://i.imgur.com/QkIa5tT.jpeg");
		 return catreq;
	}
	
	public CreateProduct createProductPayload(int Catid, String title,int price,String description) {
		CreateProduct CreProdreq = new CreateProduct();
		 CreProdreq.setTitle(title);
		 CreProdreq.setPrice(23);
		 CreProdreq.setDescription(description);
		 CreProdreq.setCategoryId(Catid);
		 List  <String> ImageList = new ArrayList<String>();
		 ImageList.add("https://i.imgur.com/yVeIeDa.jpeg");
		 ImageList.add("https://placeimg.com/640/480/any");
		 CreProdreq.setImages(ImageList);
		 return CreProdreq;
		
	}
	
	

}
