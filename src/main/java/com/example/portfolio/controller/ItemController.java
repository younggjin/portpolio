package com.example.portfolio.controller;

import com.example.portfolio.dto.CartDTO;
import com.example.portfolio.service.CartService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class ItemController {

    private final CartService cartService;

    public ItemController(CartService cartService) {
        this.cartService = cartService;
    }


    @PostMapping("/item/item_cart_proc")
    public String cart_proc(Principal principal, CartDTO cartDTO){
        cartService.saveCart(principal, cartDTO);


        return "redirect:/item/cart";
    }

    @GetMapping("/item/cart")
    public String cart(Model model, Principal principal){

        List<CartDTO> cartDTOList = cartService.cartList(principal);
        model.addAttribute("cart", cartDTOList);

        return "/item/cart";
    }
    @PostMapping("/item/cart_modify")
    @ResponseBody
    public void cart_modify(@RequestParam(name="cart_idx")Long idx, @RequestParam(name="item_quantity")int item_quantity){

        cartService.cartModify(idx, item_quantity);

    }

    @GetMapping("/cart/delete/{cart_idx}")
    public String cart_delete(Principal principal, @PathVariable(name="cart_idx") Long idx){
        cartService.cartDelete(principal, idx);

        return "redirect:/item/cart";
    }

    @GetMapping("/item/delivery")
    public String delivery(Principal principal){
        //주문하는 사람
        //배송 상품 목록

        return "/item/delivery";
    }
    @GetMapping("/item/buy_item")
    public String buy_item(){

        return "/item/buy_item";
    }
}
