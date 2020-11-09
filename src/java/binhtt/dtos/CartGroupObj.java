package binhtt.dtos;

import binhtt.entities.TblGroup;
import binhtt.entities.TblUser;

import java.io.Serializable;
import java.util.HashMap;

public class CartGroupObj implements Serializable {
    private TblGroup group;
    private HashMap<String, TblUser> cart;

    public CartGroupObj(TblGroup group) {
        this.group = group;
        cart = new HashMap<>();
    }

    public TblGroup getGroup() {
        return group;
    }

    public void setGroup(TblGroup group) {
        this.group = group;
    }

    public HashMap<String, TblUser> getCart() {
        return cart;
    }

    public boolean addToCart(TblUser dto) throws Exception{
        if(this.cart.containsKey(dto.getStudentID())){
            return false;
        }
        this.cart.put(dto.getStudentID(), dto);
        return true;
    }

    public boolean isContains(String id) throws Exception{
        return this.cart.containsKey(id);
    }

    public void remove(String id) throws Exception {
        if(this.cart.containsKey(id)){
            this.cart.remove(id);
        }
    }
}
