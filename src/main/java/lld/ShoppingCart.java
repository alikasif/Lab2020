package lld;

import java.util.ArrayList;
import java.util.List;

interface CartItem  {
    public String getName();
    public double getPrice();
    public void setPrice(double price);
}

abstract class Product implements CartItem {
    String name;
    double price;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}

class Card extends Product {
    public Card(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

class TShirt extends Product {
    public TShirt(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

class BackPack extends Product {
    public BackPack(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

interface Coupon {
    void apply(Cart cart);
    void setSuccessor(Coupon successor);
}

class CouponAll implements Coupon {
    private static final float DISCOUNT = 0.25f;
    Coupon successor;
    public void setSuccessor(Coupon successor) {
        this.successor = successor;
    }
    public void apply(Cart cart) {
        for (CartItem item : cart.getItems()) {
            item.setPrice(item.getPrice() - item.getPrice() * DISCOUNT);
        }
        if (successor != null) {
            successor.apply(cart);
        }
    }
}

class CouponNext implements Coupon {
    private static final float DISCOUNT = 0.10f;
    private int discountItemIndex;
    Coupon successor;
    CouponNext(int discountItemIndex)   {
        this.discountItemIndex = discountItemIndex;
    }
    public void setSuccessor(Coupon successor) {
        this.successor = successor;
    }
    public void apply(Cart cart) {
        if( cart.getItems().size() > discountItemIndex)  {
            CartItem cartItem = cart.getItems().get(discountItemIndex);
            cartItem.setPrice(cartItem.getPrice() - cartItem.getPrice() * DISCOUNT);
        }
        if (successor != null) {
            successor.apply(cart);
        }
    }
}

class CouponNextBackPack implements Coupon {
    private static final float DISCOUNT = 0.15f;
    private int discountItemStartIndex;
    Coupon successor;
    Class<?> productType;
    CouponNextBackPack(int discountItemStartIndex ,Class<?> productType)    {
        this.discountItemStartIndex = discountItemStartIndex;
        this.productType = productType;
    }
    public void setSuccessor(Coupon successor) {
        this.successor = successor;
    }
    public void apply(Cart cart) {
        if ( cart.getItems().size() > discountItemStartIndex) {
            for (int i = discountItemStartIndex; i <  cart.getItems().size(); ++i) {
                CartItem cartItem = cart.getItems().get(i);
                if(productType.isInstance(cartItem))    {
                    cartItem.setPrice(cartItem.getPrice() - cartItem.getPrice() * DISCOUNT);
                    break;
                }
            }
        }
        if (successor != null) {
            successor.apply(cart);
        }
    }
}

interface Cart {
    List<CartItem> getItems();
    // void setItems(Set<CartItem> cartItems);
}

public class ShoppingCart implements Cart {
    private List<CartItem> items = new ArrayList<>();
    boolean addItem(CartItem item) {
        return items.add(item);
    }
    boolean removeItem(CartItem item) {
        items.remove(item);
        return true;
    }
    public List<CartItem> getItems() {
        return items;
    }

    public double totalCartValue() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getPrice();
        }
        return total;
    }

    public static void main(String[] args) {
        Product card1 = new Card("Card", 12.99);
        Product card2 = new Card("Card", 12.99);
        Product card3 = new Card("Card", 12.99);
        Product tshirt1 = new TShirt("Tshirt", 24.99);
        Product tshirt2 = new TShirt("Tshirt", 24.99);
        Product backPack1 = new BackPack("BackPack", 34.99);

        ShoppingCart cart = new ShoppingCart();
        cart.addItem(card1);        cart.addItem(card2);        cart.addItem(card3);

        Coupon couponAll = new CouponAll();
        cart.addItem(tshirt1);
        Coupon couponNext = new CouponNext(cart.getItems().size());
        couponAll.setSuccessor(couponNext);
        Coupon couponNextBackPack =null;
        couponNextBackPack = new CouponNextBackPack(cart.getItems().size(),  BackPack.class);
        couponNext.setSuccessor(couponNextBackPack);
        cart.addItem(tshirt2);
        cart.addItem(backPack1);
        System.out.println("Total car value before discounts \t" +cart.totalCartValue());
        couponAll.apply(cart);
        //System.out.println(backPack1.getClass().isInstance(backPack1));
        System.out.println("Total car value after discounts \t" +cart.totalCartValue());
    }
}