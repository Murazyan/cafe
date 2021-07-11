package com.sfl.cafe.controller;

import com.sfl.cafe.model.Product;
import com.sfl.cafe.model.Table;
import com.sfl.cafe.model.User;
import com.sfl.cafe.model.dto.WaiterTable;
import com.sfl.cafe.security.CurrentUser;
import com.sfl.cafe.service.ManagerService;
import com.sfl.cafe.service.ProductService;
import com.sfl.cafe.service.TableService;
import com.sfl.cafe.service.WaiterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/manager")
@Slf4j
public class ManagerController {

    private TableService tableService;
    private ManagerService managerService;
    private ProductService productService;
    private WaiterService waiterService;


    @Autowired
    public ManagerController(TableService tableService, ManagerService managerService,
                             ProductService productService, WaiterService waiterService) {
        this.tableService = tableService;
        this.managerService = managerService;
        this.productService = productService;
        this.waiterService = waiterService;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String managerHome(@AuthenticationPrincipal UserDetails userDetails,
                              ModelMap modelMap) {
        modelMap.addAttribute("currentUser", ((CurrentUser) userDetails).getUser());
        modelMap.addAttribute("newUser", new User());
        modelMap.addAttribute("newTable", new Table());
        modelMap.addAttribute("newProduct", new Product());
        modelMap.addAttribute("allWaiters", waiterService.getAllWaiters());
        modelMap.addAttribute("openTables", tableService.getFreeTables());
        modelMap.addAttribute("waiterTable", new WaiterTable());
        return "manager";
    }

    @PostMapping("/add-user")
    @PreAuthorize("hasAuthority('MANAGER')")
    public String addUser(@ModelAttribute User user, RedirectAttributes attributes) {
        boolean isSaved = managerService.addNewUser(user);
        if (!isSaved) {
            attributes.addFlashAttribute("addUserError", "User with username = "+user.getUsername()+" already exists");
        }else {
            attributes.addFlashAttribute("addUserError", null);
        }
        return "redirect:/manager/home";

    }


    @PostMapping("/add-table")
    @PreAuthorize("hasAuthority('MANAGER')")
    public String addTable(@ModelAttribute Table table,
                           @AuthenticationPrincipal CurrentUser currentUser) {
        table = tableService.addTable(table);
        log.info("Added new table :  "+table+" by "+currentUser.getUser().getName());
        return "redirect:/manager/home";

    }

    @PostMapping("/add-product")
    @PreAuthorize("hasAuthority('MANAGER')")
    public String addProduct(@ModelAttribute Product product,
                             @AuthenticationPrincipal CurrentUser currentUser) {
        product = productService.addProduct(product);
        log.info("Added new product :  "+product+" by "+currentUser.getUser().getName());
        return "redirect:/manager/home";
    }

    @PostMapping("/table-assign")
    public String tableAssign(
                              @RequestParam(name = "tableId")int tableId,
                              @RequestParam(name = "waiterId")int waiterId){
        tableService.assignTableToWaiter(tableId, waiterId);
        log.info("Assigen table id = "+tableId+" to user "+waiterId);
        return "redirect:/manager/home";
    }
}
