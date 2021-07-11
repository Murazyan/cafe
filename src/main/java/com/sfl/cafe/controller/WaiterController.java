package com.sfl.cafe.controller;


import com.sfl.cafe.model.Order;
import com.sfl.cafe.model.Product;
import com.sfl.cafe.model.ProductInOrder;
import com.sfl.cafe.model.Table;
import com.sfl.cafe.security.CurrentUser;
import com.sfl.cafe.service.OrderService;
import com.sfl.cafe.service.ProductService;
import com.sfl.cafe.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/waiter")
public class WaiterController {

    private TableService tableService;
    private ProductService productService;
    private OrderService orderService;

    @Autowired
    public WaiterController(TableService tableService, OrderService orderService,
                            ProductService productService){
            this.tableService = tableService;
            this.orderService = orderService;
            this.productService = productService;
    }

    @GetMapping("/home")
    public String waiterHome(@AuthenticationPrincipal CurrentUser currentUser,
                             ModelMap modelMap){
        modelMap.addAttribute("currentUser",currentUser.getUser());
        List<Table> tablesForWaiter = tableService.getTablesForWaiter(currentUser.getUser());
        modelMap.addAttribute("myTables", tablesForWaiter);
        modelMap.addAttribute("myOrders", orderService.getOrdersForTables(tablesForWaiter));
        modelMap.addAttribute("newOrder",new Order());
        modelMap.addAttribute("newProduct",new Product());
        modelMap.addAttribute("allProducts",productService.getAllProducts());
        modelMap.addAttribute("newProductInOrder",new ProductInOrder());
        return "waiter";
    }


    @PostMapping("/add-order")
    public String addOrder(@RequestParam(name = "table")Table table){
        orderService.addNewOrder(table);
        return "redirect:/waiter/home";

    }


    @PostMapping("/add-product-to-order")
    public String addProductToOrder(@RequestParam(name = "order")int orderId,
                                    @RequestParam(name = "product")int productId,
                                    @RequestParam(name = "amount")int amount){
        boolean isSaved = orderService.addNewProductToOrder(orderId, productId, amount);
        return "redirect:/waiter/home";
    }


    @PostMapping("/update-product-in-order")
    public String updateProductInOrder(@ModelAttribute ProductInOrder productInOrder){
        productService.updateProductInOrder(productInOrder);
        return "redirect:/waiter/home";
    }

}
