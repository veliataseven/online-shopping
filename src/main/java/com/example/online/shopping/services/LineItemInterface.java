package com.example.online.shopping.services;

import com.example.online.shopping.dtos.LineItem.LineItemDTO;
import com.example.online.shopping.models.LineItem;

public interface LineItemInterface {

    float getPrice(LineItem lineItem);

    LineItem createLineItem(LineItemDTO lineItemDTO);
}
