package com.product.model;

import java.util.ArrayList;

public interface ProductDAO {
	// 추가
	public void productInsert(Product product);
	// 전체보기(카테고리별)
	public ArrayList<Product> productAllfind(String category);
	// 전체보기
	public ArrayList<Product> productAllfind();
	// 상품 검색
	public ArrayList<Product> productSearch(String word);
	// 상세보기
	public Product findById(Long productId);
	// 수정
	public void productUpdate(Product product);
	// 삭제
	public void productDelete(Long productId);
}
