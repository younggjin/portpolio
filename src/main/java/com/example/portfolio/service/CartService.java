package com.example.portfolio.service;

import com.example.portfolio.dto.CartDTO;
import com.example.portfolio.dto.ItemDTO;
import com.example.portfolio.entity.CartEntity;
import com.example.portfolio.entity.ItemEntity;
import com.example.portfolio.entity.MemberEntity;
import com.example.portfolio.repository.CartRepository;
import com.example.portfolio.repository.ItemRepository;
import com.example.portfolio.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    public CartService(CartRepository cartRepository, ItemRepository itemRepository, MemberRepository memberRepository) {

        this.cartRepository = cartRepository;
        this.itemRepository = itemRepository;
        this.memberRepository = memberRepository;
    }

    public void saveCart(Principal principal, CartDTO cartDTO){
        Long item_idx = cartDTO.getItem_idx();

        ItemEntity itemEntity = itemRepository.findById(item_idx).orElse(null);
        MemberEntity memberEntity = memberRepository.findByUserid(principal.getName()).orElse(null);


        CartEntity cartEntity = CartEntity.toCartEntity(cartDTO, principal,itemEntity);

        cartRepository.save(cartEntity);
    }

    @Transactional
    public List<CartDTO> cartList(Principal principal){

        List<CartEntity> cartEntity = cartRepository.findByUserid(principal.getName());
        List<CartDTO> cartDTOList = new ArrayList<>();
        for(CartEntity cart : cartEntity){
            cartDTOList.add(CartDTO.tocartDTO(cart));
        }

        return cartDTOList;
    }

    public void cartDelete(Principal principal, Long idx){
        CartEntity cartEntity = cartRepository.findById(idx).orElse(null);
        if (cartEntity != null) {
            cartRepository.delete(cartEntity);
        }
    }

    public void cartModify(Long idx, int itemQuantity) {

        //System.out.println(itemQuantity);

        CartEntity cartEntity = cartRepository.findById(idx).orElse(null);
        //System.out.println(cartEntity.getIdx());
        cartEntity.setQuantity(itemQuantity);
        cartRepository.save(cartEntity);

    }
}
