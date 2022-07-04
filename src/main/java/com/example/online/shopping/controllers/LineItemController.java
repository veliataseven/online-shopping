package com.example.online.shopping.controllers;

import com.example.online.shopping.dtos.LineItem.LineItemDTO;
import com.example.online.shopping.models.LineItem;
import com.example.online.shopping.services.LineItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/lineItem")
@Tag(name = "Line Item")
@Validated
public class LineItemController {

    private final LineItemService lineItemService;

    public LineItemController(LineItemService lineItemService) {
        this.lineItemService = lineItemService;
    }

    @PostMapping(value = "/create", produces = "application/json", consumes = "application/json")
    @Operation(summary = "Create LineItem", description = "Create a line item with line item data.")
    ResponseEntity<LineItem> createLineItem(@RequestBody @Valid LineItemDTO lineItemDTO) {
        LineItem lineItem = lineItemService.createLineItem(lineItemDTO);
        return new ResponseEntity<LineItem>(lineItem, HttpStatus.CREATED);
    }
}
