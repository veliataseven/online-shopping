package com.example.online.shopping.controllers;

import com.example.online.shopping.dtos.WebOrder.WebOrderDTO;
import com.example.online.shopping.models.WebOrder;
import com.example.online.shopping.services.WebOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/webOrder")
@Validated
@Tag(name = "Web Order")
public class WebOrderController {

    private final WebOrderService webOrderService;

    public WebOrderController(WebOrderService webOrderService) {
        this.webOrderService = webOrderService;
    }

    @PostMapping(value = "/create", produces = "application/json", consumes = "application/json")
    @Operation(summary = "Create Web Order", description = "Create a web order with web order data.")
    ResponseEntity<WebOrder> createWebOrder(@RequestBody @Valid WebOrderDTO webOrderDTO) {
        WebOrder webOrder = webOrderService.createWebOrder(webOrderDTO);
        return new ResponseEntity<WebOrder>(webOrder, HttpStatus.CREATED);
    }
}
