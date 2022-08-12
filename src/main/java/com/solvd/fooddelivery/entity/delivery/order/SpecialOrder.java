package com.solvd.fooddelivery.entity.delivery.order;

import com.solvd.fooddelivery.entity.delivery.restaurant.food.Dish;
import com.solvd.fooddelivery.entity.person.Client;
import com.solvd.fooddelivery.entity.person.Courier;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class SpecialOrder extends Order {

    private int vipClientDiscount;

    public SpecialOrder(Courier courier, Client client, int deliveryDistance) {
        super(courier, client, deliveryDistance);
    }

    @Override
    public BigDecimal countOrderPriceWithDiscount(List<Dish> dishes) {
        int totalDiscount = getDiscount() + getVipClientDiscount();
        BigDecimal orderPrice = new BigDecimal(0);
        for (Dish dish : dishes) {
            BigDecimal dishPrice = dish.getPrice().multiply(new BigDecimal(dish.getDishQuantity()));
            orderPrice = orderPrice.add(dishPrice);
        }
        BigDecimal discount = new BigDecimal(totalDiscount).divide(new BigDecimal(100));
        return orderPrice.subtract(orderPrice.multiply(discount)).setScale(2, RoundingMode.CEILING);
    }

    public int getVipClientDiscount() {
        return vipClientDiscount;
    }

    public void setVipClientDiscount(int vipClientDiscount) {
        this.vipClientDiscount = vipClientDiscount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpecialOrder that = (SpecialOrder) o;

        return vipClientDiscount == that.vipClientDiscount;
    }

    @Override
    public int hashCode() {
        return vipClientDiscount;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SpecialOrder{");
        sb.append("vipClientDiscount=").append(vipClientDiscount);
        sb.append('}');
        return sb.toString();
    }
}

