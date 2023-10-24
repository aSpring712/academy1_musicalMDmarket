package com.product.model;

import java.util.ArrayList;

public interface CartDAO {
	
    void insert(CartDTO cart); //장바구니 추가
    
    ArrayList<CartDTO> listCart(String userid); //회원별 장바구니 목록
    
    int totalPrice(String userid); // 총 지불 가격 계산
    
    void deleteAll(String userid); //장바구니 비우기
    
    void deleteProduct(String userid, int productId, int amount); // 장바구니에서 해당 상품 삭제
}
