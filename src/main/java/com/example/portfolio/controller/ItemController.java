package com.example.portfolio.controller;

import com.example.portfolio.dto.CartDTO;
import com.example.portfolio.dto.DeliveryDTO;
import com.example.portfolio.dto.MemberDTO;
import com.example.portfolio.service.CartService;
import com.example.portfolio.service.DeliveryService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@Log4j2
public class ItemController {

    private final CartService cartService;
    private final DeliveryService deliveryService;


    public ItemController(CartService cartService, DeliveryService deliveryService) {
        this.cartService = cartService;
        this.deliveryService = deliveryService;
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

    @RequestMapping()
    public String cartCheck(Principal principal, @RequestParam List<String> itemId){

        return null;
    }

    @GetMapping("/item/delivery")
    public String delivery(Principal principal, Model model, @RequestParam(name="check")List<Long> cartIdx){

        //주문하는 사람

        model.addAttribute("memList", deliveryService.findMember(principal));

        //배송 상품 목록
        List<CartDTO> cartDTOList = new ArrayList<>();
        for(int a=0; a< cartIdx.size(); a++) {
            CartDTO cartDTOLists = deliveryService.findCart(cartIdx.get(a));
            cartDTOList.add(cartDTOLists);
        }

        model.addAttribute("cartList", cartDTOList);


        return "/item/delivery";
    }
    @PostMapping("/item/delevery_proc")
    public String delivery_proc(DeliveryDTO deliveryDTO, Principal principal){

        deliveryService.saveDelivery(deliveryDTO, principal);

        return "redirect:/item/buy_item";
    }
    @GetMapping("/item/buy_item")
    public String buy_item(Model model, Principal principal){

        //구매 회원 memberEntity
        MemberDTO member = deliveryService.findMember(principal);

        model.addAttribute("memberList", member);

        //배송받는 사람 deliveryEntity

        DeliveryDTO delivery = deliveryService.findDelivery(principal);

        model.addAttribute("deliveryList", delivery);

        //구매한 상품 buyEntity


        return "/item/buy_item";
    }
}
