package com.somnium.simplyshop;

import com.somnium.simplyshop.entities.Product;
import com.somnium.simplyshop.entities.UserToken;

import java.util.ArrayList;
import java.util.List;

public class GlobalContext {
    public static List<Product> products = new ArrayList<>();
    public static List<UserToken> users = new ArrayList<>();
}
